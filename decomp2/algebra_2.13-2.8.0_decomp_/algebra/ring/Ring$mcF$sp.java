package algebra.ring;

import scala.math.BigInt;

public interface Ring$mcF$sp extends Ring, Rng$mcF$sp, Rig$mcF$sp {
   // $FF: synthetic method
   static float fromInt$(final Ring$mcF$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default float fromInt(final int n) {
      return this.fromInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static float fromInt$mcF$sp$(final Ring$mcF$sp $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default float fromInt$mcF$sp(final int n) {
      return this.sumN$mcF$sp(this.one$mcF$sp(), n);
   }

   // $FF: synthetic method
   static float fromBigInt$(final Ring$mcF$sp $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default float fromBigInt(final BigInt n) {
      return this.fromBigInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static float fromBigInt$mcF$sp$(final Ring$mcF$sp $this, final BigInt n) {
      return $this.fromBigInt$mcF$sp(n);
   }

   default float fromBigInt$mcF$sp(final BigInt n) {
      return Ring$.MODULE$.defaultFromBigInt$mFc$sp(n, this);
   }
}
