package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BoundedLattice$ implements BoundedMeetSemilatticeFunctions, BoundedJoinSemilatticeFunctions, Serializable {
   public static final BoundedLattice$ MODULE$ = new BoundedLattice$();

   static {
      MeetSemilatticeFunctions.$init$(MODULE$);
      BoundedMeetSemilatticeFunctions.$init$(MODULE$);
      JoinSemilatticeFunctions.$init$(MODULE$);
      BoundedJoinSemilatticeFunctions.$init$(MODULE$);
   }

   public Object zero(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$(this, ev);
   }

   public double zero$mDc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mDc$sp$(this, ev);
   }

   public float zero$mFc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mFc$sp$(this, ev);
   }

   public int zero$mIc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mIc$sp$(this, ev);
   }

   public long zero$mJc$sp(final BoundedJoinSemilattice ev) {
      return BoundedJoinSemilatticeFunctions.zero$mJc$sp$(this, ev);
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

   public final BoundedLattice apply(final BoundedLattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BoundedLattice$.class);
   }

   public final BoundedLattice apply$mDc$sp(final BoundedLattice ev) {
      return ev;
   }

   public final BoundedLattice apply$mFc$sp(final BoundedLattice ev) {
      return ev;
   }

   public final BoundedLattice apply$mIc$sp(final BoundedLattice ev) {
      return ev;
   }

   public final BoundedLattice apply$mJc$sp(final BoundedLattice ev) {
      return ev;
   }

   private BoundedLattice$() {
   }
}
