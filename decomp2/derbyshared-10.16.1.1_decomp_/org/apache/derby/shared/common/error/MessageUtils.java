package org.apache.derby.shared.common.error;

import java.sql.DataTruncation;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.derby.shared.common.i18n.MessageService;

public class MessageUtils {
   private static final Locale EN = new Locale("en", "US");
   public static final String SQLERRMC_MESSAGE_DELIMITER = new String(new char[]{'\u0014', '\u0014', '\u0014'});
   public static final int DB2_JCC_MAX_EXCEPTION_PARAM_LENGTH = 2400;
   public static String SQLERRMC_TOKEN_DELIMITER = new String(new char[]{'\u0014'});
   private static String SQLERRMC_PREFORMATTED_MESSAGE_DELIMITER = "::";

   protected int supportedMessageParamLength() {
      return 2400;
   }

   public static Object[] getArgs(String var0, String var1) {
      Object var3 = null;
      if (var1 != null && var1.length() > 0) {
         char[] var4 = var1.toCharArray();
         int var5 = 0;
         int var6 = -1;

         for(int var7 = 0; var7 < var4.length; ++var7) {
            if (var4[var7] == 20) {
               ++var5;
               var6 = var7;
            }
         }

         String var2;
         if (var5 == 0) {
            var2 = new String(var4);
            var3 = new Object[1];
         } else {
            var2 = new String(var4, var6 + 1, var4.length - var6 - 1);
            var3 = new Object[var5 + 1];
            int var11 = 0;
            int var8 = 0;

            for(int var9 = 0; var9 < var6 + 1; ++var9) {
               if (var9 == var6 || var4[var9] == 20) {
                  ((Object[])var3)[var8++] = new String(var4, var11, var9 - var11);
                  var11 = var9 + 1;
               }
            }
         }

         ((Object[])var3)[((Object[])var3).length - 1] = var2;
      } else {
         var3 = new Object[1];
         ((Object[])var3)[((Object[])var3).length - 1] = var0;
      }

      return (Object[])var3;
   }

   public static String encodeMessageAndArgumentsAsSqlerrmc(String var0, Object[] var1) {
      String var2 = "";

      for(int var3 = 0; var1 != null && var3 < var1.length; ++var3) {
         var2 = var2 + var1[var3] + SQLERRMC_TOKEN_DELIMITER;
      }

      var2 = var2 + var0;
      return var2;
   }

   public static String encodeExceptionAsSqlerrmc(SQLException var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(var0.getLocalizedMessage());
      var0 = var0.getNextException();
      if (var0 != null) {
         var1.append(SQLERRMC_TOKEN_DELIMITER);
         var1.append("SQLSTATE: ").append(var0.getSQLState());
      }

      return var1.toString();
   }

   private String buildDataTruncationSqlerrmc(DataTruncation var1) {
      int var10000 = var1.getIndex();
      return var10000 + SQLERRMC_TOKEN_DELIMITER + var1.getParameter() + SQLERRMC_TOKEN_DELIMITER + var1.getRead() + SQLERRMC_TOKEN_DELIMITER + var1.getDataSize() + SQLERRMC_TOKEN_DELIMITER + var1.getTransferSize();
   }

   private String buildPreformattedSqlerrmc(SQLException var1) {
      if (var1 == null) {
         return "";
      } else {
         StringBuilder var2 = new StringBuilder();
         var2.append(var1.getLocalizedMessage());

         while((var1 = var1.getNextException()) != null) {
            var2.append(SQLERRMC_PREFORMATTED_MESSAGE_DELIMITER);
            var2.append("SQLSTATE: ");
            var2.append(var1.getSQLState());
         }

         return var2.toString();
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

   public static void getLocalizedMessage(int var0, short var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, int var9, String var10, String var11, String var12, String var13, String[] var14, int[] var15) {
      int var16 = var13.indexOf("_");
      Locale var17 = EN;
      if (var16 != -1) {
         int var18 = var13.lastIndexOf("_");
         String var19 = var13.substring(0, var16);
         if (var18 == var16) {
            String var20 = var13.substring(var16 + 1);
            var17 = new Locale(var19, var20);
         } else {
            String var26 = var13.substring(var16 + 1, var18);
            String var21 = var13.substring(var18 + 1);
            var17 = new Locale(var19, var26, var21);
         }
      }

      Object[] var25 = getArgs(var11, var2);
      String var24 = (String)var25[var25.length - 1];
      Object[] var27 = new Object[var25.length - 1];

      for(int var28 = 0; var28 < var27.length; ++var28) {
         var27[var28] = var25[var28];
      }

      try {
         var14[0] = formatMessage(MessageService.getBundleForLocale(var17, var24), var24, var27, true);
         var15[0] = 0;
         return;
      } catch (MissingResourceException var22) {
      } catch (ShutdownException var23) {
      }

      var14[0] = formatMessage(MessageService.getBundleForLocale(EN, var24), var24, var27, false);
      var15[0] = 0;
   }

   public static String getLocalizedMessage(Locale var0, String var1, Object[] var2) {
      Object var3 = null;

      try {
         String var8 = formatMessage(MessageService.getBundleForLocale(var0, var1), var1, var2, true);
         return var8;
      } catch (MissingResourceException var5) {
      } catch (ShutdownException var6) {
      }

      String var7 = formatMessage(MessageService.getBundleForLocale(EN, var1), var1, var2, false);
      return var7;
   }
}
