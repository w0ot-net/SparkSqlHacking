package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.MultiMap;
import io.vertx.core.VertxException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@DataObject
@JsonGen(
   publicConverter = false
)
public class RequestOptions {
   public static final ProxyOptions DEFAULT_PROXY_OPTIONS = null;
   public static final SocketAddress DEFAULT_SERVER = null;
   public static final HttpMethod DEFAULT_HTTP_METHOD;
   public static final String DEFAULT_HOST;
   public static final Integer DEFAULT_PORT;
   public static final Boolean DEFAULT_SSL;
   public static final String DEFAULT_URI = "/";
   public static final boolean DEFAULT_FOLLOW_REDIRECTS = false;
   public static final long DEFAULT_TIMEOUT = -1L;
   public static final long DEFAULT_CONNECT_TIMEOUT = -1L;
   public static final long DEFAULT_IDLE_TIMEOUT = -1L;
   private ProxyOptions proxyOptions;
   private SocketAddress server;
   private HttpMethod method;
   private String host;
   private Integer port;
   private Boolean ssl;
   private String uri;
   private MultiMap headers;
   private boolean followRedirects;
   private long timeout;
   private long connectTimeout;
   private long idleTimeout;
   private String traceOperation;

   public RequestOptions() {
      this.proxyOptions = DEFAULT_PROXY_OPTIONS;
      this.server = DEFAULT_SERVER;
      this.method = DEFAULT_HTTP_METHOD;
      this.host = DEFAULT_HOST;
      this.port = DEFAULT_PORT;
      this.ssl = DEFAULT_SSL;
      this.uri = "/";
      this.followRedirects = false;
      this.timeout = -1L;
      this.connectTimeout = -1L;
      this.idleTimeout = -1L;
      this.traceOperation = null;
   }

   public RequestOptions(RequestOptions other) {
      this.setProxyOptions(other.proxyOptions);
      this.setServer(other.server);
      this.setMethod(other.method);
      this.setHost(other.host);
      this.setPort(other.port);
      this.setSsl(other.ssl);
      this.setURI(other.uri);
      this.setFollowRedirects(other.followRedirects);
      this.setTimeout(other.timeout);
      this.setIdleTimeout(other.idleTimeout);
      this.setConnectTimeout(other.connectTimeout);
      if (other.headers != null) {
         this.setHeaders(MultiMap.caseInsensitiveMultiMap().setAll(other.headers));
      }

      this.setTraceOperation(other.traceOperation);
   }

   public RequestOptions(JsonObject json) {
      this();
      RequestOptionsConverter.fromJson(json, this);
      String method = json.getString("method");
      if (method != null) {
         this.setMethod(HttpMethod.valueOf(method));
      }

      JsonObject server = json.getJsonObject("server");
      if (server != null) {
         Integer port = server.getInteger("port", 80);
         String host = server.getString("host");
         String path = server.getString("path");
         if (host != null) {
            this.server = SocketAddress.inetSocketAddress(port, host);
         } else if (path != null) {
            this.server = SocketAddress.domainSocketAddress(path);
         }
      }

      JsonObject headers = json.getJsonObject("headers");
      if (headers != null) {
         for(Map.Entry entry : headers) {
            Object value = entry.getValue();
            if (value instanceof String) {
               this.addHeader((String)entry.getKey(), (String)value);
            } else if (value instanceof Iterable) {
               for(Object subValue : (Iterable)value) {
                  if (subValue instanceof String) {
                     this.addHeader((String)entry.getKey(), (String)subValue);
                  }
               }
            }
         }
      }

   }

   public ProxyOptions getProxyOptions() {
      return this.proxyOptions;
   }

   public RequestOptions setProxyOptions(ProxyOptions proxyOptions) {
      this.proxyOptions = proxyOptions;
      return this;
   }

   public SocketAddress getServer() {
      return this.server;
   }

   public RequestOptions setServer(SocketAddress server) {
      this.server = server;
      return this;
   }

   @GenIgnore
   public HttpMethod getMethod() {
      return this.method;
   }

   @GenIgnore
   public RequestOptions setMethod(HttpMethod method) {
      this.method = method;
      return this;
   }

   public String getHost() {
      return this.host;
   }

   public RequestOptions setHost(String host) {
      this.host = host;
      return this;
   }

   public Integer getPort() {
      return this.port;
   }

   public RequestOptions setPort(Integer port) {
      this.port = port;
      return this;
   }

   public Boolean isSsl() {
      return this.ssl;
   }

   public RequestOptions setSsl(Boolean ssl) {
      this.ssl = ssl;
      return this;
   }

   public String getURI() {
      return this.uri;
   }

   public RequestOptions setURI(String uri) {
      this.uri = uri;
      return this;
   }

   public Boolean getFollowRedirects() {
      return this.followRedirects;
   }

