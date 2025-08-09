package io.vertx.core.net.impl;

import io.netty.util.internal.PlatformDependent;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.pkcs1.PrivateKeyParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;

public class KeyStoreHelper {
   public static final String DUMMY_PASSWORD = "dummdummydummydummydummydummydummy";
   private static final String DUMMY_CERT_ALIAS = "cert-";
   private static final Pattern BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
   private static final Pattern END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
   private final String password;
   private final KeyStore store;
   private final String aliasPassword;
   private final Map wildcardMgrMap = new HashMap();
   private final Map mgrMap = new HashMap();
   private final Map wildcardMgrFactoryMap = new HashMap();
   private final Map mgrFactoryMap = new HashMap();
   private final Map trustMgrMap = new HashMap();

   public KeyStoreHelper(KeyStore ks, String password, String aliasPassword) throws Exception {
      Enumeration<String> en = ks.aliases();

      while(en.hasMoreElements()) {
         String alias = (String)en.nextElement();
         Certificate cert = ks.getCertificate(alias);
         if (ks.isCertificateEntry(alias) && !alias.startsWith("cert-")) {
            KeyStore keyStore = createEmptyKeyStore();
            keyStore.setCertificateEntry("cert-1", cert);
            TrustManagerFactory fact = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            fact.init(keyStore);
            this.trustMgrMap.put(alias, fact);
         }

         if (ks.isKeyEntry(alias) && cert instanceof X509Certificate) {
            X509Certificate x509Cert = (X509Certificate)cert;
            Collection<List<?>> ans = x509Cert.getSubjectAlternativeNames();
            List<String> domains = new ArrayList();
            if (ans != null) {
               for(List l : ans) {
                  if (l.size() == 2 && l.get(0) instanceof Number && ((Number)l.get(0)).intValue() == 2) {
                     String dns = l.get(1).toString();
                     domains.add(dns);
                  }
               }
            }

            String dn = x509Cert.getSubjectX500Principal().getName();
            domains.addAll(getX509CertificateCommonNames(dn));
            if (!domains.isEmpty()) {
               char[] keyPassword = this.keyPassword(aliasPassword, password);
               final PrivateKey key = (PrivateKey)ks.getKey(alias, keyPassword);
               final Certificate[] tmp = ks.getCertificateChain(alias);
               if (tmp != null) {
                  X509KeyManager mgr = new X509KeyManager() {
                     public String[] getClientAliases(String s, Principal[] principals) {
                        throw new UnsupportedOperationException();
                     }

                     public String chooseClientAlias(String[] strings, Principal[] principals, Socket socket) {
                        throw new UnsupportedOperationException();
                     }

                     public String[] getServerAliases(String s, Principal[] principals) {
                        throw new UnsupportedOperationException();
                     }

                     public String chooseServerAlias(String s, Principal[] principals, Socket socket) {
                        throw new UnsupportedOperationException();
                     }

                     public X509Certificate[] getCertificateChain(String s) {
                        Stream var10000 = Arrays.stream(tmp);
                        X509Certificate.class.getClass();
                        return (X509Certificate[])var10000.map(X509Certificate.class::cast).toArray((x$0) -> new X509Certificate[x$0]);
                     }

                     public PrivateKey getPrivateKey(String s) {
                        return key;
                     }
                  };
                  KeyManagerFactory kmf = toKeyManagerFactory(mgr);

                  for(String domain : domains) {
                     if (domain.startsWith("*.")) {
                        this.wildcardMgrMap.put(domain.substring(2), mgr);
                        this.wildcardMgrFactoryMap.put(domain.substring(2), kmf);
                     } else {
                        this.mgrMap.put(domain, mgr);
                        this.mgrFactoryMap.put(domain, kmf);
                     }
                  }
               }
            }
         }
      }

      this.store = ks;
      this.password = password;
      this.aliasPassword = aliasPassword;
   }

   public static KeyManagerFactory toKeyManagerFactory(X509KeyManager mgr) throws Exception {
      String keyStoreType = KeyStore.getDefaultType();
      KeyStore ks = KeyStore.getInstance(keyStoreType);
      ks.load((InputStream)null, (char[])null);
      ks.setKeyEntry("key", mgr.getPrivateKey((String)null), "dummdummydummydummydummydummydummy".toCharArray(), mgr.getCertificateChain((String)null));
      String keyAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
      KeyManagerFactory kmf = KeyManagerFactory.getInstance(keyAlgorithm);
      kmf.init(ks, "dummdummydummydummydummydummydummy".toCharArray());
      return kmf;
   }

   public KeyManagerFactory getKeyMgrFactory() throws Exception {
      KeyManagerFactory fact = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      char[] keyPassword = this.keyPassword(this.aliasPassword, this.password);
      fact.init(this.store, keyPassword);
      return fact;
   }

   private char[] keyPassword(String aliasPassword, String password) {
      if (aliasPassword != null) {
         return aliasPassword.toCharArray();
      } else {
         return password != null ? password.toCharArray() : null;
      }
   }

