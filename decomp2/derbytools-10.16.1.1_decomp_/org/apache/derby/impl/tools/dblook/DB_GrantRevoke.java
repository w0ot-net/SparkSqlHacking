package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import org.apache.derby.tools.dblook;

public class DB_GrantRevoke {
   public static void doAuthorizations(Connection var0, boolean var1) throws SQLException {
      Statement var2 = var0.createStatement();
      if (var1) {
         ResultSet var3 = var2.executeQuery("SELECT P.GRANTEE, S.SCHEMANAME, A.ALIAS, P.PERMISSION, P.OBJECTTYPE FROM SYS.SYSPERMS P, SYS.SYSALIASES A, SYS.SYSSCHEMAS S WHERE A.SCHEMAID = S.SCHEMAID AND P.OBJECTID = A.ALIASID AND A.ALIASTYPE='A'");
         generateUDTPrivs(var3);
         var3 = var2.executeQuery("SELECT P.GRANTEE, S.SCHEMANAME, SEQ.SEQUENCENAME, P.PERMISSION, P.OBJECTTYPE FROM SYS.SYSPERMS P, SYS.SYSSEQUENCES SEQ, SYS.SYSSCHEMAS S WHERE SEQ.SCHEMAID = S.SCHEMAID AND P.OBJECTID = SEQ.SEQUENCEID");
         generateSequencePrivs(var3);
         var3 = var2.executeQuery("SELECT P.GRANTEE, S.SCHEMANAME, A.ALIAS, P.PERMISSION, P.OBJECTTYPE FROM SYS.SYSPERMS P, SYS.SYSALIASES A, SYS.SYSSCHEMAS S WHERE A.SCHEMAID = S.SCHEMAID AND P.OBJECTID = A.ALIASID AND A.ALIASTYPE='G'");
         generateAggregatePrivs(var3);
      }

      ResultSet var6 = var2.executeQuery("SELECT GRANTEE, SCHEMANAME, TABLENAME, SELECTPRIV, DELETEPRIV, INSERTPRIV, UPDATEPRIV, REFERENCESPRIV, TRIGGERPRIV FROM SYS.SYSTABLEPERMS P, SYS.SYSTABLES T, SYS.SYSSCHEMAS S WHERE T.SCHEMAID = S.SCHEMAID AND T.TABLEID = P.TABLEID");
      generateTablePrivs(var6);
      var6 = var2.executeQuery("SELECT GRANTEE, SCHEMANAME, TABLENAME, TYPE, COLUMNS FROM SYS.SYSCOLPERMS P, SYS.SYSTABLES T, SYS.SYSSCHEMAS S WHERE T.SCHEMAID = S.SCHEMAID AND T.TABLEID = P.TABLEID");
      generateColumnPrivs(var6, var0);
      var6 = var2.executeQuery("SELECT GRANTEE, SCHEMANAME, ALIAS, ALIASTYPE FROM SYS.SYSROUTINEPERMS P, SYS.SYSALIASES A, SYS.SYSSCHEMAS S WHERE A.SCHEMAID = S.SCHEMAID AND P.ALIASID = A.ALIASID");
      generateRoutinePrivs(var6);
      var6.close();
      var2.close();
   }

