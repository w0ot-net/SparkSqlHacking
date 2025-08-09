package org.apache.arrow.vector.compression;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ipc.message.ArrowBodyCompression;

public class CompressionUtil {
   public static final long SIZE_OF_UNCOMPRESSED_LENGTH = 8L;
   public static final long NO_COMPRESSION_LENGTH = -1L;

   private CompressionUtil() {
   }

   public static ArrowBodyCompression createBodyCompression(CompressionCodec codec) {
      return new ArrowBodyCompression(codec.getCodecType().getType(), (byte)0);
   }

   public static ArrowBuf packageRawBuffer(BufferAllocator allocator, ArrowBuf inputBuffer) {
      ArrowBuf compressedBuffer = allocator.buffer(8L + inputBuffer.writerIndex());
      compressedBuffer.setLong(0L, -1L);
      compressedBuffer.setBytes(8L, inputBuffer, 0L, inputBuffer.writerIndex());
      compressedBuffer.writerIndex(8L + inputBuffer.writerIndex());
      return compressedBuffer;
   }

   public static ArrowBuf extractUncompressedBuffer(ArrowBuf inputBuffer) {
      return inputBuffer.slice(8L, inputBuffer.writerIndex() - 8L);
   }

   public static enum CodecType {
      NO_COMPRESSION((byte)-1),
      LZ4_FRAME((byte)0),
      ZSTD((byte)1);

      private final byte type;

      private CodecType(byte type) {
         this.type = type;
      }

      public byte getType() {
         return this.type;
      }

      public static CodecType fromCompressionType(byte type) {
         for(CodecType codecType : values()) {
            if (codecType.type == type) {
               return codecType;
            }
         }

         return NO_COMPRESSION;
      }

      // $FF: synthetic method
      private static CodecType[] $values() {
         return new CodecType[]{NO_COMPRESSION, LZ4_FRAME, ZSTD};
      }
   }
}
