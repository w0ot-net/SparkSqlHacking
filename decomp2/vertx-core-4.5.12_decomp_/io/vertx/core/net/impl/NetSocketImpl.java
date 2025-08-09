package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCounted;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.UUID;

public class NetSocketImpl extends ConnectionBase implements NetSocketInternal {
   private static final Logger log = LoggerFactory.getLogger(NetSocketImpl.class);
   private final String writeHandlerID;
   private final SslChannelProvider sslChannelProvider;
   private final SocketAddress remoteAddress;
   private final TCPMetrics metrics;
   private final InboundBuffer pending;
   private final String hostnameVerificationAlgorithm;
   private final String negotiatedApplicationLayerProtocol;
   private Handler endHandler;
   private Handler drainHandler;
   private MessageConsumer registration;
   private Handler handler;
   private Handler messageHandler;
   private Handler eventHandler;

   public NetSocketImpl(ContextInternal context, ChannelHandlerContext channel, SslChannelProvider sslChannelProvider, TCPMetrics metrics, boolean registerWriteHandler) {
      this(context, channel, (SocketAddress)null, sslChannelProvider, metrics, (String)null, (String)null, registerWriteHandler);
   }

   public NetSocketImpl(ContextInternal context, ChannelHandlerContext channel, SocketAddress remoteAddress, SslChannelProvider sslChannelProvider, TCPMetrics metrics, String hostnameVerificationAlgorithm, String negotiatedApplicationLayerProtocol, boolean registerWriteHandler) {
      super(context, channel);
      this.sslChannelProvider = sslChannelProvider;
      this.writeHandlerID = registerWriteHandler ? "__vertx.net." + UUID.randomUUID() : null;
      this.remoteAddress = remoteAddress;
      this.metrics = metrics;
      this.messageHandler = new DataMessageHandler();
      this.hostnameVerificationAlgorithm = hostnameVerificationAlgorithm;
      this.negotiatedApplicationLayerProtocol = negotiatedApplicationLayerProtocol;
      this.pending = new InboundBuffer(context);
      this.pending.drainHandler((v) -> this.doResume());
      this.pending.exceptionHandler(context::reportException);
      this.pending.handler((msg) -> {
         if (msg == InboundBuffer.END_SENTINEL) {
            Handler<Void> handler = this.endHandler();
            if (handler != null) {
               handler.handle((Object)null);
            }
         } else {
            Handler<Buffer> handler = this.handler();
            if (handler != null) {
               handler.handle((Buffer)msg);
            }
         }

      });
   }

   void registerEventBusHandler() {
      if (this.writeHandlerID != null) {
         Handler<Message<Buffer>> writeHandler = (msg) -> this.write((Buffer)msg.body());
         this.registration = this.vertx.eventBus().localConsumer(this.writeHandlerID).handler(writeHandler);
      }

   }

   void unregisterEventBusHandler() {
      if (this.registration != null) {
         MessageConsumer consumer = this.registration;
         this.registration = null;
         consumer.unregister();
      }

   }

   public TCPMetrics metrics() {
      return this.metrics;
   }

   public String writeHandlerID() {
      return this.writeHandlerID;
   }

   public synchronized Future writeMessage(Object message) {
      Promise<Void> promise = this.context.promise();
      this.writeMessage(message, promise);
      return promise.future();
   }

   public NetSocketInternal writeMessage(Object message, Handler handler) {
      this.writeToChannel(message, handler == null ? null : this.context.promise(handler));
      return this;
   }

   public String applicationLayerProtocol() {
      return this.negotiatedApplicationLayerProtocol;
   }

   public Future write(Buffer data) {
      return this.writeMessage(data.getByteBuf());
   }

