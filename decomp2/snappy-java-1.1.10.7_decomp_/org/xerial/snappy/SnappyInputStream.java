package org.xerial.snappy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SnappyInputStream extends InputStream {
   public static final int MAX_CHUNK_SIZE = 536870912;
   private boolean finishedReading;
   protected final InputStream in;
   private final int maxChunkSize;
   private byte[] compressed;
   private byte[] uncompressed;
   private int uncompressedCursor;
   private int uncompressedLimit;
   private byte[] header;

   public SnappyInputStream(InputStream var1) throws IOException {
      this(var1, 536870912);
   }

   public SnappyInputStream(InputStream var1, int var2) throws IOException {
      this.finishedReading = false;
      this.uncompressedCursor = 0;
      this.uncompressedLimit = 0;
      this.header = new byte[SnappyCodec.headerSize()];
      this.maxChunkSize = var2;
      this.in = var1;
      this.readHeader();
   }

   public void close() throws IOException {
      this.compressed = null;
      this.uncompressed = null;
      if (this.in != null) {
         this.in.close();
      }

   }

   protected void readHeader() throws IOException {
      int var1;
      int var2;
      for(var1 = 0; var1 < this.header.length; var1 += var2) {
         var2 = this.in.read(this.header, var1, this.header.length - var1);
         if (var2 == -1) {
            break;
         }
      }

      if (var1 == 0) {
         throw new SnappyIOException(SnappyErrorCode.EMPTY_INPUT, "Cannot decompress empty stream");
      } else if (var1 < this.header.length || !SnappyCodec.hasMagicHeaderPrefix(this.header)) {
         this.readFully(this.header, var1);
      }
   }

   private static boolean isValidHeader(byte[] var0) throws IOException {
      SnappyCodec var1 = SnappyCodec.readHeader(new ByteArrayInputStream(var0));
      if (var1.isValidMagicHeader()) {
         if (var1.version < 1) {
            throw new SnappyIOException(SnappyErrorCode.INCOMPATIBLE_VERSION, String.format("Compressed with an incompatible codec version %d. At least version %d is required", var1.version, 1));
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected void readFully(byte[] var1, int var2) throws IOException {
      if (var2 == 0) {
         this.finishedReading = true;
      } else {
         this.compressed = new byte[Math.max(8192, var2)];
         System.arraycopy(var1, 0, this.compressed, 0, var2);
         int var3 = var2;
         int var4 = 0;

         while((var4 = this.in.read(this.compressed, var3, this.compressed.length - var3)) != -1) {
            var3 += var4;
            if (var3 >= this.compressed.length) {
               byte[] var5 = new byte[this.compressed.length * 2];
               System.arraycopy(this.compressed, 0, var5, 0, this.compressed.length);
               this.compressed = var5;
            }
         }

         this.finishedReading = true;
         var4 = Snappy.uncompressedLength(this.compressed, 0, var3);
         this.uncompressed = new byte[var4];
         Snappy.uncompress(this.compressed, 0, var3, this.uncompressed, 0);
         this.uncompressedCursor = 0;
         this.uncompressedLimit = var4;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      int var4 = 0;

      while(var4 < var3) {
         if (this.uncompressedCursor >= this.uncompressedLimit) {
            if (!this.hasNextChunk()) {
               return var4 == 0 ? -1 : var4;
            }
         } else {
            int var5 = Math.min(this.uncompressedLimit - this.uncompressedCursor, var3 - var4);
            System.arraycopy(this.uncompressed, this.uncompressedCursor, var1, var2 + var4, var5);
            var4 += var5;
            this.uncompressedCursor += var5;
         }
      }

      return var4;
   }

   public int rawRead(Object var1, int var2, int var3) throws IOException {
      int var4 = 0;

      while(var4 < var3) {
         if (this.uncompressedCursor >= this.uncompressedLimit) {
            if (!this.hasNextChunk()) {
               return var4 == 0 ? -1 : var4;
            }
         } else {
            int var5 = Math.min(this.uncompressedLimit - this.uncompressedCursor, var3 - var4);
            Snappy.arrayCopy(this.uncompressed, this.uncompressedCursor, var5, var1, var2 + var4);
            var4 += var5;
            this.uncompressedCursor += var5;
         }
      }

      return var4;
   }

   public int read(long[] var1, int var2, int var3) throws IOException {
      return this.rawRead(var1, var2 * 8, var3 * 8);
   }

   public int read(long[] var1) throws IOException {
      return this.read((long[])var1, 0, var1.length);
   }

   public int read(double[] var1, int var2, int var3) throws IOException {
      return this.rawRead(var1, var2 * 8, var3 * 8);
   }

   public int read(double[] var1) throws IOException {
      return this.read((double[])var1, 0, var1.length);
   }

   public int read(int[] var1) throws IOException {
      return this.read((int[])var1, 0, var1.length);
   }

   public int read(int[] var1, int var2, int var3) throws IOException {
      return this.rawRead(var1, var2 * 4, var3 * 4);
   }

   public int read(float[] var1, int var2, int var3) throws IOException {
      return this.rawRead(var1, var2 * 4, var3 * 4);
   }

   public int read(float[] var1) throws IOException {
      return this.read((float[])var1, 0, var1.length);
   }

   public int read(short[] var1, int var2, int var3) throws IOException {
      return this.rawRead(var1, var2 * 2, var3 * 2);
   }

   public int read(short[] var1) throws IOException {
      return this.read((short[])var1, 0, var1.length);
   }

   private int readNext(byte[] var1, int var2, int var3) throws IOException {
      int var4;
      int var5;
      for(var4 = 0; var4 < var3; var4 += var5) {
         var5 = this.in.read(var1, var4 + var2, var3 - var4);
         if (var5 == -1) {
            this.finishedReading = true;
            return var4;
         }
      }

      return var4;
   }

   protected boolean hasNextChunk() throws IOException {
      if (this.finishedReading) {
         return false;
      } else {
         this.uncompressedCursor = 0;
         this.uncompressedLimit = 0;
         int var1 = this.readNext(this.header, 0, 4);
         if (var1 < 4) {
            return false;
         } else {
            int var2 = SnappyOutputStream.readInt(this.header, 0);
            if (var2 == SnappyCodec.MAGIC_HEADER_HEAD) {
               int var9 = SnappyCodec.headerSize() - 4;
               var1 = this.readNext(this.header, 4, var9);
               if (var1 < var9) {
                  throw new SnappyIOException(SnappyErrorCode.FAILED_TO_UNCOMPRESS, String.format("Insufficient header size in a concatenated block"));
               } else {
                  return isValidHeader(this.header) ? this.hasNextChunk() : false;
               }
            } else if (var2 < 0) {
               throw new SnappyError(SnappyErrorCode.INVALID_CHUNK_SIZE, "chunkSize is too big or negative : " + var2);
            } else if (var2 > this.maxChunkSize) {
               throw new SnappyError(SnappyErrorCode.FAILED_TO_UNCOMPRESS, String.format("Received chunkSize %,d is greater than max configured chunk size %,d", var2, this.maxChunkSize));
            } else {
               if (this.compressed == null || var2 > this.compressed.length) {
                  try {
                     this.compressed = new byte[var2];
                  } catch (OutOfMemoryError var5) {
                     throw new SnappyError(SnappyErrorCode.INVALID_CHUNK_SIZE, var5.getMessage());
                  }
               }

               int var3;
               for(var1 = 0; var1 < var2; var1 += var3) {
                  var3 = this.in.read(this.compressed, var1, var2 - var1);
                  if (var3 == -1) {
                     break;
                  }
               }

               if (var1 < var2) {
                  throw new IOException("failed to read chunk");
               } else {
                  var3 = Snappy.uncompressedLength(this.compressed, 0, var2);
                  if (this.uncompressed == null || var3 > this.uncompressed.length) {
                     this.uncompressed = new byte[var3];
                  }

                  int var4 = Snappy.uncompress(this.compressed, 0, var2, this.uncompressed, 0);
                  if (var3 != var4) {
                     throw new SnappyIOException(SnappyErrorCode.INVALID_CHUNK_SIZE, String.format("expected %,d bytes, but decompressed chunk has %,d bytes", var3, var4));
                  } else {
                     this.uncompressedLimit = var4;
                     return true;
                  }
               }
            }
         }
      }
   }

   public int read() throws IOException {
      if (this.uncompressedCursor < this.uncompressedLimit) {
         return this.uncompressed[this.uncompressedCursor++] & 255;
      } else {
         return this.hasNextChunk() ? this.read() : -1;
      }
   }

   public int available() throws IOException {
      if (this.uncompressedCursor < this.uncompressedLimit) {
         return this.uncompressedLimit - this.uncompressedCursor;
      } else {
         return this.hasNextChunk() ? this.uncompressedLimit - this.uncompressedCursor : 0;
      }
   }
}
