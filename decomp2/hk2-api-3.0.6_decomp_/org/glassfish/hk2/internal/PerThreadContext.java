package org.glassfish.hk2.internal;

import jakarta.inject.Singleton;
import java.security.AccessController;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.PerThread;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.Visibility;
import org.glassfish.hk2.utilities.CleanerFactory;
import org.glassfish.hk2.utilities.general.Hk2ThreadLocal;
import org.glassfish.hk2.utilities.reflection.Logger;

@Singleton
@Visibility(DescriptorVisibility.LOCAL)
public class PerThreadContext implements Context {
   private static final boolean LOG_THREAD_DESTRUCTION = (Boolean)AccessController.doPrivileged(() -> Boolean.getBoolean("org.hk2.debug.perthreadcontext.log"));
   private final Hk2ThreadLocal threadMap = new Hk2ThreadLocal() {
      public PerThreadContextWrapper initialValue() {
         return new PerThreadContextWrapper();
      }
   };

   public Class getScope() {
      return PerThread.class;
   }

   public Object findOrCreate(ActiveDescriptor activeDescriptor, ServiceHandle root) {
      U retVal = (U)((PerThreadContextWrapper)this.threadMap.get()).get(activeDescriptor);
      if (retVal == null) {
         retVal = (U)activeDescriptor.create(root);
         ((PerThreadContextWrapper)this.threadMap.get()).put(activeDescriptor, retVal);
      }

      return retVal;
   }

   public boolean containsKey(ActiveDescriptor descriptor) {
      return ((PerThreadContextWrapper)this.threadMap.get()).has(descriptor);
   }

   public boolean isActive() {
      return true;
   }

   public boolean supportsNullCreation() {
      return false;
   }

   public void shutdown() {
      this.threadMap.removeAll();
   }

   public void destroyOne(ActiveDescriptor descriptor) {
   }

   private static class PerThreadContextWrapper {
      private final CleanableContext context = new CleanableContext();

      public PerThreadContextWrapper() {
         this.registerStopEvent();
      }

      public boolean has(ActiveDescriptor descriptor) {
         return this.context.has(descriptor);
      }

      public Object get(ActiveDescriptor descriptor) {
         return this.context.get(descriptor);
      }

      public void put(ActiveDescriptor descriptor, Object value) {
         this.context.put(descriptor, value);
      }

      public final void registerStopEvent() {
         CleanerFactory.create().register(this, this.context);
      }
   }

   private static final class CleanableContext implements Runnable {
      private final Map instances = new HashMap();
      private final long id = Thread.currentThread().getId();

      public boolean has(ActiveDescriptor descriptor) {
         return this.instances.containsKey(descriptor);
      }

      public Object get(ActiveDescriptor descriptor) {
         return this.instances.get(descriptor);
      }

      public void put(ActiveDescriptor descriptor, Object value) {
         this.instances.put(descriptor, value);
      }

      public void run() {
         this.instances.clear();
         if (PerThreadContext.LOG_THREAD_DESTRUCTION) {
            Logger.getLogger().debug("Removing PerThreadContext data for thread " + this.id);
         }

      }
   }
}
