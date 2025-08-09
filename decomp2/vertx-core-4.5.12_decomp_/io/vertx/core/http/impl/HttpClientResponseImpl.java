package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.headers.HeadersAdaptor;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.ArrayList;
import java.util.List;

public class HttpClientResponseImpl implements HttpClientResponse {
   private static final Logger log = LoggerFactory.getLogger(HttpClientResponseImpl.class);
   private final HttpVersion version;
   private final int statusCode;
   private final String statusMessage;
   private final HttpClientRequestBase request;
   private final HttpConnection conn;
   private final HttpClientStream stream;
   private HttpEventHandler eventHandler;
   private Handler customFrameHandler;
   private Handler priorityHandler;
   private MultiMap headers;
   private MultiMap trailers;
   private List cookies;
   private NetSocket netSocket;

   HttpClientResponseImpl(HttpClientRequestBase request, HttpVersion version, HttpClientStream stream, int statusCode, String statusMessage, MultiMap headers) {
      this.version = version;
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
      this.request = request;
      this.stream = stream;
      this.conn = stream.connection();
      this.headers = headers;
   }

   private HttpEventHandler eventHandler(boolean create) {
      if (this.eventHandler == null && create) {
         this.eventHandler = new HttpEventHandler(this.request.context);
      }

      return this.eventHandler;
   }

   public HttpClientRequestBase request() {
      return this.request;
   }

   public NetSocket netSocket() {
      if (this.netSocket == null) {
         this.netSocket = HttpNetSocket.netSocket((ConnectionBase)this.conn, this.request.context, this, this.stream);
      }

      return this.netSocket;
   }

   public HttpVersion version() {
      return this.version;
   }

   public int statusCode() {
      return this.statusCode;
   }

   public String statusMessage() {
      return this.statusMessage;
   }

   public MultiMap headers() {
      return this.headers;
   }

   public String getHeader(String headerName) {
      return this.headers.get(headerName);
   }

   public String getHeader(CharSequence headerName) {
      return this.headers.get(headerName);
   }

   public MultiMap trailers() {
      synchronized(this.conn) {
         if (this.trailers == null) {
            this.trailers = new HeadersAdaptor(new DefaultHttpHeaders());
         }

         return this.trailers;
      }
   }

   public String getTrailer(String trailerName) {
      return this.trailers != null ? this.trailers.get(trailerName) : null;
   }

   public List cookies() {
      synchronized(this.conn) {
         if (this.cookies == null) {
            this.cookies = new ArrayList();
            this.cookies.addAll(this.headers().getAll(HttpHeaders.SET_COOKIE));
            if (this.trailers != null) {
               this.cookies.addAll(this.trailers.getAll(HttpHeaders.SET_COOKIE));
            }
         }

         return this.cookies;
      }
   }

   private void checkEnded() {
      if (this.trailers != null) {
         throw new IllegalStateException();
      }
   }

   public HttpClientResponse handler(Handler handler) {
      synchronized(this.conn) {
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

   public HttpClientResponse endHandler(Handler handler) {
      synchronized(this.conn) {
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

   public HttpClientResponse exceptionHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.exceptionHandler(handler);
         }

         return this;
      }
   }

   public HttpClientResponse pause() {
      this.stream.doPause();
      return this;
   }

   public HttpClientResponse resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public HttpClientResponse fetch(long amount) {
      this.stream.doFetch(amount);
      return this;
   }

   public HttpClientResponse customFrameHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         this.customFrameHandler = handler;
         return this;
      }
   }

   void handleUnknownFrame(HttpFrame frame) {
      synchronized(this.conn) {
         if (this.customFrameHandler != null) {
            this.customFrameHandler.handle(frame);
         }

      }
   }

   void handleChunk(Buffer data) {
      this.request.dataReceived();
      HttpEventHandler handler;
      synchronized(this.conn) {
         handler = this.eventHandler;
      }

      if (handler != null) {
         handler.handleChunk(data);
      }

   }

   void handleEnd(MultiMap trailers) {
      HttpEventHandler handler;
      synchronized(this.conn) {
         this.trailers = trailers;
         handler = this.eventHandler;
      }

      if (handler != null) {
         handler.handleEnd();
      }

   }

   void handleException(Throwable e) {
      HttpEventHandler handler;
      synchronized(this.conn) {
         if (this.trailers != null) {
            return;
         }

         handler = this.eventHandler;
      }

      if (handler != null) {
         handler.handleException(e);
      } else {
         log.error(e.getMessage(), e);
      }

   }

   public Future body() {
      return this.eventHandler(true).body();
   }

   public synchronized Future end() {
      this.checkEnded();
      return this.eventHandler(true).end();
   }

   public HttpClientResponse streamPriorityHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         this.priorityHandler = handler;
         return this;
      }
   }

   void handlePriorityChange(StreamPriority streamPriority) {
      Handler<StreamPriority> handler;
      synchronized(this.conn) {
         handler = this.priorityHandler;
      }

      if (handler != null) {
         handler.handle(streamPriority);
      }

   }
}
