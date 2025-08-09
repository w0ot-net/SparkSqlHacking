package org.apache.datasketches.memory.internal;

import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryRequestServer;

final class HeapWritableBufferImpl extends NativeWritableBufferImpl {
   private final Object unsafeObj;

   HeapWritableBufferImpl(Object unsafeObj, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes, MemoryRequestServer memReqSvr) {
      super(capacityBytes);
      this.unsafeObj = unsafeObj;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 0 | 64 | 0;
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
         return new HeapWritableBufferImpl(this.unsafeObj, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new HeapNonNativeWritableBufferImpl(this.unsafeObj, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableMemoryImpl toWritableMemory(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 0 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new HeapWritableMemoryImpl(this.unsafeObj, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      } else {
         typeIdOut |= 32;
         return new HeapNonNativeWritableMemoryImpl(this.unsafeObj, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr);
      }
   }

   BaseWritableBufferImpl toDuplicate(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | 4 | (readOnly ? 1 : 0);
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
