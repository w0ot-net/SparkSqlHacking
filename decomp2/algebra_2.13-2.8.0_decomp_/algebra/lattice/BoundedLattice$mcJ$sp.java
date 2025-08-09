package algebra.lattice;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public interface BoundedLattice$mcJ$sp extends BoundedLattice, BoundedJoinSemilattice$mcJ$sp, BoundedMeetSemilattice$mcJ$sp, Lattice$mcJ$sp {
   // $FF: synthetic method
   static BoundedLattice dual$(final BoundedLattice$mcJ$sp $this) {
      return $this.dual();
   }

   default BoundedLattice dual() {
      return this.dual$mcJ$sp();
   }

   // $FF: synthetic method
   static BoundedLattice dual$mcJ$sp$(final BoundedLattice$mcJ$sp $this) {
      return $this.dual$mcJ$sp();
   }

   default BoundedLattice dual$mcJ$sp() {
      return new BoundedLattice$mcJ$sp() {
         // $FF: synthetic field
         private final BoundedLattice$mcJ$sp $outer;

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

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
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

         public BoundedLattice dual$mcD$sp() {
            return BoundedLattice.dual$mcD$sp$(this);
         }

         public BoundedLattice dual$mcF$sp() {
            return BoundedLattice.dual$mcF$sp$(this);
         }

         public BoundedLattice dual$mcI$sp() {
            return BoundedLattice.dual$mcI$sp$(this);
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

         public BoundedLattice dual() {
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

         public BoundedLattice dual$mcJ$sp() {
            return this.$outer;
         }

         public {
            if (BoundedLattice$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedLattice$mcJ$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
               BoundedMeetSemilattice.$init$(this);
               BoundedJoinSemilattice.$init$(this);
               BoundedLattice.$init$(this);
            }
         }
      };
   }
}
