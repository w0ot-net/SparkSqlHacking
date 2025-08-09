package org.sparkproject.guava.base;

import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class Converter implements Function {
   private final boolean handleNullAutomatically;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient Converter reverse;

   protected Converter() {
      this(true);
   }

   Converter(boolean handleNullAutomatically) {
      this.handleNullAutomatically = handleNullAutomatically;
   }

   @ForOverride
   protected abstract Object doForward(Object a);

   @ForOverride
   protected abstract Object doBackward(Object b);

   @CheckForNull
   public final Object convert(@CheckForNull Object a) {
      return this.correctedDoForward(a);
   }

   @CheckForNull
   Object correctedDoForward(@CheckForNull Object a) {
      if (this.handleNullAutomatically) {
         return a == null ? null : Preconditions.checkNotNull(this.doForward(a));
      } else {
         return this.unsafeDoForward(a);
      }
   }

   @CheckForNull
   Object correctedDoBackward(@CheckForNull Object b) {
      if (this.handleNullAutomatically) {
         return b == null ? null : Preconditions.checkNotNull(this.doBackward(b));
      } else {
         return this.unsafeDoBackward(b);
      }
   }

   @CheckForNull
   private Object unsafeDoForward(@CheckForNull Object a) {
      return this.doForward(NullnessCasts.uncheckedCastNullableTToT(a));
   }

   @CheckForNull
   private Object unsafeDoBackward(@CheckForNull Object b) {
      return this.doBackward(NullnessCasts.uncheckedCastNullableTToT(b));
   }

   public Iterable convertAll(final Iterable fromIterable) {
      Preconditions.checkNotNull(fromIterable, "fromIterable");
      return new Iterable() {
         public Iterator iterator() {
            return new Iterator() {
               private final Iterator fromIterator = fromIterable.iterator();

               public boolean hasNext() {
                  return this.fromIterator.hasNext();
               }

               public Object next() {
                  return Converter.this.convert(this.fromIterator.next());
               }

               public void remove() {
                  this.fromIterator.remove();
               }
            };
         }
      };
   }

   @CheckReturnValue
   public Converter reverse() {
      Converter<B, A> result = this.reverse;
      return result == null ? (this.reverse = new ReverseConverter(this)) : result;
   }

   public final Converter andThen(Converter secondConverter) {
      return this.doAndThen(secondConverter);
   }

   Converter doAndThen(Converter secondConverter) {
      return new ConverterComposition(this, (Converter)Preconditions.checkNotNull(secondConverter));
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.convert(a)"
   )
   public final Object apply(Object a) {
      return this.convert(a);
   }

   public boolean equals(@CheckForNull Object object) {
      return super.equals(object);
   }

   public static Converter from(Function forwardFunction, Function backwardFunction) {
      return new FunctionBasedConverter(forwardFunction, backwardFunction);
   }

   public static Converter identity() {
      return (IdentityConverter)Converter.IdentityConverter.INSTANCE;
   }

   private static final class ReverseConverter extends Converter implements Serializable {
      final Converter original;
      private static final long serialVersionUID = 0L;

      ReverseConverter(Converter original) {
         this.original = original;
      }

      protected Object doForward(Object b) {
         throw new AssertionError();
      }

      protected Object doBackward(Object a) {
         throw new AssertionError();
      }

      @CheckForNull
      Object correctedDoForward(@CheckForNull Object b) {
         return this.original.correctedDoBackward(b);
      }

      @CheckForNull
      Object correctedDoBackward(@CheckForNull Object a) {
         return this.original.correctedDoForward(a);
      }

      public Converter reverse() {
         return this.original;
      }

      public boolean equals(@CheckForNull Object object) {
         if (object instanceof ReverseConverter) {
            ReverseConverter<?, ?> that = (ReverseConverter)object;
            return this.original.equals(that.original);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return ~this.original.hashCode();
      }

      public String toString() {
         return this.original + ".reverse()";
      }
   }

   private static final class ConverterComposition extends Converter implements Serializable {
      final Converter first;
      final Converter second;
      private static final long serialVersionUID = 0L;

      ConverterComposition(Converter first, Converter second) {
         this.first = first;
         this.second = second;
      }

      protected Object doForward(Object a) {
         throw new AssertionError();
      }

      protected Object doBackward(Object c) {
         throw new AssertionError();
      }

      @CheckForNull
      Object correctedDoForward(@CheckForNull Object a) {
         return this.second.correctedDoForward(this.first.correctedDoForward(a));
      }

      @CheckForNull
      Object correctedDoBackward(@CheckForNull Object c) {
         return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
      }

      public boolean equals(@CheckForNull Object object) {
         if (!(object instanceof ConverterComposition)) {
            return false;
         } else {
            ConverterComposition<?, ?, ?> that = (ConverterComposition)object;
            return this.first.equals(that.first) && this.second.equals(that.second);
         }
      }

      public int hashCode() {
         return 31 * this.first.hashCode() + this.second.hashCode();
      }

      public String toString() {
         return this.first + ".andThen(" + this.second + ")";
      }
   }

   private static final class FunctionBasedConverter extends Converter implements Serializable {
      private final Function forwardFunction;
      private final Function backwardFunction;

      private FunctionBasedConverter(Function forwardFunction, Function backwardFunction) {
         this.forwardFunction = (Function)Preconditions.checkNotNull(forwardFunction);
         this.backwardFunction = (Function)Preconditions.checkNotNull(backwardFunction);
      }

      protected Object doForward(Object a) {
         return this.forwardFunction.apply(a);
      }

      protected Object doBackward(Object b) {
         return this.backwardFunction.apply(b);
      }

      public boolean equals(@CheckForNull Object object) {
         if (!(object instanceof FunctionBasedConverter)) {
            return false;
         } else {
            FunctionBasedConverter<?, ?> that = (FunctionBasedConverter)object;
            return this.forwardFunction.equals(that.forwardFunction) && this.backwardFunction.equals(that.backwardFunction);
         }
      }

      public int hashCode() {
         return this.forwardFunction.hashCode() * 31 + this.backwardFunction.hashCode();
      }

      public String toString() {
         return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
      }
   }

   private static final class IdentityConverter extends Converter implements Serializable {
      static final Converter INSTANCE = new IdentityConverter();
      private static final long serialVersionUID = 0L;

      protected Object doForward(Object t) {
         return t;
      }

      protected Object doBackward(Object t) {
         return t;
      }

      public IdentityConverter reverse() {
         return this;
      }

      Converter doAndThen(Converter otherConverter) {
         return (Converter)Preconditions.checkNotNull(otherConverter, "otherConverter");
      }

      public String toString() {
         return "Converter.identity()";
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }
}
