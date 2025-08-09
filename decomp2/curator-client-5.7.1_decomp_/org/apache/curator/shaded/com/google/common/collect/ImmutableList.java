package org.apache.curator.shaded.com.google.common.collect;

import [Ljava.lang.Object;;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.apache.curator.shaded.com.google.errorprone.annotations.InlineMe;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableList extends ImmutableCollection implements List, RandomAccess {
   public static Collector toImmutableList() {
      return CollectCollectors.toImmutableList();
   }

   public static ImmutableList of() {
      return RegularImmutableList.EMPTY;
   }

   public static ImmutableList of(Object element) {
      return new SingletonImmutableList(element);
   }

   public static ImmutableList of(Object e1, Object e2) {
      return construct(e1, e2);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3) {
      return construct(e1, e2, e3);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4) {
      return construct(e1, e2, e3, e4);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5) {
      return construct(e1, e2, e3, e4, e5);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6) {
      return construct(e1, e2, e3, e4, e5, e6);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7) {
      return construct(e1, e2, e3, e4, e5, e6, e7);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7, Object e8) {
      return construct(e1, e2, e3, e4, e5, e6, e7, e8);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7, Object e8, Object e9) {
      return construct(e1, e2, e3, e4, e5, e6, e7, e8, e9);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7, Object e8, Object e9, Object e10) {
      return construct(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
   }

   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7, Object e8, Object e9, Object e10, Object e11) {
      return construct(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11);
   }

   @SafeVarargs
   public static ImmutableList of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object e7, Object e8, Object e9, Object e10, Object e11, Object e12, Object... others) {
      Preconditions.checkArgument(others.length <= 2147483635, "the total number of elements must fit in an int");
      Object[] array = new Object[12 + others.length];
      array[0] = e1;
      array[1] = e2;
      array[2] = e3;
      array[3] = e4;
      array[4] = e5;
      array[5] = e6;
      array[6] = e7;
      array[7] = e8;
      array[8] = e9;
      array[9] = e10;
      array[10] = e11;
      array[11] = e12;
      System.arraycopy(others, 0, array, 12, others.length);
      return construct(array);
   }

   public static ImmutableList copyOf(Iterable elements) {
      Preconditions.checkNotNull(elements);
      return elements instanceof Collection ? copyOf((Collection)elements) : copyOf(elements.iterator());
   }

   public static ImmutableList copyOf(Collection elements) {
      if (elements instanceof ImmutableCollection) {
         ImmutableList<E> list = ((ImmutableCollection)elements).asList();
         return list.isPartialView() ? asImmutableList(list.toArray()) : list;
      } else {
         return construct(elements.toArray());
      }
   }

   public static ImmutableList copyOf(Iterator elements) {
      if (!elements.hasNext()) {
         return of();
      } else {
         E first = (E)elements.next();
         return !elements.hasNext() ? of(first) : (new Builder()).add(first).addAll(elements).build();
      }
   }

   public static ImmutableList copyOf(Object[] elements) {
      switch (elements.length) {
         case 0:
            return of();
         case 1:
            return of(elements[0]);
         default:
            return construct(((Object;)elements).clone());
      }
   }

   public static ImmutableList sortedCopyOf(Iterable elements) {
      Comparable<?>[] array = (Comparable[])Iterables.toArray(elements, (Object[])(new Comparable[0]));
      ObjectArrays.checkElementsNotNull((Object[])array);
      Arrays.sort(array);
      return asImmutableList(array);
   }

   public static ImmutableList sortedCopyOf(Comparator comparator, Iterable elements) {
      Preconditions.checkNotNull(comparator);
      E[] array = (E[])Iterables.toArray(elements);
      ObjectArrays.checkElementsNotNull(array);
      Arrays.sort(array, comparator);
      return asImmutableList(array);
   }

   private static ImmutableList construct(Object... elements) {
      return asImmutableList(ObjectArrays.checkElementsNotNull(elements));
   }

   static ImmutableList asImmutableList(Object[] elements) {
      return asImmutableList(elements, elements.length);
   }

   static ImmutableList asImmutableList(@Nullable Object[] elements, int length) {
      switch (length) {
         case 0:
            return of();
         case 1:
            E onlyElement = (E)Objects.requireNonNull(elements[0]);
            return of(onlyElement);
         default:
            Object[] elementsWithoutTrailingNulls = length < elements.length ? Arrays.copyOf(elements, length) : elements;
            return new RegularImmutableList(elementsWithoutTrailingNulls);
      }
   }

   ImmutableList() {
   }

   public UnmodifiableIterator iterator() {
      return this.listIterator();
   }

   public UnmodifiableListIterator listIterator() {
      return this.listIterator(0);
   }

   public UnmodifiableListIterator listIterator(int index) {
      return new AbstractIndexedListIterator(this.size(), index) {
         protected Object get(int index) {
            return ImmutableList.this.get(index);
         }
      };
   }

   public void forEach(Consumer consumer) {
      Preconditions.checkNotNull(consumer);
      int n = this.size();

      for(int i = 0; i < n; ++i) {
         consumer.accept(this.get(i));
      }

   }

   public int indexOf(@CheckForNull Object object) {
      return object == null ? -1 : Lists.indexOfImpl(this, object);
   }

   public int lastIndexOf(@CheckForNull Object object) {
      return object == null ? -1 : Lists.lastIndexOfImpl(this, object);
   }

   public boolean contains(@CheckForNull Object object) {
      return this.indexOf(object) >= 0;
   }

   public ImmutableList subList(int fromIndex, int toIndex) {
      Preconditions.checkPositionIndexes(fromIndex, toIndex, this.size());
      int length = toIndex - fromIndex;
      if (length == this.size()) {
         return this;
      } else if (length == 0) {
         return of();
      } else {
         return length == 1 ? of(this.get(fromIndex)) : this.subListUnchecked(fromIndex, toIndex);
      }
   }

   ImmutableList subListUnchecked(int fromIndex, int toIndex) {
      return new SubList(fromIndex, toIndex - fromIndex);
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean addAll(int index, Collection newElements) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object set(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void add(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object remove(int index) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void replaceAll(UnaryOperator operator) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void sort(Comparator c) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this"
   )
   public final ImmutableList asList() {
      return this;
   }

   public Spliterator spliterator() {
      return CollectSpliterators.indexed(this.size(), 1296, this::get);
   }

   int copyIntoArray(@Nullable Object[] dst, int offset) {
      int size = this.size();

      for(int i = 0; i < size; ++i) {
         dst[offset + i] = this.get(i);
      }

      return offset + size;
   }

   public ImmutableList reverse() {
      return (ImmutableList)(this.size() <= 1 ? this : new ReverseImmutableList(this));
   }

   public boolean equals(@CheckForNull Object obj) {
      return Lists.equalsImpl(this, obj);
   }

   public int hashCode() {
      int hashCode = 1;
      int n = this.size();

      for(int i = 0; i < n; ++i) {
         hashCode = 31 * hashCode + this.get(i).hashCode();
         hashCode = ~(~hashCode);
      }

      return hashCode;
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.toArray());
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builderWithExpectedSize(int expectedSize) {
      CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
      return new Builder(expectedSize);
   }

   class SubList extends ImmutableList {
      final transient int offset;
      final transient int length;

      SubList(int offset, int length) {
         this.offset = offset;
         this.length = length;
      }

      public int size() {
         return this.length;
      }

      public Object get(int index) {
         Preconditions.checkElementIndex(index, this.length);
         return ImmutableList.this.get(index + this.offset);
      }

      public ImmutableList subList(int fromIndex, int toIndex) {
         Preconditions.checkPositionIndexes(fromIndex, toIndex, this.length);
         return ImmutableList.this.subList(fromIndex + this.offset, toIndex + this.offset);
      }

      boolean isPartialView() {
         return true;
      }
   }

   private static class ReverseImmutableList extends ImmutableList {
      private final transient ImmutableList forwardList;

      ReverseImmutableList(ImmutableList backingList) {
         this.forwardList = backingList;
      }

      private int reverseIndex(int index) {
         return this.size() - 1 - index;
      }

      private int reversePosition(int index) {
         return this.size() - index;
      }

      public ImmutableList reverse() {
         return this.forwardList;
      }

      public boolean contains(@CheckForNull Object object) {
         return this.forwardList.contains(object);
      }

      public int indexOf(@CheckForNull Object object) {
         int index = this.forwardList.lastIndexOf(object);
         return index >= 0 ? this.reverseIndex(index) : -1;
      }

      public int lastIndexOf(@CheckForNull Object object) {
         int index = this.forwardList.indexOf(object);
         return index >= 0 ? this.reverseIndex(index) : -1;
      }

      public ImmutableList subList(int fromIndex, int toIndex) {
         Preconditions.checkPositionIndexes(fromIndex, toIndex, this.size());
         return this.forwardList.subList(this.reversePosition(toIndex), this.reversePosition(fromIndex)).reverse();
      }

      public Object get(int index) {
         Preconditions.checkElementIndex(index, this.size());
         return this.forwardList.get(this.reverseIndex(index));
      }

      public int size() {
         return this.forwardList.size();
      }

      boolean isPartialView() {
         return this.forwardList.isPartialView();
      }
   }

   @J2ktIncompatible
   static class SerializedForm implements Serializable {
      final Object[] elements;
      private static final long serialVersionUID = 0L;

      SerializedForm(Object[] elements) {
         this.elements = elements;
      }

      Object readResolve() {
         return ImmutableList.copyOf(this.elements);
      }
   }

   public static final class Builder extends ImmutableCollection.Builder {
      @VisibleForTesting
      @Nullable Object[] contents;
      private int size;
      private boolean forceCopy;

      public Builder() {
         this(4);
      }

      Builder(int capacity) {
         this.contents = new Object[capacity];
         this.size = 0;
      }

      private void getReadyToExpandTo(int minCapacity) {
         if (this.contents.length < minCapacity) {
            this.contents = Arrays.copyOf(this.contents, expandedCapacity(this.contents.length, minCapacity));
            this.forceCopy = false;
         } else if (this.forceCopy) {
            this.contents = Arrays.copyOf(this.contents, this.contents.length);
            this.forceCopy = false;
         }

      }

      @CanIgnoreReturnValue
      public Builder add(Object element) {
         Preconditions.checkNotNull(element);
         this.getReadyToExpandTo(this.size + 1);
         this.contents[this.size++] = element;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         ObjectArrays.checkElementsNotNull(elements);
         this.add(elements, elements.length);
         return this;
      }

      private void add(@Nullable Object[] elements, int n) {
         this.getReadyToExpandTo(this.size + n);
         System.arraycopy(elements, 0, this.contents, this.size, n);
         this.size += n;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable elements) {
         Preconditions.checkNotNull(elements);
         if (elements instanceof Collection) {
            Collection<?> collection = (Collection)elements;
            this.getReadyToExpandTo(this.size + collection.size());
            if (collection instanceof ImmutableCollection) {
               ImmutableCollection<?> immutableCollection = (ImmutableCollection)collection;
               this.size = immutableCollection.copyIntoArray(this.contents, this.size);
               return this;
            }
         }

         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterator elements) {
         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder builder) {
         Preconditions.checkNotNull(builder);
         this.add(builder.contents, builder.size);
         return this;
      }

      public ImmutableList build() {
         this.forceCopy = true;
         return ImmutableList.asImmutableList(this.contents, this.size);
      }
   }
}
