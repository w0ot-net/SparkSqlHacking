package io.jsonwebtoken.impl.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompressionAlgorithm extends AbstractCompressionAlgorithm {
   private static final String ID = "GZIP";

   public GzipCompressionAlgorithm() {
      super("GZIP");
   }

   protected OutputStream doCompress(OutputStream out) throws IOException {
      return new GZIPOutputStream(out);
   }

   protected InputStream doDecompress(InputStream is) throws IOException {
      return new GZIPInputStream(is);
   }
}
