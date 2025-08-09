package org.codehaus.commons.compiler.util;

import org.codehaus.commons.nullanalysis.Nullable;

public final class SystemProperties {
   private SystemProperties() {
   }

   public static boolean getBooleanClassProperty(Class targetClass, String classPropertyName) {
      return getBooleanClassProperty(targetClass, classPropertyName, false);
   }

   public static boolean getBooleanClassProperty(Class targetClass, String classPropertyName, boolean defaultValue) {
      String s = getClassProperty(targetClass, classPropertyName);
      return s != null ? Boolean.parseBoolean(s) : defaultValue;
   }

   public static int getIntegerClassProperty(Class targetClass, String classPropertyName, int defaultValue) {
      String s = getClassProperty(targetClass, classPropertyName);
      return s != null ? Integer.parseInt(s) : defaultValue;
   }

   @Nullable
   public static String getClassProperty(Class targetClass, String classPropertyName) {
      return getClassProperty(targetClass, classPropertyName, (String)null);
   }

   @Nullable
   public static String getClassProperty(Class targetClass, String classPropertyName, @Nullable String defaultValue) {
      String result = System.getProperty(targetClass.getName() + "." + classPropertyName);
      if (result != null) {
         return result;
      } else {
         result = System.getProperty(targetClass.getSimpleName() + "." + classPropertyName);
         return result != null ? result : defaultValue;
      }
   }
}
