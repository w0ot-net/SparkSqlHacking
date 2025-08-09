package org.apache.arrow.vector.util;

import java.util.Collection;
import java.util.Set;
import org.apache.arrow.vector.complex.AbstractStructVector;

public class PromotableMultiMapWithOrdinal implements MapWithOrdinal {
   private final MapWithOrdinalImpl mapWithOrdinal = new MapWithOrdinalImpl();
   private final MultiMapWithOrdinal multiMapWithOrdinal = new MultiMapWithOrdinal();
   private final boolean promotable;
   private AbstractStructVector.ConflictPolicy conflictPolicy;
   private MapWithOrdinal delegate;

   public PromotableMultiMapWithOrdinal(boolean promotable, AbstractStructVector.ConflictPolicy conflictPolicy) {
      this.promotable = promotable;
      this.conflictPolicy = conflictPolicy;
      this.delegate = this.mapWithOrdinal;
   }

   private void promote() {
      if (this.delegate != this.multiMapWithOrdinal && this.promotable && !this.conflictPolicy.equals(AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE)) {
         for(Object key : this.mapWithOrdinal.keys()) {
            V value = (V)this.mapWithOrdinal.get(key);
            this.multiMapWithOrdinal.put(key, value, false);
         }

         this.mapWithOrdinal.clear();
         this.delegate = this.multiMapWithOrdinal;
      }
   }

   public Object getByOrdinal(int id) {
      return this.delegate.getByOrdinal(id);
   }

   public int getOrdinal(Object key) {
      return this.delegate.getOrdinal(key);
   }

   public int size() {
      return this.delegate.size();
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public Object get(Object key) {
      return this.delegate.get(key);
   }

   public Collection getAll(Object key) {
      return this.delegate.getAll(key);
   }

   public boolean put(Object key, Object value, boolean overwrite) {
      if (this.delegate.containsKey(key)) {
         this.promote();
      }

      return this.delegate.put(key, value, overwrite);
   }

   public Collection values() {
      return this.delegate.values();
   }

   public boolean containsKey(Object key) {
      return this.delegate.containsKey(key);
   }

   public boolean remove(Object key, Object value) {
      return this.delegate.remove(key, value);
   }

   public boolean removeAll(Object key) {
      return this.delegate.removeAll(key);
   }

   public void clear() {
      this.delegate.clear();
   }

   public Set keys() {
      return this.delegate.keys();
   }

   public void setConflictPolicy(AbstractStructVector.ConflictPolicy conflictPolicy) {
      this.conflictPolicy = conflictPolicy;
   }
}
