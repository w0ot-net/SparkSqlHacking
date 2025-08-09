package algebra.lattice;

import cats.kernel.Order;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class DistributiveLattice$ implements JoinSemilatticeFunctions, MeetSemilatticeFunctions, Serializable {
   public static final DistributiveLattice$ MODULE$ = new DistributiveLattice$();

   static {
      JoinSemilatticeFunctions.$init$(MODULE$);
      MeetSemilatticeFunctions.$init$(MODULE$);
   }

   public Object meet(final Object x, final Object y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$(this, x, y, ev);
   }

   public double meet$mDc$sp(final double x, final double y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mDc$sp$(this, x, y, ev);
   }

   public float meet$mFc$sp(final float x, final float y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mFc$sp$(this, x, y, ev);
   }

   public int meet$mIc$sp(final int x, final int y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mIc$sp$(this, x, y, ev);
   }

   public long meet$mJc$sp(final long x, final long y, final MeetSemilattice ev) {
      return MeetSemilatticeFunctions.meet$mJc$sp$(this, x, y, ev);
   }

   public Object join(final Object x, final Object y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$(this, x, y, ev);
   }

   public double join$mDc$sp(final double x, final double y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mDc$sp$(this, x, y, ev);
   }

   public float join$mFc$sp(final float x, final float y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mFc$sp$(this, x, y, ev);
   }

   public int join$mIc$sp(final int x, final int y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mIc$sp$(this, x, y, ev);
   }

   public long join$mJc$sp(final long x, final long y, final JoinSemilattice ev) {
      return JoinSemilatticeFunctions.join$mJc$sp$(this, x, y, ev);
   }

   public final DistributiveLattice apply(final DistributiveLattice ev) {
      return ev;
   }

   public DistributiveLattice minMax(final Order evidence$1) {
      return new MinMaxLattice(evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistributiveLattice$.class);
   }

   public final DistributiveLattice apply$mDc$sp(final DistributiveLattice ev) {
      return ev;
   }

   public final DistributiveLattice apply$mFc$sp(final DistributiveLattice ev) {
      return ev;
   }

   public final DistributiveLattice apply$mIc$sp(final DistributiveLattice ev) {
      return ev;
   }

   public final DistributiveLattice apply$mJc$sp(final DistributiveLattice ev) {
      return ev;
   }

   public DistributiveLattice minMax$mDc$sp(final Order evidence$1) {
      return new MinMaxLattice$mcD$sp(evidence$1);
   }

   public DistributiveLattice minMax$mFc$sp(final Order evidence$1) {
      return new MinMaxLattice$mcF$sp(evidence$1);
   }

   public DistributiveLattice minMax$mIc$sp(final Order evidence$1) {
      return new MinMaxLattice$mcI$sp(evidence$1);
   }

   public DistributiveLattice minMax$mJc$sp(final Order evidence$1) {
      return new MinMaxLattice$mcJ$sp(evidence$1);
   }

   private DistributiveLattice$() {
   }
}
