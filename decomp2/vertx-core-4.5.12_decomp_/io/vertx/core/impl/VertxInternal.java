package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolverGroup;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.PoolOptions;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketClientOptions;
import io.vertx.core.impl.btc.BlockedThreadChecker;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.file.FileResolver;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.spi.transport.Transport;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public interface VertxInternal extends Vertx {
   PromiseInternal promise();

   PromiseInternal promise(Handler var1);

   long maxEventLoopExecTime();

   TimeUnit maxEventLoopExecTimeUnit();

   ContextInternal getOrCreateContext();

   EventLoopGroup getEventLoopGroup();

   EventLoopGroup getAcceptorEventLoopGroup();

   WorkerPool getWorkerPool();

   WorkerPool getInternalWorkerPool();

   Map sharedHttpServers();

   Map sharedNetServers();

   Map sharedTCPServers(Class var1);

   VertxMetrics metricsSPI();

   Transport transport();

   WebSocketClient createWebSocketClient(WebSocketClientOptions var1, CloseFuture var2);

   HttpClient createHttpPoolClient(HttpClientOptions var1, PoolOptions var2, CloseFuture var3);

   default Object createSharedClient(String clientKey, String clientName, CloseFuture closeFuture, Function supplier) {
      return SharedClientHolder.createSharedClient(this, clientKey, clientName, closeFuture, supplier);
   }

   ContextInternal getContext();

   ContextInternal createEventLoopContext(Deployment var1, CloseFuture var2, WorkerPool var3, ClassLoader var4);

   ContextInternal createEventLoopContext(EventLoop var1, WorkerPool var2, ClassLoader var3);

   ContextInternal createEventLoopContext();

   ContextInternal createWorkerContext(Deployment var1, CloseFuture var2, WorkerPool var3, ClassLoader var4);

   ContextInternal createWorkerContext(EventLoop var1, WorkerPool var2, ClassLoader var3);

   ContextInternal createWorkerContext();

   ContextInternal createVirtualThreadContext(Deployment var1, CloseFuture var2, ClassLoader var3);

   ContextInternal createVirtualThreadContext(EventLoop var1, ClassLoader var2);

   ContextInternal createVirtualThreadContext();

   WorkerExecutorInternal createSharedWorkerExecutor(String var1);

   WorkerExecutorInternal createSharedWorkerExecutor(String var1, int var2);

   WorkerExecutorInternal createSharedWorkerExecutor(String var1, int var2, long var3);

   WorkerExecutorInternal createSharedWorkerExecutor(String var1, int var2, long var3, TimeUnit var5);

   WorkerPool createSharedWorkerPool(String var1, int var2, long var3, TimeUnit var5);

   WorkerPool wrapWorkerPool(ExecutorService var1);

   void simulateKill();

   Deployment getDeployment(String var1);

   void failoverCompleteHandler(FailoverCompleteHandler var1);

   boolean isKilled();

   void failDuringFailover(boolean var1);

   File resolveFile(String var1);

   /** @deprecated */
   @Deprecated
   default void executeBlockingInternal(Handler blockingCodeHandler, Handler resultHandler) {
      ContextInternal context = this.getOrCreateContext();
      context.executeBlockingInternal(blockingCodeHandler, resultHandler);
   }

   default Future executeBlockingInternal(Callable blockingCodeHandler) {
      ContextInternal context = this.getOrCreateContext();
      return context.executeBlockingInternal(blockingCodeHandler);
   }

   /** @deprecated */
   @Deprecated
   default void executeBlockingInternal(Handler blockingCodeHandler, boolean ordered, Handler resultHandler) {
      ContextInternal context = this.getOrCreateContext();
      context.executeBlockingInternal(blockingCodeHandler, ordered, resultHandler);
   }

   default Future executeBlockingInternal(Callable blockingCodeHandler, boolean ordered) {
      ContextInternal context = this.getOrCreateContext();
      return context.executeBlockingInternal(blockingCodeHandler, ordered);
   }

   ClusterManager getClusterManager();

   HAManager haManager();

   void resolveAddress(String var1, Handler var2);

   AddressResolver addressResolver();

   FileResolver fileResolver();

   AddressResolverGroup nettyAddressResolverGroup();

   List contextLocals();

   BlockedThreadChecker blockedThreadChecker();

   CloseFuture closeFuture();

   VertxTracer tracer();

   void addCloseHook(Closeable var1);

   void removeCloseHook(Closeable var1);

   boolean isVirtualThreadAvailable();
}
