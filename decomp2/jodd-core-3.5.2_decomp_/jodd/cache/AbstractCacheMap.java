package jodd.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractCacheMap implements Cache {
   protected Map cacheMap;
   private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
   private final Lock readLock;
   private final Lock writeLock;
   protected int cacheSize;
   protected long timeout;
   protected boolean existCustomTimeout;
   protected int hitCount;
   protected int missCount;

   public AbstractCacheMap() {
      this.readLock = this.cacheLock.readLock();
      this.writeLock = this.cacheLock.writeLock();
   }

   public int getCacheSize() {
      return this.cacheSize;
   }

   public long getCacheTimeout() {
      return this.timeout;
   }

   protected boolean isPruneExpiredActive() {
      return this.timeout != 0L || this.existCustomTimeout;
   }

   public void put(Object key, Object object) {
      this.put(key, object, this.timeout);
   }

   public void put(Object key, Object object, long timeout) {
      this.writeLock.lock();

      try {
         AbstractCacheMap<K, V>.CacheObject<K, V> co = new CacheObject(key, object, timeout);
         if (timeout != 0L) {
            this.existCustomTimeout = true;
         }

         if (this.isFull()) {
            this.pruneCache();
         }

         this.cacheMap.put(key, co);
      } finally {
         this.writeLock.unlock();
      }

   }

   public int getHitCount() {
      return this.hitCount;
   }

   public int getMissCount() {
      return this.missCount;
   }

   public Object get(Object key) {
      this.readLock.lock();

      Object var3;
      try {
         AbstractCacheMap<K, V>.CacheObject<K, V> co = (CacheObject)this.cacheMap.get(key);
         if (co != null) {
            if (co.isExpired()) {
               this.cacheMap.remove(key);
               ++this.missCount;
               var3 = null;
               return var3;
            }

            ++this.hitCount;
            var3 = co.getObject();
            return var3;
         }

         ++this.missCount;
         var3 = null;
      } finally {
         this.readLock.unlock();
      }

      return var3;
   }

   public Iterator iterator() {
      return new CacheValuesIterator(this);
   }

   protected abstract int pruneCache();

   public final int prune() {
      this.writeLock.lock();

      int var1;
      try {
         var1 = this.pruneCache();
      } finally {
         this.writeLock.unlock();
      }

      return var1;
   }

   public boolean isFull() {
      if (this.cacheSize == 0) {
         return false;
      } else {
         return this.cacheMap.size() >= this.cacheSize;
      }
   }

   public void remove(Object key) {
      this.writeLock.lock();

      try {
         this.cacheMap.remove(key);
      } finally {
         this.writeLock.unlock();
      }

   }

   public void clear() {
      this.writeLock.lock();

      try {
         this.cacheMap.clear();
      } finally {
         this.writeLock.unlock();
      }

   }

   public int size() {
      return this.cacheMap.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   class CacheObject {
      final Object key;
      final Object cachedObject;
      long lastAccess;
      long accessCount;
      long ttl;

      CacheObject(Object key, Object object, long ttl) {
         this.key = key;
         this.cachedObject = object;
         this.ttl = ttl;
         this.lastAccess = System.currentTimeMillis();
      }

      boolean isExpired() {
         if (this.ttl == 0L) {
            return false;
         } else {
            return this.lastAccess + this.ttl < System.currentTimeMillis();
         }
      }

      Object getObject() {
         this.lastAccess = System.currentTimeMillis();
         ++this.accessCount;
         return this.cachedObject;
      }
   }
}
