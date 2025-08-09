package spire.math;

import cats.kernel.Eq;
import scala.math.Equiv;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005q!\u0003\u0005\u0006E\u0001!\ta\t\u0005\u0006O\u00011\t\u0001\u000b\u0005\u0006s\u0001!\tA\u000f\u0002\u0012'\u000e\fG.Y#rk&4xK]1qa\u0016\u0014(B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\tQa\u001d9je\u0016,\"A\u0003\r\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0004%Q1R\"A\n\u000b\u0005\u0019i\u0011BA\u000b\u0014\u0005\u0015)\u0015/^5w!\t9\u0002\u0004\u0004\u0001\u0005\u000be\u0001!\u0019A\u000e\u0003\u0003\u0005\u001b\u0001!\u0005\u0002\u001d?A\u0011A\"H\u0005\u0003=5\u0011qAT8uQ&tw\r\u0005\u0002\rA%\u0011\u0011%\u0004\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u0001%!\taQ%\u0003\u0002'\u001b\t!QK\\5u\u0003\t)\u0017/F\u0001*!\rQcG\u0006\b\u0003WMr!\u0001L\u0019\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=R\u0012A\u0002\u001fs_>$h(C\u0001\t\u0013\t\u0011t!A\u0004bY\u001e,'M]1\n\u0005Q*\u0014a\u00029bG.\fw-\u001a\u0006\u0003e\u001dI!a\u000e\u001d\u0003\u0005\u0015\u000b(B\u0001\u001b6\u0003\u0015)\u0017/^5w)\rYd\b\u0011\t\u0003\u0019qJ!!P\u0007\u0003\u000f\t{w\u000e\\3b]\")qh\u0001a\u0001-\u0005\t\u0001\u0010C\u0003B\u0007\u0001\u0007a#A\u0001z\u0001"
)
public interface ScalaEquivWrapper extends Equiv {
   Eq eq();

   // $FF: synthetic method
   static boolean equiv$(final ScalaEquivWrapper $this, final Object x, final Object y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final Object x, final Object y) {
      return this.eq().eqv(x, y);
   }

   static void $init$(final ScalaEquivWrapper $this) {
   }
}
