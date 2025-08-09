package org.apache.derby.impl.sql.compile;

import java.util.HashMap;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class SetOperatorNode extends TableOperatorNode {
   boolean all;
   ResultSetNode.QueryExpressionClauses qec = new ResultSetNode.QueryExpressionClauses();
   private PredicateList leftOptPredicates;
   private PredicateList rightOptPredicates;
   private PredicateList pushedPredicates;
   private HashMap leftScopedPreds;
   private HashMap rightScopedPreds;

   SetOperatorNode(ResultSetNode var1, ResultSetNode var2, boolean var3, Properties var4, ContextManager var5) throws StandardException {
      super(var1, var2, var4, var5);
      this.all = var3;
      this.setResultColumns(this.leftResultSet.getResultColumns().copyListAndObjects());
   }

   public Optimizable modifyAccessPath(JBitSet var1, PredicateList var2) throws StandardException {
      if (var2 != null && !this.getTrulyTheBestAccessPath().getJoinStrategy().isHashJoin()) {
         for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
            if (this.pushOptPredicate(var2.getOptPredicate(var3))) {
               var2.removeOptPredicate(var3);
            }
         }
      }

      CostEstimate var9 = this.getFinalCostEstimate();
      Object var4 = (ResultSetNode)this.modifyAccessPath(var1);
      CollectNodesVisitor var5 = new CollectNodesVisitor(UnionNode.class);
      this.accept(var5);
      boolean var6 = false;

      for(UnionNode var8 : var5.getList()) {
         if (var8.hasUnPushedPredicates()) {
            var6 = true;
            break;
         }
      }

      if (var6) {
         ProjectRestrictNode var10 = new ProjectRestrictNode((ResultSetNode)var4, ((ResultSetNode)var4).getResultColumns(), (ValueNode)null, this.pushedPredicates, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
         ((ResultSetNode)var10).setCostEstimate(var9.cloneMe());
         ((ResultSetNode)var10).setReferencedTableMap(((ResultSetNode)var4).getReferencedTableMap());
         var4 = var10;
      }

      return (Optimizable)var4;
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      if (!(this instanceof UnionNode)) {
         return false;
      } else {
         Predicate var2 = (Predicate)var1;
         if (!var2.pushableToSubqueries()) {
            return false;
         } else {
            JBitSet var3 = new JBitSet(this.getReferencedTableMap().size());
            BaseTableNumbersVisitor var4 = new BaseTableNumbersVisitor(var3);
            this.leftResultSet.accept(var4);
            boolean var5 = var3.getFirstSetBit() != -1;
            if (!var5) {
               return false;
            } else {
               var3.clearAll();
               this.rightResultSet.accept(var4);
               var5 = var3.getFirstSetBit() != -1;
               if (!var5) {
                  return false;
               } else {
                  var3.clearAll();
                  this.accept(var4);
                  int[] var6 = new int[]{-1};
                  Predicate var7 = null;
                  if (this.leftScopedPreds == null) {
                     this.leftScopedPreds = new HashMap();
                  } else {
                     var7 = (Predicate)this.leftScopedPreds.get(var2);
                  }

                  if (var7 == null) {
                     var7 = var2.getPredScopedForResultSet(var3, this.leftResultSet, var6);
                     this.leftScopedPreds.put(var2, var7);
                  }

                  this.getLeftOptPredicateList().addOptPredicate(var7);
                  var7 = null;
                  if (this.rightScopedPreds == null) {
                     this.rightScopedPreds = new HashMap();
                  } else {
                     var7 = (Predicate)this.rightScopedPreds.get(var2);
                  }

                  if (var7 == null) {
                     var7 = var2.getPredScopedForResultSet(var3, this.rightResultSet, var6);
                     this.rightScopedPreds.put(var2, var7);
                  }

                  this.getRightOptPredicateList().addOptPredicate(var7);
                  if (this.pushedPredicates == null) {
                     this.pushedPredicates = new PredicateList(this.getContextManager());
                  }

                  this.pushedPredicates.addOptPredicate(var2);
                  return true;
               }
            }
         }
      }
   }

   public void pullOptPredicates(OptimizablePredicateList var1) throws StandardException {
      if (this.pushedPredicates != null) {
         if (this.leftOptPredicates != null) {
            this.leftOptPredicates.removeAllElements();
         }

         if (this.rightOptPredicates != null) {
            this.rightOptPredicates.removeAllElements();
         }

         RemapCRsVisitor var2 = new RemapCRsVisitor(false);

         for(int var3 = 0; var3 < this.pushedPredicates.size(); ++var3) {
            Predicate var4 = (Predicate)this.pushedPredicates.getOptPredicate(var3);
            if (var4.isScopedForPush()) {
               var4.getAndNode().accept(var2);
            } else {
               var1.addOptPredicate(var4);
            }
         }

         this.pushedPredicates.removeAllElements();
      }
   }

   protected boolean hasUnPushedPredicates() {
      return this.leftOptPredicates != null && this.leftOptPredicates.size() > 0 || this.rightOptPredicates != null && this.rightOptPredicates.size() > 0;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   void bindResultColumns(FromList var1) throws StandardException {
      super.bindResultColumns(var1);
      this.buildRCL();
   }

   void bindResultColumns(TableDescriptor var1, FromVTI var2, ResultColumnList var3, DMLStatementNode var4, FromList var5) throws StandardException {
      super.bindResultColumns(var1, var2, var3, var4, var5);
      this.buildRCL();
   }

   private void buildRCL() throws StandardException {
      if (this.leftResultSet.getResultColumns().visibleSize() != this.rightResultSet.getResultColumns().visibleSize()) {
         throw StandardException.newException("42X58", new Object[]{this.getOperatorName()});
      } else {
         this.setResultColumns(this.leftResultSet.getResultColumns().copyListAndObjects());
         this.getResultColumns().removeGeneratedGroupingColumns();
         this.getResultColumns().removeOrderByColumns();
         this.getResultColumns().setUnionResultExpression(this.rightResultSet.getResultColumns(), this.tableNumber, this.level, this.getOperatorName());
      }
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      if (var1 == null) {
         ResultColumnList var2 = this.rightResultSet.getResultColumns();
         ResultColumnList var3 = this.leftResultSet.getResultColumns();
         this.leftResultSet.bindUntypedNullsToResultColumns(var3);
         this.rightResultSet.bindUntypedNullsToResultColumns(var2);
      } else {
         this.leftResultSet.bindUntypedNullsToResultColumns(var1);
         this.rightResultSet.bindUntypedNullsToResultColumns(var1);
      }

   }

   void replaceOrForbidDefaults(TableDescriptor var1, ResultColumnList var2, boolean var3) throws StandardException {
      this.leftResultSet.replaceOrForbidDefaults(var1, var2, var3);
      this.rightResultSet.replaceOrForbidDefaults(var1, var2, var3);
   }

   int getParamColumnTypes(DataTypeDescriptor[] var1, RowResultSetNode var2) throws StandardException {
      int var3 = 0;

      for(int var4 = 0; var4 < var1.length; ++var4) {
         if (var1[var4] == null) {
            ResultColumn var5 = (ResultColumn)var2.getResultColumns().elementAt(var4);
            if (!var5.getExpression().requiresTypeFromContext()) {
               var1[var4] = var5.getExpression().getTypeServices();
               ++var3;
            }
         }
      }

      return var3;
   }

   void setParamColumnTypes(DataTypeDescriptor[] var1, RowResultSetNode var2) throws StandardException {
      ResultColumnList var3 = var2.getResultColumns();
      int var4 = var3.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)var3.elementAt(var5);
         if (var6.getExpression().requiresTypeFromContext()) {
            var6.getExpression().setType(var1[var5]);
         }
      }

   }

   public void bindExpressions(FromList var1) throws StandardException {
      for(int var2 = 0; var2 < this.qec.size(); ++var2) {
         OrderByList var3 = this.qec.getOrderByList(var2);
         if (var3 != null) {
            var3.bindOrderByColumns(this);
            var3.pullUpOrderByColumns(this);
         }

         bindOffsetFetch(this.qec.getOffset(var2), this.qec.getFetchFirst(var2));
      }

      super.bindExpressions(var1);
   }

   void bindTargetExpressions(FromList var1) throws StandardException {
      this.leftResultSet.bindTargetExpressions(var1);
      this.rightResultSet.bindTargetExpressions(var1);
   }

   public void pushQueryExpressionSuffix() {
      this.qec.push();
   }

   void pushOrderByList(OrderByList var1) {
      this.qec.setOrderByList(var1);
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
      this.qec.setOffset(var1);
      this.qec.setFetchFirst(var2);
      this.qec.setHasJDBCLimitClause(var3);
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.leftResultSet = this.leftResultSet.preprocess(var1, var2, var3);
      this.rightResultSet = this.rightResultSet.preprocess(var1, var2, var3);
      this.setReferencedTableMap((JBitSet)this.leftResultSet.getReferencedTableMap().clone());
      this.getReferencedTableMap().or(this.rightResultSet.getReferencedTableMap());

      for(int var5 = 0; var5 < this.qec.size(); ++var5) {
         OrderByList var6 = this.qec.getOrderByList(var5);
         if (!this.all && var6 != null && var6.allAscending() && var6.isInOrderPrefix(this.getResultColumns())) {
            var6 = null;
            this.qec.setOrderByList(var5, (OrderByList)null);
         }

         if (var6 != null && var6.size() > 1) {
            var6.removeDupColumns();
         }
      }

      return this;
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return this.genProjectRestrict(var1);
   }

   void verifySelectStarSubquery(FromList var1, int var2) throws StandardException {
      this.leftResultSet.verifySelectStarSubquery(var1, var2);
      this.rightResultSet.verifySelectStarSubquery(var1, var2);
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      return this.leftResultSet.getFromTableByName(var1, var2, var3);
   }

   ResultSetNode setResultToBooleanTrueNode(boolean var1) throws StandardException {
      FromList var2 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      var2.addFromTable(this);
      var2.markAsTransparent();
      ResultColumnList var3 = new ResultColumnList(this.getContextManager());
      AllResultColumn var4 = new AllResultColumn((TableName)null, this.getContextManager());
      var3.addResultColumn(var4);
      SelectNode var5 = new SelectNode(var3, var2, (ValueNode)null, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      return ((ResultSetNode)var5).setResultToBooleanTrueNode(var1);
   }

   boolean flattenableInFromSubquery(FromList var1) {
      return false;
   }

   boolean performMaterialization(JBitSet var1) throws StandardException {
      return false;
   }

   abstract String getOperatorName();

   PredicateList getLeftOptPredicateList() throws StandardException {
      if (this.leftOptPredicates == null) {
         this.leftOptPredicates = new PredicateList(this.getContextManager());
      }

      return this.leftOptPredicates;
   }

   PredicateList getRightOptPredicateList() throws StandardException {
      if (this.rightOptPredicates == null) {
         this.rightOptPredicates = new PredicateList(this.getContextManager());
      }

      return this.rightOptPredicates;
   }
}
