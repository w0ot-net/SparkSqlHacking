package org.apache.derby.impl.tools.ij;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import javax.sql.DataSource;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.reference.ModuleUtil;
import org.apache.derby.tools.JDBCDisplayUtil;

public final class util {
   private static final Class[] STRING_P = new Class[]{"".getClass()};
   private static final Class[] INT_P;
   private String key;
   private static final String[][] protocolDrivers;

   private util() {
   }

   public static String getArg(String var0, String[] var1) {
      if (var1 == null) {
         return null;
      } else {
         int var2;
         for(var2 = 0; var2 < var1.length && !var0.equals(var1[var2]); ++var2) {
         }

         return var2 >= var1.length - 1 ? null : var1[var2 + 1];
      }
   }

   public static boolean getPropertyArg(String[] var0) throws IOException {
      String var1;
      Object var5;
      if ((var1 = getArg("-p", var0)) != null) {
         FileInputStream var2 = new FileInputStream(var1);
         var5 = new BufferedInputStream(var2);
      } else {
         if ((var1 = getArg("-pr", var0)) == null) {
            return false;
         }

         var5 = getResourceAsStream(var1);
         if (var5 == null) {
            throw ijException.resourceNotFound();
         }
      }

      Properties var3 = System.getProperties();
      loadWithTrimmedValues((InputStream)var5, var3);
      return true;
   }

   static String qualifyResourceName(String var0, boolean var1) {
      var0 = var0.trim();
      if (var0.startsWith("/")) {
         return var0;
      } else {
         String var2 = getSystemProperty("ij.defaultResourcePackage").trim();
         if (var2 == null) {
            return null;
         } else {
            if (var2.endsWith("/")) {
               var0 = var2 + var0;
            } else {
               var0 = var2 + "/" + var0;
            }

            return var1 && !var0.startsWith("/") ? null : var0;
         }
      }
   }

   static InputStream getResourceAsStream(String var0) {
      boolean var1 = JVMInfo.isModuleAware();
      Class var2 = util.class;
      String var3 = qualifyResourceName(var0, true);
      if (var3 == null) {
         return null;
      } else {
         Object var4 = null;
         if (var1) {
            try {
               var4 = ModuleUtil.getResourceAsStream(var3);
            } catch (Exception var6) {
               System.out.println(var6.getMessage());
            }
         } else {
            var4 = var2.getResourceAsStream(var3);
         }

         if (var4 != null) {
            var4 = new BufferedInputStream((InputStream)var4, 2048);
         }

         return (InputStream)var4;
      }
   }

   public static String getFileArg(String[] var0) throws IOException {
      boolean var3 = false;
      if (var0 == null) {
         return null;
      } else {
         String var1;
         if ((var1 = getArg("-f", var0)) != null) {
            return var1;
         } else {
            for(int var4 = 0; var4 < var0.length; ++var4) {
               if (!var0[var4].equals("-f") && !var0[var4].equals("-fr") && !var0[var4].equals("-p") && !var0[var4].equals("-pr")) {
                  return var0[var4];
               }

               ++var4;
            }

            return null;
         }
      }
   }

   public static String getInputResourceNameArg(String[] var0) {
      return getArg("-fr", var0);
   }

   public static boolean invalidArgs(String[] var0) {
      boolean var1 = false;
      boolean var2 = false;

      for(int var3 = 0; var3 < var0.length; ++var3) {
         if (var2 || !var0[var3].equals("-f") && !var0[var3].equals("-fr")) {
            if (!var0[var3].equals("-p") && !var0[var3].equals("-pr")) {
               if (var0[var3].equals("--help")) {
                  return true;
               }

               if (var2) {
                  return true;
               }

               var2 = true;
            } else {
               ++var3;
               if (var3 >= var0.length) {
                  return true;
               }
            }
         } else {
            var2 = true;
            ++var3;
            if (var3 >= var0.length) {
               return true;
            }
         }
      }

      return false;
   }

   static void Usage(LocalizedOutput var0) {
      var0.println(LocalizedResource.getMessage("IJ_UsageJavaComCloudToolsIjPPropeInput"));
      var0.flush();
   }

