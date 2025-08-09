package spire.std;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u00039\u0001\u0011\u0005\u0013HA\u0006TQ>\u0014HoU5h]\u0016$'BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001\r%\u0015\u0002\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n E9\u0011A\u0003\b\b\u0003+iq!AF\r\u000e\u0003]Q!\u0001\u0007\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0011BA\u000e\t\u0003\u001d\tGnZ3ce\u0006L!!\b\u0010\u0002\u000fA\f7m[1hK*\u00111\u0004C\u0005\u0003A\u0005\u0012aaU5h]\u0016$'BA\u000f\u001f!\ti1%\u0003\u0002%\u001d\t)1\u000b[8siB\u0011aeJ\u0007\u0002\r%\u0011\u0001F\u0002\u0002\u000b'\"|'\u000f^(sI\u0016\u0014\u0018A\u0002\u0013j]&$H\u0005F\u0001,!\tiA&\u0003\u0002.\u001d\t!QK\\5u\u0003\u0015y'\u000fZ3s+\u0005\u0001\u0004C\u0001\u0014\u0001\u0003\u0019\u0019\u0018n\u001a8v[R\u00111G\u000e\t\u0003\u001bQJ!!\u000e\b\u0003\u0007%sG\u000fC\u00038\u0007\u0001\u0007!%A\u0001b\u0003\r\t'm\u001d\u000b\u0003EiBQa\u000e\u0003A\u0002\t\u0002"
)
public interface ShortSigned extends Signed.mcS.sp, ShortOrder {
   // $FF: synthetic method
   static ShortSigned order$(final ShortSigned $this) {
      return $this.order();
   }

   default ShortSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final ShortSigned $this, final short a) {
      return $this.signum(a);
   }

   default int signum(final short a) {
      return this.signum$mcS$sp(a);
   }

   // $FF: synthetic method
   static short abs$(final ShortSigned $this, final short a) {
      return $this.abs(a);
   }

   default short abs(final short a) {
      return this.abs$mcS$sp(a);
   }

   // $FF: synthetic method
   static int signum$mcS$sp$(final ShortSigned $this, final short a) {
      return $this.signum$mcS$sp(a);
   }

   default int signum$mcS$sp(final short a) {
      return Integer.signum(a);
   }

   // $FF: synthetic method
   static short abs$mcS$sp$(final ShortSigned $this, final short a) {
      return $this.abs$mcS$sp(a);
   }

   default short abs$mcS$sp(final short a) {
      return a < 0 ? (short)(-a) : a;
   }

   static void $init$(final ShortSigned $this) {
   }
}
