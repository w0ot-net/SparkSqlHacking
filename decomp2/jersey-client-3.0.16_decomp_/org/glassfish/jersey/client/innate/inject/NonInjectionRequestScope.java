package org.glassfish.jersey.client.innate.inject;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.LazyUid;
import org.glassfish.jersey.process.internal.RequestContext;
import org.glassfish.jersey.process.internal.RequestScope;

public class NonInjectionRequestScope extends RequestScope {
   public RequestContext createContext() {
      return new Instance();
   }

   public static final class Instance implements RequestContext {
      private static final ExtendedLogger logger;
      private final LazyUid id;
      private final AtomicInteger referenceCounter;

      private Instance() {
         this.id = new LazyUid();
         this.referenceCounter = new AtomicInteger(1);
      }

      public Instance getReference() {
         this.referenceCounter.incrementAndGet();
         return this;
      }

      public void release() {
         this.referenceCounter.decrementAndGet();
      }

      public String toString() {
         return "Instance{id=" + this.id + ", referenceCounter=" + this.referenceCounter + '}';
      }

      static {
         logger = new ExtendedLogger(Logger.getLogger(Instance.class.getName()), Level.FINEST);
      }
   }
}
