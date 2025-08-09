package org.apache.curator.framework.recipes.queue;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.PathUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;

class ChildrenCache implements Closeable {
   private final WatcherRemoveCuratorFramework client;
   private final String path;
   private final AtomicReference children = new AtomicReference(new Data(Lists.newArrayList(), 0L));
   private final AtomicBoolean isClosed = new AtomicBoolean(false);
   private final CuratorWatcher watcher = new CuratorWatcher() {
      public void process(WatchedEvent event) throws Exception {
         if (!ChildrenCache.this.isClosed.get()) {
            ChildrenCache.this.sync();
         }

      }
   };
   private final BackgroundCallback callback = new BackgroundCallback() {
      public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
         if (event.getResultCode() == Code.OK.intValue()) {
            ChildrenCache.this.setNewChildren(event.getChildren());
         }

      }
   };
   private final ConnectionStateListener connectionStateListener = (__, newState) -> {
      if (newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED) {
         try {
            this.sync();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

   };

   ChildrenCache(CuratorFramework client, String path) {
      this.client = client.newWatcherRemoveCuratorFramework();
      this.path = PathUtils.validatePath(path);
   }

   void start() throws Exception {
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      this.sync();
   }

   public void close() throws IOException {
      this.client.removeWatchers();
      this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
      this.isClosed.set(true);
      this.notifyFromCallback();
   }

   Data getData() {
      return (Data)this.children.get();
   }

   Data blockingNextGetData(long startVersion) throws InterruptedException {
      return this.blockingNextGetData(startVersion, 0L, (TimeUnit)null);
   }

   synchronized Data blockingNextGetData(long startVersion, long maxWait, TimeUnit unit) throws InterruptedException {
      long startMs = System.currentTimeMillis();
      boolean hasMaxWait = unit != null;
      long maxWaitMs = hasMaxWait ? unit.toMillis(maxWait) : -1L;

      while(startVersion == ((Data)this.children.get()).version) {
         if (hasMaxWait) {
            long elapsedMs = System.currentTimeMillis() - startMs;
            long thisWaitMs = maxWaitMs - elapsedMs;
            if (thisWaitMs <= 0L) {
               break;
            }

            this.wait(thisWaitMs);
         } else {
            this.wait();
         }
      }

      return (Data)this.children.get();
   }

   private synchronized void notifyFromCallback() {
      this.notifyAll();
   }

   private synchronized void sync() throws Exception {
      ((ErrorListenerPathable)((BackgroundPathable)this.client.getChildren().usingWatcher(this.watcher)).inBackground(this.callback)).forPath(this.path);
   }

   private synchronized void setNewChildren(List newChildren) {
      if (newChildren != null) {
         Data currentData = (Data)this.children.get();
         this.children.set(new Data(newChildren, currentData.version + 1L));
         this.notifyFromCallback();
      }

   }

   static class Data {
      final List children;
      final long version;

      private Data(List children, long version) {
         this.children = ImmutableList.copyOf(children);
         this.version = version;
      }
   }
}
