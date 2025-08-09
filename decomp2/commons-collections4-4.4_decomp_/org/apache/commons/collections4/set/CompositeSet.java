package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeSet implements Set, Serializable {
   private static final long serialVersionUID = 5185069727540378940L;
   private SetMutator mutator;
   private final List all = new ArrayList();

   public CompositeSet() {
   }

   public CompositeSet(Set set) {
      this.addComposited(set);
   }

   public CompositeSet(Set... sets) {
      this.addComposited(sets);
   }

   public int size() {
      int size = 0;

      for(Set item : this.all) {
         size += item.size();
      }

      return size;
   }

   public boolean isEmpty() {
      for(Set item : this.all) {
         if (!item.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public boolean contains(Object obj) {
      for(Set item : this.all) {
         if (item.contains(obj)) {
            return true;
         }
      }

      return false;
   }

   public Iterator iterator() {
      if (this.all.isEmpty()) {
         return EmptyIterator.emptyIterator();
      } else {
         IteratorChain<E> chain = new IteratorChain();

         for(Set item : this.all) {
            chain.addIterator(item.iterator());
         }

         return chain;
      }
   }

   public Object[] toArray() {
      Object[] result = new Object[this.size()];
      int i = 0;

      for(Iterator<E> it = this.iterator(); it.hasNext(); ++i) {
         result[i] = it.next();
      }

      return result;
   }

   public Object[] toArray(Object[] array) {
      int size = this.size();
      Object[] result = null;
      if (array.length >= size) {
         result = array;
      } else {
         result = Array.newInstance(array.getClass().getComponentType(), size);
      }

      int offset = 0;

      for(Collection item : this.all) {
         for(Object e : item) {
            result[offset++] = e;
         }
      }

      if (result.length > size) {
         result[size] = null;
      }

      return result;
   }

   public boolean add(Object obj) {
      if (this.mutator == null) {
         throw new UnsupportedOperationException("add() is not supported on CompositeSet without a SetMutator strategy");
      } else {
         return this.mutator.add(this, this.all, obj);
      }
   }

   public boolean remove(Object obj) {
      for(Set set : this.getSets()) {
         if (set.contains(obj)) {
            return set.remove(obj);
         }
      }

      return false;
   }

   public boolean containsAll(Collection coll) {
      if (coll == null) {
         return false;
      } else {
         for(Object item : coll) {
            if (!this.contains(item)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean addAll(Collection coll) {
      if (this.mutator == null) {
         throw new UnsupportedOperationException("addAll() is not supported on CompositeSet without a SetMutator strategy");
      } else {
         return this.mutator.addAll(this, this.all, coll);
      }
   }

   public boolean removeIf(Predicate filter) {
      if (Objects.isNull(filter)) {
         return false;
      } else {
         boolean changed = false;

         for(Collection item : this.all) {
            changed |= item.removeIf(filter);
         }

         return changed;
      }
   }

   public boolean removeAll(Collection coll) {
      if (CollectionUtils.isEmpty(coll)) {
         return false;
      } else {
         boolean changed = false;

         for(Collection item : this.all) {
            changed |= item.removeAll(coll);
         }

         return changed;
      }
   }

   public boolean retainAll(Collection coll) {
      boolean changed = false;

      for(Collection item : this.all) {
         changed |= item.retainAll(coll);
      }

      return changed;
   }

   public void clear() {
      for(Collection coll : this.all) {
         coll.clear();
      }

   }

   public void setMutator(SetMutator mutator) {
      this.mutator = mutator;
   }

   public synchronized void addComposited(Set set) {
      if (set != null) {
         for(Set existingSet : this.getSets()) {
            Collection<E> intersects = CollectionUtils.intersection(existingSet, set);
            if (intersects.size() > 0) {
               if (this.mutator == null) {
                  throw new UnsupportedOperationException("Collision adding composited set with no SetMutator set");
               }

               this.getMutator().resolveCollision(this, existingSet, set, intersects);
               if (CollectionUtils.intersection(existingSet, set).size() > 0) {
                  throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
               }
            }
         }

         this.all.add(set);
      }

   }

   public void addComposited(Set set1, Set set2) {
      this.addComposited(set1);
      this.addComposited(set2);
   }

   public void addComposited(Set... sets) {
      if (sets != null) {
         for(Set set : sets) {
            this.addComposited(set);
         }
      }

   }

   public void removeComposited(Set set) {
      this.all.remove(set);
   }

   public Set toSet() {
      return new HashSet(this);
   }

   public List getSets() {
      return UnmodifiableList.unmodifiableList(this.all);
   }

   protected SetMutator getMutator() {
      return this.mutator;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof Set)) {
         return false;
      } else {
         Set<?> set = (Set)obj;
         return set.size() == this.size() && set.containsAll(this);
      }
   }

   public int hashCode() {
      int code = 0;

      for(Object e : this) {
         code += e == null ? 0 : e.hashCode();
      }

      return code;
   }

   public interface SetMutator extends Serializable {
      boolean add(CompositeSet var1, List var2, Object var3);

      boolean addAll(CompositeSet var1, List var2, Collection var3);

      void resolveCollision(CompositeSet var1, Set var2, Set var3, Collection var4);
   }
}
