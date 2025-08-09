package io.airlift.compress.lzo;

import io.airlift.compress.IncompatibleJvmException;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

final class UnsafeUtil {
   public static final Unsafe UNSAFE;
   private static final long ADDRESS_OFFSET;

   private UnsafeUtil() {
   }

   public static long getAddress(Buffer buffer) {
      if (!buffer.isDirect()) {
         throw new IllegalArgumentException("buffer is not direct");
      } else {
         return UNSAFE.getLong(buffer, ADDRESS_OFFSET);
      }
   }

   static {
      ByteOrder order = ByteOrder.nativeOrder();
      if (!order.equals(ByteOrder.LITTLE_ENDIAN)) {
         throw new IncompatibleJvmException(String.format("LZO requires a little endian platform (found %s)", order));
      } else {
         try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe)theUnsafe.get((Object)null);
         } catch (Exception var3) {
            throw new IncompatibleJvmException("LZO requires access to sun.misc.Unsafe");
         }

         try {
            ADDRESS_OFFSET = UNSAFE.objectFieldOffset(Buffer.class.getDeclaredField("address"));
         } catch (NoSuchFieldException var2) {
            throw new IncompatibleJvmException("LZO requires access to java.nio.Buffer raw address field");
         }
      }
   }
}
