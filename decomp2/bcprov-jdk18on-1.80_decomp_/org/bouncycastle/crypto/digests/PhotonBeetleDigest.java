package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

public class PhotonBeetleDigest implements Digest {
   private byte[] state;
   private byte[][] state_2d;
   private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
   private final int INITIAL_RATE_INBYTES = 16;
   private int RATE_INBYTES = 4;
   private int SQUEEZE_RATE_INBYTES = 16;
   private int STATE_INBYTES = 32;
   private int TAG_INBYTES = 32;
   private int LAST_THREE_BITS_OFFSET = 5;
   private int ROUND = 12;
   private int D = 8;
   private int Dq = 3;
   private int Dr = 7;
   private int DSquare = 64;
   private int S = 4;
   private int S_1 = 3;
   private byte[][] RC = new byte[][]{{1, 3, 7, 14, 13, 11, 6, 12, 9, 2, 5, 10}, {0, 2, 6, 15, 12, 10, 7, 13, 8, 3, 4, 11}, {2, 0, 4, 13, 14, 8, 5, 15, 10, 1, 6, 9}, {6, 4, 0, 9, 10, 12, 1, 11, 14, 5, 2, 13}, {14, 12, 8, 1, 2, 4, 9, 3, 6, 13, 10, 5}, {15, 13, 9, 0, 3, 5, 8, 2, 7, 12, 11, 4}, {13, 15, 11, 2, 1, 7, 10, 0, 5, 14, 9, 6}, {9, 11, 15, 6, 5, 3, 14, 4, 1, 10, 13, 2}};
   private byte[][] MixColMatrix = new byte[][]{{2, 4, 2, 11, 2, 8, 5, 6}, {12, 9, 8, 13, 7, 7, 5, 2}, {4, 4, 13, 13, 9, 4, 13, 9}, {1, 6, 5, 1, 12, 13, 15, 14}, {15, 12, 9, 13, 14, 5, 14, 13}, {9, 14, 5, 15, 4, 12, 9, 6}, {12, 2, 2, 10, 3, 1, 1, 14}, {15, 1, 13, 10, 5, 10, 2, 3}};
   private byte[] sbox = new byte[]{12, 5, 6, 11, 9, 0, 10, 13, 3, 14, 15, 8, 4, 7, 1, 2};

   public PhotonBeetleDigest() {
      this.state = new byte[this.STATE_INBYTES];
      this.state_2d = new byte[this.D][this.D];
   }

   public String getAlgorithmName() {
      return "Photon-Beetle Hash";
   }

   public int getDigestSize() {
      return this.TAG_INBYTES;
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
         int var4 = var3.length;
         if (var4 == 0) {
            byte[] var10000 = this.state;
            int var10001 = this.STATE_INBYTES - 1;
            var10000[var10001] = (byte)(var10000[var10001] ^ 1 << this.LAST_THREE_BITS_OFFSET);
         } else if (var4 <= 16) {
            System.arraycopy(var3, 0, this.state, 0, var4);
            if (var4 < 16) {
               byte[] var9 = this.state;
               var9[var4] = (byte)(var9[var4] ^ 1);
            }

            byte[] var10 = this.state;
            int var13 = this.STATE_INBYTES - 1;
            var10[var13] = (byte)(var10[var13] ^ (var4 < 16 ? 1 : 2) << this.LAST_THREE_BITS_OFFSET);
         } else {
            System.arraycopy(var3, 0, this.state, 0, 16);
            var4 -= 16;
            int var5 = (var4 + this.RATE_INBYTES - 1) / this.RATE_INBYTES;

            int var6;
            for(var6 = 0; var6 < var5 - 1; ++var6) {
               this.PHOTON_Permutation();
               Bytes.xorTo(this.RATE_INBYTES, var3, 16 + var6 * this.RATE_INBYTES, this.state, 0);
            }

            this.PHOTON_Permutation();
            int var7 = var4 - var6 * this.RATE_INBYTES;
            Bytes.xorTo(var7, var3, 16 + var6 * this.RATE_INBYTES, this.state, 0);
            if (var7 < this.RATE_INBYTES) {
               byte[] var11 = this.state;
               var11[var7] = (byte)(var11[var7] ^ 1);
            }

            byte[] var12 = this.state;
            int var14 = this.STATE_INBYTES - 1;
            var12[var14] = (byte)(var12[var14] ^ (var4 % this.RATE_INBYTES == 0 ? 1 : 2) << this.LAST_THREE_BITS_OFFSET);
         }

         this.PHOTON_Permutation();
         System.arraycopy(this.state, 0, var1, var2, this.SQUEEZE_RATE_INBYTES);
         this.PHOTON_Permutation();
         System.arraycopy(this.state, 0, var1, var2 + this.SQUEEZE_RATE_INBYTES, this.TAG_INBYTES - this.SQUEEZE_RATE_INBYTES);
         this.reset();
         return this.TAG_INBYTES;
      }
   }

   public void reset() {
      this.buffer.reset();
      Arrays.fill((byte[])this.state, (byte)0);
   }

   void PHOTON_Permutation() {
      for(int var1 = 0; var1 < this.DSquare; ++var1) {
         this.state_2d[var1 >>> this.Dq][var1 & this.Dr] = (byte)((this.state[var1 >> 1] & 255) >>> 4 * (var1 & 1) & 15);
      }

      for(int var4 = 0; var4 < this.ROUND; ++var4) {
         for(int var8 = 0; var8 < this.D; ++var8) {
            byte[] var10000 = this.state_2d[var8];
            var10000[0] ^= this.RC[var8][var4];
         }

         for(int var9 = 0; var9 < this.D; ++var9) {
            for(int var2 = 0; var2 < this.D; ++var2) {
               this.state_2d[var9][var2] = this.sbox[this.state_2d[var9][var2]];
            }
         }

         for(int var10 = 1; var10 < this.D; ++var10) {
            System.arraycopy(this.state_2d[var10], 0, this.state, 0, this.D);
            System.arraycopy(this.state, var10, this.state_2d[var10], 0, this.D - var10);
            System.arraycopy(this.state, 0, this.state_2d[var10], this.D - var10, var10);
         }

         for(int var14 = 0; var14 < this.D; ++var14) {
            for(int var11 = 0; var11 < this.D; ++var11) {
               int var5 = 0;

               for(int var3 = 0; var3 < this.D; ++var3) {
                  byte var6 = this.MixColMatrix[var11][var3];
                  byte var7 = this.state_2d[var3][var14];
                  var5 ^= var6 * (var7 & 1);
                  var5 ^= var6 * (var7 & 2);
                  var5 ^= var6 * (var7 & 4);
                  var5 ^= var6 * (var7 & 8);
               }

               int var20 = var5 >>> 4;
               var5 = var5 & 15 ^ var20 ^ var20 << 1;
               int var21 = var5 >>> 4;
               var5 = var5 & 15 ^ var21 ^ var21 << 1;
               this.state[var11] = (byte)var5;
            }

            for(int var12 = 0; var12 < this.D; ++var12) {
               this.state_2d[var12][var14] = this.state[var12];
            }
         }
      }

      for(int var13 = 0; var13 < this.DSquare; var13 += 2) {
         this.state[var13 >>> 1] = (byte)(this.state_2d[var13 >>> this.Dq][var13 & this.Dr] & 15 | (this.state_2d[var13 >>> this.Dq][var13 + 1 & this.Dr] & 15) << 4);
      }

   }
}
