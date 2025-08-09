package org.apache.curator.shaded.com.google.common.collect;

import [Ljava.lang.Comparable;;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.LazyInit;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableSortedSet extends ImmutableSortedSetFauxverideShim implements NavigableSet, SortedIterable {
   static final int SPLITERATOR_CHARACTERISTICS = 1301;
   final transient Comparator comparator;
   @LazyInit
   @CheckForNull
   @GwtIncompatible
   transient ImmutableSortedSet descendingSet;

   public static Collector toImmutableSortedSet(Comparator comparator) {
      return CollectCollectors.toImmutableSortedSet(comparator);
   }

   static RegularImmutableSortedSet emptySet(Comparator comparator) {
      return Ordering.natural().equals(comparator) ? RegularImmutableSortedSet.NATURAL_EMPTY_SET : new RegularImmutableSortedSet(ImmutableList.of(), comparator);
   }

   public static ImmutableSortedSet of() {
      return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
   }

   public static ImmutableSortedSet of(Comparable element) {
      return new RegularImmutableSortedSet(ImmutableList.of(element), Ordering.natural());
   }

   public static ImmutableSortedSet of(Comparable e1, Comparable e2) {
      return construct(Ordering.natural(), 2, e1, e2);
   }

   public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3) {
      return construct(Ordering.natural(), 3, e1, e2, e3);
   }

   public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4) {
      return construct(Ordering.natural(), 4, e1, e2, e3, e4);
   }

   public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5) {
      return construct(Ordering.natural(), 5, e1, e2, e3, e4, e5);
   }

   public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5, Comparable e6, Comparable... remaining) {
      Comparable[] contents = new Comparable[6 + remaining.length];
      contents[0] = e1;
      contents[1] = e2;
      contents[2] = e3;
      contents[3] = e4;
      contents[4] = e5;
      contents[5] = e6;
      System.arraycopy(remaining, 0, contents, 6, remaining.length);
      return construct(Ordering.natural(), contents.length, contents);
   }

   public static ImmutableSortedSet copyOf(Comparable[] elements) {
      return construct(Ordering.natural(), elements.length, (Comparable[])((Comparable;)elements).clone());
   }

   public static ImmutableSortedSet copyOf(Iterable elements) {
      Ordering<E> naturalOrder = Ordering.natural();
      return copyOf(naturalOrder, (Iterable)elements);
   }

   public static ImmutableSortedSet copyOf(Collection elements) {
      Ordering<E> naturalOrder = Ordering.natural();
      return copyOf(naturalOrder, (Collection)elements);
   }

   public static ImmutableSortedSet copyOf(Iterator elements) {
      Ordering<E> naturalOrder = Ordering.natural();
      return copyOf(naturalOrder, (Iterator)elements);
   }

   public static ImmutableSortedSet copyOf(Comparator comparator, Iterator elements) {
      return (new Builder(comparator)).addAll(elements).build();
   }

   public static ImmutableSortedSet copyOf(Comparator comparator, Iterable elements) {
      Preconditions.checkNotNull(comparator);
      boolean hasSameComparator = SortedIterables.hasSameComparator(comparator, elements);
      if (hasSameComparator && elements instanceof ImmutableSortedSet) {
         ImmutableSortedSet<E> original = (ImmutableSortedSet)elements;
         if (!original.isPartialView()) {
            return original;
         }
      }

      E[] array = (E[])Iterables.toArray(elements);
      return construct(comparator, array.length, array);
   }

   public static ImmutableSortedSet copyOf(Comparator comparator, Collection elements) {
      return copyOf(comparator, (Iterable)elements);
   }

   public static ImmutableSortedSet copyOfSorted(SortedSet sortedSet) {
      Comparator<? super E> comparator = SortedIterables.comparator(sortedSet);
      ImmutableList<E> list = ImmutableList.copyOf((Collection)sortedSet);
      return list.isEmpty() ? emptySet(comparator) : new RegularImmutableSortedSet(list, comparator);
   }

   static ImmutableSortedSet construct(Comparator comparator, int n, Object... contents) {
      if (n == 0) {
         return emptySet(comparator);
      } else {
         ObjectArrays.checkElementsNotNull(contents, n);
         Arrays.sort(contents, 0, n, comparator);
         int uniques = 1;

         for(int i = 1; i < n; ++i) {
            E cur = (E)contents[i];
            E prev = (E)contents[uniques - 1];
            if (comparator.compare(cur, prev) != 0) {
               contents[uniques++] = cur;
            }
         }

         Arrays.fill(contents, uniques, n, (Object)null);
         return new RegularImmutableSortedSet(ImmutableList.asImmutableList(contents, uniques), comparator);
      }
   }

   public static Builder orderedBy(Comparator comparator) {
      return new Builder(comparator);
   }

   public static Builder reverseOrder() {
      return new Builder(Collections.reverseOrder());
   }

   public static Builder naturalOrder() {
      return new Builder(Ordering.natural());
   }

   int unsafeCompare(Object a, @CheckForNull Object b) {
      return unsafeCompare(this.comparator, a, b);
   }

   static int unsafeCompare(Comparator comparator, Object a, @CheckForNull Object b) {
      return comparator.compare(a, b);
   }

   ImmutableSortedSet(Comparator comparator) {
      this.comparator = comparator;
   }

   public Comparator comparator() {
      return this.comparator;
   }

   public abstract UnmodifiableIterator iterator();

   public ImmutableSortedSet headSet(Object toElement) {
      return this.headSet(toElement, false);
   }

   public ImmutableSortedSet headSet(Object toElement, boolean inclusive) {
      return this.headSetImpl(Preconditions.checkNotNull(toElement), inclusive);
   }

   public ImmutableSortedSet subSet(Object fromElement, Object toElement) {
      return this.subSet(fromElement, true, toElement, false);
   }

   @GwtIncompatible
   public ImmutableSortedSet subSet(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
      Preconditions.checkNotNull(fromElement);
      Preconditions.checkNotNull(toElement);
      Preconditions.checkArgument(this.comparator.compare(fromElement, toElement) <= 0);
      return this.subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
   }

   public ImmutableSortedSet tailSet(Object fromElement) {
      return this.tailSet(fromElement, true);
   }

   public ImmutableSortedSet tailSet(Object fromElement, boolean inclusive) {
      return this.tailSetImpl(Preconditions.checkNotNull(fromElement), inclusive);
   }

   abstract ImmutableSortedSet headSetImpl(Object toElement, boolean inclusive);

   abstract ImmutableSortedSet subSetImpl(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive);

   abstract ImmutableSortedSet tailSetImpl(Object fromElement, boolean inclusive);

   @CheckForNull
   @GwtIncompatible
   public Object lower(Object e) {
      return Iterators.getNext(this.headSet(e, false).descendingIterator(), (Object)null);
   }

   @CheckForNull
   public Object floor(Object e) {
      return Iterators.getNext(this.headSet(e, true).descendingIterator(), (Object)null);
   }

   @CheckForNull
   public Object ceiling(Object e) {
      return Iterables.getFirst(this.tailSet(e, true), (Object)null);
   }

   @CheckForNull
   @GwtIncompatible
   public Object higher(Object e) {
      return Iterables.getFirst(this.tailSet(e, false), (Object)null);
   }

   public Object first() {
      return this.iterator().next();
   }

   public Object last() {
      return this.descendingIterator().next();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @GwtIncompatible
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object pollFirst() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @GwtIncompatible
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object pollLast() {
      throw new UnsupportedOperationException();
   }

   @GwtIncompatible
   public ImmutableSortedSet descendingSet() {
      ImmutableSortedSet<E> result = this.descendingSet;
      if (result == null) {
         result = this.descendingSet = this.createDescendingSet();
         result.descendingSet = this;
      }

      return result;
   }

   @GwtIncompatible
   abstract ImmutableSortedSet createDescendingSet();

   public Spliterator spliterator() {
      return new Spliterators.AbstractSpliterator((long)this.size(), 1365) {
         final UnmodifiableIterator iterator = ImmutableSortedSet.this.iterator();

         public boolean tryAdvance(Consumer action) {
            if (this.iterator.hasNext()) {
               action.accept(this.iterator.next());
               return true;
            } else {
               return false;
            }
         }

         public Comparator getComparator() {
            return ImmutableSortedSet.this.comparator;
         }
      };
   }

   @GwtIncompatible
   public abstract UnmodifiableIterator descendingIterator();

   abstract int indexOf(@CheckForNull Object target);

   @J2ktIncompatible
   private void readObject(ObjectInputStream unused) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.comparator, this.toArray());
   }

   public static final class Builder extends ImmutableSet.Builder {
      private final Comparator comparator;
      private Object[] elements;
      private int n;

      public Builder(Comparator comparator) {
         super(true);
         this.comparator = (Comparator)Preconditions.checkNotNull(comparator);
         this.elements = new Object[4];
         this.n = 0;
      }

      void copy() {
         this.elements = Arrays.copyOf(this.elements, this.elements.length);
      }

      private void sortAndDedup() {
         if (this.n != 0) {
            Arrays.sort(this.elements, 0, this.n, this.comparator);
            int unique = 1;

            for(int i = 1; i < this.n; ++i) {
               int cmp = this.comparator.compare(this.elements[unique - 1], this.elements[i]);
               if (cmp < 0) {
                  this.elements[unique++] = this.elements[i];
               } else if (cmp > 0) {
                  throw new AssertionError("Comparator " + this.comparator + " compare method violates its contract");
               }
            }

            Arrays.fill(this.elements, unique, this.n, (Object)null);
            this.n = unique;
         }
      }

      @CanIgnoreReturnValue
      public Builder add(Object element) {
         Preconditions.checkNotNull(element);
         this.copyIfNecessary();
         if (this.n == this.elements.length) {
            this.sortAndDedup();
            int newLength = ImmutableCollection.Builder.expandedCapacity(this.n, this.n + 1);
            if (newLength > this.elements.length) {
               this.elements = Arrays.copyOf(this.elements, newLength);
            }
         }

         this.elements[this.n++] = element;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         ObjectArrays.checkElementsNotNull(elements);

         for(Object e : elements) {
            this.add(e);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable elements) {
         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterator elements) {
         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(ImmutableSet.Builder builder) {
         this.copyIfNecessary();
         Builder<E> other = (Builder)builder;

         for(int i = 0; i < other.n; ++i) {
            this.add(other.elements[i]);
         }

         return this;
      }

      public ImmutableSortedSet build() {
         this.sortAndDedup();
         if (this.n == 0) {
            return ImmutableSortedSet.emptySet(this.comparator);
         } else {
            this.forceCopy = true;
            return new RegularImmutableSortedSet(ImmutableList.asImmutableList(this.elements, this.n), this.comparator);
         }
      }
   }

   @J2ktIncompatible
   private static class SerializedForm implements Serializable {
      final Comparator comparator;
      final Object[] elements;
      private static final long serialVersionUID = 0L;

      public SerializedForm(Comparator comparator, Object[] elements) {
         this.comparator = comparator;
         this.elements = elements;
      }

      Object readResolve() {
         return (new Builder(this.comparator)).add(this.elements).build();
      }
   }
}
