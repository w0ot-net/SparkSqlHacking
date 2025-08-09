package scala.concurrent;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import scala.Function1;
import scala.concurrent.impl.ExecutionContextImpl$;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

public final class ExecutionContext$ {
   public static final ExecutionContext$ MODULE$ = new ExecutionContext$();
   private static ExecutionContextExecutor global;
   private static ExecutionContextExecutor opportunistic;
   private static final Function1 defaultReporter = (x$1) -> {
      $anonfun$defaultReporter$1(x$1);
      return BoxedUnit.UNIT;
   };
   private static volatile byte bitmap$0;

   private ExecutionContextExecutor global$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            ExecutionContextImpl$ var10002 = ExecutionContextImpl$.MODULE$;
            global = ExecutionContextImpl$.MODULE$.fromExecutor((Executor)null, MODULE$.defaultReporter());
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return global;
   }

   public final ExecutionContextExecutor global() {
      return (byte)(bitmap$0 & 1) == 0 ? this.global$lzycompute() : global;
   }

   private ExecutionContextExecutor opportunistic$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            opportunistic = new ExecutionContextExecutor() {
               private ThreadLocal scala$concurrent$BatchingExecutor$$_tasksLocal;

               public final void submitAsyncBatched(final Runnable runnable) {
                  BatchingExecutor.submitAsyncBatched$(this, runnable);
               }

               public final void submitSyncBatched(final Runnable runnable) {
                  BatchingExecutor.submitSyncBatched$(this, runnable);
               }

               /** @deprecated */
               public ExecutionContext prepare() {
                  return ExecutionContext.prepare$(this);
               }

               public final ThreadLocal scala$concurrent$BatchingExecutor$$_tasksLocal() {
                  return this.scala$concurrent$BatchingExecutor$$_tasksLocal;
               }

               public final void scala$concurrent$BatchingExecutor$_setter_$scala$concurrent$BatchingExecutor$$_tasksLocal_$eq(final ThreadLocal x$1) {
                  this.scala$concurrent$BatchingExecutor$$_tasksLocal = x$1;
               }

               public final void submitForExecution(final Runnable runnable) {
                  ExecutionContext$.MODULE$.global().execute(runnable);
               }

               public final void execute(final Runnable runnable) {
                  if ((!(runnable instanceof scala.concurrent.impl.Promise.Transformation) || ((scala.concurrent.impl.Promise.Transformation)runnable).benefitsFromBatching()) && runnable instanceof Batchable) {
                     Object submitAsyncBatched_b = this.scala$concurrent$BatchingExecutor$$_tasksLocal().get();
                     if (submitAsyncBatched_b instanceof BatchingExecutor.AsyncBatch) {
                        ((BatchingExecutor.AsyncBatch)submitAsyncBatched_b).push(runnable);
                     } else {
                        this.submitForExecution(new BatchingExecutor.AsyncBatch(runnable));
                     }
                  } else {
                     ExecutionContext$.MODULE$.global().execute(runnable);
                  }
               }

               public final void reportFailure(final Throwable t) {
                  ExecutionContext$.MODULE$.global().reportFailure(t);
               }

               public {
                  BatchingExecutor.$init$(this);
                  Statics.releaseFence();
               }
            };
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return opportunistic;
   }

   public ExecutionContextExecutor opportunistic() {
      return (byte)(bitmap$0 & 2) == 0 ? this.opportunistic$lzycompute() : opportunistic;
   }

   public ExecutionContextExecutorService fromExecutorService(final ExecutorService e, final Function1 reporter) {
      return ExecutionContextImpl$.MODULE$.fromExecutorService(e, reporter);
   }

   public ExecutionContextExecutorService fromExecutorService(final ExecutorService e) {
      Function1 fromExecutorService_reporter = this.defaultReporter();
      return ExecutionContextImpl$.MODULE$.fromExecutorService(e, fromExecutorService_reporter);
   }

   public ExecutionContextExecutor fromExecutor(final Executor e, final Function1 reporter) {
      return ExecutionContextImpl$.MODULE$.fromExecutor(e, reporter);
   }

   public ExecutionContextExecutor fromExecutor(final Executor e) {
      Function1 fromExecutor_reporter = this.defaultReporter();
      return ExecutionContextImpl$.MODULE$.fromExecutor(e, fromExecutor_reporter);
   }

   public final Function1 defaultReporter() {
      return defaultReporter;
   }

   // $FF: synthetic method
   public static final void $anonfun$defaultReporter$1(final Throwable x$1) {
      x$1.printStackTrace();
   }

   private ExecutionContext$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
