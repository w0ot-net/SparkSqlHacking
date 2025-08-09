package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;

abstract class AEADBufferBaseEngine extends AEADBaseEngine {
   protected byte[] m_buf;
   protected byte[] m_aad;
   protected int m_bufPos;
   protected int m_aadPos;
   protected boolean aadFinished;
   protected boolean initialised = false;
   protected int AADBufferSize;
   protected int BlockSize;
   protected State m_state;

   AEADBufferBaseEngine() {
      this.m_state = AEADBufferBaseEngine.State.Uninitialized;
   }

   public void processAADByte(byte var1) {
      this.checkAAD();
      if (this.m_aadPos == this.AADBufferSize) {
         this.processBufferAAD(this.m_aad, 0);
         this.m_aadPos = 0;
      }

      this.m_aad[this.m_aadPos++] = var1;
   }

   public void processAADBytes(byte[] var1, int var2, int var3) {
      if (var2 + var3 > var1.length) {
         throw new DataLengthException("input buffer too short");
      } else if (var3 > 0) {
         this.checkAAD();
         if (this.m_aadPos > 0) {
            int var4 = this.AADBufferSize - this.m_aadPos;
            if (var3 <= var4) {
               System.arraycopy(var1, var2, this.m_aad, this.m_aadPos, var3);
               this.m_aadPos += var3;
               return;
            }

            System.arraycopy(var1, var2, this.m_aad, this.m_aadPos, var4);
            var2 += var4;
            var3 -= var4;
            this.processBufferAAD(this.m_aad, 0);
            this.m_aadPos = 0;
         }

         while(var3 > this.AADBufferSize) {
            this.processBufferAAD(var1, var2);
            var2 += this.AADBufferSize;
            var3 -= this.AADBufferSize;
         }

         System.arraycopy(var1, var2, this.m_aad, this.m_aadPos, var3);
         this.m_aadPos += var3;
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
               int var8 = this.BlockSize - this.m_bufPos;
               if (var3 <= var8) {
                  System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
                  this.m_bufPos += var3;
                  return 0;
               }

               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var8);
               var2 += var8;
               var3 -= var8;
               this.validateAndProcessBuffer(this.m_buf, 0, var4, var5);
               var7 = this.BlockSize;
            }

