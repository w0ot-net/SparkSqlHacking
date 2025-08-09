package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.shared.common.error.StandardException;

class JoinNode extends TableOperatorNode {
   static final int INNERJOIN = 1;
   static final int CROSSJOIN = 2;
   static final int LEFTOUTERJOIN = 3;
   static final int RIGHTOUTERJOIN = 4;
   static final int FULLOUTERJOIN = 5;
   static final int UNIONJOIN = 6;
   private boolean naturalJoin;
   private boolean optimized;
   private PredicateList leftPredicateList;
   private PredicateList rightPredicateList;
   protected boolean flattenableJoin = true;
   List aggregates;
   SubqueryList subqueryList;
   ValueNode joinClause;
   boolean joinClauseNormalized;
   PredicateList joinPredicates;
   ResultColumnList usingClause;
   Properties joinOrderStrategyProperties;

   JoinNode(ResultSetNode var1, ResultSetNode var2, ValueNode var3, ResultColumnList var4, ResultColumnList var5, Properties var6, Properties var7, ContextManager var8) throws StandardException {
      super(var1, var2, var6, var8);
      this.setResultColumns(var5);
      this.joinClause = var3;
      this.joinClauseNormalized = false;
      this.usingClause = var4;
      this.joinOrderStrategyProperties = var7;
      if (this.getResultColumns() != null && this.leftResultSet.getReferencedTableMap() != null) {
         this.setReferencedTableMap((JBitSet)this.leftResultSet.getReferencedTableMap().clone());
         this.getReferencedTableMap().or(this.rightResultSet.getReferencedTableMap());
      }

      this.joinPredicates = new PredicateList(var8);
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceOptimizingJoinNode();
      }

      this.updateBestPlanMap((short)1, this);
      this.leftResultSet = this.optimizeSource(var1, this.leftResultSet, this.getLeftPredicateList(), var3);

      for(int var5 = this.joinPredicates.size() - 1; var5 >= 0; --var5) {
         Predicate var6 = (Predicate)this.joinPredicates.elementAt(var5);
         if (((Predicate)this.joinPredicates.elementAt(var5)).getPushable()) {
            this.joinPredicates.removeElementAt(var5);
            this.getRightPredicateList().addElement(var6);
         }
      }

      this.rightResultSet = this.optimizeSource(var1, this.rightResultSet, this.getRightPredicateList(), this.leftResultSet.getCostEstimate());
      this.setCostEstimate(this.getCostEstimate(var1));
      this.getCostEstimate().setCost(this.leftResultSet.getCostEstimate().getEstimatedCost() + this.rightResultSet.getCostEstimate().getEstimatedCost(), this.rightResultSet.getCostEstimate().rowCount(), this.rightResultSet.getCostEstimate().rowCount());
      this.adjustNumberOfRowsReturned(this.getCostEstimate());
      this.getCurrentAccessPath().getJoinStrategy().estimateCost(this, var2, (ConglomerateDescriptor)null, var3, var1, this.getCostEstimate());
      var1.considerCost(this, var2, this.getCostEstimate(), var3);
      if (!this.optimized && this.subqueryList != null) {
         this.subqueryList.optimize(var1.getDataDictionary(), this.getCostEstimate().rowCount());
         this.subqueryList.modifyAccessPaths();
      }

