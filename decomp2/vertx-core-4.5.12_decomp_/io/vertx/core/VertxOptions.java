package io.vertx.core;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.tracing.TracingOptions;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class VertxOptions {
   /** @deprecated */
   @Deprecated
   private static final String DISABLE_TCCL_PROP_NAME = "vertx.disableTCCL";
   public static final int DEFAULT_EVENT_LOOP_POOL_SIZE = 2 * CpuCoreSensor.availableProcessors();
   public static final int DEFAULT_WORKER_POOL_SIZE = 20;
   public static final int DEFAULT_INTERNAL_BLOCKING_POOL_SIZE = 20;
   public static final long DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
   public static final TimeUnit DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;
   public static final long DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
   public static final TimeUnit DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
   public static final long DEFAULT_MAX_WORKER_EXECUTE_TIME;
   public static final TimeUnit DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
   public static final int DEFAULT_QUORUM_SIZE = 1;
   public static final String DEFAULT_HA_GROUP = "__DEFAULT__";
   public static final boolean DEFAULT_HA_ENABLED = false;
   public static final boolean DEFAULT_PREFER_NATIVE_TRANSPORT = false;
   private static final long DEFAULT_WARNING_EXCEPTION_TIME;
   public static final TimeUnit DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
   public static final boolean DEFAULT_DISABLE_TCCL;
   public static final boolean DEFAULT_USE_DAEMON_THREAD = false;
   private int eventLoopPoolSize;
   private int workerPoolSize;
   private int internalBlockingPoolSize;
   private long blockedThreadCheckInterval;
   private long maxEventLoopExecuteTime;
   private long maxWorkerExecuteTime;
   private ClusterManager clusterManager;
   private boolean haEnabled;
   private int quorumSize;
   private String haGroup;
   private MetricsOptions metricsOptions;
   private TracingOptions tracingOptions;
   private FileSystemOptions fileSystemOptions;
   private long warningExceptionTime;
   private EventBusOptions eventBusOptions;
   private AddressResolverOptions addressResolverOptions;
   private boolean preferNativeTransport;
   private TimeUnit maxEventLoopExecuteTimeUnit;
   private TimeUnit maxWorkerExecuteTimeUnit;
   private TimeUnit warningExceptionTimeUnit;
   private TimeUnit blockedThreadCheckIntervalUnit;
   private boolean disableTCCL;
   private Boolean useDaemonThread;

   public VertxOptions() {
      this.eventLoopPoolSize = DEFAULT_EVENT_LOOP_POOL_SIZE;
      this.workerPoolSize = 20;
      this.internalBlockingPoolSize = 20;
      this.blockedThreadCheckInterval = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
      this.maxEventLoopExecuteTime = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
      this.maxWorkerExecuteTime = DEFAULT_MAX_WORKER_EXECUTE_TIME;
      this.haEnabled = false;
      this.quorumSize = 1;
      this.haGroup = "__DEFAULT__";
      this.metricsOptions = new MetricsOptions();
      this.fileSystemOptions = new FileSystemOptions();
      this.warningExceptionTime = DEFAULT_WARNING_EXCEPTION_TIME;
      this.eventBusOptions = new EventBusOptions();
      this.addressResolverOptions = new AddressResolverOptions();
      this.preferNativeTransport = false;
      this.maxEventLoopExecuteTimeUnit = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
      this.maxWorkerExecuteTimeUnit = DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
      this.warningExceptionTimeUnit = DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
      this.blockedThreadCheckIntervalUnit = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;
      this.disableTCCL = DEFAULT_DISABLE_TCCL;
      this.useDaemonThread = false;
   }

   public VertxOptions(VertxOptions other) {
      this.eventLoopPoolSize = DEFAULT_EVENT_LOOP_POOL_SIZE;
      this.workerPoolSize = 20;
      this.internalBlockingPoolSize = 20;
      this.blockedThreadCheckInterval = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
      this.maxEventLoopExecuteTime = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
      this.maxWorkerExecuteTime = DEFAULT_MAX_WORKER_EXECUTE_TIME;
      this.haEnabled = false;
      this.quorumSize = 1;
      this.haGroup = "__DEFAULT__";
      this.metricsOptions = new MetricsOptions();
      this.fileSystemOptions = new FileSystemOptions();
      this.warningExceptionTime = DEFAULT_WARNING_EXCEPTION_TIME;
      this.eventBusOptions = new EventBusOptions();
      this.addressResolverOptions = new AddressResolverOptions();
      this.preferNativeTransport = false;
      this.maxEventLoopExecuteTimeUnit = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
      this.maxWorkerExecuteTimeUnit = DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
      this.warningExceptionTimeUnit = DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
      this.blockedThreadCheckIntervalUnit = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;
      this.disableTCCL = DEFAULT_DISABLE_TCCL;
      this.useDaemonThread = false;
      this.eventLoopPoolSize = other.getEventLoopPoolSize();
      this.workerPoolSize = other.getWorkerPoolSize();
      this.blockedThreadCheckInterval = other.getBlockedThreadCheckInterval();
      this.maxEventLoopExecuteTime = other.getMaxEventLoopExecuteTime();
      this.maxWorkerExecuteTime = other.getMaxWorkerExecuteTime();
      this.internalBlockingPoolSize = other.getInternalBlockingPoolSize();
      this.clusterManager = other.getClusterManager();
      this.haEnabled = other.isHAEnabled();
      this.quorumSize = other.getQuorumSize();
      this.haGroup = other.getHAGroup();
      this.metricsOptions = other.getMetricsOptions() != null ? new MetricsOptions(other.getMetricsOptions()) : null;
      this.fileSystemOptions = other.getFileSystemOptions() != null ? new FileSystemOptions(other.getFileSystemOptions()) : null;
      this.warningExceptionTime = other.warningExceptionTime;
      this.eventBusOptions = new EventBusOptions(other.eventBusOptions);
      this.addressResolverOptions = other.addressResolverOptions != null ? new AddressResolverOptions(other.getAddressResolverOptions()) : null;
      this.preferNativeTransport = other.preferNativeTransport;
      this.maxEventLoopExecuteTimeUnit = other.maxEventLoopExecuteTimeUnit;
      this.maxWorkerExecuteTimeUnit = other.maxWorkerExecuteTimeUnit;
      this.warningExceptionTimeUnit = other.warningExceptionTimeUnit;
      this.blockedThreadCheckIntervalUnit = other.blockedThreadCheckIntervalUnit;
      this.tracingOptions = other.tracingOptions != null ? other.tracingOptions.copy() : null;
      this.disableTCCL = other.disableTCCL;
      this.useDaemonThread = other.useDaemonThread;
   }

   public VertxOptions(JsonObject json) {
      this();
      VertxOptionsConverter.fromJson(json, this);
   }

   public int getEventLoopPoolSize() {
      return this.eventLoopPoolSize;
   }

   public VertxOptions setEventLoopPoolSize(int eventLoopPoolSize) {
      if (eventLoopPoolSize < 1) {
         throw new IllegalArgumentException("eventLoopPoolSize must be > 0");
      } else {
         this.eventLoopPoolSize = eventLoopPoolSize;
         return this;
      }
   }

   public int getWorkerPoolSize() {
      return this.workerPoolSize;
   }

   public VertxOptions setWorkerPoolSize(int workerPoolSize) {
      if (workerPoolSize < 1) {
         throw new IllegalArgumentException("workerPoolSize must be > 0");
      } else {
         this.workerPoolSize = workerPoolSize;
         return this;
      }
   }

   public long getBlockedThreadCheckInterval() {
      return this.blockedThreadCheckInterval;
   }

   public VertxOptions setBlockedThreadCheckInterval(long blockedThreadCheckInterval) {
      if (blockedThreadCheckInterval < 1L) {
         throw new IllegalArgumentException("blockedThreadCheckInterval must be > 0");
      } else {
         this.blockedThreadCheckInterval = blockedThreadCheckInterval;
         return this;
      }
   }

   public long getMaxEventLoopExecuteTime() {
      return this.maxEventLoopExecuteTime;
   }

   public VertxOptions setMaxEventLoopExecuteTime(long maxEventLoopExecuteTime) {
      if (maxEventLoopExecuteTime < 1L) {
         throw new IllegalArgumentException("maxEventLoopExecuteTime must be > 0");
      } else {
         this.maxEventLoopExecuteTime = maxEventLoopExecuteTime;
         return this;
      }
   }

   public long getMaxWorkerExecuteTime() {
      return this.maxWorkerExecuteTime;
   }

   public VertxOptions setMaxWorkerExecuteTime(long maxWorkerExecuteTime) {
      if (maxWorkerExecuteTime < 1L) {
         throw new IllegalArgumentException("maxWorkerpExecuteTime must be > 0");
      } else {
         this.maxWorkerExecuteTime = maxWorkerExecuteTime;
         return this;
      }
   }

   public ClusterManager getClusterManager() {
      return this.clusterManager;
   }

   /** @deprecated */
   @Deprecated
   public VertxOptions setClusterManager(ClusterManager clusterManager) {
      this.clusterManager = clusterManager;
      return this;
   }

   public int getInternalBlockingPoolSize() {
      return this.internalBlockingPoolSize;
   }

   public VertxOptions setInternalBlockingPoolSize(int internalBlockingPoolSize) {
      if (internalBlockingPoolSize < 1) {
         throw new IllegalArgumentException("internalBlockingPoolSize must be > 0");
      } else {
         this.internalBlockingPoolSize = internalBlockingPoolSize;
         return this;
      }
   }

   public boolean isHAEnabled() {
      return this.haEnabled;
   }

   public VertxOptions setHAEnabled(boolean haEnabled) {
      this.haEnabled = haEnabled;
      return this;
   }

   public int getQuorumSize() {
      return this.quorumSize;
   }

   public VertxOptions setQuorumSize(int quorumSize) {
      if (quorumSize < 1) {
         throw new IllegalArgumentException("quorumSize should be >= 1");
      } else {
         this.quorumSize = quorumSize;
         return this;
      }
   }

   public String getHAGroup() {
      return this.haGroup;
   }

   public VertxOptions setHAGroup(String haGroup) {
      Objects.requireNonNull(haGroup, "ha group cannot be null");
      this.haGroup = haGroup;
      return this;
   }

   public MetricsOptions getMetricsOptions() {
      return this.metricsOptions;
   }

   public FileSystemOptions getFileSystemOptions() {
      return this.fileSystemOptions;
   }

   public VertxOptions setMetricsOptions(MetricsOptions metrics) {
      this.metricsOptions = metrics;
      return this;
   }

   public VertxOptions setFileSystemOptions(FileSystemOptions fileSystemOptions) {
      this.fileSystemOptions = fileSystemOptions;
      return this;
   }

   public long getWarningExceptionTime() {
      return this.warningExceptionTime;
   }

   public VertxOptions setWarningExceptionTime(long warningExceptionTime) {
      if (warningExceptionTime < 1L) {
         throw new IllegalArgumentException("warningExceptionTime must be > 0");
      } else {
         this.warningExceptionTime = warningExceptionTime;
         return this;
      }
   }

   public EventBusOptions getEventBusOptions() {
      return this.eventBusOptions;
   }

   public VertxOptions setEventBusOptions(EventBusOptions options) {
      Objects.requireNonNull(options);
      this.eventBusOptions = options;
      return this;
   }

   public AddressResolverOptions getAddressResolverOptions() {
      return this.addressResolverOptions;
   }

   public VertxOptions setAddressResolverOptions(AddressResolverOptions addressResolverOptions) {
      this.addressResolverOptions = addressResolverOptions;
      return this;
   }

   public boolean getPreferNativeTransport() {
      return this.preferNativeTransport;
   }

   public VertxOptions setPreferNativeTransport(boolean preferNativeTransport) {
      this.preferNativeTransport = preferNativeTransport;
      return this;
   }

   public TimeUnit getMaxEventLoopExecuteTimeUnit() {
      return this.maxEventLoopExecuteTimeUnit;
   }

   public VertxOptions setMaxEventLoopExecuteTimeUnit(TimeUnit maxEventLoopExecuteTimeUnit) {
      this.maxEventLoopExecuteTimeUnit = maxEventLoopExecuteTimeUnit;
      return this;
   }

   public TimeUnit getMaxWorkerExecuteTimeUnit() {
      return this.maxWorkerExecuteTimeUnit;
   }

   public VertxOptions setMaxWorkerExecuteTimeUnit(TimeUnit maxWorkerExecuteTimeUnit) {
      this.maxWorkerExecuteTimeUnit = maxWorkerExecuteTimeUnit;
      return this;
   }

   public TimeUnit getWarningExceptionTimeUnit() {
      return this.warningExceptionTimeUnit;
   }

   public VertxOptions setWarningExceptionTimeUnit(TimeUnit warningExceptionTimeUnit) {
      this.warningExceptionTimeUnit = warningExceptionTimeUnit;
      return this;
   }

   public TimeUnit getBlockedThreadCheckIntervalUnit() {
      return this.blockedThreadCheckIntervalUnit;
   }

   public VertxOptions setBlockedThreadCheckIntervalUnit(TimeUnit blockedThreadCheckIntervalUnit) {
      this.blockedThreadCheckIntervalUnit = blockedThreadCheckIntervalUnit;
      return this;
   }

   public TracingOptions getTracingOptions() {
      return this.tracingOptions;
   }

   public VertxOptions setTracingOptions(TracingOptions tracingOptions) {
      this.tracingOptions = tracingOptions;
      return this;
   }

   public boolean getDisableTCCL() {
      return this.disableTCCL;
   }

   public VertxOptions setDisableTCCL(boolean disableTCCL) {
      this.disableTCCL = disableTCCL;
      return this;
   }

   public Boolean getUseDaemonThread() {
      return this.useDaemonThread;
   }

   public VertxOptions setUseDaemonThread(Boolean daemon) {
      this.useDaemonThread = daemon;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      VertxOptionsConverter.toJson(this, json);
      return json;
   }

   public String toString() {
      return "VertxOptions{eventLoopPoolSize=" + this.eventLoopPoolSize + ", workerPoolSize=" + this.workerPoolSize + ", internalBlockingPoolSize=" + this.internalBlockingPoolSize + ", blockedThreadCheckIntervalUnit=" + this.blockedThreadCheckIntervalUnit + ", blockedThreadCheckInterval=" + this.blockedThreadCheckInterval + ", maxEventLoopExecuteTimeUnit=" + this.maxEventLoopExecuteTimeUnit + ", maxEventLoopExecuteTime=" + this.maxEventLoopExecuteTime + ", maxWorkerExecuteTimeUnit=" + this.maxWorkerExecuteTimeUnit + ", maxWorkerExecuteTime=" + this.maxWorkerExecuteTime + ", clusterManager=" + this.clusterManager + ", haEnabled=" + this.haEnabled + ", preferNativeTransport=" + this.preferNativeTransport + ", quorumSize=" + this.quorumSize + ", haGroup='" + this.haGroup + '\'' + ", metrics=" + this.metricsOptions + ", tracing=" + this.tracingOptions + ", fileSystemOptions=" + this.fileSystemOptions + ", addressResolver=" + this.addressResolverOptions.toJson() + ", eventbus=" + this.eventBusOptions.toJson() + ", warningExceptionTimeUnit=" + this.warningExceptionTimeUnit + ", warningExceptionTime=" + this.warningExceptionTime + ", disableTCCL=" + this.disableTCCL + ", useDaemonThread=" + this.useDaemonThread + '}';
   }

   static {
      DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
      DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT = TimeUnit.MILLISECONDS;
      DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME = TimeUnit.SECONDS.toNanos(2L);
      DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT = TimeUnit.NANOSECONDS;
      DEFAULT_MAX_WORKER_EXECUTE_TIME = TimeUnit.SECONDS.toNanos(60L);
      DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT = TimeUnit.NANOSECONDS;
      DEFAULT_WARNING_EXCEPTION_TIME = TimeUnit.SECONDS.toNanos(5L);
      DEFAULT_WARNING_EXCEPTION_TIME_UNIT = TimeUnit.NANOSECONDS;
      DEFAULT_DISABLE_TCCL = Boolean.getBoolean("vertx.disableTCCL");
   }
}
