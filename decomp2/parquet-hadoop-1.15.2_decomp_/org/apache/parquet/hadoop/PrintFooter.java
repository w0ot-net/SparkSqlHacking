package org.apache.parquet.hadoop;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.util.HiddenFileFilter;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.schema.MessageType;

public class PrintFooter {
   private static Map stats = new LinkedHashMap();
   private static int blockCount = 0;
   private static long recordCount = 0L;

   public static void main(String[] args) throws Exception {
      if (args.length != 1) {
         System.err.println("usage PrintFooter <path>");
      } else {
         Path path = new Path(new URI(args[0]));
         Configuration configuration = new Configuration();
         FileSystem fs = path.getFileSystem(configuration);
         FileStatus fileStatus = fs.getFileStatus(path);
         Path summary = new Path(fileStatus.getPath(), "_metadata");
         if (fileStatus.isDir() && fs.exists(summary)) {
            System.out.println("reading summary file");
            FileStatus summaryStatus = fs.getFileStatus(summary);

            for(Footer footer : ParquetFileReader.readSummaryFile(configuration, summaryStatus)) {
               add(footer.getParquetMetadata());
            }
         } else {
            List<FileStatus> statuses;
            if (fileStatus.isDir()) {
               System.out.println("listing files in " + fileStatus.getPath());
               statuses = Arrays.asList(fs.listStatus(fileStatus.getPath(), HiddenFileFilter.INSTANCE));
            } else {
               statuses = new ArrayList();
               statuses.add(fileStatus);
            }

            System.out.println("opening " + statuses.size() + " files");
            int i = 0;
            ExecutorService threadPool = Executors.newFixedThreadPool(5);

            try {
               long t0 = System.currentTimeMillis();
               Deque<Future<ParquetMetadata>> footers = new LinkedBlockingDeque();

               for(FileStatus currentFile : statuses) {
                  footers.add(threadPool.submit(() -> {
                     try {
                        return ParquetFileReader.readFooter(configuration, currentFile, ParquetMetadataConverter.NO_FILTER);
                     } catch (Exception e) {
                        throw new ParquetDecodingException("could not read footer", e);
                     }
                  }));
               }

               int previousPercent = 0;
               int n = 60;
               System.out.print("0% [");

               for(int j = 0; j < n; ++j) {
                  System.out.print(" ");
               }

               System.out.print("] 100%");

               for(int j = 0; j < n + 6; ++j) {
                  System.out.print('\b');
               }

               while(!footers.isEmpty()) {
                  Future<ParquetMetadata> futureFooter = (Future)footers.removeFirst();
                  if (!futureFooter.isDone()) {
                     footers.addLast(futureFooter);
                  } else {
                     ParquetMetadata footer = (ParquetMetadata)futureFooter.get();
                     ++i;

                     for(int currentPercent = i * n / statuses.size(); currentPercent > previousPercent; ++previousPercent) {
                        System.out.print("*");
                     }

                     add(footer);
                  }
               }

               System.out.println("");
               long t1 = System.currentTimeMillis();
               System.out.println("read all footers in " + (t1 - t0) + " ms");
            } finally {
               threadPool.shutdownNow();
            }
         }

         Set<Map.Entry<ColumnDescriptor, ColStats>> entries = stats.entrySet();
         long total = 0L;
         long totalUnc = 0L;

         for(Map.Entry entry : entries) {
            ColStats colStats = (ColStats)entry.getValue();
            total += colStats.allStats.total;
            totalUnc += colStats.uncStats.total;
         }

         for(Map.Entry entry : entries) {
            ColStats colStats = (ColStats)entry.getValue();
            System.out.println(entry.getKey() + " " + percent(colStats.allStats.total, total) + "% of all space " + colStats);
         }

         System.out.println("number of blocks: " + blockCount);
         System.out.println("total data size: " + humanReadable(total) + " (raw " + humanReadable(totalUnc) + ")");
         System.out.println("total record: " + humanReadable(recordCount));
         System.out.println("average block size: " + humanReadable(total / (long)blockCount) + " (raw " + humanReadable(totalUnc / (long)blockCount) + ")");
         System.out.println("average record count: " + humanReadable(recordCount / (long)blockCount));
      }
   }

