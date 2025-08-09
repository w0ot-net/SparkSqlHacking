package org.apache.datasketches.memory.internal;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import sun.nio.ch.DirectBuffer;

final class AccessByteBuffer {
   static final ByteBuffer ZERO_READ_ONLY_DIRECT_BYTE_BUFFER = ByteBuffer.allocateDirect(0).asReadOnlyBuffer();
   private static final long NIO_BUFFER_ADDRESS_FIELD_OFFSET = UnsafeUtil.getFieldOffset(Buffer.class, "address");
   private static final long NIO_BUFFER_CAPACITY_FIELD_OFFSET = UnsafeUtil.getFieldOffset(Buffer.class, "capacity");
   private static final long BYTE_BUFFER_HB_FIELD_OFFSET = UnsafeUtil.getFieldOffset(ByteBuffer.class, "hb");
   private static final long BYTE_BUFFER_OFFSET_FIELD_OFFSET = UnsafeUtil.getFieldOffset(ByteBuffer.class, "offset");
   final long nativeBaseOffset;
   final long initialCumOffset;
   final long capacityBytes;
   final long offsetBytes;
   final Object unsafeObj;
   final boolean resourceReadOnly;
   final ByteOrder byteOrder;

   AccessByteBuffer(ByteBuffer byteBuf) {
      this.capacityBytes = (long)byteBuf.capacity();
      this.resourceReadOnly = byteBuf.isReadOnly();
      this.byteOrder = byteBuf.order();
      boolean direct = byteBuf.isDirect();
      if (direct) {
         this.nativeBaseOffset = ((DirectBuffer)byteBuf).address();
         this.unsafeObj = null;
         this.offsetBytes = 0L;
         this.initialCumOffset = this.nativeBaseOffset;
      } else {
         this.nativeBaseOffset = 0L;
         this.offsetBytes = (long)UnsafeUtil.unsafe.getInt(byteBuf, BYTE_BUFFER_OFFSET_FIELD_OFFSET);
         this.unsafeObj = UnsafeUtil.unsafe.getObject(byteBuf, BYTE_BUFFER_HB_FIELD_OFFSET);
         this.initialCumOffset = UnsafeUtil.getArrayBaseOffset(this.unsafeObj.getClass()) + this.offsetBytes;
      }

   }

   static ByteBuffer getDummyReadOnlyDirectByteBuffer(long address, int capacity) {
      ByteBuffer byteBuf = ZERO_READ_ONLY_DIRECT_BYTE_BUFFER.duplicate();
      UnsafeUtil.unsafe.putLong(byteBuf, NIO_BUFFER_ADDRESS_FIELD_OFFSET, address);
      UnsafeUtil.unsafe.putInt(byteBuf, NIO_BUFFER_CAPACITY_FIELD_OFFSET, capacity);
      byteBuf.limit(capacity);
      return byteBuf;
   }
}
