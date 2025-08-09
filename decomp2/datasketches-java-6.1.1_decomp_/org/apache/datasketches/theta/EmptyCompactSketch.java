package org.apache.datasketches.theta;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class EmptyCompactSketch extends CompactSketch {
   private static final long EMPTY_SKETCH_MASK = 258385249304575L;
   private static final long EMPTY_SKETCH_TEST = 10995116475137L;
   static final byte[] EMPTY_COMPACT_SKETCH_ARR = new byte[]{1, 3, 3, 0, 0, 30, 0, 0};
   private static final EmptyCompactSketch EMPTY_COMPACT_SKETCH = new EmptyCompactSketch();

   private EmptyCompactSketch() {
   }

   static synchronized EmptyCompactSketch getInstance() {
      return EMPTY_COMPACT_SKETCH;
   }

   static synchronized EmptyCompactSketch getHeapInstance(Memory srcMem) {
      long pre0 = srcMem.getLong(0L);
      if (testCandidatePre0(pre0)) {
         return EMPTY_COMPACT_SKETCH;
      } else {
         long maskedPre0 = pre0 & 258385249304575L;
         throw new SketchesArgumentException("Input Memory does not match required Preamble. Memory Pre0: " + Long.toHexString(maskedPre0) + ", required Pre0: " + Long.toHexString(10995116475137L));
      }
   }

   public CompactSketch compact(boolean dstOrdered, WritableMemory wmem) {
      if (wmem == null) {
         return getInstance();
      } else {
         wmem.putByteArray(0L, EMPTY_COMPACT_SKETCH_ARR, 0, 8);
         return new DirectCompactSketch(wmem);
      }
   }

   static boolean testCandidatePre0(long candidate) {
      return (candidate & 258385249304575L) == 10995116475137L;
   }

   public int getCurrentBytes() {
      return 8;
   }

   public double getEstimate() {
      return (double)0.0F;
   }

   public int getRetainedEntries(boolean valid) {
      return 0;
   }

   public long getThetaLong() {
      return Long.MAX_VALUE;
   }

   public boolean isEmpty() {
      return true;
   }

   public boolean isOrdered() {
      return true;
   }

   public HashIterator iterator() {
      return new HeapCompactHashIterator(new long[0]);
   }

   public byte[] toByteArray() {
      return EMPTY_COMPACT_SKETCH_ARR;
   }

   long[] getCache() {
      return new long[0];
   }

   int getCompactPreambleLongs() {
      return 1;
   }

   int getCurrentPreambleLongs() {
      return 1;
   }

   Memory getMemory() {
      return null;
   }

   short getSeedHash() {
      return 0;
   }
}
