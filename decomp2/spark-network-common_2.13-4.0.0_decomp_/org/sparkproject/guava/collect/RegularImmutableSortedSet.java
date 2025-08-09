package org.sparkproject.guava.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class RegularImmutableSortedSet extends ImmutableSortedSet {
   static final RegularImmutableSortedSet NATURAL_EMPTY_SET = new RegularImmutableSortedSet(ImmutableList.of(), Ordering.natural());
   private final transient ImmutableList elements;

   RegularImmutableSortedSet(ImmutableList elements, Comparator comparator) {
      super(comparator);
      this.elements = elements;
   }

   @CheckForNull
   Object[] internalArray() {
      return this.elements.internalArray();
   }

   int internalArrayStart() {
      return this.elements.internalArrayStart();
   }

   int internalArrayEnd() {
      return this.elements.internalArrayEnd();
   }

   public UnmodifiableIterator iterator() {
      return this.elements.iterator();
   }

   @GwtIncompatible
   public UnmodifiableIterator descendingIterator() {
      return this.elements.reverse().iterator();
   }

   public Spliterator spliterator() {
      return this.asList().spliterator();
   }

   public void forEach(Consumer action) {
      this.elements.forEach(action);
   }

   public int size() {
      return this.elements.size();
   }

   public boolean contains(@CheckForNull Object o) {
      try {
         return o != null && this.unsafeBinarySearch(o) >= 0;
      } catch (ClassCastException var3) {
         return false;
      }
   }

   public boolean containsAll(Collection targets) {
      if (targets instanceof Multiset) {
         targets = ((Multiset)targets).elementSet();
      }

      if (SortedIterables.hasSameComparator(this.comparator(), targets) && targets.size() > 1) {
         Iterator<E> thisIterator = this.iterator();
         Iterator<?> thatIterator = targets.iterator();
         if (!thisIterator.hasNext()) {
            return false;
         } else {
            Object target = thatIterator.next();
            E current = (E)thisIterator.next();

            try {
               while(true) {
                  int cmp = this.unsafeCompare(current, target);
                  if (cmp < 0) {
                     if (!thisIterator.hasNext()) {
                        return false;
                     }

                     current = (E)thisIterator.next();
                  } else if (cmp == 0) {
                     if (!thatIterator.hasNext()) {
                        return true;
                     }

                     target = thatIterator.next();
                  } else if (cmp > 0) {
                     return false;
                  }
               }
            } catch (ClassCastException | NullPointerException var7) {
               return false;
            }
         }
      } else {
         return super.containsAll(targets);
      }
   }

   private int unsafeBinarySearch(Object key) throws ClassCastException {
      return Collections.binarySearch(this.elements, key, this.unsafeComparator());
   }

   boolean isPartialView() {
      return this.elements.isPartialView();
   }

   int copyIntoArray(@Nullable Object[] dst, int offset) {
      return this.elements.copyIntoArray(dst, offset);
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof Set)) {
         return false;
      } else {
         Set<?> that = (Set)object;
         if (this.size() != that.size()) {
            return false;
         } else if (this.isEmpty()) {
            return true;
         } else if (SortedIterables.hasSameComparator(this.comparator, that)) {
            Iterator<?> otherIterator = that.iterator();

            try {
               for(Object element : this) {
                  Object otherElement = otherIterator.next();
                  if (otherElement == null || this.unsafeCompare(element, otherElement) != 0) {
                     return false;
                  }
               }

               return true;
            } catch (ClassCastException var7) {
               return false;
            } catch (NoSuchElementException var8) {
               return false;
            }
         } else {
            return this.containsAll(that);
         }
      }
   }

   public Object first() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.elements.get(0);
      }
   }

   public Object last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.elements.get(this.size() - 1);
      }
   }

   @CheckForNull
   public Object lower(Object element) {
      int index = this.headIndex(element, false) - 1;
      return index == -1 ? null : this.elements.get(index);
   }

   @CheckForNull
   public Object floor(Object element) {
      int index = this.headIndex(element, true) - 1;
      return index == -1 ? null : this.elements.get(index);
   }

   @CheckForNull
   public Object ceiling(Object element) {
      int index = this.tailIndex(element, true);
      return index == this.size() ? null : this.elements.get(index);
   }

   @CheckForNull
   public Object higher(Object element) {
      int index = this.tailIndex(element, false);
      return index == this.size() ? null : this.elements.get(index);
   }

   ImmutableSortedSet headSetImpl(Object toElement, boolean inclusive) {
      return this.getSubSet(0, this.headIndex(toElement, inclusive));
   }

   int headIndex(Object toElement, boolean inclusive) {
      int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(toElement), this.comparator());
      if (index >= 0) {
         return inclusive ? index + 1 : index;
      } else {
         return ~index;
      }
   }

   ImmutableSortedSet subSetImpl(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
      return this.tailSetImpl(fromElement, fromInclusive).headSetImpl(toElement, toInclusive);
   }

   ImmutableSortedSet tailSetImpl(Object fromElement, boolean inclusive) {
      return this.getSubSet(this.tailIndex(fromElement, inclusive), this.size());
   }

   int tailIndex(Object fromElement, boolean inclusive) {
      int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(fromElement), this.comparator());
      if (index >= 0) {
         return inclusive ? index : index + 1;
      } else {
         return ~index;
      }
   }

   Comparator unsafeComparator() {
      return this.comparator;
   }

   RegularImmutableSortedSet getSubSet(int newFromIndex, int newToIndex) {
      if (newFromIndex == 0 && newToIndex == this.size()) {
         return this;
      } else {
         return newFromIndex < newToIndex ? new RegularImmutableSortedSet(this.elements.subList(newFromIndex, newToIndex), this.comparator) : emptySet(this.comparator);
      }
   }

   int indexOf(@CheckForNull Object target) {
      if (target == null) {
         return -1;
      } else {
         int position;
         try {
            position = Collections.binarySearch(this.elements, target, this.unsafeComparator());
         } catch (ClassCastException var4) {
            return -1;
         }

         return position >= 0 ? position : -1;
      }
   }

   ImmutableList createAsList() {
      return (ImmutableList)(this.size() <= 1 ? this.elements : new ImmutableSortedAsList(this, this.elements));
   }

   ImmutableSortedSet createDescendingSet() {
      Comparator<? super E> reversedOrder = Collections.reverseOrder(this.comparator);
      return this.isEmpty() ? emptySet(reversedOrder) : new RegularImmutableSortedSet(this.elements.reverse(), reversedOrder);
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
