package spire.std;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005c\u0006C\u00035\u0001\u0011\u0005SGA\u0006GY>\fGoU5h]\u0016$'B\u0001\u0004\b\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001\f#\u0011\u0002\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007c\u0001\n\u001fC9\u00111c\u0007\b\u0003)eq!!\u0006\r\u000e\u0003YQ!aF\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0011B\u0001\u000e\b\u0003\u001d\tGnZ3ce\u0006L!\u0001H\u000f\u0002\u000fA\f7m[1hK*\u0011!dB\u0005\u0003?\u0001\u0012aaU5h]\u0016$'B\u0001\u000f\u001e!\ta!%\u0003\u0002$\u001b\t)a\t\\8biB\u0011QEJ\u0007\u0002\u000b%\u0011q%\u0002\u0002\u000b\r2|\u0017\r^(sI\u0016\u0014\u0018A\u0002\u0013j]&$H\u0005F\u0001+!\ta1&\u0003\u0002-\u001b\t!QK\\5u\u0003\u0019\u0019\u0018n\u001a8v[R\u0011qF\r\t\u0003\u0019AJ!!M\u0007\u0003\u0007%sG\u000fC\u00034\u0005\u0001\u0007\u0011%A\u0001b\u0003\r\t'm\u001d\u000b\u0003CYBQaM\u0002A\u0002\u0005\u0002"
)
public interface FloatSigned extends Signed.mcF.sp, FloatOrder {
   // $FF: synthetic method
   static int signum$(final FloatSigned $this, final float a) {
      return $this.signum(a);
   }

   default int signum(final float a) {
      return this.signum$mcF$sp(a);
   }

   // $FF: synthetic method
   static float abs$(final FloatSigned $this, final float a) {
      return $this.abs(a);
   }

   default float abs(final float a) {
      return this.abs$mcF$sp(a);
   }

   // $FF: synthetic method
   static int signum$mcF$sp$(final FloatSigned $this, final float a) {
      return $this.signum$mcF$sp(a);
   }

   default int signum$mcF$sp(final float a) {
      return (int)Math.signum(a);
   }

   // $FF: synthetic method
   static float abs$mcF$sp$(final FloatSigned $this, final float a) {
      return $this.abs$mcF$sp(a);
   }

   default float abs$mcF$sp(final float a) {
      return a < 0.0F ? -a : a;
   }

   static void $init$(final FloatSigned $this) {
   }
}
