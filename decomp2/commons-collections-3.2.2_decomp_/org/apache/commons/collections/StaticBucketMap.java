package org.apache.commons.collections;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/** @deprecated */
public final class StaticBucketMap implements Map {
   private static final int DEFAULT_BUCKETS = 255;
   private Node[] m_buckets;
   private Lock[] m_locks;

   public StaticBucketMap() {
      this(255);
   }

   public StaticBucketMap(int numBuckets) {
      int size = Math.max(17, numBuckets);
      if (size % 2 == 0) {
         --size;
      }

      this.m_buckets = new Node[size];
      this.m_locks = new Lock[size];

      for(int i = 0; i < size; ++i) {
         this.m_locks[i] = new Lock();
      }

   }

   private final int getHash(Object key) {
      if (key == null) {
         return 0;
      } else {
         int hash = key.hashCode();
         hash += ~(hash << 15);
         hash ^= hash >>> 10;
         hash += hash << 3;
         hash ^= hash >>> 6;
         hash += ~(hash << 11);
         hash ^= hash >>> 16;
         hash %= this.m_buckets.length;
         return hash < 0 ? hash * -1 : hash;
      }
   }

   public Set keySet() {
      return new KeySet();
   }

   public int size() {
      int cnt = 0;

      for(int i = 0; i < this.m_buckets.length; ++i) {
         cnt += this.m_locks[i].size;
      }

      return cnt;
   }

   public Object put(Object key, Object value) {
      int hash = this.getHash(key);
      synchronized(this.m_locks[hash]) {
         Node n = this.m_buckets[hash];
         if (n == null) {
            n = new Node();
            n.key = key;
            n.value = value;
            this.m_buckets[hash] = n;
            ++this.m_locks[hash].size;
            return null;
         } else {
            for(Node next = n; next != null; next = next.next) {
               n = next;
               if (next.key == key || next.key != null && next.key.equals(key)) {
                  Object returnVal = next.value;
                  next.value = value;
                  return returnVal;
               }
            }

            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            n.next = newNode;
            ++this.m_locks[hash].size;
            return null;
         }
      }
   }

   public Object get(Object key) {
      int hash = this.getHash(key);
      synchronized(this.m_locks[hash]) {
         for(Node n = this.m_buckets[hash]; n != null; n = n.next) {
            if (n.key == key || n.key != null && n.key.equals(key)) {
               return n.value;
            }
         }

         return null;
      }
   }

