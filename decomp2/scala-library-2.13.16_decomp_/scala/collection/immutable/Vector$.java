package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedSeqFactory;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class Vector$ implements StrictOptimizedSeqFactory {
   public static final Vector$ MODULE$ = new Vector$();
   private static final long serialVersionUID = 3L;
   private static final int scala$collection$immutable$Vector$$defaultApplyPreferredMaxLength;
   private static final NewVectorIterator scala$collection$immutable$Vector$$emptyIterator;

   static {
      Vector$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      scala$collection$immutable$Vector$$defaultApplyPreferredMaxLength = liftedTree1$1();
      scala$collection$immutable$Vector$$emptyIterator = new NewVectorIterator(Vector0$.MODULE$, 0, 0);
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem) {
      return StrictOptimizedSeqFactory.fill$(this, n, elem);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      return StrictOptimizedSeqFactory.tabulate$(this, n, f);
   }

   public scala.collection.SeqOps concat(final Seq xss) {
      return StrictOptimizedSeqFactory.concat$(this, xss);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return SeqFactory.unapplySeq$(this, x);
   }

   public Object apply(final Seq elems) {
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

   public Vector empty() {
      return Vector0$.MODULE$;
   }

   public Vector from(final IterableOnce it) {
      if (it instanceof Vector) {
         return (Vector)it;
      } else {
         int knownSize = it.knownSize();
         if (knownSize == 0) {
            return Vector0$.MODULE$;
         } else if (knownSize > 0 && knownSize <= 32) {
            Object[] var9;
            label26: {
               if (it instanceof ArraySeq.ofRef) {
                  ArraySeq.ofRef var4 = (ArraySeq.ofRef)it;
                  Class var10000 = var4.elemTag().runtimeClass();
                  Class var5 = Object.class;
                  if (var10000 != null) {
                     if (var10000.equals(var5)) {
                        var9 = var4.unsafeArray();
                        break label26;
                     }
                  }
               }

               if (it instanceof Iterable) {
                  Iterable var6 = (Iterable)it;
                  Object[] a1 = new Object[knownSize];
                  var6.copyToArray(a1);
                  var9 = a1;
               } else {
                  Object[] a1 = new Object[knownSize];
                  it.iterator().copyToArray(a1);
                  var9 = a1;
               }
            }

            Object[] a1 = var9;
            return new Vector1(a1);
         } else {
            return (Vector)(new VectorBuilder()).addAll(it).result();
         }
      }
   }

   public ReusableBuilder newBuilder() {
      return new VectorBuilder();
   }

   public Vector fillSparse(final int n, final Object elem) {
      if (n <= 0) {
         return Vector0$.MODULE$;
      } else {
         VectorBuilder b = new VectorBuilder();
         b.initSparse(n, elem);
         return b.result();
      }
   }

   public int scala$collection$immutable$Vector$$defaultApplyPreferredMaxLength() {
      return scala$collection$immutable$Vector$$defaultApplyPreferredMaxLength;
   }

   public NewVectorIterator scala$collection$immutable$Vector$$emptyIterator() {
      return scala$collection$immutable$Vector$$emptyIterator;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Vector$.class);
   }

   // $FF: synthetic method
   private static final int liftedTree1$1() {
      int var10000;
      try {
         var10000 = Integer.parseInt(System.getProperty("scala.collection.immutable.Vector.defaultApplyPreferredMaxLength", "250"));
      } catch (SecurityException var0) {
         var10000 = 250;
      }

      return var10000;
   }

   private Vector$() {
   }
}
