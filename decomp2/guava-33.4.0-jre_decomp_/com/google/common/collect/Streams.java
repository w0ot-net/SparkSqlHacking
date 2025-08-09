package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Streams {
   public static Stream stream(Iterable iterable) {
      return iterable instanceof Collection ? ((Collection)iterable).stream() : StreamSupport.stream(iterable.spliterator(), false);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "collection.stream()"
   )
   public static Stream stream(Collection collection) {
      return collection.stream();
   }

   public static Stream stream(Iterator iterator) {
      return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
   }

   public static Stream stream(Optional optional) {
      return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
   }

   @Beta
   @InlineMe(
      replacement = "optional.stream()"
   )
   @InlineMeValidationDisabled("Java 9+ API only")
   public static Stream stream(java.util.Optional optional) {
      return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
   }

   @Beta
   @InlineMe(
      replacement = "optional.stream()"
   )
   @InlineMeValidationDisabled("Java 9+ API only")
   public static IntStream stream(OptionalInt optional) {
      return optional.isPresent() ? IntStream.of(optional.getAsInt()) : IntStream.empty();
   }

   @Beta
   @InlineMe(
      replacement = "optional.stream()"
   )
   @InlineMeValidationDisabled("Java 9+ API only")
   public static LongStream stream(OptionalLong optional) {
      return optional.isPresent() ? LongStream.of(optional.getAsLong()) : LongStream.empty();
   }

   @Beta
   @InlineMe(
      replacement = "optional.stream()"
   )
   @InlineMeValidationDisabled("Java 9+ API only")
   public static DoubleStream stream(OptionalDouble optional) {
      return optional.isPresent() ? DoubleStream.of(optional.getAsDouble()) : DoubleStream.empty();
   }

   private static void closeAll(BaseStream[] toClose) {
      Exception exception = null;

      for(BaseStream stream : toClose) {
         try {
            stream.close();
         } catch (Exception e) {
            if (exception == null) {
               exception = e;
            } else {
               exception.addSuppressed(e);
            }
         }
      }

      if (exception != null) {
         sneakyThrow(exception);
      }

   }

   private static void sneakyThrow(Throwable t) {
      class SneakyThrower {
         void throwIt(Throwable t) throws Throwable {
            throw t;
         }
      }

      (new SneakyThrower()).throwIt(t);
   }

   @SafeVarargs
   public static Stream concat(Stream... streams) {
      boolean isParallel = false;
      int characteristics = 336;
      long estimatedSize = 0L;
      ImmutableList.Builder<Spliterator<? extends T>> splitrsBuilder = new ImmutableList.Builder(streams.length);

      for(Stream stream : streams) {
         isParallel |= stream.isParallel();
         Spliterator<? extends T> splitr = stream.spliterator();
         splitrsBuilder.add((Object)splitr);
         characteristics &= splitr.characteristics();
         estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr.estimateSize());
      }

      return (Stream)StreamSupport.stream(CollectSpliterators.flatMap(splitrsBuilder.build().spliterator(), (splitrx) -> splitrx, characteristics, estimatedSize), isParallel).onClose(() -> closeAll(streams));
   }

   public static IntStream concat(IntStream... streams) {
      boolean isParallel = false;
      int characteristics = 336;
      long estimatedSize = 0L;
      ImmutableList.Builder<Spliterator.OfInt> splitrsBuilder = new ImmutableList.Builder(streams.length);

      for(IntStream stream : streams) {
         isParallel |= stream.isParallel();
         Spliterator.OfInt splitr = stream.spliterator();
         splitrsBuilder.add((Object)splitr);
         characteristics &= splitr.characteristics();
         estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr.estimateSize());
      }

      return (IntStream)StreamSupport.intStream(CollectSpliterators.flatMapToInt(splitrsBuilder.build().spliterator(), (splitrx) -> splitrx, characteristics, estimatedSize), isParallel).onClose(() -> closeAll(streams));
   }

   public static LongStream concat(LongStream... streams) {
      boolean isParallel = false;
      int characteristics = 336;
      long estimatedSize = 0L;
      ImmutableList.Builder<Spliterator.OfLong> splitrsBuilder = new ImmutableList.Builder(streams.length);

      for(LongStream stream : streams) {
         isParallel |= stream.isParallel();
         Spliterator.OfLong splitr = stream.spliterator();
         splitrsBuilder.add((Object)splitr);
         characteristics &= splitr.characteristics();
         estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr.estimateSize());
      }

      return (LongStream)StreamSupport.longStream(CollectSpliterators.flatMapToLong(splitrsBuilder.build().spliterator(), (splitrx) -> splitrx, characteristics, estimatedSize), isParallel).onClose(() -> closeAll(streams));
   }

   public static DoubleStream concat(DoubleStream... streams) {
      boolean isParallel = false;
      int characteristics = 336;
      long estimatedSize = 0L;
      ImmutableList.Builder<Spliterator.OfDouble> splitrsBuilder = new ImmutableList.Builder(streams.length);

      for(DoubleStream stream : streams) {
         isParallel |= stream.isParallel();
         Spliterator.OfDouble splitr = stream.spliterator();
         splitrsBuilder.add((Object)splitr);
         characteristics &= splitr.characteristics();
         estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr.estimateSize());
      }

      return (DoubleStream)StreamSupport.doubleStream(CollectSpliterators.flatMapToDouble(splitrsBuilder.build().spliterator(), (splitrx) -> splitrx, characteristics, estimatedSize), isParallel).onClose(() -> closeAll(streams));
   }

   @Beta
   public static Stream zip(Stream streamA, Stream streamB, final BiFunction function) {
      Preconditions.checkNotNull(streamA);
      Preconditions.checkNotNull(streamB);
      Preconditions.checkNotNull(function);
      boolean isParallel = streamA.isParallel() || streamB.isParallel();
      Spliterator<A> splitrA = streamA.spliterator();
      Spliterator<B> splitrB = streamB.spliterator();
      int characteristics = splitrA.characteristics() & splitrB.characteristics() & 80;
      final Iterator<A> itrA = Spliterators.iterator(splitrA);
      final Iterator<B> itrB = Spliterators.iterator(splitrB);
      Stream var10000 = StreamSupport.stream(new Spliterators.AbstractSpliterator(Math.min(splitrA.estimateSize(), splitrB.estimateSize()), characteristics) {
         public boolean tryAdvance(Consumer action) {
            if (itrA.hasNext() && itrB.hasNext()) {
               action.accept(function.apply(itrA.next(), itrB.next()));
               return true;
            } else {
               return false;
            }
         }
      }, isParallel);
      Objects.requireNonNull(streamA);
      var10000 = (Stream)var10000.onClose(streamA::close);
      Objects.requireNonNull(streamB);
      return (Stream)var10000.onClose(streamB::close);
   }

   @Beta
   public static void forEachPair(Stream streamA, Stream streamB, BiConsumer consumer) {
      Preconditions.checkNotNull(consumer);
      if (!streamA.isParallel() && !streamB.isParallel()) {
         Iterator<A> iterA = streamA.iterator();
         Iterator<B> iterB = streamB.iterator();

         while(iterA.hasNext() && iterB.hasNext()) {
            consumer.accept(iterA.next(), iterB.next());
         }
      } else {
         zip(streamA, streamB, TemporaryPair::new).forEach((pair) -> consumer.accept(pair.a, pair.b));
      }

   }

   public static Stream mapWithIndex(Stream stream, final FunctionWithIndex function) {
      Preconditions.checkNotNull(stream);
      Preconditions.checkNotNull(function);
      boolean isParallel = stream.isParallel();
      Spliterator<T> fromSpliterator = stream.spliterator();
      if (!fromSpliterator.hasCharacteristics(16384)) {
         final Iterator<T> fromIterator = Spliterators.iterator(fromSpliterator);
         Stream var5 = StreamSupport.stream(new Spliterators.AbstractSpliterator(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 80) {
            long index = 0L;

            public boolean tryAdvance(Consumer action) {
               if (fromIterator.hasNext()) {
                  action.accept(function.apply(fromIterator.next(), (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }
         }, isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var5.onClose(stream::close);
      } else {
         class Splitr extends MapWithIndexSpliterator implements Consumer {
            @CheckForNull
            Object holder;

            Splitr(Spliterator splitr, long index) {
               super(splitr, index);
            }

            public void accept(@ParametricNullness Object t) {
               this.holder = t;
            }

            public boolean tryAdvance(Consumer action) {
               if (this.fromSpliterator.tryAdvance(this)) {
                  boolean var2;
                  try {
                     action.accept(function.apply(NullnessCasts.uncheckedCastNullableTToT(this.holder), (long)(this.index++)));
                     var2 = true;
                  } finally {
                     this.holder = null;
                  }

                  return var2;
               } else {
                  return false;
               }
            }

            Splitr createSplit(Spliterator from, long i) {
               return new Splitr(from, i);
            }
         }

         Stream var10000 = StreamSupport.stream(new Splitr(fromSpliterator, 0L), isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var10000.onClose(stream::close);
      }
   }

   public static Stream mapWithIndex(IntStream stream, final IntFunctionWithIndex function) {
      Preconditions.checkNotNull(stream);
      Preconditions.checkNotNull(function);
      boolean isParallel = stream.isParallel();
      Spliterator.OfInt fromSpliterator = stream.spliterator();
      if (!fromSpliterator.hasCharacteristics(16384)) {
         final PrimitiveIterator.OfInt fromIterator = Spliterators.iterator(fromSpliterator);
         Stream var5 = StreamSupport.stream(new Spliterators.AbstractSpliterator(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 80) {
            long index = 0L;

            public boolean tryAdvance(Consumer action) {
               if (fromIterator.hasNext()) {
                  action.accept(function.apply(fromIterator.nextInt(), (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }
         }, isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var5.onClose(stream::close);
      } else {
         class Splitr extends MapWithIndexSpliterator implements IntConsumer, Spliterator {
            int holder;

            Splitr(Spliterator.OfInt splitr, long index) {
               super(splitr, index);
            }

            public void accept(int t) {
               this.holder = t;
            }

            public boolean tryAdvance(Consumer action) {
               if (((Spliterator.OfInt)this.fromSpliterator).tryAdvance(this)) {
                  action.accept(function.apply(this.holder, (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }

            Splitr createSplit(Spliterator.OfInt from, long i) {
               return new Splitr(from, i);
            }
         }

         Stream var10000 = StreamSupport.stream(new Splitr(fromSpliterator, 0L), isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var10000.onClose(stream::close);
      }
   }

   public static Stream mapWithIndex(LongStream stream, final LongFunctionWithIndex function) {
      Preconditions.checkNotNull(stream);
      Preconditions.checkNotNull(function);
      boolean isParallel = stream.isParallel();
      Spliterator.OfLong fromSpliterator = stream.spliterator();
      if (!fromSpliterator.hasCharacteristics(16384)) {
         final PrimitiveIterator.OfLong fromIterator = Spliterators.iterator(fromSpliterator);
         Stream var5 = StreamSupport.stream(new Spliterators.AbstractSpliterator(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 80) {
            long index = 0L;

            public boolean tryAdvance(Consumer action) {
               if (fromIterator.hasNext()) {
                  action.accept(function.apply(fromIterator.nextLong(), (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }
         }, isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var5.onClose(stream::close);
      } else {
         class Splitr extends MapWithIndexSpliterator implements LongConsumer, Spliterator {
            long holder;

            Splitr(Spliterator.OfLong splitr, long index) {
               super(splitr, index);
            }

            public void accept(long t) {
               this.holder = t;
            }

            public boolean tryAdvance(Consumer action) {
               if (((Spliterator.OfLong)this.fromSpliterator).tryAdvance(this)) {
                  action.accept(function.apply(this.holder, (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }

            Splitr createSplit(Spliterator.OfLong from, long i) {
               return new Splitr(from, i);
            }
         }

         Stream var10000 = StreamSupport.stream(new Splitr(fromSpliterator, 0L), isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var10000.onClose(stream::close);
      }
   }

   public static Stream mapWithIndex(DoubleStream stream, final DoubleFunctionWithIndex function) {
      Preconditions.checkNotNull(stream);
      Preconditions.checkNotNull(function);
      boolean isParallel = stream.isParallel();
      Spliterator.OfDouble fromSpliterator = stream.spliterator();
      if (!fromSpliterator.hasCharacteristics(16384)) {
         final PrimitiveIterator.OfDouble fromIterator = Spliterators.iterator(fromSpliterator);
         Stream var5 = StreamSupport.stream(new Spliterators.AbstractSpliterator(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 80) {
            long index = 0L;

            public boolean tryAdvance(Consumer action) {
               if (fromIterator.hasNext()) {
                  action.accept(function.apply(fromIterator.nextDouble(), (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }
         }, isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var5.onClose(stream::close);
      } else {
         class Splitr extends MapWithIndexSpliterator implements DoubleConsumer, Spliterator {
            double holder;

            Splitr(Spliterator.OfDouble splitr, long index) {
               super(splitr, index);
            }

            public void accept(double t) {
               this.holder = t;
            }

            public boolean tryAdvance(Consumer action) {
               if (((Spliterator.OfDouble)this.fromSpliterator).tryAdvance(this)) {
                  action.accept(function.apply(this.holder, (long)(this.index++)));
                  return true;
               } else {
                  return false;
               }
            }

            Splitr createSplit(Spliterator.OfDouble from, long i) {
               return new Splitr(from, i);
            }
         }

         Stream var10000 = StreamSupport.stream(new Splitr(fromSpliterator, 0L), isParallel);
         Objects.requireNonNull(stream);
         return (Stream)var10000.onClose(stream::close);
      }
   }

   public static java.util.Optional findLast(Stream stream) {
      class OptionalState {
         boolean set = false;
         @CheckForNull
         Object value = null;

         void set(Object value) {
            this.set = true;
            this.value = value;
         }

         Object get() {
            return Objects.requireNonNull(this.value);
         }
      }

      OptionalState state = new OptionalState();
      Deque<Spliterator<T>> splits = new ArrayDeque();
      splits.addLast(stream.spliterator());

      while(!splits.isEmpty()) {
         Spliterator<T> spliterator = (Spliterator)splits.removeLast();
         if (spliterator.getExactSizeIfKnown() != 0L) {
            if (spliterator.hasCharacteristics(16384)) {
               while(true) {
                  Spliterator<T> prefix = spliterator.trySplit();
                  if (prefix == null || prefix.getExactSizeIfKnown() == 0L) {
                     break;
                  }

                  if (spliterator.getExactSizeIfKnown() == 0L) {
                     spliterator = prefix;
                     break;
                  }
               }

               Objects.requireNonNull(state);
               spliterator.forEachRemaining(state::set);
               return java.util.Optional.of(state.get());
            }

            Spliterator<T> prefix = spliterator.trySplit();
            if (prefix != null && prefix.getExactSizeIfKnown() != 0L) {
               splits.addLast(prefix);
               splits.addLast(spliterator);
            } else {
               Objects.requireNonNull(state);
               spliterator.forEachRemaining(state::set);
               if (state.set) {
                  return java.util.Optional.of(state.get());
               }
            }
         }
      }

      return java.util.Optional.empty();
   }

   public static OptionalInt findLast(IntStream stream) {
      java.util.Optional<Integer> boxedLast = findLast(stream.boxed());
      return (OptionalInt)boxedLast.map(OptionalInt::of).orElse(OptionalInt.empty());
   }

   public static OptionalLong findLast(LongStream stream) {
      java.util.Optional<Long> boxedLast = findLast(stream.boxed());
      return (OptionalLong)boxedLast.map(OptionalLong::of).orElse(OptionalLong.empty());
   }

   public static OptionalDouble findLast(DoubleStream stream) {
      java.util.Optional<Double> boxedLast = findLast(stream.boxed());
      return (OptionalDouble)boxedLast.map(OptionalDouble::of).orElse(OptionalDouble.empty());
   }

   private Streams() {
   }

   private static class TemporaryPair {
      @ParametricNullness
      final Object a;
      @ParametricNullness
      final Object b;

      TemporaryPair(@ParametricNullness Object a, @ParametricNullness Object b) {
         this.a = a;
         this.b = b;
      }
   }

   private abstract static class MapWithIndexSpliterator implements Spliterator {
      final Spliterator fromSpliterator;
      long index;

      MapWithIndexSpliterator(Spliterator fromSpliterator, long index) {
         this.fromSpliterator = fromSpliterator;
         this.index = index;
      }

      abstract MapWithIndexSpliterator createSplit(Spliterator from, long i);

      @CheckForNull
      public MapWithIndexSpliterator trySplit() {
         Spliterator<?> splitOrNull = this.fromSpliterator.trySplit();
         if (splitOrNull == null) {
            return null;
         } else {
            S result = (S)this.createSplit(splitOrNull, this.index);
            this.index += splitOrNull.getExactSizeIfKnown();
            return result;
         }
      }

      public long estimateSize() {
         return this.fromSpliterator.estimateSize();
      }

      public int characteristics() {
         return this.fromSpliterator.characteristics() & 16464;
      }
   }

   public interface DoubleFunctionWithIndex {
      @ParametricNullness
      Object apply(double from, long index);
   }

   public interface FunctionWithIndex {
      @ParametricNullness
      Object apply(@ParametricNullness Object from, long index);
   }

   public interface IntFunctionWithIndex {
      @ParametricNullness
      Object apply(int from, long index);
   }

   public interface LongFunctionWithIndex {
      @ParametricNullness
      Object apply(long from, long index);
   }
}
