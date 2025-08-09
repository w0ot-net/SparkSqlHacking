package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.shared.common.error.StandardException;

public class StatementTablePermission extends StatementPermission {
   UUID tableUUID;
   int privType;

   public StatementTablePermission(UUID var1, int var2) {
      this.tableUUID = var1;
      this.privType = var2;
   }

   public int getPrivType() {
      return this.privType;
   }

   public UUID getTableUUID() {
      return this.tableUUID;
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (!this.getClass().equals(var1.getClass())) {
         return false;
      } else {
         StatementTablePermission var2 = (StatementTablePermission)var1;
         return this.privType == var2.privType && this.tableUUID.equals(var2.tableUUID);
      }
   }

   public int hashCode() {
      return this.privType + this.tableUUID.hashCode();
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      ExecPreparedStatement var4 = var3.getPreparedStatement();
      if (!this.hasPermissionOnTable(var1, var3, var2, var4)) {
         DataDictionary var5 = var1.getDataDictionary();
         TableDescriptor var6 = this.getTableDescriptor(var5);
         throw StandardException.newException(var2 ? "42501" : "42500", new Object[]{var1.getCurrentUserId(var3), this.getPrivName(), var6.getSchemaName(), var6.getName()});
      }
   }

   protected TableDescriptor getTableDescriptor(DataDictionary var1) throws StandardException {
      TableDescriptor var2 = var1.getTableDescriptor(this.tableUUID);
      if (var2 == null) {
         throw StandardException.newException("4250E", new Object[]{"table"});
      } else {
         return var2;
      }
   }

   protected boolean hasPermissionOnTable(LanguageConnectionContext var1, Activation var2, boolean var3, ExecPreparedStatement var4) throws StandardException {
      DataDictionary var5 = var1.getDataDictionary();
      String var6 = var1.getCurrentUserId(var2);
      boolean var7 = this.oneAuthHasPermissionOnTable(var5, "PUBLIC", var3) || this.oneAuthHasPermissionOnTable(var5, var6, var3);
      if (!var7) {
         String var8 = var1.getCurrentRoleId(var2);
         if (var8 != null) {
            String var9 = var5.getAuthorizationDatabaseOwner();
            RoleGrantDescriptor var10 = var5.getRoleGrantDescriptor(var8, var6, var9);
            if (var10 == null) {
               var10 = var5.getRoleGrantDescriptor(var8, "PUBLIC", var9);
            }

            if (var10 == null) {
               var1.setCurrentRole(var2, (String)null);
            } else {
               String var12;
               for(RoleClosureIterator var11 = var5.createRoleClosureIterator(var2.getTransactionController(), var8, true); !var7 && (var12 = var11.next()) != null; var7 = this.oneAuthHasPermissionOnTable(var5, var12, var3)) {
               }

               if (var7) {
                  DependencyManager var13 = var5.getDependencyManager();
                  RoleGrantDescriptor var14 = var5.getRoleDefinitionDescriptor(var8);
                  ContextManager var15 = var1.getContextManager();
                  var13.addDependency(var4, var14, var15);
                  var13.addDependency(var2, var14, var15);
               }
            }
         }
      }

      return var7;
   }

   protected boolean oneAuthHasPermissionOnTable(DataDictionary var1, String var2, boolean var3) throws StandardException {
      TablePermsDescriptor var4 = var1.getTablePermissions(this.tableUUID, var2);
      if (var4 == null) {
         return false;
      } else {
         String var5 = null;
         switch (this.privType) {
            case 0:
            case 8:
               var5 = var4.getSelectPriv();
               break;
            case 1:
               var5 = var4.getUpdatePriv();
               break;
            case 2:
               var5 = var4.getReferencesPriv();
               break;
            case 3:
               var5 = var4.getInsertPriv();
               break;
            case 4:
               var5 = var4.getDeletePriv();
               break;
            case 5:
               var5 = var4.getTriggerPriv();
            case 6:
            case 7:
         }

         return "Y".equals(var5) || !var3 && "y".equals(var5);
      }
   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      return this.oneAuthHasPermissionOnTable(var2, var1, false) ? var2.getTablePermissions(this.tableUUID, var1) : null;
   }

   public String getPrivName() {
      switch (this.privType) {
         case 0:
         case 8:
            return "SELECT";
         case 1:
            return "UPDATE";
         case 2:
            return "REFERENCES";
         case 3:
            return "INSERT";
         case 4:
            return "DELETE";
         case 5:
            return "TRIGGER";
         case 6:
         case 7:
         default:
            return "?";
      }
   }

   public String toString() {
      String var10000 = this.getPrivName();
      return "StatementTablePermission: " + var10000 + " " + this.tableUUID;
   }
}
