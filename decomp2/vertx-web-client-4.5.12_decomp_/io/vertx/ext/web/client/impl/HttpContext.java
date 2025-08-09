package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.EncoderMode;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientInternal;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.spi.CacheStore;
import io.vertx.ext.web.codec.spi.BodyStream;
import io.vertx.ext.web.multipart.MultipartForm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpContext {
   private final Handler handler;
   private final HttpClientInternal client;
   private final WebClientOptions options;
   private final List interceptors;
   private ContextInternal context;
   private HttpRequestImpl request;
   private Object body;
   private String contentType;
   private Map attrs;
   private int interceptorIdx;
   private boolean invoking;
   private boolean invokeNext;
   private ClientPhase phase;
   private RequestOptions requestOptions;
   private HttpClientRequest clientRequest;
   private HttpClientResponse clientResponse;
   private Promise requestPromise;
   private HttpResponse response;
   private Throwable failure;
   private int redirects;
   private List redirectedLocations = Collections.emptyList();
   private CacheStore privateCacheStore;

   HttpContext(HttpClientInternal client, WebClientOptions options, List interceptors, Handler handler) {
      this.handler = handler;
      this.client = client;
      this.options = options;
      this.interceptors = interceptors;
   }

   public HttpContext duplicate() {
      return new HttpContext(this.client, this.options, this.interceptors, this.handler);
   }

   public HttpClientRequest clientRequest() {
      return this.clientRequest;
   }

   public HttpClientResponse clientResponse() {
      return this.clientResponse;
   }

   public ClientPhase phase() {
      return this.phase;
   }

   public HttpRequest request() {
      return this.request;
   }

   public RequestOptions requestOptions() {
      return this.requestOptions;
   }

   public void setRequestOptions(RequestOptions requestOptions) {
      this.requestOptions = requestOptions;
   }

   public HttpResponse response() {
      return this.response;
   }

   public HttpContext response(HttpResponse response) {
      this.response = response;
      return this;
   }

   public int redirects() {
      return this.redirects;
   }

   public HttpContext redirects(int redirects) {
      this.redirects = redirects;
      return this;
   }

   public String contentType() {
      return this.contentType;
   }

   public Object body() {
      return this.body;
   }

   public Throwable failure() {
      return this.failure;
   }

   public List getRedirectedLocations() {
      return this.redirectedLocations;
   }

   public CacheStore privateCacheStore() {
      return this.privateCacheStore;
   }

   public HttpContext privateCacheStore(CacheStore cacheStore) {
      this.privateCacheStore = cacheStore;
      return this;
   }

   public void prepareRequest(HttpRequest request, String contentType, Object body) {
      this.request = (HttpRequestImpl)request;
      this.contentType = contentType;
      this.body = body;
      this.fire(ClientPhase.PREPARE_REQUEST);
   }

   public void createRequest(RequestOptions requestOptions) {
      this.requestOptions = requestOptions;
      this.fire(ClientPhase.CREATE_REQUEST);
   }

   public void sendRequest(HttpClientRequest clientRequest) {
      this.clientRequest = clientRequest;
      this.fire(ClientPhase.SEND_REQUEST);
   }

   private void handleFollowRedirect() {
      this.fire(ClientPhase.CREATE_REQUEST);
   }

   public void receiveResponse(HttpClientResponse clientResponse) {
      int sc = clientResponse.statusCode();
      int maxRedirects = this.request.followRedirects() ? this.client.options().getMaxRedirects() : 0;
      this.clientResponse = clientResponse;
      if (this.redirects < maxRedirects && sc >= 300 && sc < 400) {
         ++this.redirects;
         Future<RequestOptions> next = (Future)this.client.redirectHandler().apply(clientResponse);
         if (next != null) {
            if (this.redirectedLocations.isEmpty()) {
               this.redirectedLocations = new ArrayList();
            }

            this.redirectedLocations.add(clientResponse.getHeader(HttpHeaders.LOCATION));
            next.onComplete((ar) -> {
               if (ar.succeeded()) {
                  RequestOptions options = (RequestOptions)ar.result();
                  this.requestOptions = options;
                  this.fire(ClientPhase.FOLLOW_REDIRECT);
               } else {
                  this.fail(ar.cause());
               }

            });
            return;
         }
      }

      this.clientResponse = clientResponse;
      this.fire(ClientPhase.RECEIVE_RESPONSE);
   }

   public void dispatchResponse(HttpResponse response) {
      this.response = response;
      this.fire(ClientPhase.DISPATCH_RESPONSE);
   }

   public boolean fail(Throwable cause) {
      if (this.phase == ClientPhase.FAILURE) {
         return false;
      } else {
         this.failure = cause;
         this.fire(ClientPhase.FAILURE);
         return true;
      }
   }

   private void fire(ClientPhase phase) {
      Objects.requireNonNull(phase);
      this.phase = phase;
      this.interceptorIdx = 0;
      if (this.invoking) {
         this.invokeNext = true;
      } else {
         this.next();
      }

   }

   public void next() {
      if (this.invoking) {
         this.invokeNext = true;
      } else {
         while(true) {
            if (this.interceptorIdx < this.interceptors.size()) {
               label70: {
                  Handler<HttpContext<?>> interceptor = (Handler)this.interceptors.get(this.interceptorIdx);
                  this.invoking = true;
                  ++this.interceptorIdx;

                  try {
                     interceptor.handle(this);
                  } catch (Exception e) {
                     this.failure = e;
                     this.invokeNext = false;
                     this.phase = ClientPhase.FAILURE;
                     break label70;
                  } finally {
                     this.invoking = false;
                  }

                  if (!this.invokeNext) {
                     return;
                  }

                  this.invokeNext = false;
                  continue;
               }
            }

            this.interceptorIdx = 0;
            this.execute();
            break;
         }
      }

   }

   private void execute() {
      switch (this.phase) {
         case PREPARE_REQUEST:
            this.handlePrepareRequest();
            break;
         case CREATE_REQUEST:
            this.handleCreateRequest();
            break;
         case SEND_REQUEST:
            this.handleSendRequest();
            break;
         case FOLLOW_REDIRECT:
            this.handleFollowRedirect();
            break;
         case RECEIVE_RESPONSE:
            this.handleReceiveResponse();
            break;
         case DISPATCH_RESPONSE:
            this.handleDispatchResponse();
            break;
         case FAILURE:
            this.handleFailure();
      }

   }

   private void handleFailure() {
      HttpClientRequest req = this.clientRequest;
      if (req != null) {
         this.clientRequest = null;
         req.reset();
      }

      this.handler.handle(Future.failedFuture(this.failure));
   }

   private void handleDispatchResponse() {
      this.handler.handle(Future.succeededFuture(this.response));
   }

   private void handlePrepareRequest() {
      this.context = this.client.vertx().getOrCreateContext();
      this.redirects = 0;

      RequestOptions requestOptions;
      try {
         requestOptions = this.request.buildRequestOptions();
      } catch (Exception e) {
         this.fail(e);
         return;
      }

      if (this.contentType != null) {
         String prev = requestOptions.getHeaders().get(HttpHeaders.CONTENT_TYPE);
         if (prev == null) {
            requestOptions.addHeader(HttpHeaders.CONTENT_TYPE, this.contentType);
         } else {
            this.contentType = prev;
         }
      }

      this.createRequest(requestOptions);
   }

   private void handleCreateRequest() {
      this.requestPromise = this.context.promise();
      if (this.body == null && !"application/json".equals(this.contentType)) {
         this.requestPromise.future().onSuccess((request) -> {
            this.clientRequest = null;
            request.end();
         });
      } else {
         if (this.body instanceof MultipartForm) {
            MultipartFormUpload multipartForm;
            try {
               boolean multipart = "multipart/form-data".equals(this.contentType);
               HttpPostRequestEncoder.EncoderMode encoderMode = this.request.multipartMixed() ? EncoderMode.RFC1738 : EncoderMode.HTML5;
               multipartForm = new MultipartFormUpload(this.context, (MultipartForm)this.body, multipart, encoderMode);
               this.body = multipartForm;
            } catch (Exception e) {
               this.fail(e);
               return;
            }

            for(Map.Entry header : multipartForm.headers()) {
               this.requestOptions.putHeader((String)header.getKey(), (String)header.getValue());
            }
         }

         if (this.body instanceof ReadStream) {
            ReadStream<Buffer> stream = (ReadStream)this.body;
            Pipe<Buffer> pipe = stream.pipe();
            this.requestPromise.future().onComplete((ar) -> {
               if (ar.succeeded()) {
                  HttpClientRequest req = (HttpClientRequest)ar.result();
                  if (this.request.headers == null || !this.request.headers.contains(HttpHeaders.CONTENT_LENGTH)) {
                     req.setChunked(true);
                  }

                  pipe.endOnFailure(false);
                  pipe.to(req, (ar2) -> {
                     this.clientRequest = null;
                     if (ar2.failed()) {
                        req.reset(0L, ar2.cause());
                     }

                  });
                  if (this.body instanceof MultipartFormUpload) {
                     ((MultipartFormUpload)this.body).run();
                  }
               } else {
                  this.clientRequest = null;
                  pipe.close();
               }

            });
         } else {
            Buffer buffer;
            if (this.body instanceof Buffer) {
               buffer = (Buffer)this.body;
            } else if (this.body instanceof JsonObject) {
               buffer = ((JsonObject)this.body).toBuffer();
            } else {
               buffer = Json.encodeToBuffer(this.body);
            }

            this.requestOptions.putHeader(HttpHeaders.CONTENT_LENGTH, "" + buffer.length());
            this.requestPromise.future().onSuccess((request) -> {
               this.clientRequest = null;
               request.end(buffer);
            });
         }
      }

      this.client.request(this.requestOptions).onComplete((ar1) -> {
         if (ar1.succeeded()) {
            this.sendRequest((HttpClientRequest)ar1.result());
         } else {
            this.fail(ar1.cause());
            this.requestPromise.fail(ar1.cause());
         }

      });
   }

   private void handleReceiveResponse() {
      HttpClientResponse resp = this.clientResponse;
      Context context = Vertx.currentContext();
      Promise<HttpResponse<T>> promise = Promise.promise();
      promise.future().onComplete((r) -> context.runOnContext((v) -> {
            if (r.succeeded()) {
               this.dispatchResponse((HttpResponse)r.result());
            } else {
               this.fail(r.cause());
            }

         }));
      resp.exceptionHandler((err) -> {
         if (!promise.future().isComplete()) {
            promise.fail(err);
         }

      });
      Pipe<Buffer> pipe = resp.pipe();
      this.request.bodyCodec().create((ar1) -> {
         if (ar1.succeeded()) {
            BodyStream<T> stream = (BodyStream)ar1.result();
            pipe.to(stream, (ar2) -> {
               if (ar2.succeeded()) {
                  stream.result().onComplete((ar3) -> {
                     if (ar3.succeeded()) {
                        promise.complete(new HttpResponseImpl(resp.version(), resp.statusCode(), resp.statusMessage(), resp.headers(), resp.trailers(), resp.cookies(), stream.result().result(), this.redirectedLocations));
                     } else {
                        promise.fail(ar3.cause());
                     }

                  });
               } else {
                  promise.fail(ar2.cause());
               }

            });
         } else {
            pipe.close();
            this.fail(ar1.cause());
         }

      });
   }

   private void handleSendRequest() {
      this.clientRequest.response((ar) -> {
         if (ar.succeeded()) {
            this.receiveResponse(((HttpClientResponse)ar.result()).pause());
         } else {
            this.fail(ar.cause());
         }

      });
      this.requestPromise.complete(this.clientRequest);
   }

   public Object get(String key) {
      return this.attrs != null ? this.attrs.get(key) : null;
   }

   public HttpContext set(String key, Object value) {
      if (value == null) {
         if (this.attrs != null) {
            this.attrs.remove(key);
         }
      } else {
         if (this.attrs == null) {
            this.attrs = new HashMap();
         }

         this.attrs.put(key, value);
      }

      return this;
   }
}
