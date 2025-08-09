package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.table.Table;

public interface SQLTableNamer {
   String getAliasForTable(SQLStatement var1, Table var2, String var3);
}
