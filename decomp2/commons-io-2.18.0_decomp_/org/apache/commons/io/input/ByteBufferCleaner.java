package org.apache.commons.io.input;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

final class ByteBufferCleaner {
   private static final Cleaner INSTANCE = getCleaner();

   static void clean(ByteBuffer buffer) {
      try {
         INSTANCE.clean(buffer);
      } catch (Exception e) {
         throw new IllegalStateException("Failed to clean direct buffer.", e);
      }
   }

   private static Cleaner getCleaner() {
      try {
         return new Java8Cleaner();
      } catch (Exception e) {
         try {
            return new Java9Cleaner();
         } catch (Exception var2) {
            throw new IllegalStateException("Failed to initialize a Cleaner.", e);
         }
      }
   }

   static boolean isSupported() {
      return INSTANCE != null;
   }

   private static final class Java8Cleaner implements Cleaner {
      private final Method cleanerMethod;
      private final Method cleanMethod;

      private Java8Cleaner() throws ReflectiveOperationException, SecurityException {
         this.cleanMethod = Class.forName("sun.misc.Cleaner").getMethod("clean");
         this.cleanerMethod = Class.forName("sun.nio.ch.DirectBuffer").getMethod("cleaner");
      }

      public void clean(ByteBuffer buffer) throws ReflectiveOperationException {
         Object cleaner = this.cleanerMethod.invoke(buffer);
         if (cleaner != null) {
            this.cleanMethod.invoke(cleaner);
         }

      }
   }

   private static final class Java9Cleaner implements Cleaner {
      private final Object theUnsafe;
      private final Method invokeCleaner;

      private Java9Cleaner() throws ReflectiveOperationException, SecurityException {
         Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
         Field field = unsafeClass.getDeclaredField("theUnsafe");
         field.setAccessible(true);
         this.theUnsafe = field.get((Object)null);
         this.invokeCleaner = unsafeClass.getMethod("invokeCleaner", ByteBuffer.class);
      }

      public void clean(ByteBuffer buffer) throws ReflectiveOperationException {
         this.invokeCleaner.invoke(this.theUnsafe, buffer);
      }
   }

   private interface Cleaner {
      void clean(ByteBuffer var1) throws ReflectiveOperationException;
   }
}
