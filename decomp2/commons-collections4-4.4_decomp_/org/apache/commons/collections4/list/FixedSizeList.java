package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public class FixedSizeList extends AbstractSerializableListDecorator implements BoundedCollection {
   private static final long serialVersionUID = -2218010673611160319L;

   public static FixedSizeList fixedSizeList(List list) {
      return new FixedSizeList(list);
   }

   protected FixedSizeList(List list) {
      super(list);
   }

   public boolean add(Object object) {
      throw unsupportedOperationException();
   }

   public void add(int index, Object object) {
      throw unsupportedOperationException();
   }

   public boolean addAll(Collection coll) {
      throw unsupportedOperationException();
   }

   public boolean addAll(int index, Collection coll) {
      throw unsupportedOperationException();
   }

   public void clear() {
      throw unsupportedOperationException();
   }

   public Object get(int index) {
      return this.decorated().get(index);
   }

   public int indexOf(Object object) {
      return this.decorated().indexOf(object);
   }

   public Iterator iterator() {
      return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
   }

   public int lastIndexOf(Object object) {
      return this.decorated().lastIndexOf(object);
   }

   public ListIterator listIterator() {
      return new FixedSizeListIterator(this.decorated().listIterator(0));
   }

   public ListIterator listIterator(int index) {
      return new FixedSizeListIterator(this.decorated().listIterator(index));
   }

   public Object remove(int index) {
      throw unsupportedOperationException();
   }

   public boolean remove(Object object) {
      throw unsupportedOperationException();
   }

   public boolean removeIf(Predicate filter) {
      throw unsupportedOperationException();
   }

   public boolean removeAll(Collection coll) {
      throw unsupportedOperationException();
   }

   public boolean retainAll(Collection coll) {
      throw unsupportedOperationException();
   }

   public Object set(int index, Object object) {
      return this.decorated().set(index, object);
   }

   public List subList(int fromIndex, int toIndex) {
      List<E> sub = this.decorated().subList(fromIndex, toIndex);
      return new FixedSizeList(sub);
   }

   public boolean isFull() {
      return true;
   }

   public int maxSize() {
      return this.size();
   }

   private static UnsupportedOperationException unsupportedOperationException() {
      return new UnsupportedOperationException("List is fixed size");
   }

   private class FixedSizeListIterator extends AbstractListIteratorDecorator {
      protected FixedSizeListIterator(ListIterator iterator) {
         super(iterator);
      }

      public void remove() {
         throw FixedSizeList.unsupportedOperationException();
      }

      public void add(Object object) {
         throw FixedSizeList.unsupportedOperationException();
      }
   }
}
