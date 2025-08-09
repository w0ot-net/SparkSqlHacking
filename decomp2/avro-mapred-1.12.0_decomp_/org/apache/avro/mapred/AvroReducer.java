package org.apache.avro.mapred;

import java.io.Closeable;
import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapred.Reporter;

public class AvroReducer extends Configured implements JobConfigurable, Closeable {
   private Pair outputPair;

   public void reduce(Object key, Iterable values, AvroCollector collector, Reporter reporter) throws IOException {
      if (this.outputPair == null) {
         this.outputPair = new Pair(AvroJob.getOutputSchema(this.getConf()));
      }

      for(Object value : values) {
         this.outputPair.set(key, value);
         collector.collect(this.outputPair);
      }

   }

   public void close() throws IOException {
   }

   public void configure(JobConf jobConf) {
   }
}
