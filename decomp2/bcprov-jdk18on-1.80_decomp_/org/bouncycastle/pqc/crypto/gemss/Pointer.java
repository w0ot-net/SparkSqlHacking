package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.util.Pack;

class Pointer {
   protected long[] array;
   protected int cp;

   public Pointer() {
      this.cp = 0;
   }

   public Pointer(int var1) {
      this.array = new long[var1];
      this.cp = 0;
   }

   public Pointer(Pointer var1) {
      this.array = var1.array;
      this.cp = var1.cp;
   }

   public Pointer(Pointer var1, int var2) {
      this.array = var1.array;
      this.cp = var1.cp + var2;
   }

   public long get(int var1) {
      return this.array[this.cp + var1];
   }

   public long get() {
      return this.array[this.cp];
   }

   public void set(int var1, long var2) {
      this.array[this.cp + var1] = var2;
   }

   public void set(long var1) {
      this.array[this.cp] = var1;
   }

   public void setXor(int var1, long var2) {
      long[] var10000 = this.array;
      int var10001 = this.cp + var1;
      var10000[var10001] ^= var2;
   }

   public void setXor(long var1) {
      long[] var10000 = this.array;
      int var10001 = this.cp;
      var10000[var10001] ^= var1;
   }

   public void setXorRange(Pointer var1, int var2) {
      int var3 = this.cp;
      int var4 = var1.cp;

      for(int var5 = 0; var5 < var2; ++var5) {
         long[] var10000 = this.array;
         int var10001 = var3++;
         var10000[var10001] ^= var1.array[var4++];
      }

   }

   public void setXorRange(Pointer var1, int var2, int var3) {
      int var4 = this.cp;
      var2 += var1.cp;

      for(int var5 = 0; var5 < var3; ++var5) {
         long[] var10000 = this.array;
         int var10001 = var4++;
         var10000[var10001] ^= var1.array[var2++];
      }

   }

   public void setXorRange(int var1, Pointer var2, int var3, int var4) {
      var1 += this.cp;
      var3 += var2.cp;

      for(int var5 = 0; var5 < var4; ++var5) {
         long[] var10000 = this.array;
         int var10001 = var1++;
         var10000[var10001] ^= var2.array[var3++];
      }

   }

   public void setXorRange_SelfMove(Pointer var1, int var2) {
      int var3 = var1.cp;

      for(int var4 = 0; var4 < var2; ++var4) {
         long[] var10000 = this.array;
         int var10003 = this.cp++;
         var10000[var10003] ^= var1.array[var3++];
      }

   }

   public void setXorMatrix_NoMove(Pointer var1, int var2, int var3) {
      int var4 = this.cp;

      for(int var6 = 0; var6 < var3; ++var6) {
         int var7 = 0;

         for(int var5 = var4; var7 < var2; ++var7) {
            long[] var10000 = this.array;
            int var10001 = var5++;
            var10000[var10001] ^= var1.array[var1.cp++];
         }
      }

   }

   public void setXorMatrix(Pointer var1, int var2, int var3) {
      int var4 = this.cp;

      for(int var6 = 0; var6 < var3; ++var6) {
         int var7 = 0;

         for(int var5 = var4; var7 < var2; ++var7) {
            long[] var10000 = this.array;
            int var10001 = var5++;
            var10000[var10001] ^= var1.array[var1.cp++];
         }
      }

      this.cp += var2;
   }

   public void setXorRangeXor(int var1, Pointer var2, int var3, Pointer var4, int var5, int var6) {
      var1 += this.cp;
      var3 += var2.cp;
      var5 += var4.cp;

      for(int var7 = 0; var7 < var6; ++var7) {
         long[] var10000 = this.array;
         int var10001 = var1++;
         var10000[var10001] ^= var2.array[var3++] ^ var4.array[var5++];
      }

   }

   public void setXorRange(int var1, PointerUnion var2, int var3, int var4) {
      var1 += this.cp;
      var3 += var2.cp;
      if (var2.remainder == 0) {
         for(int var5 = 0; var5 < var4; ++var5) {
            long[] var10000 = this.array;
            int var10001 = var1++;
            var10000[var10001] ^= var2.array[var3++];
         }
      } else {
         int var10 = var2.remainder << 3;
         int var6 = 8 - var2.remainder << 3;

         for(int var7 = 0; var7 < var4; ++var7) {
            long[] var11 = this.array;
            int var12 = var1++;
            long var13 = var11[var12];
            long var10003 = var2.array[var3] >>> var10;
            ++var3;
            var11[var12] = var13 ^ (var10003 | var2.array[var3] << var6);
         }
      }

   }

   public void setXorRangeAndMask(Pointer var1, int var2, long var3) {
      int var5 = this.cp;
      int var6 = var1.cp;

      for(int var7 = 0; var7 < var2; ++var7) {
         long[] var10000 = this.array;
         int var10001 = var5++;
         var10000[var10001] ^= var1.array[var6++] & var3;
      }

   }

