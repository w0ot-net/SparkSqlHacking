package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class XoodyakEngine extends AEADBufferBaseEngine {
   private byte[] state;
   private int phase;
   private MODE mode;
   private final int f_bPrime_1 = 47;
   private byte[] K;
   private byte[] iv;
   private final int PhaseUp = 2;
   private final int[] RC = new int[]{88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};
   private boolean encrypted;
   private byte aadcd;

   public XoodyakEngine() {
      this.algorithmName = "Xoodyak AEAD";
      this.KEY_SIZE = 16;
      this.IV_SIZE = 16;
      this.MAC_SIZE = 16;
      this.BlockSize = 24;
      this.AADBufferSize = 44;
      this.m_aad = new byte[this.AADBufferSize];
   }

   public void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.K = var1;
      this.iv = var2;
      this.state = new byte[48];
      this.mac = new byte[this.MAC_SIZE];
      this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
      this.initialised = true;
      this.m_state = this.forEncryption ? AEADBufferBaseEngine.State.EncInit : AEADBufferBaseEngine.State.DecInit;
      this.reset();
   }

   protected void processBufferAAD(byte[] var1, int var2) {
      this.AbsorbAny(var1, var2, this.AADBufferSize, this.aadcd);
      this.aadcd = 0;
   }

   protected void processFinalAAD() {
      if (this.mode != XoodyakEngine.MODE.ModeKeyed) {
         throw new IllegalArgumentException("Xoodyak has not been initialised");
      } else {
         if (!this.aadFinished) {
            this.AbsorbAny(this.m_aad, 0, this.m_aadPos, this.aadcd);
            this.aadFinished = true;
            this.m_aadPos = 0;
         }

      }
   }

   protected void processBuffer(byte[] var1, int var2, byte[] var3, int var4) {
      this.processFinalAAD();
      this.encrypt(var1, var2, this.BlockSize, var3, var4);
   }

   private void encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) {
      int var6 = var3;
      byte[] var8 = new byte[this.BlockSize];

      for(int var9 = this.encrypted ? 0 : 128; var6 != 0 || !this.encrypted; this.encrypted = true) {
         int var7 = Math.min(var6, this.BlockSize);
         if (this.forEncryption) {
            System.arraycopy(var1, var2, var8, 0, var7);
         }

         this.Up((byte[])null, 0, var9);

         for(int var10 = 0; var10 < var7; ++var10) {
            var4[var5 + var10] = (byte)(var1[var2++] ^ this.state[var10]);
         }

         if (this.forEncryption) {
            this.Down(var8, 0, var7, 0);
         } else {
            this.Down(var4, var5, var7, 0);
         }

         var9 = 0;
         var5 += var7;
         var6 -= var7;
      }

   }

   protected void processFinalBlock(byte[] var1, int var2) {
      this.processFinalAAD();
      if (this.forEncryption) {
         Arrays.fill((byte[])this.m_buf, this.m_bufPos, this.BlockSize, (byte)0);
      }

      this.encrypt(this.m_buf, 0, this.m_bufPos, var1, var2);
      this.mac = new byte[this.MAC_SIZE];
      this.Up(this.mac, this.MAC_SIZE, 64);
   }

   protected void reset(boolean var1) {
      if (!this.initialised) {
         throw new IllegalArgumentException("Need call init function before encryption/decryption");
      } else {
         Arrays.fill((byte[])this.state, (byte)0);
         this.aadFinished = false;
         this.encrypted = false;
         this.phase = 2;
         Arrays.fill((byte[])this.m_buf, (byte)0);
         Arrays.fill((byte[])this.m_aad, (byte)0);
         this.m_bufPos = 0;
         this.m_aadPos = 0;
         this.aadcd = 3;
         int var2 = this.K.length;
         int var3 = this.iv.length;
         byte[] var4 = new byte[this.AADBufferSize];
         this.mode = XoodyakEngine.MODE.ModeKeyed;
         System.arraycopy(this.K, 0, var4, 0, var2);
         System.arraycopy(this.iv, 0, var4, var2, var3);
         var4[var2 + var3] = (byte)var3;
         this.AbsorbAny(var4, 0, var2 + var3 + 1, 2);
         super.reset(var1);
      }
   }

   private void AbsorbAny(byte[] var1, int var2, int var3, int var4) {
      do {
         if (this.phase != 2) {
            this.Up((byte[])null, 0, 0);
         }

         int var5 = Math.min(var3, this.AADBufferSize);
         this.Down(var1, var2, var5, var4);
         var4 = 0;
         var2 += var5;
         var3 -= var5;
      } while(var3 != 0);

   }

   private void Up(byte[] var1, int var2, int var3) {
      if (this.mode != XoodyakEngine.MODE.ModeHash) {
         byte[] var10000 = this.state;
         var10000[47] = (byte)(var10000[47] ^ var3);
      }

      int var4 = Pack.littleEndianToInt(this.state, 0);
      int var5 = Pack.littleEndianToInt(this.state, 4);
      int var6 = Pack.littleEndianToInt(this.state, 8);
      int var7 = Pack.littleEndianToInt(this.state, 12);
      int var8 = Pack.littleEndianToInt(this.state, 16);
      int var9 = Pack.littleEndianToInt(this.state, 20);
      int var10 = Pack.littleEndianToInt(this.state, 24);
      int var11 = Pack.littleEndianToInt(this.state, 28);
      int var12 = Pack.littleEndianToInt(this.state, 32);
      int var13 = Pack.littleEndianToInt(this.state, 36);
      int var14 = Pack.littleEndianToInt(this.state, 40);
      int var15 = Pack.littleEndianToInt(this.state, 44);

      for(int var16 = 0; var16 < 12; ++var16) {
         int var17 = var4 ^ var8 ^ var12;
         int var18 = var5 ^ var9 ^ var13;
         int var19 = var6 ^ var10 ^ var14;
         int var20 = var7 ^ var11 ^ var15;
         int var21 = Integers.rotateLeft(var20, 5) ^ Integers.rotateLeft(var20, 14);
         int var22 = Integers.rotateLeft(var17, 5) ^ Integers.rotateLeft(var17, 14);
         int var23 = Integers.rotateLeft(var18, 5) ^ Integers.rotateLeft(var18, 14);
         int var24 = Integers.rotateLeft(var19, 5) ^ Integers.rotateLeft(var19, 14);
         var4 ^= var21;
         var8 ^= var21;
         var12 ^= var21;
         var5 ^= var22;
         var9 ^= var22;
         var13 ^= var22;
         var6 ^= var23;
         var10 ^= var23;
         var14 ^= var23;
         var7 ^= var24;
         var11 ^= var24;
         var15 ^= var24;
         int var33 = Integers.rotateLeft(var12, 11);
         int var34 = Integers.rotateLeft(var13, 11);
         int var35 = Integers.rotateLeft(var14, 11);
         int var36 = Integers.rotateLeft(var15, 11);
         int var25 = var4 ^ this.RC[var16];
         var4 = var25 ^ ~var11 & var33;
         var5 ^= ~var8 & var34;
         var6 ^= ~var9 & var35;
         var7 ^= ~var10 & var36;
         int var42 = var11 ^ ~var33 & var25;
         int var44 = var8 ^ ~var34 & var5;
         int var46 = var9 ^ ~var35 & var6;
         int var48 = var10 ^ ~var36 & var7;
         var33 ^= ~var25 & var11;
         var34 ^= ~var5 & var8;
         var35 ^= ~var6 & var9;
         var36 ^= ~var7 & var10;
         var8 = Integers.rotateLeft(var42, 1);
         var9 = Integers.rotateLeft(var44, 1);
         var10 = Integers.rotateLeft(var46, 1);
         var11 = Integers.rotateLeft(var48, 1);
         var12 = Integers.rotateLeft(var35, 8);
         var13 = Integers.rotateLeft(var36, 8);
         var14 = Integers.rotateLeft(var33, 8);
         var15 = Integers.rotateLeft(var34, 8);
      }

      Pack.intToLittleEndian(var4, this.state, 0);
      Pack.intToLittleEndian(var5, this.state, 4);
      Pack.intToLittleEndian(var6, this.state, 8);
      Pack.intToLittleEndian(var7, this.state, 12);
      Pack.intToLittleEndian(var8, this.state, 16);
      Pack.intToLittleEndian(var9, this.state, 20);
      Pack.intToLittleEndian(var10, this.state, 24);
      Pack.intToLittleEndian(var11, this.state, 28);
      Pack.intToLittleEndian(var12, this.state, 32);
      Pack.intToLittleEndian(var13, this.state, 36);
      Pack.intToLittleEndian(var14, this.state, 40);
      Pack.intToLittleEndian(var15, this.state, 44);
      this.phase = 2;
      if (var1 != null) {
         System.arraycopy(this.state, 0, var1, 0, var2);
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
      var6[47] = (byte)(var6[47] ^ (this.mode == XoodyakEngine.MODE.ModeHash ? var4 & 1 : var4));
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
