package org.apache.zookeeper.server.util;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BitHashSet implements Iterable {
   private final BitSet elementBits;
   private final Set cache;
   private final int cacheSize;
   private int elementCount;

   public BitHashSet() {
      this(Integer.getInteger("zookeeper.bitHashCacheSize", 10));
   }

   public BitHashSet(int cacheSize) {
      this.elementBits = new BitSet();
      this.cache = new HashSet();
      this.elementCount = 0;
      this.cacheSize = cacheSize;
   }

   public synchronized boolean add(Integer elementBit) {
      if (elementBit != null && !this.elementBits.get(elementBit)) {
         if (this.cache.size() < this.cacheSize) {
            this.cache.add(elementBit);
         }

         this.elementBits.set(elementBit);
         ++this.elementCount;
         return true;
      } else {
         return false;
      }
   }

   public synchronized int remove(Set bitSet, BitSet bits) {
      this.cache.removeAll(bitSet);
      this.elementBits.andNot(bits);
      int elementCountBefore = this.elementCount;
      this.elementCount = this.elementBits.cardinality();
      return elementCountBefore - this.elementCount;
   }

   public synchronized boolean remove(Integer elementBit) {
      if (elementBit != null && this.elementBits.get(elementBit)) {
         this.cache.remove(elementBit);
         this.elementBits.clear(elementBit);
         --this.elementCount;
         return true;
      } else {
         return false;
      }
   }

   public synchronized boolean contains(Integer elementBit) {
      return elementBit == null ? false : this.elementBits.get(elementBit);
   }

   public synchronized int size() {
      return this.elementCount;
   }

   public Iterator iterator() {
      final int currentSize = this.size();
      return this.cache.size() == currentSize ? this.cache.iterator() : new Iterator() {
         int returnedCount = 0;
         int bitIndex = 0;

         public boolean hasNext() {
            return this.returnedCount < currentSize;
         }

         public Integer next() {
            int bit = BitHashSet.this.elementBits.nextSetBit(this.bitIndex);
            this.bitIndex = bit + 1;
            ++this.returnedCount;
            return bit;
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   public synchronized int cachedSize() {
      return this.cache.size();
   }

   public synchronized boolean isEmpty() {
      return this.elementCount == 0;
   }
}
