package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@DoNotMock("Use TestingExecutors.sameThreadScheduledExecutor, or wrap a real Executor from java.util.concurrent.Executors with MoreExecutors.listeningDecorator")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface ListeningExecutorService extends ExecutorService {
   ListenableFuture submit(Callable task);

   ListenableFuture submit(Runnable task);

   ListenableFuture submit(Runnable task, @ParametricNullness Object result);

   List invokeAll(Collection tasks) throws InterruptedException;

   List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException;

   @J2ktIncompatible
   default List invokeAll(Collection tasks, Duration timeout) throws InterruptedException {
      return this.invokeAll(tasks, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   @J2ktIncompatible
   default Object invokeAny(Collection tasks, Duration timeout) throws InterruptedException, ExecutionException, TimeoutException {
      return this.invokeAny(tasks, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   @J2ktIncompatible
   default boolean awaitTermination(Duration timeout) throws InterruptedException {
      return this.awaitTermination(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }
}
