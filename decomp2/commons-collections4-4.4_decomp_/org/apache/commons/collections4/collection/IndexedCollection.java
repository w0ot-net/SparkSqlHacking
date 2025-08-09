package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;

public class IndexedCollection extends AbstractCollectionDecorator {
   private static final long serialVersionUID = -5512610452568370038L;
   private final Transformer keyTransformer;
   private final MultiMap index;
   private final boolean uniqueIndex;

   public static IndexedCollection uniqueIndexedCollection(Collection coll, Transformer keyTransformer) {
      return new IndexedCollection(coll, keyTransformer, MultiValueMap.multiValueMap(new HashMap()), true);
   }

   public static IndexedCollection nonUniqueIndexedCollection(Collection coll, Transformer keyTransformer) {
      return new IndexedCollection(coll, keyTransformer, MultiValueMap.multiValueMap(new HashMap()), false);
   }

   public IndexedCollection(Collection coll, Transformer keyTransformer, MultiMap map, boolean uniqueIndex) {
      super(coll);
      this.keyTransformer = keyTransformer;
      this.index = map;
      this.uniqueIndex = uniqueIndex;
      this.reindex();
   }

   public boolean add(Object object) {
      boolean added = super.add(object);
      if (added) {
         this.addToIndex(object);
      }

      return added;
   }

   public boolean addAll(Collection coll) {
      boolean changed = false;

      for(Object c : coll) {
         changed |= this.add(c);
      }

      return changed;
   }

   public void clear() {
      super.clear();
      this.index.clear();
   }

   public boolean contains(Object object) {
      return this.index.containsKey(this.keyTransformer.transform(object));
   }

   public boolean containsAll(Collection coll) {
      for(Object o : coll) {
         if (!this.contains(o)) {
            return false;
         }
      }

      return true;
   }

   public Object get(Object key) {
      Collection<C> coll = (Collection)this.index.get(key);
      return coll == null ? null : coll.iterator().next();
   }

   public Collection values(Object key) {
      return (Collection)this.index.get(key);
   }

   public void reindex() {
      this.index.clear();

      for(Object c : this.decorated()) {
         this.addToIndex(c);
      }

   }

   public boolean remove(Object object) {
      boolean removed = super.remove(object);
      if (removed) {
         this.removeFromIndex(object);
      }

      return removed;
   }

   public boolean removeIf(Predicate filter) {
      if (Objects.isNull(filter)) {
         return false;
      } else {
         boolean changed = false;
         Iterator<C> it = this.iterator();

         while(it.hasNext()) {
            if (filter.test(it.next())) {
               it.remove();
               changed = true;
            }
         }

         if (changed) {
            this.reindex();
         }

         return changed;
      }
   }

   public boolean removeAll(Collection coll) {
      boolean changed = false;

      for(Object o : coll) {
         changed |= this.remove(o);
      }

      return changed;
   }

   public boolean retainAll(Collection coll) {
      boolean changed = super.retainAll(coll);
      if (changed) {
         this.reindex();
      }

      return changed;
   }

   private void addToIndex(Object object) {
      K key = (K)this.keyTransformer.transform(object);
      if (this.uniqueIndex && this.index.containsKey(key)) {
         throw new IllegalArgumentException("Duplicate key in uniquely indexed collection.");
      } else {
         this.index.put(key, object);
      }
   }

   private void removeFromIndex(Object object) {
      this.index.remove(this.keyTransformer.transform(object));
   }
}
