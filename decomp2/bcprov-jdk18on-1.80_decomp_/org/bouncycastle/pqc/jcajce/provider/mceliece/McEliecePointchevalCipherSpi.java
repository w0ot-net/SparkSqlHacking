package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2KeyParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McEliecePointchevalCipher;

public class McEliecePointchevalCipherSpi extends AsymmetricHybridCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
   private Digest digest;
   private McEliecePointchevalCipher cipher;
   private ByteArrayOutputStream buf = new ByteArrayOutputStream();

   protected McEliecePointchevalCipherSpi(Digest var1, McEliecePointchevalCipher var2) {
      this.digest = var1;
      this.cipher = var2;
      this.buf = new ByteArrayOutputStream();
   }

   public byte[] update(byte[] var1, int var2, int var3) {
      this.buf.write(var1, var2, var3);
      return new byte[0];
   }

   public byte[] doFinal(byte[] var1, int var2, int var3) throws BadPaddingException {
      this.update(var1, var2, var3);
      byte[] var4 = this.buf.toByteArray();
      this.buf.reset();
      if (this.opMode == 1) {
         return this.cipher.messageEncrypt(var4);
      } else if (this.opMode == 2) {
         try {
            return this.cipher.messageDecrypt(var4);
         } catch (InvalidCipherTextException var6) {
            throw new BadPaddingException(var6.getMessage());
         }
      } else {
         return null;
      }
   }

   protected int encryptOutputSize(int var1) {
      return 0;
   }

   protected int decryptOutputSize(int var1) {
      return 0;
   }

   protected void initCipherEncrypt(Key var1, AlgorithmParameterSpec var2, SecureRandom var3) throws InvalidKeyException, InvalidAlgorithmParameterException {
      AsymmetricKeyParameter var4 = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)var1);
      ParametersWithRandom var5 = new ParametersWithRandom(var4, var3);
      this.digest.reset();
      this.cipher.init(true, var5);
   }

   protected void initCipherDecrypt(Key var1, AlgorithmParameterSpec var2) throws InvalidKeyException, InvalidAlgorithmParameterException {
      AsymmetricKeyParameter var3 = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)var1);
      this.digest.reset();
      this.cipher.init(false, var3);
   }

   public String getName() {
      return "McEliecePointchevalCipher";
   }

   public int getKeySize(Key var1) throws InvalidKeyException {
      McElieceCCA2KeyParameters var2;
      if (var1 instanceof PublicKey) {
         var2 = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)var1);
      } else {
         var2 = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)var1);
      }

      return this.cipher.getKeySize(var2);
   }

   public static class McEliecePointcheval extends McEliecePointchevalCipherSpi {
      public McEliecePointcheval() {
         super(DigestFactory.createSHA1(), new McEliecePointchevalCipher());
      }
   }

   public static class McEliecePointcheval224 extends McEliecePointchevalCipherSpi {
      public McEliecePointcheval224() {
         super(DigestFactory.createSHA224(), new McEliecePointchevalCipher());
      }
   }

   public static class McEliecePointcheval256 extends McEliecePointchevalCipherSpi {
      public McEliecePointcheval256() {
         super(DigestFactory.createSHA256(), new McEliecePointchevalCipher());
      }
   }

   public static class McEliecePointcheval384 extends McEliecePointchevalCipherSpi {
      public McEliecePointcheval384() {
         super(DigestFactory.createSHA384(), new McEliecePointchevalCipher());
      }
   }

   public static class McEliecePointcheval512 extends McEliecePointchevalCipherSpi {
      public McEliecePointcheval512() {
         super(DigestFactory.createSHA512(), new McEliecePointchevalCipher());
      }
   }
}
