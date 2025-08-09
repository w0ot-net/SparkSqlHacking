package org.apache.derby.shared.common.i18n;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.reference.ModuleUtil;

public final class MessageService {
   private static final Locale EN = new Locale("en", "US");
   private static final String LOCALE_STUB = "locale_";
   private static final String CLIENT_MESSAGES = "clientmessages";
   private static final String TOOLS_MESSAGES = "toolsmessages";
   private static final String SYSINFO_MESSAGES = "sysinfoMessages";
   private static final String SERVER_MESSAGES = "drda";
   private static BundleFinder finder;

   private MessageService() {
   }

   public static ResourceBundle getBundleForLocale(Locale var0, String var1) {
      try {
         return getBundleWithEnDefault("org.apache.derby.loc.m" + hashString50(var1), var0);
      } catch (MissingResourceException var3) {
         return null;
      }
   }

   public static void setFinder(BundleFinder var0) {
      finder = var0;
   }

   public static String getTextMessage(String var0, Object... var1) {
      try {
         return formatMessage(getBundle(var0), var0, var1, true);
      } catch (MissingResourceException var3) {
      } catch (ShutdownException var4) {
      }

      return formatMessage(getBundleForLocale(EN, var0), var0, var1, false);
   }

   public static String getProperty(String var0, String var1) {
      ResourceBundle var2 = getBundle(var0);

      try {
         if (var2 != null) {
            return var2.getString(var0.concat(".").concat(var1));
         }
      } catch (MissingResourceException var4) {
      }

      return null;
   }

   public static String formatMessage(ResourceBundle var0, String var1, Object[] var2, boolean var3) {
      if (var2 == null) {
         var2 = new Object[0];
      }

      if (var0 != null) {
         try {
            var1 = var0.getString(var1);

            try {
               return MessageFormat.format(var1, var2);
            } catch (IllegalArgumentException var7) {
            } catch (NullPointerException var8) {
            }
         } catch (MissingResourceException var9) {
            if (var3) {
               throw var9;
            }
         }
      }

      if (var1 == null) {
         var1 = "UNKNOWN";
      }

      StringBuffer var4 = new StringBuffer(var1);
      int var5 = var2.length;
      if (var5 > 0) {
         var4.append(" : ");
      }

      for(int var6 = 0; var6 < var5; ++var6) {
         if (var6 > 0) {
            var4.append(", ");
         }

         var4.append('[');
         var4.append(var6);
         var4.append("] ");
         if (var2[var6] == null) {
            var4.append("null");
         } else {
            var4.append(var2[var6].toString());
         }
      }

      return var4.toString();
   }

   private static ResourceBundle getBundle(String var0) {
      ResourceBundle var1 = null;
      if (finder != null) {
         var1 = finder.getBundle(var0);
      }

      if (var1 == null) {
         var1 = getBundleForLocale(Locale.getDefault(), var0);
      }

      return var1;
   }

   public static ResourceBundle getBundleWithEnDefault(String var0, Locale var1) {
      Object var2 = null;
      ResourceBundle var4 = getBundle(var0, var1);
      if (var4 == null) {
         Locale var3 = Locale.getDefault();
         if (!var3.equals(var1)) {
            var4 = getBundle(var0, var3);
         }
      }

      if (var4 == null) {
         var4 = lookupBundle(var0, EN);
      }

      return var4;
   }

   private static ResourceBundle getBundle(String var0, Locale var1) {
      ResourceBundle var2 = null;

      try {
         var2 = lookupBundle(localizeResourceName(var0, var1.toString()), var1);
      } catch (MissingResourceException var5) {
      }

      if (var2 == null) {
         try {
            var2 = lookupBundle(localizeResourceName(var0, var1.getLanguage()), var1);
         } catch (MissingResourceException var4) {
         }
      }

      return var2;
   }

   private static ResourceBundle lookupBundle(String var0, Locale var1) {
      return JVMInfo.isModuleAware() ? lookupBundleInModule(var0, var1) : ResourceBundle.getBundle(var0, var1);
   }

   public static ResourceBundle lookupBundleInModule(String var0, Locale var1) {
      boolean var3 = var1.getLanguage().equals(EN.getLanguage());
      boolean var4 = false;
      boolean var5 = false;
      String var2;
      if (var0.contains("clientmessages")) {
         var2 = "org.apache.derby.client";
      } else if (var0.contains("drda")) {
         var2 = "org.apache.derby.server";
      } else if (!var0.contains("toolsmessages") && !var0.contains("sysinfoMessages")) {
         if (var3) {
            var2 = "org.apache.derby.engine";
            var4 = true;
         } else {
            var2 = ModuleUtil.localizationModuleName(var1.toString());
            var5 = true;
         }
      } else if (var3) {
         var2 = "org.apache.derby.tools";
      } else {
         var2 = ModuleUtil.localizationModuleName(var1.toString());
         var5 = true;
      }

      Module var6 = ModuleUtil.derbyModule(var2);
      if (var6 == null && var5) {
         var2 = ModuleUtil.localizationModuleName(var1.getLanguage());
         var6 = ModuleUtil.derbyModule(var2);
      }

      if (var6 == null) {
         return null;
      } else {
         ResourceBundle var7 = lookupBundleInModule(var6, var0, var1.toString(), var3, var4);
         if (var7 == null) {
            var7 = lookupBundleInModule(var6, var0, var1.getLanguage(), var3, var4);
         }

         return var7;
      }
   }

   private static ResourceBundle lookupBundleInModule(Module var0, String var1, String var2, boolean var3, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      var5.append(var1.replace('.', '/'));
      if (!var3) {
         var5.append("_");
         var5.append(var2);
      }

      if (var4 && var3) {
         var5.append("_en");
      }

      var5.append(".properties");
      String var6 = var5.toString();
      return getModuleResourceBundle(var6, var0);
   }

   private static PropertyResourceBundle getModuleResourceBundle(String var0, Module var1) {
      try {
         InputStream var2 = var1.getResourceAsStream(var0);
         return var2 != null ? new PropertyResourceBundle(var2) : null;
      } catch (Exception var3) {
         System.out.println(var3.getMessage());
         return null;
      }
   }

   private static String localizeResourceName(String var0, String var1) {
      if (var0 != null && !var0.contains("clientmessages") && !var0.contains("drda")) {
         if (EN.toString().equals(var1)) {
            return var0;
         } else {
            int var2 = var0.lastIndexOf(46);
            String var3 = var0.substring(0, var2 + 1) + "locale_" + var1 + var0.substring(var2, var0.length());
            return var3;
         }
      } else {
         return var0;
      }
   }

   public static int hashString50(String var0) {
      int var1 = 0;
      int var2 = var0.length();
      if (var2 > 5) {
         var2 = 5;
      }

      for(int var3 = 0; var3 < var2; ++var3) {
         var1 += var0.charAt(var3);
      }

      var1 %= 50;
      return var1;
   }
}
