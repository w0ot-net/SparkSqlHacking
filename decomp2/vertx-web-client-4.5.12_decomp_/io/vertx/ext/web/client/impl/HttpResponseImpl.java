package io.vertx.ext.web.client.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import java.util.List;

public class HttpResponseImpl implements HttpResponse {
   private final HttpVersion version;
   private final int statusCode;
   private final String statusMessage;
   private final MultiMap headers;
   private final MultiMap trailers;
   private final List cookies;
   private final Object body;
   private final List redirects;

   public HttpResponseImpl(HttpVersion version, int statusCode, String statusMessage, MultiMap headers, MultiMap trailers, List cookies, Object body, List redirects) {
      this.version = version;
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
      this.headers = headers;
      this.trailers = trailers;
      this.cookies = cookies;
      this.body = body;
      this.redirects = redirects;
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

   public String getHeader(String headerName) {
      return this.headers.get(headerName);
   }

   public String getHeader(CharSequence headerName) {
      return this.headers.get(headerName);
   }

   public MultiMap trailers() {
      return this.trailers;
   }

   public String getTrailer(String trailerName) {
      return this.trailers.get(trailerName);
   }

   public List cookies() {
      return this.cookies;
   }

   public MultiMap headers() {
      return this.headers;
   }

   public Object body() {
      return this.body;
   }

   public Buffer bodyAsBuffer() {
      return this.body instanceof Buffer ? (Buffer)this.body : null;
   }

   public List followedRedirects() {
      return this.redirects;
   }

   public JsonArray bodyAsJsonArray() {
      Buffer b = this.bodyAsBuffer();
      return b != null ? (JsonArray)BodyCodecImpl.JSON_ARRAY_DECODER.apply(b) : null;
   }
}
