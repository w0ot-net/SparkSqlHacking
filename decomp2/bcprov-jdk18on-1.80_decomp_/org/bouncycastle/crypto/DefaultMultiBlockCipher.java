package org.bouncycastle.crypto;

public abstract class DefaultMultiBlockCipher implements MultiBlockCipher {
   protected DefaultMultiBlockCipher() {
   }

   public int getMultiBlockSize() {
      return this.getBlockSize();
   }

   public int processBlocks(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException, IllegalStateException {
      int var6 = 0;
      int var7 = this.getMultiBlockSize();

      for(int var8 = 0; var8 != var3; ++var8) {
         var6 += this.processBlock(var1, var2, var4, var5 + var6);
         var2 += var7;
      }

      return var6;
   }
}
