package algebra.ring;

import scala.math.BigInt;

public interface Ring$mcD$sp extends Ring, Rng$mcD$sp, Rig$mcD$sp {
   // $FF: synthetic method
   static double fromInt$(final Ring$mcD$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default double fromInt(final int n) {
      return this.fromInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static double fromInt$mcD$sp$(final Ring$mcD$sp $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default double fromInt$mcD$sp(final int n) {
      return this.sumN$mcD$sp(this.one$mcD$sp(), n);
   }

   // $FF: synthetic method
   static double fromBigInt$(final Ring$mcD$sp $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default double fromBigInt(final BigInt n) {
      return this.fromBigInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static double fromBigInt$mcD$sp$(final Ring$mcD$sp $this, final BigInt n) {
      return $this.fromBigInt$mcD$sp(n);
   }

   default double fromBigInt$mcD$sp(final BigInt n) {
      return Ring$.MODULE$.defaultFromBigInt$mDc$sp(n, this);
   }
}
