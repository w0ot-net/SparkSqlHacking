package io.netty.handler.ssl;

import [Ljava.lang.String;;
import [Ljava.security.cert.X509Certificate;;
import io.netty.handler.ssl.util.KeyManagerFactoryWrapper;
import io.netty.handler.ssl.util.TrustManagerFactoryWrapper;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public final class SslContextBuilder {
   private static final Map.Entry[] EMPTY_ENTRIES = new Map.Entry[0];
   private final boolean forServer;
   private SslProvider provider;
   private Provider sslContextProvider;
   private X509Certificate[] trustCertCollection;
   private TrustManagerFactory trustManagerFactory;
   private X509Certificate[] keyCertChain;
   private PrivateKey key;
   private String keyPassword;
   private KeyManagerFactory keyManagerFactory;
   private Iterable ciphers;
   private CipherSuiteFilter cipherFilter;
   private ApplicationProtocolConfig apn;
   private long sessionCacheSize;
   private long sessionTimeout;
   private ClientAuth clientAuth;
   private String[] protocols;
   private boolean startTls;
   private boolean enableOcsp;
   private SecureRandom secureRandom;
   private String keyStoreType;
   private String endpointIdentificationAlgorithm;
   private final Map options;

   public static SslContextBuilder forClient() {
      return new SslContextBuilder(false);
   }

   public static SslContextBuilder forServer(File keyCertChainFile, File keyFile) {
      return (new SslContextBuilder(true)).keyManager(keyCertChainFile, keyFile);
   }

   public static SslContextBuilder forServer(InputStream keyCertChainInputStream, InputStream keyInputStream) {
      return (new SslContextBuilder(true)).keyManager(keyCertChainInputStream, keyInputStream);
   }

   public static SslContextBuilder forServer(PrivateKey key, X509Certificate... keyCertChain) {
      return (new SslContextBuilder(true)).keyManager(key, keyCertChain);
   }

   public static SslContextBuilder forServer(PrivateKey key, Iterable keyCertChain) {
      return forServer(key, (X509Certificate[])toArray(keyCertChain, EmptyArrays.EMPTY_X509_CERTIFICATES));
   }

   public static SslContextBuilder forServer(File keyCertChainFile, File keyFile, String keyPassword) {
      return (new SslContextBuilder(true)).keyManager(keyCertChainFile, keyFile, keyPassword);
   }

   public static SslContextBuilder forServer(InputStream keyCertChainInputStream, InputStream keyInputStream, String keyPassword) {
      return (new SslContextBuilder(true)).keyManager(keyCertChainInputStream, keyInputStream, keyPassword);
   }

   public static SslContextBuilder forServer(PrivateKey key, String keyPassword, X509Certificate... keyCertChain) {
      return (new SslContextBuilder(true)).keyManager(key, keyPassword, keyCertChain);
   }

   public static SslContextBuilder forServer(PrivateKey key, String keyPassword, Iterable keyCertChain) {
      return forServer(key, keyPassword, (X509Certificate[])toArray(keyCertChain, EmptyArrays.EMPTY_X509_CERTIFICATES));
   }

   public static SslContextBuilder forServer(KeyManagerFactory keyManagerFactory) {
      return (new SslContextBuilder(true)).keyManager(keyManagerFactory);
   }

   public static SslContextBuilder forServer(KeyManager keyManager) {
      return (new SslContextBuilder(true)).keyManager(keyManager);
   }

   private SslContextBuilder(boolean forServer) {
      this.cipherFilter = IdentityCipherSuiteFilter.INSTANCE;
      this.clientAuth = ClientAuth.NONE;
      this.keyStoreType = KeyStore.getDefaultType();
      this.options = new HashMap();
      this.forServer = forServer;
   }

   public SslContextBuilder option(SslContextOption option, Object value) {
      if (value == null) {
         this.options.remove(option);
      } else {
         this.options.put(option, value);
      }

      return this;
   }

   public SslContextBuilder sslProvider(SslProvider provider) {
      this.provider = provider;
      return this;
   }

   public SslContextBuilder keyStoreType(String keyStoreType) {
      this.keyStoreType = keyStoreType;
      return this;
   }

   public SslContextBuilder sslContextProvider(Provider sslContextProvider) {
      this.sslContextProvider = sslContextProvider;
      return this;
   }

   public SslContextBuilder trustManager(File trustCertCollectionFile) {
      try {
         return this.trustManager(SslContext.toX509Certificates(trustCertCollectionFile));
      } catch (Exception e) {
         throw new IllegalArgumentException("File does not contain valid certificates: " + trustCertCollectionFile, e);
      }
   }

   public SslContextBuilder trustManager(InputStream trustCertCollectionInputStream) {
      try {
         return this.trustManager(SslContext.toX509Certificates(trustCertCollectionInputStream));
      } catch (Exception e) {
         throw new IllegalArgumentException("Input stream does not contain valid certificates.", e);
      }
   }

   public SslContextBuilder trustManager(X509Certificate... trustCertCollection) {
      this.trustCertCollection = trustCertCollection != null ? (X509Certificate[])((X509Certificate;)trustCertCollection).clone() : null;
      this.trustManagerFactory = null;
      return this;
   }

   public SslContextBuilder trustManager(Iterable trustCertCollection) {
      return this.trustManager((X509Certificate[])toArray(trustCertCollection, EmptyArrays.EMPTY_X509_CERTIFICATES));
   }

   public SslContextBuilder trustManager(TrustManagerFactory trustManagerFactory) {
      this.trustCertCollection = null;
      this.trustManagerFactory = trustManagerFactory;
      return this;
   }

   public SslContextBuilder trustManager(TrustManager trustManager) {
      if (trustManager != null) {
         this.trustManagerFactory = new TrustManagerFactoryWrapper(trustManager);
      } else {
         this.trustManagerFactory = null;
      }

      this.trustCertCollection = null;
      return this;
   }

   public SslContextBuilder keyManager(File keyCertChainFile, File keyFile) {
      return this.keyManager((File)keyCertChainFile, (File)keyFile, (String)null);
   }

   public SslContextBuilder keyManager(InputStream keyCertChainInputStream, InputStream keyInputStream) {
      return this.keyManager((InputStream)keyCertChainInputStream, (InputStream)keyInputStream, (String)null);
   }

   public SslContextBuilder keyManager(PrivateKey key, X509Certificate... keyCertChain) {
      return this.keyManager((PrivateKey)key, (String)null, (X509Certificate[])keyCertChain);
   }

   public SslContextBuilder keyManager(PrivateKey key, Iterable keyCertChain) {
      return this.keyManager(key, (X509Certificate[])toArray(keyCertChain, EmptyArrays.EMPTY_X509_CERTIFICATES));
   }

   public SslContextBuilder keyManager(File keyCertChainFile, File keyFile, String keyPassword) {
      X509Certificate[] keyCertChain;
      try {
         keyCertChain = SslContext.toX509Certificates(keyCertChainFile);
      } catch (Exception e) {
         throw new IllegalArgumentException("File does not contain valid certificates: " + keyCertChainFile, e);
      }

      PrivateKey key;
      try {
         key = SslContext.toPrivateKey(keyFile, keyPassword);
      } catch (Exception e) {
         throw new IllegalArgumentException("File does not contain valid private key: " + keyFile, e);
      }

      return this.keyManager(key, keyPassword, keyCertChain);
   }

   public SslContextBuilder keyManager(InputStream keyCertChainInputStream, InputStream keyInputStream, String keyPassword) {
      X509Certificate[] keyCertChain;
      try {
         keyCertChain = SslContext.toX509Certificates(keyCertChainInputStream);
      } catch (Exception e) {
         throw new IllegalArgumentException("Input stream not contain valid certificates.", e);
      }

      PrivateKey key;
      try {
         key = SslContext.toPrivateKey(keyInputStream, keyPassword);
      } catch (Exception e) {
         throw new IllegalArgumentException("Input stream does not contain valid private key.", e);
      }

      return this.keyManager(key, keyPassword, keyCertChain);
   }

   public SslContextBuilder keyManager(PrivateKey key, String keyPassword, X509Certificate... keyCertChain) {
      if (this.forServer) {
         ObjectUtil.checkNonEmpty(keyCertChain, "keyCertChain");
         ObjectUtil.checkNotNull(key, "key required for servers");
      }

      if (keyCertChain != null && keyCertChain.length != 0) {
         for(X509Certificate cert : keyCertChain) {
            ObjectUtil.checkNotNullWithIAE(cert, "cert");
         }

         this.keyCertChain = (X509Certificate[])((X509Certificate;)keyCertChain).clone();
      } else {
         this.keyCertChain = null;
      }

      this.key = key;
      this.keyPassword = keyPassword;
      this.keyManagerFactory = null;
      return this;
   }

   public SslContextBuilder keyManager(PrivateKey key, String keyPassword, Iterable keyCertChain) {
      return this.keyManager(key, keyPassword, (X509Certificate[])toArray(keyCertChain, EmptyArrays.EMPTY_X509_CERTIFICATES));
   }

   public SslContextBuilder keyManager(KeyManagerFactory keyManagerFactory) {
      if (this.forServer) {
         ObjectUtil.checkNotNull(keyManagerFactory, "keyManagerFactory required for servers");
      }

      this.keyCertChain = null;
      this.key = null;
      this.keyPassword = null;
      this.keyManagerFactory = keyManagerFactory;
      return this;
   }

   public SslContextBuilder keyManager(KeyManager keyManager) {
      if (this.forServer) {
         ObjectUtil.checkNotNull(keyManager, "keyManager required for servers");
      }

      if (keyManager != null) {
         this.keyManagerFactory = new KeyManagerFactoryWrapper(keyManager);
      } else {
         this.keyManagerFactory = null;
      }

      this.keyCertChain = null;
      this.key = null;
      this.keyPassword = null;
      return this;
   }

   public SslContextBuilder ciphers(Iterable ciphers) {
      return this.ciphers(ciphers, IdentityCipherSuiteFilter.INSTANCE);
   }

   public SslContextBuilder ciphers(Iterable ciphers, CipherSuiteFilter cipherFilter) {
      this.cipherFilter = (CipherSuiteFilter)ObjectUtil.checkNotNull(cipherFilter, "cipherFilter");
      this.ciphers = ciphers;
      return this;
   }

   public SslContextBuilder applicationProtocolConfig(ApplicationProtocolConfig apn) {
      this.apn = apn;
      return this;
   }

   public SslContextBuilder sessionCacheSize(long sessionCacheSize) {
      this.sessionCacheSize = sessionCacheSize;
      return this;
   }

   public SslContextBuilder sessionTimeout(long sessionTimeout) {
      this.sessionTimeout = sessionTimeout;
      return this;
   }

   public SslContextBuilder clientAuth(ClientAuth clientAuth) {
      this.clientAuth = (ClientAuth)ObjectUtil.checkNotNull(clientAuth, "clientAuth");
      return this;
   }

   public SslContextBuilder protocols(String... protocols) {
      this.protocols = protocols == null ? null : (String[])((String;)protocols).clone();
      return this;
   }

   public SslContextBuilder protocols(Iterable protocols) {
      return this.protocols((String[])toArray(protocols, EmptyArrays.EMPTY_STRINGS));
   }

   public SslContextBuilder startTls(boolean startTls) {
      this.startTls = startTls;
      return this;
   }

   public SslContextBuilder enableOcsp(boolean enableOcsp) {
      this.enableOcsp = enableOcsp;
      return this;
   }

   public SslContextBuilder secureRandom(SecureRandom secureRandom) {
      this.secureRandom = secureRandom;
      return this;
   }

   public SslContextBuilder endpointIdentificationAlgorithm(String algorithm) {
      this.endpointIdentificationAlgorithm = algorithm;
      return this;
   }

   public SslContext build() throws SSLException {
      return this.forServer ? SslContext.newServerContextInternal(this.provider, this.sslContextProvider, this.trustCertCollection, this.trustManagerFactory, this.keyCertChain, this.key, this.keyPassword, this.keyManagerFactory, this.ciphers, this.cipherFilter, this.apn, this.sessionCacheSize, this.sessionTimeout, this.clientAuth, this.protocols, this.startTls, this.enableOcsp, this.secureRandom, this.keyStoreType, (Map.Entry[])toArray(this.options.entrySet(), EMPTY_ENTRIES)) : SslContext.newClientContextInternal(this.provider, this.sslContextProvider, this.trustCertCollection, this.trustManagerFactory, this.keyCertChain, this.key, this.keyPassword, this.keyManagerFactory, this.ciphers, this.cipherFilter, this.apn, this.protocols, this.sessionCacheSize, this.sessionTimeout, this.enableOcsp, this.secureRandom, this.keyStoreType, this.endpointIdentificationAlgorithm, (Map.Entry[])toArray(this.options.entrySet(), EMPTY_ENTRIES));
   }

   private static Object[] toArray(Iterable iterable, Object[] prototype) {
      if (iterable == null) {
         return null;
      } else {
         List<T> list = new ArrayList();

         for(Object element : iterable) {
            list.add(element);
         }

         return list.toArray(prototype);
      }
   }
}
