package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public class MinMaxLattice$mcI$sp extends MinMaxLattice implements DistributiveLattice$mcI$sp {
   public final Order order$mcI$sp;

   public Lattice dual() {
      return Lattice$mcI$sp.dual$(this);
   }

   public Lattice dual$mcI$sp() {
      return Lattice$mcI$sp.dual$mcI$sp$(this);
   }

   public Semilattice joinSemilattice() {
      return JoinSemilattice$mcI$sp.joinSemilattice$(this);
   }

   public Semilattice joinSemilattice$mcI$sp() {
      return JoinSemilattice$mcI$sp.joinSemilattice$mcI$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice$mcI$sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
      return JoinSemilattice$mcI$sp.joinPartialOrder$mcI$sp$(this, ev);
   }

   public Semilattice meetSemilattice() {
      return MeetSemilattice$mcI$sp.meetSemilattice$(this);
   }

   public Semilattice meetSemilattice$mcI$sp() {
      return MeetSemilattice$mcI$sp.meetSemilattice$mcI$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
   }

   public int join(final int x, final int y) {
      return this.join$mcI$sp(x, y);
   }

   public int join$mcI$sp(final int x, final int y) {
      return this.order$mcI$sp.max$mcI$sp(x, y);
   }

   public int meet(final int x, final int y) {
      return this.meet$mcI$sp(x, y);
   }

   public int meet$mcI$sp(final int x, final int y) {
      return this.order$mcI$sp.min$mcI$sp(x, y);
   }

   public MinMaxLattice$mcI$sp(final Order order$mcI$sp) {
      super(order$mcI$sp);
      this.order$mcI$sp = order$mcI$sp;
   }
}
