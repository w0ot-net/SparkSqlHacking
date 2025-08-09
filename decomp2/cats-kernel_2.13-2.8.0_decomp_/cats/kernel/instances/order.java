package cats.kernel.instances;

import cats.kernel.Order;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ\u0001G\u0001\u0005\u0002e\tQa\u001c:eKJT!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8\u0001\u0001\t\u0003\u0019\u0005i\u0011\u0001\u0002\u0002\u0006_J$WM]\n\u0004\u0003=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\r-%\u0011q\u0003\u0002\u0002\u000f\u001fJ$WM]%ogR\fgnY3t\u0003\u0019a\u0014N\\5u}Q\t1\u0002"
)
public final class order {
   public static Ordering catsKernelOrderingForOrder(final Order ev) {
      return order$.MODULE$.catsKernelOrderingForOrder(ev);
   }
}
