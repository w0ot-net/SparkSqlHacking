package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;

final class MapNonNativeWritableBufferImpl extends NonNativeWritableBufferImpl {
   private final AllocateDirectWritableMap dirWMap;

   MapNonNativeWritableBufferImpl(AllocateDirectWritableMap dirWMap, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes) {
      super(capacityBytes);
      this.dirWMap = dirWMap;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 16 | 64 | 32;
      this.cumOffsetBytes = cumOffsetBytes;
      if (this.owner != null && this.owner != Thread.currentThread()) {
         throw new IllegalStateException("Attempted access outside owning thread");
      } else {
         this.owner = Thread.currentThread();
      }
   }

   BaseWritableBufferImpl toWritableRegion(long regionOffsetBytes, long capacityBytes, boolean readOnly, ByteOrder byteOrder) {
      long newOffsetBytes = this.offsetBytes + regionOffsetBytes;
      long newCumOffsetBytes = this.cumOffsetBytes + regionOffsetBytes;
      int typeIdOut = removeNnBuf(this.typeId) | 16 | 2 | (readOnly ? 1 : 0);
      if (Util.isNativeByteOrder(byteOrder)) {
         typeIdOut |= 0;
         return new MapWritableBufferImpl(this.dirWMap, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes);
      } else {
         typeIdOut |= 32;
         return new MapNonNativeWritableBufferImpl(this.dirWMap, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes);
      }
   }

   BaseWritableMemoryImpl toWritableMemory(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 0 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new MapWritableMemoryImpl(this.dirWMap, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes);
      } else {
         typeIdOut |= 32;
         return new MapNonNativeWritableMemoryImpl(this.dirWMap, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes);
      }
   }

   BaseWritableBufferImpl toDuplicate(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | 4 | (readOnly ? 1 : 0);
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
