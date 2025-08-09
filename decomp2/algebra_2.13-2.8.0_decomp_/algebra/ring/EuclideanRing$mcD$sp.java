package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;

public interface EuclideanRing$mcD$sp extends EuclideanRing, GCDRing$mcD$sp {
   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRing$mcD$sp $this, final double a, final double b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final double a, final double b) {
      return this.equotmod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final EuclideanRing$mcD$sp $this, final double a, final double b) {
      return $this.equotmod$mcD$sp(a, b);
   }

   default Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return new Tuple2.mcDD.sp(this.equot$mcD$sp(a, b), this.emod$mcD$sp(a, b));
   }

   // $FF: synthetic method
   static double gcd$(final EuclideanRing$mcD$sp $this, final double a, final double b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default double gcd(final double a, final double b, final Eq ev) {
      return this.gcd$mcD$sp(a, b, ev);
   }

   // $FF: synthetic method
   static double gcd$mcD$sp$(final EuclideanRing$mcD$sp $this, final double a, final double b, final Eq ev) {
      return $this.gcd$mcD$sp(a, b, ev);
   }

   default double gcd$mcD$sp(final double a, final double b, final Eq ev) {
      return EuclideanRing$.MODULE$.euclid$mDc$sp(a, b, ev, this);
   }

   // $FF: synthetic method
   static double lcm$(final EuclideanRing$mcD$sp $this, final double a, final double b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default double lcm(final double a, final double b, final Eq ev) {
      return this.lcm$mcD$sp(a, b, ev);
   }

   // $FF: synthetic method
   static double lcm$mcD$sp$(final EuclideanRing$mcD$sp $this, final double a, final double b, final Eq ev) {
      return $this.lcm$mcD$sp(a, b, ev);
   }

   default double lcm$mcD$sp(final double a, final double b, final Eq ev) {
      return !this.isZero$mcD$sp(a, ev) && !this.isZero$mcD$sp(b, ev) ? this.times$mcD$sp(this.equot$mcD$sp(a, this.gcd$mcD$sp(a, b, ev)), b) : this.zero$mcD$sp();
   }
}
