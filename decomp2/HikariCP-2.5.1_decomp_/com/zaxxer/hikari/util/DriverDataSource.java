package com.zaxxer.hikari.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverDataSource implements DataSource {
   private static final Logger LOGGER = LoggerFactory.getLogger(DriverDataSource.class);
   private final String jdbcUrl;
   private final Properties driverProperties;
   private Driver driver;

   public DriverDataSource(String jdbcUrl, String driverClassName, Properties properties, String username, String password) {
      this.jdbcUrl = jdbcUrl;
      this.driverProperties = new Properties();

      for(Map.Entry entry : properties.entrySet()) {
         this.driverProperties.setProperty(entry.getKey().toString(), entry.getValue().toString());
      }

      if (username != null) {
         this.driverProperties.put("user", this.driverProperties.getProperty("user", username));
      }

      if (password != null) {
         this.driverProperties.put("password", this.driverProperties.getProperty("password", password));
      }

      if (driverClassName != null) {
         Enumeration<Driver> drivers = DriverManager.getDrivers();

         while(drivers.hasMoreElements()) {
            Driver d = (Driver)drivers.nextElement();
            if (d.getClass().getName().equals(driverClassName)) {
               this.driver = d;
               break;
            }
         }

         if (this.driver == null) {
            LOGGER.warn("Registered driver with driverClassName={} was not found, trying direct instantiation.", driverClassName);

            try {
               Class<?> driverClass = this.getClass().getClassLoader().loadClass(driverClassName);
               this.driver = (Driver)driverClass.newInstance();
            } catch (Exception e) {
               LOGGER.warn("Failed to create instance of driver class {}, trying jdbcUrl resolution", driverClassName, e);
            }
         }
      }

      try {
         if (this.driver == null) {
            this.driver = DriverManager.getDriver(jdbcUrl);
         } else if (!this.driver.acceptsURL(jdbcUrl)) {
            throw new RuntimeException("Driver " + driverClassName + " claims to not accept jdbcUrl, " + jdbcUrl);
         }

      } catch (SQLException e) {
         throw new RuntimeException("Failed to get driver instance for jdbcUrl=" + jdbcUrl, e);
      }
   }

   public Connection getConnection() throws SQLException {
      return this.driver.connect(this.jdbcUrl, this.driverProperties);
   }

   public Connection getConnection(String username, String password) throws SQLException {
      return this.getConnection();
   }

   public PrintWriter getLogWriter() throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }

   public void setLogWriter(PrintWriter logWriter) throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }

   public void setLoginTimeout(int seconds) throws SQLException {
      DriverManager.setLoginTimeout(seconds);
   }

   public int getLoginTimeout() throws SQLException {
      return DriverManager.getLoginTimeout();
   }

   public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
      return this.driver.getParentLogger();
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return false;
   }
}
