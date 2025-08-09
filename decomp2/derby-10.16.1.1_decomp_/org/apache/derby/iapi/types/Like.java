package org.apache.derby.iapi.types;

import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

public class Like {
   private static final char anyChar = '_';
   private static final char anyString = '%';
   private static final String SUPER_STRING = "\uffff";

   private Like() {
   }

   public static Boolean like(char[] var0, int var1, char[] var2, int var3, char[] var4, int var5, RuleBasedCollator var6) throws StandardException {
      return like(var0, 0, var1, var2, 0, var3, var4, var5, var6);
   }

   private static Boolean like(char[] var0, int var1, int var2, char[] var3, int var4, int var5, char[] var6, int var7, RuleBasedCollator var8) throws StandardException {
      char var9 = ' ';
      boolean var10 = true;
      if (var0 == null) {
         return null;
      } else if (var3 == null) {
         return null;
      } else {
         if (var6 == null) {
            var10 = false;
         } else {
            var9 = var6[0];
         }

         Boolean var11;
         while((var11 = checkLengths(var1, var2, var4, var3, var5)) == null) {
            while(var3[var4] != '_' && var3[var4] != '%' && (!var10 || var3[var4] != var9)) {
               if (!checkEquality(var0, var1, var3, var4, var8)) {
                  return Boolean.FALSE;
               }

               ++var1;
               ++var4;
               var11 = checkLengths(var1, var2, var4, var3, var5);
               if (var11 != null) {
                  return var11;
               }
            }

            if (var10 && var3[var4] == var9) {
               ++var4;
               if (var4 == var5) {
                  throw StandardException.newException("22025", new Object[0]);
               }

               if (var3[var4] != var9 && var3[var4] != '_' && var3[var4] != '%') {
                  throw StandardException.newException("22025", new Object[0]);
               }

               if (!checkEquality(var0, var1, var3, var4, var8)) {
                  return Boolean.FALSE;
               }

               ++var1;
               ++var4;
               var11 = checkLengths(var1, var2, var4, var3, var5);
               if (var11 != null) {
                  return var11;
               }
            } else if (var3[var4] == '_') {
               ++var1;
               ++var4;
               var11 = checkLengths(var1, var2, var4, var3, var5);
               if (var11 != null) {
                  return var11;
               }
            } else if (var3[var4] == '%') {
               if (var4 + 1 == var5) {
                  return Boolean.TRUE;
               }

               boolean var12 = true;

               for(int var13 = var4 + 1; var13 < var5; ++var13) {
                  if (var3[var13] != '%') {
                     var12 = false;
                     break;
                  }
               }

               if (var12) {
                  return Boolean.TRUE;
               }

               int var22 = var2 - var1;
               int var14 = 0;
               int var15 = getMinLen(var3, var4 + 1, var5, var10, var9);

               for(int var16 = var22; var16 >= var15; --var16) {
                  Boolean var17 = like(var0, var1 + var14, var1 + var14 + var16, var3, var4 + 1, var5, var6, var7, var8);
                  if (var17) {
                     return var17;
                  }

                  ++var14;
               }

               return Boolean.FALSE;
            }
         }

         return var11;
      }
   }

   private static boolean checkEquality(char[] var0, int var1, char[] var2, int var3, RuleBasedCollator var4) {
      if (var0[var1] == var2[var3]) {
         return true;
      } else if (var4 == null) {
         return false;
      } else {
         String var5 = new String(var0, var1, 1);
         String var6 = new String(var2, var3, 1);
         return var4.compare(var5, var6) == 0;
      }
   }

   static int getMinLen(char[] var0, int var1, int var2, boolean var3, char var4) {
      int var5 = 0;
      int var6 = var1;

      while(var6 < var2) {
         if (var3 && var0[var6] == var4) {
            var6 += 2;
            ++var5;
         } else if (var0[var6] == '%') {
            ++var6;
         } else {
            ++var6;
            ++var5;
         }
      }

      return var5;
   }

   static Boolean checkLengths(int var0, int var1, int var2, char[] var3, int var4) {
      if (var0 == var1) {
         if (var2 == var4) {
            return Boolean.TRUE;
         } else {
            for(int var5 = var2; var5 < var4; ++var5) {
               if (var3[var5] != '%') {
                  return Boolean.FALSE;
               }
            }

            return Boolean.TRUE;
         }
      } else {
         return var2 == var4 ? Boolean.FALSE : null;
      }
   }

   public static Boolean like(char[] var0, int var1, char[] var2, int var3, RuleBasedCollator var4) throws StandardException {
      return var0 != null && var2 != null ? like(var0, var1, var2, var3, (char[])null, 0, var4) : null;
   }

