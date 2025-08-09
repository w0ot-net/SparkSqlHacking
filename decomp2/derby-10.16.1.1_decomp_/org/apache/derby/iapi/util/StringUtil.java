package org.apache.derby.iapi.util;

import java.util.Locale;

public class StringUtil {
   private static char[] hex_table = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

   public static final String formatForPrint(String var0) {
      if (var0.length() > 60) {
         StringBuffer var1 = new StringBuffer(var0.substring(0, 60));
         var1.append("&");
         var0 = var1.toString();
      }

      return var0;
   }

   public static String[] toStringArray(Object[] var0) {
      int var2 = var0.length;
      String[] var3 = new String[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = var0[var1].toString();
      }

      return var3;
   }

   public static byte[] getAsciiBytes(String var0) {
      char[] var1 = var0.toCharArray();
      byte[] var2 = new byte[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = (byte)(var1[var3] & 127);
      }

      return var2;
   }

   public static String trimTrailing(String var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1;
         for(var1 = var0.length(); var1 > 0 && Character.isWhitespace(var0.charAt(var1 - 1)); --var1) {
         }

         return var0.substring(0, var1);
      }
   }

   public static String truncate(String var0, int var1) {
      if (var0 != null && var0.length() > var1) {
         var0 = var0.substring(0, var1);
      }

      return var0;
   }

   public static String slice(String var0, int var1, int var2, boolean var3) {
      String var4 = var0.substring(var1, var2 + 1);
      if (var3) {
         var4 = var4.trim();
      }

      return var4;
   }

   public static String toHexString(byte[] var0, int var1, int var2) {
      StringBuffer var3 = new StringBuffer(var2 * 2);
      int var4 = var1 + var2;

      for(int var5 = var1; var5 < var4; ++var5) {
         int var6 = (var0[var5] & 240) >>> 4;
         int var7 = var0[var5] & 15;
         var3.append(hex_table[var6]);
         var3.append(hex_table[var7]);
      }

      return var3.toString();
   }

   public static byte[] fromHexString(String var0, int var1, int var2) {
      if (var2 % 2 != 0) {
         return null;
      } else {
         byte[] var3 = new byte[var2 / 2];
         int var4 = 0;
         int var5 = var1 + var2;

         for(int var6 = var1; var6 < var5; var6 += 2) {
            int var7 = Character.digit(var0.charAt(var6), 16);
            int var8 = Character.digit(var0.charAt(var6 + 1), 16);
            if (var7 == -1 || var8 == -1) {
               return null;
            }

            var3[var4++] = (byte)(var7 << 4 & 240 | var8 & 15);
         }

         return var3;
      }
   }

   public static String hexDump(byte[] var0) {
      StringBuffer var2 = new StringBuffer(var0.length * 3);
      var2.append("Hex dump:\n");

      for(int var3 = 0; var3 < var0.length; var3 += 16) {
         String var4 = Integer.toHexString(var3);

         for(int var5 = var4.length(); var5 < 8; ++var5) {
            var2.append("0");
         }

         var2.append(var4);
         var2.append(":");

         for(int var8 = 0; var8 < 16 && var3 + var8 < var0.length; ++var8) {
            byte var1 = var0[var3 + var8];
            if (var8 % 2 == 0) {
               var2.append(" ");
            }

            byte var6 = (byte)((var1 & 240) >>> 4);
            byte var7 = (byte)(var1 & 15);
            var2.append(hex_table[var6]);
            var2.append(hex_table[var7]);
         }

         var2.append("  ");

         for(int var9 = 0; var9 < 16 && var3 + var9 < var0.length; ++var9) {
            char var10 = (char)var0[var3 + var9];
            if (Character.isLetterOrDigit(var10)) {
               var2.append(String.valueOf(var10));
            } else {
               var2.append(".");
            }
         }

         var2.append("\n");
      }

      return var2.toString();
   }

   public static String SQLToUpperCase(String var0) {
      return var0.toUpperCase(Locale.ENGLISH);
   }

   public static boolean SQLEqualsIgnoreCase(String var0, String var1) {
      return var1 == null ? false : SQLToUpperCase(var0).equals(SQLToUpperCase(var1));
   }

   public static String normalizeSQLIdentifier(String var0) {
      if (var0.length() == 0) {
         return var0;
      } else {
         return var0.charAt(0) == '"' && var0.length() >= 3 && var0.charAt(var0.length() - 1) == '"' ? compressQuotes(var0.substring(1, var0.length() - 1), "\"\"") : SQLToUpperCase(var0);
      }
   }

   public static String compressQuotes(String var0, String var1) {
      String var2 = var0;

      for(int var3 = var0.indexOf(var1); var3 != -1; var3 = var2.indexOf(var1, var3 + 1)) {
         String var10000 = var2.substring(0, var3 + 1);
         var2 = var10000 + var2.substring(var3 + 2);
      }

      return var2;
   }

   static String quoteString(String var0, char var1) {
      StringBuffer var2 = new StringBuffer(var0.length() + 2);
      var2.append(var1);

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         char var4 = var0.charAt(var3);
         if (var4 == var1) {
            var2.append(var1);
         }

         var2.append(var4);
      }

      var2.append(var1);
      return var2.toString();
   }

   public static String quoteStringLiteral(String var0) {
      return quoteString(var0, '\'');
   }

   public static String ensureIndent(String var0, int var1) {
      StringBuffer var2 = new StringBuffer();

      while(var1-- > 0) {
         var2.append("\t");
      }

      if (var0 == null) {
         return var2.toString() + "null";
      } else {
         var0 = doRegExpA(var0, var2.toString());
         var0 = doRegExpB(var0);
         var0 = doRegExpC(var0, var2.toString());
         return var0;
      }
   }

   private static String doRegExpA(String var0, String var1) {
      StringBuffer var2 = new StringBuffer();

      int var3;
      for(var3 = 0; var3 < var0.length() && var0.charAt(var3) == '\t'; ++var3) {
      }

      var2.append(var1);
      var2.append(var0.substring(var3));
      return var2.toString();
   }

   private static String doRegExpB(String var0) {
      StringBuffer var1 = new StringBuffer();

      int var2;
      for(var2 = var0.length() - 1; var2 >= 0 && var0.charAt(var2) == '\n'; --var2) {
      }

      var1.append(var0.substring(0, var2 + 1));
      return var1.toString();
   }

   private static String doRegExpC(String var0, String var1) {
      StringBuffer var2 = new StringBuffer();
      int var3 = 0;

      while(var3 < var0.length()) {
         char var4 = var0.charAt(var3);
         if (var4 == '\n') {
            var2.append(var4);

            int var5;
            for(var5 = var3 + 1; var5 < var0.length() && var0.charAt(var5) == '\t'; ++var5) {
            }

            var2.append(var1);
            var3 = var5;
         } else {
            var2.append(var4);
            ++var3;
         }
      }

      return var2.toString();
   }

   public static String shortDBName(String var0, char var1) {
      int var2 = var0.lastIndexOf(var1);
      return var0.substring(var2 + 1);
   }
}
