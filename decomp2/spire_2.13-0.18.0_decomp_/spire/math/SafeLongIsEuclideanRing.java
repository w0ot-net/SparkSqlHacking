package spire.math;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0011\"\u0004\u0005\u0006Y\u0001!\t!\f\u0005\u0006c\u0001!\tA\r\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\r\u0002!\te\u0012\u0005\u0006\u001b\u0002!\tE\u0014\u0005\u0006/\u0002!\t\u0005\u0017\u0002\u0018'\u00064W\rT8oO&\u001bX)^2mS\u0012,\u0017M\u001c*j]\u001eT!AC\u0006\u0002\t5\fG\u000f\u001b\u0006\u0002\u0019\u0005)1\u000f]5sKN!\u0001A\u0004\u000b*!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019QCI\u0013\u000f\u0005YybBA\f\u001e\u001d\tAB$D\u0001\u001a\u0015\tQ2$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005a\u0011B\u0001\u0010\f\u0003\u001d\tGnZ3ce\u0006L!\u0001I\u0011\u0002\u000fA\f7m[1hK*\u0011adC\u0005\u0003G\u0011\u0012Q\"R;dY&$W-\u00198SS:<'B\u0001\u0011\"!\t1s%D\u0001\n\u0013\tA\u0013B\u0001\u0005TC\u001a,Gj\u001c8h!\t1#&\u0003\u0002,\u0013\t\t2+\u00194f\u0019>tw-S:H\u0007\u0012\u0013\u0016N\\4\u0002\r\u0011Jg.\u001b;%)\u0005q\u0003CA\b0\u0013\t\u0001\u0004C\u0001\u0003V]&$\u0018!E3vG2LG-Z1o\rVt7\r^5p]R\u00111g\u000f\t\u0003iar!!N\u001c\u000f\u0005a1\u0014\"A\t\n\u0005\u0001\u0002\u0012BA\u001d;\u0005\u0019\u0011\u0015nZ%oi*\u0011\u0001\u0005\u0005\u0005\u0006y\t\u0001\r!J\u0001\u0002C\u0006)Q-];piR\u0019Qe\u0010!\t\u000bq\u001a\u0001\u0019A\u0013\t\u000b\u0005\u001b\u0001\u0019A\u0013\u0002\u0003\t\fA!Z7pIR\u0019Q\u0005R#\t\u000bq\"\u0001\u0019A\u0013\t\u000b\u0005#\u0001\u0019A\u0013\u0002\u0011\u0015\fXo\u001c;n_\u0012$2\u0001S&M!\u0011y\u0011*J\u0013\n\u0005)\u0003\"A\u0002+va2,'\u0007C\u0003=\u000b\u0001\u0007Q\u0005C\u0003B\u000b\u0001\u0007Q%A\u0002mG6$2aT+W)\t)\u0003\u000bC\u0003R\r\u0001\u000f!+\u0001\u0002fmB\u0019QcU\u0013\n\u0005Q##AA#r\u0011\u0015ad\u00011\u0001&\u0011\u0015\te\u00011\u0001&\u0003\r97\r\u001a\u000b\u00043ncFCA\u0013[\u0011\u0015\tv\u0001q\u0001S\u0011\u0015at\u00011\u0001&\u0011\u0015\tu\u00011\u0001&\u0001"
)
public interface SafeLongIsEuclideanRing extends EuclideanRing, SafeLongIsGCDRing {
   // $FF: synthetic method
   static BigInt euclideanFunction$(final SafeLongIsEuclideanRing $this, final SafeLong a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final SafeLong a) {
      return a.abs().toBigInt();
   }

   // $FF: synthetic method
   static SafeLong equot$(final SafeLongIsEuclideanRing $this, final SafeLong a, final SafeLong b) {
      return $this.equot(a, b);
   }

   default SafeLong equot(final SafeLong a, final SafeLong b) {
      return a.equot(b);
   }

   // $FF: synthetic method
   static SafeLong emod$(final SafeLongIsEuclideanRing $this, final SafeLong a, final SafeLong b) {
      return $this.emod(a, b);
   }

   default SafeLong emod(final SafeLong a, final SafeLong b) {
      return a.emod(b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final SafeLongIsEuclideanRing $this, final SafeLong a, final SafeLong b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final SafeLong a, final SafeLong b) {
      return a.equotmod(b);
   }

   // $FF: synthetic method
   static SafeLong lcm$(final SafeLongIsEuclideanRing $this, final SafeLong a, final SafeLong b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default SafeLong lcm(final SafeLong a, final SafeLong b, final Eq ev) {
      return a.lcm(b);
   }

   // $FF: synthetic method
   static SafeLong gcd$(final SafeLongIsEuclideanRing $this, final SafeLong a, final SafeLong b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default SafeLong gcd(final SafeLong a, final SafeLong b, final Eq ev) {
      return a.gcd(b);
   }

   static void $init$(final SafeLongIsEuclideanRing $this) {
   }
}