   public X509KeyManager getKeyMgr(String serverName) {
      X509KeyManager mgr = (X509KeyManager)this.mgrMap.get(serverName);
      if (mgr == null && !this.wildcardMgrMap.isEmpty()) {
         int index = serverName.indexOf(46) + 1;
         if (index > 0) {
            String s = serverName.substring(index);
            mgr = (X509KeyManager)this.wildcardMgrMap.get(s);
         }
      }

      return mgr;
   }

   public KeyManagerFactory getKeyMgrFactory(String serverName) {
      KeyManagerFactory mgr = (KeyManagerFactory)this.mgrFactoryMap.get(serverName);
      if (mgr == null && !this.wildcardMgrMap.isEmpty()) {
         int index = serverName.indexOf(46) + 1;
         if (index > 0) {
            String s = serverName.substring(index);
            mgr = (KeyManagerFactory)this.wildcardMgrFactoryMap.get(s);
         }
      }

      return mgr;
   }

   public KeyManager[] getKeyMgr() throws Exception {
      return this.getKeyMgrFactory().getKeyManagers();
   }

   public TrustManager[] getTrustMgr(String serverName) {
      TrustManagerFactory fact = (TrustManagerFactory)this.trustMgrMap.get(serverName);
      return fact != null ? fact.getTrustManagers() : null;
   }

   public TrustManagerFactory getTrustMgrFactory(VertxInternal vertx) throws Exception {
      TrustManagerFactory fact = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      fact.init(this.store);
      return fact;
   }

   public TrustManager[] getTrustMgrs(VertxInternal vertx) throws Exception {
      return this.getTrustMgrFactory(vertx).getTrustManagers();
   }

   public KeyStore store() {
      return this.store;
   }

   public static List getX509CertificateCommonNames(String dn) throws Exception {
      List<String> names = new ArrayList();
      if (!PlatformDependent.isAndroid()) {
         LdapName ldapDN = new LdapName(dn);

         for(Rdn rdn : ldapDN.getRdns()) {
            if (rdn.getType().equalsIgnoreCase("cn")) {
               String name = rdn.getValue().toString();
               names.add(name);
            }
         }
      } else {
         String[] rdns = dn.trim().split("[,;]");

         for(String rdn : rdns) {
            String[] nvp = rdn.trim().split("=");
            if (nvp.length == 2 && "cn".equalsIgnoreCase(nvp[0])) {
               names.add(nvp[1]);
            }
         }
      }

      return names;
   }

   public static KeyStore loadKeyStore(String type, String provider, String password, Supplier value, String alias) throws Exception {
      Objects.requireNonNull(type);
      KeyStore ks = provider == null ? KeyStore.getInstance(type) : KeyStore.getInstance(type, provider);
      Buffer keystoreBuffer = (Buffer)value.get();
      if (keystoreBuffer == null) {
         ks.load((InputStream)null, password != null ? password.toCharArray() : null);
      } else {
         InputStream in = new ByteArrayInputStream(((Buffer)value.get()).getBytes());
         Throwable var8 = null;

         try {
            ks.load(in, password != null ? password.toCharArray() : null);
         } catch (Throwable var17) {
            var8 = var17;
            throw var17;
         } finally {
            if (in != null) {
               if (var8 != null) {
                  try {
                     in.close();
                  } catch (Throwable var16) {
                     var8.addSuppressed(var16);
                  }
               } else {
                  in.close();
               }
            }

         }
      }

      if (alias != null) {
         if (!ks.containsAlias(alias)) {
            throw new IllegalArgumentException("alias does not exist in the keystore: " + alias);
         }

         for(String ksAlias : Collections.list(ks.aliases())) {
            if (!alias.equals(ksAlias)) {
               ks.deleteEntry(ksAlias);
            }
         }
      }

      return ks;
   }

   public static KeyStore loadKeyCert(List keyValue, List certValue) throws Exception {
      if (keyValue.size() < certValue.size()) {
         throw new VertxException("Missing private key");
      } else if (keyValue.size() > certValue.size()) {
         throw new VertxException("Missing X.509 certificate");
      } else {
         KeyStore keyStore = createEmptyKeyStore();
         Iterator<Buffer> keyValueIt = keyValue.iterator();
         Iterator<Buffer> certValueIt = certValue.iterator();
         int index = 0;

         while(keyValueIt.hasNext() && certValueIt.hasNext()) {
            PrivateKey key = loadPrivateKey((Buffer)keyValueIt.next());
            Certificate[] chain = loadCerts((Buffer)certValueIt.next());
            keyStore.setEntry("dummy-entry-" + index++, new KeyStore.PrivateKeyEntry(key, chain), new KeyStore.PasswordProtection("dummdummydummydummydummydummydummy".toCharArray()));
         }

         return keyStore;
      }
   }

