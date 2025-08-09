package org.apache.orc.impl;

import io.airlift.compress.Compressor;
import io.airlift.compress.Decompressor;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;

public class AircompressorCodec implements CompressionCodec {
   private final CompressionKind kind;
   private final Compressor compressor;
   private final Decompressor decompressor;
   private static final ThreadLocal threadBuffer = new ThreadLocal() {
      protected byte[] initialValue() {
         return null;
      }
   };
   private static final CompressionCodec.Options NULL_OPTION = new CompressionCodec.Options() {
      public CompressionCodec.Options copy() {
         return this;
      }

      public CompressionCodec.Options setSpeed(CompressionCodec.SpeedModifier newValue) {
         return this;
      }

      public CompressionCodec.Options setData(CompressionCodec.DataKind newValue) {
         return this;
      }

      public boolean equals(Object other) {
         return other != null && this.getClass() == other.getClass();
      }

      public int hashCode() {
         return 0;
      }
   };

   AircompressorCodec(CompressionKind kind, Compressor compressor, Decompressor decompressor) {
      this.kind = kind;
      this.compressor = compressor;
      this.decompressor = decompressor;
   }

   protected static byte[] getBuffer(int size) {
      byte[] result = (byte[])threadBuffer.get();
      if (result == null || result.length < size || result.length > size * 2) {
         result = new byte[size];
         threadBuffer.set(result);
      }

      return result;
   }

   public boolean compress(ByteBuffer in, ByteBuffer out, ByteBuffer overflow, CompressionCodec.Options options) {
      int inBytes = in.remaining();
      byte[] compressed = getBuffer(this.compressor.maxCompressedLength(inBytes));
      int outBytes = this.compressor.compress(in.array(), in.arrayOffset() + in.position(), inBytes, compressed, 0, compressed.length);
      if (outBytes < inBytes) {
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

         return true;
      } else {
         return false;
      }
   }

   public void decompress(ByteBuffer in, ByteBuffer out) throws IOException {
      int inOffset = in.position();
      int uncompressLen = this.decompressor.decompress(in.array(), in.arrayOffset() + inOffset, in.limit() - inOffset, out.array(), out.arrayOffset() + out.position(), out.remaining());
      out.position(uncompressLen + out.position());
      out.flip();
   }

   public CompressionCodec.Options getDefaultOptions() {
      return NULL_OPTION;
   }

   public void reset() {
   }

   public void destroy() {
   }

   public CompressionKind getKind() {
      return this.kind;
   }

   public void close() {
      OrcCodecPool.returnCodec(this.kind, this);
   }
}
