package org.apache.avro.file;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import org.xerial.snappy.Snappy;

public class SnappyCodec extends Codec {
   private CRC32 crc32 = new CRC32();

   private SnappyCodec() {
   }

   public String getName() {
      return "snappy";
   }

   public ByteBuffer compress(ByteBuffer in) throws IOException {
      int offset = computeOffset(in);
      ByteBuffer out = ByteBuffer.allocate(Snappy.maxCompressedLength(in.remaining()) + 4);
      int size = Snappy.compress(in.array(), offset, in.remaining(), out.array(), 0);
      this.crc32.reset();
      this.crc32.update(in.array(), offset, in.remaining());
      out.putInt(size, (int)this.crc32.getValue());
      ((Buffer)out).limit(size + 4);
      return out;
   }

   public ByteBuffer decompress(ByteBuffer in) throws IOException {
      int offset = computeOffset(in);
      ByteBuffer out = ByteBuffer.allocate(Snappy.uncompressedLength(in.array(), offset, in.remaining() - 4));
      int size = Snappy.uncompress(in.array(), offset, in.remaining() - 4, out.array(), 0);
      ((Buffer)out).limit(size);
      this.crc32.reset();
      this.crc32.update(out.array(), 0, size);
      if (in.getInt(((Buffer)in).limit() - 4) != (int)this.crc32.getValue()) {
         throw new IOException("Checksum failure");
      } else {
         return out;
      }
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return obj != null && obj.getClass() == this.getClass();
      }
   }

   static class Option extends CodecFactory {
      protected Codec createInstance() {
         return new SnappyCodec();
      }

      static {
         Snappy.getNativeLibraryVersion();
      }
   }
}
