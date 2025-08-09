package javolution.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javolution.lang.Reusable;

public final class UTF8StreamWriter extends Writer implements Reusable {
   private OutputStream _outputStream;
   private final byte[] _bytes;
   private int _index;
   private char _highSurrogate;

   public UTF8StreamWriter() {
      this._bytes = new byte[2048];
   }

   public UTF8StreamWriter(int capacity) {
      this._bytes = new byte[capacity];
   }

   public UTF8StreamWriter setOutput(OutputStream out) {
      if (this._outputStream != null) {
         throw new IllegalStateException("Writer not closed or reset");
      } else {
         this._outputStream = out;
         return this;
      }
   }

   public void write(char c) throws IOException {
      if (c >= '\ud800' && c <= '\udfff') {
         if (c < '\udc00') {
            this._highSurrogate = c;
         } else {
            int code = (this._highSurrogate - '\ud800' << 10) + (c - '\udc00') + 65536;
            this.write(code);
         }
      } else {
         this.write((int)c);
      }

   }

   public void write(int code) throws IOException {
      if ((code & -128) == 0) {
         this._bytes[this._index] = (byte)code;
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      } else {
         this.write2(code);
      }

   }

   private void write2(int c) throws IOException {
      if ((c & -2048) == 0) {
         this._bytes[this._index] = (byte)(192 | c >> 6);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      } else if ((c & -65536) == 0) {
         this._bytes[this._index] = (byte)(224 | c >> 12);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 6 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      } else if ((c & -14680064) == 0) {
         this._bytes[this._index] = (byte)(240 | c >> 18);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 12 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 6 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      } else if ((c & -201326592) == 0) {
         this._bytes[this._index] = (byte)(248 | c >> 24);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 18 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 12 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 6 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      } else {
         if ((c & Integer.MIN_VALUE) != 0) {
            throw new CharConversionException("Illegal character U+" + Integer.toHexString(c));
         }

         this._bytes[this._index] = (byte)(252 | c >> 30);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 24 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 18 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 12 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c >> 6 & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }

         this._bytes[this._index] = (byte)(128 | c & 63);
         if (++this._index >= this._bytes.length) {
            this.flushBuffer();
         }
      }

   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      int off_plus_len = off + len;
      int i = off;

      while(i < off_plus_len) {
         char c = cbuf[i++];
         if (c < 128) {
            this._bytes[this._index] = (byte)c;
            if (++this._index >= this._bytes.length) {
               this.flushBuffer();
            }
         } else {
            this.write(c);
         }
      }

   }

   public void write(String str, int off, int len) throws IOException {
      int off_plus_len = off + len;
      int i = off;

      while(i < off_plus_len) {
         char c = str.charAt(i++);
         if (c < 128) {
            this._bytes[this._index] = (byte)c;
            if (++this._index >= this._bytes.length) {
               this.flushBuffer();
            }
         } else {
            this.write(c);
         }
      }

   }

   public void write(CharSequence csq) throws IOException {
      int length = csq.length();
      int i = 0;

      while(i < length) {
         char c = csq.charAt(i++);
         if (c < 128) {
            this._bytes[this._index] = (byte)c;
            if (++this._index >= this._bytes.length) {
               this.flushBuffer();
            }
         } else {
            this.write(c);
         }
      }

   }

   public void flush() throws IOException {
      this.flushBuffer();
      this._outputStream.flush();
   }

   public void close() throws IOException {
      if (this._outputStream != null) {
         this.flushBuffer();
         this._outputStream.close();
         this.reset();
      }

   }

   private void flushBuffer() throws IOException {
      if (this._outputStream == null) {
         throw new IOException("Stream closed");
      } else {
         this._outputStream.write(this._bytes, 0, this._index);
         this._index = 0;
      }
   }

   public void reset() {
      this._highSurrogate = 0;
      this._index = 0;
      this._outputStream = null;
   }

   /** @deprecated */
   public UTF8StreamWriter setOutputStream(OutputStream out) {
      return this.setOutput(out);
   }
}
