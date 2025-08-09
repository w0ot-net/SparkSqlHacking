package org.apache.derby.impl.io.vfmem;

import java.io.File;

public class PathUtil {
   public static final char SEP;
   public static final String SEP_STR;

   private PathUtil() {
   }

   private static void basicPathChecks(String var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Path is null");
      } else if (!var0.equals(var0.trim())) {
         throw new IllegalArgumentException("Path has not been trimmed: '" + var0 + "'");
      }
   }

   public static String getBaseName(String var0) {
      basicPathChecks(var0);
      int var1 = var0.lastIndexOf(SEP);
      return var1 != -1 && var1 != var0.length() - 1 ? var0.substring(var1 + 1) : var0;
   }

   public static String getParent(String var0) {
      basicPathChecks(var0);
      if (var0.equals(SEP_STR)) {
         return null;
      } else {
         if (var0.length() > 0 && var0.charAt(var0.length() - 1) == SEP) {
            var0 = var0.substring(0, var0.length() - 1);
         }

         int var1 = var0.lastIndexOf(SEP);
         if (var1 == 0) {
            return SEP_STR;
         } else {
            return var1 > 0 ? var0.substring(0, var1) : null;
         }
      }
   }

   public static String join(String var0, String var1) {
      return var0.charAt(var0.length() - 1) == SEP ? var0 + var1 : var0 + SEP + var1;
   }

   static {
      SEP = File.separatorChar;
      SEP_STR = String.valueOf(SEP);
   }
}
