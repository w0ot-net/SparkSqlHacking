package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.FutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.NetworkMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

public abstract class ConnectionBase {
   private static final long METRICS_REPORTED_BYTES_LOW_MASK = 4095L;
   private static final long METRICS_REPORTED_BYTES_HIGH_MASK = -4096L;
   public static final VertxException CLOSED_EXCEPTION = new VertxException("Connection was closed", true);
   public static final AttributeKey REMOTE_ADDRESS_OVERRIDE = AttributeKey.valueOf("RemoteAddressOverride");
   public static final AttributeKey LOCAL_ADDRESS_OVERRIDE = AttributeKey.valueOf("LocalAddressOverride");
   private static final Logger log = LoggerFactory.getLogger(ConnectionBase.class);
   private static final int MAX_REGION_SIZE = 1048576;
   public final VoidChannelPromise voidPromise;
   protected final VertxInternal vertx;
   protected final ChannelHandlerContext chctx;
   protected final ContextInternal context;
   private Handler exceptionHandler;
   private Handler closeHandler;
   private int writeInProgress;
   private Object metric;
   private SocketAddress remoteAddress;
   private SocketAddress realRemoteAddress;
   private SocketAddress localAddress;
   private SocketAddress realLocalAddress;
   private ChannelPromise closePromise;
   private Future closeFuture;
   private long remainingBytesRead;
   private long remainingBytesWritten;
   private boolean read;
   private boolean needsFlush;
   private boolean closed;

   protected ConnectionBase(ContextInternal context, ChannelHandlerContext chctx) {
      this.vertx = context.owner();
      this.chctx = chctx;
      this.context = context;
      this.voidPromise = new VoidChannelPromise(chctx.channel(), false);
      this.closePromise = chctx.newPromise();
      PromiseInternal<Void> p = context.promise();
      this.closePromise.addListener(p);
      this.closeFuture = p.future();
      this.closeFuture.onComplete(this::checkCloseHandler);
   }

   public Future closeFuture() {
      return this.closeFuture;
   }

   public void fail(Throwable error) {
      this.chctx.pipeline().fireExceptionCaught(error);
   }

   void close(ChannelPromise promise) {
      this.closePromise.addListener((l) -> {
         if (l.isSuccess()) {
            promise.setSuccess();
         } else {
            promise.setFailure(l.cause());
         }

      });
      this.close();
   }

   final void endReadAndFlush() {
      if (this.read) {
         this.read = false;
         if (this.needsFlush) {
            this.needsFlush = false;
            this.chctx.flush();
         }
      }

   }

   final void read(Object msg) {
      this.read = true;
      if (Metrics.METRICS_ENABLED) {
         this.reportBytesRead(msg);
      }

      this.handleMessage(msg);
   }

   private void write(Object msg, Boolean flush, ChannelPromise promise) {
      if (Metrics.METRICS_ENABLED) {
         this.reportsBytesWritten(msg);
      }

      boolean writeAndFlush;
      if (flush == null) {
         writeAndFlush = !this.read;
      } else {
         writeAndFlush = flush;
      }

      this.needsFlush = !writeAndFlush;
      if (writeAndFlush) {
         this.chctx.writeAndFlush(msg, promise);
      } else {
         this.chctx.write(msg, promise);
      }

   }

   private void writeClose(PromiseInternal promise) {
      if (this.closed) {
         promise.complete();
      } else {
         this.closed = true;
         ChannelPromise channelPromise = this.chctx.newPromise().addListener((ChannelFutureListener)(f) -> this.chctx.close().addListener(promise));
         this.writeToChannel(Unpooled.EMPTY_BUFFER, true, channelPromise);
      }
   }

   private ChannelPromise wrap(FutureListener handler) {
      ChannelPromise promise = this.chctx.newPromise();
      promise.addListener(handler);
      return promise;
   }

   public final void writeToChannel(Object msg, FutureListener listener) {
      this.writeToChannel(msg, (ChannelPromise)(listener == null ? this.voidPromise : this.wrap(listener)));
   }

