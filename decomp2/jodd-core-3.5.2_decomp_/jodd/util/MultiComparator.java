package jodd.util;

import java.util.Comparator;
import java.util.List;

public class MultiComparator implements Comparator {
   protected final List comparators;

   public MultiComparator(List comparators) {
      this.comparators = comparators;
   }

   public int compare(Object o1, Object o2) {
      int comparatorsSize = this.comparators.size();

      for(int i = 0; i < comparatorsSize; ++i) {
         Comparator<T> comparator = (Comparator)this.comparators.get(i);
         int result = comparator.compare(o1, o2);
         if (result != 0) {
            return result;
         }
      }

      return 0;
   }
}
