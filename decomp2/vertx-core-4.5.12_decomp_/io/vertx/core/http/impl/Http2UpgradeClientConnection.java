package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.WriteStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

public class Http2UpgradeClientConnection implements HttpClientConnection {
   private static final Object SEND_BUFFERED_MESSAGES = new Object();
   private static final Logger log = LoggerFactory.getLogger(Http2UpgradeClientConnection.class);
   private HttpClientBase client;
   private HttpClientConnection current;
   private boolean upgradeProcessed;
   private Handler closeHandler;
   private Handler shutdownHandler;
   private Handler goAwayHandler;
   private Handler exceptionHandler;
   private Handler pingHandler;
   private Handler evictionHandler;
   private Handler concurrencyChangeHandler;
   private Handler remoteSettingsHandler;

   Http2UpgradeClientConnection(HttpClientBase client, Http1xClientConnection connection) {
      this.client = client;
      this.current = connection;
   }

   public HttpClientConnection unwrap() {
      return this.current;
   }

   public long concurrency() {
      return this.upgradeProcessed ? this.current.concurrency() : 1L;
   }

   public long activeStreams() {
      return this.current.concurrency();
   }

   public ChannelHandlerContext channelHandlerContext() {
      return this.current.channelHandlerContext();
   }

   public Channel channel() {
      return this.current.channel();
   }

   public Future close() {
      return this.current.close();
   }

   public Object metric() {
      return this.current.metric();
   }

   public long lastResponseReceivedTimestamp() {
      return this.current.lastResponseReceivedTimestamp();
   }

   public void createStream(ContextInternal context, Handler handler) {
      if (this.current instanceof Http1xClientConnection && !this.upgradeProcessed) {
         this.current.createStream(context, (ar) -> {
            if (ar.succeeded()) {
               HttpClientStream stream = (HttpClientStream)ar.result();
               UpgradingStream upgradingStream = new UpgradingStream(stream, this, (Http1xClientConnection)this.current);
               handler.handle(Future.succeededFuture(upgradingStream));
            } else {
               handler.handle(ar);
            }

         });
      } else {
         this.current.createStream(context, (ar) -> {
            if (ar.succeeded()) {
               handler.handle(Future.succeededFuture(new DelegatingStream(this, (HttpClientStream)ar.result())));
            } else {
               handler.handle(ar);
            }

         });
      }

   }

   public Future createRequest(ContextInternal context) {
      return ((HttpClientImpl)this.client).createRequest(this, context);
   }

   public ContextInternal getContext() {
      return this.current.getContext();
   }

   public HttpConnection remoteSettingsHandler(Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.remoteSettingsHandler = handler;
      } else {
         this.current.remoteSettingsHandler(handler);
      }

