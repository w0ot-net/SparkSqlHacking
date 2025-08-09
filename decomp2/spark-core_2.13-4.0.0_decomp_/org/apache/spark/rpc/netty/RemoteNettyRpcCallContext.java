package org.apache.spark.rpc.netty;

import java.nio.ByteBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.rpc.RpcAddress;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2QAB\u0004\u0001\u000fEA\u0001B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0005\t7\u0001\u0011\t\u0011)A\u00059!IA\u0005\u0001B\u0001B\u0003%Q%\u000b\u0005\u0006U\u0001!\ta\u000b\u0005\u0006a\u0001!\t&\r\u0002\u001a%\u0016lw\u000e^3OKR$\u0018P\u00159d\u0007\u0006dGnQ8oi\u0016DHO\u0003\u0002\t\u0013\u0005)a.\u001a;us*\u0011!bC\u0001\u0004eB\u001c'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0014\u0005\u0001\u0011\u0002CA\n\u0015\u001b\u00059\u0011BA\u000b\b\u0005MqU\r\u001e;z%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0003!qW\r\u001e;z\u000b:48\u0001\u0001\t\u0003'eI!AG\u0004\u0003\u00179+G\u000f^=Sa\u000e,eN^\u0001\tG\u0006dGNY1dWB\u0011QDI\u0007\u0002=)\u0011q\u0004I\u0001\u0007G2LWM\u001c;\u000b\u0005\u0005Z\u0011a\u00028fi^|'o[\u0005\u0003Gy\u00111C\u00159d%\u0016\u001c\bo\u001c8tK\u000e\u000bG\u000e\u001c2bG.\fQb]3oI\u0016\u0014\u0018\t\u001a3sKN\u001c\bC\u0001\u0014(\u001b\u0005I\u0011B\u0001\u0015\n\u0005)\u0011\u0006oY!eIJ,7o]\u0005\u0003IQ\ta\u0001P5oSRtD\u0003\u0002\u0017.]=\u0002\"a\u0005\u0001\t\u000bY!\u0001\u0019\u0001\r\t\u000bm!\u0001\u0019\u0001\u000f\t\u000b\u0011\"\u0001\u0019A\u0013\u0002\tM,g\u000e\u001a\u000b\u0003ea\u0002\"a\r\u001c\u000e\u0003QR\u0011!N\u0001\u0006g\u000e\fG.Y\u0005\u0003oQ\u0012A!\u00168ji\")\u0011(\u0002a\u0001u\u00059Q.Z:tC\u001e,\u0007CA\u001a<\u0013\taDGA\u0002B]f\u0004"
)
public class RemoteNettyRpcCallContext extends NettyRpcCallContext {
   private final NettyRpcEnv nettyEnv;
   private final RpcResponseCallback callback;

   public void send(final Object message) {
      ByteBuffer reply = this.nettyEnv.serialize(message);
      this.callback.onSuccess(reply);
   }

   public RemoteNettyRpcCallContext(final NettyRpcEnv nettyEnv, final RpcResponseCallback callback, final RpcAddress senderAddress) {
      super(senderAddress);
      this.nettyEnv = nettyEnv;
      this.callback = callback;
   }
}