   public final void writeToChannel(Object msg, ChannelPromise promise) {
      this.writeToChannel(msg, false, promise);
   }

   public final void writeToChannel(Object msg, boolean forceFlush, ChannelPromise promise) {
      synchronized(this) {
         if (!this.chctx.executor().inEventLoop() || this.writeInProgress > 0) {
            this.queueForWrite(msg, forceFlush, promise);
            return;
         }
      }

      this.write(msg, forceFlush ? true : null, promise);
   }

   private void queueForWrite(Object msg, boolean forceFlush, ChannelPromise promise) {
      ++this.writeInProgress;
      this.chctx.executor().execute(() -> {
         boolean flush;
         if (forceFlush) {
            flush = true;
         } else {
            synchronized(this) {
               flush = --this.writeInProgress == 0;
            }
         }

         this.write(msg, flush, promise);
      });
   }

   public void writeToChannel(Object obj) {
      this.writeToChannel(obj, (ChannelPromise)this.voidPromise);
   }

   public final void flush() {
      this.flush(this.voidPromise);
   }

   public final void flush(ChannelPromise promise) {
      this.writeToChannel(Unpooled.EMPTY_BUFFER, true, promise);
   }

   public boolean isNotWritable() {
      return !this.chctx.channel().isWritable();
   }

   public Future close() {
      PromiseInternal<Void> promise = this.context.promise();
      EventExecutor exec = this.chctx.executor();
      if (exec.inEventLoop()) {
         this.writeClose(promise);
      } else {
         exec.execute(() -> this.writeClose(promise));
      }

      return promise.future();
   }

   public final void close(Handler handler) {
      this.close().onComplete(handler);
   }

   public synchronized ConnectionBase closeHandler(Handler handler) {
      this.closeHandler = handler;
      return this;
   }

   public synchronized ConnectionBase exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   protected synchronized Handler exceptionHandler() {
      return this.exceptionHandler;
   }

   public void doPause() {
      this.chctx.channel().config().setAutoRead(false);
   }

   public void doResume() {
      this.chctx.channel().config().setAutoRead(true);
   }

   public void doSetWriteQueueMaxSize(int size) {
      ChannelConfig config = this.chctx.channel().config();
      config.setWriteBufferWaterMark(new WriteBufferWaterMark(size / 2, size));
   }

   public final Channel channel() {
      return this.chctx.channel();
   }

   public final ChannelHandlerContext channelHandlerContext() {
      return this.chctx;
   }

   public final ContextInternal getContext() {
      return this.context;
   }

   public final synchronized void metric(Object metric) {
      this.metric = metric;
   }

   public final synchronized Object metric() {
      return this.metric;
   }

   public abstract NetworkMetrics metrics();

   protected void handleException(Throwable t) {
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         metrics.exceptionOccurred(this.metric, this.remoteAddress(), t);
      }

