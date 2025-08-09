package org.apache.derby.jdbc;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.jdbc.AutoloadedDriver;
import org.apache.derby.iapi.jdbc.EmbeddedDataSourceInterface;
import org.apache.derby.iapi.jdbc.EmbeddedXADataSourceInterface;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.jdbc.ResourceAdapter;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.i18n.MessageService;

public class BasicEmbeddedDataSource40 implements DataSource, Serializable, EmbeddedDataSourceInterface {
   private static final long serialVersionUID = -4945135214995641182L;
   protected String description;
   protected String dataSourceName;
   protected String databaseName;
   protected String connectionAttributes;
   protected String createDatabase;
   protected String shutdownDatabase;
   protected boolean attributesAsPassword;
   private String shortDatabaseName;
   private String password;
   private String user;
   protected int loginTimeout;
   private transient PrintWriter printer;
   protected transient String jdbcurl;
   protected transient InternalDriver driver;

   public BasicEmbeddedDataSource40() {
      this.update();
   }

   public synchronized void setDatabaseName(String var1) {
      this.databaseName = var1;
      if (var1 != null && var1.contains(";")) {
         String[] var2 = var1.split(";");
         this.shortDatabaseName = var2[0];
      } else {
         this.shortDatabaseName = var1;
      }

      this.update();
   }

   public String getDatabaseName() {
      return this.databaseName;
   }

   private String getShortDatabaseName() {
      return this.shortDatabaseName;
   }

   public void setDataSourceName(String var1) {
      this.dataSourceName = var1;
   }

