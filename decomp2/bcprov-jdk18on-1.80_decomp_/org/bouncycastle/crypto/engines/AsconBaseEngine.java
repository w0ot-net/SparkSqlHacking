package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;

abstract class AsconBaseEngine extends AEADBaseEngine {
   protected State m_state;
   protected int nr;
   protected int ASCON_AEAD_RATE;
   protected long K0;
   protected long K1;
   protected long N0;
   protected long N1;
   protected long ASCON_IV;
   protected long x0;
   protected long x1;
   protected long x2;
   protected long x3;
   protected long x4;
   protected int m_bufferSizeDecrypt;
   protected byte[] m_buf;
   protected int m_bufPos;
   protected long dsep;

   AsconBaseEngine() {
      this.m_state = AsconBaseEngine.State.Uninitialized;
      this.m_bufPos = 0;
   }

   protected abstract long pad(int var1);

   protected abstract long loadBytes(byte[] var1, int var2);

   protected abstract void setBytes(long var1, byte[] var3, int var4);

   private void round(long var1) {
      long var3 = this.x0 ^ this.x1 ^ this.x2 ^ this.x3 ^ var1 ^ this.x1 & (this.x0 ^ this.x2 ^ this.x4 ^ var1);
      long var5 = this.x0 ^ this.x2 ^ this.x3 ^ this.x4 ^ var1 ^ (this.x1 ^ this.x2 ^ var1) & (this.x1 ^ this.x3);
      long var7 = this.x1 ^ this.x2 ^ this.x4 ^ var1 ^ this.x3 & this.x4;
      long var9 = this.x0 ^ this.x1 ^ this.x2 ^ var1 ^ ~this.x0 & (this.x3 ^ this.x4);
      long var11 = this.x1 ^ this.x3 ^ this.x4 ^ (this.x0 ^ this.x4) & this.x1;
      this.x0 = var3 ^ Longs.rotateRight(var3, 19) ^ Longs.rotateRight(var3, 28);
      this.x1 = var5 ^ Longs.rotateRight(var5, 39) ^ Longs.rotateRight(var5, 61);
      this.x2 = ~(var7 ^ Longs.rotateRight(var7, 1) ^ Longs.rotateRight(var7, 6));
      this.x3 = var9 ^ Longs.rotateRight(var9, 10) ^ Longs.rotateRight(var9, 17);
      this.x4 = var11 ^ Longs.rotateRight(var11, 7) ^ Longs.rotateRight(var11, 41);
   }

   protected void p(int var1) {
      if (var1 == 12) {
         this.round(240L);
         this.round(225L);
         this.round(210L);
         this.round(195L);
      }

      if (var1 >= 8) {
         this.round(180L);
         this.round(165L);
      }

      this.round(150L);
      this.round(135L);
      this.round(120L);
      this.round(105L);
      this.round(90L);
      this.round(75L);
   }

   protected abstract void ascon_aeadinit();

   protected void checkAAD() {
      switch (this.m_state.ordinal()) {
         case 1:
            this.m_state = AsconBaseEngine.State.EncAad;
         case 2:
         case 6:
            break;
         case 3:
         default:
            throw new IllegalStateException(this.getAlgorithmName() + " needs to be initialized");
         case 4:
            throw new IllegalStateException(this.getAlgorithmName() + " cannot be reused for encryption");
         case 5:
            this.m_state = AsconBaseEngine.State.DecAad;
      }

   }

   protected boolean checkData() {
      switch (this.m_state.ordinal()) {
         case 1:
         case 2:
            this.finishAAD(AsconBaseEngine.State.EncData);
            return true;
         case 3:
            return true;
         case 4:
            throw new IllegalStateException(this.getAlgorithmName() + " cannot be reused for encryption");
         case 5:
         case 6:
            this.finishAAD(AsconBaseEngine.State.DecData);
            return false;
         case 7:
            return false;
         default:
            throw new IllegalStateException(this.getAlgorithmName() + " needs to be initialized");
      }
   }

   private void finishAAD(State var1) {
      switch (this.m_state.ordinal()) {
         case 2:
         case 6:
            this.processFinalAadBlock();
            this.p(this.nr);
         default:
            this.x4 ^= this.dsep;
            this.m_bufPos = 0;
            this.m_state = var1;
      }
   }

