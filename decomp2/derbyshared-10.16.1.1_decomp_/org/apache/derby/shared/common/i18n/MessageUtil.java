package org.apache.derby.shared.common.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageUtil {
   public static final Locale US = new Locale("en", "US");
   public static final String CLIENT_MESSAGE_RESOURCE_NAME = "org.apache.derby.loc.client.clientmessages";
   private String resourceBundleName;

   public MessageUtil(String var1) {
      this.resourceBundleName = var1;
   }

   public String getTextMessage(String var1, Object... var2) {
      return this.getCompleteMessage(var1, var2);
   }

   public String getCompleteMessage(String var1, Object[] var2) {
      return getCompleteMessage(var1, this.resourceBundleName, var2);
   }

   public static String getCompleteMessage(Locale var0, String var1, String var2, Object[] var3, boolean var4) throws MissingResourceException {
      try {
         return formatMessage(ResourceBundle.getBundle(var1, var0), var2, var3, false);
      } catch (MissingResourceException var6) {
         return formatMessage(MessageService.getBundleWithEnDefault(var1, US), var2, var3, var4);
      }
   }

   public static String getCompleteMessage(String var0, String var1, Object[] var2) throws MissingResourceException {
      return getCompleteMessage(Locale.getDefault(), var1, var0, var2, true);
   }

   public static String formatMessage(ResourceBundle var0, String var1, Object[] var2, boolean var3) {
      Object var4 = null;
      Object var5 = null;
      if (var2 == null) {
         var2 = new Object[0];
      }

      if (var0 != null) {
         try {
            String var10 = var0.getString(var1);

            try {
               return MessageFormat.format(var10, var2);
            } catch (IllegalArgumentException var7) {
               if (!var3) {
                  throw var7;
               }
            } catch (NullPointerException var8) {
               if (!var3) {
                  throw var8;
               }
            }
         } catch (MissingResourceException var9) {
            if (!var3) {
               throw var9;
            }
         }
      }

      return composeDefaultMessage("UNKNOWN MESSAGE, id " + var1, var2);
   }

   private static int countParams(String var0) {
      boolean var1 = false;
      int var2 = 0;

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         char var4 = var0.charAt(var3);
         if (var4 == '{') {
            var1 = true;
         }

         if (var4 == '}' && var1) {
            ++var2;
            var1 = false;
         }
      }

      return var2;
   }

   public static String composeDefaultMessage(String var0, Object[] var1) {
      if (var0 == null) {
         var0 = "UNKNOWN";
      }

      StringBuffer var2 = new StringBuffer(var0);
      if (var1 == null) {
         return var2.toString();
      } else {
         var2.append(" : ");
         int var3 = var1.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            if (var4 > 0) {
               var2.append(", ");
            }

            var2.append('[');
            var2.append(var4);
            var2.append("] ");
            if (var1[var4] == null) {
               var2.append("null");
            } else {
               var2.append(var1[var4].toString());
            }
         }

         return var2.toString();
      }
   }
}
