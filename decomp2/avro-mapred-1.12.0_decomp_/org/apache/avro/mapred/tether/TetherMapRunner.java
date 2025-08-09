package org.apache.avro.mapred.tether;

import java.io.IOException;
import org.apache.avro.mapred.AvroJob;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.Counters;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapRunner;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TetherMapRunner extends MapRunner {
   private static final Logger LOG = LoggerFactory.getLogger(TetherMapRunner.class);
   private JobConf job;
   private TetheredProcess process;

   public void configure(JobConf job) {
      this.job = job;
   }

   public void run(RecordReader recordReader, OutputCollector collector, Reporter reporter) throws IOException {
      try {
         this.process = new TetheredProcess(this.job, collector, reporter);
         LOG.info("send configure to subprocess for map task");
         this.process.inputClient.configure(TaskType.MAP, this.job.get("avro.input.schema"), AvroJob.getMapOutputSchema(this.job).toString());
         LOG.info("send partitions to subprocess for map task");
         this.process.inputClient.partitions(this.job.getNumReduceTasks());
         Counters.Counter inputRecordCounter = reporter.getCounter("org.apache.hadoop.mapred.Task$Counter", "MAP_INPUT_RECORDS");
         TetherData data = new TetherData();

         while(recordReader.next(data, NullWritable.get())) {
            this.process.inputClient.input(data.buffer(), (long)data.count());
            inputRecordCounter.increment((long)(data.count() - 1));
            if (this.process.outputService.isFinished()) {
               break;
            }
         }

         LOG.info("send complete to subprocess for map task");
         this.process.inputClient.complete();
         if (this.process.outputService.waitForFinish()) {
            throw new IOException("Task failed: " + this.process.outputService.error());
         }
      } catch (Throwable t) {
         LOG.warn("Task failed", t);
         this.process.inputClient.abort();
         throw new IOException("Task failed: " + t, t);
      } finally {
         if (this.process != null) {
            this.process.close();
         }

      }

   }
}