   public String getDataSourceName() {
      return this.dataSourceName;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public String getDescription() {
      return this.description;
   }

   public void setUser(String var1) {
      this.user = var1;
   }

   public String getUser() {
      return this.user;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public String getPassword() {
      return this.password;
   }

   public int getLoginTimeout() throws SQLException {
      return this.loginTimeout;
   }

   public void setLoginTimeout(int var1) throws SQLException {
      this.loginTimeout = var1;
   }

   public PrintWriter getLogWriter() throws SQLException {
      return this.printer;
   }

   public void setLogWriter(PrintWriter var1) throws SQLException {
      this.printer = var1;
   }

   protected void update() {
      StringBuilder var1 = new StringBuilder(64);
      var1.append("jdbc:derby:");
      String var2 = this.getDatabaseName();
      if (var2 != null) {
         var2 = var2.trim();
      }

      if (var2 == null || var2.length() == 0) {
         var2 = " ";
      }

      var1.append(var2);
      String var3 = this.getConnectionAttributes();
      if (var3 != null) {
         var3 = var3.trim();
         if (var3.length() != 0) {
            var1.append(';');
            var1.append(this.connectionAttributes);
         }
      }

      this.jdbcurl = var1.toString();
   }

   public void setCreateDatabase(String var1) {
      if (var1 != null && var1.toLowerCase(Locale.ENGLISH).equals("create")) {
         this.createDatabase = var1;
      } else {
         this.createDatabase = null;
      }

   }

   public String getCreateDatabase() {
      return this.createDatabase;
   }

   InternalDriver findDriver() throws SQLException {
      String var1 = this.jdbcurl;
      synchronized(this) {
         if (this.driver == null || !this.driver.acceptsURL(var1)) {
            new EmbeddedDriver();
            Driver var3 = DriverManager.getDriver(var1);
            if (var3 instanceof AutoloadedDriver) {
               this.driver = (InternalDriver)AutoloadedDriver.getDriverModule();
            } else {
               this.driver = (InternalDriver)var3;
            }
         }
      }

      return this.driver;
   }

   public void setConnectionAttributes(String var1) {
      this.connectionAttributes = var1;
      this.update();
   }

   public String getConnectionAttributes() {
      return this.connectionAttributes;
   }

   public void setShutdownDatabase(String var1) {
      if (var1 != null && var1.equalsIgnoreCase("shutdown")) {
         this.shutdownDatabase = var1;
      } else {
         this.shutdownDatabase = null;
      }

   }

   public String getShutdownDatabase() {
      return this.shutdownDatabase;
   }

   public void setAttributesAsPassword(boolean var1) {
      this.attributesAsPassword = var1;
      this.update();
   }

   public boolean getAttributesAsPassword() {
      return this.attributesAsPassword;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof EmbeddedDataSource var2) {
         boolean var3 = true;
         if (this.databaseName != null) {
            if (!this.databaseName.equals(var2.databaseName)) {
               var3 = false;
            }
         } else if (var2.databaseName != null) {
            var3 = false;
         }

         if (this.dataSourceName != null) {
            if (!this.dataSourceName.equals(var2.dataSourceName)) {
               var3 = false;
            }
         } else if (var2.dataSourceName != null) {
            var3 = false;
         }

         if (this.description != null) {
            if (!this.description.equals(var2.description)) {
               var3 = false;
            }
         } else if (var2.description != null) {
            var3 = false;
         }

         if (this.createDatabase != null) {
            if (!this.createDatabase.equals(var2.createDatabase)) {
               var3 = false;
            }
         } else if (var2.createDatabase != null) {
            var3 = false;
         }

         if (this.shutdownDatabase != null) {
            if (!this.shutdownDatabase.equals(var2.shutdownDatabase)) {
               var3 = false;
            }
         } else if (var2.shutdownDatabase != null) {
            var3 = false;
         }

         if (this.connectionAttributes != null) {
            if (!this.connectionAttributes.equals(var2.connectionAttributes)) {
               var3 = false;
            }
         } else if (var2.connectionAttributes != null) {
            var3 = false;
         }

         if (this.loginTimeout != var2.loginTimeout) {
            var3 = false;
         }

         return var3;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = 5;
      var1 = 29 * var1 + (this.description != null ? this.description.hashCode() : 0);
      var1 = 29 * var1 + (this.dataSourceName != null ? this.dataSourceName.hashCode() : 0);
      var1 = 29 * var1 + (this.databaseName != null ? this.databaseName.hashCode() : 0);
      var1 = 29 * var1 + (this.connectionAttributes != null ? this.connectionAttributes.hashCode() : 0);
      var1 = 29 * var1 + (this.createDatabase != null ? this.createDatabase.hashCode() : 0);
      var1 = 29 * var1 + (this.shutdownDatabase != null ? this.shutdownDatabase.hashCode() : 0);
      var1 = 29 * var1 + this.loginTimeout;
      return var1;
   }

   public Connection getConnection() throws SQLException {
      return this.getConnection(this.getUser(), this.getPassword(), false);
   }

   public Connection getConnection(String var1, String var2) throws SQLException {
      return this.getConnection(var1, var2, true);
   }

   public final Connection getConnection(String var1, String var2, boolean var3) throws SQLException {
      Properties var4 = new Properties();
      if (var1 != null) {
         var4.put("user", var1);
      }

      if ((!var3 || !this.attributesAsPassword) && var2 != null) {
         var4.put("password", var2);
      }

      if (this.createDatabase != null) {
         var4.put("create", "true");
      }

      if (this.shutdownDatabase != null) {
         var4.put("shutdown", "true");
      }

      String var5 = this.jdbcurl;
      if (this.attributesAsPassword && var3 && var2 != null) {
         StringBuilder var6 = new StringBuilder(var5.length() + var2.length() + 1);
         var6.append(var5);
         var6.append(';');
         var6.append(var2);
         var5 = var6.toString();
      }

      Connection var7 = this.findDriver().connect(var5, var4, this.loginTimeout);
      if (var7 == null) {
         throw Util.generateCsSQLException("XCY00.S", new Object[]{"databaseName", this.getDatabaseName()});
      } else {
         return var7;
      }
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      return var1.isInstance(this);
   }

   public Object unwrap(Class var1) throws SQLException {
      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw Util.generateCsSQLException("XJ128.S", new Object[]{var1});
      }
   }

   protected static ResourceAdapter setupResourceAdapter(EmbeddedXADataSourceInterface var0, ResourceAdapter var1, String var2, String var3, boolean var4) throws SQLException {
      synchronized(var0) {
         if (var1 == null || !var1.isActive()) {
            var1 = null;
            String var6 = ((BasicEmbeddedDataSource40)var0).getShortDatabaseName();
            if (var6 != null) {
               Database var7 = null;
               if (getMonitor() != null) {
                  var7 = (Database)findService("org.apache.derby.database.Database", var6);
               }

               if (var7 == null) {
                  if (var4) {
                     var0.getConnection(var2, var3).close();
                  } else {
                     var0.getConnection().close();
                  }

                  var7 = (Database)findService("org.apache.derby.database.Database", var6);
               }

               if (var7 != null) {
                  var1 = (ResourceAdapter)var7.getResourceAdapter();
               }
            }

            if (var1 == null) {
               throw new SQLException(MessageService.getTextMessage("I024", new Object[0]), "08006", 45000);
            }

            InternalDriver var10 = ((BasicEmbeddedDataSource40)var0).findDriver();
            if (var10 == null) {
               throw new SQLException(MessageService.getTextMessage("I025", new Object[0]), "08006", 45000);
            }
         }

         return var1;
      }
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw (SQLFeatureNotSupportedException)Util.generateCsSQLException("0A000.S", new Object[]{"getParentLogger"});
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object findService(String var0, String var1) {
      return Monitor.findService(var0, var1);
   }
}