      this.optimized = true;
      return this.getCostEstimate();
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      this.joinPredicates.addPredicate((Predicate)var1);
      RemapCRsVisitor var2 = new RemapCRsVisitor(true);
      ((Predicate)var1).getAndNode().accept(var2);
      return true;
   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      super.modifyAccessPath(var1);
      return this;
   }

   protected void adjustNumberOfRowsReturned(CostEstimate var1) {
   }

   ResultColumnList getAllResultColumns(TableName var1) throws StandardException {
      if (this.usingClause == null) {
         return this.getAllResultColumnsNoUsing(var1);
      } else {
         ResultSetNode var2 = this.getLogicalLeftResultSet();
         ResultColumnList var3 = var2.getAllResultColumns((TableName)null).getJoinColumns(this.usingClause);
         ResultColumnList var4 = this.leftResultSet.getAllResultColumns(var1);
         ResultColumnList var5 = this.rightResultSet.getAllResultColumns(var1);
         if (var4 != null) {
            var4.removeJoinColumns(this.usingClause);
         }

         if (var5 != null) {
            var5.removeJoinColumns(this.usingClause);
         }

         if (var4 == null) {
            if (var5 == null) {
               return null;
            } else {
               var5.resetVirtualColumnIds();
               return var5;
            }
         } else if (var5 == null) {
            var4.resetVirtualColumnIds();
            return var4;
         } else {
            var3.destructiveAppend(var4);
            var3.destructiveAppend(var5);
            var3.resetVirtualColumnIds();
            return var3;
         }
      }
   }

   private ResultColumnList getAllResultColumnsNoUsing(TableName var1) throws StandardException {
      ResultColumnList var2 = this.leftResultSet.getAllResultColumns(var1);
      ResultColumnList var3 = this.rightResultSet.getAllResultColumns(var1);
      if (var2 == null) {
         return var3;
      } else if (var3 == null) {
         return var2;
      } else {
         ResultColumnList var4 = new ResultColumnList(this.getContextManager());
         var4.nondestructiveAppend(var2);
         var4.nondestructiveAppend(var3);
         return var4;
      }
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      ResultSetNode var2 = this.getLogicalLeftResultSet();
      ResultSetNode var3 = this.getLogicalRightResultSet();
      ResultColumn var4 = null;
      ResultColumn var5 = null;
      ResultColumn var6 = null;
      ResultColumn var7 = var2.getMatchingColumn(var1);
      if (var7 != null) {
         var4 = var7;
         if (this.usingClause != null) {
            var6 = this.usingClause.getResultColumn(var7.getName());
         }
      }

      if (var6 == null) {
         var5 = var3.getMatchingColumn(var1);
      } else if (this instanceof HalfOuterJoinNode && ((HalfOuterJoinNode)this).isRightOuterJoin()) {
         var7.setRightOuterJoinUsingClause(true);
      }

      if (var5 != null) {
         if (var7 != null) {
            throw StandardException.newException("42X03", new Object[]{var1.getSQLColumnName()});
         }

         if (this instanceof HalfOuterJoinNode) {
            var5.setNullability(true);
         }

         var4 = var5;
      }

      if (this.getResultColumns() != null) {
         for(ResultColumn var9 : this.getResultColumns()) {
            VirtualColumnNode var10 = (VirtualColumnNode)var9.getExpression();
            if (var4 == var10.getSourceColumn()) {
               var4 = var9;
               break;
            }
         }
      }

      return var4;
   }

   public void bindExpressions(FromList var1) throws StandardException {
      super.bindExpressions(var1);
      if (this.naturalJoin) {
         this.usingClause = this.getCommonColumnsForNaturalJoin();
      }

   }

   void bindResultColumns(FromList var1) throws StandardException {
      super.bindResultColumns(var1);
      this.buildRCL();
      this.deferredBindExpressions(var1);
   }

   void bindResultColumns(TableDescriptor var1, FromVTI var2, ResultColumnList var3, DMLStatementNode var4, FromList var5) throws StandardException {
      super.bindResultColumns(var1, var2, var3, var4, var5);
      this.buildRCL();
      this.deferredBindExpressions(var5);
   }

   private void buildRCL() throws StandardException {
      if (this.getResultColumns() == null) {
         this.setResultColumns(this.leftResultSet.getResultColumns());
         ResultColumnList var1 = this.getResultColumns().copyListAndObjects();
         this.leftResultSet.setResultColumns(var1);
         this.getResultColumns().genVirtualColumnNodes(this.leftResultSet, var1, false);
         if (this instanceof HalfOuterJoinNode && ((HalfOuterJoinNode)this).isRightOuterJoin()) {
            this.getResultColumns().setNullability(true);
         }

         ResultColumnList var3 = this.rightResultSet.getResultColumns();
         ResultColumnList var2 = var3.copyListAndObjects();
         this.rightResultSet.setResultColumns(var2);
         var3.genVirtualColumnNodes(this.rightResultSet, var2, false);
         var3.adjustVirtualColumnIds(this.getResultColumns().size());
         if (this instanceof HalfOuterJoinNode && !((HalfOuterJoinNode)this).isRightOuterJoin()) {
            var3.setNullability(true);
         }

         this.getResultColumns().nondestructiveAppend(var3);
      }
   }

   private void deferredBindExpressions(FromList var1) throws StandardException {
      ContextManager var2 = this.getContextManager();
      CompilerContext var3 = this.getCompilerContext();
      this.subqueryList = new SubqueryList(var2);
      this.aggregates = new ArrayList();
      if (this.joinClause != null) {
         this.joinClause = this.bindExpression(this.joinClause, true, true, "ON");
      } else if (this.usingClause != null) {
         this.joinClause = new BooleanConstantNode(true, var2);

         for(ResultColumn var5 : this.usingClause) {
            var1.insertElementAt(this.leftResultSet, 0);
            ColumnReference var7 = new ColumnReference(var5.getName(), ((FromTable)this.leftResultSet).getTableName(), var2);
            var7 = var7.bindExpression(var1, this.subqueryList, this.aggregates);
            var1.removeElementAt(0);
            var1.insertElementAt(this.rightResultSet, 0);
            ColumnReference var8 = new ColumnReference(var5.getName(), ((FromTable)this.rightResultSet).getTableName(), var2);
            var8 = var8.bindExpression(var1, this.subqueryList, this.aggregates);
            var1.removeElementAt(0);
            BinaryRelationalOperatorNode var6 = new BinaryRelationalOperatorNode(0, var7, var8, false, var2);
            ((BinaryComparisonOperatorNode)var6).bindComparisonOperator();
            AndNode var9 = new AndNode(var6, this.joinClause, var2);
            var9.postBindFixup();
            this.joinClause = var9;
         }
      }

      if (this.joinClause != null) {
         if (this.joinClause.requiresTypeFromContext()) {
            this.joinClause.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, true));
         }

         TypeId var10 = this.joinClause.getTypeId();
         if (var10.userType()) {
            this.joinClause = this.joinClause.genSQLJavaSQLTree();
         }

         if (!this.joinClause.getTypeServices().getTypeId().equals(TypeId.BOOLEAN_ID)) {
            throw StandardException.newException("42Y12", new Object[]{this.joinClause.getTypeServices().getTypeId().getSQLTypeName()});
         }
      }

   }

   public ValueNode bindExpression(ValueNode var1, boolean var2, boolean var3, String var4) throws StandardException {
      ContextManager var5 = this.getContextManager();
      CompilerContext var6 = this.getCompilerContext();
      FromList var7 = this.makeFromList(var2, var3);
      int var8 = this.orReliability(16384);
      var1 = var1.bindExpression(var7, this.subqueryList, this.aggregates);
      var6.setReliability(var8);
      SelectNode.checkNoWindowFunctions(var1, var4);
      if (!this.aggregates.isEmpty()) {
         throw StandardException.newException("42Z07", new Object[0]);
      } else {
         return var1;
      }
   }

   public FromList makeFromList(boolean var1, boolean var2) throws StandardException {
      FromList var3 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      if (var1) {
         var3.addElement((FromTable)this.leftResultSet);
      }

      if (var2) {
         var3.addElement((FromTable)this.rightResultSet);
      }

      return var3;
   }

   private ResultColumnList getCommonColumnsForNaturalJoin() throws StandardException {
      ResultColumnList var1 = this.getLeftResultSet().getAllResultColumns((TableName)null);
      ResultColumnList var2 = this.getRightResultSet().getAllResultColumns((TableName)null);
      List var3 = extractColumnNames(var1);
      var3.retainAll(extractColumnNames(var2));
      ResultColumnList var4 = new ResultColumnList(this.getContextManager());

      for(String var6 : var3) {
         ResultColumn var7 = new ResultColumn(var6, (ValueNode)null, this.getContextManager());
         var4.addResultColumn(var7);
      }

      return var4;
   }

   private static List extractColumnNames(ResultColumnList var0) {
      ArrayList var1 = new ArrayList();

      for(ResultColumn var3 : var0) {
         var1.add(var3.getName());
      }

      return var1;
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      ResultSetNode var4 = super.preprocess(var1, var2, var3);
      if (this.joinClause != null) {
         this.normExpressions();
         if (this.subqueryList != null) {
            this.joinClause = this.joinClause.preprocess(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()), new SubqueryList(this.getContextManager()), new PredicateList(this.getContextManager()));
         }

         this.joinPredicates.pullExpressions(var1, this.joinClause);
         this.joinPredicates.categorize();
         this.joinClause = null;
      }

      return var4;
   }

   void projectResultColumns() throws StandardException {
      this.leftResultSet.projectResultColumns();
      this.rightResultSet.projectResultColumns();
      this.getResultColumns().pullVirtualIsReferenced();
      super.projectResultColumns();
   }

   void normExpressions() throws StandardException {
      if (!this.joinClauseNormalized) {
         this.joinClause = this.joinClause.eliminateNots(false);
         this.joinClause = this.joinClause.putAndsOnTop();
         this.joinClause = this.joinClause.changeToCNF(false);
         this.joinClauseNormalized = true;
      }
   }

   void pushExpressions(PredicateList var1) throws StandardException {
      FromTable var2 = (FromTable)this.leftResultSet;
      FromTable var3 = (FromTable)this.rightResultSet;
      this.pushExpressionsToLeft(var1);
      var2.pushExpressions(this.getLeftPredicateList());
      this.pushExpressionsToRight(var1);
      var3.pushExpressions(this.getRightPredicateList());
      this.grabJoinPredicates(var1);
   }

   protected void pushExpressionsToLeft(PredicateList var1) throws StandardException {
      FromTable var2 = (FromTable)this.leftResultSet;
      JBitSet var3 = var2.getReferencedTableMap();

      for(int var4 = var1.size() - 1; var4 >= 0; --var4) {
         Predicate var5 = (Predicate)var1.elementAt(var4);
         if (var5.getPushable()) {
            JBitSet var6 = var5.getReferencedSet();
            if (var3.contains(var6)) {
               this.getLeftPredicateList().addPredicate(var5);
               RemapCRsVisitor var7 = new RemapCRsVisitor(true);
               var5.getAndNode().accept(var7);
               var5.getAndNode().accept(var7);
               var1.removeElementAt(var4);
            }
         }
      }

   }

   private void pushExpressionsToRight(PredicateList var1) throws StandardException {
      FromTable var2 = (FromTable)this.rightResultSet;
      JBitSet var3 = var2.getReferencedTableMap();

      for(int var4 = var1.size() - 1; var4 >= 0; --var4) {
         Predicate var5 = (Predicate)var1.elementAt(var4);
         if (var5.getPushable()) {
            JBitSet var6 = var5.getReferencedSet();
            if (var3.contains(var6)) {
               this.getRightPredicateList().addPredicate(var5);
               RemapCRsVisitor var7 = new RemapCRsVisitor(true);
               var5.getAndNode().accept(var7);
               var5.getAndNode().accept(var7);
               var1.removeElementAt(var4);
            }
         }
      }

   }

   private void grabJoinPredicates(PredicateList var1) throws StandardException {
      FromTable var2 = (FromTable)this.leftResultSet;
      FromTable var3 = (FromTable)this.rightResultSet;
      JBitSet var4 = var2.getReferencedTableMap();
      JBitSet var5 = var3.getReferencedTableMap();

      for(int var6 = var1.size() - 1; var6 >= 0; --var6) {
         Predicate var7 = (Predicate)var1.elementAt(var6);
         if (var7.getPushable()) {
            JBitSet var8 = var7.getReferencedSet();
            JBitSet var9 = (JBitSet)var5.clone();
            var9.or(var4);
            if (var9.contains(var8)) {
               this.joinPredicates.addPredicate(var7);
               RemapCRsVisitor var10 = new RemapCRsVisitor(true);
               var7.getAndNode().accept(var10);
               var7.getAndNode().accept(var10);
               var1.removeElementAt(var6);
            }
         }
      }

   }

   FromList flatten(ResultColumnList var1, PredicateList var2, SubqueryList var3, GroupByList var4, ValueNode var5) throws StandardException {
      FromList var6 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      var6.addElement((FromTable)this.leftResultSet);
      var6.addElement((FromTable)this.rightResultSet);
      this.getResultColumns().setRedundant();
      var1.remapColumnReferencesToExpressions();
      var2.remapColumnReferencesToExpressions();
      if (var4 != null) {
         var4.remapColumnReferencesToExpressions();
      }

      if (var5 != null) {
         var5.remapColumnReferencesToExpressions();
      }

      if (this.joinPredicates.size() > 0) {
         var2.destructiveAppend(this.joinPredicates);
      }

      if (this.subqueryList != null && this.subqueryList.size() > 0) {
         var3.destructiveAppend(this.subqueryList);
      }

      return var6;
   }

   boolean LOJ_reorderable(int var1) throws StandardException {
      return false;
   }

   FromTable transformOuterJoins(ValueNode var1, int var2) throws StandardException {
      if (var1 == null) {
         ((FromTable)this.leftResultSet).transformOuterJoins((ValueNode)null, var2);
         ((FromTable)this.rightResultSet).transformOuterJoins((ValueNode)null, var2);
         return this;
      } else {
         this.leftResultSet = ((FromTable)this.leftResultSet).transformOuterJoins(var1, var2);
         this.rightResultSet = ((FromTable)this.rightResultSet).transformOuterJoins(var1, var2);
         return this;
      }
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCore(var1, var2, 1, (ValueNode)null, (SubqueryList)null);
   }

   void generateCore(ActivationClassBuilder var1, MethodBuilder var2, int var3) throws StandardException {
      this.generateCore(var1, var2, var3, this.joinClause, this.subqueryList);
   }

   void generateCore(ActivationClassBuilder var1, MethodBuilder var2, int var3, ValueNode var4, SubqueryList var5) throws StandardException {
      if (this.joinPredicates != null) {
         var4 = this.joinPredicates.restorePredicates();
         this.joinPredicates = null;
      }

      this.assignResultSetNumber();
      if (var5 != null && var5.size() > 0) {
         var5.setPointOfAttachment(this.getResultSetNumber());
      }

      String var6;
      if (var3 == 3) {
         var6 = ((Optimizable)this.rightResultSet).getTrulyTheBestAccessPath().getJoinStrategy().halfOuterJoinResultSetMethodName();
      } else {
         var6 = ((Optimizable)this.rightResultSet).getTrulyTheBestAccessPath().getJoinStrategy().joinResultSetMethodName();
      }

      var1.pushGetResultSetFactoryExpression(var2);
      int var7 = this.getJoinArguments(var1, var2, var4);
      var2.callMethod((short)185, (String)null, var6, "org.apache.derby.iapi.sql.execute.NoPutResultSet", var7);
   }

   private int getJoinArguments(ActivationClassBuilder var1, MethodBuilder var2, ValueNode var3) throws StandardException {
      int var4 = this.getNumJoinArguments();
      this.leftResultSet.generate(var1, var2);
      var2.push(this.leftResultSet.getResultColumns().size());
      this.rightResultSet.generate(var1, var2);
      var2.push(this.rightResultSet.getResultColumns().size());
      this.setCostEstimate(this.getFinalCostEstimate());
      if (var3 == null) {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      } else {
         MethodBuilder var5 = var1.newUserExprFun();
         var3.generate(var1, var5);
         var5.methodReturn();
         var5.complete();
         var1.pushMethodReference(var2, var5);
      }

      var2.push(this.getResultSetNumber());
      this.addOuterJoinArguments(var1, var2);
      this.oneRowRightSide(var1, var2);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      if (this.joinOrderStrategyProperties != null) {
         var2.push(PropertyUtil.sortProperties(this.joinOrderStrategyProperties));
      } else {
         var2.pushNull("java.lang.String");
      }

      return var4;
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      if (this.getCandidateFinalCostEstimate() != null) {
         return this.getCandidateFinalCostEstimate();
      } else {
         CostEstimate var1 = this.leftResultSet.getFinalCostEstimate();
         CostEstimate var2 = this.rightResultSet.getFinalCostEstimate();
         this.setCandidateFinalCostEstimate(this.getNewCostEstimate());
         this.getCandidateFinalCostEstimate().setCost(var1.getEstimatedCost() + var2.getEstimatedCost(), var2.rowCount(), var2.rowCount());
         return this.getCandidateFinalCostEstimate();
      }
   }

   void oneRowRightSide(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.push(this.rightResultSet.isOneRowResultSet());
      var2.push(this.rightResultSet.isNotExists());
   }

   protected int getNumJoinArguments() {
      return 11;
   }

   int addOuterJoinArguments(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      return 0;
   }

   static String joinTypeToString(int var0) {
      switch (var0) {
         case 1 -> {
            return "INNER JOIN";
         }
         case 2 -> {
            return "CROSS JOIN";
         }
         case 3 -> {
            return "LEFT OUTER JOIN";
         }
         case 4 -> {
            return "RIGHT OUTER JOIN";
         }
         case 5 -> {
            return "FULL OUTER JOIN";
         }
         case 6 -> {
            return "UNION JOIN";
         }
         default -> {
            return null;
         }
      }
   }

   protected PredicateList getLeftPredicateList() throws StandardException {
      if (this.leftPredicateList == null) {
         this.leftPredicateList = new PredicateList(this.getContextManager());
      }

      return this.leftPredicateList;
   }

   protected PredicateList getRightPredicateList() throws StandardException {
      if (this.rightPredicateList == null) {
         this.rightPredicateList = new PredicateList(this.getContextManager());
      }

      return this.rightPredicateList;
   }

   int updateTargetLockMode() {
      return 6;
   }

   void notFlattenableJoin() {
      this.flattenableJoin = false;
      this.leftResultSet.notFlattenableJoin();
      this.rightResultSet.notFlattenableJoin();
   }

   boolean isFlattenableJoinNode() {
      return this.flattenableJoin;
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) throws StandardException {
      return this.leftResultSet.isOrderedOn(var1, var2, var3);
   }

   void printSubNodes(int var1) {
   }

   void setSubqueryList(SubqueryList var1) {
      this.subqueryList = var1;
   }

   void setAggregates(List var1) {
      this.aggregates = var1;
   }

   void setNaturalJoin() {
      this.naturalJoin = true;
   }

   ResultSetNode getLogicalLeftResultSet() {
      return this.leftResultSet;
   }

   ResultSetNode getLogicalRightResultSet() {
      return this.rightResultSet;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.getResultColumns() != null) {
         this.setResultColumns((ResultColumnList)this.getResultColumns().accept(var1));
      }

      if (this.joinClause != null) {
         this.joinClause = (ValueNode)this.joinClause.accept(var1);
      }

      if (this.usingClause != null) {
         this.usingClause = (ResultColumnList)this.usingClause.accept(var1);
      }

      if (this.joinPredicates != null) {
         this.joinPredicates = (PredicateList)this.joinPredicates.accept(var1);
      }

   }

   JBitSet LOJgetReferencedTables(int var1) throws StandardException {
      JBitSet var2 = this.leftResultSet.LOJgetReferencedTables(var1);
      if (var2 == null) {
         return null;
      } else {
         var2.or(this.rightResultSet.LOJgetReferencedTables(var1));
         return var2;
      }
   }
}
