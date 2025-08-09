package org.apache.hadoop.hive.common.io;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.IOException;
import java.io.OutputStream;

public class NonSyncByteArrayOutputStream extends ByteArrayOutputStream {
   public NonSyncByteArrayOutputStream(int size) {
      super(size);
   }

   public NonSyncByteArrayOutputStream() {
   }

   public byte[] getData() {
      return this.buf;
   }

   public int getLength() {
      return this.count;
   }

   public void setWritePosition(int writePosition) {
      this.count = writePosition;
   }

   public void reset() {
      this.count = 0;
   }

   public void write(DataInput in, int length) throws IOException {
      this.enLargeBuffer(length);
      in.readFully(this.buf, this.count, length);
      this.count += length;
   }

   public void write(int b) {
      this.enLargeBuffer(1);
      this.buf[this.count] = (byte)b;
      ++this.count;
   }

   private int enLargeBuffer(int increment) {
      int temp = this.count + increment;
      int newLen = temp;
      if (temp > this.buf.length) {
         if (this.buf.length << 1 > temp) {
            newLen = this.buf.length << 1;
         }

         byte[] newbuf = new byte[newLen];
         System.arraycopy(this.buf, 0, newbuf, 0, this.count);
         this.buf = newbuf;
      }

      return newLen;
   }

   public void write(byte[] b, int off, int len) {
      if (off >= 0 && off <= b.length && len >= 0 && off + len <= b.length && off + len >= 0) {
         if (len != 0) {
            this.enLargeBuffer(len);
            System.arraycopy(b, off, this.buf, this.count, len);
            this.count += len;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void writeTo(OutputStream out) throws IOException {
      out.write(this.buf, 0, this.count);
   }
}
