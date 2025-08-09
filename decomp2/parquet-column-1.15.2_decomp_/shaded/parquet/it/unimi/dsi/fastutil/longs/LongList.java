package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface LongList extends List, Comparable, LongCollection {
   LongListIterator iterator();

   default LongSpliterator spliterator() {
      return (LongSpliterator)(this instanceof RandomAccess ? new AbstractLongList.IndexBasedSpliterator(this, 0) : LongSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 16720));
   }

   LongListIterator listIterator();

   LongListIterator listIterator(int var1);

   LongList subList(int var1, int var2);

   void size(int var1);

   void getElements(int var1, long[] var2, int var3, int var4);

   void removeElements(int var1, int var2);

   void addElements(int var1, long[] var2);

   void addElements(int var1, long[] var2, int var3, int var4);

   default void setElements(long[] a) {
      this.setElements(0, a);
   }

   default void setElements(int index, long[] a) {
      this.setElements(index, a, 0, a.length);
   }

   default void setElements(int index, long[] a, int offset, int length) {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
      } else if (index > this.size()) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + this.size() + ")");
      } else {
         LongArrays.ensureOffsetLength(a, offset, length);
         if (index + length > this.size()) {
            throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size() + ")");
         } else {
            LongListIterator iter = this.listIterator(index);
            int i = 0;

            while(i < length) {
               iter.nextLong();
               iter.set(a[offset + i++]);
            }

         }
      }
   }

   boolean add(long var1);

   void add(int var1, long var2);

   /** @deprecated */
   @Deprecated
   default void add(int index, Long key) {
      this.add(index, key);
   }

   boolean addAll(int var1, LongCollection var2);

   long set(int var1, long var2);

   default void replaceAll(java.util.function.LongUnaryOperator operator) {
      LongListIterator iter = this.listIterator();

      while(iter.hasNext()) {
         iter.set(operator.applyAsLong(iter.nextLong()));
      }

   }

   default void replaceAll(LongUnaryOperator operator) {
      this.replaceAll((java.util.function.LongUnaryOperator)operator);
   }

   /** @deprecated */
   @Deprecated
   default void replaceAll(UnaryOperator operator) {
      Objects.requireNonNull(operator);
      java.util.function.LongUnaryOperator var10001;
      if (operator instanceof java.util.function.LongUnaryOperator) {
         var10001 = (java.util.function.LongUnaryOperator)operator;
      } else {
         Objects.requireNonNull(operator);
         var10001 = operator::apply;
      }

      this.replaceAll(var10001);
   }

   long getLong(int var1);

   int indexOf(long var1);

   int lastIndexOf(long var1);

   /** @deprecated */
   @Deprecated
   default boolean contains(Object key) {
      return LongCollection.super.contains(key);
   }

   /** @deprecated */
   @Deprecated
   default Long get(int index) {
      return this.getLong(index);
   }

   /** @deprecated */
   @Deprecated
   default int indexOf(Object o) {
      return this.indexOf((Long)o);
   }

   /** @deprecated */
   @Deprecated
   default int lastIndexOf(Object o) {
      return this.lastIndexOf((Long)o);
   }

   /** @deprecated */
   @Deprecated
   default boolean add(Long k) {
      return this.add(k);
   }

   long removeLong(int var1);

   /** @deprecated */
   @Deprecated
   default boolean remove(Object key) {
      return LongCollection.super.remove(key);
   }

   /** @deprecated */
   @Deprecated
   default Long remove(int index) {
      return this.removeLong(index);
   }

   /** @deprecated */
   @Deprecated
   default Long set(int index, Long k) {
      return this.set(index, k);
   }

   default boolean addAll(int index, LongList l) {
      return this.addAll(index, (LongCollection)l);
   }

   default boolean addAll(LongList l) {
      return this.addAll(this.size(), l);
   }

   static LongList of() {
      return LongImmutableList.of();
   }

   static LongList of(long e) {
      return LongLists.singleton(e);
   }

   static LongList of(long e0, long e1) {
      return LongImmutableList.of(e0, e1);
   }

   static LongList of(long e0, long e1, long e2) {
      return LongImmutableList.of(e0, e1, e2);
   }

   static LongList of(long... a) {
      switch (a.length) {
         case 0:
            return of();
         case 1:
            return of(a[0]);
         default:
            return LongImmutableList.of(a);
      }
   }

   /** @deprecated */
   @Deprecated
   default void sort(Comparator comparator) {
      this.sort(LongComparators.asLongComparator(comparator));
   }

   default void sort(LongComparator comparator) {
      if (comparator == null) {
         this.unstableSort(comparator);
      } else {
         long[] elements = this.toLongArray();
         LongArrays.stableSort(elements, comparator);
         this.setElements(elements);
      }

   }

   /** @deprecated */
   @Deprecated
   default void unstableSort(Comparator comparator) {
      this.unstableSort(LongComparators.asLongComparator(comparator));
   }

   default void unstableSort(LongComparator comparator) {
      long[] elements = this.toLongArray();
      if (comparator == null) {
         LongArrays.unstableSort(elements);
      } else {
         LongArrays.unstableSort(elements, comparator);
      }

      this.setElements(elements);
   }
}
