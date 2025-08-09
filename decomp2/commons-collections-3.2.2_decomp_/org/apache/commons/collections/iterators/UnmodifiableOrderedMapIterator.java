package org.apache.commons.collections.iterators;

import org.apache.commons.collections.OrderedMapIterator;
import org.apache.commons.collections.Unmodifiable;

public final class UnmodifiableOrderedMapIterator implements OrderedMapIterator, Unmodifiable {
   private OrderedMapIterator iterator;

   public static OrderedMapIterator decorate(OrderedMapIterator iterator) {
      if (iterator == null) {
         throw new IllegalArgumentException("OrderedMapIterator must not be null");
      } else {
         return (OrderedMapIterator)(iterator instanceof Unmodifiable ? iterator : new UnmodifiableOrderedMapIterator(iterator));
      }
   }

   private UnmodifiableOrderedMapIterator(OrderedMapIterator iterator) {
      this.iterator = iterator;
   }

   public boolean hasNext() {
      return this.iterator.hasNext();
   }

   public Object next() {
      return this.iterator.next();
   }

   public boolean hasPrevious() {
      return this.iterator.hasPrevious();
   }

   public Object previous() {
      return this.iterator.previous();
   }

   public Object getKey() {
      return this.iterator.getKey();
   }

   public Object getValue() {
      return this.iterator.getValue();
   }

   public Object setValue(Object value) {
      throw new UnsupportedOperationException("setValue() is not supported");
   }

   public void remove() {
      throw new UnsupportedOperationException("remove() is not supported");
   }
}
