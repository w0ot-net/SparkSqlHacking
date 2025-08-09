package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.shared.common.error.StandardException;

public class TablePermsDescriptor extends PermissionsDescriptor {
   private UUID tableUUID;
   private String tableName;
   private String selectPriv;
   private String deletePriv;
   private String insertPriv;
   private String updatePriv;
   private String referencesPriv;
   private String triggerPriv;

   public TablePermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4, String var5, String var6, String var7, String var8, String var9, String var10) throws StandardException {
      super(var1, var2, var3);
      this.tableUUID = var4;
      this.selectPriv = var5;
      this.deletePriv = var6;
      this.insertPriv = var7;
      this.updatePriv = var8;
      this.referencesPriv = var9;
      this.triggerPriv = var10;
      if (var4 != null) {
         this.tableName = var1.getTableDescriptor(var4).getName();
      }

   }

   public TablePermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4) throws StandardException {
      this(var1, var2, var3, var4, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null);
   }

   public TablePermsDescriptor(DataDictionary var1, UUID var2) throws StandardException {
      this(var1, (String)null, (String)null, (UUID)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null);
      this.oid = var2;
   }

   public int getCatalogNumber() {
      return 16;
   }

   public UUID getTableUUID() {
      return this.tableUUID;
   }

   public String getSelectPriv() {
      return this.selectPriv;
   }

   public String getDeletePriv() {
      return this.deletePriv;
   }

   public String getInsertPriv() {
      return this.insertPriv;
   }

   public String getUpdatePriv() {
      return this.updatePriv;
   }

   public String getReferencesPriv() {
      return this.referencesPriv;
   }

   public String getTriggerPriv() {
      return this.triggerPriv;
   }

   public String toString() {
      String var10000 = this.getGrantee();
      return "tablePerms: grantee=" + var10000 + ",tablePermsUUID=" + this.getUUID() + ",grantor=" + this.getGrantor() + ",tableUUID=" + this.getTableUUID() + ",selectPriv=" + this.getSelectPriv() + ",deletePriv=" + this.getDeletePriv() + ",insertPriv=" + this.getInsertPriv() + ",updatePriv=" + this.getUpdatePriv() + ",referencesPriv=" + this.getReferencesPriv() + ",triggerPriv=" + this.getTriggerPriv();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof TablePermsDescriptor var2)) {
         return false;
      } else {
         return super.keyEquals(var2) && this.tableUUID.equals(var2.tableUUID);
      }
   }

   public int hashCode() {
      return super.keyHashCode() + this.tableUUID.hashCode();
   }

   public boolean checkOwner(String var1) throws StandardException {
      TableDescriptor var2 = this.getDataDictionary().getTableDescriptor(this.tableUUID);
      return var2.getSchemaDescriptor().getAuthorizationId().equals(var1);
   }

   public String getObjectName() {
      return "Table Privilege on " + this.tableName;
   }

   public String getClassType() {
      return "TablePrivilege";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(462);
   }
}
