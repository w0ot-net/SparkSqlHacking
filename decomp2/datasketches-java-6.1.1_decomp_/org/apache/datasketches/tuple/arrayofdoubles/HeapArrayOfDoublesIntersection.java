package org.apache.datasketches.tuple.arrayofdoubles;

final class HeapArrayOfDoublesIntersection extends ArrayOfDoublesIntersection {
   HeapArrayOfDoublesIntersection(int numValues, long seed) {
      super(numValues, seed);
   }

   protected ArrayOfDoublesQuickSelectSketch createSketch(int nomEntries, int numValues, long seed) {
      return new HeapArrayOfDoublesQuickSelectSketch(nomEntries, 0, 1.0F, numValues, seed);
   }
}
