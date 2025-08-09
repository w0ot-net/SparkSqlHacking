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
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.api.GetDataWatchBackgroundStatable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.DataTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetDataBuilderImpl implements GetDataBuilder, BackgroundOperation, ErrorListenerPathable {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFrameworkImpl client;
   private Stat responseStat;
   private Watching watching;
   private Backgrounding backgrounding;
   private boolean decompress;

   GetDataBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.responseStat = null;
      this.watching = new Watching(client);
      this.backgrounding = new Backgrounding();
      this.decompress = false;
   }

   public GetDataBuilderImpl(CuratorFrameworkImpl client, Stat responseStat, Watcher watcher, Backgrounding backgrounding, boolean decompress) {
      this.client = client;
      this.responseStat = responseStat;
      this.watching = new Watching(client, watcher);
      this.backgrounding = backgrounding;
      this.decompress = decompress;
   }

   public GetDataWatchBackgroundStatable decompressed() {
      this.decompress = true;
      return new GetDataWatchBackgroundStatable() {
         public ErrorListenerPathable inBackground() {
            return GetDataBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
            return GetDataBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return GetDataBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathable inBackground(Object context) {
            return GetDataBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathable inBackground(BackgroundCallback callback) {
            return GetDataBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathable inBackground(BackgroundCallback callback, Executor executor) {
            return GetDataBuilderImpl.this.inBackground(callback, executor);
         }

         public byte[] forPath(String path) throws Exception {
            return GetDataBuilderImpl.this.forPath(path);
         }

         public WatchPathable storingStatIn(Stat stat) {
            return GetDataBuilderImpl.this.storingStatIn(stat);
         }

         public BackgroundPathable watched() {
            return GetDataBuilderImpl.this.watched();
         }

         public BackgroundPathable usingWatcher(Watcher watcher) {
            return GetDataBuilderImpl.this.usingWatcher(watcher);
         }

         public BackgroundPathable usingWatcher(CuratorWatcher watcher) {
            return GetDataBuilderImpl.this.usingWatcher(watcher);
         }
      };
   }

   public WatchPathable storingStatIn(Stat stat) {
      this.responseStat = stat;
      return new WatchPathable() {
         public byte[] forPath(String path) throws Exception {
            return GetDataBuilderImpl.this.forPath(path);
         }

         public Pathable watched() {
            GetDataBuilderImpl.this.watched();
            return GetDataBuilderImpl.this;
         }

         public Pathable usingWatcher(Watcher watcher) {
            GetDataBuilderImpl.this.usingWatcher(watcher);
            return GetDataBuilderImpl.this;
         }

         public Pathable usingWatcher(CuratorWatcher watcher) {
            GetDataBuilderImpl.this.usingWatcher(watcher);
            return GetDataBuilderImpl.this;
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
      return CuratorEventType.GET_DATA;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetDataBuilderImpl-Background");
         AsyncCallback.DataCallback callback = new AsyncCallback.DataCallback() {
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
               GetDataBuilderImpl.this.watching.commitWatcher(rc, false);
               trace.setReturnCode(rc).setResponseBytesLength(data).setPath(path).setWithWatcher(GetDataBuilderImpl.this.watching.hasWatcher()).setStat(stat).commit();
               if (GetDataBuilderImpl.this.responseStat != null && stat != null) {
                  DataTree.copyStat(stat, GetDataBuilderImpl.this.responseStat);
               }

               if (GetDataBuilderImpl.this.decompress && data != null) {
                  try {
                     data = GetDataBuilderImpl.this.client.getCompressionProvider().decompress(path, data);
                  } catch (Exception e) {
                     ThreadUtils.checkInterrupted(e);
                     GetDataBuilderImpl.this.log.error("Decompressing for path: " + path, e);
                     rc = Code.DATAINCONSISTENCY.intValue();
                  }
               }

               CuratorEvent event = new CuratorEventImpl(GetDataBuilderImpl.this.client, CuratorEventType.GET_DATA, rc, path, (String)null, ctx, stat, data, (List)null, (WatchedEvent)null, (List)null, (List)null);
               GetDataBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().getData((String)operationAndData.getData(), true, callback, this.backgrounding.getContext());
         } else {
            this.client.getZooKeeper().getData((String)operationAndData.getData(), this.watching.getWatcher((String)operationAndData.getData()), callback, this.backgrounding.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, this.watching);
      }

   }

   public byte[] forPath(String path) throws Exception {
      this.client.getSchemaSet().getSchema(path).validateWatch(path, this.watching.isWatched() || this.watching.hasWatcher());
      path = this.client.fixForNamespace(path);
      byte[] responseData = null;
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), this.watching), (CuratorEvent)null);
      } else {
         responseData = this.pathInForeground(path);
      }

      return responseData;
   }

   private byte[] pathInForeground(final String path) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetDataBuilderImpl-Foreground");
      byte[] responseData = (byte[])RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public byte[] call() throws Exception {
            byte[] responseData;
            if (GetDataBuilderImpl.this.watching.isWatched()) {
               responseData = GetDataBuilderImpl.this.client.getZooKeeper().getData(path, true, GetDataBuilderImpl.this.responseStat);
            } else {
               responseData = GetDataBuilderImpl.this.client.getZooKeeper().getData(path, GetDataBuilderImpl.this.watching.getWatcher(path), GetDataBuilderImpl.this.responseStat);
               GetDataBuilderImpl.this.watching.commitWatcher(Code.OK.intValue(), false);
            }

            return responseData;
         }
      });
      trace.setResponseBytesLength(responseData).setPath(path).setWithWatcher(this.watching.hasWatcher()).setStat(this.responseStat).commit();
      return this.decompress ? this.client.getCompressionProvider().decompress(path, responseData) : responseData;
   }
}
