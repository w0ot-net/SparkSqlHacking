package org.apache.derby.impl.tools.optional;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import org.apache.derby.iapi.sql.dictionary.OptionalTool;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.vti.ForeignTableVTI;

public class ForeignDBViews implements OptionalTool {
   private static final int XML_TYPE = 2009;
   private static final String[] SAFE_DROP_SQLSTATES = new String[]{"X0X05.S", "42Y55", "42Y07"};

   public void loadTool(String... var1) throws SQLException {
      if (var1 != null && var1.length >= 1) {
         String var2 = var1[0];
         String var3 = var1.length == 1 ? null : var1[1];
         Connection var4 = this.getForeignConnection(var2);
         Connection var5 = this.getDerbyConnection();
         DatabaseMetaData var6 = var4.getMetaData();
         ResultSet var7 = this.getForeignTables(var6);

         while(var7.next()) {
            this.registerForeignTable(var6, var7.getString(2), var7.getString(3), var2, var3, var5);
         }

         var7.close();
         var4.close();
      } else {
         throw this.wrap(LocalizedResource.getMessage("OT_BadLoadUnloadArgs"));
      }
   }

   public void unloadTool(String... var1) throws SQLException {
      if (var1 != null && var1.length >= 1) {
         String var2 = var1[0];
         String var3 = var1.length == 1 ? null : var1[1];
         Connection var4 = this.getForeignConnection(var2);
         Connection var5 = this.getDerbyConnection();
         DatabaseMetaData var6 = var4.getMetaData();
         ResultSet var7 = this.getForeignTables(var6);
         HashSet var8 = new HashSet();

         while(var7.next()) {
            String var9 = this.getDerbySchemaName(var3, var7.getString(2));
            String var10 = var7.getString(3);
            if (var9 != null) {
               var8.add(var9);
            }

            this.dropObject(var5, var9, var10, "view", false);
            this.dropObject(var5, var9, var10, "function", false);
         }

         var7.close();
         var4.close();

         for(String var12 : var8) {
            this.dropDerbySchema(var5, var12);
         }

         ForeignTableVTI.dropConnection(var2);
      } else {
         throw this.wrap(LocalizedResource.getMessage("OT_BadLoadUnloadArgs"));
      }
   }

   private void registerForeignTable(DatabaseMetaData var1, String var2, String var3, String var4, String var5, Connection var6) throws SQLException {
      StringBuilder var7 = new StringBuilder();
      String var8 = this.getDerbySchemaName(var5, var2);
      String var9 = this.dotSeparatedSchemaName(var8);
      this.createDerbySchema(var6, var8);
      var7.append("create function " + var9 + this.delimitedID(var3));
      var7.append("\n(");
      var7.append("\n\tforeignSchemaName varchar( 32672 ),");
      var7.append("\n\tforeignTableName varchar( 32672 ),");
      var7.append("\n\tconnectionURL varchar( 32672 )");
      var7.append("\n)\nreturns table\n(");
      ResultSet var10 = var1.getColumns((String)null, var2, var3, "%");
      int var11 = 0;

      while(var10.next()) {
         var7.append("\n\t");
         if (var11 > 0) {
            var7.append(", ");
         }

         ++var11;
         var7.append(this.delimitedID(var10.getString(4)));
         var7.append(" ");
         var7.append(this.mapType(var10.getInt(5), var10.getInt(7), var10.getInt(9), var10.getString(6)));
      }

      var10.close();
      var7.append("\n)");
      var7.append("\nlanguage java parameter style derby_jdbc_result_set no sql");
      var7.append("\nexternal name 'org.apache.derby.vti.ForeignTableVTI.readForeignTable'");
      String var12 = var7.toString();
      StringBuilder var13 = new StringBuilder();
      var13.append("create view " + var9 + this.delimitedID(var3));
      var13.append("\nas select *");
      var13.append("\nfrom table");
      var13.append("\n(\n");
      var13.append("\t" + var9 + this.delimitedID(var3));
      var13.append("\n\t(");
      String var10001 = this.stringLiteral(var2);
      var13.append("\n\t\t" + var10001 + ",");
      var10001 = this.stringLiteral(var3);
      var13.append("\n\t\t" + var10001 + ",");
      var10001 = this.stringLiteral(var4);
      var13.append("\n\t\t" + var10001);
      var13.append("\n\t)");
      var13.append("\n) s");
      String var14 = var13.toString();
      this.executeDDL(var6, var12);
      this.executeDDL(var6, var14);
   }

