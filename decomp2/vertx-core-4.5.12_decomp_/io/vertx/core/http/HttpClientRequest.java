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
import java.util.function.Function;

@VertxGen
public interface HttpClientRequest extends WriteStream {
   HttpClientRequest exceptionHandler(Handler var1);

   HttpClientRequest setWriteQueueMaxSize(int var1);

   HttpClientRequest drainHandler(Handler var1);

   @Fluent
   HttpClientRequest authority(HostAndPort var1);

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpClientRequest setHost(String var1);

   /** @deprecated */
   @Deprecated
   String getHost();

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpClientRequest setPort(int var1);

   /** @deprecated */
   @Deprecated
   int getPort();

   @Fluent
   HttpClientRequest setFollowRedirects(boolean var1);

   boolean isFollowRedirects();

   @Fluent
   HttpClientRequest setMaxRedirects(int var1);

   int getMaxRedirects();

   int numberOfRedirections();

   @Fluent
   HttpClientRequest setChunked(boolean var1);

   boolean isChunked();

   HttpMethod getMethod();

   @Fluent
   HttpClientRequest setMethod(HttpMethod var1);

   String absoluteURI();

   String getURI();

   @Fluent
   HttpClientRequest setURI(String var1);

   String path();

   String query();

   @CacheReturn
   MultiMap headers();

   @Fluent
   HttpClientRequest putHeader(String var1, String var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpClientRequest putHeader(CharSequence var1, CharSequence var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpClientRequest putHeader(String var1, Iterable var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   HttpClientRequest putHeader(CharSequence var1, Iterable var2);

   HttpClientRequest traceOperation(String var1);

   String traceOperation();

   HttpVersion version();

   Future write(String var1);

   void write(String var1, Handler var2);

   Future write(String var1, String var2);

   void write(String var1, String var2, Handler var3);

   @Fluent
   HttpClientRequest continueHandler(@Nullable Handler var1);

   @Fluent
   HttpClientRequest earlyHintsHandler(@Nullable Handler var1);

   @Fluent
   HttpClientRequest redirectHandler(@Nullable Function var1);

   Future sendHead();

   @Fluent
   HttpClientRequest sendHead(Handler var1);

   void connect(Handler var1);

   Future connect();

   @Fluent
   HttpClientRequest response(Handler var1);

   Future response();

   default void send(Handler handler) {
      this.response(handler);
      this.end();
   }

   default Future send() {
      this.end();
      return this.response();
   }

   default void send(String body, Handler handler) {
      this.response(handler);
      this.end(body);
   }

   default Future send(String body) {
      this.end(body);
      return this.response();
   }

   default void send(Buffer body, Handler handler) {
      this.response(handler);
      this.end(body);
   }

   default Future send(Buffer body) {
      this.end(body);
      return this.response();
   }

   default void send(ReadStream body, Handler handler) {
      MultiMap headers = this.headers();
      if (headers == null || !headers.contains(HttpHeaders.CONTENT_LENGTH)) {
         this.setChunked(true);
      }

      this.response(handler);
      body.pipeTo(this);
   }

   default Future send(ReadStream body) {
      MultiMap headers = this.headers();
      if (headers == null || !headers.contains(HttpHeaders.CONTENT_LENGTH)) {
         this.setChunked(true);
      }

      body.pipeTo(this);
      return this.response();
   }

   Future end(String var1);

   void end(String var1, Handler var2);

   Future end(String var1, String var2);

   void end(String var1, String var2, Handler var3);

   Future end(Buffer var1);

   void end(Buffer var1, Handler var2);

   Future end();

   void end(Handler var1);

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpClientRequest setTimeout(long var1);

   @Fluent
   default HttpClientRequest idleTimeout(long timeout) {
      return this.setTimeout(timeout);
   }

   @Fluent
   HttpClientRequest pushHandler(Handler var1);

   default boolean reset() {
      return this.reset(0L);
   }

   boolean reset(long var1);

   boolean reset(long var1, Throwable var3);

   @CacheReturn
   HttpConnection connection();

   @Fluent
   HttpClientRequest writeCustomFrame(int var1, int var2, Buffer var3);

   default int streamId() {
      return -1;
   }

   @Fluent
   default HttpClientRequest writeCustomFrame(HttpFrame frame) {
      return this.writeCustomFrame(frame.type(), frame.flags(), frame.payload());
   }

   @Fluent
   default HttpClientRequest setStreamPriority(StreamPriority streamPriority) {
      return this;
   }

   StreamPriority getStreamPriority();
}