   public static boolean isOptimizable(String var0) {
      if (var0 == null) {
         return false;
      } else if (var0.length() == 0) {
         return true;
      } else {
         char var1 = var0.charAt(0);
         return var1 != '_' && var1 != '%';
      }
   }

   public static String greaterEqualStringFromParameter(String var0, int var1) throws StandardException {
      return var0 == null ? null : greaterEqualString(var0, (String)null, var1);
   }

   public static String greaterEqualStringFromParameterWithEsc(String var0, String var1, int var2) throws StandardException {
      return var0 == null ? null : greaterEqualString(var0, var1, var2);
   }

   public static String greaterEqualString(String var0, String var1, int var2) throws StandardException {
      int var3 = var0.indexOf(95);
      int var4 = var0.indexOf(37);
      if (var1 != null && var1.length() != 0) {
         char var5 = var1.charAt(0);
         if (var0.indexOf(var5) != -1) {
            return padWithNulls(greaterEqualString(var0, var5), var2);
         }
      }

      if (var3 == -1) {
         if (var4 != -1) {
            var0 = var0.substring(0, var4);
         }
      } else if (var4 == -1) {
         var0 = var0.substring(0, var3);
      } else {
         var0 = var0.substring(0, var3 > var4 ? var4 : var3);
      }

      return padWithNulls(var0, var2);
   }

   private static String greaterEqualString(String var0, char var1) throws StandardException {
      int var2 = var0.length();
      char[] var3 = new char[var2];
      char[] var4 = new char[var2];
      var0.getChars(0, var2, var3, 0);
      int var5 = 0;

      for(int var6 = 0; var6 < var2 && var5 < var2; ++var6) {
         char var7 = var3[var6];
         if (var7 == var1) {
            ++var6;
            if (var6 >= var2) {
               throw StandardException.newException("22025", new Object[0]);
            }

            var4[var5++] = var3[var6];
         } else {
            if (var7 == '_' || var7 == '%') {
               return new String(var4, 0, var5);
            }

            var4[var5++] = var3[var6];
         }
      }

      return new String(var4, 0, var5);
   }

   public static String stripEscapesNoPatternChars(String var0, char var1) throws StandardException {
      int var2 = var0.length();
      char[] var3 = new char[var2];
      char[] var4 = new char[var2];
      var0.getChars(0, var2, var3, 0);
      int var5 = 0;

      for(int var6 = 0; var6 < var2 && var5 < var2; ++var6) {
         char var7 = var0.charAt(var6);
         if (var7 == var1) {
            ++var6;
            if (var6 >= var2) {
               throw StandardException.newException("22025", new Object[0]);
            }

            var4[var5++] = var3[var6];
         } else {
            if (var7 == '_' || var7 == '%') {
               return null;
            }

            var4[var5++] = var3[var6];
         }
      }

      return new String(var4, 0, var5);
   }

   public static String lessThanStringFromParameter(String var0, int var1) throws StandardException {
      return var0 == null ? null : lessThanString(var0, (String)null, var1);
   }

   public static String lessThanStringFromParameterWithEsc(String var0, String var1, int var2) throws StandardException {
      return var0 == null ? null : lessThanString(var0, var1, var2);
   }

   public static String lessThanString(String var0, String var1, int var2) throws StandardException {
      int var6;
      if (var1 != null && var1.length() != 0) {
         var6 = var1.charAt(0);
      } else {
         var6 = -1;
      }

      StringBuffer var7 = new StringBuffer(var2);

      for(int var8 = 0; var8 < var0.length(); ++var8) {
         char var9 = var0.charAt(var8);
         if (var9 == var6) {
            ++var8;
            if (var8 >= var0.length()) {
               throw StandardException.newException("22025", new Object[0]);
            }

            var9 = var0.charAt(var8);
         } else if (var9 == '_' || var9 == '%') {
            break;
         }

         var7.append(var9);
      }

      if (var7.length() == 0) {
         return "\uffff";
      } else {
         int var3 = var7.length() - 1;
         char var4 = var7.charAt(var3);
         char var5 = (char)(var4 + 1);
         if (var5 < var4) {
            return "\uffff";
         } else {
            var7.setCharAt(var3, var5);
            if (var7.length() < var2) {
               var7.setLength(var2);
            }

            return var7.toString();
         }
      }
   }

   public static boolean isLikeComparisonNeeded(String var0) {
      int var1 = var0.indexOf(95);
      int var2 = var0.indexOf(37);
      if (var1 == -1 && var2 == -1) {
         return false;
      } else if (var1 != -1) {
         return true;
      } else {
         return var2 != var0.length() - 1;
      }
   }

   private static String padWithNulls(String var0, int var1) {
      if (var0.length() >= var1) {
         return var0;
      } else {
         StringBuffer var2 = (new StringBuffer(var1)).append(var0);
         var2.setLength(var1);
         return var2.toString();
      }
   }
}
