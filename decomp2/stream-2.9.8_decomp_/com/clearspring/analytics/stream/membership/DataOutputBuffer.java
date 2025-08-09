package com.clearspring.analytics.stream.membership;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class DataOutputBuffer extends DataOutputStream {
   private Buffer buffer;

   public DataOutputBuffer() {
      this(new Buffer());
   }

   private DataOutputBuffer(Buffer buffer) {
      super(buffer);
      this.buffer = buffer;
   }

   public byte[] getData() {
      return this.buffer.getData();
   }

   public int getLength() {
      return this.buffer.getLength();
   }

   public DataOutputBuffer reset() {
      this.written = 0;
      this.buffer.reset();
      return this;
   }

   public void write(DataInput in, int length) throws IOException {
      this.buffer.write(in, length);
   }

   private static class Buffer extends ByteArrayOutputStream {
      private Buffer() {
      }

      public byte[] getData() {
         return Arrays.copyOf(this.buf, this.getLength());
      }

      public int getLength() {
         return this.count;
      }

      public void reset() {
         this.count = 0;
      }

      public void write(DataInput in, int len) throws IOException {
         int newcount = this.count + len;
         if (newcount > this.buf.length) {
            byte[] newbuf = new byte[Math.max(this.buf.length << 1, newcount)];
            System.arraycopy(this.buf, 0, newbuf, 0, this.count);
            this.buf = newbuf;
         }

         in.readFully(this.buf, this.count, len);
         this.count = newcount;
      }
   }
}
