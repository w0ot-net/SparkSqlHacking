package org.apache.curator.framework.recipes.cache;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.EnsureContainers;
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
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.utils.CloseableExecutorService;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class PathChildrenCache implements Closeable {
   private final Logger log;
   private final WatcherRemoveCuratorFramework client;
   private final String path;
   private final CloseableExecutorService executorService;
   private final boolean cacheData;
   private final boolean dataIsCompressed;
   private final StandardListenerManager listeners;
   private final ConcurrentMap currentData;
   private final AtomicReference initialSet;
   private final Set operationsQuantizer;
   private final AtomicReference state;
   private final EnsureContainers ensureContainers;
   private static final ChildData NULL_CHILD_DATA = new ChildData("/", (Stat)null, (byte[])null);
   private static final boolean USE_EXISTS = Boolean.getBoolean("curator-path-children-cache-use-exists");
   private volatile Watcher childrenWatcher;
   private volatile Watcher dataWatcher;
   @VisibleForTesting
   volatile Exchanger rebuildTestExchanger;
   private volatile ConnectionStateListener connectionStateListener;
   public static final Supplier defaultThreadFactorySupplier = () -> ThreadUtils.newThreadFactory("PathChildrenCache");

   /** @deprecated */
   @Deprecated
   public PathChildrenCache(CuratorFramework client, String path, PathChildrenCacheMode mode) {
      this(client, path, mode != PathChildrenCacheMode.CACHE_PATHS_ONLY, false, new CloseableExecutorService(Executors.newSingleThreadExecutor((ThreadFactory)defaultThreadFactorySupplier.get()), true));
   }

   /** @deprecated */
   @Deprecated
   public PathChildrenCache(CuratorFramework client, String path, PathChildrenCacheMode mode, ThreadFactory threadFactory) {
      this(client, path, mode != PathChildrenCacheMode.CACHE_PATHS_ONLY, false, new CloseableExecutorService(Executors.newSingleThreadExecutor(threadFactory), true));
   }

   public PathChildrenCache(CuratorFramework client, String path, boolean cacheData) {
      this(client, path, cacheData, false, new CloseableExecutorService(Executors.newSingleThreadExecutor((ThreadFactory)defaultThreadFactorySupplier.get()), true));
   }

   public PathChildrenCache(CuratorFramework client, String path, boolean cacheData, ThreadFactory threadFactory) {
      this(client, path, cacheData, false, new CloseableExecutorService(Executors.newSingleThreadExecutor(threadFactory), true));
   }

   public PathChildrenCache(CuratorFramework client, String path, boolean cacheData, boolean dataIsCompressed, ThreadFactory threadFactory) {
      this(client, path, cacheData, dataIsCompressed, new CloseableExecutorService(Executors.newSingleThreadExecutor(threadFactory), true));
   }

   public PathChildrenCache(CuratorFramework client, String path, boolean cacheData, boolean dataIsCompressed, ExecutorService executorService) {
      this(client, path, cacheData, dataIsCompressed, new CloseableExecutorService(executorService));
   }

   public PathChildrenCache(CuratorFramework client, String path, boolean cacheData, boolean dataIsCompressed, CloseableExecutorService executorService) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.listeners = StandardListenerManager.standard();
      this.currentData = Maps.newConcurrentMap();
      this.initialSet = new AtomicReference();
      this.operationsQuantizer = Sets.newSetFromMap(Maps.newConcurrentMap());
      this.state = new AtomicReference(PathChildrenCache.State.LATENT);
      this.childrenWatcher = new Watcher() {
         public void process(WatchedEvent event) {
            PathChildrenCache.this.offerOperation(new RefreshOperation(PathChildrenCache.this, PathChildrenCache.RefreshMode.STANDARD));
         }
      };
      this.dataWatcher = new Watcher() {
         public void process(WatchedEvent event) {
            try {
               if (event.getType() == EventType.NodeDeleted) {
                  PathChildrenCache.this.remove(event.getPath());
               } else if (event.getType() == EventType.NodeDataChanged) {
                  PathChildrenCache.this.offerOperation(new GetDataOperation(PathChildrenCache.this, event.getPath()));
               }
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               PathChildrenCache.this.handleException(e);
            }

         }
      };
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            PathChildrenCache.this.handleStateChange(newState);
         }
      };
      this.client = client.newWatcherRemoveCuratorFramework();
      this.path = PathUtils.validatePath(path);
      this.cacheData = cacheData;
      this.dataIsCompressed = dataIsCompressed;
      this.executorService = executorService;
      this.ensureContainers = new EnsureContainers(client, path);
   }

   public void start() throws Exception {
      this.start(PathChildrenCache.StartMode.NORMAL);
   }

   /** @deprecated */
   @Deprecated
   public void start(boolean buildInitial) throws Exception {
      this.start(buildInitial ? PathChildrenCache.StartMode.BUILD_INITIAL_CACHE : PathChildrenCache.StartMode.NORMAL);
   }

   public void start(StartMode mode) throws Exception {
      Preconditions.checkState(this.state.compareAndSet(PathChildrenCache.State.LATENT, PathChildrenCache.State.STARTED), "already started");
      mode = (StartMode)Preconditions.checkNotNull(mode, "mode cannot be null");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      switch (mode) {
         case NORMAL:
            this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.STANDARD));
            break;
         case BUILD_INITIAL_CACHE:
            this.rebuild();
            break;
         case POST_INITIALIZED_EVENT:
            this.initialSet.set(Maps.newConcurrentMap());
            this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.POST_INITIALIZED));
      }

   }

   public void rebuild() throws Exception {
      Preconditions.checkState(this.state.get() == PathChildrenCache.State.STARTED, "cache has been closed");
      this.ensurePath();
      this.clear();

      for(String child : (List)this.client.getChildren().forPath(this.path)) {
         String fullPath = ZKPaths.makePath(this.path, child);
         this.internalRebuildNode(fullPath);
         if (this.rebuildTestExchanger != null) {
            this.rebuildTestExchanger.exchange(new Object());
         }
      }

      this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.FORCE_GET_DATA_AND_STAT));
   }

   public void rebuildNode(String fullPath) throws Exception {
      Preconditions.checkArgument(ZKPaths.getPathAndNode(fullPath).getPath().equals(this.path), "Node is not part of this cache: " + fullPath);
      Preconditions.checkState(this.state.get() == PathChildrenCache.State.STARTED, "cache has been closed");
      this.ensurePath();
      this.internalRebuildNode(fullPath);
      this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.FORCE_GET_DATA_AND_STAT));
   }

   public void close() throws IOException {
      if (this.state.compareAndSet(PathChildrenCache.State.STARTED, PathChildrenCache.State.CLOSED)) {
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
         this.listeners.clear();
         this.executorService.close();
         this.client.removeWatchers();
         this.connectionStateListener = null;
         this.childrenWatcher = null;
         this.dataWatcher = null;
      }

   }

   public Listenable getListenable() {
      return this.listeners;
   }

   public List getCurrentData() {
      return ImmutableList.copyOf(Sets.newTreeSet(this.currentData.values()));
   }

   public ChildData getCurrentData(String fullPath) {
      return (ChildData)this.currentData.get(fullPath);
   }

   public void clearDataBytes(String fullPath) {
      this.clearDataBytes(fullPath, -1);
   }

   public boolean clearDataBytes(String fullPath, int ifVersion) {
      ChildData data = (ChildData)this.currentData.get(fullPath);
      if (data != null && (ifVersion < 0 || ifVersion == data.getStat().getVersion())) {
         if (data.getData() != null) {
            this.currentData.replace(fullPath, data, new ChildData(data.getPath(), data.getStat(), (byte[])null));
         }

         return true;
      } else {
         return false;
      }
   }

   public void clearAndRefresh() throws Exception {
      this.currentData.clear();
      this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.STANDARD));
   }

   public void clear() {
      this.currentData.clear();
   }

   void refresh(final RefreshMode mode) throws Exception {
      this.ensurePath();
      BackgroundCallback callback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (!PathChildrenCache.this.reRemoveWatchersOnBackgroundClosed()) {
               if (event.getResultCode() == Code.OK.intValue()) {
                  PathChildrenCache.this.processChildren(event.getChildren(), mode);
               } else if (event.getResultCode() == Code.NONODE.intValue()) {
                  if (mode == PathChildrenCache.RefreshMode.NO_NODE_EXCEPTION) {
                     PathChildrenCache.this.log.debug("KeeperException.NoNodeException received for getChildren() and refresh has failed. Resetting ensureContainers but not refreshing. Path: [{}]", PathChildrenCache.this.path);
                     PathChildrenCache.this.ensureContainers.reset();
                  } else {
                     PathChildrenCache.this.log.debug("KeeperException.NoNodeException received for getChildren(). Resetting ensureContainers. Path: [{}]", PathChildrenCache.this.path);
                     PathChildrenCache.this.ensureContainers.reset();
                     PathChildrenCache.this.offerOperation(new RefreshOperation(PathChildrenCache.this, PathChildrenCache.RefreshMode.NO_NODE_EXCEPTION));
                  }
               }

            }
         }
      };
      ((ErrorListenerPathable)((BackgroundPathable)this.client.getChildren().usingWatcher(this.childrenWatcher)).inBackground(callback)).forPath(this.path);
   }

   void callListeners(PathChildrenCacheEvent event) {
      this.listeners.forEach((listener) -> {
         try {
            listener.childEvent(this.client, event);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.handleException(e);
         }

      });
   }

   void getDataAndStat(final String fullPath) throws Exception {
      BackgroundCallback callback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (!PathChildrenCache.this.reRemoveWatchersOnBackgroundClosed()) {
               PathChildrenCache.this.applyNewData(fullPath, event.getResultCode(), event.getStat(), PathChildrenCache.this.cacheData ? event.getData() : null);
            }
         }
      };
      if (USE_EXISTS && !this.cacheData) {
         ((ErrorListenerPathable)((BackgroundPathable)this.client.checkExists().usingWatcher(this.dataWatcher)).inBackground(callback)).forPath(fullPath);
      } else if (this.dataIsCompressed && this.cacheData) {
         ((ErrorListenerPathable)((BackgroundPathable)((GetDataWatchBackgroundStatable)this.client.getData().decompressed()).usingWatcher(this.dataWatcher)).inBackground(callback)).forPath(fullPath);
      } else {
         ((ErrorListenerPathable)((BackgroundPathable)this.client.getData().usingWatcher(this.dataWatcher)).inBackground(callback)).forPath(fullPath);
      }

   }

   protected void handleException(Throwable e) {
      this.log.error("", e);
   }

   protected void ensurePath() throws Exception {
      this.ensureContainers.ensure();
   }

   @VisibleForTesting
   protected void remove(String fullPath) {
      ChildData data = (ChildData)this.currentData.remove(fullPath);
      if (data != null) {
         this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_REMOVED, data)));
      }

      Map<String, ChildData> localInitialSet = (Map)this.initialSet.get();
      if (localInitialSet != null) {
         localInitialSet.remove(ZKPaths.getNodeFromPath(fullPath));
         this.maybeOfferInitializedEvent(localInitialSet);
      }

   }

   private boolean reRemoveWatchersOnBackgroundClosed() {
      if (((State)this.state.get()).equals(PathChildrenCache.State.CLOSED)) {
         this.client.removeWatchers();
         return true;
      } else {
         return false;
      }
   }

   private void internalRebuildNode(String fullPath) throws Exception {
      if (this.cacheData) {
         try {
            Stat stat = new Stat();
            byte[] bytes = this.dataIsCompressed ? (byte[])((WatchPathable)((GetDataWatchBackgroundStatable)this.client.getData().decompressed()).storingStatIn(stat)).forPath(fullPath) : (byte[])((WatchPathable)this.client.getData().storingStatIn(stat)).forPath(fullPath);
            this.currentData.put(fullPath, new ChildData(fullPath, stat, bytes));
         } catch (KeeperException.NoNodeException var4) {
            this.currentData.remove(fullPath);
         }
      } else {
         Stat stat = (Stat)this.client.checkExists().forPath(fullPath);
         if (stat != null) {
            this.currentData.put(fullPath, new ChildData(fullPath, stat, (byte[])null));
         } else {
            this.currentData.remove(fullPath);
         }
      }

   }

   private void handleStateChange(ConnectionState newState) {
      switch (newState) {
         case SUSPENDED:
            this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_SUSPENDED, (ChildData)null)));
            break;
         case LOST:
            this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_LOST, (ChildData)null)));
            break;
         case CONNECTED:
         case RECONNECTED:
            try {
               this.offerOperation(new RefreshOperation(this, PathChildrenCache.RefreshMode.FORCE_GET_DATA_AND_STAT));
               this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CONNECTION_RECONNECTED, (ChildData)null)));
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.handleException(e);
            }
      }

   }

   private void processChildren(List children, RefreshMode mode) throws Exception {
      Set<String> removedNodes = Sets.newHashSet(this.currentData.keySet());

      for(String child : children) {
         removedNodes.remove(ZKPaths.makePath(this.path, child));
      }

      for(String fullPath : removedNodes) {
         this.remove(fullPath);
      }

      for(String name : children) {
         String fullPath = ZKPaths.makePath(this.path, name);
         if (mode == PathChildrenCache.RefreshMode.FORCE_GET_DATA_AND_STAT || !this.currentData.containsKey(fullPath)) {
            this.getDataAndStat(fullPath);
         }

         this.updateInitialSet(name, NULL_CHILD_DATA);
      }

      this.maybeOfferInitializedEvent((Map)this.initialSet.get());
   }

   private void applyNewData(String fullPath, int resultCode, Stat stat, byte[] bytes) {
      if (resultCode == Code.OK.intValue()) {
         ChildData data = new ChildData(fullPath, stat, bytes);
         ChildData previousData = (ChildData)this.currentData.put(fullPath, data);
         if (previousData == null) {
            this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_ADDED, data)));
         } else if (stat.getMzxid() != previousData.getStat().getMzxid()) {
            this.offerOperation(new EventOperation(this, new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_UPDATED, data)));
         }

         this.updateInitialSet(ZKPaths.getNodeFromPath(fullPath), data);
      } else if (resultCode == Code.NONODE.intValue()) {
         this.log.debug("NoNode at path {}, removing child from initialSet", fullPath);
         this.remove(fullPath);
      }

   }

   private void updateInitialSet(String name, ChildData data) {
      Map<String, ChildData> localInitialSet = (Map)this.initialSet.get();
      if (localInitialSet != null) {
         localInitialSet.put(name, data);
         this.maybeOfferInitializedEvent(localInitialSet);
      }

   }

   private void maybeOfferInitializedEvent(Map localInitialSet) {
      if (!this.hasUninitialized(localInitialSet) && this.initialSet.getAndSet((Object)null) != null) {
         final List<ChildData> children = ImmutableList.copyOf(localInitialSet.values());
         PathChildrenCacheEvent event = new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.INITIALIZED, (ChildData)null) {
            public List getInitialData() {
               return children;
            }
         };
         this.offerOperation(new EventOperation(this, event));
      }

   }

   private boolean hasUninitialized(Map localInitialSet) {
      if (localInitialSet == null) {
         return false;
      } else {
         Map<String, ChildData> uninitializedChildren = Maps.filterValues(localInitialSet, new Predicate() {
            public boolean apply(ChildData input) {
               return input == PathChildrenCache.NULL_CHILD_DATA;
            }
         });
         return uninitializedChildren.size() != 0;
      }
   }

   void offerOperation(final Operation operation) {
      if (this.operationsQuantizer.add(operation)) {
         this.submitToExecutor(new Runnable() {
            public void run() {
               try {
                  PathChildrenCache.this.operationsQuantizer.remove(operation);
                  operation.invoke();
               } catch (InterruptedException e) {
                  if (PathChildrenCache.this.state.get() != PathChildrenCache.State.CLOSED) {
                     PathChildrenCache.this.handleException(e);
                  }

                  Thread.currentThread().interrupt();
               } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  PathChildrenCache.this.handleException(e);
               }

            }
         });
      }

   }

   private synchronized void submitToExecutor(Runnable command) {
      if (this.state.get() == PathChildrenCache.State.STARTED) {
         this.executorService.submit(command);
      }

   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }

   public static enum StartMode {
      NORMAL,
      BUILD_INITIAL_CACHE,
      POST_INITIALIZED_EVENT;
   }

   static enum RefreshMode {
      STANDARD,
      FORCE_GET_DATA_AND_STAT,
      POST_INITIALIZED,
      NO_NODE_EXCEPTION;
   }
}
