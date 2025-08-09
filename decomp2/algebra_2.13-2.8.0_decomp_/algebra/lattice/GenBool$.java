package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class GenBool$ implements GenBoolFunctions, Serializable {
   public static final GenBool$ MODULE$ = new GenBool$();

   static {
      JoinSemilatticeFunctions.$init$(MODULE$);
      BoundedJoinSemilatticeFunctions.$init$(MODULE$);
      MeetSemilatticeFunctions.$init$(MODULE$);
      GenBoolFunctions.$init$(MODULE$);
   }

   public Object and(final Object x, final Object y, final GenBool ev) {
      return GenBoolFunctions.and$(this, x, y, ev);
   }

   public int and$mIc$sp(final int x, final int y, final GenBool ev) {
      return GenBoolFunctions.and$mIc$sp$(this, x, y, ev);
   }

   public long and$mJc$sp(final long x, final long y, final GenBool ev) {
      return GenBoolFunctions.and$mJc$sp$(this, x, y, ev);
   }

   public Object or(final Object x, final Object y, final GenBool ev) {
      return GenBoolFunctions.or$(this, x, y, ev);
   }

   public int or$mIc$sp(final int x, final int y, final GenBool ev) {
      return GenBoolFunctions.or$mIc$sp$(this, x, y, ev);
   }

   public long or$mJc$sp(final long x, final long y, final GenBool ev) {
      return GenBoolFunctions.or$mJc$sp$(this, x, y, ev);
   }

   public Object without(final Object x, final Object y, final GenBool ev) {
      return GenBoolFunctions.without$(this, x, y, ev);
   }

   public int without$mIc$sp(final int x, final int y, final GenBool ev) {
      return GenBoolFunctions.without$mIc$sp$(this, x, y, ev);
   }

   public long without$mJc$sp(final long x, final long y, final GenBool ev) {
      return GenBoolFunctions.without$mJc$sp$(this, x, y, ev);
   }

   public Object xor(final Object x, final Object y, final GenBool ev) {
      return GenBoolFunctions.xor$(this, x, y, ev);
   }

   public int xor$mIc$sp(final int x, final int y, final GenBool ev) {
      return GenBoolFunctions.xor$mIc$sp$(this, x, y, ev);
   }

   public long xor$mJc$sp(final long x, final long y, final GenBool ev) {
      return GenBoolFunctions.xor$mJc$sp$(this, x, y, ev);
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

   public final GenBool apply(final GenBool ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GenBool$.class);
   }

   public final GenBool apply$mIc$sp(final GenBool ev) {
      return ev;
   }

   public final GenBool apply$mJc$sp(final GenBool ev) {
      return ev;
   }

   private GenBool$() {
   }
}
