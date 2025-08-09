package org.apache.curator.framework.imps;

import java.util.List;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

class BackgroundSyncImpl implements BackgroundOperation {
   private final CuratorFrameworkImpl client;
   private final Object context;

   BackgroundSyncImpl(CuratorFrameworkImpl client, Object context) {
      this.client = client;
      this.context = context;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.SYNC;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("BackgroundSyncImpl");
      final String data = (String)operationAndData.getData();
      this.client.getZooKeeper().sync(data, new AsyncCallback.VoidCallback() {
         public void processResult(int rc, String path, Object ctx) {
            trace.setReturnCode(rc).setRequestBytesLength(data).commit();
            CuratorEventImpl event = new CuratorEventImpl(BackgroundSyncImpl.this.client, CuratorEventType.SYNC, rc, path, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
            BackgroundSyncImpl.this.client.processBackgroundOperation(operationAndData, event);
         }
      }, this.context);
   }
}
