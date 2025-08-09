package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class ISAPEngine extends AEADBufferBaseEngine {
   final int ISAP_STATE_SZ = 40;
   private byte[] k;
   private byte[] npub;
   private int ISAP_rH;
   private ISAP_AEAD ISAPAEAD;

   public ISAPEngine(IsapType var1) {
      this.KEY_SIZE = 16;
      this.IV_SIZE = 16;
      this.MAC_SIZE = 16;
      switch (var1.ordinal()) {
         case 0:
            this.ISAPAEAD = new ISAPAEAD_A_128A();
            this.algorithmName = "ISAP-A-128A AEAD";
            break;
         case 1:
            this.ISAPAEAD = new ISAPAEAD_K_128A();
            this.algorithmName = "ISAP-K-128A AEAD";
            break;
         case 2:
            this.ISAPAEAD = new ISAPAEAD_A_128();
            this.algorithmName = "ISAP-A-128 AEAD";
            break;
         case 3:
            this.ISAPAEAD = new ISAPAEAD_K_128();
            this.algorithmName = "ISAP-K-128 AEAD";
      }

      this.AADBufferSize = this.BlockSize;
      this.m_aad = new byte[this.AADBufferSize];
   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.npub = var2;
      this.k = var1;
      this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
      this.ISAPAEAD.init();
      this.initialised = true;
      this.m_state = this.forEncryption ? AEADBufferBaseEngine.State.EncInit : AEADBufferBaseEngine.State.DecInit;
      this.reset();
   }

   protected void processBufferAAD(byte[] var1, int var2) {
      this.ISAPAEAD.absorbMacBlock(var1, var2);
   }

   protected void processFinalAAD() {
      if (!this.aadFinished) {
         this.ISAPAEAD.absorbFinalAADBlock();
         this.ISAPAEAD.swapInternalState();
         this.m_aadPos = 0;
         this.aadFinished = true;
      }

   }

   protected void processBuffer(byte[] var1, int var2, byte[] var3, int var4) {
      this.processFinalAAD();
      this.ISAPAEAD.processEncBlock(var1, var2, var3, var4);
      this.ISAPAEAD.swapInternalState();
      if (this.forEncryption) {
         this.ISAPAEAD.absorbMacBlock(var3, var4);
      } else {
         this.ISAPAEAD.absorbMacBlock(var1, var2);
      }

      this.ISAPAEAD.swapInternalState();
   }

   protected void processFinalBlock(byte[] var1, int var2) {
      this.processFinalAAD();
      int var3 = this.m_bufPos;
      this.mac = new byte[this.MAC_SIZE];
      this.ISAPAEAD.processEncFinalBlock(var1, var2);
      this.ISAPAEAD.swapInternalState();
      if (this.forEncryption) {
         this.ISAPAEAD.processMACFinal(var1, var2, var3, this.mac);
      } else {
         this.ISAPAEAD.processMACFinal(this.m_buf, 0, var3, this.mac);
      }

   }

   protected void reset(boolean var1) {
      if (!this.initialised) {
         throw new IllegalStateException("Need call init function before encryption/decryption");
      } else {
         Arrays.fill((byte[])this.m_buf, (byte)0);
         Arrays.fill((byte[])this.m_aad, (byte)0);
         this.ISAPAEAD.reset();
         this.m_bufPos = 0;
         this.m_aadPos = 0;
         this.aadFinished = false;
         super.reset(var1);
      }
   }

   private abstract class ISAPAEAD_A implements ISAP_AEAD {
      protected long[] k64;
      protected long[] npub64;
      protected long ISAP_IV1_64;
      protected long ISAP_IV2_64;
      protected long ISAP_IV3_64;
      protected long x0;
      protected long x1;
      protected long x2;
      protected long x3;
      protected long x4;
      protected long t0;
      protected long t1;
      protected long t2;
      protected long t3;
      protected long t4;
      protected long macx0;
      protected long macx1;
      protected long macx2;
      protected long macx3;
      protected long macx4;

      public ISAPAEAD_A() {
         ISAPEngine.this.ISAP_rH = 64;
         ISAPEngine.this.BlockSize = ISAPEngine.this.ISAP_rH + 7 >> 3;
      }

      public void init() {
         this.npub64 = new long[this.getLongSize(ISAPEngine.this.npub.length)];
         this.k64 = new long[this.getLongSize(ISAPEngine.this.k.length)];
         Pack.bigEndianToLong(ISAPEngine.this.npub, 0, this.npub64);
         Pack.bigEndianToLong(ISAPEngine.this.k, 0, this.k64);
      }

      protected abstract void PX1();

      protected abstract void PX2();

      public void swapInternalState() {
         this.t0 = this.x0;
         this.t1 = this.x1;
         this.t2 = this.x2;
         this.t3 = this.x3;
         this.t4 = this.x4;
         this.x0 = this.macx0;
         this.x1 = this.macx1;
         this.x2 = this.macx2;
         this.x3 = this.macx3;
         this.x4 = this.macx4;
         this.macx0 = this.t0;
         this.macx1 = this.t1;
         this.macx2 = this.t2;
         this.macx3 = this.t3;
         this.macx4 = this.t4;
      }

      public void absorbMacBlock(byte[] var1, int var2) {
         this.x0 ^= Pack.bigEndianToLong(var1, var2);
         this.P12();
      }

      public void absorbFinalAADBlock() {
         if (ISAPEngine.this.m_aadPos == ISAPEngine.this.AADBufferSize) {
            this.absorbMacBlock(ISAPEngine.this.m_aad, 0);
            ISAPEngine.this.m_aadPos = 0;
         } else {
            for(int var1 = 0; var1 < ISAPEngine.this.m_aadPos; ++var1) {
               this.x0 ^= ((long)ISAPEngine.this.m_aad[var1] & 255L) << (7 - var1 << 3);
            }
         }

         this.x0 ^= 128L << (7 - ISAPEngine.this.m_aadPos << 3);
         this.P12();
         this.x4 ^= 1L;
      }

      public void processMACFinal(byte[] var1, int var2, int var3, byte[] var4) {
         if (var3 == ISAPEngine.this.BlockSize) {
            this.absorbMacBlock(var1, var2);
            var3 = 0;
         } else {
            for(int var5 = 0; var5 < var3; ++var5) {
               this.x0 ^= ((long)var1[var2++] & 255L) << (7 - var5 << 3);
            }
         }

         this.x0 ^= 128L << (7 - var3 << 3);
         this.P12();
         Pack.longToBigEndian(this.x0, var4, 0);
         Pack.longToBigEndian(this.x1, var4, 8);
         long var11 = this.x2;
         long var7 = this.x3;
         long var9 = this.x4;
         this.isap_rk(this.ISAP_IV2_64, var4, ISAPEngine.this.KEY_SIZE);
         this.x2 = var11;
         this.x3 = var7;
         this.x4 = var9;
         this.P12();
         Pack.longToBigEndian(this.x0, var4, 0);
         Pack.longToBigEndian(this.x1, var4, 8);
      }

      public void isap_rk(long var1, byte[] var3, int var4) {
         this.x0 = this.k64[0];
         this.x1 = this.k64[1];
         this.x2 = var1;
         this.x3 = this.x4 = 0L;
         this.P12();

         for(int var5 = 0; var5 < (var4 << 3) - 1; ++var5) {
            this.x0 ^= ((long)((var3[var5 >>> 3] >>> 7 - (var5 & 7) & 1) << 7) & 255L) << 56;
            this.PX2();
         }

         this.x0 ^= ((long)var3[var4 - 1] & 1L) << 7 << 56;
         this.P12();
      }

      public void processEncBlock(byte[] var1, int var2, byte[] var3, int var4) {
         long var5 = Pack.littleEndianToLong(var1, var2);
         long var7 = this.U64BIG(this.x0) ^ var5;
         this.PX1();
         Pack.longToLittleEndian(var7, var3, var4);
      }

      public void processEncFinalBlock(byte[] var1, int var2) {
         if (ISAPEngine.this.m_bufPos == ISAPEngine.this.BlockSize) {
            this.processEncBlock(ISAPEngine.this.m_buf, 0, var1, var2);
         } else {
            byte[] var3 = Pack.longToLittleEndian(this.x0);

            int var10001;
            byte var10002;
            for(int var4 = ISAPEngine.this.m_bufPos; var4 > 0; var1[var10001] = (byte)(var10002 ^ ISAPEngine.this.m_buf[var4])) {
               var10001 = var2 + var4 - 1;
               var10002 = var3[ISAPEngine.this.BlockSize - var4];
               --var4;
            }
         }

      }

      public void reset() {
         this.isap_rk(this.ISAP_IV3_64, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE);
         this.x3 = this.npub64[0];
         this.x4 = this.npub64[1];
         this.PX1();
         this.swapInternalState();
         this.x0 = this.npub64[0];
         this.x1 = this.npub64[1];
         this.x2 = this.ISAP_IV1_64;
         this.x3 = this.x4 = 0L;
         this.P12();
      }

      private int getLongSize(int var1) {
         return (var1 >>> 3) + ((var1 & 7) != 0 ? 1 : 0);
      }

      private long ROTR(long var1, long var3) {
         return var1 >>> (int)var3 | var1 << (int)(64L - var3);
      }

      protected long U64BIG(long var1) {
         return this.ROTR(var1, 8L) & -72057589759737856L | this.ROTR(var1, 24L) & 71776119077928960L | this.ROTR(var1, 40L) & 280375465148160L | this.ROTR(var1, 56L) & 1095216660735L;
      }

      protected void ROUND(long var1) {
         this.t0 = this.x0 ^ this.x1 ^ this.x2 ^ this.x3 ^ var1 ^ this.x1 & (this.x0 ^ this.x2 ^ this.x4 ^ var1);
         this.t1 = this.x0 ^ this.x2 ^ this.x3 ^ this.x4 ^ var1 ^ (this.x1 ^ this.x2 ^ var1) & (this.x1 ^ this.x3);
         this.t2 = this.x1 ^ this.x2 ^ this.x4 ^ var1 ^ this.x3 & this.x4;
         this.t3 = this.x0 ^ this.x1 ^ this.x2 ^ var1 ^ ~this.x0 & (this.x3 ^ this.x4);
         this.t4 = this.x1 ^ this.x3 ^ this.x4 ^ (this.x0 ^ this.x4) & this.x1;
         this.x0 = this.t0 ^ this.ROTR(this.t0, 19L) ^ this.ROTR(this.t0, 28L);
         this.x1 = this.t1 ^ this.ROTR(this.t1, 39L) ^ this.ROTR(this.t1, 61L);
         this.x2 = ~(this.t2 ^ this.ROTR(this.t2, 1L) ^ this.ROTR(this.t2, 6L));
         this.x3 = this.t3 ^ this.ROTR(this.t3, 10L) ^ this.ROTR(this.t3, 17L);
         this.x4 = this.t4 ^ this.ROTR(this.t4, 7L) ^ this.ROTR(this.t4, 41L);
      }

      public void P12() {
         this.ROUND(240L);
         this.ROUND(225L);
         this.ROUND(210L);
         this.ROUND(195L);
         this.ROUND(180L);
         this.ROUND(165L);
         this.P6();
      }

      protected void P6() {
         this.ROUND(150L);
         this.ROUND(135L);
         this.ROUND(120L);
         this.ROUND(105L);
         this.ROUND(90L);
         this.ROUND(75L);
      }
   }

   private class ISAPAEAD_A_128 extends ISAPAEAD_A {
      public ISAPAEAD_A_128() {
         this.ISAP_IV1_64 = 108156764298152972L;
         this.ISAP_IV2_64 = 180214358336080908L;
         this.ISAP_IV3_64 = 252271952374008844L;
      }

      protected void PX1() {
         this.P12();
      }

      protected void PX2() {
         this.P12();
      }
   }

   private class ISAPAEAD_A_128A extends ISAPAEAD_A {
      public ISAPAEAD_A_128A() {
         this.ISAP_IV1_64 = 108156764297430540L;
         this.ISAP_IV2_64 = 180214358335358476L;
         this.ISAP_IV3_64 = 252271952373286412L;
      }

      protected void PX1() {
         this.P6();
      }

      protected void PX2() {
         this.ROUND(75L);
      }
   }

   private abstract class ISAPAEAD_K implements ISAP_AEAD {
      final int ISAP_STATE_SZ_CRYPTO_NPUBBYTES;
      protected short[] ISAP_IV1_16;
      protected short[] ISAP_IV2_16;
      protected short[] ISAP_IV3_16;
      protected short[] k16;
      protected short[] iv16;
      private final int[] KeccakF400RoundConstants;
      protected short[] SX;
      protected short[] macSX;
      protected short[] E;
      protected short[] C;
      protected short[] macE;
      protected short[] macC;

      public ISAPAEAD_K() {
         this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES = 40 - ISAPEngine.this.IV_SIZE;
         this.KeccakF400RoundConstants = new int[]{1, 32898, 32906, 32768, 32907, 1, 32897, 32777, 138, 136, 32777, 10, 32907, 139, 32905, 32771, 32770, 128, 32778, 10};
         this.SX = new short[25];
         this.macSX = new short[25];
         this.E = new short[25];
         this.C = new short[5];
         this.macE = new short[25];
         this.macC = new short[5];
         ISAPEngine.this.ISAP_rH = 144;
         ISAPEngine.this.BlockSize = ISAPEngine.this.ISAP_rH + 7 >> 3;
      }

      public void init() {
         this.k16 = new short[ISAPEngine.this.k.length >> 1];
         this.byteToShort(ISAPEngine.this.k, this.k16, this.k16.length);
         this.iv16 = new short[ISAPEngine.this.npub.length >> 1];
         this.byteToShort(ISAPEngine.this.npub, this.iv16, this.iv16.length);
      }

      public void reset() {
         Arrays.fill((short[])this.SX, (short)0);
         this.isap_rk(this.ISAP_IV3_16, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE, this.SX, this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES, this.C);
         System.arraycopy(this.iv16, 0, this.SX, 17, 8);
         this.PermuteRoundsKX(this.SX, this.E, this.C);
         this.swapInternalState();
         Arrays.fill((short[])this.SX, 12, 25, (short)0);
         System.arraycopy(this.iv16, 0, this.SX, 0, 8);
         System.arraycopy(this.ISAP_IV1_16, 0, this.SX, 8, 4);
         this.PermuteRoundsHX(this.SX, this.E, this.C);
      }

      public void swapInternalState() {
         short[] var1 = this.SX;
         this.SX = this.macSX;
         this.macSX = var1;
         var1 = this.E;
         this.E = this.macE;
         this.macE = var1;
         var1 = this.C;
         this.C = this.macC;
         this.macC = var1;
      }

      protected abstract void PermuteRoundsHX(short[] var1, short[] var2, short[] var3);

      protected abstract void PermuteRoundsKX(short[] var1, short[] var2, short[] var3);

      protected abstract void PermuteRoundsBX(short[] var1, short[] var2, short[] var3);

      public void absorbMacBlock(byte[] var1, int var2) {
         this.byteToShortXor(var1, var2, this.SX, ISAPEngine.this.BlockSize >> 1);
         this.PermuteRoundsHX(this.SX, this.E, this.C);
      }

      public void absorbFinalAADBlock() {
         if (ISAPEngine.this.m_aadPos == ISAPEngine.this.AADBufferSize) {
            this.absorbMacBlock(ISAPEngine.this.m_aad, 0);
            ISAPEngine.this.m_aadPos = 0;
         } else {
            for(int var1 = 0; var1 < ISAPEngine.this.m_aadPos; ++var1) {
               short[] var10000 = this.SX;
               var10000[var1 >> 1] = (short)(var10000[var1 >> 1] ^ (ISAPEngine.this.m_aad[var1] & 255) << ((var1 & 1) << 3));
            }
         }

         short[] var2 = this.SX;
         int var10001 = ISAPEngine.this.m_aadPos >> 1;
         var2[var10001] = (short)(var2[var10001] ^ 128 << ((ISAPEngine.this.m_aadPos & 1) << 3));
         this.PermuteRoundsHX(this.SX, this.E, this.C);
         var2 = this.SX;
         var2[24] = (short)(var2[24] ^ 256);
      }

      public void isap_rk(short[] var1, byte[] var2, int var3, short[] var4, int var5, short[] var6) {
         short[] var7 = new short[25];
         short[] var8 = new short[25];
         System.arraycopy(this.k16, 0, var7, 0, 8);
         System.arraycopy(var1, 0, var7, 8, 4);
         this.PermuteRoundsKX(var7, var8, var6);

         for(int var9 = 0; var9 < (var3 << 3) - 1; ++var9) {
            var7[0] = (short)(var7[0] ^ (var2[var9 >> 3] >>> 7 - (var9 & 7) & 1) << 7);
            this.PermuteRoundsBX(var7, var8, var6);
         }

         var7[0] = (short)(var7[0] ^ (var2[var3 - 1] & 1) << 7);
         this.PermuteRoundsKX(var7, var8, var6);
         System.arraycopy(var7, 0, var4, 0, var5 == this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES ? 17 : 8);
      }

      public void processMACFinal(byte[] var1, int var2, int var3, byte[] var4) {
         if (var3 == ISAPEngine.this.BlockSize) {
            this.absorbMacBlock(var1, var2);
            var3 = 0;
         } else {
            for(int var5 = 0; var5 < var3; ++var5) {
               short[] var10000 = this.SX;
               var10000[var5 >> 1] = (short)(var10000[var5 >> 1] ^ (var1[var2++] & 255) << ((var5 & 1) << 3));
            }
         }

         short[] var6 = this.SX;
         var6[var3 >> 1] = (short)(var6[var3 >> 1] ^ 128 << ((var3 & 1) << 3));
         this.PermuteRoundsHX(this.SX, this.E, this.C);
         this.shortToByte(this.SX, var4);
         this.isap_rk(this.ISAP_IV2_16, var4, ISAPEngine.this.KEY_SIZE, this.SX, ISAPEngine.this.KEY_SIZE, this.C);
         this.PermuteRoundsHX(this.SX, this.E, this.C);
         this.shortToByte(this.SX, var4);
      }

      public void processEncBlock(byte[] var1, int var2, byte[] var3, int var4) {
         for(int var5 = 0; var5 < ISAPEngine.this.BlockSize; ++var5) {
            var3[var4++] = (byte)(this.SX[var5 >> 1] >>> ((var5 & 1) << 3) ^ var1[var2++]);
         }

         this.PermuteRoundsKX(this.SX, this.E, this.C);
      }

      public void processEncFinalBlock(byte[] var1, int var2) {
         int var3 = ISAPEngine.this.m_bufPos;

         for(int var4 = 0; var4 < var3; ++var4) {
            var1[var2++] = (byte)(this.SX[var4 >> 1] >>> ((var4 & 1) << 3) ^ ISAPEngine.this.m_buf[var4]);
         }

      }

      private void byteToShortXor(byte[] var1, int var2, short[] var3, int var4) {
         for(int var5 = 0; var5 < var4; ++var5) {
            var3[var5] ^= Pack.littleEndianToShort(var1, var2 + (var5 << 1));
         }

      }

      private void byteToShort(byte[] var1, short[] var2, int var3) {
         for(int var4 = 0; var4 < var3; ++var4) {
            var2[var4] = Pack.littleEndianToShort(var1, var4 << 1);
         }

      }

      private void shortToByte(short[] var1, byte[] var2) {
         for(int var3 = 0; var3 < 8; ++var3) {
            Pack.shortToLittleEndian(var1[var3], var2, var3 << 1);
         }

      }

      protected void rounds12X(short[] var1, short[] var2, short[] var3) {
         this.prepareThetaX(var1, var3);
         this.rounds_8_18(var1, var2, var3);
      }

      protected void rounds_4_18(short[] var1, short[] var2, short[] var3) {
         this.thetaRhoPiChiIotaPrepareTheta(4, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(5, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(6, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(7, var2, var1, var3);
         this.rounds_8_18(var1, var2, var3);
      }

      protected void rounds_8_18(short[] var1, short[] var2, short[] var3) {
         this.thetaRhoPiChiIotaPrepareTheta(8, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(9, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(10, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(11, var2, var1, var3);
         this.rounds_12_18(var1, var2, var3);
      }

      protected void rounds_12_18(short[] var1, short[] var2, short[] var3) {
         this.thetaRhoPiChiIotaPrepareTheta(12, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(13, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(14, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(15, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(16, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(17, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(18, var1, var2, var3);
         this.thetaRhoPiChiIota(var2, var1, var3);
      }

      protected void prepareThetaX(short[] var1, short[] var2) {
         var2[0] = (short)(var1[0] ^ var1[5] ^ var1[10] ^ var1[15] ^ var1[20]);
         var2[1] = (short)(var1[1] ^ var1[6] ^ var1[11] ^ var1[16] ^ var1[21]);
         var2[2] = (short)(var1[2] ^ var1[7] ^ var1[12] ^ var1[17] ^ var1[22]);
         var2[3] = (short)(var1[3] ^ var1[8] ^ var1[13] ^ var1[18] ^ var1[23]);
         var2[4] = (short)(var1[4] ^ var1[9] ^ var1[14] ^ var1[19] ^ var1[24]);
      }

      private short ROL16(short var1, int var2) {
         return (short)((var1 & '\uffff') << var2 ^ (var1 & '\uffff') >>> 16 - var2);
      }

      protected void thetaRhoPiChiIotaPrepareTheta(int var1, short[] var2, short[] var3, short[] var4) {
         short var5 = (short)(var4[4] ^ this.ROL16(var4[1], 1));
         short var6 = (short)(var4[0] ^ this.ROL16(var4[2], 1));
         short var7 = (short)(var4[1] ^ this.ROL16(var4[3], 1));
         short var8 = (short)(var4[2] ^ this.ROL16(var4[4], 1));
         short var9 = (short)(var4[3] ^ this.ROL16(var4[0], 1));
         short var10 = var2[0] ^= var5;
         var2[6] ^= var6;
         short var11 = this.ROL16(var2[6], 12);
         var2[12] ^= var7;
         short var12 = this.ROL16(var2[12], 11);
         var2[18] ^= var8;
         short var13 = this.ROL16(var2[18], 5);
         var2[24] ^= var9;
         short var14 = this.ROL16(var2[24], 14);
         var4[0] = var3[0] = (short)(var10 ^ ~var11 & var12 ^ this.KeccakF400RoundConstants[var1]);
         var4[1] = var3[1] = (short)(var11 ^ ~var12 & var13);
         var4[2] = var3[2] = (short)(var12 ^ ~var13 & var14);
         var4[3] = var3[3] = (short)(var13 ^ ~var14 & var10);
         var4[4] = var3[4] = (short)(var14 ^ ~var10 & var11);
         var2[3] ^= var8;
         var10 = this.ROL16(var2[3], 12);
         var2[9] ^= var9;
         var11 = this.ROL16(var2[9], 4);
         var2[10] ^= var5;
         var12 = this.ROL16(var2[10], 3);
         var2[16] ^= var6;
         var13 = this.ROL16(var2[16], 13);
         var2[22] ^= var7;
         var14 = this.ROL16(var2[22], 13);
         var3[5] = (short)(var10 ^ ~var11 & var12);
         var4[0] ^= var3[5];
         var3[6] = (short)(var11 ^ ~var12 & var13);
         var4[1] ^= var3[6];
         var3[7] = (short)(var12 ^ ~var13 & var14);
         var4[2] ^= var3[7];
         var3[8] = (short)(var13 ^ ~var14 & var10);
         var4[3] ^= var3[8];
         var3[9] = (short)(var14 ^ ~var10 & var11);
         var4[4] ^= var3[9];
         var2[1] ^= var6;
         var10 = this.ROL16(var2[1], 1);
         var2[7] ^= var7;
         var11 = this.ROL16(var2[7], 6);
         var2[13] ^= var8;
         var12 = this.ROL16(var2[13], 9);
         var2[19] ^= var9;
         var13 = this.ROL16(var2[19], 8);
         var2[20] ^= var5;
         var14 = this.ROL16(var2[20], 2);
         var3[10] = (short)(var10 ^ ~var11 & var12);
         var4[0] ^= var3[10];
         var3[11] = (short)(var11 ^ ~var12 & var13);
         var4[1] ^= var3[11];
         var3[12] = (short)(var12 ^ ~var13 & var14);
         var4[2] ^= var3[12];
         var3[13] = (short)(var13 ^ ~var14 & var10);
         var4[3] ^= var3[13];
         var3[14] = (short)(var14 ^ ~var10 & var11);
         var4[4] ^= var3[14];
         var2[4] ^= var9;
         var10 = this.ROL16(var2[4], 11);
         var2[5] ^= var5;
         var11 = this.ROL16(var2[5], 4);
         var2[11] ^= var6;
         var12 = this.ROL16(var2[11], 10);
         var2[17] ^= var7;
         var13 = this.ROL16(var2[17], 15);
         var2[23] ^= var8;
         var14 = this.ROL16(var2[23], 8);
         var3[15] = (short)(var10 ^ ~var11 & var12);
         var4[0] ^= var3[15];
         var3[16] = (short)(var11 ^ ~var12 & var13);
         var4[1] ^= var3[16];
         var3[17] = (short)(var12 ^ ~var13 & var14);
         var4[2] ^= var3[17];
         var3[18] = (short)(var13 ^ ~var14 & var10);
         var4[3] ^= var3[18];
         var3[19] = (short)(var14 ^ ~var10 & var11);
         var4[4] ^= var3[19];
         var2[2] ^= var7;
         var10 = this.ROL16(var2[2], 14);
         var2[8] ^= var8;
         var11 = this.ROL16(var2[8], 7);
         var2[14] ^= var9;
         var12 = this.ROL16(var2[14], 7);
         var2[15] ^= var5;
         var13 = this.ROL16(var2[15], 9);
         var2[21] ^= var6;
         var14 = this.ROL16(var2[21], 2);
         var3[20] = (short)(var10 ^ ~var11 & var12);
         var4[0] ^= var3[20];
         var3[21] = (short)(var11 ^ ~var12 & var13);
         var4[1] ^= var3[21];
         var3[22] = (short)(var12 ^ ~var13 & var14);
         var4[2] ^= var3[22];
         var3[23] = (short)(var13 ^ ~var14 & var10);
         var4[3] ^= var3[23];
         var3[24] = (short)(var14 ^ ~var10 & var11);
         var4[4] ^= var3[24];
      }

      protected void thetaRhoPiChiIota(short[] var1, short[] var2, short[] var3) {
         short var4 = (short)(var3[4] ^ this.ROL16(var3[1], 1));
         short var5 = (short)(var3[0] ^ this.ROL16(var3[2], 1));
         short var6 = (short)(var3[1] ^ this.ROL16(var3[3], 1));
         short var7 = (short)(var3[2] ^ this.ROL16(var3[4], 1));
         short var8 = (short)(var3[3] ^ this.ROL16(var3[0], 1));
         short var9 = var1[0] ^= var4;
         var1[6] ^= var5;
         short var10 = this.ROL16(var1[6], 12);
         var1[12] ^= var6;
         short var11 = this.ROL16(var1[12], 11);
         var1[18] ^= var7;
         short var12 = this.ROL16(var1[18], 5);
         var1[24] ^= var8;
         short var13 = this.ROL16(var1[24], 14);
         var2[0] = (short)(var9 ^ ~var10 & var11 ^ this.KeccakF400RoundConstants[19]);
         var2[1] = (short)(var10 ^ ~var11 & var12);
         var2[2] = (short)(var11 ^ ~var12 & var13);
         var2[3] = (short)(var12 ^ ~var13 & var9);
         var2[4] = (short)(var13 ^ ~var9 & var10);
         var1[3] ^= var7;
         var9 = this.ROL16(var1[3], 12);
         var1[9] ^= var8;
         var10 = this.ROL16(var1[9], 4);
         var1[10] ^= var4;
         var11 = this.ROL16(var1[10], 3);
         var1[16] ^= var5;
         var12 = this.ROL16(var1[16], 13);
         var1[22] ^= var6;
         var13 = this.ROL16(var1[22], 13);
         var2[5] = (short)(var9 ^ ~var10 & var11);
         var2[6] = (short)(var10 ^ ~var11 & var12);
         var2[7] = (short)(var11 ^ ~var12 & var13);
         var2[8] = (short)(var12 ^ ~var13 & var9);
         var2[9] = (short)(var13 ^ ~var9 & var10);
         var1[1] ^= var5;
         var9 = this.ROL16(var1[1], 1);
         var1[7] ^= var6;
         var10 = this.ROL16(var1[7], 6);
         var1[13] ^= var7;
         var11 = this.ROL16(var1[13], 9);
         var1[19] ^= var8;
         var12 = this.ROL16(var1[19], 8);
         var1[20] ^= var4;
         var13 = this.ROL16(var1[20], 2);
         var2[10] = (short)(var9 ^ ~var10 & var11);
         var2[11] = (short)(var10 ^ ~var11 & var12);
         var2[12] = (short)(var11 ^ ~var12 & var13);
         var2[13] = (short)(var12 ^ ~var13 & var9);
         var2[14] = (short)(var13 ^ ~var9 & var10);
         var1[4] ^= var8;
         var9 = this.ROL16(var1[4], 11);
         var1[5] ^= var4;
         var10 = this.ROL16(var1[5], 4);
         var1[11] ^= var5;
         var11 = this.ROL16(var1[11], 10);
         var1[17] ^= var6;
         var12 = this.ROL16(var1[17], 15);
         var1[23] ^= var7;
         var13 = this.ROL16(var1[23], 8);
         var2[15] = (short)(var9 ^ ~var10 & var11);
         var2[16] = (short)(var10 ^ ~var11 & var12);
         var2[17] = (short)(var11 ^ ~var12 & var13);
         var2[18] = (short)(var12 ^ ~var13 & var9);
         var2[19] = (short)(var13 ^ ~var9 & var10);
         var1[2] ^= var6;
         var9 = this.ROL16(var1[2], 14);
         var1[8] ^= var7;
         var10 = this.ROL16(var1[8], 7);
         var1[14] ^= var8;
         var11 = this.ROL16(var1[14], 7);
         var1[15] ^= var4;
         var12 = this.ROL16(var1[15], 9);
         var1[21] ^= var5;
         var13 = this.ROL16(var1[21], 2);
         var2[20] = (short)(var9 ^ ~var10 & var11);
         var2[21] = (short)(var10 ^ ~var11 & var12);
         var2[22] = (short)(var11 ^ ~var12 & var13);
         var2[23] = (short)(var12 ^ ~var13 & var9);
         var2[24] = (short)(var13 ^ ~var9 & var10);
      }
   }

   private class ISAPAEAD_K_128 extends ISAPAEAD_K {
      public ISAPAEAD_K_128() {
         this.ISAP_IV1_16 = new short[]{-32767, 400, 3092, 3084};
         this.ISAP_IV2_16 = new short[]{-32766, 400, 3092, 3084};
         this.ISAP_IV3_16 = new short[]{-32765, 400, 3092, 3084};
      }

      protected void PermuteRoundsHX(short[] var1, short[] var2, short[] var3) {
         this.prepareThetaX(var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(0, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(1, var2, var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(2, var1, var2, var3);
         this.thetaRhoPiChiIotaPrepareTheta(3, var2, var1, var3);
         this.rounds_4_18(var1, var2, var3);
      }

      protected void PermuteRoundsKX(short[] var1, short[] var2, short[] var3) {
         this.rounds12X(var1, var2, var3);
      }

      protected void PermuteRoundsBX(short[] var1, short[] var2, short[] var3) {
         this.rounds12X(var1, var2, var3);
      }
   }

   private class ISAPAEAD_K_128A extends ISAPAEAD_K {
      public ISAPAEAD_K_128A() {
         this.ISAP_IV1_16 = new short[]{-32767, 400, 272, 2056};
         this.ISAP_IV2_16 = new short[]{-32766, 400, 272, 2056};
         this.ISAP_IV3_16 = new short[]{-32765, 400, 272, 2056};
      }

      protected void PermuteRoundsHX(short[] var1, short[] var2, short[] var3) {
         this.prepareThetaX(var1, var3);
         this.rounds_4_18(var1, var2, var3);
      }

      protected void PermuteRoundsKX(short[] var1, short[] var2, short[] var3) {
         this.prepareThetaX(var1, var3);
         this.rounds_12_18(var1, var2, var3);
      }

      protected void PermuteRoundsBX(short[] var1, short[] var2, short[] var3) {
         this.prepareThetaX(var1, var3);
         this.thetaRhoPiChiIotaPrepareTheta(19, var1, var2, var3);
         System.arraycopy(var2, 0, var1, 0, var2.length);
      }
   }

   private interface ISAP_AEAD {
      void init();

      void reset();

      void absorbMacBlock(byte[] var1, int var2);

      void absorbFinalAADBlock();

      void swapInternalState();

      void processEncBlock(byte[] var1, int var2, byte[] var3, int var4);

      void processEncFinalBlock(byte[] var1, int var2);

      void processMACFinal(byte[] var1, int var2, int var3, byte[] var4);
   }

   public static enum IsapType {
      ISAP_A_128A,
      ISAP_K_128A,
      ISAP_A_128,
      ISAP_K_128;

      // $FF: synthetic method
      private static IsapType[] $values() {
         return new IsapType[]{ISAP_A_128A, ISAP_K_128A, ISAP_A_128, ISAP_K_128};
      }
   }
}