   public boolean containsKey(Object key) {
      int hash = this.getHash(key);
      synchronized(this.m_locks[hash]) {
         for(Node n = this.m_buckets[hash]; n != null; n = n.next) {
            if (n.key == key || n.key != null && n.key.equals(key)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean containsValue(Object value) {
      for(int i = 0; i < this.m_buckets.length; ++i) {
         synchronized(this.m_locks[i]) {
            for(Node n = this.m_buckets[i]; n != null; n = n.next) {
               if (n.value == value || n.value != null && n.value.equals(value)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public Collection values() {
      return new Values();
   }

   public Set entrySet() {
      return new EntrySet();
   }

   public void putAll(Map other) {
      for(Object key : other.keySet()) {
         this.put(key, other.get(key));
      }

   }

   public Object remove(Object key) {
      int hash = this.getHash(key);
      synchronized(this.m_locks[hash]) {
         Node n = this.m_buckets[hash];

         for(Node prev = null; n != null; n = n.next) {
            if (n.key == key || n.key != null && n.key.equals(key)) {
               if (null == prev) {
                  this.m_buckets[hash] = n.next;
               } else {
                  prev.next = n.next;
               }

               --this.m_locks[hash].size;
               return n.value;
            }

            prev = n;
         }

         return null;
      }
   }

   public final boolean isEmpty() {
      return this.size() == 0;
   }

   public final void clear() {
      for(int i = 0; i < this.m_buckets.length; ++i) {
         Lock lock = this.m_locks[i];
         synchronized(lock) {
            this.m_buckets[i] = null;
            lock.size = 0;
         }
      }

   }

   public final boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else if (!(obj instanceof Map)) {
         return false;
      } else {
         Map other = (Map)obj;
         return this.entrySet().equals(other.entrySet());
      }
   }

   public final int hashCode() {
      int hashCode = 0;

      for(int i = 0; i < this.m_buckets.length; ++i) {
         synchronized(this.m_locks[i]) {
            for(Node n = this.m_buckets[i]; n != null; n = n.next) {
               hashCode += n.hashCode();
            }
         }
      }

      return hashCode;
   }

   public void atomic(Runnable r) {
      if (r == null) {
         throw new NullPointerException();
      } else {
         this.atomic(r, 0);
      }
   }

   private void atomic(Runnable r, int bucket) {
      if (bucket >= this.m_buckets.length) {
         r.run();
      } else {
         synchronized(this.m_locks[bucket]) {
            this.atomic(r, bucket + 1);
         }
      }
   }

   private static final class Node implements Map.Entry, KeyValue {
      protected Object key;
      protected Object value;
      protected Node next;

      private Node() {
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public boolean equals(Object o) {
         if (o == null) {
            return false;
         } else if (o == this) {
            return true;
         } else if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label48: {
               label32: {
                  Map.Entry e2 = (Map.Entry)o;
                  if (this.key == null) {
                     if (e2.getKey() != null) {
                        break label32;
                     }
                  } else if (!this.key.equals(e2.getKey())) {
                     break label32;
                  }

                  if (this.value == null) {
                     if (e2.getValue() == null) {
                        break label48;
                     }
                  } else if (this.value.equals(e2.getValue())) {
                     break label48;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public Object setValue(Object val) {
         Object retVal = this.value;
         this.value = val;
         return retVal;
      }
   }

   private static final class Lock {
      public int size;

      private Lock() {
      }
   }

   private class EntryIterator implements Iterator {
      private ArrayList current;
      private int bucket;
      private Map.Entry last;

      private EntryIterator() {
         this.current = new ArrayList();
      }

      public boolean hasNext() {
         if (this.current.size() > 0) {
            return true;
         } else {
            while(this.bucket < StaticBucketMap.this.m_buckets.length) {
               synchronized(StaticBucketMap.this.m_locks[this.bucket]) {
                  for(Node n = StaticBucketMap.this.m_buckets[this.bucket]; n != null; n = n.next) {
                     this.current.add(n);
                  }

                  ++this.bucket;
                  if (this.current.size() > 0) {
                     return true;
                  }
               }
            }

            return false;
         }
      }

      protected Map.Entry nextEntry() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.last = (Map.Entry)this.current.remove(this.current.size() - 1);
            return this.last;
         }
      }

      public Object next() {
         return this.nextEntry();
      }

      public void remove() {
         if (this.last == null) {
            throw new IllegalStateException();
         } else {
            StaticBucketMap.this.remove(this.last.getKey());
            this.last = null;
         }
      }
   }

   private class ValueIterator extends EntryIterator {
      private ValueIterator() {
      }

      public Object next() {
         return this.nextEntry().getValue();
      }
   }

   private class KeyIterator extends EntryIterator {
      private KeyIterator() {
      }

      public Object next() {
         return this.nextEntry().getKey();
      }
   }

   private class EntrySet extends AbstractSet {
      private EntrySet() {
      }

      public int size() {
         return StaticBucketMap.this.size();
      }

      public void clear() {
         StaticBucketMap.this.clear();
      }

      public Iterator iterator() {
         return StaticBucketMap.this.new EntryIterator();
      }

      public boolean contains(Object o) {
         Map.Entry entry = (Map.Entry)o;
         int hash = StaticBucketMap.this.getHash(entry.getKey());
         synchronized(StaticBucketMap.this.m_locks[hash]) {
            for(Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
               if (n.equals(entry)) {
                  return true;
               }
            }

            return false;
         }
      }

      public boolean remove(Object obj) {
         if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry entry = (Map.Entry)obj;
            int hash = StaticBucketMap.this.getHash(entry.getKey());
            synchronized(StaticBucketMap.this.m_locks[hash]) {
               for(Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
                  if (n.equals(entry)) {
                     StaticBucketMap.this.remove(n.getKey());
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }

   private class KeySet extends AbstractSet {
      private KeySet() {
      }

      public int size() {
         return StaticBucketMap.this.size();
      }

      public void clear() {
         StaticBucketMap.this.clear();
      }

      public Iterator iterator() {
         return StaticBucketMap.this.new KeyIterator();
      }

      public boolean contains(Object o) {
         return StaticBucketMap.this.containsKey(o);
      }

      public boolean remove(Object o) {
         int hash = StaticBucketMap.this.getHash(o);
         synchronized(StaticBucketMap.this.m_locks[hash]) {
            for(Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
               Object k = n.getKey();
               if (k == o || k != null && k.equals(o)) {
                  StaticBucketMap.this.remove(k);
                  return true;
               }
            }

            return false;
         }
      }
   }

   private class Values extends AbstractCollection {
      private Values() {
      }

      public int size() {
         return StaticBucketMap.this.size();
      }

      public void clear() {
         StaticBucketMap.this.clear();
      }

      public Iterator iterator() {
         return StaticBucketMap.this.new ValueIterator();
      }
   }
}
