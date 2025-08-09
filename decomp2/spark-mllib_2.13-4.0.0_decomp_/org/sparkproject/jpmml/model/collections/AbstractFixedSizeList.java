package org.sparkproject.jpmml.model.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class AbstractFixedSizeList extends AbstractCollection implements List {
   public boolean equals(Object object) {
      if (object instanceof List) {
         List<?> that = (List)object;
         if (this.size() != that.size()) {
            return false;
         } else {
            ListIterator<?> thisIt = this.listIterator();
            ListIterator<?> thatIt = that.listIterator();

            while(thisIt.hasNext()) {
               if (!Objects.equals(thisIt.next(), thatIt.next())) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = 1;
      int i = 0;

      for(int max = this.size(); i < max; ++i) {
         result = 31 * result + Objects.hashCode(this.get(i));
      }

      return result;
   }

   public Iterator iterator() {
      return this.listIterator();
   }

   public ListIterator listIterator() {
      return this.listIterator(0);
   }

   public ListIterator listIterator(final int index) {
      if (index >= 0 && index <= this.size()) {
         ListIterator<E> result = new ListIterator() {
            private int cursor = index;

            public int nextIndex() {
               return this.cursor;
            }

            public boolean hasNext() {
               return this.cursor != AbstractFixedSizeList.this.size();
            }

            public Object next() {
               try {
                  int i = this.cursor;
                  E next = (E)AbstractFixedSizeList.this.get(i);
                  this.cursor = i + 1;
                  return next;
               } catch (IndexOutOfBoundsException var3) {
                  throw new NoSuchElementException();
               }
            }

            public int previousIndex() {
               return this.cursor - 1;
            }

            public boolean hasPrevious() {
               return this.cursor != 0;
            }

            public Object previous() {
               try {
                  int i = this.cursor - 1;
                  E previous = (E)AbstractFixedSizeList.this.get(i);
                  this.cursor = i;
                  return previous;
               } catch (IndexOutOfBoundsException var3) {
                  throw new NoSuchElementException();
               }
            }

            public void add(Object element) {
               throw new UnsupportedOperationException();
            }

            public void set(Object element) {
               int i = this.cursor - 1;
               AbstractFixedSizeList.this.set(i, element);
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
         return result;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(int index, Collection elements) {
      throw new UnsupportedOperationException();
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException();
   }

   public Object remove(int index) {
      throw new UnsupportedOperationException();
   }

   public int indexOf(Object object) {
      ListIterator<E> it = this.listIterator();

      while(it.hasNext()) {
         if (Objects.equals(object, it.next())) {
            return it.previousIndex();
         }
      }

      return -1;
   }

   public int lastIndexOf(Object object) {
      ListIterator<E> it = this.listIterator(this.size());

      while(it.hasPrevious()) {
         if (Objects.equals(object, it.previous())) {
            return it.nextIndex();
         }
      }

      return -1;
   }
}
