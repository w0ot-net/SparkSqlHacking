package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Roles {
   public static void doRoles(Connection var0) throws SQLException {
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT ROLEID, GRANTEE, GRANTOR, WITHADMINOPTION FROM SYS.SYSROLES WHERE ISDEF = 'Y'");
      generateRoleDefinitions(var2);
      var2.close();
      var2 = var1.executeQuery("SELECT ROLEID, GRANTEE, GRANTOR, WITHADMINOPTION FROM SYS.SYSROLES WHERE ISDEF = 'N'");
      generateRoleGrants(var2);
      var2.close();
      var1.close();
   }

   private static void generateRoleDefinitions(ResultSet var0) throws SQLException {
      for(boolean var1 = true; var0.next(); var1 = false) {
         if (var1) {
            Logs.reportString("----------------------------------------------");
            Logs.reportMessage("DBLOOK_Role_definitions_header");
            Logs.reportString("----------------------------------------------\n");
         }

         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         Logs.writeToNewDDL(roleDefinitionStatement(var0, var2));
         Logs.writeStmtEndToNewDDL();
         Logs.writeNewlineToNewDDL();
      }

   }

   private static String roleDefinitionStatement(ResultSet var0, String var1) throws SQLException {
      StringBuffer var2 = new StringBuffer("CREATE ROLE ");
      var2.append(var1);
      return var2.toString();
   }

   private static void generateRoleGrants(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         if (var1) {
            var1 = false;
            Logs.reportString("----------------------------------------------");
            Logs.reportMessage("DBLOOK_Role_grants_header");
            Logs.reportString("----------------------------------------------\n");
         }

         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         boolean var5 = var0.getString(4).equals("Y");
         Logs.writeToNewDDL(roleGrantStatement(var0, var2, var3, var5));
         Logs.writeStmtEndToNewDDL();
         Logs.writeNewlineToNewDDL();
      }

   }

   private static String roleGrantStatement(ResultSet var0, String var1, String var2, boolean var3) throws SQLException {
      StringBuffer var4 = new StringBuffer("GRANT ");
      var4.append(var1);
      var4.append(" TO ");
      var4.append(var2);
      if (var3) {
         var4.append(" WITH ADMIN OPTION");
      }

      return var4.toString();
   }
}
