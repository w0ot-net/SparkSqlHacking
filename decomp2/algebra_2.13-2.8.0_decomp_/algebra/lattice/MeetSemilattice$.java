package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MeetSemilattice$ implements MeetSemilatticeFunctions, Serializable {
   public static final MeetSemilattice$ MODULE$ = new MeetSemilattice$();

   static {
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

   public final MeetSemilattice apply(final MeetSemilattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MeetSemilattice$.class);
   }

   public final MeetSemilattice apply$mDc$sp(final MeetSemilattice ev) {
      return ev;
   }

   public final MeetSemilattice apply$mFc$sp(final MeetSemilattice ev) {
      return ev;
   }

   public final MeetSemilattice apply$mIc$sp(final MeetSemilattice ev) {
      return ev;
   }

   public final MeetSemilattice apply$mJc$sp(final MeetSemilattice ev) {
      return ev;
   }

   private MeetSemilattice$() {
   }
}
