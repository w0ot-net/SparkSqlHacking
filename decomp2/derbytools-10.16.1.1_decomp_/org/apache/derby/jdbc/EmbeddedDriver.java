package org.apache.derby.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import org.apache.derby.iapi.jdbc.AutoloadedDriver;
import org.apache.derby.iapi.jdbc.JDBCBoot;
import org.apache.derby.impl.jdbc.Util;

public class EmbeddedDriver implements Driver {
   public EmbeddedDriver() {
      JDBCBoot.boot();
   }

   public boolean acceptsURL(String var1) throws SQLException {
      return this.getDriverModule().acceptsURL(var1);
   }

   public Connection connect(String var1, Properties var2) throws SQLException {
      return this.getDriverModule().connect(var1, var2);
   }

   public DriverPropertyInfo[] getPropertyInfo(String var1, Properties var2) throws SQLException {
      return this.getDriverModule().getPropertyInfo(var1, var2);
   }

   public int getMajorVersion() {
      try {
         return this.getDriverModule().getMajorVersion();
      } catch (SQLException var2) {
         return 0;
      }
   }

   public int getMinorVersion() {
      try {
         return this.getDriverModule().getMinorVersion();
      } catch (SQLException var2) {
         return 0;
      }
   }

   public boolean jdbcCompliant() {
      try {
         return this.getDriverModule().jdbcCompliant();
      } catch (SQLException var2) {
         return false;
      }
   }

   private Driver getDriverModule() throws SQLException {
      return AutoloadedDriver.getDriverModule();
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw (SQLFeatureNotSupportedException)Util.notImplemented("getParentLogger()");
   }

   static {
      JDBCBoot.boot();
   }
}
