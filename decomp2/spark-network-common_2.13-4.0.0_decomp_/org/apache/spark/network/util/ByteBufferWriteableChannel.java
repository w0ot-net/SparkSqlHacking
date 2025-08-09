package org.apache.spark.network.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;

public class ByteBufferWriteableChannel implements WritableByteChannel {
   private final ByteBuffer destination;
   private boolean open;

   public ByteBufferWriteableChannel(ByteBuffer destination) {
      this.destination = destination;
      this.open = true;
   }

   public int write(ByteBuffer src) throws IOException {
      if (!this.isOpen()) {
         throw new ClosedChannelException();
      } else {
         int bytesToWrite = Math.min(src.remaining(), this.destination.remaining());
         if (bytesToWrite == 0) {
            return 0;
         } else {
            ByteBuffer temp = src.slice().limit(bytesToWrite);
            this.destination.put(temp);
            src.position(src.position() + bytesToWrite);
            return bytesToWrite;
         }
      }
   }

   public boolean isOpen() {
      return this.open;
   }

   public void close() {
      this.open = false;
   }
}
