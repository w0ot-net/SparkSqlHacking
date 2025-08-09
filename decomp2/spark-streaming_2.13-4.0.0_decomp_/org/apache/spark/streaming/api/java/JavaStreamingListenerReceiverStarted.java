package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t!#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'o\u0015;beR,GM\u0003\u0002\u0007\u000f\u0005!!.\u0019<b\u0015\tA\u0011\"A\u0002ba&T!AC\u0006\u0002\u0013M$(/Z1nS:<'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0014\u0007\u0001\u0011\u0002\u0004\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VM\u001a\t\u00033ii\u0011!B\u0005\u00037\u0015\u0011!DS1wCN#(/Z1nS:<G*[:uK:,'/\u0012<f]R\fAB]3dK&4XM]%oM>\u001c\u0001!F\u0001 !\tI\u0002%\u0003\u0002\"\u000b\t\u0001\"*\u0019<b%\u0016\u001cW-\u001b<fe&sgm\\\u0001\u000ee\u0016\u001cW-\u001b<fe&sgm\u001c\u0011\u0002\rqJg.\u001b;?)\t)c\u0005\u0005\u0002\u001a\u0001!)Ad\u0001a\u0001?\u0001"
)
public class JavaStreamingListenerReceiverStarted implements JavaStreamingListenerEvent {
   private final JavaReceiverInfo receiverInfo;

   public JavaReceiverInfo receiverInfo() {
      return this.receiverInfo;
   }

   public JavaStreamingListenerReceiverStarted(final JavaReceiverInfo receiverInfo) {
      this.receiverInfo = receiverInfo;
   }
}
