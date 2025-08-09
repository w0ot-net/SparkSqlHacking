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
import java.security.NoSuchProviderException;
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

public interface JcaJceHelper {
   Cipher createCipher(String var1) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;

   Mac createMac(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   KeyAgreement createKeyAgreement(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   AlgorithmParameterGenerator createAlgorithmParameterGenerator(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   AlgorithmParameters createAlgorithmParameters(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   KeyGenerator createKeyGenerator(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   KeyFactory createKeyFactory(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   SecretKeyFactory createSecretKeyFactory(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   KeyPairGenerator createKeyPairGenerator(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   /** @deprecated */
   MessageDigest createDigest(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   MessageDigest createMessageDigest(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   Signature createSignature(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   CertificateFactory createCertificateFactory(String var1) throws NoSuchProviderException, CertificateException;

   SecureRandom createSecureRandom(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   CertPathBuilder createCertPathBuilder(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   CertPathValidator createCertPathValidator(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   CertStore createCertStore(String var1, CertStoreParameters var2) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException;

   ExemptionMechanism createExemptionMechanism(String var1) throws NoSuchAlgorithmException, NoSuchProviderException;

   KeyStore createKeyStore(String var1) throws KeyStoreException, NoSuchProviderException;
}
