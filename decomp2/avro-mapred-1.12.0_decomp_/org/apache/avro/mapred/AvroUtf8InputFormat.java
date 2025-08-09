package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.avro.util.Utf8;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class AvroUtf8InputFormat extends FileInputFormat implements JobConfigurable {
   private CompressionCodecFactory compressionCodecs = null;

   public void configure(JobConf conf) {
      this.compressionCodecs = new CompressionCodecFactory(conf);
   }

   protected boolean isSplitable(FileSystem fs, Path file) {
      return this.compressionCodecs.getCodec(file) == null;
   }

   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      reporter.setStatus(split.toString());
      return new Utf8LineRecordReader(job, (FileSplit)split);
   }

   static class Utf8LineRecordReader implements RecordReader {
      private LineRecordReader lineRecordReader;
      private LongWritable currentKeyHolder = new LongWritable();
      private Text currentValueHolder = new Text();

      public Utf8LineRecordReader(Configuration job, FileSplit split) throws IOException {
         this.lineRecordReader = new LineRecordReader(job, split);
      }

      public void close() throws IOException {
         this.lineRecordReader.close();
      }

      public long getPos() throws IOException {
         return this.lineRecordReader.getPos();
      }

      public float getProgress() throws IOException {
         return this.lineRecordReader.getProgress();
      }

      public boolean next(AvroWrapper key, NullWritable value) throws IOException {
         boolean success = this.lineRecordReader.next(this.currentKeyHolder, this.currentValueHolder);
         if (success) {
            key.datum((new Utf8(this.currentValueHolder.getBytes())).setByteLength(this.currentValueHolder.getLength()));
         } else {
            key.datum((Object)null);
         }

         return success;
      }

      public AvroWrapper createKey() {
         return new AvroWrapper((Object)null);
      }

      public NullWritable createValue() {
         return NullWritable.get();
      }
   }
}
