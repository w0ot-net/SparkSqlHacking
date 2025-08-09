package io.vertx.core;

import io.netty.channel.EventLoopGroup;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientBuilder;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.PoolOptions;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketClientOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.metrics.Measured;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.VertxTracerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.tracing.TracingOptions;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@VertxGen
public interface Vertx extends Measured {
   static VertxBuilder builder() {
      return new VertxBuilder() {
         VertxOptions options;
         VertxTracerFactory tracerFactory;
         VertxMetricsFactory metricsFactory;
         ClusterManager clusterManager;

         public VertxBuilder with(VertxOptions options) {
            this.options = options;
            return this;
         }

         public VertxBuilder withTracer(VertxTracerFactory factory) {
            this.tracerFactory = factory;
            return this;
         }

         public VertxBuilder withClusterManager(ClusterManager clusterManager) {
            this.clusterManager = clusterManager;
            return this;
         }

         public VertxBuilder withMetrics(VertxMetricsFactory factory) {
            this.metricsFactory = factory;
            return this;
         }

         private io.vertx.core.impl.VertxBuilder internalBuilder() {
            VertxOptions opts = this.options != null ? this.options : new VertxOptions();
            if (this.clusterManager != null) {
               opts.setClusterManager(this.clusterManager);
            }

            if (this.metricsFactory != null) {
               MetricsOptions metricsOptions = opts.getMetricsOptions();
               if (metricsOptions != null) {
                  metricsOptions.setFactory(this.metricsFactory);
               } else {
                  opts.setMetricsOptions((new MetricsOptions()).setFactory(this.metricsFactory));
               }

               metricsOptions.setEnabled(true);
            }

            if (this.tracerFactory != null) {
               TracingOptions tracingOptions = opts.getTracingOptions();
               if (tracingOptions != null) {
                  tracingOptions.setFactory(this.tracerFactory);
               } else {
                  opts.setTracingOptions((new TracingOptions()).setFactory(this.tracerFactory));
               }
            }

            return (new io.vertx.core.impl.VertxBuilder(opts)).init();
         }

         public Vertx build() {
            return this.internalBuilder().vertx();
         }

         public Future buildClustered() {
            return Future.future((p) -> this.internalBuilder().clusteredVertx(p));
         }
      };
   }

   static Vertx vertx() {
      return vertx(new VertxOptions());
   }

   static Vertx vertx(VertxOptions options) {
      return builder().with(options).build();
   }

   static void clusteredVertx(VertxOptions options, Handler resultHandler) {
      Objects.requireNonNull(resultHandler);
      Future<Vertx> fut = clusteredVertx(options);
      fut.onComplete(resultHandler);
   }

   static Future clusteredVertx(VertxOptions options) {
      return builder().with(options).buildClustered();
   }

   static @Nullable Context currentContext() {
      return VertxImpl.currentContext(Thread.currentThread());
   }

   Context getOrCreateContext();

   NetServer createNetServer(NetServerOptions var1);

   default NetServer createNetServer() {
      return this.createNetServer(new NetServerOptions());
   }

   NetClient createNetClient(NetClientOptions var1);

   default NetClient createNetClient() {
      return this.createNetClient(new NetClientOptions());
   }

   HttpServer createHttpServer(HttpServerOptions var1);

   default HttpServer createHttpServer() {
      return this.createHttpServer(new HttpServerOptions());
   }

   default WebSocketClient createWebSocketClient() {
      return this.createWebSocketClient(new WebSocketClientOptions());
   }

   WebSocketClient createWebSocketClient(WebSocketClientOptions var1);

   HttpClientBuilder httpClientBuilder();

   default HttpClient createHttpClient(HttpClientOptions clientOptions, PoolOptions poolOptions) {
      return this.httpClientBuilder().with(clientOptions).with(poolOptions).build();
   }

   default HttpClient createHttpClient(HttpClientOptions clientOptions) {
      return this.createHttpClient(clientOptions, clientOptions.getPoolOptions());
   }

   default HttpClient createHttpClient(PoolOptions poolOptions) {
      return this.createHttpClient(new HttpClientOptions(), poolOptions);
   }

   default HttpClient createHttpClient() {
      return this.createHttpClient(new HttpClientOptions(), new PoolOptions());
   }

   DatagramSocket createDatagramSocket(DatagramSocketOptions var1);

   default DatagramSocket createDatagramSocket() {
      return this.createDatagramSocket(new DatagramSocketOptions());
   }

   @CacheReturn
   FileSystem fileSystem();

   @CacheReturn
   EventBus eventBus();

   DnsClient createDnsClient(int var1, String var2);

   DnsClient createDnsClient();

   DnsClient createDnsClient(DnsClientOptions var1);

   @CacheReturn
   SharedData sharedData();

   default Timer timer(long delay) {
      return this.timer(delay, TimeUnit.MILLISECONDS);
   }

