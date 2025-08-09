package org.bouncycastle.pqc.legacy.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.legacy.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;

public class GMSSRootSig {
   private Digest messDigestOTS;
   private int mdsize;
   private int keysize;
   private byte[] privateKeyOTS;
   private byte[] hash;
   private byte[] sign;
   private int w;
   private GMSSRandom gmssRandom;
   private int messagesize;
   private int k;
   private int r;
   private int test;
   private int counter;
   private int ii;
   private long test8;
   private long big8;
   private int steps;
   private int checksum;
   private int height;
   private byte[] seed;

   public GMSSRootSig(Digest var1, byte[][] var2, int[] var3) {
      this.messDigestOTS = var1;
      this.gmssRandom = new GMSSRandom(this.messDigestOTS);
      this.counter = var3[0];
      this.test = var3[1];
      this.ii = var3[2];
      this.r = var3[3];
      this.steps = var3[4];
      this.keysize = var3[5];
      this.height = var3[6];
      this.w = var3[7];
      this.checksum = var3[8];
      this.mdsize = this.messDigestOTS.getDigestSize();
      this.k = (1 << this.w) - 1;
      int var4 = this.mdsize << 3;
      this.messagesize = (int)Math.ceil((double)var4 / (double)this.w);
      this.privateKeyOTS = var2[0];
      this.seed = var2[1];
      this.hash = var2[2];
      this.sign = var2[3];
      this.test8 = (long)(var2[4][0] & 255) | (long)(var2[4][1] & 255) << 8 | (long)(var2[4][2] & 255) << 16 | (long)(var2[4][3] & 255) << 24 | (long)(var2[4][4] & 255) << 32 | (long)(var2[4][5] & 255) << 40 | (long)(var2[4][6] & 255) << 48 | (long)(var2[4][7] & 255) << 56;
      this.big8 = (long)(var2[4][8] & 255) | (long)(var2[4][9] & 255) << 8 | (long)(var2[4][10] & 255) << 16 | (long)(var2[4][11] & 255) << 24 | (long)(var2[4][12] & 255) << 32 | (long)(var2[4][13] & 255) << 40 | (long)(var2[4][14] & 255) << 48 | (long)(var2[4][15] & 255) << 56;
   }

   public GMSSRootSig(Digest var1, int var2, int var3) {
      this.messDigestOTS = var1;
      this.gmssRandom = new GMSSRandom(this.messDigestOTS);
      this.mdsize = this.messDigestOTS.getDigestSize();
      this.w = var2;
      this.height = var3;
      this.k = (1 << var2) - 1;
      int var4 = this.mdsize << 3;
      this.messagesize = (int)Math.ceil((double)var4 / (double)var2);
   }

