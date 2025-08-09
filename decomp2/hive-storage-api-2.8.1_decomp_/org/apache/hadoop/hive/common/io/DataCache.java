package org.apache.hadoop.hive.common.io;

import org.apache.hadoop.hive.common.io.encoded.MemoryBuffer;
import org.apache.hive.common.util.SuppressFBWarnings;

public interface DataCache {
   DiskRangeList getFileData(Object var1, DiskRangeList var2, long var3, DiskRangeListFactory var5, BooleanRef var6);

   /** @deprecated */
   @Deprecated
   long[] putFileData(Object var1, DiskRange[] var2, MemoryBuffer[] var3, long var4);

   void releaseBuffer(MemoryBuffer var1);

   void reuseBuffer(MemoryBuffer var1);

   Allocator getAllocator();

   Allocator.BufferObjectFactory getDataBufferFactory();

   long[] putFileData(Object var1, DiskRange[] var2, MemoryBuffer[] var3, long var4, CacheTag var6);

   @SuppressFBWarnings(
      value = {"UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD"},
      justification = "Used by interface consumers"
   )
   public static final class BooleanRef {
      public boolean value;
   }

   public interface DiskRangeListFactory {
      DiskRangeList createCacheChunk(MemoryBuffer var1, long var2, long var4);
   }
}
