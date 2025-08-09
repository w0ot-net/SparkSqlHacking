package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;

public interface EuclideanRing$mcI$sp extends EuclideanRing, GCDRing$mcI$sp {
   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRing$mcI$sp $this, final int a, final int b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final int a, final int b) {
      return this.equotmod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcI$sp$(final EuclideanRing$mcI$sp $this, final int a, final int b) {
      return $this.equotmod$mcI$sp(a, b);
   }

   default Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return new Tuple2.mcII.sp(this.equot$mcI$sp(a, b), this.emod$mcI$sp(a, b));
   }

   // $FF: synthetic method
   static int gcd$(final EuclideanRing$mcI$sp $this, final int a, final int b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default int gcd(final int a, final int b, final Eq ev) {
      return this.gcd$mcI$sp(a, b, ev);
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final EuclideanRing$mcI$sp $this, final int a, final int b, final Eq ev) {
      return $this.gcd$mcI$sp(a, b, ev);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq ev) {
      return EuclideanRing$.MODULE$.euclid$mIc$sp(a, b, ev, this);
   }

   // $FF: synthetic method
   static int lcm$(final EuclideanRing$mcI$sp $this, final int a, final int b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default int lcm(final int a, final int b, final Eq ev) {
      return this.lcm$mcI$sp(a, b, ev);
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final EuclideanRing$mcI$sp $this, final int a, final int b, final Eq ev) {
      return $this.lcm$mcI$sp(a, b, ev);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq ev) {
      return !this.isZero$mcI$sp(a, ev) && !this.isZero$mcI$sp(b, ev) ? this.times$mcI$sp(this.equot$mcI$sp(a, this.gcd$mcI$sp(a, b, ev)), b) : this.zero$mcI$sp();
   }
}
