package io.fabric8.kubernetes.client.vertx;

import io.fabric8.kubernetes.client.http.AsyncBody;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.http.StandardHttpHeaders;
import io.fabric8.kubernetes.client.http.StandardHttpRequest;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.streams.ReadStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

class VertxHttpRequest {
   final Vertx vertx;
   private final RequestOptions options;
   private final StandardHttpRequest request;

   public VertxHttpRequest(Vertx vertx, RequestOptions options, StandardHttpRequest request) {
      this.vertx = vertx;
      this.options = options;
      this.request = request;
   }

   public CompletableFuture consumeBytes(HttpClient client, AsyncBody.Consumer consumer) {
      Function<HttpClientResponse, HttpResponse<AsyncBody>> responseHandler = (resp) -> {
         resp.pause();
         AsyncBody result = new AsyncBody() {
            final CompletableFuture done = new CompletableFuture();

            public void consume() {
               resp.fetch(1L);
            }

            public CompletableFuture done() {
               return this.done;
            }

            public void cancel() {
               resp.handler((Handler)null);
               resp.endHandler((Handler)null);
               resp.request().reset();
               this.done.cancel(false);
            }
         };
         resp.handler((buffer) -> {
            try {
               consumer.consume(List.of(ByteBuffer.wrap(buffer.getBytes())), result);
            } catch (Exception e) {
               resp.request().reset();
               result.done().completeExceptionally(e);
            }

         }).endHandler((end) -> result.done().complete((Object)null));
         return new VertxHttpResponse(result, resp, this.request);
      };
      return client.request(this.options).compose((request) -> {
         StandardHttpRequest.BodyContent body = this.request.body();
         Future<HttpClientResponse> fut;
         if (body != null) {
            if (body instanceof StandardHttpRequest.StringBodyContent) {
               Buffer buffer = Buffer.buffer(((StandardHttpRequest.StringBodyContent)body).getContent());
               fut = request.send(buffer);
            } else if (body instanceof StandardHttpRequest.ByteArrayBodyContent) {
               Buffer buffer = Buffer.buffer(((StandardHttpRequest.ByteArrayBodyContent)body).getContent());
               fut = request.send(buffer);
            } else if (body instanceof StandardHttpRequest.InputStreamBodyContent) {
               StandardHttpRequest.InputStreamBodyContent bodyContent = (StandardHttpRequest.InputStreamBodyContent)body;
               InputStream is = bodyContent.getContent();
               ReadStream<Buffer> stream = new InputStreamReadStream(this, is, request);
               fut = request.send(stream);
            } else {
               fut = Future.failedFuture("Unsupported body content");
            }
         } else {
            fut = request.send();
         }

         return fut.map(responseHandler);
      }).toCompletionStage().toCompletableFuture();
   }

   static Map toHeadersMap(MultiMap multiMap) {
      Map<String, List<String>> headers = new LinkedHashMap();
      multiMap.names().forEach((k) -> headers.put(k, multiMap.getAll(k)));
      return headers;
   }

   private static final class VertxHttpResponse extends StandardHttpHeaders implements HttpResponse {
      private final AsyncBody result;
      private final HttpClientResponse resp;
      private final HttpRequest request;

      private VertxHttpResponse(AsyncBody result, HttpClientResponse resp, HttpRequest request) {
         super(VertxHttpRequest.toHeadersMap(resp.headers()));
         this.result = result;
         this.resp = resp;
         this.request = request;
      }

      public int code() {
         return this.resp.statusCode();
      }

      public AsyncBody body() {
         return this.result;
      }

      public HttpRequest request() {
         return this.request;
      }

      public Optional previousResponse() {
         return Optional.empty();
      }
   }
}
