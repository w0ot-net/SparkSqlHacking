package io.fabric8.kubernetes.client.internal;

import io.fabric8.kubernetes.client.utils.Utils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertUtils {
   private static final Logger LOG = LoggerFactory.getLogger(CertUtils.class);
   private static final String TRUST_STORE_SYSTEM_PROPERTY = "javax.net.ssl.trustStore";
   private static final String TRUST_STORE_PASSWORD_SYSTEM_PROPERTY = "javax.net.ssl.trustStorePassword";
   private static final String TRUST_STORE_TYPE_SYSTEM_PROPERTY = "javax.net.ssl.trustStoreType";
   private static final String KEY_STORE_SYSTEM_PROPERTY = "javax.net.ssl.keyStore";
   private static final String KEY_STORE_PASSWORD_SYSTEM_PROPERTY = "javax.net.ssl.keyStorePassword";
   private static final String KEY_STORE_DEFAULT_PASSWORD = "changeit";

   private CertUtils() {
   }

   public static ByteArrayInputStream getInputStreamFromDataOrFile(String data, String file) throws IOException {
      return data != null ? createInputStreamFromBase64EncodedString(data) : new ByteArrayInputStream((new String(Files.readAllBytes(Paths.get(file)))).trim().getBytes());
   }

   public static KeyStore createTrustStore(String caCertData, String caCertFile, String trustStoreFile, String trustStorePassphrase) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
      ByteArrayInputStream pemInputStream = getInputStreamFromDataOrFile(caCertData, caCertFile);
      KeyStore trustStore = loadTrustStore(trustStoreFile, getPassphrase("javax.net.ssl.trustStorePassword", trustStorePassphrase));
      return mergePemCertsIntoTrustStore(pemInputStream, trustStore, true);
   }

   static KeyStore loadTrustStore(String trustStoreFile, char[] trustStorePassphrase) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, FileNotFoundException {
      String trustStoreType = System.getProperty("javax.net.ssl.trustStoreType", KeyStore.getDefaultType());
      KeyStore trustStore = KeyStore.getInstance(trustStoreType);
      if (Utils.isNotNullOrEmpty(trustStoreFile)) {
         FileInputStream fis = new FileInputStream(trustStoreFile);

         try {
            trustStore.load(fis, trustStorePassphrase);
         } catch (Throwable var8) {
            try {
               fis.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         fis.close();
      } else {
         loadDefaultTrustStoreFile(trustStore, trustStorePassphrase);
      }

      return trustStore;
   }

   static KeyStore mergePemCertsIntoTrustStore(ByteArrayInputStream pemInputStream, KeyStore trustStore, boolean first) throws CertificateException, KeyStoreException {
      CertificateFactory certFactory = CertificateFactory.getInstance("X509");

      while(pemInputStream.available() > 0) {
         X509Certificate cert;
         try {
            cert = (X509Certificate)certFactory.generateCertificate(pemInputStream);
         } catch (CertificateException e) {
            if (pemInputStream.available() > 0) {
               throw e;
            }

            LOG.debug("The trailing entry generated a certificate exception.  More than likely the contents end with comments.", e);
            break;
         }

         try {
            String var10000 = cert.getSubjectX500Principal().getName();
            String alias = var10000 + "_" + cert.getSerialNumber().toString(16);
            trustStore.setCertificateEntry(alias, cert);
            first = false;
         } catch (KeyStoreException e) {
            if (first) {
               pemInputStream.reset();
               KeyStore writableStore = KeyStore.getInstance("PKCS12");

               try {
                  writableStore.load((InputStream)null, (char[])null);
               } catch (CertificateException | IOException | NoSuchAlgorithmException var9) {
                  throw e;
               }

               for(String alias : Collections.list(trustStore.aliases())) {
                  writableStore.setCertificateEntry(alias, trustStore.getCertificate(alias));
               }

               return mergePemCertsIntoTrustStore(pemInputStream, writableStore, false);
            }

            throw e;
         }
      }

      return trustStore;
   }

   public static KeyStore createKeyStore(InputStream certInputStream, InputStream keyInputStream, String clientKeyAlgo, char[] clientKeyPassphrase, String keyStoreFile, char[] keyStorePassphrase) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException, KeyStoreException {
      CertificateFactory certFactory = CertificateFactory.getInstance("X509");
      Collection<? extends Certificate> certificates = certFactory.generateCertificates(certInputStream);
      PrivateKey privateKey = loadKey(keyInputStream, clientKeyAlgo);
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      if (Utils.isNotNullOrEmpty(keyStoreFile)) {
         FileInputStream fis = new FileInputStream(keyStoreFile);

         try {
            keyStore.load(fis, keyStorePassphrase);
         } catch (Throwable var14) {
            try {
               fis.close();
            } catch (Throwable var13) {
               var14.addSuppressed(var13);
            }

            throw var14;
         }

         fis.close();
      } else {
         loadDefaultKeyStoreFile(keyStore, keyStorePassphrase);
      }

      String alias = (String)certificates.stream().map((cert) -> ((X509Certificate)cert).getIssuerX500Principal().getName()).collect(Collectors.joining("_"));
      keyStore.setKeyEntry(alias, privateKey, clientKeyPassphrase, (Certificate[])certificates.toArray(new Certificate[0]));
      return keyStore;
   }

   private static PrivateKey loadKey(InputStream keyInputStream, String clientKeyAlgo) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
      if (clientKeyAlgo == null) {
         clientKeyAlgo = "RSA";
      }

      byte[] keyBytes = decodePem(keyInputStream);
      if (!clientKeyAlgo.equals("EC") && !clientKeyAlgo.equals("RSA")) {
         throw new InvalidKeySpecException("Unknown type of PKCS8 Private Key, tried RSA and ECDSA");
      } else {
         try {
            return handleOtherKeys(keyBytes, clientKeyAlgo);
         } catch (IOException e) {
            if (clientKeyAlgo.equals("EC")) {
               return handleECKey(keyBytes);
            } else {
               throw e;
            }
         }
      }
   }

   private static PrivateKey handleECKey(byte[] keyBytes) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
      return KeyFactory.getInstance("EC").generatePrivate(PKCS1Util.getECKeySpec(keyBytes));
   }

   private static PrivateKey handleOtherKeys(byte[] keyBytes, String clientKeyAlgo) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
      try {
         return KeyFactory.getInstance(clientKeyAlgo).generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
      } catch (InvalidKeySpecException var4) {
         RSAPrivateCrtKeySpec keySpec = PKCS1Util.decodePKCS1(keyBytes);
         return KeyFactory.getInstance(clientKeyAlgo).generatePrivate(keySpec);
      }
   }

   private static void loadDefaultTrustStoreFile(KeyStore keyStore, char[] trustStorePassphrase) throws CertificateException, NoSuchAlgorithmException, IOException {
      File trustStoreFile = getDefaultTrustStoreFile();
      if (!loadDefaultStoreFile(keyStore, trustStoreFile, trustStorePassphrase)) {
         keyStore.load((KeyStore.LoadStoreParameter)null);
      }

   }

   private static File getDefaultTrustStoreFile() {
      String var10000 = System.getProperty("java.home");
      String securityDirectory = var10000 + File.separator + "lib" + File.separator + "security" + File.separator;
      String trustStorePath = System.getProperty("javax.net.ssl.trustStore");
      if (Utils.isNotNullOrEmpty(trustStorePath)) {
         return new File(trustStorePath);
      } else {
         File jssecacertsFile = new File(securityDirectory + "jssecacerts");
         return jssecacertsFile.exists() && jssecacertsFile.isFile() ? jssecacertsFile : new File(securityDirectory + "cacerts");
      }
   }

   private static void loadDefaultKeyStoreFile(KeyStore keyStore, char[] keyStorePassphrase) throws CertificateException, NoSuchAlgorithmException, IOException {
      String keyStorePath = System.getProperty("javax.net.ssl.keyStore");
      if (Utils.isNotNullOrEmpty(keyStorePath)) {
         File keyStoreFile = new File(keyStorePath);
         if (loadDefaultStoreFile(keyStore, keyStoreFile, keyStorePassphrase)) {
            return;
         }
      }

      keyStore.load((KeyStore.LoadStoreParameter)null);
   }

   private static boolean loadDefaultStoreFile(KeyStore keyStore, File fileToLoad, char[] passphrase) {
      if (fileToLoad.exists() && fileToLoad.isFile() && fileToLoad.length() != 0L) {
         Exception ex = null;

         try {
            FileInputStream fis = new FileInputStream(fileToLoad);

            boolean var14;
            try {
               keyStore.load(fis, passphrase);
               var14 = true;
            } catch (Throwable var10) {
               try {
                  fis.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }

               throw var10;
            }

            fis.close();
            return var14;
         } catch (Exception e) {
            if ((passphrase == null || passphrase.length == 0) && e.getCause() instanceof UnrecoverableKeyException) {
               try {
                  FileInputStream fis1 = new FileInputStream(fileToLoad);

                  boolean var5;
                  try {
                     keyStore.load(fis1, "changeit".toCharArray());
                     var5 = true;
                  } catch (Throwable var8) {
                     try {
                        fis1.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }

                     throw var8;
                  }

                  fis1.close();
                  return var5;
               } catch (Exception var11) {
               }
            }

            LOG.info("There is a problem with reading default keystore/truststore file {} - the file won't be loaded. The reason is: {}", fileToLoad, e.getMessage());
            return false;
         }
      } else {
         return false;
      }
   }

   public static KeyStore createKeyStore(String clientCertData, String clientCertFile, String clientKeyData, String clientKeyFile, String clientKeyAlgo, String clientKeyPassphrase, String keyStoreFile, String keyStorePassphrase) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException, KeyStoreException {
      ByteArrayInputStream certInputStream = getInputStreamFromDataOrFile(clientCertData, clientCertFile);
      ByteArrayInputStream keyInputStream = getInputStreamFromDataOrFile(clientKeyData, clientKeyFile);
      return createKeyStore(certInputStream, keyInputStream, clientKeyAlgo, clientKeyPassphrase.toCharArray(), keyStoreFile, getPassphrase("javax.net.ssl.keyStorePassword", keyStorePassphrase));
   }

   private static char[] getPassphrase(String property, String passphrase) {
      if (Utils.isNullOrEmpty(passphrase)) {
         passphrase = System.getProperty(property, passphrase);
      }

      return passphrase != null ? passphrase.toCharArray() : null;
   }

   private static byte[] decodePem(InputStream keyInputStream) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(keyInputStream));

      byte[] var3;
      try {
         while(true) {
            String line;
            if ((line = reader.readLine()) == null) {
               throw new IOException("PEM is invalid: no begin marker");
            }

            if (line.contains("-----BEGIN ")) {
               var3 = readBytes(reader, line.trim().replace("BEGIN", "END"));
               break;
            }
         }
      } catch (Throwable var5) {
         try {
            reader.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      reader.close();
      return var3;
   }

   private static byte[] readBytes(BufferedReader reader, String endMarker) throws IOException {
      StringBuffer buf = new StringBuffer();

      String line;
      while((line = reader.readLine()) != null) {
         if (line.indexOf(endMarker) != -1) {
            return Base64.getDecoder().decode(buf.toString());
         }

         buf.append(line.trim());
      }

      throw new IOException("PEM is invalid : No end marker");
   }

   private static ByteArrayInputStream createInputStreamFromBase64EncodedString(String data) {
      byte[] bytes;
      try {
         bytes = Base64.getDecoder().decode(data);
      } catch (IllegalArgumentException var3) {
         bytes = data.getBytes();
      }

      return new ByteArrayInputStream(bytes);
   }
}
