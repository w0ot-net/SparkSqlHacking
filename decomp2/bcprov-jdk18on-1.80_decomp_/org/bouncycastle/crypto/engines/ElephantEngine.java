package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

public class ElephantEngine extends AEADBaseEngine {
   private final ElephantParameters parameters;
   private final int BLOCK_SIZE;
   private int nBits;
   private int nSBox;
   private final int nRounds;
   private byte lfsrIV;
   private byte[] npub;
   private byte[] expanded_key;
   private boolean initialised;
   private int nb_its;
   private byte[] ad;
   private int adOff;
   private int adlen;
   private final byte[] tag_buffer;
   private byte[] previous_mask;
   private byte[] current_mask;
   private byte[] next_mask;
   private final byte[] buffer;
   private final byte[] previous_outputMessage;
   private State m_state;
   private final ByteArrayOutputStream aadData;
   private int inputOff;
   private byte[] inputMessage;
   private int messageLen;
   private final byte[] sBoxLayer;
   private final byte[] KeccakRoundConstants;
   private final int[] KeccakRhoOffsets;

   public ElephantEngine(ElephantParameters var1) {
      this.m_state = ElephantEngine.State.Uninitialized;
      this.aadData = new ByteArrayOutputStream();
      this.sBoxLayer = new byte[]{-18, -19, -21, -32, -30, -31, -28, -17, -25, -22, -24, -27, -23, -20, -29, -26, -34, -35, -37, -48, -46, -47, -44, -33, -41, -38, -40, -43, -39, -36, -45, -42, -66, -67, -69, -80, -78, -79, -76, -65, -73, -70, -72, -75, -71, -68, -77, -74, 14, 13, 11, 0, 2, 1, 4, 15, 7, 10, 8, 5, 9, 12, 3, 6, 46, 45, 43, 32, 34, 33, 36, 47, 39, 42, 40, 37, 41, 44, 35, 38, 30, 29, 27, 16, 18, 17, 20, 31, 23, 26, 24, 21, 25, 28, 19, 22, 78, 77, 75, 64, 66, 65, 68, 79, 71, 74, 72, 69, 73, 76, 67, 70, -2, -3, -5, -16, -14, -15, -12, -1, -9, -6, -8, -11, -7, -4, -13, -10, 126, 125, 123, 112, 114, 113, 116, 127, 119, 122, 120, 117, 121, 124, 115, 118, -82, -83, -85, -96, -94, -95, -92, -81, -89, -86, -88, -91, -87, -84, -93, -90, -114, -115, -117, -128, -126, -127, -124, -113, -121, -118, -120, -123, -119, -116, -125, -122, 94, 93, 91, 80, 82, 81, 84, 95, 87, 90, 88, 85, 89, 92, 83, 86, -98, -99, -101, -112, -110, -111, -108, -97, -105, -102, -104, -107, -103, -100, -109, -106, -50, -51, -53, -64, -62, -63, -60, -49, -57, -54, -56, -59, -55, -52, -61, -58, 62, 61, 59, 48, 50, 49, 52, 63, 55, 58, 56, 53, 57, 60, 51, 54, 110, 109, 107, 96, 98, 97, 100, 111, 103, 106, 104, 101, 105, 108, 99, 102};
      this.KeccakRoundConstants = new byte[]{1, -126, -118, 0, -117, 1, -127, 9, -118, -120, 9, 10, -117, -117, -119, 3, 2, -128};
      this.KeccakRhoOffsets = new int[]{0, 1, 6, 4, 3, 4, 4, 6, 7, 4, 3, 2, 3, 1, 7, 1, 5, 7, 5, 0, 2, 2, 5, 0, 6};
      this.KEY_SIZE = 16;
      this.IV_SIZE = 12;
      switch (var1.ordinal()) {
         case 0:
            this.BLOCK_SIZE = 20;
            this.nBits = 160;
            this.nSBox = 20;
            this.nRounds = 80;
            this.lfsrIV = 117;
            this.MAC_SIZE = 8;
            this.algorithmName = "Elephant 160 AEAD";
            break;
         case 1:
            this.BLOCK_SIZE = 22;
            this.nBits = 176;
            this.nSBox = 22;
            this.nRounds = 90;
            this.lfsrIV = 69;
            this.algorithmName = "Elephant 176 AEAD";
            this.MAC_SIZE = 8;
            break;
         case 2:
            this.BLOCK_SIZE = 25;
            this.nRounds = 18;
            this.algorithmName = "Elephant 200 AEAD";
            this.MAC_SIZE = 16;
            break;
         default:
            throw new IllegalArgumentException("Invalid parameter settings for Elephant");
      }

      this.parameters = var1;
      this.tag_buffer = new byte[this.BLOCK_SIZE];
      this.previous_mask = new byte[this.BLOCK_SIZE];
      this.current_mask = new byte[this.BLOCK_SIZE];
      this.next_mask = new byte[this.BLOCK_SIZE];
      this.buffer = new byte[this.BLOCK_SIZE];
      this.previous_outputMessage = new byte[this.BLOCK_SIZE];
      this.initialised = false;
      this.reset(false);
   }

