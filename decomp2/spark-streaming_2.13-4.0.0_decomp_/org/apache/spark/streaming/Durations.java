package org.apache.spark.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:QAB\u0004\t\u0002A1QAE\u0004\t\u0002MAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002uAQ!J\u0001\u0005\u0002\u0019BQ\u0001K\u0001\u0005\u0002%\n\u0011\u0002R;sCRLwN\\:\u000b\u0005!I\u0011!C:ue\u0016\fW.\u001b8h\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7\u0001\u0001\t\u0003#\u0005i\u0011a\u0002\u0002\n\tV\u0014\u0018\r^5p]N\u001c\"!\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0001#\u0001\u0007nS2d\u0017n]3d_:$7\u000f\u0006\u0002\u001fCA\u0011\u0011cH\u0005\u0003A\u001d\u0011\u0001\u0002R;sCRLwN\u001c\u0005\u00069\r\u0001\rA\t\t\u0003+\rJ!\u0001\n\f\u0003\t1{gnZ\u0001\bg\u0016\u001cwN\u001c3t)\tqr\u0005C\u0003&\t\u0001\u0007!%A\u0004nS:,H/Z:\u0015\u0005yQ\u0003\"\u0002\u0015\u0006\u0001\u0004\u0011\u0003"
)
public final class Durations {
   public static Duration minutes(final long minutes) {
      return Durations$.MODULE$.minutes(minutes);
   }

   public static Duration seconds(final long seconds) {
      return Durations$.MODULE$.seconds(seconds);
   }

   public static Duration milliseconds(final long milliseconds) {
      return Durations$.MODULE$.milliseconds(milliseconds);
   }
}
