package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class ChunkedOutputStream extends FilterOutputStream {
   private final int chunkSize;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public ChunkedOutputStream(OutputStream stream) {
      this(stream, 8192);
   }

   /** @deprecated */
   @Deprecated
   public ChunkedOutputStream(OutputStream stream, int chunkSize) {
      super(stream);
      if (chunkSize <= 0) {
         throw new IllegalArgumentException("chunkSize <= 0");
      } else {
         this.chunkSize = chunkSize;
      }
   }

   int getChunkSize() {
      return this.chunkSize;
   }

   public void write(byte[] data, int srcOffset, int length) throws IOException {
      int bytes = length;

      int chunk;
      for(int dstOffset = srcOffset; bytes > 0; dstOffset += chunk) {
         chunk = Math.min(bytes, this.chunkSize);
         this.out.write(data, dstOffset, chunk);
         bytes -= chunk;
      }

   }

   public static class Builder extends AbstractStreamBuilder {
      public ChunkedOutputStream get() throws IOException {
         return new ChunkedOutputStream(this.getOutputStream(), this.getBufferSize());
      }
   }
}
