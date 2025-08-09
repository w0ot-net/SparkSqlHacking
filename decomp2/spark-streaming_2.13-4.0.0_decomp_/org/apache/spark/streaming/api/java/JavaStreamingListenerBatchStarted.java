package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t\t#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()\u0019;dQN#\u0018M\u001d;fI*\u0011aaB\u0001\u0005U\u00064\u0018M\u0003\u0002\t\u0013\u0005\u0019\u0011\r]5\u000b\u0005)Y\u0011!C:ue\u0016\fW.\u001b8h\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003\u0015I!aG\u0003\u00035)\u000bg/Y*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0002\u0013\t\fGo\u00195J]\u001a|7\u0001A\u000b\u0002?A\u0011\u0011\u0004I\u0005\u0003C\u0015\u0011QBS1wC\n\u000bGo\u00195J]\u001a|\u0017A\u00032bi\u000eD\u0017J\u001c4pA\u00051A(\u001b8jiz\"\"!\n\u0014\u0011\u0005e\u0001\u0001\"\u0002\u000f\u0004\u0001\u0004y\u0002"
)
public class JavaStreamingListenerBatchStarted implements JavaStreamingListenerEvent {
   private final JavaBatchInfo batchInfo;

   public JavaBatchInfo batchInfo() {
      return this.batchInfo;
   }

   public JavaStreamingListenerBatchStarted(final JavaBatchInfo batchInfo) {
      this.batchInfo = batchInfo;
   }
}
