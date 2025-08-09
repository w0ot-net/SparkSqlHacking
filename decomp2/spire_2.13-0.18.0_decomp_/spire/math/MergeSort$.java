package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

public final class MergeSort$ implements Sort {
   public static final MergeSort$ MODULE$ = new MergeSort$();

   public final int startWidth() {
      return 8;
   }

   public final int startStep() {
      return 16;
   }

   public final void sort(final Object data, final Order evidence$7, final ClassTag evidence$8) {
      int len = .MODULE$.array_length(data);
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort(data, evidence$7, evidence$8);
      } else {
         Object buf1 = data;
         Object buf2 = evidence$8.newArray(len);
         Object tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void merge(final Object in, final Object out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= .MODULE$.array_length(in) && end <= .MODULE$.array_length(out));
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv(.MODULE$.array_apply(in, ii), .MODULE$.array_apply(in, jj))) {
            .MODULE$.array_update(out, kk, .MODULE$.array_apply(in, jj));
            ++jj;
         } else {
            .MODULE$.array_update(out, kk, .MODULE$.array_apply(in, ii));
            ++ii;
         }
      }

   }

   public final void sort$mZc$sp(final boolean[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mZc$sp(data, evidence$7, evidence$8);
      } else {
         boolean[] buf1 = data;
         boolean[] buf2 = (boolean[])evidence$8.newArray(len);
         boolean[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mZc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mZc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mZc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mZc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mBc$sp(final byte[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mBc$sp(data, evidence$7, evidence$8);
      } else {
         byte[] buf1 = data;
         byte[] buf2 = (byte[])evidence$8.newArray(len);
         byte[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mBc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mBc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mBc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mBc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mCc$sp(final char[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mCc$sp(data, evidence$7, evidence$8);
      } else {
         char[] buf1 = data;
         char[] buf2 = (char[])evidence$8.newArray(len);
         char[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mCc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mCc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mCc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mCc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mDc$sp(final double[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mDc$sp(data, evidence$7, evidence$8);
      } else {
         double[] buf1 = data;
         double[] buf2 = (double[])evidence$8.newArray(len);
         double[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mDc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mDc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mDc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mDc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mFc$sp(final float[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mFc$sp(data, evidence$7, evidence$8);
      } else {
         float[] buf1 = data;
         float[] buf2 = (float[])evidence$8.newArray(len);
         float[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mFc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mFc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mFc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mFc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mIc$sp(final int[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mIc$sp(data, evidence$7, evidence$8);
      } else {
         int[] buf1 = data;
         int[] buf2 = (int[])evidence$8.newArray(len);
         int[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mIc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mIc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mIc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mIc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mJc$sp(final long[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mJc$sp(data, evidence$7, evidence$8);
      } else {
         long[] buf1 = data;
         long[] buf2 = (long[])evidence$8.newArray(len);
         long[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mJc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mJc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mJc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mJc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mSc$sp(final short[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mSc$sp(data, evidence$7, evidence$8);
      } else {
         short[] buf1 = data;
         short[] buf2 = (short[])evidence$8.newArray(len);
         short[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mSc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mSc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mSc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mSc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void sort$mVc$sp(final BoxedUnit[] data, final Order evidence$7, final ClassTag evidence$8) {
      int len = data.length;
      if (len <= this.startStep()) {
         InsertionSort$.MODULE$.sort$mVc$sp(data, evidence$7, evidence$8);
      } else {
         BoxedUnit[] buf1 = data;
         BoxedUnit[] buf2 = (BoxedUnit[])evidence$8.newArray(len);
         BoxedUnit[] tmp = null;
         int i = 0;

         for(int limit = len - this.startWidth(); i < limit; i += this.startWidth()) {
            InsertionSort$.MODULE$.sort$mVc$sp(data, i, i + this.startWidth(), evidence$8, evidence$7);
         }

         if (i < len) {
            InsertionSort$.MODULE$.sort$mVc$sp(data, i, len, evidence$8, evidence$7);
         }

         int width = this.startWidth();

         for(int step = this.startStep(); width < len; step *= 2) {
            i = 0;

            for(int var14 = len - step; i < var14; i += step) {
               this.merge$mVc$sp(buf1, buf2, i, i + width, i + step, evidence$7);
            }

            while(i < len) {
               this.merge$mVc$sp(buf1, buf2, i, package$.MODULE$.min(i + width, len), len, evidence$7);
               i += step;
            }

            tmp = buf2;
            buf2 = buf1;
            buf1 = tmp;
            width *= 2;
         }

         if (buf1 != data) {
            System.arraycopy(buf1, 0, data, 0, len);
         }

      }
   }

   public final void merge$mZc$sp(final boolean[] in, final boolean[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcZ$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mBc$sp(final byte[] in, final byte[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcB$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mCc$sp(final char[] in, final char[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcC$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mDc$sp(final double[] in, final double[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcD$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mFc$sp(final float[] in, final float[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcF$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mIc$sp(final int[] in, final int[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcI$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mJc$sp(final long[] in, final long[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcJ$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mSc$sp(final short[] in, final short[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcS$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   public final void merge$mVc$sp(final BoxedUnit[] in, final BoxedUnit[] out, final int start, final int mid, final int end, final Order o) {
      scala.Predef..MODULE$.require(start >= 0 && start <= mid && mid <= end && end <= in.length && end <= out.length);
      int ii = start;
      int jj = mid;

      for(int kk = start; kk < end; ++kk) {
         if (ii >= mid || jj < end && !o.lteqv$mcV$sp(in[ii], in[jj])) {
            out[kk] = in[jj];
            ++jj;
         } else {
            out[kk] = in[ii];
            ++ii;
         }
      }

   }

   private MergeSort$() {
   }
}
