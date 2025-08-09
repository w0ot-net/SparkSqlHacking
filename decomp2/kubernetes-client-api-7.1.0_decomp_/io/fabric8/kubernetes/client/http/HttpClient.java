package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.utils.HttpClientUtils;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

public interface HttpClient extends AutoCloseable {
   void close();

   DerivedClientBuilder newBuilder();

   CompletableFuture sendAsync(HttpRequest var1, Class var2);

   CompletableFuture consumeBytes(HttpRequest var1, AsyncBody.Consumer var2);

   WebSocket.Builder newWebSocketBuilder();

   HttpRequest.Builder newHttpRequestBuilder();

   boolean isClosed();

   public static enum ProxyType {
      HTTP,
      SOCKS4,
      SOCKS5,
      DIRECT;
   }

   public interface Factory {
      default Builder newBuilder(Config config) {
         Builder builder = this.newBuilder();
         HttpClientUtils.applyCommonConfiguration(config, builder, this);
         return builder;
      }

      Builder newBuilder();

      default int priority() {
         return 0;
      }
   }

   public interface Builder extends DerivedClientBuilder {
      HttpClient build();

      Builder connectTimeout(long var1, TimeUnit var3);

      Builder addOrReplaceInterceptor(String var1, Interceptor var2);

      Builder authenticatorNone();

      Builder sslContext(KeyManager[] var1, TrustManager[] var2);

      Builder followAllRedirects();

      Builder proxyAddress(InetSocketAddress var1);

      Builder proxyAuthorization(String var1);

      Builder tlsVersions(TlsVersion... var1);

      Builder preferHttp11();

      Builder proxyType(ProxyType var1);
   }

   public interface DerivedClientBuilder {
      HttpClient build();

      DerivedClientBuilder addOrReplaceInterceptor(String var1, Interceptor var2);

      DerivedClientBuilder authenticatorNone();

      DerivedClientBuilder tag(Object var1);
   }
}
