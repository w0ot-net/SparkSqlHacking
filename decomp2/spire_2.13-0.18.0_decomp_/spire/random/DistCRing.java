package spire.random;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0019\u0005!\bC\u0003=\u0001\u0011\u0005QHA\u0005ESN$8IU5oO*\u0011aaB\u0001\u0007e\u0006tGm\\7\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\f1M!\u0001\u0001\u0004\n\"!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191\u0003\u0006\f\u000e\u0003\u0015I!!F\u0003\u0003\u0011\u0011K7\u000f^\"S]\u001e\u0004\"a\u0006\r\r\u0001\u0011)\u0011\u0004\u0001b\u00015\t\t\u0011)\u0005\u0002\u001c=A\u0011Q\u0002H\u0005\u0003;9\u0011qAT8uQ&tw\r\u0005\u0002\u000e?%\u0011\u0001E\u0004\u0002\u0004\u0003:L\bc\u0001\u0012/c9\u00111e\u000b\b\u0003I%r!!\n\u0015\u000e\u0003\u0019R!aJ\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0011B\u0001\u0016\b\u0003\u001d\tGnZ3ce\u0006L!\u0001L\u0017\u0002\u000fA\f7m[1hK*\u0011!fB\u0005\u0003_A\u0012Qa\u0011*j]\u001eT!\u0001L\u0017\u0011\u0007M\u0011d#\u0003\u00024\u000b\t!A)[:u\u0003\u0019!\u0013N\\5uIQ\ta\u0007\u0005\u0002\u000eo%\u0011\u0001H\u0004\u0002\u0005+:LG/A\u0002bY\u001e,\u0012a\u000f\t\u0004E92\u0012aA8oKV\t\u0011\u0007"
)
public interface DistCRing extends DistCRng, CommutativeRing {
   CommutativeRing alg();

   // $FF: synthetic method
   static Dist one$(final DistCRing $this) {
      return $this.one();
   }

   default Dist one() {
      return Dist$.MODULE$.constant(this.alg().one());
   }

   static void $init$(final DistCRing $this) {
   }
}
