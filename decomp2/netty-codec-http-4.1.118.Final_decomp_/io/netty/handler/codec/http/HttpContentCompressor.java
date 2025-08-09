package io.netty.handler.codec.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.compression.Brotli;
import io.netty.handler.codec.compression.BrotliEncoder;
import io.netty.handler.codec.compression.BrotliOptions;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.compression.DeflateOptions;
import io.netty.handler.codec.compression.GzipOptions;
import io.netty.handler.codec.compression.SnappyFrameEncoder;
import io.netty.handler.codec.compression.SnappyOptions;
import io.netty.handler.codec.compression.StandardCompressionOptions;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.compression.Zstd;
import io.netty.handler.codec.compression.ZstdEncoder;
import io.netty.handler.codec.compression.ZstdOptions;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpContentCompressor extends HttpContentEncoder {
   private final BrotliOptions brotliOptions;
   private final GzipOptions gzipOptions;
   private final DeflateOptions deflateOptions;
   private final ZstdOptions zstdOptions;
   private final SnappyOptions snappyOptions;
   private final int contentSizeThreshold;
   private ChannelHandlerContext ctx;
   private final Map factories;

   public HttpContentCompressor() {
      this(0, (CompressionOptions[])null);
   }

   /** @deprecated */
   @Deprecated
   public HttpContentCompressor(int compressionLevel) {
      this(compressionLevel, 15, 8, 0);
   }

   /** @deprecated */
   @Deprecated
   public HttpContentCompressor(int compressionLevel, int windowBits, int memLevel) {
      this(compressionLevel, windowBits, memLevel, 0);
   }

   /** @deprecated */
   @Deprecated
   public HttpContentCompressor(int compressionLevel, int windowBits, int memLevel, int contentSizeThreshold) {
      this(contentSizeThreshold, defaultCompressionOptions(StandardCompressionOptions.gzip(ObjectUtil.checkInRange(compressionLevel, 0, 9, "compressionLevel"), ObjectUtil.checkInRange(windowBits, 9, 15, "windowBits"), ObjectUtil.checkInRange(memLevel, 1, 9, "memLevel")), StandardCompressionOptions.deflate(ObjectUtil.checkInRange(compressionLevel, 0, 9, "compressionLevel"), ObjectUtil.checkInRange(windowBits, 9, 15, "windowBits"), ObjectUtil.checkInRange(memLevel, 1, 9, "memLevel"))));
   }

   public HttpContentCompressor(CompressionOptions... compressionOptions) {
      this(0, compressionOptions);
   }

   public HttpContentCompressor(int contentSizeThreshold, CompressionOptions... compressionOptions) {
      this.contentSizeThreshold = ObjectUtil.checkPositiveOrZero(contentSizeThreshold, "contentSizeThreshold");
      BrotliOptions brotliOptions = null;
      GzipOptions gzipOptions = null;
      DeflateOptions deflateOptions = null;
      ZstdOptions zstdOptions = null;
      SnappyOptions snappyOptions = null;
      if (compressionOptions == null || compressionOptions.length == 0) {
         compressionOptions = defaultCompressionOptions(StandardCompressionOptions.gzip(), StandardCompressionOptions.deflate());
      }

      ObjectUtil.deepCheckNotNull("compressionOptions", compressionOptions);

      for(CompressionOptions compressionOption : compressionOptions) {
         if (Brotli.isAvailable() && compressionOption instanceof BrotliOptions) {
            brotliOptions = (BrotliOptions)compressionOption;
         } else if (compressionOption instanceof GzipOptions) {
            gzipOptions = (GzipOptions)compressionOption;
         } else if (compressionOption instanceof DeflateOptions) {
            deflateOptions = (DeflateOptions)compressionOption;
         } else if (Zstd.isAvailable() && compressionOption instanceof ZstdOptions) {
            zstdOptions = (ZstdOptions)compressionOption;
         } else {
            if (!(compressionOption instanceof SnappyOptions)) {
               throw new IllegalArgumentException("Unsupported " + CompressionOptions.class.getSimpleName() + ": " + compressionOption);
            }

            snappyOptions = (SnappyOptions)compressionOption;
         }
      }

      this.gzipOptions = gzipOptions;
      this.deflateOptions = deflateOptions;
      this.brotliOptions = brotliOptions;
      this.zstdOptions = zstdOptions;
      this.snappyOptions = snappyOptions;
      this.factories = new HashMap();
      if (this.gzipOptions != null) {
         this.factories.put("gzip", new GzipEncoderFactory());
      }

      if (this.deflateOptions != null) {
         this.factories.put("deflate", new DeflateEncoderFactory());
      }

      if (Brotli.isAvailable() && this.brotliOptions != null) {
         this.factories.put("br", new BrEncoderFactory());
      }

      if (this.zstdOptions != null) {
         this.factories.put("zstd", new ZstdEncoderFactory());
      }

      if (this.snappyOptions != null) {
         this.factories.put("snappy", new SnappyEncoderFactory());
      }

   }

   private static CompressionOptions[] defaultCompressionOptions(GzipOptions gzipOptions, DeflateOptions deflateOptions) {
      List<CompressionOptions> options = new ArrayList(5);
      options.add(gzipOptions);
      options.add(deflateOptions);
      options.add(StandardCompressionOptions.snappy());
      if (Brotli.isAvailable()) {
         options.add(StandardCompressionOptions.brotli());
      }

      if (Zstd.isAvailable()) {
         options.add(StandardCompressionOptions.zstd());
      }

      return (CompressionOptions[])options.toArray(new CompressionOptions[0]);
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      this.ctx = ctx;
   }

   protected HttpContentEncoder.Result beginEncode(HttpResponse httpResponse, String acceptEncoding) throws Exception {
      if (this.contentSizeThreshold > 0 && httpResponse instanceof HttpContent && ((HttpContent)httpResponse).content().readableBytes() < this.contentSizeThreshold) {
         return null;
      } else {
         String contentEncoding = httpResponse.headers().get((CharSequence)HttpHeaderNames.CONTENT_ENCODING);
         if (contentEncoding != null) {
            return null;
         } else {
            String targetContentEncoding = this.determineEncoding(acceptEncoding);
            if (targetContentEncoding == null) {
               return null;
            } else {
               CompressionEncoderFactory encoderFactory = (CompressionEncoderFactory)this.factories.get(targetContentEncoding);
               if (encoderFactory == null) {
                  throw new IllegalStateException("Couldn't find CompressionEncoderFactory: " + targetContentEncoding);
               } else {
                  return new HttpContentEncoder.Result(targetContentEncoding, new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), new ChannelHandler[]{encoderFactory.createEncoder()}));
               }
            }
         }
      }
   }

   protected String determineEncoding(String acceptEncoding) {
      float starQ = -1.0F;
      float brQ = -1.0F;
      float zstdQ = -1.0F;
      float snappyQ = -1.0F;
      float gzipQ = -1.0F;
      float deflateQ = -1.0F;

      for(String encoding : acceptEncoding.split(",")) {
         float q = 1.0F;
         int equalsPos = encoding.indexOf(61);
         if (equalsPos != -1) {
            try {
               q = Float.parseFloat(encoding.substring(equalsPos + 1));
            } catch (NumberFormatException var15) {
               q = 0.0F;
            }
         }

         if (encoding.contains("*")) {
            starQ = q;
         } else if (encoding.contains("br") && q > brQ) {
            brQ = q;
         } else if (encoding.contains("zstd") && q > zstdQ) {
            zstdQ = q;
         } else if (encoding.contains("snappy") && q > snappyQ) {
            snappyQ = q;
         } else if (encoding.contains("gzip") && q > gzipQ) {
            gzipQ = q;
         } else if (encoding.contains("deflate") && q > deflateQ) {
            deflateQ = q;
         }
      }

      if (brQ > 0.0F || zstdQ > 0.0F || snappyQ > 0.0F || gzipQ > 0.0F || deflateQ > 0.0F) {
         if (brQ != -1.0F && brQ >= zstdQ && this.brotliOptions != null) {
            return "br";
         }

         if (zstdQ != -1.0F && zstdQ >= snappyQ && this.zstdOptions != null) {
            return "zstd";
         }

         if (snappyQ != -1.0F && snappyQ >= gzipQ && this.snappyOptions != null) {
            return "snappy";
         }

         if (gzipQ != -1.0F && gzipQ >= deflateQ && this.gzipOptions != null) {
            return "gzip";
         }

         if (deflateQ != -1.0F && this.deflateOptions != null) {
            return "deflate";
         }
      }

      if (starQ > 0.0F) {
         if (brQ == -1.0F && this.brotliOptions != null) {
            return "br";
         }

         if (zstdQ == -1.0F && this.zstdOptions != null) {
            return "zstd";
         }

         if (snappyQ == -1.0F && this.snappyOptions != null) {
            return "snappy";
         }

         if (gzipQ == -1.0F && this.gzipOptions != null) {
            return "gzip";
         }

         if (deflateQ == -1.0F && this.deflateOptions != null) {
            return "deflate";
         }
      }

      return null;
   }

   /** @deprecated */
   @Deprecated
   protected ZlibWrapper determineWrapper(String acceptEncoding) {
      float starQ = -1.0F;
      float gzipQ = -1.0F;
      float deflateQ = -1.0F;

      for(String encoding : acceptEncoding.split(",")) {
         float q = 1.0F;
         int equalsPos = encoding.indexOf(61);
         if (equalsPos != -1) {
            try {
               q = Float.parseFloat(encoding.substring(equalsPos + 1));
            } catch (NumberFormatException var12) {
               q = 0.0F;
            }
         }

         if (encoding.contains("*")) {
            starQ = q;
         } else if (encoding.contains("gzip") && q > gzipQ) {
            gzipQ = q;
         } else if (encoding.contains("deflate") && q > deflateQ) {
            deflateQ = q;
         }
      }

      if (!(gzipQ > 0.0F) && !(deflateQ > 0.0F)) {
         if (starQ > 0.0F) {
            if (gzipQ == -1.0F) {
               return ZlibWrapper.GZIP;
            }

            if (deflateQ == -1.0F) {
               return ZlibWrapper.ZLIB;
            }
         }

         return null;
      } else if (gzipQ >= deflateQ) {
         return ZlibWrapper.GZIP;
      } else {
         return ZlibWrapper.ZLIB;
      }
   }

   private final class GzipEncoderFactory implements CompressionEncoderFactory {
      private GzipEncoderFactory() {
      }

      public MessageToByteEncoder createEncoder() {
         return ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP, HttpContentCompressor.this.gzipOptions.compressionLevel(), HttpContentCompressor.this.gzipOptions.windowBits(), HttpContentCompressor.this.gzipOptions.memLevel());
      }
   }

   private final class DeflateEncoderFactory implements CompressionEncoderFactory {
      private DeflateEncoderFactory() {
      }

      public MessageToByteEncoder createEncoder() {
         return ZlibCodecFactory.newZlibEncoder(ZlibWrapper.ZLIB, HttpContentCompressor.this.deflateOptions.compressionLevel(), HttpContentCompressor.this.deflateOptions.windowBits(), HttpContentCompressor.this.deflateOptions.memLevel());
      }
   }

   private final class BrEncoderFactory implements CompressionEncoderFactory {
      private BrEncoderFactory() {
      }

      public MessageToByteEncoder createEncoder() {
         return new BrotliEncoder(HttpContentCompressor.this.brotliOptions.parameters());
      }
   }

   private final class ZstdEncoderFactory implements CompressionEncoderFactory {
      private ZstdEncoderFactory() {
      }

      public MessageToByteEncoder createEncoder() {
         return new ZstdEncoder(HttpContentCompressor.this.zstdOptions.compressionLevel(), HttpContentCompressor.this.zstdOptions.blockSize(), HttpContentCompressor.this.zstdOptions.maxEncodeSize());
      }
   }

   private static final class SnappyEncoderFactory implements CompressionEncoderFactory {
      private SnappyEncoderFactory() {
      }

      public MessageToByteEncoder createEncoder() {
         return new SnappyFrameEncoder();
      }
   }
}
