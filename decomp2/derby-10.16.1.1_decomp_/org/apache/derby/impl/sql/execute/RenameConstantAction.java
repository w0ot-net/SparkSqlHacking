package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class RenameConstantAction extends DDLSingleTableConstantAction {
   private String fullTableName;
   private String tableName;
   private String newTableName;
   private String oldObjectName;
   private String newObjectName;
   private UUID schemaId;
   private SchemaDescriptor sd;
   private boolean usedAlterTable;
   private int renamingWhat;

   public RenameConstantAction(String var1, String var2, String var3, String var4, SchemaDescriptor var5, UUID var6, boolean var7, int var8) {
      super(var6);
      this.fullTableName = var1;
      this.tableName = var2;
      this.sd = var5;
      this.usedAlterTable = var7;
      this.renamingWhat = var8;
      switch (this.renamingWhat) {
         case 1:
            this.newTableName = var4;
            this.oldObjectName = null;
            this.newObjectName = var4;
            break;
         case 2:
         case 3:
            this.oldObjectName = var3;
            this.newObjectName = var4;
      }

   }

   public String toString() {
      String var1;
      if (this.usedAlterTable) {
         var1 = "ALTER TABLE ";
      } else {
         var1 = "RENAME ";
      }

      switch (this.renamingWhat) {
         case 1:
            if (this.usedAlterTable) {
               var1 = var1 + this.fullTableName + " RENAME TO " + this.newTableName;
            } else {
               var1 = var1 + " TABLE " + this.fullTableName + " TO " + this.newTableName;
            }
            break;
         case 2:
            if (this.usedAlterTable) {
               var1 = var1 + this.fullTableName + " RENAME " + this.oldObjectName + " TO " + this.newObjectName;
            } else {
               var1 = var1 + " COLUMN " + this.fullTableName + "." + this.oldObjectName + " TO " + this.newObjectName;
            }
            break;
         case 3:
            var1 = var1 + " INDEX " + this.oldObjectName + " TO " + this.newObjectName;
      }

      return var1;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      DependencyManager var6 = var5.getDependencyManager();
      TransactionController var7 = var4.getTransactionExecute();
      var5.startWriting(var4);
      TableDescriptor var2 = var5.getTableDescriptor(this.tableId);
      if (var2 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.fullTableName});
      } else {
         if (this.sd == null) {
            this.sd = getAndCheckSchemaDescriptor(var5, this.schemaId, "RENAME TABLE");
         }

         long var8 = var2.getHeapConglomerateId();
         this.lockTableForDDL(var7, var8, true);
         var2 = var5.getTableDescriptor(this.tableId);
         if (var2 == null) {
            throw StandardException.newException("X0X05.S", new Object[]{this.fullTableName});
         } else {
            switch (this.renamingWhat) {
               case 1 -> this.execGutsRenameTable(var2, var1);
               case 2 -> this.execGutsRenameColumn(var2, var1);
               case 3 -> this.execGutsRenameIndex(var2, var1);
            }

         }
      }
   }

   private void execGutsRenameTable(TableDescriptor var1, Activation var2) throws StandardException {
      LanguageConnectionContext var5 = var2.getLanguageConnectionContext();
      DataDictionary var6 = var5.getDataDictionary();
      DependencyManager var7 = var6.getDependencyManager();
      TransactionController var8 = var5.getTransactionExecute();
      var7.invalidateFor(var1, 34, var5);
      ConstraintDescriptorList var3 = var6.getConstraintDescriptors(var1);

      for(int var9 = 0; var9 < var3.size(); ++var9) {
         ConstraintDescriptor var4 = var3.elementAt(var9);
         if (var4 instanceof ReferencedKeyConstraintDescriptor) {
            var7.invalidateFor(var4, 34, var5);
         }
      }

      var6.dropTableDescriptor(var1, this.sd, var8);
      var1.setTableName(this.newTableName);
      var6.addDescriptor(var1, this.sd, 1, false, var8);
   }

   private void execGutsRenameColumn(TableDescriptor var1, Activation var2) throws StandardException {
      ColumnDescriptor var3 = null;
      int var4 = 0;
      LanguageConnectionContext var7 = var2.getLanguageConnectionContext();
      DataDictionary var8 = var7.getDataDictionary();
      DependencyManager var9 = var8.getDependencyManager();
      TransactionController var10 = var7.getTransactionExecute();
      var3 = var1.getColumnDescriptor(this.oldObjectName);
      if (var3.isAutoincrement()) {
         var3.setAutoinc_create_or_modify_Start_Increment(0);
      }

      var4 = var3.getPosition();
      FormatableBitSet var11 = new FormatableBitSet(var1.getColumnDescriptorList().size() + 1);
      var11.set(var4);
      var1.setReferencedColumnMap(var11);
      var9.invalidateFor(var1, 34, var7);
      ConstraintDescriptorList var5 = var8.getConstraintDescriptors(var1);

      for(int var12 = 0; var12 < var5.size(); ++var12) {
         ConstraintDescriptor var6 = var5.elementAt(var12);
         int[] var13 = var6.getReferencedColumns();
         int var14 = var13.length;

         for(int var15 = 0; var15 < var14; ++var15) {
            if (var13[var15] == var4 && var6 instanceof ReferencedKeyConstraintDescriptor) {
               var9.invalidateFor(var6, 34, var7);
            }
         }
      }

      var8.dropColumnDescriptor(var1.getUUID(), this.oldObjectName, var10);
      var3.setColumnName(this.newObjectName);
      var8.addDescriptor(var3, var1, 2, false, var10);
      var8.getTableDescriptor(var1.getObjectID());
   }

   private void execGutsRenameIndex(TableDescriptor var1, Activation var2) throws StandardException {
      LanguageConnectionContext var3 = var2.getLanguageConnectionContext();
      DataDictionary var4 = var3.getDataDictionary();
      DependencyManager var5 = var4.getDependencyManager();
      TransactionController var6 = var3.getTransactionExecute();
      var5.invalidateFor(var1, 41, var3);
      ConglomerateDescriptor var7 = var4.getConglomerateDescriptor(this.oldObjectName, this.sd, true);
      if (var7 == null) {
         throw StandardException.newException("X0X99.S", new Object[]{this.oldObjectName});
      } else {
         var4.dropConglomerateDescriptor(var7, var6);
         var7.setConglomerateName(this.newObjectName);
         var4.addDescriptor(var7, this.sd, 0, false, var6);
      }
   }

   public String getTableName() {
      return this.tableName;
   }
}
