package org.apache.arrow.vector.compression;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.util.Preconditions;

public abstract class AbstractCompressionCodec implements CompressionCodec {
   public ArrowBuf compress(BufferAllocator allocator, ArrowBuf uncompressedBuffer) {
      if (uncompressedBuffer.writerIndex() == 0L) {
         ArrowBuf compressedBuffer = allocator.buffer(8L);
         compressedBuffer.setLong(0L, 0L);
         compressedBuffer.writerIndex(8L);
         uncompressedBuffer.close();
         return compressedBuffer;
      } else {
         ArrowBuf compressedBuffer = this.doCompress(allocator, uncompressedBuffer);
         long compressedLength = compressedBuffer.writerIndex() - 8L;
         long uncompressedLength = uncompressedBuffer.writerIndex();
         if (compressedLength > uncompressedLength) {
            compressedBuffer.close();
            compressedBuffer = CompressionUtil.packageRawBuffer(allocator, uncompressedBuffer);
         } else {
            this.writeUncompressedLength(compressedBuffer, uncompressedLength);
         }

         uncompressedBuffer.close();
         return compressedBuffer;
      }
   }

   public ArrowBuf decompress(BufferAllocator allocator, ArrowBuf compressedBuffer) {
      Preconditions.checkArgument(compressedBuffer.writerIndex() >= 8L, "Not enough data to decompress.");
      long decompressedLength = this.readUncompressedLength(compressedBuffer);
      if (decompressedLength == 0L) {
         compressedBuffer.close();
         return allocator.getEmpty();
      } else if (decompressedLength == -1L) {
         return CompressionUtil.extractUncompressedBuffer(compressedBuffer);
      } else {
         ArrowBuf decompressedBuffer = this.doDecompress(allocator, compressedBuffer);
         compressedBuffer.close();
         return decompressedBuffer;
      }
   }

   protected void writeUncompressedLength(ArrowBuf compressedBuffer, long uncompressedLength) {
      if (!MemoryUtil.LITTLE_ENDIAN) {
         uncompressedLength = Long.reverseBytes(uncompressedLength);
      }

      compressedBuffer.setLong(0L, uncompressedLength);
   }

   protected long readUncompressedLength(ArrowBuf compressedBuffer) {
      long decompressedLength = compressedBuffer.getLong(0L);
      if (!MemoryUtil.LITTLE_ENDIAN) {
         decompressedLength = Long.reverseBytes(decompressedLength);
      }

      return decompressedLength;
   }

   protected abstract ArrowBuf doCompress(BufferAllocator var1, ArrowBuf var2);

   protected abstract ArrowBuf doDecompress(BufferAllocator var1, ArrowBuf var2);
}
