package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoderFromBuffer;

public class LZMA2InputStream extends InputStream {
   public static final int DICT_SIZE_MIN = 4096;
   public static final int DICT_SIZE_MAX = 2147483632;
   private static final int COMPRESSED_SIZE_MAX = 65536;
   private final ArrayCache arrayCache;
   private DataInputStream in;
   private LZDecoder lz;
   private RangeDecoderFromBuffer rc;
   private LZMADecoder lzma;
   private int uncompressedSize;
   private boolean isLZMAChunk;
   private boolean needDictReset;
   private boolean needProps;
   private boolean endReached;
   private IOException exception;
   private final byte[] tempBuf;

   public static int getMemoryUsage(int dictSize) {
      return 104 + getDictSize(dictSize) / 1024;
   }

   private static int getDictSize(int dictSize) {
      if (dictSize >= 4096 && dictSize <= 2147483632) {
         return dictSize + 15 & -16;
      } else {
         throw new IllegalArgumentException("Unsupported dictionary size " + dictSize);
      }
   }

   public LZMA2InputStream(InputStream in, int dictSize) {
      this(in, dictSize, (byte[])null);
   }

   public LZMA2InputStream(InputStream in, int dictSize, byte[] presetDict) {
      this(in, dictSize, presetDict, ArrayCache.getDefaultCache());
   }

   LZMA2InputStream(InputStream in, int dictSize, byte[] presetDict, ArrayCache arrayCache) {
      this.uncompressedSize = 0;
      this.isLZMAChunk = false;
      this.needDictReset = true;
      this.needProps = true;
      this.endReached = false;
      this.exception = null;
      this.tempBuf = new byte[1];
      if (in == null) {
         throw new NullPointerException();
      } else {
         this.arrayCache = arrayCache;
         this.in = new DataInputStream(in);
         this.rc = new RangeDecoderFromBuffer(65536, arrayCache);
         this.lz = new LZDecoder(getDictSize(dictSize), presetDict, arrayCache);
         if (presetDict != null && presetDict.length > 0) {
            this.needDictReset = false;
         }

      }
   }

   public int read() throws IOException {
      return this.read(this.tempBuf, 0, 1) == -1 ? -1 : this.tempBuf[0] & 255;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (len == 0) {
            return 0;
         } else if (this.in == null) {
            throw new XZIOException("Stream closed");
         } else if (this.exception != null) {
            throw this.exception;
         } else if (this.endReached) {
            return -1;
         } else {
            try {
               int size = 0;

               while(len > 0) {
                  if (this.uncompressedSize == 0) {
                     this.decodeChunkHeader();
                     if (this.endReached) {
                        return size == 0 ? -1 : size;
                     }
                  }

                  int copySizeMax = Math.min(this.uncompressedSize, len);
                  if (!this.isLZMAChunk) {
                     this.lz.copyUncompressed(this.in, copySizeMax);
                  } else {
                     this.lz.setLimit(copySizeMax);
                     this.lzma.decode();
                  }

                  int copiedSize = this.lz.flush(buf, off);
                  off += copiedSize;
                  len -= copiedSize;
                  size += copiedSize;
                  this.uncompressedSize -= copiedSize;
                  if (this.uncompressedSize == 0 && (!this.rc.isFinished() || this.lz.hasPending())) {
                     throw new CorruptedInputException();
                  }
               }

               return size;
            } catch (IOException e) {
               this.exception = e;
               throw e;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private void decodeChunkHeader() throws IOException {
      int control = this.in.readUnsignedByte();
      if (control == 0) {
         this.endReached = true;
         this.putArraysToCache();
      } else {
         if (control < 224 && control != 1) {
            if (this.needDictReset) {
               throw new CorruptedInputException();
            }
         } else {
            this.needProps = true;
            this.needDictReset = false;
            this.lz.reset();
         }

         if (control >= 128) {
            this.isLZMAChunk = true;
            this.uncompressedSize = (control & 31) << 16;
            this.uncompressedSize += this.in.readUnsignedShort() + 1;
            int compressedSize = this.in.readUnsignedShort() + 1;
            if (control >= 192) {
               this.needProps = false;
               this.decodeProps();
            } else {
               if (this.needProps) {
                  throw new CorruptedInputException();
               }

               if (control >= 160) {
                  this.lzma.reset();
               }
            }

            this.rc.prepareInputBuffer(this.in, compressedSize);
         } else {
            if (control > 2) {
               throw new CorruptedInputException();
            }

            this.isLZMAChunk = false;
            this.uncompressedSize = this.in.readUnsignedShort() + 1;
         }

      }
   }

   private void decodeProps() throws IOException {
      int props = this.in.readUnsignedByte();
      if (props > 224) {
         throw new CorruptedInputException();
      } else {
         int pb = props / 45;
         props -= pb * 9 * 5;
         int lp = props / 9;
         int lc = props - lp * 9;
         if (lc + lp > 4) {
            throw new CorruptedInputException();
         } else {
            this.lzma = new LZMADecoder(this.lz, this.rc, lc, lp, pb);
         }
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return this.isLZMAChunk ? this.uncompressedSize : Math.min(this.uncompressedSize, this.in.available());
      }
   }

   private void putArraysToCache() {
      if (this.lz != null) {
         this.lz.putArraysToCache(this.arrayCache);
         this.lz = null;
         this.rc.putArraysToCache(this.arrayCache);
         this.rc = null;
      }

   }

   public void close() throws IOException {
      if (this.in != null) {
         this.putArraysToCache();

         try {
            this.in.close();
         } finally {
            this.in = null;
         }
      }

   }
}