      this.context.emit(t, (err) -> {
         Handler<Throwable> handler;
         synchronized(this) {
            handler = this.exceptionHandler;
         }

         if (handler != null) {
            handler.handle(err);
         } else if (log.isDebugEnabled()) {
            log.error(t.getMessage(), t);
         } else {
            log.error(t.getMessage());
         }

      });
   }

   protected void handleClosed() {
      this.closed = true;
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         this.flushBytesRead();
         this.flushBytesWritten();
         if (metrics instanceof TCPMetrics) {
            ((TCPMetrics)metrics).disconnected(this.metric(), this.remoteAddress());
         }
      }

      this.closePromise.setSuccess();
   }

   private void checkCloseHandler(AsyncResult ar) {
      Handler<Void> handler;
      synchronized(this) {
         handler = this.closeHandler;
      }

      if (handler != null) {
         handler.handle((Object)null);
      }

   }

   protected void handleEvent(Object evt) {
      ReferenceCountUtil.release(evt);
   }

   protected void handleIdle(IdleStateEvent event) {
      log.debug("The connection will be closed due to timeout");
      this.chctx.close();
   }

   protected abstract void handleInterestedOpsChanged();

   protected boolean supportsFileRegion() {
      return this.vertx.transport().supportFileRegion() && !this.isSsl() && !this.isTrafficShaped();
   }

   public final void reportBytesRead(Object msg) {
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         this.doReportBytesRead(msg, metrics);
      }

   }

   private void doReportBytesRead(Object msg, NetworkMetrics metrics) {
      long bytes = this.remainingBytesRead;
      long numberOfBytes = this.sizeof(msg);
      bytes += numberOfBytes;
      long val = bytes & -4096L;
      if (val > 0L) {
         bytes &= 4095L;
         metrics.bytesRead(this.metric(), this.remoteAddress(), val);
      }

      this.remainingBytesRead = bytes;
   }

   protected long sizeof(Object msg) {
      return msg instanceof ByteBuf ? (long)((ByteBuf)msg).readableBytes() : 0L;
   }

   public final void reportBytesRead(long numberOfBytes) {
      if (numberOfBytes < 0L) {
         throw new IllegalArgumentException();
      } else {
         NetworkMetrics metrics = this.metrics();
         if (metrics != null) {
            long bytes = this.remainingBytesRead;
            bytes += numberOfBytes;
            long val = bytes & -4096L;
            if (val > 0L) {
               bytes &= 4095L;
               metrics.bytesRead(this.metric(), this.remoteAddress(), val);
            }

            this.remainingBytesRead = bytes;
         }

      }
   }

   public final void reportsBytesWritten(Object msg) {
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         long numberOfBytes = this.sizeof(msg);
         long bytes = this.remainingBytesWritten;
         bytes += numberOfBytes;
         long val = bytes & -4096L;
         if (val > 0L) {
            bytes &= 4095L;
            metrics.bytesWritten(this.metric, this.remoteAddress(), val);
         }

         this.remainingBytesWritten = bytes;
      }

   }

   public final void reportBytesWritten(long numberOfBytes) {
      if (numberOfBytes < 0L) {
         throw new IllegalArgumentException();
      } else {
         NetworkMetrics metrics = this.metrics();
         if (metrics != null) {
            long bytes = this.remainingBytesWritten;
            bytes += numberOfBytes;
            long val = bytes & -4096L;
            if (val > 0L) {
               bytes &= 4095L;
               metrics.bytesWritten(this.metric, this.remoteAddress(), val);
            }

            this.remainingBytesWritten = bytes;
         }

      }
   }

   public void flushBytesRead() {
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         long val = this.remainingBytesRead;
         if (val > 0L) {
            this.remainingBytesRead = 0L;
            metrics.bytesRead(this.metric(), this.remoteAddress(), val);
         }
      }

   }

   public void flushBytesWritten() {
      NetworkMetrics metrics = this.metrics();
      if (metrics != null) {
         long val = this.remainingBytesWritten;
         if (val > 0L) {
            this.remainingBytesWritten = 0L;
            metrics.bytesWritten(this.metric(), this.remoteAddress(), val);
         }
      }

   }

   private void sendFileRegion(RandomAccessFile file, long offset, long length, ChannelPromise writeFuture) {
      if (length < 1048576L) {
         this.writeToChannel(new DefaultFileRegion(file.getChannel(), offset, length), (ChannelPromise)writeFuture);
      } else {
         ChannelPromise promise = this.chctx.newPromise();
         FileRegion region = new DefaultFileRegion(file.getChannel(), offset, 1048576L);
         region.retain();
         this.writeToChannel(region, (ChannelPromise)promise);
         promise.addListener((future) -> {
            if (future.isSuccess()) {
               this.sendFileRegion(file, offset + 1048576L, length - 1048576L, writeFuture);
            } else {
               log.error(future.cause().getMessage(), future.cause());
               writeFuture.setFailure(future.cause());
            }

         });
      }

   }

   public final ChannelFuture sendFile(RandomAccessFile raf, long offset, long length) throws IOException {
      ChannelPromise writeFuture = this.chctx.newPromise();
      if (!this.supportsFileRegion()) {
         this.writeToChannel(new ChunkedNioFile(raf.getChannel(), offset, length, 8192), (ChannelPromise)writeFuture);
      } else {
         this.sendFileRegion(raf, offset, length, writeFuture);
      }

      if (writeFuture != null) {
         writeFuture.addListener((fut) -> raf.close());
      } else {
         raf.close();
      }

      return writeFuture;
   }

   public boolean isSsl() {
      return this.chctx.pipeline().get(SslHandler.class) != null;
   }

   public boolean isTrafficShaped() {
      return this.chctx.pipeline().get(AbstractTrafficShapingHandler.class) != null;
   }

   public SSLSession sslSession() {
      ChannelHandlerContext sslHandlerContext = this.chctx.pipeline().context(SslHandler.class);
      if (sslHandlerContext != null) {
         SslHandler sslHandler = (SslHandler)sslHandlerContext.handler();
         return sslHandler.engine().getSession();
      } else {
         return null;
      }
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      SSLSession session = this.sslSession();
      return session != null ? session.getPeerCertificateChain() : null;
   }

   public List peerCertificates() throws SSLPeerUnverifiedException {
      SSLSession session = this.sslSession();
      return session != null ? Arrays.asList(session.getPeerCertificates()) : null;
   }

   public String indicatedServerName() {
      return this.chctx.channel().hasAttr(SslHandshakeCompletionHandler.SERVER_NAME_ATTR) ? (String)this.chctx.channel().attr(SslHandshakeCompletionHandler.SERVER_NAME_ATTR).get() : null;
   }

   public ChannelPromise channelFuture() {
      return this.chctx.newPromise();
   }

   public String remoteName() {
      java.net.SocketAddress addr = this.chctx.channel().remoteAddress();
      return addr instanceof InetSocketAddress ? ((InetSocketAddress)addr).getHostString() : null;
   }

   private SocketAddress channelRemoteAddress() {
      java.net.SocketAddress addr = this.chctx.channel().remoteAddress();
      return addr != null ? this.vertx.transport().convert(addr) : null;
   }

   private SocketAddress socketAdressOverride(AttributeKey key) {
      Channel ch = this.chctx.channel();
      return ch.hasAttr(key) ? (SocketAddress)ch.attr(key).getAndSet((Object)null) : null;
   }

   public SocketAddress remoteAddress() {
      SocketAddress address = this.remoteAddress;
      if (address == null) {
         address = this.socketAdressOverride(REMOTE_ADDRESS_OVERRIDE);
         if (address == null) {
            address = this.channelRemoteAddress();
            if (address != null && address.isDomainSocket() && address.path().isEmpty()) {
               address = this.channelLocalAddress();
            }
         }

         if (address != null) {
            this.remoteAddress = address;
         }
      }

      return address;
   }

   public SocketAddress remoteAddress(boolean real) {
      if (real) {
         SocketAddress address = this.realRemoteAddress;
         if (address == null) {
            address = this.channelRemoteAddress();
         }

         if (address != null) {
            this.realRemoteAddress = address;
         }

         return address;
      } else {
         return this.remoteAddress();
      }
   }

   private SocketAddress channelLocalAddress() {
      java.net.SocketAddress addr = this.chctx.channel().localAddress();
      return addr != null ? this.vertx.transport().convert(addr) : null;
   }

   public SocketAddress localAddress() {
      SocketAddress address = this.localAddress;
      if (address == null) {
         address = this.socketAdressOverride(LOCAL_ADDRESS_OVERRIDE);
         if (address == null) {
            address = this.channelLocalAddress();
            if (address != null && address.isDomainSocket() && address.path().isEmpty()) {
               address = this.channelRemoteAddress();
            }
         }

         if (address != null) {
            this.localAddress = address;
         }
      }

      return address;
   }

   public SocketAddress localAddress(boolean real) {
      if (real) {
         SocketAddress address = this.realLocalAddress;
         if (address == null) {
            address = this.channelLocalAddress();
         }

         if (address != null) {
            this.realLocalAddress = address;
         }

         return address;
      } else {
         return this.localAddress();
      }
   }

   protected void handleMessage(Object msg) {
   }
}
