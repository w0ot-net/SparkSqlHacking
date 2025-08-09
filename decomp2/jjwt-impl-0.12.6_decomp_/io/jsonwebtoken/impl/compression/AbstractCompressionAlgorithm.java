package io.jsonwebtoken.impl.compression;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionException;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.lang.PropagatingExceptionFunction;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractCompressionAlgorithm implements CompressionAlgorithm, CompressionCodec {
   private final String id;
   private final Function OS_WRAP_FN;
   private final Function IS_WRAP_FN;
   private final Function COMPRESS_FN;
   private final Function DECOMPRESS_FN;

   private static Function propagate(CheckedFunction fn, String msg) {
      return new PropagatingExceptionFunction(fn, CompressionException.class, msg);
   }

   private static Function forCompression(CheckedFunction fn) {
      return propagate(fn, "Compression failed.");
   }

   private static Function forDecompression(CheckedFunction fn) {
      return propagate(fn, "Decompression failed.");
   }

   protected AbstractCompressionAlgorithm(String id) {
      this.id = (String)Assert.hasText(Strings.clean(id), "id argument cannot be null or empty.");
      this.OS_WRAP_FN = forCompression(new CheckedFunction() {
         public OutputStream apply(OutputStream out) throws Exception {
            return AbstractCompressionAlgorithm.this.doCompress(out);
         }
      });
      this.COMPRESS_FN = forCompression(new CheckedFunction() {
         public byte[] apply(byte[] data) throws Exception {
            return AbstractCompressionAlgorithm.this.doCompress(data);
         }
      });
      this.IS_WRAP_FN = forDecompression(new CheckedFunction() {
         public InputStream apply(InputStream is) throws Exception {
            return AbstractCompressionAlgorithm.this.doDecompress(is);
         }
      });
      this.DECOMPRESS_FN = forDecompression(new CheckedFunction() {
         public byte[] apply(byte[] data) throws Exception {
            return AbstractCompressionAlgorithm.this.doDecompress(data);
         }
      });
   }

   public String getId() {
      return this.id;
   }

   public String getAlgorithmName() {
      return this.getId();
   }

   public final OutputStream compress(OutputStream out) throws CompressionException {
      return (OutputStream)this.OS_WRAP_FN.apply(out);
   }

   protected abstract OutputStream doCompress(OutputStream var1) throws IOException;

   public final InputStream decompress(InputStream is) throws CompressionException {
      return (InputStream)this.IS_WRAP_FN.apply(is);
   }

   protected abstract InputStream doDecompress(InputStream var1) throws IOException;

   public final byte[] compress(byte[] content) {
      return Bytes.isEmpty(content) ? Bytes.EMPTY : (byte[])this.COMPRESS_FN.apply(content);
   }

   private byte[] doCompress(byte[] data) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream(512);
      OutputStream compression = this.compress((OutputStream)out);

      try {
         compression.write(data);
         compression.flush();
      } finally {
         Objects.nullSafeClose(new Closeable[]{compression});
      }

      return out.toByteArray();
   }

   public final byte[] decompress(byte[] compressed) {
      return Bytes.isEmpty(compressed) ? Bytes.EMPTY : (byte[])this.DECOMPRESS_FN.apply(compressed);
   }

   protected byte[] doDecompress(byte[] compressed) throws IOException {
      InputStream is = Streams.of(compressed);
      InputStream decompress = this.decompress(is);
      byte[] buffer = new byte[512];
      ByteArrayOutputStream out = new ByteArrayOutputStream(buffer.length);
      int read = 0;

      try {
         while(read != -1) {
            read = decompress.read(buffer);
            if (read > 0) {
               out.write(buffer, 0, read);
            }
         }
      } finally {
         Objects.nullSafeClose(new Closeable[]{decompress});
      }

      return out.toByteArray();
   }
}
