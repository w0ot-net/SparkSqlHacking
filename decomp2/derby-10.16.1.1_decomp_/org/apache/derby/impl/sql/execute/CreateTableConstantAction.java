package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class CreateTableConstantAction extends DDLConstantAction {
   private char lockGranularity;
   private boolean onCommitDeleteRows;
   private boolean onRollbackDeleteRows;
   private String tableName;
   private String schemaName;
   private int tableType;
   private ColumnInfo[] columnInfo;
   private CreateConstraintConstantAction[] constraintActions;
   private Properties properties;

   CreateTableConstantAction(String var1, String var2, int var3, ColumnInfo[] var4, CreateConstraintConstantAction[] var5, Properties var6, char var7, boolean var8, boolean var9) {
      this.schemaName = var1;
      this.tableName = var2;
      this.tableType = var3;
      this.columnInfo = var4;
      this.constraintActions = var5;
      this.properties = var6;
      this.lockGranularity = var7;
      this.onCommitDeleteRows = var8;
      this.onRollbackDeleteRows = var9;
   }

   public String toString() {
      return this.tableType == 3 ? this.constructToString("DECLARE GLOBAL TEMPORARY TABLE ", this.tableName) : this.constructToString("CREATE TABLE ", this.tableName);
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var7 = var1.getLanguageConnectionContext();
      DataDictionary var8 = var7.getDataDictionary();
      DependencyManager var9 = var8.getDependencyManager();
      TransactionController var10 = var7.getTransactionExecute();
      var1.setForCreateTable();
      ExecRow var6 = RowUtil.getEmptyValueRow(this.columnInfo.length, var7);
      int[] var11 = new int[this.columnInfo.length];

      for(int var12 = 0; var12 < this.columnInfo.length; ++var12) {
         ColumnInfo var13 = this.columnInfo[var12];
         if (var13.defaultValue != null) {
            var6.setColumn(var12 + 1, var13.defaultValue);
         } else {
            var6.setColumn(var12 + 1, var13.dataType.getNull());
         }

         var11[var12] = var13.dataType.getCollationType();
      }

      long var22 = var10.createConglomerate("heap", var6.getRowArray(), (ColumnOrdering[])null, var11, this.properties, this.tableType == 3 ? 3 : 0);
      if (this.tableType != 3) {
         var8.startWriting(var7);
      }

      SchemaDescriptor var14;
      if (this.tableType == 3) {
         var14 = var8.getSchemaDescriptor(this.schemaName, var10, true);
      } else {
         var14 = DDLConstantAction.getSchemaDescriptorForCreate(var8, var1, this.schemaName);
      }

      DataDescriptorGenerator var15 = var8.getDataDescriptorGenerator();
      TableDescriptor var2;
      if (this.tableType != 3) {
         var2 = var15.newTableDescriptor(this.tableName, var14, this.tableType, this.lockGranularity);
         var8.addDescriptor(var2, var14, 1, false, var10);
      } else {
         var2 = var15.newTableDescriptor(this.tableName, var14, this.tableType, this.onCommitDeleteRows, this.onRollbackDeleteRows);
         var2.setUUID(var8.getUUIDFactory().createUUID());
      }

      UUID var3 = var2.getUUID();
      var1.setDDLTableDescriptor(var2);
      int var16 = 1;
      ColumnDescriptor[] var17 = new ColumnDescriptor[this.columnInfo.length];

      for(int var18 = 0; var18 < this.columnInfo.length; ++var18) {
         UUID var19 = this.columnInfo[var18].newDefaultUUID;
         if (this.columnInfo[var18].defaultInfo != null && var19 == null) {
            var19 = var8.getUUIDFactory().createUUID();
         }

         ColumnDescriptor var5;
         if (this.columnInfo[var18].autoincInc != 0L) {
            var5 = new ColumnDescriptor(this.columnInfo[var18].name, var16++, this.columnInfo[var18].dataType, this.columnInfo[var18].defaultValue, this.columnInfo[var18].defaultInfo, var2, var19, this.columnInfo[var18].autoincStart, this.columnInfo[var18].autoincInc, this.columnInfo[var18].autoinc_create_or_modify_Start_Increment, this.columnInfo[var18].autoincCycle);
            if (var8.checkVersion(230, (String)null)) {
               CreateSequenceConstantAction var20 = makeCSCA(this.columnInfo[var18], TableDescriptor.makeSequenceName(var3));
               var20.executeConstantAction(var1);
            }
         } else {
            var5 = new ColumnDescriptor(this.columnInfo[var18].name, var16++, this.columnInfo[var18].dataType, this.columnInfo[var18].defaultValue, this.columnInfo[var18].defaultInfo, var2, var19, this.columnInfo[var18].autoincStart, this.columnInfo[var18].autoincInc, this.columnInfo[var18].autoincCycle);
         }

         var17[var18] = var5;
      }

      if (this.tableType != 3) {
         var8.addDescriptorArray(var17, var2, 2, false, var10);
      }

      ColumnDescriptorList var23 = var2.getColumnDescriptorList();

      for(int var24 = 0; var24 < var17.length; ++var24) {
         var23.add(var17[var24]);
      }

      ConglomerateDescriptor var25 = var15.newConglomerateDescriptor(var22, (String)null, false, (IndexRowGenerator)null, false, (UUID)null, var3, var14.getUUID());
      if (this.tableType != 3) {
         var8.addDescriptor(var25, var14, 0, false, var10);
      }

      ConglomerateDescriptorList var26 = var2.getConglomerateDescriptorList();
      var26.add(var25);
      if (this.constraintActions != null) {
         for(int var21 = 0; var21 < this.constraintActions.length; ++var21) {
            if (!this.constraintActions[var21].isForeignKeyConstraint()) {
               this.constraintActions[var21].executeConstantAction(var1);
            }
         }

         for(int var27 = 0; var27 < this.constraintActions.length; ++var27) {
            if (this.constraintActions[var27].isForeignKeyConstraint()) {
               this.constraintActions[var27].executeConstantAction(var1);
            }
         }
      }

      for(int var28 = 0; var28 < this.columnInfo.length; ++var28) {
         this.addColumnDependencies(var7, var8, var2, this.columnInfo[var28]);
      }

      this.adjustUDTDependencies(var7, var8, var2, this.columnInfo, false);
      if (this.tableType == 3) {
         var7.addDeclaredGlobalTempTable(var2);
      }

      var8.getDependencyManager().addDependency(var1.getPreparedStatement(), var2, var7.getContextManager());
   }

   public static CreateSequenceConstantAction makeCSCA(ColumnInfo var0, String var1) throws StandardException {
      DataTypeDescriptor var2 = var0.dataType;
      long[] var3 = var2.getNumericBounds();
      long var4 = var3[0];
      long var6 = var3[1];
      boolean var8 = false;
      if (var0.autoincCycle) {
         var8 = true;
      }

      return new CreateSequenceConstantAction("SYS", var1, var2, var0.autoincStart, var0.autoincInc, var6, var4, var8);
   }
}
