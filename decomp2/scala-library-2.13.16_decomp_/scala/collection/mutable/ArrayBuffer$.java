package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedSeqFactory;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class ArrayBuffer$ implements StrictOptimizedSeqFactory {
   public static final ArrayBuffer$ MODULE$ = new ArrayBuffer$();
   private static final long serialVersionUID = 3L;
   private static final Object[] emptyArray;

   static {
      ArrayBuffer$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      emptyArray = new Object[0];
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem) {
      return StrictOptimizedSeqFactory.fill$(this, n, elem);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      return StrictOptimizedSeqFactory.tabulate$(this, n, f);
   }

   public scala.collection.SeqOps concat(final scala.collection.immutable.Seq xss) {
      return StrictOptimizedSeqFactory.concat$(this, xss);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return SeqFactory.unapplySeq$(this, x);
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return IterableFactory.apply$(this, elems);
   }

   public Object iterate(final Object start, final int len, final Function1 f) {
      return IterableFactory.iterate$(this, start, len, f);
   }

   public Object unfold(final Object init, final Function1 f) {
      return IterableFactory.unfold$(this, init, f);
   }

   public Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(this, start, end, evidence$3);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(this, start, end, step, evidence$4);
   }

   public Object fill(final int n1, final int n2, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, n5, elem);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f) {
      return IterableFactory.tabulate$(this, n1, n2, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public final int DefaultInitialSize() {
      return 16;
   }

   public ArrayBuffer from(final IterableOnce coll) {
      int k = coll.knownSize();
      if (k >= 0) {
         Object[] array = this.scala$collection$mutable$ArrayBuffer$$ensureSize(emptyArray, 0, k);
         IterableOnce$ var10000 = IterableOnce$.MODULE$;
         var10000 = IterableOnce$.MODULE$;
         IterableOnce$ var10001 = IterableOnce$.MODULE$;
         int copyElemsToArray_len = Integer.MAX_VALUE;
         int copyElemsToArray_start = 0;
         int actual = coll instanceof scala.collection.Iterable ? ((scala.collection.Iterable)coll).copyToArray(array, copyElemsToArray_start, copyElemsToArray_len) : coll.iterator().copyToArray(array, copyElemsToArray_start, copyElemsToArray_len);
         if (actual != k) {
            throw new IllegalStateException((new java.lang.StringBuilder(11)).append("Copied ").append(actual).append(" of ").append(k).toString());
         } else {
            return new ArrayBuffer(array, k);
         }
      } else {
         return (ArrayBuffer)(new ArrayBuffer()).addAll(coll);
      }
   }

   public Builder newBuilder() {
      return new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
   }

   public ArrayBuffer empty() {
      return new ArrayBuffer();
   }

   public int resizeUp(final int arrayLen, final int targetLen) {
      if (targetLen < 0) {
         throw new RuntimeException((new java.lang.StringBuilder(106)).append("Overflow while resizing array of array-backed collection. Requested length: ").append(targetLen).append("; current length: ").append(arrayLen).append("; increase: ").append(targetLen - arrayLen).toString());
      } else if (targetLen <= arrayLen) {
         return -1;
      } else if (targetLen > 2147483639) {
         throw new RuntimeException((new java.lang.StringBuilder(118)).append("Array of array-backed collection exceeds VM length limit of ").append(2147483639).append(". Requested length: ").append(targetLen).append("; current length: ").append(arrayLen).toString());
      } else if (arrayLen > 1073741819) {
         return 2147483639;
      } else {
         scala.math.package$ var10000 = scala.math.package$.MODULE$;
         var10000 = scala.math.package$.MODULE$;
         int max_y = Math.max(arrayLen * 2, 16);
         return Math.max(targetLen, max_y);
      }
   }

   public Object[] scala$collection$mutable$ArrayBuffer$$ensureSize(final Object[] array, final int curSize, final int targetSize) {
      int newLen = this.resizeUp(array.length, targetSize);
      if (newLen < 0) {
         return array;
      } else {
         Object[] res = new Object[newLen];
         System.arraycopy(array, 0, res, 0, curSize);
         return res;
      }
   }

   private int resizeDown(final int arrayLen, final int targetLen) {
      if (targetLen >= arrayLen) {
         return -1;
      } else {
         scala.math.package$ var10000 = scala.math.package$.MODULE$;
         int max_y = 0;
         return Math.max(targetLen, max_y);
      }
   }

   public Object[] scala$collection$mutable$ArrayBuffer$$downsize(final Object[] array, final int targetSize) {
      int newLen = this.resizeDown(array.length, targetSize);
      if (newLen < 0) {
         return array;
      } else if (newLen == 0) {
         return emptyArray;
      } else {
         Object[] res = new Object[newLen];
         System.arraycopy(array, 0, res, 0, targetSize);
         return res;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayBuffer$.class);
   }

   private ArrayBuffer$() {
   }
}
