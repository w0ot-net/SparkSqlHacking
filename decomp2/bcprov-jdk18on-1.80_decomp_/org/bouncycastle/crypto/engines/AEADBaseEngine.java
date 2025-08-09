package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

abstract class AEADBaseEngine implements AEADCipher {
   protected boolean forEncryption;
   protected String algorithmName;
   protected int KEY_SIZE;
   protected int IV_SIZE;
   protected int MAC_SIZE;
   protected byte[] initialAssociatedText;
   protected byte[] mac;

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public int getKeyBytesSize() {
      return this.KEY_SIZE;
   }

   public int getIVBytesSize() {
      return this.IV_SIZE;
   }

   public byte[] getMac() {
      return this.mac;
   }

   public void reset() {
      this.reset(true);
   }

   public int processByte(byte var1, byte[] var2, int var3) throws DataLengthException {
      return this.processBytes(new byte[]{var1}, 0, 1, var2, var3);
   }

   public void init(boolean var1, CipherParameters var2) {
      this.forEncryption = var1;
      KeyParameter var3;
      byte[] var4;
      if (var2 instanceof AEADParameters) {
         AEADParameters var6 = (AEADParameters)var2;
         var3 = var6.getKey();
         var4 = var6.getNonce();
         this.initialAssociatedText = var6.getAssociatedText();
         int var7 = var6.getMacSize();
         if (var7 != this.MAC_SIZE * 8) {
            throw new IllegalArgumentException("Invalid value for MAC size: " + var7);
         }
      } else {
         if (!(var2 instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to " + this.algorithmName);
         }

         ParametersWithIV var8 = (ParametersWithIV)var2;
         var3 = (KeyParameter)var8.getParameters();
         var4 = var8.getIV();
         this.initialAssociatedText = null;
      }

      if (var3 == null) {
         throw new IllegalArgumentException(this.algorithmName + " Init parameters must include a key");
      } else if (var4 != null && var4.length == this.IV_SIZE) {
         byte[] var5 = var3.getKey();
         if (var5.length != this.KEY_SIZE) {
            throw new IllegalArgumentException(this.algorithmName + " key must be " + this.KEY_SIZE + " bytes long");
         } else {
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(this.getAlgorithmName(), 128, var2, Utils.getPurpose(var1)));
            this.init(var5, var4);
            if (this.initialAssociatedText != null) {
               this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
            }

         }
      } else {
         throw new IllegalArgumentException(this.algorithmName + " requires exactly " + this.IV_SIZE + " bytes of IV");
      }
   }

   protected abstract void init(byte[] var1, byte[] var2);

   protected void reset(boolean var1) {
      if (var1) {
         this.mac = null;
      }

   }
}
