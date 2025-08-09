package org.apache.thrift.transport;

import org.apache.thrift.TConfiguration;

public final class AutoExpandingBufferWriteTransport extends TEndpointTransport {
   private final AutoExpandingBuffer buf;
   private int pos;
   private int res;

   public AutoExpandingBufferWriteTransport(TConfiguration config, int initialCapacity, int frontReserve) throws TTransportException {
      super(config);
      if (initialCapacity < 1) {
         throw new IllegalArgumentException("initialCapacity");
      } else if (frontReserve >= 0 && initialCapacity >= frontReserve) {
         this.buf = new AutoExpandingBuffer(initialCapacity);
         this.pos = frontReserve;
         this.res = frontReserve;
      } else {
         throw new IllegalArgumentException("frontReserve");
      }
   }

   public void close() {
   }

   public boolean isOpen() {
      return true;
   }

   public void open() throws TTransportException {
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      throw new UnsupportedOperationException();
   }

   public void write(byte[] toWrite, int off, int len) throws TTransportException {
      this.buf.resizeIfNecessary(this.pos + len);
      System.arraycopy(toWrite, off, this.buf.array(), this.pos, len);
      this.pos += len;
   }

   public AutoExpandingBuffer getBuf() {
      return this.buf;
   }

   public int getLength() {
      return this.pos;
   }

   public void reset() {
      this.pos = this.res;
   }
}
