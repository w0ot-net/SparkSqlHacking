package org.sparkproject.guava.collect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class SortedLists {
   private SortedLists() {
   }

   public static int binarySearch(List list, Comparable e, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
      Preconditions.checkNotNull(e);
      return binarySearch(list, (Object)e, (Comparator)Ordering.natural(), presentBehavior, absentBehavior);
   }

   public static int binarySearch(List list, Function keyFunction, Comparable key, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
      Preconditions.checkNotNull(key);
      return binarySearch(list, keyFunction, key, Ordering.natural(), presentBehavior, absentBehavior);
   }

   public static int binarySearch(List list, Function keyFunction, @ParametricNullness Object key, Comparator keyComparator, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
      return binarySearch(Lists.transform(list, keyFunction), key, keyComparator, presentBehavior, absentBehavior);
   }

   public static int binarySearch(List list, @ParametricNullness Object key, Comparator comparator, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
      Preconditions.checkNotNull(comparator);
      Preconditions.checkNotNull(list);
      Preconditions.checkNotNull(presentBehavior);
      Preconditions.checkNotNull(absentBehavior);
      if (!(list instanceof RandomAccess)) {
         list = new ArrayList(list);
      }

      int lower = 0;
      int upper = list.size() - 1;

      while(lower <= upper) {
         int middle = lower + upper >>> 1;
         int c = comparator.compare(key, list.get(middle));
         if (c < 0) {
            upper = middle - 1;
         } else {
            if (c <= 0) {
               return lower + presentBehavior.resultIndex(comparator, key, list.subList(lower, upper + 1), middle - lower);
            }

            lower = middle + 1;
         }
      }

      return absentBehavior.resultIndex(lower);
   }

   static enum KeyPresentBehavior {
      ANY_PRESENT {
         int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex) {
            return foundIndex;
         }
      },
      LAST_PRESENT {
         int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex) {
            int lower = foundIndex;
            int upper = list.size() - 1;

            while(lower < upper) {
               int middle = lower + upper + 1 >>> 1;
               int c = comparator.compare(list.get(middle), key);
               if (c > 0) {
                  upper = middle - 1;
               } else {
                  lower = middle;
               }
            }

            return lower;
         }
      },
      FIRST_PRESENT {
         int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex) {
            int lower = 0;
            int upper = foundIndex;

            while(lower < upper) {
               int middle = lower + upper >>> 1;
               int c = comparator.compare(list.get(middle), key);
               if (c < 0) {
                  lower = middle + 1;
               } else {
                  upper = middle;
               }
            }

            return lower;
         }
      },
      FIRST_AFTER {
         public int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex) {
            return LAST_PRESENT.resultIndex(comparator, key, list, foundIndex) + 1;
         }
      },
      LAST_BEFORE {
         public int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex) {
            return FIRST_PRESENT.resultIndex(comparator, key, list, foundIndex) - 1;
         }
      };

      private KeyPresentBehavior() {
      }

      abstract int resultIndex(Comparator comparator, @ParametricNullness Object key, List list, int foundIndex);

      // $FF: synthetic method
      private static KeyPresentBehavior[] $values() {
         return new KeyPresentBehavior[]{ANY_PRESENT, LAST_PRESENT, FIRST_PRESENT, FIRST_AFTER, LAST_BEFORE};
      }
   }

   static enum KeyAbsentBehavior {
      NEXT_LOWER {
         int resultIndex(int higherIndex) {
            return higherIndex - 1;
         }
      },
      NEXT_HIGHER {
         public int resultIndex(int higherIndex) {
            return higherIndex;
         }
      },
      INVERTED_INSERTION_INDEX {
         public int resultIndex(int higherIndex) {
            return ~higherIndex;
         }
      };

      private KeyAbsentBehavior() {
      }

      abstract int resultIndex(int higherIndex);

      // $FF: synthetic method
      private static KeyAbsentBehavior[] $values() {
         return new KeyAbsentBehavior[]{NEXT_LOWER, NEXT_HIGHER, INVERTED_INSERTION_INDEX};
      }
   }
}
