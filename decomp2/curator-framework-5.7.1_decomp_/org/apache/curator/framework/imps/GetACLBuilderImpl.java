package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.GetACLBuilder;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class GetACLBuilderImpl implements GetACLBuilder, BackgroundOperation, ErrorListenerPathable {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding;
   private Stat responseStat;

   GetACLBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.backgrounding = new Backgrounding();
      this.responseStat = new Stat();
   }

   public GetACLBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, Stat responseStat) {
      this.client = client;
      this.backgrounding = backgrounding;
      this.responseStat = responseStat;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
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

   public ErrorListenerPathable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public Pathable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public Pathable storingStatIn(Stat stat) {
      this.responseStat = stat;
      return this;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.GET_ACL;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetACLBuilderImpl-Background");
         AsyncCallback.ACLCallback callback = new AsyncCallback.ACLCallback() {
            public void processResult(int rc, String path, Object ctx, List acl, Stat stat) {
               trace.setReturnCode(rc).setPath(path).setStat(stat).commit();
               CuratorEventImpl event = new CuratorEventImpl(GetACLBuilderImpl.this.client, CuratorEventType.GET_ACL, rc, path, (String)null, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, acl, (List)null);
               GetACLBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         this.client.getZooKeeper().getACL((String)operationAndData.getData(), this.responseStat, callback, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   public List forPath(String path) throws Exception {
      path = this.client.fixForNamespace(path);
      List<ACL> result = null;
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null), (CuratorEvent)null);
      } else {
         result = this.pathInForeground(path);
      }

      return result;
   }

   private List pathInForeground(final String path) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetACLBuilderImpl-Foreground");
      List<ACL> result = (List)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public List call() throws Exception {
            return GetACLBuilderImpl.this.client.getZooKeeper().getACL(path, GetACLBuilderImpl.this.responseStat);
         }
      });
      trace.setPath(path).setStat(this.responseStat).commit();
      return result;
   }
}
