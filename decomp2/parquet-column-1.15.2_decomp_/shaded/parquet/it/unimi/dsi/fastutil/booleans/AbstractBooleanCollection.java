package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

public abstract class AbstractBooleanCollection extends AbstractCollection implements BooleanCollection {
   protected AbstractBooleanCollection() {
   }

   public abstract BooleanIterator iterator();

   public boolean add(boolean k) {
      throw new UnsupportedOperationException();
   }

   public boolean contains(boolean k) {
      BooleanIterator iterator = this.iterator();

      while(iterator.hasNext()) {
         if (k == iterator.nextBoolean()) {
            return true;
         }
      }

      return false;
   }

   public boolean rem(boolean k) {
      BooleanIterator iterator = this.iterator();

      while(iterator.hasNext()) {
         if (k == iterator.nextBoolean()) {
            iterator.remove();
            return true;
         }
      }

      return false;
   }

   /** @deprecated */
   @Deprecated
   public boolean add(Boolean key) {
      return BooleanCollection.super.add(key);
   }

   /** @deprecated */
   @Deprecated
   public boolean contains(Object key) {
      return BooleanCollection.super.contains(key);
   }

   /** @deprecated */
   @Deprecated
   public boolean remove(Object key) {
      return BooleanCollection.super.remove(key);
   }

   public boolean[] toArray(boolean[] a) {
      int size = this.size();
      if (a == null) {
         a = new boolean[size];
      } else if (a.length < size) {
         a = Arrays.copyOf(a, size);
      }

      BooleanIterators.unwrap(this.iterator(), a);
      return a;
   }

   public boolean[] toBooleanArray() {
      int size = this.size();
      if (size == 0) {
         return BooleanArrays.EMPTY_ARRAY;
      } else {
         boolean[] a = new boolean[size];
         BooleanIterators.unwrap(this.iterator(), a);
         return a;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean[] toBooleanArray(boolean[] a) {
      return this.toArray(a);
   }

   public boolean addAll(BooleanCollection c) {
      boolean retVal = false;
      BooleanIterator i = c.iterator();

      while(i.hasNext()) {
         if (this.add(i.nextBoolean())) {
            retVal = true;
         }
      }

      return retVal;
   }

   public boolean addAll(Collection c) {
      return c instanceof BooleanCollection ? this.addAll((BooleanCollection)c) : super.addAll(c);
   }

   public boolean containsAll(BooleanCollection c) {
      BooleanIterator i = c.iterator();

      while(i.hasNext()) {
         if (!this.contains(i.nextBoolean())) {
            return false;
         }
      }

      return true;
   }

   public boolean containsAll(Collection c) {
      return c instanceof BooleanCollection ? this.containsAll((BooleanCollection)c) : super.containsAll(c);
   }

   public boolean removeAll(BooleanCollection c) {
      boolean retVal = false;
      BooleanIterator i = c.iterator();

      while(i.hasNext()) {
         if (this.rem(i.nextBoolean())) {
            retVal = true;
         }
      }

      return retVal;
   }

   public boolean removeAll(Collection c) {
      return c instanceof BooleanCollection ? this.removeAll((BooleanCollection)c) : super.removeAll(c);
   }

   public boolean retainAll(BooleanCollection c) {
      boolean retVal = false;
      BooleanIterator i = this.iterator();

      while(i.hasNext()) {
         if (!c.contains(i.nextBoolean())) {
            i.remove();
            retVal = true;
         }
      }

      return retVal;
   }

   public boolean retainAll(Collection c) {
      return c instanceof BooleanCollection ? this.retainAll((BooleanCollection)c) : super.retainAll(c);
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      BooleanIterator i = this.iterator();
      int n = this.size();
      boolean first = true;
      s.append("{");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         boolean k = i.nextBoolean();
         s.append(String.valueOf(k));
      }

      s.append("}");
      return s.toString();
   }
}
