package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

public final class InsertionSort$ implements Sort {
   public static final InsertionSort$ MODULE$ = new InsertionSort$();

   public final void sort(final Object data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort(data, 0, .MODULE$.array_length(data), evidence$4, evidence$3);
   }

   public final void sort(final Object data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= .MODULE$.array_length(data));

      for(int i = start + 1; i < end; ++i) {
         Object item = .MODULE$.array_apply(data, i);

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt(.MODULE$.array_apply(data, hole - 1), item); --hole) {
            .MODULE$.array_update(data, hole, .MODULE$.array_apply(data, hole - 1));
         }

         .MODULE$.array_update(data, hole, item);
      }

   }

   public final void sort$mZc$sp(final boolean[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mZc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mBc$sp(final byte[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mBc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mCc$sp(final char[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mCc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mDc$sp(final double[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mDc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mFc$sp(final float[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mFc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mIc$sp(final int[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mIc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mJc$sp(final long[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mJc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mSc$sp(final short[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mSc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mVc$sp(final BoxedUnit[] data, final Order evidence$3, final ClassTag evidence$4) {
      this.sort$mVc$sp(data, 0, data.length, evidence$4, evidence$3);
   }

   public final void sort$mZc$sp(final boolean[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         boolean item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcZ$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mBc$sp(final byte[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         byte item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcB$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mCc$sp(final char[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         char item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcC$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mDc$sp(final double[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         double item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcD$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mFc$sp(final float[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         float item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcF$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mIc$sp(final int[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         int item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcI$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mJc$sp(final long[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         long item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcJ$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mSc$sp(final short[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         short item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcS$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   public final void sort$mVc$sp(final BoxedUnit[] data, final int start, final int end, final ClassTag evidence$5, final Order evidence$6) {
      scala.Predef..MODULE$.require(start <= end && start >= 0 && end <= data.length);

      for(int i = start + 1; i < end; ++i) {
         BoxedUnit item = data[i];

         int hole;
         for(hole = i; hole > start && spire.algebra.package$.MODULE$.Order().apply(evidence$6).gt$mcV$sp(data[hole - 1], item); --hole) {
            data[hole] = data[hole - 1];
         }

         data[hole] = item;
      }

   }

   private InsertionSort$() {
   }
}
