package org.apache.orc.impl;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.decoder.DecoderJNI;
import com.aayushatharva.brotli4j.decoder.DecoderJNI.Status;
import com.aayushatharva.brotli4j.encoder.Encoder;
import com.aayushatharva.brotli4j.encoder.Encoder.Mode;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;

public class BrotliCodec implements CompressionCodec, DirectDecompressionCodec {
   private static final BrotliOptions DEFAULT_OPTIONS;

   public CompressionCodec.Options getDefaultOptions() {
      return DEFAULT_OPTIONS;
   }

   public boolean compress(ByteBuffer in, ByteBuffer out, ByteBuffer overflow, CompressionCodec.Options options) throws IOException {
      BrotliOptions brotliOptions = (BrotliOptions)options;
      int inBytes = in.remaining();
      byte[] compressed = Encoder.compress(in.array(), in.arrayOffset() + in.position(), inBytes, brotliOptions.brotli4jParameter());
      int outBytes = compressed.length;
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
      int compressedBytes = in.remaining();
      DecoderJNI.Wrapper decoder = new DecoderJNI.Wrapper(compressedBytes);

      try {
         decoder.getInputBuffer().put(in);
         decoder.push(compressedBytes);

         while(decoder.getStatus() != Status.DONE) {
            switch (decoder.getStatus()) {
               case OK:
                  decoder.push(0);
                  break;
               case NEEDS_MORE_OUTPUT:
                  ByteBuffer buffer = decoder.pull();
                  out.put(buffer);
                  break;
               case NEEDS_MORE_INPUT:
                  decoder.push(0);
                  if (decoder.getStatus() == Status.NEEDS_MORE_INPUT) {
                     return;
                  }
                  break;
               default:
                  return;
            }
         }

      } finally {
         out.flip();
         decoder.destroy();
      }
   }

   public boolean isAvailable() {
      return true;
   }

   public CompressionKind getKind() {
      return CompressionKind.BROTLI;
   }

   public void directDecompress(ByteBuffer in, ByteBuffer out) throws IOException {
      this.decompress(in, out);
   }

   public void reset() {
   }

   public void destroy() {
   }

   public void close() {
      OrcCodecPool.returnCodec(CompressionKind.BROTLI, this);
   }

   static {
      Brotli4jLoader.ensureAvailability();
      DEFAULT_OPTIONS = new BrotliOptions();
   }

   static class BrotliOptions implements CompressionCodec.Options {
      private Encoder.Mode mode;
      private int quality;
      private int lgwin;

      BrotliOptions() {
         this.mode = Mode.GENERIC;
         this.quality = -1;
         this.lgwin = -1;
      }

      BrotliOptions(int quality, int lgwin, Encoder.Mode mode) {
         this.mode = Mode.GENERIC;
         this.quality = -1;
         this.lgwin = -1;
         this.quality = quality;
         this.lgwin = lgwin;
         this.mode = mode;
      }

      public CompressionCodec.Options copy() {
         return new BrotliOptions(this.quality, this.lgwin, this.mode);
      }

      public CompressionCodec.Options setSpeed(CompressionCodec.SpeedModifier newValue) {
         switch (newValue) {
            case FAST -> this.quality = 1;
            case DEFAULT -> this.quality = -1;
            case FASTEST -> this.quality = 0;
         }

         return this;
      }

      public CompressionCodec.Options setData(CompressionCodec.DataKind newValue) {
         switch (newValue) {
            case BINARY -> this.mode = Mode.GENERIC;
            case TEXT -> this.mode = Mode.TEXT;
         }

         return this;
      }

      public Encoder.Parameters brotli4jParameter() {
         return (new Encoder.Parameters()).setQuality(this.quality).setWindow(this.lgwin).setMode(this.mode);
      }
   }
}
