package org.apache.hadoop.hive.common.io;

import com.google.common.collect.MinMaxPriorityQueue;
import java.io.OutputStream;
import java.util.Comparator;

public class SortPrintStream extends FetchConverter {
   private static final Comparator STR_COMP = new Comparator() {
      public int compare(String o1, String o2) {
         return o1.compareTo(o2);
      }
   };
   protected final MinMaxPriorityQueue outputs;

   public SortPrintStream(OutputStream out, String encoding) throws Exception {
      super(out, false, encoding);
      this.outputs = MinMaxPriorityQueue.orderedBy(STR_COMP).create();
   }

   public void process(String out) {
      assert out != null;

      this.outputs.add(out);
   }

   public void processFinal() {
      while(!this.outputs.isEmpty()) {
         this.printDirect((String)this.outputs.removeFirst());
      }

   }
}
