package shaded.parquet.net.openhft.hashing;

import java.lang.reflect.Field;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
enum ModernHotSpotStringHash implements StringHash {
   INSTANCE;

   private static final long valueOffset;

   public long longHash(String s, LongHashFunction hashFunction, int off, int len) {
      char[] value = (char[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
      return hashFunction.hashChars(value, off, len);
   }

   public void hash(String s, LongTupleHashFunction hashFunction, int off, int len, long[] result) {
      char[] value = (char[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
      hashFunction.hashChars(value, off, len, result);
   }

   static {
      try {
         Field valueField = String.class.getDeclaredField("value");
         valueOffset = UnsafeAccess.UNSAFE.objectFieldOffset(valueField);
      } catch (NoSuchFieldException e) {
         throw new AssertionError(e);
      }
   }
}
