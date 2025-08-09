package org.apache.derby.impl.tools.dblook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Key {
   private static PreparedStatement getReferenceCols;
   private static boolean printedHeader;

   public static void doKeys(Connection var0) throws SQLException {
      printedHeader = false;
      getReferenceCols = var0.prepareStatement("SELECT CG.TABLEID, CG.DESCRIPTOR FROM SYS.SYSCONGLOMERATES CG, SYS.SYSKEYS K WHERE K.CONSTRAINTID = ? AND K.CONGLOMERATEID = CG.CONGLOMERATEID");
      Statement var1 = var0.createStatement();
      ResultSet var2 = var1.executeQuery("SELECT CS.CONSTRAINTNAME, CS.TYPE, CS.TABLEID, CS.CONSTRAINTID, CS.SCHEMAID, CS.STATE, CG.DESCRIPTOR, CG.ISCONSTRAINT FROM SYS.SYSCONSTRAINTS CS, SYS.SYSCONGLOMERATES CG, SYS.SYSKEYS K WHERE CS.STATE != 'D' AND CS.CONSTRAINTID = K.CONSTRAINTID AND CG.CONGLOMERATEID = K.CONGLOMERATEID ORDER BY CS.TABLEID");
      createKeysFrom(var2);
      var2 = var1.executeQuery("SELECT CS.CONSTRAINTNAME, CS.TYPE, CS.TABLEID, CS.CONSTRAINTID, CS.SCHEMAID, CS.STATE, CG.DESCRIPTOR, CG.ISCONSTRAINT, K.DELETERULE, K.UPDATERULE, K.KEYCONSTRAINTID FROM SYS.SYSCONSTRAINTS CS, SYS.SYSCONGLOMERATES CG, SYS.SYSFOREIGNKEYS K WHERE CS.STATE != 'D' AND CS.CONSTRAINTID = K.CONSTRAINTID AND CG.CONGLOMERATEID = K.CONGLOMERATEID ORDER BY CS.TABLEID");
      createKeysFrom(var2);
      getReferenceCols.close();
      var1.close();
      var2.close();
   }

   private static void createKeysFrom(ResultSet var0) throws SQLException {
      boolean var1 = true;

      while(var0.next()) {
         if (var0.getBoolean(8)) {
            String var2 = var0.getString(3);
            String var3 = dblook.lookupTableId(var2);
            if (!dblook.isExcludedTable(var3)) {
               if (var1) {
                  printHeader();
                  if (var0.getString(2).equals("F")) {
                     Logs.reportMessage("DBLOOK_ForeignHeader");
                  } else {
                     Logs.reportMessage("DBLOOK_PrimUniqueHeader");
                  }
               }

               StringBuffer var4 = createKeyString(var2, var3, var0);
               if (var0.getString(2).equals("F")) {
                  var4.append(makeFKReferenceClause(var0.getString(11), var0.getString(9).charAt(0), var0.getString(10).charAt(0)));
               }

               makeDeferredClauses(var4, var0, 6);
               Logs.writeToNewDDL(var4.toString());
               Logs.writeStmtEndToNewDDL();
               Logs.writeNewlineToNewDDL();
               var1 = false;
            }
         }
      }

   }

   private static StringBuffer createKeyString(String var0, String var1, ResultSet var2) throws SQLException {
      StringBuffer var3 = new StringBuffer("ALTER TABLE ");
      var3.append(var1);
      var3.append(" ADD");
      String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var2.getString(1)));
      var3.append(" CONSTRAINT ");
      var3.append(var4);
      var3.append(expandKeyType(var2.getString(2).charAt(0)));
      var3.append("(");
      var3.append(dblook.getColumnListFromDescription(var0, var2.getString(7)));
      var3.append(")");
      return var3;
   }

   private static String expandKeyType(char var0) {
      switch (var0) {
         case 'F':
            return " FOREIGN KEY ";
         case 'P':
            return " PRIMARY KEY ";
         case 'U':
            return " UNIQUE ";
         default:
            Logs.debug("INTERNAL ERROR: unexpected key type" + var0, (String)null);
            return "";
      }
   }

   private static String makeFKReferenceClause(String var0, char var1, char var2) throws SQLException {
      StringBuffer var3 = new StringBuffer();
      getReferenceCols.setString(1, var0);
      ResultSet var4 = getReferenceCols.executeQuery();
      var4.next();
      var3.append(" REFERENCES ");
      var3.append(dblook.lookupTableId(var4.getString(1)));
      var3.append(" (");
      var3.append(dblook.getColumnListFromDescription(var4.getString(1), var4.getString(2)));
      var3.append(")");
      var3.append(" ON DELETE ");
      switch (var1) {
         case 'C' -> var3.append("CASCADE");
         case 'R' -> var3.append("NO ACTION");
         case 'S' -> var3.append("RESTRICT");
         case 'U' -> var3.append("SET NULL");
         default -> Logs.debug("INTERNAL ERROR: unexpected 'on-delete' action: " + var1, (String)null);
      }

      var3.append(" ON UPDATE ");
      switch (var2) {
         case 'R' -> var3.append("NO ACTION");
         case 'S' -> var3.append("RESTRICT");
         default -> Logs.debug("INTERNAL ERROR: unexpected 'on-update' action: " + var2, (String)null);
      }

      var4.close();
      return var3.toString();
   }

   static void makeDeferredClauses(StringBuffer var0, ResultSet var1, int var2) throws SQLException {
      String var3 = var1.getString(var2);
      String var4 = dblook.addQuotes(dblook.expandDoubleQuotes(var1.getString(1)));
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = true;
      switch (var3.charAt(0)) {
         case 'D':
            var5 = false;
            var6 = false;
            var7 = false;
            break;
         case 'E':
            var5 = false;
            var6 = false;
            var7 = true;
            break;
         case 'd':
            var5 = true;
            var6 = true;
            var7 = false;
            break;
         case 'e':
            var5 = true;
            var6 = true;
            var7 = true;
            break;
         case 'i':
            var5 = true;
            var6 = false;
            var7 = true;
            break;
         case 'j':
            var5 = true;
            var6 = false;
            var7 = false;
            break;
         default:
            Logs.debug("INTERNAL ERROR: Invalid state value '" + var3 + "' for constraint " + var4, (String)null);
      }

      if (var5) {
         var0.append(" DEFERRABLE ");
         if (var6) {
            var0.append(" INITIALLY DEFERRED ");
         }
      }

   }

   private static void printHeader() {
      if (!printedHeader) {
         Logs.reportString("----------------------------------------------");
         Logs.reportMessage("DBLOOK_KeysHeader");
         Logs.reportString("----------------------------------------------\n");
         printedHeader = true;
      }
   }
}
