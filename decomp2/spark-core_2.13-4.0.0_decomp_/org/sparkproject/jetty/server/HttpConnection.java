package org.sparkproject.jetty.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritePendingException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpCompliance;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpParser;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.EofException;
import org.sparkproject.jetty.io.RetainableByteBuffer;
import org.sparkproject.jetty.io.RetainableByteBufferPool;
import org.sparkproject.jetty.io.WriteFlusher;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IteratingCallback;
import org.sparkproject.jetty.util.thread.Invocable;

public class HttpConnection extends AbstractConnection implements Runnable, HttpTransport, WriteFlusher.Listener, Connection.UpgradeFrom, Connection.UpgradeTo {
   private static final Logger LOG = LoggerFactory.getLogger(HttpConnection.class);
   public static final HttpField CONNECTION_CLOSE;
   private static final ThreadLocal __currentConnection;
   private final HttpConfiguration _config;
   private final Connector _connector;
   private final ByteBufferPool _bufferPool;
   private final RetainableByteBufferPool _retainableByteBufferPool;
   private final HttpInput _input;
   private final HttpGenerator _generator;
   private final HttpChannelOverHttp _channel;
   private final HttpParser _parser;
   private volatile RetainableByteBuffer _retainableByteBuffer;
   private final AsyncReadCallback _asyncReadCallback = new AsyncReadCallback();
   private final SendCallback _sendCallback = new SendCallback();
   private final boolean _recordHttpComplianceViolations;
   private final LongAdder bytesIn = new LongAdder();
   private final LongAdder bytesOut = new LongAdder();
   private boolean _useInputDirectByteBuffers;
   private boolean _useOutputDirectByteBuffers;

   public static HttpConnection getCurrentConnection() {
      return (HttpConnection)__currentConnection.get();
   }

   protected static HttpConnection setCurrentConnection(HttpConnection connection) {
      HttpConnection last = (HttpConnection)__currentConnection.get();
      __currentConnection.set(connection);
      return last;
   }

   public HttpConnection(HttpConfiguration config, Connector connector, EndPoint endPoint, boolean recordComplianceViolations) {
      super(endPoint, connector.getExecutor());
      this._config = config;
      this._connector = connector;
      this._bufferPool = this._connector.getByteBufferPool();
      this._retainableByteBufferPool = this._bufferPool.asRetainableByteBufferPool();
      this._generator = this.newHttpGenerator();
      this._channel = this.newHttpChannel();
      this._input = this._channel.getRequest().getHttpInput();
      this._parser = this.newHttpParser(config.getHttpCompliance());
      this._recordHttpComplianceViolations = recordComplianceViolations;
      if (LOG.isDebugEnabled()) {
         LOG.debug("New HTTP Connection {}", this);
      }

   }

   public long getBeginNanoTime() {
      return this._parser.getBeginNanoTime();
   }

   public HttpConfiguration getHttpConfiguration() {
      return this._config;
   }

   public boolean isRecordHttpComplianceViolations() {
      return this._recordHttpComplianceViolations;
   }

   protected HttpGenerator newHttpGenerator() {
      return new HttpGenerator(this._config.getSendServerVersion(), this._config.getSendXPoweredBy());
   }

   protected HttpChannelOverHttp newHttpChannel() {
      return new HttpChannelOverHttp(this, this._connector, this._config, this.getEndPoint(), this);
   }

   protected HttpParser newHttpParser(HttpCompliance compliance) {
      HttpParser parser = new HttpParser(this.newRequestHandler(), this.getHttpConfiguration().getRequestHeaderSize(), compliance);
      parser.setHeaderCacheSize(this.getHttpConfiguration().getHeaderCacheSize());
      parser.setHeaderCacheCaseSensitive(this.getHttpConfiguration().isHeaderCacheCaseSensitive());
      return parser;
   }

   protected HttpParser.RequestHandler newRequestHandler() {
      return this._channel;
   }

   public Server getServer() {
      return this._connector.getServer();
   }

