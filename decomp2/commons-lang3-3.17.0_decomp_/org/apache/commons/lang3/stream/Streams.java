package org.apache.commons.lang3.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.FailablePredicate;

public class Streams {
   public static FailableStream failableStream(Collection stream) {
      return failableStream(of(stream));
   }

   public static FailableStream failableStream(Stream stream) {
      return new FailableStream(stream);
   }

   public static FailableStream failableStream(Object value) {
      return failableStream(streamOf(value));
   }

   @SafeVarargs
   public static FailableStream failableStream(Object... values) {
      return failableStream(of(values));
   }

   public static Stream instancesOf(Class clazz, Collection collection) {
      return instancesOf(clazz, of(collection));
   }

   private static Stream instancesOf(Class clazz, Stream stream) {
      Stream var10000 = of(stream);
      Objects.requireNonNull(clazz);
      return var10000.filter(clazz::isInstance);
   }

   public static Stream nonNull(Collection collection) {
      return of(collection).filter(Objects::nonNull);
   }

   public static Stream nonNull(Object array) {
      return nonNull(streamOf(array));
   }

   @SafeVarargs
   public static Stream nonNull(Object... array) {
      return nonNull(of(array));
   }

   public static Stream nonNull(Stream stream) {
      return of(stream).filter(Objects::nonNull);
   }

   public static Stream of(Collection collection) {
      return collection == null ? Stream.empty() : collection.stream();
   }

   public static Stream of(Enumeration enumeration) {
      return StreamSupport.stream(new EnumerationSpliterator(Long.MAX_VALUE, 16, enumeration), false);
   }

   public static Stream of(Iterable iterable) {
      return iterable == null ? Stream.empty() : StreamSupport.stream(iterable.spliterator(), false);
   }

   public static Stream of(Iterator iterator) {
      return iterator == null ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false);
   }

   private static Stream of(Stream stream) {
      return stream == null ? Stream.empty() : stream;
   }

   @SafeVarargs
   public static Stream of(Object... values) {
      return values == null ? Stream.empty() : Stream.of(values);
   }

   /** @deprecated */
   @Deprecated
   public static FailableStream stream(Collection collection) {
      return failableStream(collection);
   }

   /** @deprecated */
   @Deprecated
   public static FailableStream stream(Stream stream) {
      return failableStream(stream);
   }

   private static Stream streamOf(Object value) {
      return value == null ? Stream.empty() : Stream.of(value);
   }

   public static Collector toArray(Class elementType) {
      return new ArrayCollector(elementType);
   }

   public static class ArrayCollector implements Collector {
      private static final Set characteristics = Collections.emptySet();
      private final Class elementType;

      public ArrayCollector(Class elementType) {
         this.elementType = (Class)Objects.requireNonNull(elementType, "elementType");
      }

      public BiConsumer accumulator() {
         return List::add;
      }

      public Set characteristics() {
         return characteristics;
      }

      public BinaryOperator combiner() {
         return (left, right) -> {
            left.addAll(right);
            return left;
         };
      }

      public Function finisher() {
         return (list) -> list.toArray(ArrayUtils.newInstance(this.elementType, list.size()));
      }

      public Supplier supplier() {
         return ArrayList::new;
      }
   }

   private static final class EnumerationSpliterator extends Spliterators.AbstractSpliterator {
      private final Enumeration enumeration;

      protected EnumerationSpliterator(long estimatedSize, int additionalCharacteristics, Enumeration enumeration) {
         super(estimatedSize, additionalCharacteristics);
         this.enumeration = (Enumeration)Objects.requireNonNull(enumeration, "enumeration");
      }

      public void forEachRemaining(Consumer action) {
         while(this.enumeration.hasMoreElements()) {
            this.next(action);
         }

      }

      private boolean next(Consumer action) {
         action.accept(this.enumeration.nextElement());
         return true;
      }

      public boolean tryAdvance(Consumer action) {
         return this.enumeration.hasMoreElements() && this.next(action);
      }
   }

   public static class FailableStream {
      private Stream stream;
      private boolean terminated;

      public FailableStream(Stream stream) {
         this.stream = stream;
      }

      public boolean allMatch(FailablePredicate predicate) {
         this.assertNotTerminated();
         return this.stream().allMatch(Failable.asPredicate(predicate));
      }

      public boolean anyMatch(FailablePredicate predicate) {
         this.assertNotTerminated();
         return this.stream().anyMatch(Failable.asPredicate(predicate));
      }

      protected void assertNotTerminated() {
         if (this.terminated) {
            throw new IllegalStateException("This stream is already terminated.");
         }
      }

      public Object collect(Collector collector) {
         this.makeTerminated();
         return this.stream().collect(collector);
      }

      public Object collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner) {
         this.makeTerminated();
         return this.stream().collect(supplier, accumulator, combiner);
      }

      public FailableStream filter(FailablePredicate predicate) {
         this.assertNotTerminated();
         this.stream = this.stream.filter(Failable.asPredicate(predicate));
         return this;
      }

      public void forEach(FailableConsumer action) {
         this.makeTerminated();
         this.stream().forEach(Failable.asConsumer(action));
      }

      protected void makeTerminated() {
         this.assertNotTerminated();
         this.terminated = true;
      }

      public FailableStream map(FailableFunction mapper) {
         this.assertNotTerminated();
         return new FailableStream(this.stream.map(Failable.asFunction(mapper)));
      }

      public Object reduce(Object identity, BinaryOperator accumulator) {
         this.makeTerminated();
         return this.stream().reduce(identity, accumulator);
      }

      public Stream stream() {
         return this.stream;
      }
   }
}
