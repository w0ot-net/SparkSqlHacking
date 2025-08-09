package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class HalfOuterJoinNode extends JoinNode {
   private boolean rightOuterJoin;
   private boolean transformed = false;

   HalfOuterJoinNode(ResultSetNode var1, ResultSetNode var2, ValueNode var3, ResultColumnList var4, boolean var5, Properties var6, ContextManager var7) throws StandardException {
      super(var1, var2, var3, var4, (ResultColumnList)null, var6, (Properties)null, var7);
      this.rightOuterJoin = var5;
      this.flattenableJoin = false;
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      FromTable var2 = (FromTable)this.leftResultSet;
      return var2.getReferencedTableMap().contains(var1.getReferencedMap()) ? var2.pushOptPredicate(var1) : false;
   }

   public String toString() {
      return "";
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      if (this.rightOuterJoin) {
         ResultSetNode var5 = this.leftResultSet;
         this.leftResultSet = this.rightResultSet;
         this.rightResultSet = var5;
         this.transformed = true;
      }

      ResultSetNode var4 = super.preprocess(var1, var2, var3);
      return var4;
   }

   void pushExpressions(PredicateList var1) throws StandardException {
      FromTable var2 = (FromTable)this.leftResultSet;
      FromTable var3 = (FromTable)this.rightResultSet;
      this.pushExpressionsToLeft(var1);

      for(int var4 = this.joinPredicates.size() - 1; var4 >= 0; --var4) {
         Predicate var5 = (Predicate)this.joinPredicates.elementAt(var4);
         if (var5.getPushable()) {
            this.getRightPredicateList().addPredicate(var5);
            this.joinPredicates.removeElementAt(var4);
         }
      }

      PredicateList var6 = new PredicateList(this.getContextManager());
      var2.pushExpressions(this.getLeftPredicateList());
      var3.pushExpressions(var6);
   }

   boolean LOJ_reorderable(int var1) throws StandardException {
      boolean var2 = false;
      ResultSetNode var3;
      ResultSetNode var4;
      if (this.rightOuterJoin) {
         var3 = this.rightResultSet;
         var4 = this.leftResultSet;
      } else {
         var3 = this.leftResultSet;
         var4 = this.rightResultSet;
      }

      super.normExpressions();
      if (var3 instanceof FromBaseTable && var4 instanceof FromBaseTable) {
         return var2;
      } else {
         if (var3 instanceof HalfOuterJoinNode) {
            var2 = ((HalfOuterJoinNode)var3).LOJ_reorderable(var1) || var2;
         } else if (!(var3 instanceof FromBaseTable)) {
            return var2;
         }

         if (var4 instanceof HalfOuterJoinNode) {
            var2 = ((HalfOuterJoinNode)var4).LOJ_reorderable(var1) || var2;
         } else if (!(var4 instanceof FromBaseTable)) {
            return var2;
         }

         if (!this.rightOuterJoin && (!(var4 instanceof HalfOuterJoinNode) || !((HalfOuterJoinNode)var4).rightOuterJoin)) {
            JBitSet var5 = var3.LOJgetReferencedTables(var1);
            JBitSet var6 = var4.LOJgetReferencedTables(var1);
            if ((var5 == null || var6 == null) && var2) {
               return this.LOJ_bindResultColumns(var2);
            } else {
               if (var4 instanceof HalfOuterJoinNode) {
                  JBitSet var7 = ((HalfOuterJoinNode)var4).LOJgetRPReferencedTables(var1);
                  if (!this.isNullRejecting(this.joinClause, var5, var7)) {
                     return this.LOJ_bindResultColumns(var2);
                  }

                  JBitSet var8 = ((HalfOuterJoinNode)var4).LOJgetNPReferencedTables(var1);
                  if (this.isNullRejecting(((HalfOuterJoinNode)var4).joinClause, var7, var8)) {
                     if (super.subqueryList.size() != 0 || ((JoinNode)var4).subqueryList.size() != 0 || super.joinPredicates.size() != 0 || ((JoinNode)var4).joinPredicates.size() != 0 || super.usingClause != null || ((JoinNode)var4).usingClause != null) {
                        return this.LOJ_bindResultColumns(var2);
                     }

                     var2 = true;
                     ResultSetNode var10 = ((HalfOuterJoinNode)var4).leftResultSet;
                     ResultSetNode var11 = ((HalfOuterJoinNode)var4).rightResultSet;
                     ((HalfOuterJoinNode)var4).rightResultSet = var10;
                     ((HalfOuterJoinNode)var4).leftResultSet = var3;
                     ValueNode var12 = this.joinClause;
                     this.joinClause = ((HalfOuterJoinNode)var4).joinClause;
                     ((HalfOuterJoinNode)var4).joinClause = var12;
                     FromList var14 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
                     this.leftResultSet = var4;
                     this.rightResultSet = var11;
                     ((HalfOuterJoinNode)this.leftResultSet).setResultColumns((ResultColumnList)null);
                     ((JoinNode)this.leftResultSet).bindResultColumns(var14);
                     boolean var13 = ((HalfOuterJoinNode)this.leftResultSet).LOJ_reorderable(var1);
                  }
               }

               return this.LOJ_bindResultColumns(var2);
            }
         } else {
            return this.LOJ_bindResultColumns(var2);
         }
      }
   }

   private boolean isNullRejecting(ValueNode var1, JBitSet var2, JBitSet var3) throws StandardException {
      ValueNode var4 = var1;
      boolean var5 = false;

      while(var4 != null) {
         if (var4 instanceof AndNode var6) {
            var4 = var6.getLeftOperand();
         }

         if (var4 instanceof BinaryRelationalOperatorNode var7) {
            ValueNode var8 = var7.getLeftOperand();
            ValueNode var9 = var7.getRightOperand();
            boolean var10 = false;
            boolean var11 = false;
            if (var8 instanceof ColumnReference) {
               if (var2.get(((ColumnReference)var8).getTableNumber())) {
                  var10 = true;
               } else {
                  if (!var3.get(((ColumnReference)var8).getTableNumber())) {
                     return false;
                  }

                  var11 = true;
               }
            }

            if (var9 instanceof ColumnReference) {
               if (var2.get(((ColumnReference)var9).getTableNumber())) {
                  var10 = true;
               } else {
                  if (!var3.get(((ColumnReference)var9).getTableNumber())) {
                     return false;
                  }

                  var11 = true;
               }
            }

            if (var10 && var11) {
               var5 = true;
            }
         } else if (!(var4 instanceof BooleanConstantNode) || !var5) {
            return false;
         }

         if (var6 != null) {
            var4 = var6.getRightOperand();
         } else {
            var4 = null;
         }
      }

      return var5;
   }

   boolean LOJ_bindResultColumns(boolean var1) throws StandardException {
      if (var1) {
         this.setResultColumns((ResultColumnList)null);
         FromList var2 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         ((JoinNode)this).bindResultColumns(var2);
      }

      return var1;
   }

   FromTable transformOuterJoins(ValueNode var1, int var2) throws StandardException {
      if (var1 == null) {
         this.leftResultSet.notFlattenableJoin();
         this.rightResultSet.notFlattenableJoin();
         return this;
      } else {
         super.transformOuterJoins(var1, var2);
         ResultSetNode var3;
         if (this.rightOuterJoin) {
            var3 = this.leftResultSet;
         } else {
            var3 = this.rightResultSet;
         }

         JBitSet var4 = var3.LOJgetReferencedTables(var2);
         ValueNode var5 = var1;

         while(var5 instanceof AndNode) {
            AndNode var6 = (AndNode)var5;
            ValueNode var7 = var6.getLeftOperand();
            if (var7.getClass().equals(IsNullNode.class) && ((IsNullNode)var7).isNullNode()) {
               var5 = var6.getRightOperand();
            } else {
               if (var7 instanceof RelationalOperator) {
                  JBitSet var8 = new JBitSet(var2);
                  if (!var7.categorize(var8, true)) {
                     var5 = var6.getRightOperand();
                     continue;
                  }

                  for(int var9 = 0; var9 < var2; ++var9) {
                     if (var8.get(var9) && var4.get(var9)) {
                        JoinNode var10 = new JoinNode(this.leftResultSet, this.rightResultSet, this.joinClause, (ResultColumnList)null, this.getResultColumns(), (Properties)null, (Properties)null, this.getContextManager());
                        var10.setTableNumber(this.tableNumber);
                        var10.setSubqueryList(this.subqueryList);
                        var10.setAggregates(this.aggregates);
                        return var10;
                     }
                  }
               }

               var5 = var6.getRightOperand();
            }
         }

         this.leftResultSet.notFlattenableJoin();
         this.rightResultSet.notFlattenableJoin();
         return this;
      }
   }

   protected void adjustNumberOfRowsReturned(CostEstimate var1) {
      CostEstimate var2 = this.getLeftResultSet().getCostEstimate();
      if (var1.rowCount() < var2.rowCount()) {
         var1.setCost(var1.getEstimatedCost(), var2.rowCount(), var2.rowCount());
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      super.generateCore(var1, var2, 3);
   }

   int addOuterJoinArguments(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.rightResultSet.getResultColumns().generateNulls(var1, var2);
      var2.push(this.rightOuterJoin);
      return 2;
   }

   protected int getNumJoinArguments() {
      return super.getNumJoinArguments() + 2;
   }

   void oneRowRightSide(ActivationClassBuilder var1, MethodBuilder var2) {
      var2.push(false);
      var2.push(false);
   }

   ResultSetNode getLogicalLeftResultSet() {
      return this.rightOuterJoin ? this.rightResultSet : this.leftResultSet;
   }

   ResultSetNode getLogicalRightResultSet() {
      return this.rightOuterJoin ? this.leftResultSet : this.rightResultSet;
   }

   boolean isRightOuterJoin() {
      return this.rightOuterJoin;
   }

   void isJoinColumnForRightOuterJoin(ResultColumn var1) {
      if (this.isRightOuterJoin() && this.usingClause != null && this.usingClause.getResultColumn(var1.getUnderlyingOrAliasName()) != null) {
         var1.setRightOuterJoinUsingClause(true);
         var1.setJoinResultset(this);
      }

   }

   JBitSet LOJgetNPReferencedTables(int var1) throws StandardException {
      return this.rightOuterJoin && !this.transformed ? this.leftResultSet.LOJgetReferencedTables(var1) : this.rightResultSet.LOJgetReferencedTables(var1);
   }

   public JBitSet LOJgetRPReferencedTables(int var1) throws StandardException {
      return this.rightOuterJoin && !this.transformed ? this.rightResultSet.LOJgetReferencedTables(var1) : this.leftResultSet.LOJgetReferencedTables(var1);
   }
}
