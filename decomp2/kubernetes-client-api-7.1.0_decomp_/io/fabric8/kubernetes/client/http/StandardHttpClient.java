package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.utils.AsyncUtils;
import io.fabric8.kubernetes.client.utils.ExponentialBackoffIntervalCalculator;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StandardHttpClient implements HttpClient, Interceptor.RequestTags {
   private static final long MAX_ADDITIONAL_REQUEST_TIMEOUT;
   private static final Logger LOG;
   protected StandardHttpClientBuilder builder;
   protected AtomicBoolean closed;

   protected StandardHttpClient(StandardHttpClientBuilder builder, AtomicBoolean closed) {
      this.builder = builder;
      this.closed = closed;
   }

   public abstract CompletableFuture buildWebSocketDirect(StandardWebSocketBuilder var1, WebSocket.Listener var2);

   public abstract CompletableFuture consumeBytesDirect(StandardHttpRequest var1, AsyncBody.Consumer var2);

   public HttpClient.DerivedClientBuilder newBuilder() {
      return this.builder.copy(this);
   }

   public CompletableFuture sendAsync(HttpRequest request, Class type) {
      CompletableFuture<HttpResponse<V>> upstream = HttpResponse.SupportedResponses.from(type).sendAsync(request, this);
      CompletableFuture<HttpResponse<V>> result = new CompletableFuture();
      upstream.whenComplete(completeOrCancel((r) -> {
         if (r.body() instanceof Closeable) {
            Utils.closeQuietly((Closeable)r.body());
         }

      }, result));
      return result;
   }

   public CompletableFuture consumeBytes(HttpRequest request, AsyncBody.Consumer consumer) {
      StandardHttpRequest standardHttpRequest = (StandardHttpRequest)request;
      return this.retryWithExponentialBackoff(standardHttpRequest, () -> this.consumeBytesOnce(standardHttpRequest, consumer), (r) -> ((AsyncBody)r.body()).cancel(), (r) -> r);
   }

   private CompletableFuture consumeBytesOnce(StandardHttpRequest standardHttpRequest, AsyncBody.Consumer consumer) {
      StandardHttpRequest.Builder copy = standardHttpRequest.newBuilder();

      for(Interceptor interceptor : this.builder.getInterceptors().values()) {
         interceptor.before(copy, standardHttpRequest, this);
         standardHttpRequest = copy.build();
      }

      StandardHttpRequest effectiveRequest = standardHttpRequest;

      for(Interceptor interceptor : this.builder.getInterceptors().values()) {
         consumer = interceptor.consumer(consumer, effectiveRequest);
      }

      AsyncBody.Consumer<List<ByteBuffer>> effectiveConsumer = consumer;
      CompletableFuture<HttpResponse<AsyncBody>> cf = this.consumeBytesDirect(effectiveRequest, consumer);
      cf.thenAccept((response) -> this.builder.getInterceptors().values().forEach((i) -> i.after(effectiveRequest, response, consumer)));

      for(Interceptor interceptor : this.builder.getInterceptors().values()) {
         cf = cf.thenCompose((response) -> !HttpResponse.isSuccessful(response.code()) ? interceptor.afterFailure((HttpRequest.Builder)copy, response, this).thenCompose((b) -> {
               if (Boolean.TRUE.equals(b)) {
                  ((AsyncBody)response.body()).cancel();
                  CompletableFuture<HttpResponse<AsyncBody>> result = this.consumeBytesDirect(copy.build(), effectiveConsumer);
                  result.thenAccept((r) -> this.builder.getInterceptors().values().forEach((i) -> i.after(effectiveRequest, r, effectiveConsumer)));
                  return result;
               } else {
                  return CompletableFuture.completedFuture(response);
               }
            }) : CompletableFuture.completedFuture(response));
      }

      return cf;
   }

   private static BiConsumer completeOrCancel(Consumer cancel, CompletableFuture result) {
      return (r, t) -> {
         if (t != null) {
            result.completeExceptionally(t);
         } else if (!result.complete(r)) {
            cancel.accept(r);
         }

      };
   }

   private CompletableFuture retryWithExponentialBackoff(StandardHttpRequest request, Supplier action, Consumer onCancel, Function responseExtractor) {
      RequestConfig requestConfig = (RequestConfig)this.getTag(RequestConfig.class);
      ExponentialBackoffIntervalCalculator retryIntervalCalculator = ExponentialBackoffIntervalCalculator.from(requestConfig);
      Duration timeout;
      if (request.getTimeout() != null && !request.getTimeout().isNegative() && !request.getTimeout().isZero()) {
         timeout = request.getTimeout().plusMillis(Math.min(request.getTimeout().toMillis(), MAX_ADDITIONAL_REQUEST_TIMEOUT));
      } else {
         timeout = null;
      }

      return AsyncUtils.retryWithExponentialBackoff(action, onCancel, timeout, retryIntervalCalculator, (response, throwable, retryInterval) -> this.shouldRetry(request, responseExtractor, response, throwable, retryInterval));
   }

   long shouldRetry(StandardHttpRequest request, Function responseExtractor, Object response, Throwable throwable, long retryInterval) {
      if (response != null) {
         HttpResponse<?> httpResponse = (HttpResponse)responseExtractor.apply(response);
         if (httpResponse != null) {
            int code = httpResponse.code();
            if (code == 429 || code >= 500) {
               retryInterval = Math.max(retryAfterMillis(httpResponse), retryInterval);
               LOG.debug("HTTP operation on url: {} should be retried as the response code was {}, retrying after {} millis", new Object[]{request.uri(), code, retryInterval});
               return retryInterval;
            }
         }
      } else {
         Throwable actualCause = unwrapCompletionException(throwable);
         this.builder.interceptors.forEach((s, interceptor) -> interceptor.afterConnectionFailure(request, actualCause));
         if (actualCause instanceof IOException) {
            LOG.debug(String.format("HTTP operation on url: %s should be retried after %d millis because of IOException", request.uri(), retryInterval), actualCause);
            return retryInterval;
         }
      }

      return -1L;
   }

   static Throwable unwrapCompletionException(Throwable throwable) {
      Throwable actualCause;
      if (throwable instanceof CompletionException) {
         actualCause = throwable.getCause();
      } else {
         actualCause = throwable;
      }

      return actualCause;
   }

   static long retryAfterMillis(HttpResponse httpResponse) {
      String retryAfter = httpResponse.header("Retry-After");
      if (retryAfter != null) {
         try {
            return (long)Integer.parseInt(retryAfter) * 1000L;
         } catch (NumberFormatException var4) {
            try {
               ZonedDateTime after = ZonedDateTime.parse(retryAfter, DateTimeFormatter.RFC_1123_DATE_TIME);
               return after.toEpochSecond() * 1000L - System.currentTimeMillis();
            } catch (DateTimeParseException var3) {
            }
         }
      }

      return 0L;
   }

   public WebSocket.Builder newWebSocketBuilder() {
      return new StandardWebSocketBuilder(this);
   }

   public HttpRequest.Builder newHttpRequestBuilder() {
      return new StandardHttpRequest.Builder();
   }

   final CompletableFuture buildWebSocket(StandardWebSocketBuilder standardWebSocketBuilder, WebSocket.Listener listener) {
      CompletableFuture<WebSocketResponse> intermediate = this.retryWithExponentialBackoff(standardWebSocketBuilder.asHttpRequest(), () -> this.buildWebSocketOnce(standardWebSocketBuilder, listener), (r) -> Optional.ofNullable(r.webSocket).ifPresent((w) -> w.sendClose(1000, (String)null)), (r) -> r.webSocketUpgradeResponse);
      CompletableFuture<WebSocket> result = new CompletableFuture();
      intermediate.whenComplete((r, t) -> {
         if (t != null) {
            result.completeExceptionally(t);
         } else {
            completeOrCancel((w) -> w.sendClose(1000, (String)null), result).accept(r.webSocket, r.throwable != null ? (new WebSocketHandshakeException(r.webSocketUpgradeResponse)).initCause(r.throwable) : null);
         }

      });
      return result;
   }

   private CompletableFuture buildWebSocketOnce(StandardWebSocketBuilder standardWebSocketBuilder, WebSocket.Listener listener) {
      StandardWebSocketBuilder copy = standardWebSocketBuilder.newBuilder();
      this.builder.getInterceptors().values().forEach((i) -> i.before(copy, copy.asHttpRequest(), this));
      CompletableFuture<WebSocketResponse> cf = this.buildWebSocketDirect(copy, listener);
      cf.thenAccept((response) -> this.builder.getInterceptors().values().forEach((i) -> i.after(response.webSocketUpgradeResponse.request(), response.webSocketUpgradeResponse, (AsyncBody.Consumer)null)));

      for(Interceptor interceptor : this.builder.getInterceptors().values()) {
         cf = cf.thenCompose((response) -> response.throwable != null ? interceptor.afterFailure((BasicBuilder)copy, response.webSocketUpgradeResponse, this).thenCompose((b) -> {
               if (Boolean.TRUE.equals(b)) {
                  return this.buildWebSocketDirect(copy, listener);
               } else {
                  CompletableFuture<WebSocketResponse> result = CompletableFuture.completedFuture(response);
                  result.thenAccept((r) -> this.builder.getInterceptors().values().forEach((i) -> i.after(r.webSocketUpgradeResponse.request(), r.webSocketUpgradeResponse, (AsyncBody.Consumer)null)));
                  return result;
               }
            }) : CompletableFuture.completedFuture(response));
      }

      return cf;
   }

   public Object getTag(Class type) {
      return type.cast(this.builder.tags.get(type));
   }

   public final void close() {
      if (this.closed.compareAndSet(false, true)) {
         this.doClose();
      }

   }

   protected abstract void doClose();

   public boolean isClosed() {
      return this.closed.get();
   }

   public AtomicBoolean getClosed() {
      return this.closed;
   }

   static {
      MAX_ADDITIONAL_REQUEST_TIMEOUT = TimeUnit.SECONDS.toMillis(5L);
      LOG = LoggerFactory.getLogger(StandardHttpClient.class);
   }
}
