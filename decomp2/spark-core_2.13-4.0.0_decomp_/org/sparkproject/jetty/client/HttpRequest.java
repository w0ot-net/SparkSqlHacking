package org.sparkproject.jetty.client;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.ContentProvider;
import org.sparkproject.jetty.client.api.ContentResponse;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.client.internal.RequestContentAdapter;
import org.sparkproject.jetty.client.util.FutureResponseListener;
import org.sparkproject.jetty.client.util.PathRequestContent;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Fields;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.URIUtil;

public class HttpRequest implements Request {
   private static final URI NULL_URI = URI.create("null:0");
   private final HttpFields.Mutable headers = HttpFields.build();
   private final Fields params = new Fields(true);
   private final List responseListeners = new ArrayList();
   private final AtomicReference aborted = new AtomicReference();
   private final HttpClient client;
   private final HttpConversation conversation;
   private Connection connection;
   private String scheme;
   private String host;
   private int port;
   private String path;
   private String query;
   private URI uri;
   private String method;
   private HttpVersion version;
   private boolean versionExplicit;
   private long idleTimeout;
   private long timeout;
   private long timeoutNanoTime;
   private Request.Content content;
   private boolean followRedirects;
   private List cookies;
   private Map attributes;
   private List requestListeners;
   private BiFunction pushListener;
   private Supplier trailers;
   private String upgradeProtocol;
   private Object tag;
   private boolean normalized;

   protected HttpRequest(HttpClient client, HttpConversation conversation, URI uri) {
      this.method = HttpMethod.GET.asString();
      this.version = HttpVersion.HTTP_1_1;
      this.idleTimeout = -1L;
      this.timeoutNanoTime = Long.MAX_VALUE;
      if (uri.getHost() == null) {
         throw new IllegalArgumentException(String.format("Invalid URI host: null (authority: %s)", uri.getRawAuthority()));
      } else {
         this.client = client;
         this.conversation = conversation;
         this.scheme = uri.getScheme();
         this.host = uri.getHost();
         this.port = HttpClient.normalizePort(this.scheme, uri.getPort());
         this.path = uri.getRawPath();
         this.query = uri.getRawQuery();
         this.extractParams(this.query);
         this.followRedirects(client.isFollowRedirects());
         HttpField acceptEncodingField = client.getAcceptEncodingField();
         if (acceptEncodingField != null) {
            this.headers.put(acceptEncodingField);
         }

         HttpField userAgentField = client.getUserAgentField();
         if (userAgentField != null) {
            this.headers.put(userAgentField);
         }

      }
   }

   HttpRequest copy(URI newURI) {
      if (newURI == null) {
         StringBuilder builder = new StringBuilder(64);
         URIUtil.appendSchemeHostPort(builder, this.getScheme(), this.getHost(), this.getPort());
         newURI = URI.create(builder.toString());
      }

      HttpRequest newRequest = this.copyInstance(newURI);
      newRequest.method(this.getMethod()).version(this.getVersion()).body(this.getBody()).idleTimeout(this.getIdleTimeout(), TimeUnit.MILLISECONDS).timeout(this.getTimeout(), TimeUnit.MILLISECONDS).followRedirects(this.isFollowRedirects()).tag(this.getTag()).headers((h) -> h.clear().add(this.getHeaders()).remove(EnumSet.of(HttpHeader.HOST, HttpHeader.EXPECT, HttpHeader.COOKIE, HttpHeader.AUTHORIZATION, HttpHeader.PROXY_AUTHORIZATION)));
      return newRequest;
   }

   HttpRequest copyInstance(URI newURI) {
      return new HttpRequest(this.getHttpClient(), this.getConversation(), newURI);
   }

   HttpClient getHttpClient() {
      return this.client;
   }

   public HttpConversation getConversation() {
      return this.conversation;
   }

   public Connection getConnection() {
      return this.connection;
   }

   void setConnection(Connection connection) {
      this.connection = connection;
   }

   public String getScheme() {
      return this.scheme;
   }

   public Request scheme(String scheme) {
      this.scheme = scheme;
      this.uri = null;
      return this;
   }

   public String getHost() {
      return this.host;
   }

   public Request host(String host) {
      this.host = host;
      this.uri = null;
      return this;
   }

   public int getPort() {
      return this.port;
   }

   public Request port(int port) {
      this.port = port;
      this.uri = null;
      return this;
   }

   public String getMethod() {
      return this.method;
   }

   public Request method(HttpMethod method) {
      return this.method(method.asString());
   }

   public Request method(String method) {
      this.method = ((String)Objects.requireNonNull(method)).toUpperCase(Locale.ENGLISH);
      return this;
   }

   public String getPath() {
      return this.path;
   }

