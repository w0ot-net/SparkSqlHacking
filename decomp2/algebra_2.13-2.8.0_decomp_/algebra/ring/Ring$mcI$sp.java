package algebra.ring;

import scala.math.BigInt;

public interface Ring$mcI$sp extends Ring, Rng$mcI$sp, Rig$mcI$sp {
   // $FF: synthetic method
   static int fromInt$(final Ring$mcI$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default int fromInt(final int n) {
      return this.fromInt$mcI$sp(n);
   }

   // $FF: synthetic method
   static int fromInt$mcI$sp$(final Ring$mcI$sp $this, final int n) {
      return $this.fromInt$mcI$sp(n);
   }

   default int fromInt$mcI$sp(final int n) {
      return this.sumN$mcI$sp(this.one$mcI$sp(), n);
   }

   // $FF: synthetic method
   static int fromBigInt$(final Ring$mcI$sp $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default int fromBigInt(final BigInt n) {
      return this.fromBigInt$mcI$sp(n);
   }

   // $FF: synthetic method
   static int fromBigInt$mcI$sp$(final Ring$mcI$sp $this, final BigInt n) {
      return $this.fromBigInt$mcI$sp(n);
   }

   default int fromBigInt$mcI$sp(final BigInt n) {
      return Ring$.MODULE$.defaultFromBigInt$mIc$sp(n, this);
   }
}
