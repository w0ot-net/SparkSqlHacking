package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryRequestServer;

final class DirectWritableBufferImpl extends NativeWritableBufferImpl {
   private final AllocateDirect direct;

   DirectWritableBufferImpl(AllocateDirect direct, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes, MemoryRequestServer memReqSvr) {
      super(capacityBytes);
      this.direct = direct;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 8 | 64 | 0;
      this.cumOffsetBytes = cumOffsetBytes;
      this.memReqSvr = memReqSvr;
      if (this.owner != null && this.owner != Thread.currentThread()) {
         throw new IllegalStateException("Attempted access outside owning thread");
      } else {
         this.owner = Thread.currentThread();
      }
   }

   BaseWritableBufferImpl toWritableRegion(long regionOffsetBytes, long capacityBytes, boolean readOnly, ByteOrder byteOrder) {
      long newOffsetBytes = this.offsetBytes + regionOffsetBytes;
      long newCumOffsetBytes = this.cumOffsetBytes + regionOffsetBytes;
      int typeIdOut = removeNnBuf(this.typeId) | 64 | 2 | (readOnly ? 1 : 0);
      if (Util.isNativeByteOrder(byteOrder)) {
         typeIdOut |= 0;
         return new DirectWritableBufferImpl(this.direct, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new DirectNonNativeWritableBufferImpl(this.direct, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableMemoryImpl toWritableMemory(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 0 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new DirectWritableMemoryImpl(this.direct, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new DirectNonNativeWritableMemoryImpl(this.direct, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableBufferImpl toDuplicate(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | 4 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new DirectWritableBufferImpl(this.direct, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new DirectNonNativeWritableBufferImpl(this.direct, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      }
   }

   public void close() {
      this.checkValid();
      checkThread(this.owner);
      this.direct.close();
   }

   Object getUnsafeObject() {
      return null;
   }

   public boolean isAlive() {
      return this.direct.getValid().get();
   }
}
