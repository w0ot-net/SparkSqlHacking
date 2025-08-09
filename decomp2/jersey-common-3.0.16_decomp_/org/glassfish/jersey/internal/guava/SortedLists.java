package org.glassfish.jersey.internal.guava;

import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

final class SortedLists {
   private SortedLists() {
   }

   public static int binarySearch(List list, Object key, Comparator comparator, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
      Preconditions.checkNotNull(comparator);
      Preconditions.checkNotNull(list);
      Preconditions.checkNotNull(presentBehavior);
      Preconditions.checkNotNull(absentBehavior);
      if (!(list instanceof RandomAccess)) {
         list = Lists.newArrayList((Iterable)list);
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

   public static enum KeyPresentBehavior {
      ANY_PRESENT {
         int resultIndex(Comparator comparator, Object key, List list, int foundIndex) {
            return foundIndex;
         }
      },
      LAST_PRESENT {
         int resultIndex(Comparator comparator, Object key, List list, int foundIndex) {
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
         int resultIndex(Comparator comparator, Object key, List list, int foundIndex) {
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
         public int resultIndex(Comparator comparator, Object key, List list, int foundIndex) {
            return LAST_PRESENT.resultIndex(comparator, key, list, foundIndex) + 1;
         }
      };

      private KeyPresentBehavior() {
      }

      abstract int resultIndex(Comparator var1, Object var2, List var3, int var4);
   }

   public static enum KeyAbsentBehavior {
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

      abstract int resultIndex(int var1);
   }
}
