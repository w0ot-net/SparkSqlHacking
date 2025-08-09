package org.apache.commons.collections4.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.IterableSortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;

public abstract class AbstractSortedMapDecorator extends AbstractMapDecorator implements IterableSortedMap {
   protected AbstractSortedMapDecorator() {
   }

   public AbstractSortedMapDecorator(SortedMap map) {
      super(map);
   }

   protected SortedMap decorated() {
      return (SortedMap)super.decorated();
   }

   public Comparator comparator() {
      return this.decorated().comparator();
   }

   public Object firstKey() {
      return this.decorated().firstKey();
   }

   public Object lastKey() {
      return this.decorated().lastKey();
   }

   public SortedMap subMap(Object fromKey, Object toKey) {
      return this.decorated().subMap(fromKey, toKey);
   }

   public SortedMap headMap(Object toKey) {
      return this.decorated().headMap(toKey);
   }

   public SortedMap tailMap(Object fromKey) {
      return this.decorated().tailMap(fromKey);
   }

   public Object previousKey(Object key) {
      SortedMap<K, V> headMap = this.headMap(key);
      return headMap.isEmpty() ? null : headMap.lastKey();
   }

   public Object nextKey(Object key) {
      Iterator<K> it = this.tailMap(key).keySet().iterator();
      it.next();
      return it.hasNext() ? it.next() : null;
   }

   public OrderedMapIterator mapIterator() {
      return new SortedMapIterator(this.entrySet());
   }

   protected static class SortedMapIterator extends EntrySetToMapIteratorAdapter implements OrderedMapIterator {
      protected SortedMapIterator(Set entrySet) {
         super(entrySet);
      }

      public synchronized void reset() {
         super.reset();
         this.iterator = new ListIteratorWrapper(this.iterator);
      }

      public boolean hasPrevious() {
         return ((ListIterator)this.iterator).hasPrevious();
      }

      public Object previous() {
         this.entry = (Map.Entry)((ListIterator)this.iterator).previous();
         return this.getKey();
      }
   }
}
