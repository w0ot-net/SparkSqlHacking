package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.HttpResponseImpl;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

public class CachedHttpResponse {
   private final String version;
   private final int statusCode;
   private final String statusMessage;
   private final Buffer body;
   private final MultiMap responseHeaders;
   private final Instant timestamp;
   private final CacheControl cacheControl;

   static CachedHttpResponse wrap(HttpResponse response) {
      return wrap(response, CacheControl.parse(response.headers()));
   }

   static CachedHttpResponse wrap(HttpResponse response, CacheControl cacheControl) {
      return new CachedHttpResponse(response.version().name(), response.statusCode(), response.statusMessage(), response.bodyAsBuffer(), response.headers(), cacheControl);
   }

   CachedHttpResponse(String version, int statusCode, String statusMessage, Buffer body, MultiMap responseHeaders, CacheControl cacheControl) {
      this.version = version;
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
      this.body = body;
      this.responseHeaders = responseHeaders;
      this.timestamp = Instant.now();
      this.cacheControl = cacheControl;
   }

   public String getVersion() {
      return this.version;
   }

   public int getStatusCode() {
      return this.statusCode;
   }

   public String getStatusMessage() {
      return this.statusMessage;
   }

   public Buffer getBody() {
      return this.body;
   }

   public MultiMap getResponseHeaders() {
      return this.responseHeaders;
   }

   public Instant getTimestamp() {
      return this.timestamp;
   }

   public CacheControl getCacheControl() {
      return this.cacheControl;
   }

   public boolean isFresh() {
      return this.age() <= this.cacheControl.getMaxAge();
   }

   public boolean useStaleWhileRevalidate() {
      return this.useStale(CacheControlDirective.STALE_WHILE_REVALIDATE);
   }

   public boolean useStaleIfError() {
      return this.useStale(CacheControlDirective.STALE_IF_ERROR);
   }

   public long age() {
      return Duration.between(this.timestamp, Instant.now()).getSeconds();
   }

   public HttpResponse rehydrate() {
      return new HttpResponseImpl(HttpVersion.valueOf(this.version), this.statusCode, this.statusMessage, this.responseHeaders, MultiMap.caseInsensitiveMultiMap(), Collections.emptyList(), this.body, Collections.emptyList());
   }

   private boolean useStale(CacheControlDirective directive) {
      long secondsStale = Math.max(0L, this.age() - this.getCacheControl().getMaxAge());
      long maxSecondsStale = (Long)this.getCacheControl().getTimeDirectives().getOrDefault(directive, 0L);
      return secondsStale <= maxSecondsStale;
   }
}
