package spire.math;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006U\u0001!\ta\u000b\u0002\u000f'\u00064W\rT8oO&\u001b(+Z1m\u0015\t1q!\u0001\u0003nCRD'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0014\t\u0001Q\u0001C\u0007\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007E!b#D\u0001\u0013\u0015\t\u0019r!A\u0004bY\u001e,'M]1\n\u0005U\u0011\"AC%t\u0013:$Xm\u001a:bYB\u0011q\u0003G\u0007\u0002\u000b%\u0011\u0011$\u0002\u0002\t'\u00064W\rT8oOB\u0011qcG\u0005\u00039\u0015\u0011\u0011dU1gK2{gn\u001a+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001!!\tY\u0011%\u0003\u0002#\u0019\t!QK\\5u\u0003!!x\u000eR8vE2,GCA\u0013)!\tYa%\u0003\u0002(\u0019\t1Ai\\;cY\u0016DQ!\u000b\u0002A\u0002Y\t\u0011A\\\u0001\ti>\u0014\u0015nZ%oiR\u0011A\u0006\u000f\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005Er\u0012A\u0002\u001fs_>$h(C\u0001\u000e\u0013\t!D\"A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$A\u0002\"jO&sGO\u0003\u00025\u0019!)\u0011f\u0001a\u0001-\u0001"
)
public interface SafeLongIsReal extends IsIntegral, SafeLongTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final SafeLongIsReal $this, final SafeLong n) {
      return $this.toDouble(n);
   }

   default double toDouble(final SafeLong n) {
      return n.toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final SafeLongIsReal $this, final SafeLong n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final SafeLong n) {
      return n.toBigInt();
   }

   static void $init$(final SafeLongIsReal $this) {
   }
}
