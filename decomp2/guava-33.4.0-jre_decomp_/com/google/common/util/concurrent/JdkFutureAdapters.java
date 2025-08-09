package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class JdkFutureAdapters {
   public static ListenableFuture listenInPoolThread(Future future) {
      return (ListenableFuture)(future instanceof ListenableFuture ? (ListenableFuture)future : new ListenableFutureAdapter(future));
   }

   public static ListenableFuture listenInPoolThread(Future future, Executor executor) {
      Preconditions.checkNotNull(executor);
      return (ListenableFuture)(future instanceof ListenableFuture ? (ListenableFuture)future : new ListenableFutureAdapter(future, executor));
   }

   private JdkFutureAdapters() {
   }

   private static class ListenableFutureAdapter extends ForwardingFuture implements ListenableFuture {
      private static final ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("ListenableFutureAdapter-thread-%d").build();
      private static final Executor defaultAdapterExecutor;
      private final Executor adapterExecutor;
      private final ExecutionList executionList;
      private final AtomicBoolean hasListeners;
      private final Future delegate;

      ListenableFutureAdapter(Future delegate) {
         this(delegate, defaultAdapterExecutor);
      }

      ListenableFutureAdapter(Future delegate, Executor adapterExecutor) {
         this.executionList = new ExecutionList();
         this.hasListeners = new AtomicBoolean(false);
         this.delegate = (Future)Preconditions.checkNotNull(delegate);
         this.adapterExecutor = (Executor)Preconditions.checkNotNull(adapterExecutor);
      }

      protected Future delegate() {
         return this.delegate;
      }

      public void addListener(Runnable listener, Executor exec) {
         this.executionList.add(listener, exec);
         if (this.hasListeners.compareAndSet(false, true)) {
            if (this.delegate.isDone()) {
               this.executionList.execute();
               return;
            }

            this.adapterExecutor.execute(() -> {
               try {
                  Uninterruptibles.getUninterruptibly(this.delegate);
               } catch (Throwable var2) {
               }

               this.executionList.execute();
            });
         }

      }

      static {
         defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactory);
      }
   }
}
