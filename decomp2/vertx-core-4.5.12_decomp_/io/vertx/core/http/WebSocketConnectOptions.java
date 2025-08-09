package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@DataObject
@JsonGen(
   publicConverter = false
)
public class WebSocketConnectOptions extends RequestOptions {
   public static final ProxyOptions DEFAULT_PROXY_OPTIONS = null;
   public static final WebsocketVersion DEFAULT_VERSION;
   public static final List DEFAULT_SUB_PROTOCOLS;
   public static final boolean DEFAULT_ALLOW_ORIGIN_HEADER = true;
   public static final boolean DEFAULT_REGISTER_WRITE_HANDLERS = false;
   private ProxyOptions proxyOptions;
   private WebsocketVersion version;
   private List subProtocols;
   private boolean allowOriginHeader;
   private boolean registerWriteHandlers;

   public WebSocketConnectOptions() {
      this.proxyOptions = DEFAULT_PROXY_OPTIONS;
      this.version = DEFAULT_VERSION;
      this.subProtocols = DEFAULT_SUB_PROTOCOLS;
      this.allowOriginHeader = true;
      this.registerWriteHandlers = false;
   }

   public WebSocketConnectOptions(WebSocketConnectOptions other) {
      super((RequestOptions)other);
      this.proxyOptions = other.proxyOptions != null ? new ProxyOptions(other.proxyOptions) : null;
      this.version = other.version;
      this.subProtocols = other.subProtocols;
      this.allowOriginHeader = other.allowOriginHeader;
      this.registerWriteHandlers = other.registerWriteHandlers;
   }

   public WebSocketConnectOptions(JsonObject json) {
      super(json);
      WebSocketConnectOptionsConverter.fromJson(json, this);
   }

   public WebsocketVersion getVersion() {
      return this.version;
   }

   public WebSocketConnectOptions setVersion(WebsocketVersion version) {
      this.version = version;
      return this;
   }

   public List getSubProtocols() {
      return this.subProtocols;
   }

   public WebSocketConnectOptions setSubProtocols(List subProtocols) {
      this.subProtocols = subProtocols;
      return this;
   }

   public WebSocketConnectOptions addSubProtocol(String subprotocol) {
      if (this.subProtocols == null) {
         this.subProtocols = new ArrayList();
      }

      this.subProtocols.add(subprotocol);
      return this;
   }

   public ProxyOptions getProxyOptions() {
      return this.proxyOptions;
   }

   public RequestOptions setProxyOptions(ProxyOptions proxyOptions) {
      this.proxyOptions = proxyOptions;
      return this;
   }

   public boolean getAllowOriginHeader() {
      return this.allowOriginHeader;
   }

   public WebSocketConnectOptions setAllowOriginHeader(boolean allowOriginHeader) {
      this.allowOriginHeader = allowOriginHeader;
      return this;
   }

   public WebSocketConnectOptions setHost(String host) {
      return (WebSocketConnectOptions)super.setHost(host);
   }

   public WebSocketConnectOptions setPort(Integer port) {
      return (WebSocketConnectOptions)super.setPort(port);
   }

   public WebSocketConnectOptions setSsl(Boolean ssl) {
      return (WebSocketConnectOptions)super.setSsl(ssl);
   }

   public WebSocketConnectOptions setURI(String uri) {
      return (WebSocketConnectOptions)super.setURI(uri);
   }

   public WebSocketConnectOptions setTimeout(long timeout) {
      return (WebSocketConnectOptions)super.setTimeout(timeout);
   }

   public WebSocketConnectOptions setConnectTimeout(long timeout) {
      return (WebSocketConnectOptions)super.setConnectTimeout(timeout);
   }

   public WebSocketConnectOptions setIdleTimeout(long timeout) {
      return (WebSocketConnectOptions)super.setIdleTimeout(timeout);
   }

   public WebSocketConnectOptions addHeader(String key, String value) {
      return (WebSocketConnectOptions)super.addHeader(key, value);
   }

   public WebSocketConnectOptions addHeader(CharSequence key, CharSequence value) {
      return (WebSocketConnectOptions)super.addHeader(key, value);
   }

   public WebSocketConnectOptions addHeader(CharSequence key, Iterable values) {
      return (WebSocketConnectOptions)super.addHeader(key, values);
   }

   public WebSocketConnectOptions putHeader(String key, String value) {
      return (WebSocketConnectOptions)super.putHeader(key, value);
   }

   public WebSocketConnectOptions putHeader(CharSequence key, CharSequence value) {
      return (WebSocketConnectOptions)super.putHeader(key, value);
   }

   public WebSocketConnectOptions putHeader(CharSequence key, Iterable values) {
      return (WebSocketConnectOptions)super.putHeader(key, values);
   }

   @GenIgnore
   public WebSocketConnectOptions setHeaders(MultiMap headers) {
      return (WebSocketConnectOptions)super.setHeaders(headers);
   }

   public WebSocketConnectOptions setServer(SocketAddress server) {
      return (WebSocketConnectOptions)super.setServer(server);
   }

   public WebSocketConnectOptions setMethod(HttpMethod method) {
      return (WebSocketConnectOptions)super.setMethod(method);
   }

   public WebSocketConnectOptions setFollowRedirects(Boolean followRedirects) {
      return (WebSocketConnectOptions)super.setFollowRedirects(followRedirects);
   }

   public WebSocketConnectOptions setAbsoluteURI(String absoluteURI) {
      URI uri;
      try {
         uri = new URI(absoluteURI);
      } catch (URISyntaxException e) {
         throw new IllegalArgumentException(e);
      }

      this.setAbsoluteURI(uri);
      return this;
   }

   public WebSocketConnectOptions setAbsoluteURI(URL url) {
      URI uri;
      try {
         uri = url.toURI();
      } catch (URISyntaxException e) {
         throw new IllegalArgumentException(e);
      }

      this.setAbsoluteURI(uri);
      return this;
   }

   private void setAbsoluteURI(URI uri) {
      String scheme = uri.getScheme();
      if (!"ws".equals(scheme) && !"wss".equals(scheme)) {
         throw new IllegalArgumentException("Scheme: " + scheme);
      } else {
         boolean ssl = scheme.length() == 3;
         int port = uri.getPort();
         if (port == -1) {
            port = ssl ? 443 : 80;
         }

         StringBuilder relativeUri = new StringBuilder();
         if (uri.getRawPath() != null) {
            relativeUri.append(uri.getRawPath());
         }

         if (uri.getRawQuery() != null) {
            relativeUri.append('?').append(uri.getRawQuery());
         }

         if (uri.getRawFragment() != null) {
            relativeUri.append('#').append(uri.getRawFragment());
         }

         this.setHost(uri.getHost());
         this.setPort(port);
         this.setSsl(ssl);
         this.setURI(relativeUri.toString());
      }
   }

   public WebSocketConnectOptions setTraceOperation(String op) {
      return (WebSocketConnectOptions)super.setTraceOperation(op);
   }

   public JsonObject toJson() {
      JsonObject json = super.toJson();
      WebSocketConnectOptionsConverter.toJson(this, json);
      return json;
   }

   public boolean isRegisterWriteHandlers() {
      return this.registerWriteHandlers;
   }

   public WebSocketConnectOptions setRegisterWriteHandlers(boolean registerWriteHandlers) {
      this.registerWriteHandlers = registerWriteHandlers;
      return this;
   }

   static {
      DEFAULT_VERSION = WebsocketVersion.V13;
      DEFAULT_SUB_PROTOCOLS = null;
   }
}
