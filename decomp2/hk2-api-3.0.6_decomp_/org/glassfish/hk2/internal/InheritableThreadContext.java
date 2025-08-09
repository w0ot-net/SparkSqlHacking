package org.glassfish.hk2.internal;

import jakarta.inject.Singleton;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.InheritableThread;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.Visibility;
import org.glassfish.hk2.utilities.CleanerFactory;
import org.glassfish.hk2.utilities.reflection.Logger;

@Singleton
@Visibility(DescriptorVisibility.LOCAL)
public class InheritableThreadContext implements Context {
   private static final boolean LOG_THREAD_DESTRUCTION = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.parseBoolean(System.getProperty("org.hk2.debug.inheritablethreadcontext.log", "false"));
      }
   });
   private InheritableThreadLocal threadMap = new InheritableThreadLocal() {
      public InheritableContextThreadWrapper initialValue() {
         return new InheritableContextThreadWrapper();
      }
   };

   public Class getScope() {
      return InheritableThread.class;
   }

   public Object findOrCreate(ActiveDescriptor activeDescriptor, ServiceHandle root) {
      U retVal = (U)((InheritableContextThreadWrapper)this.threadMap.get()).get(activeDescriptor);
      if (retVal == null) {
         retVal = (U)activeDescriptor.create(root);
         ((InheritableContextThreadWrapper)this.threadMap.get()).put(activeDescriptor, retVal);
      }

      return retVal;
   }

   public boolean containsKey(ActiveDescriptor descriptor) {
      return ((InheritableContextThreadWrapper)this.threadMap.get()).has(descriptor);
   }

   public boolean isActive() {
      return true;
   }

   public boolean supportsNullCreation() {
      return false;
   }

   public void shutdown() {
      this.threadMap = null;
   }

   public void destroyOne(ActiveDescriptor descriptor) {
   }

   private static class InheritableContextThreadWrapper {
      private final HashMap instances = new HashMap();
      private final long id = Thread.currentThread().getId();

      public InheritableContextThreadWrapper() {
         this.registerStopEvent();
      }

      public boolean has(ActiveDescriptor d) {
         return this.instances.containsKey(d);
      }

      public Object get(ActiveDescriptor d) {
         return this.instances.get(d);
      }

      public void put(ActiveDescriptor d, Object v) {
         this.instances.put(d, v);
      }

      public final void registerStopEvent() {
         CleanerFactory.create().register(this, () -> {
            this.instances.clear();
            if (InheritableThreadContext.LOG_THREAD_DESTRUCTION) {
               Logger.getLogger().debug("Removing PerThreadContext data for thread " + this.id);
            }

         });
      }
   }
}
