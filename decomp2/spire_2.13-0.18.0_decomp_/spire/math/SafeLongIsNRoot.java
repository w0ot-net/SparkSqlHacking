package spire.math;

import java.math.BigInteger;
import scala.MatchError;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.NRoot$;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\u0006S\u0001!\tA\u000b\u0002\u0010'\u00064W\rT8oO&\u001bhJU8pi*\u0011aaB\u0001\u0005[\u0006$\bNC\u0001\t\u0003\u0015\u0019\b/\u001b:f'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007E!b#D\u0001\u0013\u0015\t\u0019r!A\u0004bY\u001e,'M]1\n\u0005U\u0011\"!\u0002(S_>$\bCA\f\u0019\u001b\u0005)\u0011BA\r\u0006\u0005!\u0019\u0016MZ3M_:<\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003u\u0001\"a\u0003\u0010\n\u0005}a!\u0001B+oSR\fQA\u001c:p_R$2A\u0006\u0012%\u0011\u0015\u0019#\u00011\u0001\u0017\u0003\u0005\t\u0007\"B\u0013\u0003\u0001\u00041\u0013!A6\u0011\u0005-9\u0013B\u0001\u0015\r\u0005\rIe\u000e^\u0001\u0005MB|w\u000fF\u0002\u0017W1BQaI\u0002A\u0002YAQ!L\u0002A\u0002Y\t\u0011A\u0019"
)
public interface SafeLongIsNRoot extends NRoot {
   // $FF: synthetic method
   static SafeLong nroot$(final SafeLongIsNRoot $this, final SafeLong a, final int k) {
      return $this.nroot(a, k);
   }

   default SafeLong nroot(final SafeLong a, final int k) {
      SafeLong var3;
      if (a instanceof SafeLongLong) {
         SafeLongLong var5 = (SafeLongLong)a;
         long n = var5.x();
         var3 = SafeLong$.MODULE$.apply(NRoot$.MODULE$.apply$mJc$sp((NRoot)spire.std.package.long$.MODULE$.LongAlgebra()).nroot$mcJ$sp(n, k));
      } else {
         if (!(a instanceof SafeLongBigInteger)) {
            throw new MatchError(a);
         }

         SafeLongBigInteger var8 = (SafeLongBigInteger)a;
         BigInteger n = var8.x();
         var3 = SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt((BigInteger)NRoot$.MODULE$.apply((NRoot)spire.std.package.bigInteger$.MODULE$.BigIntegerAlgebra()).nroot(n, k)));
      }

      return var3;
   }

   // $FF: synthetic method
   static SafeLong fpow$(final SafeLongIsNRoot $this, final SafeLong a, final SafeLong b) {
      return $this.fpow(a, b);
   }

   default SafeLong fpow(final SafeLong a, final SafeLong b) {
      return b.isValidInt() ? a.pow(b.toInt()) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt((BigInteger)NRoot$.MODULE$.apply((NRoot)spire.std.package.bigInteger$.MODULE$.BigIntegerAlgebra()).fpow(a.toBigInteger(), b.toBigInteger())));
   }

   static void $init$(final SafeLongIsNRoot $this) {
   }
}
