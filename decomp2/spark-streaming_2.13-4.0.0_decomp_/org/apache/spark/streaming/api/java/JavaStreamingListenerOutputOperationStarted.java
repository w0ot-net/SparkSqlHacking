package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\tY#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014x*\u001e;qkR|\u0005/\u001a:bi&|gn\u0015;beR,GM\u0003\u0002\u0007\u000f\u0005!!.\u0019<b\u0015\tA\u0011\"A\u0002ba&T!AC\u0006\u0002\u0013M$(/Z1nS:<'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0014\u0007\u0001\u0011\u0002\u0004\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VM\u001a\t\u00033ii\u0011!B\u0005\u00037\u0015\u0011!DS1wCN#(/Z1nS:<G*[:uK:,'/\u0012<f]R\f1c\\;uaV$x\n]3sCRLwN\\%oM>\u001c\u0001!F\u0001 !\tI\u0002%\u0003\u0002\"\u000b\t9\"*\u0019<b\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\\\u0001\u0015_V$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\u001c\u0011\u0002\rqJg.\u001b;?)\t)c\u0005\u0005\u0002\u001a\u0001!)Ad\u0001a\u0001?\u0001"
)
public class JavaStreamingListenerOutputOperationStarted implements JavaStreamingListenerEvent {
   private final JavaOutputOperationInfo outputOperationInfo;

   public JavaOutputOperationInfo outputOperationInfo() {
      return this.outputOperationInfo;
   }

   public JavaStreamingListenerOutputOperationStarted(final JavaOutputOperationInfo outputOperationInfo) {
      this.outputOperationInfo = outputOperationInfo;
   }
}
