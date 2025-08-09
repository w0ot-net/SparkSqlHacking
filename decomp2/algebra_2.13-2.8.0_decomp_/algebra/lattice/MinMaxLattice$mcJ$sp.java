package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public class MinMaxLattice$mcJ$sp extends MinMaxLattice implements DistributiveLattice$mcJ$sp {
   public final Order order$mcJ$sp;

   public Lattice dual() {
      return Lattice$mcJ$sp.dual$(this);
   }

   public Lattice dual$mcJ$sp() {
      return Lattice$mcJ$sp.dual$mcJ$sp$(this);
   }

   public Semilattice joinSemilattice() {
      return JoinSemilattice$mcJ$sp.joinSemilattice$(this);
   }

   public Semilattice joinSemilattice$mcJ$sp() {
      return JoinSemilattice$mcJ$sp.joinSemilattice$mcJ$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice$mcJ$sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return JoinSemilattice$mcJ$sp.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public Semilattice meetSemilattice() {
      return MeetSemilattice$mcJ$sp.meetSemilattice$(this);
   }

   public Semilattice meetSemilattice$mcJ$sp() {
      return MeetSemilattice$mcJ$sp.meetSemilattice$mcJ$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
      return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
   }

   public long join(final long x, final long y) {
      return this.join$mcJ$sp(x, y);
   }

   public long join$mcJ$sp(final long x, final long y) {
      return this.order$mcJ$sp.max$mcJ$sp(x, y);
   }

   public long meet(final long x, final long y) {
      return this.meet$mcJ$sp(x, y);
   }

   public long meet$mcJ$sp(final long x, final long y) {
      return this.order$mcJ$sp.min$mcJ$sp(x, y);
   }

   public MinMaxLattice$mcJ$sp(final Order order$mcJ$sp) {
      super(order$mcJ$sp);
      this.order$mcJ$sp = order$mcJ$sp;
   }
}
