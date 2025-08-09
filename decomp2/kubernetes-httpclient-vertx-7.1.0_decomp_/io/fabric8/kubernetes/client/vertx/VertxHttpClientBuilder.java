package io.fabric8.kubernetes.client.vertx;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.StandardHttpClientBuilder;
import io.fabric8.kubernetes.client.http.TlsVersion;
import io.fabric8.kubernetes.client.http.HttpClient.ProxyType;
import io.fabric8.kubernetes.client.utils.HttpClientUtils;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.IdentityCipherSuiteFilter;
import io.netty.handler.ssl.JdkSslContext;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.spi.tls.SslContextFactory;
import io.vertx.ext.web.client.WebClientOptions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class VertxHttpClientBuilder extends StandardHttpClientBuilder {
   private static final int MAX_CONNECTIONS = 8192;
   private static final int MAX_WS_MESSAGE_SIZE = Integer.MAX_VALUE;
   final Vertx vertx;
   private final boolean closeVertx;

   public VertxHttpClientBuilder(HttpClient.Factory clientFactory, Vertx sharedVertx) {
      this(clientFactory, sharedVertx != null ? sharedVertx : createVertxInstance(), sharedVertx == null);
   }

   VertxHttpClientBuilder(HttpClient.Factory clientFactory, Vertx vertx, boolean closeVertx) {
      super(clientFactory);
      this.vertx = vertx;
      this.closeVertx = closeVertx;
   }

   public VertxHttpClient build() {
      if (this.client != null) {
         return new VertxHttpClient(this, ((VertxHttpClient)this.client).getClosed(), ((VertxHttpClient)this.client).getClient(), this.closeVertx);
      } else {
         WebClientOptions options = new WebClientOptions();
         options.setMaxPoolSize(8192);
         options.setMaxWebSockets(8192);
         options.setIdleTimeoutUnit(TimeUnit.SECONDS);
         options.setMaxWebSocketFrameSize(Integer.MAX_VALUE);
         options.setMaxWebSocketMessageSize(Integer.MAX_VALUE);
         if (this.connectTimeout != null) {
            options.setConnectTimeout((int)this.connectTimeout.toMillis());
         }

         if (this.followRedirects) {
            options.setFollowRedirects(this.followRedirects);
         }

         if (this.proxyType != ProxyType.DIRECT && this.proxyAddress != null) {
            ProxyOptions proxyOptions = (new ProxyOptions()).setHost(this.proxyAddress.getHostName()).setPort(this.proxyAddress.getPort()).setType(this.convertProxyType());
            String[] userPassword = HttpClientUtils.decodeBasicCredentials(this.proxyAuthorization);
            if (userPassword != null) {
               proxyOptions.setUsername(userPassword[0]);
               proxyOptions.setPassword(userPassword[1]);
            } else {
               this.addProxyAuthInterceptor();
            }

            options.setProxyOptions(proxyOptions);
         }

         final String[] protocols;
         if (this.tlsVersions != null && this.tlsVersions.length > 0) {
            protocols = (String[])Stream.of(this.tlsVersions).map(TlsVersion::javaName).toArray((x$0) -> new String[x$0]);
            options.setEnabledSecureTransportProtocols(new HashSet(Arrays.asList(protocols)));
         } else {
            protocols = null;
         }

         if (this.preferHttp11) {
            options.setProtocolVersion(HttpVersion.HTTP_1_1);
         }

         if (this.sslContext != null) {
            options.setSsl(true);
            options.setSslEngineOptions(new JdkSSLEngineOptions() {
               public JdkSSLEngineOptions copy() {
                  return this;
               }

               public SslContextFactory sslContextFactory() {
                  return () -> new JdkSslContext(VertxHttpClientBuilder.this.sslContext, true, (Iterable)null, IdentityCipherSuiteFilter.INSTANCE, ApplicationProtocolConfig.DISABLED, ClientAuth.NONE, protocols, false);
               }
            });
         }

         return new VertxHttpClient(this, new AtomicBoolean(), this.vertx.createHttpClient(options), this.closeVertx);
      }
   }

   protected VertxHttpClientBuilder newInstance(HttpClient.Factory clientFactory) {
      return new VertxHttpClientBuilder(clientFactory, this.vertx, this.closeVertx);
   }

   private io.vertx.core.net.ProxyType convertProxyType() {
      switch (this.proxyType) {
         case HTTP:
            return io.vertx.core.net.ProxyType.HTTP;
         case SOCKS4:
            return io.vertx.core.net.ProxyType.SOCKS4;
         case SOCKS5:
            return io.vertx.core.net.ProxyType.SOCKS5;
         default:
            throw new KubernetesClientException("Unsupported proxy type");
      }
   }

   private static Vertx createVertxInstance() {
      String originalValue = System.getProperty("vertx.disableDnsResolver");

      Vertx vertx;
      try {
         System.setProperty("vertx.disableDnsResolver", "true");
         vertx = Vertx.vertx((new VertxOptions()).setFileSystemOptions((new FileSystemOptions()).setFileCachingEnabled(false).setClassPathResolvingEnabled(false)).setUseDaemonThread(true));
      } finally {
         if (originalValue == null) {
            System.clearProperty("vertx.disableDnsResolver");
         } else {
            System.setProperty("vertx.disableDnsResolver", originalValue);
         }

      }

      return vertx;
   }
}
