package org.apache.hadoop.hive.common;

import java.io.Closeable;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JavaUtils {
   private static final Logger LOG = LoggerFactory.getLogger(JavaUtils.class);

   public static ClassLoader getClassLoader() {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      if (classLoader == null) {
         classLoader = JavaUtils.class.getClassLoader();
      }

      return classLoader;
   }

   public static Class loadClass(String className) throws ClassNotFoundException {
      return loadClass(className, true);
   }

   public static Class loadClass(String className, boolean init) throws ClassNotFoundException {
      return Class.forName(className, init, getClassLoader());
   }

   public static boolean closeClassLoadersTo(ClassLoader current, ClassLoader stop) {
      if (!isValidHierarchy(current, stop)) {
         return false;
      } else {
         for(; current != null && current != stop; current = current.getParent()) {
            try {
               closeClassLoader(current);
            } catch (IOException e) {
               String detailedMessage = current instanceof URLClassLoader ? Arrays.toString(((URLClassLoader)current).getURLs()) : "";
               LOG.info("Failed to close class loader " + current + " " + detailedMessage, e);
            }
         }

         return true;
      }
   }

   private static boolean isValidHierarchy(ClassLoader current, ClassLoader stop) {
      if (current != null && stop != null && current != stop) {
         while(current != null && current != stop) {
            current = current.getParent();
         }

         return current == stop;
      } else {
         return false;
      }
   }

   public static void closeClassLoader(ClassLoader loader) throws IOException {
      if (loader instanceof Closeable) {
         ((Closeable)loader).close();
      } else {
         LOG.warn("Ignoring attempt to close class loader ({}) -- not instance of UDFClassLoader.", loader == null ? "mull" : loader.getClass().getSimpleName());
      }

   }

   public static String lockIdToString(long extLockId) {
      return "lockid:" + extLockId;
   }

   public static String txnIdToString(long txnId) {
      return "txnid:" + txnId;
   }

   public static String txnIdsToString(List txnIds) {
      return "Transactions requested to be aborted: " + txnIds.toString();
   }

   private JavaUtils() {
   }
}
