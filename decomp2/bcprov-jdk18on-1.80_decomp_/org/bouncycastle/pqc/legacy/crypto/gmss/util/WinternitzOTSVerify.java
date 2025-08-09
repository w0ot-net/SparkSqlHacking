package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

public class WinternitzOTSVerify {
   private Digest messDigestOTS;
   private int mdsize;
   private int w;

   public WinternitzOTSVerify(Digest var1, int var2) {
      this.w = var2;
      this.messDigestOTS = var1;
      this.mdsize = this.messDigestOTS.getDigestSize();
   }

   public int getSignatureLength() {
      int var1 = this.messDigestOTS.getDigestSize();
      int var2 = ((var1 << 3) + (this.w - 1)) / this.w;
      int var3 = this.getLog((var2 << this.w) + 1);
      var2 += (var3 + this.w - 1) / this.w;
      return var1 * var2;
   }

   public byte[] Verify(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[this.mdsize];
      this.messDigestOTS.update(var1, 0, var1.length);
      this.messDigestOTS.doFinal(var3, 0);
      int var4 = ((this.mdsize << 3) + (this.w - 1)) / this.w;
      int var5 = this.getLog((var4 << this.w) + 1);
      int var6 = var4 + (var5 + this.w - 1) / this.w;
      int var7 = this.mdsize * var6;
      if (var7 != var2.length) {
         return null;
      } else {
         byte[] var8 = new byte[var7];
         int var9 = 0;
         int var10 = 0;
         if (8 % this.w == 0) {
            int var12 = 8 / this.w;
            int var13 = (1 << this.w) - 1;

            for(int var14 = 0; var14 < var3.length; ++var14) {
               for(int var15 = 0; var15 < var12; ++var15) {
                  int var11 = var3[var14] & var13;
                  var9 += var11;
                  this.hashSignatureBlock(var2, var10 * this.mdsize, var13 - var11, var8, var10 * this.mdsize);
                  var3[var14] = (byte)(var3[var14] >>> this.w);
                  ++var10;
               }
            }

            var9 = (var4 << this.w) - var9;

            for(int var39 = 0; var39 < var5; var39 += this.w) {
               int var28 = var9 & var13;
               this.hashSignatureBlock(var2, var10 * this.mdsize, var13 - var28, var8, var10 * this.mdsize);
               var9 >>>= this.w;
               ++var10;
            }
         } else if (this.w < 8) {
            int var32 = this.mdsize / this.w;
            int var37 = (1 << this.w) - 1;
            int var16 = 0;

            for(int var17 = 0; var17 < var32; ++var17) {
               long var40 = 0L;

               for(int var18 = 0; var18 < this.w; ++var18) {
                  var40 ^= (long)((var3[var16] & 255) << (var18 << 3));
                  ++var16;
               }

               for(int var53 = 0; var53 < 8; ++var53) {
                  int var29 = (int)(var40 & (long)var37);
                  var9 += var29;
                  this.hashSignatureBlock(var2, var10 * this.mdsize, var37 - var29, var8, var10 * this.mdsize);
                  var40 >>>= this.w;
                  ++var10;
               }
            }

            var32 = this.mdsize % this.w;
            long var41 = 0L;

            for(int var47 = 0; var47 < var32; ++var47) {
               var41 ^= (long)((var3[var16] & 255) << (var47 << 3));
               ++var16;
            }

            var32 <<= 3;

            for(int var48 = 0; var48 < var32; var48 += this.w) {
               int var30 = (int)(var41 & (long)var37);
               var9 += var30;
               this.hashSignatureBlock(var2, var10 * this.mdsize, var37 - var30, var8, var10 * this.mdsize);
               var41 >>>= this.w;
               ++var10;
            }

            var9 = (var4 << this.w) - var9;

            for(int var49 = 0; var49 < var5; var49 += this.w) {
               int var31 = var9 & var37;
               this.hashSignatureBlock(var2, var10 * this.mdsize, var37 - var31, var8, var10 * this.mdsize);
               var9 >>>= this.w;
               ++var10;
            }
         } else if (this.w < 57) {
            int var35 = (this.mdsize << 3) - this.w;
            int var38 = (1 << this.w) - 1;
            byte[] var42 = new byte[this.mdsize];

            int var19;
            for(var19 = 0; var19 <= var35; ++var10) {
               int var20 = var19 >>> 3;
               int var22 = var19 % 8;
               var19 += this.w;
               int var21 = var19 + 7 >>> 3;
               long var43 = 0L;
               int var23 = 0;

               for(int var24 = var20; var24 < var21; ++var24) {
                  var43 ^= (long)((var3[var24] & 255) << (var23 << 3));
                  ++var23;
               }

               var43 >>>= var22;
               long var50 = var43 & (long)var38;
               var9 = (int)((long)var9 + var50);
               System.arraycopy(var2, var10 * this.mdsize, var42, 0, this.mdsize);

               while(var50 < (long)var38) {
                  this.messDigestOTS.update(var42, 0, var42.length);
                  this.messDigestOTS.doFinal(var42, 0);
                  ++var50;
               }

               System.arraycopy(var42, 0, var8, var10 * this.mdsize, this.mdsize);
            }

            int var54 = var19 >>> 3;
            if (var54 < this.mdsize) {
               int var55 = var19 % 8;
               long var45 = 0L;
               int var56 = 0;

               for(int var57 = var54; var57 < this.mdsize; ++var57) {
                  var45 ^= (long)((var3[var57] & 255) << (var56 << 3));
                  ++var56;
               }

               var45 >>>= var55;
               long var51 = var45 & (long)var38;
               var9 = (int)((long)var9 + var51);
               System.arraycopy(var2, var10 * this.mdsize, var42, 0, this.mdsize);

               while(var51 < (long)var38) {
                  this.messDigestOTS.update(var42, 0, var42.length);
                  this.messDigestOTS.doFinal(var42, 0);
                  ++var51;
               }

               System.arraycopy(var42, 0, var8, var10 * this.mdsize, this.mdsize);
               ++var10;
            }

            var9 = (var4 << this.w) - var9;

            for(int var58 = 0; var58 < var5; var58 += this.w) {
               long var52 = (long)(var9 & var38);
               System.arraycopy(var2, var10 * this.mdsize, var42, 0, this.mdsize);

               while(var52 < (long)var38) {
                  this.messDigestOTS.update(var42, 0, var42.length);
                  this.messDigestOTS.doFinal(var42, 0);
                  ++var52;
               }

               System.arraycopy(var42, 0, var8, var10 * this.mdsize, this.mdsize);
               var9 >>>= this.w;
               ++var10;
            }
         }

         this.messDigestOTS.update(var8, 0, var8.length);
         byte[] var36 = new byte[this.mdsize];
         this.messDigestOTS.doFinal(var36, 0);
         return var36;
      }
   }

   public int getLog(int var1) {
      int var2 = 1;

      for(int var3 = 2; var3 < var1; ++var2) {
         var3 <<= 1;
      }

      return var2;
   }

   private void hashSignatureBlock(byte[] var1, int var2, int var3, byte[] var4, int var5) {
      if (var3 < 1) {
         System.arraycopy(var1, var2, var4, var5, this.mdsize);
      } else {
         this.messDigestOTS.update(var1, var2, this.mdsize);
         this.messDigestOTS.doFinal(var4, var5);

         while(true) {
            --var3;
            if (var3 <= 0) {
               break;
            }

            this.messDigestOTS.update(var4, var5, this.mdsize);
            this.messDigestOTS.doFinal(var4, var5);
         }
      }

   }
}
