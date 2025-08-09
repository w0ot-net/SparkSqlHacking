package org.xerial.snappy;

import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Checksum;

final class SnappyFramed {
   public static final int COMPRESSED_DATA_FLAG = 0;
   public static final int UNCOMPRESSED_DATA_FLAG = 1;
   public static final int STREAM_IDENTIFIER_FLAG = 255;
   private static final int MASK_DELTA = -1568478504;
   private static final Supplier CHECKSUM_SUPPLIER;
   public static final byte[] HEADER_BYTES;

   public static Checksum getCRC32C() {
      return (Checksum)CHECKSUM_SUPPLIER.get();
   }

   public static int maskedCrc32c(Checksum var0, byte[] var1, int var2, int var3) {
      var0.reset();
      var0.update(var1, var2, var3);
      return mask((int)var0.getValue());
   }

   public static int mask(int var0) {
      return (var0 >>> 15 | var0 << 17) + -1568478504;
   }

   static final int readBytes(ReadableByteChannel var0, ByteBuffer var1) throws IOException {
      int var2 = var1.remaining();
      int var3 = 0;
      int var4 = var0.read(var1);
      var3 = var4;
      if (var4 < var2) {
         while(var1.remaining() != 0 && var4 != -1) {
            var4 = var0.read(var1);
            if (var4 != -1) {
               var3 += var4;
            }
         }
      }

      if (var3 > 0) {
         var1.limit(var1.position());
      } else {
         var1.position(var1.limit());
      }

      return var3;
   }

   static int skip(ReadableByteChannel var0, int var1, ByteBuffer var2) throws IOException {
      if (var1 <= 0) {
         return 0;
      } else {
         int var3 = var1;
         int var4 = 0;

         while(var3 > 0 && var4 != -1) {
            var2.clear();
            if (var3 < var2.capacity()) {
               var2.limit(var3);
            }

            var4 = var0.read(var2);
            if (var4 > 0) {
               var3 -= var4;
            }
         }

         var2.clear();
         return var1 - var3;
      }
   }

   static {
      Object var0 = null;

      try {
         Class var1 = Class.forName("java.util.zip.CRC32C");
         MethodHandles.Lookup var2 = MethodHandles.lookup();
         MethodHandle var3 = var2.findConstructor(var1, MethodType.methodType(Void.TYPE));
         var5 = LambdaMetafactory.metafactory(var2, "get", MethodType.methodType(Supplier.class), MethodType.methodType(Object.class), var3, MethodType.methodType(Checksum.class)).getTarget().invoke();
      } catch (Throwable var4) {
         Logger.getLogger(SnappyFramed.class.getName()).log(Level.FINE, "java.util.zip.CRC32C not loaded, using PureJavaCrc32C", var4);
         var5 = null;
      }

      CHECKSUM_SUPPLIER = var5 != null ? var5 : PureJavaCrc32C::new;
      HEADER_BYTES = new byte[]{-1, 6, 0, 0, 115, 78, 97, 80, 112, 89};
   }
}
