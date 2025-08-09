package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface Optimizer {
   String MODULE = "org.apache.derby.iapi.sql.compile.Optimizer";
   String JOIN_ORDER_OPTIMIZATION = "derby.optimizer.optimizeJoinOrder";
   String RULE_BASED_OPTIMIZATION = "derby.optimizer.ruleBasedOptimization";
   String NO_TIMEOUT = "derby.optimizer.noTimeout";
   String MAX_MEMORY_PER_TABLE = "derby.language.maxMemoryPerTable";
   int MAX_DYNAMIC_MATERIALIZED_ROWS = 512;
   String USE_STATISTICS = "derby.language.useStatistics";
   int NORMAL_PLAN = 1;
   int SORT_AVOIDANCE_PLAN = 2;

   boolean getNextPermutation() throws StandardException;

   boolean getNextDecoratedPermutation() throws StandardException;

   void costPermutation() throws StandardException;

   void costOptimizable(Optimizable var1, TableDescriptor var2, ConglomerateDescriptor var3, OptimizablePredicateList var4, CostEstimate var5) throws StandardException;

   void considerCost(Optimizable var1, OptimizablePredicateList var2, CostEstimate var3, CostEstimate var4) throws StandardException;

   DataDictionary getDataDictionary();

   void modifyAccessPaths() throws StandardException;

   CostEstimate getOptimizedCost();

   CostEstimate getFinalCost();

   void prepForNextRound();

   void setOuterRows(double var1);

   int getNumberOfJoinStrategies();

   int tableLockThreshold();

   JoinStrategy getJoinStrategy(int var1);

   JoinStrategy getJoinStrategy(String var1);

   int getLevel();

   double uniqueJoinWithOuterTable(OptimizablePredicateList var1) throws StandardException;

   boolean useStatistics();

   int getMaxMemoryPerTable();

   int getOptimizableCount();

   Optimizable getOptimizable(int var1);

   void updateBestPlanMaps(short var1, Object var2) throws StandardException;
}
