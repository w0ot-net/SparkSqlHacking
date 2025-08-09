package org.apache.hadoop.hive.common;

public class DatabaseName {
   static final String CAT_DB_TABLE_SEPARATOR = ".";
   private final String cat;
   private final String db;

   public DatabaseName(String cat, String db) {
      this.cat = cat;
      this.db = db;
   }

   public static DatabaseName fromString(String name, String defaultCatalog) {
      if (name.contains(".")) {
         String[] names = name.split("\\.");
         if (names.length != 2) {
            throw new RuntimeException("Database name must be either <dbname> or <catname>.<dbname>");
         } else {
            return new DatabaseName(names[0], names[1]);
         }
      } else {
         assert defaultCatalog != null;

         return new DatabaseName(defaultCatalog, name);
      }
   }

   public String getCat() {
      return this.cat;
   }

   public String getDb() {
      return this.db;
   }

   public static String getQualified(String catName, String dbName) {
      return catName + "." + dbName;
   }

   public int hashCode() {
      return this.cat.hashCode() * 31 + this.db.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj != null && obj instanceof DatabaseName) {
         DatabaseName that = (DatabaseName)obj;
         return this.db.equals(that.db) && this.cat.equals(that.cat);
      } else {
         return false;
      }
   }

   public String toString() {
      return this.cat + "." + this.db;
   }
}
