package org.apache.derby.iapi.services.property;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class PropertyUtil {
   private static final String[] servicePropertyList = new String[]{"derby.engineType", "derby.database.noAutoBoot", "derby.storage.tempDirectory", "encryptionProvider", "encryptionAlgorithm", "restoreFrom", "logDevice", "derby.storage.logArchiveMode"};
   public static final int SET_IN_JVM = 0;
   public static final int SET_IN_DATABASE = 1;
   public static final int SET_IN_APPLICATION = 2;
   public static final int NOT_SET = -1;

   static int whereSet(String var0, Dictionary var1) {
      boolean var2 = isDBOnly(var1);
      if (!var2 && getMonitor().getJVMProperty(var0) != null) {
         return 0;
      } else if (var1 != null && var1.get(var0) != null) {
         return 1;
      } else {
         return !var2 && getSystemProperty(var0) != null ? 2 : -1;
      }
   }

   public static boolean isDBOnly(Dictionary var0) {
      if (var0 == null) {
         return false;
      } else {
         String var1 = (String)var0.get("derby.database.propertiesOnly");
         boolean var2 = Boolean.valueOf(var1 != null ? var1.trim() : null);
         return var2;
      }
   }

   public static boolean isDBOnly(Properties var0) {
      if (var0 == null) {
         return false;
      } else {
         String var1 = var0.getProperty("derby.database.propertiesOnly");
         boolean var2 = Boolean.valueOf(var1 != null ? var1.trim() : var1);
         return var2;
      }
   }

   public static String[] getServicePropertyList() {
      return (String[])ArrayUtil.copy(servicePropertyList);
   }

   public static String getSystemProperty(String var0) {
      return getSystemProperty(var0, (String)null);
   }

   public static String getSystemProperty(String var0, String var1) {
      ModuleFactory var2 = getMonitorLite();
      String var3 = var2.getJVMProperty(var0);
      if (var3 == null) {
         Properties var4 = var2.getApplicationProperties();
         if (var4 != null) {
            var3 = var4.getProperty(var0);
         }
      }

      return var3 == null ? var1 : var3;
   }

   public static String getPropertyFromSet(Properties var0, String var1) {
      boolean var2 = var0 != null ? isDBOnly(var0) : false;
      if ("derby.authentication.provider".equals(var1)) {
         String var3 = getPropertyFromSet(true, var0, var1);
         if (nativeAuthenticationEnabled(var3)) {
            return var3;
         }
      }

      return getPropertyFromSet(var2, var0, var1);
   }

   public static Serializable getPropertyFromSet(Dictionary var0, String var1) {
      boolean var2 = var0 != null ? isDBOnly(var0) : false;
      return getPropertyFromSet(var2, var0, var1);
   }

   public static Serializable getPropertyFromSet(boolean var0, Dictionary var1, String var2) {
      if (var1 != null) {
         if (!var0) {
            String var3 = getMonitor().getJVMProperty(var2);
            if (var3 != null) {
               return var3;
            }
         }

         Serializable var4 = (Serializable)var1.get(var2);
         if (var4 != null) {
            return var4;
         }

         if (var0) {
            return null;
         }
      }

      return getSystemProperty(var2);
   }

   public static String getPropertyFromSet(boolean var0, Properties var1, String var2) {
      if (var1 != null) {
         if (!var0) {
            String var3 = getMonitor().getJVMProperty(var2);
            if (var3 != null) {
               return var3;
            }
         }

         String var4 = var1.getProperty(var2);
         if (var4 != null) {
            return var4;
         }

         if (var0) {
            return null;
         }
      }

      return getSystemProperty(var2);
   }

   public static String getDatabaseProperty(PersistentSet var0, String var1) throws StandardException {
      if (var0 == null) {
         return null;
      } else {
         Serializable var2 = var0.getProperty(var1);
         return var2 == null ? null : var2.toString();
      }
   }

   public static String getServiceProperty(PersistentSet var0, String var1, String var2) throws StandardException {
      String var3 = getDatabaseProperty(var0, "derby.database.propertiesOnly");
      boolean var4 = Boolean.valueOf(var3 != null ? var3.trim() : var3);
      if (!var4) {
         var3 = getMonitor().getJVMProperty(var1);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = getDatabaseProperty(var0, var1);
      if (var3 != null) {
         return var3;
      } else {
         return var4 ? var2 : getSystemProperty(var1, var2);
      }
   }

   public static String getServiceProperty(PersistentSet var0, String var1) throws StandardException {
      return getServiceProperty(var0, var1, (String)null);
   }

   public static boolean getSystemBoolean(String var0) {
      return getSystemBoolean(var0, false);
   }

   public static boolean getSystemBoolean(String var0, boolean var1) {
      String var2 = getSystemProperty(var0);
      return var2 == null ? var1 : Boolean.valueOf(var2.trim());
   }

   public static boolean getServiceBoolean(PersistentSet var0, String var1, boolean var2) throws StandardException {
      String var3 = getServiceProperty(var0, var1);
      return booleanProperty(var1, var3, var2);
   }

   public static int getSystemInt(String var0, int var1, int var2, int var3) {
      return handleInt(getSystemProperty(var0), var1, var2, var3);
   }

   public static int getServiceInt(PersistentSet var0, String var1, int var2, int var3, int var4) throws StandardException {
      return handleInt(getServiceProperty(var0, var1), var2, var3, var4);
   }

   public static int getServiceInt(PersistentSet var0, Properties var1, String var2, int var3, int var4, int var5) throws StandardException {
      String var6 = null;
      if (var1 != null) {
         var6 = var1.getProperty(var2);
      }

      if (var6 == null) {
         var6 = getServiceProperty(var0, var2);
      }

      return handleInt(var6, var3, var4, var5);
   }

   public static int getSystemInt(String var0, int var1) {
      return getSystemInt(var0, 0, Integer.MAX_VALUE, var1);
   }

   public static int handleInt(String var0, int var1, int var2, int var3) {
      if (var0 == null) {
         return var3;
      } else {
         try {
            int var4 = Integer.parseInt(var0);
            if (var4 >= var1 && var4 <= var2) {
               return var4;
            }
         } catch (NumberFormatException var5) {
         }

         return var3;
      }
   }

   public static boolean booleanProperty(String var0, Serializable var1, boolean var2) throws StandardException {
      if (var1 == null) {
         return var2;
      } else {
         String var3 = ((String)var1).trim();
         if ("TRUE".equals(StringUtil.SQLToUpperCase(var3))) {
            return true;
         } else if ("FALSE".equals(StringUtil.SQLToUpperCase(var3))) {
            return false;
         } else {
            throw StandardException.newException("XCY00.S", new Object[]{var0, var3});
         }
      }
   }

   public static int intPropertyValue(String var0, Serializable var1, int var2, int var3, int var4) throws StandardException {
      if (var1 == null) {
         return var4;
      } else {
         String var5 = ((String)var1).trim();

         try {
            int var6 = Integer.parseInt(var5);
            if (var6 >= var2 && var6 <= var3) {
               return var6;
            } else {
               throw StandardException.newException("XCY00.S", new Object[]{var0, var5});
            }
         } catch (NumberFormatException var7) {
            throw StandardException.newException("XCY00.S", new Object[]{var0, var5});
         }
      }
   }

   public static boolean isServiceProperty(String var0) {
      for(int var1 = 0; var1 < servicePropertyList.length; ++var1) {
         if (var0.equals(servicePropertyList[var1])) {
            return true;
         }
      }

      return false;
   }

   public static boolean existsBuiltinUser(PersistentSet var0, String var1) throws StandardException {
      if (propertiesContainsBuiltinUser(var0.getProperties(), var1)) {
         return true;
      } else {
         boolean var2 = false;
         var2 = Boolean.valueOf(getDatabaseProperty(var0, "derby.database.propertiesOnly"));
         return !var2 && systemPropertiesExistsBuiltinUser(var1);
      }
   }

   public static boolean nativeAuthenticationEnabled(Properties var0) {
      String var1 = getPropertyFromSet(var0, "derby.authentication.provider");
      return nativeAuthenticationEnabled(var1);
   }

   private static boolean nativeAuthenticationEnabled(String var0) {
      return var0 == null ? false : StringUtil.SQLToUpperCase(var0).startsWith("NATIVE:");
   }

   public static boolean localNativeAuthenticationEnabled(Properties var0) {
      if (!nativeAuthenticationEnabled(var0)) {
         return false;
      } else {
         String var1 = getPropertyFromSet(var0, "derby.authentication.provider");
         return StringUtil.SQLToUpperCase(var1).endsWith(":LOCAL");
      }
   }

   private static boolean systemPropertiesExistsBuiltinUser(String var0) {
      ModuleFactory var1 = getMonitorLite();
      Properties var2 = System.getProperties();
      if (propertiesContainsBuiltinUser(var2, var0)) {
         return true;
      } else {
         Properties var3 = var1.getApplicationProperties();
         return propertiesContainsBuiltinUser(var3, var0);
      }
   }

   private static boolean propertiesContainsBuiltinUser(Properties var0, String var1) {
      if (var0 != null) {
         Enumeration var2 = var0.propertyNames();

         while(var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            if (var3.startsWith("derby.user.")) {
               String var4 = StringUtil.normalizeSQLIdentifier(var3.substring("derby.user.".length()));
               if (var1.equals(var4)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static ModuleFactory getMonitorLite() {
      return Monitor.getMonitorLite();
   }
}
