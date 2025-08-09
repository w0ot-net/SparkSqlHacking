package org.apache.thrift.transport;

import java.io.Closeable;
import java.nio.ByteBuffer;
import org.apache.thrift.TConfiguration;

public abstract class TTransport implements Closeable {
   public abstract boolean isOpen();

   public boolean peek() {
      return this.isOpen();
   }

   public abstract void open() throws TTransportException;

   public abstract void close();

   public int read(ByteBuffer dst) throws TTransportException {
      byte[] arr = new byte[dst.remaining()];
      int n = this.read(arr, 0, arr.length);
      dst.put(arr, 0, n);
      return n;
   }

   public abstract int read(byte[] var1, int var2, int var3) throws TTransportException;

   public int readAll(byte[] buf, int off, int len) throws TTransportException {
      int got = 0;

      int ret;
      for(ret = 0; got < len; got += ret) {
         ret = this.read(buf, off + got, len - got);
         if (ret <= 0) {
            throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + len + " bytes, but only got " + got + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
         }
      }

      return got;
   }

   public void write(byte[] buf) throws TTransportException {
      this.write(buf, 0, buf.length);
   }

   public abstract void write(byte[] var1, int var2, int var3) throws TTransportException;

   public int write(ByteBuffer src) throws TTransportException {
      byte[] arr = new byte[src.remaining()];
      src.get(arr);
      this.write(arr, 0, arr.length);
      return arr.length;
   }

   public void flush() throws TTransportException {
   }

   public byte[] getBuffer() {
      return null;
   }

   public int getBufferPosition() {
      return 0;
   }

   public int getBytesRemainingInBuffer() {
      return -1;
   }

   public void consumeBuffer(int len) {
   }

   public abstract TConfiguration getConfiguration();

   public abstract void updateKnownMessageSize(long var1) throws TTransportException;

   public abstract void checkReadBytesAvailable(long var1) throws TTransportException;
}
