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

public final class ArrayDeque$ implements StrictOptimizedSeqFactory {
   public static final ArrayDeque$ MODULE$ = new ArrayDeque$();
   private static final long serialVersionUID = 3L;

   static {
      ArrayDeque$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
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

   public int $lessinit$greater$default$1() {
      return 16;
   }

   public ArrayDeque from(final IterableOnce coll) {
      int s = coll.knownSize();
      if (s >= 0) {
         Object[] array = this.alloc(s);
         IterableOnce$ var10000 = IterableOnce$.MODULE$;
         var10000 = IterableOnce$.MODULE$;
         IterableOnce$ var10001 = IterableOnce$.MODULE$;
         int copyElemsToArray_len = Integer.MAX_VALUE;
         int copyElemsToArray_start = 0;
         int actual = coll instanceof scala.collection.Iterable ? ((scala.collection.Iterable)coll).copyToArray(array, copyElemsToArray_start, copyElemsToArray_len) : coll.iterator().copyToArray(array, copyElemsToArray_start, copyElemsToArray_len);
         if (actual != s) {
            throw new IllegalStateException((new java.lang.StringBuilder(11)).append("Copied ").append(actual).append(" of ").append(s).toString());
         } else {
            return new ArrayDeque(array, 0, s);
         }
      } else {
         return (ArrayDeque)(new ArrayDeque(16)).addAll(coll);
      }
   }

   public Builder newBuilder() {
      return new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
   }

   public ArrayDeque empty() {
      return new ArrayDeque(16);
   }

   public final int DefaultInitialSize() {
      return 16;
   }

   public final int StableSize() {
      return 128;
   }

   public Object[] alloc(final int len) {
      if (len < 0) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(20)).append("requirement failed: ").append("Non-negative array size required").toString());
      } else {
         int size = Integer.MIN_VALUE >>> Integer.numberOfLeadingZeros(len) << 1;
         if (size < 0) {
            throw new IllegalArgumentException((new java.lang.StringBuilder(20)).append("requirement failed: ").append($anonfun$alloc$2(len)).toString());
         } else {
            return new Object[Math.max(size, 16)];
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayDeque$.class);
   }

   // $FF: synthetic method
   public static final String $anonfun$alloc$1() {
      return "Non-negative array size required";
   }

   // $FF: synthetic method
   public static final String $anonfun$alloc$2(final int len$1) {
      return (new java.lang.StringBuilder(58)).append("ArrayDeque too big - cannot allocate ArrayDeque of length ").append(len$1).toString();
   }

   private ArrayDeque$() {
   }
}
