package org.sparkproject.jetty.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpVersion;

public class HttpResponse implements Response {
   private final HttpFields.Mutable headers = HttpFields.build();
   private final Request request;
   private final List listeners;
   private HttpVersion version;
   private int status;
   private String reason;
   private HttpFields.Mutable trailers;

   public HttpResponse(Request request, List listeners) {
      this.request = request;
      this.listeners = listeners;
   }

   public Request getRequest() {
      return this.request;
   }

   public HttpVersion getVersion() {
      return this.version;
   }

   public HttpResponse version(HttpVersion version) {
      this.version = version;
      return this;
   }

   public int getStatus() {
      return this.status;
   }

   public HttpResponse status(int status) {
      this.status = status;
      return this;
   }

   public String getReason() {
      return this.reason;
   }

   public HttpResponse reason(String reason) {
      this.reason = reason;
      return this;
   }

   public HttpFields getHeaders() {
      return this.headers.asImmutable();
   }

   public void clearHeaders() {
      this.headers.clear();
   }

   public HttpResponse addHeader(HttpField header) {
      this.headers.add(header);
      return this;
   }

   public HttpResponse headers(Consumer consumer) {
      consumer.accept(this.headers);
      return this;
   }

   public List getListeners(Class type) {
      ArrayList<T> result = new ArrayList();

      for(Response.ResponseListener listener : this.listeners) {
         if (type == null || type.isInstance(listener)) {
            result.add(listener);
         }
      }

      return result;
   }

   public HttpFields getTrailers() {
      return this.trailers == null ? null : this.trailers.asImmutable();
   }

   public HttpResponse trailer(HttpField trailer) {
      if (this.trailers == null) {
         this.trailers = HttpFields.build();
      }

      this.trailers.add(trailer);
      return this;
   }

   public boolean abort(Throwable cause) {
      return this.request.abort(cause);
   }

   public String toString() {
      return String.format("%s[%s %d %s]@%x", HttpResponse.class.getSimpleName(), this.getVersion(), this.getStatus(), this.getReason(), this.hashCode());
   }
}
