package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;
import org.apache.orc.impl.HadoopShims.DirectCompressionType;

public class ZlibCodec implements CompressionCodec, DirectDecompressionCodec {
   private static final HadoopShims SHIMS = HadoopShimsFactory.get();
   private HadoopShims.DirectDecompressor decompressShim = null;
   private Boolean direct = null;
   private static final ZlibOptions DEFAULT_OPTIONS = new ZlibOptions(-1, 0, true);

   public CompressionCodec.Options getDefaultOptions() {
      return DEFAULT_OPTIONS;
   }

   public boolean compress(ByteBuffer in, ByteBuffer out, ByteBuffer overflow, CompressionCodec.Options options) {
      ZlibOptions zlo = (ZlibOptions)options;
      int length = in.remaining();
      int outSize = 0;
      Deflater deflater = new Deflater(zlo.level, true);

      try {
         deflater.setStrategy(zlo.strategy);
         deflater.setInput(in.array(), in.arrayOffset() + in.position(), length);
         deflater.finish();
         int offset = out.arrayOffset() + out.position();

         while(!deflater.finished() && length > outSize) {
            int size = deflater.deflate(out.array(), offset, out.remaining());
            out.position(size + out.position());
            outSize += size;
            offset += size;
            if (out.remaining() == 0) {
               if (overflow == null) {
                  boolean var11 = false;
                  return var11;
               }

               out = overflow;
               offset = overflow.arrayOffset() + overflow.position();
            }
         }
      } finally {
         deflater.end();
      }

      return length > outSize;
   }

   public void decompress(ByteBuffer in, ByteBuffer out) throws IOException {
      if (in.isDirect() && out.isDirect()) {
         this.directDecompress(in, out);
      } else {
         Inflater inflater = new Inflater(true);

         try {
            inflater.setInput(in.array(), in.arrayOffset() + in.position(), in.remaining());

            while(!inflater.finished() && !inflater.needsDictionary() && !inflater.needsInput()) {
               try {
                  int count = inflater.inflate(out.array(), out.arrayOffset() + out.position(), out.remaining());
                  out.position(count + out.position());
                  if (!inflater.finished() && !inflater.needsDictionary() && !inflater.needsInput() && count == 0) {
                     if (out.remaining() == 0) {
                        String var10 = String.valueOf(in);
                        throw new IOException("Decompress output buffer too small. in = " + var10 + ", out = " + String.valueOf(out));
                     }

                     String var10002 = String.valueOf(in);
                     throw new IOException("Decompress error. in = " + var10002 + ", out = " + String.valueOf(out));
                  }
               } catch (DataFormatException dfe) {
                  throw new IOException("Bad compression data", dfe);
               }
            }

            out.flip();
         } finally {
            inflater.end();
         }

         in.position(in.limit());
      }
   }

   public boolean isAvailable() {
      if (this.direct == null) {
         try {
            this.ensureShim();
            this.direct = this.decompressShim != null;
         } catch (UnsatisfiedLinkError var2) {
            this.direct = false;
         }
      }

      return this.direct;
   }

   private void ensureShim() {
      if (this.decompressShim == null) {
         this.decompressShim = SHIMS.getDirectDecompressor(DirectCompressionType.ZLIB_NOHEADER);
      }

   }

   public void directDecompress(ByteBuffer in, ByteBuffer out) throws IOException {
      this.ensureShim();
      this.decompressShim.decompress(in, out);
      out.flip();
   }

   public void reset() {
      if (this.decompressShim != null) {
         this.decompressShim.reset();
      }

   }

   public void destroy() {
      if (this.decompressShim != null) {
         this.decompressShim.end();
      }

   }

   public CompressionKind getKind() {
      return CompressionKind.ZLIB;
   }

   public void close() {
      OrcCodecPool.returnCodec(CompressionKind.ZLIB, this);
   }

   static class ZlibOptions implements CompressionCodec.Options {
      private int level;
      private int strategy;
      private final boolean FIXED;

      ZlibOptions(int level, int strategy, boolean fixed) {
         this.level = level;
         this.strategy = strategy;
         this.FIXED = fixed;
      }

      public ZlibOptions copy() {
         return new ZlibOptions(this.level, this.strategy, false);
      }

      public ZlibOptions setSpeed(CompressionCodec.SpeedModifier newValue) {
         if (this.FIXED) {
            throw new IllegalStateException("Attempt to modify the default options");
         } else {
            switch (newValue) {
               case FAST -> this.level = 2;
               case DEFAULT -> this.level = -1;
               case FASTEST -> this.level = 1;
            }

            return this;
         }
      }

      public ZlibOptions setData(CompressionCodec.DataKind newValue) {
         if (this.FIXED) {
            throw new IllegalStateException("Attempt to modify the default options");
         } else {
            switch (newValue) {
               case BINARY -> this.strategy = 1;
               case TEXT -> this.strategy = 0;
            }

            return this;
         }
      }

      public boolean equals(Object other) {
         if (other != null && this.getClass() == other.getClass()) {
            if (this == other) {
               return true;
            } else {
               ZlibOptions otherOpts = (ZlibOptions)other;
               return this.level == otherOpts.level && this.strategy == otherOpts.strategy;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.level + this.strategy * 101;
      }
   }
}
