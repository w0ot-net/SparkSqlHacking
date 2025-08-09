package org.apache.parquet.hadoop.util;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.hadoop.util.wrapped.io.VectorIoBridge;
import org.apache.parquet.io.DelegatingSeekableInputStream;
import org.apache.parquet.io.ParquetFileRange;

class H2SeekableInputStream extends DelegatingSeekableInputStream {
   private final FSDataInputStream stream;
   private final Reader reader;

   public H2SeekableInputStream(FSDataInputStream stream) {
      super(stream);
      this.stream = stream;
      this.reader = new H2Reader();
   }

   public void close() throws IOException {
      this.stream.close();
   }

   public long getPos() throws IOException {
      return this.stream.getPos();
   }

   public void seek(long newPos) throws IOException {
      this.stream.seek(newPos);
   }

   public void readFully(byte[] bytes, int start, int len) throws IOException {
      this.stream.readFully(bytes, start, len);
   }

   public int read(ByteBuffer buf) throws IOException {
      return this.stream.read(buf);
   }

   public void readFully(ByteBuffer buf) throws IOException {
      readFully(this.reader, buf);
   }

   public boolean readVectoredAvailable(ByteBufferAllocator allocator) {
      return VectorIoBridge.instance().readVectoredAvailable(this.stream, allocator);
   }

   public void readVectored(List ranges, ByteBufferAllocator allocator) throws IOException {
      VectorIoBridge.instance().readVectoredRanges(this.stream, ranges, allocator);
   }

   public static void readFully(Reader reader, ByteBuffer buf) throws IOException {
      while(true) {
         if (buf.hasRemaining()) {
            int readCount = reader.read(buf);
            if (readCount != -1) {
               continue;
            }

            throw new EOFException("Reached the end of stream. Still have: " + buf.remaining() + " bytes left");
         }

         return;
      }
   }

   private class H2Reader implements Reader {
      private H2Reader() {
      }

      public int read(ByteBuffer buf) throws IOException {
         return H2SeekableInputStream.this.stream.read(buf);
      }
   }

   interface Reader {
      int read(ByteBuffer var1) throws IOException;
   }
}
