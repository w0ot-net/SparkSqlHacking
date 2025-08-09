package jakarta.xml.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServiceLoaderUtil {
   private static final String OSGI_SERVICE_LOADER_CLASS_NAME = "org.glassfish.hk2.osgiresourcelocator.ServiceLoader";
   private static final String OSGI_SERVICE_LOADER_METHOD_NAME = "lookupProviderClasses";

   static Object firstByServiceLoader(Class spiClass, Logger logger, ExceptionHandler handler) throws Exception {
      try {
         ServiceLoader<P> serviceLoader = ServiceLoader.load(spiClass);
         Iterator var4 = serviceLoader.iterator();
         if (var4.hasNext()) {
            P impl = (P)var4.next();
            logger.log(Level.FINE, "ServiceProvider loading Facility used; returning object [{0}]", impl.getClass().getName());
            return impl;
         } else {
            return null;
         }
      } catch (Throwable t) {
         throw handler.createException(t, "Error while searching for service [" + spiClass.getName() + "]");
      }
   }

   static Object lookupUsingOSGiServiceLoader(String factoryId, Logger logger) {
      try {
         Class<? extends T> serviceClass = Class.forName(factoryId);
         Class<?> target = Class.forName("org.glassfish.hk2.osgiresourcelocator.ServiceLoader");
         Method m = target.getMethod("lookupProviderClasses", Class.class);
         Iterator<? extends T> iter = ((Iterable)m.invoke((Object)null, serviceClass)).iterator();
         if (iter.hasNext()) {
            T next = (T)iter.next();
            logger.log(Level.FINE, "Found implementation using OSGi facility; returning object [{0}].", next.getClass().getName());
            return next;
         } else {
            return null;
         }
      } catch (InvocationTargetException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException ex) {
         logger.log(Level.FINE, "Unable to find from OSGi: [" + factoryId + "]", ex);
         return null;
      }
   }

   static Iterable lookupsUsingOSGiServiceLoader(String factoryId, Logger logger) {
      try {
         return (Iterable)Class.forName("org.glassfish.hk2.osgiresourcelocator.ServiceLoader").getMethod("lookupProviderClasses", Class.class).invoke((Object)null, Class.forName(factoryId));
      } catch (InvocationTargetException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException ex) {
         logger.log(Level.FINE, ex, () -> "Unable to find from OSGi: [" + factoryId + "]");
         return null;
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
      return classLoader == null ? Class.forName(className) : classLoader.loadClass(className);
   }

   static Object newInstance(String className, String defaultImplClassName, ExceptionHandler handler) throws Exception {
      try {
         return safeLoadClass(className, defaultImplClassName, contextClassLoader(handler)).getConstructor().newInstance();
      } catch (ClassNotFoundException x) {
         throw handler.createException(x, "Provider " + className + " not found");
      } catch (Exception x) {
         throw handler.createException(x, "Provider " + className + " could not be instantiated: " + String.valueOf(x));
      }
   }

   static Class safeLoadClass(String className, String defaultImplClassName, ClassLoader classLoader) throws ClassNotFoundException {
      try {
         checkPackageAccess(className);
      } catch (SecurityException se) {
         if (defaultImplClassName != null && defaultImplClassName.equals(className)) {
            return Class.forName(className);
         }

         throw se;
      }

      return nullSafeLoadClass(className, classLoader);
   }

   static ClassLoader contextClassLoader(ExceptionHandler exceptionHandler) throws Exception {
      try {
         return Thread.currentThread().getContextClassLoader();
      } catch (Exception x) {
         throw exceptionHandler.createException(x, x.toString());
      }
   }

   abstract static class ExceptionHandler {
      public abstract Exception createException(Throwable var1, String var2);
   }
}
