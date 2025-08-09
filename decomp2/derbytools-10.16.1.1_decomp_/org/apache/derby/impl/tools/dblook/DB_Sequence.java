package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.tools.dblook;

public class DB_Sequence {
   public static void doSequences(Connection var0) throws SQLException {
      PreparedStatement var1 = var0.prepareStatement("SELECT SCHEMAID, SEQUENCENAME, SEQUENCEDATATYPE, STARTVALUE, MINIMUMVALUE, MAXIMUMVALUE, INCREMENT, CYCLEOPTION\nFROM SYS.SYSSEQUENCES\nWHERE CAST( SCHEMAID AS CHAR( 36) ) != '8000000d-00d0-fd77-3ed8-000a0a0b1900'");
      ResultSet var2 = var1.executeQuery();

      for(boolean var3 = true; var2.next(); var3 = false) {
         int var4 = 1;
         String var5 = dblook.lookupSchemaId(var2.getString(var4++));
         String var6 = var2.getString(var4++);
         String var7 = stripNotNull(var2.getString(var4++));
         long var8 = var2.getLong(var4++);
         long var10 = var2.getLong(var4++);
         long var12 = var2.getLong(var4++);
         long var14 = var2.getLong(var4++);
         String var16 = "Y".equals(var2.getString(var4++)) ? "CYCLE" : "NO CYCLE";
         if (var3) {
            Logs.reportString("----------------------------------------------");
            Logs.reportMessage("DBLOOK_SequenceHeader");
            Logs.reportString("----------------------------------------------\n");
         }

         String var17 = dblook.addQuotes(dblook.expandDoubleQuotes(var6));
         var17 = var5 + "." + var17;
         String var18 = createSequenceString(var17, var7, var8, var10, var12, var14, var16);
         Logs.writeToNewDDL(var18);
         Logs.writeStmtEndToNewDDL();
         Logs.writeNewlineToNewDDL();
      }

      var2.close();
      var1.close();
   }

   private static String stripNotNull(String var0) {
      int var1 = var0.indexOf("NOT");
      return var1 > 0 ? var0.substring(0, var1) : var0;
   }

   private static String createSequenceString(String var0, String var1, long var2, long var4, long var6, long var8, String var10) throws SQLException {
      StringBuffer var11 = new StringBuffer();
      var11.append("CREATE SEQUENCE " + var0 + "\n");
      var11.append("    AS " + var1 + "\n");
      var11.append("    START WITH " + Long.toString(var2) + "\n");
      var11.append("    INCREMENT BY " + Long.toString(var8) + "\n");
      var11.append("    MAXVALUE " + Long.toString(var6) + "\n");
      var11.append("    MINVALUE " + Long.toString(var4) + "\n");
      var11.append("    " + var10 + "\n");
      return var11.toString();
   }
}
