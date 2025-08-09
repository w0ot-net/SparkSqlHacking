package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class UnionNode extends SetOperatorNode {
   private boolean addNewNodesCalled;
   boolean tableConstructor;
   boolean topTableConstructor;

   UnionNode(ResultSetNode var1, ResultSetNode var2, boolean var3, boolean var4, Properties var5, ContextManager var6) throws StandardException {
      super(var1, var2, var3, var5, var6);
      this.tableConstructor = var4;
   }

   void markTopTableConstructor() {
      this.topTableConstructor = true;
   }

   boolean tableConstructor() {
      return this.tableConstructor;
   }

   void rejectParameters() throws StandardException {
      if (!this.tableConstructor()) {
         super.rejectParameters();
      }

   }

   void setTableConstructorTypes(ResultColumnList var1) throws StandardException {
      if (this.tableConstructor()) {
         Object var2;
         UnionNode var3;
         for(var2 = this; var2 instanceof UnionNode; var2 = var3.leftResultSet) {
            var3 = (UnionNode)var2;
            ((RowResultSetNode)var3.rightResultSet).setTableConstructorTypes(var1);
         }

         ((RowResultSetNode)var2).setTableConstructorTypes(var1);
      }

   }

   ResultSetNode enhanceRCLForInsert(InsertNode var1, boolean var2, int[] var3) throws StandardException {
      if (!this.tableConstructor()) {
         return super.enhanceRCLForInsert(var1, var2, var3);
      } else {
         this.leftResultSet = var1.enhanceAndCheckForAutoincrement(this.leftResultSet, var2, var3, false);
         this.rightResultSet = var1.enhanceAndCheckForAutoincrement(this.rightResultSet, var2, var3, false);
         if (!var2 || this.getResultColumns().size() < var1.resultColumnList.size()) {
            this.setResultColumns(this.getRCLForInsert(var1, var3));
         }

         return this;
      }
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      if (var2 != null && !this.getCurrentAccessPath().getJoinStrategy().isHashJoin()) {
         for(int var5 = var2.size() - 1; var5 >= 0; --var5) {
            if (this.pushOptPredicate(var2.getOptPredicate(var5))) {
               var2.removeOptPredicate(var5);
            }
         }
      }

      this.updateBestPlanMap((short)1, this);
      this.leftResultSet = this.optimizeSource(var1, this.leftResultSet, this.getLeftOptPredicateList(), var3);
      this.rightResultSet = this.optimizeSource(var1, this.rightResultSet, this.getRightOptPredicateList(), var3);
      CostEstimate var6 = this.getCostEstimate(var1);
      var6.setCost(this.leftResultSet.getCostEstimate().getEstimatedCost(), this.leftResultSet.getCostEstimate().rowCount(), this.leftResultSet.getCostEstimate().singleScanRowCount() + this.rightResultSet.getCostEstimate().singleScanRowCount());
      var6.add(this.rightResultSet.getCostEstimate(), var6);
      this.getCurrentAccessPath().getJoinStrategy().estimateCost(this, var2, (ConglomerateDescriptor)null, var3, var1, var6);
      var1.considerCost(this, var2, var6, var3);
      return var6;
   }

   void pushExpressions(PredicateList var1) throws StandardException {
      if (this.leftResultSet instanceof UnionNode) {
         ((UnionNode)this.leftResultSet).pushExpressions(var1);
      } else if (this.leftResultSet instanceof SelectNode) {
         var1.pushExpressionsIntoSelect((SelectNode)this.leftResultSet, true);
      }

      if (this.rightResultSet instanceof UnionNode) {
         ((UnionNode)this.rightResultSet).pushExpressions(var1);
      } else if (this.rightResultSet instanceof SelectNode) {
         var1.pushExpressionsIntoSelect((SelectNode)this.rightResultSet, true);
      }

   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      Optimizable var2 = super.modifyAccessPath(var1);
      return this.addNewNodesCalled ? var2 : (Optimizable)this.addNewNodes();
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      ResultSetNode var1 = super.modifyAccessPaths();
      return this.addNewNodesCalled ? var1 : this.addNewNodes();
   }

   private ResultSetNode addNewNodes() throws StandardException {
      Object var1 = this;
      if (this.addNewNodesCalled) {
         return this;
      } else {
         this.addNewNodesCalled = true;
         if (!this.all) {
            if (!this.columnTypesAndLengthsMatch()) {
               var1 = new NormalizeResultSetNode(this, (ResultColumnList)null, (Properties)null, false, this.getContextManager());
            }

            var1 = new DistinctNode(((ResultSetNode)var1).genProjectRestrict(), false, this.tableProperties, this.getContextManager());
            ((FromTable)var1).setTableNumber(this.tableNumber);
            ((ResultSetNode)var1).setReferencedTableMap((JBitSet)this.getReferencedTableMap().clone());
            this.all = true;
         }

         for(int var2 = 0; var2 < this.qec.size(); ++var2) {
            OrderByList var3 = this.qec.getOrderByList(var2);
            if (var3 != null) {
               var1 = new OrderByNode((ResultSetNode)var1, var3, this.tableProperties, this.getContextManager());
            }

            ValueNode var4 = this.qec.getOffset(var2);
            ValueNode var5 = this.qec.getFetchFirst(var2);
            if (var4 != null || var5 != null) {
               ResultColumnList var6 = ((ResultSetNode)var1).getResultColumns().copyListAndObjects();
               var6.genVirtualColumnNodes((ResultSetNode)var1, ((ResultSetNode)var1).getResultColumns());
               var1 = new RowCountNode((ResultSetNode)var1, var6, var4, var5, this.qec.getHasJDBCLimitClause()[var2], this.getContextManager());
            }
         }

         return (ResultSetNode)var1;
      }
   }

   public String toString() {
      return "";
   }

   public void bindExpressions(FromList var1) throws StandardException {
      super.bindExpressions(var1);
      if (this.topTableConstructor && !this.isInsertSource()) {
         DataTypeDescriptor[] var2 = new DataTypeDescriptor[this.leftResultSet.getResultColumns().size()];
         int var4 = 0;

         Object var3;
         SetOperatorNode var5;
         for(var3 = this; var3 instanceof SetOperatorNode; var3 = var5.leftResultSet) {
            var5 = (SetOperatorNode)var3;
            RowResultSetNode var6 = (RowResultSetNode)var5.rightResultSet;
            var4 += this.getParamColumnTypes(var2, var6);
         }

         var4 += this.getParamColumnTypes(var2, (RowResultSetNode)var3);
         if (var4 < var2.length) {
            throw StandardException.newException("42Y10", new Object[0]);
         }

         for(var3 = this; var3 instanceof SetOperatorNode; var3 = var5.leftResultSet) {
            var5 = (SetOperatorNode)var3;
            RowResultSetNode var10 = (RowResultSetNode)var5.rightResultSet;
            this.setParamColumnTypes(var2, var10);
         }

         this.setParamColumnTypes(var2, (RowResultSetNode)var3);
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.getFinalCostEstimate());
      var1.pushGetResultSetFactoryExpression(var2);
      this.leftResultSet.generate(var1, var2);
      if (!this.getResultColumns().isExactTypeAndLengthMatch(this.leftResultSet.getResultColumns())) {
         var1.pushGetResultSetFactoryExpression(var2);
         var2.swap();
         this.generateNormalizationResultSet(var1, var2, this.getCompilerContext().getNextResultSetNumber(), this.makeResultDescription());
      }

      this.rightResultSet.generate(var1, var2);
      if (!this.getResultColumns().isExactTypeAndLengthMatch(this.rightResultSet.getResultColumns())) {
         var1.pushGetResultSetFactoryExpression(var2);
         var2.swap();
         this.generateNormalizationResultSet(var1, var2, this.getCompilerContext().getNextResultSetNumber(), this.makeResultDescription());
      }

      var2.push(this.getResultSetNumber());
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getUnionResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 5);
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      if (this.getCandidateFinalCostEstimate() != null) {
         return this.getCandidateFinalCostEstimate();
      } else {
         CostEstimate var1 = this.leftResultSet.getFinalCostEstimate();
         CostEstimate var2 = this.rightResultSet.getFinalCostEstimate();
         this.setCandidateFinalCostEstimate(this.getNewCostEstimate());
         this.getCandidateFinalCostEstimate().setCost(var1.getEstimatedCost(), var1.rowCount(), var1.singleScanRowCount() + var2.singleScanRowCount());
         this.getCandidateFinalCostEstimate().add(var2, this.getCandidateFinalCostEstimate());
         return this.getCandidateFinalCostEstimate();
      }
   }

   String getOperatorName() {
      return "UNION";
   }
}
