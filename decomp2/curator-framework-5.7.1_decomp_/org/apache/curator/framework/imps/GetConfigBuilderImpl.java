package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.TimeTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundEnsembleable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.Ensembleable;
import org.apache.curator.framework.api.ErrorListenerEnsembleable;
import org.apache.curator.framework.api.GetConfigBuilder;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.WatchBackgroundEnsembleable;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class GetConfigBuilderImpl implements GetConfigBuilder, BackgroundOperation, ErrorListenerEnsembleable {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding;
   private Watching watching;
   private Stat stat;

   public GetConfigBuilderImpl(CuratorFrameworkImpl client) {
      this.client = (CuratorFrameworkImpl)client.usingNamespace((String)null);
      this.backgrounding = new Backgrounding();
      this.watching = new Watching(this.client);
   }

   public GetConfigBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, Watcher watcher, Stat stat) {
      this.client = (CuratorFrameworkImpl)client.usingNamespace((String)null);
      this.backgrounding = backgrounding;
      this.watching = new Watching(this.client, watcher);
      this.stat = stat;
   }

   public WatchBackgroundEnsembleable storingStatIn(Stat stat) {
      this.stat = stat;
      return new WatchBackgroundEnsembleable() {
         public ErrorListenerEnsembleable inBackground() {
            return GetConfigBuilderImpl.this.inBackground();
         }

         public ErrorListenerEnsembleable inBackground(Object context) {
            return GetConfigBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerEnsembleable inBackground(BackgroundCallback callback) {
            return GetConfigBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context) {
            return GetConfigBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Executor executor) {
            return GetConfigBuilderImpl.this.inBackground(callback, executor);
         }

         public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return GetConfigBuilderImpl.this.inBackground(callback, context, executor);
         }

         public byte[] forEnsemble() throws Exception {
            return GetConfigBuilderImpl.this.forEnsemble();
         }

         public BackgroundEnsembleable watched() {
            return GetConfigBuilderImpl.this.watched();
         }

         public BackgroundEnsembleable usingWatcher(Watcher watcher) {
            return GetConfigBuilderImpl.this.usingWatcher(watcher);
         }

         public BackgroundEnsembleable usingWatcher(CuratorWatcher watcher) {
            return GetConfigBuilderImpl.this.usingWatcher(watcher);
         }
      };
   }

   public BackgroundEnsembleable watched() {
      this.watching = new Watching(this.client, true);
      return new InternalBackgroundEnsembleable();
   }

   public BackgroundEnsembleable usingWatcher(Watcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return new InternalBackgroundEnsembleable();
   }

   public BackgroundEnsembleable usingWatcher(CuratorWatcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return new InternalBackgroundEnsembleable();
   }

   public ErrorListenerEnsembleable inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerEnsembleable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public ErrorListenerEnsembleable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(callback, executor);
      return this;
   }

   public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public Ensembleable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public byte[] forEnsemble() throws Exception {
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, (Object)null, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), this.watching), (CuratorEvent)null);
         return null;
      } else {
         return this.configInForeground();
      }
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.GET_CONFIG;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final TimeTrace trace = this.client.getZookeeperClient().startTracer("GetDataBuilderImpl-Background");
         AsyncCallback.DataCallback callback = new AsyncCallback.DataCallback() {
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
               GetConfigBuilderImpl.this.watching.commitWatcher(rc, false);
               trace.commit();
               CuratorEvent event = new CuratorEventImpl(GetConfigBuilderImpl.this.client, CuratorEventType.GET_CONFIG, rc, path, (String)null, ctx, stat, data, (List)null, (WatchedEvent)null, (List)null, (List)null);
               GetConfigBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().getConfig(true, callback, this.backgrounding.getContext());
         } else {
            this.client.getZooKeeper().getConfig(this.watching.getWatcher("/zookeeper/config"), callback, this.backgrounding.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, this.watching);
      }

   }

   private byte[] configInForeground() throws Exception {
      TimeTrace trace = this.client.getZookeeperClient().startTracer("GetConfigBuilderImpl-Foreground");

      byte[] var2;
      try {
         var2 = (byte[])RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
            public byte[] call() throws Exception {
               if (GetConfigBuilderImpl.this.watching.isWatched()) {
                  return GetConfigBuilderImpl.this.client.getZooKeeper().getConfig(true, GetConfigBuilderImpl.this.stat);
               } else {
                  byte[] config = GetConfigBuilderImpl.this.client.getZooKeeper().getConfig(GetConfigBuilderImpl.this.watching.getWatcher("/zookeeper/config"), GetConfigBuilderImpl.this.stat);
                  GetConfigBuilderImpl.this.watching.commitWatcher(Code.OK.intValue(), false);
                  return config;
               }
            }
         });
      } finally {
         trace.commit();
      }

      return var2;
   }

   private class InternalBackgroundEnsembleable implements BackgroundEnsembleable {
      private InternalBackgroundEnsembleable() {
      }

      public ErrorListenerEnsembleable inBackground() {
         return GetConfigBuilderImpl.this.inBackground();
      }

      public ErrorListenerEnsembleable inBackground(Object context) {
         return GetConfigBuilderImpl.this.inBackground(context);
      }

      public ErrorListenerEnsembleable inBackground(BackgroundCallback callback) {
         return GetConfigBuilderImpl.this.inBackground(callback);
      }

      public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context) {
         return GetConfigBuilderImpl.this.inBackground(callback, context);
      }

      public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Executor executor) {
         return GetConfigBuilderImpl.this.inBackground(callback, executor);
      }

      public ErrorListenerEnsembleable inBackground(BackgroundCallback callback, Object context, Executor executor) {
         return GetConfigBuilderImpl.this.inBackground(callback, context, executor);
      }

      public byte[] forEnsemble() throws Exception {
         return GetConfigBuilderImpl.this.forEnsemble();
      }
   }
}
