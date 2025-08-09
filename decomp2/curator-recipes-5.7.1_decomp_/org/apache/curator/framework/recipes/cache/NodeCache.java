package org.apache.curator.framework.recipes.cache;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.GetDataWatchBackgroundStatable;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class NodeCache implements Closeable {
   private final Logger log;
   private final WatcherRemoveCuratorFramework client;
   private final String path;
   private final boolean dataIsCompressed;
   private final AtomicReference data;
   private final AtomicReference state;
   private final StandardListenerManager listeners;
   private final AtomicBoolean isConnected;
   private ConnectionStateListener connectionStateListener;
   private Watcher watcher;
   private final BackgroundCallback backgroundCallback;
   @VisibleForTesting
   volatile Exchanger rebuildTestExchanger;

   public NodeCache(CuratorFramework client, String path) {
      this(client, path, false);
   }

   public NodeCache(CuratorFramework client, String path, boolean dataIsCompressed) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.data = new AtomicReference((Object)null);
      this.state = new AtomicReference(NodeCache.State.LATENT);
      this.listeners = StandardListenerManager.standard();
      this.isConnected = new AtomicBoolean(true);
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            if (newState != ConnectionState.CONNECTED && newState != ConnectionState.RECONNECTED) {
               NodeCache.this.isConnected.set(false);
            } else if (NodeCache.this.isConnected.compareAndSet(false, true)) {
               try {
                  NodeCache.this.reset();
               } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  NodeCache.this.log.error("Trying to reset after reconnection", e);
               }
            }

         }
      };
      this.watcher = new Watcher() {
         public void process(WatchedEvent event) {
            try {
               NodeCache.this.reset();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               NodeCache.this.handleException(e);
            }

         }
      };
      this.backgroundCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            NodeCache.this.processBackgroundResult(event);
         }
      };
      this.client = client.newWatcherRemoveCuratorFramework();
      this.path = PathUtils.validatePath(path);
      this.dataIsCompressed = dataIsCompressed;
   }

   public CuratorFramework getClient() {
      return this.client;
   }

   public void start() throws Exception {
      this.start(false);
   }

   public void start(boolean buildInitial) throws Exception {
      Preconditions.checkState(this.state.compareAndSet(NodeCache.State.LATENT, NodeCache.State.STARTED), "Cannot be started more than once");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      if (buildInitial) {
         this.client.checkExists().creatingParentContainersIfNeeded().forPath(this.path);
         this.internalRebuild();
      }

      this.reset();
   }

   public void close() throws IOException {
      if (this.state.compareAndSet(NodeCache.State.STARTED, NodeCache.State.CLOSED)) {
         this.client.removeWatchers();
         this.listeners.clear();
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
         this.connectionStateListener = null;
         this.watcher = null;
      }

   }

   public Listenable getListenable() {
      Preconditions.checkState(this.state.get() != NodeCache.State.CLOSED, "Closed");
      return this.listeners;
   }

   public void rebuild() throws Exception {
      Preconditions.checkState(this.state.get() == NodeCache.State.STARTED, "Not started");
      this.internalRebuild();
      this.reset();
   }

   public ChildData getCurrentData() {
      return (ChildData)this.data.get();
   }

   public String getPath() {
      return this.path;
   }

   private void reset() throws Exception {
      if (this.state.get() == NodeCache.State.STARTED && this.isConnected.get()) {
         ((ErrorListenerPathable)((BackgroundPathable)this.client.checkExists().creatingParentContainersIfNeeded().usingWatcher(this.watcher)).inBackground(this.backgroundCallback)).forPath(this.path);
      }

   }

   private void internalRebuild() throws Exception {
      try {
         Stat stat = new Stat();
         byte[] bytes = this.dataIsCompressed ? (byte[])((WatchPathable)((GetDataWatchBackgroundStatable)this.client.getData().decompressed()).storingStatIn(stat)).forPath(this.path) : (byte[])((WatchPathable)this.client.getData().storingStatIn(stat)).forPath(this.path);
         this.data.set(new ChildData(this.path, stat, bytes));
      } catch (KeeperException.NoNodeException var3) {
         this.data.set((Object)null);
      }

   }

   private void processBackgroundResult(CuratorEvent event) throws Exception {
      switch (event.getType()) {
         case GET_DATA:
            if (event.getResultCode() == Code.OK.intValue()) {
               ChildData childData = new ChildData(this.path, event.getStat(), event.getData());
               this.setNewData(childData);
            }
            break;
         case EXISTS:
            if (event.getResultCode() == Code.NONODE.intValue()) {
               this.setNewData((ChildData)null);
            } else if (event.getResultCode() == Code.OK.intValue()) {
               if (this.dataIsCompressed) {
                  ((ErrorListenerPathable)((BackgroundPathable)((GetDataWatchBackgroundStatable)this.client.getData().decompressed()).usingWatcher(this.watcher)).inBackground(this.backgroundCallback)).forPath(this.path);
               } else {
                  ((ErrorListenerPathable)((BackgroundPathable)this.client.getData().usingWatcher(this.watcher)).inBackground(this.backgroundCallback)).forPath(this.path);
               }
            }
      }

   }

   private void setNewData(ChildData newData) throws InterruptedException {
      ChildData previousData = (ChildData)this.data.getAndSet(newData);
      if (!Objects.equal(previousData, newData)) {
         this.listeners.forEach((listener) -> {
            try {
               listener.nodeChanged();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.log.error("Calling listener", e);
            }

         });
         if (this.rebuildTestExchanger != null) {
            try {
               this.rebuildTestExchanger.exchange(new Object());
            } catch (InterruptedException var4) {
               Thread.currentThread().interrupt();
            }
         }
      }

   }

   protected void handleException(Throwable e) {
      this.log.error("", e);
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
