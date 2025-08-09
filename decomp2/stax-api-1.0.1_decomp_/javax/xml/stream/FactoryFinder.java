package javax.xml.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

class FactoryFinder {
   private static boolean debug = false;
   // $FF: synthetic field
   static Class class$javax$xml$stream$FactoryFinder;

   private static void debugPrintln(String msg) {
      if (debug) {
         System.err.println("STREAM: " + msg);
      }

   }

   private static ClassLoader findClassLoader() throws FactoryConfigurationError {
      ClassLoader classLoader;
      try {
         Class clazz = Class.forName((class$javax$xml$stream$FactoryFinder == null ? (class$javax$xml$stream$FactoryFinder = class$("javax.xml.stream.FactoryFinder")) : class$javax$xml$stream$FactoryFinder).getName() + "$ClassLoaderFinderConcrete");
         ClassLoaderFinder clf = (ClassLoaderFinder)clazz.newInstance();
         classLoader = clf.getContextClassLoader();
      } catch (LinkageError var3) {
         classLoader = (class$javax$xml$stream$FactoryFinder == null ? (class$javax$xml$stream$FactoryFinder = class$("javax.xml.stream.FactoryFinder")) : class$javax$xml$stream$FactoryFinder).getClassLoader();
      } catch (ClassNotFoundException var4) {
         classLoader = (class$javax$xml$stream$FactoryFinder == null ? (class$javax$xml$stream$FactoryFinder = class$("javax.xml.stream.FactoryFinder")) : class$javax$xml$stream$FactoryFinder).getClassLoader();
      } catch (Exception x) {
         throw new FactoryConfigurationError(x.toString(), x);
      }

      return classLoader;
   }

   private static Object newInstance(String className, ClassLoader classLoader) throws FactoryConfigurationError {
      try {
         Class spiClass;
         if (classLoader == null) {
            spiClass = Class.forName(className);
         } else {
            spiClass = classLoader.loadClass(className);
         }

         return spiClass.newInstance();
      } catch (ClassNotFoundException x) {
         throw new FactoryConfigurationError("Provider " + className + " not found", x);
      } catch (Exception x) {
         throw new FactoryConfigurationError("Provider " + className + " could not be instantiated: " + x, x);
      }
   }

   static Object find(String factoryId) throws FactoryConfigurationError {
      return find(factoryId, (String)null);
   }

   static Object find(String factoryId, String fallbackClassName) throws FactoryConfigurationError {
      ClassLoader classLoader = findClassLoader();
      return find(factoryId, fallbackClassName, classLoader);
   }

   static Object find(String factoryId, String fallbackClassName, ClassLoader classLoader) throws FactoryConfigurationError {
      try {
         String systemProp = System.getProperty(factoryId);
         if (systemProp != null) {
            debugPrintln("found system property" + systemProp);
            return newInstance(systemProp, classLoader);
         }
      } catch (SecurityException var8) {
      }

      try {
         String javah = System.getProperty("java.home");
         String configFile = javah + File.separator + "lib" + File.separator + "jaxp.properties";
         File f = new File(configFile);
         if (f.exists()) {
            Properties props = new Properties();
            props.load(new FileInputStream(f));
            String factoryClassName = props.getProperty(factoryId);
            if (factoryClassName != null && factoryClassName.length() > 0) {
               debugPrintln("found java.home property " + factoryClassName);
               return newInstance(factoryClassName, classLoader);
            }
         }
      } catch (Exception ex) {
         if (debug) {
            ex.printStackTrace();
         }
      }

      String serviceId = "META-INF/services/" + factoryId;

      try {
         InputStream is = null;
         if (classLoader == null) {
            is = ClassLoader.getSystemResourceAsStream(serviceId);
         } else {
            is = classLoader.getResourceAsStream(serviceId);
         }

         if (is != null) {
            debugPrintln("found " + serviceId);
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String factoryClassName = rd.readLine();
            rd.close();
            if (factoryClassName != null && !"".equals(factoryClassName)) {
               debugPrintln("loaded from services: " + factoryClassName);
               return newInstance(factoryClassName, classLoader);
            }
         }
      } catch (Exception ex) {
         if (debug) {
            ex.printStackTrace();
         }
      }

      if (fallbackClassName == null) {
         throw new FactoryConfigurationError("Provider for " + factoryId + " cannot be found", (Exception)null);
      } else {
         debugPrintln("loaded from fallback value: " + fallbackClassName);
         return newInstance(fallbackClassName, classLoader);
      }
   }

   // $FF: synthetic method
   static Class class$(String x0) {
      try {
         return Class.forName(x0);
      } catch (ClassNotFoundException x1) {
         throw new NoClassDefFoundError(x1.getMessage());
      }
   }

   static {
      try {
         debug = System.getProperty("xml.stream.debug") != null;
      } catch (Exception var1) {
      }

   }

   private abstract static class ClassLoaderFinder {
      private ClassLoaderFinder() {
      }

      abstract ClassLoader getContextClassLoader();
   }

   static class ClassLoaderFinderConcrete extends ClassLoaderFinder {
      ClassLoader getContextClassLoader() {
         return Thread.currentThread().getContextClassLoader();
      }
   }
}
