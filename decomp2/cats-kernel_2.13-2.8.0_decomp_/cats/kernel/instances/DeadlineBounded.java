package cats.kernel.instances;

import cats.kernel.LowerBounded;
import cats.kernel.UpperBounded;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Deadline;
import scala.concurrent.duration.Deadline.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0003\u0006C\u0003*\u0001\u0011\u0005\u0003FA\bEK\u0006$G.\u001b8f\u0005>,h\u000eZ3e\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u00011\u0003\u0002\u0001\u000e'}\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007c\u0001\u000b\u0016/5\tq!\u0003\u0002\u0017\u000f\taAj\\<fe\n{WO\u001c3fIB\u0011\u0001$H\u0007\u00023)\u0011!dG\u0001\tIV\u0014\u0018\r^5p]*\u0011AdD\u0001\u000bG>t7-\u001e:sK:$\u0018B\u0001\u0010\u001a\u0005!!U-\u00193mS:,\u0007c\u0001\u000b!/%\u0011\u0011e\u0002\u0002\r+B\u0004XM\u001d\"pk:$W\rZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0011\u0002\"AD\u0013\n\u0005\u0019z!\u0001B+oSR\f\u0001\"\\5o\u0005>,h\u000eZ\u000b\u0002/\u0005AQ.\u0019=C_VtG\r"
)
public interface DeadlineBounded extends LowerBounded, UpperBounded {
   // $FF: synthetic method
   static Deadline minBound$(final DeadlineBounded $this) {
      return $this.minBound();
   }

   default Deadline minBound() {
      return .MODULE$.apply(scala.concurrent.duration.FiniteDuration..MODULE$.apply(-Long.MAX_VALUE, TimeUnit.NANOSECONDS));
   }

   // $FF: synthetic method
   static Deadline maxBound$(final DeadlineBounded $this) {
      return $this.maxBound();
   }

   default Deadline maxBound() {
      return .MODULE$.apply(scala.concurrent.duration.FiniteDuration..MODULE$.apply(Long.MAX_VALUE, TimeUnit.NANOSECONDS));
   }

   static void $init$(final DeadlineBounded $this) {
   }
}
