package org.sparkproject.jetty.client.http;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.HttpClientTransport;
import org.sparkproject.jetty.client.HttpExchange;
import org.sparkproject.jetty.client.HttpReceiver;
import org.sparkproject.jetty.client.HttpResponse;
import org.sparkproject.jetty.client.HttpResponseException;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpParser;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.RetainableByteBuffer;
import org.sparkproject.jetty.io.RetainableByteBufferPool;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;

public class HttpReceiverOverHTTP extends HttpReceiver implements HttpParser.ResponseHandler {
   private static final Logger LOG = LoggerFactory.getLogger(HttpReceiverOverHTTP.class);
   private final LongAdder inMessages = new LongAdder();
   private final HttpParser parser;
   private final RetainableByteBufferPool retainableByteBufferPool;
   private RetainableByteBuffer networkBuffer;
   private boolean shutdown;
   private boolean complete;
   private boolean unsolicited;
   private String method;
   private int status;

   public HttpReceiverOverHTTP(HttpChannelOverHTTP channel) {
      super(channel);
      HttpClient httpClient = channel.getHttpDestination().getHttpClient();
      this.parser = new HttpParser(this, httpClient.getMaxResponseHeadersSize(), httpClient.getHttpCompliance());
      HttpClientTransport transport = httpClient.getTransport();
      if (transport instanceof HttpClientTransportOverHTTP) {
         HttpClientTransportOverHTTP httpTransport = (HttpClientTransportOverHTTP)transport;
         this.parser.setHeaderCacheSize(httpTransport.getHeaderCacheSize());
         this.parser.setHeaderCacheCaseSensitive(httpTransport.isHeaderCacheCaseSensitive());
      }

      this.retainableByteBufferPool = httpClient.getByteBufferPool().asRetainableByteBufferPool();
   }

   public HttpChannelOverHTTP getHttpChannel() {
      return (HttpChannelOverHTTP)super.getHttpChannel();
   }

   private HttpConnectionOverHTTP getHttpConnection() {
      return this.getHttpChannel().getHttpConnection();
   }

   protected ByteBuffer getResponseBuffer() {
      return this.networkBuffer == null ? null : this.networkBuffer.getBuffer();
   }

   public void receive() {
      if (this.networkBuffer == null) {
         this.acquireNetworkBuffer();
      }

      this.process();
   }

