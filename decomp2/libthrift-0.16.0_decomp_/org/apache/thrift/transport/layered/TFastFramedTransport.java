package org.apache.thrift.transport.layered;

import java.util.Objects;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.AutoExpandingBufferReadTransport;
import org.apache.thrift.transport.AutoExpandingBufferWriteTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class TFastFramedTransport extends TLayeredTransport {
   public static final int DEFAULT_BUF_CAPACITY = 1024;
   private final AutoExpandingBufferWriteTransport writeBuffer;
   private AutoExpandingBufferReadTransport readBuffer;
   private final int initialBufferCapacity;
   private final byte[] i32buf;
   private final int maxLength;

   public TFastFramedTransport(TTransport underlying) throws TTransportException {
      this(underlying, 1024, 16384000);
   }

   public TFastFramedTransport(TTransport underlying, int initialBufferCapacity) throws TTransportException {
      this(underlying, initialBufferCapacity, 16384000);
   }

   public TFastFramedTransport(TTransport underlying, int initialBufferCapacity, int maxLength) throws TTransportException {
      super(underlying);
      this.i32buf = new byte[4];
      TConfiguration config = Objects.isNull(underlying.getConfiguration()) ? new TConfiguration() : underlying.getConfiguration();
      this.maxLength = maxLength;
      config.setMaxFrameSize(maxLength);
      this.initialBufferCapacity = initialBufferCapacity;
      this.readBuffer = new AutoExpandingBufferReadTransport(config, initialBufferCapacity);
      this.writeBuffer = new AutoExpandingBufferWriteTransport(config, initialBufferCapacity, 4);
   }

   public void close() {
      this.getInnerTransport().close();
   }

   public boolean isOpen() {
      return this.getInnerTransport().isOpen();
   }

   public void open() throws TTransportException {
      this.getInnerTransport().open();
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      int got = this.readBuffer.read(buf, off, len);
      if (got > 0) {
         return got;
      } else {
         this.readFrame();
         return this.readBuffer.read(buf, off, len);
      }
   }

   private void readFrame() throws TTransportException {
      this.getInnerTransport().readAll(this.i32buf, 0, 4);
      int size = TFramedTransport.decodeFrameSize(this.i32buf);
      if (size < 0) {
         this.close();
         throw new TTransportException(5, "Read a negative frame size (" + size + ")!");
      } else if (size > this.getInnerTransport().getConfiguration().getMaxFrameSize()) {
         this.close();
         throw new TTransportException(5, "Frame size (" + size + ") larger than max length (" + this.maxLength + ")!");
      } else {
         this.readBuffer.fill(this.getInnerTransport(), size);
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      this.writeBuffer.write(buf, off, len);
   }

   public void consumeBuffer(int len) {
      this.readBuffer.consumeBuffer(len);
   }

   public void clear() throws TTransportException {
      this.readBuffer = new AutoExpandingBufferReadTransport(this.getConfiguration(), this.initialBufferCapacity);
   }

   public void flush() throws TTransportException {
      int payloadLength = this.writeBuffer.getLength() - 4;
      byte[] data = this.writeBuffer.getBuf().array();
      TFramedTransport.encodeFrameSize(payloadLength, data);
      this.getInnerTransport().write(data, 0, payloadLength + 4);
      this.writeBuffer.reset();
      this.getInnerTransport().flush();
   }

   public byte[] getBuffer() {
      return this.readBuffer.getBuffer();
   }

   public int getBufferPosition() {
      return this.readBuffer.getBufferPosition();
   }

   public int getBytesRemainingInBuffer() {
      return this.readBuffer.getBytesRemainingInBuffer();
   }

   public static class Factory extends TTransportFactory {
      private final int initialCapacity;
      private final int maxLength;

      public Factory() {
         this(1024, 16384000);
      }

      public Factory(int initialCapacity) {
         this(initialCapacity, 16384000);
      }

      public Factory(int initialCapacity, int maxLength) {
         this.initialCapacity = initialCapacity;
         this.maxLength = maxLength;
      }

      public TTransport getTransport(TTransport trans) throws TTransportException {
         return new TFastFramedTransport(trans, this.initialCapacity, this.maxLength);
      }
   }
}
