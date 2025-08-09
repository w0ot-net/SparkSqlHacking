package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class StatementSchemaPermission extends StatementPermission {
   private String schemaName;
   private String aid;
   private int privType;

   public StatementSchemaPermission(String var1, String var2, int var3) {
      this.schemaName = var1;
      this.aid = var2;
      this.privType = var3;
   }

   public void check(LanguageConnectionContext var1, boolean var2, Activation var3) throws StandardException {
      DataDictionary var4 = var1.getDataDictionary();
      TransactionController var5 = var1.getTransactionExecute();
      String var6 = var1.getCurrentUserId(var3);
      switch (this.privType) {
         case 16:
            if (!this.schemaName.equals(var6) || this.aid != null && !this.aid.equals(var6)) {
               throw StandardException.newException("42508", new Object[]{var6, this.schemaName});
            }
            break;
         case 17:
         case 18:
            SchemaDescriptor var7 = var4.getSchemaDescriptor(this.schemaName, var5, false);
            if (var7 == null) {
               return;
            }

            if (!var6.equals(var7.getAuthorizationId())) {
               throw StandardException.newException("42507", new Object[]{var6, this.schemaName});
            }
      }

   }

   public PermissionsDescriptor getPermissionDescriptor(String var1, DataDictionary var2) throws StandardException {
      return null;
   }

   private String getPrivName() {
      switch (this.privType) {
         case 16 -> {
            return "CREATE_SCHEMA";
         }
         case 17 -> {
            return "MODIFY_SCHEMA";
         }
         case 18 -> {
            return "DROP_SCHEMA";
         }
         default -> {
            return "?";
         }
      }
   }

   public String toString() {
      String var10000 = this.schemaName;
      return "StatementSchemaPermission: " + var10000 + " owner:" + this.aid + " " + this.getPrivName();
   }
}
