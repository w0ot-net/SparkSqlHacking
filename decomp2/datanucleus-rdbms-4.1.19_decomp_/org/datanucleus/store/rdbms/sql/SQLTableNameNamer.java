package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.table.Table;

public class SQLTableNameNamer implements SQLTableNamer {
   public String getAliasForTable(SQLStatement stmt, Table table, String groupName) {
      return table.getName();
   }
}
