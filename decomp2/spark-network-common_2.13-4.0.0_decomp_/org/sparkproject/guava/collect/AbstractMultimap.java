package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMultimap implements Multimap {
   @LazyInit
   @CheckForNull
   private transient Collection entries;
   @LazyInit
   @CheckForNull
   private transient Set keySet;
   @LazyInit
   @CheckForNull
   private transient Multiset keys;
   @LazyInit
   @CheckForNull
   private transient Collection values;
   @LazyInit
   @CheckForNull
   private transient Map asMap;

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsValue(@CheckForNull Object value) {
      for(Collection collection : this.asMap().values()) {
         if (collection.contains(value)) {
            return true;
         }
      }

      return false;
   }

   public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
      Collection<V> collection = (Collection)this.asMap().get(key);
      return collection != null && collection.contains(value);
   }

   @CanIgnoreReturnValue
   public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      Collection<V> collection = (Collection)this.asMap().get(key);
      return collection != null && collection.remove(value);
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.get(key).add(value);
   }

   @CanIgnoreReturnValue
   public boolean putAll(@ParametricNullness Object key, Iterable values) {
      Preconditions.checkNotNull(values);
      if (values instanceof Collection) {
         Collection<? extends V> valueCollection = (Collection)values;
         return !valueCollection.isEmpty() && this.get(key).addAll(valueCollection);
      } else {
         Iterator<? extends V> valueItr = values.iterator();
         return valueItr.hasNext() && Iterators.addAll(this.get(key), valueItr);
      }
   }

   @CanIgnoreReturnValue
   public boolean putAll(Multimap multimap) {
      boolean changed = false;

      for(Map.Entry entry : multimap.entries()) {
         changed |= this.put(entry.getKey(), entry.getValue());
      }

      return changed;
   }

   @CanIgnoreReturnValue
   public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
      Preconditions.checkNotNull(values);
      Collection<V> result = this.removeAll(key);
      this.putAll(key, values);
      return result;
   }

   public Collection entries() {
      Collection<Map.Entry<K, V>> result = this.entries;
      return result == null ? (this.entries = this.createEntries()) : result;
   }

   abstract Collection createEntries();

   abstract Iterator entryIterator();

   Spliterator entrySpliterator() {
      return Spliterators.spliterator(this.entryIterator(), (long)this.size(), this instanceof SetMultimap ? 1 : 0);
   }

   public Set keySet() {
      Set<K> result = this.keySet;
      return result == null ? (this.keySet = this.createKeySet()) : result;
   }

   abstract Set createKeySet();

   public Multiset keys() {
      Multiset<K> result = this.keys;
      return result == null ? (this.keys = this.createKeys()) : result;
   }

   abstract Multiset createKeys();

   public Collection values() {
      Collection<V> result = this.values;
      return result == null ? (this.values = this.createValues()) : result;
   }

   abstract Collection createValues();

   Iterator valueIterator() {
      return Maps.valueIterator(this.entries().iterator());
   }

   Spliterator valueSpliterator() {
      return Spliterators.spliterator(this.valueIterator(), (long)this.size(), 0);
   }

   public Map asMap() {
      Map<K, Collection<V>> result = this.asMap;
      return result == null ? (this.asMap = this.createAsMap()) : result;
   }

   abstract Map createAsMap();

   public boolean equals(@CheckForNull Object object) {
      return Multimaps.equalsImpl(this, object);
   }

   public int hashCode() {
      return this.asMap().hashCode();
   }

   public String toString() {
      return this.asMap().toString();
   }

   class Entries extends Multimaps.Entries {
      Multimap multimap() {
         return AbstractMultimap.this;
      }

      public Iterator iterator() {
         return AbstractMultimap.this.entryIterator();
      }

      public Spliterator spliterator() {
         return AbstractMultimap.this.entrySpliterator();
      }
   }

   class EntrySet extends Entries implements Set {
      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }

      public boolean equals(@CheckForNull Object obj) {
         return Sets.equalsImpl(this, obj);
      }
   }

   class Values extends AbstractCollection {
      public Iterator iterator() {
         return AbstractMultimap.this.valueIterator();
      }

      public Spliterator spliterator() {
         return AbstractMultimap.this.valueSpliterator();
      }

      public int size() {
         return AbstractMultimap.this.size();
      }

      public boolean contains(@CheckForNull Object o) {
         return AbstractMultimap.this.containsValue(o);
      }

      public void clear() {
         AbstractMultimap.this.clear();
      }
   }
}
