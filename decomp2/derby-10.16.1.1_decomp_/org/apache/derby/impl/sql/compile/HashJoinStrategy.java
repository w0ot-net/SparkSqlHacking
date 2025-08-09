package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableIntHolder;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class HashJoinStrategy extends BaseJoinStrategy {
   public boolean feasible(Optimizable var1, OptimizablePredicateList var2, Optimizer var3) throws StandardException {
      ConglomerateDescriptor var4 = null;
      if (!var1.isMaterializable()) {
         if (var1.optimizerTracingIsOn()) {
            var1.getOptimizerTracer().traceSkipUnmaterializableHashJoin();
         }

         return false;
      } else if (var1.isTargetTable()) {
         return false;
      } else {
         if (var2 != null && var2.size() > 0 && !(var1 instanceof FromBaseTable)) {
            FromTable var5 = (FromTable)var1;
            JBitSet var6 = new JBitSet(var5.getReferencedTableMap().size());
            BaseTableNumbersVisitor var7 = new BaseTableNumbersVisitor(var6);
            var5.accept(var7);
            JBitSet var8 = new JBitSet(var6.size());

            for(int var9 = 0; var9 < var2.size(); ++var9) {
               Predicate var10 = (Predicate)var2.getOptPredicate(var9);
               if (var10.isJoinPredicate()) {
                  var8.or(var10.getReferencedSet());
               }
            }

            var6.and(var8);
            if (var6.getFirstSetBit() != -1) {
               return false;
            }
         }

         if (var1.isBaseTable()) {
            var4 = var1.getCurrentAccessPath().getConglomerateDescriptor();
         }

         int[] var11 = this.findHashKeyColumns(var1, var4, var2);
         return var11 != null;
      }
   }

   public boolean ignoreBulkFetch() {
      return true;
   }

   public boolean multiplyBaseCostByOuterRows() {
      return false;
   }

   public OptimizablePredicateList getBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2, Optimizable var3) throws StandardException {
      for(int var4 = var1.size() - 1; var4 >= 0; --var4) {
         OptimizablePredicate var5 = var1.getOptPredicate(var4);
         if (var3.getReferencedTableMap().contains(var5.getReferencedMap())) {
            var2.addOptPredicate(var5);
            var1.removeOptPredicate(var4);
         }
      }

      var2.classify(var3, var3.getCurrentAccessPath().getConglomerateDescriptor());
      return var2;
   }

   public double nonBasePredicateSelectivity(Optimizable var1, OptimizablePredicateList var2) throws StandardException {
      double var3 = (double)1.0F;
      if (var2 != null) {
         for(int var5 = 0; var5 < var2.size(); ++var5) {
            if (!var2.isRedundantPredicate(var5)) {
               var3 *= var2.getOptPredicate(var5).selectivity(var1);
            }
         }
      }

      return var3;
   }

   public void putBasePredicates(OptimizablePredicateList var1, OptimizablePredicateList var2) throws StandardException {
      for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
         OptimizablePredicate var4 = var2.getOptPredicate(var3);
         var1.addOptPredicate(var4);
         var2.removeOptPredicate(var3);
      }

   }

   public void estimateCost(Optimizable var1, OptimizablePredicateList var2, ConglomerateDescriptor var3, CostEstimate var4, Optimizer var5, CostEstimate var6) {
   }

   public int maxCapacity(int var1, int var2, double var3) {
      if (var1 >= 0) {
         return var1;
      } else {
         var3 += (double)ClassSize.estimateHashEntrySize();
         return var3 <= (double)1.0F ? var2 : (int)((double)var2 / var3);
      }
   }

   public String getName() {
      return "HASH";
   }

   public int scanCostType() {
      return 1;
   }

   public String getOperatorSymbol() {
      return "#";
   }

   public String resultSetMethodName(boolean var1, boolean var2, boolean var3) {
      return "getHashScanResultSet";
   }

   public String joinResultSetMethodName() {
      return "getHashJoinResultSet";
   }

   public String halfOuterJoinResultSetMethodName() {
      return "getHashLeftOuterJoinResultSet";
   }

   public int getScanArgs(TransactionController var1, MethodBuilder var2, Optimizable var3, OptimizablePredicateList var4, OptimizablePredicateList var5, ExpressionClassBuilderInterface var6, int var7, int var8, int var9, int var10, int var11, boolean var12, int var13, int var14, boolean var15) throws StandardException {
      ExpressionClassBuilder var16 = (ExpressionClassBuilder)var6;
      this.fillInScanArgs1(var1, var2, var3, var4, var16, var8);
      var5.generateQualifiers(var16, var2, var3, true);
      var2.push(var3.initialCapacity());
      var2.push(var3.loadFactor());
      var2.push(var3.maxCapacity(this, var14));
      int[] var17 = var3.hashKeyColumns();
      FormatableIntHolder[] var18 = FormatableIntHolder.getFormatableIntHolders(var17);
      FormatableArrayHolder var19 = new FormatableArrayHolder(var18);
      int var20 = var16.addItem(var19);
      var2.push(var20);
      this.fillInScanArgs2(var2, var3, var7, var9, var10, var11, var12, var13);
      return 28;
   }

   public void divideUpPredicateLists(Optimizable var1, OptimizablePredicateList var2, OptimizablePredicateList var3, OptimizablePredicateList var4, OptimizablePredicateList var5, DataDictionary var6) throws StandardException {
      var2.copyPredicatesToOtherList(var5);
      ConglomerateDescriptor var7 = var1.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      var2.transferPredicates(var3, var1.getReferencedTableMap(), var1);

      for(int var8 = var3.size() - 1; var8 >= 0; --var8) {
         Predicate var9 = (Predicate)var3.getOptPredicate(var8);
         if (!var9.isStoreQualifier() && !var9.isStartKey() && !var9.isStopKey()) {
            var3.removeOptPredicate(var8);
         }
      }

      for(int var12 = var2.size() - 1; var12 >= 0; --var12) {
         Predicate var14 = (Predicate)var2.getOptPredicate(var12);
         if (!var14.isStoreQualifier()) {
            var2.removeOptPredicate(var12);
         }
      }

      var2.copyPredicatesToOtherList(var4);
      Optimizable var13 = var1;
      if (var1 instanceof ProjectRestrictNode var15) {
         if (var15.getChildResult() instanceof Optimizable) {
            var13 = (Optimizable)var15.getChildResult();
         }
      }

      int[] var16 = this.findHashKeyColumns(var13, var7, var4);
      if (var16 != null) {
         var1.setHashKeyColumns(var16);
         var4.markAllPredicatesQualifiers();
         int[] var17 = new int[var16.length];
         if (var7 != null && var7.isIndex()) {
            for(int var18 = 0; var18 < var16.length; ++var18) {
               var17[var18] = var7.getIndexDescriptor().baseColumnPositions()[var16[var18]];
            }
         } else {
            for(int var11 = 0; var11 < var16.length; ++var11) {
               var17[var11] = var16[var11] + 1;
            }
         }

         for(int var19 = var16.length - 1; var19 >= 0; --var19) {
            var4.putOptimizableEqualityPredicateFirst(var1, var17[var19]);
         }

      } else {
         String var10;
         if (var7 != null && var7.isIndex()) {
            var10 = var7.getConglomerateName();
         } else {
            var10 = var1.getBaseTableName();
         }

         throw StandardException.newException("42Y63", new Object[]{var10, var1.getBaseTableName()});
      }
   }

   public boolean isHashJoin() {
      return true;
   }

   public boolean doesMaterialization() {
      return true;
   }

   private int[] findHashKeyColumns(Optimizable var1, ConglomerateDescriptor var2, OptimizablePredicateList var3) throws StandardException {
      if (var3 == null) {
         return (int[])null;
      } else {
         int[] var4;
         if (var2 == null) {
            var4 = new int[var1.getNumColumnsReturned()];

            for(int var5 = 0; var5 < var4.length; ++var5) {
               var4[var5] = var5 + 1;
            }
         } else if (var2.isIndex()) {
            var4 = var2.getIndexDescriptor().baseColumnPositions();
         } else {
            var4 = new int[var1.getTableDescriptor().getNumberOfColumns()];

            for(int var8 = 0; var8 < var4.length; ++var8) {
               var4[var8] = var8 + 1;
            }
         }

         ArrayList var9 = new ArrayList();

         for(int var6 = 0; var6 < var4.length; ++var6) {
            if (var3.hasOptimizableEquijoin(var1, var4[var6])) {
               var9.add(var6);
            }
         }

         if (var9.isEmpty()) {
            return null;
         } else {
            int[] var10 = new int[var9.size()];

            for(int var7 = 0; var7 < var10.length; ++var7) {
               var10[var7] = (Integer)var9.get(var7);
            }

            return var10;
         }
      }
   }

   public String toString() {
      return this.getName();
   }
}
