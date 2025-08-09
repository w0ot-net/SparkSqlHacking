package org.apache.parquet.io.api;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.schema.PrimitiveComparator;

public abstract class Binary implements Comparable, Serializable {
   protected boolean isBackingBytesReused;
   public static final Binary EMPTY = fromConstantByteArray(new byte[0]);

   private Binary() {
   }

   public abstract String toStringUsingUTF8();

   public abstract int length();

   public abstract void writeTo(OutputStream var1) throws IOException;

   public abstract void writeTo(DataOutput var1) throws IOException;

   public abstract byte[] getBytes();

   public abstract byte[] getBytesUnsafe();

   public abstract Binary slice(int var1, int var2);

   abstract boolean equals(byte[] var1, int var2, int var3);

   abstract boolean equals(ByteBuffer var1, int var2, int var3);

   abstract boolean equals(Binary var1);

   /** @deprecated */
   @Deprecated
   public abstract int compareTo(Binary var1);

   abstract int lexicographicCompare(Binary var1);

   abstract int lexicographicCompare(byte[] var1, int var2, int var3);

   abstract int lexicographicCompare(ByteBuffer var1, int var2, int var3);

   public abstract ByteBuffer toByteBuffer();

   public short get2BytesLittleEndian() {
      throw new UnsupportedOperationException("Not implemented");
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else {
         return obj instanceof Binary ? this.equals((Binary)obj) : false;
      }
   }

   public String toString() {
      return "Binary{" + this.length() + (this.isBackingBytesReused ? " reused" : " constant") + " bytes, " + Arrays.toString(this.getBytesUnsafe()) + "}";
   }

   public Binary copy() {
      return this.isBackingBytesReused ? fromConstantByteArray(this.getBytes()) : this;
   }

   public boolean isBackingBytesReused() {
      return this.isBackingBytesReused;
   }

   public static Binary fromReusedByteArray(byte[] value, int offset, int length) {
      return new ByteArraySliceBackedBinary(value, offset, length, true);
   }

   public static Binary fromConstantByteArray(byte[] value, int offset, int length) {
      return new ByteArraySliceBackedBinary(value, offset, length, false);
   }

   /** @deprecated */
   @Deprecated
   public static Binary fromByteArray(byte[] value, int offset, int length) {
      return fromReusedByteArray(value, offset, length);
   }

   public static Binary fromReusedByteArray(byte[] value) {
      return new ByteArrayBackedBinary(value, true);
   }

   public static Binary fromConstantByteArray(byte[] value) {
      return new ByteArrayBackedBinary(value, false);
   }

   /** @deprecated */
   @Deprecated
   public static Binary fromByteArray(byte[] value) {
      return fromReusedByteArray(value);
   }

   public static Binary fromReusedByteBuffer(ByteBuffer value, int offset, int length) {
      return new ByteBufferBackedBinary(value, offset, length, true);
   }

   public static Binary fromConstantByteBuffer(ByteBuffer value, int offset, int length) {
      return new ByteBufferBackedBinary(value, offset, length, false);
   }

   public static Binary fromReusedByteBuffer(ByteBuffer value) {
      return new ByteBufferBackedBinary(value, true);
   }

   public static Binary fromConstantByteBuffer(ByteBuffer value) {
      return new ByteBufferBackedBinary(value, false);
   }

   /** @deprecated */
   @Deprecated
   public static Binary fromByteBuffer(ByteBuffer value) {
      return fromReusedByteBuffer(value);
   }

   public static Binary fromString(String value) {
      return new FromStringBinary(value);
   }

   public static Binary fromCharSequence(CharSequence value) {
      return new FromCharSequenceBinary(value);
   }

   public static int lexicographicCompare(Binary one, Binary other) {
      return one.lexicographicCompare(other);
   }

   private static final int hashCode(byte[] array, int offset, int length) {
      int result = 1;

      for(int i = offset; i < offset + length; ++i) {
         byte b = array[i];
         result = 31 * result + b;
      }

      return result;
   }

   private static final int hashCode(ByteBuffer buf, int offset, int length) {
      int result = 1;

      for(int i = offset; i < offset + length; ++i) {
         byte b = buf.get(i);
         result = 31 * result + b;
      }

      return result;
   }

