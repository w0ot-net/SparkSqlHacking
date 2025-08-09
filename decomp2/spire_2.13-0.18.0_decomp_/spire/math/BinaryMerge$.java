package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class BinaryMerge$ implements Merge {
   public static final BinaryMerge$ MODULE$ = new BinaryMerge$();

   public Object merge(final Object a, final Object b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge.ArrayBinaryMerge(a, b, evidence$3, evidence$4)).result();
   }

   public boolean[] merge$mZc$sp(final boolean[] a, final boolean[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcZ$sp(a, b, evidence$3, evidence$4)).result$mcZ$sp();
   }

   public byte[] merge$mBc$sp(final byte[] a, final byte[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcB$sp(a, b, evidence$3, evidence$4)).result$mcB$sp();
   }

   public char[] merge$mCc$sp(final char[] a, final char[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcC$sp(a, b, evidence$3, evidence$4)).result$mcC$sp();
   }

   public double[] merge$mDc$sp(final double[] a, final double[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcD$sp(a, b, evidence$3, evidence$4)).result$mcD$sp();
   }

   public float[] merge$mFc$sp(final float[] a, final float[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcF$sp(a, b, evidence$3, evidence$4)).result$mcF$sp();
   }

   public int[] merge$mIc$sp(final int[] a, final int[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcI$sp(a, b, evidence$3, evidence$4)).result$mcI$sp();
   }

   public long[] merge$mJc$sp(final long[] a, final long[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcJ$sp(a, b, evidence$3, evidence$4)).result$mcJ$sp();
   }

   public short[] merge$mSc$sp(final short[] a, final short[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcS$sp(a, b, evidence$3, evidence$4)).result$mcS$sp();
   }

   public BoxedUnit[] merge$mVc$sp(final BoxedUnit[] a, final BoxedUnit[] b, final Order evidence$3, final ClassTag evidence$4) {
      return (new BinaryMerge$ArrayBinaryMerge$mcV$sp(a, b, evidence$3, evidence$4)).result$mcV$sp();
   }

   private BinaryMerge$() {
   }
}