   public Request path(String path) {
      URI uri = this.newURI(path);
      if (uri == null) {
         this.path = path;
         this.query = null;
      } else {
         String rawPath = uri.getRawPath();
         if (rawPath == null) {
            rawPath = "";
         }

         if (!rawPath.startsWith("/")) {
            rawPath = "/" + rawPath;
         }

         this.path = rawPath;
         String query = uri.getRawQuery();
         if (query != null) {
            this.query = query;
            this.params.clear();
            this.extractParams(query);
         }

         if (uri.isAbsolute()) {
            this.path = this.buildURI(false).toString();
         }
      }

      this.uri = null;
      return this;
   }

   public String getQuery() {
      return this.query;
   }

   public URI getURI() {
      if (this.uri == null) {
         this.uri = this.buildURI(true);
      }

      boolean isNullURI = this.uri == NULL_URI;
      return isNullURI ? null : this.uri;
   }

   public HttpVersion getVersion() {
      return this.version;
   }

   public boolean isVersionExplicit() {
      return this.versionExplicit;
   }

   public Request version(HttpVersion version) {
      this.version = (HttpVersion)Objects.requireNonNull(version);
      this.versionExplicit = true;
      return this;
   }

   public Request param(String name, String value) {
      return this.param(name, value, false);
   }

   private Request param(String name, String value, boolean fromQuery) {
      this.params.add(name, value);
      if (!fromQuery) {
         if (this.query != null) {
            String var10001 = this.query;
            this.query = var10001 + "&" + this.urlEncode(name) + "=" + this.urlEncode(value);
         } else {
            this.query = this.buildQuery();
         }

         this.uri = null;
      }

      return this;
   }

   public Fields getParams() {
      return new Fields(this.params, true);
   }

   public String getAgent() {
      return this.headers.get(HttpHeader.USER_AGENT);
   }

   public Request agent(String agent) {
      this.headers.put(HttpHeader.USER_AGENT, agent);
      return this;
   }

   public Request accept(String... accepts) {
      StringBuilder result = new StringBuilder();

      for(String accept : accepts) {
         if (result.length() > 0) {
            result.append(", ");
         }

         result.append(accept);
      }

      if (result.length() > 0) {
         this.headers.put(HttpHeader.ACCEPT, result.toString());
      }

      return this;
   }

   /** @deprecated */
   @Deprecated
   public Request header(String name, String value) {
      if (value == null) {
         this.headers.remove(name);
      } else {
         this.headers.add(name, value);
      }

      return this;
   }

   /** @deprecated */
   @Deprecated
   public Request header(HttpHeader header, String value) {
      if (value == null) {
         this.headers.remove(header);
      } else {
         this.headers.add(header, value);
      }

      return this;
   }

   public List getCookies() {
      return this.cookies != null ? this.cookies : Collections.emptyList();
   }

   public Request cookie(HttpCookie cookie) {
      if (this.cookies == null) {
         this.cookies = new ArrayList();
      }

      this.cookies.add(cookie);
      return this;
   }

   public Request tag(Object tag) {
      this.tag = tag;
      return this;
   }

   public Object getTag() {
      return this.tag;
   }

   public Request attribute(String name, Object value) {
      if (this.attributes == null) {
         this.attributes = new HashMap(4);
      }

      this.attributes.put(name, value);
      return this;
   }

   public Map getAttributes() {
      return this.attributes != null ? this.attributes : Collections.emptyMap();
   }

   public HttpFields getHeaders() {
      return this.headers;
   }

   public Request headers(Consumer consumer) {
      consumer.accept(this.headers);
      return this;
   }

   public HttpRequest addHeader(HttpField header) {
      this.headers.add(header);
      return this;
   }

   public List getRequestListeners(Class type) {
      if (type != null && this.requestListeners != null) {
         ArrayList<T> result = new ArrayList();

         for(Request.RequestListener listener : this.requestListeners) {
            if (type.isInstance(listener)) {
               result.add(listener);
            }
         }

         return result;
      } else {
         return this.requestListeners != null ? this.requestListeners : Collections.emptyList();
      }
   }

   public Request listener(Request.Listener listener) {
      return this.requestListener(listener);
   }

   public Request onRequestQueued(final Request.QueuedListener listener) {
      return this.requestListener(new Request.QueuedListener() {
         public void onQueued(Request request) {
            listener.onQueued(request);
         }
      });
   }

   public Request onRequestBegin(final Request.BeginListener listener) {
      return this.requestListener(new Request.BeginListener() {
         public void onBegin(Request request) {
            listener.onBegin(request);
         }
      });
   }

   public Request onRequestHeaders(final Request.HeadersListener listener) {
      return this.requestListener(new Request.HeadersListener() {
         public void onHeaders(Request request) {
            listener.onHeaders(request);
         }
      });
   }

