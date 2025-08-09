package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

abstract class ByteBufChecksum implements Checksum {
   private final ByteProcessor updateProcessor = new ByteProcessor() {
      public boolean process(byte value) throws Exception {
         ByteBufChecksum.this.update(value);
         return true;
      }
   };

   private static Method updateByteBuffer(Checksum checksum) {
      if (PlatformDependent.javaVersion() >= 8) {
         try {
            Method method = checksum.getClass().getDeclaredMethod("update", ByteBuffer.class);
            method.invoke(checksum, ByteBuffer.allocate(1));
            return method;
         } catch (Throwable var2) {
            return null;
         }
      } else {
         return null;
      }
   }

   static ByteBufChecksum wrapChecksum(Checksum checksum) {
      ObjectUtil.checkNotNull(checksum, "checksum");
      if (checksum instanceof ByteBufChecksum) {
         return (ByteBufChecksum)checksum;
      } else if (checksum instanceof Adler32 && ByteBufChecksum.ZlibChecksumMethods.ADLER32_UPDATE_METHOD != null) {
         return new ReflectiveByteBufChecksum(checksum, ByteBufChecksum.ZlibChecksumMethods.ADLER32_UPDATE_METHOD);
      } else {
         return (ByteBufChecksum)(checksum instanceof CRC32 && ByteBufChecksum.ZlibChecksumMethods.CRC32_UPDATE_METHOD != null ? new ReflectiveByteBufChecksum(checksum, ByteBufChecksum.ZlibChecksumMethods.CRC32_UPDATE_METHOD) : new SlowByteBufChecksum(checksum));
      }
   }

   public void update(ByteBuf b, int off, int len) {
      if (b.hasArray()) {
         this.update(b.array(), b.arrayOffset() + off, len);
      } else {
         b.forEachByte(off, len, this.updateProcessor);
      }

   }

   private static class ZlibChecksumMethods {
      private static final Method ADLER32_UPDATE_METHOD = ByteBufChecksum.updateByteBuffer(new Adler32());
      private static final Method CRC32_UPDATE_METHOD = ByteBufChecksum.updateByteBuffer(new CRC32());
   }

   private static final class ReflectiveByteBufChecksum extends SlowByteBufChecksum {
      private final Method method;

      ReflectiveByteBufChecksum(Checksum checksum, Method method) {
         super(checksum);
         this.method = method;
      }

      public void update(ByteBuf b, int off, int len) {
         if (b.hasArray()) {
            this.update(b.array(), b.arrayOffset() + off, len);
         } else {
            try {
               this.method.invoke(this.checksum, CompressionUtil.safeNioBuffer(b, off, len));
            } catch (Throwable var5) {
               throw new Error();
            }
         }

      }
   }

   private static class SlowByteBufChecksum extends ByteBufChecksum {
      protected final Checksum checksum;

      SlowByteBufChecksum(Checksum checksum) {
         this.checksum = checksum;
      }

      public void update(int b) {
         this.checksum.update(b);
      }

      public void update(byte[] b, int off, int len) {
         this.checksum.update(b, off, len);
      }

      public long getValue() {
         return this.checksum.getValue();
      }

      public void reset() {
         this.checksum.reset();
      }
   }
}
