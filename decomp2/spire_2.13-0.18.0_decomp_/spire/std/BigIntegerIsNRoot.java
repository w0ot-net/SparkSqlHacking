package spire.std;

import java.math.BigInteger;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005I2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003%\u0001\u0011\u0005Q\u0005C\u0003.\u0001\u0011\u0005aFA\tCS\u001eLe\u000e^3hKJL5O\u0014*p_RT!AB\u0004\u0002\u0007M$HMC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001A\u0006\u0012!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0019!#F\f\u000e\u0003MQ!\u0001F\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011ac\u0005\u0002\u0006\u001dJ{w\u000e\u001e\t\u00031ui\u0011!\u0007\u0006\u00035m\tA!\\1uQ*\tA$\u0001\u0003kCZ\f\u0017B\u0001\u0010\u001a\u0005)\u0011\u0015nZ%oi\u0016<WM]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0005\u0002\"\u0001\u0004\u0012\n\u0005\rj!\u0001B+oSR\fQA\u001c:p_R$2a\u0006\u0014)\u0011\u00159#\u00011\u0001\u0018\u0003\u0005\t\u0007\"B\u0015\u0003\u0001\u0004Q\u0013!A6\u0011\u00051Y\u0013B\u0001\u0017\u000e\u0005\rIe\u000e^\u0001\u0005MB|w\u000fF\u0002\u0018_ABQaJ\u0002A\u0002]AQ!M\u0002A\u0002]\t\u0011A\u0019"
)
public interface BigIntegerIsNRoot extends NRoot {
   // $FF: synthetic method
   static BigInteger nroot$(final BigIntegerIsNRoot $this, final BigInteger a, final int k) {
      return $this.nroot(a, k);
   }

   default BigInteger nroot(final BigInteger a, final int k) {
      BigInteger var10000;
      if (a.signum() < 0 && k % 2 == 1) {
         var10000 = this.nroot(a.negate(), k).negate();
      } else {
         if (a.signum() < 0) {
            throw new ArithmeticException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot find %d-root of negative number."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(k)})));
         }

         if (k == 1) {
            var10000 = a;
         } else {
            if (k <= 1) {
               throw new ArithmeticException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot find non-positive %d-root of an integer number."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(k)})));
            }

            var10000 = this.findNroot$1(BigInteger.ZERO, a.bitLength() / k, k, a);
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static BigInteger fpow$(final BigIntegerIsNRoot $this, final BigInteger a, final BigInteger b) {
      return $this.fpow(a, b);
   }

   default BigInteger fpow(final BigInteger a, final BigInteger b) {
      return spire.math.package$.MODULE$.pow(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(a)), scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(b))).bigDecimal().toBigInteger();
   }

   private BigInteger findNroot$1(final BigInteger b, final int i, final int k$1, final BigInteger a$1) {
      while(i >= 0) {
         BigInteger c = b.setBit(i);
         if (c.pow(k$1).compareTo(a$1) <= 0) {
            --i;
            b = c;
         } else {
            --i;
            b = b;
         }
      }

      return b;
   }

   static void $init$(final BigIntegerIsNRoot $this) {
   }
}
