package org.sparkproject.jetty.client.http;

import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.HttpExchange;
import org.sparkproject.jetty.client.HttpRequest;
import org.sparkproject.jetty.client.HttpRequestException;
import org.sparkproject.jetty.client.HttpSender;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IteratingCallback;

public class HttpSenderOverHTTP extends HttpSender {
   private static final Logger LOG = LoggerFactory.getLogger(HttpSenderOverHTTP.class);
   private final IteratingCallback headersCallback = new HeadersCallback();
   private final IteratingCallback contentCallback = new ContentCallback();
   private final HttpGenerator generator = new HttpGenerator();
   private MetaData.Request metaData;
   private ByteBuffer contentBuffer;
   private boolean lastContent;
   private Callback callback;
   private boolean shutdown;

   public HttpSenderOverHTTP(HttpChannelOverHTTP channel) {
      super(channel);
   }

   public HttpChannelOverHTTP getHttpChannel() {
      return (HttpChannelOverHTTP)super.getHttpChannel();
   }

   protected void sendHeaders(HttpExchange exchange, ByteBuffer contentBuffer, boolean lastContent, Callback callback) {
      try {
         this.contentBuffer = contentBuffer;
         this.lastContent = lastContent;
         this.callback = callback;
         HttpRequest request = exchange.getRequest();
         Request.Content requestContent = request.getBody();
         long contentLength = requestContent == null ? -1L : requestContent.getLength();
         String path = request.getPath();
         String query = request.getQuery();
         if (query != null) {
            path = path + "?" + query;
         }

         this.metaData = new MetaData.Request(request.getMethod(), HttpURI.from(path), request.getVersion(), request.getHeaders(), contentLength, request.getTrailers());
         if (LOG.isDebugEnabled()) {
            LOG.debug("Sending headers with content {} last={} for {}", new Object[]{BufferUtil.toDetailString(contentBuffer), lastContent, exchange.getRequest()});
         }

         this.headersCallback.iterate();
      } catch (Throwable var11) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to send headers on exchange {}", exchange, var11);
         }

