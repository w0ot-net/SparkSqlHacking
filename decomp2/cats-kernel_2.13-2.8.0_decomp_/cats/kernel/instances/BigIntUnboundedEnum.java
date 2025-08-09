package cats.kernel.instances;

import cats.kernel.UnboundedEnumerable;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0011\u0005\u0013\u0006C\u0003-\u0001\u0011\u0005SFA\nCS\u001eLe\u000e^+oE>,h\u000eZ3e\u000b:,XN\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Q)r#D\u0001\b\u0013\t1rAA\nV]\n|WO\u001c3fI\u0016sW/\\3sC\ndW\r\u0005\u0002\u0019A9\u0011\u0011D\b\b\u00035ui\u0011a\u0007\u0006\u00039-\ta\u0001\u0010:p_Rt\u0014\"\u0001\t\n\u0005}y\u0011a\u00029bG.\fw-Z\u0005\u0003C\t\u0012aAQ5h\u0013:$(BA\u0010\u0010\u0003\u0019!\u0013N\\5uIQ\tQ\u0005\u0005\u0002\u000fM%\u0011qe\u0004\u0002\u0005+:LG/\u0001\u0003oKb$HCA\f+\u0011\u0015Y#\u00011\u0001\u0018\u0003\u0005\t\u0017\u0001\u00039sKZLw.^:\u0015\u0005]q\u0003\"B\u0016\u0004\u0001\u00049\u0002"
)
public interface BigIntUnboundedEnum extends UnboundedEnumerable {
   // $FF: synthetic method
   static BigInt next$(final BigIntUnboundedEnum $this, final BigInt a) {
      return $this.next(a);
   }

   default BigInt next(final BigInt a) {
      return a.$plus(.MODULE$.int2bigInt(1));
   }

   // $FF: synthetic method
   static BigInt previous$(final BigIntUnboundedEnum $this, final BigInt a) {
      return $this.previous(a);
   }

   default BigInt previous(final BigInt a) {
      return a.$minus(.MODULE$.int2bigInt(1));
   }

   static void $init$(final BigIntUnboundedEnum $this) {
   }
}
