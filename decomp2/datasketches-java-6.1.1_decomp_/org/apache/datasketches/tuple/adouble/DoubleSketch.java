package org.apache.datasketches.tuple.adouble;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.UpdatableSketch;

public class DoubleSketch extends UpdatableSketch {
   public DoubleSketch(int lgK, DoubleSummary.Mode mode) {
      this(lgK, ResizeFactor.X8.ordinal(), 1.0F, mode);
   }

   public DoubleSketch(int lgK, int lgResizeFactor, float samplingProbability, DoubleSummary.Mode mode) {
      super(1 << lgK, lgResizeFactor, samplingProbability, new DoubleSummaryFactory(mode));
   }

   /** @deprecated */
   @Deprecated
   public DoubleSketch(Memory mem, DoubleSummary.Mode mode) {
      super(mem, new DoubleSummaryDeserializer(), new DoubleSummaryFactory(mode));
   }

   public void update(String key, Double value) {
      super.update((String)key, value);
   }

   public void update(long key, Double value) {
      super.update(key, value);
   }
}
