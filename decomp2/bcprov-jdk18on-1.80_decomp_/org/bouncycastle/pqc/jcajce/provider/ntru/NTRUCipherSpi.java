package org.bouncycastle.pqc.jcajce.provider.ntru;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.jcajce.spec.KEMParameterSpec;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.pqc.crypto.ntru.NTRUKEMExtractor;
import org.bouncycastle.pqc.crypto.ntru.NTRUKEMGenerator;
import org.bouncycastle.pqc.jcajce.provider.util.WrapUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;

class NTRUCipherSpi extends CipherSpi {
   private final String algorithmName;
   private NTRUKEMGenerator kemGen;
   private KTSParameterSpec kemParameterSpec;
   private BCNTRUPublicKey wrapKey;
   private BCNTRUPrivateKey unwrapKey;
   private AlgorithmParameters engineParams;

   NTRUCipherSpi(String var1) throws NoSuchAlgorithmException {
      this.algorithmName = var1;
   }

   protected void engineSetMode(String var1) throws NoSuchAlgorithmException {
      throw new NoSuchAlgorithmException("Cannot support mode " + var1);
   }

   protected void engineSetPadding(String var1) throws NoSuchPaddingException {
      throw new NoSuchPaddingException("Padding " + var1 + " unknown");
   }

   protected int engineGetKeySize(Key var1) {
      return 2048;
   }

   protected int engineGetBlockSize() {
      return 0;
   }

   protected int engineGetOutputSize(int var1) {
      return -1;
   }

   protected byte[] engineGetIV() {
      return null;
   }

   protected AlgorithmParameters engineGetParameters() {
      if (this.engineParams == null) {
         try {
            this.engineParams = AlgorithmParameters.getInstance(this.algorithmName, "BCPQC");
            this.engineParams.init(this.kemParameterSpec);
         } catch (Exception var2) {
            throw Exceptions.illegalStateException(var2.toString(), var2);
         }
      }

      return this.engineParams;
   }

   protected void engineInit(int var1, Key var2, SecureRandom var3) throws InvalidKeyException {
      try {
         this.engineInit(var1, var2, (AlgorithmParameterSpec)null, var3);
      } catch (InvalidAlgorithmParameterException var5) {
         throw Exceptions.illegalArgumentException(var5.getMessage(), var5);
      }
   }

   protected void engineInit(int var1, Key var2, AlgorithmParameterSpec var3, SecureRandom var4) throws InvalidKeyException, InvalidAlgorithmParameterException {
      if (var3 == null) {
         this.kemParameterSpec = new KEMParameterSpec("AES-KWP");
      } else {
         if (!(var3 instanceof KTSParameterSpec)) {
            throw new InvalidAlgorithmParameterException(this.algorithmName + " can only accept KTSParameterSpec");
         }

         this.kemParameterSpec = (KTSParameterSpec)var3;
      }

      if (var1 == 3) {
         if (!(var2 instanceof BCNTRUPublicKey)) {
            throw new InvalidKeyException("Only a " + this.algorithmName + " public key can be used for wrapping");
         }

         this.wrapKey = (BCNTRUPublicKey)var2;
         this.kemGen = new NTRUKEMGenerator(CryptoServicesRegistrar.getSecureRandom(var4));
      } else {
         if (var1 != 4) {
            throw new InvalidParameterException("Cipher only valid for wrapping/unwrapping");
         }

         if (!(var2 instanceof BCNTRUPrivateKey)) {
            throw new InvalidKeyException("Only a " + this.algorithmName + " private key can be used for unwrapping");
         }

         this.unwrapKey = (BCNTRUPrivateKey)var2;
      }

   }

   protected void engineInit(int var1, Key var2, AlgorithmParameters var3, SecureRandom var4) throws InvalidKeyException, InvalidAlgorithmParameterException {
      AlgorithmParameterSpec var5 = null;
      if (var3 != null) {
         try {
            var5 = var3.getParameterSpec(KEMParameterSpec.class);
         } catch (Exception var7) {
            throw new InvalidAlgorithmParameterException("can't handle parameter " + var3.toString());
         }
      }

      this.engineInit(var1, var2, var5, var4);
   }

   protected byte[] engineUpdate(byte[] var1, int var2, int var3) {
      throw new IllegalStateException("Not supported in a wrapping mode");
   }

   protected int engineUpdate(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException {
      throw new IllegalStateException("Not supported in a wrapping mode");
   }

   protected byte[] engineDoFinal(byte[] var1, int var2, int var3) throws IllegalBlockSizeException, BadPaddingException {
      throw new IllegalStateException("Not supported in a wrapping mode");
   }

   protected int engineDoFinal(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
      throw new IllegalStateException("Not supported in a wrapping mode");
   }

   protected byte[] engineWrap(Key var1) throws IllegalBlockSizeException, InvalidKeyException {
      byte[] var2 = var1.getEncoded();
      if (var2 == null) {
         throw new InvalidKeyException("Cannot wrap key, null encoding.");
      } else {
         SecretWithEncapsulation var3 = null;

         byte[] var8;
         try {
            var3 = this.kemGen.generateEncapsulated(this.wrapKey.getKeyParams());
            Wrapper var4 = WrapUtil.getKeyWrapper(this.kemParameterSpec, var3.getSecret());
            byte[] var5 = var3.getEncapsulation();
            byte[] var6 = var1.getEncoded();
            byte[] var7 = Arrays.concatenate(var5, var4.wrap(var6, 0, var6.length));
            Arrays.clear(var6);
            var8 = var7;
         } catch (IllegalArgumentException var16) {
            throw new IllegalBlockSizeException("unable to generate KTS secret: " + var16.getMessage());
         } finally {
            try {
               if (var3 != null) {
                  var3.destroy();
               }
            } catch (DestroyFailedException var17) {
               throw new IllegalBlockSizeException("unable to destroy interim values: " + var17.getMessage());
            }

         }

         return var8;
      }
   }

   protected Key engineUnwrap(byte[] var1, String var2, int var3) throws InvalidKeyException, NoSuchAlgorithmException {
      if (var3 != 3) {
         throw new InvalidKeyException("only SECRET_KEY supported");
      } else {
         byte[] var4 = null;

         SecretKeySpec var9;
         try {
            NTRUKEMExtractor var5 = new NTRUKEMExtractor(this.unwrapKey.getKeyParams());
            var4 = var5.extractSecret(Arrays.copyOfRange((byte[])var1, 0, var5.getEncapsulationLength()));
            Wrapper var6 = WrapUtil.getKeyUnwrapper(this.kemParameterSpec, var4);
            byte[] var7 = Arrays.copyOfRange(var1, var5.getEncapsulationLength(), var1.length);
            SecretKeySpec var8 = new SecretKeySpec(var6.unwrap(var7, 0, var7.length), var2);
            var9 = var8;
         } catch (IllegalArgumentException var14) {
            throw new NoSuchAlgorithmException("unable to extract KTS secret: " + var14.getMessage());
         } catch (InvalidCipherTextException var15) {
            throw new InvalidKeyException("unable to extract KTS secret: " + var15.getMessage());
         } finally {
            if (var4 != null) {
               Arrays.clear(var4);
            }

         }

         return var9;
      }
   }

   public static class Base extends NTRUCipherSpi {
      public Base() throws NoSuchAlgorithmException {
         super("NTRU");
      }
   }
}
