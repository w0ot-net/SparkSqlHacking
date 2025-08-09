package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class XoodyakDigest implements Digest {
   private byte[] state = new byte[48];
   private int phase;
   private MODE mode;
   private int Rabsorb;
   private final int f_bPrime = 48;
   private final int Rhash = 16;
   private final int PhaseDown = 1;
   private final int PhaseUp = 2;
   private final int MAXROUNDS = 12;
   private final int TAGLEN = 16;
   private final int[] RC = new int[]{88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};
   private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

   public XoodyakDigest() {
      this.reset();
   }

   public String getAlgorithmName() {
      return "Xoodyak Hash";
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
         byte[] var3 = this.buffer.toByteArray();
         int var4 = 0;
         int var5 = this.buffer.size();
         byte var6 = 3;

         do {
            if (this.phase != 2) {
               this.Up((byte[])null, 0, 0, 0);
            }

            int var7 = Math.min(var5, this.Rabsorb);
            this.Down(var3, var4, var7, var6);
            var6 = 0;
            var4 += var7;
            var5 -= var7;
         } while(var5 != 0);

         this.Up(var1, var2, 16, 64);
         this.Down((byte[])null, 0, 0, 0);
         this.Up(var1, var2 + 16, 16, 0);
         this.reset();
         return 32;
      }
   }

   public void reset() {
      Arrays.fill((byte[])this.state, (byte)0);
      this.phase = 2;
      this.mode = XoodyakDigest.MODE.ModeHash;
      this.Rabsorb = 16;
      this.buffer.reset();
   }

   private void Up(byte[] var1, int var2, int var3, int var4) {
      if (this.mode != XoodyakDigest.MODE.ModeHash) {
         byte[] var10000 = this.state;
         var10000[47] = (byte)(var10000[47] ^ var4);
      }

      int var5 = Pack.littleEndianToInt(this.state, 0);
      int var6 = Pack.littleEndianToInt(this.state, 4);
      int var7 = Pack.littleEndianToInt(this.state, 8);
      int var8 = Pack.littleEndianToInt(this.state, 12);
      int var9 = Pack.littleEndianToInt(this.state, 16);
      int var10 = Pack.littleEndianToInt(this.state, 20);
      int var11 = Pack.littleEndianToInt(this.state, 24);
      int var12 = Pack.littleEndianToInt(this.state, 28);
      int var13 = Pack.littleEndianToInt(this.state, 32);
      int var14 = Pack.littleEndianToInt(this.state, 36);
      int var15 = Pack.littleEndianToInt(this.state, 40);
      int var16 = Pack.littleEndianToInt(this.state, 44);

      for(int var17 = 0; var17 < 12; ++var17) {
         int var18 = var5 ^ var9 ^ var13;
         int var19 = var6 ^ var10 ^ var14;
         int var20 = var7 ^ var11 ^ var15;
         int var21 = var8 ^ var12 ^ var16;
         int var22 = Integers.rotateLeft(var21, 5) ^ Integers.rotateLeft(var21, 14);
         int var23 = Integers.rotateLeft(var18, 5) ^ Integers.rotateLeft(var18, 14);
         int var24 = Integers.rotateLeft(var19, 5) ^ Integers.rotateLeft(var19, 14);
         int var25 = Integers.rotateLeft(var20, 5) ^ Integers.rotateLeft(var20, 14);
         var5 ^= var22;
         var9 ^= var22;
         var13 ^= var22;
         var6 ^= var23;
         var10 ^= var23;
         var14 ^= var23;
         var7 ^= var24;
         var11 ^= var24;
         var15 ^= var24;
         var8 ^= var25;
         var12 ^= var25;
         var16 ^= var25;
         int var34 = Integers.rotateLeft(var13, 11);
         int var35 = Integers.rotateLeft(var14, 11);
         int var36 = Integers.rotateLeft(var15, 11);
         int var37 = Integers.rotateLeft(var16, 11);
         int var26 = var5 ^ this.RC[var17];
         var5 = var26 ^ ~var12 & var34;
         var6 ^= ~var9 & var35;
         var7 ^= ~var10 & var36;
         var8 ^= ~var11 & var37;
         int var43 = var12 ^ ~var34 & var26;
         int var45 = var9 ^ ~var35 & var6;
         int var47 = var10 ^ ~var36 & var7;
         int var49 = var11 ^ ~var37 & var8;
         var34 ^= ~var26 & var12;
         var35 ^= ~var6 & var9;
         var36 ^= ~var7 & var10;
         var37 ^= ~var8 & var11;
         var9 = Integers.rotateLeft(var43, 1);
         var10 = Integers.rotateLeft(var45, 1);
         var11 = Integers.rotateLeft(var47, 1);
         var12 = Integers.rotateLeft(var49, 1);
         var13 = Integers.rotateLeft(var36, 8);
         var14 = Integers.rotateLeft(var37, 8);
         var15 = Integers.rotateLeft(var34, 8);
         var16 = Integers.rotateLeft(var35, 8);
      }

      Pack.intToLittleEndian(var5, this.state, 0);
      Pack.intToLittleEndian(var6, this.state, 4);
      Pack.intToLittleEndian(var7, this.state, 8);
      Pack.intToLittleEndian(var8, this.state, 12);
      Pack.intToLittleEndian(var9, this.state, 16);
      Pack.intToLittleEndian(var10, this.state, 20);
      Pack.intToLittleEndian(var11, this.state, 24);
      Pack.intToLittleEndian(var12, this.state, 28);
      Pack.intToLittleEndian(var13, this.state, 32);
      Pack.intToLittleEndian(var14, this.state, 36);
      Pack.intToLittleEndian(var15, this.state, 40);
      Pack.intToLittleEndian(var16, this.state, 44);
      this.phase = 2;
      if (var1 != null) {
         System.arraycopy(this.state, 0, var1, var2, var3);
      }

   }

   void Down(byte[] var1, int var2, int var3, int var4) {
      for(int var5 = 0; var5 < var3; ++var5) {
         byte[] var10000 = this.state;
         var10000[var5] ^= var1[var2++];
      }

      byte[] var6 = this.state;
      var6[var3] = (byte)(var6[var3] ^ 1);
      var6 = this.state;
      var6[47] = (byte)(var6[47] ^ (this.mode == XoodyakDigest.MODE.ModeHash ? var4 & 1 : var4));
      this.phase = 1;
   }

   static enum MODE {
      ModeHash,
      ModeKeyed;

      // $FF: synthetic method
      private static MODE[] $values() {
         return new MODE[]{ModeHash, ModeKeyed};
      }
   }
}
