package org.apache.orc;

public enum CompressionKind {
   NONE,
   ZLIB,
   SNAPPY,
   LZO,
   LZ4,
   ZSTD,
   BROTLI;

   // $FF: synthetic method
   private static CompressionKind[] $values() {
      return new CompressionKind[]{NONE, ZLIB, SNAPPY, LZO, LZ4, ZSTD, BROTLI};
   }
}
