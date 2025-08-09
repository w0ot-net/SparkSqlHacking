package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class SelectNode extends ResultSetNode {
   FromList fromList;
   FromTable targetTable;
   private List selectAggregates;
   private List whereAggregates;
   private List havingAggregates;
   ValueNode whereClause;
   ValueNode originalWhereClause;
   GroupByList groupByList;
   WindowList windows;
   OptimizerPlan overridingPlan;
   List windowFuncCalls;
   private boolean wasGroupBy;
   boolean orderByQuery;
   ResultSetNode.QueryExpressionClauses qec = new ResultSetNode.QueryExpressionClauses();
   PredicateList wherePredicates;
   SubqueryList selectSubquerys;
   SubqueryList whereSubquerys;
   SubqueryList havingSubquerys;
   private boolean bindTargetListOnly;
   private boolean isDistinct;
   private boolean orderByAndDistinctMerged;
   boolean originalWhereClauseHadSubqueries;
   private FromList preJoinFL;
   ValueNode havingClause;
   private int nestingLevel;

   SelectNode(ResultColumnList var1, FromList var2, ValueNode var3, GroupByList var4, ValueNode var5, WindowList var6, OptimizerPlan var7, ContextManager var8) throws StandardException {
      super(var8);
      this.setResultColumns(var1);
      if (this.getResultColumns() != null) {
         this.getResultColumns().markInitialSize();
      }

      this.fromList = var2;
      this.whereClause = var3;
      this.originalWhereClause = var3;
      this.groupByList = var4;
      this.havingClause = var5;
      this.windows = var6;
      this.overridingPlan = var7;
      this.bindTargetListOnly = false;
      this.originalWhereClauseHadSubqueries = false;
      if (this.whereClause != null) {
         CollectNodesVisitor var9 = new CollectNodesVisitor(SubqueryNode.class, SubqueryNode.class);
         this.whereClause.accept(var9);
         if (!var9.getList().isEmpty()) {
            this.originalWhereClauseHadSubqueries = true;
         }
      }

      if (this.getResultColumns() != null) {
         CollectNodesVisitor var12 = new CollectNodesVisitor(WindowFunctionNode.class, SelectNode.class);
         this.getResultColumns().accept(var12);
         this.windowFuncCalls = var12.getList();

         for(int var10 = 0; var10 < this.windowFuncCalls.size(); ++var10) {
            WindowFunctionNode var11 = (WindowFunctionNode)this.windowFuncCalls.get(var10);
            if (var11.getWindow() instanceof WindowDefinitionNode) {
               this.windows = this.addInlinedWindowDefinition(this.windows, var11);
            }
         }
      }

   }

   private WindowList addInlinedWindowDefinition(WindowList var1, WindowFunctionNode var2) {
      WindowDefinitionNode var3 = (WindowDefinitionNode)var2.getWindow();
      if (var1 == null) {
         var1 = new WindowList(this.getContextManager());
      }

      WindowDefinitionNode var4 = var3.findEquivalentWindow(var1);
      if (var4 != null) {
         var2.setWindow(var4);
      } else {
         var1.addWindow((WindowDefinitionNode)var2.getWindow());
      }

      return var1;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "SELECT";
   }

   void makeDistinct() {
      this.isDistinct = true;
   }

   void clearDistinct() {
      this.isDistinct = false;
   }

   boolean hasDistinct() {
      return this.isDistinct;
   }

   void printSubNodes(int var1) {
   }

   FromList getFromList() {
      return this.fromList;
   }

   ColumnReference findColumnReferenceInResult(String var1) throws StandardException {
      if (this.fromList.size() != 1) {
         return null;
      } else {
         FromTable var2 = (FromTable)this.fromList.elementAt(0);
         if ((!(var2 instanceof ProjectRestrictNode) || !(((ProjectRestrictNode)var2).getChildResult() instanceof FromBaseTable)) && !(var2 instanceof FromBaseTable)) {
            return null;
         } else {
            for(ResultColumn var4 : this.getResultColumns()) {
               if (!(var4.getExpression() instanceof ColumnReference)) {
                  return null;
               }

               ColumnReference var5 = (ColumnReference)var4.getExpression();
               if (var5.getColumnName().equals(var1)) {
                  return (ColumnReference)var5.getClone();
               }
            }

            return null;
         }
      }
   }

   ValueNode getWhereClause() {
      return this.whereClause;
   }

   PredicateList getWherePredicates() {
      return this.wherePredicates;
   }

   SubqueryList getSelectSubquerys() {
      return this.selectSubquerys;
   }

   SubqueryList getWhereSubquerys() {
      return this.whereSubquerys;
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      int var3 = this.fromList.size();
      this.wherePredicates = new PredicateList(this.getContextManager());
      this.preJoinFL = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      if (var2.size() == 0) {
         this.nestingLevel = 0;
      } else {
         this.nestingLevel = ((FromTable)var2.elementAt(0)).getLevel() + 1;
      }

      this.fromList.setLevel(this.nestingLevel);

      for(int var4 = 0; var4 < var3; ++var4) {
         var2.insertElementAt((ResultSetNode)this.fromList.elementAt(var4), 0);
      }

      this.fromList.bindTables(var1, var2);

      for(int var5 = 0; var5 < var3; ++var5) {
         var2.removeElementAt(0);
      }

      if (this.overridingPlan != null) {
         this.overridingPlan.bind(var1, this.getLanguageConnectionContext(), this.getCompilerContext());
      }

      return this;
   }

   void bindExpressions(FromList var1) throws StandardException {
      boolean var2 = this.getCompilerContext().skipTypePrivileges(true);
      int var3 = var1.size();
      int var4 = this.fromList.size();

      for(int var6 = 0; var6 < this.qec.size(); ++var6) {
         OrderByList var7 = this.qec.getOrderByList(var6);
         if (var7 != null) {
            var7.pullUpOrderByColumns(this);
         }
      }

      if (!this.bindTargetListOnly) {
         this.fromList.bindExpressions(var1);
      }

      this.selectSubquerys = new SubqueryList(this.getContextManager());
      this.selectAggregates = new ArrayList();

      for(int var9 = 0; var9 < var4; ++var9) {
         var1.insertElementAt((ResultSetNode)this.fromList.elementAt(var9), var9);
      }

      var1.setWindows(this.windows);
      this.getResultColumns().bindExpressions(var1, this.selectSubquerys, this.selectAggregates);
      if (this.bindTargetListOnly) {
         for(int var11 = 0; var11 < var4; ++var11) {
            var1.removeElementAt(0);
         }

      } else {
         this.whereAggregates = new ArrayList();
         this.whereSubquerys = new SubqueryList(this.getContextManager());
         CompilerContext var10 = this.getCompilerContext();
         if (this.whereClause != null) {
            var10.beginScope("whereScope");
            var10.pushCurrentPrivType(0);
            int var12 = this.orReliability(16384);
            this.whereClause = this.whereClause.bindExpression(var1, this.whereSubquerys, this.whereAggregates);
            var10.setReliability(var12);
            if (this.whereAggregates.size() > 0) {
               throw StandardException.newException("42903", new Object[0]);
            }

            if (this.whereClause.isParameterNode()) {
               throw StandardException.newException("42X19.S.2", new Object[0]);
            }

            this.whereClause = this.whereClause.checkIsBoolean();
            this.getCompilerContext().popCurrentPrivType();
            var10.endScope("whereScope");
            checkNoWindowFunctions(this.whereClause, "WHERE");
         }

         if (this.havingClause != null) {
            int var13 = this.orReliability(16384);
            this.havingAggregates = new ArrayList();
            this.havingSubquerys = new SubqueryList(this.getContextManager());
            this.havingClause.bindExpression(var1, this.havingSubquerys, this.havingAggregates);
            this.havingClause = this.havingClause.checkIsBoolean();
            checkNoWindowFunctions(this.havingClause, "HAVING");
            var10.setReliability(var13);
         }

         for(int var14 = 0; var14 < var4; ++var14) {
            var1.removeElementAt(0);
         }

         if (this.groupByList != null) {
            ArrayList var15 = new ArrayList(0);
            this.groupByList.bindGroupByColumns(this, var15);
            checkNoWindowFunctions(this.groupByList, "GROUP BY");
         }

         if (this.groupByList != null || this.selectAggregates.size() > 0) {
            VerifyAggregateExpressionsVisitor var16 = new VerifyAggregateExpressionsVisitor(this.groupByList);
            this.getResultColumns().accept(var16);
         }

         int var5 = numDistinctAggregates(this.selectAggregates);
         if (this.groupByList == null && var5 > 1) {
            throw StandardException.newException("42Z02", new Object[0]);
         } else {
            for(int var17 = 0; var17 < this.qec.size(); ++var17) {
               OrderByList var8 = this.qec.getOrderByList(var17);
               if (var8 != null) {
                  var8.bindOrderByColumns(this);
               }

               bindOffsetFetch(this.qec.getOffset(var17), this.qec.getFetchFirst(var17));
            }

            this.getCompilerContext().skipTypePrivileges(var2);
         }
      }
   }

   void bindExpressionsWithTables(FromList var1) throws StandardException {
      this.bindExpressions(var1);
   }

   void bindTargetExpressions(FromList var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(FromSubquery.class, FromSubquery.class);
      this.fromList.accept(var2);
      if (!var2.getList().isEmpty()) {
         this.bindTargetListOnly = false;
      } else {
         this.bindTargetListOnly = true;
      }

      this.bindExpressions(var1);
      this.bindTargetListOnly = false;
   }

   void bindResultColumns(FromList var1) throws StandardException {
      this.fromList.bindResultColumns(var1);
      super.bindResultColumns(var1);
      if (this.getResultColumns().size() > 1012) {
         throw StandardException.newException("54004", new Object[0]);
      } else if (this.getResultColumns().size() == 0) {
         throw StandardException.newException("42X81", new Object[0]);
      }
   }

   void bindResultColumns(TableDescriptor var1, FromVTI var2, ResultColumnList var3, DMLStatementNode var4, FromList var5) throws StandardException {
      this.fromList.bindResultColumns(var5);
      super.bindResultColumns(var1, var2, var3, var4, var5);
   }

   void pushExpressionsIntoSelect(Predicate var1) throws StandardException {
      this.wherePredicates.pullExpressions(this.getReferencedTableMap().size(), var1.getAndNode());
      this.fromList.pushPredicates(this.wherePredicates);
   }

   void verifySelectStarSubquery(FromList var1, int var2) throws StandardException {
      for(ResultColumn var4 : this.getResultColumns()) {
         if (var4 instanceof AllResultColumn) {
            if (var2 != 15) {
               throw StandardException.newException("42X38", new Object[0]);
            }

            String var5 = ((AllResultColumn)var4).getFullTableName();
            if (var5 != null && this.fromList.getFromTableByName(var5, (String)null, true) == null && var1.getFromTableByName(var5, (String)null, true) == null && this.fromList.getFromTableByName(var5, (String)null, false) == null && var1.getFromTableByName(var5, (String)null, false) == null) {
               throw StandardException.newException("42X10", new Object[]{var5});
            }
         }
      }

   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      return this.fromList.getFromTableByName(var1, var2, var3);
   }

   void rejectParameters() throws StandardException {
      super.rejectParameters();
      this.fromList.rejectParameters();
   }

   public void pushQueryExpressionSuffix() {
      this.qec.push();
   }

   void pushOrderByList(OrderByList var1) {
      this.qec.setOrderByList(var1);
      this.orderByQuery = true;
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
      this.qec.setOffset(var1);
      this.qec.setFetchFirst(var2);
      this.qec.setHasJDBCLimitClause(var3);
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      Object var4 = this;
      this.whereClause = this.normExpressions(this.whereClause);
      this.havingClause = this.normExpressions(this.havingClause);
      boolean var5 = this.fromList.LOJ_reorderable(var1);
      if (var5) {
         FromList var6 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         this.bindExpressions(var6);
         this.fromList.bindResultColumns(var6);
      }

      this.fromList.preprocess(var1, this.groupByList, this.whereClause);
      this.getResultColumns().preprocess(var1, this.fromList, this.whereSubquerys, this.wherePredicates);
      if (this.whereClause != null) {
         if (this.whereSubquerys != null) {
            this.whereSubquerys.markWhereSubqueries();
         }

         this.whereClause = this.whereClause.preprocess(var1, this.fromList, this.whereSubquerys, this.wherePredicates);
      }

      if (this.groupByList != null) {
         this.groupByList.preprocess(var1, this.fromList, this.whereSubquerys, this.wherePredicates);
      }

      if (this.havingClause != null) {
         this.havingSubquerys.markHavingSubqueries();
         this.havingClause = this.havingClause.preprocess(var1, this.fromList, this.havingSubquerys, this.wherePredicates);
      }

      if (this.whereClause != null) {
         this.wherePredicates.pullExpressions(var1, this.whereClause);
         this.whereClause = null;
      }

      this.fromList.flattenFromTables(this.getResultColumns(), this.wherePredicates, this.whereSubquerys, this.groupByList, this.havingClause);
      if (this.wherePredicates != null && this.wherePredicates.size() > 0 && this.fromList.size() > 0) {
         if (this.fromList.size() > 1) {
            this.performTransitiveClosure(var1);
         }

         for(int var10 = 0; var10 < this.qec.size(); ++var10) {
            OrderByList var7 = this.qec.getOrderByList(var10);
            if (var7 != null) {
               var7.removeConstantColumns(this.wherePredicates);
               if (var7.size() == 0) {
                  this.qec.setOrderByList(var10, (OrderByList)null);
                  this.getResultColumns().removeOrderByColumns();
               }
            }
         }
      }

      if (this.groupByList != null && this.havingClause == null && this.selectAggregates.isEmpty() && this.whereAggregates.isEmpty()) {
         this.isDistinct = true;
         this.groupByList = null;
         this.wasGroupBy = true;
      }

      if (this.isDistinct && this.groupByList == null) {
         int var11 = this.getResultColumns().allTopCRsFromSameTable();
         if (var11 != -1 && this.fromList.returnsAtMostSingleRow(this.getResultColumns(), this.whereClause, this.wherePredicates, this.getDataDictionary())) {
            this.isDistinct = false;
         }

         for(int var13 = 0; var13 < this.qec.size(); ++var13) {
            OrderByList var8 = this.qec.getOrderByList(var13);
            if (this.isDistinct && var8 != null && var8.allAscending()) {
               if (var8.isInOrderPrefix(this.getResultColumns())) {
                  this.qec.setOrderByList(var13, (OrderByList)null);
               } else {
                  var4 = this.genProjectRestrictForReordering();
                  var8.resetToSourceRCs();
                  this.setResultColumns(var8.reorderRCL(this.getResultColumns()));
                  ((ResultSetNode)var4).getResultColumns().removeOrderByColumns();
                  this.qec.setOrderByList(var13, (OrderByList)null);
               }

               this.orderByAndDistinctMerged = true;
            }
         }
      }

      this.fromList.pushPredicates(this.wherePredicates);
      this.setReferencedTableMap(new JBitSet(var1));
      int var12 = this.fromList.size();

      for(int var14 = 0; var14 < var12; ++var14) {
         this.getReferencedTableMap().or(((FromTable)this.fromList.elementAt(var14)).getReferencedTableMap());
      }

      if (var4 != this) {
         ((ResultSetNode)var4).setReferencedTableMap((JBitSet)this.getReferencedTableMap().clone());
      }

      if (this.qec.getOrderByList(0) != null) {
         CollectNodesVisitor var15 = new CollectNodesVisitor(WindowFunctionNode.class);
         this.qec.getOrderByList(0).accept(var15);

         for(WindowFunctionNode var9 : var15.getList()) {
            this.windowFuncCalls.add(var9);
            if (var9.getWindow() instanceof WindowDefinitionNode) {
               this.windows = this.addInlinedWindowDefinition(this.windows, var9);
            }
         }
      }

      return (ResultSetNode)var4;
   }

   private void performTransitiveClosure(int var1) throws StandardException {
      this.wherePredicates.joinClauseTransitiveClosure(var1, this.fromList, this.getCompilerContext());
      this.wherePredicates.searchClauseTransitiveClosure(var1, this.fromList.hashJoinSpecified());
   }

   private ValueNode normExpressions(ValueNode var1) throws StandardException {
      if (var1 != null) {
         var1 = var1.eliminateNots(false);
         var1 = var1.putAndsOnTop();
         var1 = var1.changeToCNF(true);
      }

      return var1;
   }

   ResultSetNode addNewPredicate(Predicate var1) throws StandardException {
      this.wherePredicates.addPredicate(var1);
      return this;
   }

   boolean flattenableInFromSubquery(FromList var1) {
      if (this.isDistinct) {
         return false;
      } else if (this.fromList.size() > 1) {
         return false;
      } else if (this.selectSubquerys != null && this.selectSubquerys.size() > 0) {
         return false;
      } else if (this.groupByList == null && this.havingClause == null) {
         if (!this.getResultColumns().isCloneable()) {
            return false;
         } else if (this.selectAggregates != null && this.selectAggregates.size() > 0) {
            return false;
         } else {
            for(int var2 = 0; var2 < this.qec.size(); ++var2) {
               if (this.qec.getOrderByList(var2) != null && this.qec.getOrderByList(var2).size() > 0) {
                  return false;
               }

               if (this.qec.getOffset(var2) != null || this.qec.getFetchFirst(var2) != null) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   ResultSetNode genProjectRestrict(int var1) throws StandardException {
      boolean[] var2 = new boolean[this.qec.size()];
      Object var3 = new ProjectRestrictNode((ResultSetNode)this.fromList.elementAt(0), this.getResultColumns(), this.whereClause, this.wherePredicates, this.selectSubquerys, this.whereSubquerys, (Properties)null, this.getContextManager());
      if (this.selectAggregates != null && this.selectAggregates.size() > 0 || this.groupByList != null) {
         List var4 = this.selectAggregates;
         if (this.havingAggregates != null && !this.havingAggregates.isEmpty()) {
            this.havingAggregates.addAll(this.selectAggregates);
            var4 = this.havingAggregates;
         }

         GroupByNode var5 = new GroupByNode((ResultSetNode)var3, this.groupByList, var4, this.havingClause, this.havingSubquerys, this.nestingLevel, this.getContextManager());
         var5.considerPostOptimizeOptimizations(this.originalWhereClause != null);
         var5.assignCostEstimate(this.getOptimizer().getOptimizedCost());
         this.groupByList = null;
         var3 = var5.getParent();

         for(int var6 = 0; var6 < var2.length; ++var6) {
            var2[var6] = var2[var6] || var5.getIsInSortedOrder();
         }
      }

      if (this.windows != null) {
         if (this.windows.size() > 1) {
            throw StandardException.newException("42ZC1", new Object[0]);
         }

         WindowDefinitionNode var10 = (WindowDefinitionNode)this.windows.elementAt(0);
         WindowResultSetNode var15 = new WindowResultSetNode((ResultSetNode)var3, var10, this.windowFuncCalls, this.nestingLevel, this.getContextManager());
         var3 = var15.getParent();
         var15.assignCostEstimate(this.getOptimizer().getOptimizedCost());
      }

      if (this.isDistinct) {
         this.getResultColumns().verifyAllOrderable();
         boolean var11 = false;
         if (var1 == 1 && !this.orderByAndDistinctMerged) {
            boolean var16 = true;
            HashSet var21 = new HashSet();
            int var7 = this.getResultColumns().size();

            for(int var8 = 1; var8 <= var7; ++var8) {
               BaseColumnNode var9 = this.getResultColumns().getResultColumn(var8).getBaseColumnNode();
               if (var9 == null) {
                  var16 = false;
                  break;
               }

               var21.add(var9);
            }

            if (var16 && ((ResultSetNode)var3).isPossibleDistinctScan(var21)) {
               ((ResultSetNode)var3).markForDistinctScan();
               var11 = true;
            }
         }

         if (!var11) {
            boolean var17 = this.isOrderedResult(this.getResultColumns(), (ResultSetNode)var3, !this.orderByAndDistinctMerged);
            var3 = new DistinctNode((ResultSetNode)var3, var17, (Properties)null, this.getContextManager());
            ((ResultSetNode)var3).setCostEstimate(this.getCostEstimate().cloneMe());

            for(int var22 = 0; var22 < var2.length; ++var22) {
               var2[var22] = var2[var22] || var17;
            }
         }
      }

      for(int var12 = 0; var12 < this.qec.size(); ++var12) {
         OrderByList var18 = this.qec.getOrderByList(var12);
         if (var18 != null) {
            if (var18.getSortNeeded()) {
               var3 = new OrderByNode((ResultSetNode)var3, var18, (Properties)null, this.getContextManager());
               ((ResultSetNode)var3).setCostEstimate(this.getCostEstimate().cloneMe());
            }

            int var23 = this.getResultColumns().getOrderBySelect();
            if (var23 > 0) {
               ResultColumnList var25 = ((ResultSetNode)var3).getResultColumns();
               ResultColumnList var27 = var25.copyListAndObjects();
               ((ResultSetNode)var3).setResultColumns(var27);
               var25.removeOrderByColumns();
               var25.genVirtualColumnNodes((ResultSetNode)var3, var27);
               var3 = new ProjectRestrictNode((ResultSetNode)var3, var25, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
            }
         }

         ValueNode var24 = this.qec.getOffset(var12);
         ValueNode var26 = this.qec.getFetchFirst(var12);
         if (var24 != null || var26 != null) {
            ResultColumnList var28 = ((ResultSetNode)var3).getResultColumns();
            ResultColumnList var29 = var28.copyListAndObjects();
            ((ResultSetNode)var3).setResultColumns(var29);
            var28.genVirtualColumnNodes((ResultSetNode)var3, var29);
            var3 = new RowCountNode((ResultSetNode)var3, var28, var24, var26, this.qec.getHasJDBCLimitClause()[var12], this.getContextManager());
         }
      }

      if (this.wasGroupBy && this.getResultColumns().numGeneratedColumnsForGroupBy() > 0 && this.windows == null) {
         ResultColumnList var13 = ((ResultSetNode)var3).getResultColumns();
         ResultColumnList var19 = var13.copyListAndObjects();
         ((ResultSetNode)var3).setResultColumns(var19);
         var13.removeGeneratedGroupingColumns();
         var13.genVirtualColumnNodes((ResultSetNode)var3, var19);
         var3 = new ProjectRestrictNode((ResultSetNode)var3, var13, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
      }

      for(int var14 = 0; var14 < this.qec.size(); ++var14) {
         OrderByList var20 = this.qec.getOrderByList(var14);
         if ((var20 == null || !var20.getSortNeeded()) && this.orderByQuery) {
            var2[var14] = true;
         }

         if (var2[var14]) {
            ((ResultSetNode)var3).adjustForSortElimination(var20);
         }

         ((ResultSetNode)var3).setCostEstimate(this.getCostEstimate().cloneMe());
      }

      return (ResultSetNode)var3;
   }

   private boolean isOrderedResult(ResultColumnList var1, ResultSetNode var2, boolean var3) throws StandardException {
      int var4 = 0;

      for(ResultColumn var6 : var1) {
         if (var6.getExpression() instanceof ColumnReference) {
            ++var4;
         } else if (!(var6.getExpression() instanceof ConstantNode)) {
            return false;
         }
      }

      if (var4 == 0) {
         return true;
      } else {
         ColumnReference[] var9 = new ColumnReference[var4];
         int var10 = 0;

         for(ResultColumn var8 : var1) {
            if (var8.getExpression() instanceof ColumnReference) {
               var9[var10++] = (ColumnReference)var8.getExpression();
            }
         }

         return var2.isOrderedOn(var9, var3, (List)null);
      }
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return this;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      for(int var6 = 0; var6 < this.qec.size(); ++var6) {
         OrderByList var7 = this.qec.getOrderByList(var6);
         if (var7 != null && var7.size() > 1) {
            var7.removeDupColumns();
         }
      }

      if (this.wherePredicates != null) {
         for(int var9 = this.wherePredicates.size() - 1; var9 >= 0; --var9) {
            if (((Predicate)this.wherePredicates.elementAt(var9)).isScopedForPush()) {
               this.wherePredicates.removeOptPredicate(var9);
            }
         }
      }

      if (var2 != null) {
         if (this.wherePredicates == null) {
            this.wherePredicates = new PredicateList(this.getContextManager());
         }

         int var10 = var2.size();

         for(int var12 = var10 - 1; var12 >= 0; --var12) {
            Predicate var8 = (Predicate)var2.getOptPredicate(var12);
            if (var8.isScopedToSourceResultSet()) {
               this.wherePredicates.addOptPredicate(var8);
               var2.removeOptPredicate(var8);
            }
         }
      }

      Optimizer var5 = this.getOptimizer(this.fromList, this.wherePredicates, var1, this.qec.getOrderByList(0), this.overridingPlan);
      var5.setOuterRows(var3);

      while(var5.getNextPermutation()) {
         while(var5.getNextDecoratedPermutation()) {
            var5.costPermutation();
         }
      }

      if (this.wherePredicates != null) {
         for(int var11 = this.wherePredicates.size() - 1; var11 >= 0; --var11) {
            Predicate var13 = (Predicate)this.wherePredicates.getOptPredicate(var11);
            if (var13.isScopedForPush()) {
               var2.addOptPredicate(var13);
               this.wherePredicates.removeOptPredicate(var13);
            }
         }
      }

      this.setCostEstimate(var5.getOptimizedCost());
      if (this.selectAggregates != null && this.selectAggregates.size() > 0) {
         this.getCostEstimate().setEstimatedRowCount((long)var3);
         this.getCostEstimate().setSingleScanRowCount((double)1.0F);
      }

      this.selectSubquerys.optimize(var1, this.getCostEstimate().rowCount());
      if (this.whereSubquerys != null && this.whereSubquerys.size() > 0) {
         this.whereSubquerys.optimize(var1, this.getCostEstimate().rowCount());
      }

      if (this.havingSubquerys != null && this.havingSubquerys.size() > 0) {
         this.havingSubquerys.optimize(var1, this.getCostEstimate().rowCount());
      }

      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceEndQueryBlock();
      }

      return this;
   }

   private Optimizer getOptimizer(OptimizableList var1, OptimizablePredicateList var2, DataDictionary var3, RequiredRowOrdering var4, OptimizerPlan var5) throws StandardException {
      if (this.getOptimizer() == null) {
         OptimizerFactory var6 = this.getLanguageConnectionContext().getOptimizerFactory();
         this.setOptimizer(var6.getOptimizer(var1, var2, var3, var4, this.getCompilerContext().getNumTables(), var5, this.getLanguageConnectionContext()));
      }

      this.getOptimizer().prepForNextRound();
      return this.getOptimizer();
   }

   ResultSetNode modifyAccessPaths(PredicateList var1) throws StandardException {
      this.getOptimizerImpl().addScopedPredicatesToList(var1, this.getContextManager());
      return this.modifyAccessPaths();
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      int var1 = this.fromList.size();
      this.getOptimizer().modifyAccessPaths();
      this.setCostEstimate(this.getOptimizer().getFinalCost());
      this.selectSubquerys.modifyAccessPaths();
      if (this.whereSubquerys != null && this.whereSubquerys.size() > 0) {
         this.whereSubquerys.modifyAccessPaths();
      }

      if (this.havingSubquerys != null && this.havingSubquerys.size() > 0) {
         this.havingSubquerys.modifyAccessPaths();
      }

      this.preJoinFL.removeAllElements();
      this.preJoinFL.nondestructiveAppend(this.fromList);

      while(this.fromList.size() > 1) {
         ResultSetNode var4 = (ResultSetNode)this.fromList.elementAt(0);
         ResultColumnList var2 = var4.getResultColumns();
         var4.setResultColumns(var2.copyListAndObjects());
         var2.genVirtualColumnNodes(var4, var4.getResultColumns());
         ResultSetNode var5 = (ResultSetNode)this.fromList.elementAt(1);
         ResultColumnList var3 = var5.getResultColumns();
         var5.setResultColumns(var3.copyListAndObjects());
         var3.genVirtualColumnNodes(var5, var5.getResultColumns());
         var3.adjustVirtualColumnIds(var2.size());
         var2.nondestructiveAppend(var3);
         this.fromList.setElementAt(new JoinNode(var4, var5, (ValueNode)null, (ResultColumnList)null, var2, (Properties)null, this.fromList.properties, this.getContextManager()), 0);
         this.fromList.removeElementAt(1);
      }

      return this.genProjectRestrict(var1);
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      return this.getOptimizer().getFinalCost();
   }

   boolean isUpdatableCursor(DataDictionary var1) throws StandardException {
      if (this.isDistinct) {
         return false;
      } else if (this.selectAggregates != null && this.selectAggregates.size() <= 0) {
         if (this.groupByList == null && this.havingClause == null) {
            if (this.fromList.size() != 1) {
               return false;
            } else {
               this.targetTable = (FromTable)this.fromList.elementAt(0);
               if (this.targetTable instanceof FromVTI) {
                  return ((FromVTI)this.targetTable).isUpdatableCursor();
               } else if (!(this.targetTable instanceof FromBaseTable)) {
                  return false;
               } else if (!this.targetTable.columnsAreUpdatable()) {
                  return false;
               } else {
                  TableDescriptor var2 = this.getTableDescriptor(((FromBaseTable)this.targetTable).getBaseTableName(), this.getSchemaDescriptor(((FromBaseTable)this.targetTable).getTableNameField().getSchemaName()));
                  if (var2.getTableType() == 1) {
                     return false;
                  } else if (var2.getTableType() == 2) {
                     return false;
                  } else if (this.getSelectSubquerys() != null && this.getSelectSubquerys().size() != 0) {
                     return false;
                  } else {
                     return this.getWhereSubquerys() == null || this.getWhereSubquerys().size() == 0;
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   FromTable getCursorTargetTable() {
      return this.targetTable;
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return this.fromList.referencesTarget(var1, var2) || this.selectSubquerys != null && this.selectSubquerys.referencesTarget(var1, var2) || this.whereSubquerys != null && this.whereSubquerys.referencesTarget(var1, var2);
   }

   boolean subqueryReferencesTarget(String var1, boolean var2) throws StandardException {
      return this.selectSubquerys != null && this.selectSubquerys.referencesTarget(var1, var2) || this.whereSubquerys != null && this.whereSubquerys.referencesTarget(var1, var2);
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      this.fromList.bindUntypedNullsToResultColumns(var1);
   }

   void decrementLevel(int var1) {
      this.fromList.decrementLevel(var1);
      this.selectSubquerys.decrementLevel(var1);
      this.whereSubquerys.decrementLevel(var1);
      this.wherePredicates.decrementLevel(this.fromList, var1);
   }

   boolean uniqueSubquery(boolean var1) throws StandardException {
      ColumnReference var2 = null;
      ResultColumn var3 = (ResultColumn)this.getResultColumns().elementAt(0);
      if (var1 && var3.getExpression() instanceof ColumnReference) {
         var2 = (ColumnReference)var3.getExpression();
         if (var2.getCorrelated()) {
            var2 = null;
         }
      }

      return this.fromList.returnsAtMostSingleRow(var2 == null ? null : this.getResultColumns(), this.whereClause, this.wherePredicates, this.getDataDictionary());
   }

   int updateTargetLockMode() {
      return this.fromList.updateTargetLockMode();
   }

   boolean returnsAtMostOneRow() {
      return this.groupByList == null && this.selectAggregates != null && !this.selectAggregates.isEmpty();
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.fromList.referencesSessionSchema() || this.selectSubquerys != null && this.selectSubquerys.referencesSessionSchema() || this.whereSubquerys != null && this.whereSubquerys.referencesSessionSchema();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.fromList != null) {
         this.fromList = (FromList)this.fromList.accept(var1);
      }

      if (this.whereClause != null) {
         this.whereClause = (ValueNode)this.whereClause.accept(var1);
      }

      if (this.wherePredicates != null) {
         this.wherePredicates = (PredicateList)this.wherePredicates.accept(var1);
      }

      if (this.havingClause != null) {
         this.havingClause = (ValueNode)this.havingClause.accept(var1);
      }

      if (!(var1 instanceof HasCorrelatedCRsVisitor)) {
         if (this.selectSubquerys != null) {
            this.selectSubquerys = (SubqueryList)this.selectSubquerys.accept(var1);
         }

         if (this.whereSubquerys != null) {
            this.whereSubquerys = (SubqueryList)this.whereSubquerys.accept(var1);
         }

         if (this.groupByList != null) {
            this.groupByList = (GroupByList)this.groupByList.accept(var1);
         }

         for(int var2 = 0; var2 < this.qec.size(); ++var2) {
            OrderByList var3 = this.qec.getOrderByList(var2);
            if (var3 != null) {
               this.qec.setOrderByList(var2, (OrderByList)var3.accept(var1));
            }

            ValueNode var4 = this.qec.getOffset(var2);
            if (var4 != null) {
               this.qec.setOffset(var2, (ValueNode)var4.accept(var1));
            }

            ValueNode var5 = this.qec.getFetchFirst(var2);
            if (var5 != null) {
               this.qec.setFetchFirst(var2, (ValueNode)var5.accept(var1));
            }
         }

         if (this.preJoinFL != null) {
            this.preJoinFL = (FromList)this.preJoinFL.accept(var1);
         }

         if (this.windows != null) {
            this.windows = (WindowList)this.windows.accept(var1);
         }
      }

   }

   boolean hasAggregatesInSelectList() {
      return !this.selectAggregates.isEmpty();
   }

   boolean hasWindows() {
      return this.windows != null;
   }

   static void checkNoWindowFunctions(QueryTreeNode var0, String var1) throws StandardException {
      HasNodeVisitor var2 = new HasNodeVisitor(WindowFunctionNode.class, SubqueryNode.class);
      var0.accept(var2);
      if (var2.hasNode()) {
         throw StandardException.newException("42ZC2", new Object[]{var1});
      }
   }

   void replaceOrForbidDefaults(TableDescriptor var1, ResultColumnList var2, boolean var3) throws StandardException {
   }

   boolean hasOffsetFetchFirst() {
      return this.qec.hasOffsetFetchFirst();
   }
}
