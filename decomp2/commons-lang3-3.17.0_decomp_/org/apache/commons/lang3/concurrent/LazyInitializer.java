package org.apache.commons.lang3.concurrent;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

public class LazyInitializer extends AbstractConcurrentInitializer {
   private static final Object NO_INIT = new Object();
   private volatile Object object;

   public static Builder builder() {
      return new Builder();
   }

   public LazyInitializer() {
      this.object = NO_INIT;
   }

   private LazyInitializer(FailableSupplier initializer, FailableConsumer closer) {
      super(initializer, closer);
      this.object = NO_INIT;
   }

   public Object get() throws ConcurrentException {
      T result = (T)this.object;
      if (result == NO_INIT) {
         synchronized(this) {
            result = (T)this.object;
            if (result == NO_INIT) {
               this.object = result = (T)this.initialize();
            }
         }
      }

      return result;
   }

   protected ConcurrentException getTypedException(Exception e) {
      return new ConcurrentException(e);
   }

   public boolean isInitialized() {
      return this.object != NO_INIT;
   }

   public static class Builder extends AbstractConcurrentInitializer.AbstractBuilder {
      public LazyInitializer get() {
         return new LazyInitializer(this.getInitializer(), this.getCloser());
      }
   }
}
