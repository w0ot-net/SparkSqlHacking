package org.glassfish.jersey.internal.util.collection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.glassfish.jersey.internal.LocalizationMessages;

public class KeyComparatorHashMap extends AbstractMap implements Map, Cloneable, Serializable {
   private static final long serialVersionUID = 3000273665665137463L;
   static final int DEFAULT_INITIAL_CAPACITY = 16;
   static final int MAXIMUM_CAPACITY = 1073741824;
   static final float DEFAULT_LOAD_FACTOR = 0.75F;
   transient Entry[] table;
   transient int size;
   int threshold;
   final float loadFactor;
   transient volatile int modCount;
   final KeyComparator keyComparator;
   static final Object NULL_KEY = new Object();
   private transient Set entrySet;

   public KeyComparatorHashMap(int initialCapacity, float loadFactor, KeyComparator keyComparator) {
      this.entrySet = null;
      if (initialCapacity < 0) {
         throw new IllegalArgumentException(LocalizationMessages.ILLEGAL_INITIAL_CAPACITY(initialCapacity));
      } else {
         if (initialCapacity > 1073741824) {
            initialCapacity = 1073741824;
         }

         if (!(loadFactor <= 0.0F) && !Float.isNaN(loadFactor)) {
            int capacity;
            for(capacity = 1; capacity < initialCapacity; capacity <<= 1) {
            }

            this.loadFactor = loadFactor;
            this.threshold = (int)((float)capacity * loadFactor);
            this.table = new Entry[capacity];
            this.keyComparator = keyComparator;
            this.init();
         } else {
            throw new IllegalArgumentException(LocalizationMessages.ILLEGAL_LOAD_FACTOR(loadFactor));
         }
      }
   }

   public KeyComparatorHashMap(int initialCapacity, KeyComparator keyComparator) {
      this(initialCapacity, 0.75F, keyComparator);
   }

   public KeyComparatorHashMap(KeyComparator keyComparator) {
      this(16, 0.75F, keyComparator);
   }

   public KeyComparatorHashMap(Map m, KeyComparator keyComparator) {
      this(Math.max((int)((float)m.size() / 0.75F) + 1, 16), 0.75F, keyComparator);
      this.putAllForCreate(m);
   }

   public int getModCount() {
      return this.modCount;
   }

   void init() {
   }

   static Object maskNull(Object key) {
      return key == null ? NULL_KEY : key;
   }

   static boolean isNull(Object key) {
      return key == NULL_KEY;
   }

   static Object unmaskNull(Object key) {
      return key == NULL_KEY ? null : key;
   }

   static int hash(Object x) {
      int h = x.hashCode();
      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   static boolean eq(Object x, Object y) {
      return x == y || x.equals(y);
   }

   static int indexFor(int h, int length) {
      return h & length - 1;
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   int keyComparatorHash(Object k) {
      return isNull(k) ? this.hash(k.hashCode()) : this.hash(this.keyComparator.hash(k));
   }

   int hash(int h) {
      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   boolean keyComparatorEq(Object x, Object y) {
      if (isNull(x)) {
         return x == y;
      } else if (isNull(y)) {
         return x == y;
      } else {
         return x == y || this.keyComparator.equals(x, y);
      }
   }

   public Object get(Object key) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);

      for(Entry<K, V> e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && this.keyComparatorEq(k, e.key)) {
            return e.value;
         }
      }

      return null;
   }

