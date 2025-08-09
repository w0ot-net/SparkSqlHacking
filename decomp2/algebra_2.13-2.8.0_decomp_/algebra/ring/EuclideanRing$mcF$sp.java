package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EuclideanRing$mcF$sp extends EuclideanRing, GCDRing$mcF$sp {
   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRing$mcF$sp $this, final float a, final float b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final float a, final float b) {
      return this.equotmod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcF$sp$(final EuclideanRing$mcF$sp $this, final float a, final float b) {
      return $this.equotmod$mcF$sp(a, b);
   }

   default Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.equot$mcF$sp(a, b)), BoxesRunTime.boxToFloat(this.emod$mcF$sp(a, b)));
   }

   // $FF: synthetic method
   static float gcd$(final EuclideanRing$mcF$sp $this, final float a, final float b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default float gcd(final float a, final float b, final Eq ev) {
      return this.gcd$mcF$sp(a, b, ev);
   }

   // $FF: synthetic method
   static float gcd$mcF$sp$(final EuclideanRing$mcF$sp $this, final float a, final float b, final Eq ev) {
      return $this.gcd$mcF$sp(a, b, ev);
   }

   default float gcd$mcF$sp(final float a, final float b, final Eq ev) {
      return EuclideanRing$.MODULE$.euclid$mFc$sp(a, b, ev, this);
   }

   // $FF: synthetic method
   static float lcm$(final EuclideanRing$mcF$sp $this, final float a, final float b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default float lcm(final float a, final float b, final Eq ev) {
      return this.lcm$mcF$sp(a, b, ev);
   }

   // $FF: synthetic method
   static float lcm$mcF$sp$(final EuclideanRing$mcF$sp $this, final float a, final float b, final Eq ev) {
      return $this.lcm$mcF$sp(a, b, ev);
   }

   default float lcm$mcF$sp(final float a, final float b, final Eq ev) {
      return !this.isZero$mcF$sp(a, ev) && !this.isZero$mcF$sp(b, ev) ? this.times$mcF$sp(this.equot$mcF$sp(a, this.gcd$mcF$sp(a, b, ev)), b) : this.zero$mcF$sp();
   }
}
