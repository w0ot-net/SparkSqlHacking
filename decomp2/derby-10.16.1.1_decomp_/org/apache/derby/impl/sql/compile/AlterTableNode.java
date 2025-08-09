package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.impl.sql.execute.ColumnInfo;
import org.apache.derby.impl.sql.execute.ConstraintConstantAction;
import org.apache.derby.impl.sql.execute.CreateConstraintConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class AlterTableNode extends DDLStatementNode {
   public TableElementList tableElementList = null;
   char lockGranularity;
   private boolean updateStatistics = false;
   private boolean updateStatisticsAll = false;
   private boolean dropStatistics;
   private boolean dropStatisticsAll;
   private String indexNameForStatistics;
   public boolean compressTable = false;
   public boolean sequential = false;
   public boolean purge = false;
   public boolean defragment = false;
   public boolean truncateEndOfTable = false;
   public int behavior;
   public TableDescriptor baseTable;
   protected int numConstraints;
   private int changeType = 0;
   private boolean truncateTable = false;
   protected SchemaDescriptor schemaDescriptor = null;
   protected ColumnInfo[] colInfos = null;
   protected ConstraintConstantAction[] conActions = null;

   AlterTableNode(TableName var1, ContextManager var2) throws StandardException {
      super(var1, var2);
      this.truncateTable = true;
      this.schemaDescriptor = this.getSchemaDescriptor();
   }

   AlterTableNode(TableName var1, boolean var2, ContextManager var3) throws StandardException {
      super(var1, var3);
      this.sequential = var2;
      this.compressTable = true;
      this.schemaDescriptor = this.getSchemaDescriptor();
   }

   AlterTableNode(TableName var1, boolean var2, boolean var3, boolean var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.purge = var2;
      this.defragment = var3;
      this.truncateEndOfTable = var4;
      this.compressTable = true;
      this.schemaDescriptor = this.getSchemaDescriptor(true, false);
   }

   AlterTableNode(TableName var1, int var2, boolean var3, String var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.changeType = var2;
      this.indexNameForStatistics = var4;
      switch (var2) {
         case 5:
            this.updateStatisticsAll = var3;
            this.updateStatistics = true;
            break;
         case 6:
            this.dropStatisticsAll = var3;
            this.dropStatistics = true;
      }

      this.schemaDescriptor = this.getSchemaDescriptor();
   }

   AlterTableNode(TableName var1, int var2, TableElementList var3, char var4, int var5, ContextManager var6) throws StandardException {
      super(var1, var6);
      this.changeType = var2;
      switch (var2) {
         case 1:
         case 2:
         case 3:
         case 4:
            this.tableElementList = var3;
            this.lockGranularity = var4;
            this.behavior = var5;
         default:
            this.schemaDescriptor = this.getSchemaDescriptor();
      }
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   public String statementToString() {
      return this.truncateTable ? "TRUNCATE TABLE" : "ALTER TABLE";
   }

   public int getChangeType() {
      return this.changeType;
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      if (!this.compressTable || !this.purge && !this.defragment && !this.truncateEndOfTable) {
         this.baseTable = this.getTableDescriptor();
      } else {
         this.baseTable = this.getTableDescriptor(false);
      }

      if (this.baseTable.getTableType() == 3) {
         throw StandardException.newException("42995", new Object[0]);
      } else {
         this.getCompilerContext().createDependency(this.baseTable);
         if (this.changeType == 1 && this.tableElementList != null) {
            for(int var6 = 0; var6 < this.tableElementList.size(); ++var6) {
               if (this.tableElementList.elementAt(var6) instanceof ColumnDefinitionNode) {
                  ColumnDefinitionNode var7 = (ColumnDefinitionNode)this.tableElementList.elementAt(var6);
                  if (!var7.hasGenerationClause() || var7.getType() != null) {
                     if (var7.getType() == null) {
                        throw StandardException.newException("42XA9", new Object[]{var7.getColumnName()});
                     }

                     if (var7.getType().getTypeId().isStringTypeId()) {
                        var7.setCollationType(this.schemaDescriptor.getCollationType());
                     }
                  }
               }
            }
         }

         if (this.tableElementList != null) {
            this.tableElementList.validate(this, var1, this.baseTable);
            if (this.tableElementList.countNumberOfColumns() + this.baseTable.getNumberOfColumns() > 1012) {
               throw StandardException.newException("54011", new Object[]{String.valueOf(this.tableElementList.countNumberOfColumns() + this.baseTable.getNumberOfColumns()), this.getRelativeName(), String.valueOf(1012)});
            }

            var5 = this.tableElementList.countConstraints(2) + this.tableElementList.countConstraints(6) + this.tableElementList.countConstraints(3);
            var2 = this.tableElementList.countConstraints(4);
            var3 = this.tableElementList.countConstraints(6);
            var4 = this.tableElementList.countGenerationClauses();
         }

         if (var5 + this.baseTable.getTotalNumberOfIndexes() > 32767) {
            throw StandardException.newException("42Z9F", new Object[]{String.valueOf(var5 + this.baseTable.getTotalNumberOfIndexes()), this.getRelativeName(), String.valueOf(32767)});
         } else {
            if (var2 > 0 || var4 > 0 || var3 > 0) {
               FromList var8 = this.makeFromList(var1, this.tableElementList, false);
               FormatableBitSet var10 = this.baseTable.makeColumnMap(this.baseTable.getGeneratedColumns());
               if (var4 > 0) {
                  this.tableElementList.bindAndValidateGenerationClauses(this.schemaDescriptor, var8, var10, this.baseTable);
               }

               if (var2 > 0) {
                  this.tableElementList.bindAndValidateCheckConstraints(var8);
               }

               if (var3 > 0) {
                  this.tableElementList.validateForeignKeysOnGenerationClauses(var8, var10);
               }
            }

            if (this.tableElementList != null) {
               this.tableElementList.validatePrimaryKeyNullability();
            }

            if (this.updateStatistics && !this.updateStatisticsAll || this.dropStatistics && !this.dropStatisticsAll) {
               ConglomerateDescriptor var9 = null;
               if (this.schemaDescriptor.getUUID() != null) {
                  var9 = var1.getConglomerateDescriptor(this.indexNameForStatistics, this.schemaDescriptor, false);
               }

               if (var9 == null) {
                  Object[] var10001 = new Object[1];
                  String var10004 = this.schemaDescriptor.getSchemaName();
                  var10001[0] = var10004 + "." + this.indexNameForStatistics;
                  throw StandardException.newException("42X65", var10001);
               }
            }

            this.getCompilerContext().createDependency(this.baseTable);
         }
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return isSessionSchema(this.baseTable.getSchemaName());
   }

   public ConstantAction makeConstantAction() throws StandardException {
      this.prepConstantAction();
      return this.getGenericConstantActionFactory().getAlterTableConstantAction(this.schemaDescriptor, this.getRelativeName(), this.baseTable.getUUID(), this.baseTable.getHeapConglomerateId(), 0, this.colInfos, this.conActions, this.lockGranularity, this.compressTable, this.behavior, this.sequential, this.truncateTable, this.purge, this.defragment, this.truncateEndOfTable, this.updateStatistics, this.updateStatisticsAll, this.dropStatistics, this.dropStatisticsAll, this.indexNameForStatistics);
   }

   private void prepConstantAction() throws StandardException {
      if (this.tableElementList != null) {
         this.genColumnInfo();
      }

      if (this.numConstraints > 0) {
         this.conActions = new ConstraintConstantAction[this.numConstraints];
         this.tableElementList.genConstraintActions(false, this.conActions, this.getRelativeName(), this.schemaDescriptor, this.getDataDictionary());

         for(int var1 = 0; var1 < this.conActions.length; ++var1) {
            ConstraintConstantAction var2 = this.conActions[var1];
            if (var2 instanceof CreateConstraintConstantAction) {
               int var3 = var2.getConstraintType();
               if (var3 == 2) {
                  DataDictionary var4 = this.getDataDictionary();
                  ConstraintDescriptorList var5 = var4.getConstraintDescriptors(this.baseTable);
                  if (var5.getPrimaryKey() != null) {
                     throw StandardException.newException("X0Y58.S", new Object[]{this.baseTable.getQualifiedName()});
                  }
               }
            }
         }
      }

   }

   public void genColumnInfo() throws StandardException {
      this.colInfos = new ColumnInfo[this.tableElementList.countNumberOfColumns()];
      this.numConstraints = this.tableElementList.genColumnInfos(this.colInfos);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableElementList != null) {
         this.tableElementList.accept(var1);
      }

   }
}
