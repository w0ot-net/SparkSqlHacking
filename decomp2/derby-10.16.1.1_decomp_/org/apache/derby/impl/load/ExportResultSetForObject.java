package org.apache.derby.impl.load;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.iapi.util.IdUtil;

class ExportResultSetForObject {
   private Connection con;
   private String selectQuery;
   private ResultSet rs;
   private int columnCount;
   private String[] columnNames;
   private String[] columnTypes;
   private int[] columnLengths;
   private Statement expStmt = null;
   private String schemaName;
   private String tableName;

   public ExportResultSetForObject(Connection var1, String var2, String var3, String var4) {
      this.con = var1;
      if (var4 == null) {
         this.schemaName = var2;
         this.tableName = var3;
         String var10001 = IdUtil.mkQualifiedName(var2, var3);
         this.selectQuery = "select * from " + var10001;
      } else {
         this.selectQuery = var4;
      }

   }

   public ResultSet getResultSet() throws SQLException {
      this.rs = null;
      this.expStmt = this.con.createStatement();
      this.rs = this.expStmt.executeQuery(this.selectQuery);
      this.getMetaDataInfo();
      return this.rs;
   }

   public int getColumnCount() {
      return this.columnCount;
   }

   public String[] getColumnDefinition() {
      return this.columnNames;
   }

   public String[] getColumnTypes() {
      return this.columnTypes;
   }

   public int[] getColumnLengths() {
      return this.columnLengths;
   }

   private void getMetaDataInfo() throws SQLException {
      ResultSetMetaData var1 = this.rs.getMetaData();
      this.columnCount = var1.getColumnCount();
      int var2 = this.columnCount;
      this.columnNames = new String[var2];
      this.columnTypes = new String[var2];
      this.columnLengths = new int[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = var1.getColumnType(var3 + 1);
         this.columnNames[var3] = var1.getColumnName(var3 + 1);
         this.columnTypes[var3] = var1.getColumnTypeName(var3 + 1);
         if (!ColumnInfo.importExportSupportedType(var4)) {
            throw LoadError.nonSupportedTypeColumn(this.columnNames[var3], this.columnTypes[var3]);
         }

         this.columnLengths[var3] = var1.getColumnDisplaySize(var3 + 1);
      }

   }

   public void close() throws Exception {
      if (this.expStmt != null) {
         this.expStmt.close();
      }

   }
}
