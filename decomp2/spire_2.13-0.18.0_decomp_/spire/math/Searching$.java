package spire.math;

import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class Searching$ {
   public static final Searching$ MODULE$ = new Searching$();

   public final int search(final Object as, final Object item, final Order evidence$1) {
      return this.search((Object)as, item, 0, .MODULE$.array_length(as) - 1, evidence$1);
   }

   public final int search(final Object as, final Object item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare(.MODULE$.array_apply(as, middle), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search(final IndexedSeq as, final Object item, final Order evidence$3) {
      return this.search((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search(final IndexedSeq as, final Object item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare(as.apply(middle), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final Seq minimalElements(final Iterable as, final PartialOrder ev) {
      ArrayBuffer candidates = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      as.foreach((a) -> {
         BooleanRef aIsNotMinimal = BooleanRef.create(false);
         this.inspect$1(candidates.length() - 1, ev, a, candidates, aIsNotMinimal);
         return !aIsNotMinimal.elem ? candidates.$plus$eq(a) : BoxedUnit.UNIT;
      });
      return candidates.toSeq();
   }

   public final int search$mZc$sp(final boolean[] as, final boolean item, final Order evidence$1) {
      return this.search$mZc$sp((boolean[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mBc$sp(final byte[] as, final byte item, final Order evidence$1) {
      return this.search$mBc$sp((byte[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mCc$sp(final char[] as, final char item, final Order evidence$1) {
      return this.search$mCc$sp((char[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mDc$sp(final double[] as, final double item, final Order evidence$1) {
      return this.search$mDc$sp((double[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mFc$sp(final float[] as, final float item, final Order evidence$1) {
      return this.search$mFc$sp((float[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mIc$sp(final int[] as, final int item, final Order evidence$1) {
      return this.search$mIc$sp((int[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mJc$sp(final long[] as, final long item, final Order evidence$1) {
      return this.search$mJc$sp((long[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mSc$sp(final short[] as, final short item, final Order evidence$1) {
      return this.search$mSc$sp((short[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mVc$sp(final BoxedUnit[] as, final BoxedUnit item, final Order evidence$1) {
      return this.search$mVc$sp((BoxedUnit[])as, item, 0, as.length - 1, evidence$1);
   }

   public final int search$mZc$sp(final boolean[] as, final boolean item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcZ$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mBc$sp(final byte[] as, final byte item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcB$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mCc$sp(final char[] as, final char item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcC$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mDc$sp(final double[] as, final double item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcD$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mFc$sp(final float[] as, final float item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcF$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mIc$sp(final int[] as, final int item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcI$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mJc$sp(final long[] as, final long item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcJ$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mSc$sp(final short[] as, final short item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcS$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mVc$sp(final BoxedUnit[] as, final BoxedUnit item, final int lower, final int upper, final Order evidence$2) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$2.compare$mcV$sp(as[middle], item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mZc$sp(final IndexedSeq as, final boolean item, final Order evidence$3) {
      return this.search$mZc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mBc$sp(final IndexedSeq as, final byte item, final Order evidence$3) {
      return this.search$mBc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mCc$sp(final IndexedSeq as, final char item, final Order evidence$3) {
      return this.search$mCc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mDc$sp(final IndexedSeq as, final double item, final Order evidence$3) {
      return this.search$mDc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mFc$sp(final IndexedSeq as, final float item, final Order evidence$3) {
      return this.search$mFc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mIc$sp(final IndexedSeq as, final int item, final Order evidence$3) {
      return this.search$mIc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mJc$sp(final IndexedSeq as, final long item, final Order evidence$3) {
      return this.search$mJc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mSc$sp(final IndexedSeq as, final short item, final Order evidence$3) {
      return this.search$mSc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mVc$sp(final IndexedSeq as, final BoxedUnit item, final Order evidence$3) {
      return this.search$mVc$sp((IndexedSeq)as, item, 0, as.length() - 1, evidence$3);
   }

   public final int search$mZc$sp(final IndexedSeq as, final boolean item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcZ$sp(BoxesRunTime.unboxToBoolean(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mBc$sp(final IndexedSeq as, final byte item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcB$sp(BoxesRunTime.unboxToByte(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mCc$sp(final IndexedSeq as, final char item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcC$sp(BoxesRunTime.unboxToChar(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mDc$sp(final IndexedSeq as, final double item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcD$sp(BoxesRunTime.unboxToDouble(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mFc$sp(final IndexedSeq as, final float item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcF$sp(BoxesRunTime.unboxToFloat(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mIc$sp(final IndexedSeq as, final int item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcI$sp(BoxesRunTime.unboxToInt(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mJc$sp(final IndexedSeq as, final long item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcJ$sp(BoxesRunTime.unboxToLong(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mSc$sp(final IndexedSeq as, final short item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcS$sp(BoxesRunTime.unboxToShort(as.apply(middle)), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   public final int search$mVc$sp(final IndexedSeq as, final BoxedUnit item, final int lower, final int upper, final Order evidence$4) {
      int first = lower;
      int last = upper;

      while(first <= last) {
         int middle = first + last >>> 1;
         int compare = evidence$4.compare$mcV$sp((BoxedUnit)as.apply(middle), item);
         if (compare < 0) {
            first = middle + 1;
         } else {
            if (compare <= 0) {
               return middle;
            }

            last = middle - 1;
         }
      }

      return -first - 1;
   }

   private static final void fastRemove$1(final int j, final ArrayBuffer candidates$1) {
      if (j < candidates$1.length() - 1) {
         candidates$1.update(j, candidates$1.apply(candidates$1.length() - 1));
      }

      candidates$1.remove(candidates$1.length() - 1);
   }

   private final void inspect$1(final int i, final PartialOrder ev$1, final Object a$1, final ArrayBuffer candidates$1, final BooleanRef aIsNotMinimal$1) {
      while(i >= 0) {
         double c = ev$1.partialCompare(a$1, candidates$1.apply(i));
         if (c <= (double)0.0F) {
            fastRemove$1(i, candidates$1);
            if (i < candidates$1.length()) {
               i = i;
            } else {
               --i;
            }
         } else if (c > (double)0.0F) {
            aIsNotMinimal$1.elem = true;
            --i;
         } else {
            --i;
         }
      }

      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   private Searching$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
