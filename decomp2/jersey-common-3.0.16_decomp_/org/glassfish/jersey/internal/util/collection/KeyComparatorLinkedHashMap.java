package org.glassfish.jersey.internal.util.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class KeyComparatorLinkedHashMap extends KeyComparatorHashMap implements Map {
   private static final long serialVersionUID = 3801124242820219131L;
   private transient Entry header;
   private final boolean accessOrder;

   public KeyComparatorLinkedHashMap(int initialCapacity, float loadFactor, KeyComparator keyComparator) {
      super(initialCapacity, loadFactor, keyComparator);
      this.accessOrder = false;
   }

   public KeyComparatorLinkedHashMap(int initialCapacity, KeyComparator keyComparator) {
      super(initialCapacity, keyComparator);
      this.accessOrder = false;
   }

   public KeyComparatorLinkedHashMap(KeyComparator keyComparator) {
      super(keyComparator);
      this.accessOrder = false;
   }

   public KeyComparatorLinkedHashMap(Map m, KeyComparator keyComparator) {
      super(m, keyComparator);
      this.accessOrder = false;
   }

   public KeyComparatorLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder, KeyComparator keyComparator) {
      super(initialCapacity, loadFactor, keyComparator);
      this.accessOrder = accessOrder;
   }

   void init() {
      this.header = new Entry(-1, (Object)null, (Object)null, (KeyComparatorHashMap.Entry)null);
      this.header.before = this.header.after = this.header;
   }

   void transfer(KeyComparatorHashMap.Entry[] newTable) {
      int newCapacity = newTable.length;

      for(Entry<K, V> e = this.header.after; e != this.header; e = e.after) {
         int index = indexFor(e.hash, newCapacity);
         e.next = newTable[index];
         newTable[index] = e;
      }

   }

   public boolean containsValue(Object value) {
      if (value == null) {
         for(Entry e = this.header.after; e != this.header; e = e.after) {
            if (e.value == null) {
               return true;
            }
         }
      } else {
         for(Entry e = this.header.after; e != this.header; e = e.after) {
            if (value.equals(e.value)) {
               return true;
            }
         }
      }

      return false;
   }

   public Object get(Object key) {
      Entry<K, V> e = (Entry)this.getEntry(key);
      if (e == null) {
         return null;
      } else {
         e.recordAccess(this);
         return e.value;
      }
   }

   public void clear() {
      super.clear();
      this.header.before = this.header.after = this.header;
   }

   Iterator newKeyIterator() {
      return new KeyIterator();
   }

   Iterator newValueIterator() {
      return new ValueIterator();
   }

   Iterator newEntryIterator() {
      return new EntryIterator();
   }

   void addEntry(int hash, Object key, Object value, int bucketIndex) {
      this.createEntry(hash, key, value, bucketIndex);
      Entry<K, V> eldest = this.header.after;
      if (this.removeEldestEntry(eldest)) {
         this.removeEntryForKey(eldest.key);
      } else if (this.size >= this.threshold) {
         this.resize(2 * this.table.length);
      }

   }

   void createEntry(int hash, Object key, Object value, int bucketIndex) {
      KeyComparatorHashMap.Entry<K, V> old = this.table[bucketIndex];
      Entry<K, V> e = new Entry(hash, key, value, old);
      this.table[bucketIndex] = e;
      e.addBefore(this.header);
      ++this.size;
   }

   protected boolean removeEldestEntry(Map.Entry eldest) {
      return false;
   }

   private static class Entry extends KeyComparatorHashMap.Entry {
      Entry before;
      Entry after;

      Entry(int hash, Object key, Object value, KeyComparatorHashMap.Entry next) {
         super(hash, key, value, next);
      }

      private void remove() {
         this.before.after = this.after;
         this.after.before = this.before;
      }

      private void addBefore(Entry existingEntry) {
         this.after = existingEntry;
         this.before = existingEntry.before;
         this.before.after = this;
         this.after.before = this;
      }

      void recordAccess(KeyComparatorHashMap m) {
         KeyComparatorLinkedHashMap<K, V> lm = (KeyComparatorLinkedHashMap)m;
         if (lm.accessOrder) {
            ++lm.modCount;
            this.remove();
            this.addBefore(lm.header);
         }

      }

      void recordRemoval(KeyComparatorHashMap m) {
         this.remove();
      }

      public boolean equals(Object obj) {
         return super.equals(obj);
      }

      public int hashCode() {
         return super.hashCode();
      }
   }

   private abstract class LinkedHashIterator implements Iterator {
      Entry nextEntry;
      Entry lastReturned;
      int expectedModCount;

      private LinkedHashIterator() {
         this.nextEntry = KeyComparatorLinkedHashMap.this.header.after;
         this.lastReturned = null;
         this.expectedModCount = KeyComparatorLinkedHashMap.this.modCount;
      }

      public boolean hasNext() {
         return this.nextEntry != KeyComparatorLinkedHashMap.this.header;
      }

      public void remove() {
         if (this.lastReturned == null) {
            throw new IllegalStateException();
         } else if (KeyComparatorLinkedHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            KeyComparatorLinkedHashMap.this.remove(this.lastReturned.key);
            this.lastReturned = null;
            this.expectedModCount = KeyComparatorLinkedHashMap.this.modCount;
         }
      }

      Entry nextEntry() {
         if (KeyComparatorLinkedHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else if (this.nextEntry == KeyComparatorLinkedHashMap.this.header) {
            throw new NoSuchElementException();
         } else {
            Entry<K, V> e = this.lastReturned = this.nextEntry;
            this.nextEntry = e.after;
            return e;
         }
      }
   }

   private class KeyIterator extends LinkedHashIterator {
      private KeyIterator() {
      }

      public Object next() {
         return this.nextEntry().getKey();
      }
   }

   private class ValueIterator extends LinkedHashIterator {
      private ValueIterator() {
      }

      public Object next() {
         return this.nextEntry().value;
      }
   }

   private class EntryIterator extends LinkedHashIterator {
      private EntryIterator() {
      }

      public Map.Entry next() {
         return this.nextEntry();
      }
   }
}
