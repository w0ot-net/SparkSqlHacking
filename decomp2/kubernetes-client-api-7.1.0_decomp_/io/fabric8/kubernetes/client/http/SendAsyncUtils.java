package io.fabric8.kubernetes.client.http;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

class SendAsyncUtils {
   private SendAsyncUtils() {
   }

   static CompletableFuture reader(HttpRequest request, HttpClient client) {
      return inputStream(request, client).thenApply((res) -> new HttpResponseAdapter(res, new InputStreamReader((InputStream)res.body(), StandardCharsets.UTF_8)));
   }

   static CompletableFuture inputStream(HttpRequest request, HttpClient client) {
      HttpClientReadableByteChannel byteChannel = new HttpClientReadableByteChannel();
      CompletableFuture<HttpResponse<AsyncBody>> futureResponse = client.consumeBytes(request, byteChannel);
      return futureResponse.thenApply((res) -> {
         byteChannel.onResponse(res);
         return new HttpResponseAdapter(res, Channels.newInputStream(byteChannel));
      });
   }

   static CompletableFuture bytes(HttpRequest request, HttpClient client) {
      ByteArrayBodyHandler byteArrayBodyHandler = new ByteArrayBodyHandler();
      CompletableFuture<HttpResponse<AsyncBody>> futureResponse = client.consumeBytes(request, byteArrayBodyHandler);
      return futureResponse.thenCompose((res) -> {
         byteArrayBodyHandler.onResponse(res);
         return byteArrayBodyHandler.getResult().thenApply((b) -> new HttpResponseAdapter(res, b));
      });
   }

   static CompletableFuture string(HttpRequest request, HttpClient client) {
      return bytes(request, client).thenApply((res) -> new HttpResponseAdapter(res, new String((byte[])res.body(), StandardCharsets.UTF_8)));
   }
}
