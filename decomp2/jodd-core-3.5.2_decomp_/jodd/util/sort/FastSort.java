package jodd.util.sort;

import java.util.Comparator;

public class FastSort {
   public static void sort(Object[] array, Comparator comparator) {
      TimSort.sort(array, comparator);
   }

   public static void sort(Comparable[] array) {
      ComparableTimSort.sort(array);
   }
}
