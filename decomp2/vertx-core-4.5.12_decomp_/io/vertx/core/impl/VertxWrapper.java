package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolverGroup;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.TimeoutStream;
import io.vertx.core.Timer;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
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
import io.vertx.core.impl.btc.BlockedThreadChecker;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.file.FileResolver;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.spi.transport.Transport;
import java.io.File;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public abstract class VertxWrapper implements VertxInternal {
   protected final VertxInternal delegate;

   protected VertxWrapper(VertxInternal delegate) {
      if (delegate == null) {
         throw new NullPointerException("Null delegate not allowed");
      } else {
         this.delegate = delegate;
      }
   }

   public NetServer createNetServer(NetServerOptions options) {
      return this.delegate.createNetServer(options);
   }

   public NetClient createNetClient(NetClientOptions options) {
      return this.delegate.createNetClient(options);
   }

   public HttpServer createHttpServer(HttpServerOptions options) {
      return this.delegate.createHttpServer(options);
   }

   public HttpServer createHttpServer() {
      return this.delegate.createHttpServer();
   }

   public DatagramSocket createDatagramSocket(DatagramSocketOptions options) {
      return this.delegate.createDatagramSocket(options);
   }

   public FileSystem fileSystem() {
      return this.delegate.fileSystem();
   }

   public EventBus eventBus() {
      return this.delegate.eventBus();
   }

   public DnsClient createDnsClient(int port, String host) {
      return this.delegate.createDnsClient(port, host);
   }

   public DnsClient createDnsClient() {
      return this.delegate.createDnsClient();
   }

   public DnsClient createDnsClient(DnsClientOptions options) {
      return this.delegate.createDnsClient(options);
   }

   public SharedData sharedData() {
      return this.delegate.sharedData();
   }

   public Timer timer(long delay, TimeUnit unit) {
      return this.delegate.timer(delay, unit);
   }

   public long setTimer(long delay, Handler handler) {
      return this.delegate.setTimer(delay, handler);
   }

   public TimeoutStream timerStream(long delay) {
      return this.delegate.timerStream(delay);
   }

   public long setPeriodic(long initialDelay, long delay, Handler handler) {
      return this.delegate.setPeriodic(initialDelay, delay, handler);
   }

   public TimeoutStream periodicStream(long initialDelay, long delay) {
      return this.delegate.periodicStream(initialDelay, delay);
   }

   public boolean cancelTimer(long id) {
      return this.delegate.cancelTimer(id);
   }

   public void runOnContext(Handler action) {
      this.delegate.runOnContext(action);
   }

   public Future close() {
      return this.delegate.close();
   }

   public void close(Handler completionHandler) {
      this.delegate.close(completionHandler);
   }

   public void deployVerticle(Verticle verticle, Handler completionHandler) {
      this.delegate.deployVerticle(verticle, completionHandler);
   }

   public Future deployVerticle(Verticle verticle, DeploymentOptions options) {
      return this.delegate.deployVerticle(verticle, options);
   }

   public Future deployVerticle(Class verticleClass, DeploymentOptions options) {
      return this.delegate.deployVerticle(verticleClass, options);
   }

   public Future deployVerticle(Supplier verticleSupplier, DeploymentOptions options) {
      return this.delegate.deployVerticle(verticleSupplier, options);
   }

   public void deployVerticle(Verticle verticle, DeploymentOptions options, Handler completionHandler) {
      this.delegate.deployVerticle(verticle, options, completionHandler);
   }

   public void deployVerticle(Class verticleClass, DeploymentOptions options, Handler completionHandler) {
      this.delegate.deployVerticle(verticleClass, options, completionHandler);
   }

   public void deployVerticle(Supplier verticleSupplier, DeploymentOptions options, Handler completionHandler) {
      this.delegate.deployVerticle(verticleSupplier, options, completionHandler);
   }

   public Future deployVerticle(String name, DeploymentOptions options) {
      return this.delegate.deployVerticle(name, options);
   }

   public void deployVerticle(String name, DeploymentOptions options, Handler completionHandler) {
      this.delegate.deployVerticle(name, options, completionHandler);
   }

   public Future undeploy(String deploymentID) {
      return this.delegate.undeploy(deploymentID);
   }

   public void undeploy(String deploymentID, Handler completionHandler) {
      this.delegate.undeploy(deploymentID, completionHandler);
   }

   public Set deploymentIDs() {
      return this.delegate.deploymentIDs();
   }

   public void registerVerticleFactory(VerticleFactory factory) {
      this.delegate.registerVerticleFactory(factory);
   }

   public void unregisterVerticleFactory(VerticleFactory factory) {
      this.delegate.unregisterVerticleFactory(factory);
   }

   public Set verticleFactories() {
      return this.delegate.verticleFactories();
   }

   public boolean isClustered() {
      return this.delegate.isClustered();
   }

   public EventLoopGroup nettyEventLoopGroup() {
      return this.delegate.nettyEventLoopGroup();
   }

   public boolean isNativeTransportEnabled() {
      return this.delegate.isNativeTransportEnabled();
   }

   public Throwable unavailableNativeTransportCause() {
      return this.delegate.unavailableNativeTransportCause();
   }

   public Vertx exceptionHandler(Handler handler) {
      return this.delegate.exceptionHandler(handler);
   }

   public Handler exceptionHandler() {
      return this.delegate.exceptionHandler();
   }

   public PromiseInternal promise() {
      return this.delegate.promise();
   }

   public PromiseInternal promise(Handler handler) {
      return this.delegate.promise(handler);
   }

   public long maxEventLoopExecTime() {
      return this.delegate.maxEventLoopExecTime();
   }

   public TimeUnit maxEventLoopExecTimeUnit() {
      return this.delegate.maxEventLoopExecTimeUnit();
   }

   public ContextInternal getOrCreateContext() {
      return this.delegate.getOrCreateContext();
   }

   public EventLoopGroup getEventLoopGroup() {
      return this.delegate.getEventLoopGroup();
   }

   public EventLoopGroup getAcceptorEventLoopGroup() {
      return this.delegate.getAcceptorEventLoopGroup();
   }

   public WorkerPool getWorkerPool() {
      return this.delegate.getWorkerPool();
   }

   public WorkerPool getInternalWorkerPool() {
      return this.delegate.getInternalWorkerPool();
   }

   public Map sharedHttpServers() {
      return this.delegate.sharedHttpServers();
   }

   public Map sharedNetServers() {
      return this.delegate.sharedNetServers();
   }

   public Map sharedTCPServers(Class type) {
      return this.delegate.sharedTCPServers(type);
   }

   public VertxMetrics metricsSPI() {
      return this.delegate.metricsSPI();
   }

   public Transport transport() {
      return this.delegate.transport();
   }

   public WebSocketClient createWebSocketClient(WebSocketClientOptions options) {
      return this.delegate.createWebSocketClient(options);
   }

   public HttpClient createHttpPoolClient(HttpClientOptions clientOptions, PoolOptions poolOptions, CloseFuture closeFuture) {
      return this.delegate.createHttpPoolClient(clientOptions, poolOptions, closeFuture);
   }

   public HttpClientBuilder httpClientBuilder() {
      return this.delegate.httpClientBuilder();
   }

   public WebSocketClient createWebSocketClient(WebSocketClientOptions options, CloseFuture closeFuture) {
      return this.delegate.createWebSocketClient(options, closeFuture);
   }

   public ContextInternal getContext() {
      return this.delegate.getContext();
   }

   public ContextInternal createEventLoopContext(Deployment deployment, CloseFuture closeFuture, WorkerPool workerPool, ClassLoader tccl) {
      return this.delegate.createEventLoopContext(deployment, closeFuture, workerPool, tccl);
   }

   public ContextInternal createEventLoopContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader tccl) {
      return this.delegate.createEventLoopContext(eventLoop, workerPool, tccl);
   }

   public ContextInternal createEventLoopContext() {
      return this.delegate.createEventLoopContext();
   }

   public ContextInternal createVirtualThreadContext(Deployment deployment, CloseFuture closeFuture, ClassLoader tccl) {
      return this.delegate.createVirtualThreadContext(deployment, closeFuture, tccl);
   }

   public ContextInternal createVirtualThreadContext(EventLoop eventLoop, ClassLoader tccl) {
      return this.delegate.createVirtualThreadContext(eventLoop, tccl);
   }

   public ContextInternal createVirtualThreadContext() {
      return this.delegate.createVirtualThreadContext();
   }

   public ContextInternal createWorkerContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader tccl) {
      return this.delegate.createWorkerContext(eventLoop, workerPool, tccl);
   }

   public ContextInternal createWorkerContext(Deployment deployment, CloseFuture closeFuture, WorkerPool workerPool, ClassLoader tccl) {
      return this.delegate.createWorkerContext(deployment, closeFuture, workerPool, tccl);
   }

   public ContextInternal createWorkerContext() {
      return this.delegate.createWorkerContext();
   }

   public WorkerExecutorInternal createSharedWorkerExecutor(String name) {
      return this.delegate.createSharedWorkerExecutor(name);
   }

   public WorkerExecutorInternal createSharedWorkerExecutor(String name, int poolSize) {
      return this.delegate.createSharedWorkerExecutor(name, poolSize);
   }

   public WorkerExecutorInternal createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime) {
      return this.delegate.createSharedWorkerExecutor(name, poolSize, maxExecuteTime);
   }

   public WorkerExecutorInternal createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit) {
      return this.delegate.createSharedWorkerExecutor(name, poolSize, maxExecuteTime, maxExecuteTimeUnit);
   }

   public WorkerPool createSharedWorkerPool(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit) {
      return this.delegate.createSharedWorkerPool(name, poolSize, maxExecuteTime, maxExecuteTimeUnit);
   }

   public WorkerPool wrapWorkerPool(ExecutorService executor) {
      return this.delegate.wrapWorkerPool(executor);
   }

   public void simulateKill() {
      this.delegate.simulateKill();
   }

   public Deployment getDeployment(String deploymentID) {
      return this.delegate.getDeployment(deploymentID);
   }

   public void failoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler) {
      this.delegate.failoverCompleteHandler(failoverCompleteHandler);
   }

   public boolean isKilled() {
      return this.delegate.isKilled();
   }

   public void failDuringFailover(boolean fail) {
      this.delegate.failDuringFailover(fail);
   }

   public File resolveFile(String fileName) {
      return this.delegate.resolveFile(fileName);
   }

   public ClusterManager getClusterManager() {
      return this.delegate.getClusterManager();
   }

   public HAManager haManager() {
      return this.delegate.haManager();
   }

   public void resolveAddress(String hostname, Handler resultHandler) {
      this.delegate.resolveAddress(hostname, resultHandler);
   }

   public AddressResolver addressResolver() {
      return this.delegate.addressResolver();
   }

   public FileResolver fileResolver() {
      return this.delegate.fileResolver();
   }

   public AddressResolverGroup nettyAddressResolverGroup() {
      return this.delegate.nettyAddressResolverGroup();
   }

   public List contextLocals() {
      return this.delegate.contextLocals();
   }

   public BlockedThreadChecker blockedThreadChecker() {
      return this.delegate.blockedThreadChecker();
   }

   public CloseFuture closeFuture() {
      return this.delegate.closeFuture();
   }

   public VertxTracer tracer() {
      return this.delegate.tracer();
   }

   public void addCloseHook(Closeable hook) {
      this.delegate.addCloseHook(hook);
   }

   public void removeCloseHook(Closeable hook) {
      this.delegate.removeCloseHook(hook);
   }

   public boolean isVirtualThreadAvailable() {
      return this.delegate.isVirtualThreadAvailable();
   }

   public boolean isMetricsEnabled() {
      return this.delegate.isMetricsEnabled();
   }
}
