package org.apache.datasketches.quantiles;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public abstract class UpdateDoublesSketch extends DoublesSketch {
   UpdateDoublesSketch(int k) {
      super(k);
   }

   public static UpdateDoublesSketch wrap(WritableMemory srcMem) {
      return DirectUpdateDoublesSketch.wrapInstance(srcMem);
   }

   public abstract void update(double var1);

   public static UpdateDoublesSketch heapify(Memory srcMem) {
      return HeapUpdateDoublesSketch.heapifyInstance(srcMem);
   }

   public CompactDoublesSketch compact() {
      return this.compact((WritableMemory)null);
   }

   public CompactDoublesSketch compact(WritableMemory dstMem) {
      return (CompactDoublesSketch)(dstMem == null ? HeapCompactDoublesSketch.createFromUpdateSketch(this) : DirectCompactDoublesSketch.createFromUpdateSketch(this, dstMem));
   }

   boolean isCompact() {
      return false;
   }

   abstract void putMinItem(double var1);

   abstract void putMaxItem(double var1);

   abstract void putN(long var1);

   abstract void putCombinedBuffer(double[] var1);

   abstract void putBaseBufferCount(int var1);

   abstract void putBitPattern(long var1);

   abstract double[] growCombinedBuffer(int var1, int var2);
}
