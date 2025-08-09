package org.apache.thrift.transport;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.thrift.TConfiguration;

public final class TByteBuffer extends TEndpointTransport {
   private final ByteBuffer byteBuffer;

   public TByteBuffer(ByteBuffer byteBuffer) throws TTransportException {
      super(new TConfiguration());
      this.byteBuffer = byteBuffer;
      this.updateKnownMessageSize((long)byteBuffer.capacity());
   }

   public boolean isOpen() {
      return true;
   }

   public void open() {
   }

   public void close() {
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      this.checkReadBytesAvailable((long)len);
      int n = Math.min(this.byteBuffer.remaining(), len);
      if (n > 0) {
         try {
            this.byteBuffer.get(buf, off, n);
         } catch (BufferUnderflowException e) {
            throw new TTransportException("Unexpected end of input buffer", e);
         }
      }

      return n;
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      try {
         this.byteBuffer.put(buf, off, len);
      } catch (BufferOverflowException e) {
         throw new TTransportException("Not enough room in output buffer", e);
      }
   }

   public ByteBuffer getByteBuffer() {
      return this.byteBuffer;
   }

   public TByteBuffer clear() {
      this.byteBuffer.clear();
      return this;
   }

   public TByteBuffer flip() {
      this.byteBuffer.flip();
      return this;
   }

   public byte[] toByteArray() {
      byte[] data = new byte[this.byteBuffer.remaining()];
      this.byteBuffer.slice().get(data);
      return data;
   }
}
