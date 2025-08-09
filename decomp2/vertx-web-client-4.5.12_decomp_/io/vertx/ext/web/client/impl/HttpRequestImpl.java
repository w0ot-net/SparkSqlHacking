package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.multipart.MultipartForm;
import io.vertx.uritemplate.UriTemplate;
import io.vertx.uritemplate.Variables;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpRequestImpl implements HttpRequest {
   private final WebClientBase client;
   private ProxyOptions proxyOptions;
   private SocketAddress serverAddress;
   private MultiMap queryParams;
   private Variables templateParams;
   private HttpMethod method;
   private final UriTemplate absoluteUri;
   private int port;
   private String host;
   private String virtualHost;
   private Object uri;
   private long timeout = -1L;
   private long idleTimeout = -1L;
   private long connectTimeout = -1L;
   private boolean followRedirects;
   private Boolean ssl;
   private boolean multipartMixed = true;
   private String traceOperation;
   private List expectations;
   private BodyCodec codec;
   MultiMap headers;

   HttpRequestImpl(WebClientBase client, HttpMethod method, SocketAddress serverAddress, UriTemplate absoluteUri, BodyCodec codec, boolean followRedirects, ProxyOptions proxyOptions, MultiMap headers) {
      Objects.requireNonNull(absoluteUri, "AbsoluteUri cannot be null");
      this.client = client;
      this.absoluteUri = absoluteUri;
      this.serverAddress = serverAddress;
      this.method = method;
      this.ssl = null;
      this.host = null;
      this.port = -1;
      this.uri = null;
      this.codec = codec;
      this.headers = headers;
      this.followRedirects = followRedirects;
      this.proxyOptions = proxyOptions;
   }

   HttpRequestImpl(WebClientBase client, HttpMethod method, SocketAddress serverAddress, Boolean ssl, int port, String host, Object uri, BodyCodec codec, boolean followRedirects, ProxyOptions proxyOptions, MultiMap headers) {
      Objects.requireNonNull(host, "Host cannot be null");
      this.client = client;
      this.absoluteUri = null;
      this.serverAddress = serverAddress;
      this.method = method;
      this.ssl = ssl;
      this.port = port;
      this.host = host;
      this.uri = uri;
      this.codec = codec;
      this.headers = headers;
      this.followRedirects = followRedirects;
      this.proxyOptions = proxyOptions;
   }

   private HttpRequestImpl(HttpRequestImpl other) {
      this.client = other.client;
      this.absoluteUri = other.absoluteUri;
      this.serverAddress = other.serverAddress;
      this.ssl = other.ssl;
      this.method = other.method;
      this.port = other.port;
      this.host = other.host;
      this.uri = other.uri;
      this.codec = other.codec;
      this.headers = other.headers != null ? HttpHeaders.headers().addAll(other.headers) : HttpHeaders.headers();
      this.followRedirects = other.followRedirects;
      this.proxyOptions = other.proxyOptions != null ? new ProxyOptions(other.proxyOptions) : null;
      this.timeout = other.timeout;
      this.idleTimeout = other.idleTimeout;
      this.connectTimeout = other.connectTimeout;
      this.queryParams = other.queryParams != null ? MultiMap.caseInsensitiveMultiMap().addAll(other.queryParams) : null;
      this.multipartMixed = other.multipartMixed;
      this.virtualHost = other.virtualHost;
      this.expectations = other.expectations != null ? new ArrayList(other.expectations) : null;
   }

   public HttpRequest as(BodyCodec responseCodec) {
      this.codec = responseCodec;
      return this;
   }

   public BodyCodec bodyCodec() {
      return this.codec;
   }

   public HttpRequest method(HttpMethod value) {
      this.method = value;
      return this;
   }

   public HttpMethod method() {
      return this.method;
   }

   public HttpRequest ssl(Boolean value) {
      this.ssl = value;
      return this;
   }

   public Boolean ssl() {
      return this.ssl;
   }

   public HttpRequest port(int value) {
      this.port = value;
      return this;
   }

   public int port() {
      return this.port;
   }

   public HttpRequest host(String value) {
      Objects.requireNonNull(this.host, "Host cannot be null");
      this.host = value;
      return this;
   }

   public String host() {
      return this.host;
   }

   public HttpRequest uri(String value) {
      this.queryParams = null;
      this.uri = value;
      return this;
   }

   public String uri() {
      return this.uri.toString();
   }

   public HttpRequest virtualHost(String value) {
      this.virtualHost = value;
      return this;
   }

   public String virtualHost() {
      return this.virtualHost;
   }

   public HttpRequest putHeaders(MultiMap headers) {
      this.headers().addAll(headers);
      return this;
   }

   public HttpRequest putHeader(String name, String value) {
      this.headers().set(name, value);
      return this;
   }

   public HttpRequest putHeader(String name, Iterable value) {
      this.headers().set(name, value);
      return this;
   }

   public MultiMap headers() {
      if (this.headers == null) {
         this.headers = HttpHeaders.headers();
      }

      return this.headers;
   }

   public HttpRequest authentication(Credentials credentials) {
      this.putHeader(HttpHeaders.AUTHORIZATION.toString(), credentials.toHttpAuthorization());
      return this;
   }

   public HttpRequest timeout(long value) {
      this.timeout = value;
      return this;
   }

   public long timeout() {
      return this.timeout;
   }

   public HttpRequest idleTimeout(long timeout) {
      this.idleTimeout = timeout;
      return this;
   }

   public long idleTimeout() {
      return this.idleTimeout;
   }

   public HttpRequest connectTimeout(long timeout) {
      this.connectTimeout = timeout;
      return this;
   }

   public long connectTimeout() {
      return this.connectTimeout;
   }

   public HttpRequest addQueryParam(String paramName, String paramValue) {
      this.queryParams().add(paramName, paramValue);
      return this;
   }

   public HttpRequest setQueryParam(String paramName, String paramValue) {
      this.queryParams().set(paramName, paramValue);
      return this;
   }

   public HttpRequest setTemplateParam(String paramName, String paramValue) {
      this.templateParams().set(paramName, paramValue);
      return this;
   }

   public HttpRequest setTemplateParam(String paramName, List paramValue) {
      this.templateParams().set(paramName, paramValue);
      return this;
   }

   public HttpRequest setTemplateParam(String paramName, Map paramValue) {
      this.templateParams().set(paramName, paramValue);
      return this;
   }

   public HttpRequest followRedirects(boolean value) {
      this.followRedirects = value;
      return this;
   }

   public boolean followRedirects() {
      return this.followRedirects;
   }

   public HttpRequest proxy(ProxyOptions proxyOptions) {
      this.proxyOptions = proxyOptions;
      return this;
   }

   public ProxyOptions proxy() {
      return this.proxyOptions;
   }

   public HttpRequest expect(ResponsePredicate expectation) {
      if (this.expectations == null) {
         this.expectations = new ArrayList();
      }

      this.expectations.add(expectation);
      return this;
   }

   public List expectations() {
      return this.expectations != null ? this.expectations : Collections.emptyList();
   }

   public MultiMap queryParams() {
      if (this.queryParams == null) {
         this.queryParams = MultiMap.caseInsensitiveMultiMap();
         if (this.uri instanceof String) {
            int idx = ((String)this.uri).indexOf(63);
            if (idx >= 0) {
               QueryStringDecoder dec = new QueryStringDecoder((String)this.uri);
               dec.parameters().forEach((name, value) -> this.queryParams.add(name, value));
               this.uri = ((String)this.uri).substring(0, idx);
            }
         }
      }

      return this.queryParams;
   }

   public Variables templateParams() {
      if (!(this.uri instanceof UriTemplate) && !(this.absoluteUri instanceof UriTemplate)) {
         throw new IllegalStateException();
      } else {
         if (this.templateParams == null) {
            this.templateParams = Variables.variables();
         }

         return this.templateParams;
      }
   }

   public HttpRequest copy() {
      return new HttpRequestImpl(this);
   }

   public HttpRequest multipartMixed(boolean allow) {
      this.multipartMixed = allow;
      return this;
   }

   public HttpRequest traceOperation(String traceOperation) {
      this.traceOperation = traceOperation;
      return this;
   }

   public String traceOperation() {
      return this.traceOperation;
   }

   public boolean multipartMixed() {
      return this.multipartMixed;
   }

   public void sendStream(ReadStream body, Handler handler) {
      this.send((String)null, body, handler);
   }

   public void send(Handler handler) {
      this.send((String)null, (Object)null, handler);
   }

   public void sendBuffer(Buffer body, Handler handler) {
      this.send((String)null, body, handler);
   }

   public void sendJsonObject(JsonObject body, Handler handler) {
      this.send("application/json", body, handler);
   }

   public void sendJson(Object body, Handler handler) {
      this.send("application/json", body, handler);
   }

   public void sendForm(MultiMap body, Handler handler) {
      this.sendForm(body, "UTF-8", handler);
   }

   public void sendForm(MultiMap body, String charset, Handler handler) {
      MultipartForm parts = MultipartForm.create();

      for(Map.Entry attribute : body) {
         parts.attribute((String)attribute.getKey(), (String)attribute.getValue());
      }

      parts.setCharset(charset);
      this.send("application/x-www-form-urlencoded", parts, handler);
   }

   public void sendMultipartForm(MultipartForm body, Handler handler) {
      this.send("multipart/form-data", body, handler);
   }

   RequestOptions buildRequestOptions() throws URISyntaxException, MalformedURLException {
      String protocol = null;
      Boolean ssl = null;
      int port = -1;
      String host = null;
      String uri = null;
      if (this.absoluteUri != null) {
         uri = this.absoluteUri.expandToString(this.templateParams(), this.client.options.getTemplateExpandOptions());
         ClientUri curi = ClientUri.parse(uri);
         uri = curi.uri;
         host = curi.host;
         port = curi.port;
         protocol = curi.protocol;
         ssl = curi.ssl;
      }

      if (this.ssl != null) {
         ssl = this.ssl;
      }

      if (this.port >= 0) {
         port = this.port;
      }

      if (this.host != null) {
         host = this.host;
      }

      if (this.uri != null) {
         if (this.uri instanceof String) {
            uri = (String)this.uri;
         } else {
            uri = ((UriTemplate)this.uri).expandToString(this.templateParams(), this.client.options.getTemplateExpandOptions());
         }
      }

      if (this.queryParams != null) {
         uri = buildUri(uri, this.queryParams);
      }

      RequestOptions requestOptions = new RequestOptions();
      if (protocol != null && !protocol.equals("http") && !protocol.equals("https")) {
         URI tmp = new URI(protocol, (String)null, host, port, uri, (String)null, (String)null);
         requestOptions.setServer(this.serverAddress).setMethod(this.method).setAbsoluteURI(tmp.toString());
      } else {
         requestOptions.setServer(this.serverAddress).setMethod(this.method).setHost(host).setPort(port).setURI(uri);
         if (ssl != null) {
            requestOptions.setSsl(ssl);
         }
      }

      if (this.virtualHost != null) {
         if (requestOptions.getServer() == null) {
            requestOptions.setServer(SocketAddress.inetSocketAddress(requestOptions.getPort(), requestOptions.getHost()));
         }

         requestOptions.setHost(this.virtualHost);
      }

      this.mergeHeaders(requestOptions);
      if (this.timeout >= 0L) {
         requestOptions.setTimeout(this.timeout);
      }

      if (this.idleTimeout >= 0L) {
         requestOptions.setIdleTimeout(this.idleTimeout);
      }

      if (this.connectTimeout >= 0L) {
         requestOptions.setConnectTimeout(this.connectTimeout);
      }

      requestOptions.setProxyOptions(this.proxyOptions);
      requestOptions.setTraceOperation(this.traceOperation);
      return requestOptions;
   }

   void send(String contentType, Object body, Handler handler) {
      HttpContext<T> ctx = this.client.createContext(handler);
      ctx.prepareRequest(this, contentType, body);
   }

   void mergeHeaders(RequestOptions options) {
      if (this.headers != null) {
         MultiMap tmp = options.getHeaders();
         if (tmp == null) {
            tmp = MultiMap.caseInsensitiveMultiMap();
            options.setHeaders(tmp);
         }

         tmp.addAll(this.headers);
      }

   }

   private static String buildUri(String uri, MultiMap queryParams) {
      QueryStringDecoder decoder = new QueryStringDecoder(uri);
      QueryStringEncoder encoder = new QueryStringEncoder(decoder.rawPath());
      decoder.parameters().forEach((name, values) -> {
         for(String value : values) {
            encoder.addParam(name, value);
         }

      });
      queryParams.forEach((param) -> encoder.addParam((String)param.getKey(), (String)param.getValue()));
      uri = encoder.toString();
      return uri;
   }
}
