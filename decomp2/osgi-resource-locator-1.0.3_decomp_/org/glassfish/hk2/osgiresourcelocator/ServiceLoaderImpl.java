package org.glassfish.hk2.osgiresourcelocator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.BundleReference;

public final class ServiceLoaderImpl extends ServiceLoader {
   private ReadWriteLock rwLock = new ReentrantReadWriteLock();
   private BundleListener bundleTracker;
   private BundleContext bundleContext;
   private ProvidersList providersList = new ProvidersList();

   public ServiceLoaderImpl() {
      ClassLoader cl = this.getClass().getClassLoader();
      if (cl instanceof BundleReference) {
         this.bundleContext = this.getBundleContextSecured(((BundleReference)BundleReference.class.cast(cl)).getBundle());
      }

      if (this.bundleContext == null) {
         throw new RuntimeException("There is no bundle context available yet. Instatiate this class in STARTING or ACTIVE state only");
      }
   }

   private BundleContext getBundleContextSecured(final Bundle bundle) {
      return System.getSecurityManager() != null ? (BundleContext)AccessController.doPrivileged(new PrivilegedAction() {
         public BundleContext run() {
            return bundle.getBundleContext();
         }
      }) : bundle.getBundleContext();
   }

   public void trackBundles() {
      assert this.bundleTracker == null;

      this.bundleTracker = new BundleTracker();
      this.bundleContext.addBundleListener(this.bundleTracker);

      for(Bundle bundle : this.bundleContext.getBundles()) {
         this.addProviders(bundle);
      }

   }

