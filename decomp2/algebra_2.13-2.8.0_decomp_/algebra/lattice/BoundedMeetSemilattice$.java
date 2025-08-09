package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BoundedMeetSemilattice$ implements BoundedMeetSemilatticeFunctions, Serializable {
   public static final BoundedMeetSemilattice$ MODULE$ = new BoundedMeetSemilattice$();

   static {
      MeetSemilatticeFunctions.$init$(MODULE$);
      BoundedMeetSemilatticeFunctions.$init$(MODULE$);
   }

   public Object one(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$(this, ev);
   }

   public double one$mDc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mDc$sp$(this, ev);
   }

   public float one$mFc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mFc$sp$(this, ev);
   }

   public int one$mIc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mIc$sp$(this, ev);
   }

   public long one$mJc$sp(final BoundedMeetSemilattice ev) {
      return BoundedMeetSemilatticeFunctions.one$mJc$sp$(this, ev);
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

   public final BoundedMeetSemilattice apply(final BoundedMeetSemilattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BoundedMeetSemilattice$.class);
   }

   public final BoundedMeetSemilattice apply$mDc$sp(final BoundedMeetSemilattice ev) {
      return ev;
   }

   public final BoundedMeetSemilattice apply$mFc$sp(final BoundedMeetSemilattice ev) {
      return ev;
   }

   public final BoundedMeetSemilattice apply$mIc$sp(final BoundedMeetSemilattice ev) {
      return ev;
   }

   public final BoundedMeetSemilattice apply$mJc$sp(final BoundedMeetSemilattice ev) {
      return ev;
   }

   private BoundedMeetSemilattice$() {
   }
}
