package org.datanucleus.store.rdbms.sql;

import java.util.HashMap;
import java.util.Map;
import org.datanucleus.util.StringUtils;

public class SQLTableGroup {
   String name;
   SQLJoin.JoinType joinType = null;
   Map tablesByAlias = new HashMap();

   SQLTableGroup(String name, SQLJoin.JoinType joinType) {
      this.name = name;
      this.joinType = joinType;
   }

   public String getName() {
      return this.name;
   }

   public SQLJoin.JoinType getJoinType() {
      return this.joinType;
   }

   public void addTable(SQLTable tbl) {
      this.tablesByAlias.put(tbl.getAlias().toString(), tbl);
   }

   public int getNumberOfTables() {
      return this.tablesByAlias.size();
   }

   public SQLTable[] getTables() {
      return (SQLTable[])this.tablesByAlias.values().toArray(new SQLTable[this.tablesByAlias.size()]);
   }

   public String toString() {
      return "SQLTableGroup: " + this.name + " join=" + this.joinType + " tables=" + StringUtils.mapToString(this.tablesByAlias);
   }
}
