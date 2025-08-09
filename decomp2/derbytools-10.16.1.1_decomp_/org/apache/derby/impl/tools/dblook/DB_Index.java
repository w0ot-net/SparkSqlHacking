package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Index {
   public static void doIndexes(Connection var0) throws SQLException {
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT TABLEID, CONGLOMERATENAME, DESCRIPTOR, SCHEMAID, ISINDEX, ISCONSTRAINT FROM SYS.SYSCONGLOMERATES ORDER BY TABLEID");
      boolean var3 = true;

      while(var2.next()) {
         if (var2.getBoolean(5) && !var2.getBoolean(6)) {
            String var4 = var2.getString(1);
            String var5 = dblook.lookupTableId(var4);
            if (var5 != null && !dblook.isExcludedTable(var5)) {
               String var6 = dblook.lookupSchemaId(var2.getString(4));
               if (!dblook.isIgnorableSchema(var6)) {
                  if (var3) {
                     Logs.reportString("----------------------------------------------");
                     Logs.reportMessage("DBLOOK_IndexesHeader");
                     Logs.reportString("----------------------------------------------\n");
                  }

                  String var7 = dblook.addQuotes(dblook.expandDoubleQuotes(var2.getString(2)));
                  var7 = var6 + "." + var7;
                  StringBuffer var8 = createIndex(var7, var5, var4, var2.getString(3));
                  Logs.writeToNewDDL(var8.toString());
                  Logs.writeStmtEndToNewDDL();
                  Logs.writeNewlineToNewDDL();
                  var3 = false;
               }
            }
         }
      }

      var2.close();
      var1.close();
   }

   private static StringBuffer createIndex(String var0, String var1, String var2, String var3) throws SQLException {
      StringBuffer var4 = new StringBuffer("CREATE ");
      if (var3.indexOf("UNIQUE") != -1) {
         var4.append("UNIQUE ");
      }

      var4.append("INDEX ");
      var4.append(var0);
      var4.append(" ON ");
      var4.append(var1);
      var4.append(" (");
      var4.append(dblook.getColumnListFromDescription(var2, var3));
      var4.append(")");
      return var4;
   }
}
