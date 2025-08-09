package algebra.lattice;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public interface BoundedLattice$mcD$sp extends BoundedLattice, BoundedJoinSemilattice$mcD$sp, BoundedMeetSemilattice$mcD$sp, Lattice$mcD$sp {
   // $FF: synthetic method
   static BoundedLattice dual$(final BoundedLattice$mcD$sp $this) {
      return $this.dual();
   }

   default BoundedLattice dual() {
      return this.dual$mcD$sp();
   }

   // $FF: synthetic method
   static BoundedLattice dual$mcD$sp$(final BoundedLattice$mcD$sp $this) {
      return $this.dual$mcD$sp();
   }

   default BoundedLattice dual$mcD$sp() {
      return new BoundedLattice$mcD$sp() {
         // $FF: synthetic field
         private final BoundedLattice$mcD$sp $outer;

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

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$mcD$sp$(this, ev);
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

         public BoundedLattice dual$mcF$sp() {
            return BoundedLattice.dual$mcF$sp$(this);
         }

         public BoundedLattice dual$mcI$sp() {
            return BoundedLattice.dual$mcI$sp$(this);
         }

         public BoundedLattice dual$mcJ$sp() {
            return BoundedLattice.dual$mcJ$sp$(this);
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

         public BoundedLattice dual() {
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

         public BoundedLattice dual$mcD$sp() {
            return this.$outer;
         }

         public {
            if (BoundedLattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedLattice$mcD$sp.this;
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
