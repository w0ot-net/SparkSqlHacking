package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;

abstract class AsconBaseDigest implements ExtendedDigest {
   protected long x0;
   protected long x1;
   protected long x2;
   protected long x3;
   protected long x4;
   protected final int CRYPTO_BYTES = 32;
   protected final int ASCON_HASH_RATE = 8;
   protected int ASCON_PB_ROUNDS = 12;
   protected final byte[] m_buf = new byte[8];
   protected int m_bufPos = 0;

   private void round(long var1) {
      long var3 = this.x0 ^ this.x1 ^ this.x2 ^ this.x3 ^ var1 ^ this.x1 & (this.x0 ^ this.x2 ^ this.x4 ^ var1);
      long var5 = this.x0 ^ this.x2 ^ this.x3 ^ this.x4 ^ var1 ^ (this.x1 ^ this.x2 ^ var1) & (this.x1 ^ this.x3);
      long var7 = this.x1 ^ this.x2 ^ this.x4 ^ var1 ^ this.x3 & this.x4;
      long var9 = this.x0 ^ this.x1 ^ this.x2 ^ var1 ^ ~this.x0 & (this.x3 ^ this.x4);
      long var11 = this.x1 ^ this.x3 ^ this.x4 ^ (this.x0 ^ this.x4) & this.x1;
      this.x0 = var3 ^ Longs.rotateRight(var3, 19) ^ Longs.rotateRight(var3, 28);
      this.x1 = var5 ^ Longs.rotateRight(var5, 39) ^ Longs.rotateRight(var5, 61);
      this.x2 = ~(var7 ^ Longs.rotateRight(var7, 1) ^ Longs.rotateRight(var7, 6));
      this.x3 = var9 ^ Longs.rotateRight(var9, 10) ^ Longs.rotateRight(var9, 17);
      this.x4 = var11 ^ Longs.rotateRight(var11, 7) ^ Longs.rotateRight(var11, 41);
   }

   protected void p(int var1) {
      if (var1 == 12) {
         this.round(240L);
         this.round(225L);
         this.round(210L);
         this.round(195L);
      }

      if (var1 >= 8) {
         this.round(180L);
         this.round(165L);
      }

      this.round(150L);
      this.round(135L);
      this.round(120L);
      this.round(105L);
      this.round(90L);
      this.round(75L);
   }

   protected abstract long pad(int var1);

   protected abstract long loadBytes(byte[] var1, int var2);

   protected abstract long loadBytes(byte[] var1, int var2, int var3);

   protected abstract void setBytes(long var1, byte[] var3, int var4);

   protected abstract void setBytes(long var1, byte[] var3, int var4, int var5);

   public int getDigestSize() {
      return 32;
   }

   public int getByteLength() {
      return 8;
   }

   public void update(byte var1) {
      this.m_buf[this.m_bufPos] = var1;
      if (++this.m_bufPos == 8) {
         this.x0 ^= this.loadBytes(this.m_buf, 0);
         this.p(this.ASCON_PB_ROUNDS);
         this.m_bufPos = 0;
      }

   }

   public void update(byte[] var1, int var2, int var3) {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else {
         int var4 = 8 - this.m_bufPos;
         if (var3 < var4) {
            System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
            this.m_bufPos += var3;
         } else {
            int var5 = 0;
            if (this.m_bufPos > 0) {
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var4);
               var5 += var4;
               this.x0 ^= this.loadBytes(this.m_buf, 0);
               this.p(this.ASCON_PB_ROUNDS);
            }

            int var6;
            while((var6 = var3 - var5) >= 8) {
               this.x0 ^= this.loadBytes(var1, var2 + var5);
               this.p(this.ASCON_PB_ROUNDS);
               var5 += 8;
            }

            System.arraycopy(var1, var2 + var5, this.m_buf, 0, var6);
            this.m_bufPos = var6;
         }
      }
   }

   public int doFinal(byte[] var1, int var2) {
      return this.hash(var1, var2, 32);
   }

   protected void padAndAbsorb() {
      this.x0 ^= this.loadBytes(this.m_buf, 0, this.m_bufPos);
      this.x0 ^= this.pad(this.m_bufPos);
      this.p(12);
   }

   protected void squeeze(byte[] var1, int var2, int var3) {
      while(var3 > 8) {
         this.setBytes(this.x0, var1, var2);
         this.p(this.ASCON_PB_ROUNDS);
         var2 += 8;
         var3 -= 8;
      }

      this.setBytes(this.x0, var1, var2, var3);
      this.reset();
   }

   protected int hash(byte[] var1, int var2, int var3) {
      if (32 + var2 > var1.length) {
         throw new OutputLengthException("output buffer is too short");
      } else {
         this.padAndAbsorb();
         this.squeeze(var1, var2, var3);
         return var3;
      }
   }

   public void reset() {
      Arrays.clear(this.m_buf);
      this.m_bufPos = 0;
   }
}
