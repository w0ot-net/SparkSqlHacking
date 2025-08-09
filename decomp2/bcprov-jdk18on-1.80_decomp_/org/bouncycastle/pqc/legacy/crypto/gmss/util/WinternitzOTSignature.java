package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

public class WinternitzOTSignature {
   private Digest messDigestOTS;
   private int mdsize;
   private int keysize;
   private byte[][] privateKeyOTS;
   private int w;
   private GMSSRandom gmssRandom;
   private int messagesize;
   private int checksumsize;

   public WinternitzOTSignature(byte[] var1, Digest var2, int var3) {
      this.w = var3;
      this.messDigestOTS = var2;
      this.gmssRandom = new GMSSRandom(this.messDigestOTS);
      this.mdsize = this.messDigestOTS.getDigestSize();
      this.messagesize = ((this.mdsize << 3) + var3 - 1) / var3;
      this.checksumsize = this.getLog((this.messagesize << var3) + 1);
      this.keysize = this.messagesize + (this.checksumsize + var3 - 1) / var3;
      this.privateKeyOTS = new byte[this.keysize][];
      byte[] var4 = new byte[this.mdsize];
      System.arraycopy(var1, 0, var4, 0, var4.length);

      for(int var5 = 0; var5 < this.keysize; ++var5) {
         this.privateKeyOTS[var5] = this.gmssRandom.nextSeed(var4);
      }

   }

   public byte[][] getPrivateKey() {
      return this.privateKeyOTS;
   }

   public byte[] getPublicKey() {
      byte[] var1 = new byte[this.keysize * this.mdsize];
      int var2 = 0;
      int var3 = (1 << this.w) - 1;

      for(int var4 = 0; var4 < this.keysize; ++var4) {
         this.hashPrivateKeyBlock(var4, var3, var1, var2);
         var2 += this.mdsize;
      }

      this.messDigestOTS.update(var1, 0, var1.length);
      byte[] var5 = new byte[this.mdsize];
      this.messDigestOTS.doFinal(var5, 0);
      return var5;
   }

