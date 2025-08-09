package org.apache.curator.framework.state;

import java.io.Closeable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.UnaryListenerManager;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionStateManager implements Closeable {
   private static final int QUEUE_SIZE;
   private final Logger log;
   private final BlockingQueue eventQueue;
   private final CuratorFramework client;
   private final int sessionTimeoutMs;
   private final int sessionExpirationPercent;
   private final AtomicBoolean initialConnectMessageSent;
   private final ExecutorService service;
   private final AtomicReference state;
   private final UnaryListenerManager listeners;
   private ConnectionState currentConnectionState;
   private volatile long startOfSuspendedEpoch;
   private volatile long lastExpiredInstanceIndex;

   public ConnectionStateManager(CuratorFramework client, ThreadFactory threadFactory, int sessionTimeoutMs, int sessionExpirationPercent) {
      this(client, threadFactory, sessionTimeoutMs, sessionExpirationPercent, ConnectionStateListenerManagerFactory.standard);
   }

   public ConnectionStateManager(CuratorFramework client, ThreadFactory threadFactory, int sessionTimeoutMs, int sessionExpirationPercent, ConnectionStateListenerManagerFactory managerFactory) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.eventQueue = new ArrayBlockingQueue(QUEUE_SIZE);
      this.initialConnectMessageSent = new AtomicBoolean(false);
      this.state = new AtomicReference(ConnectionStateManager.State.LATENT);
      this.startOfSuspendedEpoch = 0L;
      this.lastExpiredInstanceIndex = -1L;
      this.client = client;
      this.sessionTimeoutMs = sessionTimeoutMs;
      this.sessionExpirationPercent = sessionExpirationPercent;
      if (threadFactory == null) {
         threadFactory = ThreadUtils.newThreadFactory("ConnectionStateManager");
      }

      this.service = Executors.newSingleThreadExecutor(threadFactory);
      this.listeners = managerFactory.newManager(client);
   }

   public void start() {
      Preconditions.checkState(this.state.compareAndSet(ConnectionStateManager.State.LATENT, ConnectionStateManager.State.STARTED), "Cannot be started more than once");
      this.service.submit(new Callable() {
         public Object call() throws Exception {
            ConnectionStateManager.this.processEvents();
            return null;
         }
      });
   }

   public void close() {
      if (this.state.compareAndSet(ConnectionStateManager.State.STARTED, ConnectionStateManager.State.CLOSED)) {
         this.service.shutdownNow();
         this.listeners.clear();
      }

   }

   public Listenable getListenable() {
      return this.listeners;
   }

   public synchronized boolean setToSuspended() {
      if (this.state.get() != ConnectionStateManager.State.STARTED) {
         return false;
      } else if (this.currentConnectionState != ConnectionState.LOST && this.currentConnectionState != ConnectionState.SUSPENDED) {
         this.setCurrentConnectionState(ConnectionState.SUSPENDED);
         this.postState(ConnectionState.SUSPENDED);
         return true;
      } else {
         return false;
      }
   }

   public synchronized boolean addStateChange(ConnectionState newConnectionState) {
      if (this.state.get() != ConnectionStateManager.State.STARTED) {
         return false;
      } else {
         ConnectionState previousState = this.currentConnectionState;
         if (previousState == newConnectionState) {
            return false;
         } else {
            this.setCurrentConnectionState(newConnectionState);
            ConnectionState localState = newConnectionState;
            boolean isNegativeMessage = newConnectionState == ConnectionState.LOST || newConnectionState == ConnectionState.SUSPENDED || newConnectionState == ConnectionState.READ_ONLY;
            if (!isNegativeMessage && this.initialConnectMessageSent.compareAndSet(false, true)) {
               localState = ConnectionState.CONNECTED;
            }

            this.postState(localState);
            return true;
         }
      }
   }

   public synchronized boolean blockUntilConnected(int maxWaitTime, TimeUnit units) throws InterruptedException {
      long startTime = System.currentTimeMillis();
      boolean hasMaxWait = units != null;
      long maxWaitTimeMs = hasMaxWait ? TimeUnit.MILLISECONDS.convert((long)maxWaitTime, units) : 0L;

      while(!this.isConnected()) {
         if (hasMaxWait) {
            long waitTime = maxWaitTimeMs - (System.currentTimeMillis() - startTime);
            if (waitTime <= 0L) {
               return this.isConnected();
            }

            this.wait(waitTime);
         } else {
            this.wait();
         }
      }

      return this.isConnected();
   }

   public synchronized boolean isConnected() {
      return this.currentConnectionState != null && this.currentConnectionState.isConnected();
   }

   private void postState(ConnectionState state) {
      this.log.info("State change: " + state);
      this.notifyAll();

      while(!this.eventQueue.offer(state)) {
         this.eventQueue.poll();
         this.log.warn("ConnectionStateManager queue full - dropping events to make room");
      }

   }

   private void processEvents() {
      while(this.state.get() == ConnectionStateManager.State.STARTED) {
         try {
            int useSessionTimeoutMs = this.getUseSessionTimeoutMs();
            long elapsedMs = this.startOfSuspendedEpoch == 0L ? (long)(useSessionTimeoutMs / 2) : System.currentTimeMillis() - this.startOfSuspendedEpoch;
            long pollMaxMs = (long)useSessionTimeoutMs - elapsedMs;
            ConnectionState newState = (ConnectionState)this.eventQueue.poll(pollMaxMs, TimeUnit.MILLISECONDS);
            if (newState != null) {
               if (this.listeners.isEmpty()) {
                  this.log.warn("There are no ConnectionStateListeners registered.");
               }

               this.listeners.forEach((listener) -> listener.stateChanged(this.client, newState));
            } else if (this.sessionExpirationPercent > 0) {
               synchronized(this) {
                  this.checkSessionExpiration();
               }
            }

            synchronized(this) {
               if (this.currentConnectionState == ConnectionState.LOST && this.client.getZookeeperClient().isConnected()) {
                  this.log.warn("ConnectionState is LOST but isConnected() is true. Forcing RECONNECTED.");
                  this.addStateChange(ConnectionState.RECONNECTED);
               }
            }
         } catch (InterruptedException var12) {
         }
      }

   }

   private void checkSessionExpiration() {
      if (this.currentConnectionState == ConnectionState.SUSPENDED && this.startOfSuspendedEpoch != 0L) {
         long elapsedMs = System.currentTimeMillis() - this.startOfSuspendedEpoch;
         int useSessionTimeoutMs = this.getUseSessionTimeoutMs();
         if (elapsedMs >= (long)useSessionTimeoutMs) {
            this.startOfSuspendedEpoch = System.currentTimeMillis();
            this.log.warn(String.format("Session timeout has elapsed while SUSPENDED. Injecting a session expiration. Elapsed ms: %d. Adjusted session timeout ms: %d", elapsedMs, useSessionTimeoutMs));

            try {
               if (this.lastExpiredInstanceIndex == this.client.getZookeeperClient().getInstanceIndex()) {
                  this.client.getZookeeperClient().reset();
               } else {
                  this.lastExpiredInstanceIndex = this.client.getZookeeperClient().getInstanceIndex();
                  this.client.getZookeeperClient().getZooKeeper().getTestable().injectSessionExpiration();
               }
            } catch (Exception e) {
               this.log.error("Could not inject session expiration", e);
            }
         }
      } else if (this.currentConnectionState == ConnectionState.LOST) {
         try {
            this.client.getZookeeperClient().getZooKeeper();
         } catch (Exception e) {
            this.log.error("Could not get ZooKeeper", e);
         }
      }

   }

   private void setCurrentConnectionState(ConnectionState newConnectionState) {
      this.currentConnectionState = newConnectionState;
      this.startOfSuspendedEpoch = this.currentConnectionState == ConnectionState.SUSPENDED ? System.currentTimeMillis() : 0L;
   }

   private int getUseSessionTimeoutMs() {
      int lastNegotiatedSessionTimeoutMs = this.client.getZookeeperClient().getLastNegotiatedSessionTimeoutMs();
      int useSessionTimeoutMs = lastNegotiatedSessionTimeoutMs > 0 ? lastNegotiatedSessionTimeoutMs : this.sessionTimeoutMs;
      useSessionTimeoutMs = this.sessionExpirationPercent > 0 && this.startOfSuspendedEpoch != 0L ? useSessionTimeoutMs * this.sessionExpirationPercent / 100 : useSessionTimeoutMs;
      return useSessionTimeoutMs;
   }

   static {
      int size = 25;
      String property = System.getProperty("ConnectionStateManagerSize", (String)null);
      if (property != null) {
         try {
            size = Integer.parseInt(property);
         } catch (NumberFormatException var3) {
         }
      }

      QUEUE_SIZE = size;
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
