package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class CreateViewConstantAction extends DDLConstantAction {
   private final String tableName;
   private final String schemaName;
   private final String viewText;
   private final int tableType;
   private final int checkOption;
   private final ColumnInfo[] columnInfo;
   private final ProviderInfo[] providerInfo;
   private final UUID compSchemaId;

   CreateViewConstantAction(String var1, String var2, int var3, String var4, int var5, ColumnInfo[] var6, ProviderInfo[] var7, UUID var8) {
      this.schemaName = var1;
      this.tableName = var2;
      this.tableType = var3;
      this.viewText = var4;
      this.checkOption = var5;
      this.columnInfo = var6;
      this.providerInfo = var7;
      this.compSchemaId = var8;
   }

   public String toString() {
      return this.constructToString("CREATE VIEW ", this.tableName);
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var6 = var1.getLanguageConnectionContext();
      DataDictionary var7 = var6.getDataDictionary();
      DependencyManager var8 = var7.getDependencyManager();
      TransactionController var9 = var6.getTransactionExecute();
      var7.startWriting(var6);
      SchemaDescriptor var10 = DDLConstantAction.getSchemaDescriptorForCreate(var7, var1, this.schemaName);
      DataDescriptorGenerator var11 = var7.getDataDescriptorGenerator();
      TableDescriptor var2 = var11.newTableDescriptor(this.tableName, var10, this.tableType, 'R');
      var7.addDescriptor(var2, var10, 1, false, var9);
      UUID var3 = var2.getUUID();
      ColumnDescriptor[] var12 = new ColumnDescriptor[this.columnInfo.length];
      int var13 = 1;

      for(int var14 = 0; var14 < this.columnInfo.length; ++var14) {
         ColumnDescriptor var4 = new ColumnDescriptor(this.columnInfo[var14].name, var13++, this.columnInfo[var14].dataType, this.columnInfo[var14].defaultValue, this.columnInfo[var14].defaultInfo, var2, (UUID)null, this.columnInfo[var14].autoincStart, this.columnInfo[var14].autoincInc, this.columnInfo[var14].autoincCycle);
         var12[var14] = var4;
      }

      var7.addDescriptorArray(var12, var2, 2, false, var9);
      ColumnDescriptorList var17 = var2.getColumnDescriptorList();

      for(int var15 = 0; var15 < var12.length; ++var15) {
         var17.add(var12[var15]);
      }

      ViewDescriptor var5 = var11.newViewDescriptor(var3, this.tableName, this.viewText, this.checkOption, this.compSchemaId == null ? var6.getDefaultSchema().getUUID() : this.compSchemaId);

      for(int var18 = 0; var18 < this.providerInfo.length; ++var18) {
         Provider var16 = (Provider)this.providerInfo[var18].getDependableFinder().getDependable(var7, this.providerInfo[var18].getObjectId());
         var8.addDependency(var5, var16, var6.getContextManager());
      }

      this.storeViewTriggerDependenciesOnPrivileges(var1, var5);
      var7.addDescriptor(var5, var10, 8, true, var9);
   }
}
