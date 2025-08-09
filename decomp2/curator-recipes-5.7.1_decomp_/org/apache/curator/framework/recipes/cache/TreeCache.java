package org.apache.curator.framework.recipes.cache;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.Supplier;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.Backgroundable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.Watchable;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class TreeCache implements Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(TreeCache.class);
   private final boolean createParentNodes;
   private final boolean disableZkWatches;
   private final TreeCacheSelector selector;
   private static final ChildData DEAD = new ChildData("/", (Stat)null, (byte[])null);
   private static final AtomicReferenceFieldUpdater childDataUpdater = AtomicReferenceFieldUpdater.newUpdater(TreeNode.class, ChildData.class, "childData");
   private static final AtomicReferenceFieldUpdater childrenUpdater = AtomicReferenceFieldUpdater.newUpdater(TreeNode.class, ConcurrentMap.class, "children");
   private final AtomicLong outstandingOps;
   private final AtomicBoolean isInitialized;
   private final TreeNode root;
   private final WatcherRemoveCuratorFramework client;
   private final ExecutorService executorService;
   private final boolean cacheData;
   private final boolean dataIsCompressed;
   private final int maxDepth;
   private final StandardListenerManager listeners;
   private final StandardListenerManager errorListeners;
   private final AtomicReference treeState;
   private final ConnectionStateListener connectionStateListener;
   private static final Supplier defaultThreadFactorySupplier = () -> ThreadUtils.newThreadFactory("TreeCache");

   public static Builder newBuilder(CuratorFramework client, String path) {
      return new Builder(client, path);
   }

   static boolean isLive(ChildData cd) {
      return cd != null && cd != DEAD;
   }

   public TreeCache(CuratorFramework client, String path) {
      this(client, path, true, false, Integer.MAX_VALUE, Executors.newSingleThreadExecutor((ThreadFactory)defaultThreadFactorySupplier.get()), false, false, new DefaultTreeCacheSelector());
   }

   TreeCache(CuratorFramework client, String path, boolean cacheData, boolean dataIsCompressed, int maxDepth, ExecutorService executorService, boolean createParentNodes, boolean disableZkWatches, TreeCacheSelector selector) {
      this.outstandingOps = new AtomicLong(0L);
      this.isInitialized = new AtomicBoolean(false);
      this.listeners = StandardListenerManager.standard();
      this.errorListeners = StandardListenerManager.standard();
      this.treeState = new AtomicReference(TreeCache.TreeState.LATENT);
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            TreeCache.this.handleStateChange(newState);
         }
      };
      this.createParentNodes = createParentNodes;
      this.selector = (TreeCacheSelector)Preconditions.checkNotNull(selector, "selector cannot be null");
      this.root = new TreeNode(PathUtils.validatePath(path), (TreeNode)null);
      Preconditions.checkNotNull(client, "client cannot be null");
      this.client = client.newWatcherRemoveCuratorFramework();
      this.cacheData = cacheData;
      this.dataIsCompressed = dataIsCompressed;
      this.maxDepth = maxDepth;
      this.disableZkWatches = disableZkWatches;
      this.executorService = (ExecutorService)Preconditions.checkNotNull(executorService, "executorService cannot be null");
   }

   public TreeCache start() throws Exception {
      Preconditions.checkState(this.treeState.compareAndSet(TreeCache.TreeState.LATENT, TreeCache.TreeState.STARTED), "already started");
      if (this.createParentNodes) {
         this.client.createContainers(this.root.path);
      }

      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      if (this.client.getZookeeperClient().isConnected()) {
         this.root.wasCreated();
      }

      return this;
   }

   public void close() {
      if (this.treeState.compareAndSet(TreeCache.TreeState.STARTED, TreeCache.TreeState.CLOSED)) {
         this.client.removeWatchers();
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
         this.listeners.clear();
         this.executorService.shutdown();

         try {
            this.root.wasDeleted();
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.handleException(e);
         }
      }

   }

   public Listenable getListenable() {
      return this.listeners;
   }

   @VisibleForTesting
   public Listenable getUnhandledErrorListenable() {
      return this.errorListeners;
   }

   private TreeNode find(String findPath) {
      PathUtils.validatePath(findPath);
      LinkedList<String> rootElements = new LinkedList(ZKPaths.split(this.root.path));
      LinkedList<String> findElements = new LinkedList(ZKPaths.split(findPath));

      while(!rootElements.isEmpty()) {
         if (findElements.isEmpty()) {
            return null;
         }

         String nextRoot = (String)rootElements.removeFirst();
         String nextFind = (String)findElements.removeFirst();
         if (!nextFind.equals(nextRoot)) {
            return null;
         }
      }

      TreeNode current = this.root;

      while(!findElements.isEmpty()) {
         String nextFind = (String)findElements.removeFirst();
         ConcurrentMap<String, TreeNode> map = current.children;
         if (map == null) {
            return null;
         }

         current = (TreeNode)map.get(nextFind);
         if (current == null) {
            return null;
         }
      }

      return current;
   }

   public Map getCurrentChildren(String fullPath) {
      TreeNode node = this.find(fullPath);
      if (node != null && isLive(node.childData)) {
         ConcurrentMap<String, TreeNode> map = node.children;
         Map<String, ChildData> result;
         if (map == null) {
            result = ImmutableMap.of();
         } else {
            ImmutableMap.Builder<String, ChildData> builder = ImmutableMap.builder();

            for(Map.Entry entry : map.entrySet()) {
               ChildData childData = ((TreeNode)entry.getValue()).childData;
               if (isLive(childData)) {
                  builder.put(entry.getKey(), childData);
               }
            }

            result = builder.build();
         }

         return isLive(node.childData) ? result : null;
      } else {
         return null;
      }
   }

   public ChildData getCurrentData(String fullPath) {
      TreeNode node = this.find(fullPath);
      if (node == null) {
         return null;
      } else {
         ChildData result = node.childData;
         return isLive(result) ? result : null;
      }
   }

   public Iterator iterator() {
      return new TreeCacheIterator(this.root);
   }

   public int size() {
      return this.size(this.root);
   }

   private int size(TreeNode node) {
      int size;
      if (isLive(node.childData)) {
         size = 1;
         if (node.children != null) {
            for(TreeNode child : node.children.values()) {
               size += this.size(child);
            }
         }
      } else {
         size = 0;
      }

      return size;
   }

   private void callListeners(TreeCacheEvent event) {
      this.listeners.forEach((listener) -> {
         try {
            listener.childEvent(this.client, event);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.handleException(e);
         }

      });
   }

   private void handleException(Throwable e) {
      if (this.errorListeners.size() == 0) {
         LOG.error("", e);
      } else {
         this.errorListeners.forEach((listener) -> {
            try {
               listener.unhandledError("", e);
            } catch (Exception e2) {
               ThreadUtils.checkInterrupted(e2);
               LOG.error("Exception handling exception", e2);
            }

         });
      }

   }

   private void handleStateChange(ConnectionState newState) {
      switch (newState) {
         case SUSPENDED:
            this.publishEvent(TreeCacheEvent.Type.CONNECTION_SUSPENDED);
            break;
         case LOST:
            this.isInitialized.set(false);
            this.publishEvent(TreeCacheEvent.Type.CONNECTION_LOST);
            break;
         case CONNECTED:
            try {
               this.root.wasCreated();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.handleException(e);
            }
            break;
         case RECONNECTED:
            try {
               this.root.wasReconnected();
               this.publishEvent(TreeCacheEvent.Type.CONNECTION_RECONNECTED);
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.handleException(e);
            }
      }

   }

   private void publishEvent(TreeCacheEvent.Type type) {
      this.publishEvent(new TreeCacheEvent(type, (ChildData)null));
   }

   private void publishEvent(TreeCacheEvent.Type type, ChildData data, ChildData oldData) {
      this.publishEvent(new TreeCacheEvent(type, data, oldData));
   }

   private void publishEvent(final TreeCacheEvent event) {
      if (this.treeState.get() != TreeCache.TreeState.CLOSED) {
         LOG.debug("publishEvent: {}", event);
         this.executorService.submit(new Runnable() {
            public void run() {
               try {
                  TreeCache.this.callListeners(event);
               } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  TreeCache.this.handleException(e);
               }

            }
         });
      }

   }

   public static final class Builder {
      private final CuratorFramework client;
      private final String path;
      private boolean cacheData;
      private boolean dataIsCompressed;
      private ExecutorService executorService;
      private int maxDepth;
      private boolean createParentNodes;
      private boolean disableZkWatches;
      private TreeCacheSelector selector;

      private Builder(CuratorFramework client, String path) {
         this.cacheData = true;
         this.dataIsCompressed = false;
         this.executorService = null;
         this.maxDepth = Integer.MAX_VALUE;
         this.createParentNodes = false;
         this.disableZkWatches = false;
         this.selector = new DefaultTreeCacheSelector();
         this.client = (CuratorFramework)Preconditions.checkNotNull(client);
         this.path = PathUtils.validatePath(path);
      }

      public TreeCache build() {
         ExecutorService executor = this.executorService;
         if (executor == null) {
            executor = Executors.newSingleThreadExecutor((ThreadFactory)TreeCache.defaultThreadFactorySupplier.get());
         }

         return new TreeCache(this.client, this.path, this.cacheData, this.dataIsCompressed, this.maxDepth, executor, this.createParentNodes, this.disableZkWatches, this.selector);
      }

      public Builder setCacheData(boolean cacheData) {
         this.cacheData = cacheData;
         return this;
      }

      public Builder setDataIsCompressed(boolean dataIsCompressed) {
         this.dataIsCompressed = dataIsCompressed;
         return this;
      }

      public Builder setExecutor(ThreadFactory threadFactory) {
         return this.setExecutor(Executors.newSingleThreadExecutor(threadFactory));
      }

      public Builder setExecutor(ExecutorService executorService) {
         this.executorService = (ExecutorService)Preconditions.checkNotNull(executorService);
         return this;
      }

      public Builder setMaxDepth(int maxDepth) {
         this.maxDepth = maxDepth;
         return this;
      }

      public Builder setCreateParentNodes(boolean createParentNodes) {
         this.createParentNodes = createParentNodes;
         return this;
      }

      public Builder disableZkWatches(boolean disableZkWatches) {
         this.disableZkWatches = disableZkWatches;
         return this;
      }

      public Builder setSelector(TreeCacheSelector selector) {
         this.selector = selector;
         return this;
      }
   }

   final class TreeNode implements Watcher, BackgroundCallback {
      volatile ChildData childData;
      final TreeNode parent;
      final String path;
      volatile ConcurrentMap children;
      final int depth;

      TreeNode(String path, TreeNode parent) {
         this.path = path;
         this.parent = parent;
         this.depth = parent == null ? 0 : parent.depth + 1;
      }

      private void refresh() throws Exception {
         if (this.depth < TreeCache.this.maxDepth && TreeCache.this.selector.traverseChildren(this.path)) {
            TreeCache.this.outstandingOps.addAndGet(2L);
            this.doRefreshData();
            this.doRefreshChildren();
         } else {
            this.refreshData();
         }

      }

      private void refreshChildren() throws Exception {
         if (this.depth < TreeCache.this.maxDepth && TreeCache.this.selector.traverseChildren(this.path)) {
            TreeCache.this.outstandingOps.incrementAndGet();
            this.doRefreshChildren();
         }

      }

      private void refreshData() throws Exception {
         TreeCache.this.outstandingOps.incrementAndGet();
         this.doRefreshData();
      }

      private void doRefreshChildren() throws Exception {
         if (TreeCache.this.treeState.get() == TreeCache.TreeState.STARTED) {
            this.maybeWatch(TreeCache.this.client.getChildren()).forPath(this.path);
         }

      }

      private void doRefreshData() throws Exception {
         if (TreeCache.this.treeState.get() == TreeCache.TreeState.STARTED) {
            if (TreeCache.this.dataIsCompressed) {
               this.maybeWatch((Watchable)TreeCache.this.client.getData().decompressed()).forPath(this.path);
            } else {
               this.maybeWatch(TreeCache.this.client.getData()).forPath(this.path);
            }
         }

      }

      private Pathable maybeWatch(Watchable dataBuilder) {
         return TreeCache.this.disableZkWatches ? (Pathable)((Backgroundable)dataBuilder).inBackground(this) : (Pathable)((BackgroundPathable)dataBuilder.usingWatcher(this)).inBackground(this);
      }

      void wasReconnected() throws Exception {
         this.refresh();
         ConcurrentMap<String, TreeNode> childMap = this.children;
         if (childMap != null) {
            for(TreeNode child : childMap.values()) {
               child.wasReconnected();
            }
         }

      }

      void wasCreated() throws Exception {
         this.refresh();
      }

      void wasDeleted() throws Exception {
         ChildData oldChildData = (ChildData)TreeCache.childDataUpdater.getAndSet(this, TreeCache.DEAD);
         if (oldChildData != TreeCache.DEAD) {
            ConcurrentMap<String, TreeNode> childMap = (ConcurrentMap)TreeCache.childrenUpdater.getAndSet(this, (Object)null);
            if (childMap != null) {
               ArrayList<TreeNode> childCopy = new ArrayList(childMap.values());
               childMap.clear();

               for(TreeNode child : childCopy) {
                  child.wasDeleted();
               }
            }

            if (TreeCache.this.treeState.get() != TreeCache.TreeState.CLOSED) {
               if (TreeCache.isLive(oldChildData)) {
                  TreeCache.this.publishEvent(TreeCacheEvent.Type.NODE_REMOVED, oldChildData, (ChildData)null);
               }

               if (this.parent == null) {
                  this.maybeWatch(TreeCache.this.client.checkExists()).forPath(this.path);
               } else {
                  ConcurrentMap<String, TreeNode> parentChildMap = this.parent.children;
                  if (parentChildMap != null) {
                     parentChildMap.remove(ZKPaths.getNodeFromPath(this.path), this);
                  }
               }

            }
         }
      }

      public void process(WatchedEvent event) {
         TreeCache.LOG.debug("process: {}", event);

         try {
            switch (event.getType()) {
               case NodeCreated:
                  Preconditions.checkState(this.parent == null, "unexpected NodeCreated on non-root node");
                  this.wasCreated();
                  break;
               case NodeChildrenChanged:
                  this.refreshChildren();
                  break;
               case NodeDataChanged:
                  this.refreshData();
                  break;
               case NodeDeleted:
                  this.wasDeleted();
            }
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            TreeCache.this.handleException(e);
         }

      }

      public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
         TreeCache.LOG.debug("processResult: {}", event);
         Stat newStat = event.getStat();
         label106:
         switch (event.getType()) {
            case EXISTS:
               Preconditions.checkState(this.parent == null, "unexpected EXISTS on non-root node");
               if (event.getResultCode() == Code.OK.intValue()) {
                  TreeCache.childDataUpdater.compareAndSet(this, TreeCache.DEAD, (Object)null);
                  this.wasCreated();
               }
               break;
            case CHILDREN:
               if (event.getResultCode() == Code.OK.intValue()) {
                  ChildData oldChildData = this.childData;
                  if (TreeCache.isLive(oldChildData) && oldChildData.getStat().getMzxid() == newStat.getMzxid()) {
                     TreeCache.childDataUpdater.compareAndSet(this, oldChildData, new ChildData(oldChildData.getPath(), newStat, oldChildData.getData()));
                  }

                  if (!event.getChildren().isEmpty()) {
                     ConcurrentMap<String, TreeNode> childMap = this.children;

                     while(childMap == null) {
                        childMap = Maps.newConcurrentMap();
                        if (!TreeCache.childrenUpdater.compareAndSet(this, (Object)null, childMap)) {
                           childMap = this.children;
                        }
                     }

                     List<String> newChildren = new ArrayList();

                     for(String child : event.getChildren()) {
                        if (!childMap.containsKey(child) && TreeCache.this.selector.acceptChild(ZKPaths.makePath(this.path, child))) {
                           newChildren.add(child);
                        }
                     }

                     Collections.sort(newChildren);

                     for(String child : newChildren) {
                        String fullPath = ZKPaths.makePath(this.path, child);
                        TreeNode node = TreeCache.this.new TreeNode(fullPath, this);
                        if (childMap.putIfAbsent(child, node) == null) {
                           node.wasCreated();
                        }
                     }
                  }
               } else if (event.getResultCode() == Code.NONODE.intValue()) {
                  this.wasDeleted();
               }
               break;
            case GET_DATA:
               if (event.getResultCode() == Code.OK.intValue()) {
                  String eventPath = event.getPath();
                  ChildData toPublish = new ChildData(eventPath, newStat, event.getData());
                  ChildData toUpdate = TreeCache.this.cacheData ? toPublish : new ChildData(eventPath, newStat, (byte[])null);

                  ChildData oldChildData;
                  do {
                     oldChildData = this.childData;
                     if (TreeCache.isLive(oldChildData) && newStat.getMzxid() <= oldChildData.getStat().getMzxid() || this.parent != null && oldChildData == TreeCache.DEAD) {
                        break label106;
                     }
                  } while(!TreeCache.childDataUpdater.compareAndSet(this, oldChildData, toUpdate));

                  if (TreeCache.isLive(oldChildData)) {
                     TreeCache.this.publishEvent(TreeCacheEvent.Type.NODE_UPDATED, toPublish, oldChildData);
                  } else {
                     TreeCache.this.publishEvent(TreeCacheEvent.Type.NODE_ADDED, toPublish, (ChildData)null);
                  }
               } else if (event.getResultCode() == Code.NONODE.intValue()) {
                  this.wasDeleted();
               }
               break;
            default:
               TreeCache.LOG.info(String.format("Unknown event %s", event));
               TreeCache.this.outstandingOps.decrementAndGet();
               return;
         }

         if (TreeCache.this.outstandingOps.decrementAndGet() == 0L && TreeCache.this.isInitialized.compareAndSet(false, true)) {
            TreeCache.this.publishEvent(TreeCacheEvent.Type.INITIALIZED);
         }

      }
   }

   private static enum TreeState {
      LATENT,
      STARTED,
      CLOSED;
   }
}
