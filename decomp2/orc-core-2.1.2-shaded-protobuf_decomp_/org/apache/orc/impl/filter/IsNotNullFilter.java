package org.apache.orc.impl.filter;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.OrcFilterContext;

public class IsNotNullFilter implements VectorFilter {
   private final String colName;

   public IsNotNullFilter(String colName) {
      this.colName = colName;
   }

   public void filter(OrcFilterContext fc, Selected bound, Selected selOut) {
      ColumnVector[] branch = fc.findColumnVector(this.colName);
      ColumnVector v = branch[branch.length - 1];
      boolean noNulls = OrcFilterContext.noNulls(branch);
      if (!noNulls && (!v.isRepeating || OrcFilterContext.isNull(branch, 0))) {
         if (!v.isRepeating) {
            int currSize = 0;

            for(int i = 0; i < bound.selSize; ++i) {
               int rowIdx = bound.sel[i];
               if (!OrcFilterContext.isNull(branch, rowIdx)) {
                  selOut.sel[currSize++] = rowIdx;
               }
            }

            selOut.selSize = currSize;
         }
      } else {
         selOut.selectAll(bound);
      }

   }
}
