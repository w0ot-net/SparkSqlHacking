package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeCollection implements Collection, Serializable {
   private static final long serialVersionUID = 8417515734108306801L;
   private CollectionMutator mutator;
   private final List all = new ArrayList();

   public CompositeCollection() {
   }

   public CompositeCollection(Collection compositeCollection) {
      this.addComposited(compositeCollection);
   }

   public CompositeCollection(Collection compositeCollection1, Collection compositeCollection2) {
      this.addComposited(compositeCollection1, compositeCollection2);
   }

   public CompositeCollection(Collection... compositeCollections) {
      this.addComposited(compositeCollections);
   }

   public int size() {
      int size = 0;

      for(Collection item : this.all) {
         size += item.size();
      }

      return size;
   }

   public boolean isEmpty() {
      for(Collection item : this.all) {
         if (!item.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public boolean contains(Object obj) {
      for(Collection item : this.all) {
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

         for(Collection item : this.all) {
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
         throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
      } else {
         return this.mutator.add(this, this.all, obj);
      }
   }

   public boolean remove(Object obj) {
      if (this.mutator == null) {
         throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
      } else {
         return this.mutator.remove(this, this.all, obj);
      }
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
         throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
      } else {
         return this.mutator.addAll(this, this.all, coll);
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

   public boolean retainAll(Collection coll) {
      boolean changed = false;
      if (coll != null) {
         for(Collection item : this.all) {
            changed |= item.retainAll(coll);
         }
      }

      return changed;
   }

   public void clear() {
      for(Collection coll : this.all) {
         coll.clear();
      }

   }

   public void setMutator(CollectionMutator mutator) {
      this.mutator = mutator;
   }

   public void addComposited(Collection compositeCollection) {
      if (compositeCollection != null) {
         this.all.add(compositeCollection);
      }

   }

   public void addComposited(Collection compositeCollection1, Collection compositeCollection2) {
      if (compositeCollection1 != null) {
         this.all.add(compositeCollection1);
      }

      if (compositeCollection2 != null) {
         this.all.add(compositeCollection2);
      }

   }

   public void addComposited(Collection... compositeCollections) {
      for(Collection compositeCollection : compositeCollections) {
         if (compositeCollection != null) {
            this.all.add(compositeCollection);
         }
      }

   }

   public void removeComposited(Collection coll) {
      this.all.remove(coll);
   }

   public Collection toCollection() {
      return new ArrayList(this);
   }

   public List getCollections() {
      return UnmodifiableList.unmodifiableList(this.all);
   }

   protected CollectionMutator getMutator() {
      return this.mutator;
   }

   public interface CollectionMutator extends Serializable {
      boolean add(CompositeCollection var1, List var2, Object var3);

      boolean addAll(CompositeCollection var1, List var2, Collection var3);

      boolean remove(CompositeCollection var1, List var2, Object var3);
   }
}
