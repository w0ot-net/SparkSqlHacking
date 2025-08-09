package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Executor;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.SyncBuilder;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

public class SyncBuilderImpl implements SyncBuilder, BackgroundOperation, ErrorListenerPathable {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding = new Backgrounding();

   public SyncBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
   }

   public SyncBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding) {
      this.client = client;
      this.backgrounding = backgrounding;
   }

   public ErrorListenerPathable inBackground() {
      return this;
   }

   public ErrorListenerPathable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public Pathable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.SYNC;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("SyncBuilderImpl-Background");
         String path = (String)operationAndData.getData();
         String adjustedPath = this.client.fixForNamespace(path);
         AsyncCallback.VoidCallback voidCallback = new AsyncCallback.VoidCallback() {
            public void processResult(int rc, String path, Object ctx) {
               trace.setReturnCode(rc).setPath(path).commit();
               CuratorEvent event = new CuratorEventImpl(SyncBuilderImpl.this.client, CuratorEventType.SYNC, rc, path, path, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               SyncBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         this.client.getZooKeeper().sync(adjustedPath, voidCallback, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   public Void forPath(String path) throws Exception {
      OperationAndData<String> operationAndData = new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null);
      this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
      return null;
   }
}
