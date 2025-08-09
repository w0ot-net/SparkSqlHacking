package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class Selection$ {
   public static final Selection$ MODULE$ = new Selection$();

   public final void select(final Object data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect(data, k, evidence$9, evidence$10);
   }

   public final void linearSelect(final Object data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select(data, k, evidence$11, evidence$12);
   }

   public final void quickSelect(final Object data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select(data, k, evidence$13, evidence$14);
   }

   public final void select$mZc$sp(final boolean[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mZc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mBc$sp(final byte[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mBc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mCc$sp(final char[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mCc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mDc$sp(final double[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mDc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mFc$sp(final float[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mFc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mIc$sp(final int[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mIc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mJc$sp(final long[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mJc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mSc$sp(final short[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mSc$sp(data, k, evidence$9, evidence$10);
   }

   public final void select$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      this.quickSelect$mVc$sp(data, k, evidence$9, evidence$10);
   }

   public final void linearSelect$mZc$sp(final boolean[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mZc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mBc$sp(final byte[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mBc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mCc$sp(final char[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mCc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mDc$sp(final double[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mDc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mFc$sp(final float[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mFc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mIc$sp(final int[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mIc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mJc$sp(final long[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mJc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mSc$sp(final short[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mSc$sp(data, k, evidence$11, evidence$12);
   }

   public final void linearSelect$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      LinearSelect$.MODULE$.select$mVc$sp(data, k, evidence$11, evidence$12);
   }

   public final void quickSelect$mZc$sp(final boolean[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mZc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mBc$sp(final byte[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mBc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mCc$sp(final char[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mCc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mDc$sp(final double[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mDc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mFc$sp(final float[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mFc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mIc$sp(final int[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mIc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mJc$sp(final long[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mJc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mSc$sp(final short[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mSc$sp(data, k, evidence$13, evidence$14);
   }

   public final void quickSelect$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      QuickSelect$.MODULE$.select$mVc$sp(data, k, evidence$13, evidence$14);
   }

   private Selection$() {
   }
}
