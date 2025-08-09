package jodd.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeUtil {
   public static final Unsafe UNSAFE;
   private static final long STRING_VALUE_FIELD_OFFSET;
   private static final long STRING_OFFSET_FIELD_OFFSET;
   private static final long STRING_COUNT_FIELD_OFFSET;

   public static char[] getChars(String string) {
      if (UNSAFE != null) {
         char[] value = (char[])UNSAFE.getObject(string, STRING_VALUE_FIELD_OFFSET);
         if (STRING_OFFSET_FIELD_OFFSET != -1L) {
            int offset = UNSAFE.getInt(string, STRING_OFFSET_FIELD_OFFSET);
            int count = UNSAFE.getInt(string, STRING_COUNT_FIELD_OFFSET);
            if (offset == 0 && count == value.length) {
               return value;
            } else {
               char[] result = new char[count];
               System.arraycopy(value, offset, result, 0, count);
               return result;
            }
         } else {
            return value;
         }
      } else {
         return string.toCharArray();
      }
   }

   public static String createMutableString(char[] chars) {
      if (UNSAFE == null) {
         return null;
      } else {
         String mutable = new String();
         UNSAFE.putObject(mutable, STRING_VALUE_FIELD_OFFSET, chars);
         if (STRING_COUNT_FIELD_OFFSET != -1L) {
            UNSAFE.putInt(mutable, STRING_COUNT_FIELD_OFFSET, chars.length);
         }

         return mutable;
      }
   }

   static {
      Unsafe unsafe;
      try {
         Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
         unsafeField.setAccessible(true);
         unsafe = (Unsafe)unsafeField.get((Object)null);
      } catch (Throwable var9) {
         unsafe = null;
      }

      long stringValueFieldOffset = -1L;
      long stringOffsetFieldOffset = -1L;
      long stringCountFieldOffset = -1L;
      if (unsafe != null) {
         try {
            stringValueFieldOffset = unsafe.objectFieldOffset(String.class.getDeclaredField("value"));
            stringOffsetFieldOffset = unsafe.objectFieldOffset(String.class.getDeclaredField("offset"));
            stringCountFieldOffset = unsafe.objectFieldOffset(String.class.getDeclaredField("count"));
         } catch (Throwable var8) {
         }
      }

      UNSAFE = unsafe;
      STRING_VALUE_FIELD_OFFSET = stringValueFieldOffset;
      STRING_OFFSET_FIELD_OFFSET = stringOffsetFieldOffset;
      STRING_COUNT_FIELD_OFFSET = stringCountFieldOffset;
   }
}
