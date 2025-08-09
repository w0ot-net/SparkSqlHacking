package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.derby.tools.dblook;

public class DB_Table {
   private static PreparedStatement getColumnInfoStmt;
   private static PreparedStatement getColumnTypeStmt;
   private static PreparedStatement getAutoIncStmt;

   public static void doTables(Connection var0, HashMap var1) throws SQLException {
      getColumnInfoStmt = var0.prepareStatement("SELECT C.COLUMNNAME, C.REFERENCEID, C.COLUMNNUMBER FROM SYS.SYSCOLUMNS C, SYS.SYSTABLES T WHERE T.TABLEID = ? AND T.TABLEID = C.REFERENCEID ORDER BY C.COLUMNNUMBER");
      getColumnTypeStmt = var0.prepareStatement("SELECT COLUMNDATATYPE, COLUMNDEFAULT FROM SYS.SYSCOLUMNS WHERE REFERENCEID = ? AND COLUMNNAME = ?");
      getAutoIncStmt = var0.prepareStatement("SELECT AUTOINCREMENTSTART, AUTOINCREMENTINC, COLUMNNAME, REFERENCEID, COLUMNDEFAULT FROM SYS.SYSCOLUMNS WHERE COLUMNNAME = ? AND REFERENCEID = ?");
      boolean var2 = true;

      for(Map.Entry var5 : var1.entrySet()) {
         String var6 = (String)var5.getKey();
         String var7 = (String)var5.getValue();
         if (!dblook.isExcludedTable(var7)) {
            if (var2) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_TablesHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL("CREATE TABLE " + var7 + " (");
            boolean var8 = true;
            getColumnInfoStmt.setString(1, var6);

            ResultSet var9;
            for(var9 = getColumnInfoStmt.executeQuery(); var9.next(); var8 = false) {
               String var10 = dblook.addQuotes(var9.getString(1));
               String var11 = createColumn(var10, var9.getString(2), var9.getInt(3));
               if (!var8) {
                  var11 = ", " + var11;
               }

               Logs.writeToNewDDL(var11);
            }

            var9.close();
            Logs.writeToNewDDL(")");
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var2 = false;
         }
      }

      getColumnInfoStmt.close();
      getColumnTypeStmt.close();
      getAutoIncStmt.close();
   }

   private static String createColumn(String var0, String var1, int var2) throws SQLException {
      getColumnTypeStmt.setString(1, var1);
      getColumnTypeStmt.setString(2, dblook.stripQuotes(var0));
      ResultSet var3 = getColumnTypeStmt.executeQuery();
      StringBuffer var4 = new StringBuffer();
      if (var3.next()) {
         var4.append(dblook.addQuotes(dblook.expandDoubleQuotes(dblook.stripQuotes(var0))));
         var4.append(" ");
         var4.append(var3.getString(1));
         if (!reinstateAutoIncrement(var0, var1, var4) && var3.getString(2) != null) {
            String var5 = var3.getString(2);
            if (var5.startsWith("GENERATED ALWAYS AS")) {
               var4.append(" ");
            } else {
               var4.append(" DEFAULT ");
            }

            var4.append(var5);
         }
      }

      var3.close();
      return var4.toString();
   }

   public static boolean reinstateAutoIncrement(String var0, String var1, StringBuffer var2) throws SQLException {
      getAutoIncStmt.setString(1, dblook.stripQuotes(var0));
      getAutoIncStmt.setString(2, var1);
      ResultSet var3 = getAutoIncStmt.executeQuery();
      if (var3.next()) {
         long var4 = var3.getLong(1);
         if (!var3.wasNull()) {
            var2.append(" GENERATED ");
            var2.append(var3.getObject(5) == null ? "ALWAYS " : "BY DEFAULT ");
            var2.append("AS IDENTITY (START WITH ");
            var2.append(var3.getLong(1));
            var2.append(", INCREMENT BY ");
            var2.append(var3.getLong(2));
            var2.append(")");
            return true;
         }
      }

      return false;
   }
}
