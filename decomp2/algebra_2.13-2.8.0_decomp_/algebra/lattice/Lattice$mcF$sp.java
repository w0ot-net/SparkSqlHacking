package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public interface Lattice$mcF$sp extends Lattice, MeetSemilattice$mcF$sp, JoinSemilattice$mcF$sp {
   // $FF: synthetic method
   static Lattice dual$(final Lattice$mcF$sp $this) {
      return $this.dual();
   }

   default Lattice dual() {
      return this.dual$mcF$sp();
   }

   // $FF: synthetic method
   static Lattice dual$mcF$sp$(final Lattice$mcF$sp $this) {
      return $this.dual$mcF$sp();
   }

   default Lattice dual$mcF$sp() {
      return new Lattice$mcF$sp() {
         // $FF: synthetic field
         private final Lattice$mcF$sp $outer;

         public Semilattice joinSemilattice() {
            return JoinSemilattice$mcF$sp.joinSemilattice$(this);
         }

         public Semilattice joinSemilattice$mcF$sp() {
            return JoinSemilattice$mcF$sp.joinSemilattice$mcF$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcF$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice$mcF$sp.joinPartialOrder$mcF$sp$(this, ev);
         }

         public Semilattice meetSemilattice() {
            return MeetSemilattice$mcF$sp.meetSemilattice$(this);
         }

         public Semilattice meetSemilattice$mcF$sp() {
            return MeetSemilattice$mcF$sp.meetSemilattice$mcF$sp$(this);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice$mcF$sp.meetPartialOrder$mcF$sp$(this, ev);
         }

         public Lattice dual$mcD$sp() {
            return Lattice.dual$mcD$sp$(this);
         }

         public Lattice dual$mcI$sp() {
            return Lattice.dual$mcI$sp$(this);
         }

         public Lattice dual$mcJ$sp() {
            return Lattice.dual$mcJ$sp$(this);
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

         public Semilattice meetSemilattice$mcD$sp() {
            return MeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public Semilattice meetSemilattice$mcI$sp() {
            return MeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public Semilattice meetSemilattice$mcJ$sp() {
            return MeetSemilattice.meetSemilattice$mcJ$sp$(this);
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

         public Semilattice joinSemilattice$mcD$sp() {
            return JoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public Semilattice joinSemilattice$mcI$sp() {
            return JoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public Semilattice joinSemilattice$mcJ$sp() {
            return JoinSemilattice.joinSemilattice$mcJ$sp$(this);
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

         public Lattice dual() {
            return this.dual$mcF$sp();
         }

         public float meet$mcF$sp(final float a, final float b) {
            return this.$outer.join$mcF$sp(a, b);
         }

         public float join$mcF$sp(final float a, final float b) {
            return this.$outer.meet$mcF$sp(a, b);
         }

         public Lattice dual$mcF$sp() {
            return this.$outer;
         }

         public {
            if (Lattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = Lattice$mcF$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
            }
         }
      };
   }
}
