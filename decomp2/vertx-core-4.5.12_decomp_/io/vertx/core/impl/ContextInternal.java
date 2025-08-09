package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.future.FailedFuture;
import io.vertx.core.impl.future.PromiseImpl;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.future.SucceededFuture;
import io.vertx.core.spi.context.storage.AccessMode;
import io.vertx.core.spi.context.storage.ContextLocal;
import io.vertx.core.spi.tracing.VertxTracer;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface ContextInternal extends Context {
   ContextLocal LOCAL_MAP = new ContextLocalImpl(0, ConcurrentHashMap::new);

   static ContextInternal current() {
      return VertxImpl.currentContext(Thread.currentThread());
   }

   default void runOnContext(Handler action) {
      this.executor().execute(() -> this.dispatch(action));
   }

   Executor executor();

   EventLoop nettyEventLoop();

   default PromiseInternal promise() {
      return new PromiseImpl(this);
   }

   default PromiseInternal promise(Handler handler) {
      if (handler instanceof PromiseInternal) {
         PromiseInternal<T> promise = (PromiseInternal)handler;
         if (promise.context() != null) {
            return promise;
         }
      }

      PromiseInternal<T> promise = this.promise();
      promise.future().onComplete(handler);
      return promise;
   }

   default Future succeededFuture() {
      return new SucceededFuture(this, (Object)null);
   }

   default Future succeededFuture(Object result) {
      return new SucceededFuture(this, result);
   }

   default Future failedFuture(Throwable failure) {
      return new FailedFuture(this, failure);
   }

   default Future failedFuture(String message) {
      return new FailedFuture(this, message);
   }

   /** @deprecated */
   @Deprecated
   default void executeBlocking(Handler blockingCodeHandler, TaskQueue queue, Handler resultHandler) {
      Future<T> fut = this.executeBlocking(blockingCodeHandler, queue);
      ContextImpl.setResultHandler(this, fut, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   Future executeBlocking(Handler var1, TaskQueue var2);

   Future executeBlocking(Callable var1, TaskQueue var2);

   /** @deprecated */
   @Deprecated
   default void executeBlockingInternal(Handler action, Handler resultHandler) {
      Future<T> fut = this.executeBlockingInternal(action);
      ContextImpl.setResultHandler(this, fut, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   default void executeBlockingInternal(Handler action, boolean ordered, Handler resultHandler) {
      Future<T> fut = this.executeBlockingInternal(action, ordered);
      ContextImpl.setResultHandler(this, fut, resultHandler);
   }

   default void executeBlocking(Handler blockingCodeHandler, boolean ordered, Handler resultHandler) {
      Future<T> fut = this.executeBlocking(blockingCodeHandler, ordered);
      ContextImpl.setResultHandler(this, fut, resultHandler);
   }

   Future executeBlockingInternal(Handler var1);

   Future executeBlockingInternal(Callable var1);

   Future executeBlockingInternal(Handler var1, boolean var2);

   Future executeBlockingInternal(Callable var1, boolean var2);

   Deployment getDeployment();

   VertxInternal owner();

   boolean inThread();

   void emit(Object var1, Handler var2);

   default void emit(Handler task) {
      this.emit((Object)null, task);
   }

   default void execute(Handler task) {
      this.execute((Object)null, task);
   }

   void execute(Runnable var1);

   void execute(Object var1, Handler var2);

   default boolean isRunningOnContext() {
      return VertxImpl.currentContext(Thread.currentThread()) == this && this.inThread();
   }

   default void dispatch(Runnable handler) {
      ContextInternal prev = this.beginDispatch();

      try {
         handler.run();
      } catch (Throwable t) {
         this.reportException(t);
      } finally {
         this.endDispatch(prev);
      }

   }

   default void dispatch(Handler handler) {
      this.dispatch((Object)null, handler);
   }

   default void dispatch(Object event, Handler handler) {
      ContextInternal prev = this.beginDispatch();

      try {
         handler.handle(event);
      } catch (Throwable t) {
         this.reportException(t);
      } finally {
         this.endDispatch(prev);
      }

   }

   Future close();

   default ContextInternal beginDispatch() {
      VertxImpl vertx = (VertxImpl)this.owner();
      return vertx.beginDispatch(this);
   }

   default void endDispatch(ContextInternal previous) {
      VertxImpl vertx = (VertxImpl)this.owner();
      vertx.endDispatch(previous);
   }

   void reportException(Throwable var1);

   ConcurrentMap contextData();

   default Object get(Object key) {
      return this.contextData().get(key);
   }

   default void put(Object key, Object value) {
      this.contextData().put(key, value);
   }

   default boolean remove(Object key) {
      return this.contextData().remove(key) != null;
   }

   default ConcurrentMap localContextData() {
      return (ConcurrentMap)LOCAL_MAP.get(this, (Supplier)(ConcurrentHashMap::new));
   }

   default Object getLocal(ContextLocal key) {
      return this.getLocal(key, AccessMode.CONCURRENT);
   }

   Object getLocal(ContextLocal var1, AccessMode var2);

   Object getLocal(ContextLocal var1, AccessMode var2, Supplier var3);

   void putLocal(ContextLocal var1, AccessMode var2, Object var3);

   default void removeLocal(ContextLocal key, AccessMode accessMode) {
      this.putLocal(key, accessMode, (Object)null);
   }

   /** @deprecated */
   @Deprecated
   default Object getLocal(Object key) {
      return this.localContextData().get(key);
   }

   /** @deprecated */
   @Deprecated
   default void putLocal(Object key, Object value) {
      this.localContextData().put(key, value);
   }

   /** @deprecated */
   @Deprecated
   default boolean removeLocal(Object key) {
      return this.localContextData().remove(key) != null;
   }

   ClassLoader classLoader();

   WorkerPool workerPool();

   VertxTracer tracer();

   ContextInternal duplicate();

   default long setPeriodic(long delay, Handler handler) {
      VertxImpl owner = (VertxImpl)this.owner();
      return owner.scheduleTimeout(this, true, delay, TimeUnit.MILLISECONDS, false, handler);
   }

   default long setTimer(long delay, Handler handler) {
      VertxImpl owner = (VertxImpl)this.owner();
      return owner.scheduleTimeout(this, false, delay, TimeUnit.MILLISECONDS, false, handler);
   }

   default boolean isDeployment() {
      return this.getDeployment() != null;
   }

   default String deploymentID() {
      Deployment deployment = this.getDeployment();
      return deployment != null ? deployment.deploymentID() : null;
   }

   default int getInstanceCount() {
      Deployment deployment = this.getDeployment();
      if (deployment == null) {
         return 0;
      } else {
         return deployment.deploymentOptions() == null ? 1 : deployment.deploymentOptions().getInstances();
      }
   }

   CloseFuture closeFuture();

   default void addCloseHook(Closeable hook) {
      this.closeFuture().add(hook);
   }

   default void removeCloseHook(Closeable hook) {
      this.closeFuture().remove(hook);
   }

   default ContextInternal unwrap() {
      return this;
   }

   default boolean isDuplicate() {
      return false;
   }
}
