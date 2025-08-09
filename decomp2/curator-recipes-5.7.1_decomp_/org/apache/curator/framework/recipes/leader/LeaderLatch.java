package org.apache.curator.framework.recipes.leader;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.ErrorListenerPathAndBytesable;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.recipes.AfterConnectionEstablished;
import org.apache.curator.framework.recipes.locks.LockInternals;
import org.apache.curator.framework.recipes.locks.LockInternalsSorter;
import org.apache.curator.framework.recipes.locks.StandardLockInternalsDriver;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderLatch implements Closeable {
   private final Logger log;
   private final WatcherRemoveCuratorFramework client;
   private final String latchPath;
   private final String id;
   private final AtomicReference state;
   private final AtomicBoolean hasLeadership;
   private final AtomicReference ourPath;
   private final AtomicReference lastPathIsLeader;
   private final StandardListenerManager listeners;
   private final CloseMode closeMode;
   private final AtomicReference startTask;
   private final ConnectionStateListener listener;
   private static final String LOCK_NAME = "latch-";
   private static final LockInternalsSorter sorter = new LockInternalsSorter() {
      public String fixForSorting(String str, String lockName) {
         return StandardLockInternalsDriver.standardFixForSorting(str, lockName);
      }
   };
   @VisibleForTesting
   volatile CountDownLatch debugResetWaitLatch;
   @VisibleForTesting
   volatile CountDownLatch debugResetWaitBeforeNodeDeleteLatch;
   @VisibleForTesting
   volatile CountDownLatch debugCheckLeaderShipLatch;

   public LeaderLatch(CuratorFramework client, String latchPath) {
      this(client, latchPath, "", LeaderLatch.CloseMode.SILENT);
   }

   public LeaderLatch(CuratorFramework client, String latchPath, String id) {
      this(client, latchPath, id, LeaderLatch.CloseMode.SILENT);
   }

   public LeaderLatch(CuratorFramework client, String latchPath, String id, CloseMode closeMode) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.state = new AtomicReference(LeaderLatch.State.LATENT);
      this.hasLeadership = new AtomicBoolean(false);
      this.ourPath = new AtomicReference();
      this.lastPathIsLeader = new AtomicReference();
      this.listeners = StandardListenerManager.standard();
      this.startTask = new AtomicReference();
      this.listener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            LeaderLatch.this.handleStateChange(newState);
         }
      };
      this.debugResetWaitLatch = null;
      this.debugResetWaitBeforeNodeDeleteLatch = null;
      this.debugCheckLeaderShipLatch = null;
      this.client = ((CuratorFramework)Preconditions.checkNotNull(client, "client cannot be null")).newWatcherRemoveCuratorFramework();
      this.latchPath = PathUtils.validatePath(latchPath);
      this.id = (String)Preconditions.checkNotNull(id, "id cannot be null");
      this.closeMode = (CloseMode)Preconditions.checkNotNull(closeMode, "closeMode cannot be null");
   }

   public void start() throws Exception {
      Preconditions.checkState(this.state.compareAndSet(LeaderLatch.State.LATENT, LeaderLatch.State.STARTED), "Cannot be started more than once");
      this.startTask.set(AfterConnectionEstablished.execute(this.client, new Runnable() {
         public void run() {
            try {
               LeaderLatch.this.internalStart();
            } finally {
               LeaderLatch.this.startTask.set((Object)null);
            }

         }
      }));
   }

   public void close() throws IOException {
      this.close(this.closeMode);
   }

   @VisibleForTesting
   void closeOnDemand() throws IOException {
      this.internalClose(this.closeMode, false);
   }

   public void close(CloseMode closeMode) throws IOException {
      Preconditions.checkNotNull(closeMode, "closeMode cannot be null");
      this.internalClose(closeMode, true);
   }

   private synchronized void internalClose(CloseMode closeMode, boolean failOnClosed) throws IOException {
      if (!this.state.compareAndSet(LeaderLatch.State.STARTED, LeaderLatch.State.CLOSED)) {
         if (failOnClosed) {
            throw new IllegalStateException("Already closed or has not been started");
         }
      } else {
         this.cancelStartTask();
         boolean var7 = false;

         try {
            var7 = true;
            this.setNode((String)null);
            this.client.removeWatchers();
            var7 = false;
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            throw new IOException(e);
         } finally {
            if (var7) {
               this.client.getConnectionStateListenable().removeListener(this.listener);
               switch (closeMode) {
                  case NOTIFY_LEADER:
                     this.setLeadership(false);
                     this.listeners.clear();
                     break;
                  case SILENT:
                     this.listeners.clear();
                     this.setLeadership(false);
               }

            }
         }

         this.client.getConnectionStateListenable().removeListener(this.listener);
         switch (closeMode) {
            case NOTIFY_LEADER:
               this.setLeadership(false);
               this.listeners.clear();
               break;
            case SILENT:
               this.listeners.clear();
               this.setLeadership(false);
         }

      }
   }

   @VisibleForTesting
   protected boolean cancelStartTask() {
      Future<?> localStartTask = (Future)this.startTask.getAndSet((Object)null);
      if (localStartTask != null) {
         localStartTask.cancel(true);
         return true;
      } else {
         return false;
      }
   }

   public void addListener(LeaderLatchListener listener) {
      this.listeners.addListener(listener);
   }

   public void addListener(LeaderLatchListener listener, Executor executor) {
      this.listeners.addListener(listener, executor);
   }

   public void removeListener(LeaderLatchListener listener) {
      this.listeners.removeListener(listener);
   }

   public void await() throws InterruptedException, EOFException {
      synchronized(this) {
         while(this.state.get() == LeaderLatch.State.STARTED && !this.hasLeadership.get()) {
            this.wait();
         }
      }

      if (this.state.get() != LeaderLatch.State.STARTED) {
         throw new EOFException();
      }
   }

   public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
      long waitNanos = TimeUnit.NANOSECONDS.convert(timeout, unit);
      synchronized(this) {
         while(this.state.get() == LeaderLatch.State.STARTED) {
            if (this.hasLeadership()) {
               return true;
            }

            if (waitNanos <= 0L) {
               return false;
            }

            long startNanos = System.nanoTime();
            TimeUnit.NANOSECONDS.timedWait(this, waitNanos);
            long elapsed = System.nanoTime() - startNanos;
            waitNanos -= elapsed;
         }

         return false;
      }
   }

   public String getId() {
      return this.id;
   }

   public State getState() {
      return (State)this.state.get();
   }

   public Collection getParticipants() throws Exception {
      Collection<String> participantNodes = LockInternals.getParticipantNodes(this.client, this.latchPath, "latch-", sorter);
      return LeaderSelector.getParticipants(this.client, participantNodes);
   }

   public Participant getLeader() throws Exception {
      Collection<String> participantNodes = LockInternals.getParticipantNodes(this.client, this.latchPath, "latch-", sorter);
      return LeaderSelector.getLeader(this.client, participantNodes);
   }

   public boolean hasLeadership() {
      return this.state.get() == LeaderLatch.State.STARTED && this.hasLeadership.get();
   }

   public String getOurPath() {
      return (String)this.ourPath.get();
   }

   public String getLastPathIsLeader() {
      return (String)this.lastPathIsLeader.get();
   }

   @VisibleForTesting
   void reset() throws Exception {
      this.setLeadership(false);
      if (this.debugResetWaitBeforeNodeDeleteLatch != null) {
         this.debugResetWaitBeforeNodeDeleteLatch.await();
      }

      this.setNode((String)null);
      BackgroundCallback callback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (LeaderLatch.this.debugResetWaitLatch != null) {
               LeaderLatch.this.debugResetWaitLatch.await();
               LeaderLatch.this.debugResetWaitLatch = null;
            }

            if (event.getResultCode() == Code.OK.intValue()) {
               LeaderLatch.this.setNode(event.getName());
               if (LeaderLatch.this.state.get() == LeaderLatch.State.CLOSED) {
                  LeaderLatch.this.setNode((String)null);
               } else {
                  LeaderLatch.this.getChildren();
               }
            } else {
               LeaderLatch.this.log.error("getChildren() failed. rc = {}", event.getResultCode());
            }

         }
      };
      ((ErrorListenerPathAndBytesable)((ACLBackgroundPathAndBytesable)this.client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)).inBackground(callback)).forPath(ZKPaths.makePath(this.latchPath, "latch-"), LeaderSelector.getIdBytes(this.id));
   }

   private synchronized void internalStart() {
      if (this.state.get() == LeaderLatch.State.STARTED) {
         this.client.getConnectionStateListenable().addListener(this.listener);

         try {
            this.reset();
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.log.error("An error occurred checking resetting leadership.", e);
         }
      }

   }

   private void checkLeadership(List children) throws Exception {
      if (this.debugCheckLeaderShipLatch != null) {
         this.debugCheckLeaderShipLatch.await();
      }

      String localOurPath = (String)this.ourPath.get();
      List<String> sortedChildren = LockInternals.getSortedChildren("latch-", sorter, children);
      int ourIndex = localOurPath != null ? sortedChildren.indexOf(ZKPaths.getNodeFromPath(localOurPath)) : -1;
      this.log.debug("checkLeadership with id: {}, ourPath: {}, children: {}", new Object[]{this.id, localOurPath, sortedChildren});
      if (ourIndex < 0) {
         this.log.error("Can't find our node. Resetting. Index: {}", ourIndex);
         this.reset();
      } else if (ourIndex == 0) {
         ((ErrorListenerPathable)this.client.getData().inBackground((client, event) -> {
            long ephemeralOwner = event.getStat() != null ? event.getStat().getEphemeralOwner() : -1L;
            long thisSessionId = client.getZookeeperClient().getZooKeeper().getSessionId();
            if (ephemeralOwner != thisSessionId) {
               this.reset();
            } else {
               this.lastPathIsLeader.set(localOurPath);
               this.setLeadership(true);
            }

         })).forPath(localOurPath);
      } else {
         this.setLeadership(false);
         String watchPath = (String)sortedChildren.get(ourIndex - 1);
         Watcher watcher = new Watcher() {
            public void process(WatchedEvent event) {
               if (LeaderLatch.this.state.get() == LeaderLatch.State.STARTED && event.getType() == EventType.NodeDeleted) {
                  try {
                     LeaderLatch.this.getChildren();
                  } catch (Exception ex) {
                     ThreadUtils.checkInterrupted(ex);
                     LeaderLatch.this.log.error("An error occurred checking the leadership.", ex);
                  }
               }

            }
         };
         BackgroundCallback callback = new BackgroundCallback() {
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
               if (event.getResultCode() == Code.NONODE.intValue()) {
                  LeaderLatch.this.getChildren();
               }

            }
         };
         ((ErrorListenerPathable)((BackgroundPathable)this.client.getData().usingWatcher(watcher)).inBackground(callback)).forPath(ZKPaths.makePath(this.latchPath, watchPath));
      }
   }

   private void getChildren() throws Exception {
      BackgroundCallback callback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (event.getResultCode() == Code.OK.intValue()) {
               LeaderLatch.this.checkLeadership(event.getChildren());
            }

         }
      };
      ((ErrorListenerPathable)this.client.getChildren().inBackground(callback)).forPath(ZKPaths.makePath(this.latchPath, (String)null));
   }

   @VisibleForTesting
   protected void handleStateChange(ConnectionState newState) {
      switch (newState) {
         case RECONNECTED:
            try {
               if (this.client.getConnectionStateErrorPolicy().isErrorState(ConnectionState.SUSPENDED) || !this.hasLeadership.get()) {
                  this.getChildren();
               }
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.log.error("Could not reset leader latch", e);
               this.setLeadership(false);
            }
            break;
         case SUSPENDED:
            if (this.client.getConnectionStateErrorPolicy().isErrorState(ConnectionState.SUSPENDED)) {
               this.setLeadership(false);
            }
            break;
         case LOST:
            this.setLeadership(false);
      }

   }

   private synchronized void setLeadership(boolean newValue) {
      boolean oldValue = this.hasLeadership.getAndSet(newValue);
      if (oldValue && !newValue) {
         this.listeners.forEach(LeaderLatchListener::notLeader);
      } else if (!oldValue && newValue) {
         this.listeners.forEach(LeaderLatchListener::isLeader);
      }

      this.notifyAll();
   }

   private void setNode(String newValue) throws Exception {
      String oldPath = (String)this.ourPath.getAndSet(newValue);
      this.log.debug("setNode with id: {}, oldPath: {}, newValue: {}", new Object[]{this.id, oldPath, newValue});
      if (oldPath != null) {
         ((ErrorListenerPathable)((ChildrenDeletable)this.client.delete().guaranteed()).inBackground()).forPath(oldPath);
      }

   }

   public static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }

   public static enum CloseMode {
      SILENT,
      NOTIFY_LEADER;
   }
}
