package org.jvnet.hk2.internal;

import jakarta.inject.Singleton;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.ContextualInput;
import org.glassfish.hk2.utilities.cache.Cache;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.reflection.Logger;

@Singleton
public class SingletonContext implements Context {
   private int generationNumber = Integer.MIN_VALUE;
   private final ServiceLocatorImpl locator;
   private final Cache valueCache = new Cache(new Computable() {
      public Object compute(ContextualInput a) {
         ActiveDescriptor<Object> activeDescriptor = a.getDescriptor();
         Object cachedVal = activeDescriptor.getCache();
         if (cachedVal != null) {
            return cachedVal;
         } else {
            Object createdVal = activeDescriptor.create(a.getRoot());
            activeDescriptor.setCache(createdVal);
            if (activeDescriptor instanceof SystemDescriptor) {
               ((SystemDescriptor)activeDescriptor).setSingletonGeneration(SingletonContext.this.generationNumber++);
            }

            return createdVal;
         }
      }
   }, new Cache.CycleHandler() {
      public void handleCycle(ContextualInput key) {
         String var10004 = key.getDescriptor().getImplementation();
         throw new MultiException(new IllegalStateException("A circular dependency involving Singleton service " + var10004 + " was found.  Full descriptor is " + key.getDescriptor()));
      }
   });

   SingletonContext(ServiceLocatorImpl impl) {
      this.locator = impl;
   }

   public Class getScope() {
      return Singleton.class;
   }

   public Object findOrCreate(ActiveDescriptor activeDescriptor, ServiceHandle root) {
      try {
         return this.valueCache.compute(new ContextualInput(activeDescriptor, root));
      } catch (Throwable th) {
         if (th instanceof MultiException) {
            throw (MultiException)th;
         } else {
            throw new MultiException(th);
         }
      }
   }

   public boolean containsKey(ActiveDescriptor descriptor) {
      return this.valueCache.containsKey(new ContextualInput(descriptor, (ServiceHandle)null));
   }

   public boolean isActive() {
      return true;
   }

   public boolean supportsNullCreation() {
      return false;
   }

   public void shutdown() {
      List<ActiveDescriptor<?>> all = this.locator.getDescriptors(BuilderHelper.allFilter());
      long myLocatorId = this.locator.getLocatorId();
      TreeSet<SystemDescriptor<Object>> singlesOnly = new TreeSet(new GenerationComparator());

      for(ActiveDescriptor one : all) {
         if (one.getScope() != null && one.getScope().equals(Singleton.class.getName())) {
            synchronized(this) {
               if (one.getCache() == null) {
                  continue;
               }
            }

            if (one.getLocatorId() != null && one.getLocatorId() == myLocatorId) {
               SystemDescriptor<Object> oneAsObject = (SystemDescriptor)one;
               singlesOnly.add(oneAsObject);
            }
         }
      }

      for(SystemDescriptor one : singlesOnly) {
         this.destroyOne(one);
      }

   }

   public void destroyOne(ActiveDescriptor one) {
      this.valueCache.remove(new ContextualInput(one, (ServiceHandle)null));
      Object value = one.getCache();
      one.releaseCache();
      if (value != null) {
         try {
            one.dispose(value);
         } catch (Throwable th) {
            Logger.getLogger().debug("SingletonContext", "releaseOne", th);
         }

      }
   }

   private static class GenerationComparator implements Comparator, Serializable {
      private static final long serialVersionUID = -6931828935035131179L;

      public int compare(SystemDescriptor o1, SystemDescriptor o2) {
         if (o1.getSingletonGeneration() > o2.getSingletonGeneration()) {
            return -1;
         } else {
            return o1.getSingletonGeneration() == o2.getSingletonGeneration() ? 0 : 1;
         }
      }
   }
}
