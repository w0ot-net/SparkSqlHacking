package algebra.lattice;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public interface BoundedLattice$mcF$sp extends BoundedLattice, BoundedJoinSemilattice$mcF$sp, BoundedMeetSemilattice$mcF$sp, Lattice$mcF$sp {
   // $FF: synthetic method
   static BoundedLattice dual$(final BoundedLattice$mcF$sp $this) {
      return $this.dual();
   }

   default BoundedLattice dual() {
      return this.dual$mcF$sp();
   }

   // $FF: synthetic method
   static BoundedLattice dual$mcF$sp$(final BoundedLattice$mcF$sp $this) {
      return $this.dual$mcF$sp();
   }

   default BoundedLattice dual$mcF$sp() {
      return new BoundedLattice$mcF$sp() {
         // $FF: synthetic field
         private final BoundedLattice$mcF$sp $outer;

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

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$mcF$sp$(this, ev);
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

         public BoundedLattice dual$mcD$sp() {
            return BoundedLattice.dual$mcD$sp$(this);
         }

         public BoundedLattice dual$mcI$sp() {
            return BoundedLattice.dual$mcI$sp$(this);
         }

         public BoundedLattice dual$mcJ$sp() {
            return BoundedLattice.dual$mcJ$sp$(this);
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

         public BoundedLattice dual() {
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

         public BoundedLattice dual$mcF$sp() {
            return this.$outer;
         }

         public {
            if (BoundedLattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedLattice$mcF$sp.this;
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
