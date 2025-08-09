package shaded.parquet.it.unimi.dsi.fastutil.objects;

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
import java.util.stream.Stream;

public final class ObjectCollections {
   private ObjectCollections() {
   }

   public static ObjectCollection synchronize(ObjectCollection c) {
      return new SynchronizedCollection(c);
   }

   public static ObjectCollection synchronize(ObjectCollection c, Object sync) {
      return new SynchronizedCollection(c, sync);
   }

   public static ObjectCollection unmodifiable(ObjectCollection c) {
      return new UnmodifiableCollection(c);
   }

   public static ObjectCollection asCollection(ObjectIterable iterable) {
      return (ObjectCollection)(iterable instanceof ObjectCollection ? (ObjectCollection)iterable : new IterableCollection(iterable));
   }

   public abstract static class EmptyCollection extends AbstractObjectCollection {
      protected EmptyCollection() {
      }

      public boolean contains(Object k) {
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

      public ObjectBidirectionalIterator iterator() {
         return ObjectIterators.EMPTY_ITERATOR;
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.EMPTY_SPLITERATOR;
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

      public boolean removeIf(Predicate filter) {
         Objects.requireNonNull(filter);
         return false;
      }
   }

   static class SynchronizedCollection implements ObjectCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final ObjectCollection collection;
      protected final Object sync;

      protected SynchronizedCollection(ObjectCollection c, Object sync) {
         this.collection = (ObjectCollection)Objects.requireNonNull(c);
         this.sync = sync;
      }

      protected SynchronizedCollection(ObjectCollection c) {
         this.collection = (ObjectCollection)Objects.requireNonNull(c);
         this.sync = this;
      }

      public boolean add(Object k) {
         synchronized(this.sync) {
            return this.collection.add(k);
         }
      }

      public boolean contains(Object k) {
         synchronized(this.sync) {
            return this.collection.contains(k);
         }
      }

      public boolean remove(Object k) {
         synchronized(this.sync) {
            return this.collection.remove(k);
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

      public Object[] toArray() {
         synchronized(this.sync) {
            return this.collection.toArray();
         }
      }

      public Object[] toArray(Object[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public ObjectIterator iterator() {
         return this.collection.iterator();
      }

      public ObjectSpliterator spliterator() {
         return this.collection.spliterator();
      }

      public Stream stream() {
         return this.collection.stream();
      }

      public Stream parallelStream() {
         return this.collection.parallelStream();
      }

      public void forEach(Consumer action) {
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

      public boolean removeIf(Predicate filter) {
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

   static class UnmodifiableCollection implements ObjectCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final ObjectCollection collection;

      protected UnmodifiableCollection(ObjectCollection c) {
         this.collection = (ObjectCollection)Objects.requireNonNull(c);
      }

      public boolean add(Object k) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.collection.size();
      }

      public boolean isEmpty() {
         return this.collection.isEmpty();
      }

      public boolean contains(Object o) {
         return this.collection.contains(o);
      }

      public ObjectIterator iterator() {
         return ObjectIterators.unmodifiable(this.collection.iterator());
      }

      public ObjectSpliterator spliterator() {
         return this.collection.spliterator();
      }

      public Stream stream() {
         return this.collection.stream();
      }

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

      public void forEach(Consumer action) {
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

      public boolean removeIf(Predicate filter) {
         throw new UnsupportedOperationException();
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

   public static class IterableCollection extends AbstractObjectCollection implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final ObjectIterable iterable;

      protected IterableCollection(ObjectIterable iterable) {
         this.iterable = (ObjectIterable)Objects.requireNonNull(iterable);
      }

      public int size() {
         long size = this.iterable.spliterator().getExactSizeIfKnown();
         if (size >= 0L) {
            return (int)Math.min(2147483647L, size);
         } else {
            int c = 0;

            for(ObjectIterator<K> iterator = this.iterator(); iterator.hasNext(); ++c) {
               iterator.next();
            }

            return c;
         }
      }

      public boolean isEmpty() {
         return !this.iterable.iterator().hasNext();
      }

      public ObjectIterator iterator() {
         return this.iterable.iterator();
      }

      public ObjectSpliterator spliterator() {
         return this.iterable.spliterator();
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

      public ObjectCollection get() {
         int expectedNeededNextSize = 1 + (this.expectedFinalSize - 1) / this.suppliedCount.incrementAndGet();
         if (expectedNeededNextSize < 0) {
            expectedNeededNextSize = 8;
         }

         return (ObjectCollection)this.builder.apply(expectedNeededNextSize);
      }
   }
}