   public Request onRequestCommit(final Request.CommitListener listener) {
      return this.requestListener(new Request.CommitListener() {
         public void onCommit(Request request) {
            listener.onCommit(request);
         }
      });
   }

   public Request onRequestContent(final Request.ContentListener listener) {
      return this.requestListener(new Request.ContentListener() {
         public void onContent(Request request, ByteBuffer content) {
            listener.onContent(request, content);
         }
      });
   }

   public Request onRequestSuccess(final Request.SuccessListener listener) {
      return this.requestListener(new Request.SuccessListener() {
         public void onSuccess(Request request) {
            listener.onSuccess(request);
         }
      });
   }

   public Request onRequestFailure(final Request.FailureListener listener) {
      return this.requestListener(new Request.FailureListener() {
         public void onFailure(Request request, Throwable failure) {
            listener.onFailure(request, failure);
         }
      });
   }

   private Request requestListener(Request.RequestListener listener) {
      if (this.requestListeners == null) {
         this.requestListeners = new ArrayList();
      }

      this.requestListeners.add(listener);
      return this;
   }

   public Request onResponseBegin(final Response.BeginListener listener) {
      this.responseListeners.add(new Response.BeginListener() {
         public void onBegin(Response response) {
            listener.onBegin(response);
         }
      });
      return this;
   }

   public Request onResponseHeader(final Response.HeaderListener listener) {
      this.responseListeners.add(new Response.HeaderListener() {
         public boolean onHeader(Response response, HttpField field) {
            return listener.onHeader(response, field);
         }
      });
      return this;
   }

   public Request onResponseHeaders(final Response.HeadersListener listener) {
      this.responseListeners.add(new Response.HeadersListener() {
         public void onHeaders(Response response) {
            listener.onHeaders(response);
         }
      });
      return this;
   }

   public Request onResponseContent(final Response.ContentListener listener) {
      this.responseListeners.add(new Response.ContentListener() {
         public void onContent(Response response, ByteBuffer content) {
            listener.onContent(response, content);
         }
      });
      return this;
   }

   public Request onResponseContentAsync(final Response.AsyncContentListener listener) {
      this.responseListeners.add(new Response.AsyncContentListener() {
         public void onContent(Response response, ByteBuffer content, Callback callback) {
            listener.onContent(response, content, callback);
         }
      });
      return this;
   }

   public Request onResponseContentDemanded(final Response.DemandedContentListener listener) {
      this.responseListeners.add(new Response.DemandedContentListener() {
         public void onBeforeContent(Response response, LongConsumer demand) {
            listener.onBeforeContent(response, demand);
         }

         public void onContent(Response response, LongConsumer demand, ByteBuffer content, Callback callback) {
            listener.onContent(response, demand, content, callback);
         }
      });
      return this;
   }

   public Request onResponseSuccess(final Response.SuccessListener listener) {
      this.responseListeners.add(new Response.SuccessListener() {
         public void onSuccess(Response response) {
            listener.onSuccess(response);
         }
      });
      return this;
   }

   public Request onResponseFailure(final Response.FailureListener listener) {
      this.responseListeners.add(new Response.FailureListener() {
         public void onFailure(Response response, Throwable failure) {
            listener.onFailure(response, failure);
         }
      });
      return this;
   }

   public Request onComplete(final Response.CompleteListener listener) {
      this.responseListeners.add(new Response.CompleteListener() {
         public void onComplete(Result result) {
            listener.onComplete(result);
         }
      });
      return this;
   }

   public Request pushListener(BiFunction listener) {
      this.pushListener = listener;
      return this;
   }

   public HttpRequest trailers(Supplier trailers) {
      this.trailers = trailers;
      return this;
   }

   public HttpRequest upgradeProtocol(String upgradeProtocol) {
      this.upgradeProtocol = upgradeProtocol;
      return this;
   }

   public ContentProvider getContent() {
      return this.content instanceof RequestContentAdapter ? ((RequestContentAdapter)this.content).getContentProvider() : null;
   }

   public Request content(ContentProvider content) {
      return this.content(content, (String)null);
   }

   public Request content(ContentProvider content, String contentType) {
      if (contentType != null) {
         this.headers.put(HttpHeader.CONTENT_TYPE, contentType);
      }

      return this.body(ContentProvider.toRequestContent(content));
   }

   public Request.Content getBody() {
      return this.content;
   }

   public Request body(Request.Content content) {
      this.content = content;
      return this;
   }

   public Request file(Path file) throws IOException {
      return this.file(file, "application/octet-stream");
   }

   public Request file(Path file, String contentType) throws IOException {
      return this.body(new PathRequestContent(contentType, file));
   }

   public boolean isFollowRedirects() {
      return this.followRedirects;
   }

   public Request followRedirects(boolean follow) {
      this.followRedirects = follow;
      return this;
   }

