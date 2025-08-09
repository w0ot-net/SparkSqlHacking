package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;

public interface Field$mcJ$sp extends Field, CommutativeSemifield$mcJ$sp, DivisionRing$mcJ$sp, EuclideanRing$mcJ$sp {
   // $FF: synthetic method
   static long gcd$(final Field$mcJ$sp $this, final long a, final long b, final Eq eqA) {
      return $this.gcd(a, b, eqA);
   }

   default long gcd(final long a, final long b, final Eq eqA) {
      return this.gcd$mcJ$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final Field$mcJ$sp $this, final long a, final long b, final Eq eqA) {
      return $this.gcd$mcJ$sp(a, b, eqA);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return this.isZero$mcJ$sp(a, eqA) && this.isZero$mcJ$sp(b, eqA) ? this.zero$mcJ$sp() : this.one$mcJ$sp();
   }

   // $FF: synthetic method
   static long lcm$(final Field$mcJ$sp $this, final long a, final long b, final Eq eqA) {
      return $this.lcm(a, b, eqA);
   }

   default long lcm(final long a, final long b, final Eq eqA) {
      return this.lcm$mcJ$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final Field$mcJ$sp $this, final long a, final long b, final Eq eqA) {
      return $this.lcm$mcJ$sp(a, b, eqA);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return this.times$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final Field$mcJ$sp $this, final long a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final long a) {
      return this.euclideanFunction$mcJ$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcJ$sp$(final Field$mcJ$sp $this, final long a) {
      return $this.euclideanFunction$mcJ$sp(a);
   }

   default BigInt euclideanFunction$mcJ$sp(final long a) {
      return .MODULE$.BigInt().apply(0);
   }

   // $FF: synthetic method
   static long equot$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.equot(a, b);
   }

   default long equot(final long a, final long b) {
      return this.equot$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long equot$mcJ$sp$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.equot$mcJ$sp(a, b);
   }

   default long equot$mcJ$sp(final long a, final long b) {
      return this.div$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long emod$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.emod(a, b);
   }

   default long emod(final long a, final long b) {
      return this.emod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long emod$mcJ$sp$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.emod$mcJ$sp(a, b);
   }

   default long emod$mcJ$sp(final long a, final long b) {
      return this.zero$mcJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final long a, final long b) {
      return this.equotmod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcJ$sp$(final Field$mcJ$sp $this, final long a, final long b) {
      return $this.equotmod$mcJ$sp(a, b);
   }

   default Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return new Tuple2.mcJJ.sp(this.div$mcJ$sp(a, b), this.zero$mcJ$sp());
   }

   // $FF: synthetic method
   static long fromDouble$(final Field$mcJ$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default long fromDouble(final double a) {
      return this.fromDouble$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final Field$mcJ$sp $this, final double a) {
      return $this.fromDouble$mcJ$sp(a);
   }

   default long fromDouble$mcJ$sp(final double a) {
      return BoxesRunTime.unboxToLong(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
