package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterators;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterators;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectArrays;

public final class FloatCollections {
   private FloatCollections() {
   }

   public static FloatCollection synchronize(FloatCollection c) {
      return new SynchronizedCollection(c);
   }

   public static FloatCollection synchronize(FloatCollection c, Object sync) {
      return new SynchronizedCollection(c, sync);
   }

   public static FloatCollection unmodifiable(FloatCollection c) {
      return new UnmodifiableCollection(c);
   }

   public static FloatCollection asCollection(FloatIterable iterable) {
      return (FloatCollection)(iterable instanceof FloatCollection ? (FloatCollection)iterable : new IterableCollection(iterable));
   }

   public abstract static class EmptyCollection extends AbstractFloatCollection {
      protected EmptyCollection() {
      }

      public boolean contains(float k) {
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

      public FloatBidirectionalIterator iterator() {
         return FloatIterators.EMPTY_ITERATOR;
      }

      public FloatSpliterator spliterator() {
         return FloatSpliterators.EMPTY_SPLITERATOR;
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

      public float[] toFloatArray() {
         return FloatArrays.EMPTY_ARRAY;
      }

      /** @deprecated */
      @Deprecated
      public float[] toFloatArray(float[] a) {
         return a;
      }

      public void forEach(FloatConsumer action) {
      }

      public boolean containsAll(FloatCollection c) {
         return c.isEmpty();
      }

      public boolean addAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(FloatPredicate filter) {
         Objects.requireNonNull(filter);
         return false;
      }

      public DoubleIterator doubleIterator() {
         return DoubleIterators.EMPTY_ITERATOR;
      }

      public DoubleSpliterator doubleSpliterator() {
         return DoubleSpliterators.EMPTY_SPLITERATOR;
      }
   }

   static class SynchronizedCollection implements FloatCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatCollection collection;
      protected final Object sync;

      protected SynchronizedCollection(FloatCollection c, Object sync) {
         this.collection = (FloatCollection)Objects.requireNonNull(c);
         this.sync = sync;
      }

      protected SynchronizedCollection(FloatCollection c) {
         this.collection = (FloatCollection)Objects.requireNonNull(c);
         this.sync = this;
      }

      public boolean add(float k) {
         synchronized(this.sync) {
            return this.collection.add(k);
         }
      }

      public boolean contains(float k) {
         synchronized(this.sync) {
            return this.collection.contains(k);
         }
      }

      public boolean rem(float k) {
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

      public float[] toFloatArray() {
         synchronized(this.sync) {
            return this.collection.toFloatArray();
         }
      }

      public Object[] toArray() {
         synchronized(this.sync) {
            return this.collection.toArray();
         }
      }

      /** @deprecated */
      @Deprecated
      public float[] toFloatArray(float[] a) {
         return this.toArray(a);
      }

      public float[] toArray(float[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public boolean addAll(FloatCollection c) {
         synchronized(this.sync) {
            return this.collection.addAll(c);
         }
      }

      public boolean containsAll(FloatCollection c) {
         synchronized(this.sync) {
            return this.collection.containsAll(c);
         }
      }

      public boolean removeAll(FloatCollection c) {
         synchronized(this.sync) {
            return this.collection.removeAll(c);
         }
      }

      public boolean retainAll(FloatCollection c) {
         synchronized(this.sync) {
            return this.collection.retainAll(c);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Float k) {
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

      public FloatIterator iterator() {
         return this.collection.iterator();
      }

      public FloatSpliterator spliterator() {
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

      public void forEach(FloatConsumer action) {
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

      public boolean removeIf(FloatPredicate filter) {
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

   static class UnmodifiableCollection implements FloatCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatCollection collection;

      protected UnmodifiableCollection(FloatCollection c) {
         this.collection = (FloatCollection)Objects.requireNonNull(c);
      }

      public boolean add(float k) {
         throw new UnsupportedOperationException();
      }

      public boolean rem(float k) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.collection.size();
      }

      public boolean isEmpty() {
         return this.collection.isEmpty();
      }

      public boolean contains(float o) {
         return this.collection.contains(o);
      }

      public FloatIterator iterator() {
         return FloatIterators.unmodifiable(this.collection.iterator());
      }

      public FloatSpliterator spliterator() {
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

      public void forEach(FloatConsumer action) {
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

      public boolean removeIf(FloatPredicate filter) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Float k) {
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

      public float[] toFloatArray() {
         return this.collection.toFloatArray();
      }

      /** @deprecated */
      @Deprecated
      public float[] toFloatArray(float[] a) {
         return this.toArray(a);
      }

      public float[] toArray(float[] a) {
         return this.collection.toArray(a);
      }

      public boolean containsAll(FloatCollection c) {
         return this.collection.containsAll(c);
      }

      public boolean addAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(FloatCollection c) {
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

   public static class IterableCollection extends AbstractFloatCollection implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatIterable iterable;

      protected IterableCollection(FloatIterable iterable) {
         this.iterable = (FloatIterable)Objects.requireNonNull(iterable);
      }

      public int size() {
         long size = this.iterable.spliterator().getExactSizeIfKnown();
         if (size >= 0L) {
            return (int)Math.min(2147483647L, size);
         } else {
            int c = 0;

            for(FloatIterator iterator = this.iterator(); iterator.hasNext(); ++c) {
               iterator.nextFloat();
            }

            return c;
         }
      }

      public boolean isEmpty() {
         return !this.iterable.iterator().hasNext();
      }

      public FloatIterator iterator() {
         return this.iterable.iterator();
      }

      public FloatSpliterator spliterator() {
         return this.iterable.spliterator();
      }

      public DoubleIterator doubleIterator() {
         return this.iterable.doubleIterator();
      }

      public DoubleSpliterator doubleSpliterator() {
         return this.iterable.doubleSpliterator();
      }
   }
}
