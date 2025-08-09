package org.apache.avro.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.avro.util.NonCopyingByteArrayOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ZstandardCodec extends Codec {
   public static final int DEFAULT_COMPRESSION = 3;
   public static final boolean DEFAULT_USE_BUFFERPOOL = false;
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private final int compressionLevel;
   private final boolean useChecksum;
   private final boolean useBufferPool;

   public ZstandardCodec(int compressionLevel, boolean useChecksum, boolean useBufferPool) {
      this.compressionLevel = compressionLevel;
      this.useChecksum = useChecksum;
      this.useBufferPool = useBufferPool;
   }

   public String getName() {
      return "zstandard";
   }

   public ByteBuffer compress(ByteBuffer data) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(8192);
      OutputStream outputStream = ZstandardLoader.output(baos, this.compressionLevel, this.useChecksum, this.useBufferPool);

      try {
         outputStream.write(data.array(), computeOffset(data), data.remaining());
      } catch (Throwable var7) {
         if (outputStream != null) {
            try {
               outputStream.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (outputStream != null) {
         outputStream.close();
      }

      return baos.asByteBuffer();
   }

   public ByteBuffer decompress(ByteBuffer compressedData) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(8192);
      InputStream bytesIn = new ByteArrayInputStream(compressedData.array(), computeOffset(compressedData), compressedData.remaining());
      InputStream ios = ZstandardLoader.input(bytesIn, this.useBufferPool);

      try {
         IOUtils.copy(ios, baos);
      } catch (Throwable var8) {
         if (ios != null) {
            try {
               ios.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }
         }

         throw var8;
      }

      if (ios != null) {
         ios.close();
      }

      return baos.asByteBuffer();
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public boolean equals(Object obj) {
      return this == obj || obj != null && obj.getClass() == this.getClass();
   }

   public String toString() {
      String var10000 = this.getName();
      return var10000 + "[" + this.compressionLevel + "]";
   }

   static class Option extends CodecFactory {
      private final int compressionLevel;
      private final boolean useChecksum;
      private final boolean useBufferPool;

      Option(int compressionLevel, boolean useChecksum, boolean useBufferPool) {
         this.compressionLevel = compressionLevel;
         this.useChecksum = useChecksum;
         this.useBufferPool = useBufferPool;
      }

      protected Codec createInstance() {
         return new ZstandardCodec(this.compressionLevel, this.useChecksum, this.useBufferPool);
      }
   }
}
