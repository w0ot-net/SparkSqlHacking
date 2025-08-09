package org.apache.datasketches.quantilescommon;

public interface SortedView {
   long[] getCumulativeWeights();

   long getN();

   int getNumRetained();

   boolean isEmpty();

   SortedViewIterator iterator();
}
