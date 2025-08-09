package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;

final class MapNonNativeWritableMemoryImpl extends NonNativeWritableMemoryImpl {
   private final AllocateDirectWritableMap dirWMap;

   MapNonNativeWritableMemoryImpl(AllocateDirectWritableMap dirWMap, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes) {
      this.dirWMap = dirWMap;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 16 | 0 | 32;
      this.cumOffsetBytes = cumOffsetBytes;
      if (this.owner != null && this.owner != Thread.currentThread()) {
         throw new IllegalStateException("Attempted access outside owning thread");
      } else {
         this.owner = Thread.currentThread();
      }
   }

   BaseWritableMemoryImpl toWritableRegion(long regionOffsetBytes, long capacityBytes, boolean readOnly, ByteOrder byteOrder) {
      long newOffsetBytes = this.offsetBytes + regionOffsetBytes;
      long newCumOffsetBytes = this.cumOffsetBytes + regionOffsetBytes;
      int typeIdOut = removeNnBuf(this.typeId) | 16 | 2 | (readOnly ? 1 : 0);
      if (Util.isNativeByteOrder(byteOrder)) {
         typeIdOut |= 0;
         return new MapWritableMemoryImpl(this.dirWMap, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes);
      } else {
         typeIdOut |= 32;
         return new MapNonNativeWritableMemoryImpl(this.dirWMap, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes);
      }
   }

   BaseWritableBufferImpl toWritableBuffer(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new MapWritableBufferImpl(this.dirWMap, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes);
      } else {
         typeIdOut |= 32;
         return new MapNonNativeWritableBufferImpl(this.dirWMap, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes);
      }
   }

   public void close() {
      this.checkValid();
      checkThread(this.owner);
      this.dirWMap.close();
   }

   public void force() {
      this.checkValid();
      checkThread(this.owner);
      this.checkNotReadOnly();
      this.dirWMap.force();
   }

   Object getUnsafeObject() {
      return null;
   }

   public boolean isLoaded() {
      this.checkValid();
      checkThread(this.owner);
      return this.dirWMap.isLoaded();
   }

   public boolean isAlive() {
      return this.dirWMap.getValid().get();
   }

   public void load() {
      this.checkValid();
      checkThread(this.owner);
      this.dirWMap.load();
   }
}
