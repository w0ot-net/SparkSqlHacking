package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.ReflectionUtils;

class DelegatingMapper extends MapReduceBase implements Mapper {
   AvroMapper mapper;
   JobConf conf;
   boolean isMapOnly;
   AvroCollector out;

   public void configure(JobConf conf) {
      this.conf = conf;
      this.isMapOnly = conf.getNumReduceTasks() == 0;
   }

   public void map(AvroWrapper wrapper, NullWritable value, OutputCollector collector, Reporter reporter) throws IOException {
      if (this.mapper == null) {
         TaggedInputSplit is = (TaggedInputSplit)reporter.getInputSplit();
         Class<? extends AvroMapper> mapperClass = is.getMapperClass();
         this.mapper = (AvroMapper)ReflectionUtils.newInstance(mapperClass, this.conf);
      }

      if (this.out == null) {
         this.out = new MapCollector(collector, this.isMapOnly);
      }

      this.mapper.map(wrapper.datum(), this.out, reporter);
   }
}