   public RequestOptions setFollowRedirects(Boolean followRedirects) {
      this.followRedirects = followRedirects;
      return this;
   }

   public long getTimeout() {
      return this.timeout;
   }

   public RequestOptions setTimeout(long timeout) {
      this.timeout = timeout;
      return this;
   }

   public long getConnectTimeout() {
      return this.connectTimeout;
   }

   public RequestOptions setConnectTimeout(long timeout) {
      this.connectTimeout = timeout;
      return this;
   }

   public long getIdleTimeout() {
      return this.idleTimeout;
   }

   public RequestOptions setIdleTimeout(long timeout) {
      this.idleTimeout = timeout;
      return this;
   }

   private URL parseUrl(String surl) {
      try {
         return new URL(surl);
      } catch (MalformedURLException e) {
         throw new VertxException("Invalid url: " + surl, e);
      }
   }

   public RequestOptions setAbsoluteURI(String absoluteURI) {
      Objects.requireNonNull(absoluteURI, "Cannot set a null absolute URI");
      URL url = this.parseUrl(absoluteURI);
      return this.setAbsoluteURI(url);
   }

   @GenIgnore({"permitted-type"})
   public RequestOptions setAbsoluteURI(URL url) {
      Objects.requireNonNull(url, "Cannot set a null absolute URI");
      Boolean ssl = Boolean.FALSE;
      int port = url.getPort();
      String relativeUri = url.getPath().isEmpty() ? "/" + url.getFile() : url.getFile();
      switch (url.getProtocol()) {
         case "http":
            if (port == -1) {
               port = 80;
            }
            break;
         case "https":
            ssl = Boolean.TRUE;
            if (port == -1) {
               port = 443;
            }
            break;
         default:
            throw new IllegalArgumentException();
      }

      this.uri = relativeUri;
      this.port = port;
      this.ssl = ssl;
      this.host = url.getHost();
      return this;
   }

   @GenIgnore
   public RequestOptions addHeader(String key, String value) {
      return this.addHeader((CharSequence)key, (CharSequence)value);
   }

   @GenIgnore
   public RequestOptions addHeader(CharSequence key, CharSequence value) {
      this.checkHeaders();
      Objects.requireNonNull(key, "no null key accepted");
      Objects.requireNonNull(value, "no null value accepted");
      this.headers.add(key, value);
      return this;
   }

   @GenIgnore
   public RequestOptions addHeader(CharSequence key, Iterable values) {
      this.checkHeaders();
      Objects.requireNonNull(key, "no null key accepted");
      Objects.requireNonNull(values, "no null values accepted");
      this.headers.add(key, values);
      return this;
   }

   @GenIgnore
   public RequestOptions putHeader(String key, String value) {
      return this.putHeader((CharSequence)key, (CharSequence)value);
   }

   @GenIgnore
   public RequestOptions putHeader(CharSequence key, CharSequence value) {
      this.checkHeaders();
      this.headers.set(key, value);
      return this;
   }

   @GenIgnore
   public RequestOptions putHeader(CharSequence key, Iterable values) {
      this.checkHeaders();
      this.headers.set(key, values);
      return this;
   }

   @GenIgnore
   public RequestOptions removeHeader(String key) {
      return this.removeHeader((CharSequence)key);
   }

   @GenIgnore
   public RequestOptions removeHeader(CharSequence key) {
      if (this.headers != null) {
         this.headers.remove(key);
      }

      return this;
   }

   @GenIgnore
   public RequestOptions setHeaders(MultiMap headers) {
      this.headers = headers;
      return this;
   }

   @GenIgnore
   public MultiMap getHeaders() {
      return this.headers;
   }

   private void checkHeaders() {
      if (this.headers == null) {
         this.headers = HttpHeaders.headers();
      }

   }

   public String getTraceOperation() {
      return this.traceOperation;
   }

   public RequestOptions setTraceOperation(String op) {
      this.traceOperation = op;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      RequestOptionsConverter.toJson(this, json);
      if (this.method != null) {
         json.put("method", this.method.name());
      }

      if (this.server != null) {
         JsonObject server = new JsonObject();
         if (this.server.isInetSocket()) {
            server.put("host", this.server.host());
            server.put("port", this.server.port());
         } else {
            server.put("path", this.server.path());
         }

         json.put("server", server);
      }

      if (this.headers != null) {
         JsonObject headers = new JsonObject();

         for(String name : this.headers.names()) {
            List<String> values = this.headers.getAll(name);
            if (values.size() == 1) {
               headers.put(name, values.iterator().next());
            } else {
               headers.put(name, values);
            }
         }

         json.put("headers", headers);
      }

      return json;
   }

   static {
      DEFAULT_HTTP_METHOD = HttpMethod.GET;
      DEFAULT_HOST = null;
      DEFAULT_PORT = null;
      DEFAULT_SSL = null;
   }
}