   public void setXorRangeAndMaskMove(Pointer var1, int var2, long var3) {
      int var5 = this.cp;

      for(int var6 = 0; var6 < var2; ++var6) {
         long[] var10000 = this.array;
         int var10001 = var5++;
         var10000[var10001] ^= var1.array[var1.cp++] & var3;
      }

   }

   public void setRangeRotate(int var1, Pointer var2, int var3, int var4, int var5) {
      int var6 = 64 - var5;
      var1 += this.cp;
      var3 += var2.cp;

      for(int var7 = 0; var7 < var4; ++var7) {
         int var10001 = var1++;
         long var10002 = var2.array[var3] >>> var6;
         ++var3;
         this.array[var10001] = var10002 ^ var2.array[var3] << var5;
      }

   }

   public void move(int var1) {
      this.cp += var1;
   }

   public void moveIncremental() {
      ++this.cp;
   }

   public long[] getArray() {
      return this.array;
   }

   public int getIndex() {
      return this.cp;
   }

   public void setAnd(int var1, long var2) {
      long[] var10000 = this.array;
      int var10001 = this.cp + var1;
      var10000[var10001] &= var2;
   }

   public void setAnd(long var1) {
      long[] var10000 = this.array;
      int var10001 = this.cp;
      var10000[var10001] &= var1;
   }

   public void setClear(int var1) {
      this.array[this.cp + var1] = 0L;
   }

   public void changeIndex(Pointer var1) {
      this.array = var1.array;
      this.cp = var1.cp;
   }

   public void changeIndex(int var1) {
      this.cp = var1;
   }

   public void changeIndex(Pointer var1, int var2) {
      this.array = var1.array;
      this.cp = var1.cp + var2;
   }

   public void setRangeClear(int var1, int var2) {
      var1 += this.cp;
      Arrays.fill(this.array, var1, var1 + var2, 0L);
   }

   public int getLength() {
      return this.array.length - this.cp;
   }

   public void copyFrom(Pointer var1, int var2) {
      System.arraycopy(var1.array, var1.cp, this.array, this.cp, var2);
   }

   public void copyFrom(int var1, Pointer var2, int var3, int var4) {
      System.arraycopy(var2.array, var2.cp + var3, this.array, this.cp + var1, var4);
   }

   public void set1_gf2n(int var1, int var2) {
      int var3 = this.cp + var1;
      this.array[var3++] = 1L;

      for(int var4 = 1; var4 < var2; ++var4) {
         this.array[var3++] = 0L;
      }

   }

   public byte[] toBytes(int var1) {
      byte[] var2 = new byte[var1];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = (byte)((int)(this.array[this.cp + (var3 >>> 3)] >>> ((var3 & 7) << 3)));
      }

