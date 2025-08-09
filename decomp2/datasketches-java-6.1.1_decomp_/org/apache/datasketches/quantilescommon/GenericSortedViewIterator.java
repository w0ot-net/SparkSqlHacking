package org.apache.datasketches.quantilescommon;

public class GenericSortedViewIterator extends SortedViewIterator {
   private final Object[] quantiles;

   public GenericSortedViewIterator(Object[] quantiles, long[] cumWeights) {
      super(cumWeights);
      this.quantiles = quantiles;
   }

   public Object getQuantile() {
      return this.quantiles[this.index];
   }

   public Object getQuantile(QuantileSearchCriteria searchCrit) {
      if (searchCrit == QuantileSearchCriteria.INCLUSIVE) {
         return this.quantiles[this.index];
      } else {
         return this.index == 0 ? null : this.quantiles[this.index - 1];
      }
   }
}
