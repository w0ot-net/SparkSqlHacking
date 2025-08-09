package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectArrays;

public final class DoubleCollections {
   private DoubleCollections() {
   }

   public static DoubleCollection synchronize(DoubleCollection c) {
      return new SynchronizedCollection(c);
   }

   public static DoubleCollection synchronize(DoubleCollection c, Object sync) {
      return new SynchronizedCollection(c, sync);
   }

   public static DoubleCollection unmodifiable(DoubleCollection c) {
      return new UnmodifiableCollection(c);
   }

   public static DoubleCollection asCollection(DoubleIterable iterable) {
      return (DoubleCollection)(iterable instanceof DoubleCollection ? (DoubleCollection)iterable : new IterableCollection(iterable));
   }

   public abstract static class EmptyCollection extends AbstractDoubleCollection {
      protected EmptyCollection() {
      }

      public boolean contains(double k) {
         return false;
      }

      public Object[] toArray() {
         return ObjectArrays.EMPTY_ARRAY;
      }

      public Object[] toArray(Object[] array) {
         if (array.length > 0) {
            array[0] = null;
         }

         return array;
      }

      public DoubleBidirectionalIterator iterator() {
         return DoubleIterators.EMPTY_ITERATOR;
      }

      public DoubleSpliterator spliterator() {
         return DoubleSpliterators.EMPTY_SPLITERATOR;
      }

      public int size() {
         return 0;
      }

      public void clear() {
      }

      public int hashCode() {
         return 0;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return !(o instanceof Collection) ? false : ((Collection)o).isEmpty();
         }
      }

      /** @deprecated */
      @Deprecated
      public void forEach(Consumer action) {
      }

      public boolean containsAll(Collection c) {
         return c.isEmpty();
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean removeIf(Predicate filter) {
         Objects.requireNonNull(filter);
         return false;
      }

      public double[] toDoubleArray() {
         return DoubleArrays.EMPTY_ARRAY;
      }

      /** @deprecated */
      @Deprecated
      public double[] toDoubleArray(double[] a) {
         return a;
      }

      public void forEach(java.util.function.DoubleConsumer action) {
      }

      public boolean containsAll(DoubleCollection c) {
         return c.isEmpty();
      }

      public boolean addAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(java.util.function.DoublePredicate filter) {
         Objects.requireNonNull(filter);
         return false;
      }
   }

   static class SynchronizedCollection implements DoubleCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleCollection collection;
      protected final Object sync;

      protected SynchronizedCollection(DoubleCollection c, Object sync) {
         this.collection = (DoubleCollection)Objects.requireNonNull(c);
         this.sync = sync;
      }

      protected SynchronizedCollection(DoubleCollection c) {
         this.collection = (DoubleCollection)Objects.requireNonNull(c);
         this.sync = this;
      }

      public boolean add(double k) {
         synchronized(this.sync) {
            return this.collection.add(k);
         }
      }

      public boolean contains(double k) {
         synchronized(this.sync) {
            return this.collection.contains(k);
         }
      }

      public boolean rem(double k) {
         synchronized(this.sync) {
            return this.collection.rem(k);
         }
      }

      public int size() {
         synchronized(this.sync) {
            return this.collection.size();
         }
      }

      public boolean isEmpty() {
         synchronized(this.sync) {
            return this.collection.isEmpty();
         }
      }

      public double[] toDoubleArray() {
         synchronized(this.sync) {
            return this.collection.toDoubleArray();
         }
      }

      public Object[] toArray() {
         synchronized(this.sync) {
            return this.collection.toArray();
         }
      }

      /** @deprecated */
      @Deprecated
      public double[] toDoubleArray(double[] a) {
         return this.toArray(a);
      }

