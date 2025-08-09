package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Check {
   public static void doChecks(Connection var0) throws SQLException {
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT CS.CONSTRAINTNAME, CS.TABLEID, CS.SCHEMAID, CS.STATE, CK.CHECKDEFINITION FROM SYS.SYSCONSTRAINTS CS, SYS.SYSCHECKS CK WHERE CS.CONSTRAINTID = CK.CONSTRAINTID AND CS.STATE != 'D' ORDER BY CS.TABLEID");
      boolean var3 = true;

      while(var2.next()) {
         String var4 = var2.getString(2);
         String var5 = dblook.lookupTableId(var4);
         if (!dblook.isExcludedTable(var5)) {
            if (var3) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_ChecksHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            StringBuffer var6 = createCheckString(var5, var2);
            DB_Key.makeDeferredClauses(var6, var2, 4);
            Logs.writeToNewDDL(var6.toString());
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var3 = false;
         }
      }

      var1.close();
      var2.close();
   }

   private static StringBuffer createCheckString(String var0, ResultSet var1) throws SQLException {
      StringBuffer var2 = new StringBuffer("ALTER TABLE ");
      var2.append(var0);
      var2.append(" ADD");
      String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var1.getString(1)));
      var2.append(" CONSTRAINT ");
      var2.append(var3);
      var2.append(" CHECK ");
      var2.append(dblook.removeNewlines(var1.getString(5)));
      return var2;
   }
}
