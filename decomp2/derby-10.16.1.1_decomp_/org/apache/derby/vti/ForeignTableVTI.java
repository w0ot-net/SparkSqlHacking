package org.apache.derby.vti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.util.ArrayUtil;

public class ForeignTableVTI extends ForwardingVTI implements RestrictedVTI {
   private static HashMap _connections = new HashMap();
   private String _foreignSchemaName;
   private String _foreignTableName;
   private String _connectionURL;
   private Connection _foreignConnection;
   private String[] _columnNames;
   private Restriction _restriction;
   private int[] _columnNumberMap;
   private PreparedStatement _foreignPreparedStatement;

   public ForeignTableVTI(String var1, String var2, Connection var3) {
      this._foreignSchemaName = var1;
      this._foreignTableName = var2;
      this._foreignConnection = var3;
   }

   protected ForeignTableVTI(String var1, String var2, String var3) {
      this._foreignSchemaName = var1;
      this._foreignTableName = var2;
      this._connectionURL = var3;
   }

   public static ForeignTableVTI readForeignTable(String var0, String var1, String var2) {
      return new ForeignTableVTI(var0, var1, var2);
   }

   public static void dropConnection(String var0) {
      _connections.remove(var0);
   }

   public static int countConnections() {
      return _connections.size();
   }

   public void close() throws SQLException {
      if (!this.isClosed()) {
         this._foreignSchemaName = null;
         this._foreignTableName = null;
         this._connectionURL = null;
         this._columnNames = null;
         this._restriction = null;
         this._columnNumberMap = null;
         if (this.getWrappedResultSet() != null) {
            this.getWrappedResultSet().close();
         }

         if (this._foreignPreparedStatement != null) {
            this._foreignPreparedStatement.close();
         }

         this.wrapResultSet((ResultSet)null);
         this._foreignPreparedStatement = null;
         this._foreignConnection = null;
      }

   }

   public boolean next() throws SQLException {
      if (!this.isClosed() && this.getWrappedResultSet() == null) {
         this._foreignPreparedStatement = prepareStatement(getForeignConnection(this._connectionURL, this._foreignConnection), this.makeQuery());
         this.wrapResultSet(this._foreignPreparedStatement.executeQuery());
      }

      return this.getWrappedResultSet().next();
   }

   public boolean isClosed() {
      return this._connectionURL == null && this._foreignConnection == null;
   }

   public void initScan(String[] var1, Restriction var2) throws SQLException {
      this._columnNames = (String[])ArrayUtil.copy(var1);
      this._restriction = var2;
      int var3 = this._columnNames.length;
      this._columnNumberMap = new int[var3];
      int var4 = 1;

      for(int var5 = 0; var5 < var3; ++var5) {
         if (this._columnNames[var5] != null) {
            this._columnNumberMap[var5] = var4++;
         }
      }

   }

   private static Connection getForeignConnection(String var0, Connection var1) throws SQLException {
      if (var1 != null) {
         return var1;
      } else {
         Connection var2 = (Connection)_connections.get(var0);
         if (var2 == null) {
            var2 = DriverManager.getConnection(var0);
            if (var2 != null) {
               _connections.put(var0, var2);
            }
         }

         return var2;
      }
   }

   private String makeQuery() {
      StringBuilder var1 = new StringBuilder();
      var1.append("select ");
      int var2 = this._columnNames.length;
      int var3 = 0;

      for(int var4 = 0; var4 < var2; ++var4) {
         String var5 = this._columnNames[var4];
         if (var5 != null) {
            if (var3 > 0) {
               var1.append(", ");
            }

            ++var3;
            var1.append(delimitedID(var5));
         }
      }

      var1.append("\nfrom ");
      var1.append(delimitedID(this._foreignSchemaName));
      var1.append('.');
      var1.append(delimitedID(this._foreignTableName));
      if (this._restriction != null) {
         String var6 = this._restriction.toSQL();
         if (var6 != null) {
            var6 = var6.trim();
            if (var6.length() != 0) {
               var1.append("\nwhere " + var6);
            }
         }
      }

      return var1.toString();
   }

   private static String delimitedID(String var0) {
      return IdUtil.normalToDelimited(var0);
   }

   private static PreparedStatement prepareStatement(Connection var0, String var1) throws SQLException {
      return var0.prepareStatement(var1);
   }

   protected int mapColumnNumber(int var1) {
      return this._columnNumberMap[var1 - 1];
   }
}
