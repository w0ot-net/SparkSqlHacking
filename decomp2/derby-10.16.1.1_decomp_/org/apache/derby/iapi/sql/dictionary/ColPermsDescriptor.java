package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.shared.common.error.StandardException;

public class ColPermsDescriptor extends PermissionsDescriptor {
   private UUID tableUUID;
   private String type;
   private FormatableBitSet columns;
   private String tableName;

   public ColPermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4, String var5, FormatableBitSet var6) throws StandardException {
      super(var1, var2, var3);
      this.tableUUID = var4;
      this.type = var5;
      this.columns = var6;
      if (var4 != null) {
         this.tableName = var1.getTableDescriptor(var4).getName();
      }

   }

   public ColPermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4, String var5) throws StandardException {
      this(var1, var2, var3, var4, var5, (FormatableBitSet)null);
   }

   public ColPermsDescriptor(DataDictionary var1, UUID var2) throws StandardException {
      super(var1, (String)null, (String)null);
      this.oid = var2;
   }

   public int getCatalogNumber() {
      return 17;
   }

   public UUID getTableUUID() {
      return this.tableUUID;
   }

   public String getType() {
      return this.type;
   }

   public FormatableBitSet getColumns() {
      return this.columns;
   }

   public String toString() {
      String var10000 = this.getGrantee();
      return "colPerms: grantee=" + var10000 + ",colPermsUUID=" + this.getUUID() + ",grantor=" + this.getGrantor() + ",tableUUID=" + this.getTableUUID() + ",type=" + this.getType() + ",columns=" + this.getColumns();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof ColPermsDescriptor var2)) {
         return false;
      } else {
         boolean var10000;
         label33: {
            if (super.keyEquals(var2) && this.tableUUID.equals(var2.tableUUID)) {
               if (this.type == null) {
                  if (var2.type == null) {
                     break label33;
                  }
               } else if (this.type.equals(var2.type)) {
                  break label33;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      return super.keyHashCode() + this.tableUUID.hashCode() + (this.type == null ? 0 : this.type.hashCode());
   }

   public boolean checkOwner(String var1) throws StandardException {
      TableDescriptor var2 = this.getDataDictionary().getTableDescriptor(this.tableUUID);
      return var2.getSchemaDescriptor().getAuthorizationId().equals(var1);
   }

   public String getObjectName() {
      return "Column Privilege on " + this.tableName;
   }

   public String getClassType() {
      return "ColumnsPrivilege";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(463);
   }
}
