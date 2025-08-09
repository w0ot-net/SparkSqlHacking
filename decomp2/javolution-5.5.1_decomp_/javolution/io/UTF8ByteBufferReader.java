package javolution.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.Reader;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import javolution.lang.Reusable;

public final class UTF8ByteBufferReader extends Reader implements Reusable {
   private ByteBuffer _byteBuffer;
   private int _code;
   private int _moreBytes;

   public UTF8ByteBufferReader setInput(ByteBuffer byteBuffer) {
      if (this._byteBuffer != null) {
         throw new IllegalStateException("Reader not closed or reset");
      } else {
         this._byteBuffer = byteBuffer;
         return this;
      }
   }

   public boolean ready() throws IOException {
      if (this._byteBuffer != null) {
         return this._byteBuffer.hasRemaining();
      } else {
         throw new IOException("Reader closed");
      }
   }

   public void close() throws IOException {
      if (this._byteBuffer != null) {
         this.reset();
      }

   }

   public int read() throws IOException {
      if (this._byteBuffer != null) {
         if (this._byteBuffer.hasRemaining()) {
            byte b = this._byteBuffer.get();
            return b >= 0 ? b : this.read2(b);
         } else {
            return -1;
         }
      } else {
         throw new IOException("Reader closed");
      }
   }

   private int read2(byte b) throws IOException {
      try {
         if (b >= 0 && this._moreBytes == 0) {
            return b;
         } else if ((b & 192) == 128 && this._moreBytes != 0) {
            this._code = this._code << 6 | b & 63;
            return --this._moreBytes == 0 ? this._code : this.read2(this._byteBuffer.get());
         } else if ((b & 224) == 192 && this._moreBytes == 0) {
            this._code = b & 31;
            this._moreBytes = 1;
            return this.read2(this._byteBuffer.get());
         } else if ((b & 240) == 224 && this._moreBytes == 0) {
            this._code = b & 15;
            this._moreBytes = 2;
            return this.read2(this._byteBuffer.get());
         } else if ((b & 248) == 240 && this._moreBytes == 0) {
            this._code = b & 7;
            this._moreBytes = 3;
            return this.read2(this._byteBuffer.get());
         } else if ((b & 252) == 248 && this._moreBytes == 0) {
            this._code = b & 3;
            this._moreBytes = 4;
            return this.read2(this._byteBuffer.get());
         } else if ((b & 254) == 252 && this._moreBytes == 0) {
            this._code = b & 1;
            this._moreBytes = 5;
            return this.read2(this._byteBuffer.get());
         } else {
            throw new CharConversionException("Invalid UTF-8 Encoding");
         }
      } catch (BufferUnderflowException var3) {
         throw new CharConversionException("Incomplete Sequence");
      }
   }

   public int read(char[] cbuf, int off, int len) throws IOException {
      if (this._byteBuffer == null) {
         throw new IOException("Reader closed");
      } else {
         int off_plus_len = off + len;
         int remaining = this._byteBuffer.remaining();
         if (remaining <= 0) {
            return -1;
         } else {
            int i = off;

            while(i < off_plus_len) {
               if (remaining-- <= 0) {
                  return i - off;
               }

               byte b = this._byteBuffer.get();
               if (b >= 0) {
                  cbuf[i++] = (char)b;
               } else {
                  if (i >= off_plus_len - 1) {
                     this._byteBuffer.position(this._byteBuffer.position() - 1);
                     ++remaining;
                     return i - off;
                  }

                  int code = this.read2(b);
                  remaining = this._byteBuffer.remaining();
                  if (code < 65536) {
                     cbuf[i++] = (char)code;
                  } else {
                     if (code > 1114111) {
                        throw new CharConversionException("Cannot convert U+" + Integer.toHexString(code) + " to char (code greater than U+10FFFF)");
                     }

                     cbuf[i++] = (char)((code - 65536 >> 10) + '\ud800');
                     cbuf[i++] = (char)((code - 65536 & 1023) + '\udc00');
                  }
               }
            }

            return len;
         }
      }
   }

   public void read(Appendable dest) throws IOException {
      if (this._byteBuffer == null) {
         throw new IOException("Reader closed");
      } else {
         while(this._byteBuffer.hasRemaining()) {
            byte b = this._byteBuffer.get();
            if (b >= 0) {
               dest.append((char)b);
            } else {
               int code = this.read2(b);
               if (code < 65536) {
                  dest.append((char)code);
               } else {
                  if (code > 1114111) {
                     throw new CharConversionException("Cannot convert U+" + Integer.toHexString(code) + " to char (code greater than U+10FFFF)");
                  }

                  dest.append((char)((code - 65536 >> 10) + '\ud800'));
                  dest.append((char)((code - 65536 & 1023) + '\udc00'));
               }
            }
         }

      }
   }

   public void reset() {
      this._byteBuffer = null;
      this._code = 0;
      this._moreBytes = 0;
   }

   /** @deprecated */
   public UTF8ByteBufferReader setByteBuffer(ByteBuffer byteBuffer) {
      return this.setInput(byteBuffer);
   }
}
