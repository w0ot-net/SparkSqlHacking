package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

public abstract class Access {
   public static Access unsafe() {
      return UnsafeAccess.INSTANCE;
   }

   public static Access toByteBuffer() {
      return ByteBufferAccess.INSTANCE;
   }

   public static Access toNativeCharSequence() {
      return CharSequenceAccess.nativeCharSequenceAccess();
   }

   public static Access toCharSequence(ByteOrder backingOrder) {
      return CharSequenceAccess.charSequenceAccess(backingOrder);
   }

   protected Access() {
   }

   public long getLong(Object input, long offset) {
      return this.byteOrder(input) == ByteOrder.LITTLE_ENDIAN ? this.getUnsignedInt(input, offset) | this.getUnsignedInt(input, offset + 4L) << 32 : this.getUnsignedInt(input, offset + 4L) | this.getUnsignedInt(input, offset) << 32;
   }

   public long getUnsignedInt(Object input, long offset) {
      return (long)this.getInt(input, offset) & 4294967295L;
   }

   public int getInt(Object input, long offset) {
      return this.byteOrder(input) == ByteOrder.LITTLE_ENDIAN ? this.getUnsignedShort(input, offset) | this.getUnsignedShort(input, offset + 2L) << 16 : this.getUnsignedShort(input, offset + 2L) | this.getUnsignedShort(input, offset) << 16;
   }

   public int getUnsignedShort(Object input, long offset) {
      return this.byteOrder(input) == ByteOrder.LITTLE_ENDIAN ? this.getUnsignedByte(input, offset) | this.getUnsignedByte(input, offset + 1L) << 8 : this.getUnsignedByte(input, offset + 1L) | this.getUnsignedByte(input, offset) << 8;
   }

   public int getShort(Object input, long offset) {
      return (short)this.getUnsignedShort(input, offset);
   }

   public int getUnsignedByte(Object input, long offset) {
      return this.getByte(input, offset) & 255;
   }

   public abstract int getByte(Object var1, long var2);

   public long i64(Object input, long offset) {
      return this.getLong(input, offset);
   }

   public long u32(Object input, long offset) {
      return this.getUnsignedInt(input, offset);
   }

   public int i32(Object input, long offset) {
      return this.getInt(input, offset);
   }

   public int u16(Object input, long offset) {
      return this.getUnsignedShort(input, offset);
   }

   public int i16(Object input, long offset) {
      return this.getShort(input, offset);
   }

   public int u8(Object input, long offset) {
      return this.getUnsignedByte(input, offset);
   }

   public int i8(Object input, long offset) {
      return this.getByte(input, offset);
   }

   public abstract ByteOrder byteOrder(Object var1);

   public Access byteOrder(Object input, ByteOrder byteOrder) {
      return this.byteOrder(input) == byteOrder ? this : this.reverseAccess();
   }

   protected abstract Access reverseAccess();

   static Access newDefaultReverseAccess(Access access) {
      return (Access)(access instanceof ReverseAccess ? access.reverseAccess() : new ReverseAccess(access));
   }

   private static class ReverseAccess extends Access {
      final Access access;

      private ReverseAccess(Access access) {
         this.access = access;
      }

      public long getLong(Object input, long offset) {
         return Long.reverseBytes(this.access.getLong(input, offset));
      }

      public long getUnsignedInt(Object input, long offset) {
         return Long.reverseBytes(this.access.getUnsignedInt(input, offset)) >>> 32;
      }

      public int getInt(Object input, long offset) {
         return Integer.reverseBytes(this.access.getInt(input, offset));
      }

      public int getUnsignedShort(Object input, long offset) {
         return Integer.reverseBytes(this.access.getUnsignedShort(input, offset)) >>> 16;
      }

      public int getShort(Object input, long offset) {
         return Integer.reverseBytes(this.access.getShort(input, offset)) >> 16;
      }

      public int getUnsignedByte(Object input, long offset) {
         return this.access.getUnsignedByte(input, offset);
      }

      public int getByte(Object input, long offset) {
         return this.access.getByte(input, offset);
      }

      public ByteOrder byteOrder(Object input) {
         return ByteOrder.LITTLE_ENDIAN == this.access.byteOrder(input) ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
      }

      protected Access reverseAccess() {
         return this.access;
      }
   }
}
