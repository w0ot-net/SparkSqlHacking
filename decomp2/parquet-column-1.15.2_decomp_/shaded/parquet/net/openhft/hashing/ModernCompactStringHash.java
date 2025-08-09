package shaded.parquet.net.openhft.hashing;

import java.lang.reflect.Field;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
enum ModernCompactStringHash implements StringHash {
   INSTANCE;

   private static final long valueOffset;
   private static final boolean enableCompactStrings;
   private static final Access compactLatin1Access = CompactLatin1CharSequenceAccess.INSTANCE;

   public long longHash(String s, LongHashFunction hashFunction, int off, int len) {
      int sl = s.length();
      if (len > 0 && sl > 0) {
         byte[] value = (byte[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
         if (enableCompactStrings && sl == value.length) {
            Util.checkArrayOffs(sl, off, len);
            return hashFunction.hash(value, compactLatin1Access, (long)off * 2L, (long)len * 2L);
         } else {
            return hashFunction.hashBytes(value, off * 2, len * 2);
         }
      } else {
         Util.checkArrayOffs(sl, off, len);
         return hashFunction.hashVoid();
      }
   }

   public void hash(String s, LongTupleHashFunction hashFunction, int off, int len, long[] result) {
      int sl = s.length();
      if (len > 0 && sl > 0) {
         byte[] value = (byte[])UnsafeAccess.UNSAFE.getObject(s, valueOffset);
         if (enableCompactStrings && sl == value.length) {
            Util.checkArrayOffs(sl, off, len);
            hashFunction.hash(value, compactLatin1Access, (long)off * 2L, (long)len * 2L, result);
         } else {
            hashFunction.hashBytes(value, off * 2, len * 2, result);
         }
      } else {
         Util.checkArrayOffs(sl, off, len);
         hashFunction.hashVoid(result);
      }

   }

   static {
      try {
         Field valueField = String.class.getDeclaredField("value");
         valueOffset = UnsafeAccess.UNSAFE.objectFieldOffset(valueField);
         byte[] value = (byte[])UnsafeAccess.UNSAFE.getObject("A", valueOffset);
         enableCompactStrings = 1 == value.length;
      } catch (NoSuchFieldException e) {
         throw new AssertionError(e);
      }
   }
}
