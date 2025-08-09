package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientInternal;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.client.impl.WebClientBase;
import io.vertx.uritemplate.UriTemplate;

@VertxGen
public interface WebClient {
   static WebClient create(Vertx vertx) {
      WebClientOptions options = new WebClientOptions();
      return create(vertx, options);
   }

   static WebClient create(Vertx vertx, WebClientOptions options) {
      return new WebClientBase(vertx.createHttpClient(options), options);
   }

   static WebClient wrap(HttpClient httpClient) {
      return wrap(httpClient, new WebClientOptions());
   }

   static WebClient wrap(HttpClient httpClient, WebClientOptions options) {
      WebClientOptions actualOptions = new WebClientOptions(((HttpClientInternal)httpClient).options());
      actualOptions.init(options);
      return new WebClientBase(httpClient, actualOptions);
   }

   default HttpRequest request(HttpMethod method, int port, String host, String requestURI) {
      return this.request(method, (SocketAddress)null, port, host, (String)requestURI);
   }

   default HttpRequest request(HttpMethod method, int port, String host, UriTemplate requestURI) {
      return this.request(method, (SocketAddress)null, port, host, (UriTemplate)requestURI);
   }

   HttpRequest request(HttpMethod var1, SocketAddress var2, int var3, String var4, String var5);

   HttpRequest request(HttpMethod var1, SocketAddress var2, int var3, String var4, UriTemplate var5);

   default HttpRequest request(HttpMethod method, String host, String requestURI) {
      return this.request(method, (SocketAddress)null, host, (String)requestURI);
   }

   default HttpRequest request(HttpMethod method, String host, UriTemplate requestURI) {
      return this.request(method, (SocketAddress)null, host, (UriTemplate)requestURI);
   }

   HttpRequest request(HttpMethod var1, SocketAddress var2, String var3, String var4);

   HttpRequest request(HttpMethod var1, SocketAddress var2, String var3, UriTemplate var4);

   default HttpRequest request(HttpMethod method, String requestURI) {
      return this.request(method, (SocketAddress)null, requestURI);
   }

   default HttpRequest request(HttpMethod method, UriTemplate requestURI) {
      return this.request(method, (SocketAddress)null, requestURI);
   }

   HttpRequest request(HttpMethod var1, SocketAddress var2, String var3);

   HttpRequest request(HttpMethod var1, SocketAddress var2, UriTemplate var3);

   default HttpRequest request(HttpMethod method, RequestOptions options) {
      return this.request(method, (SocketAddress)null, (RequestOptions)options);
   }

   HttpRequest request(HttpMethod var1, SocketAddress var2, RequestOptions var3);

   default HttpRequest requestAbs(HttpMethod method, String absoluteURI) {
      return this.requestAbs(method, (SocketAddress)null, (String)absoluteURI);
   }

   default HttpRequest requestAbs(HttpMethod method, UriTemplate absoluteURI) {
      return this.requestAbs(method, (SocketAddress)null, (UriTemplate)absoluteURI);
   }

   HttpRequest requestAbs(HttpMethod var1, SocketAddress var2, String var3);

   HttpRequest requestAbs(HttpMethod var1, SocketAddress var2, UriTemplate var3);

   default HttpRequest get(String requestURI) {
      return this.request(HttpMethod.GET, requestURI);
   }

   default HttpRequest get(UriTemplate requestURI) {
      return this.request(HttpMethod.GET, requestURI);
   }

   default HttpRequest get(int port, String host, String requestURI) {
      return this.request(HttpMethod.GET, port, host, requestURI);
   }

