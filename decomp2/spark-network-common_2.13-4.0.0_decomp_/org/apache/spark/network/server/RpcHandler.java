package org.apache.spark.network.server;

import java.nio.ByteBuffer;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.client.MergedBlockMetaResponseCallback;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.protocol.MergedBlockMetaRequest;

public abstract class RpcHandler {
   private static final RpcResponseCallback ONE_WAY_CALLBACK = new OneWayRpcCallback();
   private static final MergedBlockMetaReqHandler NOOP_MERGED_BLOCK_META_REQ_HANDLER = new NoopMergedBlockMetaReqHandler();

   public abstract void receive(TransportClient var1, ByteBuffer var2, RpcResponseCallback var3);

   public StreamCallbackWithID receiveStream(TransportClient client, ByteBuffer messageHeader, RpcResponseCallback callback) {
      throw new UnsupportedOperationException();
   }

   public abstract StreamManager getStreamManager();

   public void receive(TransportClient client, ByteBuffer message) {
      this.receive(client, message, ONE_WAY_CALLBACK);
   }

   public MergedBlockMetaReqHandler getMergedBlockMetaReqHandler() {
      return NOOP_MERGED_BLOCK_META_REQ_HANDLER;
   }

   public void channelActive(TransportClient client) {
   }

   public void channelInactive(TransportClient client) {
   }

   public void exceptionCaught(Throwable cause, TransportClient client) {
   }

   private static class OneWayRpcCallback implements RpcResponseCallback {
      private static final SparkLogger logger = SparkLoggerFactory.getLogger(OneWayRpcCallback.class);

      public void onSuccess(ByteBuffer response) {
         logger.warn("Response provided for one-way RPC.");
      }

      public void onFailure(Throwable e) {
         logger.error("Error response provided for one-way RPC.", e);
      }
   }

   private static class NoopMergedBlockMetaReqHandler implements MergedBlockMetaReqHandler {
      public void receiveMergeBlockMetaReq(TransportClient client, MergedBlockMetaRequest mergedBlockMetaRequest, MergedBlockMetaResponseCallback callback) {
      }
   }

   public interface MergedBlockMetaReqHandler {
      void receiveMergeBlockMetaReq(TransportClient var1, MergedBlockMetaRequest var2, MergedBlockMetaResponseCallback var3);
   }
}
