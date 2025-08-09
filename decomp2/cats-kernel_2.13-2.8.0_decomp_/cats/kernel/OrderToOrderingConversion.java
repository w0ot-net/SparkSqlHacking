package cats.kernel;

import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\rPe\u0012,'\u000fV8Pe\u0012,'/\u001b8h\u0007>tg/\u001a:tS>t'BA\u0003\u0007\u0003\u0019YWM\u001d8fY*\tq!\u0001\u0003dCR\u001c8\u0001A\n\u0003\u0001)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\tY1#\u0003\u0002\u0015\u0019\t!QK\\5u\u0003i\u0019\u0017\r^:LKJtW\r\\(sI\u0016\u0014\u0018N\\4G_J|%\u000fZ3s+\t9b\u0005\u0006\u0002\u0019_A\u0019\u0011$\t\u0013\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\t\u0003\u0019a$o\\8u}%\tQ\"\u0003\u0002!\u0019\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005!y%\u000fZ3sS:<'B\u0001\u0011\r!\t)c\u0005\u0004\u0001\u0005\u000b\u001d\u0012!\u0019\u0001\u0015\u0003\u0003\u0005\u000b\"!\u000b\u0017\u0011\u0005-Q\u0013BA\u0016\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0017\n\u00059b!aA!os\")\u0001G\u0001a\u0002c\u0005\u0011QM\u001e\t\u0004eM\"S\"\u0001\u0003\n\u0005Q\"!!B(sI\u0016\u0014\b"
)
public interface OrderToOrderingConversion {
   // $FF: synthetic method
   static Ordering catsKernelOrderingForOrder$(final OrderToOrderingConversion $this, final Order ev) {
      return $this.catsKernelOrderingForOrder(ev);
   }

   default Ordering catsKernelOrderingForOrder(final Order ev) {
      return ev.toOrdering();
   }

   static void $init$(final OrderToOrderingConversion $this) {
   }
}
