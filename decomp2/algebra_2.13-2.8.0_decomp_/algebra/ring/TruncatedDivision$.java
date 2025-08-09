package algebra.ring;

import cats.kernel.OrderFunctions;
import scala.Tuple2;

public final class TruncatedDivision$ extends OrderFunctions implements TruncatedDivisionFunctions {
   public static final TruncatedDivision$ MODULE$ = new TruncatedDivision$();

   static {
      SignedFunctions.$init$(MODULE$);
      TruncatedDivisionFunctions.$init$(MODULE$);
   }

   public Object tquot(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquot$(this, x, y, ev);
   }

   public double tquot$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquot$mDc$sp$(this, x, y, ev);
   }

   public float tquot$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquot$mFc$sp$(this, x, y, ev);
   }

   public int tquot$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquot$mIc$sp$(this, x, y, ev);
   }

   public long tquot$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquot$mJc$sp$(this, x, y, ev);
   }

   public Object tmod(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tmod$(this, x, y, ev);
   }

   public double tmod$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tmod$mDc$sp$(this, x, y, ev);
   }

   public float tmod$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tmod$mFc$sp$(this, x, y, ev);
   }

   public int tmod$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tmod$mIc$sp$(this, x, y, ev);
   }

   public long tmod$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tmod$mJc$sp$(this, x, y, ev);
   }

   public Tuple2 tquotmod(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquotmod$(this, x, y, ev);
   }

   public Tuple2 tquotmod$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquotmod$mDc$sp$(this, x, y, ev);
   }

   public Tuple2 tquotmod$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquotmod$mFc$sp$(this, x, y, ev);
   }

   public Tuple2 tquotmod$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquotmod$mIc$sp$(this, x, y, ev);
   }

   public Tuple2 tquotmod$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.tquotmod$mJc$sp$(this, x, y, ev);
   }

   public Object fquot(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquot$(this, x, y, ev);
   }

   public double fquot$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquot$mDc$sp$(this, x, y, ev);
   }

   public float fquot$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquot$mFc$sp$(this, x, y, ev);
   }

   public int fquot$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquot$mIc$sp$(this, x, y, ev);
   }

   public long fquot$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquot$mJc$sp$(this, x, y, ev);
   }

   public Object fmod(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fmod$(this, x, y, ev);
   }

   public double fmod$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fmod$mDc$sp$(this, x, y, ev);
   }

   public float fmod$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fmod$mFc$sp$(this, x, y, ev);
   }

   public int fmod$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fmod$mIc$sp$(this, x, y, ev);
   }

   public long fmod$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fmod$mJc$sp$(this, x, y, ev);
   }

   public Tuple2 fquotmod(final Object x, final Object y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquotmod$(this, x, y, ev);
   }

   public Tuple2 fquotmod$mDc$sp(final double x, final double y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquotmod$mDc$sp$(this, x, y, ev);
   }

   public Tuple2 fquotmod$mFc$sp(final float x, final float y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquotmod$mFc$sp$(this, x, y, ev);
   }

   public Tuple2 fquotmod$mIc$sp(final int x, final int y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquotmod$mIc$sp$(this, x, y, ev);
   }

   public Tuple2 fquotmod$mJc$sp(final long x, final long y, final TruncatedDivision ev) {
      return TruncatedDivisionFunctions.fquotmod$mJc$sp$(this, x, y, ev);
   }

   public Signed.Sign sign(final Object a, final Signed ev) {
      return SignedFunctions.sign$(this, a, ev);
   }

   public Signed.Sign sign$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.sign$mDc$sp$(this, a, ev);
   }

   public Signed.Sign sign$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.sign$mFc$sp$(this, a, ev);
   }

   public Signed.Sign sign$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.sign$mIc$sp$(this, a, ev);
   }

   public Signed.Sign sign$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.sign$mJc$sp$(this, a, ev);
   }

   public int signum(final Object a, final Signed ev) {
      return SignedFunctions.signum$(this, a, ev);
   }

   public int signum$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.signum$mDc$sp$(this, a, ev);
   }

   public int signum$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.signum$mFc$sp$(this, a, ev);
   }

   public int signum$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.signum$mIc$sp$(this, a, ev);
   }

   public int signum$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.signum$mJc$sp$(this, a, ev);
   }

   public Object abs(final Object a, final Signed ev) {
      return SignedFunctions.abs$(this, a, ev);
   }

   public double abs$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.abs$mDc$sp$(this, a, ev);
   }

   public float abs$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.abs$mFc$sp$(this, a, ev);
   }

   public int abs$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.abs$mIc$sp$(this, a, ev);
   }

   public long abs$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.abs$mJc$sp$(this, a, ev);
   }

   public boolean isSignZero(final Object a, final Signed ev) {
      return SignedFunctions.isSignZero$(this, a, ev);
   }

   public boolean isSignZero$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignZero$mDc$sp$(this, a, ev);
   }

   public boolean isSignZero$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignZero$mFc$sp$(this, a, ev);
   }

   public boolean isSignZero$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignZero$mIc$sp$(this, a, ev);
   }

   public boolean isSignZero$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignZero$mJc$sp$(this, a, ev);
   }

   public boolean isSignPositive(final Object a, final Signed ev) {
      return SignedFunctions.isSignPositive$(this, a, ev);
   }

   public boolean isSignPositive$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignPositive$mDc$sp$(this, a, ev);
   }

   public boolean isSignPositive$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignPositive$mFc$sp$(this, a, ev);
   }

   public boolean isSignPositive$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignPositive$mIc$sp$(this, a, ev);
   }

   public boolean isSignPositive$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignPositive$mJc$sp$(this, a, ev);
   }

   public boolean isSignNegative(final Object a, final Signed ev) {
      return SignedFunctions.isSignNegative$(this, a, ev);
   }

   public boolean isSignNegative$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignNegative$mDc$sp$(this, a, ev);
   }

   public boolean isSignNegative$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignNegative$mFc$sp$(this, a, ev);
   }

   public boolean isSignNegative$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignNegative$mIc$sp$(this, a, ev);
   }

   public boolean isSignNegative$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignNegative$mJc$sp$(this, a, ev);
   }

   public boolean isSignNonZero(final Object a, final Signed ev) {
      return SignedFunctions.isSignNonZero$(this, a, ev);
   }

   public boolean isSignNonZero$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignNonZero$mDc$sp$(this, a, ev);
   }

   public boolean isSignNonZero$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignNonZero$mFc$sp$(this, a, ev);
   }

   public boolean isSignNonZero$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignNonZero$mIc$sp$(this, a, ev);
   }

   public boolean isSignNonZero$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignNonZero$mJc$sp$(this, a, ev);
   }

   public boolean isSignNonPositive(final Object a, final Signed ev) {
      return SignedFunctions.isSignNonPositive$(this, a, ev);
   }

   public boolean isSignNonPositive$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignNonPositive$mDc$sp$(this, a, ev);
   }

   public boolean isSignNonPositive$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignNonPositive$mFc$sp$(this, a, ev);
   }

   public boolean isSignNonPositive$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignNonPositive$mIc$sp$(this, a, ev);
   }

   public boolean isSignNonPositive$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignNonPositive$mJc$sp$(this, a, ev);
   }

   public boolean isSignNonNegative(final Object a, final Signed ev) {
      return SignedFunctions.isSignNonNegative$(this, a, ev);
   }

   public boolean isSignNonNegative$mDc$sp(final double a, final Signed ev) {
      return SignedFunctions.isSignNonNegative$mDc$sp$(this, a, ev);
   }

   public boolean isSignNonNegative$mFc$sp(final float a, final Signed ev) {
      return SignedFunctions.isSignNonNegative$mFc$sp$(this, a, ev);
   }

   public boolean isSignNonNegative$mIc$sp(final int a, final Signed ev) {
      return SignedFunctions.isSignNonNegative$mIc$sp$(this, a, ev);
   }

   public boolean isSignNonNegative$mJc$sp(final long a, final Signed ev) {
      return SignedFunctions.isSignNonNegative$mJc$sp$(this, a, ev);
   }

   public TruncatedDivision apply(final TruncatedDivision ev) {
      return ev;
   }

   private TruncatedDivision$() {
   }
}
