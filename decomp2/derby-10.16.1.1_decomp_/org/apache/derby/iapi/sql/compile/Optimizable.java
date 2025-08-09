package org.apache.derby.iapi.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public interface Optimizable extends Visitable {
   boolean nextAccessPath(Optimizer var1, OptimizablePredicateList var2, RowOrdering var3) throws StandardException;

   CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException;

   AccessPath getCurrentAccessPath();

   AccessPath getBestAccessPath();

   AccessPath getBestSortAvoidancePath();

   AccessPath getTrulyTheBestAccessPath();

   void rememberSortAvoidancePath();

   boolean considerSortAvoidancePath();

   void rememberJoinStrategyAsBest(AccessPath var1);

   TableDescriptor getTableDescriptor();

   JBitSet getReferencedTableMap();

   boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException;

   void pullOptPredicates(OptimizablePredicateList var1) throws StandardException;

   Optimizable modifyAccessPath(JBitSet var1) throws StandardException;

   boolean isCoveringIndex(ConglomerateDescriptor var1) throws StandardException;

   Properties getProperties();

   void setProperties(Properties var1);

   void verifyProperties(DataDictionary var1) throws StandardException;

   String getName() throws StandardException;

   String getBaseTableName();

   int convertAbsoluteToRelativeColumnPosition(int var1);

   void updateBestPlanMap(short var1, Object var2) throws StandardException;

   void rememberAsBest(int var1, Optimizer var2) throws StandardException;

   void startOptimizing(Optimizer var1, RowOrdering var2);

   CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException;

   boolean isBaseTable();

   boolean isMaterializable() throws StandardException;

   boolean supportsMultipleInstantiations();

   boolean hasLargeObjectColumns();

   int getResultSetNumber();

   int getTableNumber();

   boolean hasTableNumber();

   boolean forUpdate();

   int initialCapacity();

   float loadFactor();

   int[] hashKeyColumns();

   void setHashKeyColumns(int[] var1);

   boolean feasibleJoinStrategy(OptimizablePredicateList var1, Optimizer var2) throws StandardException;

   boolean memoryUsageOK(double var1, int var3) throws StandardException;

   int maxCapacity(JoinStrategy var1, int var2) throws StandardException;

   boolean legalJoinOrder(JBitSet var1);

   DataDictionary getDataDictionary() throws StandardException;

   boolean isTargetTable();

   int getNumColumnsReturned();

   boolean isOneRowScan() throws StandardException;

   void initAccessPaths(Optimizer var1);

   double uniqueJoin(OptimizablePredicateList var1) throws StandardException;

   OptTrace getOptimizerTracer();

   boolean optimizerTracingIsOn();
}
