package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Iterators {
   private static final UnmodifiableListIterator EMPTY_LIST_ITERATOR = new UnmodifiableListIterator() {
      public boolean hasNext() {
         return false;
      }

      public Object next() {
         throw new NoSuchElementException();
      }

      public boolean hasPrevious() {
         return false;
      }

      public Object previous() {
         throw new NoSuchElementException();
      }

      public int nextIndex() {
         return 0;
      }

      public int previousIndex() {
         return -1;
      }
   };
   private static final Iterator EMPTY_MODIFIABLE_ITERATOR = new Iterator() {
      public boolean hasNext() {
         return false;
      }

      public Object next() {
         throw new NoSuchElementException();
      }

      public void remove() {
         CollectPreconditions.checkRemove(false);
      }
   };

   private Iterators() {
   }

   /** @deprecated */
   @Deprecated
   public static UnmodifiableIterator emptyIterator() {
      return emptyListIterator();
   }

   private static UnmodifiableListIterator emptyListIterator() {
      return EMPTY_LIST_ITERATOR;
   }

   static Iterator emptyModifiableIterator() {
      return EMPTY_MODIFIABLE_ITERATOR;
   }

   public static UnmodifiableIterator unmodifiableIterator(final Iterator iterator) {
      Preconditions.checkNotNull(iterator);
      return iterator instanceof UnmodifiableIterator ? (UnmodifiableIterator)iterator : new UnmodifiableIterator() {
         public boolean hasNext() {
            return iterator.hasNext();
         }

         public Object next() {
            return iterator.next();
         }
      };
   }

   public static int size(Iterator iterator) {
      int count;
      for(count = 0; iterator.hasNext(); ++count) {
         iterator.next();
      }

      return count;
   }

   public static boolean removeAll(Iterator removeFrom, Collection elementsToRemove) {
      return removeIf(removeFrom, Predicates.in(elementsToRemove));
   }

   public static boolean removeIf(Iterator removeFrom, Predicate predicate) {
      Preconditions.checkNotNull(predicate);
      boolean modified = false;

      while(removeFrom.hasNext()) {
         if (predicate.test(removeFrom.next())) {
            removeFrom.remove();
            modified = true;
         }
      }

      return modified;
   }

   public static boolean elementsEqual(Iterator iterator1, Iterator iterator2) {
      while(true) {
         if (iterator1.hasNext()) {
            if (!iterator2.hasNext()) {
               return false;
            }

            Object o1 = iterator1.next();
            Object o2 = iterator2.next();
            if (Objects.equals(o1, o2)) {
               continue;
            }

            return false;
         }

         return !iterator2.hasNext();
      }
   }

   public static boolean addAll(Collection addTo, Iterator iterator) {
      Preconditions.checkNotNull(addTo);
      Preconditions.checkNotNull(iterator);

      boolean wasModified;
      for(wasModified = false; iterator.hasNext(); wasModified |= addTo.add(iterator.next())) {
      }

      return wasModified;
   }

   public static boolean all(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(predicate);

      while(iterator.hasNext()) {
         T element = (T)iterator.next();
         if (!predicate.test(element)) {
            return false;
         }
      }

      return true;
   }

   private static int indexOf(Iterator iterator, Predicate predicate) {
      Preconditions.checkNotNull(predicate, "predicate");

      for(int i = 0; iterator.hasNext(); ++i) {
         T current = (T)iterator.next();
         if (predicate.test(current)) {
            return i;
         }
      }

      return -1;
   }

   public static Iterator transform(Iterator fromIterator, final Function function) {
      Preconditions.checkNotNull(function);
      return new TransformedIterator(fromIterator) {
         Object transform(Object from) {
            return function.apply(from);
         }
      };
   }

   public static Object getNext(Iterator iterator, Object defaultValue) {
      return iterator.hasNext() ? iterator.next() : defaultValue;
   }

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

   public static UnmodifiableIterator forArray(Object... array) {
      return forArray(array, 0, array.length, 0);
   }

   static UnmodifiableListIterator forArray(final Object[] array, final int offset, int length, int index) {
      Preconditions.checkArgument(length >= 0);
      int end = offset + length;
      Preconditions.checkPositionIndexes(offset, end, array.length);
      Preconditions.checkPositionIndex(index, length);
      return (UnmodifiableListIterator)(length == 0 ? emptyListIterator() : new AbstractIndexedListIterator(length, index) {
         protected Object get(int index) {
            return array[offset + index];
         }
      });
   }

   public static UnmodifiableIterator singletonIterator(final Object value) {
      return new UnmodifiableIterator() {
         boolean done;

         public boolean hasNext() {
            return !this.done;
         }

         public Object next() {
            if (this.done) {
               throw new NoSuchElementException();
            } else {
               this.done = true;
               return value;
            }
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

   private static class PeekingImpl implements PeekingIterator {
      private final Iterator iterator;
      private boolean hasPeeked;
      private Object peekedElement;

      public PeekingImpl(Iterator iterator) {
         this.iterator = (Iterator)Preconditions.checkNotNull(iterator);
      }

      public boolean hasNext() {
         return this.hasPeeked || this.iterator.hasNext();
      }

      public Object next() {
         if (!this.hasPeeked) {
            return this.iterator.next();
         } else {
            E result = (E)this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return result;
         }
      }

      public void remove() {
         Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
         this.iterator.remove();
      }

      public Object peek() {
         if (!this.hasPeeked) {
            this.peekedElement = this.iterator.next();
            this.hasPeeked = true;
         }

         return this.peekedElement;
      }
   }
}
