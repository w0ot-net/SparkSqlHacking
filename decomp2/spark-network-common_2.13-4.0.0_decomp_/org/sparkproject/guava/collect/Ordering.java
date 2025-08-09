package org.sparkproject.guava.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class Ordering implements Comparator {
   static final int LEFT_IS_GREATER = 1;
   static final int RIGHT_IS_GREATER = -1;

   @GwtCompatible(
      serializable = true
   )
   public static Ordering natural() {
      return NaturalOrdering.INSTANCE;
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering from(Comparator comparator) {
      return (Ordering)(comparator instanceof Ordering ? (Ordering)comparator : new ComparatorOrdering(comparator));
   }

   /** @deprecated */
   @Deprecated
   @GwtCompatible(
      serializable = true
   )
   public static Ordering from(Ordering ordering) {
      return (Ordering)Preconditions.checkNotNull(ordering);
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering explicit(List valuesInOrder) {
      return new ExplicitOrdering(valuesInOrder);
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering explicit(Object leastValue, Object... remainingValuesInOrder) {
      return explicit(Lists.asList(leastValue, remainingValuesInOrder));
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering allEqual() {
      return AllEqualOrdering.INSTANCE;
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering usingToString() {
      return UsingToStringOrdering.INSTANCE;
   }

   @J2ktIncompatible
   public static Ordering arbitrary() {
      return Ordering.ArbitraryOrderingHolder.ARBITRARY_ORDERING;
   }

   protected Ordering() {
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering reverse() {
      return new ReverseOrdering(this);
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering nullsFirst() {
      return new NullsFirstOrdering(this);
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering nullsLast() {
      return new NullsLastOrdering(this);
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering onResultOf(Function function) {
      return new ByFunctionOrdering(function, this);
   }

   Ordering onKeys() {
      return this.onResultOf(Maps.keyFunction());
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering compound(Comparator secondaryComparator) {
      return new CompoundOrdering(this, (Comparator)Preconditions.checkNotNull(secondaryComparator));
   }

   @GwtCompatible(
      serializable = true
   )
   public static Ordering compound(Iterable comparators) {
      return new CompoundOrdering(comparators);
   }

   @GwtCompatible(
      serializable = true
   )
   public Ordering lexicographical() {
      return new LexicographicalOrdering(this);
   }

   public abstract int compare(@ParametricNullness Object left, @ParametricNullness Object right);

   @ParametricNullness
   public Object min(Iterator iterator) {
      E minSoFar;
      for(minSoFar = (E)iterator.next(); iterator.hasNext(); minSoFar = (E)this.min(minSoFar, iterator.next())) {
      }

      return minSoFar;
   }

   @ParametricNullness
   public Object min(Iterable iterable) {
      return this.min(iterable.iterator());
   }

   @ParametricNullness
   public Object min(@ParametricNullness Object a, @ParametricNullness Object b) {
      return this.compare(a, b) <= 0 ? a : b;
   }

   @ParametricNullness
   public Object min(@ParametricNullness Object a, @ParametricNullness Object b, @ParametricNullness Object c, Object... rest) {
      E minSoFar = (E)this.min(this.min(a, b), c);

      for(Object r : rest) {
         minSoFar = (E)this.min(minSoFar, r);
      }

      return minSoFar;
   }

   @ParametricNullness
   public Object max(Iterator iterator) {
      E maxSoFar;
      for(maxSoFar = (E)iterator.next(); iterator.hasNext(); maxSoFar = (E)this.max(maxSoFar, iterator.next())) {
      }

      return maxSoFar;
   }

   @ParametricNullness
   public Object max(Iterable iterable) {
      return this.max(iterable.iterator());
   }

   @ParametricNullness
   public Object max(@ParametricNullness Object a, @ParametricNullness Object b) {
      return this.compare(a, b) >= 0 ? a : b;
   }

   @ParametricNullness
   public Object max(@ParametricNullness Object a, @ParametricNullness Object b, @ParametricNullness Object c, Object... rest) {
      E maxSoFar = (E)this.max(this.max(a, b), c);

      for(Object r : rest) {
         maxSoFar = (E)this.max(maxSoFar, r);
      }

      return maxSoFar;
   }

   public List leastOf(Iterable iterable, int k) {
      if (iterable instanceof Collection) {
         Collection<E> collection = (Collection)iterable;
         if ((long)collection.size() <= 2L * (long)k) {
            E[] array = (E[])collection.toArray();
            Arrays.sort(array, this);
            if (array.length > k) {
               array = (E[])Arrays.copyOf(array, k);
            }

            return Collections.unmodifiableList(Arrays.asList(array));
         }
      }

      return this.leastOf(iterable.iterator(), k);
   }

   public List leastOf(Iterator iterator, int k) {
      Preconditions.checkNotNull(iterator);
      CollectPreconditions.checkNonnegative(k, "k");
      if (k != 0 && iterator.hasNext()) {
         if (k >= 1073741823) {
            ArrayList<E> list = Lists.newArrayList(iterator);
            Collections.sort(list, this);
            if (list.size() > k) {
               list.subList(k, list.size()).clear();
            }

            list.trimToSize();
            return Collections.unmodifiableList(list);
         } else {
            TopKSelector<E> selector = TopKSelector.least(k, this);
            selector.offerAll(iterator);
            return selector.topK();
         }
      } else {
         return Collections.emptyList();
      }
   }

   public List greatestOf(Iterable iterable, int k) {
      return this.reverse().leastOf(iterable, k);
   }

   public List greatestOf(Iterator iterator, int k) {
      return this.reverse().leastOf(iterator, k);
   }

   public List sortedCopy(Iterable elements) {
      E[] array = (E[])Iterables.toArray(elements);
      Arrays.sort(array, this);
      return Lists.newArrayList((Iterable)Arrays.asList(array));
   }

   public ImmutableList immutableSortedCopy(Iterable elements) {
      return ImmutableList.sortedCopyOf(this, elements);
   }

   public boolean isOrdered(Iterable iterable) {
      Iterator<? extends T> it = iterable.iterator();
      T next;
      if (it.hasNext()) {
         for(T prev = (T)it.next(); it.hasNext(); prev = next) {
            next = (T)it.next();
            if (this.compare(prev, next) > 0) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean isStrictlyOrdered(Iterable iterable) {
      Iterator<? extends T> it = iterable.iterator();
      T next;
      if (it.hasNext()) {
         for(T prev = (T)it.next(); it.hasNext(); prev = next) {
            next = (T)it.next();
            if (this.compare(prev, next) >= 0) {
               return false;
            }
         }
      }

      return true;
   }

   /** @deprecated */
   @Deprecated
   public int binarySearch(List sortedList, @ParametricNullness Object key) {
      return Collections.binarySearch(sortedList, key, this);
   }

   @J2ktIncompatible
   private static class ArbitraryOrderingHolder {
      static final Ordering ARBITRARY_ORDERING = new ArbitraryOrdering();
   }

   @J2ktIncompatible
   @VisibleForTesting
   static class ArbitraryOrdering extends Ordering {
      private final AtomicInteger counter = new AtomicInteger(0);
      private final ConcurrentMap uids = Platform.tryWeakKeys(new MapMaker()).makeMap();

      private Integer getUid(Object obj) {
         Integer uid = (Integer)this.uids.get(obj);
         if (uid == null) {
            uid = this.counter.getAndIncrement();
            Integer alreadySet = (Integer)this.uids.putIfAbsent(obj, uid);
            if (alreadySet != null) {
               uid = alreadySet;
            }
         }

         return uid;
      }

      public int compare(@CheckForNull Object left, @CheckForNull Object right) {
         if (left == right) {
            return 0;
         } else if (left == null) {
            return -1;
         } else if (right == null) {
            return 1;
         } else {
            int leftCode = this.identityHashCode(left);
            int rightCode = this.identityHashCode(right);
            if (leftCode != rightCode) {
               return leftCode < rightCode ? -1 : 1;
            } else {
               int result = this.getUid(left).compareTo(this.getUid(right));
               if (result == 0) {
                  throw new AssertionError();
               } else {
                  return result;
               }
            }
         }
      }

      public String toString() {
         return "Ordering.arbitrary()";
      }

      int identityHashCode(Object object) {
         return System.identityHashCode(object);
      }
   }

   static class IncomparableValueException extends ClassCastException {
      final Object value;
      private static final long serialVersionUID = 0L;

      IncomparableValueException(Object value) {
         super("Cannot compare value: " + value);
         this.value = value;
      }
   }
}
