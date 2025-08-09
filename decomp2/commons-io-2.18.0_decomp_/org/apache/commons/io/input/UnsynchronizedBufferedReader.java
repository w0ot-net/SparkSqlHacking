package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class UnsynchronizedBufferedReader extends UnsynchronizedReader {
   private static final char NUL = '\u0000';
   private final Reader in;
   private char[] buf;
   private int pos;
   private int end;
   private int mark;
   private int markLimit;

   public UnsynchronizedBufferedReader(Reader in) {
      this(in, 8192);
   }

   public UnsynchronizedBufferedReader(Reader in, int size) {
      this.mark = -1;
      this.markLimit = -1;
      if (size <= 0) {
         throw new IllegalArgumentException("size <= 0");
      } else {
         this.in = in;
         this.buf = new char[size];
      }
   }

   final void chompNewline() throws IOException {
      if ((this.pos != this.end || this.fillBuf() != -1) && this.buf[this.pos] == '\n') {
         ++this.pos;
      }

   }

   public void close() throws IOException {
      if (!this.isClosed()) {
         this.in.close();
         this.buf = null;
         super.close();
      }

   }

   private int fillBuf() throws IOException {
      if (this.mark != -1 && this.pos - this.mark < this.markLimit) {
         if (this.mark == 0 && this.markLimit > this.buf.length) {
            int newLength = this.buf.length * 2;
            if (newLength > this.markLimit) {
               newLength = this.markLimit;
            }

            char[] newbuf = new char[newLength];
            System.arraycopy(this.buf, 0, newbuf, 0, this.buf.length);
            this.buf = newbuf;
         } else if (this.mark > 0) {
            System.arraycopy(this.buf, this.mark, this.buf, 0, this.buf.length - this.mark);
            this.pos -= this.mark;
            this.end -= this.mark;
            this.mark = 0;
         }

         int count = this.in.read(this.buf, this.pos, this.buf.length - this.pos);
         if (count != -1) {
            this.end += count;
         }

         return count;
      } else {
         int result = this.in.read(this.buf, 0, this.buf.length);
         if (result > 0) {
            this.mark = -1;
            this.pos = 0;
            this.end = result;
         }

         return result;
      }
   }

   public void mark(int markLimit) throws IOException {
      if (markLimit < 0) {
         throw new IllegalArgumentException();
      } else {
         this.checkOpen();
         this.markLimit = markLimit;
         this.mark = this.pos;
      }
   }

   public boolean markSupported() {
      return true;
   }

   public int peek() throws IOException {
      this.mark(1);
      int c = this.read();
      this.reset();
      return c;
   }

   public int peek(char[] buf) throws IOException {
      int n = buf.length;
      this.mark(n);
      int c = this.read(buf, 0, n);
      this.reset();
      return c;
   }

   public int read() throws IOException {
      this.checkOpen();
      return this.pos >= this.end && this.fillBuf() == -1 ? -1 : this.buf[this.pos++];
   }

   public int read(char[] buffer, int offset, int length) throws IOException {
      this.checkOpen();
      if (offset >= 0 && offset <= buffer.length - length && length >= 0) {
         int outstanding = length;

         while(outstanding > 0) {
            int available = this.end - this.pos;
            if (available > 0) {
               int count = available >= outstanding ? outstanding : available;
               System.arraycopy(this.buf, this.pos, buffer, offset, count);
               this.pos += count;
               offset += count;
               outstanding -= count;
            }

            if (outstanding == 0 || outstanding < length && !this.in.ready()) {
               break;
            }

            if ((this.mark == -1 || this.pos - this.mark >= this.markLimit) && outstanding >= this.buf.length) {
               int count = this.in.read(buffer, offset, outstanding);
               if (count > 0) {
                  outstanding -= count;
                  this.mark = -1;
               }
               break;
            }

            if (this.fillBuf() == -1) {
               break;
            }
         }

         int count = length - outstanding;
         return count <= 0 && count != length ? -1 : count;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public String readLine() throws IOException {
      this.checkOpen();
      if (this.pos == this.end && this.fillBuf() == -1) {
         return null;
      } else {
         for(int charPos = this.pos; charPos < this.end; ++charPos) {
            char ch = this.buf[charPos];
            if (ch <= '\r') {
               if (ch == '\n') {
                  String res = new String(this.buf, this.pos, charPos - this.pos);
                  this.pos = charPos + 1;
                  return res;
               }

               if (ch == '\r') {
                  String res = new String(this.buf, this.pos, charPos - this.pos);
                  this.pos = charPos + 1;
                  if ((this.pos < this.end || this.fillBuf() != -1) && this.buf[this.pos] == '\n') {
                     ++this.pos;
                  }

                  return res;
               }
            }
         }

         char eol = 0;
         StringBuilder result = new StringBuilder(80);
         result.append(this.buf, this.pos, this.end - this.pos);

         while(true) {
            this.pos = this.end;
            if (eol == '\n') {
               return result.toString();
            }

            if (this.fillBuf() == -1) {
               return result.length() <= 0 && eol == 0 ? null : result.toString();
            }

            for(int charPos = this.pos; charPos < this.end; ++charPos) {
               char c = this.buf[charPos];
               if (eol != 0) {
                  if (eol == '\r' && c == '\n') {
                     if (charPos > this.pos) {
                        result.append(this.buf, this.pos, charPos - this.pos - 1);
                     }

                     this.pos = charPos + 1;
                  } else {
                     if (charPos > this.pos) {
                        result.append(this.buf, this.pos, charPos - this.pos - 1);
                     }

                     this.pos = charPos;
                  }

                  return result.toString();
               }

               if (c == '\n' || c == '\r') {
                  eol = c;
               }
            }

            if (eol == 0) {
               result.append(this.buf, this.pos, this.end - this.pos);
            } else {
               result.append(this.buf, this.pos, this.end - this.pos - 1);
            }
         }
      }
   }

   public boolean ready() throws IOException {
      this.checkOpen();
      return this.end - this.pos > 0 || this.in.ready();
   }

   public void reset() throws IOException {
      this.checkOpen();
      if (this.mark == -1) {
         throw new IOException("mark == -1");
      } else {
         this.pos = this.mark;
      }
   }

   public long skip(long amount) throws IOException {
      if (amount < 0L) {
         throw new IllegalArgumentException();
      } else {
         this.checkOpen();
         if (amount < 1L) {
            return 0L;
         } else if ((long)(this.end - this.pos) >= amount) {
            this.pos += Math.toIntExact(amount);
            return amount;
         } else {
            long read = (long)(this.end - this.pos);

            for(this.pos = this.end; read < amount; this.pos = this.end) {
               if (this.fillBuf() == -1) {
                  return read;
               }

               if ((long)(this.end - this.pos) >= amount - read) {
                  this.pos += Math.toIntExact(amount - read);
                  return amount;
               }

               read += (long)(this.end - this.pos);
            }

            return amount;
         }
      }
   }
}
