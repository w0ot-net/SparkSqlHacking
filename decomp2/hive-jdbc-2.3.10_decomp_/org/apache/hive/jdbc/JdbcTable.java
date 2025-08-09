package org.apache.hive.jdbc;

import java.sql.SQLException;

public class JdbcTable {
   private String tableCatalog;
   private String tableName;
   private String type;
   private String comment;

   public JdbcTable(String tableCatalog, String tableName, String type, String comment) {
      this.tableCatalog = tableCatalog;
      this.tableName = tableName;
      this.type = type;
      this.comment = comment;
   }

   public String getTableCatalog() {
      return this.tableCatalog;
   }

   public String getTableName() {
      return this.tableName;
   }

   public String getType() {
      return this.type;
   }

   public String getSqlTableType() throws SQLException {
      return HiveDatabaseMetaData.toJdbcTableType(this.type);
   }

   public String getComment() {
      return this.comment;
   }
}
