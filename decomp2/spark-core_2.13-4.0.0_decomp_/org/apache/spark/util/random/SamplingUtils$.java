package org.apache.spark.util.random;

import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class SamplingUtils$ {
   public static final SamplingUtils$ MODULE$ = new SamplingUtils$();

   public Tuple2 reservoirSampleAndCount(final Iterator input, final int k, final long seed, final ClassTag evidence$1) {
      Object reservoir = evidence$1.newArray(k);

      int i;
      for(i = 0; i < k && input.hasNext(); ++i) {
         Object item = input.next();
         .MODULE$.array_update(reservoir, i, item);
      }

      if (i < k) {
         Object trimReservoir = evidence$1.newArray(i);
         System.arraycopy(reservoir, 0, trimReservoir, 0, i);
         return new Tuple2(trimReservoir, BoxesRunTime.boxToLong((long)i));
      } else {
         long l = (long)i;
         XORShiftRandom rand = new XORShiftRandom(seed);

         while(input.hasNext()) {
            Object item = input.next();
            ++l;
            long replacementIndex = (long)(rand.nextDouble() * (double)l);
            if (replacementIndex < (long)k) {
               .MODULE$.array_update(reservoir, (int)replacementIndex, item);
            }
         }

         return new Tuple2(reservoir, BoxesRunTime.boxToLong(l));
      }
   }

   public long reservoirSampleAndCount$default$3() {
      return scala.util.Random..MODULE$.nextLong();
   }

   public double computeFractionForSampleSize(final int sampleSizeLowerBound, final long total, final boolean withReplacement) {
      if (withReplacement) {
         return PoissonBounds$.MODULE$.getUpperBound((double)sampleSizeLowerBound) / (double)total;
      } else {
         double fraction = (double)sampleSizeLowerBound / (double)total;
         return BinomialBounds$.MODULE$.getUpperBound(1.0E-4, total, fraction);
      }
   }

   private SamplingUtils$() {
   }
}
