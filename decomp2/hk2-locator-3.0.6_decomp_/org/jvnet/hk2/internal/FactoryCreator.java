package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InstanceLifecycleEventType;
import org.glassfish.hk2.api.InstantiationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.general.ThreadSpecificObject;
import org.glassfish.hk2.utilities.reflection.Pretty;

public class FactoryCreator implements Creator {
   private static final Object MAP_VALUE = new Object();
   private final ConcurrentHashMap cycleFinder = new ConcurrentHashMap();
   private final ServiceLocator locator;
   private final ActiveDescriptor factoryDescriptor;
   private final InstantiationServiceImpl instantiationService;

   FactoryCreator(ServiceLocator locator, ActiveDescriptor factoryDescriptor) {
      this.locator = locator;
      this.factoryDescriptor = factoryDescriptor;
      if (!factoryDescriptor.isReified()) {
         factoryDescriptor = locator.reifyDescriptor(factoryDescriptor);
      }

      InstantiationServiceImpl found = null;

      for(Injectee factoryInjectee : factoryDescriptor.getInjectees()) {
         if (InstantiationService.class.equals(factoryInjectee.getRequiredType())) {
            found = (InstantiationServiceImpl)locator.getService(InstantiationServiceImpl.class, new Annotation[0]);
            break;
         }
      }

      this.instantiationService = found;
   }

   public List getInjectees() {
      return Collections.emptyList();
   }

   private ServiceHandle getFactoryHandle() {
      try {
         return this.locator.getServiceHandle(this.factoryDescriptor);
      } catch (Throwable th) {
         throw new MultiException(th);
      }
   }

   public Object create(ServiceHandle root, SystemDescriptor eventThrower) throws MultiException {
      ServiceHandle<Factory<T>> handle = this.getFactoryHandle();
      eventThrower.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.PRE_PRODUCTION, (Object)null, eventThrower));
      ThreadSpecificObject<ActiveDescriptor<?>> tso = new ThreadSpecificObject(handle.getActiveDescriptor());
      if (this.cycleFinder.containsKey(tso)) {
         HashSet<String> impls = new HashSet();

         for(ThreadSpecificObject candidate : this.cycleFinder.keySet()) {
            if (candidate.getThreadIdentifier() == tso.getThreadIdentifier()) {
               impls.add(((ActiveDescriptor)candidate.getIncomingObject()).getImplementation());
            }
         }

         throw new AssertionError("A cycle was detected involving these Factory implementations: " + Pretty.collection(impls));
      } else {
         this.cycleFinder.put(tso, MAP_VALUE);

         Factory<T> retValFactory;
         try {
            retValFactory = (Factory)handle.getService();
         } finally {
            this.cycleFinder.remove(tso);
         }

         if (this.instantiationService != null) {
            Injectee parentInjectee = null;
            if (root != null && root instanceof ServiceHandleImpl) {
               parentInjectee = ((ServiceHandleImpl)root).getOriginalRequest();
            }

            this.instantiationService.pushInjecteeParent(parentInjectee);
         }

         T retVal;
         try {
            retVal = (T)retValFactory.provide();
         } finally {
            if (this.instantiationService != null) {
               this.instantiationService.popInjecteeParent();
            }

         }

         eventThrower.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.POST_PRODUCTION, retVal, eventThrower));
         return retVal;
      }
   }

   public void dispose(Object instance) {
      try {
         ServiceHandle<Factory<T>> handle = this.getFactoryHandle();
         Factory<T> factory = (Factory)handle.getService();
         factory.dispose(instance);
      } catch (Throwable th) {
         if (th instanceof MultiException) {
            throw (MultiException)th;
         } else {
            throw new MultiException(th);
         }
      }
   }

   public String toString() {
      ServiceLocator var10000 = this.locator;
      return "FactoryCreator(" + var10000 + "," + this.factoryDescriptor + "," + System.identityHashCode(this) + ")";
   }
}