   public void initSign(byte[] var1, byte[] var2) {
      this.hash = new byte[this.mdsize];
      this.messDigestOTS.update(var2, 0, var2.length);
      this.hash = new byte[this.messDigestOTS.getDigestSize()];
      this.messDigestOTS.doFinal(this.hash, 0);
      byte[] var3 = new byte[this.mdsize];
      System.arraycopy(this.hash, 0, var3, 0, this.mdsize);
      int var4 = 0;
      int var5 = 0;
      int var6 = this.getLog((this.messagesize << this.w) + 1);
      if (8 % this.w == 0) {
         int var7 = 8 / this.w;

         for(int var8 = 0; var8 < this.mdsize; ++var8) {
            for(int var9 = 0; var9 < var7; ++var9) {
               var5 += var3[var8] & this.k;
               var3[var8] = (byte)(var3[var8] >>> this.w);
            }
         }

         this.checksum = (this.messagesize << this.w) - var5;
         var4 = this.checksum;

         for(int var24 = 0; var24 < var6; var24 += this.w) {
            var5 += var4 & this.k;
            var4 >>>= this.w;
         }
      } else if (this.w < 8) {
         int var25 = 0;
         int var10 = this.mdsize / this.w;

         for(int var11 = 0; var11 < var10; ++var11) {
            long var18 = 0L;

            for(int var12 = 0; var12 < this.w; ++var12) {
               var18 ^= (long)((var3[var25] & 255) << (var12 << 3));
               ++var25;
            }

            for(int var35 = 0; var35 < 8; ++var35) {
               var5 += (int)(var18 & (long)this.k);
               var18 >>>= this.w;
            }
         }

         var10 = this.mdsize % this.w;
         long var19 = 0L;

         for(int var31 = 0; var31 < var10; ++var31) {
            var19 ^= (long)((var3[var25] & 255) << (var31 << 3));
            ++var25;
         }

         var10 <<= 3;

         for(int var32 = 0; var32 < var10; var32 += this.w) {
            var5 += (int)(var19 & (long)this.k);
            var19 >>>= this.w;
         }

         this.checksum = (this.messagesize << this.w) - var5;
         var4 = this.checksum;

         for(int var33 = 0; var33 < var6; var33 += this.w) {
            var5 += var4 & this.k;
            var4 >>>= this.w;
         }
      } else if (this.w < 57) {
         long var21;
         int var26;
         for(var26 = 0; var26 <= (this.mdsize << 3) - this.w; var5 = (int)((long)var5 + (var21 & (long)this.k))) {
            int var29 = var26 >>> 3;
            int var36 = var26 % 8;
            var26 += this.w;
            int var34 = var26 + 7 >>> 3;
            var21 = 0L;
            int var13 = 0;

            for(int var14 = var29; var14 < var34; ++var14) {
               var21 ^= (long)((var3[var14] & 255) << (var13 << 3));
               ++var13;
            }

            var21 >>>= var36;
         }

         int var30 = var26 >>> 3;
         if (var30 < this.mdsize) {
            int var37 = var26 % 8;
            var21 = 0L;
            int var38 = 0;

            for(int var39 = var30; var39 < this.mdsize; ++var39) {
               var21 ^= (long)((var3[var39] & 255) << (var38 << 3));
               ++var38;
            }

            var21 >>>= var37;
            var5 = (int)((long)var5 + (var21 & (long)this.k));
         }

         this.checksum = (this.messagesize << this.w) - var5;
         var4 = this.checksum;

         for(int var40 = 0; var40 < var6; var40 += this.w) {
            var5 += var4 & this.k;
            var4 >>>= this.w;
         }
      }

      this.keysize = this.messagesize + (int)Math.ceil((double)var6 / (double)this.w);
      this.steps = (int)Math.ceil((double)(this.keysize + var5) / (double)(1 << this.height));
      this.sign = new byte[this.keysize * this.mdsize];
      this.counter = 0;
      this.test = 0;
      this.ii = 0;
      this.test8 = 0L;
      this.r = 0;
      this.privateKeyOTS = new byte[this.mdsize];
      this.seed = new byte[this.mdsize];
      System.arraycopy(var1, 0, this.seed, 0, this.mdsize);
   }

   public boolean updateSign() {
      for(int var1 = 0; var1 < this.steps; ++var1) {
         if (this.counter < this.keysize) {
            this.oneStep();
         }

         if (this.counter == this.keysize) {
            return true;
         }
      }

      return false;
   }

   public byte[] getSig() {
      return this.sign;
   }

