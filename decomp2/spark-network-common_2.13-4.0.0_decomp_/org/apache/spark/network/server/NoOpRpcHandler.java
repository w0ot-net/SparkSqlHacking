package org.apache.spark.network.server;

import java.nio.ByteBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;

public class NoOpRpcHandler extends RpcHandler {
   private final StreamManager streamManager = new OneForOneStreamManager();

   public void receive(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      throw new UnsupportedOperationException("Cannot handle messages");
   }

   public StreamManager getStreamManager() {
      return this.streamManager;
   }
}
