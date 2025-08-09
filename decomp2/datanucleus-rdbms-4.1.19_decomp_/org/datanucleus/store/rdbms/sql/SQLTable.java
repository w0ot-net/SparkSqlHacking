package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.table.Table;

public class SQLTable {
   protected SQLStatement stmt;
   protected Table table;
   protected DatastoreIdentifier alias;
   protected String groupName;

   SQLTable(SQLStatement stmt, Table tbl, DatastoreIdentifier alias, String grpName) {
      this.stmt = stmt;
      this.table = tbl;
      this.alias = alias;
      this.groupName = grpName;
   }

   public SQLStatement getSQLStatement() {
      return this.stmt;
   }

   public Table getTable() {
      return this.table;
   }

   public DatastoreIdentifier getAlias() {
      return this.alias;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public int hashCode() {
      return this.alias != null ? this.alias.hashCode() ^ this.table.hashCode() : this.table.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof SQLTable)) {
         return false;
      } else {
         SQLTable other = (SQLTable)obj;
         if (other.alias == null) {
            return other.table == this.table && this.alias == null;
         } else {
            return other.table == this.table && other.alias.equals(this.alias);
         }
      }
   }

   public String toString() {
      return this.alias != null ? this.table.toString() + " " + this.alias.toString() : this.table.toString();
   }
}
