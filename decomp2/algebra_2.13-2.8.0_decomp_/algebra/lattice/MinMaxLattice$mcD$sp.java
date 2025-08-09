package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public class MinMaxLattice$mcD$sp extends MinMaxLattice implements DistributiveLattice$mcD$sp {
   public final Order order$mcD$sp;

   public Lattice dual() {
      return Lattice$mcD$sp.dual$(this);
   }

   public Lattice dual$mcD$sp() {
      return Lattice$mcD$sp.dual$mcD$sp$(this);
   }

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

   public double join(final double x, final double y) {
      return this.join$mcD$sp(x, y);
   }

   public double join$mcD$sp(final double x, final double y) {
      return this.order$mcD$sp.max$mcD$sp(x, y);
   }

   public double meet(final double x, final double y) {
      return this.meet$mcD$sp(x, y);
   }

   public double meet$mcD$sp(final double x, final double y) {
      return this.order$mcD$sp.min$mcD$sp(x, y);
   }

   public MinMaxLattice$mcD$sp(final Order order$mcD$sp) {
      super(order$mcD$sp);
      this.order$mcD$sp = order$mcD$sp;
   }
}
