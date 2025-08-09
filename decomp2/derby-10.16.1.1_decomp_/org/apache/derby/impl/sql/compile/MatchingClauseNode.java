package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.IgnoreFilter;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class MatchingClauseNode extends QueryTreeNode {
   private static final String CURRENT_OF_NODE_NAME = "$MERGE_CURRENT";
   private ValueNode _matchingRefinement;
   private ResultColumnList _updateColumns;
   private ResultColumnList _insertColumns;
   private ResultColumnList _insertValues;
   private DMLModStatementNode _dml;
   private ResultColumnList _thenColumns;
   private int _clauseNumber;
   private String _actionMethodName;
   private String _resultSetFieldName;
   private String _rowMakingMethodName;

   private MatchingClauseNode(ValueNode var1, ResultColumnList var2, ResultColumnList var3, ResultColumnList var4, ContextManager var5) throws StandardException {
      super(var5);
      this._matchingRefinement = var1;
      this._updateColumns = var2;
      this._insertColumns = var3;
      this._insertValues = var4;
   }

   static MatchingClauseNode makeUpdateClause(ValueNode var0, ResultColumnList var1, ContextManager var2) throws StandardException {
      return new MatchingClauseNode(var0, var1, (ResultColumnList)null, (ResultColumnList)null, var2);
   }

   static MatchingClauseNode makeDeleteClause(ValueNode var0, ContextManager var1) throws StandardException {
      return new MatchingClauseNode(var0, (ResultColumnList)null, (ResultColumnList)null, (ResultColumnList)null, var1);
   }

   static MatchingClauseNode makeInsertClause(ValueNode var0, ResultColumnList var1, ResultColumnList var2, ContextManager var3) throws StandardException {
      return new MatchingClauseNode(var0, (ResultColumnList)null, var1, var2, var3);
   }

   boolean isUpdateClause() {
      return this._updateColumns != null;
   }

   boolean isInsertClause() {
      return this._insertValues != null;
   }

   boolean isDeleteClause() {
      return !this.isUpdateClause() && !this.isInsertClause();
   }

   ResultColumnList getThenColumns() {
      return this._thenColumns;
   }

   void bind(DataDictionary var1, MergeNode var2, FromList var3, FromBaseTable var4) throws StandardException {
      this.forbidSubqueries();
      this._thenColumns = new ResultColumnList(this.getContextManager());
      if (this.isDeleteClause()) {
         this.bindDelete(var1, var3, var4);
      }

      if (this.isUpdateClause()) {
         this.bindUpdate(var1, var2, var3, var4);
      }

      if (this.isInsertClause()) {
         this.bindInsert(var1, var2, var3, var4);
      }

   }

   void bindRefinement(MergeNode var1, FromList var2) throws StandardException {
      if (this._matchingRefinement != null) {
         FromList var3 = var2;
         if (this.isInsertClause()) {
            var3 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
            var3.addElement((ResultSetNode)var2.elementAt(0));
         }

         var1.bindExpression(this._matchingRefinement, var3);
      }

   }

   void getColumnsInExpressions(MergeNode var1, HashMap var2) throws StandardException {
      if (this._matchingRefinement != null) {
         var1.getColumnsInExpression(var2, this._matchingRefinement, 0);
      }

      if (this.isUpdateClause()) {
         TableName var3 = var1.getTargetTable().getTableName();

         for(ResultColumn var5 : this._updateColumns) {
            var1.getColumnsInExpression(var2, var5.getExpression(), 0);
            ColumnReference var6 = new ColumnReference(var5.getName(), var3, this.getContextManager());
            var1.addColumn(var2, var6, 2);
         }
      } else if (this.isInsertClause()) {
         for(ResultColumn var8 : this._insertValues) {
            var1.getColumnsInExpression(var2, var8.getExpression(), 0);
         }
      } else if (this.isDeleteClause()) {
         var1.getColumnsFromList(var2, (ResultColumnList)this._thenColumns, 2);
      }

   }

   private void bindUpdate(DataDictionary var1, MergeNode var2, FromList var3, FromBaseTable var4) throws StandardException {
      ResultColumnList var5 = this.realiasSetClauses(var4);
      this.bindSetClauses(var2, var3, var4, var5);
      TableName var6 = var4.getTableNameField();
      SelectNode var8 = new SelectNode(var5, var3, (ValueNode)null, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      this._dml = new UpdateNode(var6, var8, this, this.getContextManager());
      this._dml.bindStatement();
      boolean var9 = this.getCompilerContext().skipTypePrivileges(true);
      ResultColumnList var10 = new ResultColumnList(this.getContextManager());
      ResultColumnList var11 = new ResultColumnList(this.getContextManager());
      ResultColumnList var12 = this.getBoundSelectUnderUpdate().getResultColumns();
      int var13 = var12.size() / 2;

      for(int var14 = 0; var14 < var13; ++var14) {
         ResultColumn var15 = (ResultColumn)var12.elementAt(var14);
         ResultColumn var16 = (ResultColumn)var12.elementAt(var14 + var13);
         ResultColumn var17 = var15.cloneMe();
         ResultColumn var18 = var16.cloneMe();
         var10.addResultColumn(var17);
         var11.addResultColumn(var18);
      }

      this.buildThenColumnsForUpdate(var3, var4, var12, var10, var11);
      this.getCompilerContext().skipTypePrivileges(var9);
   }

   private ResultColumnList realiasSetClauses(FromBaseTable var1) throws StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());

      for(int var3 = 0; var3 < this._updateColumns.size(); ++var3) {
         ResultColumn var4 = (ResultColumn)this._updateColumns.elementAt(var3);
         TableName var5 = var1.getTableName();
         ColumnReference var6 = new ColumnReference(var4.getReference().getColumnName(), var5, this.getContextManager());
         var6.setMergeTableID(2);
         ResultColumn var7 = new ResultColumn(var6, var4.getExpression(), this.getContextManager());
         var2.addResultColumn(var7);
      }

      return var2;
   }

   private ResultSetNode getBoundSelectUnderUpdate() throws StandardException {
      for(ResultSetNode var1 = this._dml.resultSet; var1 != null; var1 = ((SingleChildResultSetNode)var1).getChildResult()) {
         if (var1 instanceof SelectNode) {
            return var1;
         }

         if (!(var1 instanceof SingleChildResultSetNode)) {
            break;
         }
      }

      throw StandardException.newException("0A000.S", new Object[0]);
   }

   private void bindSetClauses(MergeNode var1, FromList var2, FromTable var3, ResultColumnList var4) throws StandardException {
      var4.replaceOrForbidDefaults(var3.getTableDescriptor(), this._updateColumns, true);
      this.bindExpressions(var4, var2);

      for(int var5 = 0; var5 < this._updateColumns.size(); ++var5) {
         ResultColumn var6 = (ResultColumn)this._updateColumns.elementAt(var5);
         ColumnReference var7 = var6.getReference();
         var7.setMergeTableID(2);
      }

      for(ColumnReference var10 : this.getColumnReferences(this._updateColumns)) {
         var1.associateColumn(var2, var10, 0);
      }

   }

   private void buildThenColumnsForUpdate(FromList var1, FromTable var2, ResultColumnList var3, ResultColumnList var4, ResultColumnList var5) throws StandardException {
      TableDescriptor var6 = var2.getTableDescriptor();
      HashSet var7 = this.getChangedColumnNames();
      HashSet var8 = this.getChangedGeneratedColumnNames(var6, var7);
      this._thenColumns = var3.copyListAndObjects();

      for(int var9 = 0; var9 < this._thenColumns.size(); ++var9) {
         ResultColumn var10 = (ResultColumn)this._thenColumns.elementAt(var9);
         boolean var11 = var9 >= var4.size();
         boolean var12 = this.isRowLocation(var10);
         ValueNode var13 = var10.getExpression();
         if (!var12) {
            String var14 = var10.getName();
            ColumnDescriptor var15 = var6.getColumnDescriptor(var14);
            boolean var16 = false;
            if (var15.isAutoincrement() && var10.getExpression() instanceof NumericConstantNode) {
               DataValueDescriptor var17 = ((NumericConstantNode)var10.getExpression()).getValue();
               if (var17 == null) {
                  ResultColumn var27 = this.makeAutoGenRC(var2, var10, var9 + 1);
                  var27.setVirtualColumnId(var10.getVirtualColumnId());
                  this._thenColumns.setElementAt(var27, var9);
                  continue;
               }
            }

            if (!var10.isAutoincrement() && var10.getExpression() instanceof VirtualColumnNode) {
               var10.setExpression(new UntypedNullConstantNode(this.getContextManager()));
            }

            if (var15.hasGenerationClause()) {
               if (var11 && var8.contains(var14)) {
                  var10.setExpression(new UntypedNullConstantNode(this.getContextManager()));
               } else {
                  ColumnReference var26 = new ColumnReference(var14, var2.getTableName(), this.getContextManager());
                  var10.setExpression(var26);
                  var10.setColumnDescriptor((TableDescriptor)null, (ColumnDescriptor)null);
               }
            } else {
               if (var11) {
                  label89: {
                     int var23 = 0;

                     ResultColumn var28;
                     while(true) {
                        if (var23 >= var4.size()) {
                           break label89;
                        }

                        ResultColumn var18 = (ResultColumn)var4.elementAt(var23);
                        if (var14.equals(var18.getName())) {
                           Object var19 = null;
                           ResultColumn var20 = (ResultColumn)var5.elementAt(var23);
                           if (!var20.wasDefaultColumn() && !(var20.getExpression() instanceof UntypedNullConstantNode)) {
                              var28 = var20.cloneMe();
                              var28.setType(var10.getTypeServices());
                              break;
                           }

                           if (var15.isAutoincrement()) {
                              var28 = this.makeAutoGenRC(var2, var10, var9 + 1);
                              break;
                           }

                           ValueNode var21 = var10.getExpression();
                           if (var21 instanceof ColumnReference) {
                              var10.setExpression(new UntypedNullConstantNode(this.getContextManager()));
                           }
                        }

                        ++var23;
                     }

                     var28.setVirtualColumnId(var10.getVirtualColumnId());
                     this._thenColumns.setElementAt(var28, var9);
                     var16 = true;
                  }
               }

               if (!var16) {
                  DefaultInfoImpl var24 = (DefaultInfoImpl)var15.getDefaultInfo();
                  if (var24 != null && !var24.isGeneratedColumn() && !var15.isAutoincrement()) {
                     this._thenColumns.setDefault(var10, var15, var24);
                     var16 = true;
                  }
               }

               ResultColumn var25 = (ResultColumn)this._thenColumns.elementAt(var9);
               var25.setName(var15.getColumnName());
               var25.resetAutoincrementGenerated();
            }
         }
      }

   }

   private HashSet getChangedColumnNames() throws StandardException {
      HashSet var1 = new HashSet();

      for(int var2 = 0; var2 < this._updateColumns.size(); ++var2) {
         String var3 = ((ResultColumn)this._updateColumns.elementAt(var2)).getName();
         var1.add(var3);
      }

      return var1;
   }

   private HashSet getChangedGeneratedColumnNames(TableDescriptor var1, HashSet var2) throws StandardException {
      HashSet var3 = new HashSet();

      for(ColumnDescriptor var5 : var1.getColumnDescriptorList()) {
         if (var5.hasGenerationClause()) {
            if (var2.contains(var5.getColumnName())) {
               var3.add(var5.getColumnName());
            } else {
               String[] var6 = var5.getDefaultInfo().getReferencedColumnNames();

               for(String var10 : var6) {
                  if (var2.contains(var10)) {
                     var3.add(var10);
                     break;
                  }
               }
            }
         }
      }

      return var3;
   }

   private void bindDelete(DataDictionary var1, FromList var2, FromBaseTable var3) throws StandardException {
      IgnoreFilter var4 = new IgnoreFilter();
      this.getCompilerContext().addPrivilegeFilter(var4);
      FromBaseTable var5 = new FromBaseTable(var3.getTableNameField(), (String)null, (ResultColumnList)null, (Properties)null, this.getContextManager());
      FromList var6 = new FromList(this.getContextManager());
      var6.addFromTable(var5);
      var6.bindTables(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
      CurrentOfNode var7 = CurrentOfNode.makeForMerge("$MERGE_CURRENT", var5, this.getContextManager());
      FromList var8 = new FromList(this.getContextManager());
      var8.addFromTable(var7);
      SelectNode var9 = new SelectNode((ResultColumnList)null, var8, (ValueNode)null, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      this._dml = new DeleteNode(var3.getTableNameField(), var9, this, this.getContextManager());
      this.getCompilerContext().removePrivilegeFilter(var4);
      this._dml.bindStatement();
      this.buildThenColumnsForDelete();
   }

   private void buildThenColumnsForDelete() throws StandardException {
      ResultColumnList var1 = this._dml.resultSet.getResultColumns();

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         ResultColumn var3 = (ResultColumn)var1.elementAt(var2);
         ValueNode var5 = var3.getExpression();
         ResultColumn var4;
         if (var5 instanceof ColumnReference) {
            ColumnReference var6 = (ColumnReference)((ColumnReference)var5).getClone();
            var4 = new ResultColumn(var6, var6, this.getContextManager());
         } else {
            var4 = var3.cloneMe();
         }

         this._thenColumns.addResultColumn(var4);
      }

   }

   private void bindInsert(DataDictionary var1, MergeNode var2, FromList var3, FromBaseTable var4) throws StandardException {
      ResultColumnList var5 = new ResultColumnList(this.getContextManager());

      for(int var6 = 0; var6 < this._insertValues.size(); ++var6) {
         ResultColumn var7 = (ResultColumn)this._insertValues.elementAt(var6);
         var5.addResultColumn(var7.cloneMe());
      }

      var5.replaceOrForbidDefaults(var4.getTableDescriptor(), this._insertColumns, true);
      this.bindExpressions(var5, var3);
      this.bindInsertValues(var3, var4);
      FromList var8 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      var8.addElement((ResultSetNode)var3.elementAt(0));
      this.bindExpressions(this._insertValues, var8);
      SelectNode var9 = new SelectNode(var5, var3, (ValueNode)null, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      this._dml = new InsertNode(var4.getTableNameField(), this._insertColumns, var9, this, (Properties)null, (OrderByList)null, (ValueNode)null, (ValueNode)null, false, this.getContextManager());
      this._dml.bindStatement();
      this.buildThenColumnsForInsert(var3, var4, this._dml.resultSet.getResultColumns(), this._insertColumns, this._insertValues);
   }

   private void bindInsertValues(FromList var1, FromTable var2) throws StandardException {
      TableDescriptor var3 = var2.getTableDescriptor();
      if (this._insertColumns == null) {
         this._insertColumns = this.buildFullColumnList(var3);
      }

      if (this._insertColumns.size() != this._insertValues.size()) {
         throw StandardException.newException("42802", new Object[0]);
      } else {
         for(int var4 = 0; var4 < this._insertValues.size(); ++var4) {
            ResultColumn var5 = (ResultColumn)this._insertValues.elementAt(var4);
            String var6 = ((ResultColumn)this._insertColumns.elementAt(var4)).getName();
            ValueNode var7 = var5.getExpression();
            ColumnDescriptor var8 = var3.getColumnDescriptor(var6);
            if (var8 != null) {
               if (var8.isAutoincAlways() && !(var7 instanceof DefaultNode)) {
                  throw StandardException.newException("42Z23", new Object[]{var6});
               }

               if (var8.isAutoincrement() && var7 instanceof UntypedNullConstantNode) {
                  throw StandardException.newException("23502", new Object[]{var6});
               }
            }
         }

         this._insertValues.replaceOrForbidDefaults(var2.getTableDescriptor(), this._insertColumns, true);
         this.bindExpressions(this._insertValues, var1);
      }
   }

   private ResultColumnList buildFullColumnList(TableDescriptor var1) throws StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());
      ColumnDescriptorList var3 = var1.getColumnDescriptorList();
      int var4 = var3.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ColumnDescriptor var6 = var3.elementAt(var5);
         ColumnReference var7 = new ColumnReference(var6.getColumnName(), (TableName)null, this.getContextManager());
         ResultColumn var8 = new ResultColumn(var7, (ValueNode)null, this.getContextManager());
         var2.addResultColumn(var8);
      }

      return var2;
   }

   private void buildThenColumnsForInsert(FromList var1, FromTable var2, ResultColumnList var3, ResultColumnList var4, ResultColumnList var5) throws StandardException {
      boolean var6 = this.getCompilerContext().skipTypePrivileges(true);
      TableDescriptor var7 = var2.getTableDescriptor();
      this._thenColumns = var3.copyListAndObjects();

      for(int var8 = 0; var8 < this._thenColumns.size(); ++var8) {
         ResultColumn var9 = (ResultColumn)this._thenColumns.elementAt(var8);
         String var10 = var9.getName();
         ColumnDescriptor var11 = var7.getColumnDescriptor(var10);
         boolean var12 = false;
         if (!var9.isAutoincrement() && var9.getExpression() instanceof VirtualColumnNode) {
            var9.setExpression(new UntypedNullConstantNode(this.getContextManager()));
         }

         if (var11.hasGenerationClause()) {
            var9.setExpression(new UntypedNullConstantNode(this.getContextManager()));
         } else {
            int var13 = 0;

            while(var13 < var4.size()) {
               ResultColumn var21;
               label55: {
                  ResultColumn var14 = (ResultColumn)var4.elementAt(var13);
                  if (var10.equals(var14.getName())) {
                     Object var15 = null;
                     ResultColumn var16 = (ResultColumn)var5.elementAt(var13);
                     if (!var16.wasDefaultColumn() && !(var16.getExpression() instanceof UntypedNullConstantNode)) {
                        var21 = var16.cloneMe();
                        var21.setType(var9.getTypeServices());
                        break label55;
                     }

                     if (var11.isAutoincrement()) {
                        var21 = this.makeAutoGenRC(var2, var9, var8 + 1);
                        break label55;
                     }

                     ValueNode var17 = var9.getExpression();
                     if (var17 instanceof ColumnReference) {
                        var9.setExpression(new UntypedNullConstantNode(this.getContextManager()));
                     }
                  }

                  ++var13;
                  continue;
               }

               var21.setVirtualColumnId(var9.getVirtualColumnId());
               this._thenColumns.setElementAt(var21, var8);
               var12 = true;
               break;
            }

            if (!var12) {
               DefaultInfoImpl var19 = (DefaultInfoImpl)var11.getDefaultInfo();
               if (var19 != null && !var19.isGeneratedColumn() && !var11.isAutoincrement()) {
                  this._thenColumns.setDefault(var9, var11, var19);
                  var12 = true;
               }
            }

            ResultColumn var20 = (ResultColumn)this._thenColumns.elementAt(var8);
            var20.setName(var11.getColumnName());
         }
      }

      this.getCompilerContext().skipTypePrivileges(var6);
   }

   private ResultColumn makeAutoGenRC(FromTable var1, ResultColumn var2, int var3) throws StandardException {
      String var4 = var2.getName();
      ColumnReference var5 = new ColumnReference(var4, var1.getTableName(), this.getContextManager());
      ResultColumn var6 = new ResultColumn(var5, (ValueNode)null, this.getContextManager());
      VirtualColumnNode var7 = new VirtualColumnNode(var1, var6, var3, this.getContextManager());
      ResultColumn var8 = new ResultColumn(var5, var7, this.getContextManager());
      var8.setType(var2.getTypeServices());
      return var8;
   }

   private void bindExpressions(ResultColumnList var1, FromList var2) throws StandardException {
      CompilerContext var3 = this.getCompilerContext();
      int var4 = var3.getReliability();
      boolean var5 = var3.skipTypePrivileges(true);
      var3.setReliability(var4 | 8192);

      try {
         var1.bindExpressions(var2, new SubqueryList(this.getContextManager()), new ArrayList());
      } finally {
         var3.setReliability(var4);
         var3.skipTypePrivileges(var5);
      }

   }

   private void forbidSubqueries() throws StandardException {
      this.forbidSubqueries(this._matchingRefinement);
      this.forbidSubqueries(this._updateColumns);
      this.forbidSubqueries(this._insertColumns);
      this.forbidSubqueries(this._insertValues);
   }

   private void forbidSubqueries(ResultColumnList var1) throws StandardException {
      if (var1 != null) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            this.forbidSubqueries((ValueNode)var1.elementAt(var2));
         }
      }

   }

   private void forbidSubqueries(ValueNode var1) throws StandardException {
      if (var1 != null) {
         CollectNodesVisitor var2 = new CollectNodesVisitor(SubqueryNode.class);
         var1.accept(var2);
         if (var2.getList().size() > 0) {
            throw StandardException.newException("42XAO", new Object[0]);
         }
      }

   }

   void optimize() throws StandardException {
      this._dml.optimizeStatement();
   }

   ConstantAction makeConstantAction(ActivationClassBuilder var1) throws StandardException {
      String var2 = null;
      if (this._matchingRefinement != null) {
         MethodBuilder var3 = var1.newUserExprFun();
         this._matchingRefinement.generateExpression(var1, var3);
         var3.methodReturn();
         var3.complete();
         var2 = var3.getName();
      }

      return this.getGenericConstantActionFactory().getMatchingClauseConstantAction(this.getClauseType(), var2, this.buildThenColumnSignature(), this._rowMakingMethodName, this._resultSetFieldName, this._actionMethodName, this._dml.makeConstantAction());
   }

   private int getClauseType() {
      if (this.isUpdateClause()) {
         return 1;
      } else {
         return this.isInsertClause() ? 0 : 2;
      }
   }

   private ResultDescription buildThenColumnSignature() throws StandardException {
      ResultColumnDescriptor[] var1 = this._thenColumns.makeResultDescriptors();
      return this.getLanguageConnectionContext().getLanguageFactory().getResultDescription(var1, "MERGE");
   }

   void generate(ActivationClassBuilder var1, ResultColumnList var2, ResultSetNode var3, HalfOuterJoinNode var4, int var5) throws StandardException {
      this._clauseNumber = var5;
      this.adjustMatchingRefinement(var2, var3);
      this.generateInsertUpdateRow(var1, var2, var3, var4);
      this._actionMethodName = "mergeActionMethod_" + this._clauseNumber;
      MethodBuilder var6 = var1.getClassBuilder().newMethodBuilder(1, "org.apache.derby.iapi.sql.ResultSet", this._actionMethodName);
      var6.addThrownException("org.apache.derby.shared.common.error.StandardException");
      this.remapConstraints();
      this._dml.generate(var1, var6);
      var6.methodReturn();
      var6.complete();
   }

   private void remapConstraints() throws StandardException {
      if (!this.isDeleteClause()) {
         ValueNode var1 = this.isInsertClause() ? ((InsertNode)this._dml).checkConstraints : ((UpdateNode)this._dml).checkConstraints;
         if (var1 != null) {
            for(ColumnReference var4 : this.getColumnReferences(var1)) {
               var4.getSource().setResultSetNumber(0);
            }
         }

      }
   }

   void generateResultSetField(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this._resultSetFieldName = "mergeResultSetField_" + this._clauseNumber;
      LocalField var3 = var1.newFieldDeclaration(1, "org.apache.derby.iapi.sql.execute.NoPutResultSet", this._resultSetFieldName);
      var2.getField(var3);
   }

   private void generateInsertUpdateRow(ActivationClassBuilder var1, ResultColumnList var2, ResultSetNode var3, HalfOuterJoinNode var4) throws StandardException {
      this.adjustThenColumns(var2, var3, var4);
      this._rowMakingMethodName = "mergeRowMakingMethod_" + this._clauseNumber;
      MethodBuilder var5 = var1.getClassBuilder().newMethodBuilder(1, "org.apache.derby.iapi.sql.execute.ExecRow", this._rowMakingMethodName);
      var5.addThrownException("org.apache.derby.shared.common.error.StandardException");
      this._thenColumns.generateEvaluatedRow(var1, var5, false, true);
   }

   private void adjustMatchingRefinement(ResultColumnList var1, ResultSetNode var2) throws StandardException {
      if (this._matchingRefinement != null) {
         this.useGeneratedScan(var1, var2, this._matchingRefinement);
      }

   }

   private void adjustThenColumns(ResultColumnList var1, ResultSetNode var2, HalfOuterJoinNode var3) throws StandardException {
      ResultColumnList var4 = var2.getResultColumns();
      this.useGeneratedScan(var1, var2, this._thenColumns);
      int var5 = this._thenColumns.size() - 1;
      ResultColumn var6 = (ResultColumn)this._thenColumns.elementAt(var5);
      if (this.isRowLocation(var6)) {
         ResultColumn var7 = (ResultColumn)var4.elementAt(var4.size() - 1);
         ValueNode var8 = var7.getExpression();
         String var9 = var7.getName();
         ColumnReference var10 = new ColumnReference(var9, var3.getTableName(), this.getContextManager());
         var10.setSource(var7);
         ResultColumn var11 = new ResultColumn(var9, var10, this.getContextManager());
         this._thenColumns.removeElementAt(var5);
         this._thenColumns.addResultColumn(var11);
      }

   }

   private void useGeneratedScan(ResultColumnList var1, ResultSetNode var2, QueryTreeNode var3) throws StandardException {
      ResultColumnList var4 = var2.getResultColumns();

      for(ColumnReference var6 : this.getColumnReferences(var3)) {
         ResultColumn var7 = (ResultColumn)var4.elementAt(this.getSelectListOffset(var1, var6) - 1);
         var6.setSource(var7);
      }

   }

   private int getSelectListOffset(ResultColumnList var1, ValueNode var2) throws StandardException {
      int var3 = var1.size();
      if (var2 instanceof ColumnReference var4) {
         String var5 = var4.getColumnName();
         int var6 = this.getMergeTableID(var4);

         for(int var7 = 0; var7 < var3; ++var7) {
            ResultColumn var8 = (ResultColumn)var1.elementAt(var7);
            ValueNode var9 = var8.getExpression();
            ColumnReference var10 = var9 instanceof ColumnReference ? (ColumnReference)var9 : null;
            if (var10 != null && this.getMergeTableID(var10) == var6 && var5.equals(var10.getColumnName())) {
               return var7 + 1;
            }
         }
      } else if (var2 instanceof CurrentRowLocationNode) {
         return var3;
      }

      return -1;
   }

   private int getMergeTableID(ColumnReference var1) {
      int var2 = var1.getMergeTableID();
      return var2;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this._matchingRefinement != null) {
         this._matchingRefinement.accept(var1);
      }

      if (this._updateColumns != null) {
         this._updateColumns.accept(var1);
      }

      if (this._insertColumns != null) {
         this._insertColumns.accept(var1);
      }

      if (this._insertValues != null) {
         this._insertValues.accept(var1);
      }

      if (this._dml != null) {
         this._dml.accept(var1);
      }

   }

   void printSubNodes(int var1) {
   }

   public String toString() {
      if (this.isUpdateClause()) {
         return "UPDATE";
      } else {
         return this.isInsertClause() ? "INSERT" : "DELETE";
      }
   }

   private List getColumnReferences(QueryTreeNode var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(ColumnReference.class);
      var1.accept(var2);
      return var2.getList();
   }

   private boolean isRowLocation(ResultColumn var1) throws StandardException {
      if (var1.getExpression() instanceof CurrentRowLocationNode) {
         return true;
      } else {
         DataTypeDescriptor var2 = var1.getTypeServices();
         return var2 != null && var2.getTypeId().isRefTypeId();
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return referencesSessionSchema(this._matchingRefinement) || referencesSessionSchema(this._updateColumns) || referencesSessionSchema(this._insertColumns) || referencesSessionSchema(this._insertValues);
   }

   private static boolean referencesSessionSchema(QueryTreeNode var0) throws StandardException {
      return var0 != null && var0.referencesSessionSchema();
   }
}
