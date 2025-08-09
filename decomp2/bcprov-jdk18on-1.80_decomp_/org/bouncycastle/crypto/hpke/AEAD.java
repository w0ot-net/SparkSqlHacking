package org.bouncycastle.crypto.hpke;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.modes.ChaCha20Poly1305;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class AEAD {
   private final short aeadId;
   private final byte[] key;
   private final byte[] baseNonce;
   private long seq = 0L;
   private AEADCipher cipher;

   public AEAD(short var1, byte[] var2, byte[] var3) {
      this.key = var2;
      this.baseNonce = var3;
      this.aeadId = var1;
      this.seq = 0L;
      switch (var1) {
         case -1:
         case 0:
         default:
            break;
         case 1:
         case 2:
            this.cipher = new GCMBlockCipher(new AESEngine());
            break;
         case 3:
            this.cipher = new ChaCha20Poly1305();
      }

   }

   public byte[] seal(byte[] var1, byte[] var2, int var3, int var4) throws InvalidCipherTextException {
      if (var3 >= 0 && var3 <= var2.length) {
         if (var3 + var4 > var2.length) {
            throw new IndexOutOfBoundsException("Invalid length");
         } else {
            switch (this.aeadId) {
               case -1:
               case 0:
               default:
                  throw new IllegalStateException("Export only mode, cannot be used to seal/open");
               case 1:
               case 2:
               case 3:
                  ParametersWithIV var5 = new ParametersWithIV(new KeyParameter(this.key), this.ComputeNonce());
                  this.cipher.init(true, var5);
                  this.cipher.processAADBytes(var1, 0, var1.length);
                  byte[] var6 = new byte[this.cipher.getOutputSize(var4)];
                  int var7 = this.cipher.processBytes(var2, var3, var4, var6, 0);
                  this.cipher.doFinal(var6, var7);
                  ++this.seq;
                  return var6;
            }
         }
      } else {
         throw new IndexOutOfBoundsException("Invalid offset");
      }
   }

   public byte[] seal(byte[] var1, byte[] var2) throws InvalidCipherTextException {
      return this.seal(var1, var2, 0, var2.length);
   }

   public byte[] open(byte[] var1, byte[] var2, int var3, int var4) throws InvalidCipherTextException {
      if (var3 >= 0 && var3 <= var2.length) {
         if (var3 + var4 > var2.length) {
            throw new IndexOutOfBoundsException("Invalid length");
         } else {
            switch (this.aeadId) {
               case -1:
               case 0:
               default:
                  throw new IllegalStateException("Export only mode, cannot be used to seal/open");
               case 1:
               case 2:
               case 3:
                  ParametersWithIV var5 = new ParametersWithIV(new KeyParameter(this.key), this.ComputeNonce());
                  this.cipher.init(false, var5);
                  this.cipher.processAADBytes(var1, 0, var1.length);
                  byte[] var6 = new byte[this.cipher.getOutputSize(var4)];
                  int var7 = this.cipher.processBytes(var2, var3, var4, var6, 0);
                  var7 += this.cipher.doFinal(var6, var7);
                  ++this.seq;
                  return var6;
            }
         }
      } else {
         throw new IndexOutOfBoundsException("Invalid offset");
      }
   }

   public byte[] open(byte[] var1, byte[] var2) throws InvalidCipherTextException {
      return this.open(var1, var2, 0, var2.length);
   }

   private byte[] ComputeNonce() {
      byte[] var1 = Pack.longToBigEndian(this.seq);
      int var2 = this.baseNonce.length;
      byte[] var3 = Arrays.clone(this.baseNonce);

      for(int var4 = 0; var4 < 8; ++var4) {
         var3[var2 - 8 + var4] ^= var1[var4];
      }

      return var3;
   }
}
