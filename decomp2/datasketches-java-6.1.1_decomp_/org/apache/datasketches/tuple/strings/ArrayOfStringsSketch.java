package org.apache.datasketches.tuple.strings;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.UpdatableSketch;
import org.apache.datasketches.tuple.Util;

public class ArrayOfStringsSketch extends UpdatableSketch {
   public ArrayOfStringsSketch() {
      this(12);
   }

   public ArrayOfStringsSketch(int lgK) {
      this(lgK, ResizeFactor.X8, 1.0F);
   }

   public ArrayOfStringsSketch(int lgK, ResizeFactor rf, float p) {
      super(1 << lgK, rf.lg(), p, new ArrayOfStringsSummaryFactory());
   }

   /** @deprecated */
   @Deprecated
   public ArrayOfStringsSketch(Memory mem) {
      super(mem, new ArrayOfStringsSummaryDeserializer(), new ArrayOfStringsSummaryFactory());
   }

   public ArrayOfStringsSketch(ArrayOfStringsSketch sketch) {
      super(sketch);
   }

   public ArrayOfStringsSketch copy() {
      return new ArrayOfStringsSketch(this);
   }

   public void update(String[] strArrKey, String[] strArr) {
      super.update(Util.stringArrHash(strArrKey), strArr);
   }
}
