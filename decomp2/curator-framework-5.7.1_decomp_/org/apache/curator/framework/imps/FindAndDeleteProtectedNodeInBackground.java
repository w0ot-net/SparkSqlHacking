package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FindAndDeleteProtectedNodeInBackground implements BackgroundOperation {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFrameworkImpl client;
   private final String namespaceAdjustedParentPath;
   private final String protectedId;
   @VisibleForTesting
   static final AtomicBoolean debugInsertError = new AtomicBoolean(false);

   FindAndDeleteProtectedNodeInBackground(CuratorFrameworkImpl client, String namespaceAdjustedParentPath, String protectedId) {
      this.client = client;
      this.namespaceAdjustedParentPath = namespaceAdjustedParentPath;
      this.protectedId = protectedId;
   }

   void execute() {
      OperationAndData.ErrorCallback<Void> errorCallback = new OperationAndData.ErrorCallback() {
         public void retriesExhausted(OperationAndData operationAndData) {
            operationAndData.reset();
            FindAndDeleteProtectedNodeInBackground.this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
         }
      };
      OperationAndData<Void> operationAndData = new OperationAndData(this, (Object)null, (BackgroundCallback)null, errorCallback, (Object)null, (Watching)null);
      this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.CHILDREN;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("FindAndDeleteProtectedNodeInBackground");
      AsyncCallback.Children2Callback callback = new AsyncCallback.Children2Callback() {
         public void processResult(int rc, String path, Object o, List strings, Stat stat) {
            trace.setReturnCode(rc).setPath(path).setStat(stat).commit();
            if (FindAndDeleteProtectedNodeInBackground.debugInsertError.compareAndSet(true, false)) {
               rc = Code.CONNECTIONLOSS.intValue();
            }

            if (rc == Code.OK.intValue()) {
               String node = CreateBuilderImpl.findNode(strings, "/", FindAndDeleteProtectedNodeInBackground.this.protectedId);
               if (node != null) {
                  try {
                     String deletePath = FindAndDeleteProtectedNodeInBackground.this.client.unfixForNamespace(ZKPaths.makePath(FindAndDeleteProtectedNodeInBackground.this.namespaceAdjustedParentPath, node));
                     ((ErrorListenerPathable)((ChildrenDeletable)FindAndDeleteProtectedNodeInBackground.this.client.delete().guaranteed()).inBackground()).forPath(deletePath);
                  } catch (Exception e) {
                     ThreadUtils.checkInterrupted(e);
                     FindAndDeleteProtectedNodeInBackground.this.log.error("Could not start guaranteed delete for node: " + node);
                     rc = Code.CONNECTIONLOSS.intValue();
                  }
               }
            }

            if (rc != Code.OK.intValue()) {
               CuratorEventImpl event = new CuratorEventImpl(FindAndDeleteProtectedNodeInBackground.this.client, CuratorEventType.CHILDREN, rc, path, (String)null, o, stat, (byte[])null, strings, (WatchedEvent)null, (List)null, (List)null);
               FindAndDeleteProtectedNodeInBackground.this.client.processBackgroundOperation(operationAndData, event);
            }

         }
      };
      this.client.getZooKeeper().getChildren(this.namespaceAdjustedParentPath, false, callback, (Object)null);
   }
}
