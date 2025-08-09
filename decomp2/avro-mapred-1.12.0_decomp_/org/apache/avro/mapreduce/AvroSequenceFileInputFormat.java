package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.hadoop.io.AvroSequenceFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;

public class AvroSequenceFileInputFormat extends SequenceFileInputFormat {
   public RecordReader createRecordReader(InputSplit inputSplit, TaskAttemptContext context) throws IOException {
      return new AvroSequenceFileRecordReader();
   }

   protected class AvroSequenceFileRecordReader extends RecordReader {
      private SequenceFile.Reader mReader;
      private long mStart;
      private long mEnd;
      private boolean mHasMoreData;
      private Object mCurrentKey;
      private Object mCurrentValue;

      public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
         FileSplit fileSplit = (FileSplit)split;
         Configuration conf = context.getConfiguration();
         Path path = fileSplit.getPath();
         FileSystem fs = path.getFileSystem(conf);
         AvroSequenceFile.Reader.Options options = (new AvroSequenceFile.Reader.Options()).withFileSystem(fs).withInputPath(path).withConfiguration(conf);
         Schema keySchema = AvroJob.getInputKeySchema(conf);
         if (null != keySchema) {
            options.withKeySchema(keySchema);
         }

         Schema valueSchema = AvroJob.getInputValueSchema(conf);
         if (null != valueSchema) {
            options.withValueSchema(valueSchema);
         }

         this.mReader = new AvroSequenceFile.Reader(options);
         this.mEnd = fileSplit.getStart() + fileSplit.getLength();
         if (fileSplit.getStart() > this.mReader.getPosition()) {
            this.mReader.sync(fileSplit.getStart());
         }

         this.mStart = this.mReader.getPosition();
         this.mHasMoreData = this.mStart < this.mEnd;
      }

      public boolean nextKeyValue() throws IOException, InterruptedException {
         if (!this.mHasMoreData) {
            return false;
         } else {
            long pos = this.mReader.getPosition();
            this.mCurrentKey = this.mReader.next(this.mCurrentKey);
            if (null != this.mCurrentKey && (pos < this.mEnd || !this.mReader.syncSeen())) {
               this.mCurrentValue = this.mReader.getCurrentValue(this.mCurrentValue);
            } else {
               this.mHasMoreData = false;
               this.mCurrentKey = null;
               this.mCurrentValue = null;
            }

            return this.mHasMoreData;
         }
      }

      public Object getCurrentKey() {
         return this.mCurrentKey;
      }

      public Object getCurrentValue() {
         return this.mCurrentValue;
      }

      public float getProgress() throws IOException {
         return this.mEnd == this.mStart ? 0.0F : Math.min(1.0F, (float)(this.mReader.getPosition() - this.mStart) / (float)(this.mEnd - this.mStart));
      }

      public synchronized void close() throws IOException {
         this.mReader.close();
      }
   }
}
