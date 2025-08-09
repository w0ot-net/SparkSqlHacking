package org.apache.derby.iapi.services.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ClassSize {
   public static final int refSize;
   private static final int objectOverhead = 2;
   private static final int booleanSize = 4;
   private static final int charSize = 4;
   private static final int shortSize = 4;
   private static final int intSize = 4;
   private static final int longSize = 8;
   private static final int floatSize = 4;
   private static final int doubleSize = 8;
   private static final int minObjectSize;
   private static boolean dummyCatalog = false;
   static boolean noGuess = false;
   static boolean unitTest = false;
   private static final int[] wildGuess = new int[]{0, 16};

   private static void runFinalization(Runtime var0) {
      var0.runFinalization();
   }

   public static void setDummyCatalog() {
      dummyCatalog = true;
   }

   public static int getRefSize() {
      return refSize;
   }

   public static int getIntSize() {
      return 4;
   }

   public static int[] getSizeCoefficients(Class var0) {
      int[] var1;
      for(var1 = new int[]{0, 2}; null != var0; var0 = var0.getSuperclass()) {
         Field[] var2 = var0.getDeclaredFields();
         if (null != var2) {
            for(int var3 = 0; var3 < var2.length; ++var3) {
               if (!Modifier.isStatic(var2[var3].getModifiers())) {
                  Class var4 = var2[var3].getType();
                  if (!var4.isArray() && var4.isPrimitive()) {
                     String var5 = var4.getName();
                     if (!var5.equals("int") && !var5.equals("I")) {
                        if (!var5.equals("long") && !var5.equals("J")) {
                           if (!var5.equals("boolean") && !var5.equals("Z")) {
                              if (!var5.equals("short") && !var5.equals("S")) {
                                 if (!var5.equals("byte") && !var5.equals("B")) {
                                    if (!var5.equals("char") && !var5.equals("C")) {
                                       if (!var5.equals("float") && !var5.equals("F")) {
                                          if (!var5.equals("double") && !var5.equals("D")) {
                                             int var7 = var1[1]++;
                                          } else {
                                             var1[0] += 8;
                                          }
                                       } else {
                                          var1[0] += 4;
                                       }
                                    } else {
                                       var1[0] += 4;
                                    }
                                 } else {
                                    int var6 = var1[0]++;
                                 }
                              } else {
                                 var1[0] += 4;
                              }
                           } else {
                              var1[0] += 4;
                           }
                        } else {
                           var1[0] += 8;
                        }
                     } else {
                        var1[0] += 4;
                     }
                  } else {
                     int var10002 = var1[1]++;
                  }
               }
            }
         }
      }

      return var1;
   }

   public static int estimateBaseFromCoefficients(int[] var0) {
      int var1 = var0[0] + var0[1] * refSize;
      var1 = (var1 + 7) / 8;
      var1 *= 8;
      return var1 < minObjectSize ? minObjectSize : var1;
   }

   public static int estimateBaseFromCatalog(Class var0) {
      return estimateBaseFromCatalog(var0, false);
   }

   private static int estimateBaseFromCatalog(Class var0, boolean var1) {
      if (dummyCatalog) {
         return 0;
      } else {
         ClassSizeCatalog var2 = ClassSizeCatalog.getInstance();
         int[] var3 = (int[])var2.get(var0.getName());
         if (var3 == null) {
            try {
               var3 = getSizeCoefficients(var0);
            } catch (Throwable var5) {
               if (noGuess) {
                  return -2;
               }

               var3 = wildGuess;
            }

            if (var1) {
               var2.put(var0.getName(), var3);
            }
         }

         return estimateBaseFromCoefficients(var3);
      }
   }

   public static int estimateAndCatalogBase(Class var0) {
      return estimateBaseFromCatalog(var0, true);
   }

   public static int estimateBase(Class var0) {
      return estimateBaseFromCoefficients(getSizeCoefficients(var0));
   }

   public static int estimateArrayOverhead() {
      return minObjectSize;
   }

   public static int estimateHashEntrySize() {
      return 2 + 3 * refSize;
   }

   public static int estimateMemoryUsage(String var0) {
      return null == var0 ? 0 : 2 * var0.length();
   }

   private static final int fetchRefSizeFromSystemProperties() {
      String var0 = getSystemProperty("sun.arch.data.model");

      try {
         return Integer.parseInt(var0) / 8;
      } catch (NumberFormatException var4) {
         String var1 = getSystemProperty("os.arch");
         if (var1 != null) {
            String[] var2 = new String[]{"i386", "x86", "sparc"};
            if (Arrays.asList(var2).contains(var1)) {
               return 4;
            }

            String[] var3 = new String[]{"amd64", "x86_64", "sparcv9"};
            if (Arrays.asList(var3).contains(var1)) {
               return 8;
            }
         }

         return -1;
      }
   }

   private static final String getSystemProperty(String var0) {
      return System.getProperty(var0, (String)null);
   }

   static {
      int var0 = fetchRefSizeFromSystemProperties();
      if (var0 < 4) {
         Runtime var1 = Runtime.getRuntime();
         var1.gc();
         runFinalization(var1);
         long var2 = var1.totalMemory() - var1.freeMemory();
         Object[] var4 = new Object[10000];
         var1.gc();
         runFinalization(var1);
         long var5 = var1.totalMemory() - var1.freeMemory() - var2;
         int var7 = (int)((var5 + (long)(var4.length / 2)) / (long)var4.length);
         var0 = 4 > var7 ? 4 : var7;
      }

      refSize = var0;
      minObjectSize = 4 * refSize;
   }
}
