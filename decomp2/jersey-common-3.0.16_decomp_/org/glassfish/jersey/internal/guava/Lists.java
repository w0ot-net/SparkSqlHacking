package org.glassfish.jersey.internal.guava;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Lists {
   private Lists() {
   }

   private static ArrayList newArrayList() {
      return new ArrayList();
   }

   public static ArrayList newArrayList(Iterable elements) {
      Preconditions.checkNotNull(elements);
      return elements instanceof Collection ? new ArrayList(Collections2.cast(elements)) : newArrayList(elements.iterator());
   }

   public static ArrayList newArrayList(Iterator elements) {
      ArrayList<E> list = newArrayList();
      Iterators.addAll(list, elements);
      return list;
   }

   private static List reverse(List list) {
      return (List)(list instanceof ReverseList ? ((ReverseList)list).getForwardList() : new ReverseList(list));
   }

   static boolean equalsImpl(List list, Object object) {
      if (object == Preconditions.checkNotNull(list)) {
         return true;
      } else if (!(object instanceof List)) {
         return false;
      } else {
         List<?> o = (List)object;
         return list.size() == o.size() && Iterators.elementsEqual(list.iterator(), o.iterator());
      }
   }

   static int indexOfImpl(List list, Object element) {
      ListIterator<?> listIterator = list.listIterator();

      while(listIterator.hasNext()) {
         if (Objects.equals(element, listIterator.next())) {
            return listIterator.previousIndex();
         }
      }

      return -1;
   }

   static int lastIndexOfImpl(List list, Object element) {
      ListIterator<?> listIterator = list.listIterator(list.size());

      while(listIterator.hasPrevious()) {
         if (Objects.equals(element, listIterator.previous())) {
            return listIterator.nextIndex();
         }
      }

      return -1;
   }

   private static class ReverseList extends AbstractList {
      private final List forwardList;

      ReverseList(List forwardList) {
         this.forwardList = (List)Preconditions.checkNotNull(forwardList);
      }

      List getForwardList() {
         return this.forwardList;
      }

      private int reverseIndex(int index) {
         int size = this.size();
         Preconditions.checkElementIndex(index, size);
         return size - 1 - index;
      }

      private int reversePosition(int index) {
         int size = this.size();
         Preconditions.checkPositionIndex(index, size);
         return size - index;
      }

      public void add(int index, Object element) {
         this.forwardList.add(this.reversePosition(index), element);
      }

      public void clear() {
         this.forwardList.clear();
      }

      public Object remove(int index) {
         return this.forwardList.remove(this.reverseIndex(index));
      }

      protected void removeRange(int fromIndex, int toIndex) {
         this.subList(fromIndex, toIndex).clear();
      }

      public Object set(int index, Object element) {
         return this.forwardList.set(this.reverseIndex(index), element);
      }

      public Object get(int index) {
         return this.forwardList.get(this.reverseIndex(index));
      }

      public int size() {
         return this.forwardList.size();
      }

      public List subList(int fromIndex, int toIndex) {
         Preconditions.checkPositionIndexes(fromIndex, toIndex, this.size());
         return Lists.reverse(this.forwardList.subList(this.reversePosition(toIndex), this.reversePosition(fromIndex)));
      }

      public Iterator iterator() {
         return this.listIterator();
      }

      public ListIterator listIterator(int index) {
         int start = this.reversePosition(index);
         final ListIterator<T> forwardIterator = this.forwardList.listIterator(start);
         return new ListIterator() {
            boolean canRemoveOrSet;

            public void add(Object e) {
               forwardIterator.add(e);
               forwardIterator.previous();
               this.canRemoveOrSet = false;
            }

            public boolean hasNext() {
               return forwardIterator.hasPrevious();
            }

            public boolean hasPrevious() {
               return forwardIterator.hasNext();
            }

            public Object next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  this.canRemoveOrSet = true;
                  return forwardIterator.previous();
               }
            }

            public int nextIndex() {
               return ReverseList.this.reversePosition(forwardIterator.nextIndex());
            }

            public Object previous() {
               if (!this.hasPrevious()) {
                  throw new NoSuchElementException();
               } else {
                  this.canRemoveOrSet = true;
                  return forwardIterator.next();
               }
            }

            public int previousIndex() {
               return this.nextIndex() - 1;
            }

            public void remove() {
               CollectPreconditions.checkRemove(this.canRemoveOrSet);
               forwardIterator.remove();
               this.canRemoveOrSet = false;
            }

            public void set(Object e) {
               Preconditions.checkState(this.canRemoveOrSet);
               forwardIterator.set(e);
            }
         };
      }
   }
}