   private void permutation(byte[] var1) {
      switch (this.parameters.ordinal()) {
         case 0:
         case 1:
            byte var2 = this.lfsrIV;
            byte[] var3 = new byte[this.nSBox];

            for(int var8 = 0; var8 < this.nRounds; ++var8) {
               var1[0] ^= var2;
               int var10001 = this.nSBox - 1;
               var1[var10001] ^= (byte)((var2 & 1) << 7 | (var2 & 2) << 5 | (var2 & 4) << 3 | (var2 & 8) << 1 | (var2 & 16) >>> 1 | (var2 & 32) >>> 3 | (var2 & 64) >>> 5 | (var2 & 128) >>> 7);
               var2 = (byte)((var2 << 1 | (64 & var2) >>> 6 ^ (32 & var2) >>> 5) & 127);

               for(int var5 = 0; var5 < this.nSBox; ++var5) {
                  var1[var5] = this.sBoxLayer[var1[var5] & 255];
               }

               Arrays.fill(var3, (byte)0);

               for(int var6 = 0; var6 < this.nSBox; ++var6) {
                  for(int var7 = 0; var7 < 8; ++var7) {
                     int var9 = (var6 << 3) + var7;
                     if (var9 != this.nBits - 1) {
                        var9 = (var9 * this.nBits >> 2) % (this.nBits - 1);
                     }

                     var3[var9 >>> 3] = (byte)(var3[var9 >>> 3] ^ ((var1[var6] & 255) >>> var7 & 1) << (var9 & 7));
                  }
               }

               System.arraycopy(var3, 0, var1, 0, this.nSBox);
            }
            break;
         case 2:
            for(int var4 = 0; var4 < this.nRounds; ++var4) {
               this.KeccakP200Round(var1, var4);
            }
      }

   }

   private byte rotl(byte var1) {
      return (byte)((var1 & 255) << 1 | (var1 & 255) >>> 7);
   }

   private byte ROL8(byte var1, int var2) {
      return (byte)(var2 != 0 ? (var1 & 255) << var2 ^ (var1 & 255) >>> 8 - var2 : var1);
   }

   private int index(int var1, int var2) {
      return var1 + var2 * 5;
   }

   private void KeccakP200Round(byte[] var1, int var2) {
      byte[] var5 = new byte[25];

      for(int var3 = 0; var3 < 5; ++var3) {
         for(int var4 = 0; var4 < 5; ++var4) {
            var5[var3] ^= var1[this.index(var3, var4)];
         }
      }

      for(int var6 = 0; var6 < 5; ++var6) {
         var5[var6 + 5] = (byte)(this.ROL8(var5[(var6 + 1) % 5], 1) ^ var5[(var6 + 4) % 5]);
      }

      for(int var7 = 0; var7 < 5; ++var7) {
         for(int var12 = 0; var12 < 5; ++var12) {
            int var10001 = this.index(var7, var12);
            var1[var10001] ^= var5[var7 + 5];
         }
      }

      for(int var8 = 0; var8 < 5; ++var8) {
         for(int var13 = 0; var13 < 5; ++var13) {
            var5[this.index(var8, var13)] = this.ROL8(var1[this.index(var8, var13)], this.KeccakRhoOffsets[this.index(var8, var13)]);
         }
      }

      for(int var9 = 0; var9 < 5; ++var9) {
         for(int var14 = 0; var14 < 5; ++var14) {
            var1[this.index(var14, (2 * var9 + 3 * var14) % 5)] = var5[this.index(var9, var14)];
         }
      }

      for(int var15 = 0; var15 < 5; ++var15) {
         for(int var10 = 0; var10 < 5; ++var10) {
            var5[var10] = (byte)(var1[this.index(var10, var15)] ^ ~var1[this.index((var10 + 1) % 5, var15)] & var1[this.index((var10 + 2) % 5, var15)]);
         }

         for(int var11 = 0; var11 < 5; ++var11) {
            var1[this.index(var11, var15)] = var5[var11];
         }
      }

      var1[0] ^= this.KeccakRoundConstants[var2];
   }

