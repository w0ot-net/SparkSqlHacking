package io.airlift.compress.snappy;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class SnappyHadoopInputStream extends HadoopInputStream {
   private final SnappyDecompressor decompressor = new SnappyDecompressor();
   private final InputStream in;
   private int uncompressedBlockLength;
   private byte[] uncompressedChunk = new byte[0];
   private int uncompressedChunkOffset;
   private int uncompressedChunkLength;
   private byte[] compressed = new byte[0];

   public SnappyHadoopInputStream(InputStream in) {
      this.in = in;
   }

   public int read() throws IOException {
      if (this.uncompressedChunkOffset >= this.uncompressedChunkLength) {
         this.readNextChunk(this.uncompressedChunk, 0, this.uncompressedChunk.length);
         if (this.uncompressedChunkLength == 0) {
            return -1;
         }
      }

      return this.uncompressedChunk[this.uncompressedChunkOffset++] & 255;
   }

   public int read(byte[] output, int offset, int length) throws IOException {
      if (this.uncompressedChunkOffset >= this.uncompressedChunkLength) {
         boolean directDecompress = this.readNextChunk(output, offset, length);
         if (this.uncompressedChunkLength == 0) {
            return -1;
         }

         if (directDecompress) {
            this.uncompressedChunkOffset += this.uncompressedChunkLength;
            return this.uncompressedChunkLength;
         }
      }

      int size = Math.min(length, this.uncompressedChunkLength - this.uncompressedChunkOffset);
      System.arraycopy(this.uncompressedChunk, this.uncompressedChunkOffset, output, offset, size);
      this.uncompressedChunkOffset += size;
      return size;
   }

   public void resetState() {
      this.uncompressedBlockLength = 0;
      this.uncompressedChunkOffset = 0;
      this.uncompressedChunkLength = 0;
   }

   public void close() throws IOException {
      this.in.close();
   }

   private boolean readNextChunk(byte[] userBuffer, int userOffset, int userLength) throws IOException {
      this.uncompressedBlockLength -= this.uncompressedChunkOffset;
      this.uncompressedChunkOffset = 0;
      this.uncompressedChunkLength = 0;

      while(this.uncompressedBlockLength == 0) {
         this.uncompressedBlockLength = this.readBigEndianInt();
         if (this.uncompressedBlockLength == -1) {
            this.uncompressedBlockLength = 0;
            return false;
         }
      }

      int compressedChunkLength = this.readBigEndianInt();
      if (compressedChunkLength == -1) {
         return false;
      } else {
         if (this.compressed.length < compressedChunkLength) {
            this.compressed = new byte[compressedChunkLength + 8];
         }

         this.readInput(compressedChunkLength, this.compressed);
         this.uncompressedChunkLength = SnappyDecompressor.getUncompressedLength(this.compressed, 0);
         if (this.uncompressedChunkLength > this.uncompressedBlockLength) {
            throw new IOException("Chunk uncompressed size is greater than block size");
         } else {
            boolean directUncompress = true;
            if (this.uncompressedChunkLength > userLength) {
               if (this.uncompressedChunk.length < this.uncompressedChunkLength) {
                  this.uncompressedChunk = new byte[this.uncompressedChunkLength + 8];
               }

               directUncompress = false;
               userBuffer = this.uncompressedChunk;
               userOffset = 0;
               userLength = this.uncompressedChunk.length;
            }

            int bytes = this.decompressor.decompress(this.compressed, 0, compressedChunkLength, userBuffer, userOffset, userLength);
            if (this.uncompressedChunkLength != bytes) {
               throw new IOException("Expected to read " + this.uncompressedChunkLength + " bytes, but data only contained " + bytes + " bytes");
            } else {
               return directUncompress;
            }
         }
      }
   }

   private void readInput(int length, byte[] buffer) throws IOException {
      int size;
      for(int offset = 0; offset < length; offset += size) {
         size = this.in.read(buffer, offset, length - offset);
         if (size == -1) {
            throw new EOFException("encountered EOF while reading block data");
         }
      }

   }

   private int readBigEndianInt() throws IOException {
      int b1 = this.in.read();
      if (b1 < 0) {
         return -1;
      } else {
         int b2 = this.in.read();
         int b3 = this.in.read();
         int b4 = this.in.read();
         if ((b2 | b3 | b4) < 0) {
            throw new IOException("Stream is truncated");
         } else {
            return (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
         }
      }
   }
}
