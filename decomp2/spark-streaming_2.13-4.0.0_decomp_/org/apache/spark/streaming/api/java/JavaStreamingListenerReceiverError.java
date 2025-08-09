package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t\u0011#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'/\u0012:s_JT!AB\u0004\u0002\t)\fg/\u0019\u0006\u0003\u0011%\t1!\u00199j\u0015\tQ1\"A\u0005tiJ,\u0017-\\5oO*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xmE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005)\u0011BA\u000e\u0006\u0005iQ\u0015M^1TiJ,\u0017-\\5oO2K7\u000f^3oKJ,e/\u001a8u\u00031\u0011XmY3jm\u0016\u0014\u0018J\u001c4p\u0007\u0001)\u0012a\b\t\u00033\u0001J!!I\u0003\u0003!)\u000bg/\u0019*fG\u0016Lg/\u001a:J]\u001a|\u0017!\u0004:fG\u0016Lg/\u001a:J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003K\u0019\u0002\"!\u0007\u0001\t\u000bq\u0019\u0001\u0019A\u0010"
)
public class JavaStreamingListenerReceiverError implements JavaStreamingListenerEvent {
   private final JavaReceiverInfo receiverInfo;

   public JavaReceiverInfo receiverInfo() {
      return this.receiverInfo;
   }

   public JavaStreamingListenerReceiverError(final JavaReceiverInfo receiverInfo) {
      this.receiverInfo = receiverInfo;
   }
}
