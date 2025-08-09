package io.fabric8.kubernetes.client.http;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class HttpResponseAdapter implements HttpResponse {
   private final HttpResponse response;
   private final Object body;

   public HttpResponseAdapter(HttpResponse response, Object body) {
      this.response = response;
      this.body = body;
   }

   public List headers(String key) {
      return this.response.headers(key);
   }

   public boolean isSuccessful() {
      return this.response.isSuccessful();
   }

   public Map headers() {
      return this.response.headers();
   }

   public int code() {
      return this.response.code();
   }

   public String message() {
      return this.response.message();
   }

   public HttpRequest request() {
      return this.response.request();
   }

   public Optional previousResponse() {
      return this.response.previousResponse();
   }

   public Object body() {
      return this.body;
   }
}