   private static void add(ParquetMetadata footer) {
      for(BlockMetaData blockMetaData : footer.getBlocks()) {
         ++blockCount;
         MessageType schema = footer.getFileMetaData().getSchema();
         recordCount += blockMetaData.getRowCount();

         for(ColumnChunkMetaData columnMetaData : blockMetaData.getColumns()) {
            ColumnDescriptor desc = schema.getColumnDescription(columnMetaData.getPath().toArray());
            add(desc, columnMetaData.getValueCount(), columnMetaData.getTotalSize(), columnMetaData.getTotalUncompressedSize(), columnMetaData.getEncodings(), columnMetaData.getStatistics());
         }
      }

   }

   private static void printTotalString(String message, long total, long totalUnc) {
      System.out.println("total " + message + ": " + humanReadable(total) + " (raw " + humanReadable(totalUnc) + " saved " + percentComp(totalUnc, total) + "%)");
   }

   private static float percentComp(long raw, long compressed) {
      return percent(raw - compressed, raw);
   }

   private static float percent(long numerator, long denominator) {
      return (float)(numerator * 1000L / denominator) / 10.0F;
   }

   private static String humanReadable(long size) {
      if (size < 1000L) {
         return String.valueOf(size);
      } else {
         long currentSize = size;
         long previousSize = size * 1000L;
         int count = 0;

         String[] unit;
         for(unit = new String[]{"", "K", "M", "G", "T", "P"}; currentSize >= 1000L; ++count) {
            previousSize = currentSize;
            currentSize /= 1000L;
         }

         return (float)previousSize / 1000.0F + unit[count];
      }
   }

   private static void add(ColumnDescriptor desc, long valueCount, long size, long uncSize, Collection encodings, Statistics colValuesStats) {
      ColStats colStats = (ColStats)stats.get(desc);
      if (colStats == null) {
         colStats = new ColStats();
         stats.put(desc, colStats);
      }

      colStats.add(valueCount, size, uncSize, encodings, colValuesStats);
   }

   private static class Stats {
      long min;
      long max;
      long total;

      private Stats() {
         this.min = Long.MAX_VALUE;
         this.max = Long.MIN_VALUE;
         this.total = 0L;
      }

      public void add(long length) {
         this.min = Math.min(length, this.min);
         this.max = Math.max(length, this.max);
         this.total += length;
      }

      public String toString(int blocks) {
         return "min: " + PrintFooter.humanReadable(this.min) + " max: " + PrintFooter.humanReadable(this.max) + " average: " + PrintFooter.humanReadable(this.total / (long)blocks) + " total: " + PrintFooter.humanReadable(this.total);
      }
   }

   private static class ColStats {
      Stats valueCountStats;
      Stats allStats;
      Stats uncStats;
      Set encodings;
      Statistics colValuesStats;
      int blocks;

      private ColStats() {
         this.valueCountStats = new Stats();
         this.allStats = new Stats();
         this.uncStats = new Stats();
         this.encodings = new TreeSet();
         this.colValuesStats = null;
         this.blocks = 0;
      }

      public void add(long valueCount, long size, long uncSize, Collection encodings, Statistics colValuesStats) {
         ++this.blocks;
         this.valueCountStats.add(valueCount);
         this.allStats.add(size);
         this.uncStats.add(uncSize);
         this.encodings.addAll(encodings);
         this.colValuesStats = colValuesStats;
      }

      public String toString() {
         long raw = this.uncStats.total;
         long compressed = this.allStats.total;
         return this.encodings + " " + this.allStats.toString(this.blocks) + " (raw data: " + PrintFooter.humanReadable(raw) + (raw == 0L ? "" : " saving " + (raw - compressed) * 100L / raw + "%") + ")\n  values: " + this.valueCountStats.toString(this.blocks) + "\n  uncompressed: " + this.uncStats.toString(this.blocks) + "\n  column values statistics: " + this.colValuesStats.toString();
      }
   }
}
