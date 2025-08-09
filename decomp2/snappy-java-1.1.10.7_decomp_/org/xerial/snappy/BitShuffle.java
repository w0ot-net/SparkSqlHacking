package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BitShuffle {
   private static BitShuffleNative impl;

   public static int shuffle(ByteBuffer var0, BitShuffleType var1, ByteBuffer var2) throws IOException {
      if (!var0.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
      } else if (!var2.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
      } else {
         int var3 = var0.position();
         int var4 = var0.remaining();
         int var5 = var1.getTypeSize();
         if (var4 % var5 != 0) {
            throw new IllegalArgumentException("input length must be a multiple of the given type size: " + var5);
         } else if (var2.remaining() < var4) {
            throw new IllegalArgumentException("not enough space for output");
         } else {
            int var6 = impl.shuffleDirectBuffer(var0, var3, var5, var4, var2, var2.position());

            assert var6 == var4;

            var2.limit(var2.position() + var6);
            return var6;
         }
      }
   }

   public static byte[] shuffle(short[] var0) throws IOException {
      if (var0.length * 2 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         byte[] var1 = new byte[var0.length * 2];
         int var2 = impl.shuffle(var0, 0, 2, var0.length * 2, var1, 0);

         assert var2 == var0.length * 2;

         return var1;
      }
   }

   public static byte[] shuffle(int[] var0) throws IOException {
      if (var0.length * 4 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         byte[] var1 = new byte[var0.length * 4];
         int var2 = impl.shuffle(var0, 0, 4, var0.length * 4, var1, 0);

         assert var2 == var0.length * 4;

         return var1;
      }
   }

   public static byte[] shuffle(long[] var0) throws IOException {
      if (var0.length * 8 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         byte[] var1 = new byte[var0.length * 8];
         int var2 = impl.shuffle(var0, 0, 8, var0.length * 8, var1, 0);

         assert var2 == var0.length * 8;

         return var1;
      }
   }

   public static byte[] shuffle(float[] var0) throws IOException {
      if (var0.length * 4 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         byte[] var1 = new byte[var0.length * 4];
         int var2 = impl.shuffle(var0, 0, 4, var0.length * 4, var1, 0);

         assert var2 == var0.length * 4;

         return var1;
      }
   }

   public static byte[] shuffle(double[] var0) throws IOException {
      if (var0.length * 8 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         byte[] var1 = new byte[var0.length * 8];
         int var2 = impl.shuffle(var0, 0, 8, var0.length * 8, var1, 0);

         assert var2 == var0.length * 8;

         return var1;
      }
   }

   public static int unshuffle(ByteBuffer var0, BitShuffleType var1, ByteBuffer var2) throws IOException {
      if (!var0.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
      } else if (!var2.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
      } else {
         int var3 = var0.position();
         int var4 = var0.remaining();
         int var5 = var1.getTypeSize();
         if (var4 % var5 != 0) {
            throw new IllegalArgumentException("length of input shuffled data must be a multiple of the given type size: " + var5);
         } else if (var2.remaining() < var4) {
            throw new IllegalArgumentException("not enough space for output");
         } else {
            int var6 = impl.unshuffleDirectBuffer(var0, var3, var5, var4, var2, var0.position());

            assert var6 == var4;

            var0.limit(var0.position() + var6);
            return var6;
         }
      }
   }

   public static short[] unshuffleShortArray(byte[] var0) throws IOException {
      short[] var1 = new short[var0.length / 2];
      int var2 = impl.unshuffle(var0, 0, 2, var0.length, var1, 0);

      assert var2 == var0.length;

      return var1;
   }

   public static int[] unshuffleIntArray(byte[] var0) throws IOException {
      int[] var1 = new int[var0.length / 4];
      int var2 = impl.unshuffle(var0, 0, 4, var0.length, var1, 0);

      assert var2 == var0.length;

      return var1;
   }

   public static long[] unshuffleLongArray(byte[] var0) throws IOException {
      long[] var1 = new long[var0.length / 8];
      int var2 = impl.unshuffle(var0, 0, 8, var0.length, var1, 0);

      assert var2 == var0.length;

      return var1;
   }

   public static float[] unshuffleFloatArray(byte[] var0) throws IOException {
      float[] var1 = new float[var0.length / 4];
      int var2 = impl.unshuffle(var0, 0, 4, var0.length, var1, 0);

      assert var2 == var0.length;

      return var1;
   }

   public static double[] unshuffleDoubleArray(byte[] var0) throws IOException {
      double[] var1 = new double[var0.length / 8];
      int var2 = impl.unshuffle(var0, 0, 8, var0.length, var1, 0);

      assert var2 == var0.length;

      return var1;
   }

   static {
      try {
         impl = SnappyLoader.loadBitShuffleApi();
      } catch (Exception var1) {
         throw new ExceptionInInitializerError(var1);
      }
   }
}
