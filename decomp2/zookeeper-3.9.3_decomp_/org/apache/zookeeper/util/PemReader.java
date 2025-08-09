package org.apache.zookeeper.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.x500.X500Principal;

public final class PemReader {
   private static final Pattern CERT_PATTERN = Pattern.compile("-+BEGIN\\s+.*CERTIFICATE[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*CERTIFICATE[^-]*-+", 2);
   private static final Pattern PRIVATE_KEY_PATTERN = Pattern.compile("-+BEGIN\\s+.*PRIVATE\\s+KEY[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*PRIVATE\\s+KEY[^-]*-+", 2);
   private static final Pattern PUBLIC_KEY_PATTERN = Pattern.compile("-+BEGIN\\s+.*PUBLIC\\s+KEY[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*PUBLIC\\s+KEY[^-]*-+", 2);

   private PemReader() {
   }

   public static KeyStore loadTrustStore(File certificateChainFile) throws IOException, GeneralSecurityException {
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load((InputStream)null, (char[])null);

      for(X509Certificate certificate : readCertificateChain(certificateChainFile)) {
         X500Principal principal = certificate.getSubjectX500Principal();
         keyStore.setCertificateEntry(principal.getName("RFC2253"), certificate);
      }

      return keyStore;
   }

   public static KeyStore loadKeyStore(File certificateChainFile, File privateKeyFile, Optional keyPassword) throws IOException, GeneralSecurityException {
      PrivateKey key = loadPrivateKey(privateKeyFile, keyPassword);
      List<X509Certificate> certificateChain = readCertificateChain(certificateChainFile);
      if (certificateChain.isEmpty()) {
         throw new CertificateException("Certificate file does not contain any certificates: " + certificateChainFile);
      } else {
         KeyStore keyStore = KeyStore.getInstance("JKS");
         keyStore.load((InputStream)null, (char[])null);
         keyStore.setKeyEntry("key", key, ((String)keyPassword.orElse("")).toCharArray(), (Certificate[])certificateChain.toArray(new Certificate[0]));
         return keyStore;
      }
   }

   public static List readCertificateChain(File certificateChainFile) throws IOException, GeneralSecurityException {
      String contents = new String(Files.readAllBytes(certificateChainFile.toPath()), StandardCharsets.US_ASCII);
      return readCertificateChain(contents);
   }

   public static List readCertificateChain(String certificateChain) throws CertificateException {
      Matcher matcher = CERT_PATTERN.matcher(certificateChain);
      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
      List<X509Certificate> certificates = new ArrayList();

      for(int start = 0; matcher.find(start); start = matcher.end()) {
         byte[] buffer = base64Decode(matcher.group(1));
         certificates.add((X509Certificate)certificateFactory.generateCertificate(new ByteArrayInputStream(buffer)));
      }

      return certificates;
   }

   public static PrivateKey loadPrivateKey(File privateKeyFile, Optional keyPassword) throws IOException, GeneralSecurityException {
      String privateKey = new String(Files.readAllBytes(privateKeyFile.toPath()), StandardCharsets.US_ASCII);
      return loadPrivateKey(privateKey, keyPassword);
   }

   public static PrivateKey loadPrivateKey(String privateKey, Optional keyPassword) throws IOException, GeneralSecurityException {
      Matcher matcher = PRIVATE_KEY_PATTERN.matcher(privateKey);
      if (!matcher.find()) {
         throw new KeyStoreException("did not find a private key");
      } else {
         byte[] encodedKey = base64Decode(matcher.group(1));
         PKCS8EncodedKeySpec encodedKeySpec;
         if (keyPassword.isPresent()) {
            EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(encodedKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encryptedPrivateKeyInfo.getAlgName());
            SecretKey secretKey = keyFactory.generateSecret(new PBEKeySpec(((String)keyPassword.get()).toCharArray()));
            Cipher cipher = Cipher.getInstance(encryptedPrivateKeyInfo.getAlgName());
            cipher.init(2, secretKey, encryptedPrivateKeyInfo.getAlgParameters());
            encodedKeySpec = encryptedPrivateKeyInfo.getKeySpec(cipher);
         } else {
            encodedKeySpec = new PKCS8EncodedKeySpec(encodedKey);
         }

         try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(encodedKeySpec);
         } catch (InvalidKeySpecException var10) {
            try {
               KeyFactory keyFactory = KeyFactory.getInstance("EC");
               return keyFactory.generatePrivate(encodedKeySpec);
            } catch (InvalidKeySpecException var9) {
               KeyFactory keyFactory = KeyFactory.getInstance("DSA");
               return keyFactory.generatePrivate(encodedKeySpec);
            }
         }
      }
   }

   public static PublicKey loadPublicKey(File publicKeyFile) throws IOException, GeneralSecurityException {
      String publicKey = new String(Files.readAllBytes(publicKeyFile.toPath()), StandardCharsets.US_ASCII);
      return loadPublicKey(publicKey);
   }

   public static PublicKey loadPublicKey(String publicKey) throws GeneralSecurityException {
      Matcher matcher = PUBLIC_KEY_PATTERN.matcher(publicKey);
      if (!matcher.find()) {
         throw new KeyStoreException("did not find a public key");
      } else {
         String data = matcher.group(1);
         byte[] encodedKey = base64Decode(data);
         X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);

         try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(encodedKeySpec);
         } catch (InvalidKeySpecException var7) {
            try {
               KeyFactory keyFactory = KeyFactory.getInstance("EC");
               return keyFactory.generatePublic(encodedKeySpec);
            } catch (InvalidKeySpecException var6) {
               KeyFactory keyFactory = KeyFactory.getInstance("DSA");
               return keyFactory.generatePublic(encodedKeySpec);
            }
         }
      }
   }

   private static byte[] base64Decode(String base64) {
      return Base64.getMimeDecoder().decode(base64.getBytes(StandardCharsets.US_ASCII));
   }
}
