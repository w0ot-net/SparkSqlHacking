package org.apache.avro.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.avro.util.NonCopyingByteArrayOutputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class XZCodec extends Codec {
   public static final int DEFAULT_COMPRESSION = 6;
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private int compressionLevel;

   public XZCodec(int compressionLevel) {
      this.compressionLevel = compressionLevel;
   }

   public String getName() {
      return "xz";
   }

   public ByteBuffer compress(ByteBuffer data) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(8192);
      OutputStream outputStream = new XZCompressorOutputStream(baos, this.compressionLevel);

      try {
         outputStream.write(data.array(), computeOffset(data), data.remaining());
      } catch (Throwable var7) {
         try {
            outputStream.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      outputStream.close();
      return baos.asByteBuffer();
   }

   public ByteBuffer decompress(ByteBuffer data) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(8192);
      InputStream bytesIn = new ByteArrayInputStream(data.array(), computeOffset(data), data.remaining());
      InputStream ios = new XZCompressorInputStream(bytesIn);

      try {
         IOUtils.copy(ios, baos);
      } catch (Throwable var8) {
         try {
            ios.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      ios.close();
      return baos.asByteBuffer();
   }

   public int hashCode() {
      return this.compressionLevel;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         XZCodec other = (XZCodec)obj;
         return this.compressionLevel == other.compressionLevel;
      } else {
         return false;
      }
   }

   public String toString() {
      String var10000 = this.getName();
      return var10000 + "-" + this.compressionLevel;
   }

   static class Option extends CodecFactory {
      private int compressionLevel;

      Option(int compressionLevel) {
         this.compressionLevel = compressionLevel;
      }

      protected Codec createInstance() {
         return new XZCodec(this.compressionLevel);
      }
   }
}
