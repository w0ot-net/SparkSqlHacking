package io.jsonwebtoken.impl.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class BaseNCodecInputStream extends FilterInputStream {
   private final BaseNCodec baseNCodec;
   private final boolean doEncode;
   private final byte[] singleByte = new byte[1];
   private final byte[] buf;
   private final BaseNCodec.Context context = new BaseNCodec.Context();

   protected BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean doEncode) {
      super(inputStream);
      this.doEncode = doEncode;
      this.baseNCodec = baseNCodec;
      this.buf = new byte[doEncode ? 4096 : 8192];
   }

   public int available() throws IOException {
      return this.context.eof ? 0 : 1;
   }

   public boolean isStrictDecoding() {
      return this.baseNCodec.isStrictDecoding();
   }

   public synchronized void mark(int readLimit) {
   }

   public boolean markSupported() {
      return false;
   }

   public int read() throws IOException {
      int r;
      for(r = this.read(this.singleByte, 0, 1); r == 0; r = this.read(this.singleByte, 0, 1)) {
      }

      if (r > 0) {
         byte b = this.singleByte[0];
         return b < 0 ? 256 + b : b;
      } else {
         return -1;
      }
   }

   public int read(byte[] array, int offset, int len) throws IOException {
      Objects.requireNonNull(array, "array");
      if (offset >= 0 && len >= 0) {
         if (offset <= array.length && offset + len <= array.length) {
            if (len == 0) {
               return 0;
            } else {
               int readLen;
               int read;
               for(readLen = 0; readLen < len; readLen += read) {
                  if (!this.baseNCodec.hasData(this.context)) {
                     read = this.in.read(this.buf);
                     if (this.doEncode) {
                        this.baseNCodec.encode(this.buf, 0, read, this.context);
                     } else {
                        this.baseNCodec.decode(this.buf, 0, read, this.context);
                     }
                  }

                  read = this.baseNCodec.readResults(array, offset + readLen, len - readLen, this.context);
                  if (read < 0) {
                     return readLen != 0 ? readLen : -1;
                  }
               }

               return readLen;
            }
         } else {
            throw new IndexOutOfBoundsException();
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized void reset() throws IOException {
      throw new IOException("mark/reset not supported");
   }

   public long skip(long n) throws IOException {
      if (n < 0L) {
         throw new IllegalArgumentException("Negative skip length: " + n);
      } else {
         byte[] b = new byte[512];

         long todo;
         int len;
         for(todo = n; todo > 0L; todo -= (long)len) {
            len = (int)Math.min((long)b.length, todo);
            len = this.read(b, 0, len);
            if (len == -1) {
               break;
            }
         }

         return n - todo;
      }
   }
}
