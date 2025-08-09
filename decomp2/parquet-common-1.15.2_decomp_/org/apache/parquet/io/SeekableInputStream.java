package org.apache.parquet.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.parquet.bytes.ByteBufferAllocator;

public abstract class SeekableInputStream extends InputStream {
   public abstract long getPos() throws IOException;

   public abstract void seek(long var1) throws IOException;

   public abstract void readFully(byte[] var1) throws IOException;

   public abstract void readFully(byte[] var1, int var2, int var3) throws IOException;

   public abstract int read(ByteBuffer var1) throws IOException;

   public abstract void readFully(ByteBuffer var1) throws IOException;

   public void readVectored(List ranges, ByteBufferAllocator allocator) throws IOException {
      throw new UnsupportedOperationException("Vectored IO is not supported for " + this);
   }

   public boolean readVectoredAvailable(ByteBufferAllocator allocator) {
      return false;
   }
}
