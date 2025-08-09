package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;

class PointerUnion extends Pointer {
   protected int remainder;

   public PointerUnion(byte[] var1) {
      super((var1.length >> 3) + ((var1.length & 7) != 0 ? 1 : 0));
      int var2 = 0;

      for(int var3 = 0; var2 < var1.length && var3 < this.array.length; ++var3) {
         for(int var4 = 0; var4 < 8 && var2 < var1.length; ++var2) {
            long[] var10000 = this.array;
            var10000[var3] |= ((long)var1[var2] & 255L) << (var4 << 3);
            ++var4;
         }
      }

      this.remainder = 0;
   }

   public PointerUnion(int var1) {
      super((var1 >>> 3) + ((var1 & 7) != 0 ? 1 : 0));
      this.remainder = 0;
   }

   public PointerUnion(PointerUnion var1) {
      super(var1);
      this.remainder = var1.remainder;
   }

   public PointerUnion(Pointer var1) {
      super(var1);
      this.remainder = 0;
   }

   public void moveNextBytes(int var1) {
      this.remainder += var1;
      this.cp += this.remainder >>> 3;
      this.remainder &= 7;
   }

   public void moveNextByte() {
      ++this.remainder;
      this.cp += this.remainder >>> 3;
      this.remainder &= 7;
   }

   public long get() {
      return this.remainder == 0 ? this.array[this.cp] : this.array[this.cp] >>> (this.remainder << 3) | this.array[this.cp + 1] << (8 - this.remainder << 3);
   }

   public long getWithCheck() {
      if (this.cp >= this.array.length) {
         return 0L;
      } else if (this.remainder == 0) {
         return this.array[this.cp];
      } else {
         return this.cp == this.array.length - 1 ? this.array[this.cp] >>> (this.remainder << 3) : this.array[this.cp] >>> (this.remainder << 3) | this.array[this.cp + 1] << (8 - this.remainder << 3);
      }
   }

   public long getWithCheck(int var1) {
      var1 += this.cp;
      if (var1 >= this.array.length) {
         return 0L;
      } else if (this.remainder == 0) {
         return this.array[var1];
      } else {
         return var1 == this.array.length - 1 ? this.array[var1] >>> (this.remainder << 3) : this.array[var1] >>> (this.remainder << 3) | this.array[var1 + 1] << (8 - this.remainder << 3);
      }
   }

   public long get(int var1) {
      return this.remainder == 0 ? this.array[this.cp + var1] : this.array[this.cp + var1] >>> (this.remainder << 3) | this.array[this.cp + var1 + 1] << (8 - this.remainder << 3);
   }

   public byte getByte() {
      return (byte)((int)(this.array[this.cp] >>> (this.remainder << 3)));
   }

   public byte getByte(int var1) {
      int var2 = this.cp + (var1 + this.remainder >>> 3);
      int var3 = this.remainder + var1 & 7;
      return (byte)((int)(this.array[var2] >>> (var3 << 3)));
   }

   public void setRangeClear(int var1, int var2) {
      if (this.remainder == 0) {
         super.setRangeClear(var1, var2);
      } else {
         long[] var10000 = this.array;
         int var10001 = this.cp + var1;
         var10000[var10001] &= -1L >>> (8 - this.remainder << 3);
         super.setRangeClear(var1 + 1, var2);
         var10000 = this.array;
         var10001 = this.cp + var2 + 1;
         var10000[var10001] &= -1L << (this.remainder << 3);
      }

   }

   public void setAnd(int var1, long var2) {
      if (this.remainder == 0) {
         super.setAnd(var1, var2);
      } else {
         int var4 = this.remainder << 3;
         int var5 = 8 - this.remainder << 3;
         long[] var10000 = this.array;
         int var10001 = this.cp + var1;
         var10000[var10001] &= var2 << var4 | -1L >>> var5;
         var10000 = this.array;
         var10001 = this.cp + var1 + 1;
         var10000[var10001] &= var2 >>> var5 | -1L << var4;
      }

   }

   public void indexReset() {
      this.cp = 0;
      this.remainder = 0;
   }

   public void setByteIndex(int var1) {
      this.remainder = var1 & 7;
      this.cp = var1 >>> 3;
   }

   public byte[] toBytes(int var1) {
      byte[] var2 = new byte[var1];

      for(int var3 = this.remainder; var3 < var2.length + this.remainder; ++var3) {
         var2[var3 - this.remainder] = (byte)((int)(this.array[this.cp + (var3 >>> 3)] >>> ((var3 & 7) << 3)));
      }

      return var2;
   }

   public int toBytesMove(byte[] var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         var1[var2++] = (byte)((int)(this.array[this.cp] >>> (this.remainder++ << 3)));
         if (this.remainder == 8) {
            this.remainder = 0;
            ++this.cp;
         }
      }

