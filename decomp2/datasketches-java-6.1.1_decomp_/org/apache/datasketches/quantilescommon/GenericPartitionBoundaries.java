package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.common.SketchesStateException;

public final class GenericPartitionBoundaries {
   private long totalN;
   private Object[] boundaries;
   private long[] natRanks;
   private double[] normRanks;
   private Object maxItem;
   private Object minItem;
   private QuantileSearchCriteria searchCrit;
   private long[] numDeltaItems;
   private int numPartitions;

   public GenericPartitionBoundaries(long totalN, Object[] boundaries, long[] natRanks, double[] normRanks, Object maxItem, Object minItem, QuantileSearchCriteria searchCrit) {
      this.totalN = totalN;
      this.boundaries = boundaries;
      this.natRanks = natRanks;
      this.normRanks = normRanks;
      this.maxItem = maxItem;
      this.minItem = minItem;
      this.searchCrit = searchCrit;
      int len = boundaries.length;
      if (len < 2) {
         throw new SketchesStateException("Source sketch is empty");
      } else {
         this.numDeltaItems = new long[len];
         this.numDeltaItems[0] = 0L;

         for(int i = 1; i < len; ++i) {
            int addOne = (i != 1 || this.searchCrit != QuantileSearchCriteria.INCLUSIVE) && (i != len - 1 || this.searchCrit != QuantileSearchCriteria.EXCLUSIVE) ? 0 : 1;
            this.numDeltaItems[i] = natRanks[i] - natRanks[i - 1] + (long)addOne;
         }

         this.numPartitions = len - 1;
      }
   }

   public long getN() {
      return this.totalN;
   }

   public Object[] getBoundaries() {
      return this.boundaries.clone();
   }

   public long[] getNaturalRanks() {
      return (long[])this.natRanks.clone();
   }

   public double[] getNormalizedRanks() {
      return (double[])this.normRanks.clone();
   }

   public long[] getNumDeltaItems() {
      return (long[])this.numDeltaItems.clone();
   }

   public int getNumPartitions() {
      return this.numPartitions;
   }

   public Object getMaxItem() {
      return this.maxItem;
   }

   public Object getMinItem() {
      return this.minItem;
   }

   public QuantileSearchCriteria getSearchCriteria() {
      return this.searchCrit;
   }
}
