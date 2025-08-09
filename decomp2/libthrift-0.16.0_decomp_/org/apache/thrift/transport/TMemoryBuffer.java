package org.apache.thrift.transport;

import java.nio.charset.Charset;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TConfiguration;

public class TMemoryBuffer extends TEndpointTransport {
   private TByteArrayOutputStream arr_;
   private int pos_;

   public TMemoryBuffer(int size) throws TTransportException {
      super(new TConfiguration());
      this.arr_ = new TByteArrayOutputStream(size);
      this.updateKnownMessageSize((long)size);
   }

   public TMemoryBuffer(TConfiguration config, int size) throws TTransportException {
      super(config);
      this.arr_ = new TByteArrayOutputStream(size);
      this.updateKnownMessageSize((long)size);
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
      byte[] src = this.arr_.get();
      int amtToRead = len > this.arr_.len() - this.pos_ ? this.arr_.len() - this.pos_ : len;
      if (amtToRead > 0) {
         System.arraycopy(src, this.pos_, buf, off, amtToRead);
         this.pos_ += amtToRead;
      }

      return amtToRead;
   }

   public void write(byte[] buf, int off, int len) {
      this.arr_.write(buf, off, len);
   }

   public String toString(Charset charset) {
      return this.arr_.toString(charset);
   }

   public String inspect() {
      StringBuilder buf = new StringBuilder();
      byte[] bytes = this.arr_.toByteArray();

      for(int i = 0; i < bytes.length; ++i) {
         buf.append(this.pos_ == i ? "==>" : "").append(Integer.toHexString(bytes[i] & 255)).append(" ");
      }

      return buf.toString();
   }

   public int length() {
      return this.arr_.size();
   }

   public byte[] getArray() {
      return this.arr_.get();
   }
}
