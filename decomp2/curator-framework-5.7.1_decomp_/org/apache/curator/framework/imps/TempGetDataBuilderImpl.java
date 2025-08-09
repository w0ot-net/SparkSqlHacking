package org.apache.curator.framework.imps;

import java.util.concurrent.Callable;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.StatPathable;
import org.apache.curator.framework.api.TempGetDataBuilder;
import org.apache.zookeeper.data.Stat;

class TempGetDataBuilderImpl implements TempGetDataBuilder {
   private final CuratorFrameworkImpl client;
   private Stat responseStat;
   private boolean decompress;

   TempGetDataBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.responseStat = null;
      this.decompress = false;
   }

   public StatPathable decompressed() {
      this.decompress = true;
      return this;
   }

   public Pathable storingStatIn(Stat stat) {
      this.responseStat = stat;
      return this;
   }

   public byte[] forPath(String path) throws Exception {
      final String localPath = this.client.fixForNamespace(path);
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("GetDataBuilderImpl-Foreground");
      byte[] responseData = (byte[])RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public byte[] call() throws Exception {
            return TempGetDataBuilderImpl.this.client.getZooKeeper().getData(localPath, false, TempGetDataBuilderImpl.this.responseStat);
         }
      });
      trace.setResponseBytesLength(responseData).setPath(path).setStat(this.responseStat).commit();
      return this.decompress ? this.client.getCompressionProvider().decompress(path, responseData) : responseData;
   }
}
