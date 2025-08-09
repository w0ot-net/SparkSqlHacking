package io.vertx.ext.web.client.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientInternal;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.impl.predicate.PredicateInterceptor;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import io.vertx.uritemplate.ExpandOptions;
import io.vertx.uritemplate.UriTemplate;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebClientBase implements WebClientInternal {
   final HttpClient client;
   final WebClientOptions options;
   final List interceptors;

   public WebClientBase(HttpClient client, WebClientOptions options) {
      options = new WebClientOptions(options);
      if (options.getTemplateExpandOptions() == null) {
         options.setTemplateExpandOptions(new ExpandOptions());
      }

      this.client = client;
      this.options = options;
      this.interceptors = new CopyOnWriteArrayList();
      this.addInterceptor(new PredicateInterceptor());
   }

   WebClientBase(WebClientBase webClient) {
      this.client = webClient.client;
      this.options = new WebClientOptions(webClient.options);
      this.interceptors = new CopyOnWriteArrayList(webClient.interceptors);
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, String requestURI) {
      return this.request(method, serverAddress, this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI);
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, UriTemplate requestURI) {
      return new HttpRequestImpl(this, method, serverAddress, this.options.isSsl(), this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, BodyCodecImpl.BUFFER, this.options.isFollowRedirects(), buildProxyOptions(this.options), buildHeaders(this.options));
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, RequestOptions requestOptions) {
      Integer port = requestOptions.getPort();
      if (port == null) {
         port = this.options.getDefaultPort();
      }

      String host = requestOptions.getHost();
      if (host == null) {
         host = this.options.getDefaultHost();
      }

      HttpRequestImpl<Buffer> request = this.request(method, serverAddress, port, host, requestOptions.getURI());
      request.ssl(requestOptions.isSsl());
      request.timeout(requestOptions.getTimeout());
      request.idleTimeout(requestOptions.getIdleTimeout());
      request.connectTimeout(requestOptions.getConnectTimeout());
      request.followRedirects(requestOptions.getFollowRedirects());
      ProxyOptions proxyOptions = requestOptions.getProxyOptions();
      if (proxyOptions != null) {
         request.proxy(new ProxyOptions(proxyOptions));
      }

      request.traceOperation(requestOptions.getTraceOperation());
      return (HttpRequest)(requestOptions.getHeaders() == null ? request : request.putHeaders(requestOptions.getHeaders()));
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, String host, String requestURI) {
      return this.request(method, serverAddress, this.options.getDefaultPort(), host, requestURI);
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, String host, UriTemplate requestURI) {
      return this.request(method, serverAddress, this.options.getDefaultPort(), host, requestURI);
   }

   public HttpRequestImpl request(HttpMethod method, SocketAddress serverAddress, int port, String host, String requestURI) {
      return new HttpRequestImpl(this, method, serverAddress, this.options.isSsl(), port, host, requestURI, BodyCodecImpl.BUFFER, this.options.isFollowRedirects(), buildProxyOptions(this.options), buildHeaders(this.options));
   }

   public HttpRequest request(HttpMethod method, SocketAddress serverAddress, int port, String host, UriTemplate requestURI) {
      return new HttpRequestImpl(this, method, serverAddress, this.options.isSsl(), port, host, requestURI, BodyCodecImpl.BUFFER, this.options.isFollowRedirects(), buildProxyOptions(this.options), buildHeaders(this.options));
   }

   public HttpRequest requestAbs(HttpMethod method, SocketAddress serverAddress, String surl) {
      ClientUri curi;
      try {
         curi = ClientUri.parse(surl);
      } catch (MalformedURLException | URISyntaxException e) {
         throw new VertxException(e);
      }

      return new HttpRequestImpl(this, method, serverAddress, curi.ssl, curi.port, curi.host, curi.uri, BodyCodecImpl.BUFFER, this.options.isFollowRedirects(), buildProxyOptions(this.options), buildHeaders(this.options));
   }

   public HttpRequest requestAbs(HttpMethod method, SocketAddress serverAddress, UriTemplate absoluteURI) {
      return new HttpRequestImpl(this, method, serverAddress, absoluteURI, BodyCodecImpl.BUFFER, this.options.isFollowRedirects(), buildProxyOptions(this.options), buildHeaders(this.options));
   }

   public WebClientInternal addInterceptor(Handler interceptor) {
      if (this.interceptors.stream().anyMatch((i) -> i.getClass() == interceptor.getClass())) {
         throw new IllegalStateException(String.format("Client already contains a %s interceptor", interceptor.getClass()));
      } else {
         this.interceptors.add(interceptor);
         return this;
      }
   }

   public HttpContext createContext(Handler handler) {
      HttpClientInternal client = (HttpClientInternal)this.client;
      return new HttpContext(client, this.options, this.interceptors, handler);
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      return this.client.updateSSLOptions(options, force);
   }

   public void close() {
      this.client.close();
   }

   private static MultiMap buildHeaders(WebClientOptions options) {
      return options.isUserAgentEnabled() ? HttpHeaders.set(HttpHeaders.USER_AGENT, options.getUserAgent()) : HttpHeaders.headers();
   }

   private static ProxyOptions buildProxyOptions(WebClientOptions options) {
      return options.getProxyOptions() != null ? new ProxyOptions(options.getProxyOptions()) : null;
   }
}
