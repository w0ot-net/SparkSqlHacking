package org.apache.orc.impl;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdCompressCtx;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;

public class ZstdCodec implements CompressionCodec, DirectDecompressionCodec {
   private ZstdOptions zstdOptions;
   private ZstdCompressCtx zstdCompressCtx;
   private static final ThreadLocal threadBuffer = ThreadLocal.withInitial(() -> null);
   private static final ZstdOptions DEFAULT_OPTIONS = new ZstdOptions(3, 0);

   public ZstdCodec(int level, int windowLog) {
      this.zstdOptions = null;
      this.zstdCompressCtx = null;
      this.zstdOptions = new ZstdOptions(level, windowLog);
   }

   public ZstdCodec() {
      this(3, 0);
   }

   public ZstdOptions getZstdOptions() {
      return this.zstdOptions;
   }

   protected static byte[] getBuffer(int size) {
      byte[] result = (byte[])threadBuffer.get();
      if (result == null || result.length < size || result.length > size * 2) {
         result = new byte[size];
         threadBuffer.set(result);
      }

      return result;
   }

   public CompressionCodec.Options getDefaultOptions() {
      return DEFAULT_OPTIONS;
   }

   public boolean compress(ByteBuffer in, ByteBuffer out, ByteBuffer overflow, CompressionCodec.Options options) throws IOException {
      int inBytes = in.remaining();
      if (inBytes < 10) {
         return false;
      } else {
         ZstdOptions zso = (ZstdOptions)options;
         this.zstdCompressCtx = new ZstdCompressCtx();
         this.zstdCompressCtx.setLevel(zso.level);
         this.zstdCompressCtx.setLong(zso.windowLog);
         this.zstdCompressCtx.setChecksum(false);

         boolean var10;
         try {
            byte[] compressed = getBuffer((int)Zstd.compressBound((long)inBytes));
            int outBytes = this.zstdCompressCtx.compressByteArray(compressed, 0, compressed.length, in.array(), in.arrayOffset() + in.position(), inBytes);
            if (outBytes >= inBytes) {
               boolean var14 = false;
               return var14;
            }

            int remaining = out.remaining();
            if (remaining >= outBytes) {
               System.arraycopy(compressed, 0, out.array(), out.arrayOffset() + out.position(), outBytes);
               out.position(out.position() + outBytes);
            } else {
               System.arraycopy(compressed, 0, out.array(), out.arrayOffset() + out.position(), remaining);
               out.position(out.limit());
               System.arraycopy(compressed, remaining, overflow.array(), overflow.arrayOffset(), outBytes - remaining);
               overflow.position(outBytes - remaining);
            }

            var10 = true;
         } finally {
            this.zstdCompressCtx.close();
         }

         return var10;
      }
   }

   public void decompress(ByteBuffer in, ByteBuffer out) throws IOException {
      if (in.isDirect() && out.isDirect()) {
         this.directDecompress(in, out);
      } else {
         int srcOffset = in.arrayOffset() + in.position();
         int srcSize = in.remaining();
         int dstOffset = out.arrayOffset() + out.position();
         int dstSize = out.remaining() - dstOffset;
         long decompressOut = Zstd.decompressByteArray(out.array(), dstOffset, dstSize, in.array(), srcOffset, srcSize);
         in.position(in.limit());
         out.position(dstOffset + (int)decompressOut);
         out.flip();
      }
   }

   public boolean isAvailable() {
      return true;
   }

   public void directDecompress(ByteBuffer in, ByteBuffer out) throws IOException {
      Zstd.decompress(out, in);
      out.flip();
   }

   public void reset() {
   }

   public void destroy() {
      if (this.zstdCompressCtx != null) {
         this.zstdCompressCtx.close();
      }

   }

   public CompressionKind getKind() {
      return CompressionKind.ZSTD;
   }

   public void close() {
      OrcCodecPool.returnCodec(CompressionKind.ZSTD, this);
   }

   static class ZstdOptions implements CompressionCodec.Options {
      private int level;
      private int windowLog;

      ZstdOptions(int level, int windowLog) {
         this.level = level;
         this.windowLog = windowLog;
      }

      public ZstdOptions copy() {
         return new ZstdOptions(this.level, this.windowLog);
      }

      public CompressionCodec.Options setSpeed(CompressionCodec.SpeedModifier newValue) {
         return this;
      }

      public ZstdOptions setWindowLog(int newValue) {
         if ((newValue < Zstd.windowLogMin() || newValue > Zstd.windowLogMax()) && newValue != 0) {
            throw new IllegalArgumentException(String.format("Zstd compression window size should be in the range %d to %d, or set to the default value of 0.", Zstd.windowLogMin(), Zstd.windowLogMax()));
         } else {
            this.windowLog = newValue;
            return this;
         }
      }

      public ZstdOptions setLevel(int newValue) {
         if (newValue >= Zstd.minCompressionLevel() && newValue <= Zstd.maxCompressionLevel()) {
            this.level = newValue;
            return this;
         } else {
            throw new IllegalArgumentException(String.format("Zstd compression level should be in the range %d to %d", Zstd.minCompressionLevel(), Zstd.maxCompressionLevel()));
         }
      }

      public ZstdOptions setData(CompressionCodec.DataKind newValue) {
         return this;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ZstdOptions that = (ZstdOptions)o;
            if (this.level != that.level) {
               return false;
            } else {
               return this.windowLog == that.windowLog;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.level;
         result = 31 * result + this.windowLog;
         return result;
      }
   }
}
