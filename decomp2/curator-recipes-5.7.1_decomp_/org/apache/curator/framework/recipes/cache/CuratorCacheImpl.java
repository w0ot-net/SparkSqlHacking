package org.apache.curator.framework.recipes.cache;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.GetDataWatchBackgroundStatable;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.recipes.watch.PersistentWatcher;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CuratorCacheImpl implements CuratorCache, CuratorCacheBridge {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final AtomicReference state;
   private final PersistentWatcher persistentWatcher;
   private final CuratorFramework client;
   private final CuratorCacheStorage storage;
   private final String path;
   private final boolean recursive;
   private final boolean compressedData;
   private final boolean clearOnClose;
   private final StandardListenerManager listenerManager;
   private final Consumer exceptionHandler;
   private final OutstandingOps outstandingOps;

   CuratorCacheImpl(CuratorFramework client, CuratorCacheStorage storage, String path, CuratorCache.Options[] optionsArg, Consumer exceptionHandler) {
      this.state = new AtomicReference(CuratorCacheImpl.State.LATENT);
      this.listenerManager = StandardListenerManager.standard();
      this.outstandingOps = new OutstandingOps(() -> this.callListeners(CuratorCacheListener::initialized));
      Set<CuratorCache.Options> options = (Set<CuratorCache.Options>)(optionsArg != null ? Sets.newHashSet(optionsArg) : Collections.emptySet());
      this.client = client;
      this.storage = storage != null ? storage : CuratorCacheStorage.standard();
      this.path = path;
      this.recursive = !options.contains(CuratorCache.Options.SINGLE_NODE_CACHE);
      this.compressedData = options.contains(CuratorCache.Options.COMPRESSED_DATA);
      this.clearOnClose = !options.contains(CuratorCache.Options.DO_NOT_CLEAR_ON_CLOSE);
      this.persistentWatcher = new PersistentWatcher(client, path, this.recursive);
      this.persistentWatcher.getListenable().addListener(this::processEvent);
      this.persistentWatcher.getResetListenable().addListener(this::rebuild);
      this.exceptionHandler = exceptionHandler != null ? exceptionHandler : (e) -> this.log.error("CuratorCache error", e);
   }

   public void start() {
      Preconditions.checkState(this.state.compareAndSet(CuratorCacheImpl.State.LATENT, CuratorCacheImpl.State.STARTED), "Already started");
      this.persistentWatcher.start();
   }

   public void close() {
      if (this.state.compareAndSet(CuratorCacheImpl.State.STARTED, CuratorCacheImpl.State.CLOSED)) {
         this.persistentWatcher.close();
         if (this.clearOnClose) {
            this.storage.clear();
         }
      }

   }

   public boolean isCuratorCache() {
      return true;
   }

   public Listenable listenable() {
      return this.listenerManager;
   }

   public Optional get(String path) {
      return this.storage.get(path);
   }

   public int size() {
      return this.storage.size();
   }

   public Stream stream() {
      return this.storage.stream();
   }

   @VisibleForTesting
   CuratorCacheStorage storage() {
      return this.storage;
   }

   private void rebuild() {
      if (this.state.get() == CuratorCacheImpl.State.STARTED) {
         this.nodeChanged(this.path);
         this.storage.stream().map(ChildData::getPath).filter((p) -> !p.equals(this.path)).forEach(this::nodeChanged);
      }
   }

   private void processEvent(WatchedEvent event) {
      if (this.state.get() == CuratorCacheImpl.State.STARTED) {
         switch (event.getType()) {
            case NodeDataChanged:
            case NodeCreated:
               this.nodeChanged(event.getPath());
               break;
            case NodeDeleted:
               this.removeStorage(event.getPath());
         }

      }
   }

   private void checkChildrenChanged(String fromPath, Stat oldStat, Stat newStat) {
      if (this.state.get() == CuratorCacheImpl.State.STARTED && this.recursive) {
         if (oldStat == null || oldStat.getCversion() != newStat.getCversion()) {
            try {
               BackgroundCallback callback = (__, event) -> {
                  if (event.getResultCode() == Code.OK.intValue()) {
                     event.getChildren().forEach((child) -> this.nodeChanged(ZKPaths.makePath(fromPath, child)));
                  } else if (event.getResultCode() == Code.NONODE.intValue()) {
                     this.removeStorage(event.getPath());
                  } else {
                     this.handleException(event);
                  }

                  this.outstandingOps.decrement();
               };
               this.outstandingOps.increment();
               ((ErrorListenerPathable)this.client.getChildren().inBackground(callback)).forPath(fromPath);
            } catch (Exception e) {
               this.handleException(e);
            }

         }
      }
   }

   private void nodeChanged(String fromPath) {
      if (this.state.get() == CuratorCacheImpl.State.STARTED) {
         try {
            BackgroundCallback callback = (__, event) -> {
               if (event.getResultCode() == Code.OK.intValue()) {
                  Optional<ChildData> childData = this.putStorage(new ChildData(event.getPath(), event.getStat(), event.getData()));
                  this.checkChildrenChanged(event.getPath(), (Stat)childData.map(ChildData::getStat).orElse((Object)null), event.getStat());
               } else if (event.getResultCode() == Code.NONODE.intValue()) {
                  this.removeStorage(event.getPath());
               } else {
                  this.handleException(event);
               }

               this.outstandingOps.decrement();
            };
            this.outstandingOps.increment();
            if (this.compressedData) {
               ((ErrorListenerPathable)((GetDataWatchBackgroundStatable)this.client.getData().decompressed()).inBackground(callback)).forPath(fromPath);
            } else {
               ((ErrorListenerPathable)this.client.getData().inBackground(callback)).forPath(fromPath);
            }
         } catch (Exception e) {
            this.handleException(e);
         }

      }
   }

   private Optional putStorage(ChildData data) {
      Optional<ChildData> previousData = this.storage.put(data);
      if (previousData.isPresent()) {
         if (((ChildData)previousData.get()).getStat().getVersion() != data.getStat().getVersion()) {
            this.callListeners((l) -> l.event(CuratorCacheListener.Type.NODE_CHANGED, (ChildData)previousData.get(), data));
         }
      } else {
         this.callListeners((l) -> l.event(CuratorCacheListener.Type.NODE_CREATED, (ChildData)null, data));
      }

      return previousData;
   }

   private void removeStorage(String path) {
      this.storage.remove(path).ifPresent((previousData) -> this.callListeners((l) -> l.event(CuratorCacheListener.Type.NODE_DELETED, previousData, (ChildData)null)));
   }

   private void callListeners(Consumer proc) {
      if (this.state.get() == CuratorCacheImpl.State.STARTED) {
         this.client.runSafe(() -> this.listenerManager.forEach(proc));
      }

   }

   private void handleException(CuratorEvent event) {
      this.handleException((Exception)KeeperException.create(Code.get(event.getResultCode())));
   }

   private void handleException(Exception e) {
      ThreadUtils.checkInterrupted(e);
      this.exceptionHandler.accept(e);
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
