package org.apache.derby.iapi.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.i18n.MessageService;

public class AutoloadedDriver implements Driver {
   private static boolean _engineForcedDown = false;
   private static AutoloadedDriver _autoloadedDriver;
   private static Driver _driverModule;

   protected static void registerMe(AutoloadedDriver var0) {
      try {
         _autoloadedDriver = var0;
         DriverManager.registerDriver(_autoloadedDriver);
      } catch (SQLException var3) {
         String var2 = MessageService.getTextMessage("I026", new Object[]{var3.getMessage()});
         throw new IllegalStateException(var2);
      }
   }

   public boolean acceptsURL(String var1) throws SQLException {
      return !_engineForcedDown && InternalDriver.embeddedDriverAcceptsURL(var1);
   }

   public Connection connect(String var1, Properties var2) throws SQLException {
      return !InternalDriver.embeddedDriverAcceptsURL(var1) ? null : getDriverModule().connect(var1, var2);
   }

   public DriverPropertyInfo[] getPropertyInfo(String var1, Properties var2) throws SQLException {
      return getDriverModule().getPropertyInfo(var1, var2);
   }

   public int getMajorVersion() {
      try {
         return getDriverModule().getMajorVersion();
      } catch (SQLException var2) {
         return 0;
      }
   }

   public int getMinorVersion() {
      try {
         return getDriverModule().getMinorVersion();
      } catch (SQLException var2) {
         return 0;
      }
   }

   public boolean jdbcCompliant() {
      try {
         return getDriverModule().jdbcCompliant();
      } catch (SQLException var2) {
         return false;
      }
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw (SQLFeatureNotSupportedException)Util.notImplemented("getParentLogger()");
   }

   public static Driver getDriverModule() throws SQLException {
      if (_engineForcedDown && _autoloadedDriver == null) {
         throw Util.generateCsSQLException("08006.C.8");
      } else {
         if (!isBooted()) {
            JDBCBoot.boot();
         }

         return _driverModule;
      }
   }

   public static void registerDriverModule(Driver var0) {
      _driverModule = var0;
      _engineForcedDown = false;

      try {
         if (_autoloadedDriver == null) {
            _autoloadedDriver = makeAutoloadedDriver();
            DriverManager.registerDriver(_autoloadedDriver);
         }
      } catch (SQLException var2) {
      }

   }

   public static void unregisterDriverModule() {
      _engineForcedDown = true;

      try {
         if (InternalDriver.getDeregister() && _autoloadedDriver != null) {
            deregisterDriver(_autoloadedDriver);
            _autoloadedDriver = null;
         }

         InternalDriver.setDeregister(true);
         _driverModule = null;
      } catch (SQLException var1) {
      }

   }

   private static void deregisterDriver(AutoloadedDriver var0) throws SQLException {
      DriverManager.deregisterDriver(var0);
   }

   private static boolean isBooted() {
      return _driverModule != null;
   }

   private static AutoloadedDriver makeAutoloadedDriver() {
      return new AutoloadedDriver();
   }

   static {
      registerMe(new AutoloadedDriver());
   }
}
