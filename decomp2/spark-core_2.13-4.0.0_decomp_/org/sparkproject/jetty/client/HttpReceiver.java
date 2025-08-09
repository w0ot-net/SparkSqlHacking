package org.sparkproject.jetty.client;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.http.QuotedCSV;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.MathUtils;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class HttpReceiver {
   private static final Logger LOG = LoggerFactory.getLogger(HttpReceiver.class);
   private final AutoLock lock = new AutoLock();
   private final AtomicReference responseState;
   private final ContentListeners contentListeners;
   private final HttpChannel channel;
   private Decoder decoder;
   private Throwable failure;
   private long demand;
   private boolean stalled;

   protected HttpReceiver(HttpChannel channel) {
      this.responseState = new AtomicReference(HttpReceiver.ResponseState.IDLE);
      this.contentListeners = new ContentListeners();
      this.channel = channel;
   }

   protected HttpChannel getHttpChannel() {
      return this.channel;
   }

   void demand(long n) {
      if (n <= 0L) {
         throw new IllegalArgumentException("Invalid demand " + n);
      } else {
         boolean resume = false;

         try (AutoLock l = this.lock.lock()) {
            this.demand = MathUtils.cappedAdd(this.demand, n);
            if (this.stalled) {
               this.stalled = false;
               resume = true;
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Response demand={}/{}, resume={}", new Object[]{n, this.demand, resume});
            }
         }

         if (resume) {
            if (this.decoder != null) {
               this.decoder.resume();
            } else {
               this.receive();
            }
         }

      }
   }

   protected long demand() {
      return this.demand(LongUnaryOperator.identity());
   }

   private long demand(LongUnaryOperator operator) {
      try (AutoLock l = this.lock.lock()) {
         return this.demand = operator.applyAsLong(this.demand);
      }
   }

   protected boolean hasDemandOrStall() {
      boolean var2;
      try (AutoLock l = this.lock.lock()) {
         this.stalled = this.demand <= 0L;
         var2 = !this.stalled;
      }

      return var2;
   }

   protected HttpExchange getHttpExchange() {
      return this.channel.getHttpExchange();
   }

   protected HttpDestination getHttpDestination() {
      return this.channel.getHttpDestination();
   }

   public boolean isFailed() {
      return this.responseState.get() == HttpReceiver.ResponseState.FAILURE;
   }

   protected void receive() {
   }

   protected boolean responseBegin(HttpExchange exchange) {
      if (!this.updateResponseState(HttpReceiver.ResponseState.IDLE, HttpReceiver.ResponseState.TRANSIENT)) {
         return false;
      } else {
         HttpConversation conversation = exchange.getConversation();
         HttpResponse response = exchange.getResponse();
         HttpDestination destination = this.getHttpDestination();
         HttpClient client = destination.getHttpClient();
         ProtocolHandler protocolHandler = client.findProtocolHandler(exchange.getRequest(), response);
         Response.Listener handlerListener = null;
         if (protocolHandler != null) {
            handlerListener = protocolHandler.getResponseListener();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Response {} found protocol handler {}", response, protocolHandler);
            }
         }

         exchange.getConversation().updateResponseListeners(handlerListener);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Response begin {}", response);
         }

         ResponseNotifier notifier = destination.getResponseNotifier();
         notifier.notifyBegin((List)conversation.getResponseListeners(), response);
         if (this.updateResponseState(HttpReceiver.ResponseState.TRANSIENT, HttpReceiver.ResponseState.BEGIN)) {
            return true;
         } else {
            this.dispose();
            this.terminateResponse(exchange);
            return false;
         }
      }
   }

   protected boolean responseHeader(HttpExchange exchange, HttpField field) {
      if (!this.updateResponseState(HttpReceiver.ResponseState.BEGIN, HttpReceiver.ResponseState.HEADER, HttpReceiver.ResponseState.TRANSIENT)) {
         return false;
      } else {
         HttpResponse response = exchange.getResponse();
         ResponseNotifier notifier = this.getHttpDestination().getResponseNotifier();
         boolean process = notifier.notifyHeader((List)exchange.getConversation().getResponseListeners(), response, field);
         if (process) {
            response.addHeader(field);
            HttpHeader fieldHeader = field.getHeader();
            if (fieldHeader != null) {
               switch (fieldHeader) {
                  case SET_COOKIE:
                  case SET_COOKIE2:
                     URI uri = exchange.getRequest().getURI();
                     if (uri != null) {
                        this.storeCookie(uri, field);
                     }
               }
            }
         }

         if (this.updateResponseState(HttpReceiver.ResponseState.TRANSIENT, HttpReceiver.ResponseState.HEADER)) {
            return true;
         } else {
            this.dispose();
            this.terminateResponse(exchange);
            return false;
         }
      }
   }

   protected void storeCookie(URI uri, HttpField field) {
      try {
         String value = field.getValue();
         if (value != null) {
            Map<String, List<String>> header = new HashMap(1);
            header.put(field.getHeader().asString(), Collections.singletonList(value));
            this.getHttpDestination().getHttpClient().getCookieManager().put(uri, header);
         }
      } catch (IOException x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to store cookies {} from {}", new Object[]{field, uri, x});
         }
      }

   }

   protected boolean responseHeaders(HttpExchange exchange) {
      if (!this.updateResponseState(HttpReceiver.ResponseState.BEGIN, HttpReceiver.ResponseState.HEADER, HttpReceiver.ResponseState.TRANSIENT)) {
         return false;
      } else {
         HttpResponse response = exchange.getResponse();
         HttpFields responseHeaders = response.getHeaders();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Response headers {}{}{}", new Object[]{response, System.lineSeparator(), responseHeaders.toString().trim()});
         }

         if (!HttpMethod.HEAD.is(exchange.getRequest().getMethod())) {
            String contentEncoding = responseHeaders.get(HttpHeader.CONTENT_ENCODING);
            if (contentEncoding != null) {
               int comma = contentEncoding.indexOf(",");
               if (comma > 0) {
                  QuotedCSV parser = new QuotedCSV(false, new String[0]);
                  parser.addValue(contentEncoding);
                  List<String> values = parser.getValues();
                  contentEncoding = (String)values.get(values.size() - 1);
               }

               for(ContentDecoder.Factory factory : this.getHttpDestination().getHttpClient().getContentDecoderFactories()) {
                  if (factory.getEncoding().equalsIgnoreCase(contentEncoding)) {
                     this.decoder = new Decoder(exchange, factory.newContentDecoder());
                     break;
                  }
               }
            }
         }

         ResponseNotifier notifier = this.getHttpDestination().getResponseNotifier();
         List<Response.ResponseListener> responseListeners = exchange.getConversation().getResponseListeners();
         notifier.notifyHeaders((List)responseListeners, response);
         this.contentListeners.reset(responseListeners);
         this.contentListeners.notifyBeforeContent(response);
         if (this.updateResponseState(HttpReceiver.ResponseState.TRANSIENT, HttpReceiver.ResponseState.HEADERS)) {
            boolean hasDemand = this.hasDemandOrStall();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Response headers hasDemand={} {}", hasDemand, response);
            }

            return hasDemand;
         } else {
            this.dispose();
            this.terminateResponse(exchange);
            return false;
         }
      }
   }

   protected boolean responseContent(HttpExchange exchange, ByteBuffer buffer, Callback callback) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Response content {}{}{}", new Object[]{exchange.getResponse(), System.lineSeparator(), BufferUtil.toDetailString(buffer)});
      }

      if (this.demand() <= 0L) {
         callback.failed(new IllegalStateException("No demand for response content"));
         return false;
      } else {
         return this.decoder == null ? this.plainResponseContent(exchange, buffer, callback) : this.decodeResponseContent(buffer, callback);
      }
   }

   private boolean plainResponseContent(HttpExchange exchange, ByteBuffer buffer, Callback callback) {
      if (!this.updateResponseState(HttpReceiver.ResponseState.HEADERS, HttpReceiver.ResponseState.CONTENT, HttpReceiver.ResponseState.TRANSIENT)) {
         callback.failed(new IllegalStateException("Invalid response state " + String.valueOf(this.responseState)));
         return false;
      } else {
         HttpResponse response = exchange.getResponse();
         if (this.contentListeners.isEmpty()) {
            callback.succeeded();
         } else {
            this.contentListeners.notifyContent(response, buffer, callback);
         }

         if (this.updateResponseState(HttpReceiver.ResponseState.TRANSIENT, HttpReceiver.ResponseState.CONTENT)) {
            boolean hasDemand = this.hasDemandOrStall();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Response content {}, hasDemand={}", response, hasDemand);
            }

            return hasDemand;
         } else {
            this.dispose();
            this.terminateResponse(exchange);
            return false;
         }
      }
   }

   private boolean decodeResponseContent(ByteBuffer buffer, Callback callback) {
      return this.decoder.decode(buffer, callback);
   }

   protected boolean responseSuccess(HttpExchange exchange) {
      if (!exchange.responseComplete((Throwable)null)) {
         return false;
      } else {
         this.responseState.set(HttpReceiver.ResponseState.IDLE);
         this.reset();
         HttpResponse response = exchange.getResponse();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Response success {}", response);
         }

         List<Response.ResponseListener> listeners = exchange.getConversation().getResponseListeners();
         ResponseNotifier notifier = this.getHttpDestination().getResponseNotifier();
         notifier.notifySuccess((List)listeners, response);
         if (HttpStatus.isInterim(exchange.getResponse().getStatus())) {
            return true;
         } else {
            this.terminateResponse(exchange);
            return true;
         }
      }
   }

   protected boolean responseFailure(Throwable failure) {
      HttpExchange exchange = this.getHttpExchange();
      if (exchange == null) {
         return false;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Response failure {}", exchange.getResponse(), failure);
         }

         return exchange.responseComplete(failure) ? this.abort(exchange, failure) : false;
      }
   }

   private void terminateResponse(HttpExchange exchange) {
      Result result = exchange.terminateResponse();
      this.terminateResponse(exchange, result);
   }

   private void terminateResponse(HttpExchange exchange, Result result) {
      HttpResponse response = exchange.getResponse();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Response complete {}, result: {}", response, result);
      }

      if (result != null) {
         result = this.channel.exchangeTerminating(exchange, result);
         boolean ordered = this.getHttpDestination().getHttpClient().isStrictEventOrdering();
         if (!ordered) {
            this.channel.exchangeTerminated(exchange, result);
         }

         List<Response.ResponseListener> listeners = exchange.getConversation().getResponseListeners();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Request/Response {}: {}, notifying {}", new Object[]{this.failure == null ? "succeeded" : "failed", result, listeners});
         }

         ResponseNotifier notifier = this.getHttpDestination().getResponseNotifier();
         notifier.notifyComplete(listeners, result);
         if (ordered) {
            this.channel.exchangeTerminated(exchange, result);
         }
      }

   }

   protected void reset() {
      this.cleanup();
   }

   protected void dispose() {
      assert this.responseState.get() != HttpReceiver.ResponseState.TRANSIENT;

      this.cleanup();
   }

   private void cleanup() {
      this.contentListeners.clear();
      if (this.decoder != null) {
         this.decoder.destroy();
      }

      this.decoder = null;
      this.demand = 0L;
      this.stalled = false;
   }

   public boolean abort(HttpExchange exchange, Throwable failure) {
      ResponseState current;
      do {
         current = (ResponseState)this.responseState.get();
         if (current == HttpReceiver.ResponseState.FAILURE) {
            return false;
         }
      } while(!this.updateResponseState(current, HttpReceiver.ResponseState.FAILURE));

      boolean terminate = current != HttpReceiver.ResponseState.TRANSIENT;
      this.failure = failure;
      if (terminate) {
         this.dispose();
      }

      HttpResponse response = exchange.getResponse();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Response abort {} {} on {}: {}", new Object[]{response, exchange, this.getHttpChannel(), failure});
      }

      List<Response.ResponseListener> listeners = exchange.getConversation().getResponseListeners();
      ResponseNotifier notifier = this.getHttpDestination().getResponseNotifier();
      notifier.notifyFailure((List)listeners, response, failure);
      if (terminate) {
         this.terminateResponse(exchange);
         return true;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Concurrent failure: response termination skipped, performed by helpers");
         }

         return false;
      }
   }

   private boolean updateResponseState(ResponseState from1, ResponseState from2, ResponseState to) {
      ResponseState current;
      do {
         current = (ResponseState)this.responseState.get();
         if (current != from1 && current != from2) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("State update failed: [{},{}] -> {}: {}", new Object[]{from1, from2, to, current});
            }

            return false;
         }
      } while(!this.updateResponseState(current, to));

      return true;
   }

   private boolean updateResponseState(ResponseState from, ResponseState to) {
      while(true) {
         ResponseState current = (ResponseState)this.responseState.get();
         if (current == from) {
            if (!this.responseState.compareAndSet(current, to)) {
               continue;
            }

            return true;
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("State update failed: {} -> {}: {}", new Object[]{from, to, current});
         }

         return false;
      }
   }

   public String toString() {
      return String.format("%s@%x(rsp=%s,failure=%s)", this.getClass().getSimpleName(), this.hashCode(), this.responseState, this.failure);
   }

   private static enum ResponseState {
      TRANSIENT,
      IDLE,
      BEGIN,
      HEADER,
      HEADERS,
      CONTENT,
      FAILURE;

      // $FF: synthetic method
      private static ResponseState[] $values() {
         return new ResponseState[]{TRANSIENT, IDLE, BEGIN, HEADER, HEADERS, CONTENT, FAILURE};
      }
   }

   private class ContentListeners {
      private final Map demands = new ConcurrentHashMap();
      private final LongConsumer demand = HttpReceiver.this::demand;
      private final List listeners = new ArrayList(2);

      private void clear() {
         this.demands.clear();
         this.listeners.clear();
      }

      private void reset(List responseListeners) {
         this.clear();

         for(Response.ResponseListener listener : responseListeners) {
            if (listener instanceof Response.DemandedContentListener) {
               this.listeners.add((Response.DemandedContentListener)listener);
            }
         }

      }

      private boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      private void notifyBeforeContent(HttpResponse response) {
         if (this.isEmpty()) {
            this.demand.accept(1L);
         } else {
            ResponseNotifier notifier = HttpReceiver.this.getHttpDestination().getResponseNotifier();
            notifier.notifyBeforeContent((Response)response, (ObjLongConsumer)(this::demand), (List)this.listeners);
         }

      }

      private void notifyContent(HttpResponse response, ByteBuffer buffer, Callback callback) {
         HttpReceiver.this.demand((d) -> d - 1L);
         ResponseNotifier notifier = HttpReceiver.this.getHttpDestination().getResponseNotifier();
         notifier.notifyContent((Response)response, (ObjLongConsumer)(this::demand), (ByteBuffer)buffer, (Callback)callback, (List)this.listeners);
      }

      private void demand(Object context, long value) {
         if (this.listeners.size() > 1) {
            this.accept(context, value);
         } else {
            this.demand.accept(value);
         }

      }

      private void accept(Object context, long value) {
         this.demands.merge(context, value, MathUtils::cappedAdd);
         if (this.demands.size() == this.listeners.size()) {
            long minDemand = Long.MAX_VALUE;

            for(Long demand : this.demands.values()) {
               if (demand < minDemand) {
                  minDemand = demand;
               }
            }

            if (minDemand > 0L) {
               Iterator<Map.Entry<Object, Long>> iterator = this.demands.entrySet().iterator();

               while(iterator.hasNext()) {
                  Map.Entry<Object, Long> entry = (Map.Entry)iterator.next();
                  long newValue = (Long)entry.getValue() - minDemand;
                  if (newValue == 0L) {
                     iterator.remove();
                  } else {
                     entry.setValue(newValue);
                  }
               }

               this.demand.accept(minDemand);
            }
         }

      }
   }

   private class Decoder implements Destroyable {
      private final HttpExchange exchange;
      private final ContentDecoder decoder;
      private ByteBuffer encoded;
      private Callback callback;

      private Decoder(HttpExchange exchange, ContentDecoder decoder) {
         this.exchange = exchange;
         this.decoder = (ContentDecoder)Objects.requireNonNull(decoder);
         decoder.beforeDecoding(exchange);
      }

      private boolean decode(ByteBuffer encoded, Callback callback) {
         this.encoded = encoded;
         this.callback = callback;
         HttpResponse response = this.exchange.getResponse();
         if (HttpReceiver.LOG.isDebugEnabled()) {
            HttpReceiver.LOG.debug("Response content decoding {} with {}{}{}", new Object[]{response, this.decoder, System.lineSeparator(), BufferUtil.toDetailString(encoded)});
         }

         boolean needInput = this.decode();
         if (!needInput) {
            return false;
         } else {
            boolean hasDemand = HttpReceiver.this.hasDemandOrStall();
            if (HttpReceiver.LOG.isDebugEnabled()) {
               HttpReceiver.LOG.debug("Response content decoded, hasDemand={} {}", hasDemand, response);
            }

            return hasDemand;
         }
      }

      private boolean decode() {
         while(HttpReceiver.this.updateResponseState(HttpReceiver.ResponseState.HEADERS, HttpReceiver.ResponseState.CONTENT, HttpReceiver.ResponseState.TRANSIENT)) {
            DecodeResult result = this.decodeChunk();
            if (HttpReceiver.this.updateResponseState(HttpReceiver.ResponseState.TRANSIENT, HttpReceiver.ResponseState.CONTENT)) {
               if (result == HttpReceiver.DecodeResult.NEED_INPUT) {
                  return true;
               }

               if (result == HttpReceiver.DecodeResult.ABORT) {
                  return false;
               }

               boolean hasDemand = HttpReceiver.this.hasDemandOrStall();
               if (HttpReceiver.LOG.isDebugEnabled()) {
                  HttpReceiver.LOG.debug("Response content decoded chunk, hasDemand={} {}", hasDemand, this.exchange.getResponse());
               }

               if (hasDemand) {
                  continue;
               }

               return false;
            }

            HttpReceiver.this.dispose();
            HttpReceiver.this.terminateResponse(this.exchange);
            return false;
         }

         this.callback.failed(new IllegalStateException("Invalid response state " + String.valueOf(HttpReceiver.this.responseState)));
         return false;
      }

      private DecodeResult decodeChunk() {
         try {
            do {
               ByteBuffer buffer = this.decoder.decode(this.encoded);
               if (buffer.hasRemaining()) {
                  HttpResponse response = this.exchange.getResponse();
                  if (HttpReceiver.LOG.isDebugEnabled()) {
                     HttpReceiver.LOG.debug("Response content decoded chunk {}{}{}", new Object[]{response, System.lineSeparator(), BufferUtil.toDetailString(buffer)});
                  }

                  ContentListeners var10000 = HttpReceiver.this.contentListeners;
                  Runnable var10003 = () -> this.decoder.release(buffer);
                  Callback var10004 = this.callback;
                  Objects.requireNonNull(var10004);
                  var10000.notifyContent(response, buffer, Callback.from(var10003, var10004::failed));
                  return HttpReceiver.DecodeResult.DECODE;
               }
            } while(this.encoded.hasRemaining());

            this.callback.succeeded();
            this.encoded = null;
            this.callback = null;
            return HttpReceiver.DecodeResult.NEED_INPUT;
         } catch (Throwable x) {
            this.callback.failed(x);
            return HttpReceiver.DecodeResult.ABORT;
         }
      }

      private void resume() {
         if (HttpReceiver.LOG.isDebugEnabled()) {
            HttpReceiver.LOG.debug("Response content resume decoding {} with {}", this.exchange.getResponse(), this.decoder);
         }

         if (this.callback == null) {
            HttpReceiver.this.receive();
         } else {
            boolean needInput = this.decode();
            if (needInput) {
               HttpReceiver.this.receive();
            }

         }
      }

      public void destroy() {
         this.decoder.afterDecoding(this.exchange);
         if (this.decoder instanceof Destroyable) {
            ((Destroyable)this.decoder).destroy();
         }

      }
   }

   private static enum DecodeResult {
      DECODE,
      NEED_INPUT,
      ABORT;

      // $FF: synthetic method
      private static DecodeResult[] $values() {
         return new DecodeResult[]{DECODE, NEED_INPUT, ABORT};
      }
   }
}
