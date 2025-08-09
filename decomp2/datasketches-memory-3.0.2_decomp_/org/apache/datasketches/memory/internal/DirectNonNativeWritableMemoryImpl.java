package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryRequestServer;

final class DirectNonNativeWritableMemoryImpl extends NonNativeWritableMemoryImpl {
   private final AllocateDirect direct;

   DirectNonNativeWritableMemoryImpl(AllocateDirect direct, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes, MemoryRequestServer memReqSvr) {
      this.direct = direct;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 8 | 0 | 32;
      this.cumOffsetBytes = cumOffsetBytes;
      this.memReqSvr = memReqSvr;
      if (this.owner != null && this.owner != Thread.currentThread()) {
         throw new IllegalStateException("Attempted access outside owning thread");
      } else {
         this.owner = Thread.currentThread();
      }
   }

   BaseWritableMemoryImpl toWritableRegion(long regionOffsetBytes, long capacityBytes, boolean readOnly, ByteOrder byteOrder) {
      long newOffsetBytes = this.offsetBytes + regionOffsetBytes;
      long newCumOffsetBytes = this.cumOffsetBytes + regionOffsetBytes;
      int typeIdOut = removeNnBuf(this.typeId) | 0 | 2 | (readOnly ? 1 : 0);
      if (Util.isNativeByteOrder(byteOrder)) {
         typeIdOut |= 0;
         return new DirectWritableMemoryImpl(this.direct, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new DirectNonNativeWritableMemoryImpl(this.direct, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableBufferImpl toWritableBuffer(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | (readOnly ? 1 : 0);
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
