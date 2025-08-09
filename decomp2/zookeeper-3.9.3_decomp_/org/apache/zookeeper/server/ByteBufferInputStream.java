package org.apache.zookeeper.server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.Record;

public class ByteBufferInputStream extends InputStream {
   private final ByteBuffer bb;

   public ByteBufferInputStream(ByteBuffer bb) {
      this.bb = bb;
   }

   public int read() throws IOException {
      return this.bb.remaining() == 0 ? -1 : this.bb.get() & 255;
   }

   public int available() throws IOException {
      return this.bb.remaining();
   }

   public int read(@Nonnull byte[] b, int off, int len) throws IOException {
      if (this.bb.remaining() == 0) {
         return -1;
      } else {
         if (len > this.bb.remaining()) {
            len = this.bb.remaining();
         }

         this.bb.get(b, off, len);
         return len;
      }
   }

   public int read(@Nonnull byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   public long skip(long n) throws IOException {
      if (n < 0L) {
         return 0L;
      } else {
         n = Math.min(n, (long)this.bb.remaining());
         this.bb.position(this.bb.position() + (int)n);
         return n;
      }
   }

   public static void byteBuffer2Record(ByteBuffer bb, Record record) throws IOException {
      BinaryInputArchive ia = BinaryInputArchive.getArchive(new ByteBufferInputStream(bb));
      record.deserialize(ia, "request");
   }
}
