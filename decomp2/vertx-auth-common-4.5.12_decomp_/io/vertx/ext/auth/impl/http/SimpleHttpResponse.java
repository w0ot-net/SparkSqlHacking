package io.vertx.ext.auth.impl.http;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public final class SimpleHttpResponse {
   private final int statusCode;
   private final MultiMap headers;
   private final Buffer body;

   public SimpleHttpResponse(int statusCode, MultiMap headers, Buffer body) {
      this.headers = headers;
      this.body = body;
      this.statusCode = statusCode;
   }

   public int statusCode() {
      return this.statusCode;
   }

   public MultiMap headers() {
      return this.headers;
   }

   public @Nullable String getHeader(String header) {
      return this.headers != null ? this.headers.get(header) : null;
   }

   public @Nullable Buffer body() {
      return this.body;
   }

   public @Nullable JsonObject jsonObject() {
      return this.body == null ? null : new JsonObject(this.body);
   }

   public @Nullable JsonArray jsonArray() {
      return this.body == null ? null : new JsonArray(this.body);
   }

   public boolean is(String contentType) {
      if (this.headers != null) {
         String header = this.headers.get("Content-Type");
         if (header != null) {
            int sep = header.indexOf(59);
            if (sep != -1) {
               header = header.substring(0, sep).trim();
            }

            return contentType.equalsIgnoreCase(header);
         }
      }

      return false;
   }
}
