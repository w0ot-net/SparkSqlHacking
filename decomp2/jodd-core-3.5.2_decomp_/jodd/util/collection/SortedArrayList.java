package jodd.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SortedArrayList extends ArrayList {
   protected final Comparator comparator;

   public SortedArrayList(Comparator c) {
      this.comparator = c;
   }

   public SortedArrayList() {
      this.comparator = null;
   }

   public SortedArrayList(Collection c) {
      this.comparator = null;
      this.addAll(c);
   }

   public Comparator getComparator() {
      return this.comparator;
   }

   public boolean add(Object o) {
      int idx = 0;
      if (!this.isEmpty()) {
         idx = this.findInsertionPoint(o);
      }

      super.add(idx, o);
      return true;
   }

   public boolean addAll(Collection c) {
      Iterator<? extends E> i = c.iterator();
      boolean changed = false;

      while(i.hasNext()) {
         boolean ret = this.add(i.next());
         if (!changed) {
            changed = ret;
         }
      }

      return changed;
   }

   public int findInsertionPoint(Object o) {
      return this.findInsertionPoint(o, 0, this.size() - 1);
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(int index, Collection c) {
      throw new UnsupportedOperationException();
   }

   protected int compare(Object k1, Object k2) {
      return this.comparator == null ? ((Comparable)k1).compareTo(k2) : this.comparator.compare(k1, k2);
   }

   protected int findInsertionPoint(Object o, int low, int high) {
      while(low <= high) {
         int mid = low + high >>> 1;
         int delta = this.compare(this.get(mid), o);
         if (delta > 0) {
            high = mid - 1;
         } else {
            low = mid + 1;
         }
      }

      return low;
   }
}
