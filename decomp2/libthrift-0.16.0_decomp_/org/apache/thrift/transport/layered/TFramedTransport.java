package org.apache.thrift.transport.layered;

import java.util.Objects;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class TFramedTransport extends TLayeredTransport {
   private final TByteArrayOutputStream writeBuffer_;
   private final TMemoryInputTransport readBuffer_;
   private static final byte[] sizeFiller_ = new byte[]{0, 0, 0, 0};
   private final byte[] i32buf;

   public TFramedTransport(TTransport transport, int maxLength) throws TTransportException {
      super(transport);
      this.writeBuffer_ = new TByteArrayOutputStream(1024);
      this.i32buf = new byte[4];
      TConfiguration _configuration = Objects.isNull(transport.getConfiguration()) ? new TConfiguration() : transport.getConfiguration();
      _configuration.setMaxFrameSize(maxLength);
      this.writeBuffer_.write(sizeFiller_, 0, 4);
      this.readBuffer_ = new TMemoryInputTransport(_configuration, new byte[0]);
   }

   public TFramedTransport(TTransport transport) throws TTransportException {
      this(transport, 16384000);
   }

   public void open() throws TTransportException {
      this.getInnerTransport().open();
   }

   public boolean isOpen() {
      return this.getInnerTransport().isOpen();
   }

   public void close() {
      this.getInnerTransport().close();
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      int got = this.readBuffer_.read(buf, off, len);
      if (got > 0) {
         return got;
      } else {
         this.readFrame();
         return this.readBuffer_.read(buf, off, len);
      }
   }

   public byte[] getBuffer() {
      return this.readBuffer_.getBuffer();
   }

   public int getBufferPosition() {
      return this.readBuffer_.getBufferPosition();
   }

   public int getBytesRemainingInBuffer() {
      return this.readBuffer_.getBytesRemainingInBuffer();
   }

   public void consumeBuffer(int len) {
      this.readBuffer_.consumeBuffer(len);
   }

   public void clear() {
      this.readBuffer_.clear();
   }

   private void readFrame() throws TTransportException {
      this.getInnerTransport().readAll(this.i32buf, 0, 4);
      int size = decodeFrameSize(this.i32buf);
      if (size < 0) {
         this.close();
         throw new TTransportException(5, "Read a negative frame size (" + size + ")!");
      } else if (size > this.getInnerTransport().getConfiguration().getMaxFrameSize()) {
         this.close();
         throw new TTransportException(5, "Frame size (" + size + ") larger than max length (" + this.getInnerTransport().getConfiguration().getMaxFrameSize() + ")!");
      } else {
         byte[] buff = new byte[size];
         this.getInnerTransport().readAll(buff, 0, size);
         this.readBuffer_.reset(buff);
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      this.writeBuffer_.write(buf, off, len);
   }

   public void flush() throws TTransportException {
      byte[] buf = this.writeBuffer_.get();
      int len = this.writeBuffer_.len() - 4;
      this.writeBuffer_.reset();
      this.writeBuffer_.write(sizeFiller_, 0, 4);
      encodeFrameSize(len, buf);
      this.getInnerTransport().write(buf, 0, len + 4);
      this.getInnerTransport().flush();
   }

   public static final void encodeFrameSize(int frameSize, byte[] buf) {
      buf[0] = (byte)(255 & frameSize >> 24);
      buf[1] = (byte)(255 & frameSize >> 16);
      buf[2] = (byte)(255 & frameSize >> 8);
      buf[3] = (byte)(255 & frameSize);
   }

   public static final int decodeFrameSize(byte[] buf) {
      return (buf[0] & 255) << 24 | (buf[1] & 255) << 16 | (buf[2] & 255) << 8 | buf[3] & 255;
   }

   public static class Factory extends TTransportFactory {
      private int maxLength_;

      public Factory() {
         this.maxLength_ = 16384000;
      }

      public Factory(int maxLength) {
         this.maxLength_ = maxLength;
      }

      public TTransport getTransport(TTransport base) throws TTransportException {
         return new TFramedTransport(base, this.maxLength_);
      }
   }
}
