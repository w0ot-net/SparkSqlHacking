package cats.kernel.instances;

import cats.kernel.LowerBounded;
import cats.kernel.UpperBounded;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.duration.FiniteDuration.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0003\u0006C\u0003*\u0001\u0011\u0005\u0003FA\u000bGS:LG/\u001a#ve\u0006$\u0018n\u001c8C_VtG-\u001a3\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u0019B\u0001A\u0007\u0014?A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u000b\u0018\u001b\u00059\u0011B\u0001\f\b\u00051aun^3s\u0005>,h\u000eZ3e!\tAR$D\u0001\u001a\u0015\tQ2$\u0001\u0005ekJ\fG/[8o\u0015\tar\"\u0001\u0006d_:\u001cWO\u001d:f]RL!AH\r\u0003\u001d\u0019Kg.\u001b;f\tV\u0014\u0018\r^5p]B\u0019A\u0003I\f\n\u0005\u0005:!\u0001D+qa\u0016\u0014(i\\;oI\u0016$\u0017A\u0002\u0013j]&$H\u0005F\u0001%!\tqQ%\u0003\u0002'\u001f\t!QK\\5u\u0003!i\u0017N\u001c\"pk:$W#A\f\u0002\u00115\f\u0007PQ8v]\u0012\u0004"
)
public interface FiniteDurationBounded extends LowerBounded, UpperBounded {
   // $FF: synthetic method
   static FiniteDuration minBound$(final FiniteDurationBounded $this) {
      return $this.minBound();
   }

   default FiniteDuration minBound() {
      return .MODULE$.apply(-Long.MAX_VALUE, TimeUnit.NANOSECONDS);
   }

   // $FF: synthetic method
   static FiniteDuration maxBound$(final FiniteDurationBounded $this) {
      return $this.maxBound();
   }

   default FiniteDuration maxBound() {
      return .MODULE$.apply(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
   }

   static void $init$(final FiniteDurationBounded $this) {
   }
}