            while(var3 > this.BlockSize) {
               this.validateAndProcessBuffer(var1, var2, var4, var5 + var7);
               var2 += this.BlockSize;
               var3 -= this.BlockSize;
               var7 += this.BlockSize;
            }
         } else {
            int var9 = this.BlockSize + this.MAC_SIZE - this.m_bufPos;
            if (var3 <= var9) {
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
               this.m_bufPos += var3;
               return 0;
            }

            if (this.BlockSize >= this.MAC_SIZE) {
               if (this.m_bufPos > this.BlockSize) {
                  this.validateAndProcessBuffer(this.m_buf, 0, var4, var5);
                  this.m_bufPos -= this.BlockSize;
                  System.arraycopy(this.m_buf, this.BlockSize, this.m_buf, 0, this.m_bufPos);
                  var7 = this.BlockSize;
                  var9 += this.BlockSize;
                  if (var3 <= var9) {
                     System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
                     this.m_bufPos += var3;
                     return var7;
                  }
               }

               var9 = this.BlockSize - this.m_bufPos;
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var9);
               var2 += var9;
               var3 -= var9;
               this.validateAndProcessBuffer(this.m_buf, 0, var4, var5 + var7);
               var7 += this.BlockSize;
            } else {
               while(this.m_bufPos > this.BlockSize && var3 + this.m_bufPos > this.BlockSize + this.MAC_SIZE) {
                  this.validateAndProcessBuffer(this.m_buf, var7, var4, var5 + var7);
                  this.m_bufPos -= this.BlockSize;
                  var7 += this.BlockSize;
               }

               if (this.m_bufPos != 0) {
                  System.arraycopy(this.m_buf, var7, this.m_buf, 0, this.m_bufPos);
                  if (this.m_bufPos + var3 <= this.BlockSize + this.MAC_SIZE) {
                     System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
                     this.m_bufPos += var3;
                     return var7;
                  }

                  var9 = Math.max(this.BlockSize - this.m_bufPos, 0);
                  System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var9);
                  var2 += var9;
                  this.validateAndProcessBuffer(this.m_buf, 0, var4, var5 + var7);
                  var7 += this.BlockSize;
                  var3 -= var9;
               }
            }

            while(var3 > this.BlockSize + this.MAC_SIZE) {
               this.validateAndProcessBuffer(var1, var2, var4, var5 + var7);
               var2 += this.BlockSize;
               var3 -= this.BlockSize;
               var7 += this.BlockSize;
            }
         }

         System.arraycopy(var1, var2, this.m_buf, 0, var3);
         this.m_bufPos = var3;
         return var7;
      }
   }

   public int doFinal(byte[] var1, int var2) throws IllegalStateException, InvalidCipherTextException {
      boolean var3 = this.checkData();
      int var4;
      if (var3) {
         var4 = this.m_bufPos + this.MAC_SIZE;
      } else {
         if (this.m_bufPos < this.MAC_SIZE) {
            throw new InvalidCipherTextException("data too short");
         }

         this.m_bufPos -= this.MAC_SIZE;
         var4 = this.m_bufPos;
      }

      if (var2 > var1.length - var4) {
         throw new OutputLengthException("output buffer too short");
      } else {
         this.processFinalBlock(var1, var2);
         if (var3) {
            System.arraycopy(this.mac, 0, var1, var2 + var4 - this.MAC_SIZE, this.MAC_SIZE);
         } else if (!Arrays.constantTimeAreEqual(this.MAC_SIZE, this.mac, 0, this.m_buf, this.m_bufPos)) {
            throw new InvalidCipherTextException(this.algorithmName + " mac does not match");
         }

         this.reset(!var3);
         return var4;
      }
   }

   public int getBlockSize() {
      return this.BlockSize;
   }

   public int getUpdateOutputSize(int var1) {
      int var2 = Math.max(0, var1) - 1;
      switch (this.m_state.ordinal()) {
         case 3:
         case 4:
            var2 = Math.max(0, var2 + this.m_bufPos);
            break;
         case 5:
         case 6:
            var2 = Math.max(0, var2 - this.MAC_SIZE);
            break;
         case 7:
         case 8:
            var2 = Math.max(0, var2 + this.m_bufPos - this.MAC_SIZE);
      }

      return var2 - var2 % this.BlockSize;
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

   protected void checkAAD() {
      switch (this.m_state.ordinal()) {
         case 1:
            this.m_state = AEADBufferBaseEngine.State.EncAad;
         case 2:
         case 6:
            break;
         case 3:
         default:
            throw new IllegalStateException(this.getAlgorithmName() + " needs to be initialized");
         case 4:
            throw new IllegalStateException(this.getAlgorithmName() + " cannot be reused for encryption");
         case 5:
            this.m_state = AEADBufferBaseEngine.State.DecAad;
      }

   }

   protected boolean checkData() {
      switch (this.m_state.ordinal()) {
         case 1:
         case 2:
            this.finishAAD(AEADBufferBaseEngine.State.EncData);
            return true;
         case 3:
            return true;
         case 4:
            throw new IllegalStateException(this.getAlgorithmName() + " cannot be reused for encryption");
         case 5:
         case 6:
            this.finishAAD(AEADBufferBaseEngine.State.DecData);
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
            this.processFinalAAD();
         default:
            this.m_aadPos = 0;
            this.m_state = var1;
      }
   }

   protected void bufferReset() {
      Arrays.fill((byte[])this.m_buf, (byte)0);
      Arrays.fill((byte[])this.m_aad, (byte)0);
      this.m_bufPos = 0;
      this.m_aadPos = 0;
      switch (this.m_state.ordinal()) {
         case 2:
         case 3:
         case 4:
            this.m_state = AEADBufferBaseEngine.State.EncFinal;
            return;
         case 6:
         case 7:
         case 8:
            this.m_state = AEADBufferBaseEngine.State.DecInit;
         case 1:
         case 5:
            return;
         default:
            throw new IllegalStateException(this.getAlgorithmName() + " needs to be initialized");
      }
   }

   protected void validateAndProcessBuffer(byte[] var1, int var2, byte[] var3, int var4) {
      if (var4 > var3.length - this.BlockSize) {
         throw new OutputLengthException("output buffer too short");
      } else {
         this.processBuffer(var1, var2, var3, var4);
      }
   }

   protected abstract void processFinalBlock(byte[] var1, int var2);

   protected abstract void processBufferAAD(byte[] var1, int var2);

   protected abstract void processFinalAAD();

   protected abstract void processBuffer(byte[] var1, int var2, byte[] var3, int var4);

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
