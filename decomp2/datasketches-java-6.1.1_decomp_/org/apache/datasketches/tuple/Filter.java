package org.apache.datasketches.tuple;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class Filter {
   private final Predicate predicate;

   public Filter(Predicate predicate) {
      this.predicate = predicate;
   }

   public CompactSketch filter(Sketch sketchIn) {
      if (sketchIn == null) {
         return new CompactSketch((long[])null, (Summary[])null, Long.MAX_VALUE, true);
      } else {
         long[] hashes = new long[sketchIn.getRetainedEntries()];
         T[] summaries = null;
         int i = 0;
         TupleSketchIterator<T> it = sketchIn.iterator();

         while(it.next()) {
            T summary = (T)it.getSummary();
            if (this.predicate.test(summary)) {
               hashes[i] = it.getHash();
               if (summaries == null) {
                  summaries = (T[])((Summary[])((Summary[])Array.newInstance(summary.getClass(), sketchIn.getRetainedEntries())));
               }

               summaries[i++] = summary.copy();
            }
         }

         boolean isEmpty = i == 0 && !sketchIn.isEstimationMode();
         return i == 0 ? new CompactSketch((long[])null, (Summary[])null, sketchIn.getThetaLong(), isEmpty) : new CompactSketch(Arrays.copyOf(hashes, i), (Summary[])Arrays.copyOf(summaries, i), sketchIn.getThetaLong(), isEmpty);
      }
   }
}
