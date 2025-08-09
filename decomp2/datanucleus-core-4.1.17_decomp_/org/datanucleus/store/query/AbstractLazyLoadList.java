package org.datanucleus.store.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.SoftValueMap;
import org.datanucleus.util.WeakValueMap;

public abstract class AbstractLazyLoadList implements List {
   private Map itemsByIndex = null;
   protected int size = -1;

   public AbstractLazyLoadList(String cacheType) {
      if (cacheType != null) {
         if (cacheType.equalsIgnoreCase("soft")) {
            this.itemsByIndex = new SoftValueMap();
         } else if (cacheType.equalsIgnoreCase("weak")) {
            this.itemsByIndex = new WeakValueMap();
         } else if (cacheType.equalsIgnoreCase("strong")) {
            this.itemsByIndex = new HashMap();
         } else if (cacheType.equalsIgnoreCase("none")) {
            this.itemsByIndex = null;
         } else {
            this.itemsByIndex = new WeakValueMap();
         }
      } else {
         this.itemsByIndex = new WeakValueMap();
      }

   }

   protected abstract Object retrieveObjectForIndex(int var1);

   protected abstract int getSize();

   protected boolean isOpen() {
      return true;
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean add(Object e) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean addAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean addAll(int index, Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public void clear() {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean contains(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean containsAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public Object get(int index) {
      if (this.itemsByIndex != null && this.itemsByIndex.containsKey(index)) {
         return this.itemsByIndex.get(index);
      } else {
         E obj = (E)this.retrieveObjectForIndex(index);
         this.itemsByIndex.put(index, obj);
         return obj;
      }
   }

   public int indexOf(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Iterator iterator() {
      return this.listIterator(0);
   }

   public int lastIndexOf(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public ListIterator listIterator() {
      return this.listIterator(0);
   }

   public ListIterator listIterator(int index) {
      return new LazyLoadListIterator();
   }

   public Object remove(int index) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean removeAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean retainAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public int size() {
      if (this.size >= 0) {
         return this.size;
      } else {
         this.size = this.getSize();
         return this.size;
      }
   }

   public List subList(int fromIndex, int toIndex) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public Object[] toArray() {
      Object[] array = new Object[this.size()];

      for(int i = 0; i < array.length; ++i) {
         if (this.itemsByIndex != null && this.itemsByIndex.containsKey(i)) {
            array[i] = this.itemsByIndex.get(i);
         } else {
            array[i] = this.retrieveObjectForIndex(i);
         }
      }

      return array;
   }

   public Object[] toArray(Object[] a) {
      if (a == null) {
         throw new NullPointerException("null argument is illegal!");
      } else {
         Object[] array = a;
         int ourSize = this.size();
         if (a.length < ourSize) {
            array = new Object[this.size()];
         }

         for(int i = 0; i < ourSize; ++i) {
            if (this.itemsByIndex != null && this.itemsByIndex.containsKey(i)) {
               array[i] = this.itemsByIndex.get(i);
            } else {
               array[i] = this.retrieveObjectForIndex(i);
            }
         }

         return array;
      }
   }

   private class LazyLoadListIterator implements ListIterator {
      private int iteratorIndex;

      private LazyLoadListIterator() {
         this.iteratorIndex = 0;
      }

      public boolean hasNext() {
         synchronized(AbstractLazyLoadList.this) {
            if (!AbstractLazyLoadList.this.isOpen()) {
               return false;
            } else {
               return this.iteratorIndex <= AbstractLazyLoadList.this.size() - 1;
            }
         }
      }

      public boolean hasPrevious() {
         synchronized(AbstractLazyLoadList.this) {
            if (!AbstractLazyLoadList.this.isOpen()) {
               return false;
            } else {
               return this.iteratorIndex > 0;
            }
         }
      }

      public Object next() {
         synchronized(AbstractLazyLoadList.this) {
            if (!AbstractLazyLoadList.this.isOpen()) {
               throw new NoSuchElementException(Localiser.msg("052600"));
            } else if (!this.hasNext()) {
               throw new NoSuchElementException("No next element");
            } else if (AbstractLazyLoadList.this.itemsByIndex != null && AbstractLazyLoadList.this.itemsByIndex.containsKey(this.iteratorIndex)) {
               return AbstractLazyLoadList.this.itemsByIndex.get(this.iteratorIndex);
            } else {
               E obj = (E)AbstractLazyLoadList.this.retrieveObjectForIndex(this.iteratorIndex);
               if (AbstractLazyLoadList.this.itemsByIndex != null) {
                  AbstractLazyLoadList.this.itemsByIndex.put(this.iteratorIndex, obj);
               }

               ++this.iteratorIndex;
               return obj;
            }
         }
      }

      public int nextIndex() {
         return this.hasNext() ? this.iteratorIndex : AbstractLazyLoadList.this.size();
      }

      public Object previous() {
         synchronized(AbstractLazyLoadList.this) {
            if (!AbstractLazyLoadList.this.isOpen()) {
               throw new NoSuchElementException(Localiser.msg("052600"));
            } else if (!this.hasPrevious()) {
               throw new NoSuchElementException("No previous element");
            } else {
               --this.iteratorIndex;
               if (AbstractLazyLoadList.this.itemsByIndex != null && AbstractLazyLoadList.this.itemsByIndex.containsKey(this.iteratorIndex)) {
                  return AbstractLazyLoadList.this.itemsByIndex.get(this.iteratorIndex);
               } else {
                  E obj = (E)AbstractLazyLoadList.this.retrieveObjectForIndex(this.iteratorIndex);
                  if (AbstractLazyLoadList.this.itemsByIndex != null) {
                     AbstractLazyLoadList.this.itemsByIndex.put(this.iteratorIndex, obj);
                  }

                  ++this.iteratorIndex;
                  return obj;
               }
            }
         }
      }

      public int previousIndex() {
         return this.iteratorIndex == 0 ? -1 : this.iteratorIndex - 1;
      }

      public void add(Object e) {
         throw new UnsupportedOperationException(Localiser.msg("052603"));
      }

      public void remove() {
         throw new UnsupportedOperationException(Localiser.msg("052603"));
      }

      public void set(Object e) {
         throw new UnsupportedOperationException(Localiser.msg("052603"));
      }
   }
}
