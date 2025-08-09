package org.tukaani.xz;

import java.io.IOException;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoderToBuffer;

class LZMA2OutputStream extends FinishableOutputStream {
   static final int COMPRESSED_SIZE_MAX = 65536;
   private final ArrayCache arrayCache;
   private FinishableOutputStream out;
   private LZEncoder lz;
   private RangeEncoderToBuffer rc;
   private LZMAEncoder lzma;
   private final int props;
   private boolean dictResetNeeded = true;
   private boolean stateResetNeeded = true;
   private boolean propsNeeded = true;
   private int pendingSize = 0;
   private boolean finished = false;
   private IOException exception = null;
   private final byte[] chunkHeader = new byte[6];
   private final byte[] tempBuf = new byte[1];

   private static int getExtraSizeBefore(int dictSize) {
      return 65536 > dictSize ? 65536 - dictSize : 0;
   }

   static int getMemoryUsage(LZMA2Options options) {
      int dictSize = options.getDictSize();
      int extraSizeBefore = getExtraSizeBefore(dictSize);
      return 70 + LZMAEncoder.getMemoryUsage(options.getMode(), dictSize, extraSizeBefore, options.getMatchFinder());
   }

   LZMA2OutputStream(FinishableOutputStream out, LZMA2Options options, ArrayCache arrayCache) {
      if (out == null) {
         throw new NullPointerException();
      } else {
         this.arrayCache = arrayCache;
         this.out = out;
         this.rc = new RangeEncoderToBuffer(65536, arrayCache);
         int dictSize = options.getDictSize();
         int extraSizeBefore = getExtraSizeBefore(dictSize);
         this.lzma = LZMAEncoder.getInstance(this.rc, options.getLc(), options.getLp(), options.getPb(), options.getMode(), dictSize, extraSizeBefore, options.getNiceLen(), options.getMatchFinder(), options.getDepthLimit(), this.arrayCache);
         this.lz = this.lzma.getLZEncoder();
         byte[] presetDict = options.getPresetDict();
         if (presetDict != null && presetDict.length > 0) {
            this.lz.setPresetDict(dictSize, presetDict);
            this.dictResetNeeded = false;
         }

         this.props = (options.getPb() * 5 + options.getLp()) * 9 + options.getLc();
      }
   }

   public void write(int b) throws IOException {
      this.tempBuf[0] = (byte)b;
      this.write(this.tempBuf, 0, 1);
   }

   public void write(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (this.exception != null) {
            throw this.exception;
         } else if (this.finished) {
            throw new XZIOException("Stream finished or closed");
         } else {
            try {
               while(len > 0) {
                  int used = this.lz.fillWindow(buf, off, len);
                  off += used;
                  len -= used;
                  this.pendingSize += used;
                  if (this.lzma.encodeForLZMA2()) {
                     this.writeChunk();
                  }
               }

            } catch (IOException e) {
               this.exception = e;
               throw e;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private void writeChunk() throws IOException {
      int compressedSize = this.rc.finish();
      int uncompressedSize = this.lzma.getUncompressedSize();

      assert compressedSize > 0 : compressedSize;

      assert uncompressedSize > 0 : uncompressedSize;

      if (compressedSize + 2 < uncompressedSize) {
         this.writeLZMA(uncompressedSize, compressedSize);
      } else {
         this.lzma.reset();
         uncompressedSize = this.lzma.getUncompressedSize();

         assert uncompressedSize > 0 : uncompressedSize;

         this.writeUncompressed(uncompressedSize);
      }

      this.pendingSize -= uncompressedSize;
      this.lzma.resetUncompressedSize();
      this.rc.reset();
   }

   private void writeLZMA(int uncompressedSize, int compressedSize) throws IOException {
      int control;
      if (this.propsNeeded) {
         if (this.dictResetNeeded) {
            control = 224;
         } else {
            control = 192;
         }
      } else if (this.stateResetNeeded) {
         control = 160;
      } else {
         control = 128;
      }

      control |= uncompressedSize - 1 >>> 16;
      this.chunkHeader[0] = (byte)control;
      this.chunkHeader[1] = (byte)(uncompressedSize - 1 >>> 8);
      this.chunkHeader[2] = (byte)(uncompressedSize - 1);
      this.chunkHeader[3] = (byte)(compressedSize - 1 >>> 8);
      this.chunkHeader[4] = (byte)(compressedSize - 1);
      if (this.propsNeeded) {
         this.chunkHeader[5] = (byte)this.props;
         this.out.write(this.chunkHeader, 0, 6);
      } else {
         this.out.write(this.chunkHeader, 0, 5);
      }

      this.rc.write(this.out);
      this.propsNeeded = false;
      this.stateResetNeeded = false;
      this.dictResetNeeded = false;
   }

   private void writeUncompressed(int uncompressedSize) throws IOException {
      while(uncompressedSize > 0) {
         int chunkSize = Math.min(uncompressedSize, 65536);
         this.chunkHeader[0] = (byte)(this.dictResetNeeded ? 1 : 2);
         this.chunkHeader[1] = (byte)(chunkSize - 1 >>> 8);
         this.chunkHeader[2] = (byte)(chunkSize - 1);
         this.out.write(this.chunkHeader, 0, 3);
         this.lz.copyUncompressed(this.out, uncompressedSize, chunkSize);
         uncompressedSize -= chunkSize;
         this.dictResetNeeded = false;
      }

      this.stateResetNeeded = true;
   }

   private void writeEndMarker() throws IOException {
      assert !this.finished;

      if (this.exception != null) {
         throw this.exception;
      } else {
         this.lz.setFinishing();

         try {
            while(this.pendingSize > 0) {
               this.lzma.encodeForLZMA2();
               this.writeChunk();
            }

            this.out.write(0);
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }

         this.finished = true;
         this.lzma.putArraysToCache(this.arrayCache);
         this.lzma = null;
         this.lz = null;
         this.rc.putArraysToCache(this.arrayCache);
         this.rc = null;
      }
   }

   public void flush() throws IOException {
      if (this.exception != null) {
         throw this.exception;
      } else if (this.finished) {
         throw new XZIOException("Stream finished or closed");
      } else {
         try {
            this.lz.setFlushing();

            while(this.pendingSize > 0) {
               this.lzma.encodeForLZMA2();
               this.writeChunk();
            }

            this.out.flush();
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }
      }
   }

   public void finish() throws IOException {
      if (!this.finished) {
         this.writeEndMarker();

         try {
            this.out.finish();
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }
      }

   }

   public void close() throws IOException {
      if (this.out != null) {
         if (!this.finished) {
            try {
               this.writeEndMarker();
            } catch (IOException var2) {
            }
         }

         try {
            this.out.close();
         } catch (IOException e) {
            if (this.exception == null) {
               this.exception = e;
            }
         }

         this.out = null;
      }

      if (this.exception != null) {
         throw this.exception;
      }
   }
}
