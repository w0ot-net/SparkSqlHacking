package algebra.lattice;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeMonoid$mcF$sp;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcF$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcF$sp;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRig$mcF$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeMonoid$mcF$sp;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcF$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcF$sp;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedDistributiveLattice$mcF$sp extends BoundedDistributiveLattice, DistributiveLattice$mcF$sp, BoundedLattice$mcF$sp {
   default CommutativeRig asCommutativeRig() {
      return this.asCommutativeRig$mcF$sp();
   }

   default CommutativeRig asCommutativeRig$mcF$sp() {
      return new CommutativeRig$mcF$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcF$sp $outer;

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid$mcF$sp.additive$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid$mcF$sp.additive$mcF$sp$(this);
         }

         public boolean isZero(final float a, final Eq ev) {
            return AdditiveMonoid$mcF$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid$mcF$sp.isZero$mcF$sp$(this, a, ev);
         }

         public float sumN(final float a, final int n) {
            return AdditiveMonoid$mcF$sp.sumN$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid$mcF$sp.sumN$mcF$sp$(this, a, n);
         }

         public float sum(final IterableOnce as) {
            return AdditiveMonoid$mcF$sp.sum$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid$mcF$sp.sum$mcF$sp$(this, as);
         }

         public float positiveSumN(final float a, final int n) {
            return AdditiveSemigroup$mcF$sp.positiveSumN$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup$mcF$sp.positiveSumN$mcF$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid$mcF$sp.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid$mcF$sp.multiplicative$mcF$sp$(this);
         }

         public boolean isOne(final float a, final Eq ev) {
            return MultiplicativeMonoid$mcF$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid$mcF$sp.isOne$mcF$sp$(this, a, ev);
         }

         public float pow(final float a, final int n) {
            return MultiplicativeMonoid$mcF$sp.pow$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid$mcF$sp.pow$mcF$sp$(this, a, n);
         }

         public float product(final IterableOnce as) {
            return MultiplicativeMonoid$mcF$sp.product$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid$mcF$sp.product$mcF$sp$(this, as);
         }

         public float positivePow(final float a, final int n) {
            return MultiplicativeSemigroup$mcF$sp.positivePow$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup$mcF$sp.positivePow$mcF$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
         }

         public double zero$mcD$sp() {
            return AdditiveMonoid.zero$mcD$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public float zero() {
            return this.zero$mcF$sp();
         }

         public float one() {
            return this.one$mcF$sp();
         }

         public float plus(final float x, final float y) {
            return this.plus$mcF$sp(x, y);
         }

         public float times(final float x, final float y) {
            return this.times$mcF$sp(x, y);
         }

         public float zero$mcF$sp() {
            return this.$outer.zero$mcF$sp();
         }

         public float one$mcF$sp() {
            return this.$outer.one$mcF$sp();
         }

         public float plus$mcF$sp(final float x, final float y) {
            return this.$outer.join$mcF$sp(x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return this.$outer.meet$mcF$sp(x, y);
         }

         public {
            if (BoundedDistributiveLattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcF$sp.this;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$(final BoundedDistributiveLattice$mcF$sp $this) {
      return $this.dual();
   }

   default BoundedDistributiveLattice dual() {
      return this.dual$mcF$sp();
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$mcF$sp$(final BoundedDistributiveLattice$mcF$sp $this) {
      return $this.dual$mcF$sp();
   }

   default BoundedDistributiveLattice dual$mcF$sp() {
      return new BoundedDistributiveLattice$mcF$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcF$sp $outer;

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcF$sp.super.asCommutativeRig();
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice$mcF$sp.super.asCommutativeRig$mcF$sp();
         }

         public boolean isOne(final float a, final Eq ev) {
            return BoundedMeetSemilattice$mcF$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice$mcF$sp.isOne$mcF$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcF$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice$mcF$sp.meetSemilattice$mcF$sp$(this);
         }

         public boolean isZero(final float a, final Eq ev) {
            return BoundedJoinSemilattice$mcF$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice$mcF$sp.isZero$mcF$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcF$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice$mcF$sp.joinSemilattice$mcF$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcF$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice$mcF$sp.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$mcF$sp$(this, ev);
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcI$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.dual$mcD$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice.dual$mcI$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return BoundedDistributiveLattice.dual$mcJ$sp$(this);
         }

         public double zero$mcD$sp() {
            return BoundedJoinSemilattice.zero$mcD$sp$(this);
         }

         public int zero$mcI$sp() {
            return BoundedJoinSemilattice.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public int one$mcI$sp() {
            return BoundedMeetSemilattice.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public float meet(final float a, final float b) {
            return this.meet$mcF$sp(a, b);
         }

         public float join(final float a, final float b) {
            return this.join$mcF$sp(a, b);
         }

         public float one() {
            return this.one$mcF$sp();
         }

         public float zero() {
            return this.zero$mcF$sp();
         }

         public BoundedDistributiveLattice dual() {
            return this.dual$mcF$sp();
         }

         public float meet$mcF$sp(final float a, final float b) {
            return this.$outer.join$mcF$sp(a, b);
         }

         public float join$mcF$sp(final float a, final float b) {
            return this.$outer.meet$mcF$sp(a, b);
         }

         public float one$mcF$sp() {
            return this.$outer.zero$mcF$sp();
         }

         public float zero$mcF$sp() {
            return this.$outer.one$mcF$sp();
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return this.$outer;
         }

         public {
            if (BoundedDistributiveLattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcF$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
               BoundedMeetSemilattice.$init$(this);
               BoundedJoinSemilattice.$init$(this);
               BoundedLattice.$init$(this);
               BoundedDistributiveLattice.$init$(this);
            }
         }
      };
   }
}
