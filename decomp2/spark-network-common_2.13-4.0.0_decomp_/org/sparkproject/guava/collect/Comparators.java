package org.sparkproject.guava.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Comparators {
   private Comparators() {
   }

   public static Comparator lexicographical(Comparator comparator) {
      return new LexicographicalOrdering((Comparator)Preconditions.checkNotNull(comparator));
   }

   public static boolean isInOrder(Iterable iterable, Comparator comparator) {
      Preconditions.checkNotNull(comparator);
      Iterator<? extends T> it = iterable.iterator();
      T next;
      if (it.hasNext()) {
         for(T prev = (T)it.next(); it.hasNext(); prev = next) {
            next = (T)it.next();
            if (comparator.compare(prev, next) > 0) {
               return false;
            }
         }
      }

      return true;
   }

   public static boolean isInStrictOrder(Iterable iterable, Comparator comparator) {
      Preconditions.checkNotNull(comparator);
      Iterator<? extends T> it = iterable.iterator();
      T next;
      if (it.hasNext()) {
         for(T prev = (T)it.next(); it.hasNext(); prev = next) {
            next = (T)it.next();
            if (comparator.compare(prev, next) >= 0) {
               return false;
            }
         }
      }

      return true;
   }

   public static Collector least(int k, Comparator comparator) {
      CollectPreconditions.checkNonnegative(k, "k");
      Preconditions.checkNotNull(comparator);
      return Collector.of(() -> TopKSelector.least(k, comparator), TopKSelector::offer, TopKSelector::combine, TopKSelector::topK, Characteristics.UNORDERED);
   }

   public static Collector greatest(int k, Comparator comparator) {
      return least(k, comparator.reversed());
   }

   public static Comparator emptiesFirst(Comparator valueComparator) {
      Preconditions.checkNotNull(valueComparator);
      return Comparator.comparing((o) -> orElseNull(o), Comparator.nullsFirst(valueComparator));
   }

   public static Comparator emptiesLast(Comparator valueComparator) {
      Preconditions.checkNotNull(valueComparator);
      return Comparator.comparing((o) -> orElseNull(o), Comparator.nullsLast(valueComparator));
   }

   @CheckForNull
   private static Object orElseNull(Optional optional) {
      return optional.orElse((Object)null);
   }

   public static Comparable min(Comparable a, Comparable b) {
      return a.compareTo(b) <= 0 ? a : b;
   }

   @ParametricNullness
   public static Object min(@ParametricNullness Object a, @ParametricNullness Object b, Comparator comparator) {
      return comparator.compare(a, b) <= 0 ? a : b;
   }

   public static Comparable max(Comparable a, Comparable b) {
      return a.compareTo(b) >= 0 ? a : b;
   }

   @ParametricNullness
   public static Object max(@ParametricNullness Object a, @ParametricNullness Object b, Comparator comparator) {
      return comparator.compare(a, b) >= 0 ? a : b;
   }
}
