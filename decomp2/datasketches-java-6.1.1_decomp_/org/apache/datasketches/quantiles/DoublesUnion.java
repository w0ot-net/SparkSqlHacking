package org.apache.datasketches.quantiles;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public abstract class DoublesUnion {
   public static DoublesUnionBuilder builder() {
      return new DoublesUnionBuilder();
   }

   public static DoublesUnion heapify(DoublesSketch sketch) {
      return DoublesUnionImpl.heapifyInstance(sketch);
   }

   public static DoublesUnion heapify(Memory srcMem) {
      return DoublesUnionImpl.heapifyInstance(srcMem);
   }

   public static DoublesUnion wrap(Memory mem) {
      return DoublesUnionImplR.wrapInstance(mem);
   }

   public static DoublesUnion wrap(WritableMemory mem) {
      return DoublesUnionImpl.wrapInstance(mem);
   }

   public abstract boolean hasMemory();

   public abstract boolean isDirect();

   public abstract boolean isEmpty();

   public abstract int getMaxK();

   public abstract int getEffectiveK();

   public abstract void union(DoublesSketch var1);

   public abstract void union(Memory var1);

   public abstract void update(double var1);

   public abstract UpdateDoublesSketch getResult();

   public abstract UpdateDoublesSketch getResult(WritableMemory var1);

   public abstract UpdateDoublesSketch getResultAndReset();

   public abstract void reset();

   public abstract byte[] toByteArray();

   public abstract String toString();

   public abstract String toString(boolean var1, boolean var2);

   public abstract boolean isSameResource(Memory var1);
}
