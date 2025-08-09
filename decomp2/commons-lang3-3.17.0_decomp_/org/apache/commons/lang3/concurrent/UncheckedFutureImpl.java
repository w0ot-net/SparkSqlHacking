package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.exception.UncheckedInterruptedException;

final class UncheckedFutureImpl extends AbstractFutureProxy implements UncheckedFuture {
   UncheckedFutureImpl(Future future) {
      super(future);
   }

   public Object get() {
      try {
         return super.get();
      } catch (InterruptedException e) {
         throw new UncheckedInterruptedException(e);
      } catch (ExecutionException e) {
         throw new UncheckedExecutionException(e);
      }
   }

   public Object get(long timeout, TimeUnit unit) {
      try {
         return super.get(timeout, unit);
      } catch (InterruptedException e) {
         throw new UncheckedInterruptedException(e);
      } catch (ExecutionException e) {
         throw new UncheckedExecutionException(e);
      } catch (TimeoutException e) {
         throw new UncheckedTimeoutException(e);
      }
   }
}
