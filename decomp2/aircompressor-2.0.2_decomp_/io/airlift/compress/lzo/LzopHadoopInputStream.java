package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

class LzopHadoopInputStream extends HadoopInputStream {
   private static final int LZO_VERSION_MAX = 8352;
   private static final int LZOP_FILE_VERSION_MIN = 2368;
   private static final int LZOP_FORMAT_VERSION_MAX = 4112;
   private static final int LZOP_FLAG_ADLER32_DECOMPRESSED = 1;
   private static final int LZOP_FLAG_ADLER32_COMPRESSED = 2;
   private static final int LZOP_FLAG_CRC32_DECOMPRESSED = 256;
   private static final int LZOP_FLAG_CRC32_COMPRESSED = 512;
   private static final int LZOP_FLAG_CRC32_HEADER = 4096;
   private static final int LZOP_FLAG_IO_MASK = 12;
   private static final int LZOP_FLAG_OPERATING_SYSTEM_MASK = -16777216;
   private static final int LZOP_FLAG_CHARACTER_SET_MASK = 15728640;
   private final LzoDecompressor decompressor = new LzoDecompressor();
   private final InputStream in;
   private final byte[] uncompressedChunk;
   private int uncompressedLength;
   private int uncompressedOffset;
   private boolean finished;
   private byte[] compressed = new byte[0];
   private final boolean adler32Decompressed;
   private final boolean adler32Compressed;
   private final boolean crc32Decompressed;
   private final boolean crc32Compressed;

