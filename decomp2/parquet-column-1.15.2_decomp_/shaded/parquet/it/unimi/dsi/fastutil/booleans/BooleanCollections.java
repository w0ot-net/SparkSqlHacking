package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectArrays;

public final class BooleanCollections {
   private BooleanCollections() {
   }

   public static BooleanCollection synchronize(BooleanCollection c) {
      return new SynchronizedCollection(c);
   }

   public static BooleanCollection synchronize(BooleanCollection c, Object sync) {
      return new SynchronizedCollection(c, sync);
   }

   public static BooleanCollection unmodifiable(BooleanCollection c) {
      return new UnmodifiableCollection(c);
   }

   public static BooleanCollection asCollection(BooleanIterable iterable) {
      return (BooleanCollection)(iterable instanceof BooleanCollection ? (BooleanCollection)iterable : new IterableCollection(iterable));
   }

   public abstract static class EmptyCollection extends AbstractBooleanCollection {
      protected EmptyCollection() {
      }

      public boolean contains(boolean k) {
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

      public BooleanBidirectionalIterator iterator() {
         return BooleanIterators.EMPTY_ITERATOR;
      }

      public BooleanSpliterator spliterator() {
         return BooleanSpliterators.EMPTY_SPLITERATOR;
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

      public boolean[] toBooleanArray() {
         return BooleanArrays.EMPTY_ARRAY;
      }

      /** @deprecated */
      @Deprecated
      public boolean[] toBooleanArray(boolean[] a) {
         return a;
      }

      public void forEach(BooleanConsumer action) {
      }

      public boolean containsAll(BooleanCollection c) {
         return c.isEmpty();
      }

      public boolean addAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(BooleanPredicate filter) {
         Objects.requireNonNull(filter);
         return false;
      }
   }

   static class SynchronizedCollection implements BooleanCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanCollection collection;
      protected final Object sync;

      protected SynchronizedCollection(BooleanCollection c, Object sync) {
         this.collection = (BooleanCollection)Objects.requireNonNull(c);
         this.sync = sync;
      }

      protected SynchronizedCollection(BooleanCollection c) {
         this.collection = (BooleanCollection)Objects.requireNonNull(c);
         this.sync = this;
      }

      public boolean add(boolean k) {
         synchronized(this.sync) {
            return this.collection.add(k);
         }
      }

      public boolean contains(boolean k) {
         synchronized(this.sync) {
            return this.collection.contains(k);
         }
      }

      public boolean rem(boolean k) {
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

      public boolean[] toBooleanArray() {
         synchronized(this.sync) {
            return this.collection.toBooleanArray();
         }
      }

      public Object[] toArray() {
         synchronized(this.sync) {
            return this.collection.toArray();
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean[] toBooleanArray(boolean[] a) {
         return this.toArray(a);
      }

      public boolean[] toArray(boolean[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public boolean addAll(BooleanCollection c) {
         synchronized(this.sync) {
            return this.collection.addAll(c);
         }
      }

      public boolean containsAll(BooleanCollection c) {
         synchronized(this.sync) {
            return this.collection.containsAll(c);
         }
      }

      public boolean removeAll(BooleanCollection c) {
         synchronized(this.sync) {
            return this.collection.removeAll(c);
         }
      }

      public boolean retainAll(BooleanCollection c) {
         synchronized(this.sync) {
            return this.collection.retainAll(c);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Boolean k) {
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

      public Object[] toArray(Object[] a) {
         synchronized(this.sync) {
            return this.collection.toArray(a);
         }
      }

      public BooleanIterator iterator() {
         return this.collection.iterator();
      }

      public BooleanSpliterator spliterator() {
         return this.collection.spliterator();
      }

      public Stream stream() {
         return this.collection.stream();
      }

      public Stream parallelStream() {
         return this.collection.parallelStream();
      }

      public void forEach(BooleanConsumer action) {
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

      public boolean removeIf(BooleanPredicate filter) {
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

   static class UnmodifiableCollection implements BooleanCollection, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanCollection collection;

      protected UnmodifiableCollection(BooleanCollection c) {
         this.collection = (BooleanCollection)Objects.requireNonNull(c);
      }

      public boolean add(boolean k) {
         throw new UnsupportedOperationException();
      }

      public boolean rem(boolean k) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.collection.size();
      }

      public boolean isEmpty() {
         return this.collection.isEmpty();
      }

      public boolean contains(boolean o) {
         return this.collection.contains(o);
      }

      public BooleanIterator iterator() {
         return BooleanIterators.unmodifiable(this.collection.iterator());
      }

      public BooleanSpliterator spliterator() {
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

      public void forEach(BooleanConsumer action) {
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

      public boolean removeIf(BooleanPredicate filter) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Boolean k) {
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

      public boolean[] toBooleanArray() {
         return this.collection.toBooleanArray();
      }

      /** @deprecated */
      @Deprecated
      public boolean[] toBooleanArray(boolean[] a) {
         return this.toArray(a);
      }

      public boolean[] toArray(boolean[] a) {
         return this.collection.toArray(a);
      }

      public boolean containsAll(BooleanCollection c) {
         return this.collection.containsAll(c);
      }

      public boolean addAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(BooleanCollection c) {
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

   public static class IterableCollection extends AbstractBooleanCollection implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanIterable iterable;

      protected IterableCollection(BooleanIterable iterable) {
         this.iterable = (BooleanIterable)Objects.requireNonNull(iterable);
      }

      public int size() {
         long size = this.iterable.spliterator().getExactSizeIfKnown();
         if (size >= 0L) {
            return (int)Math.min(2147483647L, size);
         } else {
            int c = 0;

            for(BooleanIterator iterator = this.iterator(); iterator.hasNext(); ++c) {
               iterator.nextBoolean();
            }

            return c;
         }
      }

      public boolean isEmpty() {
         return !this.iterable.iterator().hasNext();
      }

      public BooleanIterator iterator() {
         return this.iterable.iterator();
      }

      public BooleanSpliterator spliterator() {
         return this.iterable.spliterator();
      }
   }
}
