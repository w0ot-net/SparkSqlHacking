package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

public class ZeroBytePadding implements BlockCipherPadding {
   public void init(SecureRandom var1) throws IllegalArgumentException {
   }

   public String getPaddingName() {
      return "ZeroByte";
   }

   public int addPadding(byte[] var1, int var2) {
      int var3;
      for(var3 = var1.length - var2; var2 < var1.length; ++var2) {
         var1[var2] = 0;
      }

      return var3;
   }

   public int padCount(byte[] var1) throws InvalidCipherTextException {
      int var2 = 0;
      int var3 = -1;
      int var4 = var1.length;

      while(true) {
         --var4;
         if (var4 < 0) {
            return var2;
         }

         int var5 = var1[var4] & 255;
         int var6 = (var5 ^ 0) - 1 >> 31;
         var3 &= var6;
         var2 -= var3;
      }
   }
}
