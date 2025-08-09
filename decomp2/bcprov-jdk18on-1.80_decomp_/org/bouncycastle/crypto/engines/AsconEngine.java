package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Pack;

/** @deprecated */
public class AsconEngine extends AsconBaseEngine {
   private final AsconParameters asconParameters;
   private long K2;

   public AsconEngine(AsconParameters var1) {
      this.asconParameters = var1;
      this.IV_SIZE = 16;
      this.MAC_SIZE = 16;
      switch (var1.ordinal()) {
         case 0:
            this.KEY_SIZE = 20;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -6899501409222262784L;
            this.algorithmName = "Ascon-80pq AEAD";
            break;
         case 1:
            this.KEY_SIZE = 16;
            this.ASCON_AEAD_RATE = 16;
            this.ASCON_IV = -9187330011336540160L;
            this.algorithmName = "Ascon-128a AEAD";
            break;
         case 2:
            this.KEY_SIZE = 16;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -9205344418435956736L;
            this.algorithmName = "Ascon-128 AEAD";
            break;
         default:
            throw new IllegalArgumentException("invalid parameter setting for ASCON AEAD");
      }

      this.nr = this.ASCON_AEAD_RATE == 8 ? 6 : 8;
      this.m_bufferSizeDecrypt = this.ASCON_AEAD_RATE + this.MAC_SIZE;
      this.m_buf = new byte[this.m_bufferSizeDecrypt];
      this.dsep = 1L;
   }

   protected long pad(int var1) {
      return 128L << 56 - (var1 << 3);
   }

   protected long loadBytes(byte[] var1, int var2) {
      return Pack.bigEndianToLong(var1, var2);
   }

   protected void setBytes(long var1, byte[] var3, int var4) {
      Pack.longToBigEndian(var1, var3, var4);
   }

   protected void ascon_aeadinit() {
      this.x0 = this.ASCON_IV;
      if (this.KEY_SIZE == 20) {
         this.x0 ^= this.K0;
      }

      this.x1 = this.K1;
      this.x2 = this.K2;
      this.x3 = this.N0;
      this.x4 = this.N1;
      this.p(12);
      if (this.KEY_SIZE == 20) {
         this.x2 ^= this.K0;
      }

      this.x3 ^= this.K1;
      this.x4 ^= this.K2;
   }

   protected void processFinalAadBlock() {
      this.m_buf[this.m_bufPos] = -128;
      if (this.m_bufPos >= 8) {
         this.x0 ^= Pack.bigEndianToLong(this.m_buf, 0);
         this.x1 ^= Pack.bigEndianToLong(this.m_buf, 8) & -1L << 56 - (this.m_bufPos - 8 << 3);
      } else {
         this.x0 ^= Pack.bigEndianToLong(this.m_buf, 0) & -1L << 56 - (this.m_bufPos << 3);
      }

   }

   protected void processFinalDecrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var2 >= 8) {
         long var5 = Pack.bigEndianToLong(var1, 0);
         this.x0 ^= var5;
         Pack.longToBigEndian(this.x0, var3, var4);
         this.x0 = var5;
         var4 += 8;
         var2 -= 8;
         this.x1 ^= this.pad(var2);
         if (var2 != 0) {
            long var7 = Pack.littleEndianToLong_High(var1, 8, var2);
            this.x1 ^= var7;
            Pack.longToLittleEndian_High(this.x1, var3, var4, var2);
            this.x1 &= -1L >>> (var2 << 3);
            this.x1 ^= var7;
         }
      } else {
         this.x0 ^= this.pad(var2);
         if (var2 != 0) {
            long var11 = Pack.littleEndianToLong_High(var1, 0, var2);
            this.x0 ^= var11;
            Pack.longToLittleEndian_High(this.x0, var3, var4, var2);
            this.x0 &= -1L >>> (var2 << 3);
            this.x0 ^= var11;
         }
      }

      this.finishData(AsconBaseEngine.State.DecFinal);
   }

   protected void processFinalEncrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var2 >= 8) {
         this.x0 ^= Pack.bigEndianToLong(var1, 0);
         Pack.longToBigEndian(this.x0, var3, var4);
         var4 += 8;
         var2 -= 8;
         this.x1 ^= this.pad(var2);
         if (var2 != 0) {
            this.x1 ^= Pack.littleEndianToLong_High(var1, 8, var2);
            Pack.longToLittleEndian_High(this.x1, var3, var4, var2);
         }
      } else {
         this.x0 ^= this.pad(var2);
         if (var2 != 0) {
            this.x0 ^= Pack.littleEndianToLong_High(var1, 0, var2);
            Pack.longToLittleEndian_High(this.x0, var3, var4, var2);
         }
      }

      this.finishData(AsconBaseEngine.State.EncFinal);
   }

   private void finishData(AsconBaseEngine.State var1) {
      switch (this.asconParameters.ordinal()) {
         case 0:
            this.x1 ^= this.K0 << 32 | this.K1 >> 32;
            this.x2 ^= this.K1 << 32 | this.K2 >> 32;
            this.x3 ^= this.K2 << 32;
            break;
         case 1:
            this.x2 ^= this.K1;
            this.x3 ^= this.K2;
            break;
         case 2:
            this.x1 ^= this.K1;
            this.x2 ^= this.K2;
            break;
         default:
            throw new IllegalStateException();
      }

      this.p(12);
      this.x3 ^= this.K1;
      this.x4 ^= this.K2;
      this.m_state = var1;
   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.N0 = Pack.bigEndianToLong(var2, 0);
      this.N1 = Pack.bigEndianToLong(var2, 8);
      if (this.KEY_SIZE == 16) {
         this.K1 = Pack.bigEndianToLong(var1, 0);
         this.K2 = Pack.bigEndianToLong(var1, 8);
      } else {
         if (this.KEY_SIZE != 20) {
            throw new IllegalStateException();
         }

         this.K0 = (long)Pack.bigEndianToInt(var1, 0);
         this.K1 = Pack.bigEndianToLong(var1, 4);
         this.K2 = Pack.bigEndianToLong(var1, 12);
      }

      this.m_state = this.forEncryption ? AsconBaseEngine.State.EncInit : AsconBaseEngine.State.DecInit;
      this.reset(true);
   }

   public String getAlgorithmVersion() {
      return "v1.2";
   }

   public static enum AsconParameters {
      ascon80pq,
      ascon128a,
      ascon128;

      // $FF: synthetic method
      private static AsconParameters[] $values() {
         return new AsconParameters[]{ascon80pq, ascon128a, ascon128};
      }
   }
}
