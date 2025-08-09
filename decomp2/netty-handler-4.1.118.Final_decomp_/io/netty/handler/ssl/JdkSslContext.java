package io.netty.handler.ssl;

import [Ljava.lang.String;;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;

public class JdkSslContext extends SslContext {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(JdkSslContext.class);
   static final String PROTOCOL = "TLS";
   private static final String[] DEFAULT_PROTOCOLS;
   private static final List DEFAULT_CIPHERS;
   private static final List DEFAULT_CIPHERS_NON_TLSV13;
   private static final Set SUPPORTED_CIPHERS;
   private static final Set SUPPORTED_CIPHERS_NON_TLSV13;
   private static final Provider DEFAULT_PROVIDER;
   private final String[] protocols;
   private final String[] cipherSuites;
   private final List unmodifiableCipherSuites;
   private final JdkApplicationProtocolNegotiator apn;
   private final ClientAuth clientAuth;
   private final SSLContext sslContext;
   private final boolean isClient;
   private final String endpointIdentificationAlgorithm;

   private static String[] defaultProtocols(SSLContext context, SSLEngine engine) {
      String[] supportedProtocols = context.getDefaultSSLParameters().getProtocols();
      Set<String> supportedProtocolsSet = new HashSet(supportedProtocols.length);
      Collections.addAll(supportedProtocolsSet, supportedProtocols);
      List<String> protocols = new ArrayList();
      SslUtils.addIfSupported(supportedProtocolsSet, protocols, "TLSv1.3", "TLSv1.2", "TLSv1.1", "TLSv1");
      return !protocols.isEmpty() ? (String[])protocols.toArray(EmptyArrays.EMPTY_STRINGS) : engine.getEnabledProtocols();
   }

   private static Set supportedCiphers(SSLEngine engine) {
      String[] supportedCiphers = engine.getSupportedCipherSuites();
      Set<String> supportedCiphersSet = new LinkedHashSet(supportedCiphers.length);

      for(int i = 0; i < supportedCiphers.length; ++i) {
         String supportedCipher = supportedCiphers[i];
         supportedCiphersSet.add(supportedCipher);
         if (supportedCipher.startsWith("SSL_")) {
            String tlsPrefixedCipherName = "TLS_" + supportedCipher.substring("SSL_".length());

            try {
               engine.setEnabledCipherSuites(new String[]{tlsPrefixedCipherName});
               supportedCiphersSet.add(tlsPrefixedCipherName);
            } catch (IllegalArgumentException var7) {
            }
         }
      }

      return supportedCiphersSet;
   }

   private static List defaultCiphers(SSLEngine engine, Set supportedCiphers) {
      List<String> ciphers = new ArrayList();
      SslUtils.addIfSupported(supportedCiphers, ciphers, SslUtils.DEFAULT_CIPHER_SUITES);
      SslUtils.useFallbackCiphersIfDefaultIsEmpty(ciphers, engine.getEnabledCipherSuites());
      return ciphers;
   }

   private static boolean isTlsV13Supported(String[] protocols) {
      for(String protocol : protocols) {
         if ("TLSv1.3".equals(protocol)) {
            return true;
         }
      }

      return false;
   }

   /** @deprecated */
   @Deprecated
   public JdkSslContext(SSLContext sslContext, boolean isClient, ClientAuth clientAuth) {
      this(sslContext, isClient, (Iterable)null, IdentityCipherSuiteFilter.INSTANCE, (JdkApplicationProtocolNegotiator)JdkDefaultApplicationProtocolNegotiator.INSTANCE, clientAuth, (String[])null, false);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslContext(SSLContext sslContext, boolean isClient, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, ClientAuth clientAuth) {
      this(sslContext, isClient, ciphers, cipherFilter, (ApplicationProtocolConfig)apn, clientAuth, (String[])null, false);
   }

   public JdkSslContext(SSLContext sslContext, boolean isClient, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, ClientAuth clientAuth, String[] protocols, boolean startTls) {
      this(sslContext, isClient, ciphers, cipherFilter, toNegotiator(apn, !isClient), clientAuth, protocols == null ? null : (String[])((String;)protocols).clone(), startTls);
   }

   JdkSslContext(SSLContext sslContext, boolean isClient, Iterable ciphers, CipherSuiteFilter cipherFilter, JdkApplicationProtocolNegotiator apn, ClientAuth clientAuth, String[] protocols, boolean startTls) {
      this(sslContext, isClient, ciphers, cipherFilter, apn, clientAuth, protocols, startTls, (String)null, (ResumptionController)null);
   }

   JdkSslContext(SSLContext sslContext, boolean isClient, Iterable ciphers, CipherSuiteFilter cipherFilter, JdkApplicationProtocolNegotiator apn, ClientAuth clientAuth, String[] protocols, boolean startTls, String endpointIdentificationAlgorithm, ResumptionController resumptionController) {
      super(startTls, resumptionController);
      this.apn = (JdkApplicationProtocolNegotiator)ObjectUtil.checkNotNull(apn, "apn");
      this.clientAuth = (ClientAuth)ObjectUtil.checkNotNull(clientAuth, "clientAuth");
      this.sslContext = (SSLContext)ObjectUtil.checkNotNull(sslContext, "sslContext");
      this.endpointIdentificationAlgorithm = endpointIdentificationAlgorithm;
      List<String> defaultCiphers;
      Set<String> supportedCiphers;
      if (DEFAULT_PROVIDER.equals(sslContext.getProvider())) {
         this.protocols = protocols == null ? DEFAULT_PROTOCOLS : protocols;
         if (isTlsV13Supported(this.protocols)) {
            supportedCiphers = SUPPORTED_CIPHERS;
            defaultCiphers = DEFAULT_CIPHERS;
         } else {
            supportedCiphers = SUPPORTED_CIPHERS_NON_TLSV13;
            defaultCiphers = DEFAULT_CIPHERS_NON_TLSV13;
         }
      } else {
         SSLEngine engine = sslContext.createSSLEngine();

         try {
            if (protocols == null) {
               this.protocols = defaultProtocols(sslContext, engine);
            } else {
               this.protocols = protocols;
            }

            supportedCiphers = supportedCiphers(engine);
            defaultCiphers = defaultCiphers(engine, supportedCiphers);
            if (!isTlsV13Supported(this.protocols)) {
               for(String cipher : SslUtils.DEFAULT_TLSV13_CIPHER_SUITES) {
                  supportedCiphers.remove(cipher);
                  defaultCiphers.remove(cipher);
               }
            }
         } finally {
            ReferenceCountUtil.release(engine);
         }
      }

      this.cipherSuites = ((CipherSuiteFilter)ObjectUtil.checkNotNull(cipherFilter, "cipherFilter")).filterCipherSuites(ciphers, defaultCiphers, supportedCiphers);
      this.unmodifiableCipherSuites = Collections.unmodifiableList(Arrays.asList(this.cipherSuites));
      this.isClient = isClient;
   }

   public final SSLContext context() {
      return this.sslContext;
   }

   public final boolean isClient() {
      return this.isClient;
   }

   public final SSLSessionContext sessionContext() {
      return this.isServer() ? this.context().getServerSessionContext() : this.context().getClientSessionContext();
   }

   public final List cipherSuites() {
      return this.unmodifiableCipherSuites;
   }

   public final SSLEngine newEngine(ByteBufAllocator alloc) {
      return this.configureAndWrapEngine(this.context().createSSLEngine(), alloc);
   }

   public final SSLEngine newEngine(ByteBufAllocator alloc, String peerHost, int peerPort) {
      return this.configureAndWrapEngine(this.context().createSSLEngine(peerHost, peerPort), alloc);
   }

   private SSLEngine configureAndWrapEngine(SSLEngine engine, ByteBufAllocator alloc) {
      engine.setEnabledCipherSuites(this.cipherSuites);
      engine.setEnabledProtocols(this.protocols);
      engine.setUseClientMode(this.isClient());
      if (this.isServer()) {
         switch (this.clientAuth) {
            case OPTIONAL:
               engine.setWantClientAuth(true);
               break;
            case REQUIRE:
               engine.setNeedClientAuth(true);
            case NONE:
               break;
            default:
               throw new Error("Unknown auth " + this.clientAuth);
         }
      }

      this.configureEndpointVerification(engine);
      JdkApplicationProtocolNegotiator.SslEngineWrapperFactory factory = this.apn.wrapperFactory();
      return factory instanceof JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory ? ((JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory)factory).wrapSslEngine(engine, alloc, this.apn, this.isServer()) : factory.wrapSslEngine(engine, this.apn, this.isServer());
   }

   private void configureEndpointVerification(SSLEngine engine) {
      int version = PlatformDependent.javaVersion();
      if (version >= 7) {
         SSLParameters params = engine.getSSLParameters();
         Java7SslParametersUtils.setEndpointIdentificationAlgorithm(params, this.endpointIdentificationAlgorithm);
         engine.setSSLParameters(params);
      }

   }

   public final JdkApplicationProtocolNegotiator applicationProtocolNegotiator() {
      return this.apn;
   }

   static JdkApplicationProtocolNegotiator toNegotiator(ApplicationProtocolConfig config, boolean isServer) {
      if (config == null) {
         return JdkDefaultApplicationProtocolNegotiator.INSTANCE;
      } else {
         switch (config.protocol()) {
            case NONE:
               return JdkDefaultApplicationProtocolNegotiator.INSTANCE;
            case ALPN:
               if (isServer) {
                  switch (config.selectorFailureBehavior()) {
                     case FATAL_ALERT:
                        return new JdkAlpnApplicationProtocolNegotiator(true, config.supportedProtocols());
                     case NO_ADVERTISE:
                        return new JdkAlpnApplicationProtocolNegotiator(false, config.supportedProtocols());
                     default:
                        throw new UnsupportedOperationException("JDK provider does not support " + config.selectorFailureBehavior() + " failure behavior");
                  }
               } else {
                  switch (config.selectedListenerFailureBehavior()) {
                     case ACCEPT:
                        return new JdkAlpnApplicationProtocolNegotiator(false, config.supportedProtocols());
                     case FATAL_ALERT:
                        return new JdkAlpnApplicationProtocolNegotiator(true, config.supportedProtocols());
                     default:
                        throw new UnsupportedOperationException("JDK provider does not support " + config.selectedListenerFailureBehavior() + " failure behavior");
                  }
               }
            case NPN:
               if (isServer) {
                  switch (config.selectedListenerFailureBehavior()) {
                     case ACCEPT:
                        return new JdkNpnApplicationProtocolNegotiator(false, config.supportedProtocols());
                     case FATAL_ALERT:
                        return new JdkNpnApplicationProtocolNegotiator(true, config.supportedProtocols());
                     default:
                        throw new UnsupportedOperationException("JDK provider does not support " + config.selectedListenerFailureBehavior() + " failure behavior");
                  }
               } else {
                  switch (config.selectorFailureBehavior()) {
                     case FATAL_ALERT:
                        return new JdkNpnApplicationProtocolNegotiator(true, config.supportedProtocols());
                     case NO_ADVERTISE:
                        return new JdkNpnApplicationProtocolNegotiator(false, config.supportedProtocols());
                     default:
                        throw new UnsupportedOperationException("JDK provider does not support " + config.selectorFailureBehavior() + " failure behavior");
                  }
               }
            default:
               throw new UnsupportedOperationException("JDK provider does not support " + config.protocol() + " protocol");
         }
      }
   }

   static KeyManagerFactory buildKeyManagerFactory(File certChainFile, File keyFile, String keyPassword, KeyManagerFactory kmf, String keyStore) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, CertificateException, KeyException, IOException {
      String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
      if (algorithm == null) {
         algorithm = "SunX509";
      }

      return buildKeyManagerFactory(certChainFile, algorithm, keyFile, keyPassword, kmf, keyStore);
   }

   /** @deprecated */
   @Deprecated
   protected static KeyManagerFactory buildKeyManagerFactory(File certChainFile, File keyFile, String keyPassword, KeyManagerFactory kmf) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, CertificateException, KeyException, IOException {
      return buildKeyManagerFactory(certChainFile, keyFile, keyPassword, kmf, KeyStore.getDefaultType());
   }

   static KeyManagerFactory buildKeyManagerFactory(File certChainFile, String keyAlgorithm, File keyFile, String keyPassword, KeyManagerFactory kmf, String keyStore) throws KeyStoreException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IOException, CertificateException, KeyException, UnrecoverableKeyException {
      return buildKeyManagerFactory(toX509Certificates(certChainFile), keyAlgorithm, toPrivateKey(keyFile, keyPassword), keyPassword, kmf, keyStore);
   }

   /** @deprecated */
   @Deprecated
   protected static KeyManagerFactory buildKeyManagerFactory(File certChainFile, String keyAlgorithm, File keyFile, String keyPassword, KeyManagerFactory kmf) throws KeyStoreException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IOException, CertificateException, KeyException, UnrecoverableKeyException {
      return buildKeyManagerFactory(toX509Certificates(certChainFile), keyAlgorithm, toPrivateKey(keyFile, keyPassword), keyPassword, kmf, KeyStore.getDefaultType());
   }

   static {
      Defaults defaults = new Defaults();
      defaults.init();
      DEFAULT_PROVIDER = defaults.defaultProvider;
      DEFAULT_PROTOCOLS = defaults.defaultProtocols;
      SUPPORTED_CIPHERS = defaults.supportedCiphers;
      DEFAULT_CIPHERS = defaults.defaultCiphers;
      DEFAULT_CIPHERS_NON_TLSV13 = defaults.defaultCiphersNonTLSv13;
      SUPPORTED_CIPHERS_NON_TLSV13 = defaults.supportedCiphersNonTLSv13;
      if (logger.isDebugEnabled()) {
         logger.debug("Default protocols (JDK): {} ", Arrays.asList(DEFAULT_PROTOCOLS));
         logger.debug("Default cipher suites (JDK): {}", DEFAULT_CIPHERS);
      }

   }

   private static final class Defaults {
      String[] defaultProtocols;
      List defaultCiphers;
      List defaultCiphersNonTLSv13;
      Set supportedCiphers;
      Set supportedCiphersNonTLSv13;
      Provider defaultProvider;

      private Defaults() {
      }

      void init() {
         SSLContext context;
         try {
            context = SSLContext.getInstance("TLS");
            context.init((KeyManager[])null, (TrustManager[])null, (SecureRandom)null);
         } catch (Exception e) {
            throw new Error("failed to initialize the default SSL context", e);
         }

         this.defaultProvider = context.getProvider();
         SSLEngine engine = context.createSSLEngine();
         this.defaultProtocols = JdkSslContext.defaultProtocols(context, engine);
         this.supportedCiphers = Collections.unmodifiableSet(JdkSslContext.supportedCiphers(engine));
         this.defaultCiphers = Collections.unmodifiableList(JdkSslContext.defaultCiphers(engine, this.supportedCiphers));
         List<String> ciphersNonTLSv13 = new ArrayList(this.defaultCiphers);
         ciphersNonTLSv13.removeAll(Arrays.asList(SslUtils.DEFAULT_TLSV13_CIPHER_SUITES));
         this.defaultCiphersNonTLSv13 = Collections.unmodifiableList(ciphersNonTLSv13);
         Set<String> suppertedCiphersNonTLSv13 = new LinkedHashSet(this.supportedCiphers);
         suppertedCiphersNonTLSv13.removeAll(Arrays.asList(SslUtils.DEFAULT_TLSV13_CIPHER_SUITES));
         this.supportedCiphersNonTLSv13 = Collections.unmodifiableSet(suppertedCiphersNonTLSv13);
      }
   }
}
