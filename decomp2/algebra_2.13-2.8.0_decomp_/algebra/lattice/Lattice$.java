package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Lattice$ implements JoinSemilatticeFunctions, MeetSemilatticeFunctions, Serializable {
   public static final Lattice$ MODULE$ = new Lattice$();

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

   public final Lattice apply(final Lattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Lattice$.class);
   }

   public final Lattice apply$mDc$sp(final Lattice ev) {
      return ev;
   }

   public final Lattice apply$mFc$sp(final Lattice ev) {
      return ev;
   }

   public final Lattice apply$mIc$sp(final Lattice ev) {
      return ev;
   }

   public final Lattice apply$mJc$sp(final Lattice ev) {
      return ev;
   }

   private Lattice$() {
   }
}
