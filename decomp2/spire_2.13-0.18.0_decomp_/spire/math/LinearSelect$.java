package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

public final class LinearSelect$ implements SelectLike, HighBranchingMedianOf5 {
   public static final LinearSelect$ MODULE$ = new LinearSelect$();

   static {
      SelectLike.$init$(MODULE$);
      HighBranchingMedianOf5.$init$(MODULE$);
   }

   public final void mo5(final Object data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$(this, data, offset, stride, o);
   }

   public final void mo5$mZc$sp(final boolean[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mZc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mBc$sp(final byte[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mBc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mCc$sp(final char[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mCc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mDc$sp(final double[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mDc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mFc$sp(final float[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mFc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mIc$sp(final int[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mIc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mJc$sp(final long[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mJc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mSc$sp(final short[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mSc$sp$(this, data, offset, stride, o);
   }

   public final void mo5$mVc$sp(final BoxedUnit[] data, final int offset, final int stride, final Order o) {
      HighBranchingMedianOf5.mo5$mVc$sp$(this, data, offset, stride, o);
   }

   public final void select(final Object data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mZc$sp(final boolean[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mZc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mBc$sp(final byte[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mBc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mCc$sp(final char[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mCc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mDc$sp(final double[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mDc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mFc$sp(final float[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mFc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mIc$sp(final int[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mIc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mJc$sp(final long[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mJc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mSc$sp(final short[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mSc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void select$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      SelectLike.select$mVc$sp$(this, data, k, evidence$4, evidence$5);
   }

   public final void sort(final Object data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$(this, data, left, right, stride, o);
   }

   public final void sort$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mZc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mBc$sp(final byte[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mBc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mCc$sp(final char[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mCc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mDc$sp(final double[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mDc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mFc$sp(final float[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mFc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mIc$sp(final int[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mIc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mJc$sp(final long[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mJc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mSc$sp(final short[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mSc$sp$(this, data, left, right, stride, o);
   }

   public final void sort$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final Order o) {
      SelectLike.sort$mVc$sp$(this, data, left, right, stride, o);
   }

   public final void select(final Object data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mZc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mBc$sp(final byte[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mBc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mCc$sp(final char[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mCc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mDc$sp(final double[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mDc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mFc$sp(final float[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mFc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mIc$sp(final int[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mIc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mJc$sp(final long[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mJc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mSc$sp(final short[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mSc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final void select$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      SelectLike.select$mVc$sp$(this, data, left, right, stride, k, evidence$6);
   }

   public final int equalSpan(final Object data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$(this, data, offset, stride, o);
   }

   public final int equalSpan$mZc$sp(final boolean[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mZc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mBc$sp(final byte[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mBc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mCc$sp(final char[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mCc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mDc$sp(final double[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mDc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mFc$sp(final float[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mFc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mIc$sp(final int[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mIc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mJc$sp(final long[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mJc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mSc$sp(final short[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mSc$sp$(this, data, offset, stride, o);
   }

   public final int equalSpan$mVc$sp(final BoxedUnit[] data, final int offset, final int stride, final Order o) {
      return SelectLike.equalSpan$mVc$sp$(this, data, offset, stride, o);
   }

   public final int partition(final Object data, final int left, final int right, final int stride, final Object m, final Order o) {
      return SelectLike.partition$(this, data, left, right, stride, m, o);
   }

   public final int partition$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final boolean m, final Order o) {
      return SelectLike.partition$mZc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mBc$sp(final byte[] data, final int left, final int right, final int stride, final byte m, final Order o) {
      return SelectLike.partition$mBc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mCc$sp(final char[] data, final int left, final int right, final int stride, final char m, final Order o) {
      return SelectLike.partition$mCc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mDc$sp(final double[] data, final int left, final int right, final int stride, final double m, final Order o) {
      return SelectLike.partition$mDc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mFc$sp(final float[] data, final int left, final int right, final int stride, final float m, final Order o) {
      return SelectLike.partition$mFc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mIc$sp(final int[] data, final int left, final int right, final int stride, final int m, final Order o) {
      return SelectLike.partition$mIc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mJc$sp(final long[] data, final int left, final int right, final int stride, final long m, final Order o) {
      return SelectLike.partition$mJc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mSc$sp(final short[] data, final int left, final int right, final int stride, final short m, final Order o) {
      return SelectLike.partition$mSc$sp$(this, data, left, right, stride, m, o);
   }

   public final int partition$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final BoxedUnit m, final Order o) {
      return SelectLike.partition$mVc$sp$(this, data, left, right, stride, m, o);
   }

   public final Object approxMedian(final Object data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select(data, left, right, nextStride, k, evidence$7);
      return .MODULE$.array_apply(data, k);
   }

   public final boolean approxMedian$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mZc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mZc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final byte approxMedian$mBc$sp(final byte[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mBc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mBc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final char approxMedian$mCc$sp(final char[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mCc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mCc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final double approxMedian$mDc$sp(final double[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mDc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mDc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final float approxMedian$mFc$sp(final float[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mFc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mFc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final int approxMedian$mIc$sp(final int[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mIc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mIc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final long approxMedian$mJc$sp(final long[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mJc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mJc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final short approxMedian$mSc$sp(final short[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mSc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mSc$sp(data, left, right, nextStride, k, evidence$7);
      return data[k];
   }

   public final void approxMedian$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final Order evidence$7) {
      int offset = left;
      int last = left + 4 * stride;

      int nextStride;
      for(nextStride = 5 * stride; last < right; last += nextStride) {
         this.mo5$mVc$sp(data, offset, stride, evidence$7);
         offset += nextStride;
      }

      int length = (right - left + nextStride - 1) / nextStride;
      int k = left + (length - 1) / 2 * nextStride;
      this.select$mVc$sp(data, left, right, nextStride, k, evidence$7);
      BoxedUnit var10000 = data[k];
   }

   private LinearSelect$() {
   }
}
