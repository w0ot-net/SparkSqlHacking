package org.sparkproject.jetty.client.api;

public class Result {
   private final Request request;
   private final Throwable requestFailure;
   private final Response response;
   private final Throwable responseFailure;

   public Result(Request request, Response response) {
      this(request, (Throwable)null, response, (Throwable)null);
   }

   public Result(Request request, Response response, Throwable responseFailure) {
      this(request, (Throwable)null, response, responseFailure);
   }

   public Result(Request request, Throwable requestFailure, Response response) {
      this(request, requestFailure, response, (Throwable)null);
   }

   public Result(Request request, Throwable requestFailure, Response response, Throwable responseFailure) {
      this.request = request;
      this.requestFailure = requestFailure;
      this.response = response;
      this.responseFailure = responseFailure;
   }

   public Result(Result result, Throwable responseFailure) {
      this.request = result.request;
      this.requestFailure = result.requestFailure;
      this.response = result.response;
      this.responseFailure = responseFailure;
   }

   public Request getRequest() {
      return this.request;
   }

   public Throwable getRequestFailure() {
      return this.requestFailure;
   }

   public Response getResponse() {
      return this.response;
   }

   public Throwable getResponseFailure() {
      return this.responseFailure;
   }

   public boolean isSucceeded() {
      return this.getFailure() == null;
   }

   public boolean isFailed() {
      return !this.isSucceeded();
   }

   public Throwable getFailure() {
      return this.responseFailure != null ? this.responseFailure : this.requestFailure;
   }

   public String toString() {
      return String.format("%s[%s > %s] %s", Result.class.getSimpleName(), this.request, this.response, this.getFailure());
   }
}
