package org.apache.commons.lang3.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

public class AtomicSafeInitializer extends AbstractConcurrentInitializer {
   private static final Object NO_INIT = new Object();
   private final AtomicReference factory;
   private final AtomicReference reference;

   public static Builder builder() {
      return new Builder();
   }

   public AtomicSafeInitializer() {
      this.factory = new AtomicReference();
      this.reference = new AtomicReference(this.getNoInit());
   }

   private AtomicSafeInitializer(FailableSupplier initializer, FailableConsumer closer) {
      super(initializer, closer);
      this.factory = new AtomicReference();
      this.reference = new AtomicReference(this.getNoInit());
   }

   public final Object get() throws ConcurrentException {
      T result;
      while((result = (T)this.reference.get()) == this.getNoInit()) {
         if (this.factory.compareAndSet((Object)null, this)) {
            this.reference.set(this.initialize());
         }
      }

      return result;
   }

   private Object getNoInit() {
      return NO_INIT;
   }

   protected ConcurrentException getTypedException(Exception e) {
      return new ConcurrentException(e);
   }

   public boolean isInitialized() {
      return this.reference.get() != NO_INIT;
   }

   public static class Builder extends AbstractConcurrentInitializer.AbstractBuilder {
      public AtomicSafeInitializer get() {
         return new AtomicSafeInitializer(this.getInitializer(), this.getCloser());
      }
   }
}
