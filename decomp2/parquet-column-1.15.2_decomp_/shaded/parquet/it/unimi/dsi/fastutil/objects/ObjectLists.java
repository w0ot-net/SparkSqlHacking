package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public final class ObjectLists {
   public static final EmptyList EMPTY_LIST = new EmptyList();

   private ObjectLists() {
   }

   public static ObjectList shuffle(ObjectList l, Random random) {
      int i = l.size();

      while(i-- != 0) {
         int p = random.nextInt(i + 1);
         K t = (K)l.get(i);
         l.set(i, l.get(p));
         l.set(p, t);
      }

      return l;
   }

   public static ObjectList emptyList() {
      return EMPTY_LIST;
   }

   public static ObjectList singleton(Object element) {
      return new Singleton(element);
   }

   public static ObjectList synchronize(ObjectList l) {
      return (ObjectList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l) : new SynchronizedList(l));
   }

   public static ObjectList synchronize(ObjectList l, Object sync) {
      return (ObjectList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l, sync) : new SynchronizedList(l, sync));
   }

   public static ObjectList unmodifiable(ObjectList l) {
      return (ObjectList)(l instanceof RandomAccess ? new UnmodifiableRandomAccessList(l) : new UnmodifiableList(l));
   }

   public static class EmptyList extends ObjectCollections.EmptyCollection implements ObjectList, RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyList() {
      }

      public Object get(int i) {
         throw new IndexOutOfBoundsException();
      }

      public boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      public Object remove(int i) {
         throw new UnsupportedOperationException();
      }

      public void add(int index, Object k) {
         throw new UnsupportedOperationException();
      }

      public Object set(int index, Object k) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(Object k) {
         return -1;
      }

      public int lastIndexOf(Object k) {
         return -1;
      }

      public boolean addAll(int i, Collection c) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void sort(Comparator comparator) {
      }

      public void unstableSort(Comparator comparator) {
      }

      public ObjectListIterator listIterator() {
         return ObjectIterators.EMPTY_ITERATOR;
      }

      public ObjectListIterator iterator() {
         return ObjectIterators.EMPTY_ITERATOR;
      }

      public ObjectListIterator listIterator(int i) {
         if (i == 0) {
            return ObjectIterators.EMPTY_ITERATOR;
         } else {
            throw new IndexOutOfBoundsException(String.valueOf(i));
         }
      }

      public ObjectList subList(int from, int to) {
         if (from == 0 && to == 0) {
            return this;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public void getElements(int from, Object[] a, int offset, int length) {
         if (from != 0 || length != 0 || offset < 0 || offset > a.length) {
            throw new IndexOutOfBoundsException();
         }
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, Object[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a, int offset, int length) {
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
         return ObjectLists.EMPTY_LIST;
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
         return ObjectLists.EMPTY_LIST;
      }
   }

   public static class Singleton extends AbstractObjectList implements RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      private final Object element;

      protected Singleton(Object element) {
         this.element = element;
      }

      public Object get(int i) {
         if (i == 0) {
            return this.element;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      public Object remove(int i) {
         throw new UnsupportedOperationException();
      }

      public boolean contains(Object k) {
         return Objects.equals(k, this.element);
      }

      public int indexOf(Object k) {
         return Objects.equals(k, this.element) ? 0 : -1;
      }

      public Object[] toArray() {
         return new Object[]{this.element};
      }

      public ObjectListIterator listIterator() {
         return ObjectIterators.singleton(this.element);
      }

      public ObjectListIterator iterator() {
         return this.listIterator();
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.singleton(this.element);
      }

      public ObjectListIterator listIterator(int i) {
         if (i <= 1 && i >= 0) {
            ObjectListIterator<K> l = this.listIterator();
            if (i == 1) {
               l.next();
            }

            return l;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public ObjectList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return (ObjectList)(from == 0 && to == 1 ? this : ObjectLists.EMPTY_LIST);
         }
      }

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

      public boolean removeIf(Predicate filter) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void sort(Comparator comparator) {
      }

      public void unstableSort(Comparator comparator) {
      }

      public void getElements(int from, Object[] a, int offset, int length) {
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

      public void addElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, Object[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void setElements(Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a, int offset, int length) {
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

   public static class SynchronizedList extends ObjectCollections.SynchronizedCollection implements ObjectList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final ObjectList list;

      protected SynchronizedList(ObjectList l, Object sync) {
         super(l, sync);
         this.list = l;
      }

      protected SynchronizedList(ObjectList l) {
         super(l);
         this.list = l;
      }

      public Object get(int i) {
         synchronized(this.sync) {
            return this.list.get(i);
         }
      }

      public Object set(int i, Object k) {
         synchronized(this.sync) {
            return this.list.set(i, k);
         }
      }

      public void add(int i, Object k) {
         synchronized(this.sync) {
            this.list.add(i, k);
         }
      }

      public Object remove(int i) {
         synchronized(this.sync) {
            return this.list.remove(i);
         }
      }

      public int indexOf(Object k) {
         synchronized(this.sync) {
            return this.list.indexOf(k);
         }
      }

      public int lastIndexOf(Object k) {
         synchronized(this.sync) {
            return this.list.lastIndexOf(k);
         }
      }

      public boolean removeIf(Predicate filter) {
         synchronized(this.sync) {
            return this.list.removeIf(filter);
         }
      }

      public void replaceAll(UnaryOperator operator) {
         synchronized(this.sync) {
            this.list.replaceAll(operator);
         }
      }

      public boolean addAll(int index, Collection c) {
         synchronized(this.sync) {
            return this.list.addAll(index, c);
         }
      }

      public void getElements(int from, Object[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.getElements(from, a, offset, length);
         }
      }

      public void removeElements(int from, int to) {
         synchronized(this.sync) {
            this.list.removeElements(from, to);
         }
      }

      public void addElements(int index, Object[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.addElements(index, a, offset, length);
         }
      }

      public void addElements(int index, Object[] a) {
         synchronized(this.sync) {
            this.list.addElements(index, a);
         }
      }

      public void setElements(Object[] a) {
         synchronized(this.sync) {
            this.list.setElements(a);
         }
      }

      public void setElements(int index, Object[] a) {
         synchronized(this.sync) {
            this.list.setElements(index, a);
         }
      }

      public void setElements(int index, Object[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.setElements(index, a, offset, length);
         }
      }

      public void size(int size) {
         synchronized(this.sync) {
            this.list.size(size);
         }
      }

      public ObjectListIterator listIterator() {
         return this.list.listIterator();
      }

      public ObjectListIterator iterator() {
         return this.listIterator();
      }

      public ObjectListIterator listIterator(int i) {
         return this.list.listIterator(i);
      }

      public ObjectList subList(int from, int to) {
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

      public void sort(Comparator comparator) {
         synchronized(this.sync) {
            this.list.sort(comparator);
         }
      }

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

      protected SynchronizedRandomAccessList(ObjectList l, Object sync) {
         super(l, sync);
      }

      protected SynchronizedRandomAccessList(ObjectList l) {
         super(l);
      }

      public ObjectList subList(int from, int to) {
         synchronized(this.sync) {
            return new SynchronizedRandomAccessList(this.list.subList(from, to), this.sync);
         }
      }
   }

   public static class UnmodifiableList extends ObjectCollections.UnmodifiableCollection implements ObjectList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final ObjectList list;

      protected UnmodifiableList(ObjectList l) {
         super(l);
         this.list = l;
      }

      public Object get(int i) {
         return this.list.get(i);
      }

      public Object set(int i, Object k) {
         throw new UnsupportedOperationException();
      }

      public void add(int i, Object k) {
         throw new UnsupportedOperationException();
      }

      public Object remove(int i) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(Object k) {
         return this.list.indexOf(k);
      }

      public int lastIndexOf(Object k) {
         return this.list.lastIndexOf(k);
      }

      public boolean addAll(int index, Collection c) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void getElements(int from, Object[] a, int offset, int length) {
         this.list.getElements(from, a, offset, length);
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, Object[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, Object[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void size(int size) {
         this.list.size(size);
      }

      public ObjectListIterator listIterator() {
         return ObjectIterators.unmodifiable(this.list.listIterator());
      }

      public ObjectListIterator iterator() {
         return this.listIterator();
      }

      public ObjectListIterator listIterator(int i) {
         return ObjectIterators.unmodifiable(this.list.listIterator(i));
      }

      public ObjectList subList(int from, int to) {
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

      public void sort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }

      public void unstableSort(Comparator comparator) {
         throw new UnsupportedOperationException();
      }
   }

   public static class UnmodifiableRandomAccessList extends UnmodifiableList implements RandomAccess, Serializable {
      private static final long serialVersionUID = 0L;

      protected UnmodifiableRandomAccessList(ObjectList l) {
         super(l);
      }

      public ObjectList subList(int from, int to) {
         return new UnmodifiableRandomAccessList(this.list.subList(from, to));
      }
   }

   abstract static class ImmutableListBase extends AbstractObjectList implements ObjectList {
      /** @deprecated */
      @Deprecated
      public final void add(int index, Object k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean add(Object k) {
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
      public final Object remove(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean remove(Object k) {
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
      public final void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final Object set(int index, Object k) {
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
      public final void addElements(int index, Object[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void setElements(int index, Object[] a, int offset, int length) {
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
