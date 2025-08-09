package spire.random;

import algebra.ring.CommutativeRng;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0019\r!\bC\u0003=\u0001\u0011\u0005QH\u0001\u0005ESN$8I\u00158h\u0015\t1q!\u0001\u0004sC:$w.\u001c\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u0001QCA\u0006\u0019'\u0011\u0001ABE\u0011\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\r\u0019BCF\u0007\u0002\u000b%\u0011Q#\u0002\u0002\u000e\t&\u001cHoQ*f[&\u0014\u0018N\\4\u0011\u0005]AB\u0002\u0001\u0003\u00063\u0001\u0011\rA\u0007\u0002\u0002\u0003F\u00111D\b\t\u0003\u001bqI!!\b\b\u0003\u000f9{G\u000f[5oOB\u0011QbH\u0005\u0003A9\u00111!\u00118z!\r\u0011c&\r\b\u0003G-r!\u0001J\u0015\u000f\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001dJ\u0011A\u0002\u001fs_>$h(C\u0001\t\u0013\tQs!A\u0004bY\u001e,'M]1\n\u00051j\u0013a\u00029bG.\fw-\u001a\u0006\u0003U\u001dI!a\f\u0019\u0003\t\r\u0013fn\u001a\u0006\u0003Y5\u00022a\u0005\u001a\u0017\u0013\t\u0019TA\u0001\u0003ESN$\u0018A\u0002\u0013j]&$H\u0005F\u00017!\tiq'\u0003\u00029\u001d\t!QK\\5u\u0003\r\tGnZ\u000b\u0002wA\u0019!E\f\f\u0002\r9,w-\u0019;f)\t\td\bC\u0003@\u0007\u0001\u0007\u0011'A\u0001y\u0001"
)
public interface DistCRng extends DistCSemiring, CommutativeRng {
   CommutativeRng alg();

   // $FF: synthetic method
   static Dist negate$(final DistCRng $this, final Dist x) {
      return $this.negate(x);
   }

   default Dist negate(final Dist x) {
      return new DistFromGen((g) -> this.alg().negate(x.apply(g)));
   }

   static void $init$(final DistCRng $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
