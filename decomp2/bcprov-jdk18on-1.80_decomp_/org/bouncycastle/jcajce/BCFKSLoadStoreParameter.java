package org.bouncycastle.jcajce;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import org.bouncycastle.crypto.util.PBKDF2Config;
import org.bouncycastle.crypto.util.PBKDFConfig;

public class BCFKSLoadStoreParameter extends BCLoadStoreParameter {
   private final PBKDFConfig storeConfig;
   private final EncryptionAlgorithm encAlg;
   private final MacAlgorithm macAlg;
   private final SignatureAlgorithm sigAlg;
   private final Key sigKey;
   private final X509Certificate[] certificates;
   private final CertChainValidator validator;

   private BCFKSLoadStoreParameter(Builder var1) {
      super(var1.in, var1.out, var1.protectionParameter);
      this.storeConfig = var1.storeConfig;
      this.encAlg = var1.encAlg;
      this.macAlg = var1.macAlg;
      this.sigAlg = var1.sigAlg;
      this.sigKey = var1.sigKey;
      this.certificates = var1.certs;
      this.validator = var1.validator;
   }

   public PBKDFConfig getStorePBKDFConfig() {
      return this.storeConfig;
   }

   public EncryptionAlgorithm getStoreEncryptionAlgorithm() {
      return this.encAlg;
   }

   public MacAlgorithm getStoreMacAlgorithm() {
      return this.macAlg;
   }

   public SignatureAlgorithm getStoreSignatureAlgorithm() {
      return this.sigAlg;
   }

   public Key getStoreSignatureKey() {
      return this.sigKey;
   }

   public X509Certificate[] getStoreCertificates() {
      return this.certificates;
   }

   public CertChainValidator getCertChainValidator() {
      return this.validator;
   }

   public static class Builder {
      private final OutputStream out;
      private final InputStream in;
      private final KeyStore.ProtectionParameter protectionParameter;
      private final Key sigKey;
      private PBKDFConfig storeConfig;
      private EncryptionAlgorithm encAlg;
      private MacAlgorithm macAlg;
      private SignatureAlgorithm sigAlg;
      private X509Certificate[] certs;
      private CertChainValidator validator;

      public Builder() {
         this((OutputStream)null, (KeyStore.ProtectionParameter)null);
      }