      return this;
   }

   public HttpConnection pingHandler(@Nullable Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.pingHandler = handler;
      } else {
         this.current.pingHandler(handler);
      }

      return this;
   }

   public HttpConnection goAwayHandler(@Nullable Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.goAwayHandler = handler;
      } else {
         this.current.goAwayHandler(handler);
      }

      return this;
   }

   public HttpConnection shutdownHandler(@Nullable Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.shutdownHandler = handler;
      } else {
         this.current.shutdownHandler(handler);
      }

      return this;
   }

   public HttpConnection closeHandler(Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.closeHandler = handler;
      }

      this.current.closeHandler(handler);
      return this;
   }

   public HttpConnection exceptionHandler(Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.exceptionHandler = handler;
      }

      this.current.exceptionHandler(handler);
      return this;
   }

   public HttpClientConnection evictionHandler(Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.evictionHandler = handler;
      }

      this.current.evictionHandler(handler);
      return this;
   }

   public HttpClientConnection concurrencyChangeHandler(Handler handler) {
      if (this.current instanceof Http1xClientConnection) {
         this.concurrencyChangeHandler = handler;
      }

      this.current.concurrencyChangeHandler(handler);
      return this;
   }

   public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
      return this.current.goAway(errorCode, lastStreamId, debugData);
   }

   public Future shutdown(long timeout, TimeUnit unit) {
      return this.current.shutdown(timeout, unit);
   }

   public Future updateSettings(Http2Settings settings) {
      return this.current.updateSettings(settings);
   }

   public HttpConnection updateSettings(Http2Settings settings, Handler completionHandler) {
      return this.current.updateSettings(settings, completionHandler);
   }

   public Http2Settings settings() {
      return this.current.settings();
   }

   public Http2Settings remoteSettings() {
      return this.current.remoteSettings();
   }

   public HttpConnection ping(Buffer data, Handler pongHandler) {
      return this.current.ping(data, pongHandler);
   }

   public Future ping(Buffer data) {
      return this.current.ping(data);
   }

   public SocketAddress remoteAddress() {
      return this.current.remoteAddress();
   }

   public SocketAddress remoteAddress(boolean real) {
      return this.current.remoteAddress(real);
   }

   public SocketAddress localAddress() {
      return this.current.localAddress();
   }

   public SocketAddress localAddress(boolean real) {
      return this.current.localAddress(real);
   }

   public boolean isSsl() {
      return this.current.isSsl();
   }

   public SSLSession sslSession() {
      return this.current.sslSession();
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.current.peerCertificateChain();
   }

   public List peerCertificates() throws SSLPeerUnverifiedException {
      return this.current.peerCertificates();
   }

   public boolean isValid() {
      return this.current.isValid();
   }

   public String indicatedServerName() {
      return this.current.indicatedServerName();
   }

   private static class DelegatingStream implements HttpClientStream {
      private final Http2UpgradeClientConnection connection;
      private final HttpClientStream delegate;

      DelegatingStream(Http2UpgradeClientConnection connection, HttpClientStream delegate) {
         this.connection = connection;
         this.delegate = delegate;
      }

      public int id() {
         return this.delegate.id();
      }

      public Object metric() {
         return this.delegate.metric();
      }

      public Object trace() {
         return this.delegate.trace();
      }

      public HttpVersion version() {
         return this.delegate.version();
      }

      public HttpClientConnection connection() {
         return this.connection;
      }

      public ContextInternal getContext() {
         return this.delegate.getContext();
      }

      public void writeHead(HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         this.delegate.writeHead(request, chunked, buf, end, priority, connect, handler);
      }

      public void writeBuffer(ByteBuf buf, boolean end, Handler listener) {
         this.delegate.writeBuffer(buf, end, listener);
      }

      public void writeFrame(int type, int flags, ByteBuf payload) {
         this.delegate.writeFrame(type, flags, payload);
      }

      public void continueHandler(Handler handler) {
         this.delegate.continueHandler(handler);
      }

      public void earlyHintsHandler(Handler handler) {
         this.delegate.earlyHintsHandler(handler);
      }

      public void pushHandler(Handler handler) {
         this.delegate.pushHandler(handler);
      }

      public void unknownFrameHandler(Handler handler) {
         this.delegate.unknownFrameHandler(handler);
      }

      public void headHandler(Handler handler) {
         this.delegate.headHandler(handler);
      }

      public void chunkHandler(Handler handler) {
         this.delegate.chunkHandler(handler);
      }

      public void endHandler(Handler handler) {
         this.delegate.endHandler(handler);
      }

      public void priorityHandler(Handler handler) {
         this.delegate.priorityHandler(handler);
      }

      public void closeHandler(Handler handler) {
         this.delegate.closeHandler(handler);
      }

      public void doSetWriteQueueMaxSize(int size) {
         this.delegate.doSetWriteQueueMaxSize(size);
      }

      public boolean isNotWritable() {
         return this.delegate.isNotWritable();
      }

      public void doPause() {
         this.delegate.doPause();
      }

      public void doFetch(long amount) {
         this.delegate.doFetch(amount);
      }

      public void reset(Throwable cause) {
         this.delegate.reset(cause);
      }

      public StreamPriority priority() {
         return this.delegate.priority();
      }

      public void updatePriority(StreamPriority streamPriority) {
         this.delegate.updatePriority(streamPriority);
      }

      public WriteStream exceptionHandler(@Nullable Handler handler) {
         this.delegate.exceptionHandler(handler);
         return this;
      }

      public WriteStream setWriteQueueMaxSize(int maxSize) {
         this.delegate.setWriteQueueMaxSize(maxSize);
         return this;
      }

      public boolean writeQueueFull() {
         return this.delegate.writeQueueFull();
      }

      public WriteStream drainHandler(@Nullable Handler handler) {
         this.delegate.drainHandler(handler);
         return this;
      }
   }

   private static class UpgradingStream implements HttpClientStream {
      private final Http1xClientConnection upgradingConnection;
      private final HttpClientStream upgradingStream;
      private final Http2UpgradeClientConnection upgradedConnection;
      private HttpClientStream upgradedStream;
      private Handler headHandler;
      private Handler chunkHandler;
      private Handler endHandler;
      private Handler priorityHandler;
      private Handler exceptionHandler;
      private Handler drainHandler;
      private Handler continueHandler;
      private Handler earlyHintsHandler;
      private Handler pushHandler;
      private Handler unknownFrameHandler;
      private Handler closeHandler;

      UpgradingStream(HttpClientStream stream, Http2UpgradeClientConnection upgradedConnection, Http1xClientConnection upgradingConnection) {
         this.upgradedConnection = upgradedConnection;
         this.upgradingConnection = upgradingConnection;
         this.upgradingStream = stream;
      }

      public HttpClientConnection connection() {
         return this.upgradedConnection;
      }

      public void writeHead(HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         final ChannelPipeline pipeline = this.upgradingConnection.channel().pipeline();
         HttpClientCodec httpCodec = (HttpClientCodec)pipeline.get(HttpClientCodec.class);
         VertxHttp2ClientUpgradeCodec upgradeCodec = new VertxHttp2ClientUpgradeCodec(this.upgradedConnection.client.options().getInitialSettings()) {
            public void upgradeTo(ChannelHandlerContext ctx, FullHttpResponse upgradeResponse) throws Exception {
               VertxHttp2ConnectionHandler<Http2ClientConnection> handler = Http2ClientConnection.createHttp2ConnectionHandler(UpgradingStream.this.upgradedConnection.client, UpgradingStream.this.upgradingConnection.metrics, UpgradingStream.this.upgradingConnection.getContext(), true, UpgradingStream.this.upgradedConnection.current.metric());
               UpgradingStream.this.upgradingConnection.channel().pipeline().addLast(new ChannelHandler[]{handler});
               handler.connectFuture().addListener((future) -> {
                  if (!future.isSuccess()) {
                     Http2UpgradeClientConnection.log.error(future.cause().getMessage(), future.cause());
                  } else {
                     Http2ClientConnection conn = (Http2ClientConnection)future.getNow();
                     conn.upgradeStream(UpgradingStream.this.upgradingStream.metric(), UpgradingStream.this.upgradingStream.trace(), UpgradingStream.this.upgradingStream.getContext(), (ar) -> {
                        UpgradingStream.this.upgradingConnection.closeHandler((Handler)null);
                        UpgradingStream.this.upgradingConnection.exceptionHandler((Handler)null);
                        UpgradingStream.this.upgradingConnection.evictionHandler((Handler)null);
                        UpgradingStream.this.upgradingConnection.concurrencyChangeHandler((Handler)null);
                        if (ar.succeeded()) {
                           UpgradingStream.this.upgradedStream = (HttpClientStream)ar.result();
                           UpgradingStream.this.upgradedStream.headHandler(UpgradingStream.this.headHandler);
                           UpgradingStream.this.upgradedStream.chunkHandler(UpgradingStream.this.chunkHandler);
                           UpgradingStream.this.upgradedStream.endHandler(UpgradingStream.this.endHandler);
                           UpgradingStream.this.upgradedStream.priorityHandler(UpgradingStream.this.priorityHandler);
                           UpgradingStream.this.upgradedStream.exceptionHandler(UpgradingStream.this.exceptionHandler);
                           UpgradingStream.this.upgradedStream.drainHandler(UpgradingStream.this.drainHandler);
                           UpgradingStream.this.upgradedStream.continueHandler(UpgradingStream.this.continueHandler);
                           UpgradingStream.this.upgradedStream.earlyHintsHandler(UpgradingStream.this.earlyHintsHandler);
                           UpgradingStream.this.upgradedStream.pushHandler(UpgradingStream.this.pushHandler);
                           UpgradingStream.this.upgradedStream.unknownFrameHandler(UpgradingStream.this.unknownFrameHandler);
                           UpgradingStream.this.upgradedStream.closeHandler(UpgradingStream.this.closeHandler);
                           UpgradingStream.this.upgradingStream.headHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.chunkHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.endHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.priorityHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.exceptionHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.drainHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.continueHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.earlyHintsHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.pushHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.unknownFrameHandler((Handler)null);
                           UpgradingStream.this.upgradingStream.closeHandler((Handler)null);
                           UpgradingStream.this.headHandler = null;
                           UpgradingStream.this.chunkHandler = null;
                           UpgradingStream.this.endHandler = null;
                           UpgradingStream.this.priorityHandler = null;
                           UpgradingStream.this.exceptionHandler = null;
                           UpgradingStream.this.drainHandler = null;
                           UpgradingStream.this.continueHandler = null;
                           UpgradingStream.this.earlyHintsHandler = null;
                           UpgradingStream.this.pushHandler = null;
                           UpgradingStream.this.closeHandler = null;
                           UpgradingStream.this.upgradedConnection.current = conn;
                           conn.closeHandler(UpgradingStream.this.upgradedConnection.closeHandler);
                           conn.exceptionHandler(UpgradingStream.this.upgradedConnection.exceptionHandler);
                           conn.pingHandler(UpgradingStream.this.upgradedConnection.pingHandler);
                           conn.goAwayHandler(UpgradingStream.this.upgradedConnection.goAwayHandler);
                           conn.shutdownHandler(UpgradingStream.this.upgradedConnection.shutdownHandler);
                           conn.remoteSettingsHandler(UpgradingStream.this.upgradedConnection.remoteSettingsHandler);
                           conn.evictionHandler(UpgradingStream.this.upgradedConnection.evictionHandler);
                           conn.concurrencyChangeHandler(UpgradingStream.this.upgradedConnection.concurrencyChangeHandler);
                           Handler<Long> concurrencyChangeHandler = UpgradingStream.this.upgradedConnection.concurrencyChangeHandler;
                           UpgradingStream.this.upgradedConnection.closeHandler = null;
                           UpgradingStream.this.upgradedConnection.exceptionHandler = null;
                           UpgradingStream.this.upgradedConnection.pingHandler = null;
                           UpgradingStream.this.upgradedConnection.goAwayHandler = null;
                           UpgradingStream.this.upgradedConnection.shutdownHandler = null;
                           UpgradingStream.this.upgradedConnection.remoteSettingsHandler = null;
                           UpgradingStream.this.upgradedConnection.evictionHandler = null;
                           UpgradingStream.this.upgradedConnection.concurrencyChangeHandler = null;
                           concurrencyChangeHandler.handle(conn.concurrency());
                        } else {
                           Http2UpgradeClientConnection.log.error(ar.cause().getMessage(), ar.cause());
                        }

                     });
                  }
               });
               handler.clientUpgrade(ctx);
            }
         };
         HttpClientUpgradeHandler upgradeHandler = new HttpClientUpgradeHandler(httpCodec, upgradeCodec, 65536) {
            private long bufferedSize = 0L;
            private Deque buffered = new ArrayDeque();

            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
               if (this.buffered != null) {
                  int maxContent = this.maxContentLength();
                  boolean lower = this.bufferedSize < (long)maxContent;
                  if (msg instanceof ByteBufHolder) {
                     this.bufferedSize += (long)((ByteBufHolder)msg).content().readableBytes();
                  } else if (msg instanceof ByteBuf) {
                     this.bufferedSize += (long)((ByteBuf)msg).readableBytes();
                  }

                  this.buffered.add(msg);
                  if (this.bufferedSize >= (long)maxContent && lower) {
                     ctx.fireExceptionCaught(new TooLongFrameException("Max content exceeded " + this.maxContentLength() + " bytes."));
                  }
               } else {
                  super.channelRead(ctx, msg);
               }

            }

            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
               if (Http2UpgradeClientConnection.SEND_BUFFERED_MESSAGES == evt) {
                  Deque<Object> messages = this.buffered;
                  this.buffered = null;

                  Object msg;
                  while((msg = messages.poll()) != null) {
                     super.channelRead(ctx, msg);
                  }
               } else {
                  super.userEventTriggered(ctx, evt);
               }

            }

            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
               if (this.buffered != null) {
                  Deque<Object> messages = this.buffered;
                  this.buffered = null;

                  Object msg;
                  while((msg = messages.poll()) != null) {
                     ReferenceCountUtil.release(msg);
                  }
               }

               super.handlerRemoved(ctx);
            }
         };

         class UpgradeRequestHandler extends ChannelInboundHandlerAdapter {
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
               super.userEventTriggered(ctx, evt);
               ChannelPipeline pipeline = ctx.pipeline();
               if (evt instanceof HttpClientUpgradeHandler.UpgradeEvent) {
                  switch ((HttpClientUpgradeHandler.UpgradeEvent)evt) {
                     case UPGRADE_SUCCESSFUL:
                        pipeline.remove(UpgradingStream.this.upgradingConnection.channelHandlerContext().handler());
                     case UPGRADE_REJECTED:
                        pipeline.remove(this);
                        UpgradingStream.this.upgradedConnection.upgradeProcessed = true;
                  }
               }

            }

            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
               if (msg instanceof HttpResponseHead) {
                  pipeline.remove(this);
                  HttpResponseHead resp = (HttpResponseHead)msg;
                  if (resp.statusCode != HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
                     resp.headers.set((CharSequence)HttpHeaderNames.CONNECTION, (CharSequence)HttpHeaderValues.CLOSE);
                  }
               }

               super.channelRead(ctx, msg);
            }
         }

         pipeline.addAfter("codec", (String)null, new UpgradeRequestHandler());
         pipeline.addAfter("codec", (String)null, upgradeHandler);
         this.doWriteHead(request, chunked, buf, end, priority, connect, handler);
      }

      private void doWriteHead(HttpRequestHead head, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         EventExecutor exec = this.upgradingConnection.channelHandlerContext().executor();
         if (exec.inEventLoop()) {
            this.upgradingStream.writeHead(head, chunked, buf, end, priority, connect, handler);
            if (end) {
               ChannelPipeline pipeline = this.upgradingConnection.channelHandlerContext().pipeline();
               pipeline.fireUserEventTriggered(Http2UpgradeClientConnection.SEND_BUFFERED_MESSAGES);
            }
         } else {
            exec.execute(() -> this.doWriteHead(head, chunked, buf, end, priority, connect, handler));
         }

      }

      public int id() {
         return 1;
      }

      public Object metric() {
         return this.upgradingStream.metric();
      }

      public Object trace() {
         return this.upgradingStream.trace();
      }

      public HttpVersion version() {
         HttpClientStream s = this.upgradedStream;
         if (s == null) {
            s = this.upgradingStream;
         }

         return s.version();
      }

      public ContextInternal getContext() {
         return this.upgradingStream.getContext();
      }

      public void continueHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.continueHandler(handler);
         } else {
            this.upgradingStream.continueHandler(handler);
            this.continueHandler = handler;
         }

      }

      public void earlyHintsHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.earlyHintsHandler(handler);
         } else {
            this.upgradingStream.earlyHintsHandler(handler);
            this.earlyHintsHandler = handler;
         }

      }

      public void pushHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.pushHandler(handler);
         } else {
            this.upgradingStream.pushHandler(handler);
            this.pushHandler = handler;
         }

      }

      public void closeHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.closeHandler(handler);
         } else {
            this.upgradingStream.closeHandler(handler);
            this.closeHandler = handler;
         }

      }

      public UpgradingStream drainHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.drainHandler(handler);
         } else {
            this.upgradingStream.drainHandler(handler);
            this.drainHandler = handler;
         }

         return this;
      }

      public UpgradingStream exceptionHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.exceptionHandler(handler);
         } else {
            this.upgradingStream.exceptionHandler(handler);
            this.exceptionHandler = handler;
         }

         return this;
      }

      public void headHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.headHandler(handler);
         } else {
            this.upgradingStream.headHandler(handler);
            this.headHandler = handler;
         }

      }

      public void chunkHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.chunkHandler(handler);
         } else {
            this.upgradingStream.chunkHandler(handler);
            this.chunkHandler = handler;
         }

      }

      public void endHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.endHandler(handler);
         } else {
            this.upgradingStream.endHandler(handler);
            this.endHandler = handler;
         }

      }

      public void unknownFrameHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.unknownFrameHandler(handler);
         } else {
            this.upgradingStream.unknownFrameHandler(handler);
            this.unknownFrameHandler = handler;
         }

      }

      public void priorityHandler(Handler handler) {
         if (this.upgradedStream != null) {
            this.upgradedStream.priorityHandler(handler);
         } else {
            this.upgradingStream.priorityHandler(handler);
            this.priorityHandler = handler;
         }

      }

      public WriteStream setWriteQueueMaxSize(int maxSize) {
         if (this.upgradedStream != null) {
            this.upgradedStream.setWriteQueueMaxSize(maxSize);
         } else {
            this.upgradingStream.setWriteQueueMaxSize(maxSize);
         }

         return this;
      }

      public boolean writeQueueFull() {
         return this.upgradedStream != null ? this.upgradedStream.writeQueueFull() : this.upgradingStream.writeQueueFull();
      }

      public void writeBuffer(ByteBuf buf, boolean end, Handler handler) {
         EventExecutor exec = this.upgradingConnection.channelHandlerContext().executor();
         if (exec.inEventLoop()) {
            this.upgradingStream.writeBuffer(buf, end, handler);
            if (end) {
               ChannelPipeline pipeline = this.upgradingConnection.channelHandlerContext().pipeline();
               pipeline.fireUserEventTriggered(Http2UpgradeClientConnection.SEND_BUFFERED_MESSAGES);
            }
         } else {
            exec.execute(() -> this.writeBuffer(buf, end, handler));
         }

      }

      public void writeFrame(int type, int flags, ByteBuf payload) {
         if (this.upgradedStream != null) {
            this.upgradedStream.writeFrame(type, flags, payload);
         } else {
            this.upgradingStream.writeFrame(type, flags, payload);
         }

      }

      public void doSetWriteQueueMaxSize(int size) {
         if (this.upgradedStream != null) {
            this.upgradedStream.doSetWriteQueueMaxSize(size);
         } else {
            this.upgradingStream.doSetWriteQueueMaxSize(size);
         }

      }

      public boolean isNotWritable() {
         return this.upgradedStream != null ? this.upgradedStream.isNotWritable() : this.upgradingStream.isNotWritable();
      }

      public void doPause() {
         if (this.upgradedStream != null) {
            this.upgradedStream.doPause();
         } else {
            this.upgradingStream.doPause();
         }

      }

      public void doFetch(long amount) {
         if (this.upgradedStream != null) {
            this.upgradedStream.doFetch(amount);
         } else {
            this.upgradingStream.doFetch(amount);
         }

      }

      public void reset(Throwable cause) {
         if (this.upgradedStream != null) {
            this.upgradedStream.reset(cause);
         } else {
            this.upgradingStream.reset(cause);
         }

      }

      public StreamPriority priority() {
         return this.upgradedStream != null ? this.upgradedStream.priority() : this.upgradingStream.priority();
      }

      public void updatePriority(StreamPriority streamPriority) {
         if (this.upgradedStream != null) {
            this.upgradedStream.updatePriority(streamPriority);
         } else {
            this.upgradingStream.updatePriority(streamPriority);
         }

      }
   }
}
