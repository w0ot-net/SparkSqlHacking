package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.table.Table;

public class SQLTableAlphaNamer implements SQLTableNamer {
   static String[] CHARS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

   public String getAliasForTable(SQLStatement stmt, Table table, String groupName) {
      SQLTableGroup tableGrp = (SQLTableGroup)stmt.tableGroups.get(groupName);
      String groupLetters = null;
      int numTablesInGroup = 0;
      if (tableGrp != null && tableGrp.getNumberOfTables() != 0) {
         SQLTable refSqlTbl = tableGrp.getTables()[0];
         String baseTableAlias = refSqlTbl.getAlias().toString();
         String quote = stmt.getRDBMSManager().getDatastoreAdapter().getIdentifierQuoteString();
         int lettersStartPoint = 0;
         if (baseTableAlias.startsWith(quote)) {
            lettersStartPoint = quote.length();
         }

         int lettersLength = 1;
         if (baseTableAlias.length() > lettersStartPoint + 1 && Character.isLetter(baseTableAlias.charAt(lettersStartPoint + 1))) {
            lettersLength = 2;
         }

         groupLetters = baseTableAlias.substring(lettersStartPoint, lettersStartPoint + lettersLength);
         numTablesInGroup = tableGrp.getNumberOfTables();

         for(int i = 0; i < stmt.getNumberOfUnions(); ++i) {
            int num = ((SQLStatement)stmt.unions.get(i)).getTableGroup(tableGrp.getName()).getNumberOfTables();
            if (num > numTablesInGroup) {
               numTablesInGroup = num;
            }
         }
      } else {
         int number = stmt.tableGroups.size();
         groupLetters = this.getLettersForNumber(number);
         boolean nameClashes = true;

         while(nameClashes) {
            if (stmt.primaryTable != null && stmt.primaryTable.alias.getName().equalsIgnoreCase(groupLetters)) {
               ++number;
               groupLetters = this.getLettersForNumber(number);
            } else if (stmt.tables == null) {
               nameClashes = false;
            } else if (!stmt.tables.containsKey(groupLetters) && !stmt.tables.containsKey(groupLetters.toLowerCase())) {
               if (!stmt.tables.containsKey(groupLetters + "0") && !stmt.tables.containsKey(groupLetters.toLowerCase() + "0")) {
                  nameClashes = false;
               } else {
                  ++number;
                  groupLetters = this.getLettersForNumber(number);
               }
            } else {
               ++number;
               groupLetters = this.getLettersForNumber(number);
            }
         }

         numTablesInGroup = 0;
      }

      if (stmt.parent != null) {
         if (stmt.parent.parent != null) {
            return stmt.parent.parent.parent != null ? groupLetters + numTablesInGroup + "_SUB_SUB_SUB" : groupLetters + numTablesInGroup + "_SUB_SUB";
         } else {
            return groupLetters + numTablesInGroup + "_SUB";
         }
      } else {
         return groupLetters + numTablesInGroup;
      }
   }

   private String getLettersForNumber(int number) {
      String groupLetters;
      if (number >= CHARS.length) {
         groupLetters = CHARS[number / 26] + CHARS[number % 26];
      } else {
         groupLetters = CHARS[number];
      }

      return groupLetters;
   }
}
