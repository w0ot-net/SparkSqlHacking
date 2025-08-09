package org.apache.derby.iapi.services.diag;

import java.lang.reflect.Constructor;

public class DiagnosticUtil {
   private DiagnosticUtil() {
   }

   public static Diagnosticable findDiagnostic(Object var0) {
      Class var1 = var0.getClass();

      while(true) {
         try {
            String var2 = var1.getName();
            int var3 = var2.lastIndexOf(46) + 1;
            String var10000 = var2.substring(0, var3);
            String var4 = var10000 + "D_" + var2.substring(var3);

            Class var5;
            try {
               var5 = Class.forName(var4);
            } catch (ClassNotFoundException var8) {
               var1 = var1.getSuperclass();
               if (var1 != null) {
                  continue;
               }

               return null;
            }

            Constructor var6 = var5.getConstructor();
            Diagnosticable var7 = (Diagnosticable)var5.getConstructor().newInstance();
            var7.init(var0);
            return var7;
         } catch (Exception var9) {
            return null;
         }
      }
   }

   public static String toDiagString(Object var0) {
      String var1 = null;
      if (var0 == null) {
         return "null";
      } else {
         try {
            Diagnosticable var2 = findDiagnostic(var0);
            if (var2 != null) {
               var1 = var2.diag();
            }
         } catch (Throwable var3) {
         }

         if (var1 == null) {
            var1 = var0.toString();
         }

         return var1;
      }
   }
}
