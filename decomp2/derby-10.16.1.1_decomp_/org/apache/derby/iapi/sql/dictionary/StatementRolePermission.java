package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class StatementRolePermission extends StatementPermission {
   private String roleName;
   private int privType;

   public StatementRolePermission(String var1, int var2) {
      this.roleName = var1;
      this.privType = var2;
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      DataDictionary var4 = var1.getDataDictionary();
      TransactionController var5 = var1.getTransactionExecute();
      switch (this.privType) {
         case 19 -> throw StandardException.newException("4251A", new Object[]{"CREATE ROLE"});
         case 20 -> throw StandardException.newException("4251A", new Object[]{"DROP ROLE"});
         default ->       }
   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      return null;
   }

   private String getPrivName() {
      switch (this.privType) {
         case 19 -> {
            return "CREATE_ROLE";
         }
         case 20 -> {
            return "DROP_ROLE";
         }
         default -> {
            return "?";
         }
      }
   }

   public String toString() {
      String var10000 = this.roleName;
      return "StatementRolePermission: " + var10000 + " " + this.getPrivName();
   }
}
