package org.sparkproject.jetty.client;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

public abstract class HttpSender {
   private static final Logger LOG = LoggerFactory.getLogger(HttpSender.class);
   private final ContentConsumer consumer = new ContentConsumer();
   private final AtomicReference requestState;
   private final AtomicReference failure;
   private final HttpChannel channel;
   private Request.Content.Subscription subscription;

   protected HttpSender(HttpChannel channel) {
      this.requestState = new AtomicReference(HttpSender.RequestState.QUEUED);
      this.failure = new AtomicReference();
      this.channel = channel;
   }

   protected HttpChannel getHttpChannel() {
      return this.channel;
   }

   protected HttpExchange getHttpExchange() {
      return this.channel.getHttpExchange();
   }

   public boolean isFailed() {
      return this.requestState.get() == HttpSender.RequestState.FAILURE;
   }

   public void send(HttpExchange exchange) {
      if (this.queuedToBegin(exchange)) {
         if (this.beginToHeaders(exchange)) {
            this.demand();
         }
      }
   }

   protected boolean expects100Continue(Request request) {
      return request.getHeaders().contains(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString());
   }

   protected boolean queuedToBegin(HttpExchange exchange) {
      if (!this.updateRequestState(HttpSender.RequestState.QUEUED, HttpSender.RequestState.TRANSIENT)) {
         return false;
      } else {
         Request request = exchange.getRequest();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Request begin {}", request);
         }

         RequestNotifier notifier = this.getHttpChannel().getHttpDestination().getRequestNotifier();
         notifier.notifyBegin(request);
         Request.Content body = request.getBody();
         this.consumer.exchange = exchange;
         this.consumer.expect100 = this.expects100Continue(request);
         this.subscription = body.subscribe(this.consumer, !this.consumer.expect100);
         if (this.updateRequestState(HttpSender.RequestState.TRANSIENT, HttpSender.RequestState.BEGIN)) {
            return true;
         } else {
            this.abortRequest(exchange);
            return false;
         }
      }
   }

   protected boolean beginToHeaders(HttpExchange exchange) {
      if (!this.updateRequestState(HttpSender.RequestState.BEGIN, HttpSender.RequestState.TRANSIENT)) {
         return false;
      } else {
         Request request = exchange.getRequest();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Request headers {}{}{}", new Object[]{request, System.lineSeparator(), request.getHeaders().toString().trim()});
         }

         RequestNotifier notifier = this.getHttpChannel().getHttpDestination().getRequestNotifier();
         notifier.notifyHeaders(request);
         if (this.updateRequestState(HttpSender.RequestState.TRANSIENT, HttpSender.RequestState.HEADERS)) {
            return true;
         } else {
            this.abortRequest(exchange);
            return false;
         }
      }
   }

   protected boolean headersToCommit(HttpExchange exchange) {
      if (!this.updateRequestState(HttpSender.RequestState.HEADERS, HttpSender.RequestState.TRANSIENT)) {
         return false;
      } else {
         Request request = exchange.getRequest();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Request committed {}", request);
         }

         RequestNotifier notifier = this.getHttpChannel().getHttpDestination().getRequestNotifier();
         notifier.notifyCommit(request);
         if (this.updateRequestState(HttpSender.RequestState.TRANSIENT, HttpSender.RequestState.COMMIT)) {
            return true;
         } else {
            this.abortRequest(exchange);
            return false;
         }
      }
   }

   protected boolean someToContent(HttpExchange exchange, ByteBuffer content) {
      RequestState current = (RequestState)this.requestState.get();
      switch (current.ordinal()) {
         case 4:
         case 5:
            if (!this.updateRequestState(current, HttpSender.RequestState.TRANSIENT)) {
               return false;
            } else {
               Request request = exchange.getRequest();
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Request content {}{}{}", new Object[]{request, System.lineSeparator(), BufferUtil.toDetailString(content)});
               }

               RequestNotifier notifier = this.getHttpChannel().getHttpDestination().getRequestNotifier();
               notifier.notifyContent(request, content);
               if (this.updateRequestState(HttpSender.RequestState.TRANSIENT, HttpSender.RequestState.CONTENT)) {
                  return true;
               }

               this.abortRequest(exchange);
               return false;
            }
         default:
            return false;
      }
   }

   protected boolean someToSuccess(HttpExchange exchange) {
      RequestState current = (RequestState)this.requestState.get();
      switch (current.ordinal()) {
         case 4:
         case 5:
            if (!exchange.requestComplete((Throwable)null)) {
               return false;
            }

            this.requestState.set(HttpSender.RequestState.QUEUED);
            this.reset();
            Request request = exchange.getRequest();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Request success {}", request);
            }

            HttpDestination destination = this.getHttpChannel().getHttpDestination();
            destination.getRequestNotifier().notifySuccess(exchange.getRequest());
            Result result = exchange.terminateRequest();
            this.terminateRequest(exchange, (Throwable)null, result);
            return true;
         default:
            return false;
      }
   }

   private void anyToFailure(Throwable failure) {
      HttpExchange exchange = this.getHttpExchange();
      if (exchange != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Request failure {}", exchange.getRequest(), failure);
         }

         if (exchange.requestComplete(failure)) {
            this.executeAbort(exchange, failure);
         }

      }
   }

   private void demand() {
      try {
         this.subscription.demand();
      } catch (Throwable var2) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Failure invoking demand()", var2);
         }

         this.anyToFailure(var2);
      }

   }

   private void executeAbort(HttpExchange exchange, Throwable failure) {
      try {
         Executor executor = this.getHttpChannel().getHttpDestination().getHttpClient().getExecutor();
         executor.execute(() -> this.abort(exchange, failure));
      } catch (RejectedExecutionException x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Exchange aborted {}", exchange, x);
         }

         this.abort(exchange, failure);
      }

   }

   private void abortRequest(HttpExchange exchange) {
      Throwable failure = (Throwable)this.failure.get();
      if (this.subscription != null) {
         this.subscription.fail(failure);
      }

      this.dispose();
      Request request = exchange.getRequest();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Request abort {} {} on {}: {}", new Object[]{request, exchange, this.getHttpChannel(), failure});
      }

      HttpDestination destination = this.getHttpChannel().getHttpDestination();
      destination.getRequestNotifier().notifyFailure(request, failure);
      Result result = exchange.terminateRequest();
      this.terminateRequest(exchange, failure, result);
   }

   private void terminateRequest(HttpExchange exchange, Throwable failure, Result result) {
      Request request = exchange.getRequest();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Terminating request {}", request);
      }

      if (result == null) {
         if (failure != null && exchange.responseComplete(failure)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Response failure from request {} {}", request, exchange);
            }

            this.getHttpChannel().abortResponse(exchange, failure);
         }
      } else {
         result = this.channel.exchangeTerminating(exchange, result);
         HttpDestination destination = this.getHttpChannel().getHttpDestination();
         boolean ordered = destination.getHttpClient().isStrictEventOrdering();
         if (!ordered) {
            this.channel.exchangeTerminated(exchange, result);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Request/Response {}: {}", failure == null ? "succeeded" : "failed", result);
         }

         HttpConversation conversation = exchange.getConversation();
         destination.getResponseNotifier().notifyComplete(conversation.getResponseListeners(), result);
         if (ordered) {
            this.channel.exchangeTerminated(exchange, result);
         }
      }

   }

   protected abstract void sendHeaders(HttpExchange var1, ByteBuffer var2, boolean var3, Callback var4);

   protected abstract void sendContent(HttpExchange var1, ByteBuffer var2, boolean var3, Callback var4);

   protected void reset() {
      this.consumer.reset();
   }

   protected void dispose() {
   }

   public void proceed(HttpExchange exchange, Throwable failure) {
      this.consumer.expect100 = false;
      if (failure == null) {
         this.demand();
      } else {
         this.anyToFailure(failure);
      }

   }

   public boolean abort(HttpExchange exchange, Throwable failure) {
      this.failure.compareAndSet((Object)null, failure);

      RequestState current;
      do {
         current = (RequestState)this.requestState.get();
         if (current == HttpSender.RequestState.FAILURE) {
            return false;
         }
      } while(!this.updateRequestState(current, HttpSender.RequestState.FAILURE));

      boolean abort = current != HttpSender.RequestState.TRANSIENT;
      if (abort) {
         this.abortRequest(exchange);
         return true;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Concurrent failure: request termination skipped, performed by helpers");
         }

         return false;
      }
   }

   private boolean updateRequestState(RequestState from, RequestState to) {
      boolean updated = this.requestState.compareAndSet(from, to);
      if (!updated && LOG.isDebugEnabled()) {
         LOG.debug("RequestState update failed: {} -> {}: {}", new Object[]{from, to, this.requestState.get()});
      }

      return updated;
   }

   protected String relativize(String path) {
      try {
         String result = path;
         URI uri = URI.create(path);
         if (uri.isAbsolute()) {
            result = uri.getPath();
         }

         return result.isEmpty() ? "/" : result;
      } catch (Throwable var4) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Could not relativize {}", path);
         }

         return path;
      }
   }

   public String toString() {
      return String.format("%s@%x(req=%s,failure=%s)", this.getClass().getSimpleName(), this.hashCode(), this.requestState, this.failure);
   }

   private static enum RequestState {
      TRANSIENT,
      QUEUED,
      BEGIN,
      HEADERS,
      COMMIT,
      CONTENT,
      FAILURE;

      // $FF: synthetic method
      private static RequestState[] $values() {
         return new RequestState[]{TRANSIENT, QUEUED, BEGIN, HEADERS, COMMIT, CONTENT, FAILURE};
      }
   }

   private class ContentConsumer implements Request.Content.Consumer, Callback {
      private HttpExchange exchange;
      private boolean expect100;
      private ByteBuffer contentBuffer;
      private boolean lastContent;
      private Callback callback;
      private boolean committed;

      private void reset() {
         this.exchange = null;
         this.contentBuffer = null;
         this.lastContent = false;
         this.callback = null;
         this.committed = false;
      }

      public void onContent(ByteBuffer buffer, boolean last, Callback callback) {
         if (HttpSender.LOG.isDebugEnabled()) {
            HttpSender.LOG.debug("Content {} last={} for {}", new Object[]{BufferUtil.toDetailString(buffer), last, this.exchange.getRequest()});
         }

         this.contentBuffer = buffer.slice();
         this.lastContent = last;
         this.callback = callback;
         if (this.committed) {
            HttpSender.this.sendContent(this.exchange, buffer, last, this);
         } else {
            HttpSender.this.sendHeaders(this.exchange, buffer, last, this);
         }

      }

      public void onFailure(Throwable failure) {
         this.failed(failure);
      }

      public void succeeded() {
         boolean proceed = false;
         if (this.committed) {
            proceed = HttpSender.this.someToContent(this.exchange, this.contentBuffer);
         } else {
            this.committed = true;
            if (HttpSender.this.headersToCommit(this.exchange)) {
               proceed = true;
               if (this.contentBuffer.hasRemaining()) {
                  proceed = HttpSender.this.someToContent(this.exchange, this.contentBuffer);
               }
            }
         }

         this.callback.succeeded();
         if (proceed) {
            if (this.lastContent) {
               HttpSender.this.someToSuccess(this.exchange);
            } else if (this.expect100) {
               if (HttpSender.LOG.isDebugEnabled()) {
                  HttpSender.LOG.debug("Expecting 100 Continue for {}", this.exchange.getRequest());
               }
            } else {
               HttpSender.this.demand();
            }

         }
      }

      public void failed(Throwable x) {
         if (this.callback != null) {
            this.callback.failed(x);
         }

         HttpSender.this.anyToFailure(x);
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }
   }
}