   private void oneStep() {
      if (8 % this.w == 0) {
         if (this.test == 0) {
            this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            if (this.ii < this.mdsize) {
               this.test = this.hash[this.ii] & this.k;
               this.hash[this.ii] = (byte)(this.hash[this.ii] >>> this.w);
            } else {
               this.test = this.checksum & this.k;
               this.checksum >>>= this.w;
            }
         } else if (this.test > 0) {
            this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
            this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
            this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
            --this.test;
         }

         if (this.test == 0) {
            System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
            ++this.counter;
            if (this.counter % (8 / this.w) == 0) {
               ++this.ii;
            }
         }
      } else if (this.w < 8) {
         if (this.test != 0) {
            if (this.test > 0) {
               this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
               this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
               this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
               --this.test;
            }
         } else {
            if (this.counter % 8 == 0 && this.ii < this.mdsize) {
               this.big8 = 0L;
               if (this.counter < this.mdsize / this.w << 3) {
                  for(int var1 = 0; var1 < this.w; ++var1) {
                     this.big8 ^= (long)((this.hash[this.ii] & 255) << (var1 << 3));
                     ++this.ii;
                  }
               } else {
                  for(int var5 = 0; var5 < this.mdsize % this.w; ++var5) {
                     this.big8 ^= (long)((this.hash[this.ii] & 255) << (var5 << 3));
                     ++this.ii;
                  }
               }
            }

            if (this.counter == this.messagesize) {
               this.big8 = (long)this.checksum;
            }

            this.test = (int)(this.big8 & (long)this.k);
            this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
         }

         if (this.test == 0) {
            System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
            this.big8 >>>= this.w;
            ++this.counter;
         }
      } else if (this.w < 57) {
         if (this.test8 != 0L) {
            if (this.test8 > 0L) {
               this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
               this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
               this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
               --this.test8;
            }
         } else {
            this.big8 = 0L;
            this.ii = 0;
            int var3 = this.r % 8;
            int var6 = this.r >>> 3;
            if (var6 < this.mdsize) {
               int var2;
               if (this.r <= (this.mdsize << 3) - this.w) {
                  this.r += this.w;
                  var2 = this.r + 7 >>> 3;
               } else {
                  var2 = this.mdsize;
                  this.r += this.w;
               }

               for(int var4 = var6; var4 < var2; ++var4) {
                  this.big8 ^= (long)((this.hash[var4] & 255) << (this.ii << 3));
                  ++this.ii;
               }

               this.big8 >>>= var3;
               this.test8 = this.big8 & (long)this.k;
            } else {
               this.test8 = (long)(this.checksum & this.k);
               this.checksum >>>= this.w;
            }

            this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
         }

         if (this.test8 == 0L) {
            System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
            ++this.counter;
         }
      }

   }

   public int getLog(int var1) {
      int var2 = 1;

      for(int var3 = 2; var3 < var1; ++var2) {
         var3 <<= 1;
      }

      return var2;
   }

   public byte[][] getStatByte() {
      byte[][] var1 = new byte[5][this.mdsize];
      var1[0] = this.privateKeyOTS;
      var1[1] = this.seed;
      var1[2] = this.hash;
      var1[3] = this.sign;
      var1[4] = this.getStatLong();
      return var1;
   }

   public int[] getStatInt() {
      int[] var1 = new int[]{this.counter, this.test, this.ii, this.r, this.steps, this.keysize, this.height, this.w, this.checksum};
      return var1;
   }

   public byte[] getStatLong() {
      byte[] var1 = new byte[]{(byte)((int)(this.test8 & 255L)), (byte)((int)(this.test8 >> 8 & 255L)), (byte)((int)(this.test8 >> 16 & 255L)), (byte)((int)(this.test8 >> 24 & 255L)), (byte)((int)(this.test8 >> 32 & 255L)), (byte)((int)(this.test8 >> 40 & 255L)), (byte)((int)(this.test8 >> 48 & 255L)), (byte)((int)(this.test8 >> 56 & 255L)), (byte)((int)(this.big8 & 255L)), (byte)((int)(this.big8 >> 8 & 255L)), (byte)((int)(this.big8 >> 16 & 255L)), (byte)((int)(this.big8 >> 24 & 255L)), (byte)((int)(this.big8 >> 32 & 255L)), (byte)((int)(this.big8 >> 40 & 255L)), (byte)((int)(this.big8 >> 48 & 255L)), (byte)((int)(this.big8 >> 56 & 255L))};
      return var1;
   }

   public String toString() {
      String var1 = "" + this.big8 + "  ";
      int[] var2 = new int[9];
      var2 = this.getStatInt();
      byte[][] var3 = new byte[5][this.mdsize];
      var3 = this.getStatByte();

      for(int var4 = 0; var4 < 9; ++var4) {
         var1 = var1 + var2[var4] + " ";
      }

      for(int var7 = 0; var7 < 5; ++var7) {
         var1 = var1 + new String(Hex.encode(var3[var7])) + " ";
      }

      return var1;
   }
}