   Timer timer(long var1, TimeUnit var3);

   long setTimer(long var1, Handler var3);

   /** @deprecated */
   @Deprecated
   TimeoutStream timerStream(long var1);

   default long setPeriodic(long delay, Handler handler) {
      return this.setPeriodic(delay, delay, handler);
   }

   long setPeriodic(long var1, long var3, Handler var5);

   /** @deprecated */
   @Deprecated
   default TimeoutStream periodicStream(long delay) {
      return this.periodicStream(0L, delay);
   }

   /** @deprecated */
   @Deprecated
   TimeoutStream periodicStream(long var1, long var3);

   boolean cancelTimer(long var1);

   void runOnContext(Handler var1);

   Future close();

   void close(Handler var1);

   @GenIgnore({"permitted-type"})
   default Future deployVerticle(Verticle verticle) {
      return this.deployVerticle(verticle, new DeploymentOptions());
   }

   @GenIgnore({"permitted-type"})
   void deployVerticle(Verticle var1, Handler var2);

   @GenIgnore({"permitted-type"})
   Future deployVerticle(Verticle var1, DeploymentOptions var2);

   @GenIgnore
   Future deployVerticle(Class var1, DeploymentOptions var2);

   @GenIgnore({"permitted-type"})
   Future deployVerticle(Supplier var1, DeploymentOptions var2);

   @GenIgnore({"permitted-type"})
   void deployVerticle(Verticle var1, DeploymentOptions var2, Handler var3);

   @GenIgnore
   void deployVerticle(Class var1, DeploymentOptions var2, Handler var3);

   @GenIgnore({"permitted-type"})
   void deployVerticle(Supplier var1, DeploymentOptions var2, Handler var3);

   default Future deployVerticle(String name) {
      return this.deployVerticle(name, new DeploymentOptions());
   }

   default void deployVerticle(String name, Handler completionHandler) {
      this.deployVerticle(name, new DeploymentOptions(), completionHandler);
   }

   Future deployVerticle(String var1, DeploymentOptions var2);

   void deployVerticle(String var1, DeploymentOptions var2, Handler var3);

   Future undeploy(String var1);

   void undeploy(String var1, Handler var2);

   Set deploymentIDs();

   @GenIgnore({"permitted-type"})
   void registerVerticleFactory(VerticleFactory var1);

   @GenIgnore({"permitted-type"})
   void unregisterVerticleFactory(VerticleFactory var1);

   @GenIgnore({"permitted-type"})
   Set verticleFactories();

   boolean isClustered();

   /** @deprecated */
   @Deprecated
   default void executeBlocking(Handler blockingCodeHandler, boolean ordered, Handler resultHandler) {
      Context context = this.getOrCreateContext();
      context.executeBlocking(blockingCodeHandler, ordered, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   default void executeBlocking(Handler blockingCodeHandler, Handler resultHandler) {
      this.executeBlocking(blockingCodeHandler, true, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   default Future executeBlocking(Handler blockingCodeHandler, boolean ordered) {
      Context context = this.getOrCreateContext();
      return context.executeBlocking(blockingCodeHandler, ordered);
   }

   @GenIgnore({"permitted-type"})
   default Future executeBlocking(Callable blockingCodeHandler, boolean ordered) {
      Context context = this.getOrCreateContext();
      return context.executeBlocking(blockingCodeHandler, ordered);
   }

   @GenIgnore({"permitted-type"})
   default void executeBlocking(Callable blockingCodeHandler, Handler resultHandler) {
      Future<T> future = this.executeBlocking(blockingCodeHandler, true);
      if (resultHandler != null) {
         future.onComplete(resultHandler);
      }

   }

   @GenIgnore({"permitted-type"})
   default void executeBlocking(Callable blockingCodeHandler, boolean ordered, Handler resultHandler) {
      Future<T> future = this.executeBlocking(blockingCodeHandler, ordered);
      if (resultHandler != null) {
         future.onComplete(resultHandler);
      }

   }

   /** @deprecated */
   @Deprecated
   default Future executeBlocking(Handler blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   @GenIgnore({"permitted-type"})
   default Future executeBlocking(Callable blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   /** @deprecated */
   @GenIgnore({"permitted-type"})
   @Deprecated
   EventLoopGroup nettyEventLoopGroup();

   WorkerExecutor createSharedWorkerExecutor(String var1);

   WorkerExecutor createSharedWorkerExecutor(String var1, int var2);

   WorkerExecutor createSharedWorkerExecutor(String var1, int var2, long var3);

   WorkerExecutor createSharedWorkerExecutor(String var1, int var2, long var3, TimeUnit var5);

   @CacheReturn
   boolean isNativeTransportEnabled();

   @CacheReturn
   Throwable unavailableNativeTransportCause();

   @Fluent
   Vertx exceptionHandler(@Nullable Handler var1);

   @GenIgnore
   @Nullable Handler exceptionHandler();
}
