package org.apache.curator.framework.recipes.watch;

import java.io.Closeable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathableQuietlyable;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.WatchableBase;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistentWatcher implements Closeable {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final AtomicReference state;
   private final StandardListenerManager listeners;
   private final StandardListenerManager resetListeners;
   private final ConnectionStateListener connectionStateListener;
   private final Watcher watcher;
   private final CuratorFramework client;
   private final String basePath;
   private final boolean recursive;

   public PersistentWatcher(CuratorFramework client, String basePath, boolean recursive) {
      this.state = new AtomicReference(PersistentWatcher.State.LATENT);
      this.listeners = StandardListenerManager.standard();
      this.resetListeners = StandardListenerManager.standard();
      this.connectionStateListener = (clientx, newState) -> {
         if (newState.isConnected()) {
            this.reset();
         }

      };
      this.watcher = (event) -> this.listeners.forEach((w) -> w.process(event));
      this.client = (CuratorFramework)Objects.requireNonNull(client, "client cannot be null");
      this.basePath = (String)Objects.requireNonNull(basePath, "basePath cannot be null");
      this.recursive = recursive;
   }

   public void start() {
      Preconditions.checkState(this.state.compareAndSet(PersistentWatcher.State.LATENT, PersistentWatcher.State.STARTED), "Already started");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      this.reset();
   }

   public void close() {
      if (this.state.compareAndSet(PersistentWatcher.State.STARTED, PersistentWatcher.State.CLOSED)) {
         this.listeners.clear();
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);

         try {
            ((ErrorListenerPathable)((BackgroundPathableQuietlyable)this.client.watchers().remove(this.watcher).guaranteed()).inBackground()).forPath(this.basePath);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.log.debug(String.format("Could not remove watcher for path: %s", this.basePath), e);
         }
      }

   }

   public Listenable getListenable() {
      return this.listeners;
   }

   public Listenable getResetListenable() {
      return this.resetListeners;
   }

   private void reset() {
      if (this.state.get() == PersistentWatcher.State.STARTED) {
         try {
            BackgroundCallback callback = (__, event) -> {
               if (event.getResultCode() == Code.OK.intValue()) {
                  this.resetListeners.forEach(Runnable::run);
               } else {
                  this.reset();
               }

            };
            ((Pathable)((WatchableBase)this.client.watchers().add().withMode(this.recursive ? AddWatchMode.PERSISTENT_RECURSIVE : AddWatchMode.PERSISTENT).inBackground(callback)).usingWatcher(this.watcher)).forPath(this.basePath);
         } catch (Exception e) {
            this.log.error("Could not reset persistent watch at path: " + this.basePath, e);
         }

      }
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
