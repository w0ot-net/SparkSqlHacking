package jodd.util;

import java.util.Comparator;
import java.util.List;

public abstract class BinarySearch {
   public static BinarySearch forList(final List list) {
      return new BinarySearch() {
         protected int compare(int index, Comparable element) {
            return ((Comparable)list.get(index)).compareTo(element);
         }

         protected int getLastIndex() {
            return list.size() - 1;
         }
      };
   }

   public static BinarySearch forList(final List list, final Comparator comparator) {
      return new BinarySearch() {
         protected int compare(int index, Object element) {
            return comparator.compare(list.get(index), element);
         }

         protected int getLastIndex() {
            return list.size() - 1;
         }
      };
   }

   protected abstract int compare(int var1, Object var2);

   protected abstract int getLastIndex();

   public int find(Object element) {
      return this.find(element, 0, this.getLastIndex());
   }

   public int find(Object element, int low) {
      return this.find(element, low, this.getLastIndex());
   }

   public int find(Object element, int low, int high) {
      while(true) {
         if (low <= high) {
            int mid = low + high >>> 1;
            int delta = this.compare(mid, element);
            if (delta < 0) {
               low = mid + 1;
               continue;
            }

            if (delta > 0) {
               high = mid - 1;
               continue;
            }

            return mid;
         }

         return -(low + 1);
      }
   }

   public int findFirst(Object o) {
      return this.findFirst(o, 0, this.getLastIndex());
   }

   public int findFirst(Object o, int low) {
      return this.findFirst(o, low, this.getLastIndex());
   }

   public int findFirst(Object o, int low, int high) {
      int ndx = -1;

      while(low <= high) {
         int mid = low + high >>> 1;
         int delta = this.compare(mid, o);
         if (delta < 0) {
            low = mid + 1;
         } else {
            if (delta == 0) {
               ndx = mid;
            }

            high = mid - 1;
         }
      }

      if (ndx == -1) {
         return -(low + 1);
      } else {
         return ndx;
      }
   }

   public int findLast(Object o) {
      return this.findLast(o, 0, this.getLastIndex());
   }

   public int findLast(Object o, int low) {
      return this.findLast(o, low, this.getLastIndex());
   }

   public int findLast(Object o, int low, int high) {
      int ndx = -1;

      while(low <= high) {
         int mid = low + high >>> 1;
         int delta = this.compare(mid, o);
         if (delta > 0) {
            high = mid - 1;
         } else {
            if (delta == 0) {
               ndx = mid;
            }

            low = mid + 1;
         }
      }

      if (ndx == -1) {
         return -(low + 1);
      } else {
         return ndx;
      }
   }
}