   protected abstract void processFinalAadBlock();

   protected abstract void processFinalDecrypt(byte[] var1, int var2, byte[] var3, int var4);

   protected abstract void processFinalEncrypt(byte[] var1, int var2, byte[] var3, int var4);

   protected void processBufferAAD(byte[] var1, int var2) {
      this.x0 ^= this.loadBytes(var1, var2);
      if (this.ASCON_AEAD_RATE == 16) {
         this.x1 ^= this.loadBytes(var1, 8 + var2);
      }

      this.p(this.nr);
   }

   protected void processBufferDecrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var4 + this.ASCON_AEAD_RATE > var3.length) {
         throw new OutputLengthException("output buffer too short");
      } else {
         long var5 = this.loadBytes(var1, var2);
         this.setBytes(this.x0 ^ var5, var3, var4);
         this.x0 = var5;
         if (this.ASCON_AEAD_RATE == 16) {
            long var7 = this.loadBytes(var1, var2 + 8);
            this.setBytes(this.x1 ^ var7, var3, var4 + 8);
            this.x1 = var7;
         }

         this.p(this.nr);
      }
   }

   protected void processBufferEncrypt(byte[] var1, int var2, byte[] var3, int var4) {
      if (var4 + this.ASCON_AEAD_RATE > var3.length) {
         throw new OutputLengthException("output buffer too short");
      } else {
         this.x0 ^= this.loadBytes(var1, var2);
         this.setBytes(this.x0, var3, var4);
         if (this.ASCON_AEAD_RATE == 16) {
            this.x1 ^= this.loadBytes(var1, var2 + 8);
            this.setBytes(this.x1, var3, var4 + 8);
         }

         this.p(this.nr);
      }
   }

   public void processAADByte(byte var1) {
      this.checkAAD();
      this.m_buf[this.m_bufPos] = var1;
      if (++this.m_bufPos == this.ASCON_AEAD_RATE) {
         this.processBufferAAD(this.m_buf, 0);
         this.m_bufPos = 0;
      }

   }

   public void processAADBytes(byte[] var1, int var2, int var3) {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else if (var3 > 0) {
         this.checkAAD();
         if (this.m_bufPos > 0) {
            int var4 = this.ASCON_AEAD_RATE - this.m_bufPos;
            if (var3 < var4) {
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
               this.m_bufPos += var3;
               return;
            }

            System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var4);
            var2 += var4;
            var3 -= var4;
            this.processBufferAAD(this.m_buf, 0);
         }

         while(var3 >= this.ASCON_AEAD_RATE) {
            this.processBufferAAD(var1, var2);
            var2 += this.ASCON_AEAD_RATE;
            var3 -= this.ASCON_AEAD_RATE;
         }

         System.arraycopy(var1, var2, this.m_buf, 0, var3);
         this.m_bufPos = var3;
      }
   }

   public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else {
         boolean var6 = this.checkData();
         int var7 = 0;
         if (var6) {
            if (this.m_bufPos > 0) {
               int var8 = this.ASCON_AEAD_RATE - this.m_bufPos;
               if (var3 < var8) {
                  System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
                  this.m_bufPos += var3;
                  return 0;
               }

               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var8);
               var2 += var8;
               var3 -= var8;
               this.processBufferEncrypt(this.m_buf, 0, var4, var5);
               var7 = this.ASCON_AEAD_RATE;
            }

            while(var3 >= this.ASCON_AEAD_RATE) {
               this.processBufferEncrypt(var1, var2, var4, var5 + var7);
               var2 += this.ASCON_AEAD_RATE;
               var3 -= this.ASCON_AEAD_RATE;
               var7 += this.ASCON_AEAD_RATE;
            }
         } else {
            int var9 = this.m_bufferSizeDecrypt - this.m_bufPos;
            if (var3 < var9) {
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
               this.m_bufPos += var3;
               return 0;
            }

            while(this.m_bufPos >= this.ASCON_AEAD_RATE) {
               this.processBufferDecrypt(this.m_buf, 0, var4, var5 + var7);
               this.m_bufPos -= this.ASCON_AEAD_RATE;
               System.arraycopy(this.m_buf, this.ASCON_AEAD_RATE, this.m_buf, 0, this.m_bufPos);
               var7 += this.ASCON_AEAD_RATE;
               var9 += this.ASCON_AEAD_RATE;
               if (var3 < var9) {
                  System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
                  this.m_bufPos += var3;
                  return var7;
               }
            }

            var9 = this.ASCON_AEAD_RATE - this.m_bufPos;
            System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var9);
            var2 += var9;
            var3 -= var9;
            this.processBufferDecrypt(this.m_buf, 0, var4, var5 + var7);

            for(var7 += this.ASCON_AEAD_RATE; var3 >= this.m_bufferSizeDecrypt; var7 += this.ASCON_AEAD_RATE) {
               this.processBufferDecrypt(var1, var2, var4, var5 + var7);
               var2 += this.ASCON_AEAD_RATE;
               var3 -= this.ASCON_AEAD_RATE;
            }
         }

         System.arraycopy(var1, var2, this.m_buf, 0, var3);
         this.m_bufPos = var3;
         return var7;
      }
   }

   public int doFinal(byte[] var1, int var2) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
      boolean var3 = this.checkData();
      int var4;
      if (var3) {
         var4 = this.m_bufPos + this.MAC_SIZE;
         if (var2 + var4 > var1.length) {
            throw new OutputLengthException("output buffer too short");
         }

         this.processFinalEncrypt(this.m_buf, this.m_bufPos, var1, var2);
         this.mac = new byte[this.MAC_SIZE];
         this.setBytes(this.x3, this.mac, 0);
         this.setBytes(this.x4, this.mac, 8);
         System.arraycopy(this.mac, 0, var1, var2 + this.m_bufPos, this.MAC_SIZE);
         this.reset(false);
      } else {
         if (this.m_bufPos < this.MAC_SIZE) {
            throw new InvalidCipherTextException("data too short");
         }

         this.m_bufPos -= this.MAC_SIZE;
         var4 = this.m_bufPos;
         if (var2 + var4 > var1.length) {
            throw new OutputLengthException("output buffer too short");
         }

         this.processFinalDecrypt(this.m_buf, this.m_bufPos, var1, var2);
         this.x3 ^= this.loadBytes(this.m_buf, this.m_bufPos);
         this.x4 ^= this.loadBytes(this.m_buf, this.m_bufPos + 8);
         if ((this.x3 | this.x4) != 0L) {
            throw new InvalidCipherTextException("mac check in " + this.getAlgorithmName() + " failed");
         }

         this.reset(true);
      }

      return var4;
   }

   public int getUpdateOutputSize(int var1) {
      int var2 = Math.max(0, var1);
      switch (this.m_state.ordinal()) {
         case 3:
         case 4:
            var2 += this.m_bufPos;
            break;
         case 5:
         case 6:
            var2 = Math.max(0, var2 - this.MAC_SIZE);
            break;
         case 7:
         case 8:
            var2 = Math.max(0, var2 + this.m_bufPos - this.MAC_SIZE);
      }

      return var2 - var2 % this.ASCON_AEAD_RATE;
   }

   public int getOutputSize(int var1) {
      int var2 = Math.max(0, var1);
      switch (this.m_state.ordinal()) {
         case 3:
         case 4:
            return var2 + this.m_bufPos + this.MAC_SIZE;
         case 5:
         case 6:
            return Math.max(0, var2 - this.MAC_SIZE);
         case 7:
         case 8:
            return Math.max(0, var2 + this.m_bufPos - this.MAC_SIZE);
         default:
            return var2 + this.MAC_SIZE;
      }
   }

   protected void reset(boolean var1) {
      Arrays.clear(this.m_buf);
      this.m_bufPos = 0;
      switch (this.m_state.ordinal()) {
         case 2:
         case 3:
         case 4:
            this.m_state = AsconBaseEngine.State.EncFinal;
            return;
         case 6:
         case 7:
         case 8:
            this.m_state = AsconBaseEngine.State.DecInit;
         case 1:
         case 5:
            this.ascon_aeadinit();
            super.reset(var1);
            return;
         default:
            throw new IllegalStateException(this.getAlgorithmName() + " needs to be initialized");
      }
   }

   public abstract String getAlgorithmVersion();

   protected static enum State {
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
