package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.compress.snappy.SnappyDecompressor;

class SnappyDirectDecompressWrapper implements HadoopShims.DirectDecompressor {
   private final SnappyDecompressor.SnappyDirectDecompressor root;
   private boolean isFirstCall = true;

   SnappyDirectDecompressWrapper(SnappyDecompressor.SnappyDirectDecompressor root) {
      this.root = root;
   }

   public void decompress(ByteBuffer input, ByteBuffer output) throws IOException {
      if (!this.isFirstCall) {
         this.root.reset();
      } else {
         this.isFirstCall = false;
      }

      this.root.decompress(input, output);
   }

   public void reset() {
      this.root.reset();
   }

   public void end() {
      this.root.end();
   }
}
