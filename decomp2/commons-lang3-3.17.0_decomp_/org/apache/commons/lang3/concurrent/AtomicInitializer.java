package org.apache.commons.lang3.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

public class AtomicInitializer extends AbstractConcurrentInitializer {
   private static final Object NO_INIT = new Object();
   private final AtomicReference reference;

   public static Builder builder() {
      return new Builder();
   }

   public AtomicInitializer() {
      this.reference = new AtomicReference(this.getNoInit());
   }

   private AtomicInitializer(FailableSupplier initializer, FailableConsumer closer) {
      super(initializer, closer);
      this.reference = new AtomicReference(this.getNoInit());
   }

   public Object get() throws ConcurrentException {
      T result = (T)this.reference.get();
      if (result == this.getNoInit()) {
         result = (T)this.initialize();
         if (!this.reference.compareAndSet(this.getNoInit(), result)) {
            result = (T)this.reference.get();
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
      public AtomicInitializer get() {
         return new AtomicInitializer(this.getInitializer(), this.getCloser());
      }
   }
}
