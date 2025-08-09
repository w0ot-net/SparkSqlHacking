package io.vertx.core.http.impl;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.observability.HttpRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

public class Http2ServerRequest extends HttpServerRequestInternal implements Http2ServerStreamHandler, HttpRequest {
   private static final Logger log = LoggerFactory.getLogger(Http1xServerRequest.class);
   protected final ContextInternal context;
   protected final Http2ServerStream stream;
   protected final Http2ServerResponse response;
   private final String serverOrigin;
   private final MultiMap headersMap;
   private Charset paramsCharset;
   private MultiMap params;
   private boolean semicolonIsNormalCharInParams;
   private String absoluteURI;
   private MultiMap attributes;
   private HttpEventHandler eventHandler;
   private boolean ended;
   private Handler uploadHandler;
   private boolean expectMultipart;
   private HttpPostRequestDecoder postRequestDecoder;
   private Handler customFrameHandler;
   private Handler streamPriorityHandler;

   Http2ServerRequest(Http2ServerStream stream, String serverOrigin, Http2Headers headers, String contentEncoding) {
      this.paramsCharset = StandardCharsets.UTF_8;
      this.context = stream.context;
      this.stream = stream;
      this.response = new Http2ServerResponse((Http2ServerConnection)stream.conn, stream, false, contentEncoding);
      this.serverOrigin = serverOrigin;
      this.headersMap = new Http2HeadersAdaptor(headers);
   }

   private HttpEventHandler eventHandler(boolean create) {
      if (this.eventHandler == null && create) {
         this.eventHandler = new HttpEventHandler(this.context);
      }

      return this.eventHandler;
   }

   public void dispatch(Handler handler) {
      this.context.emit(this, handler);
   }

   public void handleException(Throwable cause) {
      boolean notify;
      synchronized((Http2ServerConnection)this.stream.conn) {
         notify = !this.ended;
      }

      if (notify) {
         this.notifyException(cause);
      }

      this.response.handleException(cause);
   }

   private void notifyException(Throwable failure) {
      InterfaceHttpData upload = null;
      HttpEventHandler handler;
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (this.postRequestDecoder != null) {
            upload = this.postRequestDecoder.currentPartialHttpData();
         }

         handler = this.eventHandler;
      }

      if (handler != null) {
         handler.handleException(failure);
      }

