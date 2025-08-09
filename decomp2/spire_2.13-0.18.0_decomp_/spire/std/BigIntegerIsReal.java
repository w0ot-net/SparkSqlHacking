package spire.std;

import java.math.BigInteger;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003<\u0001\u0011\u0005AH\u0001\tCS\u001eLe\u000e^3hKJL5OU3bY*\u0011aaB\u0001\u0004gR$'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M)\u0001aC\t GA\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u00042AE\u000b\u0018\u001b\u0005\u0019\"B\u0001\u000b\b\u0003\u001d\tGnZ3ce\u0006L!AF\n\u0003\u0015%\u001b\u0018J\u001c;fOJ\fG\u000e\u0005\u0002\u0019;5\t\u0011D\u0003\u0002\u001b7\u0005!Q.\u0019;i\u0015\u0005a\u0012\u0001\u00026bm\u0006L!AH\r\u0003\u0015\tKw-\u00138uK\u001e,'\u000f\u0005\u0002!C5\tQ!\u0003\u0002#\u000b\tY\")[4J]R,w-\u001a:UeVt7-\u0019;fI\u0012Kg/[:j_:\u0004\"\u0001\n\u0017\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\n\u0003\u0019a$o\\8u}%\ta\"\u0003\u0002,\u001b\u00059\u0001/Y2lC\u001e,\u0017BA\u0017/\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYS\"\u0001\u0004%S:LG\u000f\n\u000b\u0002cA\u0011ABM\u0005\u0003g5\u0011A!\u00168ji\u0006AAo\u001c#pk\ndW\r\u0006\u00027sA\u0011AbN\u0005\u0003q5\u0011a\u0001R8vE2,\u0007\"\u0002\u001e\u0003\u0001\u00049\u0012!\u00018\u0002\u0011Q|')[4J]R$\"!\u0010!\u0011\u0005\u0011r\u0014BA /\u0005\u0019\u0011\u0015nZ%oi\")!h\u0001a\u0001/\u0001"
)
public interface BigIntegerIsReal extends IsIntegral, BigIntegerTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final BigIntegerIsReal $this, final BigInteger n) {
      return $this.toDouble(n);
   }

   default double toDouble(final BigInteger n) {
      return n.doubleValue();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final BigIntegerIsReal $this, final BigInteger n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final BigInteger n) {
      return .MODULE$.javaBigInteger2bigInt(n);
   }

   static void $init$(final BigIntegerIsReal $this) {
   }
}
