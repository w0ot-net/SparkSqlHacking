package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.general.WeakHashLRU;

public class WeakHashLRUImpl implements WeakHashLRU {
   private static final Object VALUE = new Object();
   private final boolean isWeak;
   private final WeakHashMap byKey;
   private final ConcurrentHashMap byKeyNotWeak;
   private final ReferenceQueue myQueue = new ReferenceQueue();
   private DoubleNode mru;
   private DoubleNode lru;

   public WeakHashLRUImpl(boolean isWeak) {
      this.isWeak = isWeak;
      if (isWeak) {
         this.byKey = new WeakHashMap();
         this.byKeyNotWeak = null;
      } else {
         this.byKey = null;
         this.byKeyNotWeak = new ConcurrentHashMap();
      }

   }

   private DoubleNode addToHead(Object key) {
      DoubleNode<K, Object> added = new DoubleNode(key, VALUE, this.myQueue);
      if (this.mru == null) {
         this.mru = added;
         this.lru = added;
         return added;
      } else {
         added.setNext(this.mru);
         this.mru.setPrevious(added);
         this.mru = added;
         return added;
      }
   }

   private Object remove(DoubleNode removeMe) {
      K retVal = (K)removeMe.getWeakKey().get();
      if (removeMe.getNext() != null) {
         removeMe.getNext().setPrevious(removeMe.getPrevious());
      }

      if (removeMe.getPrevious() != null) {
         removeMe.getPrevious().setNext(removeMe.getNext());
      }

      if (removeMe == this.mru) {
         this.mru = removeMe.getNext();
      }

      if (removeMe == this.lru) {
         this.lru = removeMe.getPrevious();
      }

      removeMe.setNext((DoubleNode)null);
      removeMe.setPrevious((DoubleNode)null);
      return retVal;
   }

   public synchronized void add(Object key) {
      if (key == null) {
         throw new IllegalArgumentException("key may not be null");
      } else {
         DoubleNode<K, Object> existing;
         if (this.isWeak) {
            this.clearStale();
            existing = (DoubleNode)this.byKey.get(key);
         } else {
            existing = (DoubleNode)this.byKeyNotWeak.get(key);
         }

         if (existing != null) {
            this.remove(existing);
         }

         DoubleNode<K, Object> added = this.addToHead(key);
         if (this.isWeak) {
            this.byKey.put(key, added);
         } else {
            this.byKeyNotWeak.put(key, added);
         }

      }
   }

   public boolean contains(Object key) {
      if (this.isWeak) {
         synchronized(this) {
            this.clearStale();
            return this.byKey.containsKey(key);
         }
      } else {
         return this.byKeyNotWeak.containsKey(key);
      }
   }

   public synchronized boolean remove(Object key) {
      if (this.isWeak) {
         this.clearStale();
      }

      return this.removeNoClear(key);
   }

   private boolean removeNoClear(Object key) {
      if (key == null) {
         return false;
      } else {
         DoubleNode<K, Object> removeMe;
         if (this.isWeak) {
            removeMe = (DoubleNode)this.byKey.remove(key);
         } else {
            removeMe = (DoubleNode)this.byKeyNotWeak.remove(key);
         }

         if (removeMe == null) {
            return false;
         } else {
            this.remove(removeMe);
            return true;
         }
      }
   }

   public int size() {
      if (this.isWeak) {
         synchronized(this) {
            this.clearStale();
            return this.byKey.size();
         }
      } else {
         return this.byKeyNotWeak.size();
      }
   }

   public synchronized Object remove() {
      Object var1;
      try {
         if (this.lru != null) {
            DoubleNode<K, Object> previous;
            for(DoubleNode<K, Object> current = this.lru; current != null; current = previous) {
               previous = current.getPrevious();
               K retVal = (K)current.getWeakKey().get();
               if (retVal != null) {
                  this.removeNoClear(retVal);
                  Object var4 = retVal;
                  return var4;
               }

               this.remove(current);
            }

            previous = null;
            return previous;
         }

         var1 = null;
      } finally {
         this.clearStale();
      }

      return var1;
   }

   public synchronized void releaseMatching(CacheKeyFilter filter) {
      if (filter != null) {
         if (this.isWeak) {
            this.clearStale();
         }

         LinkedList<K> removeMe = new LinkedList();

         for(DoubleNode<K, Object> current = this.mru; current != null; current = current.getNext()) {
            K key = (K)current.getWeakKey().get();
            if (key != null && filter.matches(key)) {
               removeMe.add(key);
            }
         }

         for(Object removeKey : removeMe) {
            this.removeNoClear(removeKey);
         }

      }
   }

   public synchronized void clear() {
      if (this.isWeak) {
         this.clearStale();
         this.byKey.clear();
      } else {
         this.byKeyNotWeak.clear();
      }

      this.mru = null;
      this.lru = null;
   }

   public synchronized void clearStaleReferences() {
      this.clearStale();
   }

   private void clearStale() {
      boolean goOn;
      for(goOn = false; this.myQueue.poll() != null; goOn = true) {
      }

      if (goOn) {
         DoubleNode<K, Object> next;
         for(DoubleNode<K, Object> current = this.mru; current != null; current = next) {
            next = current.getNext();
            if (current.getWeakKey().get() == null) {
               this.remove(current);
            }
         }

      }
   }

   public synchronized String toString() {
      StringBuffer sb = new StringBuffer("WeakHashLRUImpl({");
      boolean first = true;

      for(DoubleNode<K, Object> current = this.mru; current != null; current = current.getNext()) {
         K key = (K)current.getWeakKey().get();
         String keyString = key == null ? "null" : key.toString();
         if (first) {
            first = false;
            sb.append(keyString);
         } else {
            sb.append("," + keyString);
         }
      }

      sb.append("}," + System.identityHashCode(this) + ")");
      return sb.toString();
   }
}
