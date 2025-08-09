package org.sparkproject.jetty.server.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.BufferUtil;

public class ByteBufferRangeWriter implements RangeWriter {
   private final ByteBuffer buffer;
   private boolean closed = false;

   public ByteBufferRangeWriter(ByteBuffer buffer) {
      this.buffer = buffer.asReadOnlyBuffer();
   }

   public void close() throws IOException {
      this.closed = true;
   }

   public void writeTo(OutputStream outputStream, long skipTo, long length) throws IOException {
      if (skipTo > 2147483647L) {
         throw new IllegalArgumentException("Unsupported skipTo " + skipTo + " > 2147483647");
      } else if (length > 2147483647L) {
         throw new IllegalArgumentException("Unsupported length " + skipTo + " > 2147483647");
      } else {
         ByteBuffer src = this.buffer.slice();
         src.position((int)skipTo);
         src.limit(Math.addExact((int)skipTo, (int)length));
         BufferUtil.writeTo(src, outputStream);
      }
   }
}
