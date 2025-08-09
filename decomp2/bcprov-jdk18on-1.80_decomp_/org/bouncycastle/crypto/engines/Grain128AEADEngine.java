package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;

public class Grain128AEADEngine extends AEADBaseEngine {
   private static final int STATE_SIZE = 4;
   private byte[] workingKey;
   private byte[] workingIV;
   private int[] lfsr;
   private int[] nfsr;
   private int[] authAcc;
   private int[] authSr;
   private boolean initialised = false;
   private boolean aadFinished = false;
   private final ErasableOutputStream aadData = new ErasableOutputStream();

   public Grain128AEADEngine() {
      this.algorithmName = "Grain-128AEAD";
      this.KEY_SIZE = 16;
      this.IV_SIZE = 12;
      this.MAC_SIZE = 8;
   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.workingIV = new byte[16];
      this.workingKey = var1;
      this.lfsr = new int[4];
      this.nfsr = new int[4];
      this.authAcc = new int[2];
      this.authSr = new int[2];
      System.arraycopy(var2, 0, this.workingIV, 0, this.IV_SIZE);
      this.reset();
   }

   private void initGrain() {
      for(int var1 = 0; var1 < 320; ++var1) {
         int var2 = this.getOutput();
         this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0] ^ var2) & 1);
         this.lfsr = this.shift(this.lfsr, (this.getOutputLFSR() ^ var2) & 1);
      }

      for(int var4 = 0; var4 < 8; ++var4) {
         for(int var5 = 0; var5 < 8; ++var5) {
            int var3 = this.getOutput();
            this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0] ^ var3 ^ this.workingKey[var4] >> var5) & 1);
            this.lfsr = this.shift(this.lfsr, (this.getOutputLFSR() ^ var3 ^ this.workingKey[var4 + 8] >> var5) & 1);
         }
      }

      this.initGrain(this.authAcc);
      this.initGrain(this.authSr);
      this.initialised = true;
   }

   private void initGrain(int[] var1) {
      for(int var2 = 0; var2 < 2; ++var2) {
         for(int var3 = 0; var3 < 32; ++var3) {
            int var4 = this.getOutput();
            this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0]) & 1);
            this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() & 1);
            var1[var2] |= var4 << var3;
         }
      }

   }

   private int getOutputNFSR() {
      int var1 = this.nfsr[0];
      int var2 = this.nfsr[0] >>> 3;
      int var3 = this.nfsr[0] >>> 11;
      int var4 = this.nfsr[0] >>> 13;
      int var5 = this.nfsr[0] >>> 17;
      int var6 = this.nfsr[0] >>> 18;
      int var7 = this.nfsr[0] >>> 22;
      int var8 = this.nfsr[0] >>> 24;
      int var9 = this.nfsr[0] >>> 25;
      int var10 = this.nfsr[0] >>> 26;
      int var11 = this.nfsr[0] >>> 27;
      int var12 = this.nfsr[1] >>> 8;
      int var13 = this.nfsr[1] >>> 16;
      int var14 = this.nfsr[1] >>> 24;
      int var15 = this.nfsr[1] >>> 27;
      int var16 = this.nfsr[1] >>> 29;
      int var17 = this.nfsr[2] >>> 1;
      int var18 = this.nfsr[2] >>> 3;
      int var19 = this.nfsr[2] >>> 4;
      int var20 = this.nfsr[2] >>> 6;
      int var21 = this.nfsr[2] >>> 14;
      int var22 = this.nfsr[2] >>> 18;
      int var23 = this.nfsr[2] >>> 20;
      int var24 = this.nfsr[2] >>> 24;
      int var25 = this.nfsr[2] >>> 27;
      int var26 = this.nfsr[2] >>> 28;
      int var27 = this.nfsr[2] >>> 29;
      int var28 = this.nfsr[2] >>> 31;
      int var29 = this.nfsr[3];
      return (var1 ^ var10 ^ var14 ^ var25 ^ var29 ^ var2 & var18 ^ var3 & var4 ^ var5 & var6 ^ var11 & var15 ^ var12 & var13 ^ var16 & var17 ^ var19 & var23 ^ var7 & var8 & var9 ^ var20 & var21 & var22 ^ var24 & var26 & var27 & var28) & 1;
   }

   private int getOutputLFSR() {
      int var1 = this.lfsr[0];
      int var2 = this.lfsr[0] >>> 7;
      int var3 = this.lfsr[1] >>> 6;
      int var4 = this.lfsr[2] >>> 6;
      int var5 = this.lfsr[2] >>> 17;
      int var6 = this.lfsr[3];
      return (var1 ^ var2 ^ var3 ^ var4 ^ var5 ^ var6) & 1;
   }

   private int getOutput() {
      int var1 = this.nfsr[0] >>> 2;
      int var2 = this.nfsr[0] >>> 12;
      int var3 = this.nfsr[0] >>> 15;
      int var4 = this.nfsr[1] >>> 4;
      int var5 = this.nfsr[1] >>> 13;
      int var6 = this.nfsr[2];
      int var7 = this.nfsr[2] >>> 9;
      int var8 = this.nfsr[2] >>> 25;
      int var9 = this.nfsr[2] >>> 31;
      int var10 = this.lfsr[0] >>> 8;
      int var11 = this.lfsr[0] >>> 13;
      int var12 = this.lfsr[0] >>> 20;
      int var13 = this.lfsr[1] >>> 10;
      int var14 = this.lfsr[1] >>> 28;
      int var15 = this.lfsr[2] >>> 15;
      int var16 = this.lfsr[2] >>> 29;
      int var17 = this.lfsr[2] >>> 30;
      return (var2 & var10 ^ var11 & var12 ^ var9 & var13 ^ var14 & var15 ^ var2 & var9 & var17 ^ var16 ^ var1 ^ var3 ^ var4 ^ var5 ^ var6 ^ var7 ^ var8) & 1;
   }

   private int[] shift(int[] var1, int var2) {
      var1[0] = var1[0] >>> 1 | var1[1] << 31;
      var1[1] = var1[1] >>> 1 | var1[2] << 31;
      var1[2] = var1[2] >>> 1 | var1[3] << 31;
      var1[3] = var1[3] >>> 1 | var2 << 31;
      return var1;
   }

   private void setKey(byte[] var1, byte[] var2) {
      var2[12] = -1;
      var2[13] = -1;
      var2[14] = -1;
      var2[15] = 127;
      this.workingKey = var1;
      this.workingIV = var2;
      Pack.littleEndianToInt(this.workingKey, 0, this.nfsr);
      Pack.littleEndianToInt(this.workingIV, 0, this.lfsr);
   }

   public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException {
      if (!this.initialised) {
         throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
      } else {
         if (!this.aadFinished) {
            this.doProcessAADBytes(this.aadData.getBuf(), this.aadData.size());
            this.aadFinished = true;
         }

         if (var2 + var3 > var1.length) {
            throw new DataLengthException("input buffer too short");
         } else if (var5 + var3 > var4.length) {
            throw new OutputLengthException("output buffer too short");
         } else {
            this.getKeyStream(var1, var2, var3, var4, var5);
            return var3;
         }
      }
   }

   protected void reset(boolean var1) {
      this.aadData.reset();
      this.aadFinished = false;
      this.setKey(this.workingKey, this.workingIV);
      this.initGrain();
      super.reset(var1);
   }

   private void getKeyStream(byte[] var1, int var2, int var3, byte[] var4, int var5) {
      for(int var6 = 0; var6 < var3; ++var6) {
         byte var7 = 0;
         byte var8 = var1[var2 + var6];

         for(int var9 = 0; var9 < 8; ++var9) {
            int var10 = this.getOutput();
            this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0]) & 1);
            this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() & 1);
            int var11 = var8 >> var9 & 1;
            var7 = (byte)(var7 | (var11 ^ var10) << var9);
            this.updateInternalState(var11);
         }

         var4[var5 + var6] = var7;
      }

   }

   private void updateInternalState(int var1) {
      int var2 = -var1;
      int[] var10000 = this.authAcc;
      var10000[0] ^= this.authSr[0] & var2;
      var10000 = this.authAcc;
      var10000[1] ^= this.authSr[1] & var2;
      this.authShift(this.getOutput());
      this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0]) & 1);
      this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() & 1);
   }

   public void processAADByte(byte var1) {
      if (this.aadFinished) {
         throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
      } else {
         this.aadData.write(var1);
      }
   }

   public void processAADBytes(byte[] var1, int var2, int var3) {
      if (this.aadFinished) {
         throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
      } else {
         this.aadData.write(var1, var2, var3);
      }
   }

   private void doProcessAADBytes(byte[] var1, int var2) {
      byte[] var3;
      int var4;
      if (var2 < 128) {
         var3 = new byte[1 + var2];
         var3[0] = (byte)var2;
         var4 = 0;
      } else {
         var4 = len_length(var2);
         var3 = new byte[1 + var4 + var2];
         var3[0] = (byte)(128 | var4);
         int var5 = var2;

         for(int var6 = 0; var6 < var4; ++var6) {
            var3[1 + var6] = (byte)var5;
            var5 >>>= 8;
         }
      }

      for(int var9 = 0; var9 < var2; ++var9) {
         var3[1 + var4 + var9] = var1[var9];
      }

      for(int var10 = 0; var10 < var3.length; ++var10) {
         byte var11 = var3[var10];

         for(int var7 = 0; var7 < 8; ++var7) {
            this.nfsr = this.shift(this.nfsr, (this.getOutputNFSR() ^ this.lfsr[0]) & 1);
            this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() & 1);
            int var8 = var11 >> var7 & 1;
            this.updateInternalState(var8);
         }
      }

   }

   private void accumulate() {
      int[] var10000 = this.authAcc;
      var10000[0] ^= this.authSr[0];
      var10000 = this.authAcc;
      var10000[1] ^= this.authSr[1];
   }

   private void authShift(int var1) {
      this.authSr[0] = this.authSr[0] >>> 1 | this.authSr[1] << 31;
      this.authSr[1] = this.authSr[1] >>> 1 | var1 << 31;
   }

   public int doFinal(byte[] var1, int var2) throws IllegalStateException, InvalidCipherTextException {
      if (!this.aadFinished) {
         this.doProcessAADBytes(this.aadData.getBuf(), this.aadData.size());
         this.aadFinished = true;
      }

      this.accumulate();
      this.mac = Pack.intToLittleEndian(this.authAcc);
      System.arraycopy(this.mac, 0, var1, var2, this.mac.length);
      this.reset(false);
      return this.mac.length;
   }

   public int getUpdateOutputSize(int var1) {
      return var1;
   }

   public int getOutputSize(int var1) {
      return var1 + 8;
   }

   private static int len_length(int var0) {
      if ((var0 & 255) == var0) {
         return 1;
      } else if ((var0 & '\uffff') == var0) {
         return 2;
      } else {
         return (var0 & 16777215) == var0 ? 3 : 4;
      }
   }

   private static final class ErasableOutputStream extends ByteArrayOutputStream {
      public ErasableOutputStream() {
      }

      public byte[] getBuf() {
         return this.buf;
      }
   }
}
