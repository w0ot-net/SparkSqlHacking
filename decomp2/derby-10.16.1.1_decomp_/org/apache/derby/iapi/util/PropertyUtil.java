package org.apache.derby.iapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

public class PropertyUtil {
   public static String sortProperties(Properties var0) {
      return sortProperties(var0, (String)null);
   }

   public static String sortProperties(Properties var0, String var1) {
      Set var2 = var0 == null ? Collections.emptySet() : var0.stringPropertyNames();
      String[] var3 = (String[])var2.toArray(new String[var2.size()]);
      Arrays.sort(var3);
      StringBuilder var4 = new StringBuilder();
      if (var1 == null) {
         var4.append("{ ");
      }

      for(int var5 = 0; var5 < var3.length; ++var5) {
         if (var5 > 0 && var1 == null) {
            var4.append(", ");
         }

         String var6 = var3[var5];
         if (var1 != null) {
            var4.append(var1);
         }

         var4.append(var6);
         var4.append("=");
         String var7 = var0.getProperty(var6, "MISSING_VALUE");
         var4.append(var7);
         if (var1 != null) {
            var4.append("\n");
         }
      }

      if (var1 == null) {
         var4.append(" }");
      }

      return var4.toString();
   }

   public static void copyProperties(Properties var0, Properties var1) {
      for(String var3 : var0.stringPropertyNames()) {
         var1.put(var3, var0.get(var3));
      }

   }

   public static void loadWithTrimmedValues(InputStream var0, Properties var1) throws IOException {
      if (var0 != null && var1 != null) {
         Properties var2 = new Properties();
         var2.load(var0);

         for(String var4 : var2.stringPropertyNames()) {
            String var5 = var2.getProperty(var4);
            var5 = var5.trim();
            var1.put(var4, var5);
         }

      }
   }
}
