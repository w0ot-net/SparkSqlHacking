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
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceKobaraImaiCipher;

public class McElieceKobaraImaiCipherSpi extends AsymmetricHybridCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
   private Digest digest;
   private McElieceKobaraImaiCipher cipher;
   private ByteArrayOutputStream buf = new ByteArrayOutputStream();

   public McElieceKobaraImaiCipherSpi() {
      this.buf = new ByteArrayOutputStream();
   }

   protected McElieceKobaraImaiCipherSpi(Digest var1, McElieceKobaraImaiCipher var2) {
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
      if (this.opMode == 1) {
         return this.cipher.messageEncrypt(this.pad());
      } else if (this.opMode == 2) {
         try {
            byte[] var4 = this.buf.toByteArray();
            this.buf.reset();
            return this.unpad(this.cipher.messageDecrypt(var4));
         } catch (InvalidCipherTextException var5) {
            throw new BadPaddingException(var5.getMessage());
         }
      } else {
         throw new IllegalStateException("unknown mode in doFinal");
      }
   }

   protected int encryptOutputSize(int var1) {
      return 0;
   }

   protected int decryptOutputSize(int var1) {
      return 0;
   }

   protected void initCipherEncrypt(Key var1, AlgorithmParameterSpec var2, SecureRandom var3) throws InvalidKeyException, InvalidAlgorithmParameterException {
      this.buf.reset();
      AsymmetricKeyParameter var4 = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)var1);
      ParametersWithRandom var5 = new ParametersWithRandom(var4, var3);
      this.digest.reset();
      this.cipher.init(true, var5);
   }

   protected void initCipherDecrypt(Key var1, AlgorithmParameterSpec var2) throws InvalidKeyException, InvalidAlgorithmParameterException {
      this.buf.reset();
      AsymmetricKeyParameter var3 = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)var1);
      this.digest.reset();
      this.cipher.init(false, var3);
   }

   public String getName() {
      return "McElieceKobaraImaiCipher";
   }

   public int getKeySize(Key var1) throws InvalidKeyException {
      if (var1 instanceof PublicKey) {
         McElieceCCA2KeyParameters var3 = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)var1);
         return this.cipher.getKeySize(var3);
      } else if (var1 instanceof PrivateKey) {
         McElieceCCA2KeyParameters var2 = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)var1);
         return this.cipher.getKeySize(var2);
      } else {
         throw new InvalidKeyException();
      }
   }

   private byte[] pad() {
      this.buf.write(1);
      byte[] var1 = this.buf.toByteArray();
      this.buf.reset();
      return var1;
   }

   private byte[] unpad(byte[] var1) throws BadPaddingException {
      int var2;
      for(var2 = var1.length - 1; var2 >= 0 && var1[var2] == 0; --var2) {
      }

      if (var1[var2] != 1) {
         throw new BadPaddingException("invalid ciphertext");
      } else {
         byte[] var3 = new byte[var2];
         System.arraycopy(var1, 0, var3, 0, var2);
         return var3;
      }
   }

   public static class McElieceKobaraImai extends McElieceKobaraImaiCipherSpi {
      public McElieceKobaraImai() {
         super(DigestFactory.createSHA1(), new McElieceKobaraImaiCipher());
      }
   }

   public static class McElieceKobaraImai224 extends McElieceKobaraImaiCipherSpi {
      public McElieceKobaraImai224() {
         super(DigestFactory.createSHA224(), new McElieceKobaraImaiCipher());
      }
   }

   public static class McElieceKobaraImai256 extends McElieceKobaraImaiCipherSpi {
      public McElieceKobaraImai256() {
         super(DigestFactory.createSHA256(), new McElieceKobaraImaiCipher());
      }
   }

   public static class McElieceKobaraImai384 extends McElieceKobaraImaiCipherSpi {
      public McElieceKobaraImai384() {
         super(DigestFactory.createSHA384(), new McElieceKobaraImaiCipher());
      }
   }

   public static class McElieceKobaraImai512 extends McElieceKobaraImaiCipherSpi {
      public McElieceKobaraImai512() {
         super(DigestFactory.createSHA512(), new McElieceKobaraImaiCipher());
      }
   }
}
