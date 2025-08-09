package org.apache.avro.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WeakIdentityHashMap implements Map {
   private final ReferenceQueue queue = new ReferenceQueue();
   private Map backingStore = new ConcurrentHashMap();

   public void clear() {
      this.backingStore.clear();
      this.reap();
   }

   public boolean containsKey(Object key) {
      this.reap();
      return this.backingStore.containsKey(new IdentityWeakReference(key));
   }

   public boolean containsValue(Object value) {
      this.reap();
      return this.backingStore.containsValue(value);
   }

   public Set entrySet() {
      this.reap();
      Set<Map.Entry<K, V>> ret = new HashSet();

      for(Map.Entry ref : this.backingStore.entrySet()) {
         final K key = (K)((IdentityWeakReference)ref.getKey()).get();
         final V value = (V)ref.getValue();
         Map.Entry<K, V> entry = new Map.Entry() {
            public Object getKey() {
               return key;
            }

            public Object getValue() {
               return value;
            }

            public Object setValue(Object valuex) {
               throw new UnsupportedOperationException();
            }
         };
         ret.add(entry);
      }

      return Collections.unmodifiableSet(ret);
   }

   public Set keySet() {
      this.reap();
      Set<K> ret = new HashSet();

      for(IdentityWeakReference ref : this.backingStore.keySet()) {
         ret.add(ref.get());
      }

      return Collections.unmodifiableSet(ret);
   }

   public boolean equals(Object o) {
      return !(o instanceof WeakIdentityHashMap) ? false : this.backingStore.equals(((WeakIdentityHashMap)o).backingStore);
   }

   public Object get(Object key) {
      this.reap();
      return this.backingStore.get(new IdentityWeakReference(key));
   }

   public Object put(Object key, Object value) {
      this.reap();
      return this.backingStore.put(new IdentityWeakReference(key), value);
   }

   public int hashCode() {
      this.reap();
      return this.backingStore.hashCode();
   }

   public boolean isEmpty() {
      this.reap();
      return this.backingStore.isEmpty();
   }

   public void putAll(Map t) {
      throw new UnsupportedOperationException();
   }

   public Object remove(Object key) {
      this.reap();
      return this.backingStore.remove(new IdentityWeakReference(key));
   }

   public int size() {
      this.reap();
      return this.backingStore.size();
   }

   public Collection values() {
      this.reap();
      return this.backingStore.values();
   }

   private synchronized void reap() {
      for(Object zombie = this.queue.poll(); zombie != null; zombie = this.queue.poll()) {
         WeakIdentityHashMap<K, V>.IdentityWeakReference victim = (IdentityWeakReference)zombie;
         this.backingStore.remove(victim);
      }

   }

   class IdentityWeakReference extends WeakReference {
      int hash;

      IdentityWeakReference(Object obj) {
         super(obj, WeakIdentityHashMap.this.queue);
         this.hash = System.identityHashCode(obj);
      }

      public int hashCode() {
         return this.hash;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof IdentityWeakReference)) {
            return false;
         } else {
            WeakIdentityHashMap<K, V>.IdentityWeakReference ref = (IdentityWeakReference)o;
            return this.get() == ref.get();
         }
      }
   }
}
