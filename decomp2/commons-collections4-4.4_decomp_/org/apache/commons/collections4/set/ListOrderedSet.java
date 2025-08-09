package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;

public class ListOrderedSet extends AbstractSerializableSetDecorator {
   private static final long serialVersionUID = -228664372470420141L;
   private final List setOrder;

   public static ListOrderedSet listOrderedSet(Set set, List list) {
      if (set == null) {
         throw new NullPointerException("Set must not be null");
      } else if (list == null) {
         throw new NullPointerException("List must not be null");
      } else if (set.size() <= 0 && list.size() <= 0) {
         return new ListOrderedSet(set, list);
      } else {
         throw new IllegalArgumentException("Set and List must be empty");
      }
   }

   public static ListOrderedSet listOrderedSet(Set set) {
      return new ListOrderedSet(set);
   }

   public static ListOrderedSet listOrderedSet(List list) {
      if (list == null) {
         throw new NullPointerException("List must not be null");
      } else {
         CollectionUtils.filter(list, UniquePredicate.uniquePredicate());
         Set<E> set = new HashSet(list);
         return new ListOrderedSet(set, list);
      }
   }

   public ListOrderedSet() {
      super(new HashSet());
      this.setOrder = new ArrayList();
   }

   protected ListOrderedSet(Set set) {
      super(set);
      this.setOrder = new ArrayList(set);
   }

   protected ListOrderedSet(Set set, List list) {
      super(set);
      if (list == null) {
         throw new NullPointerException("List must not be null");
      } else {
         this.setOrder = list;
      }
   }

   public List asList() {
      return UnmodifiableList.unmodifiableList(this.setOrder);
   }

   public void clear() {
      this.decorated().clear();
      this.setOrder.clear();
   }

   public OrderedIterator iterator() {
      return new OrderedSetIterator(this.setOrder.listIterator(), this.decorated());
   }

   public boolean add(Object object) {
      if (this.decorated().add(object)) {
         this.setOrder.add(object);
         return true;
      } else {
         return false;
      }
   }

   public boolean addAll(Collection coll) {
      boolean result = false;

      for(Object e : coll) {
         result |= this.add(e);
      }

      return result;
   }

   public boolean remove(Object object) {
      boolean result = this.decorated().remove(object);
      if (result) {
         this.setOrder.remove(object);
      }

      return result;
   }

   public boolean removeIf(Predicate filter) {
      if (Objects.isNull(filter)) {
         return false;
      } else {
         boolean result = this.decorated().removeIf(filter);
         if (result) {
            this.setOrder.removeIf(filter);
         }

         return result;
      }
   }

   public boolean removeAll(Collection coll) {
      boolean result = false;

      for(Object name : coll) {
         result |= this.remove(name);
      }

      return result;
   }

   public boolean retainAll(Collection coll) {
      boolean result = this.decorated().retainAll(coll);
      if (!result) {
         return false;
      } else {
         if (this.decorated().size() == 0) {
            this.setOrder.clear();
         } else {
            Iterator<E> it = this.setOrder.iterator();

            while(it.hasNext()) {
               if (!this.decorated().contains(it.next())) {
                  it.remove();
               }
            }
         }

         return result;
      }
   }

   public Object[] toArray() {
      return this.setOrder.toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.setOrder.toArray(a);
   }

   public Object get(int index) {
      return this.setOrder.get(index);
   }

   public int indexOf(Object object) {
      return this.setOrder.indexOf(object);
   }

   public void add(int index, Object object) {
      if (!this.contains(object)) {
         this.decorated().add(object);
         this.setOrder.add(index, object);
      }

   }

   public boolean addAll(int index, Collection coll) {
      boolean changed = false;
      List<E> toAdd = new ArrayList();

      for(Object e : coll) {
         if (!this.contains(e)) {
            this.decorated().add(e);
            toAdd.add(e);
            changed = true;
         }
      }

      if (changed) {
         this.setOrder.addAll(index, toAdd);
      }

      return changed;
   }

   public Object remove(int index) {
      E obj = (E)this.setOrder.remove(index);
      this.remove(obj);
      return obj;
   }

   public String toString() {
      return this.setOrder.toString();
   }

   static class OrderedSetIterator extends AbstractIteratorDecorator implements OrderedIterator {
      private final Collection set;
      private Object last;

      private OrderedSetIterator(ListIterator iterator, Collection set) {
         super(iterator);
         this.set = set;
      }

      public Object next() {
         this.last = this.getIterator().next();
         return this.last;
      }

      public void remove() {
         this.set.remove(this.last);
         this.getIterator().remove();
         this.last = null;
      }

      public boolean hasPrevious() {
         return ((ListIterator)this.getIterator()).hasPrevious();
      }

      public Object previous() {
         this.last = ((ListIterator)this.getIterator()).previous();
         return this.last;
      }
   }
}
