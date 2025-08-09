package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class PoolChunkList implements PoolChunkListMetric {
   private static final Iterator EMPTY_METRICS = Collections.emptyList().iterator();
   private final PoolArena arena;
   private final PoolChunkList nextList;
   private final int minUsage;
   private final int maxUsage;
   private final int maxCapacity;
   private PoolChunk head;
   private final int freeMinThreshold;
   private final int freeMaxThreshold;
   private PoolChunkList prevList;

   PoolChunkList(PoolArena arena, PoolChunkList nextList, int minUsage, int maxUsage, int chunkSize) {
      assert minUsage <= maxUsage;

      this.arena = arena;
      this.nextList = nextList;
      this.minUsage = minUsage;
      this.maxUsage = maxUsage;
      this.maxCapacity = calculateMaxCapacity(minUsage, chunkSize);
      this.freeMinThreshold = maxUsage == 100 ? 0 : (int)((double)chunkSize * ((double)100.0F - (double)maxUsage + 0.99999999) / (double)100.0F);
      this.freeMaxThreshold = minUsage == 100 ? 0 : (int)((double)chunkSize * ((double)100.0F - (double)minUsage + 0.99999999) / (double)100.0F);
   }

   private static int calculateMaxCapacity(int minUsage, int chunkSize) {
      minUsage = minUsage0(minUsage);
      return minUsage == 100 ? 0 : (int)((long)chunkSize * (100L - (long)minUsage) / 100L);
   }

   void prevList(PoolChunkList prevList) {
      assert this.prevList == null;

      this.prevList = prevList;
   }

   boolean allocate(PooledByteBuf buf, int reqCapacity, int sizeIdx, PoolThreadCache threadCache) {
      int normCapacity = this.arena.sizeClass.sizeIdx2size(sizeIdx);
      if (normCapacity > this.maxCapacity) {
         return false;
      } else {
         for(PoolChunk<T> cur = this.head; cur != null; cur = cur.next) {
            if (cur.allocate(buf, reqCapacity, sizeIdx, threadCache)) {
               if (cur.freeBytes <= this.freeMinThreshold) {
                  this.remove(cur);
                  this.nextList.add(cur);
               }

               return true;
            }
         }

         return false;
      }
   }

   boolean free(PoolChunk chunk, long handle, int normCapacity, ByteBuffer nioBuffer) {
      chunk.free(handle, normCapacity, nioBuffer);
      if (chunk.freeBytes > this.freeMaxThreshold) {
         this.remove(chunk);
         return this.move0(chunk);
      } else {
         return true;
      }
   }

   private boolean move(PoolChunk chunk) {
      assert chunk.usage() < this.maxUsage;

      if (chunk.freeBytes > this.freeMaxThreshold) {
         return this.move0(chunk);
      } else {
         this.add0(chunk);
         return true;
      }
   }

   private boolean move0(PoolChunk chunk) {
      if (this.prevList == null) {
         assert chunk.usage() == 0;

         return false;
      } else {
         return this.prevList.move(chunk);
      }
   }

   void add(PoolChunk chunk) {
      if (chunk.freeBytes <= this.freeMinThreshold) {
         this.nextList.add(chunk);
      } else {
         this.add0(chunk);
      }
   }

   void add0(PoolChunk chunk) {
      chunk.parent = this;
      if (this.head == null) {
         this.head = chunk;
         chunk.prev = null;
         chunk.next = null;
      } else {
         chunk.prev = null;
         chunk.next = this.head;
         this.head.prev = chunk;
         this.head = chunk;
      }

   }

   private void remove(PoolChunk cur) {
      if (cur == this.head) {
         this.head = cur.next;
         if (this.head != null) {
            this.head.prev = null;
         }
      } else {
         PoolChunk<T> next = cur.next;
         cur.prev.next = next;
         if (next != null) {
            next.prev = cur.prev;
         }
      }

   }

   public int minUsage() {
      return minUsage0(this.minUsage);
   }

   public int maxUsage() {
      return Math.min(this.maxUsage, 100);
   }

   private static int minUsage0(int value) {
      return Math.max(1, value);
   }

   public Iterator iterator() {
      this.arena.lock();

      Iterator var1;
      try {
         if (this.head != null) {
            List<PoolChunkMetric> metrics = new ArrayList();
            PoolChunk<T> cur = this.head;

            do {
               metrics.add(cur);
               cur = cur.next;
            } while(cur != null);

            Iterator var7 = metrics.iterator();
            return var7;
         }

         var1 = EMPTY_METRICS;
      } finally {
         this.arena.unlock();
      }

      return var1;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      this.arena.lock();

      String var2;
      try {
         if (this.head != null) {
            PoolChunk<T> cur = this.head;

            while(true) {
               buf.append(cur);
               cur = cur.next;
               if (cur == null) {
                  return buf.toString();
               }

               buf.append(StringUtil.NEWLINE);
            }
         }

         var2 = "none";
      } finally {
         this.arena.unlock();
      }

      return var2;
   }

   void destroy(PoolArena arena) {
      for(PoolChunk<T> chunk = this.head; chunk != null; chunk = chunk.next) {
         arena.destroyChunk(chunk);
      }

      this.head = null;
   }
}
