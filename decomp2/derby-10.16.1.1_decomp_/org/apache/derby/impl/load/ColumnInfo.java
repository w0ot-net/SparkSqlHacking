package org.apache.derby.impl.load;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.derby.iapi.jdbc.EngineConnection;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

class ColumnInfo {
   private ArrayList vtiColumnNames = new ArrayList(1);
   private ArrayList insertColumnNames = new ArrayList(1);
   private ArrayList columnTypes = new ArrayList(1);
   private ArrayList jdbcColumnTypes = new ArrayList(1);
   private int noOfColumns = 0;
   private ArrayList columnPositions;
   private boolean createolumnNames = true;
   private int expectedNumberOfCols;
   private Connection conn;
   private String tableName;
   private String schemaName;
   private String[] headerColumnNames;
   private HashMap udtClassNames = new HashMap();

   public ColumnInfo(Connection var1, String var2, String var3, String var4, String var5, String var6, String[] var7) throws SQLException {
      this.conn = var1;
      this.headerColumnNames = var7;
      if (var2 == null) {
         var2 = ((EngineConnection)var1).getCurrentSchemaName();
      }

      this.schemaName = var2;
      this.tableName = var3;
      if (var4 != null) {
         StringTokenizer var8 = new StringTokenizer(var4, ",");

         while(var8.hasMoreTokens()) {
            String var9 = var8.nextToken().trim();
            if (!this.initializeColumnInfo(var9)) {
               if (this.tableExists()) {
                  throw LoadError.invalidColumnName(var9);
               }

               String var10 = this.schemaName != null ? this.schemaName + "." + this.tableName : this.tableName;
               throw LoadError.tableNotFound(var10);
            }
         }
      } else if (!this.initializeColumnInfo((String)null)) {
         String var13 = this.schemaName != null ? this.schemaName + "." + this.tableName : this.tableName;
         throw LoadError.tableNotFound(var13);
      }

      if (var5 != null) {
         StringTokenizer var11 = new StringTokenizer(var5, ",");

         while(var11.hasMoreTokens()) {
            String var15 = var11.nextToken().trim();
            int var14;
            if ("\"".equals(var15.substring(0, 1))) {
               var14 = this.readHeaders(var15.replace('"', ' ').trim());
               this.vtiColumnNames.add(var6 + var14);
            } else {
               this.vtiColumnNames.add(var6 + var15);
               var14 = Integer.parseInt(var15);
            }

            if (var14 > this.expectedNumberOfCols) {
               this.expectedNumberOfCols = var14;
            }
         }
      }

      if (this.vtiColumnNames.size() < 1) {
         for(int var12 = 1; var12 <= this.noOfColumns; ++var12) {
            this.vtiColumnNames.add(var6 + var12);
         }

         this.expectedNumberOfCols = this.noOfColumns;
      }

   }

   private int readHeaders(String var1) throws SQLException {
      if (this.headerColumnNames != null) {
         for(int var2 = 0; var2 < this.headerColumnNames.length; ++var2) {
            if (this.headerColumnNames[var2].equals(var1)) {
               return var2 + 1;
            }
         }

         throw PublicAPI.wrapStandardException(StandardException.newException("42XAU", new Object[]{var1}));
      } else {
         throw PublicAPI.wrapStandardException(StandardException.newException("42XAV", new Object[0]));
      }
   }

   private boolean initializeColumnInfo(String var1) throws SQLException {
      DatabaseMetaData var2 = this.conn.getMetaData();
      ResultSet var3 = var2.getColumns((String)null, this.schemaName, this.tableName, var1);
      boolean var4 = false;

      while(var3.next()) {
         String var5 = var3.getString(4);
         short var6 = var3.getShort(5);
         String var7 = var3.getString(6);
         int var8 = var3.getInt(7);
         int var9 = var3.getInt(9);
         int var10 = var3.getInt(10);
         var4 = true;
         if (!importExportSupportedType(var6)) {
            var3.close();
            throw LoadError.nonSupportedTypeColumn(var5, var7);
         }

         this.insertColumnNames.add(var5);
         String var11 = var7 + this.getTypeOption(var7, var8, var8, var9);
         this.columnTypes.add(var11);
         this.jdbcColumnTypes.add(Integer.valueOf(var6));
         ++this.noOfColumns;
         if (var6 == 2000) {
            this.udtClassNames.put("COLUMN" + this.noOfColumns, this.getUDTClassName(var2, var7));
         }
      }

      var3.close();
      return var4;
   }

   private String getUDTClassName(DatabaseMetaData var1, String var2) throws SQLException {
      String var3 = null;

      try {
         if (var2.charAt(0) != '"') {
            return var2;
         }

         String[] var4 = IdUtil.parseMultiPartSQLIdentifier(var2);
         String var5 = var4[0];
         String var6 = var4[1];
         ResultSet var7 = var1.getUDTs((String)null, var5, var6, new int[]{2000});
         if (var7.next()) {
            var3 = var7.getString(4);
         }

         var7.close();
      } catch (Exception var8) {
         throw LoadError.unexpectedError(var8);
      }

      if (var3 == null) {
         var3 = "???";
      }

      return var3;
   }

   public static final boolean importExportSupportedType(int var0) {
      return var0 != -7 && var0 != 1111 && var0 != 2009;
   }

