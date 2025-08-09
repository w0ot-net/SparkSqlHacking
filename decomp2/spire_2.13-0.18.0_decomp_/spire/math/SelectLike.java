package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mca\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u00063\u0001!\tA\u0007\u0005\u0006=\u00011\ta\b\u0005\u0006\u001d\u0002!)a\u0014\u0005\u0006K\u0002!)A\u001a\u0005\u0006\u001d\u0002!)\u0002\u001e\u0005\b\u0003+\u0001AQAA\f\u0011\u001d\t\t\u0004\u0001C\u0003\u0003g\u0011!bU3mK\u000e$H*[6f\u0015\tQ1\"\u0001\u0003nCRD'\"\u0001\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0019\u0001aD\u000b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0017/5\t\u0011\"\u0003\u0002\u0019\u0013\t11+\u001a7fGR\fa\u0001J5oSR$C#A\u000e\u0011\u0005Aa\u0012BA\u000f\u0012\u0005\u0011)f.\u001b;\u0002\u0019\u0005\u0004\bO]8y\u001b\u0016$\u0017.\u00198\u0016\u0005\u0001\"C#B\u0011A\u000b*cEC\u0001\u0012/!\t\u0019C\u0005\u0004\u0001\u0005\u0013\u0015\u0012\u0001\u0015!A\u0001\u0006\u00041#!A!\u0012\u0005\u001dz\u0001C\u0001\t)\u0013\tI\u0013CA\u0004O_RD\u0017N\\4)\u0005\u0011Z\u0003C\u0001\t-\u0013\ti\u0013CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007bB\u0018\u0003\u0003\u0003\u0005\u001d\u0001M\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA\u0019>E9\u0011!G\u000f\b\u0003gar!\u0001N\u001c\u000e\u0003UR!AN\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0011BA\u001d\f\u0003\u001d\tGnZ3ce\u0006L!a\u000f\u001f\u0002\u000fA\f7m[1hK*\u0011\u0011hC\u0005\u0003}}\u0012Qa\u0014:eKJT!a\u000f\u001f\t\u000b\u0005\u0013\u0001\u0019\u0001\"\u0002\t\u0011\fG/\u0019\t\u0004!\r\u0013\u0013B\u0001#\u0012\u0005\u0015\t%O]1z\u0011\u00151%\u00011\u0001H\u0003\u0011aWM\u001a;\u0011\u0005AA\u0015BA%\u0012\u0005\rIe\u000e\u001e\u0005\u0006\u0017\n\u0001\raR\u0001\u0006e&<\u0007\u000e\u001e\u0005\u0006\u001b\n\u0001\raR\u0001\u0007gR\u0014\u0018\u000eZ3\u0002\rM,G.Z2u+\t\u0001f\u000bF\u0002RC\u000e$2a\u0007*Y\u0011\u001d\u00196!!AA\u0004Q\u000b!\"\u001a<jI\u0016t7-\u001a\u00135!\r\tT(\u0016\t\u0003GY#\u0011\"J\u0002!\u0002\u0003\u0005)\u0019\u0001\u0014)\u0005Y[\u0003bB-\u0004\u0003\u0003\u0005\u001dAW\u0001\u000bKZLG-\u001a8dK\u0012*\u0004cA._+:\u0011A,X\u0007\u0002\u0017%\u00111hC\u0005\u0003?\u0002\u0014\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003w-AQ!Q\u0002A\u0002\t\u00042\u0001E\"V\u0011\u0015!7\u00011\u0001H\u0003\u0005Y\u0017\u0001B:peR,\"aZ7\u0015\u000b!|\u0017O]:\u0015\u0005mI\u0007\"\u00026\u0005\u0001\bY\u0017!A8\u0011\u0007EjD\u000e\u0005\u0002$[\u0012IQ\u0005\u0002Q\u0001\u0002\u0003\u0015\rA\n\u0015\u0003[.BQ!\u0011\u0003A\u0002A\u00042\u0001E\"m\u0011\u00151E\u00011\u0001H\u0011\u0015YE\u00011\u0001H\u0011\u0015iE\u00011\u0001H+\t)8\u0010F\u0005w{~\f\t!a\u0001\u0002\u0006Q\u00111d\u001e\u0005\bq\u0016\t\t\u0011q\u0001z\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0004cuR\bCA\u0012|\t%)S\u0001)A\u0001\u0002\u000b\u0007a\u0005\u000b\u0002|W!)\u0011)\u0002a\u0001}B\u0019\u0001c\u0011>\t\u000b\u0019+\u0001\u0019A$\t\u000b-+\u0001\u0019A$\t\u000b5+\u0001\u0019A$\t\u000b\u0011,\u0001\u0019A$)\u0007\u0015\tI\u0001\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\ty!E\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\n\u0003\u001b\u0011q\u0001^1jYJ,7-A\u0005fcV\fGn\u00159b]V!\u0011\u0011DA\u0012)!\tY\"a\n\u0002,\u0005=BcA$\u0002\u001e!1!N\u0002a\u0002\u0003?\u0001B!M\u001f\u0002\"A\u00191%a\t\u0005\u0013\u00152\u0001\u0015!A\u0001\u0006\u00041\u0003fAA\u0012W!1\u0011I\u0002a\u0001\u0003S\u0001B\u0001E\"\u0002\"!1\u0011Q\u0006\u0004A\u0002\u001d\u000baa\u001c4gg\u0016$\b\"B'\u0007\u0001\u00049\u0015!\u00039beRLG/[8o+\u0011\t)$!\u0011\u0015\u0015\u0005]\u0012\u0011JA'\u0003\u001f\n\t\u0006\u0006\u0003\u0002:\u0005\u0015CcA$\u0002<!1!n\u0002a\u0002\u0003{\u0001B!M\u001f\u0002@A\u00191%!\u0011\u0005\u0013\u0015:\u0001\u0015!A\u0001\u0006\u00041\u0003fAA!W!9\u0011qI\u0004A\u0002\u0005}\u0012!A7\t\r\u0005;\u0001\u0019AA&!\u0011\u00012)a\u0010\t\u000b\u0019;\u0001\u0019A$\t\u000b-;\u0001\u0019A$\t\u000b5;\u0001\u0019A$"
)
public interface SelectLike extends Select {
   Object approxMedian(final Object data, final int left, final int right, final int stride, final Order evidence$3);

