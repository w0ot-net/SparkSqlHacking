package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.util.ReflectionUtils;

class HadoopReducer extends HadoopReducerBase {
   protected AvroReducer getReducer(JobConf conf) {
      return (AvroReducer)ReflectionUtils.newInstance(conf.getClass("avro.reducer", AvroReducer.class, AvroReducer.class), conf);
   }

   protected AvroCollector getCollector(OutputCollector collector) {
      return new ReduceCollector(collector);
   }

   private class ReduceCollector extends AvroCollector {
      private final AvroWrapper wrapper = new AvroWrapper((Object)null);
      private OutputCollector out;

      public ReduceCollector(OutputCollector out) {
         this.out = out;
      }

      public void collect(Object datum) throws IOException {
         this.wrapper.datum(datum);
         this.out.collect(this.wrapper, NullWritable.get());
      }
   }
}
