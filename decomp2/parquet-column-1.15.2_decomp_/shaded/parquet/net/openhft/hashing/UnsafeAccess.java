package shaded.parquet.net.openhft.hashing;

import java.lang.reflect.Field;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

public class UnsafeAccess extends Access {
   static final UnsafeAccess INSTANCE;
   private static final Access INSTANCE_NON_NATIVE;
   static final UnsafeAccess OLD_INSTANCE;
   static final Unsafe UNSAFE;
   static final long BOOLEAN_BASE;
   static final long BYTE_BASE;
   static final long CHAR_BASE;
   static final long SHORT_BASE;
   static final long INT_BASE;
   static final long LONG_BASE;
   static final byte TRUE_BYTE_VALUE;
   static final byte FALSE_BYTE_VALUE;

   private UnsafeAccess() {
   }

   public long getLong(Object input, long offset) {
      return UNSAFE.getLong(input, offset);
   }

   public long getUnsignedInt(Object input, long offset) {
      return Primitives.unsignedInt(this.getInt(input, offset));
   }

   public int getInt(Object input, long offset) {
      return UNSAFE.getInt(input, offset);
   }

   public int getUnsignedShort(Object input, long offset) {
      return Primitives.unsignedShort(this.getShort(input, offset));
   }

   public int getShort(Object input, long offset) {
      return UNSAFE.getShort(input, offset);
   }

   public int getUnsignedByte(Object input, long offset) {
      return Primitives.unsignedByte(this.getByte(input, offset));
   }

   public int getByte(Object input, long offset) {
      return UNSAFE.getByte(input, offset);
   }

   public ByteOrder byteOrder(Object input) {
      return ByteOrder.nativeOrder();
   }

   protected Access reverseAccess() {
      return INSTANCE_NON_NATIVE;
   }

   static {
      OLD_INSTANCE = (UnsafeAccess)(Primitives.NATIVE_LITTLE_ENDIAN ? new OldUnsafeAccessLittleEndian() : new OldUnsafeAccessBigEndian());

      try {
         Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
         theUnsafe.setAccessible(true);
         UNSAFE = (Unsafe)theUnsafe.get((Object)null);
         BOOLEAN_BASE = (long)UNSAFE.arrayBaseOffset(boolean[].class);
         BYTE_BASE = (long)UNSAFE.arrayBaseOffset(byte[].class);
         CHAR_BASE = (long)UNSAFE.arrayBaseOffset(char[].class);
         SHORT_BASE = (long)UNSAFE.arrayBaseOffset(short[].class);
         INT_BASE = (long)UNSAFE.arrayBaseOffset(int[].class);
         LONG_BASE = (long)UNSAFE.arrayBaseOffset(long[].class);
         TRUE_BYTE_VALUE = (byte)UNSAFE.getInt(new boolean[]{true, true, true, true}, BOOLEAN_BASE);
         FALSE_BYTE_VALUE = (byte)UNSAFE.getInt(new boolean[]{false, false, false, false}, BOOLEAN_BASE);
      } catch (Exception e) {
         throw new AssertionError(e);
      }

      boolean hasGetByte = true;

      try {
         UNSAFE.getByte(new byte[1], BYTE_BASE);
      } catch (Throwable var2) {
         hasGetByte = false;
      }

      INSTANCE = hasGetByte ? new UnsafeAccess() : OLD_INSTANCE;
      INSTANCE_NON_NATIVE = Access.newDefaultReverseAccess(INSTANCE);
   }

   private static class OldUnsafeAccessLittleEndian extends UnsafeAccess {
      private OldUnsafeAccessLittleEndian() {
      }

      public int getShort(Object input, long offset) {
         return UNSAFE.getInt(input, offset - 2L) >> 16;
      }

      public int getByte(Object input, long offset) {
         return UNSAFE.getInt(input, offset - 3L) >> 24;
      }
   }

   private static class OldUnsafeAccessBigEndian extends UnsafeAccess {
      private OldUnsafeAccessBigEndian() {
      }

      public int getShort(Object input, long offset) {
         return (short)UNSAFE.getInt(input, offset - 2L);
      }

      public int getByte(Object input, long offset) {
         return (byte)UNSAFE.getInt(input, offset - 3L);
      }
   }
}
