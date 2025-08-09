package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.impl.FileResolverImpl;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.impl.transports.EpollTransport;
import io.vertx.core.impl.transports.JDKTransport;
import io.vertx.core.impl.transports.KQueueTransport;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.ExecutorServiceFactory;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.VertxServiceProvider;
import io.vertx.core.spi.VertxThreadFactory;
import io.vertx.core.spi.VertxTracerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeSelector;
import io.vertx.core.spi.cluster.impl.DefaultNodeSelector;
import io.vertx.core.spi.file.FileResolver;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.spi.transport.Transport;
import io.vertx.core.tracing.TracingOptions;
import java.util.ArrayList;
import java.util.Collection;

public class VertxBuilder {
   private static final Logger log = LoggerFactory.getLogger(VertxBuilder.class);
   private VertxOptions options;
   private JsonObject config;
   private Transport transport;
   private ClusterManager clusterManager;
   private NodeSelector clusterNodeSelector;
   private VertxTracer tracer;
   private VertxThreadFactory threadFactory;
   private ExecutorServiceFactory executorServiceFactory;
   private VertxMetrics metrics;
   private FileResolver fileResolver;

   public VertxBuilder(JsonObject config) {
      this(new VertxOptions(config));
      this.config = config;
   }

   public VertxBuilder(VertxOptions options) {
      this.options = options;
   }

   public VertxBuilder() {
      this(new VertxOptions());
   }

   public VertxOptions options() {
      return this.options;
   }

   public JsonObject config() {
      return this.config;
   }

   public Transport findTransport() {
      return this.transport;
   }

   public VertxBuilder findTransport(Transport transport) {
      this.transport = transport;
      return this;
   }

   public ClusterManager clusterManager() {
      return this.clusterManager;
   }

   public VertxBuilder clusterManager(ClusterManager clusterManager) {
      this.clusterManager = clusterManager;
      return this;
   }

   public NodeSelector clusterNodeSelector() {
      return this.clusterNodeSelector;
   }

   public VertxBuilder clusterNodeSelector(NodeSelector selector) {
      this.clusterNodeSelector = selector;
      return this;
   }

   public VertxTracer tracer() {
      return this.tracer;
   }

   public VertxBuilder tracer(VertxTracer tracer) {
      this.tracer = tracer;
      return this;
   }

   public VertxMetrics metrics() {
      return this.metrics;
   }

   public VertxBuilder metrics(VertxMetrics metrics) {
      this.metrics = metrics;
      return this;
   }

   public FileResolver fileResolver() {
      return this.fileResolver;
   }

   public VertxBuilder fileResolver(FileResolver resolver) {
      this.fileResolver = resolver;
      return this;
   }

   public VertxThreadFactory threadFactory() {
      return this.threadFactory;
   }

   public VertxBuilder threadFactory(VertxThreadFactory factory) {
      this.threadFactory = factory;
      return this;
   }

   public ExecutorServiceFactory executorServiceFactory() {
      return this.executorServiceFactory;
   }

   public VertxBuilder executorServiceFactory(ExecutorServiceFactory factory) {
      this.executorServiceFactory = factory;
      return this;
   }

   public Vertx vertx() {
      this.checkBeforeInstantiating();
      VertxImpl vertx = new VertxImpl(this.options, (ClusterManager)null, (NodeSelector)null, this.metrics, this.tracer, this.transport, this.fileResolver, this.threadFactory, this.executorServiceFactory);
      vertx.init();
      return vertx;
   }

   public void clusteredVertx(Handler handler) {
      this.checkBeforeInstantiating();
      if (this.clusterManager == null) {
         throw new IllegalStateException("No ClusterManagerFactory instances found on classpath");
      } else {
         VertxImpl vertx = new VertxImpl(this.options, this.clusterManager, (NodeSelector)(this.clusterNodeSelector == null ? new DefaultNodeSelector() : this.clusterNodeSelector), this.metrics, this.tracer, this.transport, this.fileResolver, this.threadFactory, this.executorServiceFactory);
         vertx.initClustered(this.options, handler);
      }
   }

