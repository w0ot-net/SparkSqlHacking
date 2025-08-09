package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.utils.Utils;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpLoggingInterceptor implements Interceptor {
   private final HttpLogger httpLogger;

   public HttpLoggingInterceptor() {
      this(LoggerFactory.getLogger(HttpLoggingInterceptor.class));
   }

   public HttpLoggingInterceptor(Logger logger) {
      this.httpLogger = new HttpLogger(logger);
   }

   public AsyncBody.Consumer consumer(AsyncBody.Consumer consumer, HttpRequest request) {
      return new DeferredLoggingConsumer(this.httpLogger, request, consumer);
   }

   public void after(HttpRequest request, HttpResponse response, AsyncBody.Consumer consumer) {
      if (response instanceof WebSocketUpgradeResponse) {
         this.httpLogger.logWsStart();
         this.httpLogger.logRequest(request);
         this.httpLogger.logResponse(response);
         this.httpLogger.logWsEnd();
      } else {
         DeferredLoggingConsumer deferredLoggingConsumer = (DeferredLoggingConsumer)consumer.unwrap(DeferredLoggingConsumer.class);
         if (response.body() instanceof AsyncBody && deferredLoggingConsumer != null) {
            deferredLoggingConsumer.processAsyncBody((AsyncBody)response.body(), response);
         } else {
            this.httpLogger.logStart();
            this.httpLogger.logRequest(request);
            this.httpLogger.logResponse(response);
            this.httpLogger.logEnd();
         }
      }

   }

   private static final class DeferredLoggingConsumer implements AsyncBody.Consumer {
      private static final long MAX_BODY_SIZE = 2097152L;
      private final HttpLogger httpLogger;
      private final HttpRequest originalRequest;
      private final AsyncBody.Consumer originalConsumer;
      private final AtomicLong responseBodySize;
      private final Queue responseBody;
      private final AtomicBoolean bodyTruncated = new AtomicBoolean();

      public DeferredLoggingConsumer(HttpLogger httpLogger, HttpRequest originalRequest, AsyncBody.Consumer originalConsumer) {
         this.httpLogger = httpLogger;
         this.originalRequest = originalRequest;
         this.originalConsumer = originalConsumer;
         this.responseBodySize = new AtomicLong(0L);
         this.responseBody = new ConcurrentLinkedQueue();
      }

      public void consume(List value, AsyncBody asyncBody) throws Exception {
         try {
            value.stream().forEach((bb) -> {
               if (this.responseBodySize.addAndGet((long)bb.remaining()) < 2097152L && !this.bodyTruncated.get() && BufferUtil.isPlainText(bb)) {
                  this.responseBody.add(BufferUtil.copy(bb));
               } else {
                  this.bodyTruncated.set(true);
               }

            });
         } finally {
            this.originalConsumer.consume(value, asyncBody);
         }

      }

      public Object unwrap(Class target) {
         return Optional.ofNullable(AsyncBody.Consumer.super.unwrap(target)).orElse(this.originalConsumer.unwrap(target));
      }

      private void processAsyncBody(AsyncBody asyncBody, HttpResponse response) {
         asyncBody.done().whenComplete((v, throwable) -> {
            this.httpLogger.logStart();
            this.httpLogger.logRequest(this.originalRequest);
            this.httpLogger.logResponse(response);
            this.httpLogger.logResponseBody(this.responseBody, this.responseBodySize.get(), this.bodyTruncated.get());
            this.httpLogger.logEnd();
            this.responseBody.clear();
         });
      }
   }

   private static final class HttpLogger {
      private final Logger logger;

      private HttpLogger(Logger logger) {
         this.logger = logger;
      }

      void logRequest(HttpRequest request) {
         if (this.logger.isTraceEnabled() && request != null) {
            this.logger.trace("> {} {}", request.method(), request.uri());
            request.headers().forEach((h, vv) -> vv.forEach((v) -> this.logger.trace("> {}: {}", h, v)));
            if (!Utils.isNullOrEmpty(request.bodyString())) {
               this.logger.trace(request.bodyString());
            }
         }

      }

      void logResponse(HttpResponse response) {
         if (this.logger.isTraceEnabled() && response != null) {
            this.logger.trace("< {} {}", response.code(), response.message());
            response.headers().forEach((h, vv) -> vv.forEach((v) -> this.logger.trace("< {}: {}", h, v)));
         }

      }

      void logResponseBody(Queue responseBody, long bytesReceived, boolean truncated) {
         if (this.logger.isTraceEnabled()) {
            StringBuilder bodyString = new StringBuilder();
            if (responseBody != null && !responseBody.isEmpty()) {
               while(!responseBody.isEmpty()) {
                  bodyString.append(StandardCharsets.UTF_8.decode((ByteBuffer)responseBody.poll()));
               }
            }

            if (truncated) {
               bodyString.append("... body bytes ").append(bytesReceived);
            }

            this.logger.trace(bodyString.toString());
         }

      }

      void logStart() {
         if (this.logger.isTraceEnabled()) {
            this.logger.trace("-HTTP START-");
         }

      }

      void logEnd() {
         if (this.logger.isTraceEnabled()) {
            this.logger.trace("-HTTP END-");
         }

      }

      void logWsStart() {
         if (this.logger.isTraceEnabled()) {
            this.logger.trace("-WS START-");
         }

      }

      void logWsEnd() {
         if (this.logger.isTraceEnabled()) {
            this.logger.trace("-WS END-");
         }

      }
   }
}
