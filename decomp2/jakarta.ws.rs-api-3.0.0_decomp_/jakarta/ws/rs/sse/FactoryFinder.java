package jakarta.ws.rs.sse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.AccessController;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

final class FactoryFinder {
   private static final Logger LOGGER = Logger.getLogger(FactoryFinder.class.getName());

   private FactoryFinder() {
   }

   private static ClassLoader getContextClassLoader() {
      return (ClassLoader)AccessController.doPrivileged(() -> {
         ClassLoader cl = null;

         try {
            cl = Thread.currentThread().getContextClassLoader();
         } catch (SecurityException ex) {
            LOGGER.log(Level.WARNING, "Unable to get context classloader instance.", ex);
         }

         return cl;
      });
   }

   private static Object newInstance(String className, ClassLoader classLoader) throws ClassNotFoundException {
      try {
         Class<?> spiClass;
         if (classLoader == null) {
            spiClass = Class.forName(className);
         } else {
            try {
               spiClass = Class.forName(className, false, classLoader);
            } catch (ClassNotFoundException ex) {
               LOGGER.log(Level.FINE, "Unable to load provider class " + className + " using custom classloader " + classLoader.getClass().getName() + " trying again with current classloader.", ex);
               spiClass = Class.forName(className);
            }
         }

         return spiClass.getDeclaredConstructor().newInstance();
      } catch (ClassNotFoundException x) {
         throw x;
      } catch (Exception x) {
         throw new ClassNotFoundException("Provider " + className + " could not be instantiated: " + x, x);
      }
   }

   static Object find(String factoryId, Class service) throws ClassNotFoundException {
      ClassLoader classLoader = getContextClassLoader();

      try {
         Iterator<T> iterator = ServiceLoader.load(service, getContextClassLoader()).iterator();
         if (iterator.hasNext()) {
            return iterator.next();
         }
      } catch (ServiceConfigurationError | Exception ex) {
         LOGGER.log(Level.FINER, "Failed to load service " + factoryId + ".", ex);
      }

      try {
         Iterator<T> iterator = ServiceLoader.load(service, FactoryFinder.class.getClassLoader()).iterator();
         if (iterator.hasNext()) {
            return iterator.next();
         }
      } catch (ServiceConfigurationError | Exception ex) {
         LOGGER.log(Level.FINER, "Failed to load service " + factoryId + ".", ex);
      }

      FileInputStream inputStream = null;
      String configFile = null;

      try {
         String javah = System.getProperty("java.home");
         configFile = javah + File.separator + "lib" + File.separator + "jaxrs.properties";
         File f = new File(configFile);
         if (f.exists()) {
            Properties props = new Properties();
            inputStream = new FileInputStream(f);
            props.load(inputStream);
            String factoryClassName = props.getProperty(factoryId);
            Object var9 = newInstance(factoryClassName, classLoader);
            return var9;
         }
      } catch (Exception ex) {
         LOGGER.log(Level.FINER, "Failed to load service " + factoryId + " from $java.home/lib/jaxrs.properties", ex);
      } finally {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (IOException ex) {
               LOGGER.log(Level.FINER, String.format("Error closing %s file.", configFile), ex);
            }
         }

      }

      try {
         String systemProp = System.getProperty(factoryId);
         if (systemProp != null) {
            return newInstance(systemProp, classLoader);
         }
      } catch (SecurityException se) {
         LOGGER.log(Level.FINER, "Failed to load service " + factoryId + " from a system property", se);
      }

      throw new ClassNotFoundException("Provider for " + factoryId + " cannot be found", (Throwable)null);
   }
}
