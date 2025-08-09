package io.vertx.core.impl.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentCyclicSequence implements Iterable, Iterator {
   private static final Object[] EMPTY_ARRAY = new Object[0];
   private final AtomicInteger pos;
   private final Object[] elements;

   public ConcurrentCyclicSequence() {
      this(0, EMPTY_ARRAY);
   }

   @SafeVarargs
   public ConcurrentCyclicSequence(Object... elements) {
      this(0, Arrays.copyOf(elements, elements.length, Object[].class));
   }

   private ConcurrentCyclicSequence(int pos, Object[] elements) {
      this.pos = new AtomicInteger(pos);
      this.elements = elements;
   }

   public int index() {
      return this.elements.length > 1 ? this.computeIndex(this.pos.get()) : 0;
   }

   private int computeIndex(int p) {
      return Math.abs(p % this.elements.length);
   }

   public Object first() {
      return this.elements.length > 0 ? this.elements[0] : null;
   }

   public ConcurrentCyclicSequence add(Object element) {
      int len = this.elements.length;
      Object[] copy = Arrays.copyOf(this.elements, len + 1);
      copy[len] = element;
      return new ConcurrentCyclicSequence(this.pos.get(), copy);
   }

   public ConcurrentCyclicSequence remove(Object element) {
      int len = this.elements.length;

      for(int i = 0; i < len; ++i) {
         if (Objects.equals(element, this.elements[i])) {
            if (len > 1) {
               Object[] copy = new Object[len - 1];
               System.arraycopy(this.elements, 0, copy, 0, i);
               System.arraycopy(this.elements, i + 1, copy, i, len - i - 1);
               return new ConcurrentCyclicSequence(this.pos.get() % copy.length, copy);
            }

            return new ConcurrentCyclicSequence();
         }
      }

      return this;
   }

   public boolean hasNext() {
      return true;
   }

   public Object next() {
      T result;
      if (this.elements.length == 0) {
         result = (T)null;
      } else if (this.elements.length == 1) {
         result = (T)this.elements[0];
      } else {
         result = (T)this.elements[this.computeIndex(this.pos.getAndIncrement())];
      }

      return result;
   }

   public int size() {
      return this.elements.length;
   }

   public Iterator iterator() {
      return this.iterator(true);
   }

   public Iterator iterator(boolean startAtBeginning) {
      int len = this.elements.length;
      Iterator<T> iterator;
      if (len == 0) {
         iterator = Collections.emptyIterator();
      } else if (len == 1) {
         iterator = new SingletonIter();
      } else {
         iterator = new Iter(startAtBeginning ? 0 : this.pos.getAndIncrement());
      }

      return iterator;
   }

   private class SingletonIter implements Iterator {
      boolean next;

      private SingletonIter() {
         this.next = true;
      }

      public boolean hasNext() {
         return this.next;
      }

      public Object next() {
         if (this.next) {
            this.next = false;
            return ConcurrentCyclicSequence.this.elements[0];
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   private class Iter implements Iterator {
      final int start;
      int cursor;

      public Iter(int start) {
         this.start = start;
         this.cursor = 0;
      }

      public boolean hasNext() {
         return this.cursor != ConcurrentCyclicSequence.this.elements.length;
      }

      public Object next() {
         if (this.cursor >= ConcurrentCyclicSequence.this.elements.length) {
            throw new NoSuchElementException();
         } else {
            return ConcurrentCyclicSequence.this.elements[ConcurrentCyclicSequence.this.computeIndex(this.start + this.cursor++)];
         }
      }
   }
}
