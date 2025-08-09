package jakarta.activation;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

class FactoryFinder {
   private static final Logger logger = Logger.getLogger("jakarta.activation");
   private static final ServiceLoaderUtil.ExceptionHandler EXCEPTION_HANDLER = new ServiceLoaderUtil.ExceptionHandler() {
      public RuntimeException createException(Throwable throwable, String message) {
         return new IllegalStateException(message, throwable);
      }
   };

   static Object find(Class factoryClass) throws RuntimeException {
      for(ClassLoader l : getClassLoaders(Thread.class, FactoryFinder.class, System.class)) {
         T f = (T)find(factoryClass, l);
         if (f != null) {
            return f;
         }
      }

      throw (RuntimeException)EXCEPTION_HANDLER.createException((Throwable)null, "Provider for " + factoryClass.getName() + " cannot be found");
   }

   static Object find(Class factoryClass, ClassLoader loader) throws RuntimeException {
      String className = fromSystemProperty(factoryClass.getName());
      if (className != null) {
         T result = (T)newInstance(className, factoryClass, loader);
         if (result != null) {
            return result;
         }
      }

      T factory = (T)ServiceLoaderUtil.firstByServiceLoader(factoryClass, loader, logger, EXCEPTION_HANDLER);
      if (factory != null) {
         return factory;
      } else {
         T result = (T)lookupUsingHk2ServiceLoader(factoryClass, loader);
         return result != null ? result : null;
      }
   }

   private static Object newInstance(String className, Class service, ClassLoader loader) throws RuntimeException {
      return ServiceLoaderUtil.newInstance(className, service, loader, EXCEPTION_HANDLER);
   }

   private static String fromSystemProperty(String factoryId) {
      String systemProp = getSystemProperty(factoryId);
      return systemProp;
   }

   private static String getSystemProperty(final String property) {
      logger.log(Level.FINE, "Checking system property {0}", property);
      String value = (String)AccessController.doPrivileged(new PrivilegedAction() {
         public String run() {
            return System.getProperty(property);
         }
      });
      logFound(value);
      return value;
   }

   private static void logFound(String value) {
      if (value != null) {
         logger.log(Level.FINE, "  found {0}", value);
      } else {
         logger.log(Level.FINE, "  not found");
      }

   }

   private static Class[] getHk2ServiceLoaderTargets(Class factoryClass) {
      ClassLoader[] loaders = getClassLoaders(Thread.class, factoryClass, System.class);
      Class<?>[] classes = new Class[loaders.length];
      int w = 0;

      for(ClassLoader loader : loaders) {
         if (loader != null) {
            try {
               classes[w++] = Class.forName("org.glassfish.hk2.osgiresourcelocator.ServiceLoader", false, loader);
            } catch (LinkageError | Exception var9) {
            }
         }
      }

      if (classes.length != w) {
         classes = (Class[])Arrays.copyOf(classes, w);
      }

      return classes;
   }

   private static Object lookupUsingHk2ServiceLoader(Class factoryClass, ClassLoader loader) {
      for(Class target : getHk2ServiceLoaderTargets(factoryClass)) {
         try {
            Class<?> serviceClass = Class.forName(factoryClass.getName(), false, loader);
            Class<?>[] args = new Class[]{serviceClass};
            Method m = target.getMethod("lookupProviderInstances", Class.class);
            Iterable<?> iterable = (Iterable)m.invoke((Object)null, (Object[])args);
            if (iterable != null) {
               Iterator<?> iter = iterable.iterator();
               if (iter.hasNext()) {
                  return factoryClass.cast(iter.next());
               }
            }
         } catch (Exception var11) {
         }
      }

      return null;
   }

   private static ClassLoader[] getClassLoaders(final Class... classes) {
      return (ClassLoader[])AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader[] run() {
            ClassLoader[] loaders = new ClassLoader[classes.length];
            int w = 0;

            for(Class k : classes) {
               ClassLoader cl = null;
               if (k == Thread.class) {
                  try {
                     cl = Thread.currentThread().getContextClassLoader();
                  } catch (SecurityException var9) {
                  }
               } else if (k == System.class) {
                  try {
                     cl = ClassLoader.getSystemClassLoader();
                  } catch (SecurityException var11) {
                  }
               } else {
                  try {
                     cl = k.getClassLoader();
                  } catch (SecurityException var10) {
                  }
               }

               if (cl != null) {
                  loaders[w++] = cl;
               }
            }

            if (loaders.length != w) {
               loaders = (ClassLoader[])Arrays.copyOf(loaders, w);
            }

            return loaders;
         }
      });
   }
}
