package org.apache.arrow.vector.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class ByteArrayReadableSeekableByteChannel implements SeekableByteChannel {
   private byte[] byteArray;
   private int position = 0;

   public ByteArrayReadableSeekableByteChannel(byte[] byteArray) {
      if (byteArray == null) {
         throw new NullPointerException();
      } else {
         this.byteArray = byteArray;
      }
   }

   public boolean isOpen() {
      return this.byteArray != null;
   }

   public void close() throws IOException {
      this.byteArray = null;
   }

   public int read(ByteBuffer dst) throws IOException {
      int remainingInBuf = this.byteArray.length - this.position;
      int length = Math.min(dst.remaining(), remainingInBuf);
      dst.put(this.byteArray, this.position, length);
      this.position += length;
      return length;
   }

   public long position() throws IOException {
      return (long)this.position;
   }

   public SeekableByteChannel position(long newPosition) throws IOException {
      this.position = (int)newPosition;
      return this;
   }

   public long size() throws IOException {
      return (long)this.byteArray.length;
   }

   public int write(ByteBuffer src) throws IOException {
      throw new UnsupportedOperationException("Read only");
   }

   public SeekableByteChannel truncate(long size) throws IOException {
      throw new UnsupportedOperationException("Read only");
   }
}
