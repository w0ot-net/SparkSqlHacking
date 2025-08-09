package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;

public interface Field$mcD$sp extends Field, CommutativeSemifield$mcD$sp, DivisionRing$mcD$sp, EuclideanRing$mcD$sp {
   // $FF: synthetic method
   static double gcd$(final Field$mcD$sp $this, final double a, final double b, final Eq eqA) {
      return $this.gcd(a, b, eqA);
   }

   default double gcd(final double a, final double b, final Eq eqA) {
      return this.gcd$mcD$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static double gcd$mcD$sp$(final Field$mcD$sp $this, final double a, final double b, final Eq eqA) {
      return $this.gcd$mcD$sp(a, b, eqA);
   }

   default double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return this.isZero$mcD$sp(a, eqA) && this.isZero$mcD$sp(b, eqA) ? this.zero$mcD$sp() : this.one$mcD$sp();
   }

   // $FF: synthetic method
   static double lcm$(final Field$mcD$sp $this, final double a, final double b, final Eq eqA) {
      return $this.lcm(a, b, eqA);
   }

   default double lcm(final double a, final double b, final Eq eqA) {
      return this.lcm$mcD$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static double lcm$mcD$sp$(final Field$mcD$sp $this, final double a, final double b, final Eq eqA) {
      return $this.lcm$mcD$sp(a, b, eqA);
   }

   default double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return this.times$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final Field$mcD$sp $this, final double a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final double a) {
      return this.euclideanFunction$mcD$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcD$sp$(final Field$mcD$sp $this, final double a) {
      return $this.euclideanFunction$mcD$sp(a);
   }

   default BigInt euclideanFunction$mcD$sp(final double a) {
      return .MODULE$.BigInt().apply(0);
   }

   // $FF: synthetic method
   static double equot$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.equot(a, b);
   }

   default double equot(final double a, final double b) {
      return this.equot$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double equot$mcD$sp$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.equot$mcD$sp(a, b);
   }

   default double equot$mcD$sp(final double a, final double b) {
      return this.div$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double emod$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.emod(a, b);
   }

   default double emod(final double a, final double b) {
      return this.emod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double emod$mcD$sp$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.emod$mcD$sp(a, b);
   }

   default double emod$mcD$sp(final double a, final double b) {
      return this.zero$mcD$sp();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final double a, final double b) {
      return this.equotmod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final Field$mcD$sp $this, final double a, final double b) {
      return $this.equotmod$mcD$sp(a, b);
   }

   default Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return new Tuple2.mcDD.sp(this.div$mcD$sp(a, b), this.zero$mcD$sp());
   }

   // $FF: synthetic method
   static double fromDouble$(final Field$mcD$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default double fromDouble(final double a) {
      return this.fromDouble$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final Field$mcD$sp $this, final double a) {
      return $this.fromDouble$mcD$sp(a);
   }

   default double fromDouble$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
