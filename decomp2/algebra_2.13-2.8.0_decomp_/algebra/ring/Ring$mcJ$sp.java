package algebra.ring;

import scala.math.BigInt;

public interface Ring$mcJ$sp extends Ring, Rng$mcJ$sp, Rig$mcJ$sp {
   // $FF: synthetic method
   static long fromInt$(final Ring$mcJ$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default long fromInt(final int n) {
      return this.fromInt$mcJ$sp(n);
   }

   // $FF: synthetic method
   static long fromInt$mcJ$sp$(final Ring$mcJ$sp $this, final int n) {
      return $this.fromInt$mcJ$sp(n);
   }

   default long fromInt$mcJ$sp(final int n) {
      return this.sumN$mcJ$sp(this.one$mcJ$sp(), n);
   }

   // $FF: synthetic method
   static long fromBigInt$(final Ring$mcJ$sp $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default long fromBigInt(final BigInt n) {
      return this.fromBigInt$mcJ$sp(n);
   }

   // $FF: synthetic method
   static long fromBigInt$mcJ$sp$(final Ring$mcJ$sp $this, final BigInt n) {
      return $this.fromBigInt$mcJ$sp(n);
   }

   default long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring$.MODULE$.defaultFromBigInt$mJc$sp(n, this);
   }
}
