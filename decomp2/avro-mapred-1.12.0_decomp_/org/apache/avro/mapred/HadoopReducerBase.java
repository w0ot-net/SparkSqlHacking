package org.apache.avro.mapred;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

abstract class HadoopReducerBase extends MapReduceBase implements Reducer {
   private AvroReducer reducer;
   private AvroCollector collector;
   private ReduceIterable reduceIterable = new ReduceIterable();

   protected abstract AvroReducer getReducer(JobConf conf);

   protected abstract AvroCollector getCollector(OutputCollector c);

   public void configure(JobConf conf) {
      this.reducer = this.getReducer(conf);
   }

   public final void reduce(AvroKey key, Iterator values, OutputCollector out, Reporter reporter) throws IOException {
      if (this.collector == null) {
         this.collector = this.getCollector(out);
      }

      this.reduceIterable.values = values;
      this.reducer.reduce(key.datum(), this.reduceIterable, this.collector, reporter);
   }

   public void close() throws IOException {
      this.reducer.close();
   }

   class ReduceIterable implements Iterable, Iterator {
      private Iterator values;

      public boolean hasNext() {
         return this.values.hasNext();
      }

      public Object next() {
         return ((AvroValue)this.values.next()).datum();
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      public Iterator iterator() {
         return this;
      }
   }
}
