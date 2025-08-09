package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public final class ViewDescriptor extends UniqueTupleDescriptor implements Dependent, Provider {
   private final int checkOption;
   private String viewName;
   private final String viewText;
   private UUID uuid;
   private final UUID compSchemaId;
   public static final int NO_CHECK_OPTION = 0;

   public ViewDescriptor(DataDictionary var1, UUID var2, String var3, String var4, int var5, UUID var6) {
      super(var1);
      this.uuid = var2;
      this.viewText = var4;
      this.viewName = var3;
      this.checkOption = var5;
      this.compSchemaId = var6;
   }

   public UUID getUUID() {
      return this.uuid;
   }

   public void setUUID(UUID var1) {
      this.uuid = var1;
   }

   public String getViewText() {
      return this.viewText;
   }

   public void setViewName(String var1) {
      this.viewName = var1;
   }

   public int getCheckOptionType() {
      return this.checkOption;
   }

   public UUID getCompSchemaId() {
      return this.compSchemaId;
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(145);
   }

   public String getObjectName() {
      return this.viewName;
   }

   public UUID getObjectID() {
      return this.uuid;
   }

   public String getClassType() {
      return "View";
   }

   public boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      switch (var2) {
         case 2:
         case 3:
         case 12:
         case 15:
         case 20:
         case 22:
         case 23:
         case 27:
         case 28:
         case 29:
         case 33:
         case 37:
         case 39:
         case 40:
         case 41:
         case 42:
         case 44:
         case 47:
         case 48:
            return;
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 13:
         case 14:
         case 16:
         case 17:
         case 18:
         case 19:
         case 21:
         case 24:
         case 25:
         case 26:
         case 30:
         case 31:
         case 32:
         case 34:
         case 35:
         case 36:
         case 38:
         case 43:
         case 45:
         case 46:
         default:
            DependencyManager var4 = this.getDataDictionary().getDependencyManager();
            throw StandardException.newException("X0Y23.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), this.viewName});
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      switch (var1) {
         case 37:
         case 44:
         case 47:
            TableDescriptor var3 = this.getDataDictionary().getTableDescriptor(this.uuid);
            if (var3 != null) {
               this.drop(var2, var3.getSchemaDescriptor(), var3, var1);
               var2.getLastActivation().addWarning(StandardException.newWarning("01501", new Object[]{this.getObjectName()}));
            }
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 45:
         case 46:
         case 48:
         default:
      }
   }

   public String toString() {
      return "";
   }

   public void drop(LanguageConnectionContext var1, SchemaDescriptor var2, TableDescriptor var3) throws StandardException {
      this.drop(var1, var2, var3, 9);
   }

   private void drop(LanguageConnectionContext var1, SchemaDescriptor var2, TableDescriptor var3, int var4) throws StandardException {
      DataDictionary var5 = this.getDataDictionary();
      DependencyManager var6 = var5.getDependencyManager();
      TransactionController var7 = var1.getTransactionExecute();
      var5.dropAllColumnDescriptors(var3.getUUID(), var7);
      var6.invalidateFor(var3, var4, var1);
      var6.clearDependencies(var1, this);
      var5.dropViewDescriptor(this, var7);
      var5.dropAllTableAndColPermDescriptors(var3.getUUID(), var7);
      var5.dropTableDescriptor(var3, var2, var7);
   }

   public String getName() {
      return this.viewName;
   }
}
