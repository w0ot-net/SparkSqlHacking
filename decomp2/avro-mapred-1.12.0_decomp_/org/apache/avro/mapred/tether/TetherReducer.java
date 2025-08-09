package org.apache.avro.mapred.tether;

import java.io.IOException;
import java.util.Iterator;
import org.apache.avro.mapred.AvroJob;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

class TetherReducer implements Reducer {
   private JobConf job;
   private TetheredProcess process;
   private boolean error;

   public void configure(JobConf job) {
      this.job = job;
   }

   public void reduce(TetherData datum, Iterator ignore, OutputCollector collector, Reporter reporter) throws IOException {
      try {
         if (this.process == null) {
            this.process = new TetheredProcess(this.job, collector, reporter);
            this.process.inputClient.configure(TaskType.REDUCE, AvroJob.getMapOutputSchema(this.job).toString(), AvroJob.getOutputSchema(this.job).toString());
         }

         this.process.inputClient.input(datum.buffer(), (long)datum.count());
      } catch (IOException e) {
         this.error = true;
         throw e;
      } catch (Exception e) {
         this.error = true;
         throw new IOException(e);
      }
   }

   public void close() throws IOException {
      if (this.process != null) {
         try {
            if (this.error) {
               this.process.inputClient.abort();
            } else {
               this.process.inputClient.complete();
            }

            this.process.outputService.waitForFinish();
         } catch (InterruptedException e) {
            throw new IOException(e);
         } finally {
            this.process.close();
         }

      }
   }
}
