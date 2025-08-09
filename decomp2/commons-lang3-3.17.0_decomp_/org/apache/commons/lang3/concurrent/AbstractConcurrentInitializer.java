package org.apache.commons.lang3.concurrent;

import java.util.Objects;
import org.apache.commons.lang3.builder.AbstractSupplier;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

public abstract class AbstractConcurrentInitializer implements ConcurrentInitializer {
   private final FailableConsumer closer;
   private final FailableSupplier initializer;

   public AbstractConcurrentInitializer() {
      this(FailableSupplier.nul(), FailableConsumer.nop());
   }

   AbstractConcurrentInitializer(FailableSupplier initializer, FailableConsumer closer) {
      this.closer = (FailableConsumer)Objects.requireNonNull(closer, "closer");
      this.initializer = (FailableSupplier)Objects.requireNonNull(initializer, "initializer");
   }

   public void close() throws ConcurrentException {
      if (this.isInitialized()) {
         try {
            this.closer.accept(this.get());
         } catch (Exception e) {
            throw new ConcurrentException(ExceptionUtils.throwUnchecked((Throwable)e));
         }
      }

   }

   protected abstract Exception getTypedException(Exception var1);

   protected Object initialize() throws Exception {
      try {
         return this.initializer.get();
      } catch (Exception e) {
         ExceptionUtils.throwUnchecked((Throwable)e);
         E typedException = (E)this.getTypedException(e);
         if (typedException.getClass().isAssignableFrom(e.getClass())) {
            throw e;
         } else {
            throw typedException;
         }
      }
   }

   protected abstract boolean isInitialized();

   public abstract static class AbstractBuilder extends AbstractSupplier {
      private FailableConsumer closer = FailableConsumer.nop();
      private FailableSupplier initializer = FailableSupplier.nul();

      public FailableConsumer getCloser() {
         return this.closer;
      }

      public FailableSupplier getInitializer() {
         return this.initializer;
      }

      public AbstractBuilder setCloser(FailableConsumer closer) {
         this.closer = closer != null ? closer : FailableConsumer.nop();
         return (AbstractBuilder)this.asThis();
      }

      public AbstractBuilder setInitializer(FailableSupplier initializer) {
         this.initializer = initializer != null ? initializer : FailableSupplier.nul();
         return (AbstractBuilder)this.asThis();
      }
   }
}
