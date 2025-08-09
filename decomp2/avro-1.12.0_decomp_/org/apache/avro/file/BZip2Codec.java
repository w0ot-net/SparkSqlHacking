package org.apache.avro.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.avro.util.NonCopyingByteArrayOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

public class BZip2Codec extends Codec {
   public static final int DEFAULT_BUFFER_SIZE = 65536;
   private final byte[] buffer = new byte[65536];

   public String getName() {
      return "bzip2";
   }

   public ByteBuffer compress(ByteBuffer uncompressedData) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(65536);
      BZip2CompressorOutputStream outputStream = new BZip2CompressorOutputStream(baos);

      try {
         outputStream.write(uncompressedData.array(), computeOffset(uncompressedData), uncompressedData.remaining());
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

   public ByteBuffer decompress(ByteBuffer compressedData) throws IOException {
      ByteArrayInputStream bais = new ByteArrayInputStream(compressedData.array(), computeOffset(compressedData), compressedData.remaining());
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(65536);
      BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(bais);

      ByteBuffer var6;
      try {
         int readCount = -1;

         while((readCount = inputStream.read(this.buffer, compressedData.position(), this.buffer.length)) > 0) {
            baos.write(this.buffer, 0, readCount);
         }

         var6 = baos.asByteBuffer();
      } catch (Throwable var8) {
         try {
            inputStream.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      inputStream.close();
      return var6;
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
         return new BZip2Codec();
      }
   }
}
