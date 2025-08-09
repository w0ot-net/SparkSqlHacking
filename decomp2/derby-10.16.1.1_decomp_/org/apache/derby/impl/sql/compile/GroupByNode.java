package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.impl.sql.execute.AggregatorInfo;
import org.apache.derby.impl.sql.execute.AggregatorInfoList;
import org.apache.derby.shared.common.error.StandardException;

class GroupByNode extends SingleChildResultSetNode {
   GroupByList groupingList;
   private List aggregates;
   private AggregatorInfoList aggInfo;
   FromTable parent;
   private boolean addDistinctAggregate;
   private boolean singleInputRowOptimization;
   private int addDistinctAggregateColumnNum;
   private final boolean isInSortedOrder;
   private ValueNode havingClause;
   private SubqueryList havingSubquerys;

   GroupByNode(ResultSetNode var1, GroupByList var2, List var3, ValueNode var4, SubqueryList var5, int var6, ContextManager var7) throws StandardException {
      super(var1, (Properties)null, var7);
      this.setLevel(var6);
      this.havingClause = var4;
      this.havingSubquerys = var5;
      this.groupingList = var2;
      this.aggregates = var3;
      this.parent = this;
      ResultColumnList var8 = this.childResult.getResultColumns().copyListAndObjects();
      this.setResultColumns(this.childResult.getResultColumns());
      this.childResult.setResultColumns(var8);
      this.addAggregates();
      if (this.groupingList != null && this.groupingList.isRollup()) {
         this.getResultColumns().setNullability(true);
         this.parent.getResultColumns().setNullability(true);
      }

      if (!this.addDistinctAggregate && var2 != null) {
         ColumnReference[] var9 = new ColumnReference[this.groupingList.size()];
         int var10 = this.groupingList.size();

         int var11;
         for(var11 = 0; var11 < var10; ++var11) {
            GroupByColumn var12 = (GroupByColumn)this.groupingList.elementAt(var11);
            if (!(var12.getColumnExpression() instanceof ColumnReference)) {
               break;
            }

            var9[var11] = (ColumnReference)var12.getColumnExpression();
         }

         this.isInSortedOrder = var11 == var10 && this.childResult.isOrderedOn(var9, true, (List)null);
      } else {
         this.isInSortedOrder = false;
      }

   }

   boolean getIsInSortedOrder() {
      return this.isInSortedOrder;
   }

   private void addAggregates() throws StandardException {
      this.addNewPRNode();
      this.addNewColumnsForAggregation();
      this.addDistinctAggregatesToOrderBy();
   }

   private void addDistinctAggregatesToOrderBy() {
      int var1 = numDistinctAggregates(this.aggregates);
      if (var1 != 0) {
         AggregatorInfo var2 = null;
         int var3 = this.aggInfo.size();

         for(int var4 = 0; var4 < var3; ++var4) {
            var2 = (AggregatorInfo)this.aggInfo.elementAt(var4);
            if (var2.isDistinct()) {
               break;
            }
         }

         this.addDistinctAggregate = true;
         this.addDistinctAggregateColumnNum = var2.getInputColNum();
      }

   }

