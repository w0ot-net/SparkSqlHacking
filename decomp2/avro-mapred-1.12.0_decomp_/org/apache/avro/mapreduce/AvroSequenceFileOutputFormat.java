package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.hadoop.io.AvroSequenceFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;

public class AvroSequenceFileOutputFormat extends FileOutputFormat {
   public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
      Configuration conf = context.getConfiguration();
      CompressionCodec codec = null;
      SequenceFile.CompressionType compressionType = CompressionType.NONE;
      if (getCompressOutput(context)) {
         compressionType = getOutputCompressionType(conf);
         Class<?> codecClass = getOutputCompressorClass(context, DefaultCodec.class);
         codec = (CompressionCodec)ReflectionUtils.newInstance(codecClass, conf);
      }

      Path outputFile = this.getDefaultWorkFile(context, "");
      FileSystem fs = outputFile.getFileSystem(conf);
      AvroSequenceFile.Writer.Options options = (new AvroSequenceFile.Writer.Options()).withFileSystem(fs).withConfiguration(conf).withOutputPath(outputFile).withKeyClass(context.getOutputKeyClass()).withValueClass(context.getOutputValueClass()).withProgressable(context).withCompressionType(compressionType).withCompressionCodec(codec);
      Schema keySchema = AvroJob.getOutputKeySchema(conf);
      if (null != keySchema) {
         options.withKeySchema(keySchema);
      }

      Schema valueSchema = AvroJob.getOutputValueSchema(conf);
      if (null != valueSchema) {
         options.withValueSchema(valueSchema);
      }

      final SequenceFile.Writer out = AvroSequenceFile.createWriter(options);
      return new RecordWriter() {
         public void write(Object key, Object value) throws IOException {
            out.append(key, value);
         }

         public void close(TaskAttemptContext context) throws IOException {
            out.close();
         }
      };
   }

   public static void setOutputCompressionType(Job job, SequenceFile.CompressionType compressionType) {
      setCompressOutput(job, true);
      job.getConfiguration().set("mapreduce.output.fileoutputformat.compress.type", compressionType.name());
   }

   public static SequenceFile.CompressionType getOutputCompressionType(Configuration conf) {
      String typeName = conf.get("mapreduce.output.fileoutputformat.compress.type");
      return typeName != null ? CompressionType.valueOf(typeName) : SequenceFile.getDefaultCompressionType(conf);
   }
}
