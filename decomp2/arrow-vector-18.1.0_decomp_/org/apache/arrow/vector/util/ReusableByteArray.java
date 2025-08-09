package org.apache.arrow.vector.util;

import java.util.Arrays;
import java.util.Base64;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.ReusableBuffer;

public class ReusableByteArray implements ReusableBuffer {
   protected static final byte[] EMPTY_BYTES = new byte[0];
   protected byte[] bytes;
   protected int length;

   public ReusableByteArray() {
      this.bytes = EMPTY_BYTES;
   }

   public ReusableByteArray(byte[] data) {
      this.bytes = Arrays.copyOfRange(data, 0, data.length);
      this.length = data.length;
   }

   public long getLength() {
      return (long)this.length;
   }

   public byte[] getBuffer() {
      return this.bytes;
   }

   public void set(ArrowBuf srcBytes, long start, long len) {
      this.setCapacity((int)len, false);
      srcBytes.getBytes(start, this.bytes, 0, (int)len);
      this.length = (int)len;
   }

   public void set(byte[] srcBytes, long start, long len) {
      this.setCapacity((int)len, false);
      System.arraycopy(srcBytes, (int)start, this.bytes, 0, (int)len);
      this.length = (int)len;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o == null) {
         return false;
      } else if (!(o instanceof ReusableByteArray)) {
         return false;
      } else {
         ReusableByteArray that = (ReusableByteArray)o;
         if (this.getLength() != that.getLength()) {
            return false;
         } else {
            for(int i = 0; i < this.length; ++i) {
               if (this.bytes[i] != that.bytes[i]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      if (this.bytes == null) {
         return 0;
      } else {
         int result = 1;

         for(int i = 0; i < this.length; ++i) {
            result = 31 * result + this.bytes[i];
         }

         return result;
      }
   }

   public String toString() {
      return Base64.getEncoder().encodeToString(Arrays.copyOfRange(this.bytes, 0, this.length));
   }

   protected void setCapacity(int len, boolean keepData) {
      if (this.bytes == null || this.bytes.length < len) {
         if (this.bytes != null && keepData) {
            this.bytes = Arrays.copyOf(this.bytes, Math.max(len, this.length << 1));
         } else {
            this.bytes = new byte[len];
         }
      }

   }
}
