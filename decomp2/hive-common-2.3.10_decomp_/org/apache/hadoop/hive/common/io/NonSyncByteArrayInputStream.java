package org.apache.hadoop.hive.common.io;

import java.io.ByteArrayInputStream;

public class NonSyncByteArrayInputStream extends ByteArrayInputStream {
   public NonSyncByteArrayInputStream() {
      super(new byte[0]);
   }

   public NonSyncByteArrayInputStream(byte[] bs) {
      super(bs);
   }

   public NonSyncByteArrayInputStream(byte[] buf, int offset, int length) {
      super(buf, offset, length);
   }

   public void reset(byte[] input, int start, int length) {
      this.buf = input;
      this.count = start + length;
      this.mark = start;
      this.pos = start;
   }

   public int getPosition() {
      return this.pos;
   }

   public int getLength() {
      return this.count;
   }

   public int read() {
      return this.pos < this.count ? this.buf[this.pos++] & 255 : -1;
   }

   public int read(byte[] b, int off, int len) {
      if (b == null) {
         throw new NullPointerException();
      } else if (off >= 0 && len >= 0 && len <= b.length - off) {
         if (this.pos >= this.count) {
            return -1;
         } else {
            if (this.pos + len > this.count) {
               len = this.count - this.pos;
            }

            if (len <= 0) {
               return 0;
            } else {
               System.arraycopy(this.buf, this.pos, b, off, len);
               this.pos += len;
               return len;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public long skip(long n) {
      if ((long)this.pos + n > (long)this.count) {
         n = (long)(this.count - this.pos);
      }

      if (n < 0L) {
         return 0L;
      } else {
         this.pos = (int)((long)this.pos + n);
         return n;
      }
   }

   public int available() {
      return this.count - this.pos;
   }
}
