package scala.concurrent.impl;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import scala.Function1;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContext$;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.ExecutionContextExecutorService;
import scala.math.package$;
import scala.runtime.RichDouble$;

public final class ExecutionContextImpl$ {
   public static final ExecutionContextImpl$ MODULE$ = new ExecutionContextImpl$();

   public ExecutionContextExecutorService createDefaultExecutorService(final Function1 reporter) {
      package$ var10000 = package$.MODULE$;
      var10000 = package$.MODULE$;
      int desiredParallelism = Math.min(Math.max(getInt$1("scala.concurrent.context.minThreads", "1"), getInt$1("scala.concurrent.context.numThreads", "x1")), getInt$1("scala.concurrent.context.maxThreads", "x1"));
      ExecutionContextImpl.DefaultThreadFactory threadFactory = new ExecutionContextImpl.DefaultThreadFactory(true, getInt$1("scala.concurrent.context.maxExtraThreads", "256"), "scala-execution-context-global", (thread, cause) -> reporter.apply(cause));
      return new ExecutionContextExecutorService(desiredParallelism, threadFactory) {
         /** @deprecated */
         public ExecutionContext prepare() {
            return ExecutionContext.prepare$(this);
         }

         public final void reportFailure(final Throwable cause) {
            Thread.UncaughtExceptionHandler var2 = this.getUncaughtExceptionHandler();
            if (var2 != null) {
               var2.uncaughtException(Thread.currentThread(), cause);
            }
         }
      };
   }

   public ExecutionContextExecutor fromExecutor(final Executor e, final Function1 reporter) {
      if (e == null) {
         package$ var10000 = package$.MODULE$;
         var10000 = package$.MODULE$;
         int createDefaultExecutorService_desiredParallelism = Math.min(Math.max(getInt$1("scala.concurrent.context.minThreads", "1"), getInt$1("scala.concurrent.context.numThreads", "x1")), getInt$1("scala.concurrent.context.maxThreads", "x1"));
         ExecutionContextImpl.DefaultThreadFactory createDefaultExecutorService_threadFactory = new ExecutionContextImpl.DefaultThreadFactory(true, getInt$1("scala.concurrent.context.maxExtraThreads", "256"), "scala-execution-context-global", (thread, cause) -> reporter.apply(cause));
         return new ExecutionContextExecutorService(createDefaultExecutorService_desiredParallelism, createDefaultExecutorService_threadFactory) {
            /** @deprecated */
            public ExecutionContext prepare() {
               return ExecutionContext.prepare$(this);
            }

            public final void reportFailure(final Throwable cause) {
               Thread.UncaughtExceptionHandler var2 = this.getUncaughtExceptionHandler();
               if (var2 != null) {
                  var2.uncaughtException(Thread.currentThread(), cause);
               }
            }
         };
      } else {
         return new ExecutionContextImpl(e, reporter);
      }
   }

   public Function1 fromExecutor$default$2() {
      return ExecutionContext$.MODULE$.defaultReporter();
   }

   public ExecutionContextExecutorService fromExecutorService(final ExecutorService es, final Function1 reporter) {
      if (es == null) {
         package$ var10000 = package$.MODULE$;
         var10000 = package$.MODULE$;
         int createDefaultExecutorService_desiredParallelism = Math.min(Math.max(getInt$1("scala.concurrent.context.minThreads", "1"), getInt$1("scala.concurrent.context.numThreads", "x1")), getInt$1("scala.concurrent.context.maxThreads", "x1"));
         ExecutionContextImpl.DefaultThreadFactory createDefaultExecutorService_threadFactory = new ExecutionContextImpl.DefaultThreadFactory(true, getInt$1("scala.concurrent.context.maxExtraThreads", "256"), "scala-execution-context-global", (thread, cause) -> reporter.apply(cause));
         return new ExecutionContextExecutorService(createDefaultExecutorService_desiredParallelism, createDefaultExecutorService_threadFactory) {
            /** @deprecated */
            public ExecutionContext prepare() {
               return ExecutionContext.prepare$(this);
            }

            public final void reportFailure(final Throwable cause) {
               Thread.UncaughtExceptionHandler var2 = this.getUncaughtExceptionHandler();
               if (var2 != null) {
                  var2.uncaughtException(Thread.currentThread(), cause);
               }
            }
         };
      } else {
         return new ExecutionContextExecutorService(es, reporter) {
            private final ExecutorService asExecutorService() {
               return (ExecutorService)this.executor();
            }

            public final void shutdown() {
               ((ExecutorService)this.executor()).shutdown();
            }

            public final List shutdownNow() {
               return ((ExecutorService)this.executor()).shutdownNow();
            }

            public final boolean isShutdown() {
               return ((ExecutorService)this.executor()).isShutdown();
            }

            public final boolean isTerminated() {
               return ((ExecutorService)this.executor()).isTerminated();
            }

            public final boolean awaitTermination(final long l, final TimeUnit timeUnit) {
               return ((ExecutorService)this.executor()).awaitTermination(l, timeUnit);
            }

            public final Future submit(final Callable callable) {
               return ((ExecutorService)this.executor()).submit(callable);
            }

            public final Future submit(final Runnable runnable, final Object t) {
               return ((ExecutorService)this.executor()).submit(runnable, t);
            }

            public final Future submit(final Runnable runnable) {
               return ((ExecutorService)this.executor()).submit(runnable);
            }

            public final List invokeAll(final Collection callables) {
               return ((ExecutorService)this.executor()).invokeAll(callables);
            }

            public final List invokeAll(final Collection callables, final long l, final TimeUnit timeUnit) {
               return ((ExecutorService)this.executor()).invokeAll(callables, l, timeUnit);
            }

            public final Object invokeAny(final Collection callables) {
               return ((ExecutorService)this.executor()).invokeAny(callables);
            }

            public final Object invokeAny(final Collection callables, final long l, final TimeUnit timeUnit) {
               return ((ExecutorService)this.executor()).invokeAny(callables, l, timeUnit);
            }
         };
      }
   }

   public Function1 fromExecutorService$default$2() {
      return ExecutionContext$.MODULE$.defaultReporter();
   }

   private static final int getInt$1(final String name, final String default) {
      String var10000;
      try {
         var10000 = System.getProperty(name, default);
      } catch (SecurityException var5) {
         var10000 = default;
      }

      String var2 = var10000;
      switch (var2 == null ? 0 : var2.hashCode()) {
         default:
            if (var2.charAt(0) == 'x') {
               RichDouble$ var6 = RichDouble$.MODULE$;
               double var3 = (double)Runtime.getRuntime().availableProcessors() * Double.parseDouble(var2.substring(1));
               package$ var7 = package$.MODULE$;
               return (int)Math.ceil(var3);
            } else {
               return Integer.parseInt(var2);
            }
      }
   }

   private ExecutionContextImpl$() {
   }
}