   default HttpRequest get(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.GET, port, host, requestURI);
   }

   default HttpRequest get(String host, String requestURI) {
      return this.request(HttpMethod.GET, host, requestURI);
   }

   default HttpRequest get(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.GET, host, requestURI);
   }

   default HttpRequest getAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.GET, absoluteURI);
   }

   default HttpRequest getAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.GET, absoluteURI);
   }

   default HttpRequest post(String requestURI) {
      return this.request(HttpMethod.POST, requestURI);
   }

   default HttpRequest post(UriTemplate requestURI) {
      return this.request(HttpMethod.POST, requestURI);
   }

   default HttpRequest post(int port, String host, String requestURI) {
      return this.request(HttpMethod.POST, port, host, requestURI);
   }

   default HttpRequest post(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.POST, port, host, requestURI);
   }

   default HttpRequest post(String host, String requestURI) {
      return this.request(HttpMethod.POST, host, requestURI);
   }

   default HttpRequest post(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.POST, host, requestURI);
   }

   default HttpRequest postAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.POST, absoluteURI);
   }

   default HttpRequest postAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.POST, absoluteURI);
   }

   default HttpRequest put(String requestURI) {
      return this.request(HttpMethod.PUT, requestURI);
   }

   default HttpRequest put(UriTemplate requestURI) {
      return this.request(HttpMethod.PUT, requestURI);
   }

   default HttpRequest put(int port, String host, String requestURI) {
      return this.request(HttpMethod.PUT, port, host, requestURI);
   }

   default HttpRequest put(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.PUT, port, host, requestURI);
   }

   default HttpRequest put(String host, String requestURI) {
      return this.request(HttpMethod.PUT, host, requestURI);
   }

   default HttpRequest put(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.PUT, host, requestURI);
   }

   default HttpRequest putAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.PUT, absoluteURI);
   }

   default HttpRequest putAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.PUT, absoluteURI);
   }

   default HttpRequest delete(String requestURI) {
      return this.request(HttpMethod.DELETE, requestURI);
   }

   default HttpRequest delete(UriTemplate requestURI) {
      return this.request(HttpMethod.DELETE, requestURI);
   }

   default HttpRequest delete(int port, String host, String requestURI) {
      return this.request(HttpMethod.DELETE, port, host, requestURI);
   }

   default HttpRequest delete(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.DELETE, port, host, requestURI);
   }

   default HttpRequest delete(String host, String requestURI) {
      return this.request(HttpMethod.DELETE, host, requestURI);
   }

   default HttpRequest delete(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.DELETE, host, requestURI);
   }

   default HttpRequest deleteAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.DELETE, absoluteURI);
   }

   default HttpRequest deleteAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.DELETE, absoluteURI);
   }

   default HttpRequest patch(String requestURI) {
      return this.request(HttpMethod.PATCH, requestURI);
   }

   default HttpRequest patch(UriTemplate requestURI) {
      return this.request(HttpMethod.PATCH, requestURI);
   }

   default HttpRequest patch(int port, String host, String requestURI) {
      return this.request(HttpMethod.PATCH, port, host, requestURI);
   }

   default HttpRequest patch(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.PATCH, port, host, requestURI);
   }

   default HttpRequest patch(String host, String requestURI) {
      return this.request(HttpMethod.PATCH, host, requestURI);
   }

   default HttpRequest patch(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.PATCH, host, requestURI);
   }

   default HttpRequest patchAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.PATCH, absoluteURI);
   }

   default HttpRequest patchAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.PATCH, absoluteURI);
   }

   default HttpRequest head(String requestURI) {
      return this.request(HttpMethod.HEAD, requestURI);
   }

   default HttpRequest head(UriTemplate requestURI) {
      return this.request(HttpMethod.HEAD, requestURI);
   }

   default HttpRequest head(int port, String host, String requestURI) {
      return this.request(HttpMethod.HEAD, port, host, requestURI);
   }

   default HttpRequest head(int port, String host, UriTemplate requestURI) {
      return this.request(HttpMethod.HEAD, port, host, requestURI);
   }

   default HttpRequest head(String host, String requestURI) {
      return this.request(HttpMethod.HEAD, host, requestURI);
   }

   default HttpRequest head(String host, UriTemplate requestURI) {
      return this.request(HttpMethod.HEAD, host, requestURI);
   }

   default HttpRequest headAbs(String absoluteURI) {
      return this.requestAbs(HttpMethod.HEAD, absoluteURI);
   }

   default HttpRequest headAbs(UriTemplate absoluteURI) {
      return this.requestAbs(HttpMethod.HEAD, absoluteURI);
   }

   default Future updateSSLOptions(SSLOptions options) {
      return this.updateSSLOptions(options, false);
   }

   default void updateSSLOptions(SSLOptions options, Handler handler) {
      Future<Boolean> fut = this.updateSSLOptions(options);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   Future updateSSLOptions(SSLOptions var1, boolean var2);

   default void updateSSLOptions(SSLOptions options, boolean force, Handler handler) {
      Future<Boolean> fut = this.updateSSLOptions(options, force);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   void close();
}
