package io.vertx.ext.auth.impl.http;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.function.Function;

public final class SimpleHttpClient {
   private final HttpClient client;
   private final String userAgent;

   public SimpleHttpClient(Vertx vertx, String userAgent, HttpClientOptions options) {
      this.client = vertx.createHttpClient(options);
      this.userAgent = userAgent;
   }

   public Future fetch(HttpMethod method, String url, JsonObject headers, Buffer payload) {
      if (url != null && url.length() != 0) {
         RequestOptions options = (new RequestOptions()).setMethod(method).setAbsoluteURI(url);
         if (this.userAgent != null) {
            options.addHeader("User-Agent", this.userAgent);
         }

         if (headers != null) {
            for(Map.Entry kv : headers) {
               options.addHeader((String)kv.getKey(), (String)kv.getValue());
            }
         }

         if (method != HttpMethod.POST && method != HttpMethod.PATCH && method != HttpMethod.PUT) {
            payload = null;
         }

         return this.makeRequest(options, payload);
      } else {
         return Future.failedFuture("Invalid url");
      }
   }

   public static Buffer jsonToQuery(JsonObject json) {
      Buffer buffer = Buffer.buffer();

      try {
         for(Map.Entry kv : json) {
            if (buffer.length() != 0) {
               buffer.appendByte((byte)38);
            }

            buffer.appendString(URLEncoder.encode((String)kv.getKey(), "UTF-8"));
            buffer.appendByte((byte)61);
            Object v = kv.getValue();
            if (v != null) {
               buffer.appendString(URLEncoder.encode(v.toString(), "UTF-8"));
            }
         }

         return buffer;
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
   }

   public static @Nullable JsonObject queryToJson(Buffer query) throws UnsupportedEncodingException {
      if (query == null) {
         return null;
      } else {
         JsonObject json = new JsonObject();
         String[] pairs = query.toString().split("&");

         for(String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            if (!json.containsKey(key)) {
               json.put(key, value);
            } else {
               Object oldValue = json.getValue(key);
               JsonArray array;
               if (oldValue instanceof JsonArray) {
                  array = (JsonArray)oldValue;
               } else {
                  array = new JsonArray();
                  array.add(oldValue);
                  json.put(key, array);
               }

               if (value == null) {
                  array.addNull();
               } else {
                  array.add(value);
               }
            }
         }

         return json;
      }
   }

   private Future makeRequest(RequestOptions options, Buffer payload) {
      return this.client.request(options).compose((req) -> {
         Function<HttpClientResponse, Future<SimpleHttpResponse>> resultHandler = (res) -> res.body().compose((value) -> {
               if (res.statusCode() >= 200 && res.statusCode() < 300) {
                  return Future.succeededFuture(new SimpleHttpResponse(res.statusCode(), res.headers(), value));
               } else {
                  return value != null && value.length() != 0 ? Future.failedFuture(res.statusMessage() + ": " + value) : Future.failedFuture(res.statusMessage());
               }
            });
         return payload != null ? req.send(payload).compose(resultHandler) : req.send().compose(resultHandler);
      });
   }
}