   public Connector getConnector() {
      return this._connector;
   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   public HttpParser getParser() {
      return this._parser;
   }

   public HttpGenerator getGenerator() {
      return this._generator;
   }

   public long getMessagesIn() {
      return this.getHttpChannel().getRequests();
   }

   public long getMessagesOut() {
      return this.getHttpChannel().getRequests();
   }

   public boolean isUseInputDirectByteBuffers() {
      return this._useInputDirectByteBuffers;
   }

   public void setUseInputDirectByteBuffers(boolean useInputDirectByteBuffers) {
      this._useInputDirectByteBuffers = useInputDirectByteBuffers;
   }

   public boolean isUseOutputDirectByteBuffers() {
      return this._useOutputDirectByteBuffers;
   }

   public void setUseOutputDirectByteBuffers(boolean useOutputDirectByteBuffers) {
      this._useOutputDirectByteBuffers = useOutputDirectByteBuffers;
   }

   public ByteBuffer onUpgradeFrom() {
      if (!this.isRequestBufferEmpty()) {
         ByteBuffer unconsumed = ByteBuffer.allocateDirect(this._retainableByteBuffer.remaining());
         unconsumed.put(this._retainableByteBuffer.getBuffer());
         unconsumed.flip();
         this.releaseRequestBuffer();
         return unconsumed;
      } else {
         return null;
      }
   }

   public void onUpgradeTo(ByteBuffer buffer) {
      BufferUtil.append(this.getRequestBuffer(), buffer);
   }

   public void onFlushed(long bytes) throws IOException {
      this._channel.getResponse().getHttpOutput().onFlushed(bytes);
   }

   void releaseRequestBuffer() {
      if (this._retainableByteBuffer != null && !this._retainableByteBuffer.hasRemaining()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("releaseRequestBuffer {}", this);
         }

         if (!this._retainableByteBuffer.release()) {
            throw new IllegalStateException("unreleased buffer " + String.valueOf(this._retainableByteBuffer));
         }

         this._retainableByteBuffer = null;
      }

   }

   private ByteBuffer getRequestBuffer() {
      if (this._retainableByteBuffer == null) {
         this._retainableByteBuffer = this._retainableByteBufferPool.acquire(this.getInputBufferSize(), this.isUseInputDirectByteBuffers());
      }

      return this._retainableByteBuffer.getBuffer();
   }

   public boolean isRequestBufferEmpty() {
      return this._retainableByteBuffer == null || this._retainableByteBuffer.isEmpty();
   }

