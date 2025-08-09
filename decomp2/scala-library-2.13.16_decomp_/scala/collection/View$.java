package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.LazyList$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt$;

public final class View$ implements IterableFactory {
   public static final View$ MODULE$ = new View$();
   private static final long serialVersionUID = 3L;

   static {
      View$ var10000 = MODULE$;
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

   public Object fill(final int n, final Function0 elem) {
      return IterableFactory.fill$(this, n, elem);
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

   public Object tabulate(final int n, final Function1 f) {
      return IterableFactory.tabulate$(this, n, f);
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

   public Object concat(final scala.collection.immutable.Seq xss) {
      return IterableFactory.concat$(this, xss);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public View fromIteratorProvider(final Function0 it) {
      return new AbstractView(it) {
         private final Function0 it$1;

         public Iterator iterator() {
            return (Iterator)this.it$1.apply();
         }

         public {
            this.it$1 = it$1;
         }
      };
   }

   public View from(final IterableOnce it) {
      if (it instanceof View) {
         return (View)it;
      } else if (it instanceof Iterable) {
         Function0 fromIteratorProvider_it = () -> x3$1.iterator();
         return new AbstractView(fromIteratorProvider_it) {
            private final Function0 it$1;

            public Iterator iterator() {
               return (Iterator)this.it$1.apply();
            }

            public {
               this.it$1 = it$1;
            }
         };
      } else {
         LazyList var10000 = LazyList$.MODULE$.from(it);
         if (var10000 == null) {
            throw null;
         } else {
            AbstractSeq view_this = var10000;
            return new SeqView.Id(view_this);
         }
      }
   }

   public View empty() {
      return View.Empty$.MODULE$;
   }

   public Builder newBuilder() {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      return (new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      }).mapResult((it) -> MODULE$.from(it));
   }

   public View apply(final scala.collection.immutable.Seq xs) {
      return new View.Elems(xs);
   }

   public Iterator takeRightIterator(final Iterator it, final int n) {
      int k = it.knownSize();
      if (k != 0 && n > 0) {
         if (n == Integer.MAX_VALUE) {
            return it;
         } else if (k > 0) {
            RichInt$ var10001 = RichInt$.MODULE$;
            int var4 = k - n;
            int max$extension_that = 0;
            scala.math.package$ var6 = scala.math.package$.MODULE$;
            return it.drop(Math.max(var4, max$extension_that));
         } else {
            return new View.TakeRightIterator(it, n);
         }
      } else {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }
   }

   public Iterator dropRightIterator(final Iterator it, final int n) {
      if (n <= 0) {
         return it;
      } else {
         int k = it.knownSize();
         return (Iterator)(k >= 0 ? it.take(k - n) : new View.DropRightIterator(it, n));
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(View$.class);
   }

   private View$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
