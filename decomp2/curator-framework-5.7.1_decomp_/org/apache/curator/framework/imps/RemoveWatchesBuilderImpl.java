package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.TimeTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.BackgroundPathableQuietlyable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.RemoveWatchesBuilder;
import org.apache.curator.framework.api.RemoveWatchesLocal;
import org.apache.curator.framework.api.RemoveWatchesType;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.WatcherType;
import org.apache.zookeeper.data.Stat;

public class RemoveWatchesBuilderImpl implements RemoveWatchesBuilder, RemoveWatchesType, RemoveWatchesLocal, BackgroundOperation, ErrorListenerPathable {
   private CuratorFrameworkImpl client;
   private Watcher watcher;
   private CuratorWatcher curatorWatcher;
   private Watcher.WatcherType watcherType;
   private boolean guaranteed;
   private boolean local;
   private boolean quietly;
   private Backgrounding backgrounding;

   public RemoveWatchesBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.watcher = null;
      this.curatorWatcher = null;
      this.watcherType = WatcherType.Any;
      this.guaranteed = false;
      this.local = false;
      this.quietly = false;
      this.backgrounding = new Backgrounding();
   }

   public RemoveWatchesBuilderImpl(CuratorFrameworkImpl client, Watcher watcher, CuratorWatcher curatorWatcher, Watcher.WatcherType watcherType, boolean guaranteed, boolean local, boolean quietly, Backgrounding backgrounding) {
      this.client = client;
      this.watcher = watcher;
      this.curatorWatcher = curatorWatcher;
      this.watcherType = watcherType;
      this.guaranteed = guaranteed;
      this.local = local;
      this.quietly = quietly;
      this.backgrounding = backgrounding;
   }

   void internalRemoval(Watcher watcher, String path) throws Exception {
      this.watcher = watcher;
      this.watcherType = WatcherType.Any;
      this.quietly = true;
      this.guaranteed = true;
      if (Boolean.getBoolean("curator-remove-watchers-in-foreground")) {
         this.backgrounding = new Backgrounding();
         this.pathInForeground(path);
      } else {
         this.backgrounding = new Backgrounding(true);
         this.pathInBackground(path);
      }

   }

   public RemoveWatchesType remove(Watcher watcher) {
      this.watcher = watcher;
      this.curatorWatcher = null;
      return this;
   }

   public RemoveWatchesType remove(CuratorWatcher watcher) {
      this.watcher = null;
      this.curatorWatcher = watcher;
      return this;
   }

   public RemoveWatchesType removeAll() {
      this.watcher = null;
      this.curatorWatcher = null;
      return this;
   }

   public RemoveWatchesLocal ofType(Watcher.WatcherType watcherType) {
      this.watcherType = watcherType;
      return this;
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

   public RemoveWatchesLocal guaranteed() {
      this.guaranteed = true;
      return this;
   }

   public BackgroundPathableQuietlyable locally() {
      this.local = true;
      return this;
   }

   public BackgroundPathable quietly() {
      this.quietly = true;
      return this;
   }

   public Void forPath(String path) throws Exception {
      String adjustedPath = this.client.fixForNamespace(path);
      if (this.backgrounding.inBackground()) {
         this.pathInBackground(adjustedPath);
      } else {
         this.pathInForeground(adjustedPath);
      }

      return null;
   }

   protected CuratorFrameworkImpl getClient() {
      return this.client;
   }

   private void pathInBackground(final String path) {
      OperationAndData.ErrorCallback<String> errorCallback = null;
      if (this.guaranteed) {
         errorCallback = new OperationAndData.ErrorCallback() {
            public void retriesExhausted(OperationAndData operationAndData) {
               RemoveWatchesBuilderImpl.this.client.getFailedRemoveWatcherManager().addFailedOperation(new FailedRemoveWatchManager.FailedRemoveWatchDetails(path, RemoveWatchesBuilderImpl.this.watcher));
            }
         };
      }

      this.client.processBackgroundOperation(new OperationAndData(this, path, this.backgrounding.getCallback(), errorCallback, this.backgrounding.getContext(), !this.local), (CuratorEvent)null);
   }

   private void pathInForeground(final String path) throws Exception {
      final NamespaceWatcher namespaceWatcher = this.makeNamespaceWatcher(path);
      if (this.local) {
         ZooKeeper zkClient = this.client.getZooKeeper();
         if (namespaceWatcher != null) {
            zkClient.removeWatches(path, namespaceWatcher, this.watcherType, this.local);
         } else {
            zkClient.removeAllWatches(path, this.watcherType, this.local);
         }
      } else {
         RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
            public Void call() throws Exception {
               try {
                  ZooKeeper zkClient = RemoveWatchesBuilderImpl.this.client.getZookeeperClient().getZooKeeper();
                  if (namespaceWatcher != null) {
                     zkClient.removeWatches(path, namespaceWatcher, RemoveWatchesBuilderImpl.this.watcherType, false);
                  } else {
                     zkClient.removeAllWatches(path, RemoveWatchesBuilderImpl.this.watcherType, false);
                  }
               } catch (Exception e) {
                  if (RemoveWatchesBuilderImpl.this.client.getZookeeperClient().getRetryPolicy().allowRetry(e) && RemoveWatchesBuilderImpl.this.guaranteed) {
                     RemoveWatchesBuilderImpl.this.client.getFailedRemoveWatcherManager().addFailedOperation(new FailedRemoveWatchManager.FailedRemoveWatchDetails(path, namespaceWatcher));
                     throw e;
                  }

                  if (!(e instanceof KeeperException.NoWatcherException) || !RemoveWatchesBuilderImpl.this.quietly) {
                     throw e;
                  }
               }

               return null;
            }
         });
      }

   }

   private NamespaceWatcher makeNamespaceWatcher(String path) {
      NamespaceWatcher namespaceWatcher = null;
      if (this.watcher != null) {
         if (this.watcher instanceof NamespaceWatcher) {
            namespaceWatcher = (NamespaceWatcher)this.watcher;
         } else {
            namespaceWatcher = new NamespaceWatcher(this.client, this.watcher, path);
         }
      } else if (this.curatorWatcher != null) {
         namespaceWatcher = new NamespaceWatcher(this.client, this.curatorWatcher, path);
      }

      return namespaceWatcher;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.REMOVE_WATCHES;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final TimeTrace trace = this.client.getZookeeperClient().startTracer("RemoteWatches-Background");
         AsyncCallback.VoidCallback callback = new AsyncCallback.VoidCallback() {
            public void processResult(int rc, String path, Object ctx) {
               trace.commit();
               CuratorEvent event = new CuratorEventImpl(RemoveWatchesBuilderImpl.this.client, CuratorEventType.REMOVE_WATCHES, rc, path, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               RemoveWatchesBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         ZooKeeper zkClient = this.client.getZooKeeper();
         NamespaceWatcher namespaceWatcher = this.makeNamespaceWatcher((String)operationAndData.getData());
         if (namespaceWatcher == null) {
            zkClient.removeAllWatches((String)operationAndData.getData(), this.watcherType, this.local, callback, operationAndData.getContext());
         } else {
            zkClient.removeWatches((String)operationAndData.getData(), namespaceWatcher, this.watcherType, this.local, callback, operationAndData.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }
}
