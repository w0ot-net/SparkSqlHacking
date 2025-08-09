package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;

public class PhotonBeetleEngine extends AEADBufferBaseEngine {
   private boolean input_empty;
   private byte[] K;
   private byte[] N;
   private byte[] state;
   private byte[][] state_2d;
   private int aadLen;
   private int messageLen;
   private final int RATE_INBYTES_HALF;
   private final int STATE_INBYTES;
   private final int LAST_THREE_BITS_OFFSET;
   private final int D = 8;
   private final byte[][] RC = new byte[][]{{1, 3, 7, 14, 13, 11, 6, 12, 9, 2, 5, 10}, {0, 2, 6, 15, 12, 10, 7, 13, 8, 3, 4, 11}, {2, 0, 4, 13, 14, 8, 5, 15, 10, 1, 6, 9}, {6, 4, 0, 9, 10, 12, 1, 11, 14, 5, 2, 13}, {14, 12, 8, 1, 2, 4, 9, 3, 6, 13, 10, 5}, {15, 13, 9, 0, 3, 5, 8, 2, 7, 12, 11, 4}, {13, 15, 11, 2, 1, 7, 10, 0, 5, 14, 9, 6}, {9, 11, 15, 6, 5, 3, 14, 4, 1, 10, 13, 2}};
   private final byte[][] MixColMatrix = new byte[][]{{2, 4, 2, 11, 2, 8, 5, 6}, {12, 9, 8, 13, 7, 7, 5, 2}, {4, 4, 13, 13, 9, 4, 13, 9}, {1, 6, 5, 1, 12, 13, 15, 14}, {15, 12, 9, 13, 14, 5, 14, 13}, {9, 14, 5, 15, 4, 12, 9, 6}, {12, 2, 2, 10, 3, 1, 1, 14}, {15, 1, 13, 10, 5, 10, 2, 3}};
   private final byte[] sbox = new byte[]{12, 5, 6, 11, 9, 0, 10, 13, 3, 14, 15, 8, 4, 7, 1, 2};

   public PhotonBeetleEngine(PhotonBeetleParameters var1) {
      this.KEY_SIZE = 16;
      this.IV_SIZE = 16;
      this.MAC_SIZE = 16;
      short var2 = 0;
      short var3 = 0;
      switch (var1.ordinal()) {
         case 0:
            var3 = 32;
            var2 = 224;
            break;
         case 1:
            var3 = 128;
            var2 = 128;
      }

      this.AADBufferSize = this.BlockSize = var3 + 7 >>> 3;
      this.RATE_INBYTES_HALF = this.BlockSize >>> 1;
      int var4 = var3 + var2;
      this.STATE_INBYTES = var4 + 7 >>> 3;
      this.LAST_THREE_BITS_OFFSET = var4 - (this.STATE_INBYTES - 1 << 3) - 3;
      this.initialised = false;
      this.algorithmName = "Photon-Beetle AEAD";
      this.m_aad = new byte[this.AADBufferSize];
   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.K = var1;
      this.N = var2;
      this.state = new byte[this.STATE_INBYTES];
      this.state_2d = new byte[8][8];
      this.mac = new byte[this.MAC_SIZE];
      this.initialised = true;
      this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
      this.m_state = this.forEncryption ? AEADBufferBaseEngine.State.EncInit : AEADBufferBaseEngine.State.DecInit;
      this.reset(false);
   }

   protected void processBufferAAD(byte[] var1, int var2) {
      this.PHOTON_Permutation();
      this.XOR(var1, var2, this.BlockSize);
   }

   protected void processBuffer(byte[] var1, int var2, byte[] var3, int var4) {
      this.PHOTON_Permutation();
      this.rhoohr(var3, var4, var1, var2, this.BlockSize);
   }

   public void processAADByte(byte var1) {
      ++this.aadLen;
      super.processAADByte(var1);
   }

   public void processAADBytes(byte[] var1, int var2, int var3) {
      this.aadLen += var3;
      super.processAADBytes(var1, var2, var3);
   }

   public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException {
      this.messageLen += var3;
      return super.processBytes(var1, var2, var3, var4, var5);
   }

   protected void processFinalBlock(byte[] var1, int var2) {
      int var3 = this.messageLen - (this.forEncryption ? 0 : this.MAC_SIZE);
      int var4 = this.m_bufPos;
      if (this.aadLen != 0 || var3 != 0) {
         this.input_empty = false;
      }

      byte var5 = this.select(this.aadLen != 0, var3 % this.BlockSize == 0, (byte)5, (byte)6);
      if (var3 != 0) {
         if (var4 != 0) {
            this.PHOTON_Permutation();
            this.rhoohr(var1, var2, this.m_buf, 0, var4);
            if (var4 < this.BlockSize) {
               byte[] var10000 = this.state;
               var10000[var4] = (byte)(var10000[var4] ^ 1);
            }
         }

         byte[] var6 = this.state;
         int var10001 = this.STATE_INBYTES - 1;
         var6[var10001] = (byte)(var6[var10001] ^ var5 << this.LAST_THREE_BITS_OFFSET);
      }

      if (this.input_empty) {
         byte[] var7 = this.state;
         int var8 = this.STATE_INBYTES - 1;
         var7[var8] = (byte)(var7[var8] ^ 1 << this.LAST_THREE_BITS_OFFSET);
      }

      this.PHOTON_Permutation();
      this.mac = new byte[this.MAC_SIZE];
      System.arraycopy(this.state, 0, this.mac, 0, this.MAC_SIZE);
   }

