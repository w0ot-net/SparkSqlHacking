package org.apache.orc;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.io.filter.MutableFilterContext;

public interface OrcFilterContext extends MutableFilterContext {
   ColumnVector[] findColumnVector(String var1);

   static boolean noNulls(ColumnVector[] vectorBranch) {
      for(ColumnVector v : vectorBranch) {
         if (!v.noNulls) {
            return false;
         }
      }

      return true;
   }

   static boolean isNull(ColumnVector[] vectorBranch, int idx) throws IllegalArgumentException {
      for(ColumnVector v : vectorBranch) {
         if (v instanceof ListColumnVector || v instanceof MapColumnVector) {
            throw new IllegalArgumentException(String.format("Found vector: %s in branch. List and Map vectors are not supported in isNull determination", v));
         }

         if (!v.noNulls && v.isNull[v.isRepeating ? 0 : idx]) {
            return true;
         }
      }

      return false;
   }
}
