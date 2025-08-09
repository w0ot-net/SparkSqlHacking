package io.netty.handler.codec.compression;

final class ZstdConstants {
   static final int DEFAULT_COMPRESSION_LEVEL = com.github.luben.zstd.Zstd.defaultCompressionLevel();
   static final int MIN_COMPRESSION_LEVEL = com.github.luben.zstd.Zstd.minCompressionLevel();
   static final int MAX_COMPRESSION_LEVEL = com.github.luben.zstd.Zstd.maxCompressionLevel();
   static final int MAX_BLOCK_SIZE;
   static final int DEFAULT_BLOCK_SIZE = 65536;

   private ZstdConstants() {
   }

   static {
      MAX_BLOCK_SIZE = 1 << DEFAULT_COMPRESSION_LEVEL + 7 + 15;
   }
}
