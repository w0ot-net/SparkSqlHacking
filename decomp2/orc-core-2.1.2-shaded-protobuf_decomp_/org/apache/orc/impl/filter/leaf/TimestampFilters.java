package org.apache.orc.impl.filter.leaf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.orc.impl.filter.LeafFilter;

class TimestampFilters {
   private TimestampFilters() {
   }

   static class TimestampBetween extends LeafFilter {
      private final Timestamp low;
      private final Timestamp high;

      TimestampBetween(String colName, Object low, Object high, boolean negated) {
         super(colName, negated);
         this.low = (Timestamp)low;
         this.high = (Timestamp)high;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((TimestampColumnVector)v).compareTo(rowIdx, this.low) >= 0 && ((TimestampColumnVector)v).compareTo(rowIdx, this.high) <= 0;
      }
   }

   static class TimestampEquals extends LeafFilter {
      private final Timestamp aValue;

      TimestampEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Timestamp)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((TimestampColumnVector)v).compareTo(rowIdx, this.aValue) == 0;
      }
   }

   static class TimestampIn extends LeafFilter {
      private final Set inValues;

      TimestampIn(String colName, List values, boolean negated) {
         super(colName, negated);
         this.inValues = new HashSet(values.size());

         for(Object value : values) {
            this.inValues.add((Timestamp)value);
         }

      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return this.inValues.contains(((TimestampColumnVector)v).asScratchTimestamp(rowIdx));
      }
   }

   static class TimestampLessThan extends LeafFilter {
      private final Timestamp aValue;

      TimestampLessThan(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Timestamp)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((TimestampColumnVector)v).compareTo(rowIdx, this.aValue) < 0;
      }
   }

   static class TimestampLessThanEquals extends LeafFilter {
      private final Timestamp aValue;

      TimestampLessThanEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = (Timestamp)aValue;
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         return ((TimestampColumnVector)v).compareTo(rowIdx, this.aValue) <= 0;
      }
   }
}
