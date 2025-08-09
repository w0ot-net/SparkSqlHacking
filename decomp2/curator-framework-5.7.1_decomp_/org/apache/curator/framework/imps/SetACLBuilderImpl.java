package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.ACLable;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.SetACLBuilder;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class SetACLBuilderImpl implements SetACLBuilder, BackgroundPathable, BackgroundOperation, ErrorListenerPathable {
   private final CuratorFrameworkImpl client;
   private ACLing acling;
   private Backgrounding backgrounding;
   private int version;

   SetACLBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.backgrounding = new Backgrounding();
      this.acling = new ACLing(client.getAclProvider());
      this.version = -1;
   }

   public SetACLBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, List aclList, int version) {
      this.client = client;
      this.acling = new ACLing(client.getAclProvider(), aclList);
      this.version = version;
      this.backgrounding = backgrounding;
   }

   public BackgroundPathable withACL(List aclList) {
      this.acling = new ACLing(this.client.getAclProvider(), aclList, false);
      return this;
   }

   public ACLable withVersion(int version) {
      this.version = version;
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

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
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

   public Stat forPath(String path) throws Exception {
      String fixedPath = this.client.fixForNamespace(path);
      List<ACL> aclList = this.acling.getAclList(fixedPath);
      this.client.getSchemaSet().getSchema(path).validateGeneral(path, (byte[])null, aclList);
      Stat resultStat = null;
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, fixedPath, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null), (CuratorEvent)null);
      } else {
         resultStat = this.pathInForeground(fixedPath, aclList);
      }

      return resultStat;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.SET_ACL;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("SetACLBuilderImpl-Background");
         String path = (String)operationAndData.getData();
         this.client.getZooKeeper().setACL(path, this.acling.getAclList(path), this.version, new AsyncCallback.StatCallback() {
            public void processResult(int rc, String path, Object ctx, Stat stat) {
               trace.setReturnCode(rc).setPath(path).setStat(stat).commit();
               CuratorEvent event = new CuratorEventImpl(SetACLBuilderImpl.this.client, CuratorEventType.SET_ACL, rc, path, (String)null, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               SetACLBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         }, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   private Stat pathInForeground(final String path, final List aclList) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("SetACLBuilderImpl-Foreground");
      Stat resultStat = (Stat)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public Stat call() throws Exception {
            return SetACLBuilderImpl.this.client.getZooKeeper().setACL(path, aclList, SetACLBuilderImpl.this.version);
         }
      });
      trace.setPath(path).setStat(resultStat).commit();
      return resultStat;
   }
}
