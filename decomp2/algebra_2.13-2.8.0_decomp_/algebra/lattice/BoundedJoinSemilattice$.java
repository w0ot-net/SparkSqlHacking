package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BoundedJoinSemilattice$ implements BoundedJoinSemilatticeFunctions, Serializable {
   public static final BoundedJoinSemilattice$ MODULE$ = new BoundedJoinSemilattice$();

   static {
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

   public final BoundedJoinSemilattice apply(final BoundedJoinSemilattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BoundedJoinSemilattice$.class);
   }

   public final BoundedJoinSemilattice apply$mDc$sp(final BoundedJoinSemilattice ev) {
      return ev;
   }

   public final BoundedJoinSemilattice apply$mFc$sp(final BoundedJoinSemilattice ev) {
      return ev;
   }

   public final BoundedJoinSemilattice apply$mIc$sp(final BoundedJoinSemilattice ev) {
      return ev;
   }

   public final BoundedJoinSemilattice apply$mJc$sp(final BoundedJoinSemilattice ev) {
      return ev;
   }

   private BoundedJoinSemilattice$() {
   }
}
