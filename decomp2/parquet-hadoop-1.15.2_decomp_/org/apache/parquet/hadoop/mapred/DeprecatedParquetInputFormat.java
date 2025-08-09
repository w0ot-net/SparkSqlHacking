package org.apache.parquet.hadoop.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.parquet.hadoop.Footer;
import org.apache.parquet.hadoop.ParquetInputFormat;
import org.apache.parquet.hadoop.ParquetInputSplit;
import org.apache.parquet.hadoop.ParquetRecordReader;

public class DeprecatedParquetInputFormat extends FileInputFormat {
   protected ParquetInputFormat realInputFormat = new ParquetInputFormat();

   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      return new RecordReaderWrapper(split, job, reporter);
   }

   public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
      if (isTaskSideMetaData(job)) {
         return super.getSplits(job, numSplits);
      } else {
         List<Footer> footers = this.getFooters(job);
         List<ParquetInputSplit> splits = this.realInputFormat.getSplits(job, footers);
         if (splits == null) {
            return null;
         } else {
            InputSplit[] resultSplits = new InputSplit[splits.size()];
            int i = 0;

            for(ParquetInputSplit split : splits) {
               resultSplits[i++] = new ParquetInputSplitWrapper(split);
            }

            return resultSplits;
         }
      }
   }

   public List getFooters(JobConf job) throws IOException {
      return this.realInputFormat.getFooters(job, (List)Arrays.asList(super.listStatus(job)));
   }

   public static boolean isTaskSideMetaData(JobConf job) {
      return job.getBoolean("parquet.task.side.metadata", Boolean.TRUE);
   }

   private static class RecordReaderWrapper implements RecordReader {
      private ParquetRecordReader realReader;
      private long splitLen;
      private Container valueContainer = null;
      private boolean firstRecord = false;
      private boolean eof = false;

      public RecordReaderWrapper(InputSplit oldSplit, JobConf oldJobConf, Reporter reporter) throws IOException {
         this.splitLen = oldSplit.getLength();

         try {
            this.realReader = new ParquetRecordReader(ParquetInputFormat.getReadSupportInstance((Configuration)oldJobConf), ParquetInputFormat.getFilter((Configuration)oldJobConf));
            if (oldSplit instanceof ParquetInputSplitWrapper) {
               this.realReader.initialize(((ParquetInputSplitWrapper)oldSplit).realSplit, oldJobConf, reporter);
            } else {
               if (!(oldSplit instanceof FileSplit)) {
                  throw new IllegalArgumentException("Invalid split (not a FileSplit or ParquetInputSplitWrapper): " + oldSplit);
               }

               this.realReader.initialize((FileSplit)oldSplit, oldJobConf, reporter);
            }

            this.valueContainer = new Container();
            if (this.realReader.nextKeyValue()) {
               this.firstRecord = true;
               this.valueContainer.set(this.realReader.getCurrentValue());
            } else {
               this.eof = true;
            }

         } catch (InterruptedException e) {
            Thread.interrupted();
            throw new IOException(e);
         }
      }

      public void close() throws IOException {
         this.realReader.close();
      }

      public Void createKey() {
         return null;
      }

      public Container createValue() {
         return this.valueContainer;
      }

      public long getPos() throws IOException {
         return (long)((float)this.splitLen * this.getProgress());
      }

      public float getProgress() throws IOException {
         try {
            return this.realReader.getProgress();
         } catch (InterruptedException e) {
            Thread.interrupted();
            throw new IOException(e);
         }
      }

      public boolean next(Void key, Container value) throws IOException {
         if (this.eof) {
            return false;
         } else if (this.firstRecord) {
            value.set(this.valueContainer.get());
            this.firstRecord = false;
            return true;
         } else {
            try {
               if (this.realReader.nextKeyValue()) {
                  if (value != null) {
                     value.set(this.realReader.getCurrentValue());
                  }

                  return true;
               }
            } catch (InterruptedException e) {
               throw new IOException(e);
            }

            this.eof = true;
            return false;
         }
      }
   }

   private static class ParquetInputSplitWrapper implements InputSplit {
      ParquetInputSplit realSplit;

      public ParquetInputSplitWrapper() {
      }

      public ParquetInputSplitWrapper(ParquetInputSplit realSplit) {
         this.realSplit = realSplit;
      }

      public long getLength() throws IOException {
         return this.realSplit.getLength();
      }

      public String[] getLocations() throws IOException {
         return this.realSplit.getLocations();
      }

      public void readFields(DataInput in) throws IOException {
         this.realSplit = new ParquetInputSplit();
         this.realSplit.readFields(in);
      }

      public void write(DataOutput out) throws IOException {
         this.realSplit.write(out);
      }
   }
}