   // $FF: synthetic method
   static void select$(final SelectLike $this, final Object data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select(data, k, evidence$4, evidence$5);
   }

   default void select(final Object data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select(data, 0, .MODULE$.array_length(data), 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void sort$(final SelectLike $this, final Object data, final int left, final int right, final int stride, final Order o) {
      $this.sort(data, left, right, stride, o);
   }

   default void sort(final Object data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         Object item = .MODULE$.array_apply(data, i);

         int hole;
         for(hole = i; hole > left && o.gt(.MODULE$.array_apply(data, hole - stride), item); hole -= stride) {
            .MODULE$.array_update(data, hole, .MODULE$.array_apply(data, hole - stride));
         }

         .MODULE$.array_update(data, hole, item);
      }

   }

   // $FF: synthetic method
   static void select$(final SelectLike $this, final Object data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select(data, left, right, stride, k, evidence$6);
   }

   default void select(final Object data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition(data, left, right, stride, this.approxMedian(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static int equalSpan$(final SelectLike $this, final Object data, final int offset, final int stride, final Order o) {
      return $this.equalSpan(data, offset, stride, o);
   }

   default int equalSpan(final Object data, final int offset, final int stride, final Order o) {
      Object m = .MODULE$.array_apply(data, offset);
      int i = offset + stride;

      int len;
      for(len = 1; i < .MODULE$.array_length(data) && o.eqv(m, .MODULE$.array_apply(data, i)); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int partition$(final SelectLike $this, final Object data, final int left, final int right, final int stride, final Object m, final Order o) {
      return $this.partition(data, left, right, stride, m, o);
   }

   default int partition(final Object data, final int left, final int right, final int stride, final Object m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare(.MODULE$.array_apply(data, i), m);
         if (cmp < 0) {
            Object t = .MODULE$.array_apply(data, i);
            .MODULE$.array_update(data, i, .MODULE$.array_apply(data, j));
            .MODULE$.array_update(data, j, t);
            j += stride;
         } else if (cmp == 0) {
            Object var12 = .MODULE$.array_apply(data, i);
            .MODULE$.array_update(data, i, .MODULE$.array_apply(data, j));
            .MODULE$.array_update(data, j, .MODULE$.array_apply(data, k));
            .MODULE$.array_update(data, k, var12);
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         Object var13 = .MODULE$.array_apply(data, j);
         .MODULE$.array_update(data, j, .MODULE$.array_apply(data, k));
         .MODULE$.array_update(data, k, var13);
      }

      return j;
   }

   // $FF: synthetic method
   static boolean approxMedian$mZc$sp$(final SelectLike $this, final boolean[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mZc$sp(data, left, right, stride, evidence$3);
   }

   default boolean approxMedian$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static byte approxMedian$mBc$sp$(final SelectLike $this, final byte[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mBc$sp(data, left, right, stride, evidence$3);
   }

   default byte approxMedian$mBc$sp(final byte[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static char approxMedian$mCc$sp$(final SelectLike $this, final char[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mCc$sp(data, left, right, stride, evidence$3);
   }

   default char approxMedian$mCc$sp(final char[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static double approxMedian$mDc$sp$(final SelectLike $this, final double[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mDc$sp(data, left, right, stride, evidence$3);
   }

   default double approxMedian$mDc$sp(final double[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static float approxMedian$mFc$sp$(final SelectLike $this, final float[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mFc$sp(data, left, right, stride, evidence$3);
   }

   default float approxMedian$mFc$sp(final float[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static int approxMedian$mIc$sp$(final SelectLike $this, final int[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mIc$sp(data, left, right, stride, evidence$3);
   }

   default int approxMedian$mIc$sp(final int[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static long approxMedian$mJc$sp$(final SelectLike $this, final long[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mJc$sp(data, left, right, stride, evidence$3);
   }

   default long approxMedian$mJc$sp(final long[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static short approxMedian$mSc$sp$(final SelectLike $this, final short[] data, final int left, final int right, final int stride, final Order evidence$3) {
      return $this.approxMedian$mSc$sp(data, left, right, stride, evidence$3);
   }

   default short approxMedian$mSc$sp(final short[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void approxMedian$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int left, final int right, final int stride, final Order evidence$3) {
      $this.approxMedian$mVc$sp(data, left, right, stride, evidence$3);
   }

   default void approxMedian$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final Order evidence$3) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mZc$sp$(final SelectLike $this, final boolean[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mZc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mZc$sp(final boolean[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mZc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mBc$sp$(final SelectLike $this, final byte[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mBc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mBc$sp(final byte[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mBc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mCc$sp$(final SelectLike $this, final char[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mCc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mCc$sp(final char[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mCc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mDc$sp$(final SelectLike $this, final double[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mDc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mDc$sp(final double[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mDc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mFc$sp$(final SelectLike $this, final float[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mFc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mFc$sp(final float[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mFc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mIc$sp$(final SelectLike $this, final int[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mIc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mIc$sp(final int[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mIc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mJc$sp$(final SelectLike $this, final long[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mJc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mJc$sp(final long[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mJc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mSc$sp$(final SelectLike $this, final short[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mSc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mSc$sp(final short[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mSc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void select$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      $this.select$mVc$sp(data, k, evidence$4, evidence$5);
   }

   default void select$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      this.select$mVc$sp(data, 0, data.length, 1, k, evidence$4);
   }

   // $FF: synthetic method
   static void sort$mZc$sp$(final SelectLike $this, final boolean[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mZc$sp(data, left, right, stride, o);
   }

   default void sort$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         boolean item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcZ$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mBc$sp$(final SelectLike $this, final byte[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mBc$sp(data, left, right, stride, o);
   }

   default void sort$mBc$sp(final byte[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         byte item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcB$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mCc$sp$(final SelectLike $this, final char[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mCc$sp(data, left, right, stride, o);
   }

   default void sort$mCc$sp(final char[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         char item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcC$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mDc$sp$(final SelectLike $this, final double[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mDc$sp(data, left, right, stride, o);
   }

   default void sort$mDc$sp(final double[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         double item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcD$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mFc$sp$(final SelectLike $this, final float[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mFc$sp(data, left, right, stride, o);
   }

   default void sort$mFc$sp(final float[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         float item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcF$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mIc$sp$(final SelectLike $this, final int[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mIc$sp(data, left, right, stride, o);
   }

   default void sort$mIc$sp(final int[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         int item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcI$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mJc$sp$(final SelectLike $this, final long[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mJc$sp(data, left, right, stride, o);
   }

   default void sort$mJc$sp(final long[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         long item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcJ$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mSc$sp$(final SelectLike $this, final short[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mSc$sp(data, left, right, stride, o);
   }

   default void sort$mSc$sp(final short[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         short item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcS$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void sort$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int left, final int right, final int stride, final Order o) {
      $this.sort$mVc$sp(data, left, right, stride, o);
   }

   default void sort$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final Order o) {
      for(int i = left; i < right; i += stride) {
         BoxedUnit item = data[i];

         int hole;
         for(hole = i; hole > left && o.gt$mcV$sp(data[hole - stride], item); hole -= stride) {
            data[hole] = data[hole - stride];
         }

         data[hole] = item;
      }

   }

   // $FF: synthetic method
   static void select$mZc$sp$(final SelectLike $this, final boolean[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mZc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mZc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mZc$sp(data, left, right, stride, this.approxMedian$mZc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mZc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mBc$sp$(final SelectLike $this, final byte[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mBc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mBc$sp(final byte[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mBc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mBc$sp(data, left, right, stride, this.approxMedian$mBc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mBc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mCc$sp$(final SelectLike $this, final char[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mCc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mCc$sp(final char[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mCc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mCc$sp(data, left, right, stride, this.approxMedian$mCc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mCc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mDc$sp$(final SelectLike $this, final double[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mDc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mDc$sp(final double[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mDc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mDc$sp(data, left, right, stride, this.approxMedian$mDc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mDc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mFc$sp$(final SelectLike $this, final float[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mFc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mFc$sp(final float[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mFc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mFc$sp(data, left, right, stride, this.approxMedian$mFc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mFc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mIc$sp$(final SelectLike $this, final int[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mIc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mIc$sp(final int[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mIc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mIc$sp(data, left, right, stride, this.approxMedian$mIc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mIc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mJc$sp$(final SelectLike $this, final long[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mJc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mJc$sp(final long[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mJc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mJc$sp(data, left, right, stride, this.approxMedian$mJc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mJc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mSc$sp$(final SelectLike $this, final short[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mSc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mSc$sp(final short[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mSc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         int c = this.partition$mSc$sp(data, left, right, stride, this.approxMedian$mSc$sp(data, left, right, stride, evidence$6), evidence$6);
         int span = this.equalSpan$mSc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static void select$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      $this.select$mVc$sp(data, left, right, stride, k, evidence$6);
   }

   default void select$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final int k, final Order evidence$6) {
      while(true) {
         int length = (right - left + stride - 1) / stride;
         if (length < 10) {
            this.sort$mVc$sp(data, left, right, stride, evidence$6);
            BoxedUnit var12 = BoxedUnit.UNIT;
            break;
         }

         this.approxMedian$mVc$sp(data, left, right, stride, evidence$6);
         int c = this.partition$mVc$sp(data, left, right, stride, BoxedUnit.UNIT, evidence$6);
         int span = this.equalSpan$mVc$sp(data, c, stride, evidence$6);
         if (c <= k && k < c + span) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         }

         if (k < c) {
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = c;
            left = left;
            data = data;
         } else {
            int newLeft = c + span * stride;
            evidence$6 = evidence$6;
            k = k;
            stride = stride;
            right = right;
            left = newLeft;
            data = data;
         }
      }

   }

   // $FF: synthetic method
   static int equalSpan$mZc$sp$(final SelectLike $this, final boolean[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mZc$sp(data, offset, stride, o);
   }

   default int equalSpan$mZc$sp(final boolean[] data, final int offset, final int stride, final Order o) {
      boolean m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcZ$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mBc$sp$(final SelectLike $this, final byte[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mBc$sp(data, offset, stride, o);
   }

   default int equalSpan$mBc$sp(final byte[] data, final int offset, final int stride, final Order o) {
      byte m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcB$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mCc$sp$(final SelectLike $this, final char[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mCc$sp(data, offset, stride, o);
   }

   default int equalSpan$mCc$sp(final char[] data, final int offset, final int stride, final Order o) {
      char m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcC$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mDc$sp$(final SelectLike $this, final double[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mDc$sp(data, offset, stride, o);
   }

   default int equalSpan$mDc$sp(final double[] data, final int offset, final int stride, final Order o) {
      double m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcD$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mFc$sp$(final SelectLike $this, final float[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mFc$sp(data, offset, stride, o);
   }

   default int equalSpan$mFc$sp(final float[] data, final int offset, final int stride, final Order o) {
      float m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcF$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mIc$sp$(final SelectLike $this, final int[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mIc$sp(data, offset, stride, o);
   }

   default int equalSpan$mIc$sp(final int[] data, final int offset, final int stride, final Order o) {
      int m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcI$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mJc$sp$(final SelectLike $this, final long[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mJc$sp(data, offset, stride, o);
   }

   default int equalSpan$mJc$sp(final long[] data, final int offset, final int stride, final Order o) {
      long m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcJ$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mSc$sp$(final SelectLike $this, final short[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mSc$sp(data, offset, stride, o);
   }

   default int equalSpan$mSc$sp(final short[] data, final int offset, final int stride, final Order o) {
      short m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcS$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int equalSpan$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int offset, final int stride, final Order o) {
      return $this.equalSpan$mVc$sp(data, offset, stride, o);
   }

   default int equalSpan$mVc$sp(final BoxedUnit[] data, final int offset, final int stride, final Order o) {
      BoxedUnit m = data[offset];
      int i = offset + stride;

      int len;
      for(len = 1; i < data.length && o.eqv$mcV$sp(m, data[i]); ++len) {
         i += stride;
      }

      return len;
   }

   // $FF: synthetic method
   static int partition$mZc$sp$(final SelectLike $this, final boolean[] data, final int left, final int right, final int stride, final boolean m, final Order o) {
      return $this.partition$mZc$sp(data, left, right, stride, m, o);
   }

   default int partition$mZc$sp(final boolean[] data, final int left, final int right, final int stride, final boolean m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcZ$sp(data[i], m);
         if (cmp < 0) {
            boolean t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            boolean var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         boolean var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mBc$sp$(final SelectLike $this, final byte[] data, final int left, final int right, final int stride, final byte m, final Order o) {
      return $this.partition$mBc$sp(data, left, right, stride, m, o);
   }

   default int partition$mBc$sp(final byte[] data, final int left, final int right, final int stride, final byte m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcB$sp(data[i], m);
         if (cmp < 0) {
            byte t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            byte var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         byte var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mCc$sp$(final SelectLike $this, final char[] data, final int left, final int right, final int stride, final char m, final Order o) {
      return $this.partition$mCc$sp(data, left, right, stride, m, o);
   }

   default int partition$mCc$sp(final char[] data, final int left, final int right, final int stride, final char m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcC$sp(data[i], m);
         if (cmp < 0) {
            char t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            char var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         char var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mDc$sp$(final SelectLike $this, final double[] data, final int left, final int right, final int stride, final double m, final Order o) {
      return $this.partition$mDc$sp(data, left, right, stride, m, o);
   }

   default int partition$mDc$sp(final double[] data, final int left, final int right, final int stride, final double m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcD$sp(data[i], m);
         if (cmp < 0) {
            double t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            double var14 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var14;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         double var15 = data[j];
         data[j] = data[k];
         data[k] = var15;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mFc$sp$(final SelectLike $this, final float[] data, final int left, final int right, final int stride, final float m, final Order o) {
      return $this.partition$mFc$sp(data, left, right, stride, m, o);
   }

   default int partition$mFc$sp(final float[] data, final int left, final int right, final int stride, final float m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcF$sp(data[i], m);
         if (cmp < 0) {
            float t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            float var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         float var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mIc$sp$(final SelectLike $this, final int[] data, final int left, final int right, final int stride, final int m, final Order o) {
      return $this.partition$mIc$sp(data, left, right, stride, m, o);
   }

   default int partition$mIc$sp(final int[] data, final int left, final int right, final int stride, final int m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcI$sp(data[i], m);
         if (cmp < 0) {
            int t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            int var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         int var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mJc$sp$(final SelectLike $this, final long[] data, final int left, final int right, final int stride, final long m, final Order o) {
      return $this.partition$mJc$sp(data, left, right, stride, m, o);
   }

   default int partition$mJc$sp(final long[] data, final int left, final int right, final int stride, final long m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcJ$sp(data[i], m);
         if (cmp < 0) {
            long t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            long var14 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var14;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         long var15 = data[j];
         data[j] = data[k];
         data[k] = var15;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mSc$sp$(final SelectLike $this, final short[] data, final int left, final int right, final int stride, final short m, final Order o) {
      return $this.partition$mSc$sp(data, left, right, stride, m, o);
   }

   default int partition$mSc$sp(final short[] data, final int left, final int right, final int stride, final short m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcS$sp(data[i], m);
         if (cmp < 0) {
            short t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            short var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         short var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   // $FF: synthetic method
   static int partition$mVc$sp$(final SelectLike $this, final BoxedUnit[] data, final int left, final int right, final int stride, final BoxedUnit m, final Order o) {
      return $this.partition$mVc$sp(data, left, right, stride, m, o);
   }

   default int partition$mVc$sp(final BoxedUnit[] data, final int left, final int right, final int stride, final BoxedUnit m, final Order o) {
      int i = left;
      int j = left;

      int k;
      for(k = left; i < right; i += stride) {
         int cmp = o.compare$mcV$sp(data[i], m);
         if (cmp < 0) {
            BoxedUnit t = data[i];
            data[i] = data[j];
            data[j] = t;
            j += stride;
         } else if (cmp == 0) {
            BoxedUnit var12 = data[i];
            data[i] = data[j];
            data[j] = data[k];
            data[k] = var12;
            k += stride;
            j += stride;
         }
      }

      while(k > left) {
         j -= stride;
         k -= stride;
         BoxedUnit var13 = data[j];
         data[j] = data[k];
         data[k] = var13;
      }

      return j;
   }

   static void $init$(final SelectLike $this) {
   }
}
