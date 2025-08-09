package org.tukaani.xz;

import java.io.DataOutputStream;
import java.io.IOException;

class UncompressedLZMA2OutputStream extends FinishableOutputStream {
   private final ArrayCache arrayCache;
   private FinishableOutputStream out;
   private final DataOutputStream outData;
   private final byte[] uncompBuf;
   private int uncompPos = 0;
   private boolean dictResetNeeded = true;
   private boolean finished = false;
   private IOException exception = null;
   private final byte[] tempBuf = new byte[1];

   static int getMemoryUsage() {
      return 70;
   }

   UncompressedLZMA2OutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      if (out == null) {
         throw new NullPointerException();
      } else {
         this.out = out;
         this.outData = new DataOutputStream(out);
         this.arrayCache = arrayCache;
         this.uncompBuf = arrayCache.getByteArray(65536, false);
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
                  int copySize = Math.min(65536 - this.uncompPos, len);
                  System.arraycopy(buf, off, this.uncompBuf, this.uncompPos, copySize);
                  len -= copySize;
                  this.uncompPos += copySize;
                  if (this.uncompPos == 65536) {
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
      this.outData.writeByte(this.dictResetNeeded ? 1 : 2);
      this.outData.writeShort(this.uncompPos - 1);
      this.outData.write(this.uncompBuf, 0, this.uncompPos);
      this.uncompPos = 0;
      this.dictResetNeeded = false;
   }

   private void writeEndMarker() throws IOException {
      if (this.exception != null) {
         throw this.exception;
      } else if (this.finished) {
         throw new XZIOException("Stream finished or closed");
      } else {
         try {
            if (this.uncompPos > 0) {
               this.writeChunk();
            }

            this.out.write(0);
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }

         this.finished = true;
         this.arrayCache.putArray(this.uncompBuf);
      }
   }

   public void flush() throws IOException {
      if (this.exception != null) {
         throw this.exception;
      } else if (this.finished) {
         throw new XZIOException("Stream finished or closed");
      } else {
         try {
            if (this.uncompPos > 0) {
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
