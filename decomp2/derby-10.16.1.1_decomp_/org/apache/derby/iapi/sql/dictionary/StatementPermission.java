package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public abstract class StatementPermission {
   StatementPermission() {
   }

   public abstract void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException;

   public abstract PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException;

   public boolean isCorrectPermission(PermissionsDescriptor var1) throws StandardException {
      return false;
   }

   public PrivilegedSQLObject getPrivilegedObject(DataDictionary var1) throws StandardException {
      return null;
   }

   public String getObjectType() {
      return null;
   }

   public void genericCheck(LanguageConnectionContext var1, boolean var2, Activation var3, String var4) throws StandardException {
      DataDictionary var5 = var1.getDataDictionary();
      TransactionController var6 = var1.getTransactionExecute();
      ExecPreparedStatement var7 = var3.getPreparedStatement();
      PermissionsDescriptor var8 = this.getPermissionDescriptor(var1.getCurrentUserId(var3), var5);
      if (!this.isCorrectPermission(var8)) {
         var8 = this.getPermissionDescriptor("PUBLIC", var5);
      }

      if (!this.isCorrectPermission(var8)) {
         boolean var9 = false;
         String var10 = var1.getCurrentRoleId(var3);
         if (var10 != null) {
            String var11 = var5.getAuthorizationDatabaseOwner();
            RoleGrantDescriptor var12 = var5.getRoleGrantDescriptor(var10, var1.getCurrentUserId(var3), var11);
            if (var12 == null) {
               var12 = var5.getRoleGrantDescriptor(var10, "PUBLIC", var11);
            }

            if (var12 == null) {
               var1.setCurrentRole(var3, (String)null);
            } else {
               RoleClosureIterator var13 = var5.createRoleClosureIterator(var3.getTransactionController(), var10, true);

               String var14;
               while(!var9 && (var14 = var13.next()) != null) {
                  var8 = this.getPermissionDescriptor(var14, var5);
                  if (this.isCorrectPermission(var8)) {
                     var9 = true;
                  }
               }
            }

            if (var9) {
               DependencyManager var19 = var5.getDependencyManager();
               RoleGrantDescriptor var20 = var5.getRoleDefinitionDescriptor(var10);
               ContextManager var15 = var1.getContextManager();
               var19.addDependency(var7, var20, var15);
               var19.addDependency(var3, var20, var15);
            }
         }

         if (!var9) {
            PrivilegedSQLObject var17 = this.getPrivilegedObject(var5);
            if (var17 == null) {
               throw StandardException.newException("4250E", new Object[]{this.getObjectType()});
            } else {
               SchemaDescriptor var18 = var17.getSchemaDescriptor();
               if (var18 == null) {
                  throw StandardException.newException("4250E", new Object[]{"SCHEMA"});
               } else {
                  throw StandardException.newException(var2 ? "42505" : "42504", new Object[]{var1.getCurrentUserId(var3), var4, this.getObjectType(), var18.getSchemaName(), var17.getName()});
               }
            }
         }
      }
   }
}
