package org.apache.derby.impl.sql.execute;

import java.sql.Timestamp;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class CreateTriggerConstantAction extends DDLSingleTableConstantAction {
   private String triggerName;
   private String triggerSchemaName;
   private TableDescriptor triggerTable;
   private UUID triggerTableId;
   private int eventMask;
   private boolean isBefore;
   private boolean isRow;
   private boolean isEnabled;
   private boolean referencingOld;
   private boolean referencingNew;
   private UUID whenSPSId;
   private String whenText;
   private UUID actionSPSId;
   private String actionText;
   private final String originalWhenText;
   private String originalActionText;
   private String oldReferencingName;
   private String newReferencingName;
   private UUID spsCompSchemaId;
   private int[] referencedCols;
   private int[] referencedColsInTriggerAction;
   private final ProviderInfo[] providerInfo;

   CreateTriggerConstantAction(String var1, String var2, int var3, boolean var4, boolean var5, boolean var6, TableDescriptor var7, UUID var8, String var9, UUID var10, String var11, UUID var12, int[] var13, int[] var14, String var15, String var16, boolean var17, boolean var18, String var19, String var20, ProviderInfo[] var21) {
      super(var7.getUUID());
      this.triggerName = var2;
      this.triggerSchemaName = var1;
      this.triggerTable = var7;
      this.eventMask = var3;
      this.isBefore = var4;
      this.isRow = var5;
      this.isEnabled = var6;
      this.whenSPSId = var8;
      this.whenText = var9;
      this.actionSPSId = var10;
      this.actionText = var11;
      this.spsCompSchemaId = var12;
      this.referencedCols = var13;
      this.referencedColsInTriggerAction = var14;
      this.originalActionText = var16;
      this.originalWhenText = var15;
      this.referencingOld = var17;
      this.referencingNew = var18;
      this.oldReferencingName = var19;
      this.newReferencingName = var20;
      this.providerInfo = var21;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      SPSDescriptor var2 = null;
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      DependencyManager var6 = var5.getDependencyManager();
      TransactionController var7 = var4.getTransactionExecute();
      var5.startWriting(var4);
      SchemaDescriptor var8 = getSchemaDescriptorForCreate(var5, var1, this.triggerSchemaName);
      if (this.spsCompSchemaId == null) {
         SchemaDescriptor var9 = var4.getDefaultSchema();
         if (var9.getUUID() == null) {
            var9 = var5.getSchemaDescriptor(var9.getDescriptorName(), var7, false);
         }

         if (var9 != null) {
            this.spsCompSchemaId = var9.getUUID();
         }
      }

      String var18;
      if (this.triggerTable != null) {
         this.triggerTableId = this.triggerTable.getUUID();
         var18 = this.triggerTable.getName();
      } else {
         var18 = "with UUID " + this.triggerTableId;
      }

      this.triggerTable = var5.getTableDescriptor(this.triggerTableId);
      if (this.triggerTable == null) {
         throw StandardException.newException("X0X05.S", new Object[]{var18});
      } else {
         this.lockTableForDDL(var7, this.triggerTable.getHeapConglomerateId(), true);
         this.triggerTable = var5.getTableDescriptor(this.triggerTableId);
         if (this.triggerTable == null) {
            throw StandardException.newException("X0X05.S", new Object[]{var18});
         } else {
            var6.invalidateFor(this.triggerTable, 28, var4);
            UUID var10 = var5.getUUIDFactory().createUUID();
            this.actionSPSId = this.actionSPSId == null ? var5.getUUIDFactory().createUUID() : this.actionSPSId;
            if (this.whenSPSId == null && this.whenText != null) {
               this.whenSPSId = var5.getUUIDFactory().createUUID();
            }

            DataDescriptorGenerator var11 = var5.getDataDescriptorGenerator();
            TriggerDescriptor var12 = var11.newTriggerDescriptor(var8, var10, this.triggerName, this.eventMask, this.isBefore, this.isRow, this.isEnabled, this.triggerTable, this.whenSPSId, this.actionSPSId, this.makeCreationTimestamp(var5), this.referencedCols, this.referencedColsInTriggerAction, this.originalActionText, this.referencingOld, this.referencingNew, this.oldReferencingName, this.newReferencingName, this.originalWhenText);
            var5.addDescriptor(var12, var8, 13, false, var7);
            if (this.whenText != null) {
               String var13 = "VALUES " + this.whenText;
               var2 = this.createSPS(var4, var11, var5, var7, var10, var8, this.whenSPSId, this.spsCompSchemaId, var13, true, this.triggerTable);
            }

            SPSDescriptor var3 = this.createSPS(var4, var11, var5, var7, var10, var8, this.actionSPSId, this.spsCompSchemaId, this.actionText, false, this.triggerTable);
            if (var2 != null) {
               var6.addDependency(var12, var2, var4.getContextManager());
            }

            var6.addDependency(var12, var3, var4.getContextManager());
            var6.addDependency(var12, this.triggerTable, var4.getContextManager());

            for(ProviderInfo var16 : this.providerInfo) {
               Provider var17 = (Provider)var16.getDependableFinder().getDependable(var5, var16.getObjectId());
               var6.addDependency(var12, var17, var4.getContextManager());
            }

            this.storeViewTriggerDependenciesOnPrivileges(var1, var12);
         }
      }
   }

   private SPSDescriptor createSPS(LanguageConnectionContext var1, DataDescriptorGenerator var2, DataDictionary var3, TransactionController var4, UUID var5, SchemaDescriptor var6, UUID var7, UUID var8, String var9, boolean var10, TableDescriptor var11) throws StandardException {
      if (var9 == null) {
         return null;
      } else {
         String var12 = "TRIGGER" + (var10 ? "WHEN_" : "ACTN_") + var5 + "_" + var11.getUUID().toString();
         SPSDescriptor var13 = new SPSDescriptor(var3, var12, var7 == null ? var3.getUUIDFactory().createUUID() : var7, var6.getUUID(), var8 == null ? var1.getDefaultSchema().getUUID() : var8, 'T', true, var9, true);
         var13.prepareAndRelease(var1, var11);
         var3.addSPSDescriptor(var13, var4);
         return var13;
      }
   }

   public String toString() {
      return this.constructToString("CREATE TRIGGER ", this.triggerName);
   }

   private Timestamp makeCreationTimestamp(DataDictionary var1) throws StandardException {
      Timestamp var2 = new Timestamp(System.currentTimeMillis());
      TriggerDescriptorList var3 = var1.getTriggerDescriptors(this.triggerTable);
      int var4 = var3.size();
      if (var4 == 0) {
         return var2;
      } else {
         Timestamp var5 = ((TriggerDescriptor)var3.get(var4 - 1)).getCreationTimestamp();
         if (var2.after(var5)) {
            return var2;
         } else {
            var2.setTime(var5.getTime() + 1L);
            return var2;
         }
      }
   }
}
