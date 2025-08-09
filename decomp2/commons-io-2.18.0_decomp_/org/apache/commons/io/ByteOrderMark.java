package org.apache.commons.io;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public class ByteOrderMark implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final ByteOrderMark UTF_8;
   public static final ByteOrderMark UTF_16BE;
   public static final ByteOrderMark UTF_16LE;
   public static final ByteOrderMark UTF_32BE;
   public static final ByteOrderMark UTF_32LE;
   public static final char UTF_BOM = '\ufeff';
   private final String charsetName;
   private final int[] bytes;

   public ByteOrderMark(String charsetName, int... bytes) {
      Objects.requireNonNull(charsetName, "charsetName");
      Objects.requireNonNull(bytes, "bytes");
      if (charsetName.isEmpty()) {
         throw new IllegalArgumentException("No charsetName specified");
      } else if (bytes.length == 0) {
         throw new IllegalArgumentException("No bytes specified");
      } else {
         this.charsetName = charsetName;
         this.bytes = (int[])(([I)bytes).clone();
      }
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ByteOrderMark)) {
         return false;
      } else {
         ByteOrderMark bom = (ByteOrderMark)obj;
         if (this.bytes.length != bom.length()) {
            return false;
         } else {
            for(int i = 0; i < this.bytes.length; ++i) {
               if (this.bytes[i] != bom.get(i)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int get(int pos) {
      return this.bytes[pos];
   }

   public byte[] getBytes() {
      byte[] copy = IOUtils.byteArray(this.bytes.length);

      for(int i = 0; i < this.bytes.length; ++i) {
         copy[i] = (byte)this.bytes[i];
      }

      return copy;
   }

   public String getCharsetName() {
      return this.charsetName;
   }

   public int hashCode() {
      int hashCode = this.getClass().hashCode();

      for(int b : this.bytes) {
         hashCode += b;
      }

      return hashCode;
   }

   public int length() {
      return this.bytes.length;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.getClass().getSimpleName());
      builder.append('[');
      builder.append(this.charsetName);
      builder.append(": ");

      for(int i = 0; i < this.bytes.length; ++i) {
         if (i > 0) {
            builder.append(",");
         }

         builder.append("0x");
         builder.append(Integer.toHexString(255 & this.bytes[i]).toUpperCase(Locale.ROOT));
      }

      builder.append(']');
      return builder.toString();
   }

   static {
      UTF_8 = new ByteOrderMark(StandardCharsets.UTF_8.name(), new int[]{239, 187, 191});
      UTF_16BE = new ByteOrderMark(StandardCharsets.UTF_16BE.name(), new int[]{254, 255});
      UTF_16LE = new ByteOrderMark(StandardCharsets.UTF_16LE.name(), new int[]{255, 254});
      UTF_32BE = new ByteOrderMark("UTF-32BE", new int[]{0, 0, 254, 255});
      UTF_32LE = new ByteOrderMark("UTF-32LE", new int[]{255, 254, 0, 0});
   }
}
