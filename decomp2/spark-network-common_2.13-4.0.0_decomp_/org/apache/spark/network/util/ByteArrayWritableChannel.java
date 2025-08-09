package org.apache.spark.network.util;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class ByteArrayWritableChannel implements WritableByteChannel {
   private final byte[] data;
   private int offset;

   public ByteArrayWritableChannel(int size) {
      this.data = new byte[size];
   }

   public byte[] getData() {
      return this.data;
   }

   public int length() {
      return this.offset;
   }

   public void reset() {
      this.offset = 0;
   }

   public int write(ByteBuffer src) {
      int toTransfer = Math.min(src.remaining(), this.data.length - this.offset);
      src.get(this.data, this.offset, toTransfer);
      this.offset += toTransfer;
      return toTransfer;
   }

   public void close() {
   }

   public boolean isOpen() {
      return true;
   }
}
