package javolution.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import javolution.lang.Reusable;

public final class UTF8ByteBufferWriter extends Writer implements Reusable {
   private ByteBuffer _byteBuffer;
   private char _highSurrogate;

   public UTF8ByteBufferWriter setOutput(ByteBuffer byteBuffer) {
      if (this._byteBuffer != null) {
         throw new IllegalStateException("Writer not closed or reset");
      } else {
         this._byteBuffer = byteBuffer;
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
         this._byteBuffer.put((byte)code);
      } else {
         this.write2(code);
      }

   }

   private void write2(int c) throws IOException {
      if ((c & -2048) == 0) {
         this._byteBuffer.put((byte)(192 | c >> 6));
         this._byteBuffer.put((byte)(128 | c & 63));
      } else if ((c & -65536) == 0) {
         this._byteBuffer.put((byte)(224 | c >> 12));
         this._byteBuffer.put((byte)(128 | c >> 6 & 63));
         this._byteBuffer.put((byte)(128 | c & 63));
      } else if ((c & -14680064) == 0) {
         this._byteBuffer.put((byte)(240 | c >> 18));
         this._byteBuffer.put((byte)(128 | c >> 12 & 63));
         this._byteBuffer.put((byte)(128 | c >> 6 & 63));
         this._byteBuffer.put((byte)(128 | c & 63));
      } else if ((c & -201326592) == 0) {
         this._byteBuffer.put((byte)(248 | c >> 24));
         this._byteBuffer.put((byte)(128 | c >> 18 & 63));
         this._byteBuffer.put((byte)(128 | c >> 12 & 63));
         this._byteBuffer.put((byte)(128 | c >> 6 & 63));
         this._byteBuffer.put((byte)(128 | c & 63));
      } else {
         if ((c & Integer.MIN_VALUE) != 0) {
            throw new CharConversionException("Illegal character U+" + Integer.toHexString(c));
         }

         this._byteBuffer.put((byte)(252 | c >> 30));
         this._byteBuffer.put((byte)(128 | c >> 24 & 63));
         this._byteBuffer.put((byte)(128 | c >> 18 & 63));
         this._byteBuffer.put((byte)(128 | c >> 12 & 63));
         this._byteBuffer.put((byte)(128 | c >> 6 & 63));
         this._byteBuffer.put((byte)(128 | c & 63));
      }

   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      int off_plus_len = off + len;
      int i = off;

      while(i < off_plus_len) {
         char c = cbuf[i++];
         if (c < 128) {
            this._byteBuffer.put((byte)c);
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
            this._byteBuffer.put((byte)c);
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
            this._byteBuffer.put((byte)c);
         } else {
            this.write(c);
         }
      }

   }

   public void flush() throws IOException {
      if (this._byteBuffer == null) {
         throw new IOException("Writer closed");
      }
   }

   public void close() throws IOException {
      if (this._byteBuffer != null) {
         this.reset();
      }

   }

   public void reset() {
      this._byteBuffer = null;
      this._highSurrogate = 0;
   }

   /** @deprecated */
   public UTF8ByteBufferWriter setByteBuffer(ByteBuffer byteBuffer) {
      return this.setOutput(byteBuffer);
   }
}
