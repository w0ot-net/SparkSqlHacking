package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ResultSetNode extends QueryTreeNode {
   private int resultSetNumber;
   private JBitSet referencedTableMap;
   private ResultColumnList resultColumns;
   private boolean statementResultSet;
   private boolean cursorTargetTable;
   private boolean insertSource;
   private CostEstimate costEstimate;
   private CostEstimate scratchCostEstimate;
   private Optimizer optimizer;
   private CostEstimate candidateFinalCostEstimate;

   ResultSetNode(ContextManager var1) {
      super(var1);
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   boolean isStatementResultSet() {
      return this.statementResultSet;
   }

   boolean isCursorTargetTable() {
      return this.cursorTargetTable;
   }

   void setCursorTargetTable(boolean var1) {
      this.cursorTargetTable = var1;
   }

   CostEstimate getScratchCostEstimate() {
      return this.scratchCostEstimate;
   }

   void setScratchCostEstimate(CostEstimate var1) {
      this.scratchCostEstimate = var1;
   }

   public int getResultSetNumber() {
      return this.resultSetNumber;
   }

   void setResultSetNumber(int var1) {
      this.resultSetNumber = var1;
   }

   CostEstimate getCostEstimate() {
      return this.costEstimate;
   }

   void setCostEstimate(CostEstimate var1) {
      this.costEstimate = var1;
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      return this.candidateFinalCostEstimate;
   }

   CostEstimate getCandidateFinalCostEstimate() {
      return this.candidateFinalCostEstimate;
   }

   void setCandidateFinalCostEstimate(CostEstimate var1) {
      this.candidateFinalCostEstimate = var1;
   }

   void assignResultSetNumber() throws StandardException {
      this.resultSetNumber = this.getCompilerContext().getNextResultSetNumber();
      this.resultColumns.setResultSetNumber(this.resultSetNumber);
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      return this;
   }

   ResultSetNode bindVTITables(FromList var1) throws StandardException {
      return this;
   }

   void bindExpressions(FromList var1) throws StandardException {
   }

   void bindExpressionsWithTables(FromList var1) throws StandardException {
   }

   void bindTargetExpressions(FromList var1) throws StandardException {
   }

   void setTableConstructorTypes(ResultColumnList var1) throws StandardException {
      for(int var2 = 0; var2 < this.resultColumns.size(); ++var2) {
         ResultColumn var3 = (ResultColumn)this.resultColumns.elementAt(var2);
         ValueNode var4 = var3.getExpression();
         if (var4 != null && var4.requiresTypeFromContext()) {
            ResultColumn var5 = (ResultColumn)var1.elementAt(var2);
            var4.setType(var5.getTypeServices());
         }
      }

   }

   void setInsertSource() {
      this.insertSource = true;
   }

   boolean isInsertSource() {
      return this.insertSource;
   }

   void verifySelectStarSubquery(FromList var1, int var2) throws StandardException {
   }

   ResultColumnList getAllResultColumns(TableName var1) throws StandardException {
      return null;
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      return null;
   }

   ResultSetNode setResultToBooleanTrueNode(boolean var1) throws StandardException {
      ResultColumn var2;
      if (this.resultColumns.elementAt(0) instanceof AllResultColumn) {
         var2 = new ResultColumn("", (ValueNode)null, this.getContextManager());
      } else {
         if (var1) {
            return this;
         }

         var2 = (ResultColumn)this.resultColumns.elementAt(0);
         if (var2.getExpression().isBooleanTrue() && this.resultColumns.size() == 1) {
            return this;
         }
      }

      BooleanConstantNode var3 = new BooleanConstantNode(true, this.getContextManager());
      var2.setExpression(var3);
      var2.setType(var3.getTypeServices());
      var2.setVirtualColumnId(1);
      this.resultColumns.setElementAt(var2, 0);
      return this;
   }

   FromList getFromList() throws StandardException {
      return new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
   }

   void bindResultColumns(FromList var1) throws StandardException {
      this.resultColumns.bindResultColumnsToExpressions();
   }

   void bindResultColumns(TableDescriptor var1, FromVTI var2, ResultColumnList var3, DMLStatementNode var4, FromList var5) throws StandardException {
      if (this instanceof SelectNode) {
         this.resultColumns.expandAllsAndNameColumns(((SelectNode)this).fromList);
      }

      if (var3 != null) {
         this.resultColumns.copyResultColumnNames(var3);
      }

      if (var3 != null) {
         if (var1 != null) {
            this.resultColumns.bindResultColumnsByName(var1, var4);
         } else {
            this.resultColumns.bindResultColumnsByName(var2.getResultColumns(), var2, var4);
         }
      } else {
         this.resultColumns.bindResultColumnsByPosition(var1);
      }

   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      return null;
   }

   void projectResultColumns() throws StandardException {
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return null;
   }

   ResultSetNode addNewPredicate(Predicate var1) throws StandardException {
      return null;
   }

   boolean flattenableInFromSubquery(FromList var1) {
      return false;
   }

   ResultSetNode genProjectRestrictForReordering() throws StandardException {
      ResultColumnList var1 = this.resultColumns;
      this.resultColumns = this.resultColumns.copyListAndObjects();
      var1.genVirtualColumnNodes(this, this.resultColumns, false);
      return new ProjectRestrictNode(this, var1, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      return null;
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      return this;
   }

   ResultSetNode modifyAccessPaths(PredicateList var1) throws StandardException {
      return this.modifyAccessPaths();
   }

   ResultColumnDescriptor[] makeResultDescriptors() {
      return this.resultColumns.makeResultDescriptors();
   }

   boolean columnTypesAndLengthsMatch() throws StandardException {
      return this.resultColumns.columnTypesAndLengthsMatch();
   }

   void setResultColumns(ResultColumnList var1) {
      this.resultColumns = var1;
   }

   ResultColumnList getResultColumns() {
      return this.resultColumns;
   }

   void setReferencedTableMap(JBitSet var1) {
      this.referencedTableMap = var1;
   }

   public JBitSet getReferencedTableMap() {
      return this.referencedTableMap;
   }

   void fillInReferencedTableMap(JBitSet var1) {
   }

   void rejectParameters() throws StandardException {
      if (this.resultColumns != null) {
         this.resultColumns.rejectParameters();
      }

   }

   void rejectXMLValues() throws StandardException {
      if (this.resultColumns != null) {
         this.resultColumns.rejectXMLValues();
      }

   }

   void renameGeneratedResultNames() throws StandardException {
      for(int var1 = 0; var1 < this.resultColumns.size(); ++var1) {
         ResultColumn var2 = (ResultColumn)this.resultColumns.elementAt(var1);
         if (var2.isNameGenerated()) {
            var2.setName(Integer.toString(var1 + 1));
         }
      }

   }

   void markStatementResultSet() {
      this.statementResultSet = true;
   }

   ResultSetNode enhanceRCLForInsert(InsertNode var1, boolean var2, int[] var3) throws StandardException {
      return var2 && this.resultColumns.visibleSize() >= var1.resultColumnList.size() ? this : this.generateProjectRestrictForInsert(var1, var3);
   }

   ResultColumnList getRCLForInsert(InsertNode var1, int[] var2) throws StandardException {
      ResultColumnList var3 = new ResultColumnList(this.getContextManager());
      int var4 = var1.resultColumnList.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6;
         if (var2[var5] != -1) {
            var6 = this.resultColumns.getResultColumn(var2[var5] + 1);
         } else {
            var6 = this.genNewRCForInsert(var1.targetTableDescriptor, var1.targetVTI, var5 + 1, var1.getDataDictionary());
         }

         var3.addResultColumn(var6);
      }

      return var3;
   }

   private ResultColumn genNewRCForInsert(TableDescriptor var1, FromVTI var2, int var3, DataDictionary var4) throws StandardException {
      ResultColumn var11;
      if (var2 != null) {
         ResultColumn var5 = var2.getResultColumns().getResultColumn(var3);
         var11 = var5.cloneMe();
         var11.setExpressionToNullNode();
      } else {
         ColumnDescriptor var6 = var1.getColumnDescriptor(var3);
         DataTypeDescriptor var7 = var6.getType();
         DefaultInfoImpl var8 = (DefaultInfoImpl)var6.getDefaultInfo();
         if (var8 != null && !var6.isAutoincrement()) {
            if (var6.hasGenerationClause()) {
               var11 = this.createGeneratedColumn(var1, var6);
            } else {
               String var9 = var8.getDefaultText();
               ValueNode var10 = this.parseDefault(var9);
               var10 = var10.bindExpression(this.getFromList(), (SubqueryList)null, (List)null);
               var11 = new ResultColumn(var10.getTypeServices(), var10, this.getContextManager());
            }

            DefaultDescriptor var12 = var6.getDefaultDescriptor(var4);
            this.getCompilerContext().createDependency(var12);
         } else if (var6.isAutoincrement()) {
            var11 = new ResultColumn(var6, (ValueNode)null, this.getContextManager());
            var11.setAutoincrementGenerated();
         } else {
            var11 = new ResultColumn(var7, this.getNullNode(var7), this.getContextManager());
         }
      }

      var11.markGeneratedForUnmatchedColumnInInsert();
      return var11;
   }

   private ResultSetNode generateProjectRestrictForInsert(InsertNode var1, int[] var2) throws StandardException {
      ResultColumnList var3 = new ResultColumnList(this.getContextManager());
      int var4 = var1.resultColumnList.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6;
         if (var2[var5] != -1) {
            ResultColumn var7 = this.resultColumns.getResultColumn(var2[var5] + 1);
            ColumnReference var8 = new ColumnReference(var7.getName(), (TableName)null, this.getContextManager());
            DataTypeDescriptor var9 = var7.getType();
            if (var9 == null) {
               ColumnDescriptor var10 = var1.targetTableDescriptor.getColumnDescriptor(var5 + 1);
               var9 = var10.getType();
            }

            var8.setSource(var7);
            var8.setType(var9);
            var8.setNestingLevel(0);
            var8.setSourceLevel(0);
            var6 = new ResultColumn(var9, var8, this.getContextManager());
         } else {
            var6 = this.genNewRCForInsert(var1.targetTableDescriptor, var1.targetVTI, var5 + 1, var1.getDataDictionary());
         }

         var3.addResultColumn(var6);
      }

      return new ProjectRestrictNode(this, var3, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
   }

   private ResultColumn createGeneratedColumn(TableDescriptor var1, ColumnDescriptor var2) throws StandardException {
      UntypedNullConstantNode var3 = new UntypedNullConstantNode(this.getContextManager());
      ResultColumn var4 = new ResultColumn(var2.getType(), var3, this.getContextManager());
      var4.setColumnDescriptor(var1, var2);
      return var4;
   }

   public ValueNode parseDefault(String var1) throws StandardException {
      LanguageConnectionContext var4 = this.getLanguageConnectionContext();
      String var5 = "VALUES " + var1;
      CompilerContext var6 = var4.pushCompilerContext();
      Parser var2 = var6.getParser();
      Visitable var7 = var2.parseStatement(var5);
      ValueNode var3 = ((ResultColumn)((CursorNode)var7).getResultSetNode().getResultColumns().elementAt(0)).getExpression();
      var4.popCompilerContext(var6);
      return var3;
   }

   public ResultDescription makeResultDescription() {
      ResultColumnDescriptor[] var1 = this.makeResultDescriptors();
      return this.getExecutionFactory().getResultDescription(var1, (String)null);
   }

   boolean isUpdatableCursor(DataDictionary var1) throws StandardException {
      return false;
   }

   FromTable getCursorTargetTable() {
      return null;
   }

   boolean markAsCursorTargetTable() {
      return false;
   }

   void notCursorTargetTable() {
      this.cursorTargetTable = false;
   }

   ResultSetNode genProjectRestrict() throws StandardException {
      ResultColumnList var1 = this.resultColumns;
      this.resultColumns = this.resultColumns.copyListAndObjects();
      var1.genVirtualColumnNodes(this, this.resultColumns);
      return new ProjectRestrictNode(this, var1, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
   }

   ResultSetNode genProjectRestrict(int var1) throws StandardException {
      return this.genProjectRestrict();
   }

   void generateNormalizationResultSet(ActivationClassBuilder var1, MethodBuilder var2, int var3, ResultDescription var4) throws StandardException {
      int var5 = var1.addItem(var4);
      var2.push(var3);
      var2.push(var5);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(false);
      var2.callMethod((short)185, (String)null, "getNormalizeResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 6);
   }

   ResultSetNode changeAccessPath() throws StandardException {
      return this;
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return false;
   }

   boolean subqueryReferencesTarget(String var1, boolean var2) throws StandardException {
      return false;
   }

   boolean isOneRowResultSet() throws StandardException {
      return false;
   }

   boolean isNotExists() {
      return false;
   }

   protected OptimizerImpl getOptimizerImpl() {
      return (OptimizerImpl)this.optimizer;
   }

   Optimizer getOptimizer() {
      return this.optimizer;
   }

   void setOptimizer(Optimizer var1) {
      this.optimizer = var1;
   }

   protected CostEstimate getNewCostEstimate() throws StandardException {
      OptimizerFactory var1 = this.getLanguageConnectionContext().getOptimizerFactory();
      return var1.getCostEstimate();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.resultColumns != null) {
         this.resultColumns = (ResultColumnList)this.resultColumns.accept(var1);
      }

   }

   ResultSetNode considerMaterialization(JBitSet var1) throws StandardException {
      return this;
   }

   boolean performMaterialization(JBitSet var1) throws StandardException {
      return false;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      return null;
   }

   abstract void decrementLevel(int var1);

   void pushOrderByList(OrderByList var1) {
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
   }

   void generateResultSet(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
   }

   int updateTargetLockMode() {
      return 7;
   }

   void notFlattenableJoin() {
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) throws StandardException {
      return false;
   }

   boolean returnsAtMostOneRow() {
      return false;
   }

   void replaceOrForbidDefaults(TableDescriptor var1, ResultColumnList var2, boolean var3) throws StandardException {
   }

   boolean isPossibleDistinctScan(Set var1) {
      return false;
   }

   void markForDistinctScan() {
   }

   void adjustForSortElimination() {
   }

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      this.adjustForSortElimination();
   }

   static int numDistinctAggregates(List var0) {
      int var1 = 0;
      int var2 = var0.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         if (((AggregateNode)var0.get(var3)).isDistinct()) {
            ++var1;
         }
      }

      return var1;
   }

   JBitSet LOJgetReferencedTables(int var1) throws StandardException {
      if (this instanceof FromTable && ((FromTable)this).tableNumber != -1) {
         JBitSet var2 = new JBitSet(var1);
         var2.set(((FromTable)this).tableNumber);
         return var2;
      } else {
         return null;
      }
   }

   void pushQueryExpressionSuffix() {
   }

   void printQueryExpressionSuffixClauses(int var1, QueryExpressionClauses var2) {
      for(int var3 = 0; var3 < var2.size(); ++var3) {
         OrderByList var4 = var2.getOrderByList(var3);
         if (var4 != null) {
            this.printLabel(var1, "orderByLists[" + var3 + "]:");
            var4.treePrint(var1 + 1);
         }

         ValueNode var5 = var2.getOffset(var3);
         if (var5 != null) {
            this.printLabel(var1, "offset:");
            var5.treePrint(var1 + 1);
         }

         ValueNode var6 = var2.getFetchFirst(var3);
         if (var6 != null) {
            this.printLabel(var1, "fetch first/next:");
            var6.treePrint(var1 + 1);
         }

         Boolean var7 = var2.getHasJDBCLimitClause()[var3];
         if (var7 != null) {
            this.printLabel(var1, "hasJDBCLimitClause:" + var7 + "\n");
         }
      }

   }

   static class QueryExpressionClauses {
      private final List obl = new ArrayList();
      private final List offset = new ArrayList();
      private final List fetchFirst = new ArrayList();
      private final List hasJDBCLimitClause = new ArrayList();

      public QueryExpressionClauses() {
         this.push();
      }

      int size() {
         return this.obl.size();
      }

      void push() {
         int var1 = this.size();
         if (var1 <= 0 || this.obl.get(var1 - 1) != null || this.offset.get(var1 - 1) != null || this.fetchFirst.get(var1 - 1) != null) {
            this.obl.add((Object)null);
            this.offset.add((Object)null);
            this.fetchFirst.add((Object)null);
            this.hasJDBCLimitClause.add((Object)null);
         }

      }

      void setOrderByList(OrderByList var1) {
         this.obl.set(this.size() - 1, var1);
      }

      void setOffset(ValueNode var1) {
         this.offset.set(this.size() - 1, var1);
      }

      void setFetchFirst(ValueNode var1) {
         this.fetchFirst.set(this.size() - 1, var1);
      }

      void setHasJDBCLimitClause(Boolean var1) {
         this.hasJDBCLimitClause.set(this.size() - 1, var1);
      }

      OrderByList getOrderByList(int var1) {
         return (OrderByList)this.obl.get(var1);
      }

      void setOrderByList(int var1, OrderByList var2) {
         this.obl.set(var1, var2);
      }

      ValueNode getOffset(int var1) {
         return (ValueNode)this.offset.get(var1);
      }

      void setOffset(int var1, ValueNode var2) {
         this.offset.set(var1, var2);
      }

      ValueNode getFetchFirst(int var1) {
         return (ValueNode)this.fetchFirst.get(var1);
      }

      void setFetchFirst(int var1, ValueNode var2) {
         this.fetchFirst.set(var1, var2);
      }

      Boolean[] getHasJDBCLimitClause() {
         return (Boolean[])this.hasJDBCLimitClause.toArray(new Boolean[1]);
      }

      boolean hasOffsetFetchFirst() {
         for(ValueNode var2 : this.offset) {
            if (var2 != null) {
               return true;
            }
         }

         for(ValueNode var4 : this.fetchFirst) {
            if (var4 != null) {
               return true;
            }
         }

         return false;
      }
   }
}
