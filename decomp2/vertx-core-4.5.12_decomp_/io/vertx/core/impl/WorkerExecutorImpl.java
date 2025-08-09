package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.concurrent.Callable;

class WorkerExecutorImpl implements MetricsProvider, WorkerExecutorInternal {
   private final VertxInternal vertx;
   private final CloseFuture closeFuture;
   private final VertxImpl.SharedWorkerPool pool;
   private boolean closed;

   public WorkerExecutorImpl(VertxInternal vertx, CloseFuture closeFuture, VertxImpl.SharedWorkerPool pool) {
      this.vertx = vertx;
      this.closeFuture = closeFuture;
      this.pool = pool;
   }

   public Metrics getMetrics() {
      return this.pool.metrics();
   }

   public boolean isMetricsEnabled() {
      PoolMetrics metrics = this.pool.metrics();
      return metrics != null;
   }

   public Vertx vertx() {
      return this.vertx;
   }

   public WorkerPool getPool() {
      return this.pool;
   }

   public Future executeBlocking(Handler blockingCodeHandler, boolean ordered) {
      synchronized(this) {
         if (this.closed) {
            throw new IllegalStateException("Worker executor closed");
         }
      }

      ContextInternal context = this.vertx.getOrCreateContext();
      ContextImpl impl = context instanceof DuplicatedContext ? ((DuplicatedContext)context).delegate : (ContextImpl)context;
      return ContextImpl.executeBlocking(context, (Handler)blockingCodeHandler, this.pool, ordered ? impl.executeBlockingTasks : null);
   }

   public Future executeBlocking(Callable blockingCodeHandler, boolean ordered) {
      ContextInternal context = this.vertx.getOrCreateContext();
      ContextImpl impl = context instanceof DuplicatedContext ? ((DuplicatedContext)context).delegate : (ContextImpl)context;
      return ContextImpl.executeBlocking(context, (Callable)blockingCodeHandler, this.pool, ordered ? impl.executeBlockingTasks : null);
   }

   public void executeBlocking(Handler blockingCodeHandler, boolean ordered, Handler asyncResultHandler) {
      Future<T> fut = this.executeBlocking(blockingCodeHandler, ordered);
      if (asyncResultHandler != null) {
         fut.onComplete(asyncResultHandler);
      }

   }

   public Future close() {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      PromiseInternal<Void> promise = closingCtx.promise();
      this.closeFuture.close(promise);
      return promise.future();
   }

   public void close(Handler handler) {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      this.closeFuture.close(handler != null ? closingCtx.promise(handler) : null);
   }

   public void close(Promise completion) {
      synchronized(this) {
         this.closed = true;
      }

      this.pool.close();
      completion.complete();
   }
}
