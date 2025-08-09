package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;

public class ISAPDigest implements Digest {
   private long x0;
   private long x1;
   private long x2;
   private long x3;
   private long x4;
   private long t0;
   private long t1;
   private long t2;
   private long t3;
   private long t4;
   private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

   private void ROUND(long var1) {
      this.t0 = this.x0 ^ this.x1 ^ this.x2 ^ this.x3 ^ var1 ^ this.x1 & (this.x0 ^ this.x2 ^ this.x4 ^ var1);
      this.t1 = this.x0 ^ this.x2 ^ this.x3 ^ this.x4 ^ var1 ^ (this.x1 ^ this.x2 ^ var1) & (this.x1 ^ this.x3);
      this.t2 = this.x1 ^ this.x2 ^ this.x4 ^ var1 ^ this.x3 & this.x4;
      this.t3 = this.x0 ^ this.x1 ^ this.x2 ^ var1 ^ ~this.x0 & (this.x3 ^ this.x4);
      this.t4 = this.x1 ^ this.x3 ^ this.x4 ^ (this.x0 ^ this.x4) & this.x1;
      this.x0 = this.t0 ^ this.ROTR(this.t0, 19L) ^ this.ROTR(this.t0, 28L);
      this.x1 = this.t1 ^ this.ROTR(this.t1, 39L) ^ this.ROTR(this.t1, 61L);
      this.x2 = ~(this.t2 ^ this.ROTR(this.t2, 1L) ^ this.ROTR(this.t2, 6L));
      this.x3 = this.t3 ^ this.ROTR(this.t3, 10L) ^ this.ROTR(this.t3, 17L);
      this.x4 = this.t4 ^ this.ROTR(this.t4, 7L) ^ this.ROTR(this.t4, 41L);
   }

   private void P12() {
      this.ROUND(240L);
      this.ROUND(225L);
      this.ROUND(210L);
      this.ROUND(195L);
      this.ROUND(180L);
      this.ROUND(165L);
      this.ROUND(150L);
      this.ROUND(135L);
      this.ROUND(120L);
      this.ROUND(105L);
      this.ROUND(90L);
      this.ROUND(75L);
   }

   private long ROTR(long var1, long var3) {
      return var1 >>> (int)var3 | var1 << (int)(64L - var3);
   }

   protected long U64BIG(long var1) {
      return this.ROTR(var1, 8L) & -72057589759737856L | this.ROTR(var1, 24L) & 71776119077928960L | this.ROTR(var1, 40L) & 280375465148160L | this.ROTR(var1, 56L) & 1095216660735L;
   }

   public String getAlgorithmName() {
      return "ISAP Hash";
   }

   public int getDigestSize() {
      return 32;
   }

   public void update(byte var1) {
      this.buffer.write(var1);
   }

   public void update(byte[] var1, int var2, int var3) {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else {
         this.buffer.write(var1, var2, var3);
      }
   }

   public int doFinal(byte[] var1, int var2) {
      if (32 + var2 > var1.length) {
         throw new OutputLengthException("output buffer is too short");
      } else {
         this.t0 = this.t1 = this.t2 = this.t3 = this.t4 = 0L;
         this.x0 = -1255492011513352131L;
         this.x1 = -8380609354527731710L;
         this.x2 = -5437372128236807582L;
         this.x3 = 4834782570098516968L;
         this.x4 = 3787428097924915520L;
         byte[] var3 = this.buffer.toByteArray();
         int var4 = var3.length;
         long[] var5 = new long[var4 >> 3];
         Pack.littleEndianToLong(var3, 0, var5, 0, var5.length);

         int var6;
         for(var6 = 0; var4 >= 8; var4 -= 8) {
            this.x0 ^= this.U64BIG(var5[var6++]);
            this.P12();
         }

         int var10003;
         for(this.x0 ^= 128L << (7 - var4 << 3); var4 > 0; this.x0 ^= ((long)var3[var10003 + var4] & 255L) << (7 - var4 << 3)) {
            var10003 = var6 << 3;
            --var4;
         }

         this.P12();
         long[] var7 = new long[4];

         for(var6 = 0; var6 < 3; ++var6) {
            var7[var6] = this.U64BIG(this.x0);
            this.P12();
         }

         var7[var6] = this.U64BIG(this.x0);
         Pack.longToLittleEndian(var7, var1, var2);
         this.buffer.reset();
         return 32;
      }
   }

   public void reset() {
      this.buffer.reset();
   }
}
