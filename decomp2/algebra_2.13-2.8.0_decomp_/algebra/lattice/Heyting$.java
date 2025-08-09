package algebra.lattice;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Heyting$ implements HeytingFunctions, HeytingGenBoolOverlap, Serializable {
   public static final Heyting$ MODULE$ = new Heyting$();

   static {
      MeetSemilatticeFunctions.$init$(MODULE$);
      BoundedMeetSemilatticeFunctions.$init$(MODULE$);
      JoinSemilatticeFunctions.$init$(MODULE$);
      BoundedJoinSemilatticeFunctions.$init$(MODULE$);
      HeytingFunctions.$init$(MODULE$);
      HeytingGenBoolOverlap.$init$(MODULE$);
   }

   public Object and(final Object x, final Object y, final Heyting ev) {
      return HeytingGenBoolOverlap.and$(this, x, y, ev);
   }

   public int and$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingGenBoolOverlap.and$mIc$sp$(this, x, y, ev);
   }

   public long and$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingGenBoolOverlap.and$mJc$sp$(this, x, y, ev);
   }

   public Object or(final Object x, final Object y, final Heyting ev) {
      return HeytingGenBoolOverlap.or$(this, x, y, ev);
   }

   public int or$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingGenBoolOverlap.or$mIc$sp$(this, x, y, ev);
   }

   public long or$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingGenBoolOverlap.or$mJc$sp$(this, x, y, ev);
   }

   public Object xor(final Object x, final Object y, final Heyting ev) {
      return HeytingGenBoolOverlap.xor$(this, x, y, ev);
   }

   public int xor$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingGenBoolOverlap.xor$mIc$sp$(this, x, y, ev);
   }

   public long xor$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingGenBoolOverlap.xor$mJc$sp$(this, x, y, ev);
   }

   public Object complement(final Object x, final Heyting ev) {
      return HeytingFunctions.complement$(this, x, ev);
   }

   public int complement$mIc$sp(final int x, final Heyting ev) {
      return HeytingFunctions.complement$mIc$sp$(this, x, ev);
   }

   public long complement$mJc$sp(final long x, final Heyting ev) {
      return HeytingFunctions.complement$mJc$sp$(this, x, ev);
   }

   public Object imp(final Object x, final Object y, final Heyting ev) {
      return HeytingFunctions.imp$(this, x, y, ev);
   }

   public int imp$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingFunctions.imp$mIc$sp$(this, x, y, ev);
   }

   public long imp$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingFunctions.imp$mJc$sp$(this, x, y, ev);
   }

   public Object nor(final Object x, final Object y, final Heyting ev) {
      return HeytingFunctions.nor$(this, x, y, ev);
   }

   public int nor$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingFunctions.nor$mIc$sp$(this, x, y, ev);
   }

   public long nor$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingFunctions.nor$mJc$sp$(this, x, y, ev);
   }

   public Object nxor(final Object x, final Object y, final Heyting ev) {
      return HeytingFunctions.nxor$(this, x, y, ev);
   }

   public int nxor$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingFunctions.nxor$mIc$sp$(this, x, y, ev);
   }

   public long nxor$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingFunctions.nxor$mJc$sp$(this, x, y, ev);
   }

   public Object nand(final Object x, final Object y, final Heyting ev) {
      return HeytingFunctions.nand$(this, x, y, ev);
   }

   public int nand$mIc$sp(final int x, final int y, final Heyting ev) {
      return HeytingFunctions.nand$mIc$sp$(this, x, y, ev);
   }

   public long nand$mJc$sp(final long x, final long y, final Heyting ev) {
      return HeytingFunctions.nand$mJc$sp$(this, x, y, ev);
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

   public final Heyting apply(final Heyting ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Heyting$.class);
   }

   public final Heyting apply$mIc$sp(final Heyting ev) {
      return ev;
   }

   public final Heyting apply$mJc$sp(final Heyting ev) {
      return ev;
   }

   private Heyting$() {
   }
}