   private static void generateTablePrivs(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         if (var1) {
            Logs.reportString("----------------------------------------------");
            Logs.reportMessage("DBLOOK_TablePrivHeader");
            Logs.reportString("----------------------------------------------\n");
         }

         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         String var5 = var3 + "." + var4;
         if (!dblook.isIgnorableSchema(var3)) {
            Logs.writeToNewDDL(tablePrivStatement(var0, var5, var2));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var1 = false;
         }
      }

   }

   private static String separatorStr(boolean var0) {
      return var0 ? ", " : "";
   }

   private static String tablePrivStatement(ResultSet var0, String var1, String var2) throws SQLException {
      boolean var3 = false;
      StringBuffer var4 = new StringBuffer("GRANT ");
      if (var0.getString(4).toUpperCase().equals("Y")) {
         var4.append("SELECT");
         var3 = true;
      }

      if (var0.getString(5).toUpperCase().equals("Y")) {
         var4.append(separatorStr(var3) + "DELETE");
         var3 = true;
      }

      if (var0.getString(6).toUpperCase().equals("Y")) {
         var4.append(separatorStr(var3) + "INSERT");
         var3 = true;
      }

      if (var0.getString(7).toUpperCase().equals("Y")) {
         var4.append(separatorStr(var3) + "UPDATE");
         var3 = true;
      }

      if (var0.getString(8).toUpperCase().equals("Y")) {
         var4.append(separatorStr(var3) + "REFERENCES");
         var3 = true;
      }

      if (var0.getString(9).toUpperCase().equals("Y")) {
         var4.append(separatorStr(var3) + "TRIGGER");
         var3 = true;
      }

      var4.append(" ON " + var1 + " TO " + var2);
      return var4.toString();
   }

   private static void generateColumnPrivs(ResultSet var0, Connection var1) throws SQLException {
      PreparedStatement var2 = var1.prepareStatement("SELECT COLUMNNUMBER, COLUMNNAME FROM SYS.SYSCOLUMNS C, SYS.SYSTABLES T, SYS.SYSSCHEMAS S WHERE T.TABLEID = C.REFERENCEID and S.SCHEMAID = T.SCHEMAID AND S.SCHEMANAME = ? AND T.TABLENAME = ? ORDER BY COLUMNNUMBER");
      boolean var3 = true;

      while(var0.next()) {
         if (var3) {
            Logs.reportString("----------------------------------------------");
            Logs.reportMessage("DBLOOK_ColumnPrivHeader");
            Logs.reportString("----------------------------------------------\n");
         }

         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var5 = var0.getString(2);
         String var6 = var0.getString(3);
         String var7 = dblook.addQuotes(dblook.expandDoubleQuotes(var5));
         if (!dblook.isIgnorableSchema(var7)) {
            var2.setString(1, var5);
            var2.setString(2, var6);
            ResultSet var8 = var2.executeQuery();
            String var9 = var7 + "." + dblook.addQuotes(dblook.expandDoubleQuotes(var6));
            Logs.writeToNewDDL(columnPrivStatement(var0, var9, var4, var8));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var3 = false;
            var8.close();
         }
      }

      var2.close();
   }

   private static String privTypeToString(String var0) {
      if (var0.equals("S")) {
         return "SELECT";
      } else if (var0.equals("R")) {
         return "REFERENCES";
      } else {
         return var0.equals("U") ? "UPDATE" : "";
      }
   }

   private static String mapColumnsToNames(String var0, ResultSet var1) throws SQLException {
      StringBuffer var2 = new StringBuffer();
      var1.next();
      int var3 = 1;
      boolean var4 = false;
      StringTokenizer var5 = new StringTokenizer(var0, " ,{}");

      while(var5.hasMoreTokens()) {
         for(int var6 = Integer.parseInt(var5.nextToken()); var6 + 1 > var3; var3 = var1.getInt(1)) {
            var1.next();
         }

         var2.append(separatorStr(var4));
         var4 = true;
         String var7 = dblook.addQuotes(dblook.expandDoubleQuotes(var1.getString(2)));
         var2.append(var7);
      }

      return var2.toString();
   }

   private static String columnPrivStatement(ResultSet var0, String var1, String var2, ResultSet var3) throws SQLException {
      StringBuffer var4 = new StringBuffer("GRANT ");
      String var5 = var0.getString(4).toUpperCase();
      String var6 = var0.getString(5);
      var4.append(privTypeToString(var5));
      var4.append("(");
      var4.append(mapColumnsToNames(var6, var3));
      var4.append(") ON ");
      var4.append(var1);
      var4.append(" TO ");
      var4.append(var2);
      return var4.toString();
   }

   public static void generateUDTPrivs(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         String var5 = var3 + "." + var4;
         String var6 = var0.getString(4);
         String var7 = var0.getString(5);
         if (!dblook.isIgnorableSchema(var3)) {
            if (var1) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_UDTPrivHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL(genericPrivStatement(var5, var2, var6, var7));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var1 = false;
         }
      }

   }

   public static void generateSequencePrivs(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         String var5 = var3 + "." + var4;
         String var6 = var0.getString(4);
         String var7 = var0.getString(5);
         if (!dblook.isIgnorableSchema(var3)) {
            if (var1) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_SequencePrivHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL(genericPrivStatement(var5, var2, var6, var7));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var1 = false;
         }
      }

   }

   public static void generateAggregatePrivs(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         String var5 = var3 + "." + var4;
         String var6 = var0.getString(4);
         String var7 = var0.getString(5);
         if (!dblook.isIgnorableSchema(var3)) {
            if (var1) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_AggregatePrivHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL(genericPrivStatement(var5, var2, var6, var7));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var1 = false;
         }
      }

   }

   private static String genericPrivStatement(String var0, String var1, String var2, String var3) throws SQLException {
      boolean var4 = false;
      StringBuffer var5 = new StringBuffer("GRANT " + var2 + " ON " + var3 + " ");
      var5.append(var0);
      var5.append(" TO ");
      var5.append(var1);
      return var5.toString();
   }

   public static void generateRoutinePrivs(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         String var2 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(1)));
         String var3 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(2)));
         String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var0.getString(3)));
         String var5 = var3 + "." + var4;
         String var6 = var0.getString(4);
         if (!dblook.isIgnorableSchema(var3) && !var3.equals("\"SYSCS_UTIL\"")) {
            if (var1) {
               Logs.reportString("----------------------------------------------");
               Logs.reportMessage("DBLOOK_RoutinePrivHeader");
               Logs.reportString("----------------------------------------------\n");
            }

            Logs.writeToNewDDL(routinePrivStatement(var5, var2, var6));
            Logs.writeStmtEndToNewDDL();
            Logs.writeNewlineToNewDDL();
            var1 = false;
         }
      }

   }

   private static String routinePrivStatement(String var0, String var1, String var2) throws SQLException {
      boolean var3 = false;
      StringBuffer var4 = new StringBuffer("GRANT EXECUTE ON ");
      var4.append(var2.equals("P") ? "PROCEDURE " : "FUNCTION ");
      var4.append(var0);
      var4.append(" TO ");
      var4.append(var1);
      return var4.toString();
   }
}
