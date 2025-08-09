package org.apache.derby.shared.common.info;

import java.lang.reflect.Method;

public abstract class JVMInfo {
   private static final boolean _isModuleAware;
   public static final int JDK_ID;
   public static final int J2SE_18 = 9;

   public static int jdbcMajorVersion() {
      return 4;
   }

   public static int jdbcMinorVersion() {
      switch (JDK_ID) {
         case 9:
         default:
            return 2;
      }
   }

   public static String derbyVMLevel() {
      int var10000 = jdbcMajorVersion();
      String var0 = var10000 + "." + jdbcMinorVersion();
      switch (JDK_ID) {
         case 9 -> {
            return "Java SE 8 - JDBC " + var0;
         }
         default -> {
            return "?-?";
         }
      }
   }

   private static String getSystemProperty(String var0) {
      return System.getProperty(var0);
   }

   public static final boolean isSunJVM() {
      String var0 = getSystemProperty("java.vendor");
      return "Sun Microsystems Inc.".equals(var0) || "Oracle Corporation".equals(var0);
   }

   public static final boolean isIBMJVM() {
      return "IBM Corporation".equals(getSystemProperty("java.vendor"));
   }

   public static final boolean isModuleAware() {
      return _isModuleAware;
   }

   public static final String getSystemModulePath() {
      return getSystemProperty("jdk.module.path");
   }

   public static void javaDump() {
      if (isIBMJVM()) {
         Object var0 = null;

         try {
            Class var3 = Class.forName("com.ibm.jvm.Dump");
            Method var1 = var3.getMethod("JavaDump");
            var1.invoke((Object)null);
         } catch (Exception var2) {
         }
      }

   }

   public static boolean hasJNDI() {
      try {
         Class.forName("javax.naming.Referenceable");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   static {
      String var1 = "1.8";
      var1 = System.getProperty("java.specification.version", var1);
      byte var0;
      if (var1.equals("1.8")) {
         var0 = 9;
      } else {
         var0 = 9;

         try {
            String[] var2 = var1.split("[.]");
            int var3 = var2.length >= 1 ? Integer.parseInt(var2[0]) : 0;
            int var4 = var2.length >= 2 ? Integer.parseInt(var2[1]) : 0;
            if (var3 > 1 || var3 == 1 && var4 >= 8) {
               var0 = 9;
            }
         } catch (NumberFormatException var5) {
         }
      }

      JDK_ID = var0;
      _isModuleAware = getSystemModulePath() != null;
   }
}
