package org.apache.spark.network.util;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;

public class ByteArrayReadableChannel implements ReadableByteChannel {
   private ByteBuf data;
   private boolean closed;

   public void feedData(ByteBuf buf) throws ClosedChannelException {
      if (this.closed) {
         throw new ClosedChannelException();
      } else {
         this.data = buf;
      }
   }

   public int read(ByteBuffer dst) throws IOException {
      if (this.closed) {
         throw new ClosedChannelException();
      } else {
         int totalRead;
         int bytesToRead;
         for(totalRead = 0; this.data.readableBytes() > 0 && dst.remaining() > 0; totalRead += bytesToRead) {
            bytesToRead = Math.min(this.data.readableBytes(), dst.remaining());
            dst.put(this.data.readSlice(bytesToRead).nioBuffer());
         }

         return totalRead;
      }
   }

   public void close() {
      this.closed = true;
   }

   public boolean isOpen() {
      return !this.closed;
   }
}
