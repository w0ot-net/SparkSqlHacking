package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Lists {
   private Lists() {
   }

   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayList() {
      return new ArrayList();
   }

   @SafeVarargs
   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayList(Object... elements) {
      Preconditions.checkNotNull(elements);
      int capacity = computeArrayListCapacity(elements.length);
      ArrayList<E> list = new ArrayList(capacity);
      Collections.addAll(list, elements);
      return list;
   }

   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayList(Iterable elements) {
      Preconditions.checkNotNull(elements);
      return elements instanceof Collection ? new ArrayList((Collection)elements) : newArrayList(elements.iterator());
   }

   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayList(Iterator elements) {
      ArrayList<E> list = newArrayList();
      Iterators.addAll(list, elements);
      return list;
   }

   @VisibleForTesting
   static int computeArrayListCapacity(int arraySize) {
      CollectPreconditions.checkNonnegative(arraySize, "arraySize");
      return Ints.saturatedCast(5L + (long)arraySize + (long)(arraySize / 10));
   }

   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayListWithCapacity(int initialArraySize) {
      CollectPreconditions.checkNonnegative(initialArraySize, "initialArraySize");
      return new ArrayList(initialArraySize);
   }

   @GwtCompatible(
      serializable = true
   )
   public static ArrayList newArrayListWithExpectedSize(int estimatedSize) {
      return new ArrayList(computeArrayListCapacity(estimatedSize));
   }

   @GwtCompatible(
      serializable = true
   )
   public static LinkedList newLinkedList() {
      return new LinkedList();
   }

   @GwtCompatible(
      serializable = true
   )
   public static LinkedList newLinkedList(Iterable elements) {
      LinkedList<E> list = newLinkedList();
      Iterables.addAll(list, elements);
      return list;
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static CopyOnWriteArrayList newCopyOnWriteArrayList() {
      return new CopyOnWriteArrayList();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static CopyOnWriteArrayList newCopyOnWriteArrayList(Iterable elements) {
      Collection<? extends E> elementsCollection = (Collection<? extends E>)(elements instanceof Collection ? (Collection)elements : newArrayList(elements));
      return new CopyOnWriteArrayList(elementsCollection);
   }

   public static List asList(@ParametricNullness Object first, Object[] rest) {
      return new OnePlusArrayList(first, rest);
   }

   public static List asList(@ParametricNullness Object first, @ParametricNullness Object second, Object[] rest) {
      return new TwoPlusArrayList(first, second, rest);
   }

   public static List cartesianProduct(List lists) {
      return CartesianList.create(lists);
   }

   @SafeVarargs
   public static List cartesianProduct(List... lists) {
      return cartesianProduct(Arrays.asList(lists));
   }

   public static List transform(List fromList, Function function) {
      return (List)(fromList instanceof RandomAccess ? new TransformingRandomAccessList(fromList, function) : new TransformingSequentialList(fromList, function));
   }

   public static List partition(List list, int size) {
      Preconditions.checkNotNull(list);
      Preconditions.checkArgument(size > 0);
      return (List)(list instanceof RandomAccess ? new RandomAccessPartition(list, size) : new Partition(list, size));
   }

   public static ImmutableList charactersOf(String string) {
      return new StringAsImmutableList((String)Preconditions.checkNotNull(string));
   }

   public static List charactersOf(CharSequence sequence) {
      return new CharSequenceAsList((CharSequence)Preconditions.checkNotNull(sequence));
   }

   public static List reverse(List list) {
      if (list instanceof ImmutableList) {
         List<?> reversed = ((ImmutableList)list).reverse();
         return reversed;
      } else if (list instanceof ReverseList) {
         return ((ReverseList)list).getForwardList();
      } else {
         return (List)(list instanceof RandomAccess ? new RandomAccessReverseList(list) : new ReverseList(list));
      }
   }

   static int hashCodeImpl(List list) {
      int hashCode = 1;

      for(Object o : list) {
         hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
         hashCode = ~(~hashCode);
      }

      return hashCode;
   }

   static boolean equalsImpl(List thisList, @CheckForNull Object other) {
      if (other == Preconditions.checkNotNull(thisList)) {
         return true;
      } else if (!(other instanceof List)) {
         return false;
      } else {
         List<?> otherList = (List)other;
         int size = thisList.size();
         if (size != otherList.size()) {
            return false;
         } else if (thisList instanceof RandomAccess && otherList instanceof RandomAccess) {
            for(int i = 0; i < size; ++i) {
               if (!Objects.equal(thisList.get(i), otherList.get(i))) {
                  return false;
               }
            }

            return true;
         } else {
            return Iterators.elementsEqual(thisList.iterator(), otherList.iterator());
         }
      }
   }

   static boolean addAllImpl(List list, int index, Iterable elements) {
      boolean changed = false;
      ListIterator<E> listIterator = list.listIterator(index);

      for(Object e : elements) {
         listIterator.add(e);
         changed = true;
      }

      return changed;
   }

   static int indexOfImpl(List list, @CheckForNull Object element) {
      if (list instanceof RandomAccess) {
         return indexOfRandomAccess(list, element);
      } else {
         ListIterator<?> listIterator = list.listIterator();

         while(listIterator.hasNext()) {
            if (Objects.equal(element, listIterator.next())) {
               return listIterator.previousIndex();
            }
         }

         return -1;
      }
   }

   private static int indexOfRandomAccess(List list, @CheckForNull Object element) {
      int size = list.size();
      if (element == null) {
         for(int i = 0; i < size; ++i) {
            if (list.get(i) == null) {
               return i;
            }
         }
      } else {
         for(int i = 0; i < size; ++i) {
            if (element.equals(list.get(i))) {
               return i;
            }
         }
      }

      return -1;
   }

   static int lastIndexOfImpl(List list, @CheckForNull Object element) {
      if (list instanceof RandomAccess) {
         return lastIndexOfRandomAccess(list, element);
      } else {
         ListIterator<?> listIterator = list.listIterator(list.size());

         while(listIterator.hasPrevious()) {
            if (Objects.equal(element, listIterator.previous())) {
               return listIterator.nextIndex();
            }
         }

         return -1;
      }
   }

   private static int lastIndexOfRandomAccess(List list, @CheckForNull Object element) {
      if (element == null) {
         for(int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i) == null) {
               return i;
            }
         }
      } else {
         for(int i = list.size() - 1; i >= 0; --i) {
            if (element.equals(list.get(i))) {
               return i;
            }
         }
      }

      return -1;
   }

   static ListIterator listIteratorImpl(List list, int index) {
      return (new AbstractListWrapper(list)).listIterator(index);
   }

   static List subListImpl(final List list, int fromIndex, int toIndex) {
      List<E> wrapper;
      if (list instanceof RandomAccess) {
         wrapper = new RandomAccessListWrapper(list) {
            @J2ktIncompatible
            private static final long serialVersionUID = 0L;

            public ListIterator listIterator(int index) {
               return this.backingList.listIterator(index);
            }
         };
      } else {
         wrapper = new AbstractListWrapper(list) {
            @J2ktIncompatible
            private static final long serialVersionUID = 0L;

            public ListIterator listIterator(int index) {
               return this.backingList.listIterator(index);
            }
         };
      }

      return wrapper.subList(fromIndex, toIndex);
   }

   static List cast(Iterable iterable) {
      return (List)iterable;
   }

   private static class OnePlusArrayList extends AbstractList implements Serializable, RandomAccess {
      @ParametricNullness
      final Object first;
      final Object[] rest;
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      OnePlusArrayList(@ParametricNullness Object first, Object[] rest) {
         this.first = first;
         this.rest = Preconditions.checkNotNull(rest);
      }

      public int size() {
         return IntMath.saturatedAdd(this.rest.length, 1);
      }

      @ParametricNullness
      public Object get(int index) {
         Preconditions.checkElementIndex(index, this.size());
         return index == 0 ? this.first : this.rest[index - 1];
      }
   }

   private static class TwoPlusArrayList extends AbstractList implements Serializable, RandomAccess {
      @ParametricNullness
      final Object first;
      @ParametricNullness
      final Object second;
      final Object[] rest;
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      TwoPlusArrayList(@ParametricNullness Object first, @ParametricNullness Object second, Object[] rest) {
         this.first = first;
         this.second = second;
         this.rest = Preconditions.checkNotNull(rest);
      }

      public int size() {
         return IntMath.saturatedAdd(this.rest.length, 2);
      }

      @ParametricNullness
      public Object get(int index) {
         switch (index) {
            case 0:
               return this.first;
            case 1:
               return this.second;
            default:
               Preconditions.checkElementIndex(index, this.size());
               return this.rest[index - 2];
         }
      }
   }

   private static class TransformingSequentialList extends AbstractSequentialList implements Serializable {
      final List fromList;
      final Function function;
      private static final long serialVersionUID = 0L;

      TransformingSequentialList(List fromList, Function function) {
         this.fromList = (List)Preconditions.checkNotNull(fromList);
         this.function = (Function)Preconditions.checkNotNull(function);
      }

      protected void removeRange(int fromIndex, int toIndex) {
         this.fromList.subList(fromIndex, toIndex).clear();
      }

      public int size() {
         return this.fromList.size();
      }

      public boolean isEmpty() {
         return this.fromList.isEmpty();
      }

      public ListIterator listIterator(final int index) {
         return new TransformedListIterator(this.fromList.listIterator(index)) {
            @ParametricNullness
            Object transform(@ParametricNullness Object from) {
               return TransformingSequentialList.this.function.apply(from);
            }
         };
      }

      public boolean removeIf(Predicate filter) {
         Preconditions.checkNotNull(filter);
         return this.fromList.removeIf((element) -> filter.test(this.function.apply(element)));
      }
   }

   private static class TransformingRandomAccessList extends AbstractList implements RandomAccess, Serializable {
      final List fromList;
      final Function function;
      private static final long serialVersionUID = 0L;

      TransformingRandomAccessList(List fromList, Function function) {
         this.fromList = (List)Preconditions.checkNotNull(fromList);
         this.function = (Function)Preconditions.checkNotNull(function);
      }

      protected void removeRange(int fromIndex, int toIndex) {
         this.fromList.subList(fromIndex, toIndex).clear();
      }

      @ParametricNullness
      public Object get(int index) {
         return this.function.apply(this.fromList.get(index));
      }

      public Iterator iterator() {
         return this.listIterator();
      }

      public ListIterator listIterator(int index) {
         return new TransformedListIterator(this.fromList.listIterator(index)) {
            Object transform(Object from) {
               return TransformingRandomAccessList.this.function.apply(from);
            }
         };
      }

      public boolean isEmpty() {
         return this.fromList.isEmpty();
      }

      public boolean removeIf(Predicate filter) {
         Preconditions.checkNotNull(filter);
         return this.fromList.removeIf((element) -> filter.test(this.function.apply(element)));
      }

      @ParametricNullness
      public Object remove(int index) {
         return this.function.apply(this.fromList.remove(index));
      }

      public int size() {
         return this.fromList.size();
      }
   }

   private static class Partition extends AbstractList {
      final List list;
      final int size;

      Partition(List list, int size) {
         this.list = list;
         this.size = size;
      }

      public List get(int index) {
         Preconditions.checkElementIndex(index, this.size());
         int start = index * this.size;
         int end = Math.min(start + this.size, this.list.size());
         return this.list.subList(start, end);
      }

      public int size() {
         return IntMath.divide(this.list.size(), this.size, RoundingMode.CEILING);
      }

      public boolean isEmpty() {
         return this.list.isEmpty();
      }
   }

   private static class RandomAccessPartition extends Partition implements RandomAccess {
      RandomAccessPartition(List list, int size) {
         super(list, size);
      }
   }

   private static final class StringAsImmutableList extends ImmutableList {
      private final String string;

      StringAsImmutableList(String string) {
         this.string = string;
      }

      public int indexOf(@CheckForNull Object object) {
         return object instanceof Character ? this.string.indexOf((Character)object) : -1;
      }

      public int lastIndexOf(@CheckForNull Object object) {
         return object instanceof Character ? this.string.lastIndexOf((Character)object) : -1;
      }

      public ImmutableList subList(int fromIndex, int toIndex) {
         Preconditions.checkPositionIndexes(fromIndex, toIndex, this.size());
         return Lists.charactersOf(this.string.substring(fromIndex, toIndex));
      }

      boolean isPartialView() {
         return false;
      }

      public Character get(int index) {
         Preconditions.checkElementIndex(index, this.size());
         return this.string.charAt(index);
      }

      public int size() {
         return this.string.length();
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   private static final class CharSequenceAsList extends AbstractList {
      private final CharSequence sequence;

      CharSequenceAsList(CharSequence sequence) {
         this.sequence = sequence;
      }

      public Character get(int index) {
         Preconditions.checkElementIndex(index, this.size());
         return this.sequence.charAt(index);
      }

      public int size() {
         return this.sequence.length();
      }
   }

   private static class ReverseList extends AbstractList {
      private final List forwardList;

      ReverseList(List forwardList) {
         this.forwardList = (List)Preconditions.checkNotNull(forwardList);
      }

      List getForwardList() {
         return this.forwardList;
      }

      private int reverseIndex(int index) {
         int size = this.size();
         Preconditions.checkElementIndex(index, size);
         return size - 1 - index;
      }

      private int reversePosition(int index) {
         int size = this.size();
         Preconditions.checkPositionIndex(index, size);
         return size - index;
      }

      public void add(int index, @ParametricNullness Object element) {
         this.forwardList.add(this.reversePosition(index), element);
      }

      public void clear() {
         this.forwardList.clear();
      }

      @ParametricNullness
      public Object remove(int index) {
         return this.forwardList.remove(this.reverseIndex(index));
      }

      protected void removeRange(int fromIndex, int toIndex) {
         this.subList(fromIndex, toIndex).clear();
      }

      @ParametricNullness
      public Object set(int index, @ParametricNullness Object element) {
         return this.forwardList.set(this.reverseIndex(index), element);
      }

      @ParametricNullness
      public Object get(int index) {
         return this.forwardList.get(this.reverseIndex(index));
      }

      public int size() {
         return this.forwardList.size();
      }

      public List subList(int fromIndex, int toIndex) {
         Preconditions.checkPositionIndexes(fromIndex, toIndex, this.size());
         return Lists.reverse(this.forwardList.subList(this.reversePosition(toIndex), this.reversePosition(fromIndex)));
      }

      public Iterator iterator() {
         return this.listIterator();
      }

      public ListIterator listIterator(int index) {
         int start = this.reversePosition(index);
         final ListIterator<T> forwardIterator = this.forwardList.listIterator(start);
         return new ListIterator() {
            boolean canRemoveOrSet;

            public void add(@ParametricNullness Object e) {
               forwardIterator.add(e);
               forwardIterator.previous();
               this.canRemoveOrSet = false;
            }

            public boolean hasNext() {
               return forwardIterator.hasPrevious();
            }

            public boolean hasPrevious() {
               return forwardIterator.hasNext();
            }

            @ParametricNullness
            public Object next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  this.canRemoveOrSet = true;
                  return forwardIterator.previous();
               }
            }

            public int nextIndex() {
               return ReverseList.this.reversePosition(forwardIterator.nextIndex());
            }

            @ParametricNullness
            public Object previous() {
               if (!this.hasPrevious()) {
                  throw new NoSuchElementException();
               } else {
                  this.canRemoveOrSet = true;
                  return forwardIterator.next();
               }
            }

            public int previousIndex() {
               return this.nextIndex() - 1;
            }

            public void remove() {
               CollectPreconditions.checkRemove(this.canRemoveOrSet);
               forwardIterator.remove();
               this.canRemoveOrSet = false;
            }

            public void set(@ParametricNullness Object e) {
               Preconditions.checkState(this.canRemoveOrSet);
               forwardIterator.set(e);
            }
         };
      }
   }

   private static class RandomAccessReverseList extends ReverseList implements RandomAccess {
      RandomAccessReverseList(List forwardList) {
         super(forwardList);
      }
   }

   private static class AbstractListWrapper extends AbstractList {
      final List backingList;

      AbstractListWrapper(List backingList) {
         this.backingList = (List)Preconditions.checkNotNull(backingList);
      }

      public void add(int index, @ParametricNullness Object element) {
         this.backingList.add(index, element);
      }

      public boolean addAll(int index, Collection c) {
         return this.backingList.addAll(index, c);
      }

      @ParametricNullness
      public Object get(int index) {
         return this.backingList.get(index);
      }

      @ParametricNullness
      public Object remove(int index) {
         return this.backingList.remove(index);
      }

      @ParametricNullness
      public Object set(int index, @ParametricNullness Object element) {
         return this.backingList.set(index, element);
      }

      public boolean contains(@CheckForNull Object o) {
         return this.backingList.contains(o);
      }

      public int size() {
         return this.backingList.size();
      }
   }

   private static class RandomAccessListWrapper extends AbstractListWrapper implements RandomAccess {
      RandomAccessListWrapper(List backingList) {
         super(backingList);
      }
   }
}