   private void acquireNetworkBuffer() {
      this.networkBuffer = this.newNetworkBuffer();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Acquired {}", this.networkBuffer);
      }

   }

   private void reacquireNetworkBuffer() {
      RetainableByteBuffer currentBuffer = this.networkBuffer;
      if (currentBuffer == null) {
         throw new IllegalStateException();
      } else if (currentBuffer.hasRemaining()) {
         throw new IllegalStateException();
      } else {
         currentBuffer.release();
         this.networkBuffer = this.newNetworkBuffer();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Reacquired {} <- {}", currentBuffer, this.networkBuffer);
         }

      }
   }

   private RetainableByteBuffer newNetworkBuffer() {
      HttpClient client = this.getHttpDestination().getHttpClient();
      boolean direct = client.isUseInputDirectByteBuffers();
      return this.retainableByteBufferPool.acquire(client.getResponseBufferSize(), direct);
   }

   private void releaseNetworkBuffer() {
      if (this.networkBuffer != null) {
         this.networkBuffer.release();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Released {}", this.networkBuffer);
         }

         this.networkBuffer = null;
      }
   }

   protected ByteBuffer onUpgradeFrom() {
      RetainableByteBuffer networkBuffer = this.networkBuffer;
      if (networkBuffer == null) {
         return null;
      } else {
         ByteBuffer upgradeBuffer = null;
         if (networkBuffer.hasRemaining()) {
            HttpClient client = this.getHttpDestination().getHttpClient();
            upgradeBuffer = BufferUtil.allocate(networkBuffer.remaining(), client.isUseInputDirectByteBuffers());
            BufferUtil.clearToFill(upgradeBuffer);
            BufferUtil.put(networkBuffer.getBuffer(), upgradeBuffer);
            BufferUtil.flipToFlush(upgradeBuffer, 0);
         }

         this.releaseNetworkBuffer();
         return upgradeBuffer;
      }
   }

   private void process() {
      HttpConnectionOverHTTP connection = this.getHttpConnection();
      EndPoint endPoint = connection.getEndPoint();

      try {
         while(!this.parse()) {
            if (connection.isClosed()) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Closed {}", connection);
               }

               this.releaseNetworkBuffer();
               return;
            }

            if (this.networkBuffer.isRetained()) {
               this.reacquireNetworkBuffer();
            }

            int read = endPoint.fill(this.networkBuffer.getBuffer());
            if (LOG.isDebugEnabled()) {
               LOG.debug("Read {} bytes in {} from {}", new Object[]{read, this.networkBuffer, endPoint});
            }

            if (read <= 0) {
               if (read == 0) {
                  assert this.networkBuffer.isEmpty();

                  this.releaseNetworkBuffer();
                  this.fillInterested();
                  return;
               }

               this.releaseNetworkBuffer();
               this.shutdown();
               return;
            }

            connection.addBytesIn((long)read);
         }

      } catch (Throwable var4) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Error processing {}", endPoint, var4);
         }

         this.releaseNetworkBuffer();
         this.failAndClose(var4);
      }
   }

   private boolean parse() {
      do {
         boolean handle = this.parser.parseNext(this.networkBuffer.getBuffer());
         boolean failed = this.isFailed();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Parse result={}, failed={}", handle, failed);
         }

         if (failed) {
            this.parser.close();
         }

         if (handle) {
            return !failed;
         }

         boolean complete = this.complete;
         this.complete = false;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Parse complete={}, {} {}", new Object[]{complete, this.networkBuffer, this.parser});
         }

         if (complete) {
            int status = this.status;
            this.status = 0;
            if (status == 101) {
               return true;
            }

            String method = this.method;
            this.method = null;
            if (this.getHttpChannel().isTunnel(method, status)) {
               return true;
            }

            if (this.networkBuffer.isEmpty()) {
               return false;
            }

            if (!HttpStatus.isInformational(status)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Discarding unexpected content after response {}: {}", status, this.networkBuffer);
               }

               this.networkBuffer.clear();
            }

            return false;
         }
      } while(!this.networkBuffer.isEmpty());

      return false;
   }

   protected void fillInterested() {
      this.getHttpConnection().fillInterested();
   }

   private void shutdown() {
      this.shutdown = true;
      this.parser.atEOF();
      this.parser.parseNext(BufferUtil.EMPTY_BUFFER);
   }

   protected boolean isShutdown() {
      return this.shutdown;
   }

   public void startResponse(HttpVersion version, int status, String reason) {
      HttpExchange exchange = this.getHttpExchange();
      this.unsolicited = exchange == null;
      if (exchange != null) {
         this.method = exchange.getRequest().getMethod();
         this.status = status;
         this.parser.setHeadResponse(HttpMethod.HEAD.is(this.method) || this.getHttpChannel().isTunnel(this.method, status));
         exchange.getResponse().version(version).status(status).reason(reason);
         this.responseBegin(exchange);
      }
   }

   public void parsedHeader(HttpField field) {
      HttpExchange exchange = this.getHttpExchange();
      this.unsolicited |= exchange == null;
      if (!this.unsolicited) {
         this.responseHeader(exchange, field);
      }
   }

   public boolean headerComplete() {
      HttpExchange exchange = this.getHttpExchange();
      this.unsolicited |= exchange == null;
      if (this.unsolicited) {
         return false;
      } else {
         exchange.getRequest().getConversation().setAttribute(EndPoint.class.getName(), this.getHttpConnection().getEndPoint());
         this.getHttpConnection().onResponseHeaders(exchange);
         return !this.responseHeaders(exchange);
      }
   }

   public boolean content(ByteBuffer buffer) {
      HttpExchange exchange = this.getHttpExchange();
      this.unsolicited |= exchange == null;
      if (this.unsolicited) {
         return false;
      } else {
         RetainableByteBuffer networkBuffer = this.networkBuffer;
         networkBuffer.retain();
         Objects.requireNonNull(networkBuffer);
         return !this.responseContent(exchange, buffer, Callback.from((Runnable)(networkBuffer::release), (Consumer)((failure) -> {
            networkBuffer.release();
            this.failAndClose(failure);
         })));
      }
   }

   public boolean contentComplete() {
      return false;
   }

   public void parsedTrailer(HttpField trailer) {
      HttpExchange exchange = this.getHttpExchange();
      this.unsolicited |= exchange == null;
      if (!this.unsolicited) {
         exchange.getResponse().trailer(trailer);
      }
   }

   public boolean messageComplete() {
      HttpExchange exchange = this.getHttpExchange();
      if (exchange != null && !this.unsolicited) {
         int status = exchange.getResponse().getStatus();
         if (!HttpStatus.isInterim(status)) {
            this.inMessages.increment();
            this.complete = true;
         }

         boolean stopParsing = !this.responseSuccess(exchange);
         if (status == 101) {
            stopParsing = true;
         }

         return stopParsing;
      } else {
         this.getHttpConnection().close();
         return false;
      }
   }

   public void earlyEOF() {
      HttpExchange exchange = this.getHttpExchange();
      HttpConnectionOverHTTP connection = this.getHttpConnection();
      if (exchange != null && !this.unsolicited) {
         this.failAndClose(new EOFException(String.valueOf(connection)));
      } else {
         connection.close();
      }

   }

   public void badMessage(BadMessageException failure) {
      HttpExchange exchange = this.getHttpExchange();
      if (exchange != null && !this.unsolicited) {
         HttpResponse response = exchange.getResponse();
         response.status(failure.getCode()).reason(failure.getReason());
         this.failAndClose(new HttpResponseException("HTTP protocol violation: bad response on " + String.valueOf(this.getHttpConnection()), response, failure));
      } else {
         this.getHttpConnection().close();
      }

   }

   protected void reset() {
      super.reset();
      this.parser.reset();
   }

   private void failAndClose(Throwable failure) {
      if (this.responseFailure(failure)) {
         this.getHttpConnection().close(failure);
      }

   }

   long getMessagesIn() {
      return this.inMessages.longValue();
   }

   public String toString() {
      return String.format("%s[%s]", super.toString(), this.parser);
   }
}
