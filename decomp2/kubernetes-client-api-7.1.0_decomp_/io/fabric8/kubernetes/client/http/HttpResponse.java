package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.utils.IOHelpers;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface HttpResponse extends HttpHeaders {
   default boolean isSuccessful() {
      return isSuccessful(this.code());
   }

   static boolean isSuccessful(int code) {
      return code >= 200 && code < 300;
   }

   int code();

   default String message() {
      return HttpStatusMessage.getMessageForStatus(this.code());
   }

   Object body();

   default String bodyString() throws IOException {
      Object body = this.body();
      SupportedResponses supportedResponse = HttpResponse.SupportedResponses.from(body);
      return supportedResponse == null ? "" : supportedResponse.asString(body);
   }

   HttpRequest request();

   Optional previousResponse();

   public static enum SupportedResponses {
      TEXT(String.class, Object::toString, SendAsyncUtils::string),
      INPUT_STREAM(InputStream.class, (body) -> IOHelpers.readFully((InputStream)body, StandardCharsets.UTF_8), SendAsyncUtils::inputStream),
      READER(Reader.class, (body) -> IOHelpers.readFully((Reader)body), SendAsyncUtils::reader),
      BYTE_ARRAY(byte[].class, (body) -> new String((byte[])body, StandardCharsets.UTF_8), SendAsyncUtils::bytes);

      private final Class type;
      private final ToString toString;
      private final Async async;

      private SupportedResponses(Class type, ToString toString, Async async) {
         this.type = type;
         this.toString = toString;
         this.async = async;
      }

      private String asString(Object body) throws IOException {
         return this.toString.toString(body);
      }

      CompletableFuture sendAsync(HttpRequest request, HttpClient client) {
         return this.async.sendAsync(request, client);
      }

      public static SupportedResponses from(Object object) {
         return object == null ? null : from(object.getClass());
      }

      public static SupportedResponses from(Class type) {
         for(SupportedResponses sr : values()) {
            if (sr.type.isAssignableFrom(type)) {
               return sr;
            }
         }

         throw new IllegalArgumentException("Unsupported response type: " + type.getName());
      }

      @FunctionalInterface
      interface Async {
         CompletableFuture sendAsync(HttpRequest var1, HttpClient var2);
      }

      @FunctionalInterface
      interface ToString {
         String toString(Object var1) throws IOException;
      }
   }
}
