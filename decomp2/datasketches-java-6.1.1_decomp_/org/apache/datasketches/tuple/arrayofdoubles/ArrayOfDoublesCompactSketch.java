package org.apache.datasketches.tuple.arrayofdoubles;

public abstract class ArrayOfDoublesCompactSketch extends ArrayOfDoublesSketch {
   static final byte serialVersionUID = 1;
   static final int EMPTY_SIZE = 16;
   static final int RETAINED_ENTRIES_INT = 16;
   static final int ENTRIES_START = 24;

   ArrayOfDoublesCompactSketch(int numValues) {
      super(numValues);
   }

   public int getCurrentBytes() {
      int count = this.getRetainedEntries();
      int sizeBytes = 16;
      if (count > 0) {
         sizeBytes = 24 + 8 * count + 8 * this.numValues_ * count;
      }

      return sizeBytes;
   }

   public int getMaxBytes() {
      return this.getCurrentBytes();
   }
}
