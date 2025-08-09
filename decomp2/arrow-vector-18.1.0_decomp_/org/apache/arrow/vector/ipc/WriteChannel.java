package org.apache.arrow.vector.ipc;

import com.google.flatbuffers.FlatBufferBuilder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.ipc.message.FBSerializable;
import org.apache.arrow.vector.ipc.message.MessageSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteChannel implements AutoCloseable {
   private static final Logger LOGGER = LoggerFactory.getLogger(WriteChannel.class);
   private static final byte[] ZERO_BYTES = new byte[8];
   private final byte[] intBuf = new byte[4];
   private long currentPosition = 0L;
   private final WritableByteChannel out;

   public WriteChannel(WritableByteChannel out) {
      this.out = out;
   }

   public void close() throws IOException {
      this.out.close();
   }

   public long getCurrentPosition() {
      return this.currentPosition;
   }

   public long write(byte[] buffer) throws IOException {
      return this.write(ByteBuffer.wrap(buffer));
   }

   long write(byte[] buffer, int offset, int length) throws IOException {
      return this.write(ByteBuffer.wrap(buffer, offset, length));
   }

   public long writeZeros(long zeroCount) throws IOException {
      long bytesWritten = 0L;

      for(long wholeWordsEnd = zeroCount - 8L; bytesWritten <= wholeWordsEnd; bytesWritten += this.write(ZERO_BYTES)) {
      }

      if (bytesWritten < zeroCount) {
         bytesWritten += this.write(ZERO_BYTES, 0, (int)(zeroCount - bytesWritten));
      }

      return bytesWritten;
   }

   public long align() throws IOException {
      int trailingByteSize = (int)(this.currentPosition % 8L);
      return trailingByteSize != 0 ? this.writeZeros((long)(8 - trailingByteSize)) : 0L;
   }

   public long write(ByteBuffer buffer) throws IOException {
      long length = (long)buffer.remaining();

      while(buffer.hasRemaining()) {
         this.out.write(buffer);
      }

      this.currentPosition += length;
      return length;
   }

   public long writeIntLittleEndian(int v) throws IOException {
      MessageSerializer.intToBytes(v, this.intBuf);
      return this.write(this.intBuf);
   }

   public void write(ArrowBuf buffer) throws IOException {
      int bytesToWrite;
      for(long bytesWritten = 0L; bytesWritten < buffer.readableBytes(); bytesWritten += (long)bytesToWrite) {
         bytesToWrite = (int)Math.min(2147483647L, buffer.readableBytes() - bytesWritten);
         ByteBuffer nioBuffer = buffer.nioBuffer(buffer.readerIndex() + bytesWritten, bytesToWrite);
         this.write(nioBuffer);
      }

   }

   public long write(FBSerializable writer, boolean withSizePrefix) throws IOException {
      ByteBuffer buffer = serialize(writer);
      if (withSizePrefix) {
         this.writeIntLittleEndian(buffer.remaining());
      }

      return this.write(buffer);
   }

   public static ByteBuffer serialize(FBSerializable writer) {
      FlatBufferBuilder builder = new FlatBufferBuilder();
      int root = writer.writeTo(builder);
      builder.finish(root);
      return builder.dataBuffer();
   }
}
