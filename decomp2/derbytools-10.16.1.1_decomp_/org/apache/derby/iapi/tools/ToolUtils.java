package org.apache.derby.iapi.tools;

import [Ljava.lang.Object;;
import [Ljava.lang.String;;

public abstract class ToolUtils {
   public static Object[] copy(Object[] var0) {
      return var0 == null ? null : (Object[])((Object;)var0).clone();
   }

   public static String[] copy(String[] var0) {
      return var0 == null ? null : (String[])((String;)var0).clone();
   }

   public static boolean[] copy(boolean[] var0) {
      return var0 == null ? null : (boolean[])(([Z)var0).clone();
   }

   public static byte[] copy(byte[] var0) {
      return var0 == null ? null : (byte[])(([B)var0).clone();
   }

   public static int[] copy(int[] var0) {
      return var0 == null ? null : (int[])(([I)var0).clone();
   }

   public static int[][] copy2(int[][] var0) {
      if (var0 == null) {
         return null;
      } else {
         int[][] var1 = new int[var0.length][];

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = copy(var0[var2]);
         }

         return var1;
      }
   }
}
