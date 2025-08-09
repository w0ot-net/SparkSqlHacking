package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolverGroup;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ThreadingModel;
import io.vertx.core.TimeoutStream;
import io.vertx.core.Timer;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.datagram.impl.DatagramSocketImpl;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.dns.impl.DnsClientImpl;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.EventBusInternal;
import io.vertx.core.eventbus.impl.clustered.ClusteredEventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.impl.FileSystemImpl;
import io.vertx.core.file.impl.WindowsFileSystem;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientBuilder;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.PoolOptions;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketClientOptions;
import io.vertx.core.http.impl.HttpClientBuilderImpl;
import io.vertx.core.http.impl.HttpClientImpl;
import io.vertx.core.http.impl.HttpServerImpl;
import io.vertx.core.http.impl.SharedWebSocketClient;
import io.vertx.core.http.impl.WebSocketClientImpl;
import io.vertx.core.impl.btc.BlockedThreadChecker;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.impl.resolver.DnsResolverProvider;
import io.vertx.core.impl.transports.JDKTransport;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.NetClientBuilder;
import io.vertx.core.net.impl.NetServerImpl;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.shareddata.impl.SharedDataImpl;
import io.vertx.core.spi.ExecutorServiceFactory;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.VertxThreadFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeSelector;
import io.vertx.core.spi.context.storage.ContextLocal;
import io.vertx.core.spi.file.FileResolver;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.PoolMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.spi.transport.Transport;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class VertxImpl implements VertxInternal, MetricsProvider {
   static final ThreadLocal nonVertxContextDispatch = new ThreadLocal();
   private static final Logger log = LoggerFactory.getLogger(VertxImpl.class);
   private static final String CLUSTER_MAP_NAME = "__vertx.haInfo";
   private static final String NETTY_IO_RATIO_PROPERTY_NAME = "vertx.nettyIORatio";
   private static final int NETTY_IO_RATIO = Integer.getInteger("vertx.nettyIORatio", 50);
   private final FileSystem fileSystem = this.getFileSystem();
   private final SharedData sharedData;
   private final VertxMetrics metrics;
   private final ConcurrentMap timeouts = new ConcurrentHashMap();
   private final AtomicLong timeoutCounter = new AtomicLong(0L);
   private final ClusterManager clusterManager;
   private final NodeSelector nodeSelector;
   private final DeploymentManager deploymentManager;
   private final VerticleManager verticleManager;
   private final FileResolver fileResolver;
   private final Map sharedHttpServers = new HashMap();
   private final Map sharedNetServers = new HashMap();
   private final ContextLocal[] contextLocals;
   private final List contextLocalsList;
   final WorkerPool workerPool;
   final WorkerPool internalWorkerPool;
   final WorkerPool virtualThreaWorkerPool;
   private final VertxThreadFactory threadFactory;
   private final ExecutorServiceFactory executorServiceFactory;
   private final ThreadFactory eventLoopThreadFactory;
   private final EventLoopGroup eventLoopGroup;
   private final EventLoopGroup acceptorEventLoopGroup;
   private final ExecutorService virtualThreadExecutor;
   private final BlockedThreadChecker checker;
   private final AddressResolver addressResolver;
   private final AddressResolverOptions addressResolverOptions;
   private final EventBusInternal eventBus;
   private volatile HAManager haManager;
   private boolean closed;
   private volatile Handler exceptionHandler;
   private final Map namedWorkerPools;
   private final int defaultWorkerPoolSize;
   private final long maxWorkerExecTime;
   private final TimeUnit maxWorkerExecTimeUnit;
   private final long maxEventLoopExecTime;
   private final TimeUnit maxEventLoopExecTimeUnit;
   private final CloseFuture closeFuture;
   private final Transport transport;
   private final VertxTracer tracer;
   private final ThreadLocal stickyContext = new ThreadLocal();
   private final boolean disableTCCL;
   private final Boolean useDaemonThread;

   private static ThreadFactory virtualThreadFactory() {
      try {
         Class<?> builderClass = ClassLoader.getSystemClassLoader().loadClass("java.lang.Thread$Builder");
         Class<?> ofVirtualClass = ClassLoader.getSystemClassLoader().loadClass("java.lang.Thread$Builder$OfVirtual");
         Method ofVirtualMethod = Thread.class.getDeclaredMethod("ofVirtual");
         Object builder = ofVirtualMethod.invoke((Object)null);
         Method nameMethod = ofVirtualClass.getDeclaredMethod("name", String.class, Long.TYPE);
         Method factoryMethod = builderClass.getDeclaredMethod("factory");
         builder = nameMethod.invoke(builder, "vert.x-virtual-thread-", 0L);
         return (ThreadFactory)factoryMethod.invoke(builder);
      } catch (Exception var6) {
         return null;
      }
   }

   VertxImpl(VertxOptions options, ClusterManager clusterManager, NodeSelector nodeSelector, VertxMetrics metrics, VertxTracer tracer, Transport transport, FileResolver fileResolver, VertxThreadFactory threadFactory, ExecutorServiceFactory executorServiceFactory) {
      if (Vertx.currentContext() != null) {
         log.warn("You're already on a Vert.x context, are you sure you want to create a new Vertx instance?");
      }

      Boolean useDaemonThread = options.getUseDaemonThread();
      int workerPoolSize = options.getWorkerPoolSize();
      int internalBlockingPoolSize = options.getInternalBlockingPoolSize();
      BlockedThreadChecker checker = new BlockedThreadChecker(options.getBlockedThreadCheckInterval(), options.getBlockedThreadCheckIntervalUnit(), options.getWarningExceptionTime(), options.getWarningExceptionTimeUnit());
      long maxEventLoopExecuteTime = options.getMaxEventLoopExecuteTime();
      TimeUnit maxEventLoopExecuteTimeUnit = options.getMaxEventLoopExecuteTimeUnit();
      ThreadFactory acceptorEventLoopThreadFactory = createThreadFactory(threadFactory, checker, useDaemonThread, maxEventLoopExecuteTime, maxEventLoopExecuteTimeUnit, "vert.x-acceptor-thread-", false);
      TimeUnit maxWorkerExecuteTimeUnit = options.getMaxWorkerExecuteTimeUnit();
      long maxWorkerExecuteTime = options.getMaxWorkerExecuteTime();
      ThreadFactory workerThreadFactory = createThreadFactory(threadFactory, checker, useDaemonThread, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, "vert.x-worker-thread-", true);
      ExecutorService workerExec = executorServiceFactory.createExecutor(workerThreadFactory, workerPoolSize, workerPoolSize);
      PoolMetrics workerPoolMetrics = metrics != null ? metrics.createPoolMetrics("worker", "vert.x-worker-thread", options.getWorkerPoolSize()) : null;
      ThreadFactory internalWorkerThreadFactory = createThreadFactory(threadFactory, checker, useDaemonThread, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, "vert.x-internal-blocking-", true);
      ExecutorService internalWorkerExec = executorServiceFactory.createExecutor(internalWorkerThreadFactory, internalBlockingPoolSize, internalBlockingPoolSize);
      PoolMetrics internalBlockingPoolMetrics = metrics != null ? metrics.createPoolMetrics("worker", "vert.x-internal-blocking", internalBlockingPoolSize) : null;
      ThreadFactory virtualThreadFactory = virtualThreadFactory();
      this.contextLocals = LocalSeq.get();
      this.contextLocalsList = Collections.unmodifiableList(Arrays.asList(this.contextLocals));
      this.closeFuture = new CloseFuture(log);
      this.maxEventLoopExecTime = maxEventLoopExecuteTime;
      this.maxEventLoopExecTimeUnit = maxEventLoopExecuteTimeUnit;
      this.eventLoopThreadFactory = createThreadFactory(threadFactory, checker, useDaemonThread, this.maxEventLoopExecTime, this.maxEventLoopExecTimeUnit, "vert.x-eventloop-thread-", false);
      this.eventLoopGroup = transport.eventLoopGroup(1, options.getEventLoopPoolSize(), this.eventLoopThreadFactory, NETTY_IO_RATIO);
      this.acceptorEventLoopGroup = transport.eventLoopGroup(0, 1, acceptorEventLoopThreadFactory, 100);
      this.virtualThreadExecutor = virtualThreadFactory != null ? new ThreadPerTaskExecutorService(virtualThreadFactory) : null;
      this.virtualThreaWorkerPool = virtualThreadFactory != null ? new WorkerPool(this.virtualThreadExecutor, (PoolMetrics)null) : null;
      this.internalWorkerPool = new WorkerPool(internalWorkerExec, internalBlockingPoolMetrics);
      this.namedWorkerPools = new HashMap();
      this.workerPool = new WorkerPool(workerExec, workerPoolMetrics);
      this.defaultWorkerPoolSize = options.getWorkerPoolSize();
      this.maxWorkerExecTime = maxWorkerExecuteTime;
      this.maxWorkerExecTimeUnit = maxWorkerExecuteTimeUnit;
      this.disableTCCL = options.getDisableTCCL();
      this.checker = checker;
      this.useDaemonThread = useDaemonThread;
      this.executorServiceFactory = executorServiceFactory;
      this.threadFactory = threadFactory;
      this.metrics = metrics;
      this.transport = transport;
      this.fileResolver = fileResolver;
      this.addressResolverOptions = options.getAddressResolverOptions();
      this.addressResolver = new AddressResolver(this, options.getAddressResolverOptions());
      this.tracer = tracer == VertxTracer.NOOP ? null : tracer;
      this.clusterManager = clusterManager;
      this.nodeSelector = nodeSelector;
      this.eventBus = (EventBusInternal)(clusterManager != null ? new ClusteredEventBus(this, options, clusterManager, nodeSelector) : new EventBusImpl(this));
      this.sharedData = new SharedDataImpl(this, clusterManager);
      this.deploymentManager = new DeploymentManager(this);
      this.verticleManager = new VerticleManager(this, this.deploymentManager);
   }

   void init() {
      this.eventBus.start(Promise.promise());
      if (this.metrics != null) {
         this.metrics.vertxCreated(this);
      }

   }

   void initClustered(VertxOptions options, Handler resultHandler) {
      this.nodeSelector.init(this, this.clusterManager);
      this.clusterManager.init(this, this.nodeSelector);
      Promise<Void> initPromise = this.getOrCreateContext().promise();
      initPromise.future().onComplete((ar) -> {
         if (ar.succeeded()) {
            if (this.metrics != null) {
               this.metrics.vertxCreated(this);
            }

            resultHandler.handle(Future.succeededFuture(this));
         } else {
            log.error("Failed to initialize clustered Vert.x", ar.cause());
            this.close().onComplete((ignore) -> resultHandler.handle(Future.failedFuture(ar.cause())));
         }

      });
      Promise<Void> joinPromise = Promise.promise();
      joinPromise.future().onComplete((ar) -> {
         if (ar.succeeded()) {
            this.createHaManager(options, initPromise);
         } else {
            initPromise.fail(ar.cause());
         }

      });
      this.clusterManager.join(joinPromise);
   }

   private void createHaManager(VertxOptions options, Promise initPromise) {
      if (options.isHAEnabled()) {
         this.executeBlocking((fut) -> {
            this.haManager = new HAManager(this, this.deploymentManager, this.verticleManager, this.clusterManager, this.clusterManager.getSyncMap("__vertx.haInfo"), options.getQuorumSize(), options.getHAGroup());
            fut.complete(this.haManager);
         }, false, (ar) -> {
            if (ar.succeeded()) {
               this.startEventBus(true, initPromise);
            } else {
               initPromise.fail(ar.cause());
            }

         });
      } else {
         this.startEventBus(false, initPromise);
      }

   }

   private void startEventBus(boolean haEnabled, Promise initPromise) {
      Promise<Void> promise = Promise.promise();
      this.eventBus.start(promise);
      promise.future().onComplete((ar) -> {
         if (ar.succeeded()) {
            if (haEnabled) {
               this.initializeHaManager(initPromise);
            } else {
               initPromise.complete();
            }
         } else {
            initPromise.fail(ar.cause());
         }

      });
   }

   private void initializeHaManager(Promise initPromise) {
      this.executeBlocking((fut) -> {
         this.haManager.init();
         fut.complete();
      }, false, initPromise);
   }

   protected FileSystem getFileSystem() {
      return (FileSystem)(Utils.isWindows() ? new WindowsFileSystem(this) : new FileSystemImpl(this));
   }

   public long maxEventLoopExecTime() {
      return this.maxEventLoopExecTime;
   }

   public TimeUnit maxEventLoopExecTimeUnit() {
      return this.maxEventLoopExecTimeUnit;
   }

   public DatagramSocket createDatagramSocket(DatagramSocketOptions options) {
      CloseFuture closeFuture = new CloseFuture(log);
      DatagramSocketImpl so = DatagramSocketImpl.create(this, closeFuture, options);
      closeFuture.add(so);
      CloseFuture fut = this.resolveCloseFuture();
      fut.add(closeFuture);
      return so;
   }

   public NetServer createNetServer(NetServerOptions options) {
      return new NetServerImpl(this, options);
   }

   public NetClient createNetClient(NetClientOptions options) {
      CloseFuture closeFuture = new CloseFuture(log);
      CloseFuture fut = this.resolveCloseFuture();
      fut.add(closeFuture);
      NetClientBuilder builder = new NetClientBuilder(this, options);
      builder.metrics(this.metricsSPI() != null ? this.metricsSPI().createNetClientMetrics(options) : null);
      builder.closeFuture(closeFuture);
      return builder.build();
   }

   public Transport transport() {
      return this.transport;
   }

   public boolean isNativeTransportEnabled() {
      return !(this.transport instanceof JDKTransport);
   }

   public Throwable unavailableNativeTransportCause() {
      return this.isNativeTransportEnabled() ? null : this.transport.unavailabilityCause();
   }

   public FileSystem fileSystem() {
      return this.fileSystem;
   }

   public SharedData sharedData() {
      return this.sharedData;
   }

   public HttpServer createHttpServer(HttpServerOptions serverOptions) {
      return new HttpServerImpl(this, serverOptions);
   }

   public WebSocketClient createWebSocketClient(WebSocketClientOptions options, CloseFuture closeFuture) {
      HttpClientOptions o = new HttpClientOptions(options);
      o.setDefaultHost(options.getDefaultHost());
      o.setDefaultPort(options.getDefaultPort());
      o.setVerifyHost(options.isVerifyHost());
      o.setMaxWebSocketFrameSize(options.getMaxFrameSize());
      o.setMaxWebSocketMessageSize(options.getMaxMessageSize());
      o.setMaxWebSockets(options.getMaxConnections());
      o.setSendUnmaskedFrames(options.isSendUnmaskedFrames());
      o.setTryUsePerFrameWebSocketCompression(options.getTryUsePerFrameCompression());
      o.setTryUsePerMessageWebSocketCompression(options.getTryUsePerMessageCompression());
      o.setWebSocketCompressionLevel(options.getCompressionLevel());
      o.setWebSocketCompressionAllowClientNoContext(options.getCompressionAllowClientNoContext());
      o.setWebSocketCompressionRequestServerNoContext(options.getCompressionRequestServerNoContext());
      o.setWebSocketClosingTimeout(options.getClosingTimeout());
      o.setShared(options.isShared());
      o.setName(options.getName());
      WebSocketClientImpl client = new WebSocketClientImpl(this, o, closeFuture);
      closeFuture.add(client);
      return client;
   }

   public WebSocketClient createWebSocketClient(WebSocketClientOptions options) {
      CloseFuture closeFuture = new CloseFuture();
      WebSocketClient client;
      if (options.isShared()) {
         client = (WebSocketClient)this.createSharedClient("__vertx.shared.webSocketClients", options.getName(), closeFuture, (cf) -> this.createWebSocketClient(options, cf));
         client = new SharedWebSocketClient(this, closeFuture, client);
      } else {
         client = this.createWebSocketClient(options, closeFuture);
      }

      this.resolveCloseFuture().add(closeFuture);
      return client;
   }

   public HttpClient createHttpPoolClient(HttpClientOptions clientOptions, PoolOptions poolOptions, CloseFuture closeFuture) {
      HttpClientImpl client = new HttpClientImpl(this, clientOptions, poolOptions, closeFuture);
      closeFuture.add(client);
      return client;
   }

   public HttpClientBuilder httpClientBuilder() {
      return new HttpClientBuilderImpl(this);
   }

   public EventBus eventBus() {
      return this.eventBus;
   }

   public long setPeriodic(long initialDelay, long delay, Handler handler) {
      ContextInternal ctx = this.getOrCreateContext();
      return this.scheduleTimeout(ctx, true, initialDelay, delay, TimeUnit.MILLISECONDS, ctx.isDeployment(), handler);
   }

   public TimeoutStream periodicStream(long initialDelay, long delay) {
      return new TimeoutStreamImpl(initialDelay, delay, true);
   }

   public long setTimer(long delay, Handler handler) {
      ContextInternal ctx = this.getOrCreateContext();
      return this.scheduleTimeout(ctx, false, delay, TimeUnit.MILLISECONDS, ctx.isDeployment(), handler);
   }

   public TimeoutStream timerStream(long delay) {
      return new TimeoutStreamImpl(delay, false);
   }

   public Timer timer(long delay, TimeUnit unit) {
      Objects.requireNonNull(unit);
      if (delay <= 0L) {
         throw new IllegalArgumentException("Invalid delay: " + delay);
      } else {
         ContextInternal ctx = this.getOrCreateContext();
         ScheduledFuture<Void> fut = ctx.nettyEventLoop().schedule(() -> null, delay, unit);
         TimerImpl promise = new TimerImpl(ctx, fut);
         fut.addListener(promise);
         return promise;
      }
   }

   public PromiseInternal promise() {
      ContextInternal context = this.getOrCreateContext();
      return context.promise();
   }

   public PromiseInternal promise(Handler handler) {
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

   public void runOnContext(Handler task) {
      ContextInternal context = this.getOrCreateContext();
      context.runOnContext(task);
   }

   public WorkerPool getWorkerPool() {
      return this.workerPool;
   }

   public WorkerPool getInternalWorkerPool() {
      return this.internalWorkerPool;
   }

   public EventLoopGroup getEventLoopGroup() {
      return this.eventLoopGroup;
   }

   public EventLoopGroup getAcceptorEventLoopGroup() {
      return this.acceptorEventLoopGroup;
   }

   public ContextInternal getOrCreateContext() {
      Thread thread = Thread.currentThread();
      ContextInternal ctx = this.getContext(thread);
      return ctx == null ? this.createContext(thread) : ctx;
   }

   private ContextInternal createContext(Thread thread) {
      ContextInternal ctx = this.createEventLoopContext();
      if (!(thread instanceof VertxThread)) {
         this.stickyContext.set(new WeakReference(ctx));
      }

      return ctx;
   }

   public Map sharedHttpServers() {
      return this.sharedHttpServers;
   }

   public Map sharedNetServers() {
      return this.sharedNetServers;
   }

   public Map sharedTCPServers(Class type) {
      if (NetServerImpl.class.isAssignableFrom(type)) {
         return this.sharedNetServers;
      } else if (HttpServerImpl.class.isAssignableFrom(type)) {
         return this.sharedHttpServers;
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean isMetricsEnabled() {
      return this.metrics != null;
   }

   public Metrics getMetrics() {
      return this.metrics;
   }

   public boolean cancelTimer(long id) {
      InternalTimerHandler handler = (InternalTimerHandler)this.timeouts.get(id);
      return handler != null ? handler.cancel() : false;
   }

   private ContextImpl createEventLoopContext(EventLoop eventLoop, CloseFuture closeFuture, WorkerPool workerPool, Deployment deployment, ClassLoader tccl) {
      ThreadingModel threadingModel = ThreadingModel.EVENT_LOOP;
      EventExecutor eventExecutor = new EventLoopExecutor(eventLoop);
      WorkerPool wp = workerPool != null ? workerPool : this.workerPool;
      return this.createContext(threadingModel, eventLoop, closeFuture, deployment, tccl, eventExecutor, wp);
   }

   private ContextImpl createWorkerContext(EventLoop eventLoop, CloseFuture closeFuture, WorkerPool workerPool, Deployment deployment, ClassLoader tccl) {
      WorkerPool wp = workerPool != null ? workerPool : this.workerPool;
      return this.createContext(ThreadingModel.WORKER, eventLoop, closeFuture, deployment, tccl, new WorkerExecutor(wp, new WorkerTaskQueue()), wp);
   }

   private ContextImpl createVirtualThreadContext(EventLoop eventLoop, CloseFuture closeFuture, Deployment deployment, ClassLoader tccl) {
      if (!this.isVirtualThreadAvailable()) {
         throw new IllegalStateException("This Java runtime does not support virtual threads");
      } else {
         return this.createContext(ThreadingModel.VIRTUAL_THREAD, eventLoop, closeFuture, deployment, tccl, new WorkerExecutor(this.virtualThreaWorkerPool, new WorkerTaskQueue()), this.virtualThreaWorkerPool);
      }
   }

   private ContextImpl createContext(ThreadingModel threadingModel, EventLoop eventLoop, CloseFuture closeFuture, Deployment deployment, ClassLoader tccl, EventExecutor eventExecutor, WorkerPool wp) {
      return new ContextImpl(this, this.contextLocals.length, threadingModel, eventLoop, eventExecutor, this.internalWorkerPool, wp, deployment, closeFuture, this.disableTCCL ? null : tccl);
   }

   public ContextImpl createEventLoopContext(Deployment deployment, CloseFuture closeFuture, WorkerPool workerPool, ClassLoader tccl) {
      return this.createEventLoopContext(this.eventLoopGroup.next(), closeFuture, workerPool, deployment, tccl);
   }

   public ContextImpl createEventLoopContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader tccl) {
      return this.createEventLoopContext(eventLoop, this.closeFuture, workerPool, (Deployment)null, tccl);
   }

   public ContextImpl createEventLoopContext() {
      return this.createEventLoopContext((Deployment)null, this.closeFuture, (WorkerPool)null, Thread.currentThread().getContextClassLoader());
   }

   public ContextInternal createWorkerContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader tccl) {
      return this.createWorkerContext(eventLoop, this.closeFuture, workerPool, (Deployment)null, tccl);
   }

   public ContextImpl createWorkerContext(Deployment deployment, CloseFuture closeFuture, WorkerPool workerPool, ClassLoader tccl) {
      return this.createWorkerContext(this.eventLoopGroup.next(), closeFuture, workerPool, deployment, tccl);
   }

   public ContextImpl createWorkerContext() {
      return this.createWorkerContext((Deployment)null, this.closeFuture, (WorkerPool)null, Thread.currentThread().getContextClassLoader());
   }

   public ContextImpl createVirtualThreadContext(Deployment deployment, CloseFuture closeFuture, ClassLoader tccl) {
      return this.createVirtualThreadContext(this.eventLoopGroup.next(), closeFuture, deployment, tccl);
   }

   public ContextImpl createVirtualThreadContext(EventLoop eventLoop, ClassLoader tccl) {
      return this.createVirtualThreadContext(eventLoop, this.closeFuture, (Deployment)null, tccl);
   }

   public ContextImpl createVirtualThreadContext() {
      return this.createVirtualThreadContext((Deployment)null, this.closeFuture, Thread.currentThread().getContextClassLoader());
   }

   public DnsClient createDnsClient(int port, String host) {
      return this.createDnsClient((new DnsClientOptions()).setHost(host).setPort(port));
   }

   public DnsClient createDnsClient() {
      return this.createDnsClient(new DnsClientOptions());
   }

   public DnsClient createDnsClient(DnsClientOptions options) {
      String host = options.getHost();
      int port = options.getPort();
      if (host == null || port < 0) {
         DnsResolverProvider provider = DnsResolverProvider.create(this, this.addressResolverOptions);
         InetSocketAddress address = (InetSocketAddress)provider.nameServerAddresses().get(0);
         options = (new DnsClientOptions(options)).setHost(address.getAddress().getHostAddress()).setPort(address.getPort());
      }

      return new DnsClientImpl(this, options);
   }

   private long scheduleTimeout(ContextInternal context, boolean periodic, long initialDelay, long delay, TimeUnit timeUnit, boolean addCloseHook, Handler handler) {
      if (delay < 1L) {
         throw new IllegalArgumentException("Cannot schedule a timer with delay < 1 ms");
      } else if (initialDelay < 0L) {
         throw new IllegalArgumentException("Cannot schedule a timer with initialDelay < 0");
      } else {
         long timerId = this.timeoutCounter.getAndIncrement();
         InternalTimerHandler task = new InternalTimerHandler(timerId, handler, periodic, context);
         this.timeouts.put(timerId, task);
         if (addCloseHook) {
            context.addCloseHook(task);
         }

         EventLoop el = context.nettyEventLoop();
         if (periodic) {
            task.future = el.scheduleAtFixedRate(task, initialDelay, delay, timeUnit);
         } else {
            task.future = el.schedule(task, delay, timeUnit);
         }

         return task.id;
      }
   }

   public long scheduleTimeout(ContextInternal context, boolean periodic, long delay, TimeUnit timeUnit, boolean addCloseHook, Handler handler) {
      return this.scheduleTimeout(context, periodic, delay, delay, timeUnit, addCloseHook, handler);
   }

   public ContextInternal getContext() {
      return this.getContext(Thread.currentThread());
   }

   public static ContextInternal currentContext(Thread thread) {
      if (thread instanceof VertxThread) {
         return ((VertxThread)thread).context();
      } else {
         ContextDispatch current = (ContextDispatch)nonVertxContextDispatch.get();
         return current != null ? current.context : null;
      }
   }

   private ContextInternal getContext(Thread current) {
      ContextInternal context = currentContext(current);
      return context != null && context.owner() == this ? context : this.getStickyContext();
   }

   private ContextInternal getStickyContext() {
      WeakReference<ContextInternal> ref = (WeakReference)this.stickyContext.get();
      return ref != null ? (ContextInternal)ref.get() : null;
   }

   public ClusterManager getClusterManager() {
      return this.clusterManager;
   }

   public Future close() {
      Promise<Void> promise = Promise.promise();
      this.close(promise);
      return promise.future();
   }

   private void closeClusterManager(Handler completionHandler) {
      Promise<Void> leavePromise = this.getOrCreateContext().promise();
      if (this.clusterManager != null) {
         this.clusterManager.leave(leavePromise);
      } else {
         leavePromise.complete();
      }

      leavePromise.future().onComplete((ar) -> {
         if (ar.failed()) {
            log.error("Failed to leave cluster", ar.cause());
         }

         if (completionHandler != null) {
            completionHandler.handle(Future.succeededFuture());
         }

      });
   }

   public synchronized void close(Handler completionHandler) {
      if (!this.closed && this.eventBus != null) {
         this.closed = true;
         this.closeFuture.close().onComplete((ar) -> this.deploymentManager.undeployAll().onComplete((ar1) -> {
               HAManager haManager = this.haManager();
               Promise<Void> haPromise = Promise.promise();
               if (haManager != null) {
                  this.executeBlocking((fut) -> {
                     haManager.stop();
                     fut.complete();
                  }, false, haPromise);
               } else {
                  haPromise.complete();
               }

               haPromise.future().onComplete((ar2) -> this.addressResolver.close((ar3) -> {
                     Promise<Void> ebClose = this.getOrCreateContext().promise();
                     this.eventBus.close(ebClose);
                     ebClose.future().onComplete((ar4) -> this.closeClusterManager((ar5) -> this.deleteCacheDirAndShutdown(completionHandler)));
                  }));
            }));
      } else {
         if (completionHandler != null) {
            completionHandler.handle(Future.succeededFuture());
         }

      }
   }

   void duplicate(ContextBase src, ContextBase dst) {
      for(int i = 0; i < this.contextLocals.length; ++i) {
         ContextLocalImpl<?> contextLocal = (ContextLocalImpl)this.contextLocals[i];
         Object local = src.get(i);
         if (local != null) {
            local = contextLocal.duplicator.apply(local);
         }

         dst.set(i, local);
      }

   }

   public Future deployVerticle(String name, DeploymentOptions options) {
      if (options.isHa() && this.haManager() != null) {
         Promise<String> promise = this.getOrCreateContext().promise();
         this.haManager().deployVerticle(name, options, promise);
         return promise.future();
      } else {
         return this.verticleManager.deployVerticle(name, options).map(Deployment::deploymentID);
      }
   }

   public void deployVerticle(String name, DeploymentOptions options, Handler completionHandler) {
      Future<String> fut = this.deployVerticle(name, options);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   public void deployVerticle(Verticle verticle, Handler completionHandler) {
      Future<String> fut = this.deployVerticle(verticle);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   public Future deployVerticle(Verticle verticle, DeploymentOptions options) {
      if (options.getInstances() != 1) {
         throw new IllegalArgumentException("Can't specify > 1 instances for already created verticle");
      } else {
         return this.deployVerticle((Callable)(() -> verticle), (DeploymentOptions)options);
      }
   }

   public void deployVerticle(Verticle verticle, DeploymentOptions options, Handler completionHandler) {
      Future<String> fut = this.deployVerticle(verticle, options);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   public Future deployVerticle(Class verticleClass, DeploymentOptions options) {
      return this.deployVerticle(verticleClass::newInstance, options);
   }

   public void deployVerticle(Class verticleClass, DeploymentOptions options, Handler completionHandler) {
      Future<String> fut = this.deployVerticle(verticleClass, options);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   public Future deployVerticle(Supplier verticleSupplier, DeploymentOptions options) {
      return this.deployVerticle(verticleSupplier::get, options);
   }

   public void deployVerticle(Supplier verticleSupplier, DeploymentOptions options, Handler completionHandler) {
      Future<String> fut = this.deployVerticle(verticleSupplier, options);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   private Future deployVerticle(Callable verticleSupplier, DeploymentOptions options) {
      boolean closed;
      synchronized(this) {
         closed = this.closed;
      }

      return closed ? Future.failedFuture("Vert.x closed") : this.deploymentManager.deployVerticle(verticleSupplier, options);
   }

   public Future undeploy(String deploymentID) {
      HAManager haManager = this.haManager();
      Future<Void> future;
      if (haManager != null) {
         future = this.executeBlocking((fut) -> {
            haManager.removeFromHA(deploymentID);
            fut.complete();
         }, false);
      } else {
         future = this.getOrCreateContext().succeededFuture();
      }

      return future.compose((v) -> this.deploymentManager.undeployVerticle(deploymentID));
   }

   public void undeploy(String deploymentID, Handler completionHandler) {
      Future<Void> fut = this.undeploy(deploymentID);
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   public Set deploymentIDs() {
      return this.deploymentManager.deployments();
   }

   public void registerVerticleFactory(VerticleFactory factory) {
      this.verticleManager.registerVerticleFactory(factory);
   }

   public void unregisterVerticleFactory(VerticleFactory factory) {
      this.verticleManager.unregisterVerticleFactory(factory);
   }

   public Set verticleFactories() {
      return this.verticleManager.verticleFactories();
   }

   public boolean isClustered() {
      return this.clusterManager != null;
   }

   public EventLoopGroup nettyEventLoopGroup() {
      return this.eventLoopGroup;
   }

   public void simulateKill() {
      if (this.haManager() != null) {
         this.haManager().simulateKill();
      }

   }

   public Deployment getDeployment(String deploymentID) {
      return this.deploymentManager.getDeployment(deploymentID);
   }

   public synchronized void failoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler) {
      if (this.haManager() != null) {
         this.haManager().setFailoverCompleteHandler(failoverCompleteHandler);
      }

   }

   public boolean isKilled() {
      return this.haManager().isKilled();
   }

   public void failDuringFailover(boolean fail) {
      if (this.haManager() != null) {
         this.haManager().failDuringFailover(fail);
      }

   }

   public VertxMetrics metricsSPI() {
      return this.metrics;
   }

   public File resolveFile(String fileName) {
      return this.fileResolver.resolveFile(fileName);
   }

   public void resolveAddress(String hostname, Handler resultHandler) {
      this.addressResolver.resolveHostname(hostname, resultHandler);
   }

   public AddressResolver addressResolver() {
      return this.addressResolver;
   }

   public AddressResolverGroup nettyAddressResolverGroup() {
      return this.addressResolver.nettyAddressResolverGroup();
   }

   public List contextLocals() {
      return this.contextLocalsList;
   }

   public FileResolver fileResolver() {
      return this.fileResolver;
   }

   public BlockedThreadChecker blockedThreadChecker() {
      return this.checker;
   }

   private void deleteCacheDirAndShutdown(Handler completionHandler) {
      this.executeBlockingInternal((fut) -> {
         try {
            this.fileResolver.close();
            fut.complete();
         } catch (IOException e) {
            fut.tryFail((Throwable)e);
         }

      }, (ar) -> {
         this.workerPool.close();
         this.internalWorkerPool.close();
         (new ArrayList(this.namedWorkerPools.values())).forEach(WorkerPool::close);
         if (this.virtualThreadExecutor != null) {
            this.virtualThreadExecutor.shutdown();

            try {
               this.virtualThreadExecutor.awaitTermination(10L, TimeUnit.SECONDS);
            } catch (InterruptedException var4) {
            }
         }

         this.acceptorEventLoopGroup.shutdownGracefully(0L, 10L, TimeUnit.SECONDS).addListener(new GenericFutureListener() {
            public void operationComplete(io.netty.util.concurrent.Future future) throws Exception {
               if (!future.isSuccess()) {
                  VertxImpl.log.warn("Failure in shutting down acceptor event loop group", future.cause());
               }

               VertxImpl.this.eventLoopGroup.shutdownGracefully(0L, 10L, TimeUnit.SECONDS).addListener(new GenericFutureListener() {
                  public void operationComplete(io.netty.util.concurrent.Future future) throws Exception {
                     if (!future.isSuccess()) {
                        VertxImpl.log.warn("Failure in shutting down event loop group", future.cause());
                     }

                     if (VertxImpl.this.metrics != null) {
                        VertxImpl.this.metrics.close();
                     }

                     if (VertxImpl.this.tracer != null) {
                        VertxImpl.this.tracer.close();
                     }

                     VertxImpl.this.checker.close();
                     if (completionHandler != null) {
                        VertxImpl.this.eventLoopThreadFactory.newThread(() -> completionHandler.handle(Future.succeededFuture())).start();
                     }

                  }
               });
            }
         });
      });
   }

   public HAManager haManager() {
      return this.haManager;
   }

   public WorkerExecutorImpl createSharedWorkerExecutor(String name) {
      return this.createSharedWorkerExecutor(name, this.defaultWorkerPoolSize);
   }

   public WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize) {
      return this.createSharedWorkerExecutor(name, poolSize, this.maxWorkerExecTime);
   }

   public synchronized WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime) {
      return this.createSharedWorkerExecutor(name, poolSize, maxExecuteTime, this.maxWorkerExecTimeUnit);
   }

   public synchronized WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit) {
      SharedWorkerPool sharedWorkerPool = this.createSharedWorkerPool(name, poolSize, maxExecuteTime, maxExecuteTimeUnit);
      CloseFuture parentCf = this.resolveCloseFuture();
      CloseFuture execCf = new CloseFuture();
      parentCf.add(execCf);
      WorkerExecutorImpl namedExec = new WorkerExecutorImpl(this, execCf, sharedWorkerPool);
      execCf.add(namedExec);
      return namedExec;
   }

   public synchronized SharedWorkerPool createSharedWorkerPool(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit) {
      if (poolSize < 1) {
         throw new IllegalArgumentException("poolSize must be > 0");
      } else if (maxExecuteTime < 1L) {
         throw new IllegalArgumentException("maxExecuteTime must be > 0");
      } else {
         SharedWorkerPool sharedWorkerPool = (SharedWorkerPool)this.namedWorkerPools.get(name);
         if (sharedWorkerPool == null) {
            ThreadFactory workerThreadFactory = createThreadFactory(this.threadFactory, this.checker, this.useDaemonThread, maxExecuteTime, maxExecuteTimeUnit, name + "-", true);
            ExecutorService workerExec = this.executorServiceFactory.createExecutor(workerThreadFactory, poolSize, poolSize);
            PoolMetrics workerMetrics = this.metrics != null ? this.metrics.createPoolMetrics("worker", name, poolSize) : null;
            this.namedWorkerPools.put(name, sharedWorkerPool = new SharedWorkerPool(name, workerExec, workerMetrics));
         } else {
            sharedWorkerPool.refCount++;
         }

         return sharedWorkerPool;
      }
   }

   public WorkerPool wrapWorkerPool(ExecutorService executor) {
      PoolMetrics workerMetrics = this.metrics != null ? this.metrics.createPoolMetrics("worker", (String)null, -1) : null;
      return new WorkerPool(executor, workerMetrics);
   }

   private static ThreadFactory createThreadFactory(VertxThreadFactory threadFactory, BlockedThreadChecker checker, Boolean useDaemonThread, long maxExecuteTime, TimeUnit maxExecuteTimeUnit, String prefix, boolean worker) {
      AtomicInteger threadCount = new AtomicInteger(0);
      return (runnable) -> {
         VertxThread thread = threadFactory.newVertxThread(runnable, prefix + threadCount.getAndIncrement(), worker, maxExecuteTime, maxExecuteTimeUnit);
         checker.registerThread(thread, thread.info);
         if (useDaemonThread != null && thread.isDaemon() != useDaemonThread) {
            thread.setDaemon(useDaemonThread);
         }

         return thread;
      };
   }

   public Vertx exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public Handler exceptionHandler() {
      return this.exceptionHandler;
   }

   public CloseFuture closeFuture() {
      return this.closeFuture;
   }

   public VertxTracer tracer() {
      return this.tracer;
   }

   public void addCloseHook(Closeable hook) {
      this.closeFuture.add(hook);
   }

   public void removeCloseHook(Closeable hook) {
      this.closeFuture.remove(hook);
   }

   public boolean isVirtualThreadAvailable() {
      return this.virtualThreadExecutor != null;
   }

   private CloseFuture resolveCloseFuture() {
      ContextInternal context = this.getContext();
      return context != null ? context.closeFuture() : this.closeFuture;
   }

   void executeIsolated(Handler task) {
      if (Thread.currentThread() instanceof VertxThread) {
         ContextInternal prev = this.beginDispatch((ContextInternal)null);

         try {
            task.handle((Object)null);
         } finally {
            this.endDispatch(prev);
         }
      } else {
         task.handle((Object)null);
      }

   }

   ContextInternal beginDispatch(ContextInternal context) {
      Thread thread = Thread.currentThread();
      ContextInternal prev;
      if (thread instanceof VertxThread) {
         VertxThread vertxThread = (VertxThread)thread;
         prev = vertxThread.context;
         if (!ContextImpl.DISABLE_TIMINGS) {
            vertxThread.executeStart();
         }

         vertxThread.context = context;
         if (!this.disableTCCL) {
            if (prev == null) {
               vertxThread.topLevelTCCL = Thread.currentThread().getContextClassLoader();
            }

            if (context != null) {
               thread.setContextClassLoader(context.classLoader());
            }
         }
      } else {
         prev = this.beginDispatch2(thread, context);
      }

      return prev;
   }

   private ContextInternal beginDispatch2(Thread thread, ContextInternal context) {
      ContextDispatch current = (ContextDispatch)nonVertxContextDispatch.get();
      ContextInternal prev;
      if (current != null) {
         prev = current.context;
      } else {
         current = new ContextDispatch();
         nonVertxContextDispatch.set(current);
         prev = null;
      }

      current.context = context;
      if (!this.disableTCCL) {
         if (prev == null) {
            current.topLevelTCCL = Thread.currentThread().getContextClassLoader();
         }

         thread.setContextClassLoader(context.classLoader());
      }

      return prev;
   }

   void endDispatch(ContextInternal prev) {
      Thread thread = Thread.currentThread();
      if (thread instanceof VertxThread) {
         VertxThread vertxThread = (VertxThread)thread;
         vertxThread.context = prev;
         if (!this.disableTCCL) {
            ClassLoader tccl;
            if (prev == null) {
               tccl = vertxThread.topLevelTCCL;
               vertxThread.topLevelTCCL = null;
            } else {
               tccl = prev.classLoader();
            }

            Thread.currentThread().setContextClassLoader(tccl);
         }

         if (!ContextImpl.DISABLE_TIMINGS) {
            vertxThread.executeEnd();
         }
      } else {
         this.endDispatch2(prev);
      }

   }

   private void endDispatch2(ContextInternal prev) {
      ContextDispatch current = (ContextDispatch)nonVertxContextDispatch.get();
      ClassLoader tccl;
      if (prev != null) {
         current.context = prev;
         tccl = prev.classLoader();
      } else {
         nonVertxContextDispatch.remove();
         tccl = current.topLevelTCCL;
      }

      if (!this.disableTCCL) {
         Thread.currentThread().setContextClassLoader(tccl);
      }

   }

   static {
      if (System.getProperty("io.netty.leakDetection.level") == null && System.getProperty("io.netty.leakDetectionLevel") == null) {
         ResourceLeakDetector.setLevel(Level.DISABLED);
      }

   }

   class InternalTimerHandler implements Handler, Closeable, Runnable {
      private final Handler handler;
      private final boolean periodic;
      private final long id;
      private final ContextInternal context;
      private final AtomicBoolean disposed = new AtomicBoolean();
      private volatile java.util.concurrent.Future future;

      InternalTimerHandler(long id, Handler runnable, boolean periodic, ContextInternal context) {
         this.context = context;
         this.id = id;
         this.handler = runnable;
         this.periodic = periodic;
      }

      public void run() {
         this.context.emit(this);
      }

      public void handle(Void v) {
         if (this.periodic) {
            if (!this.disposed.get()) {
               this.handler.handle(this.id);
            }
         } else if (this.disposed.compareAndSet(false, true)) {
            VertxImpl.this.timeouts.remove(this.id);

            try {
               this.handler.handle(this.id);
            } finally {
               this.context.removeCloseHook(this);
            }
         }

      }

      private boolean cancel() {
         boolean cancelled = this.tryCancel();
         if (cancelled && this.context.isDeployment()) {
            this.context.removeCloseHook(this);
         }

         return cancelled;
      }

      private boolean tryCancel() {
         if (this.disposed.compareAndSet(false, true)) {
            VertxImpl.this.timeouts.remove(this.id);
            this.future.cancel(false);
            return true;
         } else {
            return false;
         }
      }

      public void close(Promise completion) {
         this.tryCancel();
         completion.complete();
      }
   }

   private class TimeoutStreamImpl implements TimeoutStream, Handler {
      private final long initialDelay;
      private final long delay;
      private final boolean periodic;
      private Long id;
      private Handler handler;
      private Handler endHandler;
      private long demand;

      public TimeoutStreamImpl(long delay, boolean periodic) {
         this(delay, delay, periodic);
      }

      public TimeoutStreamImpl(long initialDelay, long delay, boolean periodic) {
         this.initialDelay = initialDelay;
         this.delay = delay;
         this.periodic = periodic;
         this.demand = Long.MAX_VALUE;
      }

      public synchronized void handle(Long event) {
         try {
            if (this.demand > 0L) {
               --this.demand;
               this.handler.handle(event);
            }
         } finally {
            if (!this.periodic && this.endHandler != null) {
               this.endHandler.handle((Object)null);
            }

         }

      }

      public synchronized TimeoutStream fetch(long amount) {
         this.demand += amount;
         if (this.demand < 0L) {
            this.demand = Long.MAX_VALUE;
         }

         return this;
      }

      public TimeoutStream exceptionHandler(Handler handler) {
         return this;
      }

      public void cancel() {
         if (this.id != null) {
            VertxImpl.this.cancelTimer(this.id);
         }

      }

      public synchronized TimeoutStream handler(Handler handler) {
         if (handler != null) {
            if (this.id != null) {
               throw new IllegalStateException();
            }

            ContextInternal ctx = VertxImpl.this.getOrCreateContext();
            this.handler = handler;
            this.id = VertxImpl.this.scheduleTimeout(ctx, this.periodic, this.initialDelay, this.delay, TimeUnit.MILLISECONDS, ctx.isDeployment(), this);
         } else {
            this.cancel();
         }

         return this;
      }

      public synchronized TimeoutStream pause() {
         this.demand = 0L;
         return this;
      }

      public synchronized TimeoutStream resume() {
         this.demand = Long.MAX_VALUE;
         return this;
      }

      public synchronized TimeoutStream endHandler(Handler endHandler) {
         this.endHandler = endHandler;
         return this;
      }
   }

   class SharedWorkerPool extends WorkerPool {
      private final String name;
      private int refCount = 1;

      SharedWorkerPool(String name, ExecutorService workerExec, PoolMetrics workerMetrics) {
         super(workerExec, workerMetrics);
         this.name = name;
      }

      void close() {
         synchronized(VertxImpl.this) {
            if (--this.refCount > 0) {
               return;
            }

            VertxImpl.this.namedWorkerPools.remove(this.name);
         }

         super.close();
      }
   }

   static class ContextDispatch {
      ContextInternal context;
      ClassLoader topLevelTCCL;
   }
}
