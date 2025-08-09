package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public final class BooleanLists {
   public static final EmptyList EMPTY_LIST = new EmptyList();

   private BooleanLists() {
   }

   public static BooleanList shuffle(BooleanList l, Random random) {
      int i = l.size();

      while(i-- != 0) {
         int p = random.nextInt(i + 1);
         boolean t = l.getBoolean(i);
         l.set(i, l.getBoolean(p));
         l.set(p, t);
      }

      return l;
   }

   public static BooleanList emptyList() {
      return EMPTY_LIST;
   }

   public static BooleanList singleton(boolean element) {
      return new Singleton(element);
   }

   public static BooleanList singleton(Object element) {
      return new Singleton((Boolean)element);
   }

   public static BooleanList synchronize(BooleanList l) {
      return (BooleanList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l) : new SynchronizedList(l));
   }

   public static BooleanList synchronize(BooleanList l, Object sync) {
      return (BooleanList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l, sync) : new SynchronizedList(l, sync));
   }

   public static BooleanList unmodifiable(BooleanList l) {
      return (BooleanList)(l instanceof RandomAccess ? new UnmodifiableRandomAccessList(l) : new UnmodifiableList(l));
   }

   public static class EmptyList extends BooleanCollections.EmptyCollection implements BooleanList, RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyList() {
      }

      public boolean getBoolean(int i) {
         throw new IndexOutOfBoundsException();
      }

      public boolean rem(boolean k) {
         throw new UnsupportedOperationException();
      }

      public boolean removeBoolean(int i) {
         throw new UnsupportedOperationException();
      }

      public void add(int index, boolean k) {
         throw new UnsupportedOperationException();
      }

      public boolean set(int index, boolean k) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(boolean k) {
         return -1;
      }

      public int lastIndexOf(boolean k) {
         return -1;
      }

      public boolean addAll(int i, Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(BooleanUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(BooleanList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, BooleanList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void add(int index, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean get(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean set(int index, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean remove(int k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public int indexOf(Object k) {
         return -1;
      }

      /** @deprecated */
      @Deprecated
      public int lastIndexOf(Object k) {
         return -1;
      }

      public void sort(BooleanComparator comparator) {
      }

      public void unstableSort(BooleanComparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
      }

      public BooleanListIterator listIterator() {
         return BooleanIterators.EMPTY_ITERATOR;
      }

      public BooleanListIterator iterator() {
         return BooleanIterators.EMPTY_ITERATOR;
      }

      public BooleanListIterator listIterator(int i) {
         if (i == 0) {
            return BooleanIterators.EMPTY_ITERATOR;
         } else {
            throw new IndexOutOfBoundsException(String.valueOf(i));
         }
      }

      public BooleanList subList(int from, int to) {
         if (from == 0 && to == 0) {
            return this;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public void getElements(int from, boolean[] a, int offset, int length) {
         if (from != 0 || length != 0 || offset < 0 || offset > a.length) {
            throw new IndexOutOfBoundsException();
         }
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void size(int s) {
         throw new UnsupportedOperationException();
      }

      public int compareTo(List o) {
         if (o == this) {
            return 0;
         } else {
            return o.isEmpty() ? 0 : -1;
         }
      }

      public Object clone() {
         return BooleanLists.EMPTY_LIST;
      }

      public int hashCode() {
         return 1;
      }

      public boolean equals(Object o) {
         return o instanceof List && ((List)o).isEmpty();
      }

      public String toString() {
         return "[]";
      }

      private Object readResolve() {
         return BooleanLists.EMPTY_LIST;
      }
   }

   public static class Singleton extends AbstractBooleanList implements RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      private final boolean element;

      protected Singleton(boolean element) {
         this.element = element;
      }

      public boolean getBoolean(int i) {
         if (i == 0) {
            return this.element;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public boolean rem(boolean k) {
         throw new UnsupportedOperationException();
      }

      public boolean removeBoolean(int i) {
         throw new UnsupportedOperationException();
      }

      public boolean contains(boolean k) {
         return k == this.element;
      }

      public int indexOf(boolean k) {
         return k == this.element ? 0 : -1;
      }

      public boolean[] toBooleanArray() {
         return new boolean[]{this.element};
      }

      public BooleanListIterator listIterator() {
         return BooleanIterators.singleton(this.element);
      }

      public BooleanListIterator iterator() {
         return this.listIterator();
      }

      public BooleanSpliterator spliterator() {
         return BooleanSpliterators.singleton(this.element);
      }

      public BooleanListIterator listIterator(int i) {
         if (i <= 1 && i >= 0) {
            BooleanListIterator l = this.listIterator();
            if (i == 1) {
               l.nextBoolean();
            }

            return l;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public BooleanList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return (BooleanList)(from == 0 && to == 1 ? this : BooleanLists.EMPTY_LIST);
         }
      }

      /** @deprecated */
      @Deprecated
      public void forEach(Consumer action) {
         action.accept(this.element);
      }

      public boolean addAll(int i, Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean removeIf(Predicate filter) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(BooleanUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void forEach(BooleanConsumer action) {
         action.accept(this.element);
      }

      public boolean addAll(BooleanList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, BooleanList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Object[] toArray() {
         return new Object[]{this.element};
      }

      public void sort(BooleanComparator comparator) {
      }

      public void unstableSort(BooleanComparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
      }

      public void getElements(int from, boolean[] a, int offset, int length) {
         if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
         } else if (offset + length > a.length) {
            throw new ArrayIndexOutOfBoundsException("End index (" + (offset + length) + ") is greater than array length (" + a.length + ")");
         } else if (from + length > this.size()) {
            throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + this.size() + ")");
         } else if (length > 0) {
            a[offset] = this.element;
         }
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void setElements(boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return 1;
      }

      public void size(int size) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public Object clone() {
         return this;
      }
   }

   public static class SynchronizedList extends BooleanCollections.SynchronizedCollection implements BooleanList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanList list;

      protected SynchronizedList(BooleanList l, Object sync) {
         super(l, sync);
         this.list = l;
      }

      protected SynchronizedList(BooleanList l) {
         super(l);
         this.list = l;
      }

      public boolean getBoolean(int i) {
         synchronized(this.sync) {
            return this.list.getBoolean(i);
         }
      }

      public boolean set(int i, boolean k) {
         synchronized(this.sync) {
            return this.list.set(i, k);
         }
      }

      public void add(int i, boolean k) {
         synchronized(this.sync) {
            this.list.add(i, k);
         }
      }

      public boolean removeBoolean(int i) {
         synchronized(this.sync) {
            return this.list.removeBoolean(i);
         }
      }

      public int indexOf(boolean k) {
         synchronized(this.sync) {
            return this.list.indexOf(k);
         }
      }

      public int lastIndexOf(boolean k) {
         synchronized(this.sync) {
            return this.list.lastIndexOf(k);
         }
      }

      public boolean removeIf(BooleanPredicate filter) {
         synchronized(this.sync) {
            return this.list.removeIf(filter);
         }
      }

      public void replaceAll(BooleanUnaryOperator operator) {
         synchronized(this.sync) {
            this.list.replaceAll(operator);
         }
      }

      public boolean addAll(int index, Collection c) {
         synchronized(this.sync) {
            return this.list.addAll(index, (Collection)c);
         }
      }

      public void getElements(int from, boolean[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.getElements(from, a, offset, length);
         }
      }

      public void removeElements(int from, int to) {
         synchronized(this.sync) {
            this.list.removeElements(from, to);
         }
      }

      public void addElements(int index, boolean[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.addElements(index, a, offset, length);
         }
      }

      public void addElements(int index, boolean[] a) {
         synchronized(this.sync) {
            this.list.addElements(index, a);
         }
      }

      public void setElements(boolean[] a) {
         synchronized(this.sync) {
            this.list.setElements(a);
         }
      }

      public void setElements(int index, boolean[] a) {
         synchronized(this.sync) {
            this.list.setElements(index, a);
         }
      }

      public void setElements(int index, boolean[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.setElements(index, a, offset, length);
         }
      }

      public void size(int size) {
         synchronized(this.sync) {
            this.list.size(size);
         }
      }

      public BooleanListIterator listIterator() {
         return this.list.listIterator();
      }

      public BooleanListIterator iterator() {
         return this.listIterator();
      }

      public BooleanListIterator listIterator(int i) {
         return this.list.listIterator(i);
      }

      public BooleanList subList(int from, int to) {
         synchronized(this.sync) {
            return new SynchronizedList(this.list.subList(from, to), this.sync);
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.sync) {
               return this.collection.equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.sync) {
            return this.collection.hashCode();
         }
      }

      public int compareTo(List o) {
         synchronized(this.sync) {
            return this.list.compareTo(o);
         }
      }

      public boolean addAll(int index, BooleanCollection c) {
         synchronized(this.sync) {
            return this.list.addAll(index, c);
         }
      }

      public boolean addAll(int index, BooleanList l) {
         synchronized(this.sync) {
            return this.list.addAll(index, l);
         }
      }

      public boolean addAll(BooleanList l) {
         synchronized(this.sync) {
            return this.list.addAll(l);
         }
      }

      /** @deprecated */
      @Deprecated
      public Boolean get(int i) {
         synchronized(this.sync) {
            return this.list.get(i);
         }
      }

      /** @deprecated */
      @Deprecated
      public void add(int i, Boolean k) {
         synchronized(this.sync) {
            this.list.add(i, k);
         }
      }

      /** @deprecated */
      @Deprecated
      public Boolean set(int index, Boolean k) {
         synchronized(this.sync) {
            return this.list.set(index, k);
         }
      }

      /** @deprecated */
      @Deprecated
      public Boolean remove(int i) {
         synchronized(this.sync) {
            return this.list.remove(i);
         }
      }

      /** @deprecated */
      @Deprecated
      public int indexOf(Object o) {
         synchronized(this.sync) {
            return this.list.indexOf(o);
         }
      }

      /** @deprecated */
      @Deprecated
      public int lastIndexOf(Object o) {
         synchronized(this.sync) {
            return this.list.lastIndexOf(o);
         }
      }

      public void sort(BooleanComparator comparator) {
         synchronized(this.sync) {
            this.list.sort(comparator);
         }
      }

      public void unstableSort(BooleanComparator comparator) {
         synchronized(this.sync) {
            this.list.unstableSort(comparator);
         }
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
         synchronized(this.sync) {
            this.list.sort(comparator);
         }
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
         synchronized(this.sync) {
            this.list.unstableSort(comparator);
         }
      }

      private void writeObject(ObjectOutputStream s) throws IOException {
         synchronized(this.sync) {
            s.defaultWriteObject();
         }
      }
   }

   public static class SynchronizedRandomAccessList extends SynchronizedList implements RandomAccess, Serializable {
      private static final long serialVersionUID = 0L;

      protected SynchronizedRandomAccessList(BooleanList l, Object sync) {
         super(l, sync);
      }

      protected SynchronizedRandomAccessList(BooleanList l) {
         super(l);
      }

      public BooleanList subList(int from, int to) {
         synchronized(this.sync) {
            return new SynchronizedRandomAccessList(this.list.subList(from, to), this.sync);
         }
      }
   }

   public static class UnmodifiableList extends BooleanCollections.UnmodifiableCollection implements BooleanList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanList list;

      protected UnmodifiableList(BooleanList l) {
         super(l);
         this.list = l;
      }

      public boolean getBoolean(int i) {
         return this.list.getBoolean(i);
      }

      public boolean set(int i, boolean k) {
         throw new UnsupportedOperationException();
      }

      public void add(int i, boolean k) {
         throw new UnsupportedOperationException();
      }

      public boolean removeBoolean(int i) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(boolean k) {
         return this.list.indexOf(k);
      }

      public int lastIndexOf(boolean k) {
         return this.list.lastIndexOf(k);
      }

      public boolean addAll(int index, Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void getElements(int from, boolean[] a, int offset, int length) {
         this.list.getElements(from, a, offset, length);
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void size(int size) {
         this.list.size(size);
      }

      public BooleanListIterator listIterator() {
         return BooleanIterators.unmodifiable(this.list.listIterator());
      }

      public BooleanListIterator iterator() {
         return this.listIterator();
      }

      public BooleanListIterator listIterator(int i) {
         return BooleanIterators.unmodifiable(this.list.listIterator(i));
      }

      public BooleanList subList(int from, int to) {
         return new UnmodifiableList(this.list.subList(from, to));
      }

      public boolean equals(Object o) {
         return o == this ? true : this.collection.equals(o);
      }

      public int hashCode() {
         return this.collection.hashCode();
      }

      public int compareTo(List o) {
         return this.list.compareTo(o);
      }

      public boolean addAll(int index, BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(BooleanList l) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int index, BooleanList l) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean get(int i) {
         return this.list.get(i);
      }

      /** @deprecated */
      @Deprecated
      public void add(int i, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean set(int index, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Boolean remove(int i) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public int indexOf(Object o) {
         return this.list.indexOf(o);
      }

      /** @deprecated */
      @Deprecated
      public int lastIndexOf(Object o) {
         return this.list.lastIndexOf(o);
      }

      public void sort(BooleanComparator comparator) {
         throw new UnsupportedOperationException();
      }

      public void unstableSort(BooleanComparator comparator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }
   }

   public static class UnmodifiableRandomAccessList extends UnmodifiableList implements RandomAccess, Serializable {
      private static final long serialVersionUID = 0L;

      protected UnmodifiableRandomAccessList(BooleanList l) {
         super(l);
      }

      public BooleanList subList(int from, int to) {
         return new UnmodifiableRandomAccessList(this.list.subList(from, to));
      }
   }

   abstract static class ImmutableListBase extends AbstractBooleanList implements BooleanList {
      /** @deprecated */
      @Deprecated
      public final void add(int index, boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean add(boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(int index, Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeBoolean(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean rem(boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean retainAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeIf(Predicate c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeIf(BooleanPredicate c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void add(int index, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean add(Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final Boolean remove(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final Boolean set(int index, Boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(BooleanList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(int index, BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(int index, BooleanList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean retainAll(BooleanCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean set(int index, boolean k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void clear() {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void size(int size) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void addElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void setElements(int index, boolean[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void sort(BooleanComparator comp) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void unstableSort(BooleanComparator comp) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void sort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void unstableSort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }
   }
}