   private void lfsr_step(byte[] var1, byte[] var2) {
      switch (this.parameters.ordinal()) {
         case 0:
            var1[this.BLOCK_SIZE - 1] = (byte)(((var2[0] & 255) << 3 | (var2[0] & 255) >>> 5) ^ (var2[3] & 255) << 7 ^ (var2[13] & 255) >>> 7);
            break;
         case 1:
            var1[this.BLOCK_SIZE - 1] = (byte)(this.rotl(var2[0]) ^ (var2[3] & 255) << 7 ^ (var2[19] & 255) >>> 7);
            break;
         case 2:
            var1[this.BLOCK_SIZE - 1] = (byte)(this.rotl(var2[0]) ^ this.rotl(var2[2]) ^ var2[13] << 1);
      }

      System.arraycopy(var2, 1, var1, 0, this.BLOCK_SIZE - 1);
   }

   private void xor_block(byte[] var1, byte[] var2, int var3, int var4) {
      for(int var5 = 0; var5 < var4; ++var5) {
         var1[var5] ^= var2[var5 + var3];
      }

   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.npub = var2;
      this.expanded_key = new byte[this.BLOCK_SIZE];
      System.arraycopy(var1, 0, this.expanded_key, 0, this.KEY_SIZE);
      this.permutation(this.expanded_key);
      this.initialised = true;
      this.m_state = this.forEncryption ? ElephantEngine.State.EncInit : ElephantEngine.State.DecInit;
      this.inputMessage = new byte[this.BLOCK_SIZE * 2 + (this.forEncryption ? 0 : this.MAC_SIZE)];
      this.reset(false);
   }

   public void processAADByte(byte var1) {
      this.aadData.write(var1);
   }

   public void processAADBytes(byte[] var1, int var2, int var3) {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else {
         this.aadData.write(var1, var2, var3);
      }
   }

