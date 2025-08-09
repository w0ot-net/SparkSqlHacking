package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class CollectSpliterators {
   private CollectSpliterators() {
   }

   static Spliterator indexed(int size, int extraCharacteristics, IntFunction function) {
      return indexed(size, extraCharacteristics, function, (Comparator)null);
   }

   static Spliterator indexed(int size, final int extraCharacteristics, final IntFunction function, @CheckForNull final Comparator comparator) {
      if (comparator != null) {
         Preconditions.checkArgument((extraCharacteristics & 4) != 0);
      }

      class WithCharacteristics implements Spliterator {
         private final Spliterator.OfInt delegate;

         WithCharacteristics(Spliterator.OfInt delegate) {
            this.delegate = delegate;
         }

         public boolean tryAdvance(Consumer action) {
            return this.delegate.tryAdvance((i) -> action.accept(function.apply(i)));
         }

         public void forEachRemaining(Consumer action) {
            this.delegate.forEachRemaining((i) -> action.accept(function.apply(i)));
         }

         @CheckForNull
         public Spliterator trySplit() {
            Spliterator.OfInt split = this.delegate.trySplit();
            return split == null ? null : new WithCharacteristics(split);
         }

         public long estimateSize() {
            return this.delegate.estimateSize();
         }

         public int characteristics() {
            return 16464 | extraCharacteristics;
         }

         @CheckForNull
         public Comparator getComparator() {
            if (this.hasCharacteristics(4)) {
               return comparator;
            } else {
               throw new IllegalStateException();
            }
         }
      }

      return new WithCharacteristics(IntStream.range(0, size).spliterator());
   }

   static Spliterator map(final Spliterator fromSpliterator, final Function function) {
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(function);
      return new Spliterator() {
         public boolean tryAdvance(Consumer action) {
            return fromSpliterator.tryAdvance((fromElement) -> action.accept(function.apply(fromElement)));
         }

         public void forEachRemaining(Consumer action) {
            fromSpliterator.forEachRemaining((fromElement) -> action.accept(function.apply(fromElement)));
         }

         @CheckForNull
         public Spliterator trySplit() {
            Spliterator<InElementT> fromSplit = fromSpliterator.trySplit();
            return fromSplit != null ? CollectSpliterators.map(fromSplit, function) : null;
         }

         public long estimateSize() {
            return fromSpliterator.estimateSize();
         }

         public int characteristics() {
            return fromSpliterator.characteristics() & -262;
         }
      };
   }

   static Spliterator filter(final Spliterator fromSpliterator, final Predicate predicate) {
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(predicate);

      class Splitr implements Spliterator, Consumer {
         @CheckForNull
         Object holder = null;

         public void accept(@ParametricNullness Object t) {
            this.holder = t;
         }

         public boolean tryAdvance(Consumer action) {
            while(true) {
               if (fromSpliterator.tryAdvance(this)) {
                  boolean var3;
                  try {
                     T next = (T)NullnessCasts.uncheckedCastNullableTToT(this.holder);
                     if (!predicate.test(next)) {
                        continue;
                     }

                     action.accept(next);
                     var3 = true;
                  } finally {
                     this.holder = null;
                  }

                  return var3;
               }

               return false;
            }
         }

         @CheckForNull
         public Spliterator trySplit() {
            Spliterator<T> fromSplit = fromSpliterator.trySplit();
            return fromSplit == null ? null : CollectSpliterators.filter(fromSplit, predicate);
         }

         public long estimateSize() {
            return fromSpliterator.estimateSize() / 2L;
         }

         @CheckForNull
         public Comparator getComparator() {
            return fromSpliterator.getComparator();
         }

         public int characteristics() {
            return fromSpliterator.characteristics() & 277;
         }
      }

      return new Splitr();
   }

   static Spliterator flatMap(Spliterator fromSpliterator, Function function, int topCharacteristics, long topSize) {
      Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(function);
      return new FlatMapSpliteratorOfObject((Spliterator)null, fromSpliterator, function, topCharacteristics, topSize);
   }

   static Spliterator.OfInt flatMapToInt(Spliterator fromSpliterator, Function function, int topCharacteristics, long topSize) {
      Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(function);
      return new FlatMapSpliteratorOfInt((Spliterator.OfInt)null, fromSpliterator, function, topCharacteristics, topSize);
   }

   static Spliterator.OfLong flatMapToLong(Spliterator fromSpliterator, Function function, int topCharacteristics, long topSize) {
      Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(function);
      return new FlatMapSpliteratorOfLong((Spliterator.OfLong)null, fromSpliterator, function, topCharacteristics, topSize);
   }

   static Spliterator.OfDouble flatMapToDouble(Spliterator fromSpliterator, Function function, int topCharacteristics, long topSize) {
      Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
      Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
      Preconditions.checkNotNull(fromSpliterator);
      Preconditions.checkNotNull(function);
      return new FlatMapSpliteratorOfDouble((Spliterator.OfDouble)null, fromSpliterator, function, topCharacteristics, topSize);
   }

   abstract static class FlatMapSpliterator implements Spliterator {
      @CheckForNull
      @Weak
      Spliterator prefix;
      final Spliterator from;
      final Function function;
      final Factory factory;
      int characteristics;
      long estimatedSize;

      FlatMapSpliterator(@CheckForNull Spliterator prefix, Spliterator from, Function function, Factory factory, int characteristics, long estimatedSize) {
         this.prefix = prefix;
         this.from = from;
         this.function = function;
         this.factory = factory;
         this.characteristics = characteristics;
         this.estimatedSize = estimatedSize;
      }

      public final boolean tryAdvance(Consumer action) {
         while(this.prefix == null || !this.prefix.tryAdvance(action)) {
            this.prefix = null;
            if (!this.from.tryAdvance((fromElement) -> this.prefix = (Spliterator)this.function.apply(fromElement))) {
               return false;
            }
         }

         if (this.estimatedSize != Long.MAX_VALUE) {
            --this.estimatedSize;
         }

         return true;
      }

      public final void forEachRemaining(Consumer action) {
         if (this.prefix != null) {
            this.prefix.forEachRemaining(action);
            this.prefix = null;
         }

         this.from.forEachRemaining((fromElement) -> {
            Spliterator<OutElementT> elements = (Spliterator)this.function.apply(fromElement);
            if (elements != null) {
               elements.forEachRemaining(action);
            }

         });
         this.estimatedSize = 0L;
      }

      @CheckForNull
      public final Spliterator trySplit() {
         Spliterator<InElementT> fromSplit = this.from.trySplit();
         if (fromSplit != null) {
            int splitCharacteristics = this.characteristics & -65;
            long estSplitSize = this.estimateSize();
            if (estSplitSize < Long.MAX_VALUE) {
               estSplitSize /= 2L;
               this.estimatedSize -= estSplitSize;
               this.characteristics = splitCharacteristics;
            }

            OutSpliteratorT result = (OutSpliteratorT)this.factory.newFlatMapSpliterator(this.prefix, fromSplit, this.function, splitCharacteristics, estSplitSize);
            this.prefix = null;
            return result;
         } else if (this.prefix != null) {
            OutSpliteratorT result = (OutSpliteratorT)this.prefix;
            this.prefix = null;
            return result;
         } else {
            return null;
         }
      }

      public final long estimateSize() {
         if (this.prefix != null) {
            this.estimatedSize = Math.max(this.estimatedSize, this.prefix.estimateSize());
         }

         return Math.max(this.estimatedSize, 0L);
      }

      public final int characteristics() {
         return this.characteristics;
      }

      @FunctionalInterface
      interface Factory {
         Spliterator newFlatMapSpliterator(@CheckForNull Spliterator prefix, Spliterator fromSplit, Function function, int splitCharacteristics, long estSplitSize);
      }
   }

   static final class FlatMapSpliteratorOfObject extends FlatMapSpliterator {
      FlatMapSpliteratorOfObject(@CheckForNull Spliterator prefix, Spliterator from, Function function, int characteristics, long estimatedSize) {
         super(prefix, from, function, FlatMapSpliteratorOfObject::new, characteristics, estimatedSize);
      }
   }

   abstract static class FlatMapSpliteratorOfPrimitive extends FlatMapSpliterator implements Spliterator.OfPrimitive {
      FlatMapSpliteratorOfPrimitive(@CheckForNull Spliterator.OfPrimitive prefix, Spliterator from, Function function, FlatMapSpliterator.Factory factory, int characteristics, long estimatedSize) {
         super(prefix, from, function, factory, characteristics, estimatedSize);
      }

      public final boolean tryAdvance(Object action) {
         while(this.prefix == null || !((Spliterator.OfPrimitive)this.prefix).tryAdvance(action)) {
            this.prefix = null;
            if (!this.from.tryAdvance((fromElement) -> this.prefix = (Spliterator)this.function.apply(fromElement))) {
               return false;
            }
         }

         if (this.estimatedSize != Long.MAX_VALUE) {
            --this.estimatedSize;
         }

         return true;
      }

      public final void forEachRemaining(Object action) {
         if (this.prefix != null) {
            ((Spliterator.OfPrimitive)this.prefix).forEachRemaining(action);
            this.prefix = null;
         }

         this.from.forEachRemaining((fromElement) -> {
            OutSpliteratorT elements = (OutSpliteratorT)((Spliterator.OfPrimitive)this.function.apply(fromElement));
            if (elements != null) {
               elements.forEachRemaining(action);
            }

         });
         this.estimatedSize = 0L;
      }
   }

   static final class FlatMapSpliteratorOfInt extends FlatMapSpliteratorOfPrimitive implements Spliterator.OfInt {
      FlatMapSpliteratorOfInt(@CheckForNull Spliterator.OfInt prefix, Spliterator from, Function function, int characteristics, long estimatedSize) {
         super(prefix, from, function, FlatMapSpliteratorOfInt::new, characteristics, estimatedSize);
      }
   }

   static final class FlatMapSpliteratorOfLong extends FlatMapSpliteratorOfPrimitive implements Spliterator.OfLong {
      FlatMapSpliteratorOfLong(@CheckForNull Spliterator.OfLong prefix, Spliterator from, Function function, int characteristics, long estimatedSize) {
         super(prefix, from, function, FlatMapSpliteratorOfLong::new, characteristics, estimatedSize);
      }
   }

   static final class FlatMapSpliteratorOfDouble extends FlatMapSpliteratorOfPrimitive implements Spliterator.OfDouble {
      FlatMapSpliteratorOfDouble(@CheckForNull Spliterator.OfDouble prefix, Spliterator from, Function function, int characteristics, long estimatedSize) {
         super(prefix, from, function, FlatMapSpliteratorOfDouble::new, characteristics, estimatedSize);
      }
   }
}