         callback.failed(var11);
      }

   }

   protected void sendContent(HttpExchange exchange, ByteBuffer contentBuffer, boolean lastContent, Callback callback) {
      try {
         this.contentBuffer = contentBuffer;
         this.lastContent = lastContent;
         this.callback = callback;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Sending content {} last={} for {}", new Object[]{BufferUtil.toDetailString(contentBuffer), lastContent, exchange.getRequest()});
         }

         this.contentCallback.iterate();
      } catch (Throwable var6) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to send content on {}", exchange, var6);
         }

         callback.failed(var6);
      }

   }

   protected void reset() {
      this.headersCallback.reset();
      this.contentCallback.reset();
      this.generator.reset();
      super.reset();
   }

   protected void dispose() {
      this.generator.abort();
      super.dispose();
      this.shutdownOutput();
   }

   private void shutdownOutput() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Request shutdown output {}", this.getHttpExchange().getRequest());
      }

      this.shutdown = true;
   }

   protected boolean isShutdown() {
      return this.shutdown;
   }

   public String toString() {
      return String.format("%s[%s]", super.toString(), this.generator);
   }

   private class HeadersCallback extends IteratingCallback {
      private ByteBuffer headerBuffer;
      private ByteBuffer chunkBuffer;
      private boolean generated;

      private HeadersCallback() {
         super(false);
      }

      protected IteratingCallback.Action process() throws Exception {
         HttpClient httpClient = HttpSenderOverHTTP.this.getHttpChannel().getHttpDestination().getHttpClient();
         HttpExchange exchange = HttpSenderOverHTTP.this.getHttpExchange();
         ByteBufferPool byteBufferPool = httpClient.getByteBufferPool();
         boolean useDirectByteBuffers = httpClient.isUseOutputDirectByteBuffers();

         while(true) {
            HttpGenerator.Result result = HttpSenderOverHTTP.this.generator.generateRequest(HttpSenderOverHTTP.this.metaData, this.headerBuffer, this.chunkBuffer, HttpSenderOverHTTP.this.contentBuffer, HttpSenderOverHTTP.this.lastContent);
            if (HttpSenderOverHTTP.LOG.isDebugEnabled()) {
               HttpSenderOverHTTP.LOG.debug("Generated headers ({} bytes), chunk ({} bytes), content ({} bytes) - {}/{} for {}", new Object[]{this.headerBuffer == null ? -1 : this.headerBuffer.remaining(), this.chunkBuffer == null ? -1 : this.chunkBuffer.remaining(), HttpSenderOverHTTP.this.contentBuffer == null ? -1 : HttpSenderOverHTTP.this.contentBuffer.remaining(), result, HttpSenderOverHTTP.this.generator, exchange.getRequest()});
            }

            switch (result) {
               case NEED_HEADER:
                  this.headerBuffer = byteBufferPool.acquire(httpClient.getRequestBufferSize(), useDirectByteBuffers);
                  break;
               case HEADER_OVERFLOW:
                  httpClient.getByteBufferPool().release(this.headerBuffer);
                  this.headerBuffer = null;
                  throw new IllegalArgumentException("Request header too large");
               case NEED_CHUNK:
                  this.chunkBuffer = byteBufferPool.acquire(12, useDirectByteBuffers);
                  break;
               case NEED_CHUNK_TRAILER:
                  this.chunkBuffer = byteBufferPool.acquire(httpClient.getRequestBufferSize(), useDirectByteBuffers);
                  break;
               case FLUSH:
                  EndPoint endPoint = HttpSenderOverHTTP.this.getHttpChannel().getHttpConnection().getEndPoint();
                  if (this.headerBuffer == null) {
                     this.headerBuffer = BufferUtil.EMPTY_BUFFER;
                  }

                  if (this.chunkBuffer == null) {
                     this.chunkBuffer = BufferUtil.EMPTY_BUFFER;
                  }

                  if (HttpSenderOverHTTP.this.contentBuffer == null) {
                     HttpSenderOverHTTP.this.contentBuffer = BufferUtil.EMPTY_BUFFER;
                  }

                  long bytes = (long)(this.headerBuffer.remaining() + this.chunkBuffer.remaining() + HttpSenderOverHTTP.this.contentBuffer.remaining());
                  HttpSenderOverHTTP.this.getHttpChannel().getHttpConnection().addBytesOut(bytes);
                  endPoint.write(this, this.headerBuffer, this.chunkBuffer, HttpSenderOverHTTP.this.contentBuffer);
                  this.generated = true;
                  return IteratingCallback.Action.SCHEDULED;
               case SHUTDOWN_OUT:
                  HttpSenderOverHTTP.this.shutdownOutput();
                  return IteratingCallback.Action.SUCCEEDED;
               case CONTINUE:
                  if (this.generated) {
                     return IteratingCallback.Action.SUCCEEDED;
                  }
                  break;
               case DONE:
                  if (this.generated) {
                     return IteratingCallback.Action.SUCCEEDED;
                  }

                  throw new HttpRequestException("Could not generate headers", exchange.getRequest());
               default:
                  throw new IllegalStateException(result.toString());
            }
         }
      }

      public void succeeded() {
         this.release();
         super.succeeded();
      }

      public void failed(Throwable x) {
         this.release();
         super.failed(x);
      }

      protected void onCompleteSuccess() {
         super.onCompleteSuccess();
         HttpSenderOverHTTP.this.callback.succeeded();
      }

      protected void onCompleteFailure(Throwable cause) {
         super.onCompleteFailure(cause);
         HttpSenderOverHTTP.this.callback.failed(cause);
      }

      private void release() {
         HttpClient httpClient = HttpSenderOverHTTP.this.getHttpChannel().getHttpDestination().getHttpClient();
         ByteBufferPool bufferPool = httpClient.getByteBufferPool();
         if (!BufferUtil.isTheEmptyBuffer(this.headerBuffer)) {
            bufferPool.release(this.headerBuffer);
         }

         this.headerBuffer = null;
         if (!BufferUtil.isTheEmptyBuffer(this.chunkBuffer)) {
            bufferPool.release(this.chunkBuffer);
         }

         this.chunkBuffer = null;
         HttpSenderOverHTTP.this.contentBuffer = null;
      }
   }

   private class ContentCallback extends IteratingCallback {
      private ByteBuffer chunkBuffer;

      public ContentCallback() {
         super(false);
      }

      protected IteratingCallback.Action process() throws Exception {
         HttpClient httpClient = HttpSenderOverHTTP.this.getHttpChannel().getHttpDestination().getHttpClient();
         ByteBufferPool bufferPool = httpClient.getByteBufferPool();
         boolean useDirectByteBuffers = httpClient.isUseOutputDirectByteBuffers();

         while(true) {
            HttpGenerator.Result result = HttpSenderOverHTTP.this.generator.generateRequest((MetaData.Request)null, (ByteBuffer)null, this.chunkBuffer, HttpSenderOverHTTP.this.contentBuffer, HttpSenderOverHTTP.this.lastContent);
            if (HttpSenderOverHTTP.LOG.isDebugEnabled()) {
               HttpSenderOverHTTP.LOG.debug("Generated content ({} bytes, last={}) - {}/{}", new Object[]{HttpSenderOverHTTP.this.contentBuffer == null ? -1 : HttpSenderOverHTTP.this.contentBuffer.remaining(), HttpSenderOverHTTP.this.lastContent, result, HttpSenderOverHTTP.this.generator});
            }

            switch (result) {
               case NEED_CHUNK:
                  this.chunkBuffer = bufferPool.acquire(12, useDirectByteBuffers);
                  break;
               case NEED_CHUNK_TRAILER:
                  this.chunkBuffer = bufferPool.acquire(httpClient.getRequestBufferSize(), useDirectByteBuffers);
                  break;
               case FLUSH:
                  EndPoint endPoint = HttpSenderOverHTTP.this.getHttpChannel().getHttpConnection().getEndPoint();
                  if (this.chunkBuffer != null) {
                     endPoint.write(this, this.chunkBuffer, HttpSenderOverHTTP.this.contentBuffer);
                  } else {
                     endPoint.write(this, HttpSenderOverHTTP.this.contentBuffer);
                  }

                  return IteratingCallback.Action.SCHEDULED;
               case SHUTDOWN_OUT:
                  HttpSenderOverHTTP.this.shutdownOutput();
               case CONTINUE:
                  break;
               case DONE:
                  this.release();
                  HttpSenderOverHTTP.this.callback.succeeded();
                  return IteratingCallback.Action.IDLE;
               default:
                  throw new IllegalStateException(result.toString());
            }
         }
      }

      protected void onCompleteFailure(Throwable cause) {
         this.release();
         HttpSenderOverHTTP.this.callback.failed(cause);
      }

      private void release() {
         HttpClient httpClient = HttpSenderOverHTTP.this.getHttpChannel().getHttpDestination().getHttpClient();
         ByteBufferPool bufferPool = httpClient.getByteBufferPool();
         bufferPool.release(this.chunkBuffer);
         this.chunkBuffer = null;
         HttpSenderOverHTTP.this.contentBuffer = null;
      }
   }
}
