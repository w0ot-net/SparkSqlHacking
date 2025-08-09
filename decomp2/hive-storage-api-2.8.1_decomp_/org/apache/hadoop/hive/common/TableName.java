package org.apache.hadoop.hive.common;

import java.io.Serializable;
import java.util.Objects;

public class TableName implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final String ILL_ARG_EXCEPTION_MSG = "Table name must be either <tablename>, <dbname>.<tablename> or <catname>.<dbname>.<tablename>";
   private final String cat;
   private final String db;
   private final String table;

   public TableName(String catName, String dbName, String tableName) {
      this.cat = catName;
      this.db = dbName;
      this.table = tableName;
   }

   public static TableName fromString(String name, String defaultCatalog, String defaultDatabase) throws IllegalArgumentException {
      if (name == null) {
         throw new IllegalArgumentException(String.join("", "Table value was null. ", "Table name must be either <tablename>, <dbname>.<tablename> or <catname>.<dbname>.<tablename>"));
      } else if (name.contains(".")) {
         String[] names = name.split("\\.");
         if (names.length == 2) {
            return new TableName(defaultCatalog, names[0], names[1]);
         } else if (names.length == 3) {
            return new TableName(names[0], names[1], names[2]);
         } else {
            throw new IllegalArgumentException("Table name must be either <tablename>, <dbname>.<tablename> or <catname>.<dbname>.<tablename>");
         }
      } else {
         return new TableName(defaultCatalog, defaultDatabase, name);
      }
   }

   public String getCat() {
      return this.cat;
   }

   public String getDb() {
      return this.db;
   }

   public String getTable() {
      return this.table;
   }

   /** @deprecated */
   public String getDbTable() {
      return this.db + "." + this.table;
   }

   public String getEscapedNotEmptyDbTable() {
      return this.db != null && !this.db.trim().isEmpty() ? "`" + this.db + "`" + "." + "`" + this.table + "`" : "`" + this.table + "`";
   }

   public String getNotEmptyDbTable() {
      return this.db != null && !this.db.trim().isEmpty() ? this.db + "." + this.table : this.table;
   }

   public static String getDbTable(String dbName, String tableName) {
      return dbName + "." + tableName;
   }

   public static String getQualified(String catName, String dbName, String tableName) {
      return catName + "." + dbName + "." + tableName;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         TableName tableName = (TableName)o;
         return Objects.equals(this.cat, tableName.cat) && Objects.equals(this.db, tableName.db) && Objects.equals(this.table, tableName.table);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.cat, this.db, this.table});
   }

   public String toString() {
      return this.cat + "." + this.db + "." + this.table;
   }
}
