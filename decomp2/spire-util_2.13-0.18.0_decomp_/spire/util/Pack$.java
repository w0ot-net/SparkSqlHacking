package spire.util;

import java.nio.ByteBuffer;
import scala.runtime.BoxedUnit;

public final class Pack$ implements PackMacros {
   public static final Pack$ MODULE$ = new Pack$();

   static {
      PackMacros.$init$(MODULE$);
   }

   public byte ism(final int n, final int shift) {
      return PackMacros.ism$(this, n, shift);
   }

   public byte lsm(final long n, final int shift) {
      return PackMacros.lsm$(this, n, shift);
   }

   public byte[] intToBytes(final int n) {
      byte[] arr = new byte[4];
      arr[0] = this.ism(n, 24);
      arr[1] = this.ism(n, 16);
      arr[2] = this.ism(n, 8);
      arr[3] = this.ism(n, 0);
      return arr;
   }

   public byte[] intsToBytes(final int[] ints) {
      byte[] arr = new byte[ints.length * 4];
      int i = 0;

      for(int j = 0; i < ints.length; j += 4) {
         int n = ints[i];
         arr[j] = this.ism(n, 24);
         arr[j + 1] = this.ism(n, 16);
         arr[j + 2] = this.ism(n, 8);
         arr[j + 3] = this.ism(n, 0);
         ++i;
      }

      return arr;
   }

   public int intFromBytes(final byte[] bytes) {
      return this.intFromByteBuffer(ByteBuffer.wrap(bytes));
   }

   public int intFromBytes(final byte b1, final byte b2, final byte b3, final byte b4) {
      return (b1 & 255) << 24 | (b2 & 255) << 16 | (b3 & 255) << 8 | b4 & 255;
   }

   public int intFromByteBuffer(final ByteBuffer bb) {
      int var10000;
      if (bb.remaining() >= 4) {
         var10000 = bb.getInt();
      } else {
         int n;
         for(n = 0; bb.remaining() > 0; n = n << 8 | bb.get()) {
         }

         var10000 = n;
      }

      return var10000;
   }

   public int[] intsFromBytes(final byte[] bytes, final int n) {
      return this.intsFromByteBuffer(ByteBuffer.wrap(bytes), n);
   }

   public int[] intsFromByteBuffer(final ByteBuffer bb, final int n) {
      int[] out = new int[n];

      int i;
      for(i = 0; i < n && bb.remaining() >= 4; ++i) {
         out[i] = bb.getInt();
      }

      if (i < n && bb.remaining() > 0) {
         out[i] = this.intFromByteBuffer(bb);
      }

      return out;
   }

   public byte[] longToBytes(final long n) {
      byte[] arr = new byte[8];
      arr[0] = this.lsm(n, 56);
      arr[1] = this.lsm(n, 48);
      arr[2] = this.lsm(n, 40);
      arr[3] = this.lsm(n, 32);
      arr[4] = this.lsm(n, 24);
      arr[5] = this.lsm(n, 16);
      arr[6] = this.lsm(n, 8);
      arr[7] = this.lsm(n, 0);
      return arr;
   }

   public byte[] longsToBytes(final long[] longs) {
      byte[] arr = new byte[longs.length * 8];
      int i = 0;

      for(int j = 0; i < longs.length; j += 8) {
         long n = longs[i];
         arr[j] = this.lsm(n, 56);
         arr[j + 1] = this.lsm(n, 48);
         arr[j + 2] = this.lsm(n, 40);
         arr[j + 3] = this.lsm(n, 32);
         arr[j + 4] = this.lsm(n, 24);
         arr[j + 5] = this.lsm(n, 16);
         arr[j + 6] = this.lsm(n, 8);
         arr[j + 7] = this.lsm(n, 0);
         ++i;
      }

      return arr;
   }

   public long longFromBytes(final byte[] bytes) {
      return this.longFromByteBuffer(ByteBuffer.wrap(bytes));
   }

   public long longFromBytes(final byte b1, final byte b2, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8) {
      return ((long)b1 & 255L) << 56 | ((long)b2 & 255L) << 48 | ((long)b3 & 255L) << 40 | ((long)b4 & 255L) << 32 | ((long)b5 & 255L) << 24 | ((long)b6 & 255L) << 16 | ((long)b7 & 255L) << 8 | (long)b8 & 255L;
   }

   public long longFromByteBuffer(final ByteBuffer bb) {
      long var10000;
      if (bb.remaining() >= 8) {
         var10000 = bb.getLong();
      } else {
         long n;
         for(n = 0L; bb.remaining() > 0; n = n << 8 | (long)bb.get()) {
         }

         var10000 = n;
      }

      return var10000;
   }

   public long[] longsFromBytes(final byte[] bytes, final int n) {
      return this.longsFromByteBuffer(ByteBuffer.wrap(bytes), n);
   }

   public long[] longsFromByteBuffer(final ByteBuffer bb, final int n) {
      long[] out = new long[n];

      int i;
      for(i = 0; i < n && bb.remaining() >= 8; ++i) {
         out[i] = bb.getLong();
      }

      if (i < n && bb.remaining() > 0) {
         out[i] = this.longFromByteBuffer(bb);
      }

      return out;
   }

   public byte[] bytesFromByteBuffer(final ByteBuffer bb, final int n) {
      byte[] out = new byte[n];
      if (bb.remaining() >= n) {
         bb.get(out);
      } else {
         for(int i = 0; bb.remaining() > 0; ++i) {
            out[i] = bb.get();
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return out;
   }

   public byte intToByteRuntime(final int n, final int index) {
      if (0 <= index && index < 4) {
         return (byte)(n >>> 24 - index * 8 & 255);
      } else {
         throw new IllegalArgumentException((new StringBuilder(15)).append(index).append(" outside of 0-3").toString());
      }
   }

   public byte longToByteRuntime(final long n, final int index) {
      if (0 <= index && index < 8) {
         return (byte)((int)(n >>> 56 - index * 8 & 255L));
      } else {
         throw new IllegalArgumentException((new StringBuilder(15)).append(index).append(" outside of 0-7").toString());
      }
   }

   private Pack$() {
   }
}
