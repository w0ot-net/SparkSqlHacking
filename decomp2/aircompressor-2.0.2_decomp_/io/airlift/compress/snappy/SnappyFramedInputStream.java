package io.airlift.compress.snappy;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class SnappyFramedInputStream extends InputStream {
   private final SnappyDecompressor decompressor;
   private final InputStream in;
   private final byte[] frameHeader;
   private final boolean verifyChecksums;
   private byte[] input;
   private byte[] uncompressed;
   private boolean closed;
   private boolean eof;
   private int valid;
   private int position;
   private byte[] buffer;

   public SnappyFramedInputStream(InputStream in) throws IOException {
      this(in, true);
   }

   public SnappyFramedInputStream(InputStream in, boolean verifyChecksums) throws IOException {
      this.decompressor = new SnappyDecompressor();
      this.input = new byte[0];
      this.uncompressed = new byte[0];
      this.in = in;
      this.verifyChecksums = verifyChecksums;
      this.allocateBuffersBasedOnSize(65541);
      this.frameHeader = new byte[4];
      byte[] actualHeader = new byte[SnappyFramed.HEADER_BYTES.length];
      int read = SnappyInternalUtils.readBytes(in, actualHeader, 0, actualHeader.length);
      if (read < SnappyFramed.HEADER_BYTES.length) {
         throw new EOFException("encountered EOF while reading stream header");
      } else if (!Arrays.equals(SnappyFramed.HEADER_BYTES, actualHeader)) {
         throw new IOException("invalid stream header");
      }
   }

   public int read() throws IOException {
      if (this.closed) {
         return -1;
      } else {
         return !this.ensureBuffer() ? -1 : this.buffer[this.position++] & 255;
      }
   }

   public int read(byte[] output, int offset, int length) throws IOException {
      SnappyInternalUtils.checkNotNull(output, "output is null");
      SnappyInternalUtils.checkPositionIndexes(offset, offset + length, output.length);
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else if (length == 0) {
         return 0;
      } else if (!this.ensureBuffer()) {
         return -1;
      } else {
         int size = Math.min(length, this.available());
         System.arraycopy(this.buffer, this.position, output, offset, size);
         this.position += size;
         return size;
      }
   }

   public int available() throws IOException {
      return this.closed ? 0 : this.valid - this.position;
   }

   public void close() throws IOException {
      try {
         this.in.close();
      } finally {
         if (!this.closed) {
            this.closed = true;
         }

      }

   }

   private boolean ensureBuffer() throws IOException {
      if (this.available() > 0) {
         return true;
      } else if (this.eof) {
         return false;
      } else if (!this.readBlockHeader()) {
         this.eof = true;
         return false;
      } else {
         FrameMetaData frameMetaData = getFrameMetaData(this.frameHeader);
         if (SnappyFramedInputStream.FrameAction.SKIP == frameMetaData.frameAction) {
            SnappyInternalUtils.skip(this.in, frameMetaData.length);
            return this.ensureBuffer();
         } else {
            if (frameMetaData.length > this.input.length) {
               this.allocateBuffersBasedOnSize(frameMetaData.length);
            }

            int actualRead = SnappyInternalUtils.readBytes(this.in, this.input, 0, frameMetaData.length);
            if (actualRead != frameMetaData.length) {
               throw new EOFException("unexpectd EOF when reading frame");
            } else {
               FrameData frameData = getFrameData(this.input);
               if (SnappyFramedInputStream.FrameAction.UNCOMPRESS == frameMetaData.frameAction) {
                  int uncompressedLength = SnappyDecompressor.getUncompressedLength(this.input, frameData.offset);
                  if (uncompressedLength > this.uncompressed.length) {
                     this.uncompressed = new byte[uncompressedLength];
                  }

                  this.valid = this.decompressor.decompress(this.input, frameData.offset, actualRead - frameData.offset, this.uncompressed, 0, this.uncompressed.length);
                  this.buffer = this.uncompressed;
                  this.position = 0;
               } else {
                  this.position = frameData.offset;
                  this.buffer = this.input;
                  this.valid = actualRead;
               }

               if (this.verifyChecksums) {
                  int actualCrc32c = Crc32C.maskedCrc32c(this.buffer, this.position, this.valid - this.position);
                  if (frameData.checkSum != actualCrc32c) {
                     throw new IOException("Corrupt input: invalid checksum");
                  }
               }

               return true;
            }
         }
      }
   }

   private void allocateBuffersBasedOnSize(int size) {
      if (this.input.length < size) {
         this.input = new byte[size];
      }

      if (this.uncompressed.length < size) {
         this.uncompressed = new byte[size];
      }

   }

   private static FrameMetaData getFrameMetaData(byte[] frameHeader) throws IOException {
      int length = frameHeader[1] & 255;
      length |= (frameHeader[2] & 255) << 8;
      length |= (frameHeader[3] & 255) << 16;
      int flag = frameHeader[0] & 255;
      int minLength;
      FrameAction frameAction;
      switch (flag) {
         case 0:
            frameAction = SnappyFramedInputStream.FrameAction.UNCOMPRESS;
            minLength = 5;
            break;
         case 1:
            frameAction = SnappyFramedInputStream.FrameAction.RAW;
            minLength = 5;
            break;
         case 255:
            if (length != 6) {
               throw new IOException("stream identifier chunk with invalid length: " + length);
            }

            frameAction = SnappyFramedInputStream.FrameAction.SKIP;
            minLength = 6;
            break;
         default:
            if (flag <= 127) {
               throw new IOException("unsupported unskippable chunk: " + Integer.toHexString(flag));
            }

            frameAction = SnappyFramedInputStream.FrameAction.SKIP;
            minLength = 0;
      }

      if (length < minLength) {
         throw new IOException("invalid length: " + length + " for chunk flag: " + Integer.toHexString(flag));
      } else {
         return new FrameMetaData(frameAction, length);
      }
   }

   private static FrameData getFrameData(byte[] content) {
      int crc32c = (content[3] & 255) << 24 | (content[2] & 255) << 16 | (content[1] & 255) << 8 | content[0] & 255;
      return new FrameData(crc32c, 4);
   }

   private boolean readBlockHeader() throws IOException {
      int read = SnappyInternalUtils.readBytes(this.in, this.frameHeader, 0, this.frameHeader.length);
      if (read == -1) {
         return false;
      } else if (read < this.frameHeader.length) {
         throw new EOFException("encountered EOF while reading block header");
      } else {
         return true;
      }
   }

   static enum FrameAction {
      RAW,
      SKIP,
      UNCOMPRESS;
   }

   public static final class FrameMetaData {
      final int length;
      final FrameAction frameAction;

      public FrameMetaData(FrameAction frameAction, int length) {
         this.frameAction = frameAction;
         this.length = length;
      }
   }

   public static final class FrameData {
      final int checkSum;
      final int offset;

      public FrameData(int checkSum, int offset) {
         this.checkSum = checkSum;
         this.offset = offset;
      }
   }
}
