package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t\u0019#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()\u0019;dQN+(-\\5ui\u0016$'B\u0001\u0004\b\u0003\u0011Q\u0017M^1\u000b\u0005!I\u0011aA1qS*\u0011!bC\u0001\ngR\u0014X-Y7j]\u001eT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a55\tQ!\u0003\u0002\u001c\u000b\tQ\"*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014XI^3oi\u0006I!-\u0019;dQ&sgm\\\u0002\u0001+\u0005y\u0002CA\r!\u0013\t\tSAA\u0007KCZ\f')\u0019;dQ&sgm\\\u0001\u000bE\u0006$8\r[%oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002&MA\u0011\u0011\u0004\u0001\u0005\u00069\r\u0001\ra\b"
)
public class JavaStreamingListenerBatchSubmitted implements JavaStreamingListenerEvent {
   private final JavaBatchInfo batchInfo;

   public JavaBatchInfo batchInfo() {
      return this.batchInfo;
   }

   public JavaStreamingListenerBatchSubmitted(final JavaBatchInfo batchInfo) {
      this.batchInfo = batchInfo;
   }
}
