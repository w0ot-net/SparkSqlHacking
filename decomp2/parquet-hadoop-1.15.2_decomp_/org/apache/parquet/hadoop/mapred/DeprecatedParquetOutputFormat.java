package org.apache.parquet.hadoop.mapred;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.util.Progressable;
import org.apache.parquet.hadoop.ParquetOutputFormat;
import org.apache.parquet.hadoop.ParquetRecordWriter;
import org.apache.parquet.hadoop.codec.CodecConfig;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

public class DeprecatedParquetOutputFormat extends FileOutputFormat {
   protected ParquetOutputFormat realOutputFormat = new ParquetOutputFormat();

   public static void setWriteSupportClass(Configuration configuration, Class writeSupportClass) {
      configuration.set("parquet.write.support.class", writeSupportClass.getName());
   }

   public static void setBlockSize(Configuration configuration, int blockSize) {
      configuration.setInt("parquet.block.size", blockSize);
   }

   public static void setPageSize(Configuration configuration, int pageSize) {
      configuration.setInt("parquet.page.size", pageSize);
   }

   public static void setCompression(Configuration configuration, CompressionCodecName compression) {
      configuration.set("parquet.compression", compression.name());
   }

   public static void setEnableDictionary(Configuration configuration, boolean enableDictionary) {
      configuration.setBoolean("parquet.enable.dictionary", enableDictionary);
   }

   public static void setAsOutputFormat(JobConf jobConf) {
      jobConf.setOutputFormat(DeprecatedParquetOutputFormat.class);
      jobConf.setOutputCommitter(MapredParquetOutputCommitter.class);
   }

   private CompressionCodecName getCodec(JobConf conf) {
      return CodecConfig.from(conf).getCodec();
   }

   private static Path getDefaultWorkFile(JobConf conf, String name, String extension) {
      String file = getUniqueName(conf, name) + extension;
      return new Path(getWorkOutputPath(conf), file);
   }

   public RecordWriter getRecordWriter(FileSystem fs, JobConf conf, String name, Progressable progress) throws IOException {
      return new RecordWriterWrapper(this.realOutputFormat, fs, conf, name, progress);
   }

   private class RecordWriterWrapper implements RecordWriter {
      private ParquetRecordWriter realWriter;

      public RecordWriterWrapper(ParquetOutputFormat realOutputFormat, FileSystem fs, JobConf conf, String name, Progressable progress) throws IOException {
         CompressionCodecName codec = DeprecatedParquetOutputFormat.this.getCodec(conf);
         String extension = codec.getExtension() + ".parquet";
         Path file = DeprecatedParquetOutputFormat.getDefaultWorkFile(conf, name, extension);

         try {
            this.realWriter = (ParquetRecordWriter)realOutputFormat.getRecordWriter((Configuration)conf, file, (CompressionCodecName)codec);
         } catch (InterruptedException e) {
            Thread.interrupted();
            throw new IOException(e);
         }
      }

      public void close(Reporter reporter) throws IOException {
         try {
            this.realWriter.close((TaskAttemptContext)null);
         } catch (InterruptedException e) {
            Thread.interrupted();
            throw new IOException(e);
         }
      }

      public void write(Void key, Object value) throws IOException {
         try {
            this.realWriter.write(key, value);
         } catch (InterruptedException e) {
            Thread.interrupted();
            throw new IOException(e);
         }
      }
   }
}
