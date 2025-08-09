package org.bouncycastle.jcajce.util;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathValidator;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanism;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;

public class ProviderJcaJceHelper implements JcaJceHelper {
   protected final Provider provider;

   public ProviderJcaJceHelper(Provider var1) {
      this.provider = var1;
   }

   public Cipher createCipher(String var1) throws NoSuchAlgorithmException, NoSuchPaddingException {
      return Cipher.getInstance(var1, this.provider);
   }

   public Mac createMac(String var1) throws NoSuchAlgorithmException {
      return Mac.getInstance(var1, this.provider);
   }

   public KeyAgreement createKeyAgreement(String var1) throws NoSuchAlgorithmException {
      return KeyAgreement.getInstance(var1, this.provider);
   }

   public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String var1) throws NoSuchAlgorithmException {
      return AlgorithmParameterGenerator.getInstance(var1, this.provider);
   }

   public AlgorithmParameters createAlgorithmParameters(String var1) throws NoSuchAlgorithmException {
      return AlgorithmParameters.getInstance(var1, this.provider);
   }

   public KeyGenerator createKeyGenerator(String var1) throws NoSuchAlgorithmException {
      return KeyGenerator.getInstance(var1, this.provider);
   }

   public KeyFactory createKeyFactory(String var1) throws NoSuchAlgorithmException {
      return KeyFactory.getInstance(var1, this.provider);
   }

   public SecretKeyFactory createSecretKeyFactory(String var1) throws NoSuchAlgorithmException {
      return SecretKeyFactory.getInstance(var1, this.provider);
   }

   public KeyPairGenerator createKeyPairGenerator(String var1) throws NoSuchAlgorithmException {
      return KeyPairGenerator.getInstance(var1, this.provider);
   }

   /** @deprecated */
   public MessageDigest createDigest(String var1) throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(var1, this.provider);
   }

   public MessageDigest createMessageDigest(String var1) throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(var1, this.provider);
   }

   public Signature createSignature(String var1) throws NoSuchAlgorithmException {
      return Signature.getInstance(var1, this.provider);
   }

   public CertificateFactory createCertificateFactory(String var1) throws CertificateException {
      return CertificateFactory.getInstance(var1, this.provider);
   }

   public SecureRandom createSecureRandom(String var1) throws NoSuchAlgorithmException {
      return SecureRandom.getInstance(var1, this.provider);
   }

   public CertPathBuilder createCertPathBuilder(String var1) throws NoSuchAlgorithmException {
      return CertPathBuilder.getInstance(var1, this.provider);
   }

   public CertPathValidator createCertPathValidator(String var1) throws NoSuchAlgorithmException {
      return CertPathValidator.getInstance(var1, this.provider);
   }

   public CertStore createCertStore(String var1, CertStoreParameters var2) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
      return CertStore.getInstance(var1, var2, this.provider);
   }

   public ExemptionMechanism createExemptionMechanism(String var1) throws NoSuchAlgorithmException {
      return ExemptionMechanism.getInstance(var1, this.provider);
   }

   public KeyStore createKeyStore(String var1) throws KeyStoreException {
      return KeyStore.getInstance(var1, this.provider);
   }
}
