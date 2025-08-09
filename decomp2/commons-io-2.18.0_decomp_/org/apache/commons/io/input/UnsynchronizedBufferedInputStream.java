package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;

public final class UnsynchronizedBufferedInputStream extends UnsynchronizedFilterInputStream {
   protected volatile byte[] buffer;
   protected int count;
   protected int markLimit;
   protected int markPos;
   protected int pos;

   private UnsynchronizedBufferedInputStream(InputStream in, int size) {
      super(in);
      this.markPos = -1;
      if (size <= 0) {
         throw new IllegalArgumentException("Size must be > 0");
      } else {
         this.buffer = new byte[size];
      }
   }

   public int available() throws IOException {
      InputStream localIn = this.inputStream;
      if (this.buffer != null && localIn != null) {
         return this.count - this.pos + localIn.available();
      } else {
         throw new IOException("Stream is closed");
      }
   }

   public void close() throws IOException {
      this.buffer = null;
      InputStream localIn = this.inputStream;
      this.inputStream = null;
      if (localIn != null) {
         localIn.close();
      }

   }

   private int fillBuffer(InputStream localIn, byte[] localBuf) throws IOException {
      if (this.markPos != -1 && this.pos - this.markPos < this.markLimit) {
         if (this.markPos == 0 && this.markLimit > localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > this.markLimit) {
               newLength = this.markLimit;
            }

            byte[] newbuf = new byte[newLength];
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            localBuf = this.buffer = newbuf;
         } else if (this.markPos > 0) {
            System.arraycopy(localBuf, this.markPos, localBuf, 0, localBuf.length - this.markPos);
         }

         this.pos -= this.markPos;
         this.count = this.markPos = 0;
         int bytesread = localIn.read(localBuf, this.pos, localBuf.length - this.pos);
         this.count = bytesread <= 0 ? this.pos : this.pos + bytesread;
         return bytesread;
      } else {
         int result = localIn.read(localBuf);
         if (result > 0) {
            this.markPos = -1;
            this.pos = 0;
            this.count = result;
         }

         return result;
      }
   }

   byte[] getBuffer() {
      return this.buffer;
   }

   public void mark(int readLimit) {
      this.markLimit = readLimit;
      this.markPos = this.pos;
   }

   public boolean markSupported() {
      return true;
   }

   public int read() throws IOException {
      byte[] localBuf = this.buffer;
      InputStream localIn = this.inputStream;
      if (localBuf != null && localIn != null) {
         if (this.pos >= this.count && this.fillBuffer(localIn, localBuf) == -1) {
            return -1;
         } else {
            if (localBuf != this.buffer) {
               localBuf = this.buffer;
               if (localBuf == null) {
                  throw new IOException("Stream is closed");
               }
            }

            return this.count - this.pos > 0 ? localBuf[this.pos++] & 255 : -1;
         }
      } else {
         throw new IOException("Stream is closed");
      }
   }

   public int read(byte[] dest, int offset, int length) throws IOException {
      byte[] localBuf = this.buffer;
      if (localBuf == null) {
         throw new IOException("Stream is closed");
      } else if (offset <= dest.length - length && offset >= 0 && length >= 0) {
         if (length == 0) {
            return 0;
         } else {
            InputStream localIn = this.inputStream;
            if (localIn == null) {
               throw new IOException("Stream is closed");
            } else {
               int required;
               if (this.pos < this.count) {
                  int copylength = this.count - this.pos >= length ? length : this.count - this.pos;
                  System.arraycopy(localBuf, this.pos, dest, offset, copylength);
                  this.pos += copylength;
                  if (copylength == length || localIn.available() == 0) {
                     return copylength;
                  }

                  offset += copylength;
                  required = length - copylength;
               } else {
                  required = length;
               }

               while(true) {
                  int read;
                  if (this.markPos == -1 && required >= localBuf.length) {
                     read = localIn.read(dest, offset, required);
                     if (read == -1) {
                        return required == length ? -1 : length - required;
                     }
                  } else {
                     if (this.fillBuffer(localIn, localBuf) == -1) {
                        return required == length ? -1 : length - required;
                     }

                     if (localBuf != this.buffer) {
                        localBuf = this.buffer;
                        if (localBuf == null) {
                           throw new IOException("Stream is closed");
                        }
                     }

                     read = this.count - this.pos >= required ? required : this.count - this.pos;
                     System.arraycopy(localBuf, this.pos, dest, offset, read);
                     this.pos += read;
                  }

                  required -= read;
                  if (required == 0) {
                     return length;
                  }

                  if (localIn.available() == 0) {
                     return length - required;
                  }

                  offset += read;
               }
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void reset() throws IOException {
      if (this.buffer == null) {
         throw new IOException("Stream is closed");
      } else if (-1 == this.markPos) {
         throw new IOException("Mark has been invalidated");
      } else {
         this.pos = this.markPos;
      }
   }

   public long skip(long amount) throws IOException {
      byte[] localBuf = this.buffer;
      InputStream localIn = this.inputStream;
      if (localBuf == null) {
         throw new IOException("Stream is closed");
      } else if (amount < 1L) {
         return 0L;
      } else if (localIn == null) {
         throw new IOException("Stream is closed");
      } else if ((long)(this.count - this.pos) >= amount) {
         this.pos += (int)amount;
         return amount;
      } else {
         int read = this.count - this.pos;
         this.pos = this.count;
         if (this.markPos != -1 && amount <= (long)this.markLimit) {
            if (this.fillBuffer(localIn, localBuf) == -1) {
               return (long)read;
            } else if ((long)(this.count - this.pos) >= amount - (long)read) {
               this.pos += (int)amount - read;
               return amount;
            } else {
               read += this.count - this.pos;
               this.pos = this.count;
               return (long)read;
            }
         } else {
            return (long)read + localIn.skip(amount - (long)read);
         }
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      public UnsynchronizedBufferedInputStream get() throws IOException {
         return new UnsynchronizedBufferedInputStream(this.getInputStream(), this.getBufferSize());
      }
   }
}
