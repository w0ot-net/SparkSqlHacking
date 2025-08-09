package org.apache.curator;

import java.io.Closeable;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.drivers.EventTrace;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.drivers.TracerDriver;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ConnectionState implements Watcher, Closeable {
   private static final int MAX_BACKGROUND_EXCEPTIONS = 10;
   private static final boolean LOG_EVENTS = Boolean.getBoolean("curator-log-events");
   private static final Logger log = LoggerFactory.getLogger(ConnectionState.class);
   private final HandleHolder handleHolder;
   private final AtomicBoolean isConnected = new AtomicBoolean(false);
   private final AtomicInteger lastNegotiatedSessionTimeoutMs = new AtomicInteger(0);
   private final EnsembleProvider ensembleProvider;
   private final AtomicReference tracer;
   private final Queue backgroundExceptions = new ConcurrentLinkedQueue();
   private final Queue parentWatchers = new ConcurrentLinkedQueue();
   private final AtomicLong instanceIndex = new AtomicLong();
   private volatile long connectionStartMs = 0L;

   ConnectionState(ZookeeperFactory zookeeperFactory, EnsembleProvider ensembleProvider, int sessionTimeoutMs, Watcher parentWatcher, AtomicReference tracer, boolean canBeReadOnly) {
      this.ensembleProvider = ensembleProvider;
      this.tracer = tracer;
      if (parentWatcher != null) {
         this.parentWatchers.offer(parentWatcher);
      }

      this.handleHolder = new HandleHolder(zookeeperFactory, this, ensembleProvider, sessionTimeoutMs, canBeReadOnly);
   }

   ZooKeeper getZooKeeper() throws Exception {
      if (SessionFailRetryLoop.sessionForThreadHasFailed()) {
         throw new SessionFailRetryLoop.SessionFailedException();
      } else {
         Exception exception = (Exception)this.backgroundExceptions.poll();
         if (exception != null) {
            (new EventTrace("background-exceptions", (TracerDriver)this.tracer.get())).commit();
            throw exception;
         } else {
            boolean localIsConnected = this.isConnected.get();
            if (!localIsConnected) {
               this.checkNewConnectionString();
            }

            return this.handleHolder.getZooKeeper();
         }
      }
   }

   boolean isConnected() {
      return this.isConnected.get();
   }

   void start() throws Exception {
      log.debug("Starting");
      this.ensembleProvider.start();
      this.reset();
   }

   public void close() throws IOException {
      this.close(0);
   }

   public void close(int waitForShutdownTimeoutMs) throws IOException {
      log.debug("Closing");
      CloseableUtils.closeQuietly(this.ensembleProvider);

      try {
         this.handleHolder.closeAndClear(waitForShutdownTimeoutMs);
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         throw new IOException(e);
      } finally {
         this.isConnected.set(false);
      }

   }

   void addParentWatcher(Watcher watcher) {
      this.parentWatchers.offer(watcher);
   }

   void removeParentWatcher(Watcher watcher) {
      this.parentWatchers.remove(watcher);
   }

   long getInstanceIndex() {
      return this.instanceIndex.get();
   }

   int getLastNegotiatedSessionTimeoutMs() {
      return this.lastNegotiatedSessionTimeoutMs.get();
   }

   public void process(WatchedEvent event) {
      if (LOG_EVENTS) {
         log.debug("ConnectState watcher: " + event);
      }

      if (event.getType() == EventType.None) {
         boolean wasConnected = this.isConnected.get();
         boolean newIsConnected = this.checkState(event.getState(), wasConnected);
         if (newIsConnected != wasConnected) {
            this.isConnected.set(newIsConnected);
            this.connectionStartMs = System.currentTimeMillis();
            if (newIsConnected) {
               this.lastNegotiatedSessionTimeoutMs.set(this.handleHolder.getNegotiatedSessionTimeoutMs());
               log.debug("Negotiated session timeout: " + this.lastNegotiatedSessionTimeoutMs.get());
            }
         }
      }

      for(Watcher parentWatcher : this.parentWatchers) {
         OperationTrace trace = new OperationTrace("connection-state-parent-process", (TracerDriver)this.tracer.get(), this.getSessionId());
         parentWatcher.process(event);
         trace.commit();
      }

   }

   EnsembleProvider getEnsembleProvider() {
      return this.ensembleProvider;
   }

   synchronized void reset() throws Exception {
      log.debug("reset");
      this.instanceIndex.incrementAndGet();
      this.isConnected.set(false);
      this.connectionStartMs = System.currentTimeMillis();
      this.handleHolder.closeAndReset();
      this.handleHolder.getZooKeeper();
   }

   private synchronized void checkNewConnectionString() {
      String newConnectionString = this.handleHolder.getNewConnectionString();
      if (newConnectionString != null) {
         this.handleNewConnectionString(newConnectionString);
      }

   }

   public long getSessionId() {
      long sessionId = 0L;

      try {
         ZooKeeper zk = this.handleHolder.getZooKeeper();
         if (zk != null) {
            sessionId = zk.getSessionId();
         }
      } catch (Exception var4) {
      }

      return sessionId;
   }

   private boolean checkState(Watcher.Event.KeeperState state, boolean wasConnected) {
      boolean isConnected = wasConnected;
      boolean checkNewConnectionString = true;
      switch (state) {
         case Disconnected:
         default:
            isConnected = false;
            break;
         case SyncConnected:
         case ConnectedReadOnly:
            isConnected = true;
            break;
         case AuthFailed:
            isConnected = false;
            log.error("Authentication failed");
            break;
         case Expired:
            isConnected = false;
            checkNewConnectionString = false;
            this.handleExpiredSession();
         case SaslAuthenticated:
      }

      if (state != KeeperState.Expired) {
         (new EventTrace(state.toString(), (TracerDriver)this.tracer.get(), this.getSessionId())).commit();
      }

      if (checkNewConnectionString) {
         String newConnectionString = this.handleHolder.getNewConnectionString();
         if (newConnectionString != null) {
            this.handleNewConnectionString(newConnectionString);
         }
      }

      return isConnected;
   }

   private void handleNewConnectionString(String newConnectionString) {
      log.info("Connection string changed to: " + newConnectionString);
      (new EventTrace("connection-string-changed", (TracerDriver)this.tracer.get(), this.getSessionId())).commit();

      try {
         ZooKeeper zooKeeper = this.handleHolder.getZooKeeper();
         if (zooKeeper == null) {
            log.warn("Could not update the connection string because getZooKeeper() returned null.");
         } else if (this.ensembleProvider.updateServerListEnabled()) {
            zooKeeper.updateServerList(newConnectionString);
            this.handleHolder.resetConnectionString(newConnectionString);
         } else {
            this.reset();
         }
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.queueBackgroundException(e);
      }

   }

   private void handleExpiredSession() {
      log.warn("Session expired event received");
      (new EventTrace("session-expired", (TracerDriver)this.tracer.get(), this.getSessionId())).commit();

      try {
         this.reset();
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.queueBackgroundException(e);
      }

   }

   private void queueBackgroundException(Exception e) {
      while(this.backgroundExceptions.size() >= 10) {
         this.backgroundExceptions.poll();
      }

      this.backgroundExceptions.offer(e);
   }
}
