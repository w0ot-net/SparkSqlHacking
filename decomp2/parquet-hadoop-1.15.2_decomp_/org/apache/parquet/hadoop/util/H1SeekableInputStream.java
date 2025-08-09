package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.hadoop.util.wrapped.io.VectorIoBridge;
import org.apache.parquet.io.DelegatingSeekableInputStream;
import org.apache.parquet.io.ParquetFileRange;

class H1SeekableInputStream extends DelegatingSeekableInputStream {
   private final FSDataInputStream stream;

   public H1SeekableInputStream(FSDataInputStream stream) {
      super(stream);
      this.stream = stream;
   }

   public long getPos() throws IOException {
      return this.stream.getPos();
   }

   public void seek(long newPos) throws IOException {
      this.stream.seek(newPos);
   }

   public void readFully(byte[] bytes) throws IOException {
      this.stream.readFully(bytes, 0, bytes.length);
   }

   public void readFully(byte[] bytes, int start, int len) throws IOException {
      this.stream.readFully(bytes, start, len);
   }

   public boolean readVectoredAvailable(ByteBufferAllocator allocator) {
      return VectorIoBridge.instance().readVectoredAvailable(this.stream, allocator);
   }

   public void readVectored(List ranges, ByteBufferAllocator allocator) throws IOException {
      VectorIoBridge.instance().readVectoredRanges(this.stream, ranges, allocator);
   }
}
