package io.netty.handler.codec.compression;

import com.aayushatharva.brotli4j.encoder.Encoder;
import com.aayushatharva.brotli4j.encoder.Encoder.Mode;
import io.netty.util.internal.ObjectUtil;

public final class BrotliOptions implements CompressionOptions {
   private final Encoder.Parameters parameters;
   static final BrotliOptions DEFAULT;

   BrotliOptions(Encoder.Parameters parameters) {
      if (!Brotli.isAvailable()) {
         throw new IllegalStateException("Brotli is not available", Brotli.cause());
      } else {
         this.parameters = (Encoder.Parameters)ObjectUtil.checkNotNull(parameters, "Parameters");
      }
   }

   public Encoder.Parameters parameters() {
      return this.parameters;
   }

   static {
      DEFAULT = new BrotliOptions((new Encoder.Parameters()).setQuality(4).setMode(Mode.TEXT));
   }
}
