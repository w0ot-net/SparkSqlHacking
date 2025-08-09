package org.apache.derby.impl.sql.compile;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class ProjectRestrictNode extends SingleChildResultSetNode {
   ValueNode restriction;
   ValueNode constantRestriction = null;
   PredicateList restrictionList;
   SubqueryList projectSubquerys;
   SubqueryList restrictSubquerys;
   private boolean accessPathModified;
   private boolean getTableNumberHere;
   private boolean validatingCheckConstraints = false;
   private String validatingBaseTableUUIDString;

   ProjectRestrictNode(ResultSetNode var1, ResultColumnList var2, ValueNode var3, PredicateList var4, SubqueryList var5, SubqueryList var6, Properties var7, ContextManager var8) {
      super(var1, var7, var8);
      this.setResultColumns(var2);
      this.restriction = var3;
      this.restrictionList = var4;
      this.projectSubquerys = var5;
      this.restrictSubquerys = var6;
      if (var7 != null && var1 instanceof Optimizable) {
         ((Optimizable)var1).setProperties(this.getProperties());
         this.setProperties((Properties)null);
      }

   }

   public boolean nextAccessPath(Optimizer var1, OptimizablePredicateList var2, RowOrdering var3) throws StandardException {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).nextAccessPath(var1, this.restrictionList, var3) : super.nextAccessPath(var1, var2, var3);
   }

   public void rememberAsBest(int var1, Optimizer var2) throws StandardException {
      super.rememberAsBest(var1, var2);
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).rememberAsBest(var1, var2);
      }

   }

   public void startOptimizing(Optimizer var1, RowOrdering var2) {
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).startOptimizing(var1, var2);
      } else {
         super.startOptimizing(var1, var2);
      }

   }

   public int getTableNumber() {
      if (this.getTableNumberHere) {
         return super.getTableNumber();
      } else {
         return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getTableNumber() : super.getTableNumber();
      }
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      this.setCostEstimate(this.getCostEstimate(var1));
      this.updateBestPlanMap((short)1, this);
      if (this.childResult instanceof Optimizable) {
         CostEstimate var5 = ((Optimizable)this.childResult).optimizeIt(var1, this.restrictionList, var3, var4);
         this.getCostEstimate().setCost(var5.getEstimatedCost(), var5.rowCount(), var5.singleScanRowCount());
      } else if (!this.accessPathModified) {
         this.childResult = this.childResult.optimize(var1.getDataDictionary(), this.restrictionList, var3.rowCount());
         CostEstimate var6 = this.childResult.getCostEstimate();
         this.getCostEstimate().setCost(var6.getEstimatedCost(), var6.rowCount(), var6.singleScanRowCount());
         var1.considerCost(this, this.restrictionList, this.getCostEstimate(), var3);
      }

      return this.getCostEstimate();
   }

   public boolean feasibleJoinStrategy(OptimizablePredicateList var1, Optimizer var2) throws StandardException {
      if (this.childResult instanceof Optimizable) {
         if (this.childResult instanceof UnionNode) {
            ((UnionNode)this.childResult).pullOptPredicates(this.restrictionList);
         }

         return ((Optimizable)this.childResult).feasibleJoinStrategy(this.restrictionList, var2);
      } else {
         return super.feasibleJoinStrategy(this.restrictionList, var2);
      }
   }

   public AccessPath getCurrentAccessPath() {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getCurrentAccessPath() : super.getCurrentAccessPath();
   }

   public AccessPath getBestAccessPath() {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getBestAccessPath() : super.getBestAccessPath();
   }

   public AccessPath getBestSortAvoidancePath() {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getBestSortAvoidancePath() : super.getBestSortAvoidancePath();
   }

   public AccessPath getTrulyTheBestAccessPath() {
      if (this.hasTrulyTheBestAccessPath) {
         return super.getTrulyTheBestAccessPath();
      } else {
         return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getTrulyTheBestAccessPath() : super.getTrulyTheBestAccessPath();
      }
   }

   public void rememberSortAvoidancePath() {
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).rememberSortAvoidancePath();
      } else {
         super.rememberSortAvoidancePath();
      }

   }

   public boolean considerSortAvoidancePath() {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).considerSortAvoidancePath() : super.considerSortAvoidancePath();
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      if (this.restrictionList == null) {
         this.restrictionList = new PredicateList(this.getContextManager());
      }

      this.restrictionList.addPredicate((Predicate)var1);
      Predicate var2 = (Predicate)var1;
      if (!var2.remapScopedPred()) {
         RemapCRsVisitor var3 = new RemapCRsVisitor(true);
         var2.getAndNode().accept(var3);
      }

      return true;
   }

   public void pullOptPredicates(OptimizablePredicateList var1) throws StandardException {
      if (this.restrictionList != null && !this.isNotExists()) {
         if (this.childResult instanceof UnionNode) {
            ((UnionNode)this.childResult).pullOptPredicates(this.restrictionList);
         }

         RemapCRsVisitor var2 = new RemapCRsVisitor(false);

         for(int var3 = this.restrictionList.size() - 1; var3 >= 0; --var3) {
            OptimizablePredicate var4 = this.restrictionList.getOptPredicate(var3);
            ((Predicate)var4).getAndNode().accept(var2);
            var1.addOptPredicate(var4);
            this.restrictionList.removeOptPredicate(var3);
         }
      }

   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      boolean var2 = true;
      if (this.accessPathModified) {
         return this;
      } else {
         boolean var3 = false;
         if (!(this.childResult instanceof Optimizable)) {
            var2 = false;
            this.childResult = this.childResult.modifyAccessPaths(this.restrictionList);
            this.hasTrulyTheBestAccessPath = true;
            if (!this.trulyTheBestAccessPath.getJoinStrategy().isHashJoin()) {
               return (Optimizable)this.considerMaterialization(var1);
            }

            this.getTableNumberHere = true;
         } else if (!(this.childResult instanceof FromBaseTable)) {
            if (this.trulyTheBestAccessPath.getJoinStrategy() == null) {
               this.trulyTheBestAccessPath = (AccessPathImpl)((Optimizable)this.childResult).getTrulyTheBestAccessPath();
            }

            if (this.childResult instanceof SetOperatorNode) {
               this.childResult = (ResultSetNode)((SetOperatorNode)this.childResult).modifyAccessPath(var1, this.restrictionList);
               var3 = true;
            } else {
               this.childResult = (ResultSetNode)((FromTable)this.childResult).modifyAccessPath(var1);
            }
         }

         boolean var4 = this.hasTrulyTheBestAccessPath && this.trulyTheBestAccessPath.getJoinStrategy() != null && this.trulyTheBestAccessPath.getJoinStrategy().isHashJoin();
         if (this.restrictionList != null && !var3 && !var4 && !this.validatingCheckConstraints) {
            this.restrictionList.pushUsefulPredicates((Optimizable)this.childResult);
         }

         if (var2) {
            this.childResult = this.childResult.changeAccessPath();
         }

         this.accessPathModified = true;
         return this.trulyTheBestAccessPath.getJoinStrategy() != null && this.trulyTheBestAccessPath.getJoinStrategy().isHashJoin() ? this.replaceWithHashTableNode() : (Optimizable)this.considerMaterialization(var1);
      }
   }

   private Optimizable replaceWithHashTableNode() throws StandardException {
      if (this.hasTrulyTheBestAccessPath) {
         ((FromTable)this.childResult).trulyTheBestAccessPath = (AccessPathImpl)this.getTrulyTheBestAccessPath();
         if (this.childResult instanceof SingleChildResultSetNode) {
            ((SingleChildResultSetNode)this.childResult).hasTrulyTheBestAccessPath = this.hasTrulyTheBestAccessPath;
            this.childResult.getReferencedTableMap().set(this.tableNumber);
         }
      }

      PredicateList var1 = new PredicateList(this.getContextManager());
      PredicateList var2 = new PredicateList(this.getContextManager());
      PredicateList var3 = new PredicateList(this.getContextManager());
      this.trulyTheBestAccessPath.getJoinStrategy().divideUpPredicateLists(this, this.restrictionList, var1, var2, var3, this.getDataDictionary());
      this.restrictionList = new PredicateList(this.getContextManager());

      for(Predicate var5 : var1) {
         var3.removeOptPredicate(var5);
      }

      for(Predicate var8 : var2) {
         var3.removeOptPredicate(var8);
      }

      var2.transferNonQualifiers(this, this.restrictionList);
      var3.copyPredicatesToOtherList(this.restrictionList);
      ResultColumnList var7 = this.childResult.getResultColumns();
      this.childResult.setResultColumns(var7.copyListAndObjects());
      var7.genVirtualColumnNodes(this.childResult, this.childResult.getResultColumns(), false);
      RemapCRsVisitor var9 = new RemapCRsVisitor(true);
      var1.accept(var9);
      this.childResult = new HashTableNode(this.childResult, this.tableProperties, var7, var1, var2, this.trulyTheBestAccessPath, this.getCostEstimate(), this.projectSubquerys, this.restrictSubquerys, this.hashKeyColumns(), this.getContextManager());
      return this;
   }

   public void verifyProperties(DataDictionary var1) throws StandardException {
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).verifyProperties(var1);
      } else {
         super.verifyProperties(var1);
      }

   }

   public boolean legalJoinOrder(JBitSet var1) {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).legalJoinOrder(var1) : true;
   }

   public double uniqueJoin(OptimizablePredicateList var1) throws StandardException {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).uniqueJoin(var1) : super.uniqueJoin(var1);
   }

   PredicateList getRestrictionList() {
      return this.restrictionList;
   }

   String getUserSpecifiedJoinStrategy() {
      return this.childResult instanceof FromTable ? ((FromTable)this.childResult).getUserSpecifiedJoinStrategy() : this.userSpecifiedJoinStrategy;
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.childResult = this.childResult.preprocess(var1, var2, var3);
      this.setReferencedTableMap((JBitSet)this.childResult.getReferencedTableMap().clone());
      return this;
   }

   void pushExpressions(PredicateList var1) throws StandardException {
      if (this.childResult instanceof JoinNode) {
         ((FromTable)this.childResult).pushExpressions(var1);
      }

      PredicateList var2 = var1.getPushablePredicates(this.getReferencedTableMap());
      if (var2 != null && this.childResult instanceof SelectNode) {
         SelectNode var3 = (SelectNode)this.childResult;
         if (!var3.hasWindows() && !var3.hasOffsetFetchFirst()) {
            var2.pushExpressionsIntoSelect((SelectNode)this.childResult, false);
         }
      }

      if (var2 != null && this.childResult instanceof UnionNode) {
         ((UnionNode)this.childResult).pushExpressions(var2);
      }

      if (this.restrictionList == null) {
         this.restrictionList = var2;
      } else if (var2 != null && var2.size() != 0) {
         this.restrictionList.destructiveAppend(var2);
      }

   }

   ResultSetNode addNewPredicate(Predicate var1) throws StandardException {
      if (this.restrictionList == null) {
         this.restrictionList = new PredicateList(this.getContextManager());
      }

      this.restrictionList.addPredicate(var1);
      return this;
   }

   boolean flattenableInFromSubquery(FromList var1) {
      return false;
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return this;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.childResult = this.childResult.optimize(var1, this.restrictionList, var3);
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.getCostEstimate().setCost(this.childResult.getCostEstimate().getEstimatedCost(), this.childResult.getCostEstimate().rowCount(), this.childResult.getCostEstimate().singleScanRowCount());
      return this;
   }

   CostEstimate getCostEstimate() {
      return super.getCostEstimate() == null ? this.childResult.getCostEstimate() : super.getCostEstimate();
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      if (this.getCandidateFinalCostEstimate() != null) {
         return this.getCandidateFinalCostEstimate();
      } else {
         if (this.childResult instanceof Optimizable) {
            this.setCandidateFinalCostEstimate(this.childResult.getFinalCostEstimate());
         } else {
            this.setCandidateFinalCostEstimate(this.getTrulyTheBestAccessPath().getCostEstimate());
         }

         return this.getCandidateFinalCostEstimate();
      }
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.childResult instanceof FromVTI) {
         ((FromVTI)this.childResult).computeProjectionAndRestriction(this.restrictionList);
      }

      this.generateMinion(var1, var2, false);
   }

   void generateResultSet(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateMinion(var1, var2, true);
   }

   private void generateMinion(ExpressionClassBuilder var1, MethodBuilder var2, boolean var3) throws StandardException {
      if (this.restrictionList != null && this.restrictionList.size() > 0) {
         this.restrictionList.eliminateBooleanTrueAndBooleanTrue();
      }

      if (this.nopProjectRestrict()) {
         this.generateNOPProjectRestrict();
         if (var3) {
            this.childResult.generateResultSet(var1, var2);
         } else {
            this.childResult.generate((ActivationClassBuilder)var1, var2);
         }

         this.setCostEstimate(this.childResult.getFinalCostEstimate());
      } else {
         if (this.restrictionList != null) {
            this.constantRestriction = this.restrictionList.restoreConstantPredicates();
            this.restrictionList.removeRedundantPredicates();
            this.restriction = this.restrictionList.restorePredicates();
            this.restrictionList = null;
         }

         ResultColumnList.ColumnMapping var4 = this.getResultColumns().mapSourceColumns();
         int[] var5 = var4.mapArray;
         boolean[] var6 = var4.cloneMap;
         int var7 = var1.addItem(new ReferencedColumnsDescriptorImpl(var5));
         int var8 = var1.addItem(var6);
         boolean var9 = true;
         if (!this.reflectionNeededForProjection() && var5 != null && var5.length == this.childResult.getResultColumns().size()) {
            int var10;
            for(var10 = 0; var10 < var5.length && var5[var10] == var10 + 1; ++var10) {
            }

            if (var10 == var5.length) {
               var9 = false;
            }
         }

         var1.pushGetResultSetFactoryExpression(var2);
         if (var3) {
            this.childResult.generateResultSet(var1, var2);
         } else {
            this.childResult.generate((ActivationClassBuilder)var1, var2);
         }

         this.assignResultSetNumber();
         if (this.projectSubquerys != null && this.projectSubquerys.size() > 0) {
            this.projectSubquerys.setPointOfAttachment(this.getResultSetNumber());
         }

         if (this.restrictSubquerys != null && this.restrictSubquerys.size() > 0) {
            this.restrictSubquerys.setPointOfAttachment(this.getResultSetNumber());
         }

         this.setCostEstimate(this.getFinalCostEstimate());
         if (this.restriction == null) {
            var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
         } else {
            MethodBuilder var11 = var1.newUserExprFun();
            this.restriction.generateExpression(var1, var11);
            var11.methodReturn();
            var11.complete();
            var1.pushMethodReference(var2, var11);
         }

         if (this.reflectionNeededForProjection()) {
            this.getResultColumns().generateCore(var1, var2, false);
         } else {
            var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
         }

         var2.push(this.getResultSetNumber());
         if (this.constantRestriction == null) {
            var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
         } else {
            MethodBuilder var12 = var1.newUserExprFun();
            this.constantRestriction.generateExpression(var1, var12);
            var12.methodReturn();
            var12.complete();
            var1.pushMethodReference(var2, var12);
         }

         var2.push(var7);
         var2.push(var8);
         var2.push(this.getResultColumns().reusableResult());
         var2.push(var9);
         var2.push(this.validatingCheckConstraints);
         if (this.validatingBaseTableUUIDString == null) {
            var2.push("NULL");
         } else {
            var2.push(this.validatingBaseTableUUIDString);
         }

         var2.push(this.getCostEstimate().rowCount());
         var2.push(this.getCostEstimate().getEstimatedCost());
         var2.callMethod((short)185, (String)null, "getProjectRestrictResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 13);
      }
   }

   boolean nopProjectRestrict() {
      if (this.restriction == null && this.constantRestriction == null && (this.restrictionList == null || this.restrictionList.size() <= 0)) {
         ResultColumnList var1 = this.childResult.getResultColumns();
         ResultColumnList var2 = this.getResultColumns();
         return var2.nopProjection(var1);
      } else {
         return false;
      }
   }

   void generateNOPProjectRestrict() throws StandardException {
      this.getResultColumns().setRedundant();
   }

   ResultSetNode considerMaterialization(JBitSet var1) throws StandardException {
      this.childResult = this.childResult.considerMaterialization(var1);
      if (this.childResult.performMaterialization(var1)) {
         ReferencedTablesVisitor var4 = new ReferencedTablesVisitor((JBitSet)this.childResult.getReferencedTableMap().clone());
         boolean var5 = this.restrictionList == null || this.restrictionList.size() == 0;
         if (!var5) {
            this.restrictionList.accept(var4);
         }

         if (var5 || this.childResult.getReferencedTableMap().contains(var4.getTableMap())) {
            ResultColumnList var7 = this.getResultColumns();
            this.setResultColumns(this.getResultColumns().copyListAndObjects());
            var7.genVirtualColumnNodes(this, this.getResultColumns());
            MaterializeResultSetNode var6 = new MaterializeResultSetNode(this, var7, this.tableProperties, this.getContextManager());
            if (this.getReferencedTableMap() != null) {
               var6.setReferencedTableMap((JBitSet)this.getReferencedTableMap().clone());
            }

            return var6;
         }

         ResultColumnList var3 = this.childResult.getResultColumns();
         this.childResult.setResultColumns(var3.copyListAndObjects());
         var3.genVirtualColumnNodes(this.childResult, this.childResult.getResultColumns());
         MaterializeResultSetNode var2 = new MaterializeResultSetNode(this.childResult, var3, this.tableProperties, this.getContextManager());
         if (this.childResult.getReferencedTableMap() != null) {
            var2.setReferencedTableMap((JBitSet)this.childResult.getReferencedTableMap().clone());
         }

         this.childResult = var2;
      }

      return this;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      return this.childResult.getFromTableByName(var1, var2, var3);
   }

   int updateTargetLockMode() {
      return this.restriction == null && this.constantRestriction == null ? this.childResult.updateTargetLockMode() : 6;
   }

   boolean isPossibleDistinctScan(Set var1) {
      if (this.restriction == null && (this.restrictionList == null || this.restrictionList.size() == 0)) {
         HashSet var2 = new HashSet();

         for(ResultColumn var4 : this.getResultColumns()) {
            BaseColumnNode var5 = var4.getBaseColumnNode();
            if (var5 == null) {
               return false;
            }

            var2.add(var5);
         }

         return var2.equals(var1) && this.childResult.isPossibleDistinctScan(var1);
      } else {
         return false;
      }
   }

   void markForDistinctScan() {
      this.childResult.markForDistinctScan();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.restriction != null) {
         this.restriction = (ValueNode)this.restriction.accept(var1);
      }

      if (this.restrictionList != null) {
         this.restrictionList = (PredicateList)this.restrictionList.accept(var1);
      }

   }

   void setRefActionInfo(long var1, int[] var3, String var4, boolean var5) {
      this.childResult.setRefActionInfo(var1, var3, var4, var5);
   }

   void setRestriction(ValueNode var1) {
      this.restriction = var1;
   }

   public void pushQueryExpressionSuffix() {
      this.childResult.pushQueryExpressionSuffix();
   }

   void pushOrderByList(OrderByList var1) {
      this.childResult.pushOrderByList(var1);
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
      this.childResult.pushOffsetFetchFirst(var1, var2, var3);
   }

   void setValidatingCheckConstraints(String var1) {
      this.validatingCheckConstraints = true;
      this.validatingBaseTableUUIDString = var1;
   }
}