      public Builder(OutputStream var1, char[] var2) {
         this((OutputStream)var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)));
      }

      public Builder(OutputStream var1, KeyStore.ProtectionParameter var2) {
         this.storeConfig = (new PBKDF2Config.Builder()).withIterationCount(16384).withSaltLength(64).withPRF(PBKDF2Config.PRF_SHA512).build();
         this.encAlg = BCFKSLoadStoreParameter.EncryptionAlgorithm.AES256_CCM;
         this.macAlg = BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA512;
         this.sigAlg = BCFKSLoadStoreParameter.SignatureAlgorithm.SHA512withECDSA;
         this.certs = null;
         this.in = null;
         this.out = var1;
         this.protectionParameter = var2;
         this.sigKey = null;
      }

      public Builder(OutputStream var1, PrivateKey var2) {
         this.storeConfig = (new PBKDF2Config.Builder()).withIterationCount(16384).withSaltLength(64).withPRF(PBKDF2Config.PRF_SHA512).build();
         this.encAlg = BCFKSLoadStoreParameter.EncryptionAlgorithm.AES256_CCM;
         this.macAlg = BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA512;
         this.sigAlg = BCFKSLoadStoreParameter.SignatureAlgorithm.SHA512withECDSA;
         this.certs = null;
         this.in = null;
         this.out = var1;
         this.protectionParameter = null;
         this.sigKey = var2;
      }

      public Builder(InputStream var1, PublicKey var2) {
         this.storeConfig = (new PBKDF2Config.Builder()).withIterationCount(16384).withSaltLength(64).withPRF(PBKDF2Config.PRF_SHA512).build();
         this.encAlg = BCFKSLoadStoreParameter.EncryptionAlgorithm.AES256_CCM;
         this.macAlg = BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA512;
         this.sigAlg = BCFKSLoadStoreParameter.SignatureAlgorithm.SHA512withECDSA;
         this.certs = null;
         this.in = var1;
         this.out = null;
         this.protectionParameter = null;
         this.sigKey = var2;
      }

      public Builder(InputStream var1, CertChainValidator var2) {
         this.storeConfig = (new PBKDF2Config.Builder()).withIterationCount(16384).withSaltLength(64).withPRF(PBKDF2Config.PRF_SHA512).build();
         this.encAlg = BCFKSLoadStoreParameter.EncryptionAlgorithm.AES256_CCM;
         this.macAlg = BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA512;
         this.sigAlg = BCFKSLoadStoreParameter.SignatureAlgorithm.SHA512withECDSA;
         this.certs = null;
         this.in = var1;
         this.out = null;
         this.protectionParameter = null;
         this.validator = var2;
         this.sigKey = null;
      }

      public Builder(InputStream var1, char[] var2) {
         this((InputStream)var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)));
      }

      public Builder(InputStream var1, KeyStore.ProtectionParameter var2) {
         this.storeConfig = (new PBKDF2Config.Builder()).withIterationCount(16384).withSaltLength(64).withPRF(PBKDF2Config.PRF_SHA512).build();
         this.encAlg = BCFKSLoadStoreParameter.EncryptionAlgorithm.AES256_CCM;
         this.macAlg = BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA512;
         this.sigAlg = BCFKSLoadStoreParameter.SignatureAlgorithm.SHA512withECDSA;
         this.certs = null;
         this.in = var1;
         this.out = null;
         this.protectionParameter = var2;
         this.sigKey = null;
      }

      public Builder withStorePBKDFConfig(PBKDFConfig var1) {
         this.storeConfig = var1;
         return this;
      }

      public Builder withStoreEncryptionAlgorithm(EncryptionAlgorithm var1) {
         this.encAlg = var1;
         return this;
      }

      public Builder withStoreMacAlgorithm(MacAlgorithm var1) {
         this.macAlg = var1;
         return this;
      }

      public Builder withCertificates(X509Certificate[] var1) {
         X509Certificate[] var2 = new X509Certificate[var1.length];
         System.arraycopy(var1, 0, var2, 0, var2.length);
         this.certs = var2;
         return this;
      }

      public Builder withStoreSignatureAlgorithm(SignatureAlgorithm var1) {
         this.sigAlg = var1;
         return this;
      }

      public BCFKSLoadStoreParameter build() {
         return new BCFKSLoadStoreParameter(this);
      }
   }

   public interface CertChainValidator {
      boolean isValid(X509Certificate[] var1);
   }

   public static enum EncryptionAlgorithm {
      AES256_CCM,
      AES256_KWP;

      // $FF: synthetic method
      private static EncryptionAlgorithm[] $values() {
         return new EncryptionAlgorithm[]{AES256_CCM, AES256_KWP};
      }
   }

   public static enum MacAlgorithm {
      HmacSHA512,
      HmacSHA3_512;

      // $FF: synthetic method
      private static MacAlgorithm[] $values() {
         return new MacAlgorithm[]{HmacSHA512, HmacSHA3_512};
      }
   }

   public static enum SignatureAlgorithm {
      SHA512withDSA,
      SHA3_512withDSA,
      SHA512withECDSA,
      SHA3_512withECDSA,
      SHA512withRSA,
      SHA3_512withRSA;

      // $FF: synthetic method
      private static SignatureAlgorithm[] $values() {
         return new SignatureAlgorithm[]{SHA512withDSA, SHA3_512withDSA, SHA512withECDSA, SHA3_512withECDSA, SHA512withRSA, SHA3_512withRSA};
      }
   }
}
