package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ThreadingModel;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.metrics.PoolMetrics;
import io.vertx.core.spi.tracing.VertxTracer;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Supplier;

public final class ContextImpl extends ContextBase implements ContextInternal {
   private static final Logger log = LoggerFactory.getLogger(ContextImpl.class);
   private static final String DISABLE_TIMINGS_PROP_NAME = "vertx.disableContextTimings";
   static final boolean DISABLE_TIMINGS = Boolean.getBoolean("vertx.disableContextTimings");
   private final ThreadingModel threadingModel;
   private final VertxImpl owner;
   private final JsonObject config;
   private final Deployment deployment;
   private final CloseFuture closeFuture;
   private final ClassLoader tccl;
   private final EventLoop eventLoop;
   private final EventExecutor executor;
   private ConcurrentMap data;
   private volatile Handler exceptionHandler;
   final TaskQueue internalOrderedTasks;
   final WorkerPool internalWorkerPool;
   final WorkerPool workerPool;
   final WorkerTaskQueue executeBlockingTasks;

   static void setResultHandler(ContextInternal ctx, Future fut, Handler resultHandler) {
      if (resultHandler != null) {
         fut.onComplete(resultHandler);
      } else {
         fut.onFailure(ctx::reportException);
      }

   }

   public ContextImpl(VertxImpl vertx, int localsLength, ThreadingModel threadingModel, EventLoop eventLoop, EventExecutor executor, WorkerPool internalWorkerPool, WorkerPool workerPool, Deployment deployment, CloseFuture closeFuture, ClassLoader tccl) {
      super(localsLength);
      this.threadingModel = threadingModel;
      this.deployment = deployment;
      this.config = deployment != null ? deployment.config() : new JsonObject();
      this.eventLoop = eventLoop;
      this.executor = executor;
      this.tccl = tccl;
      this.owner = vertx;
      this.workerPool = workerPool;
      this.closeFuture = closeFuture;
      this.internalWorkerPool = internalWorkerPool;
      this.executeBlockingTasks = new WorkerTaskQueue();
      this.internalOrderedTasks = new TaskQueue();
   }

   public Future close() {
      Future<Void> fut;
      if (this.closeFuture == this.owner.closeFuture()) {
         fut = Future.succeededFuture();
      } else {
         fut = this.closeFuture.close();
      }

      fut = fut.eventually((Supplier)(() -> Future.future((p) -> this.executeBlockingTasks.shutdown(this.eventLoop, p))));
      if (this.executor instanceof WorkerExecutor) {
         WorkerExecutor workerExec = (WorkerExecutor)this.executor;
         fut = fut.eventually((Supplier)(() -> Future.future((p) -> workerExec.taskQueue().shutdown(this.eventLoop, p))));
      }

      return fut;
   }

   public Deployment getDeployment() {
      return this.deployment;
   }

   public CloseFuture closeFuture() {
      return this.closeFuture;
   }

   public JsonObject config() {
      return this.config;
   }

   public EventLoop nettyEventLoop() {
      return this.eventLoop;
   }

   public VertxImpl owner() {
      return this.owner;
   }

   public Future executeBlockingInternal(Handler action) {
      return executeBlocking(this, (Handler)action, this.internalWorkerPool, this.internalOrderedTasks);
   }

   public Future executeBlockingInternal(Callable action) {
      return executeBlocking(this, (Callable)action, this.internalWorkerPool, this.internalOrderedTasks);
   }

   public Future executeBlockingInternal(Handler action, boolean ordered) {
      return executeBlocking(this, (Handler)action, this.internalWorkerPool, ordered ? this.internalOrderedTasks : null);
   }

   public Future executeBlockingInternal(Callable action, boolean ordered) {
      return executeBlocking(this, (Callable)action, this.internalWorkerPool, ordered ? this.internalOrderedTasks : null);
   }

   public Future executeBlocking(Handler blockingCodeHandler, boolean ordered) {
      return executeBlocking(this, (Handler)blockingCodeHandler, this.workerPool, ordered ? this.executeBlockingTasks : null);
   }

   public Future executeBlocking(Callable blockingCodeHandler, boolean ordered) {
      return executeBlocking(this, (Callable)blockingCodeHandler, this.workerPool, ordered ? this.executeBlockingTasks : null);
   }

   public EventExecutor executor() {
      return this.executor;
   }

   public boolean isEventLoopContext() {
      return this.threadingModel == ThreadingModel.EVENT_LOOP;
   }

