package org.apache.commons.lang3.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class Memoizer implements Computable {
   private final ConcurrentMap cache;
   private final Function mappingFunction;
   private final boolean recalculate;

   public Memoizer(Computable computable) {
      this(computable, false);
   }

   public Memoizer(Computable computable, boolean recalculate) {
      this.cache = new ConcurrentHashMap();
      this.recalculate = recalculate;
      this.mappingFunction = (k) -> FutureTasks.run(() -> computable.compute(k));
   }

   public Memoizer(Function function) {
      this(function, false);
   }

   public Memoizer(Function function, boolean recalculate) {
      this.cache = new ConcurrentHashMap();
      this.recalculate = recalculate;
      this.mappingFunction = (k) -> FutureTasks.run(() -> function.apply(k));
   }

   public Object compute(Object arg) throws InterruptedException {
      while(true) {
         Future<O> future = (Future)this.cache.computeIfAbsent(arg, this.mappingFunction);

         try {
            return future.get();
         } catch (CancellationException var4) {
            this.cache.remove(arg, future);
         } catch (ExecutionException e) {
            if (this.recalculate) {
               this.cache.remove(arg, future);
            }

            throw this.launderException(e.getCause());
         }
      }
   }

   private RuntimeException launderException(Throwable throwable) {
      throw new IllegalStateException("Unchecked exception", ExceptionUtils.throwUnchecked(throwable));
   }
}
