package io.jsonwebtoken.impl.compression;

import io.jsonwebtoken.lang.Objects;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

public class DeflateCompressionAlgorithm extends AbstractCompressionAlgorithm {
   private static final String ID = "DEF";

   public DeflateCompressionAlgorithm() {
      super("DEF");
   }

   protected OutputStream doCompress(OutputStream out) {
      return new DeflaterOutputStream(out);
   }

   protected InputStream doDecompress(InputStream is) {
      return new InflaterInputStream(is);
   }

   protected byte[] doDecompress(byte[] compressed) throws IOException {
      try {
         return super.doDecompress(compressed);
      } catch (IOException e1) {
         try {
            return this.doDecompressBackCompat(compressed);
         } catch (IOException var4) {
            throw e1;
         }
      }
   }

   byte[] doDecompressBackCompat(byte[] compressed) throws IOException {
      InflaterOutputStream inflaterOutputStream = null;
      ByteArrayOutputStream decompressedOutputStream = null;

      byte[] var4;
      try {
         decompressedOutputStream = new ByteArrayOutputStream();
         inflaterOutputStream = new InflaterOutputStream(decompressedOutputStream);
         inflaterOutputStream.write(compressed);
         inflaterOutputStream.flush();
         var4 = decompressedOutputStream.toByteArray();
      } finally {
         Objects.nullSafeClose(new Closeable[]{decompressedOutputStream, inflaterOutputStream});
      }

      return var4;
   }
}
