package org.glassfish.jersey.inject.hk2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.inject.ForeignDescriptor;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.LazyUid;
import org.glassfish.jersey.process.internal.RequestScope;

public class Hk2RequestScope extends RequestScope {
   public org.glassfish.jersey.process.internal.RequestContext createContext() {
      return new Instance();
   }

   public static final class Instance implements org.glassfish.jersey.process.internal.RequestContext {
      private static final ExtendedLogger logger;
      private final LazyUid id;
      private final Map store;
      private final AtomicInteger referenceCounter;

      private Instance() {
         this.id = new LazyUid();
         this.store = new HashMap();
         this.referenceCounter = new AtomicInteger(1);
      }

      public Instance getReference() {
         this.referenceCounter.incrementAndGet();
         return this;
      }

      public Object get(ForeignDescriptor descriptor) {
         return this.store.get(descriptor);
      }

      public Object put(ForeignDescriptor descriptor, Object value) {
         Preconditions.checkState(!this.store.containsKey(descriptor), "An instance for the descriptor %s was already seeded in this scope. Old instance: %s New instance: %s", new Object[]{descriptor, this.store.get(descriptor), value});
         return this.store.put(descriptor, value);
      }

      public void remove(ForeignDescriptor descriptor) {
         T removed = (T)this.store.remove(descriptor);
         if (removed != null) {
            descriptor.dispose(removed);
         }

      }

      public boolean contains(ForeignDescriptor provider) {
         return this.store.containsKey(provider);
      }

      public void release() {
         if (this.referenceCounter.decrementAndGet() < 1) {
            try {
               (new HashSet(this.store.keySet())).forEach(this::remove);
            } finally {
               logger.debugLog("Released scope instance {0}", new Object[]{this});
            }
         }

      }

      public String toString() {
         return "Instance{id=" + this.id + ", referenceCounter=" + this.referenceCounter + ", store size=" + this.store.size() + '}';
      }

      static {
         logger = new ExtendedLogger(Logger.getLogger(Instance.class.getName()), Level.FINEST);
      }
   }
}
