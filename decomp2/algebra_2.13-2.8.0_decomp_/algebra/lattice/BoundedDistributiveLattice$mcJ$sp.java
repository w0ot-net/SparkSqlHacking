package algebra.lattice;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeMonoid$mcJ$sp;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcJ$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcJ$sp;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRig$mcJ$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeMonoid$mcJ$sp;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcJ$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcJ$sp;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedDistributiveLattice$mcJ$sp extends BoundedDistributiveLattice, DistributiveLattice$mcJ$sp, BoundedLattice$mcJ$sp {
   default CommutativeRig asCommutativeRig() {
      return this.asCommutativeRig$mcJ$sp();
   }

   default CommutativeRig asCommutativeRig$mcJ$sp() {
      return new CommutativeRig$mcJ$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcJ$sp $outer;

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid$mcJ$sp.additive$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid$mcJ$sp.additive$mcJ$sp$(this);
         }

         public boolean isZero(final long a, final Eq ev) {
            return AdditiveMonoid$mcJ$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid$mcJ$sp.isZero$mcJ$sp$(this, a, ev);
         }

         public long sumN(final long a, final int n) {
            return AdditiveMonoid$mcJ$sp.sumN$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid$mcJ$sp.sumN$mcJ$sp$(this, a, n);
         }

         public long sum(final IterableOnce as) {
            return AdditiveMonoid$mcJ$sp.sum$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid$mcJ$sp.sum$mcJ$sp$(this, as);
         }

         public long positiveSumN(final long a, final int n) {
            return AdditiveSemigroup$mcJ$sp.positiveSumN$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup$mcJ$sp.positiveSumN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid$mcJ$sp.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid$mcJ$sp.multiplicative$mcJ$sp$(this);
         }

         public boolean isOne(final long a, final Eq ev) {
            return MultiplicativeMonoid$mcJ$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid$mcJ$sp.isOne$mcJ$sp$(this, a, ev);
         }

         public long pow(final long a, final int n) {
            return MultiplicativeMonoid$mcJ$sp.pow$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid$mcJ$sp.pow$mcJ$sp$(this, a, n);
         }

         public long product(final IterableOnce as) {
            return MultiplicativeMonoid$mcJ$sp.product$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid$mcJ$sp.product$mcJ$sp$(this, as);
         }

         public long positivePow(final long a, final int n) {
            return MultiplicativeSemigroup$mcJ$sp.positivePow$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup$mcJ$sp.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public double zero$mcD$sp() {
            return AdditiveMonoid.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long zero() {
            return this.zero$mcJ$sp();
         }

         public long one() {
            return this.one$mcJ$sp();
         }

         public long plus(final long x, final long y) {
            return this.plus$mcJ$sp(x, y);
         }

         public long times(final long x, final long y) {
            return this.times$mcJ$sp(x, y);
         }

         public long zero$mcJ$sp() {
            return this.$outer.zero$mcJ$sp();
         }

         public long one$mcJ$sp() {
            return this.$outer.one$mcJ$sp();
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return this.$outer.join$mcJ$sp(x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return this.$outer.meet$mcJ$sp(x, y);
         }

         public {
            if (BoundedDistributiveLattice$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcJ$sp.this;
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
   static BoundedDistributiveLattice dual$(final BoundedDistributiveLattice$mcJ$sp $this) {
      return $this.dual();
   }

   default BoundedDistributiveLattice dual() {
      return this.dual$mcJ$sp();
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$mcJ$sp$(final BoundedDistributiveLattice$mcJ$sp $this) {
      return $this.dual$mcJ$sp();
   }

   default BoundedDistributiveLattice dual$mcJ$sp() {
      return new BoundedDistributiveLattice$mcJ$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcJ$sp $outer;

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcJ$sp.super.asCommutativeRig();
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice$mcJ$sp.super.asCommutativeRig$mcJ$sp();
         }

         public boolean isOne(final long a, final Eq ev) {
            return BoundedMeetSemilattice$mcJ$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice$mcJ$sp.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$mcJ$sp$(this);
         }

         public boolean isZero(final long a, final Eq ev) {
            return BoundedJoinSemilattice$mcJ$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice$mcJ$sp.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$mcJ$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcI$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.dual$mcD$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.dual$mcF$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice.dual$mcI$sp$(this);
         }

         public double zero$mcD$sp() {
            return BoundedJoinSemilattice.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return BoundedJoinSemilattice.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return BoundedJoinSemilattice.zero$mcI$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return BoundedMeetSemilattice.one$mcI$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
         }

         public long meet(final long a, final long b) {
            return this.meet$mcJ$sp(a, b);
         }

         public long join(final long a, final long b) {
            return this.join$mcJ$sp(a, b);
         }

         public long one() {
            return this.one$mcJ$sp();
         }

         public long zero() {
            return this.zero$mcJ$sp();
         }

         public BoundedDistributiveLattice dual() {
            return this.dual$mcJ$sp();
         }

         public long meet$mcJ$sp(final long a, final long b) {
            return this.$outer.join$mcJ$sp(a, b);
         }

         public long join$mcJ$sp(final long a, final long b) {
            return this.$outer.meet$mcJ$sp(a, b);
         }

         public long one$mcJ$sp() {
            return this.$outer.zero$mcJ$sp();
         }

         public long zero$mcJ$sp() {
            return this.$outer.one$mcJ$sp();
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return this.$outer;
         }

         public {
            if (BoundedDistributiveLattice$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcJ$sp.this;
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
