package org.apache.hadoop.hive.cli;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.RCFile;
import org.apache.hadoop.hive.ql.io.RCFileRecordReader;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.columnar.BytesRefWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class RCFileCat implements Tool {
   private static final int STRING_BUFFER_SIZE = 16384;
   private static final int STRING_BUFFER_FLUSH_SIZE = 14336;
   private static final int STDOUT_BUFFER_SIZE = 131072;
   private static final int RECORD_PRINT_INTERVAL = 1048576;
   protected boolean test = false;
   private CharsetDecoder decoder;
   Configuration conf = null;
   private static final String TAB = "\t";
   private static final String NEWLINE = "\r\n";
   private static final String Usage = "RCFileCat [--start=start_offet] [--length=len] [--verbose] [--column-sizes | --column-sizes-pretty] [--file-sizes] fileName";

   public RCFileCat() {
      this.decoder = Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
   }

   public int run(String[] args) throws Exception {
      long start = 0L;
      long length = -1L;
      int recordCount = 0;
      long startT = System.currentTimeMillis();
      boolean verbose = false;
      boolean columnSizes = false;
      boolean pretty = false;
      boolean fileSizes = false;
      if (args.length >= 1 && args.length <= 3) {
         Path fileName = null;

         for(int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.startsWith("--start=")) {
               start = Long.parseLong(arg.substring("--start=".length()));
            } else if (arg.startsWith("--length=")) {
               length = Long.parseLong(arg.substring("--length=".length()));
            } else if (arg.equals("--verbose")) {
               verbose = true;
            } else if (arg.equals("--column-sizes")) {
               columnSizes = true;
            } else if (arg.equals("--column-sizes-pretty")) {
               columnSizes = true;
               pretty = true;
            } else if (arg.equals("--file-sizes")) {
               fileSizes = true;
            } else {
               if (fileName != null) {
                  printUsage((String)null);
                  return -1;
               }

               fileName = new Path(arg);
            }
         }

         this.setupBufferedOutput();
         FileSystem fs = FileSystem.get(fileName.toUri(), this.conf);
         long fileLen = fs.getFileStatus(fileName).getLen();
         if (start < 0L) {
            start = 0L;
         }

         if (start > fileLen) {
            return 0;
         } else {
            if (length < 0L || start + length > fileLen) {
               length = fileLen - start;
            }

            FileSplit split = new FileSplit(fileName, start, length, new JobConf(this.conf));
            RCFileRecordReader recordReader = new RCFileRecordReader(this.conf, split);
            if (!columnSizes && !fileSizes) {
               LongWritable key = new LongWritable();
               BytesRefArrayWritable value = new BytesRefArrayWritable();
               StringBuilder buf = new StringBuilder(16384);

               while(recordReader.next(key, value)) {
                  this.printRecord(value, buf);
                  ++recordCount;
                  if (verbose && recordCount % 1048576 == 0) {
                     long now = System.currentTimeMillis();
                     System.err.println("Read " + recordCount / 1024 + "k records");
                     System.err.println("Read " + recordReader.getPos() / 1048576L + "MB");
                     System.err.printf("Input scan rate %.2f MB/s\n", (double)recordReader.getPos() * (double)1.0F / (double)(now - startT) / (double)1024.0F);
                  }

                  if (buf.length() > 14336) {
                     System.out.print(buf.toString());
                     buf.setLength(0);
                  }
               }

               System.out.print(buf.toString());
               System.out.flush();
               return 0;
            } else {
               long[] compressedColumnSizes = null;
               long[] uncompressedColumnSizes = null;
               long rowNo = 0L;
               long uncompressedFileSize = 0L;

               long compressedFileSize;
               RCFile.KeyBuffer keyBuffer;
               for(compressedFileSize = 0L; recordReader.nextBlock(); rowNo += (long)keyBuffer.getNumberRows()) {
                  keyBuffer = recordReader.getKeyBuffer();
                  if (uncompressedColumnSizes == null) {
                     uncompressedColumnSizes = new long[keyBuffer.getColumnNumber()];
                  }

                  if (compressedColumnSizes == null) {
                     compressedColumnSizes = new long[keyBuffer.getColumnNumber()];
                  }

                  for(int i = 0; i < keyBuffer.getColumnNumber(); ++i) {
                     uncompressedColumnSizes[i] += (long)keyBuffer.getEachColumnUncompressedValueLen()[i];
                     compressedColumnSizes[i] += (long)keyBuffer.getEachColumnValueLen()[i];
                  }
               }

               if (columnSizes && uncompressedColumnSizes != null && compressedColumnSizes != null) {
                  for(int i = 0; i < uncompressedColumnSizes.length; ++i) {
                     if (pretty) {
                        System.out.println("Column " + i + ": Uncompressed size: " + uncompressedColumnSizes[i] + " Compressed size: " + compressedColumnSizes[i]);
                     } else {
                        System.out.print(i + "\t" + uncompressedColumnSizes[i] + "\t" + compressedColumnSizes[i] + "\r\n");
                     }
                  }
               }

               if (fileSizes) {
                  if (uncompressedColumnSizes != null && compressedColumnSizes != null) {
                     for(int i = 0; i < uncompressedColumnSizes.length; ++i) {
                        uncompressedFileSize += uncompressedColumnSizes[i];
                        compressedFileSize += compressedColumnSizes[i];
                     }
                  }

                  System.out.print("File size (uncompressed): " + uncompressedFileSize + ". File size (compressed): " + compressedFileSize + ". Number of rows: " + rowNo + "." + "\r\n");
               }

               System.out.flush();
               return 0;
            }
         }
      } else {
         printUsage((String)null);
         return -1;
      }
   }

   private void printRecord(BytesRefArrayWritable value, StringBuilder buf) throws IOException {
      int n = value.size();
      if (n > 0) {
         BytesRefWritable v = value.unCheckedGet(0);
         ByteBuffer bb = ByteBuffer.wrap(v.getData(), v.getStart(), v.getLength());
         buf.append(this.decoder.decode(bb));

         for(int i = 1; i < n; ++i) {
            buf.append("\t");
            v = value.unCheckedGet(i);
            bb = ByteBuffer.wrap(v.getData(), v.getStart(), v.getLength());
            buf.append(this.decoder.decode(bb));
         }

         buf.append("\r\n");
      }

   }

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public static void main(String[] args) {
      try {
         Configuration conf = new Configuration();
         RCFileCat instance = new RCFileCat();
         instance.setConf(conf);
         ToolRunner.run(instance, args);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("\n\n\n");
         printUsage(e.getMessage());
         System.exit(1);
      }

   }

   private void setupBufferedOutput() {
      OutputStream pdataOut;
      if (this.test) {
         pdataOut = System.out;
      } else {
         pdataOut = new FileOutputStream(FileDescriptor.out);
      }

      BufferedOutputStream bos = new BufferedOutputStream(pdataOut, 131072);
      PrintStream ps = new PrintStream(bos, false);
      System.setOut(ps);
   }

   private static void printUsage(String errorMsg) {
      System.err.println("RCFileCat [--start=start_offet] [--length=len] [--verbose] [--column-sizes | --column-sizes-pretty] [--file-sizes] fileName");
      if (errorMsg != null) {
         System.err.println(errorMsg);
      }

   }
}
