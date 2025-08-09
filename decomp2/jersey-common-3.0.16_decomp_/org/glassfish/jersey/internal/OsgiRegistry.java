package org.glassfish.jersey.internal;

import jakarta.ws.rs.ProcessingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleReference;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.SynchronousBundleListener;

public final class OsgiRegistry implements SynchronousBundleListener {
   private static final String WEB_INF_CLASSES = "WEB-INF/classes/";
   private static final String CoreBundleSymbolicNAME = "org.glassfish.jersey.core.jersey-common";
   private static final Logger LOGGER = Logger.getLogger(OsgiRegistry.class.getName());
   private final BundleContext bundleContext;
   private final Map factories = new HashMap();
   private final ReadWriteLock lock = new ReentrantReadWriteLock();
   private static OsgiRegistry instance;
   private final Map classToBundleMapping = new HashMap();

   public static synchronized OsgiRegistry getInstance() {
      if (instance == null) {
         ClassLoader classLoader = (ClassLoader)AccessController.doPrivileged(ReflectionHelper.getClassLoaderPA(ReflectionHelper.class));
         if (classLoader instanceof BundleReference) {
            BundleContext context = FrameworkUtil.getBundle(OsgiRegistry.class).getBundleContext();
            if (context != null) {
               instance = new OsgiRegistry(context);
            }
         }
      }

      return instance;
   }

   public void bundleChanged(BundleEvent event) {
      if (event.getType() == 32) {
         this.register(event.getBundle());
      } else if (event.getType() == 64 || event.getType() == 16) {
         Bundle unregisteredBundle = event.getBundle();
         this.lock.writeLock().lock();

         try {
            this.factories.remove(unregisteredBundle.getBundleId());
            if (unregisteredBundle.getSymbolicName().equals("org.glassfish.jersey.core.jersey-common")) {
               this.bundleContext.removeBundleListener(this);
               this.factories.clear();
            }
         } finally {
            this.lock.writeLock().unlock();
         }
      }

   }

   public static String bundleEntryPathToClassName(String packagePath, String bundleEntryPath) {
      packagePath = normalizedPackagePath(packagePath);
      if (bundleEntryPath.contains("WEB-INF/classes/")) {
         bundleEntryPath = bundleEntryPath.substring(bundleEntryPath.indexOf("WEB-INF/classes/") + "WEB-INF/classes/".length());
      }

      int packageIndex = bundleEntryPath.indexOf(packagePath);
      String normalizedClassNamePath = packageIndex > -1 ? bundleEntryPath.substring(packageIndex) : packagePath + bundleEntryPath.substring(bundleEntryPath.lastIndexOf(47) + 1);
      return (normalizedClassNamePath.startsWith("/") ? normalizedClassNamePath.substring(1) : normalizedClassNamePath).replace(".class", "").replace('/', '.');
   }

   public static boolean isPackageLevelEntry(String packagePath, String entryPath) {
      packagePath = normalizedPackagePath(packagePath);
      String entryWithoutPackagePath = entryPath.contains(packagePath) ? entryPath.substring(entryPath.indexOf(packagePath) + packagePath.length()) : entryPath;
      return !(entryWithoutPackagePath.startsWith("/") ? entryWithoutPackagePath.substring(1) : entryWithoutPackagePath).contains("/");
   }

   public static String normalizedPackagePath(String packagePath) {
      packagePath = packagePath.startsWith("/") ? packagePath.substring(1) : packagePath;
      packagePath = packagePath.endsWith("/") ? packagePath : packagePath + "/";
      packagePath = "/".equals(packagePath) ? "" : packagePath;
      return packagePath;
   }

