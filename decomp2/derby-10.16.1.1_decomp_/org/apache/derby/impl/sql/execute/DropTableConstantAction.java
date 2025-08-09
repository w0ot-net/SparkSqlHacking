package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropTableConstantAction extends DDLSingleTableConstantAction {
   private final long conglomerateNumber;
   private final String fullTableName;
   private final String tableName;
   private final SchemaDescriptor sd;
   private final boolean cascade;

   DropTableConstantAction(String var1, String var2, SchemaDescriptor var3, long var4, UUID var6, int var7) {
      super(var6);
      this.fullTableName = var1;
      this.tableName = var2;
      this.sd = var3;
      this.conglomerateNumber = var4;
      this.cascade = var7 == 0;
   }

   public String toString() {
      return "DROP TABLE " + this.fullTableName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var5 = var1.getLanguageConnectionContext();
      DataDictionary var6 = var5.getDataDictionary();
      DependencyManager var7 = var6.getDependencyManager();
      TransactionController var8 = var5.getTransactionExecute();
      if (this.sd != null && this.sd.getSchemaName().equals("SESSION")) {
         TableDescriptor var2 = var5.getTableDescriptorForDeclaredGlobalTempTable(this.tableName);
         if (var2 == null) {
            var2 = var6.getTableDescriptor(this.tableName, this.sd, var8);
         }

         if (var2 == null) {
            throw StandardException.newException("X0X05.S", new Object[]{this.fullTableName});
         }

         if (var2.getTableType() == 3) {
            var7.invalidateFor(var2, 1, var5);
            var8.dropConglomerate(var2.getHeapConglomerateId());
            var5.dropDeclaredGlobalTempTable(this.tableName);
            return;
         }
      }

      if (this.conglomerateNumber != 0L) {
         this.lockTableForDDL(var8, this.conglomerateNumber, true);
      }

      var6.startWriting(var5);
      TableDescriptor var19 = var6.getTableDescriptor(this.tableId);
      if (var19 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.fullTableName});
      } else {
         long var9 = var19.getHeapConglomerateId();
         this.lockTableForDDL(var8, var9, true);

         for(TriggerDescriptor var12 : var6.getTriggerDescriptors(var19)) {
            var12.drop(var5);
         }

         for(ColumnDescriptor var13 : var19.getColumnDescriptorList()) {
            if (var13.isAutoincrement() && var6.checkVersion(230, (String)null)) {
               dropIdentitySequence(var6, var19, var1);
            }

            if (var13.getDefaultInfo() != null) {
               DefaultDescriptor var14 = var13.getDefaultDescriptor(var6);
               var7.clearDependencies(var5, var14);
            }
         }

         var6.dropAllColumnDescriptors(this.tableId, var8);
         var6.dropAllTableAndColPermDescriptors(this.tableId, var8);
         this.dropAllConstraintDescriptors(var19, var1);
         ConglomerateDescriptor[] var4 = var19.getConglomerateDescriptors();
         long[] var22 = new long[var4.length - 1];
         int var23 = 0;

         for(int var24 = 0; var24 < var4.length; ++var24) {
            ConglomerateDescriptor var15 = var4[var24];
            if (var15.getConglomerateNumber() != var9) {
               long var16 = var15.getConglomerateNumber();

               int var18;
               for(var18 = 0; var18 < var23 && var22[var18] != var16; ++var18) {
               }

               if (var18 == var23) {
                  var22[var23++] = var16;
                  var8.dropConglomerate(var16);
                  var6.dropStatisticsDescriptors(var19.getUUID(), var15.getUUID(), var8);
               }
            }
         }

         var7.invalidateFor(var19, 1, var5);
         this.adjustUDTDependencies(var5, var6, var19, (ColumnInfo[])null, true);
         var6.dropTableDescriptor(var19, this.sd, var8);
         var6.dropAllConglomerateDescriptors(var19, var8);
         var8.dropConglomerate(var9);
      }
   }

   public static void dropIdentitySequence(DataDictionary var0, TableDescriptor var1, Activation var2) throws StandardException {
      DropSequenceConstantAction var3 = new DropSequenceConstantAction(var0.getSystemSchemaDescriptor(), TableDescriptor.makeSequenceName(var1.getUUID()));
      var3.executeConstantAction(var2);
   }

   private void dropAllConstraintDescriptors(TableDescriptor var1, Activation var2) throws StandardException {
      LanguageConnectionContext var7 = var2.getLanguageConnectionContext();
      DataDictionary var8 = var7.getDataDictionary();
      DependencyManager var9 = var8.getDependencyManager();
      TransactionController var10 = var7.getTransactionExecute();
      ConstraintDescriptorList var4 = var8.getConstraintDescriptors(var1);
      int var11 = 0;

      while(var11 < var4.size()) {
         ConstraintDescriptor var3 = var4.elementAt(var11);
         if (var3 instanceof ReferencedKeyConstraintDescriptor) {
            ++var11;
         } else {
            var9.invalidateFor(var3, 19, var7);
            this.dropConstraint(var3, var1, var2, var7, true);
         }
      }

      while(var4.size() > 0) {
         ConstraintDescriptor var12 = var4.elementAt(0);
         this.dropConstraint(var12, var1, var2, var7, false);
         if (this.cascade) {
            ConstraintDescriptorList var6 = var8.getForeignKeys(var12.getUUID());

            for(int var13 = 0; var13 < var6.size(); ++var13) {
               ConstraintDescriptor var5 = var6.elementAt(var13);
               var9.invalidateFor(var5, 19, var7);
               this.dropConstraint(var5, var1, var2, var7, true);
               var2.addWarning(StandardException.newWarning("01500", new Object[]{var5.getConstraintName(), var5.getTableDescriptor().getName()}));
            }
         }

         var9.invalidateFor(var12, 19, var7);
         var9.clearDependencies(var7, var12);
      }

   }
}
