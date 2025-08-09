package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public final class StatementRoutinePermission extends StatementPermission {
   private UUID routineUUID;

   public StatementRoutinePermission(UUID var1) {
      this.routineUUID = var1;
   }

   public UUID getRoutineUUID() {
      return this.routineUUID;
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      this.genericCheck(var1, var2, var3, "EXECUTE");
   }

   public boolean isCorrectPermission(PermissionsDescriptor var1) {
      if (var1 != null && var1 instanceof RoutinePermsDescriptor var2) {
         return var2.getHasExecutePermission();
      } else {
         return false;
      }
   }

   public PrivilegedSQLObject getPrivilegedObject(DataDictionary var1) throws StandardException {
      return var1.getAliasDescriptor(this.routineUUID);
   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      return var2.getRoutinePermissions(this.routineUUID, var1);
   }

   public String getObjectType() {
      return "ROUTINE";
   }

   public String toString() {
      return "StatementRoutinePermission: " + this.routineUUID;
   }
}
