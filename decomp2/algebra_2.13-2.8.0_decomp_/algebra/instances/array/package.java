package algebra.instances.array;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005)\u0011M\u001d:bs*\u0011q\u0001C\u0001\nS:\u001cH/\u00198dKNT\u0011!C\u0001\bC2<WM\u0019:b\u0007\u0001\u0001\"\u0001D\u0001\u000e\u0003\u0011\u0011q\u0001]1dW\u0006<WmE\u0002\u0002\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u00051\u0011B\u0001\r\u0007\u00059\t%O]1z\u0013:\u001cH/\u00198dKN\fa\u0001P5oSRtD#A\u0006"
)
public final class package {
   public static PartialOrder arrayPartialOrder(final PartialOrder evidence$3) {
      return package$.MODULE$.arrayPartialOrder(evidence$3);
   }

   public static Order arrayOrder(final Order evidence$2) {
      return package$.MODULE$.arrayOrder(evidence$2);
   }

   public static Eq arrayEq(final Eq evidence$1) {
      return package$.MODULE$.arrayEq(evidence$1);
   }
}
