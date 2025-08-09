package org.apache.arrow.vector.compression;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ipc.message.ArrowBodyCompression;

public class NoCompressionCodec implements CompressionCodec {
   public static final NoCompressionCodec INSTANCE = new NoCompressionCodec();
   public static final byte COMPRESSION_TYPE = -1;
   public static final ArrowBodyCompression DEFAULT_BODY_COMPRESSION = new ArrowBodyCompression((byte)-1, (byte)0);

   private NoCompressionCodec() {
   }

   public ArrowBuf compress(BufferAllocator allocator, ArrowBuf uncompressedBuffer) {
      return uncompressedBuffer;
   }

   public ArrowBuf decompress(BufferAllocator allocator, ArrowBuf compressedBuffer) {
      return compressedBuffer;
   }

   public CompressionUtil.CodecType getCodecType() {
      return CompressionUtil.CodecType.NO_COMPRESSION;
   }

   public static class Factory implements CompressionCodec.Factory {
      public static final Factory INSTANCE = new Factory();

      public CompressionCodec createCodec(CompressionUtil.CodecType codecType) {
         switch (codecType) {
            case NO_COMPRESSION:
               return NoCompressionCodec.INSTANCE;
            case LZ4_FRAME:
            case ZSTD:
               throw new IllegalArgumentException("Please add arrow-compression module to use CommonsCompressionFactory for " + String.valueOf(codecType));
            default:
               throw new IllegalArgumentException("Unsupported codec type: " + String.valueOf(codecType));
         }
      }

      public CompressionCodec createCodec(CompressionUtil.CodecType codecType, int compressionLevel) {
         return this.createCodec(codecType);
      }
   }
}
