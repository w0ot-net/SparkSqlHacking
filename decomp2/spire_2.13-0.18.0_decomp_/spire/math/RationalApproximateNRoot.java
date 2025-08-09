package spire.math;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u000552\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\u0006S\u0001!\tA\u000b\u0002\u0019%\u0006$\u0018n\u001c8bY\u0006\u0003\bO]8yS6\fG/\u001a(S_>$(B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\tQa\u001d9je\u0016\u001c2\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0019\u0011\u0003\u0006\f\u000e\u0003IQ!aE\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QC\u0005\u0002\u0006\u001dJ{w\u000e\u001e\t\u0003/ai\u0011!B\u0005\u00033\u0015\u0011\u0001BU1uS>t\u0017\r\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tQ\u0004\u0005\u0002\f=%\u0011q\u0004\u0004\u0002\u0005+:LG/A\u0003oe>|G\u000fF\u0002\u0017E\u0011BQa\t\u0002A\u0002Y\t\u0011A\u001c\u0005\u0006K\t\u0001\rAJ\u0001\u0002WB\u00111bJ\u0005\u0003Q1\u00111!\u00138u\u0003\u00111\u0007o\\<\u0015\u0007YYC\u0006C\u0003$\u0007\u0001\u0007a\u0003C\u0003&\u0007\u0001\u0007a\u0003"
)
public interface RationalApproximateNRoot extends NRoot {
   // $FF: synthetic method
   static Rational nroot$(final RationalApproximateNRoot $this, final Rational n, final int k) {
      return $this.nroot(n, k);
   }

   default Rational nroot(final Rational n, final int k) {
      return Rational$.MODULE$.apply(((NRoot)spire.std.package.double$.MODULE$.DoubleAlgebra()).nroot$mcD$sp(n.toDouble(), k));
   }

   // $FF: synthetic method
   static Rational fpow$(final RationalApproximateNRoot $this, final Rational n, final Rational k) {
      return $this.fpow(n, k);
   }

   default Rational fpow(final Rational n, final Rational k) {
      return Rational$.MODULE$.apply(BoxesRunTime.unboxToDouble(spire.syntax.package.nroot$.MODULE$.nrootOps(BoxesRunTime.boxToDouble(n.toDouble()), (NRoot)spire.std.package.double$.MODULE$.DoubleAlgebra()).$times$times(k.toDouble(), spire.std.package.double$.MODULE$.DoubleAlgebra())));
   }

   static void $init$(final RationalApproximateNRoot $this) {
   }
}