   public boolean isWorkerContext() {
      return this.threadingModel == ThreadingModel.WORKER;
   }

   public ThreadingModel threadingModel() {
      return this.threadingModel;
   }

   public boolean inThread() {
      return this.executor.inThread();
   }

   public Future executeBlocking(Handler blockingCodeHandler, TaskQueue queue) {
      return executeBlocking(this, (Handler)blockingCodeHandler, this.workerPool, queue);
   }

   public Future executeBlocking(Callable blockingCodeHandler, TaskQueue queue) {
      return executeBlocking(this, (Callable)blockingCodeHandler, this.workerPool, queue);
   }

   static Future executeBlocking(ContextInternal context, Callable blockingCodeHandler, WorkerPool workerPool, TaskQueue queue) {
      return internalExecuteBlocking(context, (promise) -> {
         T result;
         try {
            result = (T)blockingCodeHandler.call();
         } catch (Throwable e) {
            promise.fail(e);
            return;
         }

         promise.complete(result);
      }, workerPool, queue);
   }

   static Future executeBlocking(ContextInternal context, Handler blockingCodeHandler, WorkerPool workerPool, TaskQueue queue) {
      return internalExecuteBlocking(context, (promise) -> {
         try {
            blockingCodeHandler.handle(promise);
         } catch (Throwable e) {
            promise.tryFail(e);
         }

      }, workerPool, queue);
   }

   private static Future internalExecuteBlocking(final ContextInternal context, final Handler blockingCodeHandler, WorkerPool workerPool, TaskQueue queue) {
      final PoolMetrics metrics = workerPool.metrics();
      final Object queueMetric = metrics != null ? metrics.submitted() : null;
      final Promise<T> promise = context.promise();
      Future<T> fut = promise.future();
      WorkerTask task = new WorkerTask(metrics, queueMetric) {
         protected void execute() {
            context.dispatch(promise, blockingCodeHandler);
         }

         void reject() {
            if (metrics != null) {
               metrics.rejected(queueMetric);
            }

            promise.fail((Throwable)(new RejectedExecutionException()));
         }
      };

      try {
         Executor exec = workerPool.executor();
         if (queue != null) {
            queue.execute(task, exec);
         } else {
            exec.execute(task);
         }
      } catch (RejectedExecutionException var10) {
         task.reject();
      }

      return fut;
   }

   public VertxTracer tracer() {
      return this.owner.tracer();
   }

   public ClassLoader classLoader() {
      return this.tccl;
   }

   public WorkerPool workerPool() {
      return this.workerPool;
   }

   public synchronized ConcurrentMap contextData() {
      if (this.data == null) {
         this.data = new ConcurrentHashMap();
      }

      return this.data;
   }

   public void reportException(Throwable t) {
      Handler<Throwable> handler = this.exceptionHandler;
      if (handler == null) {
         handler = this.owner.exceptionHandler();
      }

      if (handler != null) {
         handler.handle(t);
      } else {
         log.error("Unhandled exception", t);
      }

   }

   public Context exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public Handler exceptionHandler() {
      return this.exceptionHandler;
   }

   protected void runOnContext(ContextInternal ctx, Handler action) {
      try {
         Executor exec = ctx.executor();
         exec.execute(() -> ctx.dispatch(action));
      } catch (RejectedExecutionException var4) {
      }

   }

   public void execute(Runnable task) {
      this.execute((ContextInternal)this, (Runnable)task);
   }

   public final void execute(Object argument, Handler task) {
      this.execute(this, argument, task);
   }

   protected void execute(ContextInternal ctx, Runnable task) {
      if (this.inThread()) {
         task.run();
      } else {
         this.executor.execute(task);
      }

   }

   protected void execute(ContextInternal ctx, Object argument, Handler task) {
      if (this.inThread()) {
         task.handle(argument);
      } else {
         this.executor.execute(() -> task.handle(argument));
      }

   }

   public void emit(Object argument, Handler task) {
      this.emit(this, argument, task);
   }

   protected void emit(ContextInternal ctx, Object argument, Handler task) {
      if (this.inThread()) {
         ContextInternal prev = ctx.beginDispatch();

         try {
            task.handle(argument);
         } catch (Throwable t) {
            this.reportException(t);
         } finally {
            ctx.endDispatch(prev);
         }
      } else {
         this.executor.execute(() -> this.emit(ctx, argument, task));
      }

   }

   public ContextInternal duplicate() {
      return new DuplicatedContext(this);
   }
}
