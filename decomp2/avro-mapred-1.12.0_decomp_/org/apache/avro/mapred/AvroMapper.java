package org.apache.avro.mapred;

import java.io.Closeable;
import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapred.Reporter;

public class AvroMapper extends Configured implements JobConfigurable, Closeable {
   public void map(Object datum, AvroCollector collector, Reporter reporter) throws IOException {
      collector.collect(datum);
   }

   public void close() throws IOException {
   }

   public void configure(JobConf jobConf) {
   }
}
