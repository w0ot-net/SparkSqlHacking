package org.apache.derby.iapi.jdbc;

import java.security.Permission;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.services.jmx.ManagementService;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.jdbc.EmbedCallableStatement;
import org.apache.derby.impl.jdbc.EmbedConnection;
import org.apache.derby.impl.jdbc.EmbedDatabaseMetaData;
import org.apache.derby.impl.jdbc.EmbedPreparedStatement;
import org.apache.derby.impl.jdbc.EmbedResultSet;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.impl.jdbc.EmbedStatement;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.mbeans.JDBCMBean;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class InternalDriver implements ModuleControl, Driver {
   private static final Object syncMe = new Object();
   private static InternalDriver activeDriver;
   private Object mbean;
   protected boolean active;
   private ContextService contextServiceFactory = getContextService();
   private AuthenticationService authenticationService;
   private static boolean deregister = true;
   private static final ExecutorService _executorPool;
   private static final String[] BOOLEAN_CHOICES;

   public static final InternalDriver activeDriver() {
      return activeDriver;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      synchronized(syncMe) {
         activeDriver = this;
      }

      this.active = true;
      this.mbean = ((ManagementService)getSystemModule("org.apache.derby.iapi.services.jmx.ManagementService")).registerMBean(new JDBC(this), JDBCMBean.class, "type=JDBC");
      AutoloadedDriver.registerDriverModule(this);
   }

   public void stop() {
      synchronized(syncMe) {
         activeDriver = null;
      }

      ((ManagementService)getSystemModule("org.apache.derby.iapi.services.jmx.ManagementService")).unregisterMBean(this.mbean);
      this.active = false;
      this.contextServiceFactory = null;
      AutoloadedDriver.unregisterDriverModule();
   }

   public boolean acceptsURL(String var1) throws SQLException {
      return this.active && embeddedDriverAcceptsURL(var1);
   }

   public static boolean embeddedDriverAcceptsURL(String var0) throws SQLException {
      if (var0 == null) {
         throw Util.generateCsSQLException("XJ028.C", "null");
      } else {
         return !var0.startsWith("jdbc:derby:net:") && !var0.startsWith("jdbc:derby://") && (var0.startsWith("jdbc:derby:") || var0.equals("jdbc:default:connection"));
      }
   }

   public Connection connect(String var1, Properties var2, int var3) throws SQLException {
      if (!this.acceptsURL(var1)) {
         return null;
      } else if (EmbedConnection.memoryState.isLowMemory()) {
         throw EmbedConnection.NO_MEM;
      } else {
         boolean var4 = var1.equals("jdbc:default:connection");
         if (var4) {
            ConnectionContext var15 = this.getConnectionContext();
            return var15 != null ? var15.getNestedConnection(false) : null;
         } else {
            FormatableProperties var5 = null;

            Connection var8;
            try {
               var5 = this.getAttributes(var1, var2);
               Object var14 = null;
               boolean var6 = Boolean.valueOf(var5.getProperty("shutdown"));
               if (var6 && getDatabaseName(var1, var5).length() == 0) {
                  if (this.getAuthenticationService() == null) {
                     throw Util.generateCsSQLException("08004", MessageService.getTextMessage("A001", new Object[0]));
                  }

                  if (!this.getAuthenticationService().authenticate((String)null, var5)) {
                     throw Util.generateCsSQLException("08004.C.1", MessageService.getTextMessage("A020", new Object[0]));
                  }

                  if (var5.getProperty("deregister") != null) {
                     boolean var16 = Boolean.valueOf(var5.getProperty("deregister"));
                     setDeregister(var16);
                  }

                  getMonitor().shutdown();
                  throw Util.generateCsSQLException("XJ015.M");
               }

               EmbedConnection var7;
               if (var3 <= 0) {
                  var7 = this.getNewEmbedConnection(var1, var5);
               } else {
                  var7 = this.timeLogin(var1, var5, var3);
               }

               if (!var7.isClosed()) {
                  var8 = var7;
                  return var8;
               }

               var8 = null;
            } catch (OutOfMemoryError var12) {
               EmbedConnection.memoryState.setLowMemory();
               throw EmbedConnection.NO_MEM;
            } finally {
               if (var5 != null) {
                  var5.clearDefaults();
               }

            }

            return var8;
         }
      }
   }

   private EmbedConnection timeLogin(String var1, Properties var2, int var3) throws SQLException {
      try {
         LoginCallable var4 = new LoginCallable(this, var1, var2);
         Future var5 = _executorPool.submit(var4);
         long var6 = System.currentTimeMillis();
         long var8 = var6 + (long)var3 * 1000L;

         while(var6 < var8) {
            try {
               EmbedConnection var10 = (EmbedConnection)var5.get(var8 - var6, TimeUnit.MILLISECONDS);
               return var10;
            } catch (InterruptedException var16) {
               InterruptStatus.setInterrupted();
               var6 = System.currentTimeMillis();
            } catch (ExecutionException var17) {
               throw this.processException(var17);
            } catch (TimeoutException var18) {
               throw Util.generateCsSQLException("XBDA0.C.1");
            }
         }

         throw Util.generateCsSQLException("XBDA0.C.1");
      } finally {
         InterruptStatus.restoreIntrFlagIfSeen();
      }
   }

   private SQLException processException(Throwable var1) {
      Throwable var2 = var1.getCause();
      return var2 instanceof SQLException ? (SQLException)var2 : Util.javaException(var1);
   }

   public void checkSystemPrivileges(String var1, Permission var2) {
      SecurityUtil.checkUserHasPermission(var1, var2);
   }

   private void checkShutdownPrivileges(String var1) throws SQLException {
   }

   public int getMajorVersion() {
      return getMonitor().getEngineVersion().getMajorVersion();
   }

   public int getMinorVersion() {
      return getMonitor().getEngineVersion().getMinorVersion();
   }

   public boolean jdbcCompliant() {
      return true;
   }

   protected FormatableProperties getAttributes(String var1, Properties var2) throws SQLException {
      FormatableProperties var3 = new FormatableProperties(var2);
      Object var7 = null;
      StringTokenizer var4 = new StringTokenizer(var1, ";");
      var4.nextToken();

      while(var4.hasMoreTokens()) {
         String var5 = var4.nextToken();
         int var6 = var5.indexOf(61);
         if (var6 == -1) {
            throw Util.generateCsSQLException("XJ028.C", var1);
         }

         var3.put(var5.substring(0, var6).trim(), var5.substring(var6 + 1).trim());
      }

      checkBoolean(var3, "dataEncryption");
      checkBoolean(var3, "create");
      checkBoolean(var3, "shutdown");
      checkBoolean(var3, "deregister");
      checkBoolean(var3, "upgrade");
      return var3;
   }

   private static void checkBoolean(Properties var0, String var1) throws SQLException {
      String[] var2 = new String[]{"true", "false"};
      checkEnumeration(var0, var1, var2);
   }

   private static void checkEnumeration(Properties var0, String var1, String[] var2) throws SQLException {
      String var3 = var0.getProperty(var1);
      if (var3 != null) {
         for(int var4 = 0; var4 < var2.length; ++var4) {
            if (var3.toUpperCase(Locale.ENGLISH).equals(var2[var4].toUpperCase(Locale.ENGLISH))) {
               return;
            }
         }

         String var6 = "{";

         for(int var5 = 0; var5 < var2.length; ++var5) {
            if (var5 > 0) {
               var6 = var6 + "|";
            }

            var6 = var6 + var2[var5];
         }

         throw Util.generateCsSQLException("XJ05B.C", var1, var3, var6 + "}");
      }
   }

   public static String getDatabaseName(String var0, Properties var1) {
      if (var0.equals("jdbc:default:connection")) {
         return "";
      } else {
         int var2 = var0.indexOf(59);
         String var3;
         if (var2 == -1) {
            var3 = var0.substring("jdbc:derby:".length());
         } else {
            var3 = var0.substring("jdbc:derby:".length(), var2);
         }

         if (var3.length() == 0 && var1 != null) {
            var3 = var1.getProperty("databaseName", var3);
         }

         var3 = var3.trim();
         return var3;
      }
   }

   public final ContextService getContextServiceFactory() {
      return this.contextServiceFactory;
   }

   public AuthenticationService getAuthenticationService() {
      if (this.authenticationService == null) {
         this.authenticationService = (AuthenticationService)findService("org.apache.derby.iapi.jdbc.AuthenticationService", "authentication");
      }

      return this.authenticationService;
   }

   EmbedConnection getNewEmbedConnection(String var1, Properties var2) throws SQLException {
      return new EmbedConnection(this, var1, var2);
   }

   private ConnectionContext getConnectionContext() {
      ContextManager var1 = this.getCurrentContextManager();
      ConnectionContext var2 = null;
      if (var1 != null) {
         var2 = (ConnectionContext)var1.getContext("JDBC_ConnectionContext");
      }

      return var2;
   }

   private ContextManager getCurrentContextManager() {
      return this.getContextServiceFactory().getCurrentContextManager();
   }

   public boolean isActive() {
      return this.active;
   }

   public Connection getNewNestedConnection(EmbedConnection var1) {
      return new EmbedConnection(var1);
   }

   public Statement newEmbedStatement(EmbedConnection var1, boolean var2, int var3, int var4, int var5) {
      return new EmbedStatement(var1, var2, var3, var4, var5);
   }

   public PreparedStatement newEmbedPreparedStatement(EmbedConnection var1, String var2, boolean var3, int var4, int var5, int var6, int var7, int[] var8, String[] var9) throws SQLException {
      return new EmbedPreparedStatement(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public CallableStatement newEmbedCallableStatement(EmbedConnection var1, String var2, int var3, int var4, int var5) throws SQLException {
      return new EmbedCallableStatement(var1, var2, var3, var4, var5);
   }

   public DatabaseMetaData newEmbedDatabaseMetaData(EmbedConnection var1, String var2) throws SQLException {
      return new EmbedDatabaseMetaData(var1, var2);
   }

   public EmbedResultSet newEmbedResultSet(EmbedConnection var1, ResultSet var2, boolean var3, EmbedStatement var4, boolean var5) throws SQLException {
      return new EmbedResultSet(var1, var2, var3, var4, var5);
   }

   public EmbedResultSetMetaData newEmbedResultSetMetaData(ResultColumnDescriptor[] var1) {
      return new EmbedResultSetMetaData(var1);
   }

   public BrokeredConnection newBrokeredConnection(BrokeredConnectionControl var1) throws SQLException {
      return new BrokeredConnection(var1);
   }

   public DriverPropertyInfo[] getPropertyInfo(String var1, Properties var2) throws SQLException {
      if (var2 != null && Boolean.valueOf(var2.getProperty("shutdown"))) {
         return new DriverPropertyInfo[0];
      } else {
         String var3 = getDatabaseName(var1, var2);
         FormatableProperties var4 = this.getAttributes(var1, var2);
         Object var13 = null;
         boolean var5 = Boolean.valueOf(var4.getProperty("dataEncryption"));
         String var6 = var4.getProperty("bootPassword");
         if (var3.length() == 0 || var5 && var6 == null) {
            String[][] var7 = new String[][]{{"databaseName", "J004"}, {"encryptionProvider", "J016"}, {"encryptionAlgorithm", "J017"}, {"encryptionKeyLength", "J018"}, {"encryptionKey", "J019"}, {"territory", "J021"}, {"collation", "J031"}, {"user", "J022"}, {"logDevice", "J025"}, {"rollForwardRecoveryFrom", "J028"}, {"createFrom", "J029"}, {"restoreFrom", "J030"}};
            String[][] var8 = new String[][]{{"shutdown", "J005"}, {"deregister", "J006"}, {"create", "J007"}, {"dataEncryption", "J010"}, {"upgrade", "J013"}};
            String[][] var9 = new String[][]{{"bootPassword", "J020"}, {"password", "J023"}};
            DriverPropertyInfo[] var10 = new DriverPropertyInfo[var7.length + var8.length + var9.length];
            int var11 = 0;

            for(int var12 = 0; var12 < var7.length; ++var11) {
               var10[var11] = new DriverPropertyInfo(var7[var12][0], var4.getProperty(var7[var12][0]));
               var10[var11].description = MessageService.getTextMessage(var7[var12][1], new Object[0]);
               ++var12;
            }

            var10[0].choices = getMonitor().getServiceList("org.apache.derby.database.Database");
            var10[0].value = var3;

            for(int var14 = 0; var14 < var9.length; ++var11) {
               var10[var11] = new DriverPropertyInfo(var9[var14][0], var4.getProperty(var9[var14][0]) == null ? "" : "****");
               var10[var11].description = MessageService.getTextMessage(var9[var14][1], new Object[0]);
               ++var14;
            }

            for(int var15 = 0; var15 < var8.length; ++var11) {
               var10[var11] = new DriverPropertyInfo(var8[var15][0], Boolean.valueOf(var4 == null ? "" : var4.getProperty(var8[var15][0])).toString());
               var10[var11].description = MessageService.getTextMessage(var8[var15][1], new Object[0]);
               var10[var11].choices = BOOLEAN_CHOICES;
               ++var15;
            }

            return var10;
         } else {
            return new DriverPropertyInfo[0];
         }
      }
   }

   public Connection connect(String var1, Properties var2) throws SQLException {
      return this.connect(var1, var2, DriverManager.getLoginTimeout());
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw (SQLFeatureNotSupportedException)Util.notImplemented("getParentLogger()");
   }

   public static void setDeregister(boolean var0) {
      deregister = var0;
   }

   public static boolean getDeregister() {
      return deregister;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object getSystemModule(String var0) {
      return Monitor.getSystemModule(var0);
   }

   private static Object findService(String var0, String var1) {
      return Monitor.findService(var0, var1);
   }

   static {
      _executorPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue(), new DaemonThreadFactory());
      BOOLEAN_CHOICES = new String[]{"false", "true"};
   }

   private static final class DaemonThreadFactory implements ThreadFactory {
      public Thread newThread(Runnable var1) {
         Thread var2 = new Thread(var1);
         var2.setDaemon(true);
         return var2;
      }
   }

   public static final class LoginCallable implements Callable {
      private InternalDriver _driver;
      private String _url;
      private Properties _info;

      public LoginCallable(InternalDriver var1, String var2, Properties var3) {
         this._driver = var1;
         this._url = var2;
         this._info = var3;
      }

      public EmbedConnection call() throws SQLException {
         String var1 = this._url;
         Properties var2 = this._info;
         InternalDriver var3 = this._driver;
         this._url = null;
         this._info = null;
         this._driver = null;
         return var3.getNewEmbedConnection(var1, var2);
      }
   }
}
