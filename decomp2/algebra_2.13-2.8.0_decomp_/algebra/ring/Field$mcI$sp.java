package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;

public interface Field$mcI$sp extends Field, CommutativeSemifield$mcI$sp, DivisionRing$mcI$sp, EuclideanRing$mcI$sp {
   // $FF: synthetic method
   static int gcd$(final Field$mcI$sp $this, final int a, final int b, final Eq eqA) {
      return $this.gcd(a, b, eqA);
   }

   default int gcd(final int a, final int b, final Eq eqA) {
      return this.gcd$mcI$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final Field$mcI$sp $this, final int a, final int b, final Eq eqA) {
      return $this.gcd$mcI$sp(a, b, eqA);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return this.isZero$mcI$sp(a, eqA) && this.isZero$mcI$sp(b, eqA) ? this.zero$mcI$sp() : this.one$mcI$sp();
   }

   // $FF: synthetic method
   static int lcm$(final Field$mcI$sp $this, final int a, final int b, final Eq eqA) {
      return $this.lcm(a, b, eqA);
   }

   default int lcm(final int a, final int b, final Eq eqA) {
      return this.lcm$mcI$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final Field$mcI$sp $this, final int a, final int b, final Eq eqA) {
      return $this.lcm$mcI$sp(a, b, eqA);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return this.times$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final Field$mcI$sp $this, final int a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final int a) {
      return this.euclideanFunction$mcI$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcI$sp$(final Field$mcI$sp $this, final int a) {
      return $this.euclideanFunction$mcI$sp(a);
   }

   default BigInt euclideanFunction$mcI$sp(final int a) {
      return .MODULE$.BigInt().apply(0);
   }

   // $FF: synthetic method
   static int equot$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.equot(a, b);
   }

   default int equot(final int a, final int b) {
      return this.equot$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int equot$mcI$sp$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.equot$mcI$sp(a, b);
   }

   default int equot$mcI$sp(final int a, final int b) {
      return this.div$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int emod$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.emod(a, b);
   }

   default int emod(final int a, final int b) {
      return this.emod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int emod$mcI$sp$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.emod$mcI$sp(a, b);
   }

   default int emod$mcI$sp(final int a, final int b) {
      return this.zero$mcI$sp();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final int a, final int b) {
      return this.equotmod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcI$sp$(final Field$mcI$sp $this, final int a, final int b) {
      return $this.equotmod$mcI$sp(a, b);
   }

   default Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return new Tuple2.mcII.sp(this.div$mcI$sp(a, b), this.zero$mcI$sp());
   }

   // $FF: synthetic method
   static int fromDouble$(final Field$mcI$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default int fromDouble(final double a) {
      return this.fromDouble$mcI$sp(a);
   }

   // $FF: synthetic method
   static int fromDouble$mcI$sp$(final Field$mcI$sp $this, final double a) {
      return $this.fromDouble$mcI$sp(a);
   }

   default int fromDouble$mcI$sp(final double a) {
      return BoxesRunTime.unboxToInt(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
