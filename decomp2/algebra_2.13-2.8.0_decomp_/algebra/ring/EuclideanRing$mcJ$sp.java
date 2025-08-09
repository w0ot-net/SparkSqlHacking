package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;

public interface EuclideanRing$mcJ$sp extends EuclideanRing, GCDRing$mcJ$sp {
   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRing$mcJ$sp $this, final long a, final long b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final long a, final long b) {
      return this.equotmod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcJ$sp$(final EuclideanRing$mcJ$sp $this, final long a, final long b) {
      return $this.equotmod$mcJ$sp(a, b);
   }

   default Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return new Tuple2.mcJJ.sp(this.equot$mcJ$sp(a, b), this.emod$mcJ$sp(a, b));
   }

   // $FF: synthetic method
   static long gcd$(final EuclideanRing$mcJ$sp $this, final long a, final long b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default long gcd(final long a, final long b, final Eq ev) {
      return this.gcd$mcJ$sp(a, b, ev);
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final EuclideanRing$mcJ$sp $this, final long a, final long b, final Eq ev) {
      return $this.gcd$mcJ$sp(a, b, ev);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
      return EuclideanRing$.MODULE$.euclid$mJc$sp(a, b, ev, this);
   }

   // $FF: synthetic method
   static long lcm$(final EuclideanRing$mcJ$sp $this, final long a, final long b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default long lcm(final long a, final long b, final Eq ev) {
      return this.lcm$mcJ$sp(a, b, ev);
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final EuclideanRing$mcJ$sp $this, final long a, final long b, final Eq ev) {
      return $this.lcm$mcJ$sp(a, b, ev);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
      return !this.isZero$mcJ$sp(a, ev) && !this.isZero$mcJ$sp(b, ev) ? this.times$mcJ$sp(this.equot$mcJ$sp(a, this.gcd$mcJ$sp(a, b, ev)), b) : this.zero$mcJ$sp();
   }
}
