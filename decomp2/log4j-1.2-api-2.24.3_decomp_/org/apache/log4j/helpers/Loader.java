package org.apache.log4j.helpers;

import java.net.URL;

public class Loader {
   static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
   private static boolean ignoreTCL;

   public static URL getResource(final String resource) {
      ClassLoader classLoader = null;
      URL url = null;

      try {
         if (!ignoreTCL) {
            classLoader = getTCL();
            if (classLoader != null) {
               LogLog.debug("Trying to find [" + resource + "] using context classloader " + classLoader + ".");
               url = classLoader.getResource(resource);
               if (url != null) {
                  return url;
               }
            }
         }

         classLoader = Loader.class.getClassLoader();
         if (classLoader != null) {
            LogLog.debug("Trying to find [" + resource + "] using " + classLoader + " class loader.");
            url = classLoader.getResource(resource);
            if (url != null) {
               return url;
            }
         }
      } catch (Throwable t) {
         LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", t);
      }

      LogLog.debug("Trying to find [" + resource + "] using ClassLoader.getSystemResource().");
      return ClassLoader.getSystemResource(resource);
   }

   /** @deprecated */
   @Deprecated
   public static URL getResource(final String resource, final Class clazz) {
      return getResource(resource);
   }

   private static ClassLoader getTCL() {
      return Thread.currentThread().getContextClassLoader();
   }

   public static boolean isJava1() {
      return false;
   }

   public static Class loadClass(final String clazz) throws ClassNotFoundException {
      if (ignoreTCL) {
         return Class.forName(clazz);
      } else {
         try {
            return getTCL().loadClass(clazz);
         } catch (Throwable var2) {
            return Class.forName(clazz);
         }
      }
   }

   static {
      String ignoreTCLProp = OptionConverter.getSystemProperty("log4j.ignoreTCL", (String)null);
      if (ignoreTCLProp != null) {
         ignoreTCL = OptionConverter.toBoolean(ignoreTCLProp, true);
      }

   }
}
