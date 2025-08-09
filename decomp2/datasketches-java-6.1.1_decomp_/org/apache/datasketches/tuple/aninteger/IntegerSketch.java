package org.apache.datasketches.tuple.aninteger;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.UpdatableSketch;

public class IntegerSketch extends UpdatableSketch {
   public IntegerSketch(int lgK, IntegerSummary.Mode mode) {
      this(lgK, ResizeFactor.X8.ordinal(), 1.0F, mode);
   }

   public IntegerSketch(int lgK, int lgResizeFactor, float samplingProbability, IntegerSummary.Mode mode) {
      super(1 << lgK, lgResizeFactor, samplingProbability, new IntegerSummaryFactory(mode));
   }

   /** @deprecated */
   @Deprecated
   public IntegerSketch(Memory mem, IntegerSummary.Mode mode) {
      super(mem, new IntegerSummaryDeserializer(), new IntegerSummaryFactory(mode));
   }

   public void update(String key, Integer value) {
      super.update((String)key, value);
   }

   public void update(long key, Integer value) {
      super.update(key, value);
   }
}
