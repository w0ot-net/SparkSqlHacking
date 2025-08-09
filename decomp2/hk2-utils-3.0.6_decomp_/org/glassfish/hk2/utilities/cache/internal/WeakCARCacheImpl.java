package org.glassfish.hk2.utilities.cache.internal;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;
import org.glassfish.hk2.utilities.cache.WeakCARCache;
import org.glassfish.hk2.utilities.general.GeneralUtilities;
import org.glassfish.hk2.utilities.general.WeakHashClock;
import org.glassfish.hk2.utilities.general.WeakHashLRU;

public class WeakCARCacheImpl implements WeakCARCache {
   private final Computable computable;
   private final int maxSize;
   private final WeakHashClock t1;
   private final WeakHashClock t2;
   private final WeakHashLRU b1;
   private final WeakHashLRU b2;
   private int p = 0;
   private final AtomicLong hits = new AtomicLong(0L);
   private final AtomicLong tries = new AtomicLong(0L);

   public WeakCARCacheImpl(Computable computable, int maxSize, boolean isWeak) {
      this.computable = computable;
      this.maxSize = maxSize;
      this.t1 = GeneralUtilities.getWeakHashClock(isWeak);
      this.t2 = GeneralUtilities.getWeakHashClock(isWeak);
      this.b1 = GeneralUtilities.getWeakHashLRU(isWeak);
      this.b2 = GeneralUtilities.getWeakHashLRU(isWeak);
   }

   private Object getValueFromT(Object key) {
      CarValue<V> cValue = (CarValue)this.t1.get(key);
      if (cValue != null) {
         cValue.referenceBit = true;
         return cValue.value;
      } else {
         cValue = (CarValue)this.t2.get(key);
         if (cValue != null) {
            cValue.referenceBit = true;
            return cValue.value;
         } else {
            return null;
         }
      }
   }

   public Object compute(Object key) {
      this.tries.getAndIncrement();
      V value = (V)this.getValueFromT(key);
      if (value != null) {
         this.hits.getAndIncrement();
         return value;
      } else {
         synchronized(this) {
            value = (V)this.getValueFromT(key);
            if (value != null) {
               this.hits.getAndIncrement();
               return value;
            } else {
               try {
                  value = (V)this.computable.compute(key);
               } catch (ComputationErrorException cee) {
                  return cee.getComputation();
               }

               int cacheSize = this.getValueSize();
               if (cacheSize >= this.maxSize) {
                  this.replace();
                  boolean inB1 = this.b1.contains(key);
                  boolean inB2 = this.b2.contains(key);
                  if (!inB1 && !inB2) {
                     if (this.t1.size() + this.b1.size() >= this.maxSize) {
                        this.b1.remove();
                     } else if (this.t1.size() + this.t2.size() + this.b1.size() + this.b2.size() >= 2 * this.maxSize) {
                        this.b2.remove();
                     }
                  }
               }

               boolean inB1 = this.b1.contains(key);
               boolean inB2 = this.b2.contains(key);
               if (!inB1 && !inB2) {
                  this.t1.put(key, new CarValue(value));
               } else if (inB1) {
                  int b1size = this.b1.size();
                  if (b1size == 0) {
                     b1size = 1;
                  }

                  int b2size = this.b2.size();
                  int ratio = b2size / b1size;
                  if (ratio <= 0) {
                     ratio = 1;
                  }

                  this.p += ratio;
                  if (this.p > this.maxSize) {
                     this.p = this.maxSize;
                  }

                  this.b1.remove(key);
                  this.t2.put(key, new CarValue(value));
               } else {
                  int b2size = this.b2.size();
                  if (b2size == 0) {
                     b2size = 1;
                  }

                  int b1size = this.b1.size();
                  int ratio = b1size / b2size;
                  if (ratio <= 0) {
                     ratio = 1;
                  }

                  this.p -= ratio;
                  if (this.p < 0) {
                     this.p = 0;
                  }

                  this.b2.remove(key);
                  this.t2.put(key, new CarValue(value));
               }

               return value;
            }
         }
      }
   }

