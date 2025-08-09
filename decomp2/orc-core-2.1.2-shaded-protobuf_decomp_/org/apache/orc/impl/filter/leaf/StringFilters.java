package org.apache.orc.impl.filter.leaf;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.expressions.StringExpr;
import org.apache.orc.impl.filter.LeafFilter;
import org.apache.orc.util.CuckooSetBytes;

class StringFilters {
   private StringFilters() {
   }

   static class StringBetween extends LeafFilter {
      private final byte[] low;
      private final byte[] high;

      StringBetween(String colName, Object low, Object high, boolean negated) {
         super(colName, negated);
         this.low = ((String)low).getBytes(StandardCharsets.UTF_8);
         this.high = ((String)high).getBytes(StandardCharsets.UTF_8);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         BytesColumnVector bv = (BytesColumnVector)v;
         return StringExpr.compare(bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx], this.low, 0, this.low.length) >= 0 && StringExpr.compare(bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx], this.high, 0, this.high.length) <= 0;
      }
   }

   static class StringEquals extends LeafFilter {
      private final byte[] aValue;

      StringEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = ((String)aValue).getBytes(StandardCharsets.UTF_8);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         BytesColumnVector bv = (BytesColumnVector)v;
         return StringExpr.equal(this.aValue, 0, this.aValue.length, bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx]);
      }
   }

   static class StringIn extends LeafFilter {
      private final CuckooSetBytes inSet;

      StringIn(String colName, List values, boolean negated) {
         super(colName, negated);
         byte[][] inValues = new byte[values.size()][];

         for(int i = 0; i < values.size(); ++i) {
            inValues[i] = ((String)values.get(i)).getBytes(StandardCharsets.UTF_8);
         }

         this.inSet = new CuckooSetBytes(inValues.length);
         this.inSet.load(inValues);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         BytesColumnVector bv = (BytesColumnVector)v;
         return this.inSet.lookup(bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx]);
      }
   }

   static class StringLessThan extends LeafFilter {
      private final byte[] aValue;

      StringLessThan(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = ((String)aValue).getBytes(StandardCharsets.UTF_8);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         BytesColumnVector bv = (BytesColumnVector)v;
         return StringExpr.compare(bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx], this.aValue, 0, this.aValue.length) < 0;
      }
   }

   static class StringLessThanEquals extends LeafFilter {
      private final byte[] aValue;

      StringLessThanEquals(String colName, Object aValue, boolean negated) {
         super(colName, negated);
         this.aValue = ((String)aValue).getBytes(StandardCharsets.UTF_8);
      }

      protected boolean allow(ColumnVector v, int rowIdx) {
         BytesColumnVector bv = (BytesColumnVector)v;
         return StringExpr.compare(bv.vector[rowIdx], bv.start[rowIdx], bv.length[rowIdx], this.aValue, 0, this.aValue.length) <= 0;
      }
   }
}
