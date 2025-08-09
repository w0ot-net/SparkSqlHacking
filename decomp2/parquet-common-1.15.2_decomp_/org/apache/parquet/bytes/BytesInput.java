package org.apache.parquet.bytes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BytesInput {
   private static final Logger LOG = LoggerFactory.getLogger(BytesInput.class);
   private static final EmptyBytesInput EMPTY_BYTES_INPUT = new EmptyBytesInput();

   public static BytesInput concat(BytesInput... inputs) {
      return new SequenceBytesIn(Arrays.asList(inputs));
   }

   public static BytesInput concat(List inputs) {
      return new SequenceBytesIn(inputs);
   }

   public static BytesInput from(InputStream in, int bytes) {
      return new StreamBytesInput(in, bytes);
   }

   /** @deprecated */
   @Deprecated
   public static BytesInput from(ByteBuffer buffer, int offset, int length) {
      ByteBuffer tmp = buffer.duplicate();
      tmp.position(offset);
      ByteBuffer slice = tmp.slice();
      slice.limit(length);
      return new ByteBufferBytesInput(slice);
   }

   public static BytesInput from(ByteBuffer... buffers) {
      return (BytesInput)(buffers.length == 1 ? new ByteBufferBytesInput(buffers[0]) : new BufferListBytesInput(Arrays.asList(buffers)));
   }

   public static BytesInput from(List buffers) {
      return (BytesInput)(buffers.size() == 1 ? new ByteBufferBytesInput((ByteBuffer)buffers.get(0)) : new BufferListBytesInput(buffers));
   }

   public static BytesInput from(byte[] in) {
      LOG.debug("BytesInput from array of {} bytes", in.length);
      return new ByteArrayBytesInput(in, 0, in.length);
   }

   public static BytesInput from(byte[] in, int offset, int length) {
      LOG.debug("BytesInput from array of {} bytes", length);
      return new ByteArrayBytesInput(in, offset, length);
   }

   public static BytesInput fromInt(int intValue) {
      return new IntBytesInput(intValue);
   }

   public static BytesInput fromUnsignedVarInt(int intValue) {
      return new UnsignedVarIntBytesInput(intValue);
   }

   public static BytesInput fromZigZagVarInt(int intValue) {
      int zigZag = intValue << 1 ^ intValue >> 31;
      return new UnsignedVarIntBytesInput(zigZag);
   }

   public static BytesInput fromUnsignedVarLong(long longValue) {
      return new UnsignedVarLongBytesInput(longValue);
   }

   public static BytesInput fromZigZagVarLong(long longValue) {
      long zigZag = longValue << 1 ^ longValue >> 63;
      return new UnsignedVarLongBytesInput(zigZag);
   }

   public static BytesInput from(CapacityByteArrayOutputStream arrayOut) {
      return new CapacityBAOSBytesInput(arrayOut);
   }

   public static BytesInput from(ByteArrayOutputStream baos) {
      return new BAOSBytesInput(baos);
   }

   public static BytesInput empty() {
      return EMPTY_BYTES_INPUT;
   }

   /** @deprecated */
   @Deprecated
   public static BytesInput copy(BytesInput bytesInput) throws IOException {
      return from(bytesInput.toByteArray());
   }

   public abstract void writeAllTo(OutputStream var1) throws IOException;

   abstract void writeInto(ByteBuffer var1);

   /** @deprecated */
   @Deprecated
   public byte[] toByteArray() throws IOException {
      long size = this.size();
      if (size > 2147483647L) {
         throw new IOException("Page size, " + size + ", is larger than allowed " + Integer.MAX_VALUE + ". Usually caused by a Parquet writer writing too big column chunks on encountering highly skewed dataset. Please set page.size.row.check.max to a lower value on the writer, default value is 10000. You can try setting it to " + 10000L / (size / 2147483647L) + " or lower.");
      } else {
         BAOS baos = new BAOS((int)this.size());
         this.writeAllTo(baos);
         LOG.debug("converted {} to byteArray of {} bytes", this.size(), baos.size());
         return baos.getBuf();
      }
   }

   /** @deprecated */
   @Deprecated
   public ByteBuffer toByteBuffer() throws IOException {
      return ByteBuffer.wrap(this.toByteArray());
   }

   public BytesInput copy(ByteBufferAllocator allocator, Consumer callback) {
      ByteBuffer buf = allocator.allocate(Math.toIntExact(this.size()));
      callback.accept(buf);
      this.writeInto(buf);
      buf.flip();
      return from(buf);
   }

   public BytesInput copy(ByteBufferReleaser releaser) {
      ByteBufferAllocator var10001 = releaser.allocator;
      releaser.getClass();
      return this.copy(var10001, releaser::releaseLater);
   }

   public ByteBuffer toByteBuffer(ByteBufferAllocator allocator, Consumer callback) {
      ByteBuffer buf = this.getInternalByteBuffer();
      if (buf == null || buf.isDirect() != allocator.isDirect()) {
         buf = allocator.allocate(Math.toIntExact(this.size()));
         callback.accept(buf);
         this.writeInto(buf);
         buf.flip();
      }

      return buf;
   }

   public ByteBuffer toByteBuffer(ByteBufferReleaser releaser) {
      ByteBufferAllocator var10001 = releaser.allocator;
      releaser.getClass();
      return this.toByteBuffer(var10001, releaser::releaseLater);
   }

   ByteBuffer getInternalByteBuffer() {
      return null;
   }

   public ByteBufferInputStream toInputStream() throws IOException {
      return ByteBufferInputStream.wrap(this.toByteBuffer());
   }

   public abstract long size();

   private static final class BAOS extends ByteArrayOutputStream {
      private BAOS(int size) {
         super(size);
      }

      public byte[] getBuf() {
         return this.buf;
      }
   }

   private static class StreamBytesInput extends BytesInput {
      private static final Logger LOG = LoggerFactory.getLogger(StreamBytesInput.class);
      private final InputStream in;
      private final int byteCount;

      private StreamBytesInput(InputStream in, int byteCount) {
         this.in = in;
         this.byteCount = byteCount;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         LOG.debug("write All {} bytes", this.byteCount);
         out.write(this.toByteArray());
      }

      void writeInto(ByteBuffer buffer) {
         try {
            ByteBuffer workBuf = buffer.duplicate();
            int pos = buffer.position();
            workBuf.limit(pos + this.byteCount);
            ReadableByteChannel channel = Channels.newChannel(this.in);

            int bytesRead;
            for(int remaining = this.byteCount; remaining > 0; remaining -= bytesRead) {
               bytesRead = channel.read(workBuf);
               if (bytesRead < 0) {
                  throw new EOFException("Reached the end of stream with " + remaining + " bytes left to read");
               }
            }

            buffer.position(pos + this.byteCount);
         } catch (IOException e) {
            throw new RuntimeException("Exception occurred during reading input stream", e);
         }
      }

      public byte[] toByteArray() throws IOException {
         LOG.debug("read all {} bytes", this.byteCount);
         byte[] buf = new byte[this.byteCount];
         (new DataInputStream(this.in)).readFully(buf);
         return buf;
      }

      public long size() {
         return (long)this.byteCount;
      }
   }

   private static class SequenceBytesIn extends BytesInput {
      private static final Logger LOG = LoggerFactory.getLogger(SequenceBytesIn.class);
      private final List inputs;
      private final long size;

      private SequenceBytesIn(List inputs) {
         this.inputs = inputs;
         long total = 0L;

         for(BytesInput input : inputs) {
            total += input.size();
         }

         this.size = total;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         for(BytesInput input : this.inputs) {
            LOG.debug("write {} bytes to out", input.size());
            if (input instanceof SequenceBytesIn) {
               LOG.debug("{");
            }

            input.writeAllTo(out);
            if (input instanceof SequenceBytesIn) {
               LOG.debug("}");
            }
         }

      }

      void writeInto(ByteBuffer buffer) {
         for(BytesInput input : this.inputs) {
            input.writeInto(buffer);
         }

      }

      ByteBuffer getInternalByteBuffer() {
         return this.inputs.size() == 1 ? ((BytesInput)this.inputs.get(0)).getInternalByteBuffer() : null;
      }

      public long size() {
         return this.size;
      }
   }

   private static class IntBytesInput extends BytesInput {
      private final int intValue;

      public IntBytesInput(int intValue) {
         this.intValue = intValue;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         BytesUtils.writeIntLittleEndian(out, this.intValue);
      }

      void writeInto(ByteBuffer buffer) {
         buffer.order(ByteOrder.LITTLE_ENDIAN).putInt(this.intValue);
      }

      public ByteBuffer toByteBuffer() {
         ByteBuffer buf = ByteBuffer.allocate(4);
         this.writeInto(buf);
         buf.flip();
         return buf;
      }

      public long size() {
         return 4L;
      }
   }

   private static class UnsignedVarIntBytesInput extends BytesInput {
      private final int intValue;

      public UnsignedVarIntBytesInput(int intValue) {
         this.intValue = intValue;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         BytesUtils.writeUnsignedVarInt(this.intValue, out);
      }

      void writeInto(ByteBuffer buffer) {
         try {
            BytesUtils.writeUnsignedVarInt(this.intValue, buffer);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public ByteBuffer toByteBuffer() {
         ByteBuffer ret = ByteBuffer.allocate((int)this.size());
         this.writeInto(ret);
         ret.flip();
         return ret;
      }

      public long size() {
         int s = (38 - Integer.numberOfLeadingZeros(this.intValue)) / 7;
         return s == 0 ? 1L : (long)s;
      }
   }

   private static class UnsignedVarLongBytesInput extends BytesInput {
      private final long longValue;

      public UnsignedVarLongBytesInput(long longValue) {
         this.longValue = longValue;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         BytesUtils.writeUnsignedVarLong(this.longValue, out);
      }

      void writeInto(ByteBuffer buffer) {
         BytesUtils.writeUnsignedVarLong(this.longValue, buffer);
      }

      public long size() {
         int s = (70 - Long.numberOfLeadingZeros(this.longValue)) / 7;
         return s == 0 ? 1L : (long)s;
      }
   }

   private static class EmptyBytesInput extends BytesInput {
      private EmptyBytesInput() {
      }

      public void writeAllTo(OutputStream out) throws IOException {
      }

      void writeInto(ByteBuffer buffer) {
      }

      public long size() {
         return 0L;
      }

      public ByteBuffer toByteBuffer() throws IOException {
         return ByteBuffer.allocate(0);
      }
   }

   private static class CapacityBAOSBytesInput extends BytesInput {
      private final CapacityByteArrayOutputStream arrayOut;

      private CapacityBAOSBytesInput(CapacityByteArrayOutputStream arrayOut) {
         this.arrayOut = arrayOut;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         this.arrayOut.writeTo(out);
      }

      void writeInto(ByteBuffer buffer) {
         this.arrayOut.writeInto(buffer);
      }

      ByteBuffer getInternalByteBuffer() {
         return this.arrayOut.getInternalByteBuffer();
      }

      public long size() {
         return this.arrayOut.size();
      }
   }

   private static class BAOSBytesInput extends BytesInput {
      private final ByteArrayOutputStream arrayOut;

      private BAOSBytesInput(ByteArrayOutputStream arrayOut) {
         this.arrayOut = arrayOut;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         this.arrayOut.writeTo(out);
      }

      void writeInto(ByteBuffer buffer) {
         buffer.put(this.arrayOut.toByteArray());
      }

      public long size() {
         return (long)this.arrayOut.size();
      }
   }

   private static class ByteArrayBytesInput extends BytesInput {
      private final byte[] in;
      private final int offset;
      private final int length;

      private ByteArrayBytesInput(byte[] in, int offset, int length) {
         this.in = in;
         this.offset = offset;
         this.length = length;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         out.write(this.in, this.offset, this.length);
      }

      void writeInto(ByteBuffer buffer) {
         buffer.put(this.in, this.offset, this.length);
      }

      public ByteBuffer toByteBuffer() throws IOException {
         return ByteBuffer.wrap(this.in, this.offset, this.length);
      }

      public long size() {
         return (long)this.length;
      }
   }

   private static class BufferListBytesInput extends BytesInput {
      private final List buffers;
      private final long length;

      public BufferListBytesInput(List buffers) {
         this.buffers = buffers;
         long totalLen = 0L;

         for(ByteBuffer buffer : buffers) {
            totalLen += (long)buffer.remaining();
         }

         this.length = totalLen;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         WritableByteChannel channel = Channels.newChannel(out);

         for(ByteBuffer buffer : this.buffers) {
            channel.write(buffer.duplicate());
         }

      }

      void writeInto(ByteBuffer target) {
         for(ByteBuffer buffer : this.buffers) {
            target.put(buffer.duplicate());
         }

      }

      public ByteBufferInputStream toInputStream() {
         return ByteBufferInputStream.wrap(this.buffers);
      }

      public long size() {
         return this.length;
      }
   }

   private static class ByteBufferBytesInput extends BytesInput {
      private final ByteBuffer buffer;

      private ByteBufferBytesInput(ByteBuffer buffer) {
         this.buffer = buffer;
      }

      public void writeAllTo(OutputStream out) throws IOException {
         Channels.newChannel(out).write(this.buffer.duplicate());
      }

      void writeInto(ByteBuffer target) {
         target.put(this.buffer.duplicate());
      }

      ByteBuffer getInternalByteBuffer() {
         return this.buffer.slice();
      }

      public ByteBufferInputStream toInputStream() {
         return ByteBufferInputStream.wrap(this.buffer);
      }

      public long size() {
         return (long)this.buffer.remaining();
      }

      public ByteBuffer toByteBuffer() throws IOException {
         return this.buffer.slice();
      }
   }
}
