package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterators;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterators;

public final class FloatLists {
   public static final EmptyList EMPTY_LIST = new EmptyList();

   private FloatLists() {
   }

   public static FloatList shuffle(FloatList l, Random random) {
      int i = l.size();

      while(i-- != 0) {
         int p = random.nextInt(i + 1);
         float t = l.getFloat(i);
         l.set(i, l.getFloat(p));
         l.set(p, t);
      }

      return l;
   }

   public static FloatList emptyList() {
      return EMPTY_LIST;
   }

   public static FloatList singleton(float element) {
      return new Singleton(element);
   }

   public static FloatList singleton(Object element) {
      return new Singleton((Float)element);
   }

   public static FloatList synchronize(FloatList l) {
      return (FloatList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l) : new SynchronizedList(l));
   }

   public static FloatList synchronize(FloatList l, Object sync) {
      return (FloatList)(l instanceof RandomAccess ? new SynchronizedRandomAccessList(l, sync) : new SynchronizedList(l, sync));
   }

   public static FloatList unmodifiable(FloatList l) {
      return (FloatList)(l instanceof RandomAccess ? new UnmodifiableRandomAccessList(l) : new UnmodifiableList(l));
   }

   public static class EmptyList extends FloatCollections.EmptyCollection implements FloatList, RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyList() {
      }

      public float getFloat(int i) {
         throw new IndexOutOfBoundsException();
      }

      public boolean rem(float k) {
         throw new UnsupportedOperationException();
      }

      public float removeFloat(int i) {
         throw new UnsupportedOperationException();
      }

      public void add(int index, float k) {
         throw new UnsupportedOperationException();
      }

      public float set(int index, float k) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(float k) {
         return -1;
      }

      public int lastIndexOf(float k) {
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

      public void replaceAll(FloatUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(FloatList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, FloatList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public void add(int index, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float get(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean add(Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float set(int index, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float remove(int k) {
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

      public void sort(FloatComparator comparator) {
      }

      public void unstableSort(FloatComparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
      }

      public FloatListIterator listIterator() {
         return FloatIterators.EMPTY_ITERATOR;
      }

      public FloatListIterator iterator() {
         return FloatIterators.EMPTY_ITERATOR;
      }

      public FloatListIterator listIterator(int i) {
         if (i == 0) {
            return FloatIterators.EMPTY_ITERATOR;
         } else {
            throw new IndexOutOfBoundsException(String.valueOf(i));
         }
      }

      public FloatList subList(int from, int to) {
         if (from == 0 && to == 0) {
            return this;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public void getElements(int from, float[] a, int offset, int length) {
         if (from != 0 || length != 0 || offset < 0 || offset > a.length) {
            throw new IndexOutOfBoundsException();
         }
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a, int offset, int length) {
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
         return FloatLists.EMPTY_LIST;
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
         return FloatLists.EMPTY_LIST;
      }
   }

   public static class Singleton extends AbstractFloatList implements RandomAccess, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      private final float element;

      protected Singleton(float element) {
         this.element = element;
      }

      public float getFloat(int i) {
         if (i == 0) {
            return this.element;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public boolean rem(float k) {
         throw new UnsupportedOperationException();
      }

      public float removeFloat(int i) {
         throw new UnsupportedOperationException();
      }

      public boolean contains(float k) {
         return Float.floatToIntBits(k) == Float.floatToIntBits(this.element);
      }

      public int indexOf(float k) {
         return Float.floatToIntBits(k) == Float.floatToIntBits(this.element) ? 0 : -1;
      }

      public float[] toFloatArray() {
         return new float[]{this.element};
      }

      public FloatListIterator listIterator() {
         return FloatIterators.singleton(this.element);
      }

      public FloatListIterator iterator() {
         return this.listIterator();
      }

      public FloatSpliterator spliterator() {
         return FloatSpliterators.singleton(this.element);
      }

      public FloatListIterator listIterator(int i) {
         if (i <= 1 && i >= 0) {
            FloatListIterator l = this.listIterator();
            if (i == 1) {
               l.nextFloat();
            }

            return l;
         } else {
            throw new IndexOutOfBoundsException();
         }
      }

      public FloatList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return (FloatList)(from == 0 && to == 1 ? this : FloatLists.EMPTY_LIST);
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

      public void replaceAll(FloatUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      public void forEach(FloatConsumer action) {
         action.accept(this.element);
      }

      public boolean addAll(FloatList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, FloatList c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int i, FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(FloatPredicate filter) {
         throw new UnsupportedOperationException();
      }

      public DoubleIterator doubleIterator() {
         return DoubleIterators.singleton((double)this.element);
      }

      public DoubleSpliterator doubleSpliterator() {
         return DoubleSpliterators.singleton((double)this.element);
      }

      /** @deprecated */
      @Deprecated
      public Object[] toArray() {
         return new Object[]{this.element};
      }

      public void sort(FloatComparator comparator) {
      }

      public void unstableSort(FloatComparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void sort(Comparator comparator) {
      }

      /** @deprecated */
      @Deprecated
      public void unstableSort(Comparator comparator) {
      }

      public void getElements(int from, float[] a, int offset, int length) {
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

      public void addElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void setElements(float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a, int offset, int length) {
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

   public static class SynchronizedList extends FloatCollections.SynchronizedCollection implements FloatList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatList list;

      protected SynchronizedList(FloatList l, Object sync) {
         super(l, sync);
         this.list = l;
      }

      protected SynchronizedList(FloatList l) {
         super(l);
         this.list = l;
      }

      public float getFloat(int i) {
         synchronized(this.sync) {
            return this.list.getFloat(i);
         }
      }

      public float set(int i, float k) {
         synchronized(this.sync) {
            return this.list.set(i, k);
         }
      }

      public void add(int i, float k) {
         synchronized(this.sync) {
            this.list.add(i, k);
         }
      }

      public float removeFloat(int i) {
         synchronized(this.sync) {
            return this.list.removeFloat(i);
         }
      }

      public int indexOf(float k) {
         synchronized(this.sync) {
            return this.list.indexOf(k);
         }
      }

      public int lastIndexOf(float k) {
         synchronized(this.sync) {
            return this.list.lastIndexOf(k);
         }
      }

      public boolean removeIf(FloatPredicate filter) {
         synchronized(this.sync) {
            return this.list.removeIf(filter);
         }
      }

      public void replaceAll(FloatUnaryOperator operator) {
         synchronized(this.sync) {
            this.list.replaceAll(operator);
         }
      }

      public boolean addAll(int index, Collection c) {
         synchronized(this.sync) {
            return this.list.addAll(index, (Collection)c);
         }
      }

      public void getElements(int from, float[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.getElements(from, a, offset, length);
         }
      }

      public void removeElements(int from, int to) {
         synchronized(this.sync) {
            this.list.removeElements(from, to);
         }
      }

      public void addElements(int index, float[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.addElements(index, a, offset, length);
         }
      }

      public void addElements(int index, float[] a) {
         synchronized(this.sync) {
            this.list.addElements(index, a);
         }
      }

      public void setElements(float[] a) {
         synchronized(this.sync) {
            this.list.setElements(a);
         }
      }

      public void setElements(int index, float[] a) {
         synchronized(this.sync) {
            this.list.setElements(index, a);
         }
      }

      public void setElements(int index, float[] a, int offset, int length) {
         synchronized(this.sync) {
            this.list.setElements(index, a, offset, length);
         }
      }

      public void size(int size) {
         synchronized(this.sync) {
            this.list.size(size);
         }
      }

      public FloatListIterator listIterator() {
         return this.list.listIterator();
      }

      public FloatListIterator iterator() {
         return this.listIterator();
      }

      public FloatListIterator listIterator(int i) {
         return this.list.listIterator(i);
      }

      public FloatList subList(int from, int to) {
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

      public boolean addAll(int index, FloatCollection c) {
         synchronized(this.sync) {
            return this.list.addAll(index, c);
         }
      }

      public boolean addAll(int index, FloatList l) {
         synchronized(this.sync) {
            return this.list.addAll(index, l);
         }
      }

      public boolean addAll(FloatList l) {
         synchronized(this.sync) {
            return this.list.addAll(l);
         }
      }

      /** @deprecated */
      @Deprecated
      public Float get(int i) {
         synchronized(this.sync) {
            return this.list.get(i);
         }
      }

      /** @deprecated */
      @Deprecated
      public void add(int i, Float k) {
         synchronized(this.sync) {
            this.list.add(i, k);
         }
      }

      /** @deprecated */
      @Deprecated
      public Float set(int index, Float k) {
         synchronized(this.sync) {
            return this.list.set(index, k);
         }
      }

      /** @deprecated */
      @Deprecated
      public Float remove(int i) {
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

      public void sort(FloatComparator comparator) {
         synchronized(this.sync) {
            this.list.sort(comparator);
         }
      }

      public void unstableSort(FloatComparator comparator) {
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

      protected SynchronizedRandomAccessList(FloatList l, Object sync) {
         super(l, sync);
      }

      protected SynchronizedRandomAccessList(FloatList l) {
         super(l);
      }

      public FloatList subList(int from, int to) {
         synchronized(this.sync) {
            return new SynchronizedRandomAccessList(this.list.subList(from, to), this.sync);
         }
      }
   }

   public static class UnmodifiableList extends FloatCollections.UnmodifiableCollection implements FloatList, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatList list;

      protected UnmodifiableList(FloatList l) {
         super(l);
         this.list = l;
      }

      public float getFloat(int i) {
         return this.list.getFloat(i);
      }

      public float set(int i, float k) {
         throw new UnsupportedOperationException();
      }

      public void add(int i, float k) {
         throw new UnsupportedOperationException();
      }

      public float removeFloat(int i) {
         throw new UnsupportedOperationException();
      }

      public int indexOf(float k) {
         return this.list.indexOf(k);
      }

      public int lastIndexOf(float k) {
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

      public void getElements(int from, float[] a, int offset, int length) {
         this.list.getElements(from, a, offset, length);
      }

      public void removeElements(int from, int to) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void addElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a) {
         throw new UnsupportedOperationException();
      }

      public void setElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      public void size(int size) {
         this.list.size(size);
      }

      public FloatListIterator listIterator() {
         return FloatIterators.unmodifiable(this.list.listIterator());
      }

      public FloatListIterator iterator() {
         return this.listIterator();
      }

      public FloatListIterator listIterator(int i) {
         return FloatIterators.unmodifiable(this.list.listIterator(i));
      }

      public FloatList subList(int from, int to) {
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

      public boolean addAll(int index, FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(FloatList l) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(int index, FloatList l) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(DoubleUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float get(int i) {
         return this.list.get(i);
      }

      /** @deprecated */
      @Deprecated
      public void add(int i, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float set(int index, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Float remove(int i) {
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

      public void sort(FloatComparator comparator) {
         throw new UnsupportedOperationException();
      }

      public void unstableSort(FloatComparator comparator) {
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

      protected UnmodifiableRandomAccessList(FloatList l) {
         super(l);
      }

      public FloatList subList(int from, int to) {
         return new UnmodifiableRandomAccessList(this.list.subList(from, to));
      }
   }

   abstract static class ImmutableListBase extends AbstractFloatList implements FloatList {
      /** @deprecated */
      @Deprecated
      public final void add(int index, float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean add(float k) {
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
      public final float removeFloat(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean rem(float k) {
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
      public final boolean removeIf(FloatPredicate c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void replaceAll(UnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void replaceAll(DoubleUnaryOperator operator) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void add(int index, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean add(Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final Float remove(int index) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean remove(Object k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final Float set(int index, Float k) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(FloatList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(int index, FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean addAll(int index, FloatList c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean removeAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final boolean retainAll(FloatCollection c) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final float set(int index, float k) {
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
      public final void addElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void setElements(int index, float[] a, int offset, int length) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void sort(FloatComparator comp) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public final void unstableSort(FloatComparator comp) {
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
