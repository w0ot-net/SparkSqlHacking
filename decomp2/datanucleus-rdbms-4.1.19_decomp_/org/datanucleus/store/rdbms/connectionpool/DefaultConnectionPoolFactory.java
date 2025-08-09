package org.datanucleus.store.rdbms.connectionpool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class DefaultConnectionPoolFactory implements ConnectionPoolFactory {
   public ConnectionPool createConnectionPool(StoreManager storeMgr) {
      Properties props = AbstractConnectionPoolFactory.getPropertiesForDriver(storeMgr);
      if (props.size() == 2) {
         props = null;
      }

      return new DefaultConnectionPool(new DriverManagerDataSource(storeMgr.getConnectionDriverName(), storeMgr.getConnectionURL(), storeMgr.getConnectionUserName(), storeMgr.getConnectionPassword(), storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null), props));
   }

   public class DefaultConnectionPool implements ConnectionPool {
      final DataSource dataSource;

      public DefaultConnectionPool(DataSource ds) {
         this.dataSource = ds;
      }

      public void close() {
      }

      public DataSource getDataSource() {
         return this.dataSource;
      }
   }

   public static class DriverManagerDataSource implements DataSource {
      private final String driverName;
      private final String url;
      private final ClassLoaderResolver clr;
      private final String userName;
      private final String password;
      private final Properties props;

      public DriverManagerDataSource(String driverName, String url, String userName, String password, ClassLoaderResolver clr, Properties props) {
         this.driverName = driverName;
         this.url = url;
         this.clr = clr;
         this.userName = userName;
         this.password = password;
         this.props = props;
         if (driverName != null) {
            try {
               clr.classForName(driverName).newInstance();
            } catch (Exception e) {
               try {
                  Class.forName(driverName).newInstance();
               } catch (Exception var9) {
                  throw (new NucleusUserException(Localiser.msg("047006", new Object[]{driverName}), e)).setFatal();
               }
            }
         }

      }

      public Connection getConnection() throws SQLException {
         if (StringUtils.isWhitespace(this.driverName)) {
            throw new NucleusUserException(Localiser.msg("047007"));
         } else {
            return this.getConnection(this.userName, this.password);
         }
      }

      public Connection getConnection(String userName, String password) throws SQLException {
         try {
            Properties info = new Properties();
            if (userName != null) {
               info.put("user", this.userName);
            }

            if (password != null) {
               info.put("password", this.password);
            }

            if (this.props != null) {
               info.putAll(this.props);
            }

            return ((Driver)this.clr.classForName(this.driverName).newInstance()).connect(this.url, info);
         } catch (SQLException e) {
            throw e;
         } catch (Exception e) {
            try {
               return DriverManager.getConnection(this.url, this.userName, this.password);
            } catch (Exception var5) {
               throw (new NucleusUserException(Localiser.msg("047006", new Object[]{this.driverName}), e)).setFatal();
            }
         }
      }

      public PrintWriter getLogWriter() {
         return DriverManager.getLogWriter();
      }

      public void setLogWriter(PrintWriter out) {
         DriverManager.setLogWriter(out);
      }

      public int getLoginTimeout() {
         return DriverManager.getLoginTimeout();
      }

      public void setLoginTimeout(int seconds) {
         DriverManager.setLoginTimeout(seconds);
      }

      public boolean equals(Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof DriverManagerDataSource)) {
            return false;
         } else {
            DriverManagerDataSource dmds = (DriverManagerDataSource)obj;
            if (this.driverName == null) {
               if (dmds.driverName != null) {
                  return false;
               }
            } else if (!this.driverName.equals(dmds.driverName)) {
               return false;
            }

            if (this.url == null) {
               if (dmds.url != null) {
                  return false;
               }
            } else if (!this.url.equals(dmds.url)) {
               return false;
            }

            return true;
         }
      }

      public int hashCode() {
         return (this.driverName == null ? 0 : this.driverName.hashCode()) ^ (this.url == null ? 0 : this.url.hashCode());
      }

      public Object unwrap(Class iface) throws SQLException {
         if (!DataSource.class.equals(iface)) {
            throw new SQLException("DataSource of type [" + this.getClass().getName() + "] can only be unwrapped as [javax.sql.DataSource], not as [" + iface.getName() + "]");
         } else {
            return this;
         }
      }

      public boolean isWrapperFor(Class iface) throws SQLException {
         return DataSource.class.equals(iface);
      }

      public Logger getParentLogger() throws SQLFeatureNotSupportedException {
         throw new SQLFeatureNotSupportedException("Not supported");
      }
   }
}