      return var2;
   }

   public void setXor(int var1, long var2) {
      if (this.remainder == 0) {
         super.setXor(var1, var2);
      } else {
         long[] var10000 = this.array;
         int var10001 = this.cp + var1;
         var10000[var10001] ^= var2 << (this.remainder << 3);
         var10000 = this.array;
         var10001 = this.cp + var1 + 1;
         var10000[var10001] ^= var2 >>> (8 - this.remainder << 3);
      }

   }

   public void setXor(long var1) {
      if (this.remainder == 0) {
         super.setXor(var1);
      } else {
         long[] var10000 = this.array;
         int var10001 = this.cp;
         var10000[var10001] ^= var1 << (this.remainder << 3);
         var10000 = this.array;
         var10001 = this.cp + 1;
         var10000[var10001] ^= var1 >>> (8 - this.remainder << 3);
      }

   }

   public void setXorRangeAndMask(Pointer var1, int var2, long var3) {
      if (this.remainder == 0) {
         super.setXorRangeAndMask(var1, var2, var3);
      } else {
         int var5 = this.cp;
         int var6 = var1.cp;
         int var9 = this.remainder << 3;
         int var10 = 8 - this.remainder << 3;

         for(int var11 = 0; var11 < var2; ++var11) {
            long var7 = var1.array[var6++] & var3;
            long[] var10000 = this.array;
            var10000[var5] ^= var7 << var9;
            var10000 = this.array;
            ++var5;
            var10000[var5] ^= var7 >>> var10;
         }

      }
   }

   public void setXorByte(int var1) {
      long[] var10000 = this.array;
      int var10001 = this.cp;
      var10000[var10001] ^= ((long)var1 & 255L) << (this.remainder << 3);
   }

   public void setAndByte(int var1, long var2) {
      int var4 = var1 + this.remainder + (this.cp << 3);
      int var5 = var4 >>> 3;
      var4 &= 7;
      long[] var10000 = this.array;
      var10000[var5] &= (var2 & 255L) << (var4 << 3) | ~(255L << (var4 << 3));
   }

   public void setAndThenXorByte(int var1, long var2, long var4) {
      int var6 = var1 + this.remainder + (this.cp << 3);
      int var7 = var6 >>> 3;
      var6 &= 7;
      long[] var10000 = this.array;
      var10000[var7] &= (var2 & 255L) << (var6 << 3) | ~(255L << (var6 << 3));
      var10000 = this.array;
      var10000[var7] ^= (var4 & 255L) << (var6 << 3);
   }

   public void set(int var1, long var2) {
      if (this.remainder == 0) {
         super.setXor(var1, var2);
      } else {
         int var4 = this.remainder << 3;
         int var5 = 8 - this.remainder << 3;
         this.array[this.cp + var1] = var2 << var4 | this.array[this.cp + var1] & -1L >>> var5;
         this.array[this.cp + var1 + 1] = var2 >>> var5 | this.array[this.cp + var1 + 1] & -1L << var4;
      }

   }

   public void setByte(int var1) {
      this.array[this.cp] = ((long)var1 & 255L) << (this.remainder << 3) | this.array[this.cp] & -1L >>> (8 - this.remainder << 3);
   }

   public void fill(int var1, byte[] var2, int var3, int var4) {
      if (this.remainder != 0) {
         int var5 = this.cp + var1;
         int var6 = this.remainder;
         long[] var10000 = this.array;
         var10000[var5] &= ~(-1L << (var6 << 3));

         for(int var7 = 0; var6 < 8 && var7 < var4; ++var6) {
            var10000 = this.array;
            var10000[var5] |= ((long)var2[var3] & 255L) << (var6 << 3);
            ++var3;
            ++var7;
         }

         ++var1;
         var4 -= 8 - this.remainder;
      }

      super.fill(var1, var2, var3, var4);
   }

   public void fillBytes(int var1, byte[] var2, int var3, int var4) {
      int var5 = var1 + this.remainder;
      int var6 = this.cp + (var5 >>> 3);
      var5 &= 7;
      if (var5 != 0) {
         long[] var10000 = this.array;
         var10000[var6] &= ~(-1L << (var5 << 3));

         int var7;
         for(var7 = 0; var5 < 8 && var7 < var4; ++var5) {
            var10000 = this.array;
            var10000[var6] |= ((long)var2[var3] & 255L) << (var5 << 3);
            ++var3;
            ++var7;
         }

         ++var6;
         var4 -= var7;
      }

      super.fill(var6 - this.cp, var2, var3, var4);
   }

   public void fillRandomBytes(int var1, SecureRandom var2, int var3) {
      byte[] var4 = new byte[var3];
      var2.nextBytes(var4);
      this.fillBytes(var1, var4, 0, var4.length);
   }

   public void changeIndex(PointerUnion var1) {
      this.array = var1.array;
      this.cp = var1.cp;
      this.remainder = var1.remainder;
   }
}
