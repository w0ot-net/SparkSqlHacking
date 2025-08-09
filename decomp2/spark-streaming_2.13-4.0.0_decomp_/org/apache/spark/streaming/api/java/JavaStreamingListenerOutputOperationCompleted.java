package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\ti#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014x*\u001e;qkR|\u0005/\u001a:bi&|gnQ8na2,G/\u001a3\u000b\u0005\u00199\u0011\u0001\u00026bm\u0006T!\u0001C\u0005\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u000b\u0017\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\u0006\u0013\tYRA\u0001\u000eKCZ\f7\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:Fm\u0016tG/A\npkR\u0004X\u000f^(qKJ\fG/[8o\u0013:4wn\u0001\u0001\u0016\u0003}\u0001\"!\u0007\u0011\n\u0005\u0005*!a\u0006&bm\u0006|U\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4p\u0003QyW\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4pA\u00051A(\u001b8jiz\"\"!\n\u0014\u0011\u0005e\u0001\u0001\"\u0002\u000f\u0004\u0001\u0004y\u0002"
)
public class JavaStreamingListenerOutputOperationCompleted implements JavaStreamingListenerEvent {
   private final JavaOutputOperationInfo outputOperationInfo;

   public JavaOutputOperationInfo outputOperationInfo() {
      return this.outputOperationInfo;
   }

   public JavaStreamingListenerOutputOperationCompleted(final JavaOutputOperationInfo outputOperationInfo) {
      this.outputOperationInfo = outputOperationInfo;
   }
}
