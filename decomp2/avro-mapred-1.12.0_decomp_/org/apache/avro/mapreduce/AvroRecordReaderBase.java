package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.avro.io.DatumReader;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AvroRecordReaderBase extends RecordReader {
   private static final Logger LOG = LoggerFactory.getLogger(AvroRecordReaderBase.class);
   private final Schema mReaderSchema;
   private Object mCurrentRecord;
   private DataFileReader mAvroFileReader;
   private long mStartPosition;
   private long mEndPosition;

   protected AvroRecordReaderBase(Schema readerSchema) {
      this.mReaderSchema = readerSchema;
      this.mCurrentRecord = null;
   }

   public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
      if (!(inputSplit instanceof FileSplit)) {
         throw new IllegalArgumentException("Only compatible with FileSplits.");
      } else {
         FileSplit fileSplit = (FileSplit)inputSplit;
         SeekableInput seekableFileInput = this.createSeekableInput(context.getConfiguration(), fileSplit.getPath());
         Configuration conf = context.getConfiguration();
         GenericData dataModel = AvroSerialization.createDataModel(conf);
         DatumReader<T> datumReader = dataModel.createDatumReader(this.mReaderSchema);
         this.mAvroFileReader = this.createAvroFileReader(seekableFileInput, datumReader);
         this.mAvroFileReader.sync(fileSplit.getStart());
         this.mStartPosition = this.mAvroFileReader.previousSync();
         this.mEndPosition = fileSplit.getStart() + fileSplit.getLength();
      }
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      assert null != this.mAvroFileReader;

      if (this.mAvroFileReader.hasNext() && !this.mAvroFileReader.pastSync(this.mEndPosition)) {
         this.mCurrentRecord = this.mAvroFileReader.next(this.mCurrentRecord);
         return true;
      } else {
         return false;
      }
   }

   public float getProgress() throws IOException, InterruptedException {
      assert null != this.mAvroFileReader;

      if (this.mEndPosition == this.mStartPosition) {
         return 0.0F;
      } else {
         long bytesRead = this.mAvroFileReader.previousSync() - this.mStartPosition;
         long bytesTotal = this.mEndPosition - this.mStartPosition;
         LOG.debug("Progress: bytesRead=" + bytesRead + ", bytesTotal=" + bytesTotal);
         return Math.min(1.0F, (float)bytesRead / (float)bytesTotal);
      }
   }

   public void close() throws IOException {
      if (null != this.mAvroFileReader) {
         try {
            this.mAvroFileReader.close();
         } finally {
            this.mAvroFileReader = null;
         }
      }

   }

   protected Object getCurrentRecord() {
      return this.mCurrentRecord;
   }

   protected SeekableInput createSeekableInput(Configuration conf, Path path) throws IOException {
      return new FsInput(path, conf);
   }

   protected DataFileReader createAvroFileReader(SeekableInput input, DatumReader datumReader) throws IOException {
      return new DataFileReader(input, datumReader);
   }
}