   public void onFillable() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} onFillable enter {} {}", new Object[]{this, this._channel.getState(), this._retainableByteBuffer});
      }

      HttpConnection last = setCurrentConnection(this);

      try {
         while(this.getEndPoint().isOpen()) {
            int filled = this.fillRequestBuffer();
            if (filled < 0 && this.getEndPoint().isOutputShutdown()) {
               this.close();
            }

            boolean handle = this.parseRequestBuffer();
            if (this.getEndPoint().getConnection() != this) {
               break;
            }

            if (handle) {
               boolean suspended = !this._channel.handle();
               if (suspended || this.getEndPoint().getConnection() != this) {
                  break;
               }
            } else {
               if (filled == 0) {
                  this.fillInterested();
                  break;
               }

               if (filled < 0) {
                  if (this._channel.getState().isIdle()) {
                     this.getEndPoint().shutdownOutput();
                  }
                  break;
               }
            }
         }
      } catch (Throwable var14) {
         Throwable x = var14;

         try {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} caught exception {}", new Object[]{this, this._channel.getState(), x});
            }

            if (this._retainableByteBuffer != null) {
               this._retainableByteBuffer.clear();
               this.releaseRequestBuffer();
            }
         } finally {
            this.getEndPoint().close(var14);
         }
      } finally {
         setCurrentConnection(last);
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} onFillable exit {} {}", new Object[]{this, this._channel.getState(), this._retainableByteBuffer});
         }

      }

   }

   void parseAndFillForContent() {
      if (this._parser.isTerminated()) {
         throw new IllegalStateException("Parser is terminated: " + String.valueOf(this._parser));
      } else {
         while(this._parser.inContentState() && !this.parseRequestBuffer() && (!this._parser.inContentState() || this.fillRequestBuffer() > 0)) {
         }

      }
   }

   private int fillRequestBuffer() {
      if (this._retainableByteBuffer != null && this._retainableByteBuffer.isRetained()) {
         throw new IllegalStateException("fill with unconsumed content on " + String.valueOf(this));
      } else if (this.isRequestBufferEmpty()) {
         ByteBuffer requestBuffer = this.getRequestBuffer();

         try {
            int filled = this.getEndPoint().fill(requestBuffer);
            if (filled == 0) {
               filled = this.getEndPoint().fill(requestBuffer);
            }

            if (filled > 0) {
               this.bytesIn.add((long)filled);
            } else {
               if (filled < 0) {
                  this._parser.atEOF();
               }

               this.releaseRequestBuffer();
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("{} filled {} {}", new Object[]{this, filled, this._retainableByteBuffer});
            }

            return filled;
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Unable to fill from endpoint {}", this.getEndPoint(), x);
            }

            this._parser.atEOF();
            if (this._retainableByteBuffer != null) {
               this._retainableByteBuffer.clear();
               this.releaseRequestBuffer();
            }

            return -1;
         }
      } else {
         return 0;
      }
   }

   private boolean parseRequestBuffer() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} parse {}", this, this._retainableByteBuffer);
      }

      boolean handle = this._parser.parseNext(this._retainableByteBuffer == null ? BufferUtil.EMPTY_BUFFER : this._retainableByteBuffer.getBuffer());
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} parsed {} {}", new Object[]{this, handle, this._parser});
      }

      if (this._retainableByteBuffer != null && !this._retainableByteBuffer.isRetained()) {
         this.releaseRequestBuffer();
      }

      return handle;
   }

   private boolean upgrade() {
      Connection connection = (Connection)this._channel.getRequest().getAttribute(UPGRADE_CONNECTION_ATTRIBUTE);
      if (connection == null) {
         return false;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Upgrade from {} to {}", this, connection);
         }

         this._channel.getState().upgrade();
         this.getEndPoint().upgrade(connection);
         this._channel.recycle();
         this._parser.reset();
         this._generator.reset();
         if (this._retainableByteBuffer != null) {
            if (!this._retainableByteBuffer.isRetained()) {
               this.releaseRequestBuffer();
            } else {
               LOG.warn("{} lingering content references?!?!", this);
               this._retainableByteBuffer = null;
            }
         }

         return true;
      }
   }

   public void onCompleted() {
      if (this.isFillInterested()) {
         LOG.warn("Pending read in onCompleted {} {}", this, this.getEndPoint());
         this._channel.abort(new IOException("Pending read in onCompleted"));
      } else if (this.upgrade()) {
         return;
      }

      boolean complete = this._input.consumeAll();
      if (this._channel.isExpecting100Continue()) {
         this._parser.close();
      } else if (this._generator.isPersistent() && !complete) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("unconsumed input {} {}", this, this._parser);
         }

         this._channel.abort(new IOException("unconsumed input"));
      }

      this._channel.recycle();
      if (!this._parser.isClosed()) {
         if (this._generator.isPersistent()) {
            this._parser.reset();
         } else {
            this._parser.close();
         }
      }

      this._generator.reset();
      if (getCurrentConnection() != this) {
         if (this._parser.isStart()) {
            if (this.isRequestBufferEmpty()) {
               this.fillInterested();
            } else if (this.getConnector().isRunning()) {
               try {
                  this.getExecutor().execute(this);
               } catch (RejectedExecutionException e) {
                  if (this.getConnector().isRunning()) {
                     LOG.warn("Failed dispatch of {}", this, e);
                  } else {
                     LOG.trace("IGNORED", e);
                  }

                  this.getEndPoint().close();
               }
            } else {
               this.getEndPoint().close();
            }
         } else if (this.getEndPoint().isOpen()) {
            this.fillInterested();
         }
      }

   }

   protected boolean onReadTimeout(Throwable timeout) {
      return this._channel.onIdleTimeout(timeout);
   }

   protected void onFillInterestedFailed(Throwable cause) {
      this._parser.close();
      super.onFillInterestedFailed(cause);
   }

   public void onOpen() {
      super.onOpen();
      if (this.isRequestBufferEmpty()) {
         this.fillInterested();
      } else {
         this.getExecutor().execute(this);
      }

   }

   public void onClose(Throwable cause) {
      if (cause == null) {
         this._sendCallback.close();
      } else {
         this._sendCallback.failed(cause);
      }

      super.onClose(cause);
   }

   public void run() {
      this.onFillable();
   }

   public void send(MetaData.Request request, MetaData.Response response, ByteBuffer content, boolean lastContent, Callback callback) {
      if (response == null) {
         if (!lastContent && BufferUtil.isEmpty(content)) {
            callback.succeeded();
            return;
         }
      } else if (this._channel.isExpecting100Continue()) {
         this._generator.setPersistent(false);
      }

      if (this._sendCallback.reset(request, response, content, lastContent, callback)) {
         this._sendCallback.iterate();
      }

   }

   HttpInput.Content newContent(ByteBuffer c) {
      return new Content(c);
   }

   public void abort(Throwable failure) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("abort {} {}", this, failure);
      }

      this.getEndPoint().close();
   }

   public boolean isPushSupported() {
      return false;
   }

   public void push(MetaData.Request request) {
      LOG.debug("ignore push in {}", this);
   }

   public void asyncReadFillInterested() {
      this.getEndPoint().tryFillInterested(this._asyncReadCallback);
   }

   public long getBytesIn() {
      return this.bytesIn.longValue();
   }

   public long getBytesOut() {
      return this.bytesOut.longValue();
   }

   public String toConnectionString() {
      return String.format("%s@%x[p=%s,g=%s]=>%s", this.getClass().getSimpleName(), this.hashCode(), this._parser, this._generator, this._channel);
   }

   static {
      CONNECTION_CLOSE = new PreEncodedHttpField(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString());
      __currentConnection = new ThreadLocal();
   }

   private class Content extends HttpInput.Content {
      public Content(ByteBuffer content) {
         super(content);
         HttpConnection.this._retainableByteBuffer.retain();
      }

      public void succeeded() {
         HttpConnection.this._retainableByteBuffer.release();
      }

      public void failed(Throwable x) {
         this.succeeded();
      }
   }

   private class AsyncReadCallback implements Callback {
      public void succeeded() {
         if (HttpConnection.this._channel.getRequest().getHttpInput().onContentProducible()) {
            HttpConnection.this._channel.handle();
         }

      }

      public void failed(Throwable x) {
         if (HttpConnection.this._channel.failed(x)) {
            HttpConnection.this._channel.handle();
         }

      }

      public Invocable.InvocationType getInvocationType() {
         return HttpConnection.this._channel.getRequest().getHttpInput().isAsync() ? Invocable.InvocationType.BLOCKING : Invocable.InvocationType.NON_BLOCKING;
      }
   }

   private class SendCallback extends IteratingCallback {
      private MetaData.Response _info;
      private boolean _head;
      private ByteBuffer _content;
      private boolean _lastContent;
      private Callback _callback;
      private ByteBuffer _header;
      private ByteBuffer _chunk;
      private boolean _shutdownOut;

      private SendCallback() {
         super(true);
      }

      public Invocable.InvocationType getInvocationType() {
         return this._callback.getInvocationType();
      }

      private boolean reset(MetaData.Request request, MetaData.Response info, ByteBuffer content, boolean last, Callback callback) {
         if (!this.reset()) {
            if (this.isClosed()) {
               callback.failed(new EofException());
            } else {
               callback.failed(new WritePendingException());
            }

            return false;
         } else {
            this._info = info;
            this._head = request != null && HttpMethod.HEAD.is(request.getMethod());
            this._content = content;
            this._lastContent = last;
            this._callback = callback;
            this._header = null;
            this._shutdownOut = false;
            if (HttpConnection.this.getConnector().isShutdown()) {
               HttpConnection.this._generator.setPersistent(false);
            }

            return true;
         }
      }

      public IteratingCallback.Action process() throws Exception {
         if (this._callback == null) {
            throw new IllegalStateException();
         } else {
            boolean useDirectByteBuffers = HttpConnection.this.isUseOutputDirectByteBuffers();

            while(true) {
               HttpGenerator.Result result = HttpConnection.this._generator.generateResponse(this._info, this._head, this._header, this._chunk, this._content, this._lastContent);
               if (HttpConnection.LOG.isDebugEnabled()) {
                  HttpConnection.LOG.debug("generate: {} for {} ({},{},{})@{}", new Object[]{result, this, BufferUtil.toSummaryString(this._header), BufferUtil.toSummaryString(this._content), this._lastContent, HttpConnection.this._generator.getState()});
               }

               switch (result) {
                  case NEED_INFO:
                     throw new EofException("request lifecycle violation");
                  case NEED_HEADER:
                     this._header = HttpConnection.this._bufferPool.acquire(Math.min(HttpConnection.this._config.getResponseHeaderSize(), HttpConnection.this._config.getOutputBufferSize()), useDirectByteBuffers);
                     break;
                  case HEADER_OVERFLOW:
                     if (this._header.capacity() >= HttpConnection.this._config.getResponseHeaderSize()) {
                        throw new BadMessageException(500, "Response header too large");
                     }

                     this.releaseHeader();
                     this._header = HttpConnection.this._bufferPool.acquire(HttpConnection.this._config.getResponseHeaderSize(), useDirectByteBuffers);
                     break;
                  case NEED_CHUNK:
                     this._chunk = HttpConnection.this._bufferPool.acquire(12, useDirectByteBuffers);
                     break;
                  case NEED_CHUNK_TRAILER:
                     this.releaseChunk();
                     this._chunk = HttpConnection.this._bufferPool.acquire(HttpConnection.this._config.getResponseHeaderSize(), useDirectByteBuffers);
                     break;
                  case FLUSH:
                     if (this._head || HttpConnection.this._generator.isNoContent()) {
                        BufferUtil.clear(this._chunk);
                        BufferUtil.clear(this._content);
                     }

                     byte gatherWrite = 0;
                     long bytes = 0L;
                     if (BufferUtil.hasContent(this._header)) {
                        gatherWrite = (byte)(gatherWrite + 4);
                        bytes += (long)this._header.remaining();
                     }

                     if (BufferUtil.hasContent(this._chunk)) {
                        gatherWrite = (byte)(gatherWrite + 2);
                        bytes += (long)this._chunk.remaining();
                     }

                     if (BufferUtil.hasContent(this._content)) {
                        ++gatherWrite;
                        bytes += (long)this._content.remaining();
                     }

                     HttpConnection.this.bytesOut.add(bytes);
                     switch (gatherWrite) {
                        case 1:
                           HttpConnection.this.getEndPoint().write(this, this._content);
                           break;
                        case 2:
                           HttpConnection.this.getEndPoint().write(this, this._chunk);
                           break;
                        case 3:
                           HttpConnection.this.getEndPoint().write(this, this._chunk, this._content);
                           break;
                        case 4:
                           HttpConnection.this.getEndPoint().write(this, this._header);
                           break;
                        case 5:
                           HttpConnection.this.getEndPoint().write(this, this._header, this._content);
                           break;
                        case 6:
                           HttpConnection.this.getEndPoint().write(this, this._header, this._chunk);
                           break;
                        case 7:
                           HttpConnection.this.getEndPoint().write(this, this._header, this._chunk, this._content);
                           break;
                        default:
                           this.succeeded();
                     }

                     return IteratingCallback.Action.SCHEDULED;
                  case SHUTDOWN_OUT:
                     this._shutdownOut = true;
                     break;
                  case DONE:
                     if (HttpConnection.this.getConnector().isShutdown() && HttpConnection.this._generator.isEnd() && HttpConnection.this._generator.isPersistent()) {
                        this._shutdownOut = true;
                     }

                     return IteratingCallback.Action.SUCCEEDED;
                  case CONTINUE:
                     break;
                  default:
                     throw new IllegalStateException("generateResponse=" + String.valueOf(result));
               }
            }
         }
      }

      private Callback release() {
         Callback complete = this._callback;
         this._callback = null;
         this._info = null;
         this._content = null;
         this.releaseHeader();
         this.releaseChunk();
         return complete;
      }

      private void releaseHeader() {
         if (this._header != null) {
            HttpConnection.this._bufferPool.release(this._header);
         }

         this._header = null;
      }

      private void releaseChunk() {
         if (this._chunk != null) {
            HttpConnection.this._bufferPool.release(this._chunk);
         }

         this._chunk = null;
      }

      protected void onCompleteSuccess() {
         boolean upgrading = HttpConnection.this._channel.getRequest().getAttribute(HttpTransport.UPGRADE_CONNECTION_ATTRIBUTE) != null;
         this.release().succeeded();
         if (this._shutdownOut && !upgrading) {
            HttpConnection.this.getEndPoint().shutdownOutput();
         }

      }

      public void onCompleteFailure(Throwable x) {
         HttpConnection.this.failedCallback(this.release(), x);
         if (this._shutdownOut) {
            HttpConnection.this.getEndPoint().shutdownOutput();
         }

      }

      public String toString() {
         return String.format("%s[i=%s,cb=%s]", super.toString(), this._info, this._callback);
      }
   }
}
