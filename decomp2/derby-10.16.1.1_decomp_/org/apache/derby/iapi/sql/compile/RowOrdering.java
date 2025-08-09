package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface RowOrdering {
   int ASCENDING = 1;
   int DESCENDING = 2;
   int DONTCARE = 3;

   boolean orderedOnColumn(int var1, int var2, int var3, int var4) throws StandardException;

   boolean orderedOnColumn(int var1, int var2, int var3) throws StandardException;

   void addOrderedColumn(int var1, int var2, int var3);

   void nextOrderPosition(int var1);

   void optimizableAlwaysOrdered(Optimizable var1);

   void columnAlwaysOrdered(Optimizable var1, int var2);

   boolean isColumnAlwaysOrdered(int var1, int var2);

   boolean alwaysOrdered(int var1);

   void removeOptimizable(int var1);

   void addUnorderedOptimizable(Optimizable var1);

   void copy(RowOrdering var1);
}