   protected void processFinalAAD() {
      if (!this.aadFinished) {
         if (this.aadLen != 0) {
            if (this.m_aadPos != 0) {
               this.PHOTON_Permutation();
               this.XOR(this.m_aad, 0, this.m_aadPos);
               if (this.m_aadPos < this.BlockSize) {
                  byte[] var10000 = this.state;
                  int var10001 = this.m_aadPos;
                  var10000[var10001] = (byte)(var10000[var10001] ^ 1);
               }
            }

            byte[] var1 = this.state;
            int var2 = this.STATE_INBYTES - 1;
            var1[var2] = (byte)(var1[var2] ^ this.select(this.messageLen - (this.forEncryption ? 0 : this.MAC_SIZE) > 0, this.aadLen % this.BlockSize == 0, (byte)3, (byte)4) << this.LAST_THREE_BITS_OFFSET);
         }

         this.m_aadPos = 0;
         this.aadFinished = true;
      }

   }

   protected void reset(boolean var1) {
      if (!this.initialised) {
         throw new IllegalArgumentException("Need call init function before encryption/decryption");
      } else {
         this.bufferReset();
         this.input_empty = true;
         this.aadLen = 0;
         this.aadFinished = false;
         this.messageLen = 0;
         System.arraycopy(this.K, 0, this.state, 0, this.K.length);
         System.arraycopy(this.N, 0, this.state, this.K.length, this.N.length);
         super.reset(var1);
      }
   }

   private void PHOTON_Permutation() {
      byte var4 = 3;
      byte var5 = 7;
      byte var6 = 64;

      for(int var1 = 0; var1 < var6; ++var1) {
         this.state_2d[var1 >>> var4][var1 & var5] = (byte)((this.state[var1 >> 1] & 255) >>> 4 * (var1 & 1) & 15);
      }

      byte var7 = 12;

      for(int var8 = 0; var8 < var7; ++var8) {
         for(int var12 = 0; var12 < 8; ++var12) {
            byte[] var10000 = this.state_2d[var12];
            var10000[0] ^= this.RC[var12][var8];
         }

         for(int var13 = 0; var13 < 8; ++var13) {
            for(int var2 = 0; var2 < 8; ++var2) {
               this.state_2d[var13][var2] = this.sbox[this.state_2d[var13][var2]];
            }
         }

         for(int var14 = 1; var14 < 8; ++var14) {
            System.arraycopy(this.state_2d[var14], 0, this.state, 0, 8);
            System.arraycopy(this.state, var14, this.state_2d[var14], 0, 8 - var14);
            System.arraycopy(this.state, 0, this.state_2d[var14], 8 - var14, var14);
         }

         for(int var18 = 0; var18 < 8; ++var18) {
            for(int var15 = 0; var15 < 8; ++var15) {
               int var9 = 0;

               for(int var3 = 0; var3 < 8; ++var3) {
                  byte var10 = this.MixColMatrix[var15][var3];
                  byte var11 = this.state_2d[var3][var18];
                  var9 ^= var10 * (var11 & 1);
                  var9 ^= var10 * (var11 & 2);
                  var9 ^= var10 * (var11 & 4);
                  var9 ^= var10 * (var11 & 8);
               }

               int var24 = var9 >>> 4;
               var9 = var9 & 15 ^ var24 ^ var24 << 1;
               int var25 = var9 >>> 4;
               var9 = var9 & 15 ^ var25 ^ var25 << 1;
               this.state[var15] = (byte)var9;
            }

            for(int var16 = 0; var16 < 8; ++var16) {
               this.state_2d[var16][var18] = this.state[var16];
            }
         }
      }

      for(int var17 = 0; var17 < var6; var17 += 2) {
         this.state[var17 >>> 1] = (byte)(this.state_2d[var17 >>> var4][var17 & var5] & 15 | (this.state_2d[var17 >>> var4][var17 + 1 & var5] & 15) << 4);
      }

   }

   private byte select(boolean var1, boolean var2, byte var3, byte var4) {
      if (var1 && var2) {
         return 1;
      } else if (var1) {
         return 2;
      } else {
         return var2 ? var3 : var4;
      }
   }

   private void rhoohr(byte[] var1, int var2, byte[] var3, int var4, int var5) {
      byte[] var6 = this.state_2d[0];
      int var8 = Math.min(var5, this.RATE_INBYTES_HALF);

      int var7;
      for(var7 = 0; var7 < this.RATE_INBYTES_HALF - 1; ++var7) {
         var6[var7] = (byte)((this.state[var7] & 255) >>> 1 | (this.state[var7 + 1] & 1) << 7);
      }

      var6[this.RATE_INBYTES_HALF - 1] = (byte)((this.state[var7] & 255) >>> 1 | (this.state[0] & 1) << 7);

      for(var7 = 0; var7 < var8; var1[var7 + var2] = (byte)(this.state[var7 + this.RATE_INBYTES_HALF] ^ var3[var7++ + var4])) {
      }

      while(var7 < var5) {
         var1[var7 + var2] = (byte)(var6[var7 - this.RATE_INBYTES_HALF] ^ var3[var7++ + var4]);
      }

      if (this.forEncryption) {
         this.XOR(var3, var4, var5);
      } else {
         this.XOR(var1, var2, var5);
      }

   }

   private void XOR(byte[] var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         byte[] var10000 = this.state;
         var10000[var4] ^= var1[var2++];
      }

   }

   public static enum PhotonBeetleParameters {
      pb32,
      pb128;

      // $FF: synthetic method
      private static PhotonBeetleParameters[] $values() {
         return new PhotonBeetleParameters[]{pb32, pb128};
      }
   }
}
