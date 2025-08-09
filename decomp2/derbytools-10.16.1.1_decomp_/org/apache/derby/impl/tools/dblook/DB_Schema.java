package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Schema {
   public static void doSchemas(Connection var0, boolean var1) throws SQLException {
      Statement var2 = var0.createStatement();
      ResultSet var3 = var2.executeQuery("SELECT SCHEMANAME, SCHEMAID FROM SYS.SYSSCHEMAS");
      boolean var4 = true;

      while(var3.next()) {
         String var5 = dblook.addQuotes(dblook.expandDoubleQuotes(var3.getString(1)));
         if (!var1 && !dblook.isIgnorableSchema(var5) && !var5.equals("\"APP\"")) {
            if (var4) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_SchemasHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL("CREATE SCHEMA " + var5);
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var4 = false;
         }
      }

      var3.close();
      var2.close();
   }
}
