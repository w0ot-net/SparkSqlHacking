package org.glassfish.hk2.utilities;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.Immediate;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.Visibility;
import org.glassfish.hk2.internal.HandleAndService;
import org.glassfish.hk2.internal.ImmediateLocalLocatorFilter;

@Singleton
@Visibility(DescriptorVisibility.LOCAL)
public class ImmediateContext implements Context {
   private final HashMap currentImmediateServices = new HashMap();
   private final HashMap creating = new HashMap();
   private final ServiceLocator locator;
   private final Filter validationFilter;

   @Inject
   private ImmediateContext(ServiceLocator locator) {
      this.locator = locator;
      this.validationFilter = new ImmediateLocalLocatorFilter(locator.getLocatorId());
   }

   public Class getScope() {
      return Immediate.class;
   }

   public Object findOrCreate(ActiveDescriptor activeDescriptor, ServiceHandle root) {
      U retVal = (U)null;
      synchronized(this) {
         HandleAndService has = (HandleAndService)this.currentImmediateServices.get(activeDescriptor);
         if (has != null) {
            return has.getService();
         }

         while(this.creating.containsKey(activeDescriptor)) {
            long alreadyCreatingThread = (Long)this.creating.get(activeDescriptor);
            if (alreadyCreatingThread == Thread.currentThread().getId()) {
               String var10004 = activeDescriptor.getImplementation();
               throw new MultiException(new IllegalStateException("A circular dependency involving Immediate service " + var10004 + " was found.  Full descriptor is " + activeDescriptor));
            }

            try {
               this.wait();
            } catch (InterruptedException ie) {
               throw new MultiException(ie);
            }
         }

         has = (HandleAndService)this.currentImmediateServices.get(activeDescriptor);
         if (has != null) {
            return has.getService();
         }

         this.creating.put(activeDescriptor, Thread.currentThread().getId());
      }

      try {
         retVal = (U)activeDescriptor.create(root);
      } finally {
         synchronized(this) {
            ServiceHandle<?> discoveredRoot = null;
            if (root != null && root.getActiveDescriptor().equals(activeDescriptor)) {
               discoveredRoot = root;
            }

            if (retVal != null) {
               this.currentImmediateServices.put(activeDescriptor, new HandleAndService(discoveredRoot, retVal));
            }

            this.creating.remove(activeDescriptor);
            this.notifyAll();
         }
      }

      return retVal;
   }

   public synchronized boolean containsKey(ActiveDescriptor descriptor) {
      return this.currentImmediateServices.containsKey(descriptor);
   }

   public void destroyOne(ActiveDescriptor descriptor) {
      this.destroyOne(descriptor, (List)null);
   }

   private void destroyOne(ActiveDescriptor descriptor, List errorHandlers) {
      if (errorHandlers == null) {
         errorHandlers = this.locator.getAllServices(ImmediateErrorHandler.class);
      }

      synchronized(this) {
         HandleAndService has = (HandleAndService)this.currentImmediateServices.remove(descriptor);
         Object instance = has.getService();

         try {
            descriptor.dispose(instance);
         } catch (Throwable var12) {
            Throwable th = var12;

            for(ImmediateErrorHandler ieh : errorHandlers) {
               try {
                  ieh.preDestroyFailed(descriptor, th);
               } catch (Throwable var11) {
               }
            }
         }

      }
   }

   public boolean supportsNullCreation() {
      return false;
   }

   public boolean isActive() {
      return true;
   }

   public void shutdown() {
      List<ImmediateErrorHandler> errorHandlers = this.locator.getAllServices(ImmediateErrorHandler.class);
      synchronized(this) {
         for(Map.Entry entry : new HashSet(this.currentImmediateServices.entrySet())) {
            HandleAndService has = (HandleAndService)entry.getValue();
            ServiceHandle<?> handle = has.getHandle();
            if (handle != null) {
               handle.destroy();
            } else {
               this.destroyOne((ActiveDescriptor)entry.getKey(), errorHandlers);
            }
         }

      }
   }

   private List getImmediateServices() {
      List<ActiveDescriptor<?>> inScopeAndInThisLocator;
      try {
         inScopeAndInThisLocator = this.locator.getDescriptors(this.validationFilter);
      } catch (IllegalStateException var3) {
         inScopeAndInThisLocator = Collections.emptyList();
      }

      return inScopeAndInThisLocator;
   }

   public Filter getValidationFilter() {
      return this.validationFilter;
   }

   public void doWork() {
      List<ActiveDescriptor<?>> inScopeAndInThisLocator = this.getImmediateServices();

      List<ImmediateErrorHandler> errorHandlers;
      try {
         errorHandlers = this.locator.getAllServices(ImmediateErrorHandler.class);
      } catch (IllegalStateException var14) {
         return;
      }

      LinkedHashSet<ActiveDescriptor<?>> newFullSet = new LinkedHashSet(inScopeAndInThisLocator);
      LinkedHashSet<ActiveDescriptor<?>> addMe = new LinkedHashSet();
      synchronized(this) {
         while(this.creating.size() > 0) {
            try {
               this.wait();
            } catch (InterruptedException ie) {
               throw new RuntimeException(ie);
            }
         }

         LinkedHashSet<ActiveDescriptor<?>> oldSet = new LinkedHashSet(this.currentImmediateServices.keySet());

         for(ActiveDescriptor ad : inScopeAndInThisLocator) {
            if (!oldSet.contains(ad)) {
               addMe.add(ad);
            }
         }

         oldSet.removeAll(newFullSet);

         for(ActiveDescriptor gone : oldSet) {
            HandleAndService has = (HandleAndService)this.currentImmediateServices.get(gone);
            ServiceHandle<?> handle = has.getHandle();
            if (handle != null) {
               handle.destroy();
            } else {
               this.destroyOne(gone, errorHandlers);
            }
         }
      }

      for(ActiveDescriptor ad : addMe) {
         try {
            this.locator.getServiceHandle(ad).getService();
         } catch (Throwable var15) {
            Throwable th = var15;

            for(ImmediateErrorHandler ieh : errorHandlers) {
               try {
                  ieh.postConstructFailed(ad, th);
               } catch (Throwable var12) {
               }
            }
         }
      }

   }
}