   public static void setupDataSource(Object var0, String var1, boolean var2) throws Exception {
      Method[] var3 = var0.getClass().getMethods();

      for(int var4 = 0; var4 < var3.length; ++var4) {
         Method var5 = var3[var4];
         String var6 = var5.getName();
         if (var6.startsWith("set") && var6.length() > "set".length() && (!var6.equals("setCreateDatabase") || var2)) {
            String var7 = var6.substring("set".length());
            String var10000 = var7.substring(0, 1).toLowerCase(Locale.ENGLISH);
            var7 = "ij.dataSource." + var10000 + var7.substring(1);
            String var8 = getSystemProperty(var7);
            if (var6.equals("setDatabaseName") && !var2) {
               var8 = var1;
            }

            if (var8 != null) {
               try {
                  var5.invoke(var0, var8);
               } catch (Throwable var10) {
                  var5.invoke(var0, Integer.valueOf(var8));
               }
            }
         }
      }

   }

   public static Connection getDataSourceConnection(String var0, String var1, String var2, String var3, boolean var4) throws SQLException {
      try {
         Class var6 = Class.forName(var0);
         if (DataSource.class.isAssignableFrom(var6)) {
            DataSource var5 = (DataSource)var6.getConstructor().newInstance();
            setupDataSource(var5, var3, var4);
            return var1 == null ? var5.getConnection() : var5.getConnection(var1, var2);
         }

         throw new ijException(LocalizedResource.getMessage("TL_notInstanceOf", var0, DataSource.class.getName()));
      } catch (InvocationTargetException var7) {
         if (var7.getTargetException() instanceof SQLException) {
            throw (SQLException)var7.getTargetException();
         }

         var7.printStackTrace(System.out);
      } catch (Exception var8) {
         var8.printStackTrace(System.out);
      }

      return null;
   }

   public static Connection startJBMS(String var0, String var1, Properties var2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Object var3 = null;
      String var4 = getSystemProperty("driver");
      if (var4 == null) {
         var4 = getSystemProperty("ij.driver");
      }

      if (var4 == null || var4.length() == 0) {
         var4 = var0;
      }

      if (var4 != null) {
         loadDriver(var4);
      }

      String var6 = getSystemProperty("ij.protocol");
      if (var6 != null) {
         loadDriverIfKnown(var6);
      }

      String var7 = getSystemProperty("ij.user");
      String var8 = getSystemProperty("ij.password");
      String var5 = getSystemProperty("database");
      if (var5 == null) {
         var5 = getSystemProperty("ij.database");
      }

      if (var5 == null || var5.length() == 0) {
         var5 = var1;
      }

      if (var5 != null) {
         if (var5.startsWith("jdbc:")) {
            loadDriverIfKnown(var5);
         }

         if (!var5.startsWith("jdbc:") && var6 != null) {
            var5 = var6 + var5;
         }

         var2 = updateConnInfo(var7, var8, var2);
         String var13 = getSystemProperty("driver");
         if (var13 == null) {
            var13 = "org.apache.derby.jdbc.EmbeddedDriver";
         }

         loadDriver(var13);
         Connection var12 = DriverManager.getConnection(var5, var2);
         return var12;
      } else {
         String var9 = getSystemProperty("ij.dataSource");
         if (var9 == null) {
            return null;
         } else {
            Connection var11 = getDataSourceConnection(var9, var7, var8, (String)null, true);
            return var11;
         }
      }
   }

   public static Properties updateConnInfo(String var0, String var1, Properties var2) {
      String var3 = getSystemProperty("ij.retrieveMessagesFromServerOnGetMessage");
      boolean var4 = false;
      if (isJCCFramework()) {
         var4 = true;
      }

      if (var3 != null) {
         if (var3.equals("false")) {
            var4 = false;
         } else {
            var4 = true;
         }
      }

      if (var2 == null) {
         var2 = new Properties();
      }

      if (var4) {
         var2.put("retrieveMessagesFromServerOnGetMessage", "true");
      }

      if (var0 != null) {
         var2.put("user", var0);
      }

      if (var1 != null) {
         var2.put("password", var1);
      }

      return var2;
   }

