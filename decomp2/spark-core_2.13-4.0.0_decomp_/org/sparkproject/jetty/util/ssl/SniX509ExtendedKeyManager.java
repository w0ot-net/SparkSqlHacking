package org.sparkproject.jetty.util.ssl;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509ExtendedKeyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SniX509ExtendedKeyManager extends X509ExtendedKeyManager {
   private static final Logger LOG = LoggerFactory.getLogger(SniX509ExtendedKeyManager.class);
   private final X509ExtendedKeyManager _delegate;
   private final SslContextFactory.Server _sslContextFactory;
   private UnaryOperator _aliasMapper = UnaryOperator.identity();

   public SniX509ExtendedKeyManager(X509ExtendedKeyManager keyManager, SslContextFactory.Server sslContextFactory) {
      this._delegate = keyManager;
      this._sslContextFactory = (SslContextFactory.Server)Objects.requireNonNull(sslContextFactory, "SslContextFactory.Server must be provided");
   }

   public UnaryOperator getAliasMapper() {
      return this._aliasMapper;
   }

   public void setAliasMapper(UnaryOperator aliasMapper) {
      this._aliasMapper = (UnaryOperator)Objects.requireNonNull(aliasMapper);
   }

   public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
      return this._delegate.chooseClientAlias(keyType, issuers, socket);
   }

   public String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine) {
      return this._delegate.chooseEngineClientAlias(keyType, issuers, engine);
   }

   protected String chooseServerAlias(String keyType, Principal[] issuers, Collection matchers, SSLSession session) {
      String[] mangledAliases = this._delegate.getServerAliases(keyType, issuers);
      if (mangledAliases != null && mangledAliases.length != 0) {
         Map<String, String> aliasMap = new LinkedHashMap();
         Arrays.stream(mangledAliases).forEach((aliasx) -> aliasMap.put((String)this.getAliasMapper().apply(aliasx), aliasx));
         String host = null;
         if (session instanceof ExtendedSSLSession) {
            List<SNIServerName> serverNames = ((ExtendedSSLSession)session).getRequestedServerNames();
            if (serverNames != null) {
               Optional var10000 = serverNames.stream().findAny();
               Objects.requireNonNull(SNIHostName.class);
               var10000 = var10000.filter(SNIHostName.class::isInstance);
               Objects.requireNonNull(SNIHostName.class);
               host = (String)var10000.map(SNIHostName.class::cast).map(SNIHostName::getAsciiName).orElse((Object)null);
            }
         }

         if (host == null) {
            String var16;
            if (matchers == null) {
               var16 = null;
            } else {
               Stream var17 = matchers.stream();
               Objects.requireNonNull(SslContextFactory.AliasSNIMatcher.class);
               var17 = var17.filter(SslContextFactory.AliasSNIMatcher.class::isInstance);
               Objects.requireNonNull(SslContextFactory.AliasSNIMatcher.class);
               var16 = (String)var17.map(SslContextFactory.AliasSNIMatcher.class::cast).findFirst().map(SslContextFactory.AliasSNIMatcher::getHost).orElse((Object)null);
            }

            host = var16;
         }

         if (session != null && host != null) {
            session.putValue("org.sparkproject.jetty.util.ssl.sniHost", host);
         }

         try {
            Stream var19 = aliasMap.keySet().stream();
            SslContextFactory.Server var10001 = this._sslContextFactory;
            Objects.requireNonNull(var10001);
            Collection<X509> certificates = (Collection)var19.map(var10001::getX509).filter(Objects::nonNull).collect(Collectors.toList());
            SniSelector sniSelector = this._sslContextFactory.getSNISelector();
            if (sniSelector == null) {
               sniSelector = this._sslContextFactory;
            }

            String alias = sniSelector.sniSelect(keyType, issuers, session, host, certificates);
            if (alias != null && alias != "delegate_no_sni_match") {
               X509 x509 = this._sslContextFactory.getX509(alias);
               if (aliasMap.containsKey(alias) && x509 != null) {
                  String mangledAlias = (String)aliasMap.get(alias);
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Matched SNI {} with alias {}, certificate {} from aliases {}", new Object[]{host, mangledAlias, x509, aliasMap.keySet()});
                  }

                  return mangledAlias;
               } else {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Invalid X509 match for SNI {}: {}", host, alias);
                  }

                  return null;
               }
            } else {
               return alias;
            }
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failure matching X509 for SNI {}", host, x);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
      SSLSocket sslSocket = (SSLSocket)socket;
      String alias = socket == null ? this.chooseServerAlias(keyType, issuers, Collections.emptyList(), (SSLSession)null) : this.chooseServerAlias(keyType, issuers, sslSocket.getSSLParameters().getSNIMatchers(), sslSocket.getHandshakeSession());
      boolean delegate = alias == "delegate_no_sni_match";
      if (delegate) {
         alias = this._delegate.chooseServerAlias(keyType, issuers, socket);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Chose {} alias={} keyType={} on {}", new Object[]{delegate ? "delegate" : "explicit", String.valueOf(alias), keyType, socket});
      }

      return alias;
   }

   public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine) {
      String alias = engine == null ? this.chooseServerAlias(keyType, issuers, Collections.emptyList(), (SSLSession)null) : this.chooseServerAlias(keyType, issuers, engine.getSSLParameters().getSNIMatchers(), engine.getHandshakeSession());
      boolean delegate = alias == "delegate_no_sni_match";
      if (delegate) {
         alias = this._delegate.chooseEngineServerAlias(keyType, issuers, engine);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Chose {} alias={} keyType={} on {}", new Object[]{delegate ? "delegate" : "explicit", String.valueOf(alias), keyType, engine});
      }

      return alias;
   }

   public X509Certificate[] getCertificateChain(String alias) {
      return this._delegate.getCertificateChain(alias);
   }

   public String[] getClientAliases(String keyType, Principal[] issuers) {
      return this._delegate.getClientAliases(keyType, issuers);
   }

   public PrivateKey getPrivateKey(String alias) {
      return this._delegate.getPrivateKey(alias);
   }

   public String[] getServerAliases(String keyType, Principal[] issuers) {
      return this._delegate.getServerAliases(keyType, issuers);
   }

   @FunctionalInterface
   public interface SniSelector {
      String DELEGATE = "delegate_no_sni_match";

      String sniSelect(String var1, Principal[] var2, SSLSession var3, String var4, Collection var5) throws SSLHandshakeException;
   }
}