   private void addNewPRNode() throws StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());

      for(ResultColumn var3 : this.getResultColumns()) {
         if (!var3.isGenerated()) {
            var1.addElement(var3);
         }
      }

      var1.copyOrderBySelect(this.getResultColumns());
      this.parent = new ProjectRestrictNode(this, var1, (ValueNode)null, (PredicateList)null, (SubqueryList)null, this.havingSubquerys, this.tableProperties, this.getContextManager());
      this.childResult.setResultColumns(new ResultColumnList(this.getContextManager()));
      this.setResultColumns(new ResultColumnList(this.getContextManager()));
   }

   private ArrayList addUnAggColumns() throws StandardException {
      ResultColumnList var1 = this.childResult.getResultColumns();
      ResultColumnList var2 = this.getResultColumns();
      ArrayList var3 = new ArrayList();
      ArrayList var4 = null;
      if (this.havingClause != null) {
         var4 = new ArrayList();
      }

      for(GroupByColumn var6 : this.groupingList) {
         ResultColumn var7 = new ResultColumn("##UnaggColumn", var6.getColumnExpression(), this.getContextManager());
         var1.addElement(var7);
         var7.markGenerated();
         var7.bindResultColumnToExpression();
         var7.setVirtualColumnId(var1.size());
         ResultColumn var8 = new ResultColumn("##UnaggColumn", var6.getColumnExpression(), this.getContextManager());
         var2.addElement(var8);
         var8.markGenerated();
         var8.bindResultColumnToExpression();
         var8.setVirtualColumnId(var2.size());
         VirtualColumnNode var9 = new VirtualColumnNode(this, var8, var2.size(), this.getContextManager());
         ValueNode var10 = var6.getColumnExpression();
         SubstituteExpressionVisitor var11 = new SubstituteExpressionVisitor(var10, var9, AggregateNode.class);
         var3.add(var11);
         if (this.havingClause != null) {
            SubstituteExpressionVisitor var12 = new SubstituteExpressionVisitor(var10, var9, (Class)null);
            var4.add(var12);
         }

         var6.setColumnPosition(var1.size());
      }

      ExpressionSorter var13 = new ExpressionSorter();
      Collections.sort(var3, var13);

      for(int var14 = 0; var14 < var3.size(); ++var14) {
         this.parent.getResultColumns().accept((Visitor)var3.get(var14));
      }

      if (var4 != null) {
         Collections.sort(var4, var13);
      }

      return var4;
   }

   private void addNewColumnsForAggregation() throws StandardException {
      this.aggInfo = new AggregatorInfoList();
      ArrayList var1 = null;
      if (this.groupingList != null) {
         var1 = this.addUnAggColumns();
      }

      this.addAggregateColumns();
      if (this.havingClause != null) {
         if (var1 != null) {
            for(int var2 = 0; var2 < var1.size(); ++var2) {
               this.havingClause.accept((Visitor)var1.get(var2));
            }
         }

         CollectNodesVisitor var5 = new CollectNodesVisitor(ColumnReference.class, AggregateNode.class);
         this.havingClause.accept(var5);

         for(ColumnReference var4 : var5.getList()) {
            if (!var4.getGeneratedToReplaceAggregate() && !var4.getGeneratedToReplaceWindowFunctionCall() && var4.getSourceLevel() == this.level) {
               throw StandardException.newException("42X24", new Object[]{var4.getSQLColumnName()});
            }
         }
      }

   }

   private void addAggregateColumns() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      ResultColumnList var6 = this.childResult.getResultColumns();
      ResultColumnList var7 = this.getResultColumns();
      LanguageFactory var12 = this.getLanguageConnectionContext().getLanguageFactory();
      ReplaceAggregatesWithCRVisitor var13 = new ReplaceAggregatesWithCRVisitor(new ResultColumnList(this.getContextManager()), ((FromTable)this.childResult).getTableNumber(), ResultSetNode.class);
      this.parent.getResultColumns().accept(var13);
      if (this.havingClause != null) {
         var13 = new ReplaceAggregatesWithCRVisitor(new ResultColumnList(this.getContextManager()), ((FromTable)this.childResult).getTableNumber());
         this.havingClause.accept(var13);
         ProjectRestrictNode var14 = (ProjectRestrictNode)this.parent;
         var14.setRestriction(this.havingClause);
      }

      int var23 = this.aggregates.size();

      for(int var15 = 0; var15 < var23; ++var15) {
         AggregateNode var16 = (AggregateNode)this.aggregates.get(var15);
         ResultColumn var3 = new ResultColumn("##aggregate result", var16.getNewNullResultExpression(), this.getContextManager());
         var3.markGenerated();
         var3.bindResultColumnToExpression();
         var6.addElement(var3);
         var3.setVirtualColumnId(var6.size());
         int var11 = var3.getVirtualColumnId();
         ColumnReference var2 = new ColumnReference(var3.getName(), (TableName)null, this.getContextManager());
         var2.setSource(var3);
         var2.setNestingLevel(this.getLevel());
         var2.setSourceLevel(this.getLevel());
         ResultColumn var4 = new ResultColumn(var3.getColumnName(), var2, this.getContextManager());
         var4.markGenerated();
         var4.bindResultColumnToExpression();
         var7.addElement(var4);
         var4.setVirtualColumnId(var7.size());
         var2 = var16.getGeneratedRef();
         var2.setSource(var4);
         var3 = var16.getNewExpressionResultColumn(var1);
         var3.markGenerated();
         var3.bindResultColumnToExpression();
         var6.addElement(var3);
         var3.setVirtualColumnId(var6.size());
         int var10 = var3.getVirtualColumnId();
         ResultColumn var5 = new ResultColumn("##aggregate expression", var16.getNewNullResultExpression(), this.getContextManager());
         var4 = this.getColumnReference(var3, var1);
         var7.addElement(var4);
         var4.setVirtualColumnId(var7.size());
         var3 = var16.getNewAggregatorResultColumn(var1);
         var3.markGenerated();
         var3.bindResultColumnToExpression();
         var6.addElement(var3);
         var3.setVirtualColumnId(var6.size());
         int var9 = var3.getVirtualColumnId();
         var4 = this.getColumnReference(var3, var1);
         var7.addElement(var4);
         var4.setVirtualColumnId(var7.size());
         ResultColumnList var8 = new ResultColumnList(this.getContextManager());
         var8.addElement(var5);
         this.aggInfo.addElement(new AggregatorInfo(var16.getAggregateName(), var16.getAggregatorClassName(), var10 - 1, var11 - 1, var9 - 1, var16.isDistinct(), var12.getResultDescription(var8.makeResultDescriptors(), "SELECT")));
      }

   }

   final FromTable getParent() {
      return this.parent;
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      ((Optimizable)this.childResult).optimizeIt(var1, var2, var3, var4);
      CostEstimate var5 = super.optimizeIt(var1, var2, var3, var4);
      return var5;
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      CostEstimate var6 = ((Optimizable)this.childResult).estimateCost(var1, var2, var3, var4, var5);
      CostEstimate var7 = this.getCostEstimate(var4);
      var7.setCost(var6.getEstimatedCost(), var6.rowCount(), var6.singleScanRowCount());
      return var7;
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      return ((Optimizable)this.childResult).pushOptPredicate(var1);
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   boolean flattenableInFromSubquery(FromList var1) {
      return false;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.childResult = this.childResult.optimize(var1, var2, var3);
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.getCostEstimate().setCost(this.childResult.getCostEstimate().getEstimatedCost(), this.childResult.getCostEstimate().rowCount(), this.childResult.getCostEstimate().singleScanRowCount());
      return this;
   }

   ResultColumnDescriptor[] makeResultDescriptors() {
      return this.childResult.makeResultDescriptors();
   }

   boolean isOneRowResultSet() throws StandardException {
      return this.groupingList == null || this.groupingList.size() == 0;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      FormatableArrayHolder var3 = var1.getColumnOrdering(this.groupingList);
      if (this.addDistinctAggregate) {
         var3 = var1.addColumnToOrdering(var3, this.addDistinctAggregateColumnNum);
      }

      int var4 = var1.addItem(var3);
      int var5 = var1.addItem(this.aggInfo);
      var1.pushGetResultSetFactoryExpression(var2);
      this.childResult.generate(var1, var2);
      var2.push(this.isInSortedOrder);
      var2.push(var5);
      var2.push(var4);
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate()));
      var2.push(this.getResultColumns().getTotalColumnSize());
      var2.push(this.getResultSetNumber());
      if (this.groupingList != null && this.groupingList.size() != 0) {
         this.genGroupedAggregateResultSet(var1, var2);
      } else {
         this.genScalarAggregateResultSet(var1, var2);
      }

   }

   private void genScalarAggregateResultSet(ActivationClassBuilder var1, MethodBuilder var2) {
      String var3 = this.addDistinctAggregate ? "getDistinctScalarAggregateResultSet" : "getScalarAggregateResultSet";
      var2.push(this.singleInputRowOptimization);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, var3, "org.apache.derby.iapi.sql.execute.NoPutResultSet", 10);
   }

   private void genGroupedAggregateResultSet(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      String var3 = this.addDistinctAggregate ? "getDistinctGroupedAggregateResultSet" : "getGroupedAggregateResultSet";
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(this.groupingList.isRollup());
      var2.callMethod((short)185, (String)null, var3, "org.apache.derby.iapi.sql.execute.NoPutResultSet", 10);
   }

   private ResultColumn getColumnReference(ResultColumn var1, DataDictionary var2) throws StandardException {
      ColumnReference var3 = new ColumnReference(var1.getName(), (TableName)null, this.getContextManager());
      var3.setSource(var1);
      var3.setNestingLevel(this.getLevel());
      var3.setSourceLevel(this.getLevel());
      ResultColumn var4 = new ResultColumn(var1.getColumnName(), var3, this.getContextManager());
      var4.markGenerated();
      var4.bindResultColumnToExpression();
      return var4;
   }

   void considerPostOptimizeOptimizations(boolean var1) throws StandardException {
      if (this.groupingList == null && this.aggregates.size() == 1) {
         AggregateNode var2 = (AggregateNode)this.aggregates.get(0);
         AggregateDefinition var3 = var2.getAggregateDefinition();
         if (var3 instanceof MaxMinAggregateDefinition) {
            if (var2.getOperand() instanceof ColumnReference) {
               ColumnReference[] var4 = new ColumnReference[]{(ColumnReference)var2.getOperand()};
               ArrayList var5 = new ArrayList(1);
               boolean var6 = this.isOrderedOn(var4, false, var5);
               if (var6) {
                  boolean var7 = true;
                  int var8 = var4[0].getColumnNumber();
                  AccessPath var9 = this.getTrulyTheBestAccessPath();
                  if (var9 == null || var9.getConglomerateDescriptor() == null || var9.getConglomerateDescriptor().getIndexDescriptor() == null) {
                     return;
                  }

                  IndexRowGenerator var10 = var9.getConglomerateDescriptor().getIndexDescriptor();
                  int[] var11 = var10.baseColumnPositions();
                  boolean[] var12 = var10.isAscending();

                  for(int var13 = 0; var13 < var11.length; ++var13) {
                     if (var8 == var11[var13]) {
                        if (!var12[var13]) {
                           var7 = false;
                        }
                        break;
                     }
                  }

                  FromBaseTable var15 = (FromBaseTable)var5.get(0);
                  MaxMinAggregateDefinition var14 = (MaxMinAggregateDefinition)var3;
                  if ((var14.isMax() || !var7) && (!var14.isMax() || var7)) {
                     if (!var1 && (var14.isMax() && var7 || !var14.isMax() && !var7)) {
                        var15.disableBulkFetch();
                        var15.doSpecialMaxScan();
                        this.singleInputRowOptimization = true;
                     }
                  } else {
                     var15.disableBulkFetch();
                     this.singleInputRowOptimization = true;
                  }
               }
            } else if (var2.getOperand() instanceof ConstantNode) {
               this.singleInputRowOptimization = true;
            }
         }
      }

   }

   private static class ExpressionSorter implements Comparator {
      public int compare(SubstituteExpressionVisitor var1, SubstituteExpressionVisitor var2) {
         try {
            ValueNode var3 = var1.getSource();
            ValueNode var4 = var2.getSource();
            CollectNodesVisitor var7 = new CollectNodesVisitor(ColumnReference.class);
            var3.accept(var7);
            int var5 = var7.getList().size();
            var7 = new CollectNodesVisitor(ColumnReference.class);
            var4.accept(var7);
            int var6 = var7.getList().size();
            return var6 - var5;
         } catch (StandardException var8) {
            throw new RuntimeException(var8);
         }
      }
   }
}
