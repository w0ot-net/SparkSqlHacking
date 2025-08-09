package org.apache.avro.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class DirectBinaryEncoder extends BinaryEncoder {
   protected OutputStream out;
   private final byte[] buf = new byte[12];

   protected DirectBinaryEncoder(OutputStream out) {
      this.configure(out);
   }

   DirectBinaryEncoder configure(OutputStream out) {
      Objects.requireNonNull(out, "OutputStream cannot be null");
      this.out = out;
      return this;
   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public void writeBoolean(boolean b) throws IOException {
      this.out.write(b ? 1 : 0);
   }

   public void writeInt(int n) throws IOException {
      int val = n << 1 ^ n >> 31;
      if ((val & -128) == 0) {
         this.out.write(val);
      } else if ((val & -16384) == 0) {
         this.out.write(128 | val);
         this.out.write(val >>> 7);
      } else {
         int len = BinaryData.encodeInt(n, this.buf, 0);
         this.out.write(this.buf, 0, len);
      }
   }

   public void writeLong(long n) throws IOException {
      long val = n << 1 ^ n >> 63;
      if ((val & -2147483648L) != 0L) {
         int len = BinaryData.encodeLong(n, this.buf, 0);
         this.out.write(this.buf, 0, len);
      } else {
         int i;
         for(i = (int)val; (i & -128) != 0; i >>>= 7) {
            this.out.write((byte)((128 | i) & 255));
         }

         this.out.write((byte)i);
      }
   }

   public void writeFloat(float f) throws IOException {
      int len = BinaryData.encodeFloat(f, this.buf, 0);
      this.out.write(this.buf, 0, len);
   }

   public void writeDouble(double d) throws IOException {
      int len = BinaryData.encodeDouble(d, this.buf, 0);
      this.out.write(this.buf, 0, len);
   }

   public void writeFixed(byte[] bytes, int start, int len) throws IOException {
      this.out.write(bytes, start, len);
   }

   protected void writeZero() throws IOException {
      this.out.write(0);
   }

   public int bytesBuffered() {
      return 0;
   }
}
