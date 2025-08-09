package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

public abstract class CharSequenceAccess extends Access {
   static CharSequenceAccess charSequenceAccess(ByteOrder order) {
      return order == ByteOrder.LITTLE_ENDIAN ? CharSequenceAccess.LittleEndianCharSequenceAccess.INSTANCE : CharSequenceAccess.BigEndianCharSequenceAccess.INSTANCE;
   }

   static CharSequenceAccess nativeCharSequenceAccess() {
      return charSequenceAccess(ByteOrder.nativeOrder());
   }

   private static int ix(long offset) {
      return (int)(offset >> 1);
   }

   protected static long getLong(CharSequence input, long offset, int char0Off, int char1Off, int char2Off, int char3Off, int char4Off, int delta) {
      int base = ix(offset);
      if (0 == ((int)offset & 1)) {
         long char0 = (long)input.charAt(base + char0Off);
         long char1 = (long)input.charAt(base + char1Off);
         long char2 = (long)input.charAt(base + char2Off);
         long char3 = (long)input.charAt(base + char3Off);
         return char0 | char1 << 16 | char2 << 32 | char3 << 48;
      } else {
         long char0 = (long)(input.charAt(base + char0Off + delta) >>> 8);
         long char1 = (long)input.charAt(base + char1Off + delta);
         long char2 = (long)input.charAt(base + char2Off + delta);
         long char3 = (long)input.charAt(base + char3Off + delta);
         long char4 = (long)input.charAt(base + char4Off);
         return char0 | char1 << 8 | char2 << 24 | char3 << 40 | char4 << 56;
      }
   }

   protected static long getUnsignedInt(CharSequence input, long offset, int char0Off, int char1Off, int char2Off, int delta) {
      int base = ix(offset);
      if (0 == ((int)offset & 1)) {
         long char0 = (long)input.charAt(base + char0Off);
         long char1 = (long)input.charAt(base + char1Off);
         return char0 | char1 << 16;
      } else {
         long char0 = (long)(input.charAt(base + char0Off + delta) >>> 8);
         long char1 = (long)input.charAt(base + char1Off + delta);
         long char2 = (long)Primitives.unsignedByte(input.charAt(base + char2Off));
         return char0 | char1 << 8 | char2 << 24;
      }
   }

   protected static char getUnsignedShort(CharSequence input, long offset, int char1Off, int delta) {
      if (0 == ((int)offset & 1)) {
         return input.charAt(ix(offset));
      } else {
         int base = ix(offset);
         int char0 = input.charAt(base + delta) >>> 8;
         int char1 = input.charAt(base + char1Off);
         return (char)(char0 | char1 << 8);
      }
   }

   protected static int getUnsignedByte(CharSequence input, long offset, int shift) {
      return Primitives.unsignedByte(input.charAt(ix(offset)) >> shift);
   }

   private CharSequenceAccess() {
   }

   public int getInt(CharSequence input, long offset) {
      return (int)this.getUnsignedInt(input, offset);
   }

   public int getShort(CharSequence input, long offset) {
      return (short)this.getUnsignedShort(input, offset);
   }

   public int getByte(CharSequence input, long offset) {
      return (byte)this.getUnsignedByte(input, offset);
   }

   private static class LittleEndianCharSequenceAccess extends CharSequenceAccess {
      private static final CharSequenceAccess INSTANCE = new LittleEndianCharSequenceAccess();
      private static final Access INSTANCE_REVERSE;

      public long getLong(CharSequence input, long offset) {
         return getLong(input, offset, 0, 1, 2, 3, 4, 0);
      }

      public long getUnsignedInt(CharSequence input, long offset) {
         return getUnsignedInt(input, offset, 0, 1, 2, 0);
      }

      public int getUnsignedShort(CharSequence input, long offset) {
         return getUnsignedShort(input, offset, 1, 0);
      }

      public int getUnsignedByte(CharSequence input, long offset) {
         return getUnsignedByte(input, offset, ((int)offset & 1) << 3);
      }

      public ByteOrder byteOrder(CharSequence input) {
         return ByteOrder.LITTLE_ENDIAN;
      }

      protected Access reverseAccess() {
         return INSTANCE_REVERSE;
      }

      static {
         INSTANCE_REVERSE = Access.newDefaultReverseAccess(INSTANCE);
      }
   }

   private static class BigEndianCharSequenceAccess extends CharSequenceAccess {
      private static final CharSequenceAccess INSTANCE = new BigEndianCharSequenceAccess();
      private static final Access INSTANCE_REVERSE;

      public long getLong(CharSequence input, long offset) {
         return getLong(input, offset, 3, 2, 1, 0, 0, 1);
      }

      public long getUnsignedInt(CharSequence input, long offset) {
         return getUnsignedInt(input, offset, 1, 0, 0, 1);
      }

      public int getUnsignedShort(CharSequence input, long offset) {
         return getUnsignedShort(input, offset, 0, 1);
      }

      public int getUnsignedByte(CharSequence input, long offset) {
         return getUnsignedByte(input, offset, ((int)offset & 1 ^ 1) << 3);
      }

      public ByteOrder byteOrder(CharSequence input) {
         return ByteOrder.BIG_ENDIAN;
      }

      protected Access reverseAccess() {
         return INSTANCE_REVERSE;
      }

      static {
         INSTANCE_REVERSE = Access.newDefaultReverseAccess(INSTANCE);
      }
   }
}
