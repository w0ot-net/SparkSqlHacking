package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public interface OptimizerFactory {
   String MODULE = "org.apache.derby.iapi.sql.compile.OptimizerFactory";

   Optimizer getOptimizer(OptimizableList var1, OptimizablePredicateList var2, DataDictionary var3, RequiredRowOrdering var4, int var5, OptimizerPlan var6, LanguageConnectionContext var7) throws StandardException;

   CostEstimate getCostEstimate();

   boolean supportsOptimizerTrace();

   int getMaxMemoryPerTable();

   boolean doJoinOrderOptimization();
}
