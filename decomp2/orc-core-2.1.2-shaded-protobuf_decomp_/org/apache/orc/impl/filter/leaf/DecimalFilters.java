package org.apache.orc.impl.filter.leaf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.impl.filter.LeafFilter;

class DecimalFilters {
   private DecimalFilters() {
   }

   static class DecimalBetween extends LeafFilter {
      private final HiveDecimalWritable low;
      private final HiveDecimalWritable high;

      DecimalBetween(String colName, Object low, Object high, boolean negated) {
         super(colName, negated);
         this.low = (HiveDecimalWritable)low;
         this.high = (HiveDecimalWritable)high;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DecimalColumnVector)v).vector[rowIdx].compareTo(this.low) >= 0 && ((DecimalColumnVector)v).vector[rowIdx].compareTo(this.high) <= 0;
      }
   }

   static class DecimalEquals extends LeafFilter {
      private final HiveDecimalWritable aValue;

      DecimalEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (HiveDecimalWritable)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DecimalColumnVector)v).vector[rowIdx].compareTo(this.aValue) == 0;
      }
   }

   static class DecimalIn extends LeafFilter {
      private final Set inValues;

      DecimalIn(String colName, List values, boolean negated) {
         super(colName, negated);
         this.inValues = new HashSet(values.size());

         for(Object value : values) {
            this.inValues.add((HiveDecimalWritable)value);
         }

      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return this.inValues.contains(((DecimalColumnVector)v).vector[rowIdx]);
      }
   }

   static class DecimalLessThan extends LeafFilter {
      private final HiveDecimalWritable aValue;

      DecimalLessThan(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (HiveDecimalWritable)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DecimalColumnVector)v).vector[rowIdx].compareTo(this.aValue) < 0;
      }
   }

   static class DecimalLessThanEquals extends LeafFilter {
      private final HiveDecimalWritable aValue;

      DecimalLessThanEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (HiveDecimalWritable)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((DecimalColumnVector)v).vector[rowIdx].compareTo(this.aValue) <= 0;
      }
   }
}
