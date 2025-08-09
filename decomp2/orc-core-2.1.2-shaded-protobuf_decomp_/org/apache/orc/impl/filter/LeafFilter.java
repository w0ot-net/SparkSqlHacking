package org.apache.orc.impl.filter;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.OrcFilterContext;

public abstract class LeafFilter implements VectorFilter {
   private final String colName;
   private final boolean negated;

   public String getColName() {
      return this.colName;
   }

   protected LeafFilter(String colName, boolean negated) {
      this.colName = colName;
      this.negated = negated;
   }

   public void filter(OrcFilterContext fc, Selected bound, Selected selOut) {
      ColumnVector[] branch = fc.findColumnVector(this.colName);
      ColumnVector v = branch[branch.length - 1];
      boolean noNulls = OrcFilterContext.noNulls(branch);
      int currSize = 0;
      if (v.isRepeating) {
         if (!OrcFilterContext.isNull(branch, 0) && this.allowWithNegation(v, 0)) {
            for(int i = 0; i < bound.selSize; ++i) {
               int rowIdx = bound.sel[i];
               selOut.sel[currSize++] = rowIdx;
            }
         }
      } else if (noNulls) {
         for(int i = 0; i < bound.selSize; ++i) {
            int rowIdx = bound.sel[i];
            if (this.allowWithNegation(v, rowIdx)) {
               selOut.sel[currSize++] = rowIdx;
            }
         }
      } else {
         for(int i = 0; i < bound.selSize; ++i) {
            int rowIdx = bound.sel[i];
            if (!OrcFilterContext.isNull(branch, rowIdx) && this.allowWithNegation(v, rowIdx)) {
               selOut.sel[currSize++] = rowIdx;
            }
         }
      }

      selOut.selSize = currSize;
   }

   private boolean allowWithNegation(ColumnVector v, int rowIdx) {
      return this.allow(v, rowIdx) != this.negated;
   }

   protected abstract boolean allow(ColumnVector var1, int var2);
}
