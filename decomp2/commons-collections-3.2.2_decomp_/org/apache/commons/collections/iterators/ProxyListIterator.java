package org.apache.commons.collections.iterators;

import java.util.ListIterator;

/** @deprecated */
public class ProxyListIterator implements ListIterator {
   private ListIterator iterator;

   public ProxyListIterator() {
   }

   public ProxyListIterator(ListIterator iterator) {
      this.iterator = iterator;
   }

   public void add(Object o) {
      this.getListIterator().add(o);
   }

   public boolean hasNext() {
      return this.getListIterator().hasNext();
   }

   public boolean hasPrevious() {
      return this.getListIterator().hasPrevious();
   }

   public Object next() {
      return this.getListIterator().next();
   }

   public int nextIndex() {
      return this.getListIterator().nextIndex();
   }

   public Object previous() {
      return this.getListIterator().previous();
   }

   public int previousIndex() {
      return this.getListIterator().previousIndex();
   }

   public void remove() {
      this.getListIterator().remove();
   }

   public void set(Object o) {
      this.getListIterator().set(o);
   }

   public ListIterator getListIterator() {
      return this.iterator;
   }

   public void setListIterator(ListIterator iterator) {
      this.iterator = iterator;
   }
}