   private String getTypeOption(String var1, int var2, int var3, int var4) {
      if ((var1.equals("CHAR") || var1.equals("BLOB") || var1.equals("CLOB") || var1.equals("VARCHAR")) && var2 != 0) {
         return "(" + var2 + ")";
      } else if (var1.equals("FLOAT") && var3 != 0) {
         return "(" + var3 + ")";
      } else {
         if (var1.equals("DECIMAL") || var1.equals("NUMERIC")) {
            if (var3 != 0 && var4 == 0) {
               return "(" + var3 + ")";
            }

            if (var3 != 0 && var4 != 0) {
               return "(" + var3 + "," + var4 + ")";
            }

            if (var3 == 0 && var4 != 0) {
               return "(" + var4 + ")";
            }
         }

         return (var1.equals("DECIMAL") || var1.equals("NUMERIC")) && var4 != 0 ? "(" + var4 + ")" : "";
      }
   }

   public String getColumnTypeNames() throws Exception {
      return ExportAbstract.stringifyObject(this.columnTypes);
   }

   public String getUDTClassNames() throws Exception {
      return ExportAbstract.stringifyObject(this.udtClassNames);
   }

   public String getColumnNamesWithCasts() {
      StringBuffer var1 = new StringBuffer();
      boolean var2 = true;
      int var3 = this.vtiColumnNames.size();

      for(int var4 = 0; var4 < this.noOfColumns && var4 < var3; ++var4) {
         if (!var2) {
            var1.append(", ");
         } else {
            var2 = false;
         }

         String var5 = (String)this.columnTypes.get(var4);
         String var6 = (String)this.vtiColumnNames.get(var4);
         if (!var5.startsWith("SMALLINT") && !var5.startsWith("INTEGER") && !var5.startsWith("DECIMAL") && !var5.startsWith("BIGINT") && !var5.startsWith("NUMERIC")) {
            if (var5.startsWith("DOUBLE")) {
               var1.append(" DOUBLE(" + var6 + ") ");
            } else if (var5.startsWith("REAL")) {
               var1.append("cast( DOUBLE(" + var6 + ")  AS REAL) ");
            } else {
               var1.append(" " + var6 + " ");
            }
         } else {
            var1.append(" cast(" + var6 + " AS " + var5 + ") ");
         }
      }

      return var2 ? " * " : var1.toString();
   }

   public String getInsertColumnNames() {
      StringBuffer var1 = new StringBuffer();
      boolean var2 = true;

      for(int var3 = 0; var3 < this.noOfColumns; ++var3) {
         if (!var2) {
            var1.append(", ");
         } else {
            var2 = false;
         }

         String var4 = (String)this.insertColumnNames.get(var3);
         var1.append(IdUtil.normalToDelimited(var4));
      }

      if (var2) {
         return null;
      } else {
         return var1.toString();
      }
   }

   public int getExpectedNumberOfColumnsInFile() {
      return this.expectedNumberOfCols;
   }

   private boolean tableExists() throws SQLException {
      DatabaseMetaData var1 = this.conn.getMetaData();
      ResultSet var2 = var1.getTables((String)null, this.schemaName, this.tableName, (String[])null);
      boolean var3 = false;
      if (var2.next()) {
         var3 = true;
      }

      var2.close();
      return var3;
   }

   public String getExpectedVtiColumnTypesAsString() {
      StringBuffer var1 = new StringBuffer();
      boolean var2 = true;

      for(int var3 = 0; var3 < this.noOfColumns && var3 < this.vtiColumnNames.size(); ++var3) {
         if (var2) {
            var2 = false;
         } else {
            var1.append(",");
         }

         String var10001 = (String)this.vtiColumnNames.get(var3);
         var1.append(var10001 + ":" + this.jdbcColumnTypes.get(var3));
      }

      return var2 ? null : var1.toString();
   }

   public static int[] getExpectedVtiColumnTypes(String var0, int var1) {
      int[] var2 = new int[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = 12;
      }

      StringTokenizer var8 = new StringTokenizer(var0, ",");

      while(var8.hasMoreTokens()) {
         String var4 = var8.nextToken().trim();
         int var5 = var4.indexOf(":");
         int var6 = Integer.parseInt(var4.substring(6, var5));
         int var7 = Integer.parseInt(var4.substring(var5 + 1));
         if (var6 <= var1) {
            var2[var6 - 1] = var7;
         }
      }

      return var2;
   }

   public static String[] getExpectedColumnTypeNames(String var0, int var1) throws Exception {
      ArrayList var2 = (ArrayList)ImportAbstract.destringifyObject(var0);
      String[] var3 = new String[var2.size()];

      for(int var4 = 0; var4 < var3.length; ++var4) {
         var3[var4] = (String)var2.get(var4);
      }

      return var3;
   }

   public static HashMap getExpectedUDTClasses(String var0) throws Exception {
      HashMap var1 = deserializeHashMap(var0);
      if (var1 == null) {
         return null;
      } else {
         HashMap var2 = new HashMap();

         for(Map.Entry var4 : var1.entrySet()) {
            String var5 = (String)var4.getKey();
            String var6 = (String)var4.getValue();
            Class var7 = Class.forName(var6);
            var2.put(var5, var7);
         }

         return var2;
      }
   }

   public static HashMap deserializeHashMap(String var0) throws Exception {
      if (var0 == null) {
         return null;
      } else {
         HashMap var1 = (HashMap)ImportAbstract.destringifyObject(var0);
         return var1;
      }
   }
}
