package org.apache.spark;

import org.apache.spark.annotation.Experimental;
import scala.reflect.ScalaSignature;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005M2A\u0001B\u0003\u0001\u0019!A1\u0003\u0001BC\u0002\u0013\u0005A\u0003\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u0016\u0011\u0019\t\u0003\u0001\"\u0001\u0006E\ty!)\u0019:sS\u0016\u0014H+Y:l\u0013:4wN\u0003\u0002\u0007\u000f\u0005)1\u000f]1sW*\u0011\u0001\"C\u0001\u0007CB\f7\r[3\u000b\u0003)\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g\u0003\u001d\tG\r\u001a:fgN,\u0012!\u0006\t\u0003-uq!aF\u000e\u0011\u0005ayQ\"A\r\u000b\u0005iY\u0011A\u0002\u001fs_>$h(\u0003\u0002\u001d\u001f\u00051\u0001K]3eK\u001aL!AH\u0010\u0003\rM#(/\u001b8h\u0015\tar\"\u0001\u0005bI\u0012\u0014Xm]:!\u0003\u0019a\u0014N\\5u}Q\u00111%\n\t\u0003I\u0001i\u0011!\u0002\u0005\u0006'\r\u0001\r!\u0006\u0015\u0003\u0001\u001d\u0002\"\u0001K\u0016\u000e\u0003%R!AK\u0003\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002-S\taQ\t\u001f9fe&lWM\u001c;bY\"\u001a\u0001AL\u0019\u0011\u0005!z\u0013B\u0001\u0019*\u0005\u0015\u0019\u0016N\\2fC\u0005\u0011\u0014!\u0002\u001a/i9\u0002\u0004"
)
public class BarrierTaskInfo {
   private final String address;

   public String address() {
      return this.address;
   }

   public BarrierTaskInfo(final String address) {
      this.address = address;
   }
}
