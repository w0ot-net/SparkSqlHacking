package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class TableOperatorNode extends FromTable {
   ResultSetNode leftResultSet;
   ResultSetNode rightResultSet;
   Optimizer leftOptimizer;
   Optimizer rightOptimizer;
   private boolean leftModifyAccessPathsDone;
   private boolean rightModifyAccessPathsDone;

   TableOperatorNode(ResultSetNode var1, ResultSetNode var2, Properties var3, ContextManager var4) throws StandardException {
      super((String)null, var3, var4);
      this.leftResultSet = var1;
      this.rightResultSet = var2;
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      this.leftResultSet.bindUntypedNullsToResultColumns(var1);
      this.rightResultSet.bindUntypedNullsToResultColumns(var1);
   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      boolean var2 = false;
      if (this.leftResultSet instanceof FromTable) {
         if (this.leftOptimizer != null) {
            this.leftOptimizer.modifyAccessPaths();
            this.leftResultSet = (ResultSetNode)this.leftOptimizer.getOptimizable(0);
         } else {
            this.leftResultSet = (ResultSetNode)((FromTable)this.leftResultSet).modifyAccessPath(var1);
         }

         this.leftModifyAccessPathsDone = true;
      } else {
         var2 = true;
      }

      if (this.rightResultSet instanceof FromTable) {
         if (this.rightOptimizer != null) {
            this.rightOptimizer.modifyAccessPaths();
            this.rightResultSet = (ResultSetNode)this.rightOptimizer.getOptimizable(0);
         } else {
            this.rightResultSet = (ResultSetNode)((FromTable)this.rightResultSet).modifyAccessPath(var1);
         }

         this.rightModifyAccessPathsDone = true;
      } else {
         var2 = true;
      }

      return (Optimizable)(var2 ? (Optimizable)this.modifyAccessPaths() : this);
   }

   public void verifyProperties(DataDictionary var1) throws StandardException {
      if (this.leftResultSet instanceof Optimizable) {
         ((Optimizable)this.leftResultSet).verifyProperties(var1);
      }

      if (this.rightResultSet instanceof Optimizable) {
         ((Optimizable)this.rightResultSet).verifyProperties(var1);
      }

      super.verifyProperties(var1);
   }

   public void updateBestPlanMap(short var1, Object var2) throws StandardException {
      super.updateBestPlanMap(var1, var2);
      if (this.leftResultSet instanceof Optimizable) {
         ((Optimizable)this.leftResultSet).updateBestPlanMap(var1, var2);
      } else if (this.leftResultSet.getOptimizerImpl() != null) {
         this.leftResultSet.getOptimizerImpl().updateBestPlanMaps(var1, var2);
      }

      if (this.rightResultSet instanceof Optimizable) {
         ((Optimizable)this.rightResultSet).updateBestPlanMap(var1, var2);
      } else if (this.rightResultSet.getOptimizerImpl() != null) {
         this.rightResultSet.getOptimizerImpl().updateBestPlanMaps(var1, var2);
      }

   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode getLeftResultSet() {
      return this.leftResultSet;
   }

   ResultSetNode getRightResultSet() {
      return this.rightResultSet;
   }

   ResultSetNode getLeftmostResultSet() {
      return this.leftResultSet instanceof TableOperatorNode ? ((TableOperatorNode)this.leftResultSet).getLeftmostResultSet() : this.leftResultSet;
   }

   void setLeftmostResultSet(ResultSetNode var1) {
      if (this.leftResultSet instanceof TableOperatorNode) {
         ((TableOperatorNode)this.leftResultSet).setLeftmostResultSet(var1);
      } else {
         this.leftResultSet = var1;
      }

   }

   void setLevel(int var1) {
      super.setLevel(var1);
      if (this.leftResultSet instanceof FromTable) {
         ((FromTable)this.leftResultSet).setLevel(var1);
      }

      if (this.rightResultSet instanceof FromTable) {
         ((FromTable)this.rightResultSet).setLevel(var1);
      }

   }

   String getExposedName() {
      return null;
   }

   void setNestedInParens(boolean var1) {
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      this.leftResultSet = this.leftResultSet.bindNonVTITables(var1, var2);
      this.rightResultSet = this.rightResultSet.bindNonVTITables(var1, var2);
      if (this.tableNumber == -1) {
         this.tableNumber = this.getCompilerContext().getNextTableNumber();
      }

      return this;
   }

   ResultSetNode bindVTITables(FromList var1) throws StandardException {
      this.leftResultSet = this.leftResultSet.bindVTITables(var1);
      this.rightResultSet = this.rightResultSet.bindVTITables(var1);
      return this;
   }

   void bindExpressions(FromList var1) throws StandardException {
      if (!(this instanceof UnionNode) || !((UnionNode)this).tableConstructor()) {
         this.leftResultSet.rejectParameters();
         this.rightResultSet.rejectParameters();
      }

      this.leftResultSet.bindExpressions(var1);
      this.rightResultSet.bindExpressions(var1);
   }

   void rejectParameters() throws StandardException {
      this.leftResultSet.rejectParameters();
      this.rightResultSet.rejectParameters();
   }

   void bindExpressionsWithTables(FromList var1) throws StandardException {
      if (!(this instanceof UnionNode) || !((UnionNode)this).tableConstructor()) {
         this.leftResultSet.rejectParameters();
         this.rightResultSet.rejectParameters();
      }

      this.leftResultSet.bindExpressionsWithTables(var1);
      this.rightResultSet.bindExpressionsWithTables(var1);
   }

   void bindResultColumns(FromList var1) throws StandardException {
      this.leftResultSet.bindResultColumns(var1);
      this.rightResultSet.bindResultColumns(var1);
   }

   void bindResultColumns(TableDescriptor var1, FromVTI var2, ResultColumnList var3, DMLStatementNode var4, FromList var5) throws StandardException {
      this.leftResultSet.bindResultColumns(var1, var2, var3, var4, var5);
      this.rightResultSet.bindResultColumns(var1, var2, var3, var4, var5);
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      FromTable var4 = this.leftResultSet.getFromTableByName(var1, var2, var3);
      if (var4 == null) {
         var4 = this.rightResultSet.getFromTableByName(var1, var2, var3);
      }

      return var4;
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.leftResultSet = this.leftResultSet.preprocess(var1, var2, var3);
      if (this.leftResultSet instanceof FromSubquery) {
         this.leftResultSet = ((FromSubquery)this.leftResultSet).extractSubquery(var1);
      }

      this.rightResultSet = this.rightResultSet.preprocess(var1, var2, var3);
      if (this.rightResultSet instanceof FromSubquery) {
         this.rightResultSet = ((FromSubquery)this.rightResultSet).extractSubquery(var1);
      }

      this.setReferencedTableMap((JBitSet)this.leftResultSet.getReferencedTableMap().clone());
      this.getReferencedTableMap().or(this.rightResultSet.getReferencedTableMap());
      this.getReferencedTableMap().set(this.tableNumber);
      if (this.isFlattenableJoinNode()) {
         return this;
      } else {
         this.projectResultColumns();
         return this.genProjectRestrict(var1);
      }
   }

   void projectResultColumns() throws StandardException {
      this.getResultColumns().doProjection();
   }

   void setReferencedColumns() {
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.leftResultSet = this.leftResultSet.optimize(var1, var2, var3);
      this.rightResultSet = this.rightResultSet.optimize(var1, var2, var3);
      this.getCostEstimate().setCost(this.leftResultSet.getCostEstimate().getEstimatedCost(), this.leftResultSet.getCostEstimate().rowCount(), this.leftResultSet.getCostEstimate().singleScanRowCount() + this.rightResultSet.getCostEstimate().singleScanRowCount());
      this.getCostEstimate().add(this.rightResultSet.getCostEstimate(), this.getCostEstimate());
      return this;
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      if (!this.leftModifyAccessPathsDone) {
         if (this.leftOptimizer != null) {
            this.leftOptimizer.modifyAccessPaths();
            this.leftResultSet = (ResultSetNode)this.leftOptimizer.getOptimizable(0);
         } else if (this instanceof SetOperatorNode) {
            SetOperatorNode var1 = (SetOperatorNode)this;
            this.leftResultSet = this.leftResultSet.modifyAccessPaths(var1.getLeftOptPredicateList());
         } else {
            this.leftResultSet = this.leftResultSet.modifyAccessPaths();
         }
      }

      if (!this.rightModifyAccessPathsDone) {
         if (this.rightOptimizer != null) {
            this.rightOptimizer.modifyAccessPaths();
            this.rightResultSet = (ResultSetNode)this.rightOptimizer.getOptimizable(0);
         } else if (this instanceof SetOperatorNode) {
            SetOperatorNode var2 = (SetOperatorNode)this;
            this.rightResultSet = this.rightResultSet.modifyAccessPaths(var2.getRightOptPredicateList());
         } else {
            this.rightResultSet = this.rightResultSet.modifyAccessPaths();
         }
      }

      return this;
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return this.leftResultSet.referencesTarget(var1, var2) || this.rightResultSet.referencesTarget(var1, var2);
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.leftResultSet.referencesSessionSchema() || this.rightResultSet.referencesSessionSchema();
   }

   protected ResultSetNode optimizeSource(Optimizer var1, ResultSetNode var2, PredicateList var3, CostEstimate var4) throws StandardException {
      ResultSetNode var5;
      if (var2 instanceof FromTable) {
         FromList var6 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), (FromTable)var2, this.getContextManager());
         if (var3 == null) {
            var3 = new PredicateList(this.getContextManager());
         }

         LanguageConnectionContext var7 = this.getLanguageConnectionContext();
         OptimizerFactory var8 = var7.getOptimizerFactory();
         var1 = var8.getOptimizer(var6, var3, this.getDataDictionary(), (RequiredRowOrdering)null, this.getCompilerContext().getNumTables(), (OptimizerPlan)null, var7);
         var1.prepForNextRound();
         if (var2 == this.leftResultSet) {
            this.leftOptimizer = var1;
         } else if (var2 == this.rightResultSet) {
            this.rightOptimizer = var1;
         }

         var1.setOuterRows(var4.rowCount());

         while(var1.getNextPermutation()) {
            while(var1.getNextDecoratedPermutation()) {
               var1.costPermutation();
            }
         }

         var5 = var2;
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceEndQueryBlock();
         }
      } else {
         var5 = var2.optimize(this.getDataDictionary(), var3, var4.rowCount());
      }

      return var5;
   }

   void decrementLevel(int var1) {
      this.leftResultSet.decrementLevel(var1);
      this.rightResultSet.decrementLevel(var1);
   }

   void adjustForSortElimination() {
      this.leftResultSet.adjustForSortElimination();
      this.rightResultSet.adjustForSortElimination();
   }

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      this.leftResultSet.adjustForSortElimination(var1);
      this.rightResultSet.adjustForSortElimination(var1);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.leftResultSet != null) {
         this.leftResultSet = (ResultSetNode)this.leftResultSet.accept(var1);
      }

      if (this.rightResultSet != null) {
         this.rightResultSet = (ResultSetNode)this.rightResultSet.accept(var1);
      }

   }

   boolean needsSpecialRCLBinding() {
      return true;
   }
}