   private static final boolean equals(ByteBuffer buf1, int offset1, int length1, ByteBuffer buf2, int offset2, int length2) {
      if (buf1 == null && buf2 == null) {
         return true;
      } else if (buf1 != null && buf2 != null) {
         if (length1 != length2) {
            return false;
         } else {
            for(int i = 0; i < length1; ++i) {
               if (buf1.get(i + offset1) != buf2.get(i + offset2)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private static final boolean equals(byte[] array1, int offset1, int length1, ByteBuffer buf, int offset2, int length2) {
      if (array1 == null && buf == null) {
         return true;
      } else if (array1 != null && buf != null) {
         if (length1 != length2) {
            return false;
         } else {
            for(int i = 0; i < length1; ++i) {
               if (array1[i + offset1] != buf.get(i + offset2)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private static final boolean equals(byte[] array1, int offset1, int length1, byte[] array2, int offset2, int length2) {
      if (array1 == null && array2 == null) {
         return true;
      } else if (array1 != null && array2 != null) {
         if (length1 != length2) {
            return false;
         } else if (array1 == array2 && offset1 == offset2) {
            return true;
         } else {
            for(int i = 0; i < length1; ++i) {
               if (array1[i + offset1] != array2[i + offset2]) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private static final int lexicographicCompare(byte[] array1, int offset1, int length1, byte[] array2, int offset2, int length2) {
      if (array1 == null && array2 == null) {
         return 0;
      } else if (array1 != null && array2 != null) {
         int minLen = Math.min(length1, length2);

         for(int i = 0; i < minLen; ++i) {
            int res = unsignedCompare(array1[i + offset1], array2[i + offset2]);
            if (res != 0) {
               return res;
            }
         }

         return length1 - length2;
      } else {
         return array1 != null ? 1 : -1;
      }
   }

   private static final int lexicographicCompare(byte[] array, int offset1, int length1, ByteBuffer buffer, int offset2, int length2) {
      if (array == null && buffer == null) {
         return 0;
      } else if (array != null && buffer != null) {
         int minLen = Math.min(length1, length2);

         for(int i = 0; i < minLen; ++i) {
            int res = unsignedCompare(array[i + offset1], buffer.get(i + offset2));
            if (res != 0) {
               return res;
            }
         }

         return length1 - length2;
      } else {
         return array != null ? 1 : -1;
      }
   }

   private static final int lexicographicCompare(ByteBuffer buffer1, int offset1, int length1, ByteBuffer buffer2, int offset2, int length2) {
      if (buffer1 == null && buffer2 == null) {
         return 0;
      } else if (buffer1 != null && buffer2 != null) {
         int minLen = Math.min(length1, length2);

         for(int i = 0; i < minLen; ++i) {
            int res = unsignedCompare(buffer1.get(i + offset1), buffer2.get(i + offset2));
            if (res != 0) {
               return res;
            }
         }

         return length1 - length2;
      } else {
         return buffer1 != null ? 1 : -1;
      }
   }

   private static int unsignedCompare(byte b1, byte b2) {
      return toUnsigned(b1) - toUnsigned(b2);
   }

   private static final int toUnsigned(byte b) {
      return b & 255;
   }

   private static class ByteArraySliceBackedBinary extends Binary {
      private final byte[] value;
      private final int offset;
      private final int length;

      public ByteArraySliceBackedBinary(byte[] value, int offset, int length, boolean isBackingBytesReused) {
         this.value = value;
         this.offset = offset;
         this.length = length;
         this.isBackingBytesReused = isBackingBytesReused;
      }

      public String toStringUsingUTF8() {
         return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(this.value, this.offset, this.length)).toString();
      }

      public int length() {
         return this.length;
      }

      public void writeTo(OutputStream out) throws IOException {
         out.write(this.value, this.offset, this.length);
      }

      public byte[] getBytes() {
         return Arrays.copyOfRange(this.value, this.offset, this.offset + this.length);
      }

      public byte[] getBytesUnsafe() {
         return this.getBytes();
      }

      public Binary slice(int start, int length) {
         return this.isBackingBytesReused ? Binary.fromReusedByteArray(this.value, this.offset + start, length) : Binary.fromConstantByteArray(this.value, this.offset + start, length);
      }

      public int hashCode() {
         return Binary.hashCode(this.value, this.offset, this.length);
      }

      boolean equals(Binary other) {
         return other.equals(this.value, this.offset, this.length);
      }

      boolean equals(byte[] other, int otherOffset, int otherLength) {
         return Binary.equals(this.value, this.offset, this.length, other, otherOffset, otherLength);
      }

      boolean equals(ByteBuffer bytes, int otherOffset, int otherLength) {
         return Binary.equals(this.value, this.offset, this.length, bytes, otherOffset, otherLength);
      }

      public int compareTo(Binary other) {
         return PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR.compare(this, other);
      }

      int lexicographicCompare(Binary other) {
         return -other.lexicographicCompare(this.value, this.offset, this.length);
      }

      int lexicographicCompare(byte[] other, int otherOffset, int otherLength) {
         return Binary.lexicographicCompare(this.value, this.offset, this.length, other, otherOffset, otherLength);
      }

      int lexicographicCompare(ByteBuffer other, int otherOffset, int otherLength) {
         return Binary.lexicographicCompare(this.value, this.offset, this.length, other, otherOffset, otherLength);
      }

      public ByteBuffer toByteBuffer() {
         return ByteBuffer.wrap(this.value, this.offset, this.length);
      }

      public short get2BytesLittleEndian() {
         if (this.length != 2) {
            throw new IllegalArgumentException("length must be 2");
         } else {
            return (short)((this.value[this.offset + 1] & 255) << 8 | this.value[this.offset] & 255);
         }
      }

      public void writeTo(DataOutput out) throws IOException {
         out.write(this.value, this.offset, this.length);
      }
   }

   private static class FromStringBinary extends ByteBufferBackedBinary {
      public FromStringBinary(String value) {
         super(encodeUTF8(value), false);
      }

      public String toString() {
         return "Binary{\"" + this.toStringUsingUTF8() + "\"}";
      }

      private static ByteBuffer encodeUTF8(String value) {
         return ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8));
      }
   }

   private static class FromCharSequenceBinary extends ByteBufferBackedBinary {
      private static final ThreadLocal ENCODER;

      public FromCharSequenceBinary(CharSequence value) {
         super(encodeUTF8(value), false);
      }

      public String toString() {
         return "Binary{\"" + this.toStringUsingUTF8() + "\"}";
      }

      private static ByteBuffer encodeUTF8(CharSequence value) {
         try {
            return ((CharsetEncoder)ENCODER.get()).encode(CharBuffer.wrap(value));
         } catch (CharacterCodingException e) {
            throw new ParquetEncodingException("UTF-8 not supported.", e);
         }
      }

      static {
         Charset var10000 = StandardCharsets.UTF_8;
         var10000.getClass();
         ENCODER = ThreadLocal.withInitial(var10000::newEncoder);
      }
   }

   private static class ByteArrayBackedBinary extends Binary {
      private final byte[] value;

      public ByteArrayBackedBinary(byte[] value, boolean isBackingBytesReused) {
         this.value = value;
         this.isBackingBytesReused = isBackingBytesReused;
      }

      public String toStringUsingUTF8() {
         return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(this.value)).toString();
      }

      public int length() {
         return this.value.length;
      }

      public void writeTo(OutputStream out) throws IOException {
         out.write(this.value);
      }

      public byte[] getBytes() {
         return Arrays.copyOfRange(this.value, 0, this.value.length);
      }

      public byte[] getBytesUnsafe() {
         return this.value;
      }

      public Binary slice(int start, int length) {
         return this.isBackingBytesReused ? Binary.fromReusedByteArray(this.value, start, length) : Binary.fromConstantByteArray(this.value, start, length);
      }

      public int hashCode() {
         return Binary.hashCode((byte[])this.value, 0, this.value.length);
      }

      boolean equals(Binary other) {
         return other.equals((byte[])this.value, 0, this.value.length);
      }

      boolean equals(byte[] other, int otherOffset, int otherLength) {
         return Binary.equals((byte[])this.value, 0, this.value.length, (byte[])other, otherOffset, otherLength);
      }

      boolean equals(ByteBuffer bytes, int otherOffset, int otherLength) {
         return Binary.equals((byte[])this.value, 0, this.value.length, (ByteBuffer)bytes, otherOffset, otherLength);
      }

      public int compareTo(Binary other) {
         return PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR.compare(this, other);
      }

      int lexicographicCompare(Binary other) {
         return -other.lexicographicCompare((byte[])this.value, 0, this.value.length);
      }

      int lexicographicCompare(byte[] other, int otherOffset, int otherLength) {
         return Binary.lexicographicCompare((byte[])this.value, 0, this.value.length, (byte[])other, otherOffset, otherLength);
      }

      int lexicographicCompare(ByteBuffer other, int otherOffset, int otherLength) {
         return Binary.lexicographicCompare((byte[])this.value, 0, this.value.length, (ByteBuffer)other, otherOffset, otherLength);
      }

      public ByteBuffer toByteBuffer() {
         return ByteBuffer.wrap(this.value);
      }

      public short get2BytesLittleEndian() {
         if (this.value.length != 2) {
            throw new IllegalArgumentException("length must be 2");
         } else {
            return (short)((this.value[1] & 255) << 8 | this.value[0] & 255);
         }
      }

      public void writeTo(DataOutput out) throws IOException {
         out.write(this.value);
      }
   }

   private static class ByteBufferBackedBinary extends Binary {
      private ByteBuffer value;
      private transient byte[] cachedBytes;
      private int offset;
      private int length;

      public ByteBufferBackedBinary(ByteBuffer value, boolean isBackingBytesReused) {
         this.value = value;
         this.offset = value.position();
         this.length = value.remaining();
         this.isBackingBytesReused = isBackingBytesReused;
      }

      public ByteBufferBackedBinary(ByteBuffer value, int offset, int length, boolean isBackingBytesReused) {
         this.value = value;
         this.offset = offset;
         this.length = length;
         this.isBackingBytesReused = isBackingBytesReused;
      }

      public String toStringUsingUTF8() {
         String ret;
         if (this.value.hasArray()) {
            ret = new String(this.value.array(), this.value.arrayOffset() + this.offset, this.length, StandardCharsets.UTF_8);
         } else {
            int limit = this.value.limit();
            this.value.limit(this.offset + this.length);
            int position = this.value.position();
            this.value.position(this.offset);
            ret = StandardCharsets.UTF_8.decode(this.value).toString();
            this.value.limit(limit);
            this.value.position(position);
         }

         return ret;
      }

      public int length() {
         return this.length;
      }

      public void writeTo(OutputStream out) throws IOException {
         if (this.value.hasArray()) {
            out.write(this.value.array(), this.value.arrayOffset() + this.offset, this.length);
         } else {
            out.write(this.getBytesUnsafe(), 0, this.length);
         }

      }

      public byte[] getBytes() {
         byte[] bytes = new byte[this.length];
         int limit = this.value.limit();
         this.value.limit(this.offset + this.length);
         int position = this.value.position();
         this.value.position(this.offset);
         this.value.get(bytes);
         this.value.limit(limit);
         this.value.position(position);
         if (!this.isBackingBytesReused) {
            this.cachedBytes = bytes;
         }

         return bytes;
      }

      public byte[] getBytesUnsafe() {
         return this.cachedBytes != null ? this.cachedBytes : this.getBytes();
      }

      public Binary slice(int start, int length) {
         return Binary.fromConstantByteArray(this.getBytesUnsafe(), start, length);
      }

      public int hashCode() {
         return this.value.hasArray() ? Binary.hashCode(this.value.array(), this.value.arrayOffset() + this.offset, this.length) : Binary.hashCode(this.value, this.offset, this.length);
      }

      boolean equals(Binary other) {
         return this.value.hasArray() ? other.equals(this.value.array(), this.value.arrayOffset() + this.offset, this.length) : other.equals(this.value, this.offset, this.length);
      }

      boolean equals(byte[] other, int otherOffset, int otherLength) {
         return this.value.hasArray() ? Binary.equals(this.value.array(), this.value.arrayOffset() + this.offset, this.length, other, otherOffset, otherLength) : Binary.equals(other, otherOffset, otherLength, this.value, this.offset, this.length);
      }

      boolean equals(ByteBuffer otherBytes, int otherOffset, int otherLength) {
         return Binary.equals((ByteBuffer)this.value, 0, this.length, (ByteBuffer)otherBytes, otherOffset, otherLength);
      }

      public int compareTo(Binary other) {
         return PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR.compare(this, other);
      }

      int lexicographicCompare(Binary other) {
         return this.value.hasArray() ? -other.lexicographicCompare(this.value.array(), this.value.arrayOffset() + this.offset, this.length) : -other.lexicographicCompare(this.value, this.offset, this.length);
      }

      int lexicographicCompare(byte[] other, int otherOffset, int otherLength) {
         return this.value.hasArray() ? Binary.lexicographicCompare(this.value.array(), this.value.arrayOffset() + this.offset, this.length, other, otherOffset, otherLength) : -Binary.lexicographicCompare(other, otherOffset, otherLength, this.value, this.offset, this.length);
      }

      int lexicographicCompare(ByteBuffer other, int otherOffset, int otherLength) {
         return Binary.lexicographicCompare(this.value, this.offset, this.length, other, otherOffset, otherLength);
      }

      public ByteBuffer toByteBuffer() {
         ByteBuffer ret = this.value.duplicate();
         ret.position(this.offset);
         ret.limit(this.offset + this.length);
         return ret;
      }

      public short get2BytesLittleEndian() {
         if (this.length != 2) {
            throw new IllegalArgumentException("length must be 2");
         } else {
            return this.value.order(ByteOrder.LITTLE_ENDIAN).getShort(this.offset);
         }
      }

      public void writeTo(DataOutput out) throws IOException {
         out.write(this.getBytesUnsafe());
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         byte[] bytes = this.getBytesUnsafe();
         out.writeInt(bytes.length);
         out.write(bytes);
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         int length = in.readInt();
         byte[] bytes = new byte[length];
         in.readFully(bytes, 0, length);
         this.value = ByteBuffer.wrap(bytes);
         this.offset = 0;
         this.length = length;
      }

      private void readObjectNoData() throws ObjectStreamException {
         this.value = ByteBuffer.wrap(new byte[0]);
      }
   }
}
