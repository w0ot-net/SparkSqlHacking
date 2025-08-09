package org.glassfish.jersey.internal.guava;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class AbstractMultimap implements Multimap {
   private transient Collection entries;
   private transient Set keySet;
   private transient Collection values;
   private transient Map asMap;

   public boolean containsValue(Object value) {
      for(Collection collection : this.asMap().values()) {
         if (collection.contains(value)) {
            return true;
         }
      }

      return false;
   }

   public boolean containsEntry(Object key, Object value) {
      Collection<V> collection = (Collection)this.asMap().get(key);
      return collection != null && collection.contains(value);
   }

   public boolean remove(Object key, Object value) {
      Collection<V> collection = (Collection)this.asMap().get(key);
      return collection != null && collection.remove(value);
   }

   public boolean put(Object key, Object value) {
      return this.get(key).add(value);
   }

   public boolean putAll(Object key, Iterable values) {
      Preconditions.checkNotNull(values);
      if (values instanceof Collection) {
         Collection<? extends V> valueCollection = (Collection)values;
         return !valueCollection.isEmpty() && this.get(key).addAll(valueCollection);
      } else {
         Iterator<? extends V> valueItr = values.iterator();
         return valueItr.hasNext() && Iterators.addAll(this.get(key), valueItr);
      }
   }

   public Collection entries() {
      Collection<Map.Entry<K, V>> result = this.entries;
      return result == null ? (this.entries = this.createEntries()) : result;
   }

   private Collection createEntries() {
      return (Collection)(this instanceof SetMultimap ? new EntrySet() : new Entries());
   }

   abstract Iterator entryIterator();

   public Set keySet() {
      Set<K> result = this.keySet;
      return result == null ? (this.keySet = this.createKeySet()) : result;
   }

   Set createKeySet() {
      return new Maps.KeySet(this.asMap());
   }

   public Collection values() {
      Collection<V> result = this.values;
      return result == null ? (this.values = this.createValues()) : result;
   }

   private Collection createValues() {
      return new Values();
   }

   Iterator valueIterator() {
      return Maps.valueIterator(this.entries().iterator());
   }

   public Map asMap() {
      Map<K, Collection<V>> result = this.asMap;
      return result == null ? (this.asMap = this.createAsMap()) : result;
   }

   abstract Map createAsMap();

   public boolean equals(Object object) {
      return Multimaps.equalsImpl(this, object);
   }

   public int hashCode() {
      return this.asMap().hashCode();
   }

   public String toString() {
      return this.asMap().toString();
   }

   private class Entries extends Multimaps.Entries {
      private Entries() {
      }

      Multimap multimap() {
         return AbstractMultimap.this;
      }

      public Iterator iterator() {
         return AbstractMultimap.this.entryIterator();
      }
   }

   private class EntrySet extends Entries implements Set {
      private EntrySet() {
      }

      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }

      public boolean equals(Object obj) {
         return Sets.equalsImpl(this, obj);
      }
   }

   private class Values extends AbstractCollection {
      private Values() {
      }

      public Iterator iterator() {
         return AbstractMultimap.this.valueIterator();
      }

      public int size() {
         return AbstractMultimap.this.size();
      }

      public boolean contains(Object o) {
         return AbstractMultimap.this.containsValue(o);
      }

      public void clear() {
         AbstractMultimap.this.clear();
      }
   }
}