   Iterable lookupProviderInstances1(Class serviceClass, ServiceLoader.ProviderFactory factory) {
      if (factory == null) {
         factory = new DefaultFactory();
      }

      List<T> providers = new ArrayList();

      for(Class c : this.lookupProviderClasses1(serviceClass)) {
         try {
            T providerInstance = (T)factory.make(c, serviceClass);
            if (providerInstance != null) {
               providers.add(providerInstance);
            } else {
               this.debug(factory + " returned null provider instance!!!");
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return providers;
   }

   Iterable lookupProviderClasses1(Class serviceClass) {
      List<Class> providerClasses = new ArrayList();
      this.rwLock.readLock().lock();

      Object var16;
      try {
         String serviceName = serviceClass.getName();

         for(ProvidersPerBundle providersPerBundle : this.providersList.getAllProviders()) {
            Bundle bundle = this.bundleContext.getBundle(providersPerBundle.getBundleId());
            if (bundle != null) {
               List<String> providerNames = (List)providersPerBundle.getServiceToProvidersMap().get(serviceName);
               if (providerNames != null) {
                  for(String providerName : providerNames) {
                     try {
                        Class providerClass = this.loadClassSecured(bundle, providerName);
                        if (this.isCompatible(providerClass, serviceClass)) {
                           providerClasses.add(providerClass);
                        }
                     } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }
         }

         var16 = providerClasses;
      } finally {
         this.rwLock.readLock().unlock();
      }

      return (Iterable)var16;
   }

   private Class loadClassSecured(final Bundle bundle, final String name) throws ClassNotFoundException {
      if (System.getSecurityManager() != null) {
         try {
            return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction() {
               public Class run() throws ClassNotFoundException {
                  return bundle.loadClass(name);
               }
            });
         } catch (PrivilegedActionException e) {
            throw (ClassNotFoundException)ClassNotFoundException.class.cast(e.getException());
         }
      } else {
         return bundle.loadClass(name);
      }
   }

   private boolean isCompatible(Class providerClass, Class serviceClass) {
      try {
         Class<?> serviceClassSeenByProviderClass = Class.forName(serviceClass.getName(), false, providerClass.getClassLoader());
         boolean isCompatible = serviceClassSeenByProviderClass == serviceClass;
         if (!isCompatible) {
            this.debug(providerClass + " loaded by " + providerClass.getClassLoader() + " sees " + serviceClass + " from " + serviceClassSeenByProviderClass.getClassLoader() + ", where as caller uses " + serviceClass + " loaded by " + serviceClass.getClassLoader());
         }

         return isCompatible;
      } catch (ClassNotFoundException e) {
         this.debug("Unable to reach " + serviceClass + " from " + providerClass + ", which is loaded by " + providerClass.getClassLoader(), e);
         return true;
      }
   }

   private List load(InputStream is) throws IOException {
      List<String> providerNames = new ArrayList();

      try {
         Scanner scanner = new Scanner(is);
         String commentPattern = "#";

         while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.startsWith("#")) {
               StringTokenizer st = new StringTokenizer(line);
               if (st.hasMoreTokens()) {
                  providerNames.add(st.nextToken());
               }
            }
         }
      } finally {
         is.close();
      }

      return providerNames;
   }

   private void addProviders(Bundle bundle) {
      this.rwLock.writeLock().lock();

      try {
         String SERVICE_LOCATION = "META-INF/services";
         if (bundle.getEntry("META-INF/services") != null) {
            Enumeration<String> entries = bundle.getEntryPaths("META-INF/services");
            if (entries == null) {
               return;
            }

            ProvidersPerBundle providers = new ProvidersPerBundle(bundle.getBundleId());

            while(entries.hasMoreElements()) {
               String entry = (String)entries.nextElement();
               String serviceName = entry.substring("META-INF/services".length() + 1);
               URL url = bundle.getEntry(entry);

               try {
                  InputStream is = url.openStream();
                  List<String> providerNames = this.load(is);
                  this.debug("Bundle = " + bundle + ", serviceName = " + serviceName + ", providerNames = " + providerNames);
                  providers.put(serviceName, providerNames);
               } catch (IOException var13) {
               }
            }

            this.providersList.addProviders(providers);
            return;
         }
      } finally {
         this.rwLock.writeLock().unlock();
      }

   }

   private synchronized void removeProviders(Bundle bundle) {
      this.rwLock.writeLock().lock();

      try {
         this.providersList.removeProviders(bundle.getBundleId());
      } finally {
         this.rwLock.writeLock().unlock();
      }

   }

   private void debug(String s) {
      if (Boolean.valueOf(this.bundleContext.getProperty("org.glassfish.hk2.osgiresourcelocator.debug"))) {
         System.out.println("org.glassfish.hk2.osgiresourcelocator:DEBUG: " + s);
      }

   }

   private void debug(String s, Throwable t) {
      if (Boolean.valueOf(this.bundleContext.getProperty("org.glassfish.hk2.osgiresourcelocator.debug"))) {
         System.out.println("org.glassfish.hk2.osgiresourcelocator:DEBUG: " + s);
         t.printStackTrace(System.out);
      }

   }

   private class BundleTracker implements BundleListener {
      private BundleTracker() {
      }

      public void bundleChanged(BundleEvent event) {
         Bundle bundle = event.getBundle();
         switch (event.getType()) {
            case 1:
               ServiceLoaderImpl.this.addProviders(bundle);
               break;
            case 8:
               ServiceLoaderImpl.this.removeProviders(bundle);
               ServiceLoaderImpl.this.addProviders(bundle);
               break;
            case 16:
               ServiceLoaderImpl.this.removeProviders(bundle);
         }

      }
   }

   private static class ProvidersPerBundle {
      private long bundleId;
      Map serviceToProvidersMap;

      private ProvidersPerBundle(long bundleId) {
         this.serviceToProvidersMap = new HashMap();
         this.bundleId = bundleId;
      }

      public long getBundleId() {
         return this.bundleId;
      }

      public void put(String serviceName, List providerNames) {
         this.serviceToProvidersMap.put(serviceName, providerNames);
      }

      public Map getServiceToProvidersMap() {
         return this.serviceToProvidersMap;
      }
   }

   private static class ProvidersList {
      private List allProviders;

      private ProvidersList() {
         this.allProviders = new LinkedList();
      }

      void addProviders(ProvidersPerBundle providers) {
         long bundleId = providers.getBundleId();
         int idx = 0;

         for(ProvidersPerBundle providersPerBundle : this.getAllProviders()) {
            if (providersPerBundle.getBundleId() > bundleId) {
               this.getAllProviders().add(idx, providers);
               return;
            }
         }

         this.getAllProviders().add(providers);
      }

      void removeProviders(long bundleId) {
         Iterator<ProvidersPerBundle> iterator = this.getAllProviders().iterator();

         while(iterator.hasNext()) {
            ProvidersPerBundle providersPerBundle = (ProvidersPerBundle)iterator.next();
            if (providersPerBundle.getBundleId() == bundleId) {
               iterator.remove();
               return;
            }
         }

      }

      public List getAllProviders() {
         return this.allProviders;
      }
   }

   private static class DefaultFactory implements ServiceLoader.ProviderFactory {
      private DefaultFactory() {
      }

      public Object make(Class providerClass, Class serviceClass) throws Exception {
         return serviceClass.isAssignableFrom(providerClass) ? providerClass.newInstance() : null;
      }
   }
}
