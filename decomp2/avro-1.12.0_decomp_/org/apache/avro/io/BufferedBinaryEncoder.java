package org.apache.avro.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Objects;
import org.apache.avro.AvroRuntimeException;

public class BufferedBinaryEncoder extends BinaryEncoder {
   private byte[] buf;
   private int pos;
   private ByteSink sink;
   private int bulkLimit;

   BufferedBinaryEncoder(OutputStream out, int bufferSize) {
      this.configure(out, bufferSize);
   }

   BufferedBinaryEncoder configure(OutputStream out, int bufferSize) {
      Objects.requireNonNull(out, "OutputStream cannot be null");
      if (null != this.sink && this.pos > 0) {
         try {
            this.flushBuffer();
         } catch (IOException e) {
            throw new AvroRuntimeException("Failure flushing old output", e);
         }
      }

      this.sink = new OutputStreamSink(out);
      this.pos = 0;
      if (null == this.buf || this.buf.length != bufferSize) {
         this.buf = new byte[bufferSize];
      }

      this.bulkLimit = this.buf.length >>> 1;
      if (this.bulkLimit > 512) {
         this.bulkLimit = 512;
      }

      return this;
   }

   public void flush() throws IOException {
      this.flushBuffer();
      this.sink.innerFlush();
   }

   private void flushBuffer() throws IOException {
      if (this.pos > 0) {
         try {
            this.sink.innerWrite(this.buf, 0, this.pos);
         } finally {
            this.pos = 0;
         }
      }

   }

   private void ensureBounds(int num) throws IOException {
      int remaining = this.buf.length - this.pos;
      if (remaining < num) {
         this.flushBuffer();
      }

   }

   public void writeBoolean(boolean b) throws IOException {
      if (this.buf.length == this.pos) {
         this.flushBuffer();
      }

      this.pos += BinaryData.encodeBoolean(b, this.buf, this.pos);
   }

   public void writeInt(int n) throws IOException {
      this.ensureBounds(5);
      this.pos += BinaryData.encodeInt(n, this.buf, this.pos);
   }

   public void writeLong(long n) throws IOException {
      this.ensureBounds(10);
      this.pos += BinaryData.encodeLong(n, this.buf, this.pos);
   }

   public void writeFloat(float f) throws IOException {
      this.ensureBounds(4);
      this.pos += BinaryData.encodeFloat(f, this.buf, this.pos);
   }

   public void writeDouble(double d) throws IOException {
      this.ensureBounds(8);
      this.pos += BinaryData.encodeDouble(d, this.buf, this.pos);
   }

   public void writeFixed(byte[] bytes, int start, int len) throws IOException {
      if (len > this.bulkLimit) {
         this.flushBuffer();
         this.sink.innerWrite(bytes, start, len);
      } else {
         this.ensureBounds(len);
         System.arraycopy(bytes, start, this.buf, this.pos, len);
         this.pos += len;
      }
   }

   public void writeFixed(ByteBuffer bytes) throws IOException {
      ByteBuffer readOnlyBytes = bytes.asReadOnlyBuffer();
      if (!bytes.hasArray() && bytes.remaining() > this.bulkLimit) {
         this.flushBuffer();
         this.sink.innerWrite(readOnlyBytes);
      } else {
         super.writeFixed(readOnlyBytes);
      }

   }

   protected void writeZero() throws IOException {
      this.writeByte(0);
   }

   private void writeByte(int b) throws IOException {
      if (this.pos == this.buf.length) {
         this.flushBuffer();
      }

      this.buf[this.pos++] = (byte)(b & 255);
   }

   public int bytesBuffered() {
      return this.pos;
   }

   private abstract static class ByteSink {
      protected ByteSink() {
      }

      protected abstract void innerWrite(byte[] bytes, int off, int len) throws IOException;

      protected abstract void innerWrite(ByteBuffer buff) throws IOException;

      protected abstract void innerFlush() throws IOException;
   }

   static class OutputStreamSink extends ByteSink {
      private final OutputStream out;
      private final WritableByteChannel channel;

      private OutputStreamSink(OutputStream out) {
         this.out = out;
         this.channel = Channels.newChannel(out);
      }

      protected void innerWrite(byte[] bytes, int off, int len) throws IOException {
         this.out.write(bytes, off, len);
      }

      protected void innerFlush() throws IOException {
         this.out.flush();
      }

      protected void innerWrite(ByteBuffer buff) throws IOException {
         this.channel.write(buff);
      }
   }
}
