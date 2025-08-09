package org.apache.commons.collections4.list;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class SetUniqueList extends AbstractSerializableListDecorator {
   private static final long serialVersionUID = 7196982186153478694L;
   private final Set set;

   public static SetUniqueList setUniqueList(List list) {
      if (list == null) {
         throw new NullPointerException("List must not be null");
      } else if (list.isEmpty()) {
         return new SetUniqueList(list, new HashSet());
      } else {
         List<E> temp = new ArrayList(list);
         list.clear();
         SetUniqueList<E> sl = new SetUniqueList(list, new HashSet());
         sl.addAll(temp);
         return sl;
      }
   }

   protected SetUniqueList(List list, Set set) {
      super(list);
      if (set == null) {
         throw new NullPointerException("Set must not be null");
      } else {
         this.set = set;
      }
   }

   public Set asSet() {
      return UnmodifiableSet.unmodifiableSet(this.set);
   }

   public boolean add(Object object) {
      int sizeBefore = this.size();
      this.add(this.size(), object);
      return sizeBefore != this.size();
   }

   public void add(int index, Object object) {
      if (!this.set.contains(object)) {
         this.set.add(object);
         super.add(index, object);
      }

   }

   public boolean addAll(Collection coll) {
      return this.addAll(this.size(), coll);
   }

   public boolean addAll(int index, Collection coll) {
      List<E> temp = new ArrayList();

      for(Object e : coll) {
         if (this.set.add(e)) {
            temp.add(e);
         }
      }

      return super.addAll(index, temp);
   }

   public Object set(int index, Object object) {
      int pos = this.indexOf(object);
      E removed = (E)super.set(index, object);
      if (pos != -1 && pos != index) {
         super.remove(pos);
      }

      this.set.remove(removed);
      this.set.add(object);
      return removed;
   }

   public boolean remove(Object object) {
      boolean result = this.set.remove(object);
      if (result) {
         super.remove(object);
      }

      return result;
   }

   public Object remove(int index) {
      E result = (E)super.remove(index);
      this.set.remove(result);
      return result;
   }

   public boolean removeIf(Predicate filter) {
      boolean result = super.removeIf(filter);
      this.set.removeIf(filter);
      return result;
   }

   public boolean removeAll(Collection coll) {
      boolean result = false;

      for(Object name : coll) {
         result |= this.remove(name);
      }

      return result;
   }

   public boolean retainAll(Collection coll) {
      boolean result = this.set.retainAll(coll);
      if (!result) {
         return false;
      } else {
         if (this.set.size() == 0) {
            super.clear();
         } else {
            super.retainAll(this.set);
         }

         return result;
      }
   }

   public void clear() {
      super.clear();
      this.set.clear();
   }

   public boolean contains(Object object) {
      return this.set.contains(object);
   }

   public boolean containsAll(Collection coll) {
      return this.set.containsAll(coll);
   }

   public Iterator iterator() {
      return new SetListIterator(super.iterator(), this.set);
   }

   public ListIterator listIterator() {
      return new SetListListIterator(super.listIterator(), this.set);
   }

   public ListIterator listIterator(int index) {
      return new SetListListIterator(super.listIterator(index), this.set);
   }

   public List subList(int fromIndex, int toIndex) {
      List<E> superSubList = super.subList(fromIndex, toIndex);
      Set<E> subSet = this.createSetBasedOnList(this.set, superSubList);
      return ListUtils.unmodifiableList(new SetUniqueList(superSubList, subSet));
   }

   protected Set createSetBasedOnList(Set set, List list) {
      Set<E> subSet;
      if (set.getClass().equals(HashSet.class)) {
         subSet = new HashSet(list.size());
      } else {
         try {
            subSet = (Set)set.getClass().getDeclaredConstructor(set.getClass()).newInstance(set);
         } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException var5) {
            subSet = new HashSet();
         }
      }

      return subSet;
   }

   static class SetListIterator extends AbstractIteratorDecorator {
      private final Set set;
      private Object last = null;

      protected SetListIterator(Iterator it, Set set) {
         super(it);
         this.set = set;
      }

      public Object next() {
         this.last = super.next();
         return this.last;
      }

      public void remove() {
         super.remove();
         this.set.remove(this.last);
         this.last = null;
      }
   }

   static class SetListListIterator extends AbstractListIteratorDecorator {
      private final Set set;
      private Object last = null;

      protected SetListListIterator(ListIterator it, Set set) {
         super(it);
         this.set = set;
      }

      public Object next() {
         this.last = super.next();
         return this.last;
      }

      public Object previous() {
         this.last = super.previous();
         return this.last;
      }

      public void remove() {
         super.remove();
         this.set.remove(this.last);
         this.last = null;
      }

      public void add(Object object) {
         if (!this.set.contains(object)) {
            super.add(object);
            this.set.add(object);
         }

      }

      public void set(Object object) {
         throw new UnsupportedOperationException("ListIterator does not support set");
      }
   }
}
