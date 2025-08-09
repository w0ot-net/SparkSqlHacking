package io.netty.util.internal.shaded.org.jctools.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeAccess {
   public static final boolean SUPPORTS_GET_AND_SET_REF = hasGetAndSetSupport();
   public static final boolean SUPPORTS_GET_AND_ADD_LONG = hasGetAndAddLongSupport();
   public static final Unsafe UNSAFE = getUnsafe();

   private static Unsafe getUnsafe() {
      Unsafe instance;
      try {
         Field field = Unsafe.class.getDeclaredField("theUnsafe");
         field.setAccessible(true);
         instance = (Unsafe)field.get((Object)null);
      } catch (Exception var4) {
         try {
            Constructor<Unsafe> c = Unsafe.class.getDeclaredConstructor();
            c.setAccessible(true);
            instance = (Unsafe)c.newInstance();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      return instance;
   }

   private static boolean hasGetAndSetSupport() {
      try {
         Unsafe.class.getMethod("getAndSetObject", Object.class, Long.TYPE, Object.class);
         return true;
      } catch (Exception var1) {
         return false;
      }
   }

   private static boolean hasGetAndAddLongSupport() {
      try {
         Unsafe.class.getMethod("getAndAddLong", Object.class, Long.TYPE, Long.TYPE);
         return true;
      } catch (Exception var1) {
         return false;
      }
   }

   public static long fieldOffset(Class clz, String fieldName) throws RuntimeException {
      try {
         return UNSAFE.objectFieldOffset(clz.getDeclaredField(fieldName));
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      }
   }
}
