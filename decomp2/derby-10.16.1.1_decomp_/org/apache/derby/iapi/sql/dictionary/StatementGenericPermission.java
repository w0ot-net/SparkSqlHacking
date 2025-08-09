package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public final class StatementGenericPermission extends StatementPermission {
   private UUID _objectID;
   private String _objectType;
   private String _privilege;

   public StatementGenericPermission(UUID var1, String var2, String var3) {
      this._objectID = var1;
      this._objectType = var2;
      this._privilege = var3;
   }

   public UUID getObjectID() {
      return this._objectID;
   }

   public String getPrivilege() {
      return this._privilege;
   }

   public String getObjectType() {
      return this._objectType;
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      this.genericCheck(var1, var2, var3, this._privilege);
   }

   public boolean isCorrectPermission(PermissionsDescriptor var1) {
      if (var1 != null && var1 instanceof PermDescriptor var2) {
         return var2.getPermObjectId().equals(this._objectID) && var2.getObjectType().equals(this._objectType) && var2.getPermission().equals(this._privilege);
      } else {
         return false;
      }
   }

   public PrivilegedSQLObject getPrivilegedObject(DataDictionary var1) throws StandardException {
      if ("TYPE".equals(this._objectType)) {
         return var1.getAliasDescriptor(this._objectID);
      } else if ("DERBY AGGREGATE".equals(this._objectType)) {
         return var1.getAliasDescriptor(this._objectID);
      } else if ("SEQUENCE".equals(this._objectType)) {
         return var1.getSequenceDescriptor(this._objectID);
      } else {
         throw StandardException.newException("XSCB3.S", new Object[0]);
      }
   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      return var2.getGenericPermissions(this._objectID, this._objectType, this._privilege, var1);
   }

   public String toString() {
      return "StatementGenericPermission( " + this._objectID + ", " + this._objectType + ", " + this._privilege + " )";
   }
}
