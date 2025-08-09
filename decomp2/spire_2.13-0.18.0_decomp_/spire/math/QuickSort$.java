package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

public final class QuickSort$ {
   public static final QuickSort$ MODULE$ = new QuickSort$();

   public final int limit() {
      return 16;
   }

   public final void sort(final Object data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort(data, 0, .MODULE$.array_length(data), evidence$9, evidence$10);
   }

   public final void qsort(final Object data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= .MODULE$.array_length(data));
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition(data, start, end, pivotIndex, ct, o);
         this.qsort(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final int partition(final Object data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= .MODULE$.array_length(data));
      Object pivotValue = .MODULE$.array_apply(data, pivotIndex);
      .MODULE$.array_update(data, pivotIndex, .MODULE$.array_apply(data, end - 1));
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt(.MODULE$.array_apply(data, i), pivotValue)) {
            Object temp = .MODULE$.array_apply(data, i);
            .MODULE$.array_update(data, i, .MODULE$.array_apply(data, store));
            .MODULE$.array_update(data, store, temp);
            ++store;
         }
      }

      .MODULE$.array_update(data, end - 1, .MODULE$.array_apply(data, store));
      .MODULE$.array_update(data, store, pivotValue);
      return store;
   }

   public final void sort$mZc$sp(final boolean[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mZc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mBc$sp(final byte[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mBc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mCc$sp(final char[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mCc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mDc$sp(final double[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mDc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mFc$sp(final float[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mFc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mIc$sp(final int[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mIc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mJc$sp(final long[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mJc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mSc$sp(final short[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mSc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void sort$mVc$sp(final BoxedUnit[] data, final Order evidence$9, final ClassTag evidence$10) {
      this.qsort$mVc$sp(data, 0, data.length, evidence$9, evidence$10);
   }

   public final void qsort$mZc$sp(final boolean[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mZc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mZc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mZc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mBc$sp(final byte[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mBc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mBc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mBc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mCc$sp(final char[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mCc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mCc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mCc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mDc$sp(final double[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mDc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mDc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mDc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mFc$sp(final float[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mFc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mFc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mFc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mIc$sp(final int[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mIc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mIc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mIc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mJc$sp(final long[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mJc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mJc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mJc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mSc$sp(final short[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mSc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mSc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mSc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final void qsort$mVc$sp(final BoxedUnit[] data, final int start, final int end, final Order o, final ClassTag ct) {
      while(true) {
         scala.Predef..MODULE$.require(start >= 0 && end <= data.length);
         if (end - start < this.limit()) {
            InsertionSort$.MODULE$.sort$mVc$sp(data, start, end, ct, o);
            return;
         }

         int pivotIndex = start + (end - start) / 2;
         int nextPivotIndex = this.partition$mVc$sp(data, start, end, pivotIndex, ct, o);
         this.qsort$mVc$sp(data, start, nextPivotIndex, o, ct);
         int var10001 = nextPivotIndex + 1;
         ct = ct;
         o = o;
         end = end;
         start = var10001;
         data = data;
      }
   }

   public final int partition$mZc$sp(final boolean[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      boolean pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcZ$sp(data[i], pivotValue)) {
            boolean temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mBc$sp(final byte[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      byte pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcB$sp(data[i], pivotValue)) {
            byte temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mCc$sp(final char[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      char pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcC$sp(data[i], pivotValue)) {
            char temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mDc$sp(final double[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      double pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcD$sp(data[i], pivotValue)) {
            double temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mFc$sp(final float[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      float pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcF$sp(data[i], pivotValue)) {
            float temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mIc$sp(final int[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      int pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcI$sp(data[i], pivotValue)) {
            int temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mJc$sp(final long[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      long pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcJ$sp(data[i], pivotValue)) {
            long temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mSc$sp(final short[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      short pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcS$sp(data[i], pivotValue)) {
            short temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   public final int partition$mVc$sp(final BoxedUnit[] data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      scala.Predef..MODULE$.require(start >= 0 && pivotIndex >= start && end > pivotIndex && end <= data.length);
      BoxedUnit pivotValue = data[pivotIndex];
      data[pivotIndex] = data[end - 1];
      int store = start;

      for(int i = start; i < end - 1; ++i) {
         if (spire.algebra.package$.MODULE$.Order().apply(evidence$12).lt$mcV$sp(data[i], pivotValue)) {
            BoxedUnit temp = data[i];
            data[i] = data[store];
            data[store] = temp;
            ++store;
         }
      }

      data[end - 1] = data[store];
      data[store] = pivotValue;
      return store;
   }

   private QuickSort$() {
   }
}
