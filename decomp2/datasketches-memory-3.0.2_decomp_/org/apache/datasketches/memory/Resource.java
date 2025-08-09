package org.apache.datasketches.memory;

import java.nio.ByteOrder;

public interface Resource extends AutoCloseable {
   MemoryRequestServer defaultMemReqSvr = new DefaultMemoryRequestServer();

   MemoryRequestServer getMemoryRequestServer();

   boolean hasMemoryRequestServer();

   void setMemoryRequestServer(MemoryRequestServer var1);

   void close();

   boolean isCloseable();

   default boolean equalTo(Resource that) {
      return that != null && this.getCapacity() == that.getCapacity() ? this.equalTo(0L, that, 0L, that.getCapacity()) : false;
   }

   boolean equalTo(long var1, Resource var3, long var4, long var6);

   void force();

   long getCapacity();

   default long getCumulativeOffset() {
      return this.getCumulativeOffset(0L);
   }

   long getCumulativeOffset(long var1);

   long getRelativeOffset();

   ByteOrder getTypeByteOrder();

   boolean hasByteBuffer();

   boolean isByteOrderCompatible(ByteOrder var1);

   boolean isDirect();

   boolean isDuplicate();

   boolean isHeap();

   boolean isLoaded();

   boolean isMemory();

   boolean isMapped();

   boolean isNonNativeOrder();

   boolean isReadOnly();

   boolean isRegionView();

   boolean isSameResource(Resource var1);

   boolean isAlive();

   void load();

   String toString(String var1, long var2, int var4, boolean var5);

   String toString();

   long xxHash64(long var1, long var3, long var5);

   long xxHash64(long var1, long var3);
}
