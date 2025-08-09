package scala.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public final class Statics {
   public static final Object pfMarker = new Object();

   public static int mix(int hash, int data) {
      int h = mixLast(hash, data);
      h = Integer.rotateLeft(h, 13);
      return h * 5 + -430675100;
   }

   public static int mixLast(int hash, int data) {
      int k = data * -862048943;
      k = Integer.rotateLeft(k, 15);
      k *= 461845907;
      return hash ^ k;
   }

   public static int finalizeHash(int hash, int length) {
      return avalanche(hash ^ length);
   }

   public static int avalanche(int h) {
      h ^= h >>> 16;
      h *= -2048144789;
      h ^= h >>> 13;
      h *= -1028477387;
      h ^= h >>> 16;
      return h;
   }

   public static int longHash(long lv) {
      int iv = (int)lv;
      return (long)iv == lv ? iv : Long.hashCode(lv);
   }

   public static int doubleHash(double dv) {
      int iv = (int)dv;
      if ((double)iv == dv) {
         return iv;
      } else {
         long lv = (long)dv;
         if ((double)lv == dv) {
            return Long.hashCode(lv);
         } else {
            float fv = (float)dv;
            return (double)fv == dv ? Float.hashCode(fv) : Double.hashCode(dv);
         }
      }
   }

   public static int floatHash(float fv) {
      int iv = (int)fv;
      if ((float)iv == fv) {
         return iv;
      } else {
         long lv = (long)fv;
         return (float)lv == fv ? Long.hashCode(lv) : Float.hashCode(fv);
      }
   }

   public static int anyHash(Object x) {
      if (x == null) {
         return 0;
      } else {
         return x instanceof Number ? anyHashNumber((Number)x) : x.hashCode();
      }
   }

   private static int anyHashNumber(Number x) {
      if (x instanceof Long) {
         return longHash((Long)x);
      } else if (x instanceof Double) {
         return doubleHash((Double)x);
      } else {
         return x instanceof Float ? floatHash((Float)x) : x.hashCode();
      }
   }

   public static void releaseFence() throws Throwable {
      Statics.VM.RELEASE_FENCE.invoke();
   }

   public static final Object ioobe(int n) throws IndexOutOfBoundsException {
      throw new IndexOutOfBoundsException(String.valueOf(n));
   }

   static final class VM {
      static final MethodHandle RELEASE_FENCE = mkHandle();

      private static MethodHandle mkHandle() {
         MethodHandles.Lookup lookup = MethodHandles.lookup();

         try {
            return lookup.findStatic(Class.forName("java.lang.invoke.VarHandle"), "releaseFence", MethodType.methodType(Void.TYPE));
         } catch (ClassNotFoundException | NoSuchMethodException e) {
            try {
               Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
               return lookup.findVirtual(unsafeClass, "storeFence", MethodType.methodType(Void.TYPE)).bindTo(findUnsafe(unsafeClass));
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e1) {
               ExceptionInInitializerError error = new ExceptionInInitializerError(e1);
               error.addSuppressed(e);
               throw error;
            }
         } catch (IllegalAccessException e) {
            throw new ExceptionInInitializerError(e);
         }
      }

      private static Object findUnsafe(Class unsafeClass) throws IllegalAccessException {
         Object found = null;

         for(Field field : unsafeClass.getDeclaredFields()) {
            if (field.getType() == unsafeClass) {
               field.setAccessible(true);
               found = field.get((Object)null);
               break;
            }
         }

         if (found == null) {
            throw new IllegalStateException("No instance of Unsafe found");
         } else {
            return found;
         }
      }
   }
}
