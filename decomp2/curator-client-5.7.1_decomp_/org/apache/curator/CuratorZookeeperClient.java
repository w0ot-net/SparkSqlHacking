package org.apache.curator;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.drivers.TracerDriver;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.ensemble.fixed.FixedEnsembleProvider;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.DefaultTracerDriver;
import org.apache.curator.utils.DefaultZookeeperFactory;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorZookeeperClient implements Closeable {
   private final Logger log;
   private final ConnectionState state;
   private final AtomicReference retryPolicy;
   private final int connectionTimeoutMs;
   private final int waitForShutdownTimeoutMs;
   private final AtomicBoolean started;
   private final AtomicReference tracer;

   public CuratorZookeeperClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, Watcher watcher, RetryPolicy retryPolicy) {
      this(new DefaultZookeeperFactory(), new FixedEnsembleProvider(connectString), sessionTimeoutMs, connectionTimeoutMs, watcher, retryPolicy, false);
   }

   public CuratorZookeeperClient(EnsembleProvider ensembleProvider, int sessionTimeoutMs, int connectionTimeoutMs, Watcher watcher, RetryPolicy retryPolicy) {
      this(new DefaultZookeeperFactory(), ensembleProvider, sessionTimeoutMs, connectionTimeoutMs, watcher, retryPolicy, false);
   }

   public CuratorZookeeperClient(ZookeeperFactory zookeeperFactory, EnsembleProvider ensembleProvider, int sessionTimeoutMs, int connectionTimeoutMs, Watcher watcher, RetryPolicy retryPolicy, boolean canBeReadOnly) {
      this(zookeeperFactory, ensembleProvider, sessionTimeoutMs, connectionTimeoutMs, 0, watcher, retryPolicy, canBeReadOnly);
   }

   public CuratorZookeeperClient(ZookeeperFactory zookeeperFactory, EnsembleProvider ensembleProvider, int sessionTimeoutMs, int connectionTimeoutMs, int waitForShutdownTimeoutMs, Watcher watcher, RetryPolicy retryPolicy, boolean canBeReadOnly) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.retryPolicy = new AtomicReference();
      this.started = new AtomicBoolean(false);
      this.tracer = new AtomicReference(new DefaultTracerDriver());
      if (sessionTimeoutMs < connectionTimeoutMs) {
         this.log.warn(String.format("session timeout [%d] is less than connection timeout [%d]", sessionTimeoutMs, connectionTimeoutMs));
      }

      retryPolicy = (RetryPolicy)Preconditions.checkNotNull(retryPolicy, "retryPolicy cannot be null");
      ensembleProvider = (EnsembleProvider)Preconditions.checkNotNull(ensembleProvider, "ensembleProvider cannot be null");
      this.connectionTimeoutMs = connectionTimeoutMs;
      this.waitForShutdownTimeoutMs = waitForShutdownTimeoutMs;
      this.state = new ConnectionState(zookeeperFactory, ensembleProvider, sessionTimeoutMs, watcher, this.tracer, canBeReadOnly);
      this.setRetryPolicy(retryPolicy);
   }

   public ZooKeeper getZooKeeper() throws Exception {
      Preconditions.checkState(this.started.get(), "Client is not started");
      return this.state.getZooKeeper();
   }

   public RetryLoop newRetryLoop() {
      return new RetryLoopImpl((RetryPolicy)this.retryPolicy.get(), this.tracer);
   }

   public SessionFailRetryLoop newSessionFailRetryLoop(SessionFailRetryLoop.Mode mode) {
      return new SessionFailRetryLoop(this, mode);
   }

   public boolean isConnected() {
      return this.state.isConnected();
   }

   public boolean blockUntilConnectedOrTimedOut() throws InterruptedException {
      Preconditions.checkState(this.started.get(), "Client is not started");
      this.log.debug("blockUntilConnectedOrTimedOut() start");
      OperationTrace trace = this.startAdvancedTracer("blockUntilConnectedOrTimedOut");
      this.internalBlockUntilConnectedOrTimedOut();
      trace.commit();
      boolean localIsConnected = this.state.isConnected();
      this.log.debug("blockUntilConnectedOrTimedOut() end. isConnected: " + localIsConnected);
      return localIsConnected;
   }

   public void start() throws Exception {
      this.log.debug("Starting");
      if (!this.started.compareAndSet(false, true)) {
         throw new IllegalStateException("Already started");
      } else {
         this.state.start();
      }
   }

   public void close() {
      this.close(this.waitForShutdownTimeoutMs);
   }

   public void close(int waitForShutdownTimeoutMs) {
      this.log.debug("Closing, waitForShutdownTimeoutMs {}", waitForShutdownTimeoutMs);
      this.started.set(false);

      try {
         this.state.close(waitForShutdownTimeoutMs);
      } catch (IOException e) {
         ThreadUtils.checkInterrupted(e);
         this.log.error("", e);
      }

   }

   public void setRetryPolicy(RetryPolicy policy) {
      Preconditions.checkNotNull(policy, "policy cannot be null");
      this.retryPolicy.set(policy);
   }

   public RetryPolicy getRetryPolicy() {
      return (RetryPolicy)this.retryPolicy.get();
   }

   public TimeTrace startTracer(String name) {
      return new TimeTrace(name, (TracerDriver)this.tracer.get());
   }

   public OperationTrace startAdvancedTracer(String name) {
      return new OperationTrace(name, (TracerDriver)this.tracer.get(), this.state.getSessionId());
   }

   public TracerDriver getTracerDriver() {
      return (TracerDriver)this.tracer.get();
   }

   public void setTracerDriver(TracerDriver tracer) {
      this.tracer.set(tracer);
   }

   public String getCurrentConnectionString() {
      return this.state.getEnsembleProvider().getConnectionString();
   }

   public int getConnectionTimeoutMs() {
      return this.connectionTimeoutMs;
   }

   public void reset() throws Exception {
      this.state.reset();
   }

   public long getInstanceIndex() {
      return this.state.getInstanceIndex();
   }

   public int getLastNegotiatedSessionTimeoutMs() {
      return this.state.getLastNegotiatedSessionTimeoutMs();
   }

   void addParentWatcher(Watcher watcher) {
      this.state.addParentWatcher(watcher);
   }

   void removeParentWatcher(Watcher watcher) {
      this.state.removeParentWatcher(watcher);
   }

   public void internalBlockUntilConnectedOrTimedOut() throws InterruptedException {
      long elapsed;
      for(long waitTimeMs = (long)this.connectionTimeoutMs; !this.state.isConnected() && waitTimeMs > 0L; waitTimeMs -= elapsed) {
         final CountDownLatch latch = new CountDownLatch(1);
         Watcher tempWatcher = new Watcher() {
            public void process(WatchedEvent event) {
               latch.countDown();
            }
         };
         this.state.addParentWatcher(tempWatcher);
         long startTimeMs = System.currentTimeMillis();
         long timeoutMs = Math.min(waitTimeMs, 1000L);

         try {
            latch.await(timeoutMs, TimeUnit.MILLISECONDS);
         } finally {
            this.state.removeParentWatcher(tempWatcher);
         }

         elapsed = Math.max(1L, System.currentTimeMillis() - startTimeMs);
      }

   }
}
