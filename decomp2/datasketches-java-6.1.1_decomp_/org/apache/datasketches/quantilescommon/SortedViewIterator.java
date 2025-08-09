package org.apache.datasketches.quantilescommon;

public class SortedViewIterator {
   protected final long[] cumWeights;
   protected long totalN;
   protected int index;

   SortedViewIterator(long[] cumWeights) {
      this.cumWeights = cumWeights;
      this.totalN = cumWeights.length > 0 ? cumWeights[cumWeights.length - 1] : 0L;
      this.index = -1;
   }

   public long getNaturalRank() {
      return this.cumWeights[this.index];
   }

   public long getNaturalRank(QuantileSearchCriteria searchCrit) {
      if (searchCrit == QuantileSearchCriteria.INCLUSIVE) {
         return this.cumWeights[this.index];
      } else {
         return this.index == 0 ? 0L : this.cumWeights[this.index - 1];
      }
   }

   public long getN() {
      return this.totalN;
   }

   public double getNormalizedRank() {
      return (double)this.getNaturalRank() / (double)this.totalN;
   }

   public double getNormalizedRank(QuantileSearchCriteria searchCrit) {
      return (double)this.getNaturalRank(searchCrit) / (double)this.totalN;
   }

   public long getWeight() {
      return this.index == 0 ? this.cumWeights[0] : this.cumWeights[this.index] - this.cumWeights[this.index - 1];
   }

   public boolean next() {
      ++this.index;
      return this.index < this.cumWeights.length;
   }
}
