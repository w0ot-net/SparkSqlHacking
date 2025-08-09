package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Alias {
   private static final char AGGREGATE_TYPE = 'G';
   private static final char UDT_TYPE = 'A';
   private static final char PROCEDURE_TYPE = 'P';
   private static final char FUNCTION_TYPE = 'F';

   public static void doPFAU(Connection var0, boolean var1) throws SQLException {
      PreparedStatement var2 = var0.prepareStatement("SELECT ALIAS, ALIASINFO, ALIASID, SCHEMAID, JAVACLASSNAME, SYSTEMALIAS FROM SYS.SYSALIASES WHERE ALIASTYPE=?");
      if (var1) {
         generateDDL(var2, 'A');
      }

      generateDDL(var2, 'P');
      generateDDL(var2, 'F');
      generateDDL(var2, 'G');
      var2.close();
   }

   private static void generateDDL(PreparedStatement var0, char var1) throws SQLException {
      var0.setString(1, new String(new char[]{var1}));
      ResultSet var2 = var0.executeQuery();
      generateDDL(var2, var1);
      var2.close();
   }

   private static void generateDDL(ResultSet var0, char var1) throws SQLException {
      boolean var2 = true;

      while(var0.next()) {
         if (!var0.getBoolean(6)) {
            String var3 = dblook.lookupSchemaId(var0.getString(4));
            if (!dblook.isIgnorableSchema(var3)) {
               if (var2) {
                  Logs.reportString("----------------------------------------------");
                  switch (var1) {
                     case 'A' -> Logs.reportMessage("DBLOOK_UDTHeader");
                     case 'F' -> Logs.reportMessage("DBLOOK_FunctionHeader");
                     case 'G' -> Logs.reportMessage("DBLOOK_AggregateHeader");
                     case 'P' -> Logs.reportMessage("DBLOOK_StoredProcHeader");
                  }

                  Logs.reportString("----------------------------------------------\n");
               }

               String var4 = var0.getString(1);
               String var5 = dblook.addQuotes(dblook.expandDoubleQuotes(var4));
               var5 = var3 + "." + var5;
               String var6 = createPFAUString(var5, var0, var1);
               Logs.writeToNewDDL(var6);
               Logs.writeStmtEndToNewDDL();
               Logs.writeNewlineToNewDDL();
               var2 = false;
            }
         }
      }

   }

   private static String createPFAUString(String var0, ResultSet var1, char var2) throws SQLException {
      StringBuffer var3 = new StringBuffer("CREATE ");
      switch (var2) {
         case 'A' -> var3.append("TYPE ");
         case 'F' -> var3.append("FUNCTION ");
         case 'G' -> var3.append("DERBY AGGREGATE ");
         case 'P' -> var3.append("PROCEDURE ");
      }

      var3.append(var0);
      var3.append(" ");
      String var4 = var1.getString(2);
      if (var2 == 'G') {
         var3.append(var4);
         var3.append(" ");
      } else if (var2 != 'A') {
         var3.append(var4.substring(var4.indexOf("("), var4.length()));
         var3.append(" ");
      }

      var3.append("EXTERNAL NAME '");
      var3.append(var1.getString(5));
      if (var2 == 'A') {
         var3.append("' ");
         var3.append(var4);
      } else if (var2 == 'G') {
         var3.append("' ");
      } else if (var2 != 'G') {
         var3.append(".");
         var3.append(var4.substring(0, var4.indexOf("(")));
         var3.append("' ");
      }

      return var3.toString();
   }

   public static void doSynonyms(Connection var0) throws SQLException {
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT ALIAS, SCHEMAID, ALIASINFO, SYSTEMALIAS FROM SYS.SYSALIASES A WHERE ALIASTYPE='S'");
      boolean var3 = true;

      while(var2.next()) {
         if (!var2.getBoolean(4)) {
            String var4 = dblook.lookupSchemaId(var2.getString(2));
            if (!dblook.isIgnorableSchema(var4)) {
               if (var3) {
                  Logs.reportString("----------------------------------------------");
                  Logs.reportMessage("DBLOOK_SynonymHeader");
                  Logs.reportString("----------------------------------------------\n");
               }

               String var5 = var2.getString(1);
               String var6 = dblook.addQuotes(dblook.expandDoubleQuotes(var5));
               var6 = var4 + "." + var6;
               Logs.writeToNewDDL("CREATE SYNONYM " + var6 + " FOR " + var2.getString(3));
               Logs.writeStmtEndToNewDDL();
               Logs.writeNewlineToNewDDL();
               var3 = false;
            }
         }
      }

      var2.close();
      var1.close();
   }
}
