package io.vertx.core.spi.tls;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;

public interface SslContextFactory {
   default SslContextFactory useAlpn(boolean useAlpn) {
      return this;
   }

   default SslContextFactory clientAuth(ClientAuth clientAuth) {
      return this;
   }

   default SslContextFactory forClient(boolean forClient) {
      return this;
   }

   default SslContextFactory keyMananagerFactory(KeyManagerFactory kmf) {
      return this;
   }

   default SslContextFactory trustManagerFactory(TrustManagerFactory tmf) {
      return this;
   }

   default SslContextFactory enabledCipherSuites(Set enabledCipherSuites) {
      return this;
   }

   default SslContextFactory applicationProtocols(List applicationProtocols) {
      return this;
   }

   default SslContextFactory serverName(String serverName) {
      return this;
   }

   SslContext create() throws SSLException;
}
