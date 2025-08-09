package io.netty.resolver.dns;

import io.netty.util.internal.MathUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.Random;

final class DnsQueryIdSpace {
   private static final int MAX_ID = 65535;
   private static final int BUCKETS = 4;
   private static final int BUCKET_SIZE = 16384;
   private static final int BUCKET_DROP_THRESHOLD = 500;
   private final DnsQueryIdRange[] idBuckets = new DnsQueryIdRange[4];

   DnsQueryIdSpace() {
      assert this.idBuckets.length == MathUtil.findNextPositivePowerOfTwo(this.idBuckets.length);

      this.idBuckets[0] = newBucket(0);
   }

   private static DnsQueryIdRange newBucket(int idBucketsIdx) {
      return new DnsQueryIdRange(16384, idBucketsIdx * 16384);
   }

   int nextId() {
      int freeIdx = -1;

      for(int bucketIdx = 0; bucketIdx < this.idBuckets.length; ++bucketIdx) {
         DnsQueryIdRange bucket = this.idBuckets[bucketIdx];
         if (bucket != null) {
            int id = bucket.nextId();
            if (id != -1) {
               return id;
            }
         } else if (freeIdx == -1 || PlatformDependent.threadLocalRandom().nextBoolean()) {
            freeIdx = bucketIdx;
         }
      }

      if (freeIdx == -1) {
         return -1;
      } else {
         DnsQueryIdRange bucket = newBucket(freeIdx);
         this.idBuckets[freeIdx] = bucket;
         int id = bucket.nextId();

         assert id >= 0;

         return id;
      }
   }

   void pushId(int id) {
      int bucketIdx = id / 16384;
      if (bucketIdx >= this.idBuckets.length) {
         throw new IllegalArgumentException("id too large: " + id);
      } else {
         DnsQueryIdRange bucket = this.idBuckets[bucketIdx];

         assert bucket != null;

         bucket.pushId(id);
         if (bucket.usableIds() == bucket.maxUsableIds()) {
            for(int idx = 0; idx < this.idBuckets.length; ++idx) {
               if (idx != bucketIdx) {
                  DnsQueryIdRange otherBucket = this.idBuckets[idx];
                  if (otherBucket != null && otherBucket.usableIds() > 500) {
                     this.idBuckets[bucketIdx] = null;
                     return;
                  }
               }
            }
         }

      }
   }

   int usableIds() {
      int usableIds = 0;

      for(DnsQueryIdRange bucket : this.idBuckets) {
         usableIds += bucket == null ? 16384 : bucket.usableIds();
      }

      return usableIds;
   }

   int maxUsableIds() {
      return 16384 * this.idBuckets.length;
   }

   private static final class DnsQueryIdRange {
      private final short[] ids;
      private final int startId;
      private int count;

      DnsQueryIdRange(int bucketSize, int startId) {
         this.ids = new short[bucketSize];
         this.startId = startId;

         for(int v = startId; v < bucketSize + startId; ++v) {
            this.pushId(v);
         }

      }

      int nextId() {
         assert this.count >= 0;

         if (this.count == 0) {
            return -1;
         } else {
            short id = this.ids[this.count - 1];
            --this.count;
            return id & '\uffff';
         }
      }

      void pushId(int id) {
         if (this.count == this.ids.length) {
            throw new IllegalStateException("overflow");
         } else {
            assert id <= this.startId + this.ids.length && id >= this.startId;

            Random random = PlatformDependent.threadLocalRandom();
            int insertionPosition = random.nextInt(this.count + 1);
            short moveId = this.ids[insertionPosition];
            short insertId = (short)id;

            assert moveId != insertId || insertionPosition == this.count;

            this.ids[this.count] = moveId;
            this.ids[insertionPosition] = insertId;
            ++this.count;
         }
      }

      int usableIds() {
         return this.count;
      }

      int maxUsableIds() {
         return this.ids.length;
      }
   }
}
