package org.apache.spark.rpc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001\u0002B\u0003\u0011\u0002G\u0005q!\u0004\u0005\u0006)\u00011\tA\u0006\u0005\u0006?\u00011\t\u0001\t\u0005\u0006_\u00011\t\u0001\r\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0015\t1q!A\u0002sa\u000eT!\u0001C\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Y\u0011AB1qC\u000eDWMC\u0001\r\u0003\ry'oZ\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0017!\u0002:fa2L8\u0001\u0001\u000b\u0003/i\u0001\"a\u0004\r\n\u0005e\u0001\"\u0001B+oSRDQaG\u0001A\u0002q\t\u0001B]3ta>t7/\u001a\t\u0003\u001fuI!A\b\t\u0003\u0007\u0005s\u00170A\u0006tK:$g)Y5mkJ,GCA\f\"\u0011\u0015\u0011#\u00011\u0001$\u0003\u0005)\u0007C\u0001\u0013-\u001d\t)#F\u0004\u0002'S5\tqE\u0003\u0002)+\u00051AH]8pizJ\u0011!E\u0005\u0003WA\tq\u0001]1dW\u0006<W-\u0003\u0002.]\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0003WA\tQb]3oI\u0016\u0014\u0018\t\u001a3sKN\u001cX#A\u0019\u0011\u0005I\u001aT\"A\u0003\n\u0005Q*!A\u0003*qG\u0006#GM]3tg\u0002"
)
public interface RpcCallContext {
   void reply(final Object response);

   void sendFailure(final Throwable e);

   RpcAddress senderAddress();
}
