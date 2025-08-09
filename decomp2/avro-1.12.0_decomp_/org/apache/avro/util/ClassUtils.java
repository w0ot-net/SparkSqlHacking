package org.apache.avro.util;

public class ClassUtils {
   private ClassUtils() {
   }

   public static Class forName(String className) throws ClassNotFoundException {
      return forName(ClassUtils.class, className);
   }

   public static Class forName(Class contextClass, String className) throws ClassNotFoundException {
      Class<?> c = null;
      if (contextClass.getClassLoader() != null) {
         c = forName(className, contextClass.getClassLoader());
      }

      if (c == null && Thread.currentThread().getContextClassLoader() != null) {
         c = forName(className, Thread.currentThread().getContextClassLoader());
      }

      if (c == null) {
         throw new ClassNotFoundException("Failed to load class" + className);
      } else {
         return c;
      }
   }

   public static Class forName(ClassLoader classLoader, String className) throws ClassNotFoundException {
      Class<?> c = null;
      if (classLoader != null) {
         c = forName(className, classLoader);
      }

      if (c == null && Thread.currentThread().getContextClassLoader() != null) {
         c = forName(className, Thread.currentThread().getContextClassLoader());
      }

      if (c == null) {
         throw new ClassNotFoundException("Failed to load class" + className);
      } else {
         return c;
      }
   }

   private static Class forName(String className, ClassLoader classLoader) {
      Class<?> c = null;
      if (classLoader != null && className != null) {
         try {
            c = Class.forName(className, true, classLoader);
         } catch (ClassNotFoundException var4) {
         }
      }

      return c;
   }
}
