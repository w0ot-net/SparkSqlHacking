package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Optional;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Predicate;
import org.sparkproject.guava.base.Predicates;
import org.sparkproject.guava.primitives.Ints;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Iterators {
   private Iterators() {
   }

   static UnmodifiableIterator emptyIterator() {
      return emptyListIterator();
   }

   static UnmodifiableListIterator emptyListIterator() {
      return Iterators.ArrayItr.EMPTY;
   }

   static Iterator emptyModifiableIterator() {
      return Iterators.EmptyModifiableIterator.INSTANCE;
   }

   public static UnmodifiableIterator unmodifiableIterator(final Iterator iterator) {
      Preconditions.checkNotNull(iterator);
      if (iterator instanceof UnmodifiableIterator) {
         UnmodifiableIterator<T> result = (UnmodifiableIterator)iterator;
         return result;
      } else {
         return new UnmodifiableIterator() {
            public boolean hasNext() {
               return iterator.hasNext();
            }

            @ParametricNullness
            public Object next() {
               return iterator.next();
            }
         };
      }
   }

   /** @deprecated */
   @Deprecated
   public static UnmodifiableIterator unmodifiableIterator(UnmodifiableIterator iterator) {
      return (UnmodifiableIterator)Preconditions.checkNotNull(iterator);
   }

   public static int size(Iterator iterator) {
      long count;
      for(count = 0L; iterator.hasNext(); ++count) {
         iterator.next();
      }

      return Ints.saturatedCast(count);
   }

   public static boolean contains(Iterator iterator, @CheckForNull Object element) {
      if (element == null) {
         while(iterator.hasNext()) {
            if (iterator.next() == null) {
               return true;
            }
         }
      } else {
         while(iterator.hasNext()) {
            if (element.equals(iterator.next())) {
               return true;
            }
         }
      }

      return false;
   }

   @CanIgnoreReturnValue
   public static boolean removeAll(Iterator removeFrom, Collection elementsToRemove) {
      Preconditions.checkNotNull(elementsToRemove);
      boolean result = false;

      while(removeFrom.hasNext()) {
         if (elementsToRemove.contains(removeFrom.next())) {
            removeFrom.remove();
            result = true;
         }
      }

      return result;
   }

   @CanIgnoreReturnValue
   public static boolean removeIf(Iterator removeFrom, Predicate predicate) {
      Preconditions.checkNotNull(predicate);
      boolean modified = false;

      while(removeFrom.hasNext()) {
         if (predicate.apply(removeFrom.next())) {
            removeFrom.remove();
            modified = true;
         }
      }

      return modified;
   }

   @CanIgnoreReturnValue
   public static boolean retainAll(Iterator removeFrom, Collection elementsToRetain) {
      Preconditions.checkNotNull(elementsToRetain);
      boolean result = false;

      while(removeFrom.hasNext()) {
         if (!elementsToRetain.contains(removeFrom.next())) {
            removeFrom.remove();
            result = true;
         }
      }

      return result;
   }

   public static boolean elementsEqual(Iterator iterator1, Iterator iterator2) {
      while(true) {
         if (iterator1.hasNext()) {
            if (!iterator2.hasNext()) {
               return false;
            }

            Object o1 = iterator1.next();
            Object o2 = iterator2.next();
            if (Objects.equal(o1, o2)) {
               continue;
            }

            return false;
         }

         return !iterator2.hasNext();
      }
   }

   public static String toString(Iterator iterator) {
      StringBuilder sb = (new StringBuilder()).append('[');
      boolean first = true;

      while(iterator.hasNext()) {
         if (!first) {
            sb.append(", ");
         }

         first = false;
         sb.append(iterator.next());
      }

      return sb.append(']').toString();
   }

   @ParametricNullness
   public static Object getOnlyElement(Iterator iterator) {
      T first = (T)iterator.next();
      if (!iterator.hasNext()) {
         return first;
      } else {
         StringBuilder sb = (new StringBuilder()).append("expected one element but was: <").append(first);

         for(int i = 0; i < 4 && iterator.hasNext(); ++i) {
            sb.append(", ").append(iterator.next());
         }

         if (iterator.hasNext()) {
            sb.append(", ...");
         }

         sb.append('>');
         throw new IllegalArgumentException(sb.toString());
      }
   }

   @ParametricNullness
   public static Object getOnlyElement(Iterator iterator, @ParametricNullness Object defaultValue) {
      return iterator.hasNext() ? getOnlyElement(iterator) : defaultValue;
   }

   @GwtIncompatible
   public static Object[] toArray(Iterator iterator, Class type) {
      List<T> list = Lists.newArrayList(iterator);
      return Iterables.toArray(list, (Class)type);
   }

   @CanIgnoreReturnValue
   public static boolean addAll(Collection addTo, Iterator iterator) {
      Preconditions.checkNotNull(addTo);
      Preconditions.checkNotNull(iterator);

      boolean wasModified;
      for(wasModified = false; iterator.hasNext(); wasModified |= addTo.add(iterator.next())) {
      }

      return wasModified;
   }

   public static int frequency(Iterator iterator, @CheckForNull Object element) {
      int count;
      for(count = 0; contains(iterator, element); ++count) {
      }

      return count;
   }

   public static Iterator cycle(final Iterable iterable) {
      Preconditions.checkNotNull(iterable);
      return new Iterator() {
         Iterator iterator = Iterators.emptyModifiableIterator();

         public boolean hasNext() {
            return this.iterator.hasNext() || iterable.iterator().hasNext();
         }

         @ParametricNullness
         public Object next() {
            if (!this.iterator.hasNext()) {
               this.iterator = iterable.iterator();
               if (!this.iterator.hasNext()) {
                  throw new NoSuchElementException();
               }
            }

            return this.iterator.next();
         }

         public void remove() {
            this.iterator.remove();
         }
      };
   }

   @SafeVarargs
   public static Iterator cycle(Object... elements) {
      return cycle((Iterable)Lists.newArrayList(elements));
   }

   private static Iterator consumingForArray(final Iterator... elements) {
      return new UnmodifiableIterator() {
         int index = 0;

         public boolean hasNext() {
            return this.index < elements.length;
         }

         public Iterator next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               I result = (I)((Iterator)java.util.Objects.requireNonNull(elements[this.index]));
               elements[this.index] = null;
               ++this.index;
               return result;
            }
         }
      };
   }

   public static Iterator concat(Iterator a, Iterator b) {
      Preconditions.checkNotNull(a);
      Preconditions.checkNotNull(b);
      return concat(consumingForArray(a, b));
   }

   public static Iterator concat(Iterator a, Iterator b, Iterator c) {
      Preconditions.checkNotNull(a);
      Preconditions.checkNotNull(b);
      Preconditions.checkNotNull(c);
      return concat(consumingForArray(a, b, c));
   }

   public static Iterator concat(Iterator a, Iterator b, Iterator c, Iterator d) {
      Preconditions.checkNotNull(a);
      Preconditions.checkNotNull(b);
      Preconditions.checkNotNull(c);
      Preconditions.checkNotNull(d);
      return concat(consumingForArray(a, b, c, d));
   }

   @SafeVarargs
   public static Iterator concat(Iterator... inputs) {
      return concatNoDefensiveCopy((Iterator[])Arrays.copyOf(inputs, inputs.length));
   }

   public static Iterator concat(Iterator inputs) {
      return new ConcatenatedIterator(inputs);
   }

   static Iterator concatNoDefensiveCopy(Iterator... inputs) {
      for(Iterator input : (Iterator[])Preconditions.checkNotNull(inputs)) {
         Preconditions.checkNotNull(input);
      }

      return concat(consumingForArray(inputs));
   }

   public static UnmodifiableIterator partition(Iterator iterator, int size) {
      return partitionImpl(iterator, size, false);
   }

   public static UnmodifiableIterator paddedPartition(Iterator iterator, int size) {
      return partitionImpl(iterator, size, true);
   }

   private static UnmodifiableIterator partitionImpl(final Iterator iterator, final int size, final boolean pad) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkArgument(size > 0);
      return new UnmodifiableIterator() {
         public boolean hasNext() {
            return iterator.hasNext();
         }

         public List next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               T[] array = (T[])(new Object[size]);

               int count;
               for(count = 0; count < size && iterator.hasNext(); ++count) {
                  array[count] = iterator.next();
               }

               for(int i = count; i < size; ++i) {
                  array[i] = null;
               }

               List<T> list = Collections.unmodifiableList(Arrays.asList(array));
               return !pad && count != size ? list.subList(0, count) : list;
            }
         }
      };
   }

   public static UnmodifiableIterator filter(final Iterator unfiltered, final Predicate retainIfTrue) {
      Preconditions.checkNotNull(unfiltered);
      Preconditions.checkNotNull(retainIfTrue);
      return new AbstractIterator() {
         @CheckForNull
         protected Object computeNext() {
            while(true) {
               if (unfiltered.hasNext()) {
                  T element = (T)unfiltered.next();
                  if (!retainIfTrue.apply(element)) {
                     continue;
                  }

                  return element;
               }

               return this.endOfData();
            }
         }
      };
   }

   @GwtIncompatible
   public static UnmodifiableIterator filter(Iterator unfiltered, Class desiredType) {
      return filter(unfiltered, Predicates.instanceOf(desiredType));
   }

   public static boolean any(Iterator iterator, Predicate predicate) {
      return indexOf(iterator, predicate) != -1;
   }

   public static boolean all(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(predicate);

      while(iterator.hasNext()) {
         T element = (T)iterator.next();
         if (!predicate.apply(element)) {
            return false;
         }
      }

      return true;
   }

   @ParametricNullness
   public static Object find(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkNotNull(predicate);

      while(iterator.hasNext()) {
         T t = (T)iterator.next();
         if (predicate.apply(t)) {
            return t;
         }
      }

      throw new NoSuchElementException();
   }

   @CheckForNull
   public static Object find(Iterator iterator, Predicate predicate, @CheckForNull Object defaultValue) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkNotNull(predicate);

      while(iterator.hasNext()) {
         T t = (T)iterator.next();
         if (predicate.apply(t)) {
            return t;
         }
      }

      return defaultValue;
   }

   public static Optional tryFind(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkNotNull(predicate);

      while(iterator.hasNext()) {
         T t = (T)iterator.next();
         if (predicate.apply(t)) {
            return Optional.of(t);
         }
      }

      return Optional.absent();
   }

   public static int indexOf(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(predicate, "predicate");

      for(int i = 0; iterator.hasNext(); ++i) {
         T current = (T)iterator.next();
         if (predicate.apply(current)) {
            return i;
         }
      }

      return -1;
   }

   public static Iterator transform(Iterator fromIterator, final Function function) {
      Preconditions.checkNotNull(function);
      return new TransformedIterator(fromIterator) {
         @ParametricNullness
         Object transform(@ParametricNullness Object from) {
            return function.apply(from);
         }
      };
   }

   @ParametricNullness
   public static Object get(Iterator iterator, int position) {
      checkNonnegative(position);
      int skipped = advance(iterator, position);
      if (!iterator.hasNext()) {
         throw new IndexOutOfBoundsException("position (" + position + ") must be less than the number of elements that remained (" + skipped + ")");
      } else {
         return iterator.next();
      }
   }

   @ParametricNullness
   public static Object get(Iterator iterator, int position, @ParametricNullness Object defaultValue) {
      checkNonnegative(position);
      advance(iterator, position);
      return getNext(iterator, defaultValue);
   }

   static void checkNonnegative(int position) {
      if (position < 0) {
         throw new IndexOutOfBoundsException("position (" + position + ") must not be negative");
      }
   }

   @ParametricNullness
   public static Object getNext(Iterator iterator, @ParametricNullness Object defaultValue) {
      return iterator.hasNext() ? iterator.next() : defaultValue;
   }

   @ParametricNullness
   public static Object getLast(Iterator iterator) {
      T current;
      do {
         current = (T)iterator.next();
      } while(iterator.hasNext());

      return current;
   }

   @ParametricNullness
   public static Object getLast(Iterator iterator, @ParametricNullness Object defaultValue) {
      return iterator.hasNext() ? getLast(iterator) : defaultValue;
   }

   @CanIgnoreReturnValue
   public static int advance(Iterator iterator, int numberToAdvance) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkArgument(numberToAdvance >= 0, "numberToAdvance must be nonnegative");

      int i;
      for(i = 0; i < numberToAdvance && iterator.hasNext(); ++i) {
         iterator.next();
      }

      return i;
   }

   public static Iterator limit(final Iterator iterator, final int limitSize) {
      Preconditions.checkNotNull(iterator);
      Preconditions.checkArgument(limitSize >= 0, "limit is negative");
      return new Iterator() {
         private int count;

         public boolean hasNext() {
            return this.count < limitSize && iterator.hasNext();
         }

         @ParametricNullness
         public Object next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               ++this.count;
               return iterator.next();
            }
         }

         public void remove() {
            iterator.remove();
         }
      };
   }

   public static Iterator consumingIterator(final Iterator iterator) {
      Preconditions.checkNotNull(iterator);
      return new UnmodifiableIterator() {
         public boolean hasNext() {
            return iterator.hasNext();
         }

         @ParametricNullness
         public Object next() {
            T next = (T)iterator.next();
            iterator.remove();
            return next;
         }

         public String toString() {
            return "Iterators.consumingIterator(...)";
         }
      };
   }

   @CheckForNull
   static Object pollNext(Iterator iterator) {
      if (iterator.hasNext()) {
         T result = (T)iterator.next();
         iterator.remove();
         return result;
      } else {
         return null;
      }
   }

   static void clear(Iterator iterator) {
      Preconditions.checkNotNull(iterator);

      while(iterator.hasNext()) {
         iterator.next();
         iterator.remove();
      }

   }

   @SafeVarargs
   public static UnmodifiableIterator forArray(Object... array) {
      return forArrayWithPosition(array, 0);
   }

   static UnmodifiableListIterator forArrayWithPosition(Object[] array, int position) {
      if (array.length == 0) {
         Preconditions.checkPositionIndex(position, array.length);
         return emptyListIterator();
      } else {
         return new ArrayItr(array, position);
      }
   }

   public static UnmodifiableIterator singletonIterator(@ParametricNullness Object value) {
      return new SingletonIterator(value);
   }

   public static UnmodifiableIterator forEnumeration(final Enumeration enumeration) {
      Preconditions.checkNotNull(enumeration);
      return new UnmodifiableIterator() {
         public boolean hasNext() {
            return enumeration.hasMoreElements();
         }

         @ParametricNullness
         public Object next() {
            return enumeration.nextElement();
         }
      };
   }

   public static Enumeration asEnumeration(final Iterator iterator) {
      Preconditions.checkNotNull(iterator);
      return new Enumeration() {
         public boolean hasMoreElements() {
            return iterator.hasNext();
         }

         @ParametricNullness
         public Object nextElement() {
            return iterator.next();
         }
      };
   }

   public static PeekingIterator peekingIterator(Iterator iterator) {
      if (iterator instanceof PeekingImpl) {
         PeekingImpl<T> peeking = (PeekingImpl)iterator;
         return peeking;
      } else {
         return new PeekingImpl(iterator);
      }
   }

   /** @deprecated */
   @Deprecated
   public static PeekingIterator peekingIterator(PeekingIterator iterator) {
      return (PeekingIterator)Preconditions.checkNotNull(iterator);
   }

   public static UnmodifiableIterator mergeSorted(Iterable iterators, Comparator comparator) {
      Preconditions.checkNotNull(iterators, "iterators");
      Preconditions.checkNotNull(comparator, "comparator");
      return new MergingIterator(iterators, comparator);
   }

   private static enum EmptyModifiableIterator implements Iterator {
      INSTANCE;

      public boolean hasNext() {
         return false;
      }

      public Object next() {
         throw new NoSuchElementException();
      }

      public void remove() {
         CollectPreconditions.checkRemove(false);
      }

      // $FF: synthetic method
      private static EmptyModifiableIterator[] $values() {
         return new EmptyModifiableIterator[]{INSTANCE};
      }
   }

   private static final class ArrayItr extends AbstractIndexedListIterator {
      static final UnmodifiableListIterator EMPTY = new ArrayItr(new Object[0], 0);
      private final Object[] array;

      ArrayItr(Object[] array, int position) {
         super(array.length, position);
         this.array = array;
      }

      @ParametricNullness
      protected Object get(int index) {
         return this.array[index];
      }
   }

   private static final class SingletonIterator extends UnmodifiableIterator {
      private final Object value;
      private boolean done;

      SingletonIterator(Object value) {
         this.value = value;
      }

      public boolean hasNext() {
         return !this.done;
      }

      @ParametricNullness
      public Object next() {
         if (this.done) {
            throw new NoSuchElementException();
         } else {
            this.done = true;
            return this.value;
         }
      }
   }

   private static class PeekingImpl implements PeekingIterator {
      private final Iterator iterator;
      private boolean hasPeeked;
      @CheckForNull
      private Object peekedElement;

      public PeekingImpl(Iterator iterator) {
         this.iterator = (Iterator)Preconditions.checkNotNull(iterator);
      }

      public boolean hasNext() {
         return this.hasPeeked || this.iterator.hasNext();
      }

      @ParametricNullness
      public Object next() {
         if (!this.hasPeeked) {
            return this.iterator.next();
         } else {
            E result = (E)NullnessCasts.uncheckedCastNullableTToT(this.peekedElement);
            this.hasPeeked = false;
            this.peekedElement = null;
            return result;
         }
      }

      public void remove() {
         Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
         this.iterator.remove();
      }

      @ParametricNullness
      public Object peek() {
         if (!this.hasPeeked) {
            this.peekedElement = this.iterator.next();
            this.hasPeeked = true;
         }

         return NullnessCasts.uncheckedCastNullableTToT(this.peekedElement);
      }
   }

   private static class MergingIterator extends UnmodifiableIterator {
      final Queue queue;

      public MergingIterator(Iterable iterators, Comparator itemComparator) {
         Comparator<PeekingIterator<T>> heapComparator = (o1, o2) -> itemComparator.compare(o1.peek(), o2.peek());
         this.queue = new PriorityQueue(2, heapComparator);

         for(Iterator iterator : iterators) {
            if (iterator.hasNext()) {
               this.queue.add(Iterators.peekingIterator(iterator));
            }
         }

      }

      public boolean hasNext() {
         return !this.queue.isEmpty();
      }

      @ParametricNullness
      public Object next() {
         PeekingIterator<T> nextIter = (PeekingIterator)this.queue.remove();
         T next = (T)nextIter.next();
         if (nextIter.hasNext()) {
            this.queue.add(nextIter);
         }

         return next;
      }
   }

   private static class ConcatenatedIterator implements Iterator {
      @CheckForNull
      private Iterator toRemove;
      private Iterator iterator = Iterators.emptyIterator();
      @CheckForNull
      private Iterator topMetaIterator;
      @CheckForNull
      private Deque metaIterators;

      ConcatenatedIterator(Iterator metaIterator) {
         this.topMetaIterator = (Iterator)Preconditions.checkNotNull(metaIterator);
      }

      @CheckForNull
      private Iterator getTopMetaIterator() {
         while(this.topMetaIterator == null || !this.topMetaIterator.hasNext()) {
            if (this.metaIterators == null || this.metaIterators.isEmpty()) {
               return null;
            }

            this.topMetaIterator = (Iterator)this.metaIterators.removeFirst();
         }

         return this.topMetaIterator;
      }

      public boolean hasNext() {
         while(!((Iterator)Preconditions.checkNotNull(this.iterator)).hasNext()) {
            this.topMetaIterator = this.getTopMetaIterator();
            if (this.topMetaIterator == null) {
               return false;
            }

            this.iterator = (Iterator)this.topMetaIterator.next();
            if (this.iterator instanceof ConcatenatedIterator) {
               ConcatenatedIterator<T> topConcat = (ConcatenatedIterator)this.iterator;
               this.iterator = topConcat.iterator;
               if (this.metaIterators == null) {
                  this.metaIterators = new ArrayDeque();
               }

               this.metaIterators.addFirst(this.topMetaIterator);
               if (topConcat.metaIterators != null) {
                  while(!topConcat.metaIterators.isEmpty()) {
                     this.metaIterators.addFirst((Iterator)topConcat.metaIterators.removeLast());
                  }
               }

               this.topMetaIterator = topConcat.topMetaIterator;
            }
         }

         return true;
      }

      @ParametricNullness
      public Object next() {
         if (this.hasNext()) {
            this.toRemove = this.iterator;
            return this.iterator.next();
         } else {
            throw new NoSuchElementException();
         }
      }

      public void remove() {
         if (this.toRemove == null) {
            throw new IllegalStateException("no calls to next() since the last call to remove()");
         } else {
            this.toRemove.remove();
            this.toRemove = null;
         }
      }
   }
}