   private ResultSet getForeignTables(DatabaseMetaData var1) throws SQLException {
      return var1.getTables((String)null, (String)null, "%", new String[]{"TABLE"});
   }

   private void createDerbySchema(Connection var1, String var2) throws SQLException {
      if (var2 != null) {
         PreparedStatement var3 = this.prepareStatement(var1, "select count(*) from sys.sysschemas where schemaname = ?");
         var3.setString(1, var2);
         ResultSet var4 = var3.executeQuery();
         var4.next();
         boolean var5 = var4.getInt(1) > 0;
         var4.close();
         var3.close();
         if (!var5) {
            String var10002 = this.delimitedID(var2);
            this.executeDDL(var1, "create schema " + var10002);
         }

      }
   }

   private void dropDerbySchema(Connection var1, String var2) throws SQLException {
      if (var2 != null) {
         this.dropObject(var1, (String)null, var2, "schema", true);
      }
   }

   private String getDerbySchemaName(String var1, String var2) {
      if (var2 == null) {
         return null;
      } else {
         return var1 == null ? var2 : var1 + var2;
      }
   }

   private String dotSeparatedSchemaName(String var1) {
      if (var1 == null) {
         return "";
      } else {
         String var10000 = this.delimitedID(var1);
         return var10000 + ".";
      }
   }

   private String mapType(int var1, int var2, int var3, String var4) throws SQLException {
      switch (var1) {
         case -7:
            return "boolean";
         case -6:
            return "smallint";
         case -5:
            return "bigint";
         case -4:
            return "long varchar for bit data";
         case -3:
            String var9 = this.precisionToLength(var2);
            return "varchar " + var9 + "  for bit data";
         case -2:
            String var8 = this.precisionToLength(var2);
            return "char " + var8 + "  for bit data";
         case -1:
            return "long varchar";
         case 1:
            String var7 = this.precisionToLength(var2);
            return "char" + var7;
         case 2:
            String var6 = this.precisionAndScale(var2, var3);
            return "numeric" + var6;
         case 3:
            String var5 = this.precisionAndScale(var2, var3);
            return "decimal" + var5;
         case 4:
            return "integer";
         case 5:
            return "smallint";
         case 6:
            return "float";
         case 7:
            return "real";
         case 8:
            return "double";
         case 12:
            String var10000 = this.precisionToLength(var2);
            return "varchar" + var10000;
         case 16:
            return "boolean";
         case 91:
            return "date";
         case 92:
            return "time";
         case 93:
            return "timestamp";
         case 2004:
            return "blob";
         case 2005:
            return "clob";
         case 2009:
            return "xml";
         default:
            throw this.wrap(LocalizedResource.getMessage("OT_UnknownForeignDataType", Integer.toString(var1), var4));
      }
   }

   private String precisionToLength(int var1) {
      return "( " + var1 + " )";
   }

   private String precisionAndScale(int var1, int var2) {
      return "( " + var1 + ", " + var2 + " )";
   }

   private void dropObject(Connection var1, String var2, String var3, String var4, boolean var5) throws SQLException {
      String var6 = this.dotSeparatedSchemaName(var2);
      String var7 = var5 ? " restrict" : "";

      try {
         this.executeDDL(var1, "drop " + var4 + " " + var6 + this.delimitedID(var3) + var7);
      } catch (SQLException var14) {
         String var9 = var14.getSQLState();

         for(String var13 : SAFE_DROP_SQLSTATES) {
            if (var9.startsWith(var13)) {
               return;
            }
         }

         throw var14;
      }
   }

   private Connection getForeignConnection(String var1) throws SQLException {
      return DriverManager.getConnection(var1);
   }

   private Connection getDerbyConnection() throws SQLException {
      return DriverManager.getConnection("jdbc:default:connection");
   }

   private String delimitedID(String var1) {
      return IdUtil.normalToDelimited(var1);
   }

   private String stringLiteral(String var1) {
      return StringUtil.quoteStringLiteral(var1);
   }

   private void executeDDL(Connection var1, String var2) throws SQLException {
      PreparedStatement var3 = this.prepareStatement(var1, var2);
      var3.execute();
      var3.close();
   }

   private PreparedStatement prepareStatement(Connection var1, String var2) throws SQLException {
      return var1.prepareStatement(var2);
   }

   private SQLException wrap(String var1) {
      String var2 = "XJ001.U".substring(0, 5);
      return new SQLException(var1, var2);
   }
}
