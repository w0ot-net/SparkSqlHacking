package org.apache.spark.streaming.api.java;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005?!)1\u0005\u0001C\u0001I\t)#*\u0019<b'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u00148\u000b\u001e:fC6LgnZ*uCJ$X\r\u001a\u0006\u0003\r\u001d\tAA[1wC*\u0011\u0001\"C\u0001\u0004CBL'B\u0001\u0006\f\u0003%\u0019HO]3b[&twM\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005eQR\"A\u0003\n\u0005m)!A\u0007&bm\u0006\u001cFO]3b[&tw\rT5ti\u0016tWM]#wK:$\u0018\u0001\u0002;j[\u0016\u001c\u0001!F\u0001 !\t\u0019\u0002%\u0003\u0002\")\t!Aj\u001c8h\u0003\u0015!\u0018.\\3!\u0003\u0019a\u0014N\\5u}Q\u0011QE\n\t\u00033\u0001AQ\u0001H\u0002A\u0002}\u0001"
)
public class JavaStreamingListenerStreamingStarted implements JavaStreamingListenerEvent {
   private final long time;

   public long time() {
      return this.time;
   }

   public JavaStreamingListenerStreamingStarted(final long time) {
      this.time = time;
   }
}
