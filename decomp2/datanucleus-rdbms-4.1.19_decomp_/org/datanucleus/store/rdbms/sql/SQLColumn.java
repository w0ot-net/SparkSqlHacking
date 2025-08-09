package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.table.Column;

public class SQLColumn {
   protected SQLTable table;
   protected Column column;
   protected DatastoreIdentifier alias;

   public SQLColumn(SQLTable table, Column col, DatastoreIdentifier alias) {
      this.table = table;
      this.column = col;
      this.alias = alias;
   }

   public SQLTable getTable() {
      return this.table;
   }

   public Column getColumn() {
      return this.column;
   }

   public DatastoreIdentifier getAlias() {
      return this.alias;
   }

   public String toString() {
      String str = null;
      if (this.table.getAlias() != null) {
         str = this.table.getAlias() + "." + this.column.getIdentifier().toString();
      } else {
         str = this.table.getTable() + "." + this.column.getIdentifier().toString();
      }

      return this.alias != null ? this.column.applySelectFunction(str) + " AS " + this.alias : this.column.applySelectFunction(str);
   }

   public String getColumnSelectString() {
      String str = null;
      if (this.table.getAlias() != null) {
         str = this.table.getAlias() + "." + this.column.getIdentifier().toString();
      } else {
         str = this.table.getTable() + "." + this.column.getIdentifier().toString();
      }

      return this.column.applySelectFunction(str);
   }
}
