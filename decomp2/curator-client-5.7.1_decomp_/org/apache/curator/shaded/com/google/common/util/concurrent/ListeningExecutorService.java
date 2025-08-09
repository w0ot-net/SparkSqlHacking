package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use TestingExecutors.sameThreadScheduledExecutor, or wrap a real Executor from java.util.concurrent.Executors with MoreExecutors.listeningDecorator")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface ListeningExecutorService extends ExecutorService {
   ListenableFuture submit(Callable task);

   ListenableFuture submit(Runnable task);

   ListenableFuture submit(Runnable task, @ParametricNullness Object result);

   List invokeAll(Collection tasks) throws InterruptedException;

   List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException;
}
