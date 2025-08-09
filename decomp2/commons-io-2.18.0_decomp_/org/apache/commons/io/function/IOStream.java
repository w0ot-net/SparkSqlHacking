package org.apache.commons.io.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.io.IOExceptionList;

public interface IOStream extends IOBaseStream {
   static IOStream adapt(Stream stream) {
      return IOStreamAdapter.adapt(stream);
   }

   static IOStream empty() {
      return IOStreamAdapter.adapt(Stream.empty());
   }

   static IOStream iterate(final Object seed, final IOUnaryOperator f) {
      Objects.requireNonNull(f);
      Iterator<T> iterator = new Iterator() {
         Object t;

         {
            this.t = IOStreams.NONE;
         }

         public boolean hasNext() {
            return true;
         }

         public Object next() throws NoSuchElementException {
            try {
               return this.t = this.t == IOStreams.NONE ? seed : f.apply(this.t);
            } catch (IOException e) {
               NoSuchElementException nsee = new NoSuchElementException();
               nsee.initCause(e);
               throw nsee;
            }
         }
      };
      return adapt(StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 1040), false));
   }

   static IOStream of(Iterable values) {
      return values == null ? empty() : adapt(StreamSupport.stream(values.spliterator(), false));
   }

   @SafeVarargs
   static IOStream of(Object... values) {
      return values != null && values.length != 0 ? adapt(Arrays.stream(values)) : empty();
   }

   static IOStream of(Object t) {
      return adapt(Stream.of(t));
   }

   default boolean allMatch(IOPredicate predicate) throws IOException {
      return ((Stream)this.unwrap()).allMatch((t) -> Erase.test(predicate, t));
   }

   default boolean anyMatch(IOPredicate predicate) throws IOException {
      return ((Stream)this.unwrap()).anyMatch((t) -> Erase.test(predicate, t));
   }

   default Object collect(Collector collector) {
      return ((Stream)this.unwrap()).collect(collector);
   }

   default Object collect(IOSupplier supplier, IOBiConsumer accumulator, IOBiConsumer combiner) throws IOException {
      return ((Stream)this.unwrap()).collect(() -> Erase.get(supplier), (t, u) -> Erase.accept(accumulator, t, u), (t, u) -> Erase.accept(combiner, t, u));
   }

   default long count() {
      return ((Stream)this.unwrap()).count();
   }

   default IOStream distinct() {
      return adapt(((Stream)this.unwrap()).distinct());
   }

   default IOStream filter(IOPredicate predicate) throws IOException {
      return adapt(((Stream)this.unwrap()).filter((t) -> Erase.test(predicate, t)));
   }

   default Optional findAny() {
      return ((Stream)this.unwrap()).findAny();
   }

   default Optional findFirst() {
      return ((Stream)this.unwrap()).findFirst();
   }

   default IOStream flatMap(IOFunction mapper) throws IOException {
      return adapt(((Stream)this.unwrap()).flatMap((t) -> (Stream)((IOStream)Erase.apply(mapper, t)).unwrap()));
   }

   default DoubleStream flatMapToDouble(IOFunction mapper) throws IOException {
      return ((Stream)this.unwrap()).flatMapToDouble((t) -> (DoubleStream)Erase.apply(mapper, t));
   }

   default IntStream flatMapToInt(IOFunction mapper) throws IOException {
      return ((Stream)this.unwrap()).flatMapToInt((t) -> (IntStream)Erase.apply(mapper, t));
   }

   default LongStream flatMapToLong(IOFunction mapper) throws IOException {
      return ((Stream)this.unwrap()).flatMapToLong((t) -> (LongStream)Erase.apply(mapper, t));
   }

   default void forAll(IOConsumer action) throws IOExceptionList {
      this.forAll(action, (i, e) -> e);
   }

   default void forAll(IOConsumer action, BiFunction exSupplier) throws IOExceptionList {
      AtomicReference<List<IOException>> causeList = new AtomicReference();
      AtomicInteger index = new AtomicInteger();
      IOConsumer<T> safeAction = IOStreams.toIOConsumer(action);
      ((Stream)this.unwrap()).forEach((e) -> {
         try {
            safeAction.accept(e);
         } catch (IOException innerEx) {
            if (causeList.get() == null) {
               causeList.set(new ArrayList());
            }

            if (exSupplier != null) {
               ((List)causeList.get()).add((IOException)exSupplier.apply(index.get(), innerEx));
            }
         }

         index.incrementAndGet();
      });
      IOExceptionList.checkEmpty((List)causeList.get(), (Object)null);
   }

   default void forEach(IOConsumer action) throws IOException {
      ((Stream)this.unwrap()).forEach((e) -> Erase.accept(action, e));
   }

   default void forEachOrdered(IOConsumer action) throws IOException {
      ((Stream)this.unwrap()).forEachOrdered((e) -> Erase.accept(action, e));
   }

   default IOStream limit(long maxSize) {
      return adapt(((Stream)this.unwrap()).limit(maxSize));
   }

   default IOStream map(IOFunction mapper) throws IOException {
      return adapt(((Stream)this.unwrap()).map((t) -> Erase.apply(mapper, t)));
   }

   default DoubleStream mapToDouble(ToDoubleFunction mapper) {
      return ((Stream)this.unwrap()).mapToDouble(mapper);
   }

   default IntStream mapToInt(ToIntFunction mapper) {
      return ((Stream)this.unwrap()).mapToInt(mapper);
   }

   default LongStream mapToLong(ToLongFunction mapper) {
      return ((Stream)this.unwrap()).mapToLong(mapper);
   }

   default Optional max(IOComparator comparator) throws IOException {
      return ((Stream)this.unwrap()).max((t, u) -> Erase.compare(comparator, t, u));
   }

   default Optional min(IOComparator comparator) throws IOException {
      return ((Stream)this.unwrap()).min((t, u) -> Erase.compare(comparator, t, u));
   }

   default boolean noneMatch(IOPredicate predicate) throws IOException {
      return ((Stream)this.unwrap()).noneMatch((t) -> Erase.test(predicate, t));
   }

   default IOStream peek(IOConsumer action) throws IOException {
      return adapt(((Stream)this.unwrap()).peek((t) -> Erase.accept(action, t)));
   }

   default Optional reduce(IOBinaryOperator accumulator) throws IOException {
      return ((Stream)this.unwrap()).reduce((t, u) -> Erase.apply(accumulator, t, u));
   }

   default Object reduce(Object identity, IOBinaryOperator accumulator) throws IOException {
      return ((Stream)this.unwrap()).reduce(identity, (t, u) -> Erase.apply(accumulator, t, u));
   }

   default Object reduce(Object identity, IOBiFunction accumulator, IOBinaryOperator combiner) throws IOException {
      return ((Stream)this.unwrap()).reduce(identity, (t, u) -> Erase.apply(accumulator, t, u), (t, u) -> Erase.apply(combiner, t, u));
   }

   default IOStream skip(long n) {
      return adapt(((Stream)this.unwrap()).skip(n));
   }

   default IOStream sorted() {
      return adapt(((Stream)this.unwrap()).sorted());
   }

   default IOStream sorted(IOComparator comparator) throws IOException {
      return adapt(((Stream)this.unwrap()).sorted((t, u) -> Erase.compare(comparator, t, u)));
   }

   default Object[] toArray() {
      return ((Stream)this.unwrap()).toArray();
   }

   default Object[] toArray(IntFunction generator) {
      return ((Stream)this.unwrap()).toArray(generator);
   }
}
