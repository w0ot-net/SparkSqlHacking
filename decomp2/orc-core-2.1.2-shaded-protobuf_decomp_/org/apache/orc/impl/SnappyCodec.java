package org.apache.orc.impl;

import io.airlift.compress.snappy.SnappyCompressor;
import io.airlift.compress.snappy.SnappyDecompressor;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.CompressionKind;
import org.apache.orc.impl.HadoopShims.DirectCompressionType;

public class SnappyCodec extends AircompressorCodec implements DirectDecompressionCodec {
   private static final HadoopShims SHIMS = HadoopShimsFactory.get();
   Boolean direct = null;
   HadoopShims.DirectDecompressor decompressShim = null;

   SnappyCodec() {
      super(CompressionKind.SNAPPY, new SnappyCompressor(), new SnappyDecompressor());
   }

   public void decompress(ByteBuffer in, ByteBuffer out) throws IOException {
      if (in.isDirect() && out.isDirect()) {
         this.directDecompress(in, out);
      } else {
         super.decompress(in, out);
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

   public void directDecompress(ByteBuffer in, ByteBuffer out) throws IOException {
      this.ensureShim();
      this.decompressShim.decompress(in, out);
      out.flip();
   }

   private void ensureShim() {
      if (this.decompressShim == null) {
         this.decompressShim = SHIMS.getDirectDecompressor(DirectCompressionType.SNAPPY);
      }

   }

   public void reset() {
      super.reset();
      if (this.decompressShim != null) {
         this.decompressShim.reset();
      }

   }

   public void destroy() {
      super.destroy();
      if (this.decompressShim != null) {
         this.decompressShim.end();
      }

   }
}
