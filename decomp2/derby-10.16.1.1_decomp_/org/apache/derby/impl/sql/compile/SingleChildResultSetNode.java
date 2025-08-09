package org.apache.derby.impl.sql.compile;

import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class SingleChildResultSetNode extends FromTable {
   ResultSetNode childResult;
   protected boolean hasTrulyTheBestAccessPath;

   SingleChildResultSetNode(ResultSetNode var1, Properties var2, ContextManager var3) {
      super((String)null, var2, var3);
      this.childResult = var1;
      if (var1.getReferencedTableMap() != null) {
         this.setReferencedTableMap((JBitSet)var1.getReferencedTableMap().clone());
      }

   }

   public AccessPath getTrulyTheBestAccessPath() {
      if (this.hasTrulyTheBestAccessPath) {
         return super.getTrulyTheBestAccessPath();
      } else {
         return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).getTrulyTheBestAccessPath() : super.getTrulyTheBestAccessPath();
      }
   }

   ResultSetNode getChildResult() {
      return this.childResult;
   }

   void setChildResult(ResultSetNode var1) {
      this.childResult = var1;
   }

   public void pullOptPredicates(OptimizablePredicateList var1) throws StandardException {
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).pullOptPredicates(var1);
      }

   }

   public boolean forUpdate() {
      return this.childResult instanceof Optimizable ? ((Optimizable)this.childResult).forUpdate() : super.forUpdate();
   }

   public void initAccessPaths(Optimizer var1) {
      super.initAccessPaths(var1);
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).initAccessPaths(var1);
      }

   }

   public void updateBestPlanMap(short var1, Object var2) throws StandardException {
      super.updateBestPlanMap(var1, var2);
      if (this.childResult instanceof Optimizable) {
         ((Optimizable)this.childResult).updateBestPlanMap(var1, var2);
      } else if (this.childResult.getOptimizerImpl() != null) {
         this.childResult.getOptimizerImpl().updateBestPlanMaps(var1, var2);
      }

   }

   void printSubNodes(int var1) {
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return this.childResult.referencesTarget(var1, var2);
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.childResult.referencesSessionSchema();
   }

   void setLevel(int var1) {
      super.setLevel(var1);
      if (this.childResult instanceof FromTable) {
         ((FromTable)this.childResult).setLevel(var1);
      }

   }

   boolean subqueryReferencesTarget(String var1, boolean var2) throws StandardException {
      return this.childResult.subqueryReferencesTarget(var1, var2);
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.childResult = this.childResult.preprocess(var1, var2, var3);
      this.setReferencedTableMap((JBitSet)this.childResult.getReferencedTableMap().clone());
      return this;
   }

   ResultSetNode addNewPredicate(Predicate var1) throws StandardException {
      this.childResult = this.childResult.addNewPredicate(var1);
      return this;
   }

   void pushExpressions(PredicateList var1) throws StandardException {
      if (this.childResult instanceof FromTable) {
         ((FromTable)this.childResult).pushExpressions(var1);
      }

   }

   boolean flattenableInFromSubquery(FromList var1) {
      return false;
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return this;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.childResult = this.childResult.optimize(var1, var2, var3);
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.getCostEstimate().setCost(this.childResult.getCostEstimate().getEstimatedCost(), this.childResult.getCostEstimate().rowCount(), this.childResult.getCostEstimate().singleScanRowCount());
      return this;
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      this.childResult = this.childResult.modifyAccessPaths();
      return this;
   }

   ResultSetNode changeAccessPath() throws StandardException {
      this.childResult = this.childResult.changeAccessPath();
      return this;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      return this.childResult.getFromTableByName(var1, var2, var3);
   }

   void decrementLevel(int var1) {
      super.decrementLevel(var1);
      this.childResult.decrementLevel(var1);
   }

   int updateTargetLockMode() {
      return this.childResult.updateTargetLockMode();
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) throws StandardException {
      return this.childResult.isOrderedOn(var1, var2, var3);
   }

   boolean isOneRowResultSet() throws StandardException {
      return this.childResult.isOneRowResultSet();
   }

   boolean isNotExists() {
      return this.childResult.isNotExists();
   }

   protected boolean reflectionNeededForProjection() {
      return !this.getResultColumns().allExpressionsAreColumns(this.childResult);
   }

   void adjustForSortElimination() {
      this.childResult.adjustForSortElimination();
   }

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      this.childResult.adjustForSortElimination(var1);
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      return this.getCostEstimate() == null ? this.childResult.getFinalCostEstimate() : this.getCostEstimate();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.childResult != null) {
         this.childResult = (ResultSetNode)this.childResult.accept(var1);
      }

   }
}
