package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryRequestServer;

final class HeapWritableMemoryImpl extends NativeWritableMemoryImpl {
   private final Object unsafeObj;

   HeapWritableMemoryImpl(Object unsafeObj, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes, MemoryRequestServer memReqSvr) {
      this.unsafeObj = unsafeObj;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 0 | 0 | 0;
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
         return new HeapWritableMemoryImpl(this.unsafeObj, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new HeapNonNativeWritableMemoryImpl(this.unsafeObj, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableBufferImpl toWritableBuffer(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new HeapWritableBufferImpl(this.unsafeObj, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new HeapNonNativeWritableBufferImpl(this.unsafeObj, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      }
   }

   Object getUnsafeObject() {
      return this.unsafeObj;
   }
}
