package org.apache.orc.impl.filter.leaf;

import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.orc.impl.filter.LeafFilter;

class LongFilters {
   private LongFilters() {
   }

   static class LongBetween extends LeafFilter {
      private final long low;
      private final long high;

      LongBetween(String colName, Object low, Object high, boolean negated) {
         super(colName, negated);
         this.low = (Long)low;
         this.high = (Long)high;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((LongColumnVector)v).vector[rowIdx] >= this.low && ((LongColumnVector)v).vector[rowIdx] <= this.high;
      }
   }

   static class LongEquals extends LeafFilter {
      private final long aValue;

      LongEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Long)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((LongColumnVector)v).vector[rowIdx] == this.aValue;
      }
   }

   static class LongIn extends LeafFilter {
      private final long[] inValues;

      LongIn(String colName, List values, boolean negated) {
         super(colName, negated);
         this.inValues = new long[values.size()];

         for(int i = 0; i < values.size(); ++i) {
            this.inValues[i] = (Long)values.get(i);
         }

         Arrays.sort(this.inValues);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return Arrays.binarySearch(this.inValues, ((LongColumnVector)v).vector[rowIdx]) >= 0;
      }
   }

   static class LongLessThan extends LeafFilter {
      private final long aValue;

      LongLessThan(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Long)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((LongColumnVector)v).vector[rowIdx] < this.aValue;
      }
   }

   static class LongLessThanEquals extends LeafFilter {
      private final long aValue;

      LongLessThanEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Long)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((LongColumnVector)v).vector[rowIdx] <= this.aValue;
      }
   }
}