      return var2;
   }

   public void indexReset() {
      this.cp = 0;
   }

   public void fillRandom(int var1, SecureRandom var2, int var3) {
      byte[] var4 = new byte[var3];
      var2.nextBytes(var4);
      this.fill(var1, var4, 0, var4.length);
   }

   public void fill(int var1, byte[] var2, int var3, int var4) {
      int var5 = 0;

      int var6;
      for(var6 = this.cp + var1; var6 < this.array.length && var5 + 8 <= var4; ++var6) {
         this.array[var6] = Pack.littleEndianToLong(var2, var3);
         var3 += 8;
         var5 += 8;
      }

      if (var5 < var4 && var6 < this.array.length) {
         int var7 = 0;

         for(this.array[var6] = 0L; var7 < 8 && var5 < var4; ++var5) {
            long[] var10000 = this.array;
            var10000[var6] |= ((long)var2[var3] & 255L) << (var7 << 3);
            ++var7;
            ++var3;
         }
      }

   }

   public void setRangeFromXor(int var1, Pointer var2, int var3, Pointer var4, int var5, int var6) {
      var1 += this.cp;
      var3 += var2.cp;
      var5 += var4.cp;

      for(int var7 = 0; var7 < var6; ++var7) {
         this.array[var1++] = var2.array[var3++] ^ var4.array[var5++];
      }

   }

   public void setRangeFromXor(Pointer var1, Pointer var2, int var3) {
      int var4 = 0;
      int var5 = this.cp;
      int var6 = var1.cp;

      for(int var7 = var2.cp; var4 < var3; ++var4) {
         this.array[var5++] = var1.array[var6++] ^ var2.array[var7++];
      }

   }

   public void setRangeFromXorAndMask_xor(Pointer var1, Pointer var2, long var3, int var5) {
      int var6 = this.cp;
      int var7 = var1.cp;
      int var8 = var2.cp;

      for(int var9 = 0; var9 < var5; ++var9) {
         this.array[var6] = (var1.array[var7] ^ var2.array[var8]) & var3;
         long[] var10000 = var1.array;
         int var10001 = var7++;
         var10000[var10001] ^= this.array[var6];
         var10000 = var2.array;
         var10001 = var8++;
         var10000[var10001] ^= this.array[var6++];
      }

   }

   public int is0_gf2n(int var1, int var2) {
      long var3 = this.get(var1);

      for(int var5 = 1; var5 < var2; ++var5) {
         var3 |= this.get(var1 + var5);
      }

      return (int)GeMSSUtils.NORBITS_UINT(var3);
   }

   public long getDotProduct(int var1, Pointer var2, int var3, int var4) {
      var1 += this.cp;
      var3 += var2.cp;
      long var5 = this.array[var1++] & var2.array[var3++];

      for(int var7 = 1; var7 < var4; ++var7) {
         var5 ^= this.array[var1++] & var2.array[var3++];
      }

      return var5;
   }

   public int getD_for_not0_or_plus(int var1, int var2) {
      int var3 = var2;
      int var5 = 0;
      long var7 = 0L;

      for(int var6 = this.cp; var3 > 0; --var3) {
         long var9 = this.array[var6++];

         for(int var4 = 1; var4 < var1; ++var4) {
            var9 |= this.array[var6++];
         }

         var7 |= GeMSSUtils.ORBITS_UINT(var9);
         var5 = (int)((long)var5 + var7);
      }

      return var5;
   }

   public int setRange_xi(long var1, int var3, int var4) {
      for(int var5 = 0; var5 < var4; ++var3) {
         this.array[this.cp + var3] = -(var1 >>> var5 & 1L);
         ++var5;
      }

      return var3;
   }

   public int searchDegree(int var1, int var2, int var3) {
      while(this.is0_gf2n(var1 * var3, var3) != 0 && var1 >= var2) {
         --var1;
      }

      return var1;
   }

   public void setRangePointerUnion(PointerUnion var1, int var2) {
      if (var1.remainder == 0) {
         System.arraycopy(var1.array, var1.cp, this.array, this.cp, var2);
      } else {
         int var3 = 8 - var1.remainder << 3;
         int var4 = var1.remainder << 3;
         int var5 = this.cp;
         int var6 = var1.cp;

         for(int var7 = 0; var7 < var2; ++var7) {
            int var10001 = var5++;
            long var10002 = var1.array[var6] >>> var4;
            ++var6;
            this.array[var10001] = var10002 ^ var1.array[var6] << var3;
         }
      }

   }

   public void setRangePointerUnion(PointerUnion var1, int var2, int var3) {
      int var4 = var3 & 63;
      int var5 = 64 - var4;
      int var6 = this.cp;
      int var7 = var1.cp;
      if (var1.remainder == 0) {
         for(int var8 = 0; var8 < var2; ++var8) {
            int var10001 = var6++;
            long var10002 = var1.array[var7] >>> var4;
            ++var7;
            this.array[var10001] = var10002 ^ var1.array[var7] << var5;
         }
      } else {
         int var11 = var1.remainder << 3;
         int var9 = 8 - var1.remainder << 3;

         for(int var10 = 0; var10 < var2; ++var10) {
            int var12 = var6++;
            long var13 = var1.array[var7] >>> var11;
            ++var7;
            this.array[var12] = (var13 | var1.array[var7] << var9) >>> var4 ^ (var1.array[var7] >>> var11 | var1.array[var7 + 1] << var9) << var5;
         }
      }

   }

   public void setRangePointerUnion_Check(PointerUnion var1, int var2, int var3) {
      int var4 = var3 & 63;
      int var5 = 64 - var4;
      int var6 = this.cp;
      int var7 = var1.cp;
      if (var1.remainder == 0) {
         int var8;
         for(var8 = 0; var8 < var2 && var7 < var1.array.length - 1; ++var8) {
            int var10001 = var6++;
            long var10002 = var1.array[var7] >>> var4;
            ++var7;
            this.array[var10001] = var10002 ^ var1.array[var7] << var5;
         }

         if (var8 < var2) {
            this.array[var6] = var1.array[var7] >>> var4;
         }
      } else {
         int var9 = var1.remainder << 3;
         int var10 = 8 - var1.remainder << 3;

         int var12;
         for(var12 = 0; var12 < var2 && var7 < var1.array.length - 2; ++var12) {
            int var13 = var6++;
            long var14 = var1.array[var7] >>> var9;
            ++var7;
            this.array[var13] = (var14 | var1.array[var7] << var10) >>> var4 ^ (var1.array[var7] >>> var9 | var1.array[var7 + 1] << var10) << var5;
         }

         if (var12 < var2) {
            long var15 = var1.array[var7] >>> var9;
            ++var7;
            this.array[var6] = (var15 | var1.array[var7] << var10) >>> var4 ^ var1.array[var7] >>> var9 << var5;
         }
      }

   }

   public int isEqual_nocst_gf2(Pointer var1, int var2) {
      int var3 = var1.cp;
      int var4 = this.cp;

      for(int var5 = 0; var5 < var2; ++var5) {
         if (this.array[var4++] != var1.array[var3++]) {
            return 0;
         }
      }

      return 1;
   }

   public void swap(Pointer var1) {
      long[] var2 = var1.array;
      int var3 = var1.cp;
      var1.array = this.array;
      var1.cp = this.cp;
      this.array = var2;
      this.cp = var3;
   }
}
