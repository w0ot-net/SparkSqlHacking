package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Trigger {
   private static final String TRIGGERNAME = "TRIGGERNAME";
   private static final String SCHEMAID = "SCHEMAID";
   private static final String EVENT = "EVENT";
   private static final String FIRINGTIME = "FIRINGTIME";
   private static final String TYPE = "TYPE";
   private static final String TABLEID = "TABLEID";
   private static final String REFERENCEDCOLUMNS = "REFERENCEDCOLUMNS";
   private static final String TRIGGERDEFINITION = "TRIGGERDEFINITION";
   private static final String REFERENCINGOLD = "REFERENCINGOLD";
   private static final String REFERENCINGNEW = "REFERENCINGNEW";
   private static final String OLDREFERENCINGNAME = "OLDREFERENCINGNAME";
   private static final String NEWREFERENCINGNAME = "NEWREFERENCINGNAME";
   private static final String WHENCLAUSETEXT = "WHENCLAUSETEXT";

   public static void doTriggers(Connection var0, boolean var1) throws SQLException {
      Statement var2 = var0.createStatement();
      ResultSet var3 = var2.executeQuery("SELECT * FROM SYS.SYSTRIGGERS WHERE STATE != 'D' ORDER BY CREATIONTIMESTAMP");
      boolean var4 = true;

      while(var3.next()) {
         String var5 = dblook.addQuotes(dblook.expandDoubleQuotes(var3.getString("TRIGGERNAME")));
         String var6 = dblook.lookupSchemaId(var3.getString("SCHEMAID"));
         if (!dblook.isIgnorableSchema(var6)) {
            var5 = var6 + "." + var5;
            String var7 = dblook.lookupTableId(var3.getString("TABLEID"));
            String var8 = var1 ? var3.getString("WHENCLAUSETEXT") : null;
            if (dblook.stringContainsTargetTable(var3.getString("TRIGGERDEFINITION")) || dblook.stringContainsTargetTable(var8) || !dblook.isExcludedTable(var7)) {
               if (var4) {
                  Logs.reportString("----------------------------------------------");
                  Logs.reportMessage("DBLOOK_TriggersHeader");
                  Logs.reportString("----------------------------------------------\n");
               }

               String var9 = createTrigger(var5, var7, var8, var3);
               Logs.writeToNewDDL(var9);
               Logs.writeStmtEndToNewDDL();
               Logs.writeNewlineToNewDDL();
               var4 = false;
            }
         }
      }

      var3.close();
      var2.close();
   }

   private static String createTrigger(String var0, String var1, String var2, ResultSet var3) throws SQLException {
      StringBuilder var4 = new StringBuilder("CREATE TRIGGER ");
      var4.append(var0);
      if (var3.getString("FIRINGTIME").charAt(0) == 'A') {
         var4.append(" AFTER ");
      } else {
         var4.append(" NO CASCADE BEFORE ");
      }

      String var5 = var3.getString("EVENT");
      switch (var5.charAt(0)) {
         case 'D':
            var4.append("DELETE");
            break;
         case 'I':
            var4.append("INSERT");
            break;
         case 'U':
            var4.append("UPDATE");
            String var6 = var3.getString("REFERENCEDCOLUMNS");
            if (!var3.wasNull() && !var6.equals("NULL")) {
               var4.append(" OF ");
               var4.append(dblook.getColumnListFromDescription(var3.getString("TABLEID"), var6));
            }
            break;
         default:
            Logs.debug("INTERNAL ERROR: unexpected trigger event: " + var5, (String)null);
      }

      var4.append(" ON ");
      var4.append(var1);
      char var9 = var3.getString("TYPE").charAt(0);
      String var7 = var3.getString("OLDREFERENCINGNAME");
      String var8 = var3.getString("NEWREFERENCINGNAME");
      if (var7 != null || var8 != null) {
         var4.append(" REFERENCING");
         if (var3.getBoolean("REFERENCINGOLD")) {
            var4.append(" OLD");
            if (var9 == 'S') {
               var4.append("_TABLE AS ");
            } else {
               var4.append(" AS ");
            }

            var4.append(var7);
         }

         if (var3.getBoolean("REFERENCINGNEW")) {
            var4.append(" NEW");
            if (var9 == 'S') {
               var4.append("_TABLE AS ");
            } else {
               var4.append(" AS ");
            }

            var4.append(var8);
         }
      }

      var4.append(" FOR EACH ");
      if (var9 == 'S') {
         var4.append("STATEMENT ");
      } else {
         var4.append("ROW ");
      }

      if (var2 != null) {
         var4.append("WHEN (");
         var4.append(dblook.removeNewlines(var2));
         var4.append(") ");
      }

      var4.append(dblook.removeNewlines(var3.getString("TRIGGERDEFINITION")));
      return var4.toString();
   }
}
