package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.LazyInit;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class ImmutableSortedMultiset extends ImmutableSortedMultisetFauxverideShim implements SortedMultiset {
   @LazyInit
   @CheckForNull
   transient ImmutableSortedMultiset descendingMultiset;

   public static Collector toImmutableSortedMultiset(Comparator comparator) {
      return toImmutableSortedMultiset(comparator, Function.identity(), (e) -> 1);
   }

   public static Collector toImmutableSortedMultiset(Comparator comparator, Function elementFunction, ToIntFunction countFunction) {
      Preconditions.checkNotNull(comparator);
      Preconditions.checkNotNull(elementFunction);
      Preconditions.checkNotNull(countFunction);
      return Collector.of(() -> TreeMultiset.create(comparator), (multiset, t) -> multiset.add(Preconditions.checkNotNull(elementFunction.apply(t)), countFunction.applyAsInt(t)), (multiset1, multiset2) -> {
         multiset1.addAll(multiset2);
         return multiset1;
      }, (multiset) -> copyOfSortedEntries(comparator, multiset.entrySet()));
   }

   public static ImmutableSortedMultiset of() {
      return RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
   }

   public static ImmutableSortedMultiset of(Comparable element) {
      RegularImmutableSortedSet<E> elementSet = (RegularImmutableSortedSet)ImmutableSortedSet.of(element);
      long[] cumulativeCounts = new long[]{0L, 1L};
      return new RegularImmutableSortedMultiset(elementSet, cumulativeCounts, 0, 1);
   }

   public static ImmutableSortedMultiset of(Comparable e1, Comparable e2) {
      return copyOf(Ordering.natural(), (Iterable)Arrays.asList(e1, e2));
   }

   public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3) {
      return copyOf(Ordering.natural(), (Iterable)Arrays.asList(e1, e2, e3));
   }

   public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4) {
      return copyOf(Ordering.natural(), (Iterable)Arrays.asList(e1, e2, e3, e4));
   }

   public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5) {
      return copyOf(Ordering.natural(), (Iterable)Arrays.asList(e1, e2, e3, e4, e5));
   }

   public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5, Comparable e6, Comparable... remaining) {
      int size = remaining.length + 6;
      List<E> all = Lists.newArrayListWithCapacity(size);
      Collections.addAll(all, new Comparable[]{e1, e2, e3, e4, e5, e6});
      Collections.addAll(all, remaining);
      return copyOf(Ordering.natural(), (Iterable)all);
   }

   public static ImmutableSortedMultiset copyOf(Comparable[] elements) {
      return copyOf(Ordering.natural(), (Iterable)Arrays.asList(elements));
   }

   public static ImmutableSortedMultiset copyOf(Iterable elements) {
      Ordering<E> naturalOrder = Ordering.natural();
      return copyOf(naturalOrder, (Iterable)elements);
   }

   public static ImmutableSortedMultiset copyOf(Iterator elements) {
      Ordering<E> naturalOrder = Ordering.natural();
      return copyOf(naturalOrder, (Iterator)elements);
   }

   public static ImmutableSortedMultiset copyOf(Comparator comparator, Iterator elements) {
      Preconditions.checkNotNull(comparator);
      return (new Builder(comparator)).addAll(elements).build();
   }

   public static ImmutableSortedMultiset copyOf(Comparator comparator, Iterable elements) {
      if (elements instanceof ImmutableSortedMultiset) {
         ImmutableSortedMultiset<E> multiset = (ImmutableSortedMultiset)elements;
         if (comparator.equals(multiset.comparator())) {
            if (multiset.isPartialView()) {
               return copyOfSortedEntries(comparator, multiset.entrySet().asList());
            }

            return multiset;
         }
      }

      Iterable var3 = Lists.newArrayList(elements);
      TreeMultiset<E> sortedCopy = TreeMultiset.create((Comparator)Preconditions.checkNotNull(comparator));
      Iterables.addAll(sortedCopy, var3);
      return copyOfSortedEntries(comparator, sortedCopy.entrySet());
   }

   public static ImmutableSortedMultiset copyOfSorted(SortedMultiset sortedMultiset) {
      return copyOfSortedEntries(sortedMultiset.comparator(), Lists.newArrayList((Iterable)sortedMultiset.entrySet()));
   }

   private static ImmutableSortedMultiset copyOfSortedEntries(Comparator comparator, Collection entries) {
      if (entries.isEmpty()) {
         return emptyMultiset(comparator);
      } else {
         ImmutableList.Builder<E> elementsBuilder = new ImmutableList.Builder(entries.size());
         long[] cumulativeCounts = new long[entries.size() + 1];
         int i = 0;

         for(Multiset.Entry entry : entries) {
            elementsBuilder.add(entry.getElement());
            cumulativeCounts[i + 1] = cumulativeCounts[i] + (long)entry.getCount();
            ++i;
         }

         return new RegularImmutableSortedMultiset(new RegularImmutableSortedSet(elementsBuilder.build(), comparator), cumulativeCounts, 0, entries.size());
      }
   }

   static ImmutableSortedMultiset emptyMultiset(Comparator comparator) {
      return (ImmutableSortedMultiset)(Ordering.natural().equals(comparator) ? RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET : new RegularImmutableSortedMultiset(comparator));
   }

   ImmutableSortedMultiset() {
   }

   public final Comparator comparator() {
      return this.elementSet().comparator();
   }

   public abstract ImmutableSortedSet elementSet();

   public ImmutableSortedMultiset descendingMultiset() {
      ImmutableSortedMultiset<E> result = this.descendingMultiset;
      return result == null ? (this.descendingMultiset = (ImmutableSortedMultiset)(this.isEmpty() ? emptyMultiset(Ordering.from(this.comparator()).reverse()) : new DescendingImmutableSortedMultiset(this))) : result;
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Multiset.Entry pollFirstEntry() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Multiset.Entry pollLastEntry() {
      throw new UnsupportedOperationException();
   }

   public abstract ImmutableSortedMultiset headMultiset(Object upperBound, BoundType boundType);

   public ImmutableSortedMultiset subMultiset(Object lowerBound, BoundType lowerBoundType, Object upperBound, BoundType upperBoundType) {
      Preconditions.checkArgument(this.comparator().compare(lowerBound, upperBound) <= 0, "Expected lowerBound <= upperBound but %s > %s", lowerBound, upperBound);
      return this.tailMultiset(lowerBound, lowerBoundType).headMultiset(upperBound, upperBoundType);
   }

   public abstract ImmutableSortedMultiset tailMultiset(Object lowerBound, BoundType boundType);

   public static Builder orderedBy(Comparator comparator) {
      return new Builder(comparator);
   }

   public static Builder reverseOrder() {
      return new Builder(Ordering.natural().reverse());
   }

   public static Builder naturalOrder() {
      return new Builder(Ordering.natural());
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   public static class Builder extends ImmutableMultiset.Builder {
      public Builder(Comparator comparator) {
         super(TreeMultiset.create((Comparator)Preconditions.checkNotNull(comparator)));
      }

      @CanIgnoreReturnValue
      public Builder add(Object element) {
         super.add(element);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         super.add(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addCopies(Object element, int occurrences) {
         super.addCopies(element, occurrences);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setCount(Object element, int count) {
         super.setCount(element, count);
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

      public ImmutableSortedMultiset build() {
         return ImmutableSortedMultiset.copyOfSorted((SortedMultiset)this.contents);
      }
   }

   @J2ktIncompatible
   private static final class SerializedForm implements Serializable {
      final Comparator comparator;
      final Object[] elements;
      final int[] counts;

      SerializedForm(SortedMultiset multiset) {
         this.comparator = multiset.comparator();
         int n = multiset.entrySet().size();
         this.elements = new Object[n];
         this.counts = new int[n];
         int i = 0;

         for(Multiset.Entry entry : multiset.entrySet()) {
            this.elements[i] = entry.getElement();
            this.counts[i] = entry.getCount();
            ++i;
         }

      }

      Object readResolve() {
         int n = this.elements.length;
         Builder<E> builder = new Builder(this.comparator);

         for(int i = 0; i < n; ++i) {
            builder.addCopies(this.elements[i], this.counts[i]);
         }

         return builder.build();
      }
   }
}
