package org.apache.avro.file;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;
import org.apache.avro.util.NonCopyingByteArrayOutputStream;

public class DeflateCodec extends Codec {
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private Deflater deflater;
   private Inflater inflater;
   private boolean nowrap = true;
   private int compressionLevel;

   public DeflateCodec(int compressionLevel) {
      this.compressionLevel = compressionLevel;
   }

   public String getName() {
      return "deflate";
   }

   public ByteBuffer compress(ByteBuffer data) throws IOException {
      NonCopyingByteArrayOutputStream baos = new NonCopyingByteArrayOutputStream(8192);
      OutputStream outputStream = new DeflaterOutputStream(baos, this.getDeflater());

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
      OutputStream outputStream = new InflaterOutputStream(baos, this.getInflater());

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

   private Inflater getInflater() {
      if (null == this.inflater) {
         this.inflater = new Inflater(this.nowrap);
      } else {
         this.inflater.reset();
      }

      return this.inflater;
   }

   private Deflater getDeflater() {
      if (null == this.deflater) {
         this.deflater = new Deflater(this.compressionLevel, this.nowrap);
      } else {
         this.deflater.reset();
      }

      return this.deflater;
   }

   public int hashCode() {
      return this.nowrap ? 0 : 1;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         DeflateCodec other = (DeflateCodec)obj;
         return this.nowrap == other.nowrap;
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
         return new DeflateCodec(this.compressionLevel);
      }
   }
}
