package org.apache.derby.impl.sql.compile;

import java.util.BitSet;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public class IntersectOrExceptNode extends SetOperatorNode {
   private int opType;
   public static final int INTERSECT_OP = 1;
   public static final int EXCEPT_OP = 2;
   private boolean addNewNodesCalled;
   private int[] intermediateOrderByColumns;
   private int[] intermediateOrderByDirection;
   private boolean[] intermediateOrderByNullsLow;

   IntersectOrExceptNode(int var1, ResultSetNode var2, ResultSetNode var3, boolean var4, Properties var5, ContextManager var6) throws StandardException {
      super(var2, var3, var4, var5, var6);
      this.opType = var1;
   }

   private int getOpType() {
      return this.opType;
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.intermediateOrderByColumns = new int[this.getResultColumns().size()];
      this.intermediateOrderByDirection = new int[this.intermediateOrderByColumns.length];
      this.intermediateOrderByNullsLow = new boolean[this.intermediateOrderByColumns.length];
      OrderByList var4 = this.qec.getOrderByList(0);
      if (var4 != null) {
         BitSet var5 = new BitSet(this.intermediateOrderByColumns.length);
         int var6 = var4.size();
         int var7 = 0;

         for(int var8 = 0; var8 < var6; ++var8) {
            if (!var5.get(var8)) {
               OrderByColumn var9 = var4.getOrderByColumn(var8);
               this.intermediateOrderByDirection[var7] = var9.isAscending() ? 1 : -1;
               this.intermediateOrderByNullsLow[var7] = var9.isNullsOrderedLow();
               int var10 = var9.getResultColumn().getColumnPosition() - 1;
               this.intermediateOrderByColumns[var7] = var10;
               var5.set(var10);
               ++var7;
            }
         }

         for(int var12 = 0; var12 < this.intermediateOrderByColumns.length; ++var12) {
            if (!var5.get(var12)) {
               this.intermediateOrderByDirection[var7] = 1;
               this.intermediateOrderByNullsLow[var7] = false;
               this.intermediateOrderByColumns[var7] = var12;
               ++var7;
            }
         }

         this.qec.setOrderByList(0, (OrderByList)null);
      } else {
         for(int var11 = 0; var11 < this.intermediateOrderByColumns.length; this.intermediateOrderByColumns[var11] = var11++) {
            this.intermediateOrderByDirection[var11] = 1;
            this.intermediateOrderByNullsLow[var11] = false;
         }
      }

      this.pushOrderingDown(this.leftResultSet);
      this.pushOrderingDown(this.rightResultSet);
      return super.preprocess(var1, var2, var3);
   }

   private void pushOrderingDown(ResultSetNode var1) throws StandardException {
      ContextManager var2 = this.getContextManager();
      OrderByList var3 = new OrderByList((ResultSetNode)null, var2);

      for(int var4 = 0; var4 < this.intermediateOrderByColumns.length; ++var4) {
         OrderByColumn var5 = new OrderByColumn(new NumericConstantNode(TypeId.getBuiltInTypeId(4), this.intermediateOrderByColumns[var4] + 1, var2), var2);
         if (this.intermediateOrderByDirection[var4] < 0) {
            var5.setDescending();
         }

         if (this.intermediateOrderByNullsLow[var4]) {
            var5.setNullsOrderedLow();
         }

         var3.addOrderByColumn(var5);
      }

      var3.bindOrderByColumns(var1);
      var1.pushQueryExpressionSuffix();
      var1.pushOrderByList(var3);
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      this.leftResultSet = this.optimizeSource(var4, this.leftResultSet, (PredicateList)null, var3);
      this.rightResultSet = this.optimizeSource(var4, this.rightResultSet, (PredicateList)null, var3);
      CostEstimate var6 = this.getCostEstimate(var4);
      CostEstimate var7 = this.leftResultSet.getCostEstimate();
      CostEstimate var8 = this.rightResultSet.getCostEstimate();
      var6.setCost(var7.getEstimatedCost() + var8.getEstimatedCost(), this.getRowCountEstimate(var7.rowCount(), var8.rowCount()), this.getSingleScanRowCountEstimate(var7.singleScanRowCount(), var8.singleScanRowCount()));
      return var6;
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
      if (this.addNewNodesCalled) {
         return this;
      } else {
         this.addNewNodesCalled = true;
         Object var1 = this;

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

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.getFinalCostEstimate());
      var1.pushGetResultSetFactoryExpression(var2);
      this.getLeftResultSet().generate(var1, var2);
      this.getRightResultSet().generate(var1, var2);
      var1.pushThisAsActivation(var2);
      var2.push(this.getResultSetNumber());
      var2.push(this.getCostEstimate().getEstimatedRowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(this.getOpType());
      var2.push(this.all);
      var2.push(this.getCompilerContext().addSavedObject(this.intermediateOrderByColumns));
      var2.push(this.getCompilerContext().addSavedObject(this.intermediateOrderByDirection));
      var2.push(this.getCompilerContext().addSavedObject(this.intermediateOrderByNullsLow));
      var2.callMethod((short)185, (String)null, "getSetOpResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 11);
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      if (this.getCandidateFinalCostEstimate() != null) {
         return this.getCandidateFinalCostEstimate();
      } else {
         CostEstimate var1 = this.leftResultSet.getFinalCostEstimate();
         CostEstimate var2 = this.rightResultSet.getFinalCostEstimate();
         this.setCandidateFinalCostEstimate(this.getNewCostEstimate());
         this.getCandidateFinalCostEstimate().setCost(var1.getEstimatedCost() + var2.getEstimatedCost(), this.getRowCountEstimate(var1.rowCount(), var2.rowCount()), this.getSingleScanRowCountEstimate(var1.singleScanRowCount(), var2.singleScanRowCount()));
         return this.getCandidateFinalCostEstimate();
      }
   }

   String getOperatorName() {
      switch (this.opType) {
         case 1 -> {
            return "INTERSECT";
         }
         case 2 -> {
            return "EXCEPT";
         }
         default -> {
            return "?";
         }
      }
   }

   double getRowCountEstimate(double var1, double var3) {
      switch (this.opType) {
         case 1 -> {
            return Math.min(var1, var3) / (double)2.0F;
         }
         case 2 -> {
            return (var1 + Math.max((double)0.0F, var1 - var3)) / (double)2.0F;
         }
         default -> {
            return (double)1.0F;
         }
      }
   }

   double getSingleScanRowCountEstimate(double var1, double var3) {
      return this.getRowCountEstimate(var1, var3);
   }
}
