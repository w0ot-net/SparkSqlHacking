package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class AsconAEAD128 extends AsconBaseEngine {
   public AsconAEAD128() {
      this.KEY_SIZE = 16;
      this.IV_SIZE = 16;
      this.MAC_SIZE = 16;
      this.ASCON_AEAD_RATE = 16;
      this.ASCON_IV = 17594342703105L;
      this.algorithmName = "Ascon-AEAD128";
      this.nr = 8;
      this.m_bufferSizeDecrypt = this.ASCON_AEAD_RATE + this.MAC_SIZE;
      this.m_buf = new byte[this.m_bufferSizeDecrypt];
      this.dsep = Long.MIN_VALUE;
   }

   protected long pad(int var1) {
      return 1L << (var1 << 3);
   }

   protected long loadBytes(byte[] var1, int var2) {
      return Pack.littleEndianToLong(var1, var2);
   }

   protected void setBytes(long var1, byte[] var3, int var4) {
      Pack.longToLittleEndian(var1, var3, var4);
   }

   protected void ascon_aeadinit() {
      this.x0 = this.ASCON_IV;
      this.x1 = this.K0;
      this.x2 = this.K1;
      this.x3 = this.N0;
      this.x4 = this.N1;
      this.p(12);
      this.x3 ^= this.K0;
      this.x4 ^= this.K1;
   }

   protected void processFinalAadBlock() {
      Arrays.fill((byte[])this.m_buf, this.m_bufPos, this.m_buf.length, (byte)0);
      if (this.m_bufPos >= 8) {
         this.x0 ^= Pack.littleEndianToLong(this.m_buf, 0);
         this.x1 ^= Pack.littleEndianToLong(this.m_buf, 8) ^ this.pad(this.m_bufPos);
      } else {
         this.x0 ^= Pack.littleEndianToLong(this.m_buf, 0) ^ this.pad(this.m_bufPos);
      }

   }

   protected void processFinalDecrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var2 >= 8) {
         long var5 = Pack.littleEndianToLong(var1, 0);
         var2 -= 8;
         long var7 = Pack.littleEndianToLong(var1, 8, var2);
         Pack.longToLittleEndian(this.x0 ^ var5, var3, var4);
         Pack.longToLittleEndian(this.x1 ^ var7, var3, var4 + 8, var2);
         this.x0 = var5;
         this.x1 &= -(1L << (var2 << 3));
         this.x1 |= var7;
         this.x1 ^= this.pad(var2);
      } else {
         if (var2 != 0) {
            long var10 = Pack.littleEndianToLong(var1, 0, var2);
            Pack.longToLittleEndian(this.x0 ^ var10, var3, var4, var2);
            this.x0 &= -(1L << (var2 << 3));
            this.x0 |= var10;
         }

         this.x0 ^= this.pad(var2);
      }

      this.finishData(AsconBaseEngine.State.DecFinal);
   }

   protected void processFinalEncrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var2 >= 8) {
         this.x0 ^= Pack.littleEndianToLong(var1, 0);
         var2 -= 8;
         this.x1 ^= Pack.littleEndianToLong(var1, 8, var2);
         Pack.longToLittleEndian(this.x0, var3, var4);
         Pack.longToLittleEndian(this.x1, var3, var4 + 8);
         this.x1 ^= this.pad(var2);
      } else {
         if (var2 != 0) {
            this.x0 ^= Pack.littleEndianToLong(var1, 0, var2);
            Pack.longToLittleEndian(this.x0, var3, var4, var2);
         }

         this.x0 ^= this.pad(var2);
      }

      this.finishData(AsconBaseEngine.State.EncFinal);
   }

   private void finishData(AsconBaseEngine.State var1) {
      this.x2 ^= this.K0;
      this.x3 ^= this.K1;
      this.p(12);
      this.x3 ^= this.K0;
      this.x4 ^= this.K1;
      this.m_state = var1;
   }

   protected void init(byte[] var1, byte[] var2) throws IllegalArgumentException {
      this.K0 = Pack.littleEndianToLong(var1, 0);
      this.K1 = Pack.littleEndianToLong(var1, 8);
      this.N0 = Pack.littleEndianToLong(var2, 0);
      this.N1 = Pack.littleEndianToLong(var2, 8);
      this.m_state = this.forEncryption ? AsconBaseEngine.State.EncInit : AsconBaseEngine.State.DecInit;
      this.reset(true);
   }

   public String getAlgorithmVersion() {
      return "v1.3";
   }
}
