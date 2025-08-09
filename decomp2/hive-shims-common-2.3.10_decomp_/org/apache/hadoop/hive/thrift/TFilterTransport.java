package org.apache.hadoop.hive.thrift;

import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TFilterTransport extends TTransport {
   protected final TTransport wrapped;

   public TFilterTransport(TTransport wrapped) {
      this.wrapped = wrapped;
   }

   public void open() throws TTransportException {
      this.wrapped.open();
   }

   public boolean isOpen() {
      return this.wrapped.isOpen();
   }

   public boolean peek() {
      return this.wrapped.peek();
   }

   public void close() {
      this.wrapped.close();
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      return this.wrapped.read(buf, off, len);
   }

   public int readAll(byte[] buf, int off, int len) throws TTransportException {
      return this.wrapped.readAll(buf, off, len);
   }

   public void write(byte[] buf) throws TTransportException {
      this.wrapped.write(buf);
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      this.wrapped.write(buf, off, len);
   }

   public void flush() throws TTransportException {
      this.wrapped.flush();
   }

   public byte[] getBuffer() {
      return this.wrapped.getBuffer();
   }

   public int getBufferPosition() {
      return this.wrapped.getBufferPosition();
   }

   public int getBytesRemainingInBuffer() {
      return this.wrapped.getBytesRemainingInBuffer();
   }

   public void consumeBuffer(int len) {
      this.wrapped.consumeBuffer(len);
   }

   public TConfiguration getConfiguration() {
      return null;
   }

   public void updateKnownMessageSize(long l) throws TTransportException {
   }

   public void checkReadBytesAvailable(long l) throws TTransportException {
   }
}
