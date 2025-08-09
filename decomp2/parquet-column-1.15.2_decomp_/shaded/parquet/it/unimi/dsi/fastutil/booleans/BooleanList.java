package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface BooleanList extends List, Comparable, BooleanCollection {
   BooleanListIterator iterator();

   default BooleanSpliterator spliterator() {
      return (BooleanSpliterator)(this instanceof RandomAccess ? new AbstractBooleanList.IndexBasedSpliterator(this, 0) : BooleanSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 16720));
   }

   BooleanListIterator listIterator();

   BooleanListIterator listIterator(int var1);

   BooleanList subList(int var1, int var2);

   void size(int var1);

   void getElements(int var1, boolean[] var2, int var3, int var4);

   void removeElements(int var1, int var2);

   void addElements(int var1, boolean[] var2);

   void addElements(int var1, boolean[] var2, int var3, int var4);

   default void setElements(boolean[] a) {
      this.setElements(0, a);
   }

   default void setElements(int index, boolean[] a) {
      this.setElements(index, a, 0, a.length);
   }

   default void setElements(int index, boolean[] a, int offset, int length) {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
      } else if (index > this.size()) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + this.size() + ")");
      } else {
         BooleanArrays.ensureOffsetLength(a, offset, length);
         if (index + length > this.size()) {
            throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size() + ")");
         } else {
            BooleanListIterator iter = this.listIterator(index);
            int i = 0;

            while(i < length) {
               iter.nextBoolean();
               iter.set(a[offset + i++]);
            }

         }
      }
   }

   boolean add(boolean var1);

   void add(int var1, boolean var2);

   /** @deprecated */
   @Deprecated
   default void add(int index, Boolean key) {
      this.add(index, key);
   }

   boolean addAll(int var1, BooleanCollection var2);

   boolean set(int var1, boolean var2);

   default void replaceAll(BooleanUnaryOperator operator) {
      BooleanListIterator iter = this.listIterator();

      while(iter.hasNext()) {
         iter.set(operator.apply(iter.nextBoolean()));
      }

   }

   /** @deprecated */
   @Deprecated
   default void replaceAll(UnaryOperator operator) {
      Objects.requireNonNull(operator);
      BooleanUnaryOperator var10001;
      if (operator instanceof BooleanUnaryOperator) {
         var10001 = (BooleanUnaryOperator)operator;
      } else {
         Objects.requireNonNull(operator);
         var10001 = operator::apply;
      }

      this.replaceAll(var10001);
   }

   boolean getBoolean(int var1);

   int indexOf(boolean var1);

   int lastIndexOf(boolean var1);

   /** @deprecated */
   @Deprecated
   default boolean contains(Object key) {
      return BooleanCollection.super.contains(key);
   }

   /** @deprecated */
   @Deprecated
   default Boolean get(int index) {
      return this.getBoolean(index);
   }

   /** @deprecated */
   @Deprecated
   default int indexOf(Object o) {
      return this.indexOf((Boolean)o);
   }

   /** @deprecated */
   @Deprecated
   default int lastIndexOf(Object o) {
      return this.lastIndexOf((Boolean)o);
   }

   /** @deprecated */
   @Deprecated
   default boolean add(Boolean k) {
      return this.add(k);
   }

   boolean removeBoolean(int var1);

   /** @deprecated */
   @Deprecated
   default boolean remove(Object key) {
      return BooleanCollection.super.remove(key);
   }

   /** @deprecated */
   @Deprecated
   default Boolean remove(int index) {
      return this.removeBoolean(index);
   }

   /** @deprecated */
   @Deprecated
   default Boolean set(int index, Boolean k) {
      return this.set(index, k);
   }

   default boolean addAll(int index, BooleanList l) {
      return this.addAll(index, (BooleanCollection)l);
   }

   default boolean addAll(BooleanList l) {
      return this.addAll(this.size(), l);
   }

   static BooleanList of() {
      return BooleanImmutableList.of();
   }

   static BooleanList of(boolean e) {
      return BooleanLists.singleton(e);
   }

   static BooleanList of(boolean e0, boolean e1) {
      return BooleanImmutableList.of(e0, e1);
   }

   static BooleanList of(boolean e0, boolean e1, boolean e2) {
      return BooleanImmutableList.of(e0, e1, e2);
   }

   static BooleanList of(boolean... a) {
      switch (a.length) {
         case 0:
            return of();
         case 1:
            return of(a[0]);
         default:
            return BooleanImmutableList.of(a);
      }
   }

   /** @deprecated */
   @Deprecated
   default void sort(Comparator comparator) {
      this.sort(BooleanComparators.asBooleanComparator(comparator));
   }

   default void sort(BooleanComparator comparator) {
      if (comparator == null) {
         this.unstableSort(comparator);
      } else {
         boolean[] elements = this.toBooleanArray();
         BooleanArrays.stableSort(elements, comparator);
         this.setElements(elements);
      }

   }

   /** @deprecated */
   @Deprecated
   default void unstableSort(Comparator comparator) {
      this.unstableSort(BooleanComparators.asBooleanComparator(comparator));
   }

   default void unstableSort(BooleanComparator comparator) {
      boolean[] elements = this.toBooleanArray();
      if (comparator == null) {
         BooleanArrays.unstableSort(elements);
      } else {
         BooleanArrays.unstableSort(elements, comparator);
      }

      this.setElements(elements);
   }
}
