package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ThreadingModel;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.tracing.VertxTracer;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;

final class DuplicatedContext extends ContextBase implements ContextInternal {
   final ContextImpl delegate;

   DuplicatedContext(ContextImpl delegate) {
      super(delegate);
      this.delegate = delegate;
   }

   public ThreadingModel threadingModel() {
      return this.delegate.threadingModel();
   }

   public boolean inThread() {
      return this.delegate.inThread();
   }

   public final CloseFuture closeFuture() {
      return this.delegate.closeFuture();
   }

   public final VertxTracer tracer() {
      return this.delegate.tracer();
   }

   public final JsonObject config() {
      return this.delegate.config();
   }

   public final Context exceptionHandler(Handler handler) {
      this.delegate.exceptionHandler(handler);
      return this;
   }

   public Executor executor() {
      return this.delegate.executor();
   }

   public final Handler exceptionHandler() {
      return this.delegate.exceptionHandler();
   }

   public final EventLoop nettyEventLoop() {
      return this.delegate.nettyEventLoop();
   }

   public final Deployment getDeployment() {
      return this.delegate.getDeployment();
   }

   public final VertxInternal owner() {
      return this.delegate.owner();
   }

   public final ClassLoader classLoader() {
      return this.delegate.classLoader();
   }

   public WorkerPool workerPool() {
      return this.delegate.workerPool();
   }

   public final void reportException(Throwable t) {
      this.delegate.reportException(t);
   }

   public final ConcurrentMap contextData() {
      return this.delegate.contextData();
   }

   public final Future executeBlockingInternal(Handler action) {
      return ContextImpl.executeBlocking(this, (Handler)action, this.delegate.internalWorkerPool, this.delegate.internalOrderedTasks);
   }

   public Future executeBlockingInternal(Callable action) {
      return ContextImpl.executeBlocking(this, (Callable)action, this.delegate.internalWorkerPool, this.delegate.internalOrderedTasks);
   }

   public final Future executeBlockingInternal(Handler action, boolean ordered) {
      return ContextImpl.executeBlocking(this, (Handler)action, this.delegate.internalWorkerPool, ordered ? this.delegate.internalOrderedTasks : null);
   }

   public Future executeBlockingInternal(Callable action, boolean ordered) {
      return ContextImpl.executeBlocking(this, (Callable)action, this.delegate.internalWorkerPool, ordered ? this.delegate.internalOrderedTasks : null);
   }

   public final Future executeBlocking(Handler action, boolean ordered) {
      return ContextImpl.executeBlocking(this, (Handler)action, this.delegate.workerPool, ordered ? this.delegate.executeBlockingTasks : null);
   }

   public final Future executeBlocking(Callable blockingCodeHandler, boolean ordered) {
      return ContextImpl.executeBlocking(this, (Callable)blockingCodeHandler, this.delegate.workerPool, ordered ? this.delegate.executeBlockingTasks : null);
   }

   public final Future executeBlocking(Handler blockingCodeHandler, TaskQueue queue) {
      return ContextImpl.executeBlocking(this, (Handler)blockingCodeHandler, this.delegate.workerPool, queue);
   }

   public final Future executeBlocking(Callable blockingCodeHandler, TaskQueue queue) {
      return ContextImpl.executeBlocking(this, (Callable)blockingCodeHandler, this.delegate.workerPool, queue);
   }

   public final void execute(Object argument, Handler task) {
      this.delegate.execute(this, argument, task);
   }

   public void emit(Object argument, Handler task) {
      this.delegate.emit(this, argument, task);
   }

   public void execute(Runnable task) {
      this.delegate.execute((ContextInternal)this, (Runnable)task);
   }

   public boolean isEventLoopContext() {
      return this.delegate.isEventLoopContext();
   }

   public boolean isWorkerContext() {
      return this.delegate.isWorkerContext();
   }

   public ContextInternal duplicate() {
      DuplicatedContext duplicate = new DuplicatedContext(this.delegate);
      this.delegate.owner().duplicate(this, duplicate);
      return duplicate;
   }

   public ContextInternal unwrap() {
      return this.delegate;
   }

   public boolean isDuplicate() {
      return true;
   }

   public Future close() {
      return Future.succeededFuture();
   }
}
