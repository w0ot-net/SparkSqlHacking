package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.IgnoreFilter;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.ScopeFilter;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DeleteNode extends DMLModStatementNode {
   private static final String COLUMNNAME = "###RowLocationToDelete";
   private boolean deferred;
   private FromTable targetTable;
   private FormatableBitSet readColsBitSet;
   private ConstantAction[] dependentConstantActions;
   private boolean cascadeDelete;
   private StatementNode[] dependentNodes;

   DeleteNode(TableName var1, ResultSetNode var2, MatchingClauseNode var3, ContextManager var4) {
      super(var2, var3, var4);
      this.targetTableName = var1;
   }

   String statementToString() {
      return "DELETE";
   }

   public void bindStatement() throws StandardException {
      this.getCompilerContext().pushCurrentPrivType(0);

      try {
         new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         ResultColumn var2 = null;
         TableName var4 = null;
         CurrentOfNode var5 = null;
         IgnoreFilter var6 = new IgnoreFilter();
         this.getCompilerContext().addPrivilegeFilter(var6);
         DataDictionary var7 = this.getDataDictionary();
         if (!this.inMatchingClause()) {
            super.bindTables(var7);
         }

         SelectNode var8 = (SelectNode)this.resultSet;
         this.targetTable = (FromTable)var8.fromList.elementAt(0);
         if (this.targetTable instanceof CurrentOfNode) {
            var5 = (CurrentOfNode)this.targetTable;
            var4 = this.inMatchingClause() ? this.targetTableName : var5.getBaseCursorTargetTableName();
         }

         if (this.targetTable instanceof FromVTI) {
            this.targetVTI = (FromVTI)this.targetTable;
            this.targetVTI.setTarget();
         } else if (this.targetTableName == null) {
            this.targetTableName = var4;
         } else if (var4 != null && !this.targetTableName.equals(var4)) {
            throw StandardException.newException("42X28", new Object[]{this.targetTableName, var5.getCursorName()});
         }

         this.verifyTargetTable();
         if (this.targetTable instanceof FromVTI) {
            this.getResultColumnList();
            this.resultColumnList = this.targetTable.getResultColumnsForList((TableName)null, this.resultColumnList, (TableName)null);
            this.resultSet.setResultColumns(this.resultColumnList);
         } else {
            this.resultColumnList = new ResultColumnList(this.getContextManager());
            FromBaseTable var9 = this.getResultColumnList(this.resultColumnList);
            this.readColsBitSet = this.getReadMap(var7, this.targetTableDescriptor);
            this.resultColumnList = var9.addColsToList(this.resultColumnList, this.readColsBitSet);
            int var10 = 1;

            int var11;
            for(var11 = this.targetTableDescriptor.getMaxColumnID(); var10 <= var11 && this.readColsBitSet.get(var10); ++var10) {
            }

            if (var10 > var11) {
               this.readColsBitSet = null;
            }

            CurrentRowLocationNode var3 = new CurrentRowLocationNode(this.getContextManager());
            var2 = new ResultColumn("###RowLocationToDelete", var3, this.getContextManager());
            var2.markGenerated();
            this.resultColumnList.addResultColumn(var2);
            this.correlateAddedColumns(this.resultColumnList, this.targetTable);
            ResultColumnList var12 = this.resultSet.getResultColumns();
            if (var12 != null) {
               var12.appendResultColumns(this.resultColumnList, false);
               this.resultColumnList = var12;
            }

            this.resultSet.setResultColumns(this.resultColumnList);
         }

         this.getCompilerContext().removePrivilegeFilter(var6);
         ScopeFilter var16 = new ScopeFilter(this.getCompilerContext(), "whereScope", 1);
         this.getCompilerContext().addPrivilegeFilter(var16);
         super.bindExpressions();
         this.getCompilerContext().removePrivilegeFilter(var16);
         this.resultSet.getResultColumns().bindUntypedNullsToResultColumns(this.resultColumnList);
         if (!(this.targetTable instanceof FromVTI)) {
            var2.bindResultColumnToExpression();
            this.bindConstraints(var7, this.getOptimizerFactory(), this.targetTableDescriptor, (Dependent)null, this.resultColumnList, (int[])null, this.readColsBitSet, true, new boolean[1]);
            if (this.resultSet.subqueryReferencesTarget(this.targetTableDescriptor.getName(), true) || this.requiresDeferredProcessing()) {
               this.deferred = true;
            }
         } else {
            this.deferred = VTIDeferModPolicy.deferIt(3, this.targetVTI, (String[])null, var8.getWhereClause());
         }

         if (this.fkTableNames != null) {
            String var21 = this.targetTableDescriptor.getSchemaName();
            String var18 = var21 + "." + this.targetTableDescriptor.getName();
            if (!this.isDependentTable) {
               this.dependentTables = new HashSet();
            }

            if (this.dependentTables.add(var18)) {
               this.cascadeDelete = true;
               int var19 = this.fkTableNames.length;
               this.dependentNodes = new StatementNode[var19];

               for(int var20 = 0; var20 < var19; ++var20) {
                  this.dependentNodes[var20] = this.getDependentTableNode(this.fkSchemaNames[var20], this.fkTableNames[var20], this.fkRefActions[var20], this.fkColDescriptors[var20]);
                  this.dependentNodes[var20].bindStatement();
               }
            }
         } else if (this.isDependentTable) {
            String var10000 = this.targetTableDescriptor.getSchemaName();
            String var17 = var10000 + "." + this.targetTableDescriptor.getName();
            this.dependentTables.add(var17);
         }

         this.getCompilerContext().pushCurrentPrivType(this.getPrivType());
         this.getCompilerContext().addRequiredTablePriv(this.targetTableDescriptor);
         this.getCompilerContext().popCurrentPrivType();
      } finally {
         this.getCompilerContext().popCurrentPrivType();
      }

   }

   int getPrivType() {
      return 4;
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.resultSet.referencesSessionSchema();
   }

   public ConstantAction makeConstantAction() throws StandardException {
      if (this.targetTableDescriptor == null) {
         return this.getGenericConstantActionFactory().getUpdatableVTIConstantAction(3, this.deferred);
      } else {
         int var1 = this.resultSet.updateTargetLockMode();
         long var2 = this.targetTableDescriptor.getHeapConglomerateId();
         TransactionController var4 = this.getLanguageConnectionContext().getTransactionCompile();
         StaticCompiledOpenConglomInfo[] var5 = new StaticCompiledOpenConglomInfo[this.indexConglomerateNumbers.length];

         for(int var6 = 0; var6 < var5.length; ++var6) {
            var5[var6] = var4.getStaticCompiledConglomInfo(this.indexConglomerateNumbers[var6]);
         }

         if (this.targetTableDescriptor.getLockGranularity() == 'T') {
            var1 = 7;
         }

         ResultDescription var7 = null;
         if (this.isDependentTable) {
            var7 = this.makeResultDescription();
         }

         return this.getGenericConstantActionFactory().getDeleteConstantAction(var2, this.targetTableDescriptor.getTableType(), var4.getStaticCompiledConglomInfo(var2), this.indicesToMaintain, this.indexConglomerateNumbers, var5, this.deferred, false, this.targetTableDescriptor.getUUID(), var1, (Object)null, (Object)null, (int[])null, 0L, (String)null, (String)null, var7, this.getFKInfo(), this.getTriggerInfo(), this.readColsBitSet == null ? (FormatableBitSet)null : new FormatableBitSet(this.readColsBitSet), getReadColMap(this.targetTableDescriptor.getNumberOfColumns(), this.readColsBitSet), this.resultColumnList.getStreamStorableColIds(this.targetTableDescriptor.getNumberOfColumns()), this.readColsBitSet == null ? this.targetTableDescriptor.getNumberOfColumns() : this.readColsBitSet.getNumBitsSet(), (UUID)null, this.resultSet.isOneRowResultSet(), this.dependentConstantActions, this.inMatchingClause());
      }
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCodeForTemporaryTable(var1);
      if (!this.isDependentTable) {
         this.generateParameterValueSet(var1);
      }

      var1.pushGetResultSetFactoryExpression(var2);
      var1.newRowLocationScanResultSetName();
      if (this.inMatchingClause()) {
         this.matchingClause.generateResultSetField(var1, var2);
      } else {
         this.resultSet.generate(var1, var2);
      }

      String var3;
      byte var4;
      if (this.targetTableDescriptor != null) {
         var1.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.CursorResultSet", var1.getRowLocationScanResultSetName());
         if (!this.cascadeDelete && !this.isDependentTable) {
            var3 = "getDeleteResultSet";
            var4 = 1;
         } else {
            var3 = "getDeleteCascadeResultSet";
            var4 = 4;
         }
      } else {
         var4 = 1;
         var3 = "getDeleteVTIResultSet";
      }

      if (this.isDependentTable) {
         var2.push(var1.addItem(this.makeConstantAction()));
      } else if (this.cascadeDelete) {
         var2.push((int)-1);
      }

      String var6 = "org.apache.derby.iapi.sql.ResultSet[]";
      if (this.cascadeDelete) {
         String var10000 = this.targetTableDescriptor.getSchemaName();
         String var5 = var10000 + "." + this.targetTableDescriptor.getName();
         LocalField var7 = var1.newFieldDeclaration(2, var6);
         var2.pushNewArray("org.apache.derby.iapi.sql.ResultSet", this.dependentNodes.length);
         var2.setField(var7);

         for(int var8 = 0; var8 < this.dependentNodes.length; ++var8) {
            this.dependentNodes[var8].setRefActionInfo(this.fkIndexConglomNumbers[var8], this.fkColArrays[var8], var5, true);
            var2.getField(var7);
            if (var2.statementNumHitLimit(10)) {
               MethodBuilder var9 = var1.newGeneratedFun("org.apache.derby.iapi.sql.ResultSet", 2);
               this.dependentNodes[var8].generate(var1, var9);
               var9.methodReturn();
               var9.complete();
               var2.pushThis();
               var2.callMethod((short)182, (String)null, var9.getName(), "org.apache.derby.iapi.sql.ResultSet", 0);
            } else {
               this.dependentNodes[var8].generate(var1, var2);
            }

            var2.setArrayElement(var8);
         }

         var2.getField(var7);
      } else if (this.isDependentTable) {
         var2.pushNull(var6);
      }

      if (this.cascadeDelete || this.isDependentTable) {
         String var13 = this.targetTableDescriptor.getSchemaName();
         String var10 = var13 + "." + this.targetTableDescriptor.getName();
         var2.push(var10);
      }

      var2.callMethod((short)185, (String)null, var3, "org.apache.derby.iapi.sql.ResultSet", var4);
      if (!this.isDependentTable && this.cascadeDelete) {
         int var11 = var1.getRowCount();
         if (var11 > 0) {
            MethodBuilder var12 = var1.getConstructor();
            var12.pushThis();
            var12.pushNewArray("org.apache.derby.iapi.sql.execute.CursorResultSet", var11);
            var12.putField("org.apache.derby.impl.sql.execute.BaseActivation", "raParentResultSets", "org.apache.derby.iapi.sql.execute.CursorResultSet[]");
            var12.endStatement();
         }
      }

   }

   protected final int getStatementType() {
      return 4;
   }

   public FormatableBitSet getReadMap(DataDictionary var1, TableDescriptor var2) throws StandardException {
      boolean[] var3 = new boolean[]{this.requiresDeferredProcessing()};
      ArrayList var4 = new ArrayList();
      this.relevantTriggers = new TriggerDescriptorList();
      FormatableBitSet var5 = getDeleteReadMap(var2, var4, this.relevantTriggers, var3);
      this.markAffectedIndexes(var4);
      this.adjustDeferredFlag(var3[0]);
      return var5;
   }

   private StatementNode getDependentTableNode(String var1, String var2, int var3, ColumnDescriptorList var4) throws StandardException {
      Object var5 = null;
      if (var3 == 0) {
         var5 = this.getEmptyDeleteNode(var1, var2);
      }

      if (var3 == 3) {
         var5 = this.getEmptyUpdateNode(var1, var2, var4);
      }

      if (var5 != null) {
         ((DMLModStatementNode)var5).isDependentTable = true;
         ((DMLModStatementNode)var5).dependentTables = this.dependentTables;
      }

      return (StatementNode)var5;
   }

   private DeleteNode getEmptyDeleteNode(String var1, String var2) throws StandardException {
      Object var3 = null;
      TableName var4 = new TableName(var1, var2, this.getContextManager());
      FromList var5 = new FromList(this.getContextManager());
      FromBaseTable var6 = new FromBaseTable(var4, (String)null, 2, (ResultColumnList)null, this.getContextManager());
      FormatableProperties var7 = new FormatableProperties();
      ((Properties)var7).put("index", "null");
      ((FromBaseTable)var6).setTableProperties(var7);
      var5.addFromTable(var6);
      SelectNode var8 = new SelectNode((ResultColumnList)null, var5, (ValueNode)var3, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      return new DeleteNode(var4, var8, (MatchingClauseNode)null, this.getContextManager());
   }

   private UpdateNode getEmptyUpdateNode(String var1, String var2, ColumnDescriptorList var3) throws StandardException {
      Object var4 = null;
      TableName var5 = new TableName(var1, var2, this.getContextManager());
      FromList var6 = new FromList(this.getContextManager());
      FromBaseTable var7 = new FromBaseTable(var5, (String)null, 2, (ResultColumnList)null, this.getContextManager());
      FormatableProperties var8 = new FormatableProperties();
      ((Properties)var8).put("index", "null");
      ((FromBaseTable)var7).setTableProperties(var8);
      var6.addFromTable(var7);
      SelectNode var9 = new SelectNode(this.getSetClause(var3), var6, (ValueNode)var4, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      return new UpdateNode(var5, var9, (MatchingClauseNode)null, this.getContextManager());
   }

   private ResultColumnList getSetClause(ColumnDescriptorList var1) throws StandardException {
      ResultColumnList var4 = new ResultColumnList(this.getContextManager());
      UntypedNullConstantNode var3 = new UntypedNullConstantNode(this.getContextManager());

      for(int var5 = 0; var5 < var1.size(); ++var5) {
         ColumnDescriptor var6 = var1.elementAt(var5);
         if (var6.getType().isNullable()) {
            ResultColumn var2 = new ResultColumn(var6, var3, this.getContextManager());
            var4.addResultColumn(var2);
         }
      }

      return var4;
   }

   public void optimizeStatement() throws StandardException {
      IgnoreFilter var1 = new IgnoreFilter();
      this.getCompilerContext().addPrivilegeFilter(var1);
      if (this.cascadeDelete) {
         for(int var2 = 0; var2 < this.dependentNodes.length; ++var2) {
            this.dependentNodes[var2].optimizeStatement();
         }
      }

      super.optimizeStatement();
      this.getCompilerContext().removePrivilegeFilter(var1);
   }

   private static FormatableBitSet getDeleteReadMap(TableDescriptor var0, List var1, TriggerDescriptorList var2, boolean[] var3) throws StandardException {
      int var4 = var0.getMaxColumnID();
      FormatableBitSet var5 = new FormatableBitSet(var4 + 1);
      DMLModStatementNode.getXAffectedIndexes(var0, (ResultColumnList)null, var5, var1);
      var0.getAllRelevantTriggers(4, (int[])null, var2);
      if (var2.size() > 0) {
         var3[0] = true;
         boolean var6 = false;

         for(TriggerDescriptor var8 : var2) {
            if (var8.getReferencingNew() || var8.getReferencingOld()) {
               var6 = true;
               break;
            }
         }

         if (var6) {
            for(int var9 = 1; var9 <= var4; ++var9) {
               var5.set(var9);
            }
         }
      }

      return var5;
   }

   private void correlateAddedColumns(ResultColumnList var1, FromTable var2) throws StandardException {
      String var3 = var2.getCorrelationName();
      if (var3 != null) {
         TableName var4 = this.makeTableName((String)null, var3);

         for(ResultColumn var6 : var1) {
            ValueNode var7 = var6.getExpression();
            if (var7 != null && var7 instanceof ColumnReference) {
               ColumnReference var8 = (ColumnReference)var7;
               var8.setQualifiedTableName(var4);
            }
         }

      }
   }
}
