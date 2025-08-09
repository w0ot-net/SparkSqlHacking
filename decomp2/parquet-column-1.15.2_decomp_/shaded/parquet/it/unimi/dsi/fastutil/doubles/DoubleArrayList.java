package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.stream.DoubleStream;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public class DoubleArrayList extends AbstractDoubleList implements RandomAccess, Cloneable, Serializable {
   private static final long serialVersionUID = -7046029254386353130L;
   public static final int DEFAULT_INITIAL_CAPACITY = 10;
   protected transient double[] a;
   protected int size;

   private static final double[] copyArraySafe(double[] a, int length) {
      return length == 0 ? DoubleArrays.EMPTY_ARRAY : Arrays.copyOf(a, length);
   }

   private static final double[] copyArrayFromSafe(DoubleArrayList l) {
      return copyArraySafe(l.a, l.size);
   }

   protected DoubleArrayList(double[] a, boolean wrapped) {
      this.a = a;
   }

   private void initArrayFromCapacity(int capacity) {
      if (capacity < 0) {
         throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
      } else {
         if (capacity == 0) {
            this.a = DoubleArrays.EMPTY_ARRAY;
         } else {
            this.a = new double[capacity];
         }

      }
   }

   public DoubleArrayList(int capacity) {
      this.initArrayFromCapacity(capacity);
   }

   public DoubleArrayList() {
      this.a = DoubleArrays.DEFAULT_EMPTY_ARRAY;
   }

   public DoubleArrayList(Collection c) {
      if (c instanceof DoubleArrayList) {
         this.a = copyArrayFromSafe((DoubleArrayList)c);
         this.size = this.a.length;
      } else {
         this.initArrayFromCapacity(c.size());
         if (c instanceof DoubleList) {
            ((DoubleList)c).getElements(0, this.a, 0, this.size = c.size());
         } else {
            this.size = DoubleIterators.unwrap(DoubleIterators.asDoubleIterator(c.iterator()), this.a);
         }
      }

   }

   public DoubleArrayList(DoubleCollection c) {
      if (c instanceof DoubleArrayList) {
         this.a = copyArrayFromSafe((DoubleArrayList)c);
         this.size = this.a.length;
      } else {
         this.initArrayFromCapacity(c.size());
         if (c instanceof DoubleList) {
            ((DoubleList)c).getElements(0, this.a, 0, this.size = c.size());
         } else {
            this.size = DoubleIterators.unwrap(c.iterator(), this.a);
         }
      }

   }

   public DoubleArrayList(DoubleList l) {
      if (l instanceof DoubleArrayList) {
         this.a = copyArrayFromSafe((DoubleArrayList)l);
         this.size = this.a.length;
      } else {
         this.initArrayFromCapacity(l.size());
         l.getElements(0, this.a, 0, this.size = l.size());
      }

   }

   public DoubleArrayList(double[] a) {
      this(a, 0, a.length);
   }

   public DoubleArrayList(double[] a, int offset, int length) {
      this(length);
      System.arraycopy(a, offset, this.a, 0, length);
      this.size = length;
   }

   public DoubleArrayList(Iterator i) {
      this();

      while(i.hasNext()) {
         this.add((Double)i.next());
      }

   }

   public DoubleArrayList(DoubleIterator i) {
      this();

      while(i.hasNext()) {
         this.add(i.nextDouble());
      }

   }

   public double[] elements() {
      return this.a;
   }

   public static DoubleArrayList wrap(double[] a, int length) {
      if (length > a.length) {
         throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
      } else {
         DoubleArrayList l = new DoubleArrayList(a, true);
         l.size = length;
         return l;
      }
   }

   public static DoubleArrayList wrap(double[] a) {
      return wrap(a, a.length);
   }

   public static DoubleArrayList of() {
      return new DoubleArrayList();
   }

   public static DoubleArrayList of(double... init) {
      return wrap(init);
   }

   public static DoubleArrayList toList(DoubleStream stream) {
      return (DoubleArrayList)stream.collect(DoubleArrayList::new, DoubleArrayList::add, DoubleList::addAll);
   }

   public static DoubleArrayList toListWithExpectedSize(DoubleStream stream, int expectedSize) {
      return expectedSize <= 10 ? toList(stream) : (DoubleArrayList)stream.collect(new DoubleCollections.SizeDecreasingSupplier(expectedSize, (size) -> size <= 10 ? new DoubleArrayList() : new DoubleArrayList(size)), DoubleArrayList::add, DoubleList::addAll);
   }

   public void ensureCapacity(int capacity) {
      if (capacity > this.a.length && (this.a != DoubleArrays.DEFAULT_EMPTY_ARRAY || capacity > 10)) {
         this.a = DoubleArrays.ensureCapacity(this.a, capacity, this.size);

         assert this.size <= this.a.length;

      }
   }

   private void grow(int capacity) {
      if (capacity > this.a.length) {
         if (this.a != DoubleArrays.DEFAULT_EMPTY_ARRAY) {
            capacity = (int)Math.max(Math.min((long)this.a.length + (long)(this.a.length >> 1), 2147483639L), (long)capacity);
         } else if (capacity < 10) {
            capacity = 10;
         }

         this.a = DoubleArrays.forceCapacity(this.a, capacity, this.size);

         assert this.size <= this.a.length;

      }
   }

   public void add(int index, double k) {
      this.ensureIndex(index);
      this.grow(this.size + 1);
      if (index != this.size) {
         System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
      }

      this.a[index] = k;
      ++this.size;

      assert this.size <= this.a.length;

   }

   public boolean add(double k) {
      this.grow(this.size + 1);
      this.a[this.size++] = k;

      assert this.size <= this.a.length;

      return true;
   }

   public double getDouble(int index) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
      } else {
         return this.a[index];
      }
   }

   public int indexOf(double k) {
      for(int i = 0; i < this.size; ++i) {
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(double k) {
      int i = this.size;

      while(i-- != 0) {
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
            return i;
         }
      }

      return -1;
   }

   public double removeDouble(int index) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
      } else {
         double old = this.a[index];
         --this.size;
         if (index != this.size) {
            System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
         }

         assert this.size <= this.a.length;

         return old;
      }
   }

   public boolean rem(double k) {
      int index = this.indexOf(k);
      if (index == -1) {
         return false;
      } else {
         this.removeDouble(index);

         assert this.size <= this.a.length;

         return true;
      }
   }

   public double set(int index, double k) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
      } else {
         double old = this.a[index];
         this.a[index] = k;
         return old;
      }
   }

   public void clear() {
      this.size = 0;

      assert this.size <= this.a.length;

   }

   public int size() {
      return this.size;
   }

   public void size(int size) {
      if (size > this.a.length) {
         this.a = DoubleArrays.forceCapacity(this.a, size, this.size);
      }

      if (size > this.size) {
         Arrays.fill(this.a, this.size, size, (double)0.0F);
      }

      this.size = size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public void trim() {
      this.trim(0);
   }

   public void trim(int n) {
      if (n < this.a.length && this.size != this.a.length) {
         double[] t = new double[Math.max(n, this.size)];
         System.arraycopy(this.a, 0, t, 0, this.size);
         this.a = t;

         assert this.size <= this.a.length;

      }
   }

   public DoubleList subList(int from, int to) {
      if (from == 0 && to == this.size()) {
         return this;
      } else {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new SubList(from, to);
         }
      }
   }

   public void getElements(int from, double[] a, int offset, int length) {
      DoubleArrays.ensureOffsetLength(a, offset, length);
      System.arraycopy(this.a, from, a, offset, length);
   }

   public void removeElements(int from, int to) {
      shaded.parquet.it.unimi.dsi.fastutil.Arrays.ensureFromTo(this.size, from, to);
      System.arraycopy(this.a, to, this.a, from, this.size - to);
      this.size -= to - from;
   }

   public void addElements(int index, double[] a, int offset, int length) {
      this.ensureIndex(index);
      DoubleArrays.ensureOffsetLength(a, offset, length);
      this.grow(this.size + length);
      System.arraycopy(this.a, index, this.a, index + length, this.size - index);
      System.arraycopy(a, offset, this.a, index, length);
      this.size += length;
   }

   public void setElements(int index, double[] a, int offset, int length) {
      this.ensureIndex(index);
      DoubleArrays.ensureOffsetLength(a, offset, length);
      if (index + length > this.size) {
         throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size + ")");
      } else {
         System.arraycopy(a, offset, this.a, index, length);
      }
   }

   public void forEach(java.util.function.DoubleConsumer action) {
      for(int i = 0; i < this.size; ++i) {
         action.accept(this.a[i]);
      }

   }

   public boolean addAll(int index, DoubleCollection c) {
      if (c instanceof DoubleList) {
         return this.addAll(index, (DoubleList)c);
      } else {
         this.ensureIndex(index);
         int n = c.size();
         if (n == 0) {
            return false;
         } else {
            this.grow(this.size + n);
            System.arraycopy(this.a, index, this.a, index + n, this.size - index);
            DoubleIterator i = c.iterator();

            for(this.size += n; n-- != 0; this.a[index++] = i.nextDouble()) {
            }

            assert this.size <= this.a.length;

            return true;
         }
      }
   }

   public boolean addAll(int index, DoubleList l) {
      this.ensureIndex(index);
      int n = l.size();
      if (n == 0) {
         return false;
      } else {
         this.grow(this.size + n);
         System.arraycopy(this.a, index, this.a, index + n, this.size - index);
         l.getElements(0, this.a, index, n);
         this.size += n;

         assert this.size <= this.a.length;

         return true;
      }
   }

   public boolean removeAll(DoubleCollection c) {
      double[] a = this.a;
      int j = 0;

      for(int i = 0; i < this.size; ++i) {
         if (!c.contains(a[i])) {
            a[j++] = a[i];
         }
      }

      boolean modified = this.size != j;
      this.size = j;
      return modified;
   }

   public boolean removeIf(java.util.function.DoublePredicate filter) {
      double[] a = this.a;
      int j = 0;

      for(int i = 0; i < this.size; ++i) {
         if (!filter.test(a[i])) {
            a[j++] = a[i];
         }
      }

      boolean modified = this.size != j;
      this.size = j;
      return modified;
   }

   public double[] toArray(double[] a) {
      if (a == null || a.length < this.size) {
         a = Arrays.copyOf(a, this.size);
      }

      System.arraycopy(this.a, 0, a, 0, this.size);
      return a;
   }

   public DoubleListIterator listIterator(final int index) {
      this.ensureIndex(index);
      return new DoubleListIterator() {
         int pos = index;
         int last = -1;

         public boolean hasNext() {
            return this.pos < DoubleArrayList.this.size;
         }

         public boolean hasPrevious() {
            return this.pos > 0;
         }

         public double nextDouble() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return DoubleArrayList.this.a[this.last = this.pos++];
            }
         }

         public double previousDouble() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return DoubleArrayList.this.a[this.last = --this.pos];
            }
         }

         public int nextIndex() {
            return this.pos;
         }

         public int previousIndex() {
            return this.pos - 1;
         }

         public void add(double k) {
            DoubleArrayList.this.add(this.pos++, k);
            this.last = -1;
         }

         public void set(double k) {
            if (this.last == -1) {
               throw new IllegalStateException();
            } else {
               DoubleArrayList.this.set(this.last, k);
            }
         }

         public void remove() {
            if (this.last == -1) {
               throw new IllegalStateException();
            } else {
               DoubleArrayList.this.removeDouble(this.last);
               if (this.last < this.pos) {
                  --this.pos;
               }

               this.last = -1;
            }
         }

         public void forEachRemaining(java.util.function.DoubleConsumer action) {
            while(this.pos < DoubleArrayList.this.size) {
               action.accept(DoubleArrayList.this.a[this.last = this.pos++]);
            }

         }

         public int back(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int remaining = this.pos;
               if (n < remaining) {
                  this.pos -= n;
               } else {
                  n = remaining;
                  this.pos = 0;
               }

               this.last = this.pos;
               return n;
            }
         }

         public int skip(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int remaining = DoubleArrayList.this.size - this.pos;
               if (n < remaining) {
                  this.pos += n;
               } else {
                  n = remaining;
                  this.pos = DoubleArrayList.this.size;
               }

               this.last = this.pos - 1;
               return n;
            }
         }
      };
   }

   public DoubleSpliterator spliterator() {
      return new Spliterator();
   }

   public void sort(DoubleComparator comp) {
      if (comp == null) {
         DoubleArrays.stableSort(this.a, 0, this.size);
      } else {
         DoubleArrays.stableSort(this.a, 0, this.size, comp);
      }

   }

   public void unstableSort(DoubleComparator comp) {
      if (comp == null) {
         DoubleArrays.unstableSort(this.a, 0, this.size);
      } else {
         DoubleArrays.unstableSort(this.a, 0, this.size, comp);
      }

   }

   public DoubleArrayList clone() {
      DoubleArrayList cloned = null;
      if (this.getClass() == DoubleArrayList.class) {
         cloned = new DoubleArrayList(copyArraySafe(this.a, this.size), false);
         cloned.size = this.size;
      } else {
         try {
            cloned = (DoubleArrayList)super.clone();
         } catch (CloneNotSupportedException err) {
            throw new InternalError(err);
         }

         cloned.a = copyArraySafe(this.a, this.size);
      }

      return cloned;
   }

   public boolean equals(DoubleArrayList l) {
      if (l == this) {
         return true;
      } else {
         int s = this.size();
         if (s != l.size()) {
            return false;
         } else {
            double[] a1 = this.a;
            double[] a2 = l.a;
            if (a1 == a2 && s == l.size()) {
               return true;
            } else {
               while(s-- != 0) {
                  if (a1[s] != a2[s]) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o == null) {
         return false;
      } else if (!(o instanceof List)) {
         return false;
      } else if (o instanceof DoubleArrayList) {
         return this.equals((DoubleArrayList)o);
      } else {
         return o instanceof SubList ? ((SubList)o).equals(this) : super.equals(o);
      }
   }

   public int compareTo(DoubleArrayList l) {
      int s1 = this.size();
      int s2 = l.size();
      double[] a1 = this.a;
      double[] a2 = l.a;
      if (a1 == a2 && s1 == s2) {
         return 0;
      } else {
         int i;
         for(i = 0; i < s1 && i < s2; ++i) {
            double e1 = a1[i];
            double e2 = a2[i];
            int r;
            if ((r = Double.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i < s2 ? -1 : (i < s1 ? 1 : 0);
      }
   }

   public int compareTo(List l) {
      if (l instanceof DoubleArrayList) {
         return this.compareTo((DoubleArrayList)l);
      } else {
         return l instanceof SubList ? -((SubList)l).compareTo((List)this) : super.compareTo(l);
      }
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      s.defaultWriteObject();

      for(int i = 0; i < this.size; ++i) {
         s.writeDouble(this.a[i]);
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.a = new double[this.size];

      for(int i = 0; i < this.size; ++i) {
         this.a[i] = s.readDouble();
      }

   }

   private class SubList extends AbstractDoubleList.DoubleRandomAccessSubList {
      private static final long serialVersionUID = -3185226345314976296L;

      protected SubList(int from, int to) {
         super(DoubleArrayList.this, from, to);
      }

      private double[] getParentArray() {
         return DoubleArrayList.this.a;
      }

      public double getDouble(int i) {
         this.ensureRestrictedIndex(i);
         return DoubleArrayList.this.a[i + this.from];
      }

      public DoubleListIterator listIterator(int index) {
         return new SubListIterator(index);
      }

      public DoubleSpliterator spliterator() {
         return new SubListSpliterator();
      }

      boolean contentsEquals(double[] otherA, int otherAFrom, int otherATo) {
         if (DoubleArrayList.this.a == otherA && this.from == otherAFrom && this.to == otherATo) {
            return true;
         } else if (otherATo - otherAFrom != this.size()) {
            return false;
         } else {
            int pos = this.from;
            int otherPos = otherAFrom;

            while(pos < this.to) {
               if (DoubleArrayList.this.a[pos++] != otherA[otherPos++]) {
                  return false;
               }
            }

            return true;
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (o == null) {
            return false;
         } else if (!(o instanceof List)) {
            return false;
         } else if (o instanceof DoubleArrayList) {
            DoubleArrayList other = (DoubleArrayList)o;
            return this.contentsEquals(other.a, 0, other.size());
         } else if (o instanceof SubList) {
            SubList other = (SubList)o;
            return this.contentsEquals(other.getParentArray(), other.from, other.to);
         } else {
            return super.equals(o);
         }
      }

      int contentsCompareTo(double[] otherA, int otherAFrom, int otherATo) {
         if (DoubleArrayList.this.a == otherA && this.from == otherAFrom && this.to == otherATo) {
            return 0;
         } else {
            int i = this.from;

            for(int j = otherAFrom; i < this.to && i < otherATo; ++j) {
               double e1 = DoubleArrayList.this.a[i];
               double e2 = otherA[j];
               int r;
               if ((r = Double.compare(e1, e2)) != 0) {
                  return r;
               }

               ++i;
            }

            return i < otherATo ? -1 : (i < this.to ? 1 : 0);
         }
      }

      public int compareTo(List l) {
         if (l instanceof DoubleArrayList) {
            DoubleArrayList other = (DoubleArrayList)l;
            return this.contentsCompareTo(other.a, 0, other.size());
         } else if (l instanceof SubList) {
            SubList other = (SubList)l;
            return this.contentsCompareTo(other.getParentArray(), other.from, other.to);
         } else {
            return super.compareTo(l);
         }
      }

      private final class SubListIterator extends DoubleIterators.AbstractIndexBasedListIterator {
         SubListIterator(int index) {
            super(0, index);
         }

         protected final double get(int i) {
            return DoubleArrayList.this.a[SubList.this.from + i];
         }

         protected final void add(int i, double k) {
            SubList.this.add(i, k);
         }

         protected final void set(int i, double k) {
            SubList.this.set(i, k);
         }

         protected final void remove(int i) {
            SubList.this.removeDouble(i);
         }

         protected final int getMaxPos() {
            return SubList.this.to - SubList.this.from;
         }

         public double nextDouble() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return DoubleArrayList.this.a[SubList.this.from + (this.lastReturned = this.pos++)];
            }
         }

         public double previousDouble() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return DoubleArrayList.this.a[SubList.this.from + (this.lastReturned = --this.pos)];
            }
         }

         public void forEachRemaining(java.util.function.DoubleConsumer action) {
            int max = SubList.this.to - SubList.this.from;

            while(this.pos < max) {
               action.accept(DoubleArrayList.this.a[SubList.this.from + (this.lastReturned = this.pos++)]);
            }

         }
      }

      private final class SubListSpliterator extends DoubleSpliterators.LateBindingSizeIndexBasedSpliterator {
         SubListSpliterator() {
            super(SubList.this.from);
         }

         private SubListSpliterator(int pos, int maxPos) {
            super(pos, maxPos);
         }

         protected final int getMaxPosFromBackingStore() {
            return SubList.this.to;
         }

         protected final double get(int i) {
            return DoubleArrayList.this.a[i];
         }

         protected final SubListSpliterator makeForSplit(int pos, int maxPos) {
            return SubList.this.new SubListSpliterator(pos, maxPos);
         }

         public boolean tryAdvance(java.util.function.DoubleConsumer action) {
            if (this.pos >= this.getMaxPos()) {
               return false;
            } else {
               action.accept(DoubleArrayList.this.a[this.pos++]);
               return true;
            }
         }

         public void forEachRemaining(java.util.function.DoubleConsumer action) {
            int max = this.getMaxPos();

            while(this.pos < max) {
               action.accept(DoubleArrayList.this.a[this.pos++]);
            }

         }
      }
   }

   private final class Spliterator implements DoubleSpliterator {
      boolean hasSplit;
      int pos;
      int max;

      public Spliterator() {
         this(0, DoubleArrayList.this.size, false);
      }

      private Spliterator(int pos, int max, boolean hasSplit) {
         this.hasSplit = false;

         assert pos <= max : "pos " + pos + " must be <= max " + max;

         this.pos = pos;
         this.max = max;
         this.hasSplit = hasSplit;
      }

      private int getWorkingMax() {
         return this.hasSplit ? this.max : DoubleArrayList.this.size;
      }

      public int characteristics() {
         return 16720;
      }

      public long estimateSize() {
         return (long)(this.getWorkingMax() - this.pos);
      }

      public boolean tryAdvance(java.util.function.DoubleConsumer action) {
         if (this.pos >= this.getWorkingMax()) {
            return false;
         } else {
            action.accept(DoubleArrayList.this.a[this.pos++]);
            return true;
         }
      }

      public void forEachRemaining(java.util.function.DoubleConsumer action) {
         for(int max = this.getWorkingMax(); this.pos < max; ++this.pos) {
            action.accept(DoubleArrayList.this.a[this.pos]);
         }

      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else {
            int max = this.getWorkingMax();
            if (this.pos >= max) {
               return 0L;
            } else {
               int remaining = max - this.pos;
               if (n < (long)remaining) {
                  this.pos = SafeMath.safeLongToInt((long)this.pos + n);
                  return n;
               } else {
                  n = (long)remaining;
                  this.pos = max;
                  return n;
               }
            }
         }
      }

      public DoubleSpliterator trySplit() {
         int max = this.getWorkingMax();
         int retLen = max - this.pos >> 1;
         if (retLen <= 1) {
            return null;
         } else {
            this.max = max;
            int myNewPos = this.pos + retLen;
            int oldPos = this.pos;
            this.pos = myNewPos;
            this.hasSplit = true;
            return DoubleArrayList.this.new Spliterator(oldPos, myNewPos, true);
         }
      }
   }
}
