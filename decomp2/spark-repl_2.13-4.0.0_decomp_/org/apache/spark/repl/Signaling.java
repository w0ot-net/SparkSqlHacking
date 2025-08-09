package org.apache.spark.repl;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015:a\u0001B\u0003\t\u0002\u0015iaAB\b\u0006\u0011\u0003)\u0001\u0003C\u0003\u001e\u0003\u0011\u0005q\u0004C\u0003!\u0003\u0011\u0005\u0011%A\u0005TS\u001et\u0017\r\\5oO*\u0011aaB\u0001\u0005e\u0016\u0004HN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h!\tq\u0011!D\u0001\u0006\u0005%\u0019\u0016n\u001a8bY&twmE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\b\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u000f\u001a\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001b\u0005\t2-\u00198dK2|e.\u00138uKJ\u0014X\u000f\u001d;\u0015\u0003\t\u0002\"AE\u0012\n\u0005\u0011\u001a\"\u0001B+oSR\u0004"
)
public final class Signaling {
   public static void cancelOnInterrupt() {
      Signaling$.MODULE$.cancelOnInterrupt();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Signaling$.MODULE$.LogStringContext(sc);
   }
}
