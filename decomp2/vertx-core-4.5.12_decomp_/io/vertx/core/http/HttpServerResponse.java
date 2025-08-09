package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.Set;

@VertxGen
public interface HttpServerResponse extends WriteStream {
   HttpServerResponse exceptionHandler(Handler var1);

   HttpServerResponse setWriteQueueMaxSize(int var1);

   HttpServerResponse drainHandler(Handler var1);

   int getStatusCode();

   @Fluent
   HttpServerResponse setStatusCode(int var1);

   String getStatusMessage();

   @Fluent
   HttpServerResponse setStatusMessage(String var1);

   @Fluent
   HttpServerResponse setChunked(boolean var1);

   boolean isChunked();

   @CacheReturn
   MultiMap headers();

   @Fluent
   HttpServerResponse putHeader(String var1, String var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putHeader(CharSequence var1, CharSequence var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putHeader(String var1, Iterable var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putHeader(CharSequence var1, Iterable var2);

   @CacheReturn
   MultiMap trailers();

   @Fluent
   HttpServerResponse putTrailer(String var1, String var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putTrailer(CharSequence var1, CharSequence var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putTrailer(String var1, Iterable var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpServerResponse putTrailer(CharSequence var1, Iterable var2);

   @Fluent
   HttpServerResponse closeHandler(@Nullable Handler var1);

   @Fluent
   HttpServerResponse endHandler(@Nullable Handler var1);

   Future write(String var1, String var2);

   void write(String var1, String var2, Handler var3);

   Future write(String var1);

   void write(String var1, Handler var2);

   @Fluent
   HttpServerResponse writeContinue();

   Future writeEarlyHints(MultiMap var1);

   void writeEarlyHints(MultiMap var1, Handler var2);

   Future end(String var1);

   void end(String var1, Handler var2);

   Future end(String var1, String var2);

   void end(String var1, String var2, Handler var3);

   Future end(Buffer var1);

   void end(Buffer var1, Handler var2);

   Future end();

   default void send(Handler handler) {
      this.end((Handler)handler);
   }

   default Future send() {
      return this.end();
   }

   default void send(String body, Handler handler) {
      this.end(body, handler);
   }

   default Future send(String body) {
      return this.end(body);
   }

   default void send(Buffer body, Handler handler) {
      this.end(body, handler);
   }

   default Future send(Buffer body) {
      return this.end(body);
   }

   default void send(ReadStream body, Handler handler) {
      MultiMap headers = this.headers();
      if (headers == null || !headers.contains(HttpHeaders.CONTENT_LENGTH)) {
         this.setChunked(true);
      }

      body.pipeTo(this, handler);
   }

   default Future send(ReadStream body) {
      MultiMap headers = this.headers();
      if (headers == null || !headers.contains(HttpHeaders.CONTENT_LENGTH)) {
         this.setChunked(true);
      }

      return body.pipeTo(this);
   }

   default Future sendFile(String filename) {
      return this.sendFile(filename, 0L);
   }

   default Future sendFile(String filename, long offset) {
      return this.sendFile(filename, offset, Long.MAX_VALUE);
   }

   Future sendFile(String var1, long var2, long var4);

   @Fluent
   default HttpServerResponse sendFile(String filename, Handler resultHandler) {
      return this.sendFile(filename, 0L, resultHandler);
   }

   @Fluent
   default HttpServerResponse sendFile(String filename, long offset, Handler resultHandler) {
      return this.sendFile(filename, offset, Long.MAX_VALUE, resultHandler);
   }

   @Fluent
   HttpServerResponse sendFile(String var1, long var2, long var4, Handler var6);

   /** @deprecated */
   @Deprecated
   void close();

   boolean ended();

   boolean closed();

   boolean headWritten();

   @Fluent
   HttpServerResponse headersEndHandler(@Nullable Handler var1);

   @Fluent
   HttpServerResponse bodyEndHandler(@Nullable Handler var1);

   long bytesWritten();

   int streamId();

   /** @deprecated */
   @Deprecated
   @Fluent
   default HttpServerResponse push(HttpMethod method, String host, String path, Handler handler) {
      return this.push(method, (String)host, path, (MultiMap)null, handler);
   }

   /** @deprecated */
   @Deprecated
   default Future push(HttpMethod method, String host, String path) {
      return this.push(method, host, path, (MultiMap)null);
   }

   @Fluent
   default HttpServerResponse push(HttpMethod method, HostAndPort host, String path, Handler handler) {
      return this.push(method, (HostAndPort)host, path, (MultiMap)null, handler);
   }

   default Future push(HttpMethod method, HostAndPort host, String path) {
      return this.push(method, host, path, (MultiMap)null);
   }

   @Fluent
   default HttpServerResponse push(HttpMethod method, String path, MultiMap headers, Handler handler) {
      return this.push(method, (String)null, path, headers, handler);
   }

   default Future push(HttpMethod method, String path, MultiMap headers) {
      return this.push(method, (HostAndPort)null, path, headers);
   }

   @Fluent
   default HttpServerResponse push(HttpMethod method, String path, Handler handler) {
      return this.push(method, (String)((String)null), path, (MultiMap)null, handler);
   }

   default Future push(HttpMethod method, String path) {
      return this.push(method, (String)null, path);
   }

   /** @deprecated */
   @Deprecated
   @Fluent
   default HttpServerResponse push(HttpMethod method, String host, String path, MultiMap headers, Handler handler) {
      Future<HttpServerResponse> fut = this.push(method, host, path, headers);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   /** @deprecated */
   @Deprecated
   Future push(HttpMethod var1, String var2, String var3, MultiMap var4);

   @Fluent
   default HttpServerResponse push(HttpMethod method, HostAndPort authority, String path, MultiMap headers, Handler handler) {
      Future<HttpServerResponse> fut = this.push(method, authority, path, headers);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   Future push(HttpMethod var1, HostAndPort var2, String var3, MultiMap var4);

   default boolean reset() {
      return this.reset(0L);
   }

   boolean reset(long var1);

   @Fluent
   HttpServerResponse writeCustomFrame(int var1, int var2, Buffer var3);

   @Fluent
   default HttpServerResponse writeCustomFrame(HttpFrame frame) {
      return this.writeCustomFrame(frame.type(), frame.flags(), frame.payload());
   }

   @Fluent
   default HttpServerResponse setStreamPriority(StreamPriority streamPriority) {
      return this;
   }

   @Fluent
   HttpServerResponse addCookie(Cookie var1);

   default @Nullable Cookie removeCookie(String name) {
      return this.removeCookie(name, true);
   }

   @Nullable Cookie removeCookie(String var1, boolean var2);

   default Set removeCookies(String name) {
      return this.removeCookies(name, true);
   }

   Set removeCookies(String var1, boolean var2);

   default @Nullable Cookie removeCookie(String name, String domain, String path) {
      return this.removeCookie(name, domain, path, true);
   }

   @Nullable Cookie removeCookie(String var1, String var2, String var3, boolean var4);
}