      if (upload instanceof NettyFileUpload) {
         ((NettyFileUpload)upload).handleException(failure);
      }

   }

   public void handleClose() {
      this.response.handleClose();
   }

   public void handleCustomFrame(HttpFrame frame) {
      if (this.customFrameHandler != null) {
         this.customFrameHandler.handle(frame);
      }

   }

   public void handleData(Buffer data) {
      if (this.postRequestDecoder != null) {
         try {
            this.postRequestDecoder.offer(new DefaultHttpContent(data.getByteBuf()));
         } catch (HttpPostRequestDecoder.TooLongFormFieldException | HttpPostRequestDecoder.TooManyFormFieldsException | HttpPostRequestDecoder.ErrorDataDecoderException e) {
            this.postRequestDecoder.destroy();
            this.postRequestDecoder = null;
            this.handleException(e);
         }
      }

      HttpEventHandler handler = this.eventHandler;
      if (handler != null) {
         handler.handleChunk(data);
      }

   }

   public void handleEnd(MultiMap trailers) {
      HttpEventHandler handler;
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.ended = true;
         if (this.postRequestDecoder != null) {
            try {
               this.postRequestDecoder.offer(LastHttpContent.EMPTY_LAST_CONTENT);

               while(this.postRequestDecoder.hasNext()) {
                  InterfaceHttpData data = this.postRequestDecoder.next();
                  if (data instanceof Attribute) {
                     Attribute attr = (Attribute)data;

                     try {
                        this.formAttributes().add(attr.getName(), attr.getValue());
                     } catch (Exception e) {
                        this.handleException(e);
                     } finally {
                        attr.release();
                     }
                  }
               }
            } catch (HttpPostRequestDecoder.EndOfDataDecoderException var24) {
            } catch (Exception e) {
               this.handleException(e);
            } finally {
               this.postRequestDecoder.destroy();
               this.postRequestDecoder = null;
            }
         }

         handler = this.eventHandler;
      }

      if (handler != null) {
         handler.handleEnd();
      }

   }

   public void handleReset(long errorCode) {
      boolean notify;
      synchronized((Http2ServerConnection)this.stream.conn) {
         notify = !this.ended;
         this.ended = true;
      }

      if (notify) {
         this.notifyException(new StreamResetException(errorCode));
      }

      this.response.handleReset(errorCode);
   }

   private void checkEnded() {
      if (this.ended) {
         throw new IllegalStateException("Request has already been read");
      }
   }

   public HttpMethod method() {
      return this.stream.method;
   }

   public int id() {
      return this.stream.id();
   }

   public Object metric() {
      return this.stream.metric();
   }

   public Context context() {
      return this.context;
   }

   public HttpServerRequest exceptionHandler(Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.exceptionHandler(handler);
         }

         return this;
      }
   }

   public HttpServerRequest handler(Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.chunkHandler(handler);
         }

         return this;
      }
   }

   public HttpServerRequest pause() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.checkEnded();
         this.stream.doPause();
         return this;
      }
   }

   public HttpServerRequest resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public HttpServerRequest fetch(long amount) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.checkEnded();
         this.stream.doFetch(amount);
         return this;
      }
   }

   public HttpServerRequest endHandler(Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.endHandler(handler);
         }

         return this;
      }
   }

   public HttpVersion version() {
      return HttpVersion.HTTP_2;
   }

   public String uri() {
      return this.stream.uri;
   }

   public String path() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         return this.stream.uri != null ? HttpUtils.parsePath(this.stream.uri) : null;
      }
   }

   public String query() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         return this.stream.uri == null ? null : HttpUtils.parseQuery(this.stream.uri);
      }
   }

   public String scheme() {
      return this.stream.scheme;
   }

   public String host() {
      return this.stream.host;
   }

   public @Nullable HostAndPort authority() {
      return this.stream.authority;
   }

   public long bytesRead() {
      return this.stream.bytesRead();
   }

   public Http2ServerResponse response() {
      return this.response;
   }

   public MultiMap headers() {
      return this.headersMap;
   }

   public HttpServerRequest setParamsCharset(String charset) {
      Objects.requireNonNull(charset, "Charset must not be null");
      Charset current = this.paramsCharset;
      this.paramsCharset = Charset.forName(charset);
      if (!this.paramsCharset.equals(current)) {
         this.params = null;
      }

      return this;
   }

   public String getParamsCharset() {
      return this.paramsCharset.name();
   }

   public MultiMap params(boolean semicolonIsNormalChar) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (this.params == null || semicolonIsNormalChar != this.semicolonIsNormalCharInParams) {
            this.params = HttpUtils.params(this.uri(), this.paramsCharset, semicolonIsNormalChar);
            this.semicolonIsNormalCharInParams = semicolonIsNormalChar;
         }

         return this.params;
      }
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return ((Http2ServerConnection)this.stream.conn).peerCertificateChain();
   }

   public SocketAddress remoteAddress() {
      return super.remoteAddress();
   }

   public String absoluteURI() {
      if (this.stream.method == HttpMethod.CONNECT) {
         return null;
      } else {
         synchronized((Http2ServerConnection)this.stream.conn) {
            if (this.absoluteURI == null) {
               this.absoluteURI = HttpUtils.absoluteURI(this.serverOrigin, this);
            }

            return this.absoluteURI;
         }
      }
   }

   public Future toNetSocket() {
      return this.response.netSocket();
   }

   public HttpServerRequest setExpectMultipart(boolean expect) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.checkEnded();
         this.expectMultipart = expect;
         if (expect) {
            if (this.postRequestDecoder == null) {
               String contentType = this.headersMap.get((CharSequence)HttpHeaderNames.CONTENT_TYPE);
               if (contentType == null) {
                  throw new IllegalStateException("Request must have a content-type header to decode a multipart request");
               }

               if (!HttpUtils.isValidMultipartContentType(contentType)) {
                  throw new IllegalStateException("Request must have a valid content-type header to decode a multipart request");
               }

               if (!HttpUtils.isValidMultipartMethod(this.stream.method.toNetty())) {
                  throw new IllegalStateException("Request method must be one of POST, PUT, PATCH or DELETE to decode a multipart request");
               }

               io.netty.handler.codec.http.HttpRequest req = new DefaultHttpRequest(io.netty.handler.codec.http.HttpVersion.HTTP_1_1, this.stream.method.toNetty(), this.stream.uri);
               req.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);
               NettyFileUploadDataFactory factory = new NettyFileUploadDataFactory(this.context, this, () -> this.uploadHandler);
               HttpServerOptions options = ((Http2ServerConnection)this.stream.conn).options;
               factory.setMaxLimit((long)options.getMaxFormAttributeSize());
               int maxFields = options.getMaxFormFields();
               int maxBufferedBytes = options.getMaxFormBufferedBytes();
               this.postRequestDecoder = new HttpPostRequestDecoder(factory, req, HttpConstants.DEFAULT_CHARSET, maxFields, maxBufferedBytes);
            }
         } else {
            this.postRequestDecoder = null;
         }

         return this;
      }
   }

   public boolean isExpectMultipart() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         return this.expectMultipart;
      }
   }

   public HttpServerRequest uploadHandler(@Nullable Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         this.uploadHandler = handler;
         return this;
      }
   }

   public MultiMap formAttributes() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         if (this.attributes == null) {
            this.attributes = MultiMap.caseInsensitiveMultiMap();
         }

         return this.attributes;
      }
   }

   public String getFormAttribute(String attributeName) {
      return this.formAttributes().get(attributeName);
   }

   public int streamId() {
      return this.stream.id();
   }

   public Future toWebSocket() {
      return this.context.failedFuture("HTTP/2 request cannot be upgraded to a WebSocket");
   }

   public boolean isEnded() {
      synchronized((Http2ServerConnection)this.stream.conn) {
         return this.ended;
      }
   }

   public HttpServerRequest customFrameHandler(Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.customFrameHandler = handler;
         return this;
      }
   }

   public HttpConnection connection() {
      return this.stream.conn;
   }

   public synchronized Future body() {
      this.checkEnded();
      return this.eventHandler(true).body();
   }

   public synchronized Future end() {
      this.checkEnded();
      return this.eventHandler(true).end();
   }

   public StreamPriority streamPriority() {
      return this.stream.priority();
   }

   public HttpServerRequest streamPriorityHandler(Handler handler) {
      synchronized((Http2ServerConnection)this.stream.conn) {
         this.streamPriorityHandler = handler;
         return this;
      }
   }

   public DecoderResult decoderResult() {
      return DecoderResult.SUCCESS;
   }

   public void handlePriorityChange(StreamPriority streamPriority) {
      Handler<StreamPriority> handler;
      synchronized((Http2ServerConnection)this.stream.conn) {
         handler = this.streamPriorityHandler;
      }

      if (handler != null) {
         handler.handle(streamPriority);
      }

   }

   public Set cookies() {
      return this.response.cookies();
   }

   public Set cookies(String name) {
      return this.response.cookies().getAll(name);
   }

   public Cookie getCookie(String name) {
      return this.response.cookies().get(name);
   }

   public Cookie getCookie(String name, String domain, String path) {
      return this.response.cookies().get(name, domain, path);
   }

   public HttpServerRequest routed(String route) {
      this.stream.routed(route);
      return this;
   }
}
