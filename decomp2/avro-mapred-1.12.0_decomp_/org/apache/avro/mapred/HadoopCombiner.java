package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.util.ReflectionUtils;

class HadoopCombiner extends HadoopReducerBase {
   protected AvroReducer getReducer(JobConf conf) {
      return (AvroReducer)ReflectionUtils.newInstance(conf.getClass("avro.combiner", AvroReducer.class, AvroReducer.class), conf);
   }

   protected AvroCollector getCollector(OutputCollector collector) {
      return new PairCollector(collector);
   }

   private class PairCollector extends AvroCollector {
      private final AvroKey keyWrapper = new AvroKey((Object)null);
      private final AvroValue valueWrapper = new AvroValue((Object)null);
      private OutputCollector collector;

      public PairCollector(OutputCollector collector) {
         this.collector = collector;
      }

      public void collect(Pair datum) throws IOException {
         this.keyWrapper.datum(datum.key());
         this.valueWrapper.datum(datum.value());
         this.collector.collect(this.keyWrapper, this.valueWrapper);
      }
   }
}