   private static PrivateKey loadPrivateKey(Buffer keyValue) throws Exception {
      if (keyValue == null) {
         throw new RuntimeException("Missing private key path");
      } else {
         KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA");
         KeyFactory ecKeyFactory = getECKeyFactory();
         List<PrivateKey> pems = loadPems(keyValue, (delimiter, content) -> {
            try {
               switch (delimiter) {
                  case "EC PRIVATE KEY":
                     if (ecKeyFactory == null) {
                        return Collections.emptyList();
                     }

                     return Collections.singletonList(ecKeyFactory.generatePrivate(PrivateKeyParser.getECKeySpec(content)));
                  case "RSA PRIVATE KEY":
                     return Collections.singletonList(rsaKeyFactory.generatePrivate(PrivateKeyParser.getRSAKeySpec(content)));
                  case "PRIVATE KEY":
                     String algorithm = PrivateKeyParser.getPKCS8EncodedKeyAlgorithm(content);
                     if (rsaKeyFactory.getAlgorithm().equals(algorithm)) {
                        return Collections.singletonList(rsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(content)));
                     } else if (ecKeyFactory != null && ecKeyFactory.getAlgorithm().equals(algorithm)) {
                        return Collections.singletonList(ecKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(content)));
                     }
                  default:
                     return Collections.emptyList();
               }
            } catch (InvalidKeySpecException e) {
               throw new VertxException(e);
            }
         });
         if (pems.isEmpty()) {
            throw new RuntimeException("Missing -----BEGIN PRIVATE KEY----- or -----BEGIN RSA PRIVATE KEY----- or -----BEGIN EC PRIVATE KEY----- delimiter");
         } else {
            return (PrivateKey)pems.get(0);
         }
      }
   }

   private static KeyFactory getECKeyFactory() {
      try {
         return KeyFactory.getInstance("EC");
      } catch (NoSuchAlgorithmException var1) {
         return null;
      }
   }

   public static KeyStore loadCA(Stream certValues) throws Exception {
      KeyStore keyStore = createEmptyKeyStore();
      keyStore.load((InputStream)null, (char[])null);
      int count = 0;

      for(Buffer certValue : certValues::iterator) {
         for(Certificate cert : loadCerts(certValue)) {
            keyStore.setCertificateEntry("cert-" + count++, cert);
         }
      }

      return keyStore;
   }

   private static List loadPems(Buffer data, BiFunction pemFact) throws IOException {
      String pem = data.toString();
      List<P> pems = new ArrayList();
      Matcher beginMatcher = BEGIN_PATTERN.matcher(pem);
      Matcher endMatcher = END_PATTERN.matcher(pem);

      while(true) {
         boolean begin = beginMatcher.find();
         if (!begin) {
            return pems;
         }

         String beginDelimiter = beginMatcher.group(1);
         boolean end = endMatcher.find();
         if (!end) {
            throw new RuntimeException("Missing -----END " + beginDelimiter + "----- delimiter");
         }

         String endDelimiter = endMatcher.group(1);
         if (!beginDelimiter.equals(endDelimiter)) {
            throw new RuntimeException("Missing -----END " + beginDelimiter + "----- delimiter");
         }

         String content = pem.substring(beginMatcher.end(), endMatcher.start());
         content = content.replaceAll("\\s", "");
         if (content.length() == 0) {
            throw new RuntimeException("Empty pem file");
         }

         Collection<P> pemItems = (Collection)pemFact.apply(endDelimiter, Base64.getDecoder().decode(content));
         pems.addAll(pemItems);
      }
   }

   private static X509Certificate[] loadCerts(Buffer buffer) throws Exception {
      if (buffer == null) {
         throw new RuntimeException("Missing X.509 certificate path");
      } else {
         CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
         List<X509Certificate> certs = loadPems(buffer, (delimiter, content) -> {
            try {
               switch (delimiter) {
                  case "CERTIFICATE":
                     return certFactory.generateCertificates(new ByteArrayInputStream(content));
                  default:
                     return Collections.emptyList();
               }
            } catch (CertificateException e) {
               throw new VertxException(e);
            }
         });
         if (certs.isEmpty()) {
            throw new RuntimeException("Missing -----BEGIN CERTIFICATE----- delimiter");
         } else {
            return (X509Certificate[])certs.toArray(new X509Certificate[0]);
         }
      }
   }

   private static KeyStore createEmptyKeyStore() throws KeyStoreException {
      String defaultKeyStoreType = KeyStore.getDefaultType();
      KeyStore keyStore;
      if (defaultKeyStoreType.equalsIgnoreCase("jks") && Security.getAlgorithms("KeyStore").contains("PKCS12")) {
         keyStore = KeyStore.getInstance("PKCS12");
      } else {
         keyStore = KeyStore.getInstance(defaultKeyStoreType);
      }

      try {
         keyStore.load((InputStream)null, (char[])null);
         return keyStore;
      } catch (NoSuchAlgorithmException | IOException | CertificateException e) {
         throw new KeyStoreException("Failed to initialize the keystore", e);
      }
   }
}
