package org.apache.datasketches.memory.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryRequestServer;

final class BBWritableMemoryImpl extends NativeWritableMemoryImpl {
   private final ByteBuffer byteBuf;
   private final Object unsafeObj;
   private final long nativeBaseOffset;

   BBWritableMemoryImpl(Object unsafeObj, long nativeBaseOffset, long offsetBytes, long capacityBytes, int typeId, long cumOffsetBytes, MemoryRequestServer memReqSvr, ByteBuffer byteBuf) {
      this.unsafeObj = unsafeObj;
      this.nativeBaseOffset = nativeBaseOffset;
      this.offsetBytes = offsetBytes;
      this.capacityBytes = capacityBytes;
      this.typeId = removeNnBuf(typeId) | 128 | 0 | 0;
      this.cumOffsetBytes = cumOffsetBytes;
      this.memReqSvr = memReqSvr;
      this.byteBuf = byteBuf;
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
         return new BBWritableMemoryImpl(this.unsafeObj, this.nativeBaseOffset, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr, this.byteBuf);
      } else {
         typeIdOut |= 32;
         return new BBNonNativeWritableMemoryImpl(this.unsafeObj, this.nativeBaseOffset, newOffsetBytes, capacityBytes, typeIdOut, newCumOffsetBytes, this.memReqSvr, this.byteBuf);
      }
   }

   BaseWritableBufferImpl toWritableBuffer(boolean readOnly, ByteOrder byteOrder) {
      int typeIdOut = removeNnBuf(this.typeId) | 64 | (readOnly ? 1 : 0);
      if (byteOrder == ByteOrder.nativeOrder()) {
         typeIdOut |= 0;
         return new BBWritableBufferImpl(this.unsafeObj, this.nativeBaseOffset, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr, this.byteBuf);
      } else {
         typeIdOut |= 32;
         return new BBNonNativeWritableBufferImpl(this.unsafeObj, this.nativeBaseOffset, this.offsetBytes, this.capacityBytes, typeIdOut, this.cumOffsetBytes, this.memReqSvr, this.byteBuf);
      }
   }

   public ByteBuffer getByteBuffer() {
      return this.byteBuf;
   }

   Object getUnsafeObject() {
      return this.unsafeObj;
   }
}
