package javolution.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.context.PersistentContext;
import javolution.lang.MathLib;
import javolution.lang.Reusable;

public class FastTable extends FastCollection implements List, Reusable, RandomAccess {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new FastTable();
      }
   };
   private static final int B0 = 4;
   private static final int C0 = 16;
   private static final int B1 = 10;
   private static final int C1 = 1024;
   private static final int M1 = 1023;
   private transient Object[] _low;
   private transient Object[][] _high;
   private transient int _capacity;
   private transient int _size;
   private transient FastComparator _valueComparator;
   private static final Object[] NULL_BLOCK = new Object[1024];
   private static final long serialVersionUID = 1L;

   public FastTable() {
      this._valueComparator = FastComparator.DEFAULT;
      this._capacity = 16;
      this._low = new Object[16];
      this._high = new Object[1][];
      this._high[0] = this._low;
   }

   public FastTable(String id) {
      // $FF: Couldn't be decompiled
   }

   public FastTable(int capacity) {
      this();

      while(capacity > this._capacity) {
         this.increaseCapacity();
      }

   }

   public FastTable(Collection values) {
      this(values.size());
      this.addAll(values);
   }

   public static FastTable newInstance() {
      return (FastTable)FACTORY.object();
   }

   public static void recycle(FastTable instance) {
      FACTORY.recycle(instance);
   }

   public void setSize(int size) {
      while(this._size < size) {
         this.addLast((Object)null);
      }

      while(this._size > size) {
         this.removeLast();
      }

   }

   public final Object get(int index) {
      if (index >= this._size) {
         throw new IndexOutOfBoundsException();
      } else {
         return index < 1024 ? this._low[index] : this._high[index >> 10][index & 1023];
      }
   }

   public final Object set(int index, Object value) {
      if (index >= this._size) {
         throw new IndexOutOfBoundsException();
      } else {
         E[] low = (E[])this._high[index >> 10];
         E previous = (E)low[index & 1023];
         low[index & 1023] = value;
         return previous;
      }
   }

   public final boolean add(Object value) {
      if (this._size >= this._capacity) {
         this.increaseCapacity();
      }

      this._high[this._size >> 10][this._size & 1023] = value;
      ++this._size;
      return true;
   }

   public final Object getFirst() {
      if (this._size == 0) {
         throw new NoSuchElementException();
      } else {
         return this._low[0];
      }
   }

   public final Object getLast() {
      if (this._size == 0) {
         throw new NoSuchElementException();
      } else {
         return this.get(this._size - 1);
      }
   }

   public final void addLast(Object value) {
      this.add(value);
   }

   public final Object removeLast() {
      if (this._size == 0) {
         throw new NoSuchElementException();
      } else {
         --this._size;
         E[] low = (E[])this._high[this._size >> 10];
         E previous = (E)low[this._size & 1023];
         low[this._size & 1023] = null;
         return previous;
      }
   }

   public final void clear() {
      for(int i = 0; i < this._size; i += 1024) {
         int count = MathLib.min(this._size - i, 1024);
         E[] low = (E[])this._high[i >> 10];
         System.arraycopy(NULL_BLOCK, 0, low, 0, count);
      }

      this._size = 0;
   }

   public void reset() {
      this.clear();
      this.setValueComparator(FastComparator.DEFAULT);
   }

   public final boolean addAll(int index, Collection values) {
      if (index >= 0 && index <= this._size) {
         int shift = values.size();
         this.shiftRight(index, shift);
         Iterator<? extends E> valuesIterator = values.iterator();
         int i = index;

         for(int n = index + shift; i < n; ++i) {
            this._high[i >> 10][i & 1023] = valuesIterator.next();
         }

         this._size += shift;
         return shift != 0;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final void add(int index, Object value) {
      if (index >= 0 && index <= this._size) {
         this.shiftRight(index, 1);
         this._high[index >> 10][index & 1023] = value;
         ++this._size;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final Object remove(int index) {
      E previous = (E)this.get(index);
      this.shiftLeft(index + 1, 1);
      --this._size;
      this._high[this._size >> 10][this._size & 1023] = null;
      return previous;
   }

   public final void removeRange(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex >= 0 && fromIndex <= toIndex && toIndex <= this._size) {
         int shift = toIndex - fromIndex;
         this.shiftLeft(toIndex, shift);
         this._size -= shift;
         int i = this._size;

         for(int n = this._size + shift; i < n; ++i) {
            this._high[i >> 10][i & 1023] = null;
         }

      } else {
         throw new IndexOutOfBoundsException("FastTable removeRange(" + fromIndex + ", " + toIndex + ") index out of bounds, size: " + this._size);
      }
   }

   public final int indexOf(Object value) {
      FastComparator comp = this.getValueComparator();

      int count;
      for(int i = 0; i < this._size; i += count) {
         E[] low = (E[])this._high[i >> 10];
         count = MathLib.min(low.length, this._size - i);

         for(int j = 0; j < count; ++j) {
            if (comp == FastComparator.DEFAULT) {
               if (defaultEquals(value, low[j])) {
                  return i + j;
               }
            } else if (comp.areEqual(value, low[j])) {
               return i + j;
            }
         }
      }

      return -1;
   }

   public final int lastIndexOf(Object value) {
      FastComparator comp = this.getValueComparator();
      int i = this._size - 1;

      while(i >= 0) {
         E[] low = (E[])this._high[i >> 10];
         int count = (i & 1023) + 1;
         int j = count;

         while(true) {
            --j;
            if (j < 0) {
               i -= count;
               break;
            }

            if (comp == FastComparator.DEFAULT) {
               if (defaultEquals(value, low[j])) {
                  return i + j - count + 1;
               }
            } else if (comp.areEqual(value, low[j])) {
               return i + j - count + 1;
            }
         }
      }

      return -1;
   }

   public Iterator iterator() {
      return FastTable.FastTableIterator.valueOf(this, 0, 0, this._size);
   }

   public ListIterator listIterator() {
      return FastTable.FastTableIterator.valueOf(this, 0, 0, this._size);
   }

   public ListIterator listIterator(int index) {
      if (index >= 0 && index <= this._size) {
         return FastTable.FastTableIterator.valueOf(this, index, 0, this._size);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final List subList(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex <= this._size && fromIndex <= toIndex) {
         return FastTable.SubTable.valueOf(this, fromIndex, toIndex - fromIndex);
      } else {
         throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + " for list of size: " + this._size);
      }
   }

   public final void trimToSize() {
      while(this._capacity - this._size > 1024) {
         this._capacity -= 1024;
         this._high[this._capacity >> 10] = null;
      }

   }

   public final FastTable sort() {
      if (this._size > 1) {
         this.quicksort(0, this._size - 1, this.getValueComparator());
      }

      return this;
   }

   private void quicksort(int first, int last, FastComparator cmp) {
      int pivIndex = 0;
      if (first < last) {
         pivIndex = this.partition(first, last, cmp);
         this.quicksort(first, pivIndex - 1, cmp);
         this.quicksort(pivIndex + 1, last, cmp);
      }

   }

   private int partition(int f, int l, FastComparator cmp) {
      E piv = (E)this.get(f);
      int up = f;
      int down = l;

      while(true) {
         while(cmp.compare(this.get(up), piv) > 0 || up >= l) {
            while(cmp.compare(this.get(down), piv) > 0 && down > f) {
               --down;
            }

            if (up < down) {
               E temp = (E)this.get(up);
               this.set(up, this.get(down));
               this.set(down, temp);
            }

            if (down <= up) {
               this.set(f, this.get(down));
               this.set(down, piv);
               return down;
            }
         }

         ++up;
      }
   }

   public FastTable setValueComparator(FastComparator comparator) {
      this._valueComparator = comparator;
      return this;
   }

   public FastComparator getValueComparator() {
      return this._valueComparator;
   }

   public final int size() {
      return this._size;
   }

   public final FastCollection.Record head() {
      return Index.valueOf(-1);
   }

   public final FastCollection.Record tail() {
      return Index.valueOf(this._size);
   }

   public final Object valueOf(FastCollection.Record record) {
      return this.get(((Index)record).intValue());
   }

   public final void delete(FastCollection.Record record) {
      this.remove(((Index)record).intValue());
   }

   public List unmodifiable() {
      return (List)super.unmodifiable();
   }

   public final boolean contains(Object value) {
      return this.indexOf(value) >= 0;
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      this.setValueComparator((FastComparator)stream.readObject());
      int size = stream.readInt();

      for(this._capacity = 16; this._capacity < this._size && this._capacity < 1024; this._capacity <<= 1) {
      }

      this._low = new Object[this._capacity];
      this._high = new Object[1][];
      this._high[0] = this._low;

      for(int i = 0; i < size; ++i) {
         this.addLast(stream.readObject());
      }

   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.writeObject(this.getValueComparator());
      int size = this._size;
      stream.writeInt(size);

      for(int i = 0; i < size; ++i) {
         stream.writeObject(this.get(i));
      }

   }

   protected final int getCapacity() {
      return this._capacity;
   }

   private void increaseCapacity() {
      // $FF: Couldn't be decompiled
   }

   private void shiftRight(int index, int shift) {
      while(this._size + shift >= this._capacity) {
         this.increaseCapacity();
      }

      int i = this._size;

      while(true) {
         --i;
         if (i < index) {
            return;
         }

         int dest = i + shift;
         this._high[dest >> 10][dest & 1023] = this._high[i >> 10][i & 1023];
      }
   }

   private void shiftLeft(int index, int shift) {
      for(int i = index; i < this._size; ++i) {
         int dest = i - shift;
         this._high[dest >> 10][dest & 1023] = this._high[i >> 10][i & 1023];
      }

   }

   private static boolean defaultEquals(Object o1, Object o2) {
      return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
   }

   // $FF: synthetic method
   static int access$000(FastTable x0) {
      return x0._capacity;
   }

   // $FF: synthetic method
   static int access$060(FastTable x0, int x1) {
      return x0._capacity <<= x1;
   }

   // $FF: synthetic method
   static int access$200(FastTable x0) {
      return x0._size;
   }

   // $FF: synthetic method
   static Object[] access$102(FastTable x0, Object[] x1) {
      return x0._low = x1;
   }

   // $FF: synthetic method
   static Object[][] access$302(FastTable x0, Object[][] x1) {
      return x0._high = x1;
   }

   // $FF: synthetic method
   static int access$012(FastTable x0, int x1) {
      return x0._capacity += x1;
   }

   private static final class SubTable extends FastCollection implements List, RandomAccess {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new SubTable();
         }

         protected void cleanup(Object obj) {
            SubTable st = (SubTable)obj;
            st._table = null;
         }
      };
      private FastTable _table;
      private int _offset;
      private int _size;

      private SubTable() {
      }

      public static SubTable valueOf(FastTable table, int offset, int size) {
         SubTable subTable = (SubTable)FACTORY.object();
         subTable._table = table;
         subTable._offset = offset;
         subTable._size = size;
         return subTable;
      }

      public int size() {
         return this._size;
      }

      public FastCollection.Record head() {
         return Index.valueOf(-1);
      }

      public FastCollection.Record tail() {
         return Index.valueOf(this._size);
      }

      public Object valueOf(FastCollection.Record record) {
         return this._table.get(((Index)record).intValue() + this._offset);
      }

      public void delete(FastCollection.Record record) {
         throw new UnsupportedOperationException("Deletion not supported, thread-safe collections.");
      }

      public boolean addAll(int index, Collection values) {
         throw new UnsupportedOperationException("Insertion not supported, thread-safe collections.");
      }

      public Object get(int index) {
         if (index >= 0 && index < this._size) {
            return this._table.get(index + this._offset);
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public Object set(int index, Object value) {
         if (index >= 0 && index < this._size) {
            return this._table.set(index + this._offset, value);
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public void add(int index, Object element) {
         throw new UnsupportedOperationException("Insertion not supported, thread-safe collections.");
      }

      public Object remove(int index) {
         throw new UnsupportedOperationException("Deletion not supported, thread-safe collections.");
      }

      public int indexOf(Object value) {
         FastComparator comp = this._table.getValueComparator();
         int i = -1;

         do {
            ++i;
            if (i >= this._size) {
               return -1;
            }
         } while(!comp.areEqual(value, this._table.get(i + this._offset)));

         return i;
      }

      public int lastIndexOf(Object value) {
         FastComparator comp = this._table.getValueComparator();
         int i = this._size;

         do {
            --i;
            if (i < 0) {
               return -1;
            }
         } while(!comp.areEqual(value, this._table.get(i + this._offset)));

         return i;
      }

      public ListIterator listIterator() {
         return this.listIterator(0);
      }

      public ListIterator listIterator(int index) {
         if (index >= 0 && index <= this._size) {
            return FastTable.FastTableIterator.valueOf(this._table, index + this._offset, this._offset, this._offset + this._size);
         } else {
            throw new IndexOutOfBoundsException("index: " + index + " for table of size: " + this._size);
         }
      }

      public List subList(int fromIndex, int toIndex) {
         if (fromIndex >= 0 && toIndex <= this._size && fromIndex <= toIndex) {
            return valueOf(this._table, this._offset + fromIndex, toIndex - fromIndex);
         } else {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + " for list of size: " + this._size);
         }
      }
   }

   private static final class FastTableIterator implements ListIterator {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new FastTableIterator();
         }

         protected void cleanup(Object obj) {
            FastTableIterator i = (FastTableIterator)obj;
            i._table = null;
            i._low = null;
            i._high = (Object[][])null;
         }
      };
      private FastTable _table;
      private int _currentIndex;
      private int _start;
      private int _end;
      private int _nextIndex;
      private Object[] _low;
      private Object[][] _high;

      private FastTableIterator() {
      }

      public static FastTableIterator valueOf(FastTable table, int nextIndex, int start, int end) {
         FastTableIterator iterator = (FastTableIterator)FACTORY.object();
         iterator._table = table;
         iterator._start = start;
         iterator._end = end;
         iterator._nextIndex = nextIndex;
         iterator._low = table._low;
         iterator._high = table._high;
         iterator._currentIndex = -1;
         return iterator;
      }

      public boolean hasNext() {
         return this._nextIndex != this._end;
      }

      public Object next() {
         if (this._nextIndex == this._end) {
            throw new NoSuchElementException();
         } else {
            int i = this._currentIndex = this._nextIndex++;
            return i < 1024 ? this._low[i] : this._high[i >> 10][i & 1023];
         }
      }

      public int nextIndex() {
         return this._nextIndex;
      }

      public boolean hasPrevious() {
         return this._nextIndex != this._start;
      }

      public Object previous() {
         if (this._nextIndex == this._start) {
            throw new NoSuchElementException();
         } else {
            int i = this._currentIndex = --this._nextIndex;
            return i < 1024 ? this._low[i] : this._high[i >> 10][i & 1023];
         }
      }

      public int previousIndex() {
         return this._nextIndex - 1;
      }

      public void add(Object o) {
         this._table.add(this._nextIndex++, o);
         ++this._end;
         this._currentIndex = -1;
      }

      public void set(Object o) {
         if (this._currentIndex >= 0) {
            this._table.set(this._currentIndex, o);
         } else {
            throw new IllegalStateException();
         }
      }

      public void remove() {
         if (this._currentIndex >= 0) {
            this._table.remove(this._currentIndex);
            --this._end;
            if (this._currentIndex < this._nextIndex) {
               --this._nextIndex;
            }

            this._currentIndex = -1;
         } else {
            throw new IllegalStateException();
         }
      }
   }
}
