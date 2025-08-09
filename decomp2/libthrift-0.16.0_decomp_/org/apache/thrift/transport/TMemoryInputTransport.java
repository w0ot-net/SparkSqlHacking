package org.apache.thrift.transport;

import org.apache.thrift.TConfiguration;

public final class TMemoryInputTransport extends TEndpointTransport {
   private byte[] buf_;
   private int pos_;
   private int endPos_;

   public TMemoryInputTransport() throws TTransportException {
      this(new TConfiguration());
   }

   public TMemoryInputTransport(TConfiguration _configuration) throws TTransportException {
      this(_configuration, new byte[0]);
   }

   public TMemoryInputTransport(byte[] buf) throws TTransportException {
      this(new TConfiguration(), buf);
   }

   public TMemoryInputTransport(TConfiguration _configuration, byte[] buf) throws TTransportException {
      this(_configuration, buf, 0, buf.length);
   }

   public TMemoryInputTransport(byte[] buf, int offset, int length) throws TTransportException {
      this(new TConfiguration(), buf, offset, length);
   }

   public TMemoryInputTransport(TConfiguration _configuration, byte[] buf, int offset, int length) throws TTransportException {
      super(_configuration);
      this.reset(buf, offset, length);
      this.updateKnownMessageSize((long)length);
   }

   public void reset(byte[] buf) {
      this.reset(buf, 0, buf.length);
   }

   public void reset(byte[] buf, int offset, int length) {
      this.buf_ = buf;
      this.pos_ = offset;
      this.endPos_ = offset + length;

      try {
         this.resetConsumedMessageSize(-1L);
      } catch (TTransportException var5) {
      }

   }

   public void clear() {
      this.buf_ = null;

      try {
         this.resetConsumedMessageSize(-1L);
      } catch (TTransportException var2) {
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
      int bytesRemaining = this.getBytesRemainingInBuffer();
      int amtToRead = len > bytesRemaining ? bytesRemaining : len;
      if (amtToRead > 0) {
         System.arraycopy(this.buf_, this.pos_, buf, off, amtToRead);
         this.consumeBuffer(amtToRead);
         this.countConsumedMessageBytes((long)amtToRead);
      }

      return amtToRead;
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      throw new UnsupportedOperationException("No writing allowed!");
   }

   public byte[] getBuffer() {
      return this.buf_;
   }

   public int getBufferPosition() {
      return this.pos_;
   }

   public int getBytesRemainingInBuffer() {
      return this.endPos_ - this.pos_;
   }

   public void consumeBuffer(int len) {
      this.pos_ += len;
   }
}
