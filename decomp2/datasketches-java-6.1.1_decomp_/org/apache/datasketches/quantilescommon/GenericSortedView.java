package org.apache.datasketches.quantilescommon;

import java.util.Comparator;
import org.apache.datasketches.common.SketchesArgumentException;

public interface GenericSortedView extends PartitioningFeature, SketchPartitionLimits, SortedView {
   default double[] getCDF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         validateItems(splitPoints, this.getComparator());
         int len = splitPoints.length + 1;
         double[] buckets = new double[len];

         for(int i = 0; i < len - 1; ++i) {
            buckets[i] = this.getRank(splitPoints[i], searchCrit);
         }

         buckets[len - 1] = (double)1.0F;
         return buckets;
      }
   }

   Comparator getComparator();

   Object getMaxItem();

   Object getMinItem();

   default double[] getPMF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         validateItems(splitPoints, this.getComparator());
         double[] buckets = this.getCDF(splitPoints, searchCrit);

         for(int i = buckets.length; i-- > 1; buckets[i] -= buckets[i - 1]) {
         }

         return buckets;
      }
   }

   Object getQuantile(double var1, QuantileSearchCriteria var3);

   Object[] getQuantiles();

   double getRank(Object var1, QuantileSearchCriteria var2);

   GenericSortedViewIterator iterator();

   static void validateItems(Object[] items, Comparator comparator) {
      int len = items.length;
      if (len == 1 && items[0] == null) {
         throw new SketchesArgumentException("Items must be unique, monotonically increasing and not null.");
      } else {
         for(int j = 0; j < len - 1; ++j) {
            if (items[j] == null || items[j + 1] == null || comparator.compare(items[j], items[j + 1]) >= 0) {
               throw new SketchesArgumentException("Items must be unique, monotonically increasing and not null.");
            }
         }

      }
   }
}
