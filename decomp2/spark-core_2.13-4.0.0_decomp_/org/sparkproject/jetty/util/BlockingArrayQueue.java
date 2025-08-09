package org.sparkproject.jetty.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingArrayQueue extends AbstractList implements BlockingQueue {
   private static final int HEAD_OFFSET = MemoryUtils.getIntegersPerCacheLine() - 1;
   private static final int TAIL_OFFSET;
   public static final int DEFAULT_CAPACITY = 128;
   public static final int DEFAULT_GROWTH = 64;
   private final int _maxCapacity;
   private final int _growCapacity;
   private final int[] _indexes;
   private final Lock _tailLock;
   private final AtomicInteger _size;
   private final Lock _headLock;
   private final Condition _notEmpty;
   private Object[] _elements;

   public BlockingArrayQueue() {
      this._indexes = new int[TAIL_OFFSET + 1];
      this._tailLock = new ReentrantLock();
      this._size = new AtomicInteger();
      this._headLock = new ReentrantLock();
      this._notEmpty = this._headLock.newCondition();
      this._elements = new Object[128];
      this._growCapacity = 64;
      this._maxCapacity = Integer.MAX_VALUE;
   }

   public BlockingArrayQueue(int maxCapacity) {
      this._indexes = new int[TAIL_OFFSET + 1];
      this._tailLock = new ReentrantLock();
      this._size = new AtomicInteger();
      this._headLock = new ReentrantLock();
      this._notEmpty = this._headLock.newCondition();
      this._elements = new Object[maxCapacity];
      this._growCapacity = -1;
      this._maxCapacity = maxCapacity;
   }

   public BlockingArrayQueue(int capacity, int growBy) {
      this._indexes = new int[TAIL_OFFSET + 1];
      this._tailLock = new ReentrantLock();
      this._size = new AtomicInteger();
      this._headLock = new ReentrantLock();
      this._notEmpty = this._headLock.newCondition();
      this._elements = new Object[capacity];
      this._growCapacity = growBy;
      this._maxCapacity = Integer.MAX_VALUE;
   }

   public BlockingArrayQueue(int capacity, int growBy, int maxCapacity) {
      this._indexes = new int[TAIL_OFFSET + 1];
      this._tailLock = new ReentrantLock();
      this._size = new AtomicInteger();
      this._headLock = new ReentrantLock();
      this._notEmpty = this._headLock.newCondition();
      if (capacity > maxCapacity) {
         throw new IllegalArgumentException();
      } else {
         this._elements = new Object[capacity];
         this._growCapacity = growBy;
         this._maxCapacity = maxCapacity;
      }
   }

   public void clear() {
      this._tailLock.lock();

      try {
         this._headLock.lock();

         try {
            this._indexes[HEAD_OFFSET] = 0;
            this._indexes[TAIL_OFFSET] = 0;
            this._size.set(0);
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

   }

   public int size() {
      return this._size.get();
   }

   public Iterator iterator() {
      return this.listIterator();
   }

   public Object poll() {
      if (this._size.get() == 0) {
         return null;
      } else {
         E e = (E)null;
         this._headLock.lock();

         try {
            if (this._size.get() > 0) {
               int head = this._indexes[HEAD_OFFSET];
               e = (E)this._elements[head];
               this._elements[head] = null;
               this._indexes[HEAD_OFFSET] = (head + 1) % this._elements.length;
               if (this._size.decrementAndGet() > 0) {
                  this._notEmpty.signal();
               }
            }
         } finally {
            this._headLock.unlock();
         }

         return e;
      }
   }

   public Object poll(long time, TimeUnit unit) throws InterruptedException {
      long nanos = unit.toNanos(time);
      E e = (E)null;
      this._headLock.lockInterruptibly();

      try {
         try {
            while(this._size.get() == 0) {
               if (nanos <= 0L) {
                  Object var7 = null;
                  return var7;
               }

               nanos = this._notEmpty.awaitNanos(nanos);
            }
         } catch (InterruptedException x) {
            this._notEmpty.signal();
            throw x;
         }

         int head = this._indexes[HEAD_OFFSET];
         e = (E)this._elements[head];
         this._elements[head] = null;
         this._indexes[HEAD_OFFSET] = (head + 1) % this._elements.length;
         if (this._size.decrementAndGet() > 0) {
            this._notEmpty.signal();
         }

         return e;
      } finally {
         this._headLock.unlock();
      }
   }

   public Object peek() {
      if (this._size.get() == 0) {
         return null;
      } else {
         E e = (E)null;
         this._headLock.lock();

         try {
            if (this._size.get() > 0) {
               e = (E)this._elements[this._indexes[HEAD_OFFSET]];
            }
         } finally {
            this._headLock.unlock();
         }

         return e;
      }
   }

   public Object remove() {
      E e = (E)this.poll();
      if (e == null) {
         throw new NoSuchElementException();
      } else {
         return e;
      }
   }

   public Object remove(int index) {
      this._tailLock.lock();

      Object var6;
      try {
         this._headLock.lock();

         try {
            if (index < 0 || index >= this._size.get()) {
               throw new IndexOutOfBoundsException("!(0<" + index + "<=" + String.valueOf(this._size) + ")");
            }

            int i = this._indexes[HEAD_OFFSET] + index;
            int capacity = this._elements.length;
            if (i >= capacity) {
               i -= capacity;
            }

            E old = (E)this._elements[i];
            int tail = this._indexes[TAIL_OFFSET];
            if (i < tail) {
               System.arraycopy(this._elements, i + 1, this._elements, i, tail - i);
               int var10002 = this._indexes[TAIL_OFFSET]--;
            } else {
               System.arraycopy(this._elements, i + 1, this._elements, i, capacity - i - 1);
               this._elements[capacity - 1] = this._elements[0];
               if (tail > 0) {
                  System.arraycopy(this._elements, 1, this._elements, 0, tail);
                  int var15 = this._indexes[TAIL_OFFSET]--;
               } else {
                  this._indexes[TAIL_OFFSET] = capacity - 1;
               }

               this._elements[this._indexes[TAIL_OFFSET]] = null;
            }

            this._size.decrementAndGet();
            var6 = old;
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return var6;
   }

   public boolean remove(Object o) {
      this._tailLock.lock();

      int head;
      try {
         this._headLock.lock();

         try {
            if (!this.isEmpty()) {
               head = this._indexes[HEAD_OFFSET];
               int tail = this._indexes[TAIL_OFFSET];
               int capacity = this._elements.length;
               int i = head;

               boolean var6;
               while(!Objects.equals(this._elements[i], o)) {
                  ++i;
                  if (i == capacity) {
                     i = 0;
                  }

                  if (i == tail) {
                     var6 = false;
                     return var6;
                  }
               }

               this.remove(i >= head ? i - head : capacity - head + i);
               var6 = true;
               return var6;
            }

            head = 0;
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return (boolean)head;
   }

   public Object element() {
      E e = (E)this.peek();
      if (e == null) {
         throw new NoSuchElementException();
      } else {
         return e;
      }
   }

   public boolean offer(Object e) {
      Objects.requireNonNull(e);
      boolean notEmpty = false;
      this._tailLock.lock();

      try {
         int size = this._size.get();
         if (size >= this._maxCapacity) {
            boolean var22 = false;
            return var22;
         }

         if (size == this._elements.length) {
            this._headLock.lock();

            try {
               if (!this.grow()) {
                  boolean var4 = false;
                  return var4;
               }
            } finally {
               this._headLock.unlock();
            }
         }

         int tail = this._indexes[TAIL_OFFSET];
         this._elements[tail] = e;
         this._indexes[TAIL_OFFSET] = (tail + 1) % this._elements.length;
         notEmpty = this._size.getAndIncrement() == 0;
      } finally {
         this._tailLock.unlock();
      }

      if (notEmpty) {
         this._headLock.lock();

         try {
            this._notEmpty.signal();
         } finally {
            this._headLock.unlock();
         }
      }

      return true;
   }

   public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
      throw new UnsupportedOperationException();
   }

   public boolean add(Object e) {
      if (this.offer(e)) {
         return true;
      } else {
         throw new IllegalStateException();
      }
   }

   public void add(int index, Object e) {
      if (e == null) {
         throw new NullPointerException();
      } else {
         this._tailLock.lock();

         try {
            this._headLock.lock();

            try {
               int size = this._size.get();
               if (index < 0 || index > size) {
                  throw new IndexOutOfBoundsException("!(0<" + index + "<=" + String.valueOf(this._size) + ")");
               }

               if (index == size) {
                  this.add(e);
               } else {
                  if (this._indexes[TAIL_OFFSET] == this._indexes[HEAD_OFFSET] && !this.grow()) {
                     throw new IllegalStateException("full");
                  }

                  int i = this._indexes[HEAD_OFFSET] + index;
                  int capacity = this._elements.length;
                  if (i >= capacity) {
                     i -= capacity;
                  }

                  this._size.incrementAndGet();
                  int tail = this._indexes[TAIL_OFFSET];
                  int var15;
                  this._indexes[TAIL_OFFSET] = var15 = (tail + 1) % capacity;
                  if (i < var15) {
                     System.arraycopy(this._elements, i, this._elements, i + 1, var15 - i);
                     this._elements[i] = e;
                  } else {
                     if (var15 > 0) {
                        System.arraycopy(this._elements, 0, this._elements, 1, var15);
                        this._elements[0] = this._elements[capacity - 1];
                     }

                     System.arraycopy(this._elements, i, this._elements, i + 1, capacity - i - 1);
                     this._elements[i] = e;
                  }
               }
            } finally {
               this._headLock.unlock();
            }
         } finally {
            this._tailLock.unlock();
         }

      }
   }

   public void put(Object o) throws InterruptedException {
      throw new UnsupportedOperationException();
   }

   public Object take() throws InterruptedException {
      E e = (E)null;
      this._headLock.lockInterruptibly();

      try {
         try {
            while(this._size.get() == 0) {
               this._notEmpty.await();
            }
         } catch (InterruptedException ex) {
            this._notEmpty.signal();
            throw ex;
         }

         int head = this._indexes[HEAD_OFFSET];
         e = (E)this._elements[head];
         this._elements[head] = null;
         this._indexes[HEAD_OFFSET] = (head + 1) % this._elements.length;
         if (this._size.decrementAndGet() > 0) {
            this._notEmpty.signal();
         }
      } finally {
         this._headLock.unlock();
      }

      return e;
   }

   public int remainingCapacity() {
      this._tailLock.lock();

      int var1;
      try {
         this._headLock.lock();

         try {
            var1 = this.getCapacity() - this.size();
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return var1;
   }

   public int drainTo(Collection c) {
      return this.drainTo(c, Integer.MAX_VALUE);
   }

   public int drainTo(Collection c, int maxElements) {
      int elements = 0;
      this._tailLock.lock();

      int head;
      try {
         this._headLock.lock();

         try {
            if (this._size.get() != 0) {
               head = this._indexes[HEAD_OFFSET];
               int tail = this._indexes[TAIL_OFFSET];
               int capacity = this._elements.length;
               int i = head;

               while(elements < maxElements && (i != tail || elements <= 0)) {
                  ++elements;
                  c.add(this._elements[i]);
                  ++i;
                  if (i == capacity) {
                     i = 0;
                  }
               }

               if (i == tail) {
                  this._indexes[HEAD_OFFSET] = 0;
                  this._indexes[TAIL_OFFSET] = 0;
                  this._size.set(0);
               } else {
                  this._indexes[HEAD_OFFSET] = i;
                  this._size.addAndGet(-elements);
               }

               return elements;
            }

            head = 0;
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return head;
   }

   public Object get(int index) {
      this._tailLock.lock();

      Object var4;
      try {
         this._headLock.lock();

         try {
            if (index < 0 || index >= this._size.get()) {
               throw new IndexOutOfBoundsException("!(0<" + index + "<=" + String.valueOf(this._size) + ")");
            }

            int i = this._indexes[HEAD_OFFSET] + index;
            int capacity = this._elements.length;
            if (i >= capacity) {
               i -= capacity;
            }

            var4 = this._elements[i];
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return var4;
   }

   public Object set(int index, Object e) {
      Objects.requireNonNull(e);
      this._tailLock.lock();

      Object var6;
      try {
         this._headLock.lock();

         try {
            if (index < 0 || index >= this._size.get()) {
               throw new IndexOutOfBoundsException("!(0<" + index + "<=" + String.valueOf(this._size) + ")");
            }

            int i = this._indexes[HEAD_OFFSET] + index;
            int capacity = this._elements.length;
            if (i >= capacity) {
               i -= capacity;
            }

            E old = (E)this._elements[i];
            this._elements[i] = e;
            var6 = old;
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return var6;
   }

   public ListIterator listIterator(int index) {
      this._tailLock.lock();

      Itr var14;
      try {
         this._headLock.lock();

         try {
            Object[] elements = new Object[this.size()];
            if (this.size() > 0) {
               int head = this._indexes[HEAD_OFFSET];
               int tail = this._indexes[TAIL_OFFSET];
               if (head < tail) {
                  System.arraycopy(this._elements, head, elements, 0, tail - head);
               } else {
                  int chunk = this._elements.length - head;
                  System.arraycopy(this._elements, head, elements, 0, chunk);
                  System.arraycopy(this._elements, 0, elements, chunk, tail);
               }
            }

            var14 = new Itr(elements, index);
         } finally {
            this._headLock.unlock();
         }
      } finally {
         this._tailLock.unlock();
      }

      return var14;
   }

   public int getCapacity() {
      this._tailLock.lock();

      int var1;
      try {
         var1 = this._elements.length;
      } finally {
         this._tailLock.unlock();
      }

      return var1;
   }

   public int getMaxCapacity() {
      return this._maxCapacity;
   }

   private boolean grow() {
      if (this._growCapacity <= 0) {
         return false;
      } else {
         this._tailLock.lock();

         int cut;
         try {
            this._headLock.lock();

            try {
               int head = this._indexes[HEAD_OFFSET];
               int tail = this._indexes[TAIL_OFFSET];
               int capacity = this._elements.length;
               Object[] elements = new Object[capacity + this._growCapacity];
               int newTail;
               if (head < tail) {
                  newTail = tail - head;
                  System.arraycopy(this._elements, head, elements, 0, newTail);
               } else if (head <= tail && this._size.get() <= 0) {
                  newTail = 0;
               } else {
                  newTail = capacity + tail - head;
                  cut = capacity - head;
                  System.arraycopy(this._elements, head, elements, 0, cut);
                  System.arraycopy(this._elements, 0, elements, cut, tail);
               }

               this._elements = elements;
               this._indexes[HEAD_OFFSET] = 0;
               this._indexes[TAIL_OFFSET] = newTail;
               cut = 1;
            } finally {
               this._headLock.unlock();
            }
         } finally {
            this._tailLock.unlock();
         }

         return (boolean)cut;
      }
   }

   static {
      TAIL_OFFSET = HEAD_OFFSET + MemoryUtils.getIntegersPerCacheLine();
   }

   private class Itr implements ListIterator {
      private final Object[] _elements;
      private int _cursor;

      public Itr(Object[] elements, int offset) {
         this._elements = elements;
         this._cursor = offset;
      }

      public boolean hasNext() {
         return this._cursor < this._elements.length;
      }

      public Object next() {
         return this._elements[this._cursor++];
      }

      public boolean hasPrevious() {
         return this._cursor > 0;
      }

      public Object previous() {
         return this._elements[--this._cursor];
      }

      public int nextIndex() {
         return this._cursor + 1;
      }

      public int previousIndex() {
         return this._cursor - 1;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      public void set(Object e) {
         throw new UnsupportedOperationException();
      }

      public void add(Object e) {
         throw new UnsupportedOperationException();
      }
   }
}