   public static Connection startJBMS() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      return startJBMS((String)null, (String)null);
   }

   public static Connection startJBMS(String var0, String var1) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      return startJBMS(var0, var1, (Properties)null);
   }

   public static void DisplayVector(LocalizedOutput var0, Vector var1) {
      int var2 = var1.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         var0.println(var1.elementAt(var3));
      }

   }

   public static void DisplayMulti(LocalizedOutput var0, PreparedStatement var1, ResultSet var2, Connection var3) throws SQLException, ijException {
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      ResultSetMetaData var7 = var2.getMetaData();
      int var8 = var7.getColumnCount();
      var6 = var2.next();

      while(!var4 && var6) {
         if (!var5) {
            var5 = true;
            if (var3.getAutoCommit()) {
               var0.println(LocalizedResource.getMessage("IJ_IjWarniAutocMayCloseUsingResulSet"));
               var4 = true;
            }
         }

         for(int var9 = 1; var9 <= var8; ++var9) {
            int var10 = var7.getColumnType(var9);
            if (var10 == 3) {
               var1.setObject(var9, var2.getObject(var9), var10, var7.getScale(var9));
            } else {
               var1.setObject(var9, var2.getObject(var9), var10);
            }
         }

         var6 = var2.next();
         if (!var6 || var3.getAutoCommit()) {
            var2.close();
         }

         var1.execute();
         JDBCDisplayUtil.DisplayResults((PrintWriter)var0, (Statement)var1, var3);
         var1.clearParameters();
      }

      if (!var5) {
         var2.close();
         throw ijException.noUsingResults();
      }
   }

   static final String getSystemProperty(String var0) {
      if (!var0.startsWith("ij.") && !var0.startsWith("derby.")) {
         return System.getProperty(var0);
      } else {
         util var1 = new util();
         var1.key = var0;
         return var1.run();
      }
   }

   public final String run() {
      return System.getProperty(this.key);
   }

   private static void loadWithTrimmedValues(InputStream var0, Properties var1) throws IOException {
      Properties var2 = new Properties();
      var2.load(var0);
      Enumeration var3 = var2.propertyNames();

      while(var3.hasMoreElements()) {
         String var4 = (String)var3.nextElement();
         String var5 = var2.getProperty(var4);
         var5 = var5.trim();
         var1.put(var4, var5);
      }

   }

   public static void loadDriverIfKnown(String var0) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      for(int var1 = 0; var1 < protocolDrivers.length; ++var1) {
         if (var0.startsWith(protocolDrivers[var1][0])) {
            loadDriver(protocolDrivers[var1][1]);
            break;
         }
      }

   }

   static void loadDriver(String var0) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class var1 = Class.forName(var0);
      if (Driver.class.isAssignableFrom(var1)) {
         var1.getConstructor().newInstance();
      } else {
         throw new ijException(LocalizedResource.getMessage("TL_notInstanceOf", var0, Driver.class.getName()));
      }
   }

   private static boolean isJCCFramework() {
      String var0 = getSystemProperty("framework");
      return var0 != null && (var0.toUpperCase(Locale.ENGLISH).equals("DERBYNET") || var0.toUpperCase(Locale.ENGLISH).indexOf("JCC") != -1);
   }

   public static String getSelectedSchema(Connection var0) throws SQLException {
      String var1 = null;
      if (var0 == null) {
         return null;
      } else {
         Statement var2 = var0.createStatement();

         Object var3;
         try {
            if (var2.execute("VALUES CURRENT SCHEMA")) {
               ResultSet var10 = var2.getResultSet();
               if (var10 != null && var10.next()) {
                  var1 = var10.getString(1);
                  return var1;
               }

               Object var4 = null;
               return (String)var4;
            }

            var3 = null;
         } catch (SQLException var8) {
            return var1;
         } finally {
            var2.close();
         }

         return (String)var3;
      }
   }

   static {
      INT_P = new Class[]{Integer.TYPE};
      protocolDrivers = new String[][]{{"jdbc:derby:net:", "com.ibm.db2.jcc.DB2Driver"}, {"jdbc:derby://", "org.apache.derby.jdbc.ClientDriver"}, {"jdbc:derby:", "org.apache.derby.jdbc.EmbeddedDriver"}};
   }
}
