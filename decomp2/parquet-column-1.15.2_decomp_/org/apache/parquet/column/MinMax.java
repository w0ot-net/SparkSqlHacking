package org.apache.parquet.column;

import org.apache.parquet.schema.PrimitiveComparator;

public final class MinMax {
   private Object min = null;
   private Object max = null;

   public MinMax(PrimitiveComparator comparator, Iterable iterable) {
      this.getMinAndMax(comparator, iterable);
   }

   public Object getMin() {
      return this.min;
   }

   public Object getMax() {
      return this.max;
   }

   private void getMinAndMax(PrimitiveComparator comparator, Iterable iterable) {
      iterable.forEach((element) -> {
         if (this.max == null) {
            this.max = element;
         } else if (element != null && comparator.compare(this.max, element) < 0) {
            this.max = element;
         }

         if (this.min == null) {
            this.min = element;
         } else if (element != null && comparator.compare(this.min, element) > 0) {
            this.min = element;
         }

      });
   }
}
