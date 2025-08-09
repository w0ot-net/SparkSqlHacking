package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.IgnoreFilter;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public final class MergeNode extends DMLModStatementNode {
   public static final int SOURCE_TABLE_INDEX = 0;
   public static final int TARGET_TABLE_INDEX = 1;
   private static final String TARGET_ROW_LOCATION_NAME = "###TargetRowLocation";
   private FromBaseTable _targetTable;
   private FromTable _sourceTable;
   private ValueNode _searchCondition;
   private QueryTreeNodeVector _matchingClauses;
   private ResultColumnList _selectList;
   private FromList _leftJoinFromList;
   private HalfOuterJoinNode _hojn;
   private ConstantAction _constantAction;
   private CursorNode _leftJoinCursor;

   public MergeNode(FromTable var1, FromTable var2, ValueNode var3, QueryTreeNodeVector var4, ContextManager var5) throws StandardException {
      super((ResultSetNode)null, (MatchingClauseNode)null, var5);
      if (!(var1 instanceof FromBaseTable)) {
         this.notBaseTable();
      } else {
         this._targetTable = (FromBaseTable)var1;
      }

      this._sourceTable = var2;
      this._searchCondition = var3;
      this._matchingClauses = var4;
   }

   FromBaseTable getTargetTable() {
      return this._targetTable;
   }

   void associateColumn(FromList var1, ColumnReference var2, int var3) throws StandardException {
      if (var3 != 0) {
         var2.setMergeTableID(var3);
      } else {
         String var4 = var2.getTableName();
         if (((FromTable)var1.elementAt(0)).getMatchingColumn(var2) != null) {
            var2.setMergeTableID(1);
         } else if (((FromTable)var1.elementAt(1)).getMatchingColumn(var2) != null) {
            var2.setMergeTableID(2);
         }
      }

   }

   void bindExpression(ValueNode var1, FromList var2) throws StandardException {
      CompilerContext var3 = this.getCompilerContext();
      int var4 = var3.getReliability();
      var3.setReliability(var4 | 8192);
      var3.pushCurrentPrivType(0);

      try {
         var1.bindExpression(var2, new SubqueryList(this.getContextManager()), new ArrayList());
      } finally {
         var3.popCurrentPrivType();
         var3.setReliability(var4);
      }

   }

   void getColumnsInExpression(HashMap var1, ValueNode var2, int var3) throws StandardException {
      if (var2 != null) {
         List var4 = this.getColumnReferences(var2);
         this.getColumnsFromList(var1, var4, var3);
      }
   }

   void getColumnsFromList(HashMap var1, ResultColumnList var2, int var3) throws StandardException {
      List var4 = this.getColumnReferences(var2);
      this.getColumnsFromList(var1, var4, var3);
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      if (!(this._sourceTable instanceof FromVTI) && !(this._sourceTable instanceof FromBaseTable)) {
         throw StandardException.newException("42XAL", new Object[0]);
      } else if (this.getExposedName(this._targetTable).equals(this.getExposedName(this._sourceTable))) {
         throw StandardException.newException("42XAM", new Object[0]);
      } else {
         this.forbidDerivedColumnLists();
         this.forbidSynonyms();
         IgnoreFilter var2 = new IgnoreFilter();
         this.getCompilerContext().addPrivilegeFilter(var2);
         FromList var3 = new FromList(this.getContextManager());
         FromTable var4 = this.cloneFromTable(this._sourceTable);
         FromBaseTable var5 = (FromBaseTable)this.cloneFromTable(this._targetTable);
         var3.addFromTable(var4);
         var3.addFromTable(var5);
         var3.bindTables(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
         if (!this.targetIsBaseTable(var5)) {
            this.notBaseTable();
         }

         this.getCompilerContext().removePrivilegeFilter(var2);

         for(MatchingClauseNode var7 : this._matchingClauses) {
            FromList var8 = this.cloneFromList(var1, var5);
            FromBaseTable var9 = (FromBaseTable)var8.elementAt(1);
            var7.bind(var1, this, var8, var9);
            SelectNode.checkNoWindowFunctions(var7, "matching clause");
            checkNoAggregates(var7);
         }

         this.bindLeftJoin(var1);
      }
   }

   static void checkNoAggregates(QueryTreeNode var0) throws StandardException {
      HasNodeVisitor var1 = new HasNodeVisitor(AggregateNode.class, SubqueryNode.class);
      var0.accept(var1);
      if (var1.hasNode()) {
         throw StandardException.newException("42Z09", new Object[0]);
      }
   }

   private String getExposedName(FromTable var1) throws StandardException {
      return var1.getTableName().getTableName();
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this._sourceTable.referencesSessionSchema() || this._targetTable.referencesSessionSchema() || this._searchCondition.referencesSessionSchema() || this._matchingClauses.referencesSessionSchema();
   }

   private void forbidDerivedColumnLists() throws StandardException {
      if (this._sourceTable.getResultColumns() != null || this._targetTable.getResultColumns() != null) {
         throw StandardException.newException("42XAQ", new Object[0]);
      }
   }

   private void forbidSynonyms() throws StandardException {
      this.forbidSynonyms(this._targetTable.getTableNameField().cloneMe());
      if (this._sourceTable instanceof FromBaseTable) {
         this.forbidSynonyms(((FromBaseTable)this._sourceTable).getTableNameField().cloneMe());
      }

   }

   private void forbidSynonyms(TableName var1) throws StandardException {
      var1.bind();
      TableName var2 = this.resolveTableToSynonym(var1);
      if (var2 != null) {
         throw StandardException.newException("42XAP", new Object[0]);
      }
   }

   private void notBaseTable() throws StandardException {
      throw StandardException.newException("42XAK", new Object[0]);
   }

   private boolean targetIsBaseTable(FromBaseTable var1) throws StandardException {
      TableDescriptor var3 = var1.getTableDescriptor();
      if (var3 == null) {
         return false;
      } else {
         switch (var3.getTableType()) {
            case 0:
            case 3:
               return true;
            default:
               return false;
         }
      }
   }

   private boolean sourceIsBase_or_VTI() throws StandardException {
      if (this._sourceTable instanceof FromVTI) {
         return true;
      } else if (!(this._sourceTable instanceof FromBaseTable)) {
         return false;
      } else {
         FromBaseTable var1 = (FromBaseTable)this._sourceTable;
         TableDescriptor var2 = var1.getTableDescriptor();
         if (var2 == null) {
            return false;
         } else {
            switch (var2.getTableType()) {
               case 0:
               case 1:
               case 3:
                  return true;
               case 2:
               default:
                  return false;
            }
         }
      }
   }

   private void bindLeftJoin(DataDictionary var1) throws StandardException {
      CompilerContext var2 = this.getCompilerContext();
      int var3 = var2.getReliability();

      try {
         var2.setReliability(var3 | 8192);
         IgnoreFilter var4 = new IgnoreFilter();
         this.getCompilerContext().addPrivilegeFilter(var4);
         this._hojn = new HalfOuterJoinNode(this._sourceTable, this._targetTable, this._searchCondition, (ResultColumnList)null, false, (Properties)null, this.getContextManager());
         this._leftJoinFromList = this._hojn.makeFromList(true, true);
         this._leftJoinFromList.bindTables(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
         if (!this.sourceIsBase_or_VTI()) {
            throw StandardException.newException("42XAL", new Object[0]);
         }

         FromList var5 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         var5.addFromTable(this._hojn);
         this.getCompilerContext().removePrivilegeFilter(var4);

         for(MatchingClauseNode var7 : this._matchingClauses) {
            var7.bindRefinement(this, this._leftJoinFromList);
         }

         ResultColumnList var11 = this.buildSelectList();
         this._selectList = var11.copyListAndObjects();
         this.resultSet = new SelectNode(var11, var5, (ValueNode)null, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
         this._leftJoinCursor = new CursorNode("SELECT", this.resultSet, (String)null, (OrderByList)null, (ValueNode)null, (ValueNode)null, false, 1, (String[])null, true, this.getContextManager());
         this.getCompilerContext().addPrivilegeFilter(var4);
         this._leftJoinCursor.bindStatement();
         this.getCompilerContext().removePrivilegeFilter(var4);
         this.addOnClausePrivileges();
      } finally {
         var2.setReliability(var3);
      }

   }

   private FromList cloneFromList(DataDictionary var1, FromBaseTable var2) throws StandardException {
      FromList var3 = new FromList(this.getContextManager());
      FromBaseTable var4 = new FromBaseTable(var2.getTableNameField(), var2.correlationName, (ResultColumnList)null, (Properties)null, this.getContextManager());
      FromTable var5 = this.cloneFromTable(this._sourceTable);
      var4.setMergeTableID(2);
      var5.setMergeTableID(1);
      var3.addFromTable(var5);
      var3.addFromTable(var4);
      IgnoreFilter var6 = new IgnoreFilter();
      this.getCompilerContext().addPrivilegeFilter(var6);
      var3.bindTables(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
      this.getCompilerContext().removePrivilegeFilter(var6);
      return var3;
   }

   private FromTable cloneFromTable(FromTable var1) throws StandardException {
      if (var1 instanceof FromVTI var3) {
         return new FromVTI(var3.methodCall, var3.correlationName, var3.getResultColumns(), (Properties)null, var3.exposedName, this.getContextManager());
      } else if (var1 instanceof FromBaseTable var2) {
         return new FromBaseTable(var2.tableName, var2.correlationName, (ResultColumnList)null, (Properties)null, this.getContextManager());
      } else {
         throw StandardException.newException("42XAL", new Object[0]);
      }
   }

   private void addOnClausePrivileges() throws StandardException {
      for(ColumnReference var2 : this.getColumnReferences(this._searchCondition)) {
         this.addColumnPrivilege(var2);
      }

      for(StaticMethodCallNode var5 : this.getRoutineReferences(this._searchCondition)) {
         this.addRoutinePrivilege(var5);
      }

      for(CastNode var6 : this.getCastNodes(this._searchCondition)) {
         this.addUDTUsagePriv(var6);
      }

   }

   private void addColumnPrivilege(ColumnReference var1) throws StandardException {
      CompilerContext var2 = this.getCompilerContext();
      ResultColumn var3 = var1.getSource();
      if (var3 != null) {
         ColumnDescriptor var4 = var3.getColumnDescriptor();
         if (var4 != null) {
            var2.pushCurrentPrivType(0);
            var2.addRequiredColumnPriv(var4);
            var2.popCurrentPrivType();
         }
      }

   }

   private void addRoutinePrivilege(StaticMethodCallNode var1) throws StandardException {
      CompilerContext var2 = this.getCompilerContext();
      var2.pushCurrentPrivType(6);
      var2.addRequiredRoutinePriv(var1.ad);
      var2.popCurrentPrivType();
   }

   private List getCastNodes(QueryTreeNode var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(CastNode.class);
      var1.accept(var2);
      return var2.getList();
   }

   private List getRoutineReferences(QueryTreeNode var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(StaticMethodCallNode.class);
      var1.accept(var2);
      return var2.getList();
   }

   private ResultColumnList buildSelectList() throws StandardException {
      HashMap var1 = new HashMap();
      this.getColumnsInExpression(var1, this._searchCondition, 0);

      for(MatchingClauseNode var3 : this._matchingClauses) {
         var3.getColumnsInExpressions(this, var1);
         int var4 = var3.isDeleteClause() ? 2 : 0;
         this.getColumnsFromList(var1, var3.getThenColumns(), var4);
      }

      ResultColumnList var5 = new ResultColumnList(this.getContextManager());
      this.addColumns((FromTable)this._leftJoinFromList.elementAt(0), var1, var5, 1);
      this.addColumns((FromTable)this._leftJoinFromList.elementAt(1), var1, var5, 2);
      this.addTargetRowLocation(var5);
      return var5;
   }

   private void addTargetRowLocation(ResultColumnList var1) throws StandardException {
      this._targetTable.setRowLocationColumnName("###TargetRowLocation");
      TableName var2 = this._targetTable.getTableName();
      ColumnReference var3 = new ColumnReference("###TargetRowLocation", var2, this.getContextManager());
      var3.setMergeTableID(2);
      ResultColumn var4 = new ResultColumn((String)null, var3, this.getContextManager());
      var4.markGenerated();
      var1.addResultColumn(var4);
   }

   private void addColumns(FromTable var1, HashMap var2, ResultColumnList var3, int var4) throws StandardException {
      String[] var5 = this.getColumns(var4, var2);
      TableName var6 = var1.getTableName();

      for(int var7 = 0; var7 < var5.length; ++var7) {
         ColumnReference var8 = new ColumnReference(var5[var7], var6, this.getContextManager());
         var8.setMergeTableID(var4);
         ResultColumn var9 = new ResultColumn((String)null, var8, this.getContextManager());
         var3.addResultColumn(var9);
      }

   }

   private String[] getColumns(int var1, HashMap var2) {
      HashSet var3 = new HashSet();

      for(ColumnReference var5 : var2.values()) {
         if (var5.getMergeTableID() == var1) {
            var3.add(var5.getColumnName());
         }
      }

      String[] var6 = new String[var3.size()];
      var3.toArray(var6);
      Arrays.sort(var6);
      return var6;
   }

   private List getColumnReferences(QueryTreeNode var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(ColumnReference.class);
      var1.accept(var2);
      return var2.getList();
   }

   private void getColumnsFromList(HashMap var1, List var2, int var3) throws StandardException {
      for(ColumnReference var5 : var2) {
         this.addColumn(var1, var5, var3);
      }

   }

   void addColumn(HashMap var1, ColumnReference var2, int var3) throws StandardException {
      if (var2.getTableName() == null) {
         var2 = var2.bindExpression(this._leftJoinFromList, new SubqueryList(this.getContextManager()), new ArrayList());
         TableName var4 = var2.getQualifiedTableName();
         var2 = new ColumnReference(var2.getColumnName(), var4, this.getContextManager());
      }

      this.associateColumn(this._leftJoinFromList, var2, var3);
      String var7 = this.makeDCMKey(var2.getTableName(), var2.getColumnName());
      ColumnReference var5 = (ColumnReference)var1.get(var7);
      if (var5 != null) {
         var5.setMergeTableID(var2.getMergeTableID());
      } else {
         var1.put(var7, var2);
      }

   }

   private String makeDCMKey(String var1, String var2) {
      return IdUtil.mkQualifiedName(var1, var2);
   }

   public void optimizeStatement() throws StandardException {
      IgnoreFilter var1 = new IgnoreFilter();
      this.getCompilerContext().addPrivilegeFilter(var1);
      this._leftJoinCursor.optimizeStatement();

      for(MatchingClauseNode var3 : this._matchingClauses) {
         var3.optimize();
      }

      this.getCompilerContext().removePrivilegeFilter(var1);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this._matchingClauses.size();
      this.generateParameterValueSet(var1);
      var1.pushGetResultSetFactoryExpression(var2);
      this._leftJoinCursor.generate(var1, var2);
      ScrollInsensitiveResultSetNode var4 = (ScrollInsensitiveResultSetNode)this._leftJoinCursor.resultSet;
      ResultSetNode var5 = var4.getChildResult();
      ConstantAction[] var6 = new ConstantAction[var3];

      for(int var7 = 0; var7 < var3; ++var7) {
         MatchingClauseNode var8 = (MatchingClauseNode)this._matchingClauses.elementAt(var7);
         var8.generate(var1, this._selectList, var5, this._hojn, var7);
         var6[var7] = var8.makeConstantAction(var1);
      }

      this._constantAction = this.getGenericConstantActionFactory().getMergeConstantAction(var6);
      var2.callMethod((short)185, (String)null, "getMergeResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this._constantAction;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      if (this._leftJoinCursor != null) {
         this._leftJoinCursor.acceptChildren(var1);
      } else {
         super.acceptChildren(var1);
         this._targetTable.accept(var1);
         this._sourceTable.accept(var1);
         this._searchCondition.accept(var1);
      }

      for(MatchingClauseNode var3 : this._matchingClauses) {
         var3.accept(var1);
      }

   }

   void printSubNodes(int var1) {
   }

   String statementToString() {
      return "MERGE";
   }
}
