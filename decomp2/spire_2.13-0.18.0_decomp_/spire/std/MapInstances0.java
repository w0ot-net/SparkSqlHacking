package spire.std;

import algebra.ring.CommutativeSemiring;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003>\u0001\u0011\raHA\u0007NCBLen\u001d;b]\u000e,7\u000f\r\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0014!\taA#\u0003\u0002\u0016\u001b\t!QK\\5u\u0003%i\u0015\r]'p]>LG-F\u0002\u0019?%\"\"!G\u0016\u0011\tiYR\u0004K\u0007\u0002\u000b%\u0011A$\u0002\u0002\n\u001b\u0006\u0004Xj\u001c8pS\u0012\u0004\"AH\u0010\r\u0001\u0011)\u0001E\u0001b\u0001C\t\t1*\u0005\u0002#KA\u0011AbI\u0005\u0003I5\u0011qAT8uQ&tw\r\u0005\u0002\rM%\u0011q%\u0004\u0002\u0004\u0003:L\bC\u0001\u0010*\t\u0015Q#A1\u0001\"\u0005\u00051\u0006b\u0002\u0017\u0003\u0003\u0003\u0005\u001d!L\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u0018;Q9\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!aM\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0011B\u0001\u001c\b\u0003\u001d\tGnZ3ce\u0006L!\u0001O\u001d\u0002\u000fA\f7m[1hK*\u0011agB\u0005\u0003wq\u0012\u0011bU3nS\u001e\u0014x.\u001e9\u000b\u0005aJ\u0014\u0001D'ba\u000e\u001bV-\\5sS:<WcA E\rR\u0011\u0001i\u0012\t\u00055\u0005\u001bU)\u0003\u0002C\u000b\taQ*\u00199D'\u0016l\u0017N]5oOB\u0011a\u0004\u0012\u0003\u0006A\r\u0011\r!\t\t\u0003=\u0019#QAK\u0002C\u0002\u0005Bq\u0001S\u0002\u0002\u0002\u0003\u000f\u0011*\u0001\u0006fm&$WM\\2fIM\u00022A\f&F\u0013\tYEHA\u0005D'\u0016l\u0017N]5oO\u0002"
)
public interface MapInstances0 {
   // $FF: synthetic method
   static MapMonoid MapMonoid$(final MapInstances0 $this, final Semigroup evidence$2) {
      return $this.MapMonoid(evidence$2);
   }

   default MapMonoid MapMonoid(final Semigroup evidence$2) {
      return new MapMonoid(evidence$2);
   }

   // $FF: synthetic method
   static MapCSemiring MapCSemiring$(final MapInstances0 $this, final CommutativeSemiring evidence$3) {
      return $this.MapCSemiring(evidence$3);
   }

   default MapCSemiring MapCSemiring(final CommutativeSemiring evidence$3) {
      return new MapCSemiring(evidence$3);
   }

   static void $init$(final MapInstances0 $this) {
   }
}
