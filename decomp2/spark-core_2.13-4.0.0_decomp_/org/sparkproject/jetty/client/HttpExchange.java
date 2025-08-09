package org.sparkproject.jetty.client;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.io.CyclicTimeouts;
import org.sparkproject.jetty.util.thread.AutoLock;

public class HttpExchange implements CyclicTimeouts.Expirable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpExchange.class);
   private final AutoLock lock = new AutoLock();
   private final HttpDestination destination;
   private final HttpRequest request;
   private final List listeners;
   private final HttpResponse response;
   private State requestState;
   private State responseState;
   private HttpChannel _channel;
   private Throwable requestFailure;
   private Throwable responseFailure;

   public HttpExchange(HttpDestination destination, HttpRequest request, List listeners) {
      this.requestState = HttpExchange.State.PENDING;
      this.responseState = HttpExchange.State.PENDING;
      this.destination = destination;
      this.request = request;
      this.listeners = listeners;
      this.response = new HttpResponse(request, listeners);
      HttpConversation conversation = request.getConversation();
      conversation.getExchanges().offer(this);
      conversation.updateResponseListeners((Response.ResponseListener)null);
   }

   public HttpDestination getHttpDestination() {
      return this.destination;
   }

   public HttpConversation getConversation() {
      return this.request.getConversation();
   }

   public HttpRequest getRequest() {
      return this.request;
   }

   public Throwable getRequestFailure() {
      try (AutoLock l = this.lock.lock()) {
         return this.requestFailure;
      }
   }

   public List getResponseListeners() {
      return this.listeners;
   }

   public HttpResponse getResponse() {
      return this.response;
   }

   public Throwable getResponseFailure() {
      try (AutoLock l = this.lock.lock()) {
         return this.responseFailure;
      }
   }

   public long getExpireNanoTime() {
      return this.request.getTimeoutNanoTime();
   }

   boolean associate(HttpChannel channel) {
      boolean result = false;
      boolean abort = false;

      try (AutoLock l = this.lock.lock()) {
         if (this.requestState == HttpExchange.State.PENDING && this.responseState == HttpExchange.State.PENDING) {
            abort = this._channel != null;
            if (!abort) {
               this._channel = channel;
               result = true;
            }
         }
      }

      if (abort) {
         this.request.abort(new IllegalStateException(this.toString()));
      }

      return result;
   }

   void disassociate(HttpChannel channel) {
      boolean abort = false;

      try (AutoLock l = this.lock.lock()) {
         if (this._channel != channel || this.requestState != HttpExchange.State.TERMINATED || this.responseState != HttpExchange.State.TERMINATED) {
            abort = true;
         }

         this._channel = null;
      }

      if (abort) {
         this.request.abort(new IllegalStateException(this.toString()));
      }

   }

   private HttpChannel getHttpChannel() {
      try (AutoLock l = this.lock.lock()) {
         return this._channel;
      }
   }

   public boolean requestComplete(Throwable failure) {
      try (AutoLock l = this.lock.lock()) {
         return this.completeRequest(failure);
      }
   }

   private boolean completeRequest(Throwable failure) {
      if (this.requestState == HttpExchange.State.PENDING) {
         this.requestState = HttpExchange.State.COMPLETED;
         this.requestFailure = failure;
         return true;
      } else {
         return false;
      }
   }

   public boolean responseComplete(Throwable failure) {
      try (AutoLock l = this.lock.lock()) {
         return this.completeResponse(failure);
      }
   }

   private boolean completeResponse(Throwable failure) {
      if (this.responseState == HttpExchange.State.PENDING) {
         this.responseState = HttpExchange.State.COMPLETED;
         this.responseFailure = failure;
         return true;
      } else {
         return false;
      }
   }

   public Result terminateRequest() {
      Result result = null;

      try (AutoLock l = this.lock.lock()) {
         if (this.requestState == HttpExchange.State.COMPLETED) {
            this.requestState = HttpExchange.State.TERMINATED;
         }

         if (this.requestState == HttpExchange.State.TERMINATED && this.responseState == HttpExchange.State.TERMINATED) {
            result = new Result(this.getRequest(), this.requestFailure, this.getResponse(), this.responseFailure);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Terminated request for {}, result: {}", this, result);
      }

      return result;
   }

   public Result terminateResponse() {
      Result result = null;

      try (AutoLock l = this.lock.lock()) {
         if (this.responseState == HttpExchange.State.COMPLETED) {
            this.responseState = HttpExchange.State.TERMINATED;
         }

         if (this.requestState == HttpExchange.State.TERMINATED && this.responseState == HttpExchange.State.TERMINATED) {
            result = new Result(this.getRequest(), this.requestFailure, this.getResponse(), this.responseFailure);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Terminated response for {}, result: {}", this, result);
      }

      return result;
   }

   public boolean abort(Throwable failure) {
      boolean abortRequest;
      boolean abortResponse;
      try (AutoLock l = this.lock.lock()) {
         abortRequest = this.completeRequest(failure);
         abortResponse = this.completeResponse(failure);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Failed {}: req={}/rsp={} {}", new Object[]{this, abortRequest, abortResponse, failure});
      }

      if (!abortRequest && !abortResponse) {
         return false;
      } else {
         Request.Content body = this.request.getBody();
         if (abortRequest && body != null) {
            body.fail(failure);
         }

         if (this.destination.remove(this)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Aborting while queued {}: {}", this, failure);
            }

            this.notifyFailureComplete(failure);
            return true;
         } else {
            HttpChannel channel = this.getHttpChannel();
            if (channel == null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Aborted before association {}: {}", this, failure);
               }

               this.notifyFailureComplete(failure);
               return true;
            } else {
               boolean aborted = channel.abort(this, abortRequest ? failure : null, abortResponse ? failure : null);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Aborted ({}) while active {}: {}", new Object[]{aborted, this, failure});
               }

               return aborted;
            }
         }
      }
   }

   private void notifyFailureComplete(Throwable failure) {
      this.destination.getRequestNotifier().notifyFailure(this.request, failure);
      List<Response.ResponseListener> listeners = this.getConversation().getResponseListeners();
      ResponseNotifier responseNotifier = this.destination.getResponseNotifier();
      responseNotifier.notifyFailure((List)listeners, this.response, failure);
      responseNotifier.notifyComplete(listeners, new Result(this.request, failure, this.response, failure));
   }

   public void resetResponse() {
      try (AutoLock l = this.lock.lock()) {
         this.responseState = HttpExchange.State.PENDING;
         this.responseFailure = null;
         this.response.clearHeaders();
      }

   }

   public void proceed(Throwable failure) {
      HttpChannel channel = this.getHttpChannel();
      if (channel != null) {
         channel.proceed(this, failure);
      }

   }

   public String toString() {
      try (AutoLock l = this.lock.lock()) {
         return String.format("%s@%x{req=%s[%s/%s] res=%s[%s/%s]}", HttpExchange.class.getSimpleName(), this.hashCode(), this.request, this.requestState, this.requestFailure, this.response, this.responseState, this.responseFailure);
      }
   }

   private static enum State {
      PENDING,
      COMPLETED,
      TERMINATED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{PENDING, COMPLETED, TERMINATED};
      }
   }
}
