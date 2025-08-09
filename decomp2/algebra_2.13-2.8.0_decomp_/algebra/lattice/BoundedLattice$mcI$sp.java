package algebra.lattice;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public interface BoundedLattice$mcI$sp extends BoundedLattice, BoundedJoinSemilattice$mcI$sp, BoundedMeetSemilattice$mcI$sp, Lattice$mcI$sp {
   // $FF: synthetic method
   static BoundedLattice dual$(final BoundedLattice$mcI$sp $this) {
      return $this.dual();
   }

   default BoundedLattice dual() {
      return this.dual$mcI$sp();
   }

   // $FF: synthetic method
   static BoundedLattice dual$mcI$sp$(final BoundedLattice$mcI$sp $this) {
      return $this.dual$mcI$sp();
   }

   default BoundedLattice dual$mcI$sp() {
      return new BoundedLattice$mcI$sp() {
         // $FF: synthetic field
         private final BoundedLattice$mcI$sp $outer;

         public boolean isOne(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$mcI$sp$(this);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
         }

         public boolean isZero(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$mcI$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$mcI$sp$(this, ev);
         }

         public BoundedLattice dual$mcD$sp() {
            return BoundedLattice.dual$mcD$sp$(this);
         }

         public BoundedLattice dual$mcF$sp() {
            return BoundedLattice.dual$mcF$sp$(this);
         }

         public BoundedLattice dual$mcJ$sp() {
            return BoundedLattice.dual$mcJ$sp$(this);
         }

         public double zero$mcD$sp() {
            return BoundedJoinSemilattice.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return BoundedJoinSemilattice.zero$mcF$sp$(this);
         }

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public int meet(final int a, final int b) {
            return this.meet$mcI$sp(a, b);
         }

         public int join(final int a, final int b) {
            return this.join$mcI$sp(a, b);
         }

         public int one() {
            return this.one$mcI$sp();
         }

         public int zero() {
            return this.zero$mcI$sp();
         }

         public BoundedLattice dual() {
            return this.dual$mcI$sp();
         }

         public int meet$mcI$sp(final int a, final int b) {
            return this.$outer.join$mcI$sp(a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return this.$outer.meet$mcI$sp(a, b);
         }

         public int one$mcI$sp() {
            return this.$outer.zero$mcI$sp();
         }

         public int zero$mcI$sp() {
            return this.$outer.one$mcI$sp();
         }

         public BoundedLattice dual$mcI$sp() {
            return this.$outer;
         }

         public {
            if (BoundedLattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedLattice$mcI$sp.this;
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
