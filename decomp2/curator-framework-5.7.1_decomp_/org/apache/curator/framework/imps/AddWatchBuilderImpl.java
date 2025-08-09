package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.AddWatchBuilder;
import org.apache.curator.framework.api.AddWatchBuilder2;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.WatchableBase;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

public class AddWatchBuilderImpl implements AddWatchBuilder, Pathable, BackgroundOperation {
   private final CuratorFrameworkImpl client;
   private Watching watching;
   private Backgrounding backgrounding = new Backgrounding();
   private AddWatchMode mode;

   AddWatchBuilderImpl(CuratorFrameworkImpl client) {
      this.mode = AddWatchMode.PERSISTENT_RECURSIVE;
      this.client = client;
      this.watching = new Watching(client, true);
   }

   public AddWatchBuilderImpl(CuratorFrameworkImpl client, Watching watching, Backgrounding backgrounding, AddWatchMode mode) {
      this.mode = AddWatchMode.PERSISTENT_RECURSIVE;
      this.client = client;
      this.watching = watching;
      this.backgrounding = backgrounding;
      this.mode = mode;
   }

   public WatchableBase inBackground() {
      this.backgrounding = new Backgrounding();
      return this;
   }

   public AddWatchBuilder2 withMode(AddWatchMode mode) {
      this.mode = mode;
      return this;
   }

   public Pathable usingWatcher(Watcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public Pathable usingWatcher(CuratorWatcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public WatchableBase inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public WatchableBase inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public WatchableBase inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public WatchableBase inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(callback, executor);
      return this;
   }

   public WatchableBase inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public Void forPath(String path) throws Exception {
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), this.watching), (CuratorEvent)null);
      } else {
         this.pathInForeground(path);
      }

      return null;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.ADD_WATCH;
   }

   public void performBackgroundOperation(OperationAndData data) throws Exception {
      String path = (String)data.getData();
      String fixedPath = this.client.fixForNamespace(path);

      try {
         OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("AddWatchBuilderImpl-Background");
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().addWatch(fixedPath, this.mode, (rc, path1, ctx) -> {
               trace.setReturnCode(rc).setWithWatcher(true).setPath(path1).commit();
               CuratorEvent event = new CuratorEventImpl(this.client, CuratorEventType.ADD_WATCH, rc, path1, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               this.client.processBackgroundOperation(data, event);
            }, this.backgrounding.getContext());
         } else {
            this.client.getZooKeeper().addWatch(fixedPath, this.watching.getWatcher(path), this.mode, (rc, path1, ctx) -> {
               trace.setReturnCode(rc).setWithWatcher(true).setPath(path1).commit();
               CuratorEvent event = new CuratorEventImpl(this.client, CuratorEventType.ADD_WATCH, rc, path1, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               this.client.processBackgroundOperation(data, event);
            }, this.backgrounding.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, this.watching);
      }

   }

   private void pathInForeground(String path) throws Exception {
      String fixedPath = this.client.fixForNamespace(path);
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("AddWatchBuilderImpl-Foreground");
      RetryLoop.callWithRetry(this.client.getZookeeperClient(), () -> {
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().addWatch(fixedPath, this.mode);
         } else {
            this.client.getZooKeeper().addWatch(fixedPath, this.watching.getWatcher(path), this.mode);
         }

         return null;
      });
      trace.setPath(fixedPath).setWithWatcher(true).commit();
   }
}
