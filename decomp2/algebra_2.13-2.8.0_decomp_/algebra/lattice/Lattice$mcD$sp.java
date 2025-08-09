package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public interface Lattice$mcD$sp extends Lattice, MeetSemilattice$mcD$sp, JoinSemilattice$mcD$sp {
   // $FF: synthetic method
   static Lattice dual$(final Lattice$mcD$sp $this) {
      return $this.dual();
   }

   default Lattice dual() {
      return this.dual$mcD$sp();
   }

   // $FF: synthetic method
   static Lattice dual$mcD$sp$(final Lattice$mcD$sp $this) {
      return $this.dual$mcD$sp();
   }

   default Lattice dual$mcD$sp() {
      return new Lattice$mcD$sp() {
         // $FF: synthetic field
         private final Lattice$mcD$sp $outer;

         public Semilattice joinSemilattice() {
            return JoinSemilattice$mcD$sp.joinSemilattice$(this);
         }

         public Semilattice joinSemilattice$mcD$sp() {
            return JoinSemilattice$mcD$sp.joinSemilattice$mcD$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcD$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice$mcD$sp.joinPartialOrder$mcD$sp$(this, ev);
         }

         public Semilattice meetSemilattice() {
            return MeetSemilattice$mcD$sp.meetSemilattice$(this);
         }

         public Semilattice meetSemilattice$mcD$sp() {
            return MeetSemilattice$mcD$sp.meetSemilattice$mcD$sp$(this);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice$mcD$sp.meetPartialOrder$mcD$sp$(this, ev);
         }

         public Lattice dual$mcF$sp() {
            return Lattice.dual$mcF$sp$(this);
         }

         public Lattice dual$mcI$sp() {
            return Lattice.dual$mcI$sp$(this);
         }

         public Lattice dual$mcJ$sp() {
            return Lattice.dual$mcJ$sp$(this);
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

         public Semilattice meetSemilattice$mcF$sp() {
            return MeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public Semilattice meetSemilattice$mcI$sp() {
            return MeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public Semilattice meetSemilattice$mcJ$sp() {
            return MeetSemilattice.meetSemilattice$mcJ$sp$(this);
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

         public Semilattice joinSemilattice$mcF$sp() {
            return JoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public Semilattice joinSemilattice$mcI$sp() {
            return JoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public Semilattice joinSemilattice$mcJ$sp() {
            return JoinSemilattice.joinSemilattice$mcJ$sp$(this);
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

         public Lattice dual() {
            return this.dual$mcD$sp();
         }

         public double meet$mcD$sp(final double a, final double b) {
            return this.$outer.join$mcD$sp(a, b);
         }

         public double join$mcD$sp(final double a, final double b) {
            return this.$outer.meet$mcD$sp(a, b);
         }

         public Lattice dual$mcD$sp() {
            return this.$outer;
         }

         public {
            if (Lattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Lattice$mcD$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
            }
         }
      };
   }
}
