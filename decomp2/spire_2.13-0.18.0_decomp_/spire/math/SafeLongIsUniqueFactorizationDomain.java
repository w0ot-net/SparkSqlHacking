package spire.math;

import scala.reflect.ScalaSignature;
import spire.algebra.UniqueFactorizationDomain;
import spire.math.prime.Factors;

@ScalaSignature(
   bytes = "\u0006\u0005A2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\u0006O\u0001!\t\u0001\u000b\u0002$'\u00064W\rT8oO&\u001bXK\\5rk\u00164\u0015m\u0019;pe&T\u0018\r^5p]\u0012{W.Y5o\u0015\t1q!\u0001\u0003nCRD'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0004#Q1R\"\u0001\n\u000b\u0005M9\u0011aB1mO\u0016\u0014'/Y\u0005\u0003+I\u0011\u0011$\u00168jcV,g)Y2u_JL'0\u0019;j_:$u.\\1j]B\u0011q\u0003G\u0007\u0002\u000b%\u0011\u0011$\u0002\u0002\t'\u00064W\rT8oO\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001e!\tYa$\u0003\u0002 \u0019\t!QK\\5u\u0003\u001dI7\u000f\u0015:j[\u0016$\"AI\u0013\u0011\u0005-\u0019\u0013B\u0001\u0013\r\u0005\u001d\u0011un\u001c7fC:DQA\n\u0002A\u0002Y\t\u0011!Y\u0001\u0007M\u0006\u001cGo\u001c:\u0015\u0005%z\u0003C\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0006\u0003\u0015\u0001(/[7f\u0013\tq3FA\u0004GC\u000e$xN]:\t\u000b\u0019\u001a\u0001\u0019\u0001\f"
)
public interface SafeLongIsUniqueFactorizationDomain extends UniqueFactorizationDomain {
   // $FF: synthetic method
   static boolean isPrime$(final SafeLongIsUniqueFactorizationDomain $this, final SafeLong a) {
      return $this.isPrime(a);
   }

   default boolean isPrime(final SafeLong a) {
      return a.isPrime();
   }

   // $FF: synthetic method
   static Factors factor$(final SafeLongIsUniqueFactorizationDomain $this, final SafeLong a) {
      return $this.factor(a);
   }

   default Factors factor(final SafeLong a) {
      return a.factor();
   }

   static void $init$(final SafeLongIsUniqueFactorizationDomain $this) {
   }
}
