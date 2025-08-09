package spire.std;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4q\u0001E\t\u0011\u0002\u0007\u0005a\u0003C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003?\u0001\u0011\u0005q\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003E\u0001\u0011\u0005S\tC\u0003L\u0001\u0011\u0005C\nC\u0003P\u0001\u0011\u0005q\bC\u0003Q\u0001\u0011\u0005\u0013\u000bC\u0003U\u0001\u0011\u0005Q\u000bC\u0003`\u0001\u0011\u0005\u0003\rC\u0003g\u0001\u0011\u0005q\rC\u0003k\u0001\u0011\u00051\u000eC\u0003o\u0001\u0011\u0005s\u000eC\u0003y\u0001\u0011\u0005\u0013PA\nM_:<\u0017j]#vG2LG-Z1o%&twM\u0003\u0002\u0013'\u0005\u00191\u000f\u001e3\u000b\u0003Q\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007c\u0001\u0010+[9\u0011qd\n\b\u0003A\u0015r!!\t\u0013\u000e\u0003\tR!aI\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0012B\u0001\u0014\u0014\u0003\u001d\tGnZ3ce\u0006L!\u0001K\u0015\u0002\u000fA\f7m[1hK*\u0011aeE\u0005\u0003W1\u0012Q\"R;dY&$W-\u00198SS:<'B\u0001\u0015*!\tAb&\u0003\u000203\t!Aj\u001c8h\u0003\u0019!\u0013N\\5uIQ\t!\u0007\u0005\u0002\u0019g%\u0011A'\u0007\u0002\u0005+:LG/A\u0003nS:,8\u000fF\u0002.oeBQ\u0001\u000f\u0002A\u00025\n\u0011!\u0019\u0005\u0006u\t\u0001\r!L\u0001\u0002E\u00061a.Z4bi\u0016$\"!L\u001f\t\u000ba\u001a\u0001\u0019A\u0017\u0002\u0007=tW-F\u0001.\u0003\u0011\u0001H.^:\u0015\u00075\u00125\tC\u00039\u000b\u0001\u0007Q\u0006C\u0003;\u000b\u0001\u0007Q&A\u0002q_^$2!\f$H\u0011\u0015Ad\u00011\u0001.\u0011\u0015Qd\u00011\u0001I!\tA\u0012*\u0003\u0002K3\t\u0019\u0011J\u001c;\u0002\u000bQLW.Z:\u0015\u00075je\nC\u00039\u000f\u0001\u0007Q\u0006C\u0003;\u000f\u0001\u0007Q&\u0001\u0003{KJ|\u0017a\u00024s_6Le\u000e\u001e\u000b\u0003[ICQaU\u0005A\u0002!\u000b\u0011A\\\u0001\u0012KV\u001cG.\u001b3fC:4UO\\2uS>tGC\u0001,_!\t96L\u0004\u0002Y5:\u0011\u0011%W\u0005\u00025%\u0011\u0001&G\u0005\u00039v\u0013aAQ5h\u0013:$(B\u0001\u0015\u001a\u0011\u0015A$\u00021\u0001.\u0003!)\u0017/^8u[>$GcA1eKB!\u0001DY\u0017.\u0013\t\u0019\u0017D\u0001\u0004UkBdWM\r\u0005\u0006q-\u0001\r!\f\u0005\u0006u-\u0001\r!L\u0001\u0006KF,x\u000e\u001e\u000b\u0004[!L\u0007\"\u0002\u001d\r\u0001\u0004i\u0003\"\u0002\u001e\r\u0001\u0004i\u0013\u0001B3n_\u0012$2!\f7n\u0011\u0015AT\u00021\u0001.\u0011\u0015QT\u00021\u0001.\u0003\r97\r\u001a\u000b\u0004aZ<HCA\u0017r\u0011\u0015\u0011h\u0002q\u0001t\u0003\t)g\u000fE\u0002\u001fi6J!!\u001e\u0017\u0003\u0005\u0015\u000b\b\"\u0002\u001d\u000f\u0001\u0004i\u0003\"\u0002\u001e\u000f\u0001\u0004i\u0013a\u00017d[R\u0019!\u0010`?\u0015\u00055Z\b\"\u0002:\u0010\u0001\b\u0019\b\"\u0002\u001d\u0010\u0001\u0004i\u0003\"\u0002\u001e\u0010\u0001\u0004i\u0003"
)
public interface LongIsEuclideanRing extends EuclideanRing.mcJ.sp {
   // $FF: synthetic method
   static long minus$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.minus(a, b);
   }

   default long minus(final long a, final long b) {
      return this.minus$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long negate$(final LongIsEuclideanRing $this, final long a) {
      return $this.negate(a);
   }

   default long negate(final long a) {
      return this.negate$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long one$(final LongIsEuclideanRing $this) {
      return $this.one();
   }

   default long one() {
      return this.one$mcJ$sp();
   }

   // $FF: synthetic method
   static long plus$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.plus(a, b);
   }

   default long plus(final long a, final long b) {
      return this.plus$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long pow$(final LongIsEuclideanRing $this, final long a, final int b) {
      return $this.pow(a, b);
   }

   default long pow(final long a, final int b) {
      return this.pow$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long times$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.times(a, b);
   }

   default long times(final long a, final long b) {
      return this.times$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long zero$(final LongIsEuclideanRing $this) {
      return $this.zero();
   }

   default long zero() {
      return this.zero$mcJ$sp();
   }

   // $FF: synthetic method
   static long fromInt$(final LongIsEuclideanRing $this, final int n) {
      return $this.fromInt(n);
   }

   default long fromInt(final int n) {
      return this.fromInt$mcJ$sp(n);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final LongIsEuclideanRing $this, final long a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final long a) {
      return this.euclideanFunction$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final long a, final long b) {
      return this.equotmod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long equot$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.equot(a, b);
   }

   default long equot(final long a, final long b) {
      return this.equot$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long emod$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.emod(a, b);
   }

   default long emod(final long a, final long b) {
      return this.emod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long gcd$(final LongIsEuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default long gcd(final long a, final long b, final Eq ev) {
      return this.gcd$mcJ$sp(a, b, ev);
   }

   // $FF: synthetic method
   static long lcm$(final LongIsEuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default long lcm(final long a, final long b, final Eq ev) {
      return this.lcm$mcJ$sp(a, b, ev);
   }

   // $FF: synthetic method
   static long minus$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.minus$mcJ$sp(a, b);
   }

   default long minus$mcJ$sp(final long a, final long b) {
      return a - b;
   }

   // $FF: synthetic method
   static long negate$mcJ$sp$(final LongIsEuclideanRing $this, final long a) {
      return $this.negate$mcJ$sp(a);
   }

   default long negate$mcJ$sp(final long a) {
      return -a;
   }

   // $FF: synthetic method
   static long one$mcJ$sp$(final LongIsEuclideanRing $this) {
      return $this.one$mcJ$sp();
   }

   default long one$mcJ$sp() {
      return 1L;
   }

   // $FF: synthetic method
   static long plus$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.plus$mcJ$sp(a, b);
   }

   default long plus$mcJ$sp(final long a, final long b) {
      return a + b;
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final int b) {
      return $this.pow$mcJ$sp(a, b);
   }

   default long pow$mcJ$sp(final long a, final int b) {
      return spire.math.package$.MODULE$.pow(a, (long)b);
   }

   // $FF: synthetic method
   static long times$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.times$mcJ$sp(a, b);
   }

   default long times$mcJ$sp(final long a, final long b) {
      return a * b;
   }

   // $FF: synthetic method
   static long zero$mcJ$sp$(final LongIsEuclideanRing $this) {
      return $this.zero$mcJ$sp();
   }

   default long zero$mcJ$sp() {
      return 0L;
   }

   // $FF: synthetic method
   static long fromInt$mcJ$sp$(final LongIsEuclideanRing $this, final int n) {
      return $this.fromInt$mcJ$sp(n);
   }

   default long fromInt$mcJ$sp(final int n) {
      return (long)n;
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcJ$sp$(final LongIsEuclideanRing $this, final long a) {
      return $this.euclideanFunction$mcJ$sp(a);
   }

   default BigInt euclideanFunction$mcJ$sp(final long a) {
      return .MODULE$.BigInt().apply(a).abs();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.equotmod$mcJ$sp(a, b);
   }

   default Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return spire.math.package$.MODULE$.equotmod(a, b);
   }

   // $FF: synthetic method
   static long equot$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.equot$mcJ$sp(a, b);
   }

   default long equot$mcJ$sp(final long a, final long b) {
      return spire.math.package$.MODULE$.equot(a, b);
   }

   // $FF: synthetic method
   static long emod$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b) {
      return $this.emod$mcJ$sp(a, b);
   }

   default long emod$mcJ$sp(final long a, final long b) {
      return spire.math.package$.MODULE$.emod(a, b);
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.gcd$mcJ$sp(a, b, ev);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
      return spire.math.package$.MODULE$.gcd(a, b);
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final LongIsEuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.lcm$mcJ$sp(a, b, ev);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
      return spire.math.package$.MODULE$.lcm(a, b);
   }

   static void $init$(final LongIsEuclideanRing $this) {
   }
}
