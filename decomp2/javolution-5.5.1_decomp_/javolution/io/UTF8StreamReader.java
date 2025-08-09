package javolution.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javolution.lang.Reusable;

public final class UTF8StreamReader extends Reader implements Reusable {
   private InputStream _inputStream;
   private int _start;
   private int _end;
   private final byte[] _bytes;
   private int _code;
   private int _moreBytes;

   public UTF8StreamReader() {
      this._bytes = new byte[2048];
   }

   public UTF8StreamReader(int capacity) {
      this._bytes = new byte[capacity];
   }

   public UTF8StreamReader setInput(InputStream inStream) {
      if (this._inputStream != null) {
         throw new IllegalStateException("Reader not closed or reset");
      } else {
         this._inputStream = inStream;
         return this;
      }
   }

   public boolean ready() throws IOException {
      if (this._inputStream == null) {
         throw new IOException("Stream closed");
      } else {
         return this._end - this._start > 0 || this._inputStream.available() != 0;
      }
   }

   public void close() throws IOException {
      if (this._inputStream != null) {
         this._inputStream.close();
         this.reset();
      }

   }

   public int read() throws IOException {
      byte b = this._bytes[this._start];
      return b >= 0 && this._start++ < this._end ? b : this.read2();
   }

   private int read2() throws IOException {
      if (this._start < this._end) {
         byte b = this._bytes[this._start++];
         if (b >= 0 && this._moreBytes == 0) {
            return b;
         } else if ((b & 192) == 128 && this._moreBytes != 0) {
            this._code = this._code << 6 | b & 63;
            return --this._moreBytes == 0 ? this._code : this.read2();
         } else if ((b & 224) == 192 && this._moreBytes == 0) {
            this._code = b & 31;
            this._moreBytes = 1;
            return this.read2();
         } else if ((b & 240) == 224 && this._moreBytes == 0) {
            this._code = b & 15;
            this._moreBytes = 2;
            return this.read2();
         } else if ((b & 248) == 240 && this._moreBytes == 0) {
            this._code = b & 7;
            this._moreBytes = 3;
            return this.read2();
         } else if ((b & 252) == 248 && this._moreBytes == 0) {
            this._code = b & 3;
            this._moreBytes = 4;
            return this.read2();
         } else if ((b & 254) == 252 && this._moreBytes == 0) {
            this._code = b & 1;
            this._moreBytes = 5;
            return this.read2();
         } else {
            throw new CharConversionException("Invalid UTF-8 Encoding");
         }
      } else if (this._inputStream == null) {
         throw new IOException("No input stream or stream closed");
      } else {
         this._start = 0;
         this._end = this._inputStream.read(this._bytes, 0, this._bytes.length);
         if (this._end > 0) {
            return this.read2();
         } else if (this._moreBytes == 0) {
            return -1;
         } else {
            throw new CharConversionException("Unexpected end of stream");
         }
      }
   }

   public int read(char[] cbuf, int off, int len) throws IOException {
      if (this._inputStream == null) {
         throw new IOException("No input stream or stream closed");
      } else {
         if (this._start >= this._end) {
            this._start = 0;
            this._end = this._inputStream.read(this._bytes, 0, this._bytes.length);
            if (this._end <= 0) {
               return this._end;
            }
         }

         int off_plus_len = off + len;
         int i = off;

         while(true) {
            if (i >= off_plus_len) {
               return len;
            }

            byte b = this._bytes[this._start];
            if (b < 0 || ++this._start >= this._end) {
               if (b >= 0) {
                  cbuf[i++] = (char)b;
                  return i - off;
               }

               if (i >= off_plus_len - 1) {
                  break;
               }

               int code = this.read2();
               if (code < 65536) {
                  cbuf[i++] = (char)code;
               } else {
                  if (code > 1114111) {
                     throw new CharConversionException("Cannot convert U+" + Integer.toHexString(code) + " to char (code greater than U+10FFFF)");
                  }

                  cbuf[i++] = (char)((code - 65536 >> 10) + '\ud800');
                  cbuf[i++] = (char)((code - 65536 & 1023) + '\udc00');
               }

               if (this._start >= this._end) {
                  break;
               }
            } else {
               cbuf[i++] = (char)b;
            }
         }

         return i - off;
      }
   }

   public void read(Appendable dest) throws IOException {
      if (this._inputStream == null) {
         throw new IOException("No input stream or stream closed");
      } else {
         while(true) {
            if (this._start >= this._end) {
               this._start = 0;
               this._end = this._inputStream.read(this._bytes, 0, this._bytes.length);
               if (this._end <= 0) {
                  return;
               }
            }

            byte b = this._bytes[this._start];
            if (b >= 0) {
               dest.append((char)b);
               ++this._start;
            } else {
               int code = this.read2();
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
      this._code = 0;
      this._end = 0;
      this._inputStream = null;
      this._moreBytes = 0;
      this._start = 0;
   }

   /** @deprecated */
   public UTF8StreamReader setInputStream(InputStream inStream) {
      return this.setInput(inStream);
   }
}
