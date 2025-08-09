package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public interface JoinStrategy {
   boolean feasible(Optimizable var1, OptimizablePredicateList var2, Optimizer var3) throws StandardException;

   boolean bulkFetchOK();

   boolean ignoreBulkFetch();

   boolean multiplyBaseCostByOuterRows();

   OptimizablePredicateList getBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2, Optimizable var3) throws StandardException;

   double nonBasePredicateSelectivity(Optimizable var1, OptimizablePredicateList var2) throws StandardException;

   void putBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2) throws StandardException;

   void estimateCost(Optimizable var1, OptimizablePredicateList var2, ConglomerateDescriptor var3, CostEstimate var4, Optimizer var5, CostEstimate var6) throws StandardException;

   int maxCapacity(int var1, int var2, double var3);

   String getName();

   int scanCostType();

   String getOperatorSymbol();

   String resultSetMethodName(boolean var1, boolean var2, boolean var3);

   String joinResultSetMethodName();

   String halfOuterJoinResultSetMethodName();

   int getScanArgs(TransactionController var1, MethodBuilder var2, Optimizable var3, OptimizablePredicateList var4, OptimizablePredicateList var5, ExpressionClassBuilderInterface var6, int var7, int var8, int var9, int var10, int var11, boolean var12, int var13, int var14, boolean var15) throws StandardException;

   void divideUpPredicateLists(Optimizable var1, OptimizablePredicateList var2, OptimizablePredicateList var3, OptimizablePredicateList var4, OptimizablePredicateList var5, DataDictionary var6) throws StandardException;

   boolean isHashJoin();

   boolean doesMaterialization();
}
