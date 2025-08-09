package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/** @deprecated */
@Deprecated
public class Streams {
   public static FailableStream stream(Collection stream) {
      return stream(stream.stream());
   }

   public static FailableStream stream(Stream stream) {
      return new FailableStream(stream);
   }

   public static Collector toArray(Class pElementType) {
      return new ArrayCollector(pElementType);
   }

   /** @deprecated */
   @Deprecated
   public static class ArrayCollector implements Collector {
      private static final Set characteristics = Collections.emptySet();
      private final Class elementType;

      public ArrayCollector(Class elementType) {
         this.elementType = elementType;
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

   /** @deprecated */
   @Deprecated
   public static class FailableStream {
      private Stream stream;
      private boolean terminated;

      public FailableStream(Stream stream) {
         this.stream = stream;
      }

      public boolean allMatch(Functions.FailablePredicate predicate) {
         this.assertNotTerminated();
         return this.stream().allMatch(Functions.asPredicate(predicate));
      }

      public boolean anyMatch(Functions.FailablePredicate predicate) {
         this.assertNotTerminated();
         return this.stream().anyMatch(Functions.asPredicate(predicate));
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

      public FailableStream filter(Functions.FailablePredicate predicate) {
         this.assertNotTerminated();
         this.stream = this.stream.filter(Functions.asPredicate(predicate));
         return this;
      }

      public void forEach(Functions.FailableConsumer action) {
         this.makeTerminated();
         this.stream().forEach(Functions.asConsumer(action));
      }

      protected void makeTerminated() {
         this.assertNotTerminated();
         this.terminated = true;
      }

      public FailableStream map(Functions.FailableFunction mapper) {
         this.assertNotTerminated();
         return new FailableStream(this.stream.map(Functions.asFunction(mapper)));
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
