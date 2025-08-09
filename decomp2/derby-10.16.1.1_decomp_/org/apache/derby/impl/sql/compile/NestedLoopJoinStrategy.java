package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class NestedLoopJoinStrategy extends BaseJoinStrategy {
   NestedLoopJoinStrategy() {
      boolean var1 = true;
   }

   public boolean feasible(Optimizable var1, OptimizablePredicateList var2, Optimizer var3) throws StandardException {
      if (var1.isMaterializable()) {
         return true;
      } else {
         return var1.supportsMultipleInstantiations();
      }
   }

   public boolean multiplyBaseCostByOuterRows() {
      return true;
   }

   public OptimizablePredicateList getBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2, Optimizable var3) throws StandardException {
      if (var1 != null) {
         var1.transferAllPredicates(var2);
         var2.classify(var3, var3.getCurrentAccessPath().getConglomerateDescriptor());
      }

      return var2;
   }

   public double nonBasePredicateSelectivity(Optimizable var1, OptimizablePredicateList var2) {
      return (double)1.0F;
   }

   public void putBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2) throws StandardException {
      for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
         OptimizablePredicate var4 = var2.getOptPredicate(var3);
         var1.addOptPredicate(var4);
         var2.removeOptPredicate(var3);
      }

   }

   public void estimateCost(Optimizable var1, OptimizablePredicateList var2, ConglomerateDescriptor var3, CostEstimate var4, Optimizer var5, CostEstimate var6) {
      var6.multiply(var4.rowCount(), var6);
      if (var1.optimizerTracingIsOn()) {
         var1.getOptimizerTracer().traceCostOfNScans(var1.getTableNumber(), var4.rowCount(), var6);
      }

   }

   public int maxCapacity(int var1, int var2, double var3) {
      return Integer.MAX_VALUE;
   }

   public String getName() {
      return "NESTEDLOOP";
   }

   public int scanCostType() {
      return 2;
   }

   public String getOperatorSymbol() {
      return "*";
   }

   public String resultSetMethodName(boolean var1, boolean var2, boolean var3) {
      if (var3) {
         return "getValidateCheckConstraintResultSet";
      } else if (var1) {
         return "getBulkTableScanResultSet";
      } else {
         return var2 ? "getMultiProbeTableScanResultSet" : "getTableScanResultSet";
      }
   }

   public String joinResultSetMethodName() {
      return "getNestedLoopJoinResultSet";
   }

   public String halfOuterJoinResultSetMethodName() {
      return "getNestedLoopLeftOuterJoinResultSet";
   }

   public int getScanArgs(TransactionController var1, MethodBuilder var2, Optimizable var3, OptimizablePredicateList var4, OptimizablePredicateList var5, ExpressionClassBuilderInterface var6, int var7, int var8, int var9, int var10, int var11, boolean var12, int var13, int var14, boolean var15) throws StandardException {
      ExpressionClassBuilder var16 = (ExpressionClassBuilder)var6;
      byte var17;
      if (var15) {
         var17 = 26;
      } else if (var7 > 1) {
         var17 = 26;
      } else {
         var17 = 24;
      }

      this.fillInScanArgs1(var1, var2, var3, var4, var16, var8);
      if (var15) {
         ((PredicateList)var4).generateInListValues(var16, var2);
      }

      this.fillInScanArgs2(var2, var3, var7, var9, var10, var11, var12, var13);
      return var17;
   }

   public void divideUpPredicateLists(Optimizable var1, OptimizablePredicateList var2, OptimizablePredicateList var3, OptimizablePredicateList var4, OptimizablePredicateList var5, DataDictionary var6) throws StandardException {
      var2.setPredicatesAndProperties(var3);
   }

   public boolean doesMaterialization() {
      return false;
   }

   public String toString() {
      return this.getName();
   }

   protected boolean validForOutermostTable() {
      return true;
   }
}
