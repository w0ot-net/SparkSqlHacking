package io.vertx.core.spi.tls;

import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.OpenSslServerContext;
import io.netty.handler.ssl.OpenSslServerSessionContext;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
import io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior;
import io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManagerFactory;

public class DefaultSslContextFactory implements SslContextFactory {
   private final SslProvider sslProvider;
   private final boolean sslSessionCacheEnabled;
   private Set enabledCipherSuites;
   private List applicationProtocols;
   private boolean useAlpn;
   private ClientAuth clientAuth;
   private boolean forClient;
   private KeyManagerFactory kmf;
   private TrustManagerFactory tmf;

   public DefaultSslContextFactory(SslProvider sslProvider, boolean sslSessionCacheEnabled) {
      this.sslProvider = sslProvider;
      this.sslSessionCacheEnabled = sslSessionCacheEnabled;
   }

   public SslContextFactory useAlpn(boolean useAlpn) {
      this.useAlpn = useAlpn;
      return this;
   }

   public SslContextFactory clientAuth(ClientAuth clientAuth) {
      this.clientAuth = clientAuth;
      return this;
   }

   public SslContextFactory forClient(boolean forClient) {
      this.forClient = forClient;
      return this;
   }

   public SslContextFactory keyMananagerFactory(KeyManagerFactory kmf) {
      this.kmf = kmf;
      return this;
   }

   public SslContextFactory trustManagerFactory(TrustManagerFactory tmf) {
      this.tmf = tmf;
      return this;
   }

   public SslContext create() throws SSLException {
      return this.createContext(this.useAlpn, this.forClient, this.kmf, this.tmf);
   }

   public SslContextFactory enabledCipherSuites(Set enabledCipherSuites) {
      this.enabledCipherSuites = enabledCipherSuites;
      return this;
   }

   public SslContextFactory applicationProtocols(List applicationProtocols) {
      this.applicationProtocols = applicationProtocols;
      return this;
   }

   private SslContext createContext(boolean useAlpn, boolean client, KeyManagerFactory kmf, TrustManagerFactory tmf) throws SSLException {
      SslContextBuilder builder;
      if (client) {
         builder = SslContextBuilder.forClient();
         if (kmf != null) {
            builder.keyManager(kmf);
         }
      } else {
         builder = SslContextBuilder.forServer(kmf);
      }

      Collection<String> cipherSuites = this.enabledCipherSuites;
      switch (this.sslProvider) {
         case OPENSSL:
            builder.sslProvider(SslProvider.OPENSSL);
            if (cipherSuites == null || cipherSuites.isEmpty()) {
               cipherSuites = OpenSsl.availableOpenSslCipherSuites();
            }
            break;
         case JDK:
            builder.sslProvider(SslProvider.JDK);
            if (cipherSuites == null || cipherSuites.isEmpty()) {
               cipherSuites = DefaultJDKCipherSuite.get();
            }
            break;
         default:
            throw new UnsupportedOperationException();
      }

      if (tmf != null) {
         builder.trustManager(tmf);
      }

      if (cipherSuites != null && cipherSuites.size() > 0) {
         builder.ciphers(cipherSuites);
      }

      if (useAlpn && this.applicationProtocols != null && this.applicationProtocols.size() > 0) {
         ApplicationProtocolConfig.SelectorFailureBehavior sfb;
         ApplicationProtocolConfig.SelectedListenerFailureBehavior slfb;
         if (this.sslProvider == SslProvider.JDK) {
            sfb = SelectorFailureBehavior.FATAL_ALERT;
            slfb = SelectedListenerFailureBehavior.FATAL_ALERT;
         } else {
            sfb = SelectorFailureBehavior.NO_ADVERTISE;
            slfb = SelectedListenerFailureBehavior.ACCEPT;
         }

         builder.applicationProtocolConfig(new ApplicationProtocolConfig(Protocol.ALPN, sfb, slfb, this.applicationProtocols));
      }

      if (this.clientAuth != null) {
         builder.clientAuth(this.clientAuth);
      }

      SslContext ctx = builder.build();
      if (ctx instanceof OpenSslServerContext) {
         SSLSessionContext sslSessionContext = ctx.sessionContext();
         if (sslSessionContext instanceof OpenSslServerSessionContext) {
            ((OpenSslServerSessionContext)sslSessionContext).setSessionCacheEnabled(this.sslSessionCacheEnabled);
         }
      }

      return ctx;
   }
}
