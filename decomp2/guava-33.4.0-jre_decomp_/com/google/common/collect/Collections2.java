package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Collections2 {
   private Collections2() {
   }

   public static Collection filter(Collection unfiltered, Predicate predicate) {
      return unfiltered instanceof FilteredCollection ? ((FilteredCollection)unfiltered).createCombined(predicate) : new FilteredCollection((Collection)Preconditions.checkNotNull(unfiltered), (Predicate)Preconditions.checkNotNull(predicate));
   }

   static boolean safeContains(Collection collection, @CheckForNull Object object) {
      Preconditions.checkNotNull(collection);

      try {
         return collection.contains(object);
      } catch (NullPointerException | ClassCastException var3) {
         return false;
      }
   }

   static boolean safeRemove(Collection collection, @CheckForNull Object object) {
      Preconditions.checkNotNull(collection);

      try {
         return collection.remove(object);
      } catch (NullPointerException | ClassCastException var3) {
         return false;
      }
   }

   public static Collection transform(Collection fromCollection, Function function) {
      return new TransformedCollection(fromCollection, function);
   }

   static boolean containsAllImpl(Collection self, Collection c) {
      for(Object o : c) {
         if (!self.contains(o)) {
            return false;
         }
      }

      return true;
   }

   static String toStringImpl(final Collection collection) {
      StringBuilder sb = newStringBuilderForCollection(collection.size()).append('[');
      boolean first = true;

      for(Object o : collection) {
         if (!first) {
            sb.append(", ");
         }

         first = false;
         if (o == collection) {
            sb.append("(this Collection)");
         } else {
            sb.append(o);
         }
      }

      return sb.append(']').toString();
   }

   static StringBuilder newStringBuilderForCollection(int size) {
      CollectPreconditions.checkNonnegative(size, "size");
      return new StringBuilder((int)Math.min((long)size * 8L, 1073741824L));
   }

   public static Collection orderedPermutations(Iterable elements) {
      return orderedPermutations(elements, Ordering.natural());
   }

   public static Collection orderedPermutations(Iterable elements, Comparator comparator) {
      return new OrderedPermutationCollection(elements, comparator);
   }

   public static Collection permutations(Collection elements) {
      return new PermutationCollection(ImmutableList.copyOf(elements));
   }

   private static boolean isPermutation(List first, List second) {
      if (first.size() != second.size()) {
         return false;
      } else {
         Multiset<?> firstMultiset = HashMultiset.create(first);
         Multiset<?> secondMultiset = HashMultiset.create(second);
         return firstMultiset.equals(secondMultiset);
      }
   }

   static class FilteredCollection extends AbstractCollection {
      final Collection unfiltered;
      final Predicate predicate;

      FilteredCollection(Collection unfiltered, Predicate predicate) {
         this.unfiltered = unfiltered;
         this.predicate = predicate;
      }

      FilteredCollection createCombined(Predicate newPredicate) {
         return new FilteredCollection(this.unfiltered, Predicates.and(this.predicate, newPredicate));
      }

      public boolean add(@ParametricNullness Object element) {
         Preconditions.checkArgument(this.predicate.apply(element));
         return this.unfiltered.add(element);
      }

      public boolean addAll(Collection collection) {
         for(Object element : collection) {
            Preconditions.checkArgument(this.predicate.apply(element));
         }

         return this.unfiltered.addAll(collection);
      }

      public void clear() {
         Iterables.removeIf(this.unfiltered, this.predicate);
      }

      public boolean contains(@CheckForNull Object element) {
         return Collections2.safeContains(this.unfiltered, element) ? this.predicate.apply(element) : false;
      }

      public boolean containsAll(Collection collection) {
         return Collections2.containsAllImpl(this, collection);
      }

      public boolean isEmpty() {
         return !Iterables.any(this.unfiltered, this.predicate);
      }

      public Iterator iterator() {
         return Iterators.filter(this.unfiltered.iterator(), this.predicate);
      }

      public Spliterator spliterator() {
         return CollectSpliterators.filter(this.unfiltered.spliterator(), this.predicate);
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         this.unfiltered.forEach((e) -> {
            if (this.predicate.test(e)) {
               action.accept(e);
            }

         });
      }

      public boolean remove(@CheckForNull Object element) {
         return this.contains(element) && this.unfiltered.remove(element);
      }

      public boolean removeAll(final Collection collection) {
         Objects.requireNonNull(collection);
         return this.removeIf(collection::contains);
      }

      public boolean retainAll(final Collection collection) {
         return this.removeIf((element) -> !collection.contains(element));
      }

      public boolean removeIf(java.util.function.Predicate filter) {
         Preconditions.checkNotNull(filter);
         return this.unfiltered.removeIf((element) -> this.predicate.apply(element) && filter.test(element));
      }

      public int size() {
         int size = 0;

         for(Object e : this.unfiltered) {
            if (this.predicate.apply(e)) {
               ++size;
            }
         }

         return size;
      }

      public @Nullable Object[] toArray() {
         return Lists.newArrayList(this.iterator()).toArray();
      }

      public Object[] toArray(Object[] array) {
         return Lists.newArrayList(this.iterator()).toArray(array);
      }
   }

   static class TransformedCollection extends AbstractCollection {
      final Collection fromCollection;
      final Function function;

      TransformedCollection(Collection fromCollection, Function function) {
         this.fromCollection = (Collection)Preconditions.checkNotNull(fromCollection);
         this.function = (Function)Preconditions.checkNotNull(function);
      }

      public void clear() {
         this.fromCollection.clear();
      }

      public boolean isEmpty() {
         return this.fromCollection.isEmpty();
      }

      public Iterator iterator() {
         return Iterators.transform(this.fromCollection.iterator(), this.function);
      }

      public Spliterator spliterator() {
         return CollectSpliterators.map(this.fromCollection.spliterator(), this.function);
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         this.fromCollection.forEach((f) -> action.accept(this.function.apply(f)));
      }

      public boolean removeIf(java.util.function.Predicate filter) {
         Preconditions.checkNotNull(filter);
         return this.fromCollection.removeIf((element) -> filter.test(this.function.apply(element)));
      }

      public int size() {
         return this.fromCollection.size();
      }
   }

   private static final class OrderedPermutationCollection extends AbstractCollection {
      final ImmutableList inputList;
      final Comparator comparator;
      final int size;

      OrderedPermutationCollection(Iterable input, Comparator comparator) {
         this.inputList = ImmutableList.sortedCopyOf(comparator, input);
         this.comparator = comparator;
         this.size = calculateSize(this.inputList, comparator);
      }

      private static int calculateSize(List sortedInputList, Comparator comparator) {
         int permutations = 1;
         int n = 1;

         int r;
         for(r = 1; n < sortedInputList.size(); ++r) {
            int comparison = comparator.compare(sortedInputList.get(n - 1), sortedInputList.get(n));
            if (comparison < 0) {
               permutations = IntMath.saturatedMultiply(permutations, IntMath.binomial(n, r));
               r = 0;
               if (permutations == Integer.MAX_VALUE) {
                  return Integer.MAX_VALUE;
               }
            }

            ++n;
         }

         return IntMath.saturatedMultiply(permutations, IntMath.binomial(n, r));
      }

      public int size() {
         return this.size;
      }

      public boolean isEmpty() {
         return false;
      }

      public Iterator iterator() {
         return new OrderedPermutationIterator(this.inputList, this.comparator);
      }

      public boolean contains(@CheckForNull Object obj) {
         if (obj instanceof List) {
            List<?> list = (List)obj;
            return Collections2.isPermutation(this.inputList, list);
         } else {
            return false;
         }
      }

      public String toString() {
         return "orderedPermutationCollection(" + this.inputList + ")";
      }
   }

   private static final class OrderedPermutationIterator extends AbstractIterator {
      @CheckForNull
      List nextPermutation;
      final Comparator comparator;

      OrderedPermutationIterator(List list, Comparator comparator) {
         this.nextPermutation = Lists.newArrayList((Iterable)list);
         this.comparator = comparator;
      }

      @CheckForNull
      protected List computeNext() {
         if (this.nextPermutation == null) {
            return (List)this.endOfData();
         } else {
            ImmutableList<E> next = ImmutableList.copyOf((Collection)this.nextPermutation);
            this.calculateNextPermutation();
            return next;
         }
      }

      void calculateNextPermutation() {
         int j = this.findNextJ();
         if (j == -1) {
            this.nextPermutation = null;
         } else {
            Objects.requireNonNull(this.nextPermutation);
            int l = this.findNextL(j);
            Collections.swap(this.nextPermutation, j, l);
            int n = this.nextPermutation.size();
            Collections.reverse(this.nextPermutation.subList(j + 1, n));
         }
      }

      int findNextJ() {
         Objects.requireNonNull(this.nextPermutation);

         for(int k = this.nextPermutation.size() - 2; k >= 0; --k) {
            if (this.comparator.compare(this.nextPermutation.get(k), this.nextPermutation.get(k + 1)) < 0) {
               return k;
            }
         }

         return -1;
      }

      int findNextL(int j) {
         Objects.requireNonNull(this.nextPermutation);
         E ak = (E)this.nextPermutation.get(j);

         for(int l = this.nextPermutation.size() - 1; l > j; --l) {
            if (this.comparator.compare(ak, this.nextPermutation.get(l)) < 0) {
               return l;
            }
         }

         throw new AssertionError("this statement should be unreachable");
      }
   }

   private static final class PermutationCollection extends AbstractCollection {
      final ImmutableList inputList;

      PermutationCollection(ImmutableList input) {
         this.inputList = input;
      }

      public int size() {
         return IntMath.factorial(this.inputList.size());
      }

      public boolean isEmpty() {
         return false;
      }

      public Iterator iterator() {
         return new PermutationIterator(this.inputList);
      }

      public boolean contains(@CheckForNull Object obj) {
         if (obj instanceof List) {
            List<?> list = (List)obj;
            return Collections2.isPermutation(this.inputList, list);
         } else {
            return false;
         }
      }

      public String toString() {
         return "permutations(" + this.inputList + ")";
      }
   }

   private static class PermutationIterator extends AbstractIterator {
      final List list;
      final int[] c;
      final int[] o;
      int j;

      PermutationIterator(List list) {
         this.list = new ArrayList(list);
         int n = list.size();
         this.c = new int[n];
         this.o = new int[n];
         Arrays.fill(this.c, 0);
         Arrays.fill(this.o, 1);
         this.j = Integer.MAX_VALUE;
      }

      @CheckForNull
      protected List computeNext() {
         if (this.j <= 0) {
            return (List)this.endOfData();
         } else {
            ImmutableList<E> next = ImmutableList.copyOf((Collection)this.list);
            this.calculateNextPermutation();
            return next;
         }
      }

      void calculateNextPermutation() {
         this.j = this.list.size() - 1;
         int s = 0;
         if (this.j != -1) {
            while(true) {
               int q = this.c[this.j] + this.o[this.j];
               if (q >= 0) {
                  if (q != this.j + 1) {
                     Collections.swap(this.list, this.j - this.c[this.j] + s, this.j - q + s);
                     this.c[this.j] = q;
                     break;
                  }

                  if (this.j == 0) {
                     break;
                  }

                  ++s;
                  this.switchDirection();
               } else {
                  this.switchDirection();
               }
            }

         }
      }

      void switchDirection() {
         this.o[this.j] = -this.o[this.j];
         --this.j;
      }
   }
}
