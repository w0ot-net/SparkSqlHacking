package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.compression.DeflateCompressionAlgorithm;
import io.jsonwebtoken.impl.compression.GzipCompressionAlgorithm;
import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.lang.Collections;

public final class StandardCompressionAlgorithms extends IdRegistry {
   public static final String NAME = "Compression Algorithm";

   public StandardCompressionAlgorithms() {
      super("Compression Algorithm", Collections.of(new CompressionAlgorithm[]{new DeflateCompressionAlgorithm(), new GzipCompressionAlgorithm()}));
   }
}
