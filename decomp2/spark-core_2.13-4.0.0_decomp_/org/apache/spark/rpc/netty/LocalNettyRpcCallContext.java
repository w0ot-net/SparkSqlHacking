package org.apache.spark.rpc.netty;

import org.apache.spark.rpc.RpcAddress;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2Q!\u0002\u0004\u0001\rAA\u0011\"\u0006\u0001\u0003\u0002\u0003\u0006IaF\u000e\t\u0011q\u0001!\u0011!Q\u0001\nuAQ!\u000b\u0001\u0005\u0002)BQA\f\u0001\u0005R=\u0012\u0001\u0004T8dC2tU\r\u001e;z%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0015\t9\u0001\"A\u0003oKR$\u0018P\u0003\u0002\n\u0015\u0005\u0019!\u000f]2\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I\u0019R\"\u0001\u0004\n\u0005Q1!a\u0005(fiRL(\u000b]2DC2d7i\u001c8uKb$\u0018!D:f]\u0012,'/\u00113ee\u0016\u001c8o\u0001\u0001\u0011\u0005aIR\"\u0001\u0005\n\u0005iA!A\u0003*qG\u0006#GM]3tg&\u0011QcE\u0001\u0002aB\u0019adI\u0013\u000e\u0003}Q!\u0001I\u0011\u0002\u0015\r|gnY;se\u0016tGOC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!sDA\u0004Qe>l\u0017n]3\u0011\u0005\u0019:S\"A\u0011\n\u0005!\n#aA!os\u00061A(\u001b8jiz\"2a\u000b\u0017.!\t\u0011\u0002\u0001C\u0003\u0016\u0007\u0001\u0007q\u0003C\u0003\u001d\u0007\u0001\u0007Q$\u0001\u0003tK:$GC\u0001\u00194!\t1\u0013'\u0003\u00023C\t!QK\\5u\u0011\u0015!D\u00011\u0001&\u0003\u001diWm]:bO\u0016\u0004"
)
public class LocalNettyRpcCallContext extends NettyRpcCallContext {
   private final Promise p;

   public void send(final Object message) {
      this.p.success(message);
   }

   public LocalNettyRpcCallContext(final RpcAddress senderAddress, final Promise p) {
      super(senderAddress);
      this.p = p;
   }
}
