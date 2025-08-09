package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RoutinePermsDescriptor extends PermissionsDescriptor {
   private UUID routineUUID;
   private String routineName;
   private boolean hasExecutePermission;

   public RoutinePermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4, boolean var5) throws StandardException {
      super(var1, var2, var3);
      this.routineUUID = var4;
      this.hasExecutePermission = var5;
      if (var4 != null) {
         this.routineName = var1.getAliasDescriptor(var4).getObjectName();
      }

   }

   public RoutinePermsDescriptor(DataDictionary var1, String var2, String var3, UUID var4) throws StandardException {
      this(var1, var2, var3, var4, true);
   }

   public RoutinePermsDescriptor(DataDictionary var1, String var2, String var3) throws StandardException {
      this(var1, var2, var3, (UUID)null);
   }

   public RoutinePermsDescriptor(DataDictionary var1, UUID var2) throws StandardException {
      this(var1, (String)null, (String)null, (UUID)null, true);
      this.oid = var2;
   }

   public int getCatalogNumber() {
      return 18;
   }

   public UUID getRoutineUUID() {
      return this.routineUUID;
   }

   public boolean getHasExecutePermission() {
      return this.hasExecutePermission;
   }

   public String toString() {
      String var10000 = this.getGrantee();
      return "routinePerms: grantee=" + var10000 + ",routinePermsUUID=" + this.getUUID() + ",grantor=" + this.getGrantor() + ",routineUUID=" + this.getRoutineUUID();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof RoutinePermsDescriptor var2)) {
         return false;
      } else {
         return super.keyEquals(var2) && this.routineUUID.equals(var2.routineUUID);
      }
   }

   public int hashCode() {
      return super.keyHashCode() + this.routineUUID.hashCode();
   }

   public boolean checkOwner(String var1) throws StandardException {
      UUID var2 = this.getDataDictionary().getAliasDescriptor(this.routineUUID).getSchemaUUID();
      return this.getDataDictionary().getSchemaDescriptor(var2, (TransactionController)null).getAuthorizationId().equals(var1);
   }

   public String getObjectName() {
      return "Routine Privilege on " + this.routineName;
   }

   public String getClassType() {
      return "RoutinePrivilege";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(461);
   }
}
