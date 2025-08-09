package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpResponseHead;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import java.util.List;

@VertxGen
public interface HttpResponse extends HttpResponseHead {
   @CacheReturn
   MultiMap trailers();

   @Nullable String getTrailer(String var1);

   @CacheReturn
   @Nullable Object body();

   @CacheReturn
   @Nullable Buffer bodyAsBuffer();

   @CacheReturn
   List followedRedirects();

   @CacheReturn
   default @Nullable String bodyAsString() {
      Buffer b = this.bodyAsBuffer();
      return b != null ? (String)BodyCodecImpl.UTF8_DECODER.apply(b) : null;
   }

   default @Nullable String bodyAsString(String encoding) {
      Buffer b = this.bodyAsBuffer();
      return b != null ? b.toString(encoding) : null;
   }

   @CacheReturn
   default @Nullable JsonObject bodyAsJsonObject() {
      Buffer b = this.bodyAsBuffer();
      return b != null ? (JsonObject)BodyCodecImpl.JSON_OBJECT_DECODER.apply(b) : null;
   }

   @CacheReturn
   @Nullable JsonArray bodyAsJsonArray();

   default @Nullable Object bodyAsJson(Class type) {
      Buffer b = this.bodyAsBuffer();
      return b != null ? BodyCodecImpl.jsonDecoder(type).apply(b) : null;
   }
}
