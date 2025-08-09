package org.apache.thrift.transport;

import org.apache.thrift.TConfiguration;

public class AutoExpandingBufferReadTransport extends TEndpointTransport {
   private final AutoExpandingBuffer buf;
   private int pos = 0;
   private int limit = 0;

   public AutoExpandingBufferReadTransport(TConfiguration config, int initialCapacity) throws TTransportException {
      super(config);
      this.buf = new AutoExpandingBuffer(initialCapacity);
   }

   public void fill(TTransport inTrans, int length) throws TTransportException {
      this.buf.resizeIfNecessary(length);
      inTrans.readAll(this.buf.array(), 0, length);
      this.pos = 0;
      this.limit = length;
   }

   public void close() {
   }

   public boolean isOpen() {
      return true;
   }

   public void open() throws TTransportException {
   }

   public final int read(byte[] target, int off, int len) throws TTransportException {
      int amtToRead = Math.min(len, this.getBytesRemainingInBuffer());
      if (amtToRead > 0) {
         System.arraycopy(this.buf.array(), this.pos, target, off, amtToRead);
         this.consumeBuffer(amtToRead);
      }

      return amtToRead;
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      throw new UnsupportedOperationException();
   }

   public final void consumeBuffer(int len) {
      this.pos += len;
   }

   public final byte[] getBuffer() {
      return this.buf.array();
   }

   public final int getBufferPosition() {
      return this.pos;
   }

   public final int getBytesRemainingInBuffer() {
      return this.limit - this.pos;
   }
}
