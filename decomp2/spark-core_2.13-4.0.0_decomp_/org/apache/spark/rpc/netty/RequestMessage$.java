package org.apache.spark.rpc.netty;

import java.io.DataInputStream;
import java.nio.ByteBuffer;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcEndpointAddress;
import org.apache.spark.util.ByteBufferInputStream;
import scala.reflect.ClassTag.;

public final class RequestMessage$ {
   public static final RequestMessage$ MODULE$ = new RequestMessage$();

   private RpcAddress readRpcAddress(final DataInputStream in) {
      boolean hasRpcAddress = in.readBoolean();
      return hasRpcAddress ? RpcAddress$.MODULE$.apply(in.readUTF(), in.readInt()) : null;
   }

   public RequestMessage apply(final NettyRpcEnv nettyEnv, final TransportClient client, final ByteBuffer bytes) {
      ByteBufferInputStream bis = new ByteBufferInputStream(bytes);
      DataInputStream in = new DataInputStream(bis);

      RequestMessage var10000;
      try {
         RpcAddress senderAddress = this.readRpcAddress(in);
         RpcEndpointAddress endpointAddress = new RpcEndpointAddress(this.readRpcAddress(in), in.readUTF());
         NettyRpcEndpointRef ref = new NettyRpcEndpointRef(nettyEnv.conf(), endpointAddress, nettyEnv);
         ref.client_$eq(client);
         var10000 = new RequestMessage(senderAddress, ref, nettyEnv.deserialize(client, bytes, .MODULE$.Nothing()));
      } finally {
         in.close();
      }

      return var10000;
   }

   private RequestMessage$() {
   }
}