   private void replace() {
      boolean found = false;

      while(!found) {
         int trySize = this.p;
         if (trySize < 1) {
            trySize = 1;
         }

         if (this.t1.size() >= trySize) {
            Map.Entry<K, CarValue<V>> entry = this.t1.next();
            if (!((CarValue)entry.getValue()).referenceBit) {
               found = true;
               this.t1.remove(entry.getKey());
               this.b1.add(entry.getKey());
            } else {
               CarValue<V> entryValue = (CarValue)entry.getValue();
               entryValue.referenceBit = false;
               this.t1.remove(entry.getKey());
               this.t2.put(entry.getKey(), entryValue);
            }
         } else {
            Map.Entry<K, CarValue<V>> entry = this.t2.next();
            if (!((CarValue)entry.getValue()).referenceBit) {
               found = true;
               this.t2.remove(entry.getKey());
               this.b2.add(entry.getKey());
            } else {
               CarValue<V> entryValue = (CarValue)entry.getValue();
               entryValue.referenceBit = false;
            }
         }
      }

   }

   public synchronized int getKeySize() {
      return this.t1.size() + this.t2.size() + this.b1.size() + this.b2.size();
   }

   public synchronized int getValueSize() {
      return this.t1.size() + this.t2.size();
   }

   public synchronized void clear() {
      this.t1.clear();
      this.t2.clear();
      this.b1.clear();
      this.b2.clear();
      this.p = 0;
      this.tries.set(0L);
      this.hits.set(0L);
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public Computable getComputable() {
      return this.computable;
   }

   public synchronized boolean remove(Object key) {
      if (this.t1.remove(key) == null) {
         if (this.t2.remove(key) == null) {
            return !this.b1.remove(key) ? this.b2.remove(key) : true;
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public synchronized void releaseMatching(CacheKeyFilter filter) {
      if (filter != null) {
         this.b2.releaseMatching(filter);
         this.b1.releaseMatching(filter);
         this.t1.releaseMatching(filter);
         this.t2.releaseMatching(filter);
      }
   }

   public synchronized void clearStaleReferences() {
      this.t1.clearStaleReferences();
      this.t2.clearStaleReferences();
      this.b1.clearStaleReferences();
      this.b2.clearStaleReferences();
   }

   public int getT1Size() {
      return this.t1.size();
   }

   public int getT2Size() {
      return this.t2.size();
   }

   public int getB1Size() {
      return this.b1.size();
   }

   public int getB2Size() {
      return this.b2.size();
   }

   public int getP() {
      return this.p;
   }

   public String dumpAllLists() {
      int var10002 = this.p;
      StringBuffer sb = new StringBuffer("p=" + var10002 + "\nT1: " + this.t1.toString() + "\n");
      sb.append("T2: " + this.t2.toString() + "\n");
      sb.append("B1: " + this.b1.toString() + "\n");
      sb.append("B2: " + this.b2.toString() + "\n");
      return sb.toString();
   }

   public double getHitRate() {
      long localHits = this.hits.get();
      long localTries = this.tries.get();
      if (localTries == 0L) {
         localTries = 1L;
      }

      return (double)localHits / (double)localTries * (double)100.0F;
   }

   public String toString() {
      int var10000 = this.t1.size();
      return "WeakCARCacheImpl(t1size=" + var10000 + ",t2Size=" + this.t2.size() + ",b1Size=" + this.b1.size() + ",b2Size=" + this.b2.size() + ",p=" + this.p + ",hitRate=" + this.getHitRate() + "%," + System.identityHashCode(this) + ")";
   }

   private static class CarValue {
      private final Object value;
      private volatile boolean referenceBit = false;

      private CarValue(Object value) {
         this.value = value;
      }
   }
}