   public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else if (this.inputOff + var3 - (this.forEncryption ? 0 : this.MAC_SIZE) >= this.BLOCK_SIZE) {
         int var6 = this.inputOff + this.messageLen + var3 - (this.forEncryption ? 0 : this.MAC_SIZE);
         int var7 = this.processAADBytes();
         int var8 = 1 + var6 / this.BLOCK_SIZE;
         int var9 = var6 % this.BLOCK_SIZE != 0 ? var8 : var8 - 1;
         int var10 = 1 + (this.IV_SIZE + var7) / this.BLOCK_SIZE;
         int var11 = Math.max(var8 + 1, var10 - 1);
         byte[] var12 = new byte[Math.max(var8, 1) * this.BLOCK_SIZE];
         System.arraycopy(this.inputMessage, 0, var12, 0, this.inputOff);
         System.arraycopy(var1, var2, var12, this.inputOff, Math.min(var3, var12.length - this.inputOff));
         int var13 = this.processBytes(var12, var4, var5, var11, var9, var8, var6, var10, false);
         int var14 = var13 - this.inputOff;
         if (var14 >= 0) {
            this.inputOff = this.inputOff + var3 - var13;
            System.arraycopy(var1, var2 + var14, this.inputMessage, 0, this.inputOff);
         } else {
            System.arraycopy(this.inputMessage, this.inputOff + var14, this.inputMessage, 0, -var14);
            System.arraycopy(var1, var2, this.inputMessage, -var14, var3);
            this.inputOff = var3 - var14;
         }

         this.messageLen += var13;
         return var13;
      } else {
         System.arraycopy(var1, var2, this.inputMessage, this.inputOff, var3);
         this.inputOff += var3;
         return 0;
      }
   }

   public int doFinal(byte[] var1, int var2) throws IllegalStateException, InvalidCipherTextException {
      if (!this.initialised) {
         throw new IllegalArgumentException(this.algorithmName + " needs call init function before doFinal");
      } else {
         int var3 = this.inputOff;
         if ((!this.forEncryption || var3 + var2 + this.MAC_SIZE <= var1.length) && (this.forEncryption || var3 + var2 - this.MAC_SIZE <= var1.length)) {
            int var4 = var3 + this.messageLen - (this.forEncryption ? 0 : this.MAC_SIZE);
            int var5 = var4 - this.messageLen;
            int var6 = this.processAADBytes();
            int var7 = 1 + var4 / this.BLOCK_SIZE;
            int var8 = var4 % this.BLOCK_SIZE != 0 ? var7 : var7 - 1;
            int var9 = 1 + (this.IV_SIZE + var6) / this.BLOCK_SIZE;
            int var10 = Math.max(var7 + 1, var9 - 1);
            var2 += this.processBytes(this.inputMessage, var1, var2, var10, var8, var7, var4, var9, true);
            this.mac = new byte[this.MAC_SIZE];
            this.xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
            this.permutation(this.tag_buffer);
            this.xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
            if (this.forEncryption) {
               System.arraycopy(this.tag_buffer, 0, this.mac, 0, this.MAC_SIZE);
               System.arraycopy(this.mac, 0, var1, var2, this.mac.length);
               var5 += this.MAC_SIZE;
            } else {
               this.inputOff -= this.MAC_SIZE;

               for(int var11 = 0; var11 < this.MAC_SIZE; ++var11) {
                  if (this.tag_buffer[var11] != this.inputMessage[this.inputOff + var11]) {
                     throw new IllegalArgumentException("Mac does not match");
                  }
               }
            }

            this.reset(false);
            return var5;
         } else {
            throw new OutputLengthException("output buffer is too short");
         }
      }
   }

   public int getUpdateOutputSize(int var1) {
      switch (this.m_state.ordinal()) {
         case 0:
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
         case 1:
         case 2:
         case 3:
            int var3 = this.inputOff + var1;
            return var3 - var3 % this.BLOCK_SIZE;
         case 4:
         case 8:
            return 0;
         case 5:
         case 6:
         case 7:
            int var2 = Math.max(0, this.inputOff + var1 - this.MAC_SIZE);
            return var2 - var2 % this.BLOCK_SIZE;
         default:
            return Math.max(0, var1 + this.inputOff - this.MAC_SIZE);
      }
   }

   public int getOutputSize(int var1) {
      switch (this.m_state.ordinal()) {
         case 0:
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
         case 1:
         case 2:
         case 3:
            return var1 + this.inputOff + this.MAC_SIZE;
         case 4:
         case 8:
            return 0;
         case 5:
         case 6:
         case 7:
         default:
            return Math.max(0, var1 + this.inputOff - this.MAC_SIZE);
      }
   }

   private int processAADBytes() {
      byte[] var1 = this.aadData.toByteArray();
      switch (this.m_state.ordinal()) {
         case 1:
         case 5:
            this.processAADBytes(this.tag_buffer);
         default:
            return var1.length;
      }
   }

   protected void reset(boolean var1) {
      this.aadData.reset();
      Arrays.fill(this.tag_buffer, (byte)0);
      Arrays.fill(this.previous_outputMessage, (byte)0);
      this.inputOff = 0;
      this.nb_its = 0;
      this.adOff = -1;
      this.messageLen = 0;
      super.reset(var1);
   }

   public int getBlockSize() {
      return this.BLOCK_SIZE;
   }

   private void checkAad() {
      switch (this.m_state.ordinal()) {
         case 3:
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
         case 4:
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
         case 5:
         case 6:
         default:
            return;
         case 7:
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
      }
   }

   private void processAADBytes(byte[] var1) {
      this.checkAad();
      if (this.adOff == -1) {
         this.adlen = this.aadData.size();
         this.ad = this.aadData.toByteArray();
         this.adOff = 0;
      }

      int var2 = 0;
      switch (this.m_state.ordinal()) {
         case 1:
            System.arraycopy(this.expanded_key, 0, this.current_mask, 0, this.BLOCK_SIZE);
            System.arraycopy(this.npub, 0, var1, 0, this.IV_SIZE);
            var2 += this.IV_SIZE;
            this.m_state = ElephantEngine.State.EncAad;
            break;
         case 2:
         case 6:
            if (this.adOff == this.adlen) {
               Arrays.fill(var1, 0, this.BLOCK_SIZE, (byte)0);
               var1[0] = 1;
               return;
            }
            break;
         case 3:
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
         case 4:
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
         case 5:
            System.arraycopy(this.expanded_key, 0, this.current_mask, 0, this.BLOCK_SIZE);
            System.arraycopy(this.npub, 0, var1, 0, this.IV_SIZE);
            var2 += this.IV_SIZE;
            this.m_state = ElephantEngine.State.DecAad;
            break;
         case 7:
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
      }

      int var3 = this.BLOCK_SIZE - var2;
      int var4 = this.adlen - this.adOff;
      if (var3 <= var4) {
         System.arraycopy(this.ad, this.adOff, var1, var2, var3);
         this.adOff += var3;
      } else {
         if (var4 > 0) {
            System.arraycopy(this.ad, this.adOff, var1, var2, var4);
            this.adOff += var4;
         }

         Arrays.fill(var1, var2 + var4, var2 + var3, (byte)0);
         var1[var2 + var4] = 1;
         switch (this.m_state.ordinal()) {
            case 2:
               this.m_state = ElephantEngine.State.EncData;
               break;
            case 6:
               this.m_state = ElephantEngine.State.DecData;
         }
      }

   }

   private int processBytes(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      int var10 = 0;
      byte[] var11 = new byte[this.BLOCK_SIZE];

      int var12;
      for(var12 = this.nb_its; var12 < var4; ++var12) {
         int var13 = var12 == var5 - 1 ? var7 - var12 * this.BLOCK_SIZE : this.BLOCK_SIZE;
         if (!var9 && (var7 <= var12 * this.BLOCK_SIZE || var13 % this.BLOCK_SIZE != 0)) {
            break;
         }

         this.lfsr_step(this.next_mask, this.current_mask);
         if (var12 < var5) {
            System.arraycopy(this.npub, 0, this.buffer, 0, this.IV_SIZE);
            Arrays.fill(this.buffer, this.IV_SIZE, this.BLOCK_SIZE, (byte)0);
            this.xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.permutation(this.buffer);
            this.xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.buffer, var1, var10, var13);
            System.arraycopy(this.buffer, 0, var2, var3, var13);
            if (this.forEncryption) {
               System.arraycopy(this.buffer, 0, var11, 0, var13);
            } else {
               System.arraycopy(var1, var10, var11, 0, var13);
            }

            var3 += var13;
            var10 += var13;
         }

         if (var12 > 0 && var12 <= var6) {
            int var14 = (var12 - 1) * this.BLOCK_SIZE;
            if (var14 == var7) {
               Arrays.fill(this.buffer, 0, this.BLOCK_SIZE, (byte)0);
               this.buffer[0] = 1;
            } else {
               int var15 = var7 - var14;
               if (this.BLOCK_SIZE <= var15) {
                  System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, this.BLOCK_SIZE);
               } else if (var15 > 0) {
                  System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, var15);
                  Arrays.fill(this.buffer, var15, this.BLOCK_SIZE, (byte)0);
                  this.buffer[var15] = 1;
               }
            }

            this.xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.permutation(this.buffer);
            this.xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
         }

         if (var12 + 1 < var8) {
            this.processAADBytes(this.buffer);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.permutation(this.buffer);
            this.xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
            this.xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
         }

         byte[] var16 = this.previous_mask;
         this.previous_mask = this.current_mask;
         this.current_mask = this.next_mask;
         this.next_mask = var16;
         System.arraycopy(var11, 0, this.previous_outputMessage, 0, this.BLOCK_SIZE);
      }

      this.nb_its = var12;
      return var10;
   }

   public static enum ElephantParameters {
      elephant160,
      elephant176,
      elephant200;

      // $FF: synthetic method
      private static ElephantParameters[] $values() {
         return new ElephantParameters[]{elephant160, elephant176, elephant200};
      }
   }

   private static enum State {
      Uninitialized,
      EncInit,
      EncAad,
      EncData,
      EncFinal,
      DecInit,
      DecAad,
      DecData,
      DecFinal;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{Uninitialized, EncInit, EncAad, EncData, EncFinal, DecInit, DecAad, DecData, DecFinal};
      }
   }
}