   public VertxBuilder init() {
      this.initTransport();
      Collection<VertxServiceProvider> providers = new ArrayList();
      initMetrics(this.options, providers);
      initTracing(this.options, providers);
      initClusterManager(this.options, providers);
      providers.addAll(ServiceHelper.loadFactories(VertxServiceProvider.class));
      this.initProviders(providers);
      this.initThreadFactory();
      this.initExecutorServiceFactory();
      this.initFileResolver();
      return this;
   }

   private void initProviders(Collection providers) {
      for(VertxServiceProvider provider : providers) {
         provider.init(this);
      }

   }

   private static void initMetrics(VertxOptions options, Collection providers) {
      MetricsOptions metricsOptions = options.getMetricsOptions();
      if (metricsOptions != null) {
         VertxMetricsFactory factory = metricsOptions.getFactory();
         if (factory != null) {
            providers.add(factory);
         }
      }

   }

   private static void initTracing(VertxOptions options, Collection providers) {
      TracingOptions tracingOptions = options.getTracingOptions();
      if (tracingOptions != null) {
         VertxTracerFactory factory = tracingOptions.getFactory();
         if (factory != null) {
            providers.add(factory);
         }
      }

   }

   private static void initClusterManager(VertxOptions options, Collection providers) {
      ClusterManager clusterManager = options.getClusterManager();
      if (clusterManager == null) {
         String clusterManagerClassName = System.getProperty("vertx.cluster.managerClass");
         if (clusterManagerClassName != null) {
            try {
               Class<?> clazz = Class.forName(clusterManagerClassName);
               clusterManager = (ClusterManager)clazz.newInstance();
            } catch (Exception e) {
               throw new IllegalStateException("Failed to instantiate " + clusterManagerClassName, e);
            }
         }
      }

      if (clusterManager != null) {
         providers.add(clusterManager);
      }

   }

   private void initTransport() {
      if (this.transport == null) {
         this.transport = findTransport(this.options.getPreferNativeTransport());
      }
   }

   private void initFileResolver() {
      if (this.fileResolver == null) {
         this.fileResolver = new FileResolverImpl(this.options.getFileSystemOptions());
      }
   }

   private void initThreadFactory() {
      if (this.threadFactory == null) {
         this.threadFactory = VertxThreadFactory.INSTANCE;
      }
   }

   private void initExecutorServiceFactory() {
      if (this.executorServiceFactory == null) {
         this.executorServiceFactory = ExecutorServiceFactory.INSTANCE;
      }
   }

   private void checkBeforeInstantiating() {
      this.checkTracing();
      this.checkMetrics();
   }

   private void checkTracing() {
      if (this.options.getTracingOptions() != null && this.tracer == null) {
         log.warn("Tracing options are configured but no tracer is instantiated. Make sure you have the VertxTracerFactory in your classpath and META-INF/services/io.vertx.core.spi.VertxServiceProvider contains the factory FQCN, or tracingOptions.getFactory() returns a non null value");
      }

   }

   private void checkMetrics() {
      if (this.options.getMetricsOptions() != null && this.options.getMetricsOptions().isEnabled() && this.metrics == null) {
         log.warn("Metrics options are configured but no metrics object is instantiated. Make sure you have the VertxMetricsFactory in your classpath and META-INF/services/io.vertx.core.spi.VertxServiceProvider contains the factory FQCN, or metricsOptions.getFactory() returns a non null value");
      }

   }

   public static Transport nativeTransport() {
      Transport transport = null;

      try {
         Transport epoll = new EpollTransport();
         if (epoll.isAvailable()) {
            return epoll;
         }

         transport = epoll;
      } catch (Throwable var3) {
      }

      try {
         Transport kqueue = new KQueueTransport();
         if (kqueue.isAvailable()) {
            return kqueue;
         }

         if (transport == null) {
            transport = kqueue;
         }
      } catch (Throwable var2) {
      }

      return transport;
   }

   static Transport findTransport(boolean preferNative) {
      if (preferNative) {
         for(Transport transport : ServiceHelper.loadFactories(Transport.class)) {
            if (transport.isAvailable()) {
               return transport;
            }
         }

         Transport nativeTransport = nativeTransport();
         if (nativeTransport != null && nativeTransport.isAvailable()) {
            return nativeTransport;
         } else {
            return JDKTransport.INSTANCE;
         }
      } else {
         return JDKTransport.INSTANCE;
      }
   }
}
