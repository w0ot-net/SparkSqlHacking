package org.apache.zookeeper.server.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BitMap {
   private final Map value2Bit = new HashMap();
   private final Map bit2Value = new HashMap();
   private final BitSet freedBitSet = new BitSet();
   private Integer nextBit = 0;
   private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

   @SuppressFBWarnings(
      value = {"DLS_DEAD_LOCAL_STORE"},
      justification = "SpotBugs false positive"
   )
   public Integer add(Object value) {
      Integer bit = this.getBit(value);
      if (bit != null) {
         return bit;
      } else {
         this.rwLock.writeLock().lock();

         Integer var3;
         try {
            bit = (Integer)this.value2Bit.get(value);
            if (bit == null) {
               bit = this.freedBitSet.nextSetBit(0);
               if (bit > -1) {
                  this.freedBitSet.clear(bit);
               } else {
                  var3 = this.nextBit;
                  Integer var4 = this.nextBit = this.nextBit + 1;
                  bit = var3;
               }

               this.value2Bit.put(value, bit);
               this.bit2Value.put(bit, value);
               var3 = bit;
               return var3;
            }

            var3 = bit;
         } finally {
            this.rwLock.writeLock().unlock();
         }

         return var3;
      }
   }

   public Object get(int bit) {
      this.rwLock.readLock().lock();

      Object var2;
      try {
         var2 = this.bit2Value.get(bit);
      } finally {
         this.rwLock.readLock().unlock();
      }

      return var2;
   }

   public Integer getBit(Object value) {
      this.rwLock.readLock().lock();

      Integer var2;
      try {
         var2 = (Integer)this.value2Bit.get(value);
      } finally {
         this.rwLock.readLock().unlock();
      }

      return var2;
   }

   public int remove(Object value) {
      this.rwLock.writeLock().lock();

      int var3;
      try {
         Integer bit = (Integer)this.value2Bit.get(value);
         if (bit != null) {
            this.value2Bit.remove(value);
            this.bit2Value.remove(bit);
            this.freedBitSet.set(bit);
            var3 = bit;
            return var3;
         }

         var3 = -1;
      } finally {
         this.rwLock.writeLock().unlock();
      }

      return var3;
   }

   public Object remove(int bit) {
      this.rwLock.writeLock().lock();

      Object var3;
      try {
         T value = (T)this.bit2Value.get(bit);
         if (value != null) {
            this.value2Bit.remove(value);
            this.bit2Value.remove(bit);
            this.freedBitSet.set(bit);
            var3 = value;
            return var3;
         }

         var3 = null;
      } finally {
         this.rwLock.writeLock().unlock();
      }

      return var3;
   }

   public int size() {
      this.rwLock.readLock().lock();

      int var1;
      try {
         var1 = this.value2Bit.size();
      } finally {
         this.rwLock.readLock().unlock();
      }

      return var1;
   }
}
