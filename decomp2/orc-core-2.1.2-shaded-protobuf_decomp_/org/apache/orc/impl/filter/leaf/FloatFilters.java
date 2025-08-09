package org.apache.orc.impl.filter.leaf;

import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.orc.impl.filter.LeafFilter;

class FloatFilters {
   private FloatFilters() {
   }

   static class FloatBetween extends LeafFilter {
      private final double low;
      private final double high;

      FloatBetween(String colName, Object low, Object high, boolean negated) {
         super(colName, negated);
         this.low = (Double)low;
         this.high = (Double)high;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DoubleColumnVector)v).vector[rowIdx] >= this.low && ((DoubleColumnVector)v).vector[rowIdx] <= this.high;
      }
   }

   static class FloatEquals extends LeafFilter {
      private final double aValue;

      FloatEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Double)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DoubleColumnVector)v).vector[rowIdx] == this.aValue;
      }
   }

   static class FloatIn extends LeafFilter {
      private final double[] inValues;

      FloatIn(String colName, List values, boolean negated) {
         super(colName, negated);
         this.inValues = new double[values.size()];

         for(int i = 0; i < values.size(); ++i) {
            this.inValues[i] = (Double)values.get(i);
         }

         Arrays.sort(this.inValues);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return Arrays.binarySearch(this.inValues, ((DoubleColumnVector)v).vector[rowIdx]) >= 0;
      }
   }

   static class FloatLessThan extends LeafFilter {
      private final double aValue;

      FloatLessThan(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Double)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DoubleColumnVector)v).vector[rowIdx] < this.aValue;
      }
   }

   static class FloatLessThanEquals extends LeafFilter {
      private final double aValue;

      FloatLessThanEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Double)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DoubleColumnVector)v).vector[rowIdx] <= this.aValue;
      }
   }
}
