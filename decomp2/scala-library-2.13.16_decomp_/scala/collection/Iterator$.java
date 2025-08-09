package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.math.Integral;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;

public final class Iterator$ implements IterableFactory {
   public static final Iterator$ MODULE$ = new Iterator$();
   private static final long serialVersionUID = 3L;
   public static final Iterator scala$collection$Iterator$$_empty;

   static {
      Iterator$ var10000 = MODULE$;
      scala$collection$Iterator$$_empty = new AbstractIterator() {
         public boolean hasNext() {
            return false;
         }

         public Nothing$ next() {
            throw new NoSuchElementException("next on empty iterator");
         }

         public int knownSize() {
            return 0;
         }

         public AbstractIterator sliceIterator(final int from, final int until) {
            return this;
         }
      };
   }

   public Object iterate(final Object start, final int len, final Function1 f) {
      return IterableFactory.iterate$(this, start, len, f);
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

   public Object concat(final scala.collection.immutable.Seq xss) {
      return IterableFactory.concat$(this, xss);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public Iterator from(final IterableOnce source) {
      return source.iterator();
   }

   public final Iterator empty() {
      return scala$collection$Iterator$$_empty;
   }

   public Iterator single(final Object a) {
      return new AbstractIterator(a) {
         private boolean consumed;
         private final Object a$1;

         public boolean hasNext() {
            return !this.consumed;
         }

         public Object next() {
            if (this.consumed) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               this.consumed = true;
               return this.a$1;
            }
         }

         public Iterator sliceIterator(final int from, final int until) {
            if (!this.consumed && from <= 0 && until != 0) {
               return this;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty;
            }
         }

         public {
            this.a$1 = a$1;
            this.consumed = false;
         }
      };
   }

   public Iterator apply(final scala.collection.immutable.Seq xs) {
      return xs.iterator();
   }

   public Builder newBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Object elem) {
            Iterator var10001 = (Iterator)this.elems();
            Function0 $plus$plus_xs = () -> Iterator$.MODULE$.single(elem);
            if (var10001 == null) {
               throw null;
            } else {
               var10001 = var10001.concat($plus$plus_xs);
               $plus$plus_xs = null;
               this.elems_$eq(var10001);
               return this;
            }
         }

         public {
            Iterator$ var10001 = Iterator$.MODULE$;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Iterator fill(final int len, final Function0 elem) {
      return new AbstractIterator(len, elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   public Iterator tabulate(final int end, final Function1 f) {
      return new AbstractIterator(end, f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   public Iterator from(final int start) {
      int from_step = 1;
      return new AbstractIterator(start, from_step) {
         private int i;
         private final int step$1;

         public boolean hasNext() {
            return true;
         }

         public int next() {
            int result = this.i;
            this.i += this.step$1;
            return result;
         }

         public {
            this.step$1 = step$1;
            this.i = start$1;
         }
      };
   }

   public Iterator from(final int start, final int step) {
      return new AbstractIterator(start, step) {
         private int i;
         private final int step$1;

         public boolean hasNext() {
            return true;
         }

         public int next() {
            int result = this.i;
            this.i += this.step$1;
            return result;
         }

         public {
            this.step$1 = step$1;
            this.i = start$1;
         }
      };
   }

   public Iterator range(final int start, final int end) {
      int range_step = 1;
      return new AbstractIterator(range_step, start, end) {
         private int i;
         private boolean hasOverflowed;
         private final int end$2;
         private final int step$2;

         public int knownSize() {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            double size = Math.ceil((double)((long)this.end$2 - (long)this.i) / (double)this.step$2);
            if (size < (double)0) {
               return 0;
            } else {
               return size > (double)Integer.MAX_VALUE ? -1 : (int)size;
            }
         }

         public boolean hasNext() {
            return (this.step$2 <= 0 || this.i < this.end$2) && (this.step$2 >= 0 || this.i > this.end$2) && !this.hasOverflowed;
         }

         public int next() {
            if (this.hasNext()) {
               int result = this.i;
               int nextValue = this.i + this.step$2;
               this.hasOverflowed = this.step$2 > 0 == nextValue < this.i;
               this.i = nextValue;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return BoxesRunTime.unboxToInt(Iterator$.scala$collection$Iterator$$_empty.next());
            }
         }

         public {
            this.end$2 = end$2;
            this.step$2 = step$2;
            if (step$2 == 0) {
               throw new IllegalArgumentException("zero step");
            } else {
               this.i = start$2;
               this.hasOverflowed = false;
            }
         }
      };
   }

   public Iterator range(final int start, final int end, final int step) {
      return new AbstractIterator(step, start, end) {
         private int i;
         private boolean hasOverflowed;
         private final int end$2;
         private final int step$2;

         public int knownSize() {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            double size = Math.ceil((double)((long)this.end$2 - (long)this.i) / (double)this.step$2);
            if (size < (double)0) {
               return 0;
            } else {
               return size > (double)Integer.MAX_VALUE ? -1 : (int)size;
            }
         }

         public boolean hasNext() {
            return (this.step$2 <= 0 || this.i < this.end$2) && (this.step$2 >= 0 || this.i > this.end$2) && !this.hasOverflowed;
         }

         public int next() {
            if (this.hasNext()) {
               int result = this.i;
               int nextValue = this.i + this.step$2;
               this.hasOverflowed = this.step$2 > 0 == nextValue < this.i;
               this.i = nextValue;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return BoxesRunTime.unboxToInt(Iterator$.scala$collection$Iterator$$_empty.next());
            }
         }

         public {
            this.end$2 = end$2;
            this.step$2 = step$2;
            if (step$2 == 0) {
               throw new IllegalArgumentException("zero step");
            } else {
               this.i = start$2;
               this.hasOverflowed = false;
            }
         }
      };
   }

   public Iterator iterate(final Object start, final Function1 f) {
      return new AbstractIterator(start, f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
   }

   public Iterator unfold(final Object init, final Function1 f) {
      return new Iterator.UnfoldIterator(init, f);
   }

   public Iterator continually(final Function0 elem) {
      return new AbstractIterator(elem) {
         private final Function0 elem$5;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            return this.elem$5.apply();
         }

         public {
            this.elem$5 = elem$5;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Iterator$.class);
   }

   private Iterator$() {
   }
}