   public Enumeration getPackageResources(String packagePath, ClassLoader classLoader, boolean recursive) {
      List<URL> result = new LinkedList();

      for(Bundle bundle : this.bundleContext.getBundles()) {
         for(String bundlePackagePath : new String[]{packagePath, "WEB-INF/classes/" + packagePath}) {
            Enumeration<URL> enumeration = findEntries(bundle, bundlePackagePath, "*.class", recursive);
            if (enumeration != null) {
               while(enumeration.hasMoreElements()) {
                  URL url = (URL)enumeration.nextElement();
                  String path = url.getPath();
                  this.classToBundleMapping.put(bundleEntryPathToClassName(packagePath, path), bundle);
                  result.add(url);
               }
            }
         }

         Enumeration<URL> jars = findEntries(bundle, "/", "*.jar", true);
         if (jars != null) {
            while(jars.hasMoreElements()) {
               URL jar = (URL)jars.nextElement();
               InputStream inputStream = classLoader.getResourceAsStream(jar.getPath());
               if (inputStream == null) {
                  LOGGER.config(LocalizationMessages.OSGI_REGISTRY_ERROR_OPENING_RESOURCE_STREAM(jar));
               } else {
                  JarInputStream jarInputStream;
                  try {
                     jarInputStream = new JarInputStream(inputStream);
                  } catch (IOException ex) {
                     LOGGER.log(Level.CONFIG, LocalizationMessages.OSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(jar), ex);

                     try {
                        inputStream.close();
                     } catch (IOException var26) {
                     }
                     continue;
                  }

                  try {
                     JarEntry jarEntry;
                     try {
                        while((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                           String jarEntryName = jarEntry.getName();
                           String jarEntryNameLeadingSlash = jarEntryName.startsWith("/") ? jarEntryName : "/" + jarEntryName;
                           if (jarEntryName.endsWith(".class") && jarEntryNameLeadingSlash.contains("/" + normalizedPackagePath(packagePath)) && (recursive || isPackageLevelEntry(packagePath, jarEntryName))) {
                              this.classToBundleMapping.put(jarEntryName.replace(".class", "").replace('/', '.'), bundle);
                              result.add(bundle.getResource(jarEntryName));
                           }
                        }
                     } catch (Exception ex) {
                        LOGGER.log(Level.CONFIG, LocalizationMessages.OSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(jar), ex);
                     }
                  } finally {
                     try {
                        jarInputStream.close();
                     } catch (IOException var25) {
                     }

                  }
               }
            }
         }
      }

      return Collections.enumeration(result);
   }

   public Class classForNameWithException(String className) throws ClassNotFoundException {
      Bundle bundle = (Bundle)this.classToBundleMapping.get(className);
      if (bundle == null) {
         throw new ClassNotFoundException(className);
      } else {
         return loadClass(bundle, className);
      }
   }

   public ResourceBundle getResourceBundle(String bundleName) {
      int lastDotIndex = bundleName.lastIndexOf(46);
      String path = bundleName.substring(0, lastDotIndex).replace('.', '/');
      String propertiesName = bundleName.substring(lastDotIndex + 1, bundleName.length()) + ".properties";

      for(Bundle bundle : this.bundleContext.getBundles()) {
         Enumeration<URL> entries = findEntries(bundle, path, propertiesName, false);
         if (entries != null && entries.hasMoreElements()) {
            URL entryUrl = (URL)entries.nextElement();

            try {
               return new PropertyResourceBundle(entryUrl.openStream());
            } catch (IOException var12) {
               if (LOGGER.isLoggable(Level.FINE)) {
                  LOGGER.fine("Exception caught when tried to load resource bundle in OSGi");
               }

               return null;
            }
         }
      }

      return null;
   }

   private OsgiRegistry(BundleContext bundleContext) {
      this.bundleContext = bundleContext;
   }

   void hookUp() {
      this.setOSGiServiceFinderIteratorProvider();
      this.bundleContext.addBundleListener(this);
      this.registerExistingBundles();
   }

   private void registerExistingBundles() {
      for(Bundle bundle : this.bundleContext.getBundles()) {
         if (bundle.getState() == 4 || bundle.getState() == 8 || bundle.getState() == 32 || bundle.getState() == 16) {
            this.register(bundle);
         }
      }

   }

   private void setOSGiServiceFinderIteratorProvider() {
      ServiceFinder.setIteratorProvider(new OsgiServiceFinder());
   }

   private void register(Bundle bundle) {
      if (LOGGER.isLoggable(Level.FINEST)) {
         LOGGER.log(Level.FINEST, "checking bundle {0}", bundle.getBundleId());
      }

      this.lock.writeLock().lock();

      Map<String, Callable<List<Class<?>>>> map;
      try {
         map = (Map)this.factories.get(bundle.getBundleId());
         if (map == null) {
            map = new ConcurrentHashMap();
            this.factories.put(bundle.getBundleId(), map);
         }
      } finally {
         this.lock.writeLock().unlock();
      }

      Enumeration<URL> e = findEntries(bundle, "META-INF/services/", "*", false);
      if (e != null) {
         while(e.hasMoreElements()) {
            URL u = (URL)e.nextElement();
            String url = u.toString();
            if (!url.endsWith("/")) {
               String factoryId = url.substring(url.lastIndexOf("/") + 1);
               map.put(factoryId, new BundleSpiProvidersLoader(factoryId, u, bundle));
            }
         }
      }

   }

   private List locateAllProviders(Class serviceClass) {
      this.lock.readLock().lock();

      Object var12;
      try {
         List<Class<?>> result = new LinkedList();

         for(Map value : this.factories.values()) {
            if (value.containsKey(serviceClass.getName())) {
               try {
                  for(Class clazz : (List)((Callable)value.get(serviceClass.getName())).call()) {
                     if (serviceClass.isAssignableFrom(clazz)) {
                        result.add(clazz);
                     } else if (LOGGER.isLoggable(Level.FINER)) {
                        LOGGER.log(Level.FINER, "Ignoring provider class " + clazz.getName() + " because it is not assignable to  service class " + serviceClass.getName());
                     }
                  }
               } catch (Exception var10) {
               }
            }
         }

         var12 = result;
      } finally {
         this.lock.readLock().unlock();
      }

      return (List)var12;
   }

   private static Class loadClass(final Bundle bundle, final String className) throws ClassNotFoundException {
      try {
         return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Class run() throws ClassNotFoundException {
               return bundle.loadClass(className);
            }
         });
      } catch (PrivilegedActionException ex) {
         Exception originalException = ex.getException();
         if (originalException instanceof ClassNotFoundException) {
            throw (ClassNotFoundException)originalException;
         } else if (originalException instanceof RuntimeException) {
            throw (RuntimeException)originalException;
         } else {
            throw new ProcessingException(originalException);
         }
      }
   }

   private static Enumeration findEntries(final Bundle bundle, final String path, final String fileNamePattern, final boolean recursive) {
      return (Enumeration)AccessController.doPrivileged(new PrivilegedAction() {
         public Enumeration run() {
            return bundle.findEntries(path, fileNamePattern, recursive);
         }
      });
   }

   private final class OsgiServiceFinder extends ServiceFinder.ServiceIteratorProvider {
      final ServiceFinder.ServiceIteratorProvider defaultIterator;

      private OsgiServiceFinder() {
         this.defaultIterator = new ServiceFinder.DefaultServiceIteratorProvider();
      }

      public Iterator createIterator(final Class serviceClass, final String serviceName, ClassLoader loader, boolean ignoreOnClassNotFound) {
         final List<Class<?>> providerClasses = OsgiRegistry.this.locateAllProviders(serviceClass);
         return !providerClasses.isEmpty() ? new Iterator() {
            Iterator it = providerClasses.iterator();

            public boolean hasNext() {
               return this.it.hasNext();
            }

            public Object next() {
               Class<T> nextClass = (Class)this.it.next();

               try {
                  return nextClass.newInstance();
               } catch (Exception ex) {
                  ServiceConfigurationError sce = new ServiceConfigurationError(serviceName + ": " + LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextClass.getName(), serviceClass, ex.getLocalizedMessage()));
                  sce.initCause(ex);
                  throw sce;
               }
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         } : this.defaultIterator.createIterator(serviceClass, serviceName, loader, ignoreOnClassNotFound);
      }

      public Iterator createClassIterator(Class service, String serviceName, ClassLoader loader, boolean ignoreOnClassNotFound) {
         final List<Class<?>> providerClasses = OsgiRegistry.this.locateAllProviders(service);
         return !providerClasses.isEmpty() ? new Iterator() {
            Iterator it = providerClasses.iterator();

            public boolean hasNext() {
               return this.it.hasNext();
            }

            public Class next() {
               return (Class)this.it.next();
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         } : this.defaultIterator.createClassIterator(service, serviceName, loader, ignoreOnClassNotFound);
      }
   }

   private static class BundleSpiProvidersLoader implements Callable {
      private final String spi;
      private final URL spiRegistryUrl;
      private final String spiRegistryUrlString;
      private final Bundle bundle;

      BundleSpiProvidersLoader(String spi, URL spiRegistryUrl, Bundle bundle) {
         this.spi = spi;
         this.spiRegistryUrl = spiRegistryUrl;
         this.spiRegistryUrlString = spiRegistryUrl.toExternalForm();
         this.bundle = bundle;
      }

      public List call() throws Exception {
         BufferedReader reader = null;

         Object var4;
         try {
            if (OsgiRegistry.LOGGER.isLoggable(Level.FINEST)) {
               OsgiRegistry.LOGGER.log(Level.FINEST, "Loading providers for SPI: {0}", this.spi);
            }

            reader = new BufferedReader(new InputStreamReader(this.spiRegistryUrl.openStream(), "UTF-8"));
            List<Class<?>> providerClasses = new ArrayList();

            String providerClassName;
            while((providerClassName = reader.readLine()) != null) {
               if (providerClassName.trim().length() != 0 && !providerClassName.startsWith("#")) {
                  if (OsgiRegistry.LOGGER.isLoggable(Level.FINEST)) {
                     OsgiRegistry.LOGGER.log(Level.FINEST, "SPI provider: {0}", providerClassName);
                  }

                  providerClasses.add(OsgiRegistry.loadClass(this.bundle, providerClassName));
               }
            }

            var4 = providerClasses;
         } catch (Exception e) {
            OsgiRegistry.LOGGER.log(Level.WARNING, LocalizationMessages.EXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), e);
            throw e;
         } catch (Error e) {
            OsgiRegistry.LOGGER.log(Level.WARNING, LocalizationMessages.ERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), e);
            throw e;
         } finally {
            if (reader != null) {
               try {
                  reader.close();
               } catch (IOException ioe) {
                  OsgiRegistry.LOGGER.log(Level.FINE, "Error closing SPI registry stream:" + this.spiRegistryUrl, ioe);
               }
            }

         }

         return (List)var4;
      }

      public String toString() {
         return this.spiRegistryUrlString;
      }

      public int hashCode() {
         return this.spiRegistryUrlString.hashCode();
      }

      public boolean equals(Object obj) {
         return obj instanceof BundleSpiProvidersLoader ? this.spiRegistryUrlString.equals(((BundleSpiProvidersLoader)obj).spiRegistryUrlString) : false;
      }
   }
}