   public LzopHadoopInputStream(InputStream in, int maxUncompressedLength) throws IOException {
      this.in = (InputStream)Objects.requireNonNull(in, "in is null");
      this.uncompressedChunk = new byte[maxUncompressedLength + 8];
      byte[] magic = new byte[LzoConstants.LZOP_MAGIC.length];
      this.readInput(magic, 0, magic.length);
      if (!Arrays.equals(magic, LzoConstants.LZOP_MAGIC)) {
         throw new IOException("Not an LZOP file");
      } else {
         byte[] header = new byte[25];
         this.readInput(header, 0, header.length);
         ByteArrayInputStream headerStream = new ByteArrayInputStream(header);
         int lzopFileVersion = readBigEndianShort(headerStream);
         if (lzopFileVersion < 2368) {
            throw new IOException(String.format("Unsupported LZOP file version 0x%08X", lzopFileVersion));
         } else {
            int lzoVersion = readBigEndianShort(headerStream);
            if (lzoVersion > 8352) {
               throw new IOException(String.format("Unsupported LZO version 0x%08X", lzoVersion));
            } else {
               int lzopFormatVersion = readBigEndianShort(headerStream);
               if (lzopFormatVersion > 4112) {
                  throw new IOException(String.format("Unsupported LZOP format version 0x%08X", lzopFormatVersion));
               } else {
                  int variant = headerStream.read();
                  if (variant != 1) {
                     throw new IOException(String.format("Unsupported LZO variant %s", variant));
                  } else {
                     headerStream.read();
                     int flags = readBigEndianInt(headerStream);
                     flags &= -13;
                     flags &= 16777215;
                     flags &= -15728641;
                     this.adler32Decompressed = (flags & 1) != 0;
                     this.adler32Compressed = (flags & 2) != 0;
                     this.crc32Decompressed = (flags & 256) != 0;
                     this.crc32Compressed = (flags & 512) != 0;
                     boolean crc32Header = (flags & 4096) != 0;
                     flags &= -2;
                     flags &= -3;
                     flags &= -257;
                     flags &= -513;
                     flags &= -4097;
                     if (flags != 0) {
                        throw new IOException(String.format("Unsupported LZO flags 0x%08X", flags));
                     } else {
                        readBigEndianInt(headerStream);
                        readBigEndianInt(headerStream);
                        readBigEndianInt(headerStream);
                        int fileNameLength = headerStream.read();
                        byte[] fileName = new byte[fileNameLength];
                        this.readInput(fileName, 0, fileName.length);
                        int headerChecksumValue = readBigEndianInt(in);
                        Checksum headerChecksum = (Checksum)(crc32Header ? new CRC32() : new Adler32());
                        headerChecksum.update(header, 0, header.length);
                        headerChecksum.update(fileName, 0, fileName.length);
                        if (headerChecksumValue != (int)headerChecksum.getValue()) {
                           throw new IOException("Invalid header checksum");
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public int read() throws IOException {
      if (this.finished) {
         return -1;
      } else {
         while(this.uncompressedOffset >= this.uncompressedLength) {
            int compressedLength = this.bufferCompressedData();
            if (this.finished) {
               return -1;
            }

            this.decompress(compressedLength, this.uncompressedChunk, 0, this.uncompressedChunk.length);
         }

         return this.uncompressedChunk[this.uncompressedOffset++] & 255;
      }
   }

   public int read(byte[] output, int offset, int length) throws IOException {
      if (this.finished) {
         return -1;
      } else {
         while(this.uncompressedOffset >= this.uncompressedLength) {
            int compressedLength = this.bufferCompressedData();
            if (this.finished) {
               return -1;
            }

            if (length >= this.uncompressedLength) {
               this.decompress(compressedLength, output, offset, length);
               this.uncompressedOffset = this.uncompressedLength;
               return this.uncompressedLength;
            }

            this.decompress(compressedLength, this.uncompressedChunk, 0, this.uncompressedChunk.length);
         }

         int size = Math.min(length, this.uncompressedLength - this.uncompressedOffset);
         System.arraycopy(this.uncompressedChunk, this.uncompressedOffset, output, offset, size);
         this.uncompressedOffset += size;
         return size;
      }
   }

   public void resetState() {
      this.uncompressedLength = 0;
      this.uncompressedOffset = 0;
      this.finished = false;
   }

   public void close() throws IOException {
      this.in.close();
   }

   private int bufferCompressedData() throws IOException {
      this.uncompressedOffset = 0;
      this.uncompressedLength = readBigEndianInt(this.in);
      if (this.uncompressedLength == -1) {
         throw new EOFException("encountered EOF while reading block data");
      } else if (this.uncompressedLength == 0) {
         this.finished = true;
         return -1;
      } else {
         int compressedLength = readBigEndianInt(this.in);
         if (compressedLength == -1) {
            throw new EOFException("encountered EOF while reading block data");
         } else {
            this.skipChecksums(compressedLength < this.uncompressedLength);
            return compressedLength;
         }
      }
   }

   private void skipChecksums(boolean compressed) throws IOException {
      if (this.adler32Decompressed) {
         readBigEndianInt(this.in);
      }

      if (this.crc32Decompressed) {
         readBigEndianInt(this.in);
      }

      if (compressed && this.adler32Compressed) {
         readBigEndianInt(this.in);
      }

      if (compressed && this.crc32Compressed) {
         readBigEndianInt(this.in);
      }

   }

   private void decompress(int compressedLength, byte[] output, int outputOffset, int outputLength) throws IOException {
      if (this.uncompressedLength == compressedLength) {
         this.readInput(output, outputOffset, compressedLength);
      } else {
         if (this.compressed.length < compressedLength) {
            this.compressed = new byte[compressedLength + 8];
         }

         this.readInput(this.compressed, 0, compressedLength);
         int actualUncompressedLength = this.decompressor.decompress(this.compressed, 0, compressedLength, output, outputOffset, outputLength);
         if (actualUncompressedLength != this.uncompressedLength) {
            throw new IOException("Decompressor did not decompress the entire block");
         }
      }

   }

   private void readInput(byte[] buffer, int offset, int length) throws IOException {
      while(length > 0) {
         int size = this.in.read(buffer, offset, length);
         if (size == -1) {
            throw new EOFException("encountered EOF while reading block data");
         }

         offset += size;
         length -= size;
      }

   }

   private static int readBigEndianShort(InputStream in) throws IOException {
      int b1 = in.read();
      if (b1 < 0) {
         return -1;
      } else {
         int b2 = in.read();
         if (b2 < 0) {
            throw new IOException("Stream is truncated");
         } else {
            return (b1 << 8) + b2;
         }
      }
   }

   private static int readBigEndianInt(InputStream in) throws IOException {
      int b1 = in.read();
      if (b1 < 0) {
         return -1;
      } else {
         int b2 = in.read();
         int b3 = in.read();
         int b4 = in.read();
         if ((b2 | b3 | b4) < 0) {
            throw new IOException("Stream is truncated");
         } else {
            return (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
         }
      }
   }
}
