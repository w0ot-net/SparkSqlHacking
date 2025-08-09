package algebra.lattice;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeMonoid$mcD$sp;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcD$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcD$sp;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRig$mcD$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeMonoid$mcD$sp;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcD$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcD$sp;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedDistributiveLattice$mcD$sp extends BoundedDistributiveLattice, DistributiveLattice$mcD$sp, BoundedLattice$mcD$sp {
   default CommutativeRig asCommutativeRig() {
      return this.asCommutativeRig$mcD$sp();
   }

   default CommutativeRig asCommutativeRig$mcD$sp() {
      return new CommutativeRig$mcD$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcD$sp $outer;

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid$mcD$sp.additive$(this);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid$mcD$sp.additive$mcD$sp$(this);
         }

         public boolean isZero(final double a, final Eq ev) {
            return AdditiveMonoid$mcD$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid$mcD$sp.isZero$mcD$sp$(this, a, ev);
         }

         public double sumN(final double a, final int n) {
            return AdditiveMonoid$mcD$sp.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid$mcD$sp.sumN$mcD$sp$(this, a, n);
         }

         public double sum(final IterableOnce as) {
            return AdditiveMonoid$mcD$sp.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid$mcD$sp.sum$mcD$sp$(this, as);
         }

         public double positiveSumN(final double a, final int n) {
            return AdditiveSemigroup$mcD$sp.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup$mcD$sp.positiveSumN$mcD$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid$mcD$sp.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid$mcD$sp.multiplicative$mcD$sp$(this);
         }

         public boolean isOne(final double a, final Eq ev) {
            return MultiplicativeMonoid$mcD$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid$mcD$sp.isOne$mcD$sp$(this, a, ev);
         }

         public double pow(final double a, final int n) {
            return MultiplicativeMonoid$mcD$sp.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid$mcD$sp.pow$mcD$sp$(this, a, n);
         }

         public double product(final IterableOnce as) {
            return MultiplicativeMonoid$mcD$sp.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid$mcD$sp.product$mcD$sp$(this, as);
         }

         public double positivePow(final double a, final int n) {
            return MultiplicativeSemigroup$mcD$sp.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup$mcD$sp.positivePow$mcD$sp$(this, a, n);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
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

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
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

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public double zero() {
            return this.zero$mcD$sp();
         }

         public double one() {
            return this.one$mcD$sp();
         }

         public double plus(final double x, final double y) {
            return this.plus$mcD$sp(x, y);
         }

         public double times(final double x, final double y) {
            return this.times$mcD$sp(x, y);
         }

         public double zero$mcD$sp() {
            return this.$outer.zero$mcD$sp();
         }

         public double one$mcD$sp() {
            return this.$outer.one$mcD$sp();
         }

         public double plus$mcD$sp(final double x, final double y) {
            return this.$outer.join$mcD$sp(x, y);
         }

         public double times$mcD$sp(final double x, final double y) {
            return this.$outer.meet$mcD$sp(x, y);
         }

         public {
            if (BoundedDistributiveLattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcD$sp.this;
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
   static BoundedDistributiveLattice dual$(final BoundedDistributiveLattice$mcD$sp $this) {
      return $this.dual();
   }

   default BoundedDistributiveLattice dual() {
      return this.dual$mcD$sp();
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$mcD$sp$(final BoundedDistributiveLattice$mcD$sp $this) {
      return $this.dual$mcD$sp();
   }

   default BoundedDistributiveLattice dual$mcD$sp() {
      return new BoundedDistributiveLattice$mcD$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcD$sp $outer;

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcD$sp.super.asCommutativeRig();
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice$mcD$sp.super.asCommutativeRig$mcD$sp();
         }

         public boolean isOne(final double a, final Eq ev) {
            return BoundedMeetSemilattice$mcD$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice$mcD$sp.isOne$mcD$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcD$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice$mcD$sp.meetSemilattice$mcD$sp$(this);
         }

         public boolean isZero(final double a, final Eq ev) {
            return BoundedJoinSemilattice$mcD$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice$mcD$sp.isZero$mcD$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcD$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice$mcD$sp.joinSemilattice$mcD$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcD$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice$mcD$sp.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$mcD$sp$(this, ev);
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcI$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.dual$mcF$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return BoundedDistributiveLattice.dual$mcI$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return BoundedDistributiveLattice.dual$mcJ$sp$(this);
         }

         public float zero$mcF$sp() {
            return BoundedJoinSemilattice.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return BoundedJoinSemilattice.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return BoundedMeetSemilattice.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public double meet(final double a, final double b) {
            return this.meet$mcD$sp(a, b);
         }

         public double join(final double a, final double b) {
            return this.join$mcD$sp(a, b);
         }

         public double one() {
            return this.one$mcD$sp();
         }

         public double zero() {
            return this.zero$mcD$sp();
         }

         public BoundedDistributiveLattice dual() {
            return this.dual$mcD$sp();
         }

         public double meet$mcD$sp(final double a, final double b) {
            return this.$outer.join$mcD$sp(a, b);
         }

         public double join$mcD$sp(final double a, final double b) {
            return this.$outer.meet$mcD$sp(a, b);
         }

         public double one$mcD$sp() {
            return this.$outer.zero$mcD$sp();
         }

         public double zero$mcD$sp() {
            return this.$outer.one$mcD$sp();
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return this.$outer;
         }

         public {
            if (BoundedDistributiveLattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcD$sp.this;
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
