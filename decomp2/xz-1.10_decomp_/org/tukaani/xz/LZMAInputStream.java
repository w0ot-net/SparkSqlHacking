package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoderFromStream;

public class LZMAInputStream extends InputStream {
   public static final int DICT_SIZE_MAX = 2147483632;
   private InputStream in;
   private ArrayCache arrayCache;
   private LZDecoder lz;
   private RangeDecoderFromStream rc;
   private LZMADecoder lzma;
   private boolean endReached;
   private boolean relaxedEndCondition;
   private final byte[] tempBuf;
   private long remainingSize;
   private IOException exception;

   public static int getMemoryUsage(int dictSize, byte propsByte) throws UnsupportedOptionsException, CorruptedInputException {
      if (dictSize >= 0 && dictSize <= 2147483632) {
         int props = propsByte & 255;
         if (props > 224) {
            throw new CorruptedInputException("Invalid LZMA properties byte");
         } else {
            props %= 45;
            int lp = props / 9;
            int lc = props - lp * 9;
            return getMemoryUsage(dictSize, lc, lp);
         }
      } else {
         throw new UnsupportedOptionsException("LZMA dictionary is too big for this implementation");
      }
   }

   public static int getMemoryUsage(int dictSize, int lc, int lp) {
      if (lc >= 0 && lc <= 8 && lp >= 0 && lp <= 4) {
         return 10 + getDictSize(dictSize) / 1024 + (1536 << lc + lp) / 1024;
      } else {
         throw new IllegalArgumentException("Invalid lc or lp");
      }
   }

   private static int getDictSize(int dictSize) {
      if (dictSize >= 0 && dictSize <= 2147483632) {
         if (dictSize < 4096) {
            dictSize = 4096;
         }

         return dictSize + 15 & -16;
      } else {
         throw new IllegalArgumentException("LZMA dictionary is too big for this implementation");
      }
   }

   public LZMAInputStream(InputStream in) throws IOException {
      this(in, -1);
   }

   public LZMAInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
      this(in, -1, arrayCache);
   }

   public LZMAInputStream(InputStream in, int memoryLimit) throws IOException {
      this(in, memoryLimit, ArrayCache.getDefaultCache());
   }

   public LZMAInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      DataInputStream inData = new DataInputStream(in);
      byte propsByte = inData.readByte();
      int dictSize = 0;

      for(int i = 0; i < 4; ++i) {
         dictSize |= inData.readUnsignedByte() << 8 * i;
      }

      long uncompSize = 0L;

      for(int i = 0; i < 8; ++i) {
         uncompSize |= (long)inData.readUnsignedByte() << 8 * i;
      }

      int memoryNeeded = getMemoryUsage(dictSize, propsByte);
      if (memoryLimit != -1 && memoryNeeded > memoryLimit) {
         throw new MemoryLimitException(memoryNeeded, memoryLimit);
      } else {
         this.relaxedEndCondition = true;
         this.initialize(in, uncompSize, propsByte, dictSize, (byte[])null, arrayCache);
      }
   }

   public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      this.initialize(in, uncompSize, propsByte, dictSize, (byte[])null, ArrayCache.getDefaultCache());
   }

   public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      this.initialize(in, uncompSize, propsByte, dictSize, presetDict, ArrayCache.getDefaultCache());
   }

   public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      this.initialize(in, uncompSize, propsByte, dictSize, presetDict, arrayCache);
   }

   public LZMAInputStream(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      this.initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, ArrayCache.getDefaultCache());
   }

   public LZMAInputStream(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
      this.endReached = false;
      this.relaxedEndCondition = false;
      this.tempBuf = new byte[1];
      this.exception = null;
      this.initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, arrayCache);
   }

   private void initialize(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
      if (uncompSize < -1L) {
         throw new UnsupportedOptionsException("Uncompressed size is too big");
      } else {
         int props = propsByte & 255;
         if (props > 224) {
            throw new CorruptedInputException("Invalid LZMA properties byte");
         } else {
            int pb = props / 45;
            props -= pb * 9 * 5;
            int lp = props / 9;
            int lc = props - lp * 9;
            if (dictSize >= 0 && dictSize <= 2147483632) {
               this.initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, arrayCache);
            } else {
               throw new UnsupportedOptionsException("LZMA dictionary is too big for this implementation");
            }
         }
      }
   }

   private void initialize(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
      if (uncompSize >= -1L && lc >= 0 && lc <= 8 && lp >= 0 && lp <= 4 && pb >= 0 && pb <= 4) {
         this.in = in;
         this.arrayCache = arrayCache;
         dictSize = getDictSize(dictSize);
         if (uncompSize >= 0L && (long)dictSize > uncompSize) {
            dictSize = getDictSize((int)uncompSize);
         }

         this.lz = new LZDecoder(getDictSize(dictSize), presetDict, arrayCache);
         this.rc = new RangeDecoderFromStream(in);
         this.lzma = new LZMADecoder(this.lz, this.rc, lc, lp, pb);
         this.remainingSize = uncompSize;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void enableRelaxedEndCondition() {
      this.relaxedEndCondition = true;
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
                  int copySizeMax = len;
                  if (this.remainingSize >= 0L && this.remainingSize < (long)len) {
                     copySizeMax = (int)this.remainingSize;
                  }

                  this.lz.setLimit(copySizeMax);

                  try {
                     this.lzma.decode();
                  } catch (CorruptedInputException e) {
                     if (this.remainingSize != -1L || !this.lzma.endMarkerDetected()) {
                        throw e;
                     }

                     this.endReached = true;
                     this.rc.normalize();
                  }

                  int copiedSize = this.lz.flush(buf, off);
                  off += copiedSize;
                  len -= copiedSize;
                  size += copiedSize;
                  if (this.remainingSize >= 0L) {
                     this.remainingSize -= (long)copiedSize;

                     assert this.remainingSize >= 0L;

                     if (this.remainingSize == 0L) {
                        this.endReached = true;
                     }
                  }

                  if (this.endReached) {
                     if (this.lz.hasPending()) {
                        throw new CorruptedInputException();
                     }

                     if (!this.rc.isFinished()) {
                        if (this.remainingSize == -1L || !this.relaxedEndCondition) {
                           throw new CorruptedInputException();
                        }

                        this.lz.setLimit(1);
                        boolean endMarkerDetected = false;

                        try {
                           this.lzma.decode();
                        } catch (CorruptedInputException e) {
                           if (!this.lzma.endMarkerDetected()) {
                              throw e;
                           }

                           endMarkerDetected = true;
                           this.rc.normalize();
                        }

                        if (!endMarkerDetected || !this.rc.isFinished()) {
                           throw new CorruptedInputException();
                        }

                        assert !this.lz.hasPending();

                        assert this.lz.hasSpace();
                     }

                     this.putArraysToCache();
                     return size == 0 ? -1 : size;
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

   private void putArraysToCache() {
      if (this.lz != null) {
         this.lz.putArraysToCache(this.arrayCache);
         this.lz = null;
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
