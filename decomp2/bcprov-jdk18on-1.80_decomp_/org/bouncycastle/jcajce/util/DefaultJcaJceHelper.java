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

public class DefaultJcaJceHelper implements JcaJceHelper {
   public Cipher createCipher(String var1) throws NoSuchAlgorithmException, NoSuchPaddingException {
      return Cipher.getInstance(var1);
   }

   public Mac createMac(String var1) throws NoSuchAlgorithmException {
      return Mac.getInstance(var1);
   }

   public KeyAgreement createKeyAgreement(String var1) throws NoSuchAlgorithmException {
      return KeyAgreement.getInstance(var1);
   }

   public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String var1) throws NoSuchAlgorithmException {
      return AlgorithmParameterGenerator.getInstance(var1);
   }

   public AlgorithmParameters createAlgorithmParameters(String var1) throws NoSuchAlgorithmException {
      return AlgorithmParameters.getInstance(var1);
   }

   public KeyGenerator createKeyGenerator(String var1) throws NoSuchAlgorithmException {
      return KeyGenerator.getInstance(var1);
   }

   public KeyFactory createKeyFactory(String var1) throws NoSuchAlgorithmException {
      return KeyFactory.getInstance(var1);
   }

   public SecretKeyFactory createSecretKeyFactory(String var1) throws NoSuchAlgorithmException {
      return SecretKeyFactory.getInstance(var1);
   }

   public KeyPairGenerator createKeyPairGenerator(String var1) throws NoSuchAlgorithmException {
      return KeyPairGenerator.getInstance(var1);
   }

   /** @deprecated */
   public MessageDigest createDigest(String var1) throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(var1);
   }

   public MessageDigest createMessageDigest(String var1) throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(var1);
   }

   public Signature createSignature(String var1) throws NoSuchAlgorithmException {
      return Signature.getInstance(var1);
   }

   public CertificateFactory createCertificateFactory(String var1) throws CertificateException {
      return CertificateFactory.getInstance(var1);
   }

   public SecureRandom createSecureRandom(String var1) throws NoSuchAlgorithmException {
      return SecureRandom.getInstance(var1);
   }

   public CertPathBuilder createCertPathBuilder(String var1) throws NoSuchAlgorithmException {
      return CertPathBuilder.getInstance(var1);
   }

   public CertPathValidator createCertPathValidator(String var1) throws NoSuchAlgorithmException {
      return CertPathValidator.getInstance(var1);
   }

   public CertStore createCertStore(String var1, CertStoreParameters var2) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
      return CertStore.getInstance(var1, var2);
   }

   public ExemptionMechanism createExemptionMechanism(String var1) throws NoSuchAlgorithmException {
      return ExemptionMechanism.getInstance(var1);
   }

   public KeyStore createKeyStore(String var1) throws KeyStoreException {
      return KeyStore.getInstance(var1);
   }
}
