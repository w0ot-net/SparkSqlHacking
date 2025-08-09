package org.apache.curator.framework.recipes.nodes;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CreateBuilderMain;
import org.apache.curator.framework.api.CreateModable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathAndBytesable;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistentNode implements Closeable {
   private final AtomicReference initialCreateLatch;
   private final Logger log;
   private final WatcherRemoveCuratorFramework client;
   private final AtomicReference nodePath;
   private final String basePath;
   private final CreateMode mode;
   private final long ttl;
   private final AtomicReference data;
   private final AtomicReference state;
   private volatile boolean authFailure;
   private volatile boolean parentCreationFailure;
   private final BackgroundCallback backgroundCallback;
   private final boolean useProtection;
   private final boolean useParentCreation;
   private final AtomicReference createMethod;
   private final StandardListenerManager listeners;
   private final CuratorWatcher watcher;
   private final BackgroundCallback checkExistsCallback;
   private final BackgroundCallback setDataCallback;
   private final ConnectionStateListener connectionStateListener;
   @VisibleForTesting
   volatile CountDownLatch debugCreateNodeLatch;
   @VisibleForTesting
   final AtomicLong debugWaitMsForBackgroundBeforeClose;

   public PersistentNode(CuratorFramework givenClient, CreateMode mode, boolean useProtection, String basePath, byte[] initData) {
      this(givenClient, mode, useProtection, basePath, initData, -1L, true);
   }

   public PersistentNode(CuratorFramework givenClient, CreateMode mode, boolean useProtection, String basePath, byte[] initData, boolean useParentCreation) {
      this(givenClient, mode, useProtection, basePath, initData, -1L, useParentCreation);
   }

   public PersistentNode(CuratorFramework givenClient, CreateMode mode, boolean useProtection, String basePath, byte[] initData, long ttl, boolean useParentCreation) {
      this.initialCreateLatch = new AtomicReference(new CountDownLatch(1));
      this.log = LoggerFactory.getLogger(this.getClass());
      this.nodePath = new AtomicReference((Object)null);
      this.data = new AtomicReference();
      this.state = new AtomicReference(PersistentNode.State.LATENT);
      this.createMethod = new AtomicReference((Object)null);
      this.listeners = StandardListenerManager.standard();
      this.watcher = new CuratorWatcher() {
         public void process(WatchedEvent event) throws Exception {
            if (PersistentNode.this.isActive()) {
               if (event.getType() == EventType.NodeDeleted) {
                  PersistentNode.this.createNode();
               } else if (event.getType() == EventType.NodeDataChanged) {
                  PersistentNode.this.watchNode();
               }
            }

         }
      };
      this.checkExistsCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework dummy, CuratorEvent event) throws Exception {
            if (PersistentNode.this.isActive()) {
               if (event.getResultCode() == Code.NONODE.intValue()) {
                  PersistentNode.this.createNode();
               } else {
                  boolean isEphemeral = event.getStat().getEphemeralOwner() != 0L;
                  if (isEphemeral != PersistentNode.this.mode.isEphemeral()) {
                     PersistentNode.this.log.warn("Existing node ephemeral state doesn't match requested state. Maybe the node was created outside of PersistentNode? " + PersistentNode.this.basePath);
                  }
               }
            } else {
               PersistentNode.this.client.removeWatchers();
            }

         }
      };
      this.setDataCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework dummy, CuratorEvent event) throws Exception {
            if (event.getResultCode() == Code.OK.intValue()) {
               PersistentNode.this.initialisationComplete();
            } else if (event.getResultCode() == Code.NOAUTH.intValue()) {
               PersistentNode.this.log.warn("Client does not have authorisation to write node at path {}", event.getPath());
               PersistentNode.this.authFailure = true;
            }

         }
      };
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework dummy, ConnectionState newState) {
            if (newState == ConnectionState.RECONNECTED && PersistentNode.this.isActive()) {
               PersistentNode.this.createNode();
            }

         }
      };
      this.debugCreateNodeLatch = null;
      this.debugWaitMsForBackgroundBeforeClose = new AtomicLong(0L);
      this.useProtection = useProtection;
      this.useParentCreation = useParentCreation;
      this.client = ((CuratorFramework)Preconditions.checkNotNull(givenClient, "client cannot be null")).newWatcherRemoveCuratorFramework();
      this.basePath = PathUtils.validatePath(basePath);
      this.mode = (CreateMode)Preconditions.checkNotNull(mode, "mode cannot be null");
      this.ttl = ttl;
      byte[] data = (byte[])Preconditions.checkNotNull(initData, "data cannot be null");
      this.backgroundCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework dummy, CuratorEvent event) throws Exception {
            if (PersistentNode.this.isActive()) {
               PersistentNode.this.processBackgroundCallback(event);
            } else {
               PersistentNode.this.processBackgroundCallbackClosedState(event);
            }

         }
      };
      this.data.set(Arrays.copyOf(data, data.length));
   }

   private void processBackgroundCallbackClosedState(CuratorEvent event) {
      String path = null;
      if (event.getResultCode() == Code.NODEEXISTS.intValue()) {
         path = event.getPath();
      } else if (event.getResultCode() == Code.OK.intValue()) {
         path = event.getName();
      }

      if (path != null) {
         try {
            ((ErrorListenerPathable)((ChildrenDeletable)this.client.delete().guaranteed()).inBackground()).forPath(path);
         } catch (Exception e) {
            this.log.error("Could not delete node after close", e);
         }
      }

   }

   private void processBackgroundCallback(CuratorEvent event) throws Exception {
      String path = null;
      boolean nodeExists = false;
      if (event.getResultCode() == Code.NODEEXISTS.intValue()) {
         path = event.getPath();
         nodeExists = true;
      } else if (event.getResultCode() == Code.OK.intValue()) {
         path = event.getName();
      } else {
         if (event.getResultCode() == Code.NOAUTH.intValue()) {
            this.log.warn("Client does not have authorisation to create node at path {}", event.getPath());
            this.authFailure = true;
            return;
         }

         if (event.getResultCode() == Code.NONODE.intValue()) {
            this.log.warn("Client cannot create parent hierarchy for path {} with useParentCreation set to {}", event.getPath(), this.useParentCreation);
            this.parentCreationFailure = true;
            return;
         }
      }

      if (path != null) {
         this.authFailure = false;
         this.nodePath.set(path);
         this.watchNode();
         if (nodeExists) {
            ((ErrorListenerPathAndBytesable)this.client.setData().inBackground(this.setDataCallback)).forPath(this.getActualPath(), this.getData());
         } else {
            this.initialisationComplete();
            this.notifyListeners();
         }
      } else {
         this.createNode();
      }

   }

   private void initialisationComplete() {
      CountDownLatch localLatch = (CountDownLatch)this.initialCreateLatch.getAndSet((Object)null);
      if (localLatch != null) {
         localLatch.countDown();
      }

   }

   public void start() {
      Preconditions.checkState(this.state.compareAndSet(PersistentNode.State.LATENT, PersistentNode.State.STARTED), "Already started");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      this.createNode();
   }

   public boolean waitForInitialCreate(long timeout, TimeUnit unit) throws InterruptedException {
      Preconditions.checkState(this.state.get() == PersistentNode.State.STARTED, "Not started");
      CountDownLatch localLatch = (CountDownLatch)this.initialCreateLatch.get();
      return localLatch == null || localLatch.await(timeout, unit);
   }

   public void close() throws IOException {
      if (this.debugWaitMsForBackgroundBeforeClose.get() > 0L) {
         try {
            Thread.sleep(this.debugWaitMsForBackgroundBeforeClose.get());
         } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
         }
      }

      if (this.state.compareAndSet(PersistentNode.State.STARTED, PersistentNode.State.CLOSED)) {
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);

         try {
            this.deleteNode();
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            throw new IOException(e);
         }

         this.client.removeWatchers();
      }
   }

   public Listenable getListenable() {
      return this.listeners;
   }

   public String getActualPath() {
      return (String)this.nodePath.get();
   }

   public void setData(byte[] data) throws Exception {
      data = (byte[])Preconditions.checkNotNull(data, "data cannot be null");
      Preconditions.checkState(this.nodePath.get() != null, "initial create has not been processed. Call waitForInitialCreate() to ensure.");
      Preconditions.checkState(!this.parentCreationFailure, "Failed to create parent nodes.");
      this.data.set(Arrays.copyOf(data, data.length));
      if (this.isActive()) {
         ((ErrorListenerPathAndBytesable)this.client.setData().inBackground(this.setDataCallback)).forPath(this.getActualPath(), this.getData());
      }

   }

   public byte[] getData() {
      return (byte[])this.data.get();
   }

   protected void deleteNode() throws Exception {
      String localNodePath = (String)this.nodePath.getAndSet((Object)null);
      if (localNodePath != null) {
         try {
            ((ChildrenDeletable)this.client.delete().guaranteed()).forPath(localNodePath);
         } catch (KeeperException.NoNodeException var3) {
         }
      }

   }

   private void createNode() {
      if (this.isActive()) {
         if (this.debugCreateNodeLatch != null) {
            try {
               this.debugCreateNodeLatch.await();
            } catch (InterruptedException var6) {
               Thread.currentThread().interrupt();
               return;
            }
         }

         try {
            String existingPath = (String)this.nodePath.get();
            String createPath;
            if (existingPath != null && !this.useProtection) {
               createPath = existingPath;
            } else if (existingPath != null && this.mode.isSequential()) {
               createPath = this.basePath + ZKPaths.extractSequentialSuffix(existingPath);
            } else {
               createPath = this.basePath;
            }

            CreateModable<ACLBackgroundPathAndBytesable<String>> localCreateMethod = (CreateModable)this.createMethod.get();
            if (localCreateMethod == null) {
               CreateBuilderMain createBuilder = (CreateBuilderMain)(this.mode.isTTL() ? this.client.create().withTtl(this.ttl) : this.client.create());
               CreateModable<ACLBackgroundPathAndBytesable<String>> tempCreateMethod;
               if (this.useParentCreation) {
                  tempCreateMethod = (CreateModable<ACLBackgroundPathAndBytesable<String>>)(this.useProtection ? createBuilder.creatingParentContainersIfNeeded().withProtection() : createBuilder.creatingParentContainersIfNeeded());
               } else {
                  tempCreateMethod = (CreateModable<ACLBackgroundPathAndBytesable<String>>)(this.useProtection ? createBuilder.withProtection() : createBuilder);
               }

               this.createMethod.compareAndSet((Object)null, tempCreateMethod);
               localCreateMethod = (CreateModable)this.createMethod.get();
            }

            ((ErrorListenerPathAndBytesable)((ACLBackgroundPathAndBytesable)localCreateMethod.withMode(this.getCreateMode(existingPath != null))).inBackground(this.backgroundCallback)).forPath(createPath, (byte[])this.data.get());
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            throw new RuntimeException("Creating node. BasePath: " + this.basePath, e);
         }
      }
   }

   private CreateMode getCreateMode(boolean pathIsSet) {
      if (pathIsSet) {
         switch (this.mode) {
            case EPHEMERAL_SEQUENTIAL:
               return CreateMode.EPHEMERAL;
            case PERSISTENT_SEQUENTIAL:
               return CreateMode.PERSISTENT;
            case PERSISTENT_SEQUENTIAL_WITH_TTL:
               return CreateMode.PERSISTENT_WITH_TTL;
         }
      }

      return this.mode;
   }

   private void watchNode() throws Exception {
      if (this.isActive()) {
         String localNodePath = (String)this.nodePath.get();
         if (localNodePath != null) {
            ((ErrorListenerPathable)((BackgroundPathable)this.client.checkExists().usingWatcher(this.watcher)).inBackground(this.checkExistsCallback)).forPath(localNodePath);
         }

      }
   }

   private void notifyListeners() {
      String path = this.getActualPath();
      this.listeners.forEach((listener) -> {
         try {
            listener.nodeCreated(path);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.log.error("From PersistentNode listener", e);
         }

      });
   }

   private boolean isActive() {
      return this.state.get() == PersistentNode.State.STARTED;
   }

   @VisibleForTesting
   boolean isAuthFailure() {
      return this.authFailure;
   }

   @VisibleForTesting
   boolean isParentCreationFailure() {
      return this.parentCreationFailure;
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