   public long getIdleTimeout() {
      return this.idleTimeout;
   }

   public Request idleTimeout(long timeout, TimeUnit unit) {
      this.idleTimeout = unit.toMillis(timeout);
      return this;
   }

   public long getTimeout() {
      return this.timeout;
   }

   public Request timeout(long timeout, TimeUnit unit) {
      this.timeout = unit.toMillis(timeout);
      return this;
   }

   public ContentResponse send() throws InterruptedException, TimeoutException, ExecutionException {
      FutureResponseListener listener = new FutureResponseListener(this);
      this.send(listener);

      try {
         return listener.get();
      } catch (ExecutionException x) {
         if (x.getCause() instanceof TimeoutException) {
            TimeoutException t = (TimeoutException)x.getCause();
            this.abort(t);
            throw t;
         } else {
            this.abort(x);
            throw x;
         }
      } catch (Throwable x) {
         this.abort(x);
         throw x;
      }
   }

   public void send(Response.CompleteListener listener) {
      HttpClient var10001 = this.client;
      Objects.requireNonNull(var10001);
      this.sendAsync(var10001::send, listener);
   }

   void sendAsync(HttpDestination destination, Response.CompleteListener listener) {
      Objects.requireNonNull(destination);
      this.sendAsync(destination::send, listener);
   }

   private void sendAsync(BiConsumer sender, Response.CompleteListener listener) {
      if (listener != null) {
         this.responseListeners.add(listener);
      }

      sender.accept(this, this.responseListeners);
   }

   void sent() {
      if (this.timeoutNanoTime == Long.MAX_VALUE) {
         long timeout = this.getTimeout();
         if (timeout > 0L) {
            this.timeoutNanoTime = NanoTime.now() + TimeUnit.MILLISECONDS.toNanos(timeout);
         }
      }

   }

   long getTimeoutNanoTime() {
      return this.timeoutNanoTime;
   }

   protected List getResponseListeners() {
      return this.responseListeners;
   }

   public BiFunction getPushListener() {
      return this.pushListener;
   }

   public Supplier getTrailers() {
      return this.trailers;
   }

   public String getUpgradeProtocol() {
      return this.upgradeProtocol;
   }

   public boolean abort(Throwable cause) {
      return this.aborted.compareAndSet((Object)null, (Throwable)Objects.requireNonNull(cause)) ? this.conversation.abort(cause) : false;
   }

   public Throwable getAbortCause() {
      return (Throwable)this.aborted.get();
   }

   boolean normalized() {
      boolean result = this.normalized;
      this.normalized = true;
      return result;
   }

   private String buildQuery() {
      StringBuilder result = new StringBuilder();
      Iterator<Fields.Field> iterator = this.params.iterator();

      while(iterator.hasNext()) {
         Fields.Field field = (Fields.Field)iterator.next();
         List<String> values = field.getValues();

         for(int i = 0; i < values.size(); ++i) {
            if (i > 0) {
               result.append("&");
            }

            result.append(field.getName()).append("=");
            result.append(this.urlEncode((String)values.get(i)));
         }

         if (iterator.hasNext()) {
            result.append("&");
         }
      }

      return result.toString();
   }

   private String urlEncode(String value) {
      return value == null ? "" : URLEncoder.encode(value, StandardCharsets.UTF_8);
   }

   private void extractParams(String query) {
      if (query != null) {
         for(String nameValue : query.split("&")) {
            String[] parts = nameValue.split("=");
            if (parts.length > 0) {
               String name = this.urlDecode(parts[0]);
               if (name.trim().length() != 0) {
                  this.param(name, parts.length < 2 ? "" : this.urlDecode(parts[1]), true);
               }
            }
         }
      }

   }

   private String urlDecode(String value) {
      return URLDecoder.decode(value, StandardCharsets.UTF_8);
   }

   private URI buildURI(boolean withQuery) {
      String path = this.getPath();
      String query = this.getQuery();
      if (query != null && withQuery) {
         path = path + "?" + query;
      }

      URI result = this.newURI(path);
      if (result == null) {
         return NULL_URI;
      } else {
         if (!result.isAbsolute()) {
            String var10000 = (new Origin(this.getScheme(), this.getHost(), this.getPort())).asString();
            result = URI.create(var10000 + path);
         }

         return result;
      }
   }

   private URI newURI(String path) {
      try {
         if ("*".equals(path)) {
            return null;
         } else {
            URI result = new URI(path);
            return result.isOpaque() ? null : result;
         }
      } catch (URISyntaxException var3) {
         return null;
      }
   }

   public String toString() {
      return String.format("%s[%s %s %s]@%x", this.getClass().getSimpleName(), this.getMethod(), this.getPath(), this.getVersion(), this.hashCode());
   }
}
