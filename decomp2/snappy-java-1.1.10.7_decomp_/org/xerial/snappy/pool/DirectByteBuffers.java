package org.xerial.snappy.pool;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;

final class DirectByteBuffers {
   static final Class DIRECT_BUFFER_CLAZZ = lookupClassQuietly("java.nio.DirectByteBuffer");
   static final MethodHandle CLEAN_HANDLE;

   private static Class lookupClassQuietly(String var0) {
      try {
         return DirectByteBuffers.class.getClassLoader().loadClass(var0);
      } catch (Throwable var2) {
         Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Did not find requested class: " + var0, var2);
         return null;
      }
   }

   static boolean nonNull(Object var0) {
      return var0 != null;
   }

   public static void releaseDirectByteBuffer(final ByteBuffer var0) {
      assert var0 != null && var0.isDirect();

      if (CLEAN_HANDLE != null && DIRECT_BUFFER_CLAZZ.isInstance(var0)) {
         try {
            PrivilegedExceptionAction var1 = new PrivilegedExceptionAction() {
               public Void run() throws Exception {
                  try {
                     DirectByteBuffers.CLEAN_HANDLE.invokeExact(var0);
                     return null;
                  } catch (Exception var2) {
                     throw var2;
                  } catch (Throwable var3) {
                     throw new RuntimeException(var3);
                  }
               }
            };
            AccessController.doPrivileged(var1);
         } catch (Throwable var2) {
            Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Exception occurred attempting to clean up Sun specific DirectByteBuffer.", var2);
         }
      }

   }

   static {
      MethodHandle var0 = null;

      try {
         PrivilegedExceptionAction var1 = new PrivilegedExceptionAction() {
            public MethodHandle run() throws Exception {
               MethodHandle var1 = null;
               if (DirectByteBuffers.DIRECT_BUFFER_CLAZZ != null) {
                  MethodHandles.Lookup var2 = MethodHandles.lookup();

                  try {
                     Class var3 = Class.forName("sun.misc.Unsafe");
                     MethodHandle var11 = var2.findVirtual(var3, "invokeCleaner", MethodType.methodType(Void.TYPE, ByteBuffer.class));
                     Field var12 = var3.getDeclaredField("theUnsafe");
                     var12.setAccessible(true);
                     Object var13 = var12.get((Object)null);
                     var1 = var11.bindTo(var13);
                  } catch (Exception var10) {
                     Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "unable to use java 9 Unsafe.invokeCleaner", var10);
                     Method var4 = DirectByteBuffers.DIRECT_BUFFER_CLAZZ.getMethod("cleaner");
                     var4.setAccessible(true);
                     MethodHandle var5 = var2.unreflect(var4);
                     Class var6 = var5.type().returnType();
                     MethodHandle var7 = var2.findVirtual(var6, "clean", MethodType.methodType(Void.TYPE));
                     MethodHandle var8 = var2.findStatic(DirectByteBuffers.class, "nonNull", MethodType.methodType(Boolean.TYPE, Object.class)).asType(MethodType.methodType(Boolean.TYPE, var6));
                     MethodHandle var9 = MethodHandles.dropArguments(MethodHandles.constant(Void.class, (Object)null).asType(MethodType.methodType(Void.TYPE)), 0, new Class[]{var6});
                     var1 = MethodHandles.filterReturnValue(var5, MethodHandles.guardWithTest(var8, var7, var9)).asType(MethodType.methodType(Void.TYPE, ByteBuffer.class));
                  }
               }

               return var1;
            }
         };
         var0 = (MethodHandle)AccessController.doPrivileged(var1);
      } catch (Throwable var2) {
         Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Exception occurred attempting to lookup Sun specific DirectByteBuffer cleaner classes.", var2);
      }

      CLEAN_HANDLE = var0;
   }
}