   public byte[] getSignature(byte[] var1) {
      byte[] var2 = new byte[this.keysize * this.mdsize];
      byte[] var3 = new byte[this.mdsize];
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      this.messDigestOTS.update(var1, 0, var1.length);
      this.messDigestOTS.doFinal(var3, 0);
      if (8 % this.w == 0) {
         int var7 = 8 / this.w;
         int var8 = (1 << this.w) - 1;

         for(int var9 = 0; var9 < var3.length; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
               var6 = var3[var9] & var8;
               var5 += var6;
               this.hashPrivateKeyBlock(var4, var6, var2, var4 * this.mdsize);
               var3[var9] = (byte)(var3[var9] >>> this.w);
               ++var4;
            }
         }

         var5 = (this.messagesize << this.w) - var5;

         for(int var34 = 0; var34 < this.checksumsize; var34 += this.w) {
            var6 = var5 & var8;
            this.hashPrivateKeyBlock(var4, var6, var2, var4 * this.mdsize);
            var5 >>>= this.w;
            ++var4;
         }
      } else if (this.w < 8) {
         int var28 = this.mdsize / this.w;
         int var32 = (1 << this.w) - 1;
         int var11 = 0;

         for(int var12 = 0; var12 < var28; ++var12) {
            long var35 = 0L;

            for(int var13 = 0; var13 < this.w; ++var13) {
               var35 ^= (long)((var3[var11] & 255) << (var13 << 3));
               ++var11;
            }

            for(int var48 = 0; var48 < 8; ++var48) {
               var6 = (int)var35 & var32;
               var5 += var6;
               this.hashPrivateKeyBlock(var4, var6, var2, var4 * this.mdsize);
               var35 >>>= this.w;
               ++var4;
            }
         }

         var28 = this.mdsize % this.w;
         long var36 = 0L;

         for(int var42 = 0; var42 < var28; ++var42) {
            var36 ^= (long)((var3[var11] & 255) << (var42 << 3));
            ++var11;
         }

         var28 <<= 3;

         for(int var43 = 0; var43 < var28; var43 += this.w) {
            var6 = (int)var36 & var32;
            var5 += var6;
            this.hashPrivateKeyBlock(var4, var6, var2, var4 * this.mdsize);
            var36 >>>= this.w;
            ++var4;
         }

         var5 = (this.messagesize << this.w) - var5;

         for(int var44 = 0; var44 < this.checksumsize; var44 += this.w) {
            var6 = var5 & var32;
            this.hashPrivateKeyBlock(var4, var6, var2, var4 * this.mdsize);
            var5 >>>= this.w;
            ++var4;
         }
      } else if (this.w < 57) {
         int var31 = (this.mdsize << 3) - this.w;
         int var33 = (1 << this.w) - 1;
         byte[] var37 = new byte[this.mdsize];

         int var14;
         for(var14 = 0; var14 <= var31; ++var4) {
            int var15 = var14 >>> 3;
            int var17 = var14 % 8;
            var14 += this.w;
            int var16 = var14 + 7 >>> 3;
            long var38 = 0L;
            int var18 = 0;

            for(int var19 = var15; var19 < var16; ++var19) {
               var38 ^= (long)((var3[var19] & 255) << (var18 << 3));
               ++var18;
            }

            var38 >>>= var17;
            long var45 = var38 & (long)var33;
            var5 = (int)((long)var5 + var45);
            System.arraycopy(this.privateKeyOTS[var4], 0, var37, 0, this.mdsize);

            while(var45 > 0L) {
               this.messDigestOTS.update(var37, 0, var37.length);
               this.messDigestOTS.doFinal(var37, 0);
               --var45;
            }

            System.arraycopy(var37, 0, var2, var4 * this.mdsize, this.mdsize);
         }

         int var49 = var14 >>> 3;
         if (var49 < this.mdsize) {
            int var50 = var14 % 8;
            long var40 = 0L;
            int var51 = 0;

            for(int var52 = var49; var52 < this.mdsize; ++var52) {
               var40 ^= (long)((var3[var52] & 255) << (var51 << 3));
               ++var51;
            }

            var40 >>>= var50;
            long var46 = var40 & (long)var33;
            var5 = (int)((long)var5 + var46);
            System.arraycopy(this.privateKeyOTS[var4], 0, var37, 0, this.mdsize);

            while(var46 > 0L) {
               this.messDigestOTS.update(var37, 0, var37.length);
               this.messDigestOTS.doFinal(var37, 0);
               --var46;
            }

            System.arraycopy(var37, 0, var2, var4 * this.mdsize, this.mdsize);
            ++var4;
         }

         var5 = (this.messagesize << this.w) - var5;

         for(int var53 = 0; var53 < this.checksumsize; var53 += this.w) {
            long var47 = (long)(var5 & var33);
            System.arraycopy(this.privateKeyOTS[var4], 0, var37, 0, this.mdsize);

            while(var47 > 0L) {
               this.messDigestOTS.update(var37, 0, var37.length);
               this.messDigestOTS.doFinal(var37, 0);
               --var47;
            }

            System.arraycopy(var37, 0, var2, var4 * this.mdsize, this.mdsize);
            var5 >>>= this.w;
            ++var4;
         }
      }

      return var2;
   }

   public int getLog(int var1) {
      int var2 = 1;

      for(int var3 = 2; var3 < var1; ++var2) {
         var3 <<= 1;
      }

      return var2;
   }

   private void hashPrivateKeyBlock(int var1, int var2, byte[] var3, int var4) {
      if (var2 < 1) {
         System.arraycopy(this.privateKeyOTS[var1], 0, var3, var4, this.mdsize);
      } else {
         this.messDigestOTS.update(this.privateKeyOTS[var1], 0, this.mdsize);
         this.messDigestOTS.doFinal(var3, var4);

         while(true) {
            --var2;
            if (var2 <= 0) {
               break;
            }

            this.messDigestOTS.update(var3, var4, this.mdsize);
            this.messDigestOTS.doFinal(var3, var4);
         }
      }

   }
}
