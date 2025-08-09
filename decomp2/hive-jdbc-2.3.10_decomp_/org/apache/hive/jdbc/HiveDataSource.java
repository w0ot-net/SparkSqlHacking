package org.apache.hive.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class HiveDataSource implements DataSource {
   public Connection getConnection() throws SQLException {
      return this.getConnection("", "");
   }

   public Connection getConnection(String username, String password) throws SQLException {
      try {
         return new HiveConnection("", (Properties)null);
      } catch (Exception ex) {
         throw new SQLException("Error in getting HiveConnection", ex);
      }
   }

   public PrintWriter getLogWriter() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getLoginTimeout() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setLogWriter(PrintWriter arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setLoginTimeout(int arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWrapperFor(Class arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class arg0) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }
}
