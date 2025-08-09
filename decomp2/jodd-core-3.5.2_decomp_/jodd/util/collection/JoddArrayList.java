package jodd.util.collection;

import [Ljava.lang.Object;;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class JoddArrayList extends AbstractList implements RandomAccess, Cloneable {
   private static final int DEFAULT_CAPACITY = 16;
   private static final Object[] EMPTY_BUFFER = new Object[0];
   protected Object[] buffer;
   protected int size;
   protected int start;
   protected int end;
   protected int pivotIndex;
   protected PIVOT_TYPE pivotType;
   protected int minimalGrowSize;
   protected int maxFreeSpaceBeforeNormalize;

   public JoddArrayList(int initialCapacity) {
      this.pivotType = JoddArrayList.PIVOT_TYPE.FIRST_QUARTER;
      this.minimalGrowSize = 10;
      this.maxFreeSpaceBeforeNormalize = 32;
      this.init(initialCapacity);
   }

   public JoddArrayList(int initialCapacity, PIVOT_TYPE pivot_type, int minimalGrowSize, int maxFreeSpaceBeforeNormalize) {
      this.pivotType = JoddArrayList.PIVOT_TYPE.FIRST_QUARTER;
      this.minimalGrowSize = 10;
      this.maxFreeSpaceBeforeNormalize = 32;
      this.init(initialCapacity);
      this.pivotType = pivot_type;
      this.minimalGrowSize = minimalGrowSize;
      this.maxFreeSpaceBeforeNormalize = maxFreeSpaceBeforeNormalize;
   }

   public JoddArrayList() {
      this.pivotType = JoddArrayList.PIVOT_TYPE.FIRST_QUARTER;
      this.minimalGrowSize = 10;
      this.maxFreeSpaceBeforeNormalize = 32;
      this.init(16);
   }

   public JoddArrayList(Collection collection) {
      this.pivotType = JoddArrayList.PIVOT_TYPE.FIRST_QUARTER;
      this.minimalGrowSize = 10;
      this.maxFreeSpaceBeforeNormalize = 32;
      this.buffer = collection.toArray();
      this.size = this.buffer.length;
      if (this.buffer.getClass() != Object[].class) {
         Object[] copy = new Object[this.size];
         System.arraycopy(this.buffer, 0, copy, 0, this.size);
         this.buffer = copy;
      }

      this.start = 0;
      this.end = this.size;
      this.pivotIndex = this.pivotType.calculate(this.size);
   }

   public JoddArrayList(Object... array) {
      this.pivotType = JoddArrayList.PIVOT_TYPE.FIRST_QUARTER;
      this.minimalGrowSize = 10;
      this.maxFreeSpaceBeforeNormalize = 32;
      this.buffer = ((Object;)array).clone();
      this.size = this.buffer.length;
      this.start = 0;
      this.end = this.size;
      this.pivotIndex = this.pivotType.calculate(this.size);
   }

   protected void init(int capacity) {
      this.pivotIndex = capacity;
      this.buffer = EMPTY_BUFFER;
      this.size = 0;
      this.start = 0;
      this.end = 0;
   }

   public void trimToSize() {
      ++this.modCount;
      if (this.size < this.buffer.length) {
         Object[] newBuffer = new Object[this.size];
         System.arraycopy(this.buffer, this.start, newBuffer, 0, this.size);
         this.buffer = newBuffer;
         this.start = 0;
         this.size = this.buffer.length;
         this.end = this.size;
         this.pivotIndex = this.pivotType.calculate(this.size);
      }

   }

   protected void normalize() {
      int newPivotIndex = this.pivotType.calculate(this.buffer.length);
      int newStart = newPivotIndex - this.pivotType.calculate(this.size);
      int newEnd = newStart + this.size;
      System.arraycopy(this.buffer, this.start, this.buffer, newStart, this.size);
      if (newStart > this.start) {
         for(int i = this.start; i < newStart; ++i) {
            this.buffer[i] = null;
         }
      } else {
         for(int i = Math.max(this.start, newEnd); i < this.end; ++i) {
            this.buffer[i] = null;
         }
      }

      this.start = newStart;
      this.end = newEnd;
      this.pivotIndex = newPivotIndex;
   }

   protected void ensureCapacity(int index, int elementsToAdd) {
      if (this.buffer == EMPTY_BUFFER) {
         if (elementsToAdd <= this.pivotIndex) {
            this.buffer = new Object[this.pivotIndex];
         } else {
            this.buffer = new Object[elementsToAdd];
         }

         this.pivotIndex = this.pivotType.calculate(this.buffer.length);
         this.start = this.pivotIndex;
         this.end = this.start;
         this.size = 0;
      } else {
         ++this.modCount;
         int realIndex = this.start + index;
         if (realIndex <= this.pivotIndex && realIndex < this.end - 1) {
            int gap = this.start;
            if (gap < elementsToAdd) {
               if (this.buffer.length - this.size - elementsToAdd > this.maxFreeSpaceBeforeNormalize) {
                  this.normalize();
                  return;
               }

               int currentSize = this.pivotIndex;
               int newSize = currentSize + (currentSize >> 1);
               int delta = newSize - currentSize;
               if (delta < this.minimalGrowSize) {
                  delta = this.minimalGrowSize;
               }

               int newGap = gap + delta;
               if (newGap < elementsToAdd) {
                  delta = elementsToAdd - gap;
               }

               int totalSize = this.buffer.length + delta;
               Object[] newBuffer = new Object[totalSize];
               System.arraycopy(this.buffer, this.start, newBuffer, newGap, this.size);
               this.start += delta;
               this.end += delta;
               this.pivotIndex += delta;
               this.buffer = newBuffer;
            }
         } else {
            int gap = this.buffer.length - this.end;
            if (gap < elementsToAdd) {
               if (this.buffer.length - this.size - elementsToAdd > this.maxFreeSpaceBeforeNormalize) {
                  this.normalize();
                  return;
               }

               int currentSize = this.buffer.length - this.pivotIndex;
               int newSize = currentSize + (currentSize >> 1);
               int delta = newSize - currentSize;
               if (delta < this.minimalGrowSize) {
                  delta = this.minimalGrowSize;
               }

               int newGap = gap + delta;
               if (newGap < elementsToAdd) {
                  delta = elementsToAdd - gap;
               }

               int totalSize = this.buffer.length + delta;
               Object[] newBuffer = new Object[totalSize];
               System.arraycopy(this.buffer, this.start, newBuffer, this.start, this.size);
               this.buffer = newBuffer;
            }
         }

      }
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public boolean contains(Object o) {
      return this.indexOf(o) >= 0;
   }

   public int indexOf(Object o) {
      if (o == null) {
         for(int i = this.start; i < this.end; ++i) {
            if (this.buffer[i] == null) {
               return i - this.start;
            }
         }
      } else {
         for(int i = this.start; i < this.end; ++i) {
            if (o.equals(this.buffer[i])) {
               return i - this.start;
            }
         }
      }

      return -1;
   }

   public int lastIndexOf(Object o) {
      if (o == null) {
         for(int i = this.end - 1; i >= this.start; --i) {
            if (this.buffer[i] == null) {
               return i - this.start;
            }
         }
      } else {
         for(int i = this.end - 1; i >= this.start; --i) {
            if (o.equals(this.buffer[i])) {
               return i - this.start;
            }
         }
      }

      return -1;
   }

   public Object clone() {
      try {
         JoddArrayList<E> v = (JoddArrayList)super.clone();
         v.buffer = this.buffer == EMPTY_BUFFER ? this.buffer : (Object[])this.buffer.clone();
         v.modCount = 0;
         v.start = this.start;
         v.end = this.end;
         v.size = this.size;
         v.pivotIndex = this.pivotIndex;
         v.pivotType = this.pivotType;
         v.minimalGrowSize = this.minimalGrowSize;
         v.maxFreeSpaceBeforeNormalize = this.maxFreeSpaceBeforeNormalize;
         return v;
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public Object[] toArray() {
      Object[] copy = new Object[this.size];
      System.arraycopy(this.buffer, this.start, copy, 0, this.size);
      return copy;
   }

   public Object[] toArray(Object[] array) {
      if (array.length < this.size) {
         Class arrayType = array.getClass();
         T[] copy = (T[])(arrayType == Object[].class ? (Object[])(new Object[this.size]) : (Object[])((Object[])Array.newInstance(arrayType.getComponentType(), this.size)));
         System.arraycopy(this.buffer, this.start, copy, 0, this.size);
         return copy;
      } else {
         System.arraycopy(this.buffer, this.start, array, 0, this.size);
         if (array.length > this.size) {
            array[this.size] = null;
         }

         return array;
      }
   }

   public Object get(int index) {
      this.rangeCheck(index);
      return this.buffer[this.start + index];
   }

   public Object getFirst() {
      return this.get(0);
   }

   public Object getLast() {
      return this.get(this.size - 1);
   }

   public Object set(int index, Object element) {
      this.rangeCheck(index);
      index += this.start;
      E oldValue = (E)this.buffer[index];
      this.buffer[index] = element;
      return oldValue;
   }

   public boolean add(Object e) {
      int index = this.size;
      this.ensureCapacity(index, 1);
      this.buffer[this.end] = e;
      ++this.end;
      ++this.size;
      return true;
   }

   public boolean addFirst(Object e) {
      int index = 0;
      this.ensureCapacity(index, 1);
      if (this.size > 0) {
         --this.start;
      } else {
         ++this.end;
      }

      this.buffer[this.start] = e;
      ++this.size;
      return true;
   }

   public boolean addLast(Object e) {
      return this.add(e);
   }

   public void add(int index, Object element) {
      if (index == 0) {
         this.addFirst(element);
      } else if (index == this.size) {
         this.add(element);
      } else {
         this.rangeCheck(index);
         this.ensureCapacity(index, 1);
         int realIndex = this.start + index;
         if (realIndex <= this.pivotIndex && realIndex < this.end - 1) {
            System.arraycopy(this.buffer, this.start, this.buffer, this.start - 1, realIndex - this.start);
            --this.start;
            --realIndex;
         } else {
            System.arraycopy(this.buffer, realIndex, this.buffer, realIndex + 1, this.end - realIndex);
            ++this.end;
         }

         this.buffer[realIndex] = element;
         ++this.size;
      }
   }

   public boolean addAll(Collection collection) {
      if (collection.isEmpty()) {
         return false;
      } else {
         Object[] array = collection.toArray();
         return this.doAddAll(array);
      }
   }

   public boolean addAll(Object... array) {
      return array.length == 0 ? false : this.doAddAll(array);
   }

   protected boolean doAddAll(Object[] array) {
      int numNew = array.length;
      this.ensureCapacity(this.end, numNew);
      System.arraycopy(array, 0, this.buffer, this.end, numNew);
      this.size += numNew;
      this.end += numNew;
      return true;
   }

   public boolean addAll(int index, Collection collection) {
      this.rangeCheck(index);
      Object[] array = collection.toArray();
      return this.doAddAll(index, array);
   }

   public boolean addAll(int index, Object... array) {
      this.rangeCheck(index);
      return this.doAddAll(index, array);
   }

   protected boolean doAddAll(int index, Object[] array) {
      int numNew = array.length;
      this.ensureCapacity(index, numNew);
      int realIndex = this.start + index;
      if (realIndex <= this.pivotIndex) {
         if (index > 0) {
            System.arraycopy(this.buffer, this.start, this.buffer, this.start - numNew, index);
         }

         realIndex -= numNew;
         System.arraycopy(array, 0, this.buffer, realIndex, numNew);
         this.start -= numNew;
      } else {
         int numMoved = this.end - realIndex;
         if (numMoved > 0) {
            System.arraycopy(this.buffer, realIndex, this.buffer, realIndex + numNew, numMoved);
         }

         System.arraycopy(array, 0, this.buffer, realIndex, numNew);
         this.end += numNew;
      }

      this.size += numNew;
      return numNew != 0;
   }

   public void clear() {
      ++this.modCount;

      for(int i = this.start; i < this.end; ++i) {
         this.buffer[i] = null;
      }

      this.pivotIndex = this.pivotType.calculate(this.buffer.length);
      this.start = this.pivotIndex;
      this.end = this.start;
      this.size = 0;
   }

   public Object removeFirst() {
      return this.remove(0);
   }

   public Object removeLast() {
      return this.remove(this.size - 1);
   }

   public Object remove(int index) {
      this.rangeCheck(index);
      ++this.modCount;
      return this.doRemove(index);
   }

   protected Object doRemove(int index) {
      int realIndex = this.start + index;
      E oldValue = (E)this.buffer[realIndex];
      if (realIndex <= this.pivotIndex && realIndex < this.end - 1) {
         if (index > 0) {
            System.arraycopy(this.buffer, this.start, this.buffer, this.start + 1, index);
         }

         this.buffer[this.start] = null;
         ++this.start;
         --this.size;
         if (this.start > this.pivotIndex) {
            this.pivotIndex = this.start;
         }
      } else {
         int numMoved = this.end - realIndex - 1;
         if (numMoved > 0) {
            System.arraycopy(this.buffer, realIndex + 1, this.buffer, realIndex, numMoved);
         }

         --this.end;
         --this.size;
         this.buffer[this.end] = null;
         if (this.end <= this.pivotIndex) {
            this.pivotIndex = this.end - 1;
            if (this.pivotIndex < this.start) {
               this.pivotIndex = this.start;
            }
         }
      }

      return oldValue;
   }

   public boolean remove(Object o) {
      if (o == null) {
         for(int index = this.start; index < this.end; ++index) {
            if (this.buffer[index] == null) {
               this.doRemove(index - this.start);
               return true;
            }
         }
      } else {
         for(int index = this.start; index < this.end; ++index) {
            if (o.equals(this.buffer[index])) {
               this.doRemove(index - this.start);
               return true;
            }
         }
      }

      return false;
   }

   protected void removeRange(int fromIndex, int toIndex) {
      ++this.modCount;
      int numMoved = this.size - toIndex;
      System.arraycopy(this.buffer, this.start + toIndex, this.buffer, this.start + fromIndex, numMoved);
      int newSize = this.size - (toIndex - fromIndex);

      for(int i = this.start + newSize; i < this.start + this.size; ++i) {
         this.buffer[i] = null;
      }

      this.size = newSize;
      this.end = this.start + this.size;
      this.pivotIndex = this.start + this.pivotType.calculate(this.size);
   }

   public boolean removeAll(Collection c) {
      return this.batchRemove(c, false);
   }

   public boolean retainAll(Collection c) {
      return this.batchRemove(c, true);
   }

   protected boolean batchRemove(Collection collection, boolean complement) {
      int r = 0;
      int w = 0;
      boolean modified = false;

      try {
         for(; r < this.size; ++r) {
            Object element = this.buffer[this.start + r];
            if (collection.contains(element) == complement) {
               this.buffer[this.start + w++] = this.buffer[this.start + r];
            }
         }
      } finally {
         if (r != this.size) {
            System.arraycopy(this.buffer, this.start + r, this.buffer, this.start + w, this.size - r);
            w += this.size - r;
         }

         if (w != this.size) {
            for(int i = w; i < this.size; ++i) {
               this.buffer[this.start + i] = null;
            }

            this.modCount += this.size - w;
            this.size = w;
            modified = true;
            this.end = this.start + this.size;
            this.pivotIndex = this.start + this.pivotType.calculate(this.size);
         }

      }

      return modified;
   }

   private void rangeCheck(int index) {
      if (index < 0 || index > this.size) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
      }
   }

   public ListIterator listIterator(int index) {
      this.rangeCheck(index);
      return new ListItr(index);
   }

   public ListIterator listIterator() {
      return new ListItr(0);
   }

   public Iterator iterator() {
      return new Itr();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("[");
      if (this.buffer != EMPTY_BUFFER) {
         for(int i = this.start; i < this.end; ++i) {
            if (i != this.start) {
               sb.append(',');
            }

            sb.append(this.buffer[i]);
         }
      }

      sb.append(']');
      return sb.toString();
   }

   public static enum PIVOT_TYPE {
      FIRST_QUARTER {
         public int calculate(int value) {
            return value >> 2;
         }
      },
      HALF {
         public int calculate(int value) {
            return value >> 1;
         }
      },
      LAST_QUARTER {
         public int calculate(int value) {
            return value - (value >> 2);
         }
      };

      private PIVOT_TYPE() {
      }

      public abstract int calculate(int var1);
   }

   private class Itr implements Iterator {
      int cursor;
      int lastRet;
      int expectedModCount;

      private Itr() {
         this.lastRet = -1;
         this.expectedModCount = JoddArrayList.this.modCount;
      }

      public boolean hasNext() {
         return this.cursor != JoddArrayList.this.size;
      }

      public Object next() {
         this.checkForComodification();
         int i = this.cursor;
         if (i >= JoddArrayList.this.size) {
            throw new NoSuchElementException();
         } else {
            this.cursor = i + 1;
            this.lastRet = i;
            return JoddArrayList.this.buffer[JoddArrayList.this.start + i];
         }
      }

      public void remove() {
         if (this.lastRet < 0) {
            throw new IllegalStateException();
         } else {
            this.checkForComodification();

            try {
               JoddArrayList.this.remove(this.lastRet);
               this.cursor = this.lastRet;
               this.lastRet = -1;
               this.expectedModCount = JoddArrayList.this.modCount;
            } catch (IndexOutOfBoundsException var2) {
               throw new ConcurrentModificationException();
            }
         }
      }

      final void checkForComodification() {
         if (JoddArrayList.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }
   }

   private class ListItr extends Itr implements ListIterator {
      ListItr(int index) {
         this.cursor = index;
      }

      public boolean hasPrevious() {
         return this.cursor != 0;
      }

      public int nextIndex() {
         return this.cursor;
      }

      public int previousIndex() {
         return this.cursor - 1;
      }

      public Object previous() {
         this.checkForComodification();
         int i = this.cursor - 1;
         if (i < 0) {
            throw new NoSuchElementException();
         } else if (i >= JoddArrayList.this.size) {
            throw new ConcurrentModificationException();
         } else {
            this.cursor = i;
            this.lastRet = i;
            return JoddArrayList.this.buffer[JoddArrayList.this.start + i];
         }
      }

      public void set(Object e) {
         if (this.lastRet < 0) {
            throw new IllegalStateException();
         } else {
            this.checkForComodification();

            try {
               JoddArrayList.this.set(this.lastRet, e);
            } catch (IndexOutOfBoundsException var3) {
               throw new ConcurrentModificationException();
            }
         }
      }

      public void add(Object e) {
         this.checkForComodification();

         try {
            int i = this.cursor;
            JoddArrayList.this.add(i, e);
            this.cursor = i + 1;
            this.lastRet = -1;
            this.expectedModCount = JoddArrayList.this.modCount;
         } catch (IndexOutOfBoundsException var3) {
            throw new ConcurrentModificationException();
         }
      }
   }
}
