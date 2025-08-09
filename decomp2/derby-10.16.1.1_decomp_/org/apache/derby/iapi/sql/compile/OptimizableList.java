package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public interface OptimizableList {
   int size();

   Optimizable getOptimizable(int var1);

   void setOptimizable(int var1, Optimizable var2);

   void verifyProperties(DataDictionary var1) throws StandardException;

   void reOrder(int[] var1);

   boolean useStatistics();

   boolean optimizeJoinOrder();

   boolean legalJoinOrder(int var1);

   void initAccessPaths(Optimizer var1);
}
