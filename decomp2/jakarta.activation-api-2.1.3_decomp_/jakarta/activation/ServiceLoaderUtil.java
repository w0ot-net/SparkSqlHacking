package jakarta.activation;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServiceLoaderUtil {
   static Object firstByServiceLoader(Class spiClass, ClassLoader loader, Logger logger, ExceptionHandler handler) throws Exception {
      logger.log(Level.FINE, "Using java.util.ServiceLoader to find {0}", spiClass.getName());

      try {
         ServiceLoader<P> serviceLoader = ServiceLoader.load(spiClass, loader);
         Iterator var5 = serviceLoader.iterator();
         if (var5.hasNext()) {
            P impl = (P)var5.next();
            logger.log(Level.FINE, "ServiceProvider loading Facility used; returning object [{0}]", impl.getClass().getName());
            return impl;
         } else {
            return null;
         }
      } catch (Throwable t) {
         throw handler.createException(t, "Error while searching for service [" + spiClass.getName() + "]");
      }
   }

   static void checkPackageAccess(String className) {
      SecurityManager s = System.getSecurityManager();
      if (s != null) {
         int i = className.lastIndexOf(46);
         if (i != -1) {
            s.checkPackageAccess(className.substring(0, i));
         }
      }

   }

   static Class nullSafeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
      if (classLoader == null) {
         classLoader = ClassLoader.getSystemClassLoader();
      }

      return Class.forName(className, false, classLoader);
   }

   static Object newInstance(String className, Class service, ClassLoader classLoader, ExceptionHandler handler) throws Exception {
      try {
         Class<P> cls = safeLoadClass(className, classLoader);
         return service.cast(cls.getConstructor().newInstance());
      } catch (ClassNotFoundException x) {
         throw handler.createException(x, "Provider " + className + " not found");
      } catch (Exception x) {
         throw handler.createException(x, "Provider " + className + " could not be instantiated: " + x);
      }
   }

   static Class safeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
      checkPackageAccess(className);
      return nullSafeLoadClass(className, classLoader);
   }

   abstract static class ExceptionHandler {
      public abstract Exception createException(Throwable var1, String var2);
   }
}
