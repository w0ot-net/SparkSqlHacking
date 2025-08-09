package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class GetChildrenBuilderImpl implements GetChildrenBuilder, BackgroundOperation, ErrorListenerPathable {
   private final CuratorFrameworkImpl client;
   private Watching watching;
   private Backgrounding backgrounding;
   private Stat responseStat;

   GetChildrenBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.watching = new Watching(client);
      this.backgrounding = new Backgrounding();
      this.responseStat = null;
   }

   public GetChildrenBuilderImpl(CuratorFrameworkImpl client, Watcher watcher, Backgrounding backgrounding, Stat responseStat) {
      this.client = client;
      this.watching = new Watching(client, watcher);
      this.backgrounding = backgrounding;
      this.responseStat = responseStat;
   }

   public WatchPathable storingStatIn(Stat stat) {
      this.responseStat = stat;
      return new WatchPathable() {
         public List forPath(String path) throws Exception {
            return GetChildrenBuilderImpl.this.forPath(path);
         }

         public Pathable watched() {
            GetChildrenBuilderImpl.this.watched();
            return GetChildrenBuilderImpl.this;
         }

         public Pathable usingWatcher(Watcher watcher) {
            GetChildrenBuilderImpl.this.usingWatcher(watcher);
            return GetChildrenBuilderImpl.this;
         }

         public Pathable usingWatcher(CuratorWatcher watcher) {
            GetChildrenBuilderImpl.this.usingWatcher(watcher);
            return GetChildrenBuilderImpl.this;
         }
      };
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public ErrorListenerPathable inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerPathable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public Pathable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public BackgroundPathable watched() {
      this.watching = new Watching(this.client, true);
      return this;
   }

   public BackgroundPathable usingWatcher(Watcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public BackgroundPathable usingWatcher(CuratorWatcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.CHILDREN;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetChildrenBuilderImpl-Background");
         AsyncCallback.Children2Callback callback = new AsyncCallback.Children2Callback() {
            public void processResult(int rc, String path, Object o, List strings, Stat stat) {
               GetChildrenBuilderImpl.this.watching.commitWatcher(rc, false);
               trace.setReturnCode(rc).setPath(path).setWithWatcher(GetChildrenBuilderImpl.this.watching.hasWatcher()).setStat(stat).commit();
               if (strings == null) {
                  strings = Lists.newArrayList();
               }

               CuratorEventImpl event = new CuratorEventImpl(GetChildrenBuilderImpl.this.client, CuratorEventType.CHILDREN, rc, path, (String)null, o, stat, (byte[])null, strings, (WatchedEvent)null, (List)null, (List)null);
               GetChildrenBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().getChildren((String)operationAndData.getData(), true, callback, this.backgrounding.getContext());
         } else {
            this.client.getZooKeeper().getChildren((String)operationAndData.getData(), this.watching.getWatcher((String)operationAndData.getData()), callback, this.backgrounding.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, this.watching);
      }

   }

   public List forPath(String path) throws Exception {
      this.client.getSchemaSet().getSchema(path).validateWatch(path, this.watching.isWatched() || this.watching.hasWatcher());
      path = this.client.fixForNamespace(path);
      List<String> children = null;
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), this.watching), (CuratorEvent)null);
      } else {
         children = this.pathInForeground(path);
      }

      return children;
   }

   private List pathInForeground(final String path) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetChildrenBuilderImpl-Foreground");
      List<String> children = (List)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public List call() throws Exception {
            List<String> children;
            if (GetChildrenBuilderImpl.this.watching.isWatched()) {
               children = GetChildrenBuilderImpl.this.client.getZooKeeper().getChildren(path, true, GetChildrenBuilderImpl.this.responseStat);
            } else {
               children = GetChildrenBuilderImpl.this.client.getZooKeeper().getChildren(path, GetChildrenBuilderImpl.this.watching.getWatcher(path), GetChildrenBuilderImpl.this.responseStat);
               GetChildrenBuilderImpl.this.watching.commitWatcher(Code.OK.intValue(), false);
            }

            return children;
         }
      });
      trace.setPath(path).setWithWatcher(this.watching.hasWatcher()).setStat(this.responseStat).commit();
      return children;
   }
}
