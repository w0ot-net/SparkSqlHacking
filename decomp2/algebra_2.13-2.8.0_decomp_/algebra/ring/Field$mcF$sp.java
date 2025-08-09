package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;

public interface Field$mcF$sp extends Field, CommutativeSemifield$mcF$sp, DivisionRing$mcF$sp, EuclideanRing$mcF$sp {
   // $FF: synthetic method
   static float gcd$(final Field$mcF$sp $this, final float a, final float b, final Eq eqA) {
      return $this.gcd(a, b, eqA);
   }

   default float gcd(final float a, final float b, final Eq eqA) {
      return this.gcd$mcF$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static float gcd$mcF$sp$(final Field$mcF$sp $this, final float a, final float b, final Eq eqA) {
      return $this.gcd$mcF$sp(a, b, eqA);
   }

   default float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return this.isZero$mcF$sp(a, eqA) && this.isZero$mcF$sp(b, eqA) ? this.zero$mcF$sp() : this.one$mcF$sp();
   }

   // $FF: synthetic method
   static float lcm$(final Field$mcF$sp $this, final float a, final float b, final Eq eqA) {
      return $this.lcm(a, b, eqA);
   }

   default float lcm(final float a, final float b, final Eq eqA) {
      return this.lcm$mcF$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static float lcm$mcF$sp$(final Field$mcF$sp $this, final float a, final float b, final Eq eqA) {
      return $this.lcm$mcF$sp(a, b, eqA);
   }

   default float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return this.times$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final Field$mcF$sp $this, final float a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final float a) {
      return this.euclideanFunction$mcF$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcF$sp$(final Field$mcF$sp $this, final float a) {
      return $this.euclideanFunction$mcF$sp(a);
   }

   default BigInt euclideanFunction$mcF$sp(final float a) {
      return .MODULE$.BigInt().apply(0);
   }

   // $FF: synthetic method
   static float equot$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.equot(a, b);
   }

   default float equot(final float a, final float b) {
      return this.equot$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float equot$mcF$sp$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.equot$mcF$sp(a, b);
   }

   default float equot$mcF$sp(final float a, final float b) {
      return this.div$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float emod$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.emod(a, b);
   }

   default float emod(final float a, final float b) {
      return this.emod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float emod$mcF$sp$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.emod$mcF$sp(a, b);
   }

   default float emod$mcF$sp(final float a, final float b) {
      return this.zero$mcF$sp();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final float a, final float b) {
      return this.equotmod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcF$sp$(final Field$mcF$sp $this, final float a, final float b) {
      return $this.equotmod$mcF$sp(a, b);
   }

   default Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.div$mcF$sp(a, b)), BoxesRunTime.boxToFloat(this.zero$mcF$sp()));
   }

   // $FF: synthetic method
   static float fromDouble$(final Field$mcF$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default float fromDouble(final double a) {
      return this.fromDouble$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final Field$mcF$sp $this, final double a) {
      return $this.fromDouble$mcF$sp(a);
   }

   default float fromDouble$mcF$sp(final double a) {
      return BoxesRunTime.unboxToFloat(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
