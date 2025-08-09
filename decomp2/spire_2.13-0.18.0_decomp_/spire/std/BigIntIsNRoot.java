package spire.std;

import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005Y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u00032\u0001\u0011\u0005!GA\u0007CS\u001eLe\u000e^%t\u001dJ{w\u000e\u001e\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\rE\u0002\u0013+]i\u0011a\u0005\u0006\u0003)\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u0017'\t)aJU8piB\u0011\u0001\u0004\t\b\u00033yq!AG\u000f\u000e\u0003mQ!\u0001H\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011BA\u0010\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!!\t\u0012\u0003\r\tKw-\u00138u\u0015\tyR\"\u0001\u0004%S:LG\u000f\n\u000b\u0002KA\u0011ABJ\u0005\u0003O5\u0011A!\u00168ji\u0006)aN]8piR\u0019qC\u000b\u0017\t\u000b-\u0012\u0001\u0019A\f\u0002\u0003\u0005DQ!\f\u0002A\u00029\n\u0011a\u001b\t\u0003\u0019=J!\u0001M\u0007\u0003\u0007%sG/\u0001\u0003ga><HcA\f4i!)1f\u0001a\u0001/!)Qg\u0001a\u0001/\u0005\t!\r"
)
public interface BigIntIsNRoot extends NRoot {
   // $FF: synthetic method
   static BigInt nroot$(final BigIntIsNRoot $this, final BigInt a, final int k) {
      return $this.nroot(a, k);
   }

   default BigInt nroot(final BigInt a, final int k) {
      BigInt var10000;
      if (a.$less(.MODULE$.int2bigInt(0)) && k % 2 == 1) {
         var10000 = this.nroot(a.unary_$minus(), k).unary_$minus();
      } else {
         if (a.$less(.MODULE$.int2bigInt(0))) {
            throw new ArithmeticException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot find %d-root of negative number."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(k)})));
         }

         var10000 = this.findNroot$1(.MODULE$.int2bigInt(0), a.bitLength() - 1, k, a);
      }

      return var10000;
   }

   // $FF: synthetic method
   static BigInt fpow$(final BigIntIsNRoot $this, final BigInt a, final BigInt b) {
      return $this.fpow(a, b);
   }

   default BigInt fpow(final BigInt a, final BigInt b) {
      return spire.math.package$.MODULE$.pow(scala.package..MODULE$.BigDecimal().apply(a), scala.package..MODULE$.BigDecimal().apply(b)).toBigInt();
   }

   private BigInt findNroot$1(final BigInt b, final int i, final int k$1, final BigInt a$1) {
      while(i >= 0) {
         BigInt c = b.setBit(i);
         if (c.pow(k$1).$less$eq(a$1)) {
            --i;
            b = c;
         } else {
            --i;
            b = b;
         }
      }

      return b;
   }

   static void $init$(final BigIntIsNRoot $this) {
   }
}
