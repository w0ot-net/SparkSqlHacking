package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.OutputCollector;

class MapCollector extends AvroCollector {
   private final AvroWrapper wrapper = new AvroWrapper((Object)null);
   private final AvroKey keyWrapper = new AvroKey((Object)null);
   private final AvroValue valueWrapper = new AvroValue((Object)null);
   private OutputCollector collector;
   private boolean isMapOnly;

   public MapCollector(OutputCollector collector, boolean isMapOnly) {
      this.collector = collector;
      this.isMapOnly = isMapOnly;
   }

   public void collect(Object datum) throws IOException {
      if (this.isMapOnly) {
         this.wrapper.datum(datum);
         this.collector.collect(this.wrapper, NullWritable.get());
      } else {
         Pair<K, V> pair = (Pair)datum;
         this.keyWrapper.datum(pair.key());
         this.valueWrapper.datum(pair.value());
         this.collector.collect(this.keyWrapper, this.valueWrapper);
      }

   }
}
