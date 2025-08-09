package org.apache.avro.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.avro.SystemLimitException;
import org.apache.avro.io.BinaryData;

public class Utf8 implements Comparable, CharSequence, Externalizable {
   private static final byte[] EMPTY = new byte[0];
   private byte[] bytes;
   private int hash;
   private int length;
   private String string;

   public Utf8() {
      this.bytes = EMPTY;
   }

   public Utf8(String string) {
      byte[] bytes = getBytesFor(string);
      int length = bytes.length;
      SystemLimitException.checkMaxStringLength((long)length);
      this.bytes = bytes;
      this.length = length;
      this.string = string;
   }

   public Utf8(Utf8 other) {
      this.length = other.length;
      this.bytes = Arrays.copyOf(other.bytes, other.length);
      this.string = other.string;
      this.hash = other.hash;
   }

   public Utf8(byte[] bytes) {
      int length = bytes.length;
      SystemLimitException.checkMaxStringLength((long)length);
      this.bytes = bytes;
      this.length = length;
   }

   public byte[] getBytes() {
      return this.bytes;
   }

   /** @deprecated */
   @Deprecated
   public int getLength() {
      return this.length;
   }

   public int getByteLength() {
      return this.length;
   }

   /** @deprecated */
   @Deprecated
   public Utf8 setLength(int newLength) {
      return this.setByteLength(newLength);
   }

   public Utf8 setByteLength(int newLength) {
      SystemLimitException.checkMaxStringLength((long)newLength);
      if (this.bytes.length < newLength) {
         this.bytes = Arrays.copyOf(this.bytes, newLength);
      }

      this.length = newLength;
      this.string = null;
      this.hash = 0;
      return this;
   }

   public Utf8 set(String string) {
      byte[] bytes = getBytesFor(string);
      int length = bytes.length;
      SystemLimitException.checkMaxStringLength((long)length);
      this.bytes = bytes;
      this.length = length;
      this.string = string;
      this.hash = 0;
      return this;
   }

   public Utf8 set(Utf8 other) {
      if (this.bytes.length < other.length) {
         this.bytes = new byte[other.length];
      }

      this.length = other.length;
      System.arraycopy(other.bytes, 0, this.bytes, 0, this.length);
      this.string = other.string;
      this.hash = other.hash;
      return this;
   }

   public String toString() {
      if (this.length == 0) {
         return "";
      } else {
         if (this.string == null) {
            this.string = new String(this.bytes, 0, this.length, StandardCharsets.UTF_8);
         }

         return this.string;
      }
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Utf8)) {
         return false;
      } else {
         Utf8 that = (Utf8)o;
         if (this.length != that.length) {
            return false;
         } else {
            byte[] thatBytes = that.bytes;

            for(int i = 0; i < this.length; ++i) {
               if (this.bytes[i] != thatBytes[i]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int h = this.hash;
      if (h == 0) {
         byte[] bytes = this.bytes;
         int length = this.length;

         for(int i = 0; i < length; ++i) {
            h = h * 31 + bytes[i];
         }

         this.hash = h;
      }

      return h;
   }

   public int compareTo(Utf8 that) {
      return BinaryData.compareBytes(this.bytes, 0, this.length, that.bytes, 0, that.length);
   }

   public char charAt(int index) {
      return this.toString().charAt(index);
   }

   public int length() {
      return this.toString().length();
   }

   public CharSequence subSequence(int start, int end) {
      return this.toString().subSequence(start, end);
   }

   public static byte[] getBytesFor(String str) {
      return str.getBytes(StandardCharsets.UTF_8);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeInt(this.bytes.length);
      out.write(this.bytes);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.setByteLength(in.readInt());
      in.readFully(this.bytes);
   }
}
