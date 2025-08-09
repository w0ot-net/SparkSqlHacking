package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class JoinSemilattice$ implements JoinSemilatticeFunctions, Serializable {
   public static final JoinSemilattice$ MODULE$ = new JoinSemilattice$();

   static {
      JoinSemilatticeFunctions.$init$(MODULE$);
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

   public final JoinSemilattice apply(final JoinSemilattice ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JoinSemilattice$.class);
   }

   public final JoinSemilattice apply$mDc$sp(final JoinSemilattice ev) {
      return ev;
   }

   public final JoinSemilattice apply$mFc$sp(final JoinSemilattice ev) {
      return ev;
   }

   public final JoinSemilattice apply$mIc$sp(final JoinSemilattice ev) {
      return ev;
   }

   public final JoinSemilattice apply$mJc$sp(final JoinSemilattice ev) {
      return ev;
   }

   private JoinSemilattice$() {
   }
}
