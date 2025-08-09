package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.shared.common.error.StandardException;

public class StatementColumnPermission extends StatementTablePermission {
   private FormatableBitSet columns;

   public StatementColumnPermission(UUID var1, int var2, FormatableBitSet var3) {
      super(var1, var2);
      this.columns = var3;
   }

   public FormatableBitSet getColumns() {
      return this.columns;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof StatementColumnPermission var2) {
         return !this.columns.equals(var2.columns) ? false : super.equals(var1);
      } else {
         return false;
      }
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      DataDictionary var4 = var1.getDataDictionary();
      ExecPreparedStatement var5 = var3.getPreparedStatement();
      if (!this.hasPermissionOnTable(var1, var3, var2, var5)) {
         String var6 = var1.getCurrentUserId(var3);
         FormatableBitSet var7 = null;
         if (!var2) {
            var7 = this.addPermittedColumns(var4, false, "PUBLIC", var7);
            var7 = this.addPermittedColumns(var4, false, var6, var7);
         }

         var7 = this.addPermittedColumns(var4, true, "PUBLIC", var7);
         var7 = this.addPermittedColumns(var4, true, var6, var7);
         if (this.privType != 8 || var7 == null) {
            FormatableBitSet var8 = (FormatableBitSet)this.columns.clone();

            for(int var9 = var8.anySetBit(); var9 >= 0; var9 = var8.anySetBit(var9)) {
               if (var7 != null && var7.get(var9)) {
                  var8.clear(var9);
               }
            }

            if (var8.anySetBit() >= 0) {
               String var21 = var1.getCurrentRoleId(var3);
               Object var10 = null;
               if (var21 != null) {
                  String var11 = var4.getAuthorizationDatabaseOwner();
                  RoleGrantDescriptor var22 = var4.getRoleGrantDescriptor(var21, var6, var11);
                  if (var22 == null) {
                     var22 = var4.getRoleGrantDescriptor(var21, "PUBLIC", var11);
                  }

                  if (var22 == null) {
                     var1.setCurrentRole(var3, (String)null);
                  } else {
                     RoleClosureIterator var12 = var4.createRoleClosureIterator(var3.getTransactionController(), var21, true);

                     String var13;
                     while(var8.anySetBit() >= 0 && (var13 = var12.next()) != null) {
                        var7 = this.tryRole(var1, var4, var2, var13);
                        if (this.privType == 8 && var7 != null) {
                           DependencyManager var27 = var4.getDependencyManager();
                           RoleGrantDescriptor var15 = var4.getRoleDefinitionDescriptor(var21);
                           ContextManager var16 = var1.getContextManager();
                           var27.addDependency(var5, var15, var16);
                           var27.addDependency(var3, var15, var16);
                           return;
                        }

                        for(int var14 = var8.anySetBit(); var14 >= 0; var14 = var8.anySetBit(var14)) {
                           if (var7 != null && var7.get(var14)) {
                              var8.clear(var14);
                           }
                        }
                     }
                  }
               }

               TableDescriptor var23 = this.getTableDescriptor(var4);
               if (this.privType == 8) {
                  throw StandardException.newException(var2 ? "42501" : "42500", new Object[]{var6, this.getPrivName(), var23.getSchemaName(), var23.getName()});
               } else {
                  int var24 = var8.anySetBit();
                  if (var24 >= 0) {
                     ColumnDescriptor var26 = var23.getColumnDescriptor(var24 + 1);
                     if (var26 == null) {
                        throw StandardException.newException("4250E", new Object[]{"column"});
                     } else {
                        throw StandardException.newException(var2 ? "42503" : "42502", new Object[]{var6, this.getPrivName(), var26.getColumnName(), var23.getSchemaName(), var23.getName()});
                     }
                  } else {
                     DependencyManager var25 = var4.getDependencyManager();
                     RoleGrantDescriptor var28 = var4.getRoleDefinitionDescriptor(var21);
                     ContextManager var29 = var1.getContextManager();
                     var25.addDependency(var5, var28, var29);
                     var25.addDependency(var3, var28, var29);
                  }
               }
            }
         }
      }
   }

   private FormatableBitSet addPermittedColumns(DataDictionary var1, boolean var2, String var3, FormatableBitSet var4) throws StandardException {
      if (var4 != null && var4.getNumBitsSet() == var4.size()) {
         return var4;
      } else {
         ColPermsDescriptor var5 = var1.getColumnPermissions(this.tableUUID, this.privType, false, var3);
         if (var5 != null) {
            if (var4 == null) {
               return var5.getColumns();
            }

            var4.or(var5.getColumns());
         }

         return var4;
      }
   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      if (this.oneAuthHasPermissionOnTable(var2, var1, false)) {
         return var2.getTablePermissions(this.tableUUID, var1);
      } else if (this.oneAuthHasPermissionOnTable(var2, "PUBLIC", false)) {
         return var2.getTablePermissions(this.tableUUID, "PUBLIC");
      } else {
         ColPermsDescriptor var3 = var2.getColumnPermissions(this.tableUUID, this.privType, false, var1);
         if (var3 != null && var3.getColumns() != null) {
            FormatableBitSet var4 = var3.getColumns();

            for(int var5 = this.columns.anySetBit(); var5 >= 0; var5 = this.columns.anySetBit(var5)) {
               if (var4.get(var5)) {
                  return var3;
               }
            }
         }

         return null;
      }
   }

   public PermissionsDescriptor getPUBLIClevelColPermsDescriptor(String var1, DataDictionary var2) throws StandardException {
      ColPermsDescriptor var3 = var2.getColumnPermissions(this.tableUUID, this.privType, false, var1);
      FormatableBitSet var4 = var3.getColumns();
      boolean var5 = true;

      for(int var6 = this.columns.anySetBit(); var6 >= 0 && var5; var6 = this.columns.anySetBit(var6)) {
         if (!var4.get(var6)) {
            var5 = false;
         }
      }

      return var5 ? null : var2.getColumnPermissions(this.tableUUID, this.privType, false, "PUBLIC");
   }

   public boolean allColumnsCoveredByUserOrPUBLIC(String var1, DataDictionary var2) throws StandardException {
      ColPermsDescriptor var3 = var2.getColumnPermissions(this.tableUUID, this.privType, false, var1);
      FormatableBitSet var4 = var3.getColumns();
      FormatableBitSet var5 = (FormatableBitSet)this.columns.clone();
      boolean var6 = true;
      if (var4 != null) {
         for(int var7 = var5.anySetBit(); var7 >= 0; var7 = var5.anySetBit(var7)) {
            if (var4.get(var7)) {
               var5.clear(var7);
            }
         }
      }

      if (var5.anySetBit() >= 0) {
         var3 = var2.getColumnPermissions(this.tableUUID, this.privType, false, "PUBLIC");
         var4 = var3.getColumns();
         if (var4 != null) {
            for(int var10 = var5.anySetBit(); var10 >= 0; var10 = var5.anySetBit(var10)) {
               if (var4.get(var10)) {
                  var5.clear(var10);
               }
            }
         }

         if (var5.anySetBit() >= 0) {
            var6 = false;
         }
      }

      return var6;
   }

   private FormatableBitSet tryRole(LanguageConnectionContext var1, DataDictionary var2, boolean var3, String var4) throws StandardException {
      FormatableBitSet var5 = null;
      if (!var3) {
         var5 = this.addPermittedColumns(var2, false, var4, (FormatableBitSet)null);
      }

      var5 = this.addPermittedColumns(var2, true, var4, var5);
      return var5;
   }

   public String toString() {
      String var10000 = this.getPrivName();
      return "StatementColumnPermission: " + var10000 + " " + this.tableUUID + " columns: " + this.columns;
   }
}