   public boolean containsKey(Object key) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);

      for(Entry<K, V> e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && this.keyComparatorEq(k, e.key)) {
            return true;
         }
      }

      return false;
   }

   Entry getEntry(Object key) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);

      Entry<K, V> e;
      for(e = this.table[i]; e != null && (e.hash != hash || !this.keyComparatorEq(k, e.key)); e = e.next) {
      }

      return e;
   }

   public Object put(Object key, Object value) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);

      for(Entry<K, V> e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && this.keyComparatorEq(k, e.key)) {
            V oldValue = (V)e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
         }
      }

      ++this.modCount;
      this.addEntry(hash, k, value, i);
      return null;
   }

   private void putForCreate(Object key, Object value) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);

      for(Entry<K, V> e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && this.keyComparatorEq(k, e.key)) {
            e.value = value;
            return;
         }
      }

      this.createEntry(hash, k, value, i);
   }

   private void putAllForCreate(Map m) {
      for(Map.Entry e : m.entrySet()) {
         this.putForCreate(e.getKey(), e.getValue());
      }

   }

   void resize(int newCapacity) {
      Entry<K, V>[] oldTable = this.table;
      int oldCapacity = oldTable.length;
      if (oldCapacity == 1073741824) {
         this.threshold = Integer.MAX_VALUE;
      } else {
         Entry<K, V>[] newTable = new Entry[newCapacity];
         this.transfer(newTable);
         this.table = newTable;
         this.threshold = (int)((float)newCapacity * this.loadFactor);
      }
   }

   void transfer(Entry[] newTable) {
      Entry<K, V>[] src = this.table;
      int newCapacity = newTable.length;

      for(int j = 0; j < src.length; ++j) {
         Entry<K, V> e = src[j];
         if (e != null) {
            src[j] = null;

            while(true) {
               Entry<K, V> next = e.next;
               int i = indexFor(e.hash, newCapacity);
               e.next = newTable[i];
               newTable[i] = e;
               e = next;
               if (next == null) {
                  break;
               }
            }
         }
      }

   }

   public void putAll(Map m) {
      int numKeysToBeAdded = m.size();
      if (numKeysToBeAdded != 0) {
         if (numKeysToBeAdded > this.threshold) {
            int targetCapacity = (int)((float)numKeysToBeAdded / this.loadFactor + 1.0F);
            if (targetCapacity > 1073741824) {
               targetCapacity = 1073741824;
            }

            int newCapacity;
            for(newCapacity = this.table.length; newCapacity < targetCapacity; newCapacity <<= 1) {
            }

            if (newCapacity > this.table.length) {
               this.resize(newCapacity);
            }
         }

         for(Map.Entry e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
         }

      }
   }

   public Object remove(Object key) {
      Entry<K, V> e = this.removeEntryForKey(key);
      return e == null ? null : e.value;
   }

   Entry removeEntryForKey(Object key) {
      K k = (K)maskNull(key);
      int hash = this.keyComparatorHash(k);
      int i = indexFor(hash, this.table.length);
      Entry<K, V> prev = this.table[i];

      Entry<K, V> e;
      Entry<K, V> next;
      for(e = prev; e != null; e = next) {
         next = e.next;
         if (e.hash == hash && this.keyComparatorEq(k, e.key)) {
            ++this.modCount;
            --this.size;
            if (prev == e) {
               this.table[i] = next;
            } else {
               prev.next = next;
            }

            e.recordRemoval(this);
            return e;
         }

         prev = e;
      }

      return e;
   }

   Entry removeMapping(Object o) {
      if (!(o instanceof Map.Entry)) {
         return null;
      } else {
         Map.Entry<K, V> entry = (Map.Entry)o;
         K k = (K)maskNull(entry.getKey());
         int hash = this.keyComparatorHash(k);
         int i = indexFor(hash, this.table.length);
         Entry<K, V> prev = this.table[i];

         Entry<K, V> e;
         Entry<K, V> next;
         for(e = prev; e != null; e = next) {
            next = e.next;
            if (e.hash == hash && e.equals(entry)) {
               ++this.modCount;
               --this.size;
               if (prev == e) {
                  this.table[i] = next;
               } else {
                  prev.next = next;
               }

               e.recordRemoval(this);
               return e;
            }

            prev = e;
         }

         return e;
      }
   }

   public void clear() {
      ++this.modCount;
      Entry[] tab = this.table;

      for(int i = 0; i < tab.length; ++i) {
         tab[i] = null;
      }

      this.size = 0;
   }

   public boolean containsValue(Object value) {
      if (value == null) {
         return this.containsNullValue();
      } else {
         Entry[] tab = this.table;

         for(int i = 0; i < tab.length; ++i) {
            for(Entry e = tab[i]; e != null; e = e.next) {
               if (value.equals(e.value)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private boolean containsNullValue() {
      Entry[] tab = this.table;

      for(Entry aTab : tab) {
         for(Entry e = aTab; e != null; e = e.next) {
            if (e.value == null) {
               return true;
            }
         }
      }

      return false;
   }

   public Object clone() {
      KeyComparatorHashMap<K, V> result = null;

      try {
         result = (KeyComparatorHashMap)super.clone();
         result.table = new Entry[this.table.length];
         result.entrySet = null;
         result.modCount = 0;
         result.size = 0;
         result.init();
         result.putAllForCreate(this);
      } catch (CloneNotSupportedException var3) {
      }

      return result;
   }

   void addEntry(int hash, Object key, Object value, int bucketIndex) {
      Entry<K, V> e = this.table[bucketIndex];
      this.table[bucketIndex] = new Entry(hash, key, value, e);
      if (this.size++ >= this.threshold) {
         this.resize(2 * this.table.length);
      }

   }

   void createEntry(int hash, Object key, Object value, int bucketIndex) {
      Entry<K, V> e = this.table[bucketIndex];
      this.table[bucketIndex] = new Entry(hash, key, value, e);
      ++this.size;
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

   public Set entrySet() {
      Set<Map.Entry<K, V>> es = this.entrySet;
      return es != null ? es : (this.entrySet = new EntrySet());
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      Iterator<Map.Entry<K, V>> i = this.entrySet().iterator();
      s.defaultWriteObject();
      s.writeInt(this.table.length);
      s.writeInt(this.size);

      while(i.hasNext()) {
         Map.Entry<K, V> e = (Map.Entry)i.next();
         s.writeObject(e.getKey());
         s.writeObject(e.getValue());
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      int numBuckets = s.readInt();
      this.table = new Entry[numBuckets];
      this.init();
      int ss = s.readInt();

      for(int i = 0; i < ss; ++i) {
         K key = (K)s.readObject();
         V value = (V)s.readObject();
         this.putForCreate(key, value);
      }

   }

   int capacity() {
      return this.table.length;
   }

   float loadFactor() {
      return this.loadFactor;
   }

   static class Entry implements Map.Entry {
      final Object key;
      Object value;
      final int hash;
      Entry next;

      Entry(int h, Object k, Object v, Entry n) {
         this.value = v;
         this.next = n;
         this.key = k;
         this.hash = h;
      }

      public Object getKey() {
         return KeyComparatorHashMap.unmaskNull(this.key);
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object newValue) {
         V oldValue = (V)this.value;
         this.value = newValue;
         return oldValue;
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry e = (Map.Entry)o;
            Object k1 = this.getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || k1 != null && k1.equals(k2)) {
               Object v1 = this.getValue();
               Object v2 = e.getValue();
               if (v1 == v2 || v1 != null && v1.equals(v2)) {
                  return true;
               }
            }

            return false;
         }
      }

      public int hashCode() {
         return (this.key == KeyComparatorHashMap.NULL_KEY ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public String toString() {
         return this.getKey() + "=" + this.getValue();
      }

      void recordAccess(KeyComparatorHashMap m) {
      }

      void recordRemoval(KeyComparatorHashMap m) {
      }
   }

   private abstract class HashIterator implements Iterator {
      Entry next;
      int expectedModCount;
      int index;
      Entry current;

      HashIterator() {
         this.expectedModCount = KeyComparatorHashMap.this.modCount;
         Entry<K, V>[] t = KeyComparatorHashMap.this.table;
         int i = t.length;
         Entry<K, V> n = null;
         if (KeyComparatorHashMap.this.size != 0) {
            while(i > 0) {
               --i;
               if ((n = t[i]) != null) {
                  break;
               }
            }
         }

         this.next = n;
         this.index = i;
      }

      public boolean hasNext() {
         return this.next != null;
      }

      Entry nextEntry() {
         if (KeyComparatorHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            Entry<K, V> e = this.next;
            if (e == null) {
               throw new NoSuchElementException();
            } else {
               Entry<K, V> n = e.next;
               Entry<K, V>[] t = KeyComparatorHashMap.this.table;

               int i;
               for(i = this.index; n == null && i > 0; n = t[i]) {
                  --i;
               }

               this.index = i;
               this.next = n;
               return this.current = e;
            }
         }
      }

      public void remove() {
         if (this.current == null) {
            throw new IllegalStateException();
         } else if (KeyComparatorHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            K k = (K)this.current.key;
            this.current = null;
            KeyComparatorHashMap.this.removeEntryForKey(k);
            this.expectedModCount = KeyComparatorHashMap.this.modCount;
         }
      }
   }

   private class ValueIterator extends HashIterator {
      private ValueIterator() {
      }

      public Object next() {
         return this.nextEntry().value;
      }
   }

   private class KeyIterator extends HashIterator {
      private KeyIterator() {
      }

      public Object next() {
         return this.nextEntry().getKey();
      }
   }

   private class EntryIterator extends HashIterator {
      private EntryIterator() {
      }

      public Map.Entry next() {
         return this.nextEntry();
      }
   }

   private class EntrySet extends AbstractSet {
      private EntrySet() {
      }

      public Iterator iterator() {
         return KeyComparatorHashMap.this.newEntryIterator();
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<K, V> e = (Map.Entry)o;
            Entry<K, V> candidate = KeyComparatorHashMap.this.getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
         }
      }

      public boolean remove(Object o) {
         return KeyComparatorHashMap.this.removeMapping(o) != null;
      }

      public int size() {
         return KeyComparatorHashMap.this.size;
      }

      public void clear() {
         KeyComparatorHashMap.this.clear();
      }
   }
}
