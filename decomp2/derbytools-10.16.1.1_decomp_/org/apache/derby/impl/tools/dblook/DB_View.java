package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_View {
   public static void doViews(Connection var0) throws SQLException {
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT V.VIEWDEFINITION, T.TABLENAME, T.SCHEMAID, V.COMPILATIONSCHEMAID FROM SYS.SYSVIEWS V, SYS.SYSTABLES T WHERE T.TABLEID = V.TABLEID");
      boolean var3 = true;

      while(var2.next()) {
         String var4 = dblook.lookupSchemaId(var2.getString(3));
         if (!dblook.isIgnorableSchema(var4) && dblook.stringContainsTargetTable(var2.getString(1))) {
            if (var3) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_ViewsHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL("SET SCHEMA ");
            Logs.writeToNewDDL(dblook.lookupSchemaId(var2.getString(4)));
            Logs.writeStmtEndToNewDDL();
            Logs.writeToNewDDL(dblook.removeNewlines(var2.getString(1)));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var3 = false;
         }
      }

      if (!var3) {
         Logs.reportMessage("DBLOOK_DefaultSchema");
         Logs.writeToNewDDL("SET SCHEMA \"APP\"");
         Logs.writeStmtEndToNewDDL();
         Logs.writeNewlineToNewDDL();
      }

      var2.close();
      var1.close();
   }
}