   public void write(String str, Handler handler) {
      this.write(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8), handler);
   }

   public Future write(String str) {
      return this.writeMessage(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
   }

   public Future write(String str, String enc) {
      return this.writeMessage(Unpooled.copiedBuffer(str, Charset.forName(enc)));
   }

   public void write(String str, String enc, Handler handler) {
      Charset cs = enc != null ? Charset.forName(enc) : CharsetUtil.UTF_8;
      this.write(Unpooled.copiedBuffer(str, cs), handler);
   }

   public void write(Buffer message, Handler handler) {
      this.write(message.getByteBuf(), handler);
   }

   private void write(ByteBuf buff, Handler handler) {
      this.reportBytesWritten((long)buff.readableBytes());
      this.writeMessage(buff, handler);
   }

   private synchronized Handler handler() {
      return this.handler;
   }

   public synchronized NetSocket handler(Handler dataHandler) {
      this.handler = dataHandler;
      return this;
   }

   private synchronized Handler messageHandler() {
      return this.messageHandler;
   }

   public synchronized NetSocketInternal messageHandler(Handler handler) {
      this.messageHandler = (Handler)(handler == null ? new DataMessageHandler() : handler);
      return this;
   }

   public synchronized NetSocketInternal eventHandler(Handler handler) {
      this.eventHandler = handler;
      return this;
   }

   public synchronized NetSocket pause() {
      this.pending.pause();
      return this;
   }

   public NetSocket fetch(long amount) {
      this.pending.fetch(amount);
      return this;
   }

   public synchronized NetSocket resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public NetSocket setWriteQueueMaxSize(int maxSize) {
      this.doSetWriteQueueMaxSize(maxSize);
      return this;
   }

   public boolean writeQueueFull() {
      return this.isNotWritable();
   }

   private synchronized Handler endHandler() {
      return this.endHandler;
   }

   public synchronized NetSocket endHandler(Handler endHandler) {
      this.endHandler = endHandler;
      return this;
   }

   public synchronized NetSocket drainHandler(Handler drainHandler) {
      this.drainHandler = drainHandler;
      this.vertx.runOnContext((v) -> this.callDrainHandler());
      return this;
   }

   public Future sendFile(String filename, long offset, long length) {
      Promise<Void> promise = this.context.promise();
      this.sendFile(filename, offset, length, promise);
      return promise.future();
   }

   public NetSocket sendFile(String filename, long offset, long length, Handler resultHandler) {
      File f = this.vertx.resolveFile(filename);
      if (f.isDirectory()) {
         throw new IllegalArgumentException("filename must point to a file and not to a directory");
      } else {
         RandomAccessFile raf = null;

         try {
            raf = new RandomAccessFile(f, "r");
            ChannelFuture future = super.sendFile(raf, Math.min(offset, f.length()), Math.min(length, f.length() - offset));
            if (resultHandler != null) {
               future.addListener((fut) -> {
                  AsyncResult<Void> res;
                  if (future.isSuccess()) {
                     res = Future.succeededFuture();
                  } else {
                     res = Future.failedFuture(future.cause());
                  }

                  this.vertx.runOnContext((v) -> resultHandler.handle(res));
               });
            }
         } catch (IOException e) {
            try {
               if (raf != null) {
                  raf.close();
               }
            } catch (IOException var11) {
            }

            if (resultHandler != null) {
               this.vertx.runOnContext((v) -> resultHandler.handle(Future.failedFuture((Throwable)e)));
            } else {
               log.error("Failed to send file", e);
            }
         }

         return this;
      }
   }

   public NetSocketImpl exceptionHandler(Handler handler) {
      return (NetSocketImpl)super.exceptionHandler(handler);
   }

   public NetSocketImpl closeHandler(Handler handler) {
      return (NetSocketImpl)super.closeHandler(handler);
   }

   public Future upgradeToSsl() {
      return this.upgradeToSsl((String)null);
   }

   public Future upgradeToSsl(String serverName) {
      PromiseInternal<Void> promise = this.context.promise();
      if (this.chctx.pipeline().get("ssl") == null) {
         if (this.remoteAddress != null && this.hostnameVerificationAlgorithm == null) {
            promise.fail("Missing hostname verification algorithm: you must set TCP client options host name verification algorithm");
            return promise.future();
         }

         ChannelPromise flush = this.chctx.newPromise();
         this.flush(flush);
         flush.addListener((fut) -> {
            if (fut.isSuccess()) {
               ChannelPromise channelPromise = this.chctx.newPromise();
               this.chctx.pipeline().addFirst("handshaker", new SslHandshakeCompletionHandler(channelPromise));
               channelPromise.addListener(promise);
               ChannelHandler sslHandler;
               if (this.remoteAddress != null) {
                  sslHandler = this.sslChannelProvider.createClientSslHandler(this.remoteAddress, serverName, false);
               } else {
                  sslHandler = this.sslChannelProvider.createServerHandler(HttpUtils.socketAddressToHostAndPort(this.chctx.channel().remoteAddress()));
               }

               this.chctx.pipeline().addFirst("ssl", sslHandler);
            } else {
               promise.fail(fut.cause());
            }

         });
      }

      return promise.future();
   }

   public NetSocket upgradeToSsl(Handler handler) {
      return this.upgradeToSsl((String)null, handler);
   }

   public NetSocket upgradeToSsl(String serverName, Handler handler) {
      Future<Void> fut = this.upgradeToSsl(serverName);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   protected void handleInterestedOpsChanged() {
      this.context.emit((Object)null, (v) -> this.callDrainHandler());
   }

   public void end(Handler handler) {
      this.close(handler);
   }

   public Future end() {
      return this.close();
   }

   protected void handleClosed() {
      InboundBuffer var10002 = this.pending;
      this.context.emit(InboundBuffer.END_SENTINEL, var10002::write);
      super.handleClosed();
   }

   public void handleMessage(Object msg) {
      this.context.emit(msg, this.messageHandler());
   }

   protected void handleEvent(Object evt) {
      Handler<Object> handler;
      synchronized(this) {
         handler = this.eventHandler;
      }

      if (handler != null) {
         this.context.emit(evt, handler);
      } else {
         super.handleEvent(evt);
      }

   }

   private synchronized void callDrainHandler() {
      if (this.drainHandler != null && !this.writeQueueFull()) {
         this.drainHandler.handle((Object)null);
      }

   }

   private class DataMessageHandler implements Handler {
      private DataMessageHandler() {
      }

      public void handle(Object msg) {
         if (msg instanceof ByteBuf) {
            Object var4 = VertxHandler.safeBuffer((ByteBuf)msg);
            ByteBuf byteBuf = var4;
            Buffer buffer = Buffer.buffer(byteBuf);
            if (!NetSocketImpl.this.pending.write((Object)buffer)) {
               NetSocketImpl.this.doPause();
            }
         } else {
            this.handleInvalid(msg);
         }

      }

      private void handleInvalid(Object msg) {
         if (msg instanceof ReferenceCounted && !(msg instanceof ByteBuf)) {
            ReferenceCounted refCounter = (ReferenceCounted)msg;
            refCounter.release();
         }

      }
   }
}
