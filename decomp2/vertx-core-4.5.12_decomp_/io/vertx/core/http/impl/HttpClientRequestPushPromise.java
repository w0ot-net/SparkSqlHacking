package io.vertx.core.http.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.net.SocketAddress;
import java.util.function.Function;

class HttpClientRequestPushPromise extends HttpClientRequestBase {
   private final HttpClientStream stream;
   private final MultiMap headers;

   public HttpClientRequestPushPromise(HttpClientStream stream, HttpMethod method, String uri, String host, int port, MultiMap headers) {
      super(stream, stream.connection().getContext().promise(), method, SocketAddress.inetSocketAddress(port, host), host, port, uri);
      this.stream = stream;
      this.headers = headers;
   }

   public HttpVersion version() {
      return this.stream.version();
   }

   void handleResponse(Promise promise, HttpClientResponse resp, long timeoutMs) {
      promise.complete(resp);
   }

   public HttpClientRequest exceptionHandler(Handler handler) {
      return this;
   }

   public HttpConnection connection() {
      return this.stream.connection();
   }

   public boolean isChunked() {
      return false;
   }

   public MultiMap headers() {
      return this.headers;
   }

   public Future write(Buffer data) {
      throw new IllegalStateException();
   }

   public HttpClientRequest setWriteQueueMaxSize(int maxSize) {
      throw new IllegalStateException();
   }

   public HttpClientRequest drainHandler(Handler handler) {
      throw new IllegalStateException();
   }

   public HttpClientRequest setFollowRedirects(boolean followRedirect) {
      throw new IllegalStateException();
   }

   public boolean isFollowRedirects() {
      return false;
   }

   public HttpClientRequest setMaxRedirects(int maxRedirects) {
      throw new IllegalStateException();
   }

   public int getMaxRedirects() {
      return 0;
   }

   public int numberOfRedirections() {
      return 0;
   }

   public HttpClientRequest redirectHandler(@Nullable Function handler) {
      throw new IllegalStateException();
   }

   public HttpClientRequest setChunked(boolean chunked) {
      throw new IllegalStateException();
   }

   public HttpClientRequest putHeader(String name, String value) {
      throw new IllegalStateException();
   }

   public HttpClientRequest putHeader(CharSequence name, CharSequence value) {
      throw new IllegalStateException();
   }

   public HttpClientRequest putHeader(String name, Iterable values) {
      throw new IllegalStateException();
   }

   public HttpClientRequest putHeader(CharSequence name, Iterable values) {
      throw new IllegalStateException();
   }

   public HttpClientRequest traceOperation(String op) {
      throw new IllegalStateException();
   }

   public String traceOperation() {
      throw new IllegalStateException();
   }

   public Future write(String chunk) {
      throw new IllegalStateException();
   }

   public Future write(String chunk, String enc) {
      throw new IllegalStateException();
   }

   public void write(Buffer data, Handler handler) {
      throw new IllegalStateException();
   }

   public void write(String chunk, Handler handler) {
      throw new IllegalStateException();
   }

   public void write(String chunk, String enc, Handler handler) {
      throw new IllegalStateException();
   }

   public HttpClientRequest continueHandler(@Nullable Handler handler) {
      throw new IllegalStateException();
   }

   public HttpClientRequest earlyHintsHandler(@Nullable Handler handler) {
      throw new IllegalStateException();
   }

   public Future sendHead() {
      throw new IllegalStateException();
   }

   public HttpClientRequest sendHead(Handler completionHandler) {
      throw new IllegalStateException();
   }

   public Future connect() {
      throw new IllegalStateException();
   }

   public void connect(Handler handler) {
      throw new IllegalStateException();
   }

   public Future end(String chunk) {
      throw new IllegalStateException();
   }

   public void end(String chunk, Handler handler) {
      throw new IllegalStateException();
   }

   public Future end(String chunk, String enc) {
      throw new IllegalStateException();
   }

   public void end(String chunk, String enc, Handler handler) {
      throw new IllegalStateException();
   }

   public Future end(Buffer chunk) {
      throw new IllegalStateException();
   }

   public void end(Buffer chunk, Handler handler) {
      throw new IllegalStateException();
   }

   public Future end() {
      throw new IllegalStateException();
   }

   public void end(Handler handler) {
      throw new IllegalStateException();
   }

   public boolean writeQueueFull() {
      throw new IllegalStateException();
   }

   public StreamPriority getStreamPriority() {
      return this.stream.priority();
   }

   public HttpClientRequest writeCustomFrame(int type, int flags, Buffer payload) {
      throw new UnsupportedOperationException("Cannot write frame with HTTP/1.x ");
   }
}
