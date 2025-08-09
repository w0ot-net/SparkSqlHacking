package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.ReflectionUtils;

class HadoopMapper extends MapReduceBase implements Mapper {
   private AvroMapper mapper;
   private MapCollector out;
   private boolean isMapOnly;

   public void configure(JobConf conf) {
      this.mapper = (AvroMapper)ReflectionUtils.newInstance(conf.getClass("avro.mapper", AvroMapper.class, AvroMapper.class), conf);
      this.isMapOnly = conf.getNumReduceTasks() == 0;
   }

   public void map(AvroWrapper wrapper, NullWritable value, OutputCollector collector, Reporter reporter) throws IOException {
      if (this.out == null) {
         this.out = new MapCollector(collector, this.isMapOnly);
      }

      this.mapper.map(wrapper.datum(), this.out, reporter);
   }

   public void close() throws IOException {
      this.mapper.close();
   }
}