      public double[] toArray(double[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public boolean addAll(DoubleCollection c) {
         synchronized(this.sync) {
            return this.collection.addAll(c);
         }
      }

      public boolean containsAll(DoubleCollection c) {
         synchronized(this.sync) {
            return this.collection.containsAll(c);
         }
      }

      public boolean removeAll(DoubleCollection c) {
         synchronized(this.sync) {
            return this.collection.removeAll(c);
         }
      }

      public boolean retainAll(DoubleCollection c) {
         synchronized(this.sync) {
            return this.collection.retainAll(c);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Double k) {
         synchronized(this.sync) {
            return this.collection.add(k);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean contains(Object k) {
         synchronized(this.sync) {
            return this.collection.contains(k);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean remove(Object k) {
         synchronized(this.sync) {
            return this.collection.remove(k);
         }
      }

      public DoubleIterator doubleIterator() {
         return this.collection.doubleIterator();
      }

      public DoubleSpliterator doubleSpliterator() {
         return this.collection.doubleSpliterator();
      }

      public DoubleStream doubleStream() {
         return this.collection.doubleStream();
      }

      public DoubleStream doubleParallelStream() {
         return this.collection.doubleParallelStream();
      }

      public Object[] toArray(Object[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public DoubleIterator iterator() {
         return this.collection.iterator();
      }

      public DoubleSpliterator spliterator() {
         return this.collection.spliterator();
      }

      /** @deprecated */
      @Deprecated
      public Stream stream() {
         return this.collection.stream();
      }

      /** @deprecated */
      @Deprecated
      public Stream parallelStream() {
         return this.collection.parallelStream();
      }

      public void forEach(java.util.function.DoubleConsumer action) {
         synchronized(this.sync) {
            this.collection.forEach(action);
         }
      }

      public boolean addAll(Collection c) {
         synchronized(this.sync) {
            return this.collection.addAll(c);
         }
      }

      public boolean containsAll(Collection c) {
         synchronized(this.sync) {
            return this.collection.containsAll(c);
         }
      }

      public boolean removeAll(Collection c) {
         synchronized(this.sync) {
            return this.collection.removeAll(c);
         }
      }

      public boolean retainAll(Collection c) {
         synchronized(this.sync) {
            return this.collection.retainAll(c);
         }
      }

      public boolean removeIf(java.util.function.DoublePredicate filter) {
         synchronized(this.sync) {
            return this.collection.removeIf(filter);
         }
      }

      public void clear() {
         synchronized(this.sync) {
            this.collection.clear();
         }
      }

      public String toString() {
         synchronized(this.sync) {
            return this.collection.toString();
         }
      }

      public int hashCode() {
         synchronized(this.sync) {
            return this.collection.hashCode();
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.sync) {
               return this.collection.equals(o);
            }
         }
      }

      private void writeObject(ObjectOutputStream s) throws IOException {
         synchronized(this.sync) {
            s.defaultWriteObject();
         }
      }
   }

   static class UnmodifiableCollection implements DoubleCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleCollection collection;

      protected UnmodifiableCollection(DoubleCollection c) {
         this.collection = (DoubleCollection)Objects.requireNonNull(c);
      }

      public boolean add(double k) {
         throw new UnsupportedOperationException();
      }

      public boolean rem(double k) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.collection.size();
      }

      public boolean isEmpty() {
         return this.collection.isEmpty();
      }

      public boolean contains(double o) {
         return this.collection.contains(o);
      }

      public DoubleIterator iterator() {
         return DoubleIterators.unmodifiable(this.collection.iterator());
      }

      public DoubleSpliterator spliterator() {
         return this.collection.spliterator();
      }

      /** @deprecated */
      @Deprecated
      public Stream stream() {
         return this.collection.stream();
      }

      /** @deprecated */
      @Deprecated
      public Stream parallelStream() {
         return this.collection.parallelStream();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public Object[] toArray(Object[] a) {
         return this.collection.toArray(a);
      }

      public Object[] toArray() {
         return this.collection.toArray();
      }

      public void forEach(java.util.function.DoubleConsumer action) {
         this.collection.forEach(action);
      }

      public boolean containsAll(Collection c) {
         return this.collection.containsAll(c);
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(java.util.function.DoublePredicate filter) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Double k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean contains(Object k) {
         return this.collection.contains(k);
      }

      /** @deprecated */
      @Deprecated
      public boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      public double[] toDoubleArray() {
         return this.collection.toDoubleArray();
      }

      /** @deprecated */
      @Deprecated
      public double[] toDoubleArray(double[] a) {
         return this.toArray(a);
      }

      public double[] toArray(double[] a) {
         return this.collection.toArray(a);
      }

      public boolean containsAll(DoubleCollection c) {
         return this.collection.containsAll(c);
      }

      public boolean addAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(DoubleCollection c) {
         throw new UnsupportedOperationException();
      }

      public DoubleIterator doubleIterator() {
         return this.collection.doubleIterator();
      }

      public DoubleSpliterator doubleSpliterator() {
         return this.collection.doubleSpliterator();
      }

      public DoubleStream doubleStream() {
         return this.collection.doubleStream();
      }

      public DoubleStream doubleParallelStream() {
         return this.collection.doubleParallelStream();
      }

      public String toString() {
         return this.collection.toString();
      }

      public int hashCode() {
         return this.collection.hashCode();
      }

      public boolean equals(Object o) {
         return o == this ? true : this.collection.equals(o);
      }
   }

   public static class IterableCollection extends AbstractDoubleCollection implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleIterable iterable;

      protected IterableCollection(DoubleIterable iterable) {
         this.iterable = (DoubleIterable)Objects.requireNonNull(iterable);
      }

      public int size() {
         long size = this.iterable.spliterator().getExactSizeIfKnown();
         if (size >= 0L) {
            return (int)Math.min(2147483647L, size);
         } else {
            int c = 0;

            for(DoubleIterator iterator = this.iterator(); iterator.hasNext(); ++c) {
               iterator.nextDouble();
            }

            return c;
         }
      }

      public boolean isEmpty() {
         return !this.iterable.iterator().hasNext();
      }

      public DoubleIterator iterator() {
         return this.iterable.iterator();
      }

      public DoubleSpliterator spliterator() {
         return this.iterable.spliterator();
      }

      public DoubleIterator doubleIterator() {
         return this.iterable.doubleIterator();
      }

      public DoubleSpliterator doubleSpliterator() {
         return this.iterable.doubleSpliterator();
      }
   }

   static class SizeDecreasingSupplier implements Supplier {
      static final int RECOMMENDED_MIN_SIZE = 8;
      final AtomicInteger suppliedCount = new AtomicInteger(0);
      final int expectedFinalSize;
      final IntFunction builder;

      SizeDecreasingSupplier(int expectedFinalSize, IntFunction builder) {
         this.expectedFinalSize = expectedFinalSize;
         this.builder = builder;
      }

      public DoubleCollection get() {
         int expectedNeededNextSize = 1 + (this.expectedFinalSize - 1) / this.suppliedCount.incrementAndGet();
         if (expectedNeededNextSize < 0) {
            expectedNeededNextSize = 8;
         }

         return (DoubleCollection)this.builder.apply(expectedNeededNextSize);
      }
   }
}
