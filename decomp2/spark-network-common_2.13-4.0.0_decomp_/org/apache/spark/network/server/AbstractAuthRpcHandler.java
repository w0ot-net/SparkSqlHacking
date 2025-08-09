package org.apache.spark.network.server;

import java.nio.ByteBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.client.TransportClient;

public abstract class AbstractAuthRpcHandler extends RpcHandler {
   private final RpcHandler delegate;
   private boolean isAuthenticated;

   protected AbstractAuthRpcHandler(RpcHandler delegate) {
      this.delegate = delegate;
   }

   protected abstract boolean doAuthChallenge(TransportClient var1, ByteBuffer var2, RpcResponseCallback var3);

   public final void receive(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      if (this.isAuthenticated) {
         this.delegate.receive(client, message, callback);
      } else {
         this.isAuthenticated = this.doAuthChallenge(client, message, callback);
      }

   }

   public final void receive(TransportClient client, ByteBuffer message) {
      if (this.isAuthenticated) {
         this.delegate.receive(client, message);
      } else {
         throw new SecurityException("Unauthenticated call to receive().");
      }
   }

   public final StreamCallbackWithID receiveStream(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      if (this.isAuthenticated) {
         return this.delegate.receiveStream(client, message, callback);
      } else {
         throw new SecurityException("Unauthenticated call to receiveStream().");
      }
   }

   public StreamManager getStreamManager() {
      return this.delegate.getStreamManager();
   }

   public void channelActive(TransportClient client) {
      this.delegate.channelActive(client);
   }

   public void channelInactive(TransportClient client) {
      this.delegate.channelInactive(client);
   }

   public void exceptionCaught(Throwable cause, TransportClient client) {
      this.delegate.exceptionCaught(cause, client);
   }

   public boolean isAuthenticated() {
      return this.isAuthenticated;
   }

   public RpcHandler.MergedBlockMetaReqHandler getMergedBlockMetaReqHandler() {
      return this.delegate.getMergedBlockMetaReqHandler();
   }
}
