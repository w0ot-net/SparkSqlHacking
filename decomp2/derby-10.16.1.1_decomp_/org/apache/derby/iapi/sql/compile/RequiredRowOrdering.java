package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public interface RequiredRowOrdering {
   int SORT_REQUIRED = 1;
   int ELIMINATE_DUPS = 2;
   int NOTHING_REQUIRED = 3;

   int sortRequired(RowOrdering var1, OptimizableList var2, int[] var3) throws StandardException;

   int sortRequired(RowOrdering var1, JBitSet var2, OptimizableList var3, int[] var4) throws StandardException;

   void estimateCost(double var1, RowOrdering var3, CostEstimate var4) throws StandardException;

   void sortNeeded();

   void sortNotNeeded();

   boolean getSortNeeded();
}
