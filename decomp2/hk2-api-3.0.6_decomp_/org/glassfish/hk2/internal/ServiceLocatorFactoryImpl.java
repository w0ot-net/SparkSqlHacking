package org.glassfish.hk2.internal;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.api.ServiceLocatorListener;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.osgiresourcelocator.ServiceLoader;
import org.glassfish.hk2.utilities.reflection.Logger;

public class ServiceLocatorFactoryImpl extends ServiceLocatorFactory {
   private static final String DEBUG_SERVICE_LOCATOR_PROPERTY = "org.jvnet.hk2.properties.debug.service.locator.lifecycle";
   private static final boolean DEBUG_SERVICE_LOCATOR_LIFECYCLE = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.service.locator.lifecycle", "false"));
      }
   });
   private static final Object sLock = new Object();
   private static int name_count = 0;
   private static final String GENERATED_NAME_PREFIX = "__HK2_Generated_";
   private final Object lock = new Object();
   private final HashMap serviceLocators = new HashMap();
   private final HashSet listeners = new HashSet();

   private static ServiceLocatorGenerator getGeneratorSecure() {
      return (ServiceLocatorGenerator)AccessController.doPrivileged(new PrivilegedAction() {
         public ServiceLocatorGenerator run() {
            try {
               return ServiceLocatorFactoryImpl.getGenerator();
            } catch (Throwable th) {
               Logger.getLogger().warning("Error finding implementation of hk2:", th);
               return null;
            }
         }
      });
   }

   private static Iterable getOSGiSafeGenerators() {
      try {
         return ServiceLoader.lookupProviderInstances(ServiceLocatorGenerator.class);
      } catch (Throwable var1) {
         return null;
      }
   }

   private static ServiceLocatorGenerator getGenerator() {
      Iterable<? extends ServiceLocatorGenerator> generators = getOSGiSafeGenerators();
      if (generators != null) {
         Iterator<? extends ServiceLocatorGenerator> iterator = generators.iterator();
         return iterator.hasNext() ? (ServiceLocatorGenerator)iterator.next() : null;
      } else {
         ClassLoader classLoader = ServiceLocatorFactoryImpl.class.getClassLoader();
         Iterator<ServiceLocatorGenerator> providers = java.util.ServiceLoader.load(ServiceLocatorGenerator.class, classLoader).iterator();

         while(providers.hasNext()) {
            try {
               return (ServiceLocatorGenerator)providers.next();
            } catch (ServiceConfigurationError sce) {
               Logger.getLogger().debug("ServiceLocatorFactoryImpl", "getGenerator", sce);
            }
         }

         Logger.getLogger().warning("Cannot find a default implementation of the HK2 ServiceLocatorGenerator");
         return null;
      }
   }

   public ServiceLocator create(String name) {
      return this.create(name, (ServiceLocator)null, (ServiceLocatorGenerator)null, ServiceLocatorFactory.CreatePolicy.RETURN);
   }

   public ServiceLocator find(String name) {
      synchronized(this.lock) {
         return (ServiceLocator)this.serviceLocators.get(name);
      }
   }

   public void destroy(String name) {
      this.destroy(name, (ServiceLocator)null);
   }

   private void destroy(String name, ServiceLocator locator) {
      ServiceLocator killMe = null;
      synchronized(this.lock) {
         if (name != null) {
            killMe = (ServiceLocator)this.serviceLocators.remove(name);
         }

         if (DEBUG_SERVICE_LOCATOR_LIFECYCLE) {
            Logger.getLogger().debug("ServiceFactoryImpl destroying locator with name " + name + " and locator " + locator + " with found locator " + killMe, new Throwable());
         }

         if (killMe == null) {
            killMe = locator;
         }

         if (killMe != null) {
            for(ServiceLocatorListener listener : this.listeners) {
               try {
                  listener.locatorDestroyed(killMe);
               } catch (Throwable th) {
                  Logger.getLogger().debug(this.getClass().getName(), "destroy " + listener, th);
               }
            }
         }
      }

      if (killMe != null) {
         killMe.shutdown();
      }

   }

   public void destroy(ServiceLocator locator) {
      if (locator != null) {
         this.destroy(locator.getName(), locator);
      }
   }

   public ServiceLocator create(String name, ServiceLocator parent) {
      return this.create(name, parent, (ServiceLocatorGenerator)null, ServiceLocatorFactory.CreatePolicy.RETURN);
   }

   private static String getGeneratedName() {
      synchronized(sLock) {
         return "__HK2_Generated_" + name_count++;
      }
   }

   public ServiceLocator create(String name, ServiceLocator parent, ServiceLocatorGenerator generator) {
      return this.create(name, parent, generator, ServiceLocatorFactory.CreatePolicy.RETURN);
   }

   private void callListenerAdded(ServiceLocator added) {
      for(ServiceLocatorListener listener : this.listeners) {
         try {
            listener.locatorAdded(added);
         } catch (Throwable th) {
            Logger.getLogger().debug(this.getClass().getName(), "create " + listener, th);
         }
      }

   }

   public ServiceLocator create(String name, ServiceLocator parent, ServiceLocatorGenerator generator, ServiceLocatorFactory.CreatePolicy policy) {
      if (DEBUG_SERVICE_LOCATOR_LIFECYCLE) {
         Logger.getLogger().debug("ServiceFactoryImpl given create of " + name + " with parent " + parent + " with generator " + generator + " and policy " + policy, new Throwable());
      }

      synchronized(this.lock) {
         if (name == null) {
            name = getGeneratedName();
            ServiceLocator added = this.internalCreate(name, parent, generator);
            this.callListenerAdded(added);
            if (DEBUG_SERVICE_LOCATOR_LIFECYCLE) {
               Logger.getLogger().debug("ServiceFactoryImpl added untracked listener " + added);
            }

            return added;
         } else {
            ServiceLocator retVal = (ServiceLocator)this.serviceLocators.get(name);
            if (retVal != null) {
               if (policy == null || ServiceLocatorFactory.CreatePolicy.RETURN.equals(policy)) {
                  if (DEBUG_SERVICE_LOCATOR_LIFECYCLE) {
                     Logger.getLogger().debug("ServiceFactoryImpl added found listener under RETURN policy of " + retVal);
                  }

                  return retVal;
               }

               if (!policy.equals(ServiceLocatorFactory.CreatePolicy.DESTROY)) {
                  throw new IllegalStateException("A ServiceLocator named " + name + " already exists");
               }

               this.destroy(retVal);
            }

            retVal = this.internalCreate(name, parent, generator);
            this.serviceLocators.put(name, retVal);
            this.callListenerAdded(retVal);
            if (DEBUG_SERVICE_LOCATOR_LIFECYCLE) {
               Logger.getLogger().debug("ServiceFactoryImpl created locator " + retVal);
            }

            return retVal;
         }
      }
   }

   private ServiceLocator internalCreate(String name, ServiceLocator parent, ServiceLocatorGenerator generator) {
      if (generator == null) {
         if (ServiceLocatorFactoryImpl.DefaultGeneratorInitializer.defaultGenerator == null) {
            throw new IllegalStateException("No generator was provided and there is no default generator registered");
         }

         generator = ServiceLocatorFactoryImpl.DefaultGeneratorInitializer.defaultGenerator;
      }

      return generator.create(name, parent);
   }

   public void addListener(ServiceLocatorListener listener) {
      if (listener == null) {
         throw new IllegalArgumentException();
      } else {
         synchronized(this.lock) {
            if (!this.listeners.contains(listener)) {
               try {
                  HashSet<ServiceLocator> currentLocators = new HashSet(this.serviceLocators.values());
                  listener.initialize(Collections.unmodifiableSet(currentLocators));
               } catch (Throwable th) {
                  Logger.getLogger().debug(this.getClass().getName(), "addListener " + listener, th);
                  return;
               }

               this.listeners.add(listener);
            }
         }
      }
   }

   public void removeListener(ServiceLocatorListener listener) {
      if (listener == null) {
         throw new IllegalArgumentException();
      } else {
         synchronized(this.lock) {
            this.listeners.remove(listener);
         }
      }
   }

   private static final class DefaultGeneratorInitializer {
      private static final ServiceLocatorGenerator defaultGenerator = ServiceLocatorFactoryImpl.getGeneratorSecure();
   }
}
