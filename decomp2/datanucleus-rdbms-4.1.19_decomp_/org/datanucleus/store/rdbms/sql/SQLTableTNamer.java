package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.table.Table;

public class SQLTableTNamer implements SQLTableNamer {
   public String getAliasForTable(SQLStatement stmt, Table table, String groupName) {
      int number = 0;
      if (stmt.getPrimaryTable() != null) {
         int numTables = stmt.getNumberOfTables();

         for(int i = 0; i < stmt.getNumberOfUnions(); ++i) {
            int num = ((SQLStatement)stmt.unions.get(i)).getNumberOfTables();
            if (num > numTables) {
               numTables = num;
            }
         }

         number = numTables > 0 ? numTables + 1 : 1;
      }

      if (stmt.parent != null) {
         if (stmt.parent.parent != null) {
            return stmt.parent.parent.parent != null ? "T" + number + "_SUB_SUB_SUB" : "T" + number + "_SUB_SUB";
         } else {
            return "T" + number + "_SUB";
         }
      } else {
         return "T" + number;
      }
   }
}
