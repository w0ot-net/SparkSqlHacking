package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.channel.unix.UnixChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.ImmediateExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;

public class SslHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(SslHandler.class);
   private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
   private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);
   private static final int STATE_SENT_FIRST_MESSAGE = 1;
   private static final int STATE_FLUSHED_BEFORE_HANDSHAKE = 2;
   private static final int STATE_READ_DURING_HANDSHAKE = 4;
   private static final int STATE_HANDSHAKE_STARTED = 8;
   private static final int STATE_NEEDS_FLUSH = 16;
   private static final int STATE_OUTBOUND_CLOSED = 32;
   private static final int STATE_CLOSE_NOTIFY = 64;
   private static final int STATE_PROCESS_TASK = 128;
   private static final int STATE_FIRE_CHANNEL_READ = 256;
   private static final int STATE_UNWRAP_REENTRY = 512;
   private static final int MAX_PLAINTEXT_LENGTH = 16384;
   private volatile ChannelHandlerContext ctx;
   private final SSLEngine engine;
   private final SslEngineType engineType;
   private final Executor delegatedTaskExecutor;
   private final boolean jdkCompatibilityMode;
   private final ByteBuffer[] singleBuffer;
   private final boolean startTls;
   private final ResumptionController resumptionController;
   private final SslTasksRunner sslTaskRunnerForUnwrap;
   private final SslTasksRunner sslTaskRunner;
   private SslHandlerCoalescingBufferQueue pendingUnencryptedWrites;
   private Promise handshakePromise;
   private final LazyChannelPromise sslClosePromise;
   private int packetLength;
   private short state;
   private volatile long handshakeTimeoutMillis;
   private volatile long closeNotifyFlushTimeoutMillis;
   private volatile long closeNotifyReadTimeoutMillis;
   volatile int wrapDataSize;

   public SslHandler(SSLEngine engine) {
      this(engine, false);
   }

   public SslHandler(SSLEngine engine, boolean startTls) {
      this(engine, startTls, ImmediateExecutor.INSTANCE);
   }

   public SslHandler(SSLEngine engine, Executor delegatedTaskExecutor) {
      this(engine, false, delegatedTaskExecutor);
   }

   public SslHandler(SSLEngine engine, boolean startTls, Executor delegatedTaskExecutor) {
      this(engine, startTls, delegatedTaskExecutor, (ResumptionController)null);
   }

   SslHandler(SSLEngine engine, boolean startTls, Executor delegatedTaskExecutor, ResumptionController resumptionController) {
      this.singleBuffer = new ByteBuffer[1];
      this.sslTaskRunnerForUnwrap = new SslTasksRunner(true);
      this.sslTaskRunner = new SslTasksRunner(false);
      this.handshakePromise = new LazyChannelPromise();
      this.sslClosePromise = new LazyChannelPromise();
      this.handshakeTimeoutMillis = 10000L;
      this.closeNotifyFlushTimeoutMillis = 3000L;
      this.wrapDataSize = 16384;
      this.engine = (SSLEngine)ObjectUtil.checkNotNull(engine, "engine");
      this.delegatedTaskExecutor = (Executor)ObjectUtil.checkNotNull(delegatedTaskExecutor, "delegatedTaskExecutor");
      this.engineType = SslHandler.SslEngineType.forEngine(engine);
      this.startTls = startTls;
      this.jdkCompatibilityMode = this.engineType.jdkCompatibilityMode(engine);
      this.setCumulator(this.engineType.cumulator);
      this.resumptionController = resumptionController;
   }

   public long getHandshakeTimeoutMillis() {
      return this.handshakeTimeoutMillis;
   }

   public void setHandshakeTimeout(long handshakeTimeout, TimeUnit unit) {
      ObjectUtil.checkNotNull(unit, "unit");
      this.setHandshakeTimeoutMillis(unit.toMillis(handshakeTimeout));
   }

   public void setHandshakeTimeoutMillis(long handshakeTimeoutMillis) {
      this.handshakeTimeoutMillis = ObjectUtil.checkPositiveOrZero(handshakeTimeoutMillis, "handshakeTimeoutMillis");
   }

   public final void setWrapDataSize(int wrapDataSize) {
      this.wrapDataSize = wrapDataSize;
   }

   /** @deprecated */
   @Deprecated
   public long getCloseNotifyTimeoutMillis() {
      return this.getCloseNotifyFlushTimeoutMillis();
   }

   /** @deprecated */
   @Deprecated
   public void setCloseNotifyTimeout(long closeNotifyTimeout, TimeUnit unit) {
      this.setCloseNotifyFlushTimeout(closeNotifyTimeout, unit);
   }

   /** @deprecated */
   @Deprecated
   public void setCloseNotifyTimeoutMillis(long closeNotifyFlushTimeoutMillis) {
      this.setCloseNotifyFlushTimeoutMillis(closeNotifyFlushTimeoutMillis);
   }

   public final long getCloseNotifyFlushTimeoutMillis() {
      return this.closeNotifyFlushTimeoutMillis;
   }

   public final void setCloseNotifyFlushTimeout(long closeNotifyFlushTimeout, TimeUnit unit) {
      this.setCloseNotifyFlushTimeoutMillis(unit.toMillis(closeNotifyFlushTimeout));
   }

   public final void setCloseNotifyFlushTimeoutMillis(long closeNotifyFlushTimeoutMillis) {
      this.closeNotifyFlushTimeoutMillis = ObjectUtil.checkPositiveOrZero(closeNotifyFlushTimeoutMillis, "closeNotifyFlushTimeoutMillis");
   }

   public final long getCloseNotifyReadTimeoutMillis() {
      return this.closeNotifyReadTimeoutMillis;
   }

   public final void setCloseNotifyReadTimeout(long closeNotifyReadTimeout, TimeUnit unit) {
      this.setCloseNotifyReadTimeoutMillis(unit.toMillis(closeNotifyReadTimeout));
   }

   public final void setCloseNotifyReadTimeoutMillis(long closeNotifyReadTimeoutMillis) {
      this.closeNotifyReadTimeoutMillis = ObjectUtil.checkPositiveOrZero(closeNotifyReadTimeoutMillis, "closeNotifyReadTimeoutMillis");
   }

   public SSLEngine engine() {
      return this.engine;
   }

   public String applicationProtocol() {
      SSLEngine engine = this.engine();
      return !(engine instanceof ApplicationProtocolAccessor) ? null : ((ApplicationProtocolAccessor)engine).getNegotiatedApplicationProtocol();
   }

   public Future handshakeFuture() {
      return this.handshakePromise;
   }

   /** @deprecated */
   @Deprecated
   public ChannelFuture close() {
      return this.closeOutbound();
   }

   /** @deprecated */
   @Deprecated
   public ChannelFuture close(ChannelPromise promise) {
      return this.closeOutbound(promise);
   }

   public ChannelFuture closeOutbound() {
      return this.closeOutbound(this.ctx.newPromise());
   }

   public ChannelFuture closeOutbound(final ChannelPromise promise) {
      ChannelHandlerContext ctx = this.ctx;
      if (ctx.executor().inEventLoop()) {
         this.closeOutbound0(promise);
      } else {
         ctx.executor().execute(new Runnable() {
            public void run() {
               SslHandler.this.closeOutbound0(promise);
            }
         });
      }

      return promise;
   }

   private void closeOutbound0(ChannelPromise promise) {
      this.setState(32);
      this.engine.closeOutbound();

      try {
         this.flush(this.ctx, promise);
      } catch (Exception e) {
         if (!promise.tryFailure(e)) {
            logger.warn("{} flush() raised a masked exception.", this.ctx.channel(), e);
         }
      }

   }

   public Future sslCloseFuture() {
      return this.sslClosePromise;
   }

   public void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
      try {
         if (this.pendingUnencryptedWrites != null && !this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.releaseAndFailAll(ctx, new ChannelException("Pending write on removal of SslHandler"));
         }

         this.pendingUnencryptedWrites = null;
         SSLException cause = null;
         if (!this.handshakePromise.isDone()) {
            cause = new SSLHandshakeException("SslHandler removed before handshake completed");
            if (this.handshakePromise.tryFailure(cause)) {
               ctx.fireUserEventTriggered(new SslHandshakeCompletionEvent(cause));
            }
         }

         if (!this.sslClosePromise.isDone()) {
            if (cause == null) {
               cause = new SSLException("SslHandler removed before SSLEngine was closed");
            }

            this.notifyClosePromise(cause);
         }
      } finally {
         ReferenceCountUtil.release(this.engine);
      }

   }

   public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
      ctx.bind(localAddress, promise);
   }

   public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
      ctx.connect(remoteAddress, localAddress, promise);
   }

   public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      ctx.deregister(promise);
   }

   public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      this.closeOutboundAndChannel(ctx, promise, true);
   }

   public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      this.closeOutboundAndChannel(ctx, promise, false);
   }

   public void read(ChannelHandlerContext ctx) throws Exception {
      if (!this.handshakePromise.isDone()) {
         this.setState(4);
      }

      ctx.read();
   }

   private static IllegalStateException newPendingWritesNullException() {
      return new IllegalStateException("pendingUnencryptedWrites is null, handlerRemoved0 called?");
   }

   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
      if (!(msg instanceof ByteBuf)) {
         UnsupportedMessageTypeException exception = new UnsupportedMessageTypeException(msg, new Class[]{ByteBuf.class});
         ReferenceCountUtil.safeRelease(msg);
         promise.setFailure(exception);
      } else if (this.pendingUnencryptedWrites == null) {
         ReferenceCountUtil.safeRelease(msg);
         promise.setFailure(newPendingWritesNullException());
      } else {
         this.pendingUnencryptedWrites.add((ByteBuf)msg, promise);
      }

   }

   public void flush(ChannelHandlerContext ctx) throws Exception {
      if (this.startTls && !this.isStateSet(1)) {
         this.setState(1);
         this.pendingUnencryptedWrites.writeAndRemoveAll(ctx);
         this.forceFlush(ctx);
         this.startHandshakeProcessing(true);
      } else if (!this.isStateSet(128)) {
         try {
            this.wrapAndFlush(ctx);
         } catch (Throwable cause) {
            this.setHandshakeFailure(ctx, cause);
            PlatformDependent.throwException(cause);
         }

      }
   }

   private void wrapAndFlush(ChannelHandlerContext ctx) throws SSLException {
      if (this.pendingUnencryptedWrites.isEmpty()) {
         this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, ctx.newPromise());
      }

      if (!this.handshakePromise.isDone()) {
         this.setState(2);
      }

      try {
         this.wrap(ctx, false);
      } finally {
         this.forceFlush(ctx);
      }

   }

   private void wrap(ChannelHandlerContext ctx, boolean inUnwrap) throws SSLException {
      ByteBuf out = null;
      ByteBufAllocator alloc = ctx.alloc();

      try {
         int wrapDataSize = this.wrapDataSize;

         while(true) {
            if (ctx.isRemoved()) {
               return;
            }

            ChannelPromise promise = ctx.newPromise();
            ByteBuf buf = wrapDataSize > 0 ? this.pendingUnencryptedWrites.remove(alloc, wrapDataSize, promise) : this.pendingUnencryptedWrites.removeFirst(promise);
            if (buf == null) {
               return;
            }

            SSLEngineResult result;
            try {
               if (buf.readableBytes() > 16384) {
                  int readableBytes = buf.readableBytes();
                  int numPackets = readableBytes / 16384;
                  if (readableBytes % 16384 != 0) {
                     ++numPackets;
                  }

                  if (out == null) {
                     out = this.allocateOutNetBuf(ctx, readableBytes, buf.nioBufferCount() + numPackets);
                  }

                  result = this.wrapMultiple(alloc, this.engine, buf, out);
               } else {
                  if (out == null) {
                     out = this.allocateOutNetBuf(ctx, buf.readableBytes(), buf.nioBufferCount());
                  }

                  result = this.wrap(alloc, this.engine, buf, out);
               }
            } catch (SSLException e) {
               buf.release();
               promise.setFailure(e);
               throw e;
            }

            if (buf.isReadable()) {
               this.pendingUnencryptedWrites.addFirst(buf, promise);
               promise = null;
            } else {
               buf.release();
            }

            if (out.isReadable()) {
               ByteBuf b = out;
               out = null;
               if (promise != null) {
                  ctx.write(b, promise);
               } else {
                  ctx.write(b);
               }
            } else if (promise != null) {
               ctx.write(Unpooled.EMPTY_BUFFER, promise);
            }

            if (result.getStatus() == Status.CLOSED) {
               if (!this.pendingUnencryptedWrites.isEmpty()) {
                  Throwable exception = this.handshakePromise.cause();
                  if (exception == null) {
                     exception = this.sslClosePromise.cause();
                     if (exception == null) {
                        exception = new SslClosedEngineException("SSLEngine closed already");
                     }
                  }

                  this.pendingUnencryptedWrites.releaseAndFailAll(ctx, exception);
               }
               break;
            }

            switch (result.getHandshakeStatus()) {
               case NEED_TASK:
                  if (!this.runDelegatedTasks(inUnwrap)) {
                     return;
                  }
                  break;
               case FINISHED:
               case NOT_HANDSHAKING:
                  this.setHandshakeSuccess();
                  break;
               case NEED_WRAP:
                  if (result.bytesProduced() > 0 && this.pendingUnencryptedWrites.isEmpty()) {
                     this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER);
                  }
                  break;
               case NEED_UNWRAP:
                  this.readIfNeeded(ctx);
                  return;
               default:
                  throw new IllegalStateException("Unknown handshake status: " + result.getHandshakeStatus());
            }
         }
      } finally {
         if (out != null) {
            out.release();
         }

         if (inUnwrap) {
            this.setState(16);
         }

      }

   }

   private boolean wrapNonAppData(final ChannelHandlerContext ctx, boolean inUnwrap) throws SSLException {
      ByteBuf out = null;
      ByteBufAllocator alloc = ctx.alloc();

      try {
         while(!ctx.isRemoved()) {
            if (out == null) {
               out = this.allocateOutNetBuf(ctx, 2048, 1);
            }

            SSLEngineResult result = this.wrap(alloc, this.engine, Unpooled.EMPTY_BUFFER, out);
            if (result.bytesProduced() > 0) {
               ctx.write(out).addListener(new ChannelFutureListener() {
                  public void operationComplete(ChannelFuture future) {
                     Throwable cause = future.cause();
                     if (cause != null) {
                        SslHandler.this.setHandshakeFailureTransportFailure(ctx, cause);
                     }

                  }
               });
               if (inUnwrap) {
                  this.setState(16);
               }

               out = null;
            }

            SSLEngineResult.HandshakeStatus status = result.getHandshakeStatus();
            switch (status) {
               case NEED_TASK:
                  if (!this.runDelegatedTasks(inUnwrap)) {
                     return false;
                  }
                  break;
               case FINISHED:
                  if (this.setHandshakeSuccess() && inUnwrap && !this.pendingUnencryptedWrites.isEmpty()) {
                     this.wrap(ctx, true);
                  }

                  boolean var12 = false;
                  return var12;
               case NOT_HANDSHAKING:
                  if (this.setHandshakeSuccess() && inUnwrap && !this.pendingUnencryptedWrites.isEmpty()) {
                     this.wrap(ctx, true);
                  }

                  if (!inUnwrap) {
                     this.unwrapNonAppData(ctx);
                  }

                  boolean var11 = true;
                  return var11;
               case NEED_WRAP:
                  break;
               case NEED_UNWRAP:
                  if (inUnwrap || this.unwrapNonAppData(ctx) <= 0) {
                     boolean var7 = false;
                     return var7;
                  }
                  break;
               default:
                  throw new IllegalStateException("Unknown handshake status: " + result.getHandshakeStatus());
            }

            if (result.bytesProduced() == 0 && status != HandshakeStatus.NEED_TASK || result.bytesConsumed() == 0 && result.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING) {
               return false;
            }
         }

         return false;
      } finally {
         if (out != null) {
            out.release();
         }

      }
   }

   private SSLEngineResult wrapMultiple(ByteBufAllocator alloc, SSLEngine engine, ByteBuf in, ByteBuf out) throws SSLException {
      SSLEngineResult result = null;

      do {
         int nextSliceSize = Math.min(16384, in.readableBytes());
         int nextOutSize = this.engineType.calculateRequiredOutBufSpace(this, nextSliceSize, in.nioBufferCount());
         if (!out.isWritable(nextOutSize)) {
            if (result != null) {
               break;
            }

            out.ensureWritable(nextOutSize);
         }

         ByteBuf wrapBuf = in.readSlice(nextSliceSize);
         result = this.wrap(alloc, engine, wrapBuf, out);
         if (result.getStatus() == Status.CLOSED) {
            break;
         }

         if (wrapBuf.isReadable()) {
            in.readerIndex(in.readerIndex() - wrapBuf.readableBytes());
         }
      } while(in.readableBytes() > 0);

      return result;
   }

   private SSLEngineResult wrap(ByteBufAllocator alloc, SSLEngine engine, ByteBuf in, ByteBuf out) throws SSLException {
      ByteBuf newDirectIn = null;

      try {
         int readerIndex = in.readerIndex();
         int readableBytes = in.readableBytes();
         ByteBuffer[] in0;
         if (!in.isDirect() && this.engineType.wantsDirectBuffer) {
            newDirectIn = alloc.directBuffer(readableBytes);
            newDirectIn.writeBytes(in, readerIndex, readableBytes);
            in0 = this.singleBuffer;
            in0[0] = newDirectIn.internalNioBuffer(newDirectIn.readerIndex(), readableBytes);
         } else if (!(in instanceof CompositeByteBuf) && in.nioBufferCount() == 1) {
            in0 = this.singleBuffer;
            in0[0] = in.internalNioBuffer(readerIndex, readableBytes);
         } else {
            in0 = in.nioBuffers();
         }

         while(true) {
            ByteBuffer out0 = toByteBuffer(out, out.writerIndex(), out.writableBytes());
            SSLEngineResult result = engine.wrap(in0, out0);
            in.skipBytes(result.bytesConsumed());
            out.writerIndex(out.writerIndex() + result.bytesProduced());
            if (result.getStatus() != Status.BUFFER_OVERFLOW) {
               SSLEngineResult var11 = result;
               return var11;
            }

            out.ensureWritable(engine.getSession().getPacketBufferSize());
         }
      } finally {
         this.singleBuffer[0] = null;
         if (newDirectIn != null) {
            newDirectIn.release();
         }

      }
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      boolean handshakeFailed = this.handshakePromise.cause() != null;
      ClosedChannelException exception = new ClosedChannelException();
      if (this.isStateSet(8) && !this.handshakePromise.isDone()) {
         ThrowableUtil.addSuppressed(exception, StacklessSSLHandshakeException.newInstance("Connection closed while SSL/TLS handshake was in progress", SslHandler.class, "channelInactive"));
      }

      this.setHandshakeFailure(ctx, exception, !this.isStateSet(32), this.isStateSet(8), false);
      this.notifyClosePromise(exception);

      try {
         super.channelInactive(ctx);
      } catch (DecoderException var5) {
         if (!handshakeFailed || !(var5.getCause() instanceof SSLException)) {
            throw var5;
         }
      }

   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      if (this.ignoreException(cause)) {
         if (logger.isDebugEnabled()) {
            logger.debug("{} Swallowing a harmless 'connection reset by peer / broken pipe' error that occurred while writing close_notify in response to the peer's close_notify", ctx.channel(), cause);
         }

         if (ctx.channel().isActive()) {
            ctx.close();
         }
      } else {
         ctx.fireExceptionCaught(cause);
      }

   }

   private boolean ignoreException(Throwable t) {
      if (!(t instanceof SSLException) && t instanceof IOException && this.sslClosePromise.isDone()) {
         String message = t.getMessage();
         if (message != null && IGNORABLE_ERROR_MESSAGE.matcher(message).matches()) {
            return true;
         }

         StackTraceElement[] elements = t.getStackTrace();

         for(StackTraceElement element : elements) {
            String classname = element.getClassName();
            String methodname = element.getMethodName();
            if (!classname.startsWith("io.netty.") && "read".equals(methodname)) {
               if (IGNORABLE_CLASS_IN_STACK.matcher(classname).matches()) {
                  return true;
               }

               try {
                  Class<?> clazz = PlatformDependent.getClassLoader(this.getClass()).loadClass(classname);
                  if (SocketChannel.class.isAssignableFrom(clazz) || DatagramChannel.class.isAssignableFrom(clazz)) {
                     return true;
                  }

                  if (PlatformDependent.javaVersion() >= 7 && "com.sun.nio.sctp.SctpChannel".equals(clazz.getSuperclass().getName())) {
                     return true;
                  }
               } catch (Throwable cause) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Unexpected exception while loading class {} classname {}", new Object[]{this.getClass(), classname, cause});
                  }
               }
            }
         }
      }

      return false;
   }

   /** @deprecated */
   @Deprecated
   public static boolean isEncrypted(ByteBuf buffer) {
      return isEncrypted(buffer, false);
   }

   public static boolean isEncrypted(ByteBuf buffer, boolean probeSSLv2) {
      if (buffer.readableBytes() < 5) {
         throw new IllegalArgumentException("buffer must have at least 5 readable bytes");
      } else {
         return SslUtils.getEncryptedPacketLength(buffer, buffer.readerIndex(), probeSSLv2) != -2;
      }
   }

   private void decodeJdkCompatible(ChannelHandlerContext ctx, ByteBuf in) throws NotSslRecordException {
      int packetLength = this.packetLength;
      if (packetLength > 0) {
         if (in.readableBytes() < packetLength) {
            return;
         }
      } else {
         int readableBytes = in.readableBytes();
         if (readableBytes < 5) {
            return;
         }

         packetLength = SslUtils.getEncryptedPacketLength(in, in.readerIndex(), true);
         if (packetLength == -2) {
            NotSslRecordException e = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(in));
            in.skipBytes(in.readableBytes());
            this.setHandshakeFailure(ctx, e);
            throw e;
         }

         if (packetLength == -1) {
            return;
         }

         assert packetLength > 0;

         if (packetLength > readableBytes) {
            this.packetLength = packetLength;
            return;
         }
      }

      this.packetLength = 0;

      try {
         int bytesConsumed = this.unwrap(ctx, in, packetLength);

         assert bytesConsumed == packetLength || this.engine.isInboundDone() : "we feed the SSLEngine a packets worth of data: " + packetLength + " but it only consumed: " + bytesConsumed;
      } catch (Throwable cause) {
         this.handleUnwrapThrowable(ctx, cause);
      }

   }

   private void decodeNonJdkCompatible(ChannelHandlerContext ctx, ByteBuf in) {
      try {
         this.unwrap(ctx, in, in.readableBytes());
      } catch (Throwable cause) {
         this.handleUnwrapThrowable(ctx, cause);
      }

   }

   private void handleUnwrapThrowable(ChannelHandlerContext ctx, Throwable cause) {
      try {
         if (this.handshakePromise.tryFailure(cause)) {
            ctx.fireUserEventTriggered(new SslHandshakeCompletionEvent(cause));
         }

         if (this.pendingUnencryptedWrites != null) {
            this.wrapAndFlush(ctx);
         }
      } catch (SSLException ex) {
         logger.debug("SSLException during trying to call SSLEngine.wrap(...) because of an previous SSLException, ignoring...", ex);
      } finally {
         this.setHandshakeFailure(ctx, cause, true, false, true);
      }

      PlatformDependent.throwException(cause);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws SSLException {
      if (!this.isStateSet(128)) {
         if (this.jdkCompatibilityMode) {
            this.decodeJdkCompatible(ctx, in);
         } else {
            this.decodeNonJdkCompatible(ctx, in);
         }

      }
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      this.channelReadComplete0(ctx);
   }

   private void channelReadComplete0(ChannelHandlerContext ctx) {
      this.discardSomeReadBytes();
      this.flushIfNeeded(ctx);
      this.readIfNeeded(ctx);
      this.clearState(256);
      ctx.fireChannelReadComplete();
   }

   private void readIfNeeded(ChannelHandlerContext ctx) {
      if (!ctx.channel().config().isAutoRead() && (!this.isStateSet(256) || !this.handshakePromise.isDone())) {
         ctx.read();
      }

   }

   private void flushIfNeeded(ChannelHandlerContext ctx) {
      if (this.isStateSet(16)) {
         this.forceFlush(ctx);
      }

   }

   private int unwrapNonAppData(ChannelHandlerContext ctx) throws SSLException {
      return this.unwrap(ctx, Unpooled.EMPTY_BUFFER, 0);
   }

   private int unwrap(ChannelHandlerContext ctx, ByteBuf packet, int length) throws SSLException {
      int originalLength = length;
      boolean wrapLater = false;
      boolean notifyClosure = false;
      boolean executedRead = false;
      ByteBuf decodeOut = this.allocate(ctx, length);

      try {
         do {
            SSLEngineResult result = this.engineType.unwrap(this, packet, length, decodeOut);
            SSLEngineResult.Status status = result.getStatus();
            SSLEngineResult.HandshakeStatus handshakeStatus = result.getHandshakeStatus();
            int produced = result.bytesProduced();
            int consumed = result.bytesConsumed();
            packet.skipBytes(consumed);
            length -= consumed;
            if (handshakeStatus == HandshakeStatus.FINISHED || handshakeStatus == HandshakeStatus.NOT_HANDSHAKING) {
               boolean var10001;
               label238: {
                  label237: {
                     if (decodeOut.isReadable()) {
                        if (this.setHandshakeSuccessUnwrapMarkReentry()) {
                           break label237;
                        }
                     } else if (this.setHandshakeSuccess()) {
                        break label237;
                     }

                     if (handshakeStatus != HandshakeStatus.FINISHED && this.pendingUnencryptedWrites.isEmpty()) {
                        var10001 = false;
                        break label238;
                     }
                  }

                  var10001 = true;
               }

               wrapLater |= var10001;
            }

            if (decodeOut.isReadable()) {
               this.setState(256);
               if (this.isStateSet(512)) {
                  executedRead = true;
                  this.executeChannelRead(ctx, decodeOut);
               } else {
                  ctx.fireChannelRead(decodeOut);
               }

               decodeOut = null;
            }

            if (status == Status.CLOSED) {
               notifyClosure = true;
            } else if (status == Status.BUFFER_OVERFLOW) {
               if (decodeOut != null) {
                  decodeOut.release();
               }

               int applicationBufferSize = this.engine.getSession().getApplicationBufferSize();
               decodeOut = this.allocate(ctx, this.engineType.calculatePendingData(this, applicationBufferSize < produced ? applicationBufferSize : applicationBufferSize - produced));
               continue;
            }

            if (handshakeStatus == HandshakeStatus.NEED_TASK) {
               boolean pending = this.runDelegatedTasks(true);
               if (!pending) {
                  wrapLater = false;
                  break;
               }
            } else if (handshakeStatus == HandshakeStatus.NEED_WRAP && this.wrapNonAppData(ctx, true) && length == 0) {
               break;
            }

            if (status == Status.BUFFER_UNDERFLOW || handshakeStatus != HandshakeStatus.NEED_TASK && (consumed == 0 && produced == 0 || length == 0 && handshakeStatus == HandshakeStatus.NOT_HANDSHAKING)) {
               if (handshakeStatus == HandshakeStatus.NEED_UNWRAP) {
                  this.readIfNeeded(ctx);
               }
               break;
            }

            if (decodeOut == null) {
               decodeOut = this.allocate(ctx, length);
            }
         } while(!ctx.isRemoved());

         if (this.isStateSet(2) && this.handshakePromise.isDone()) {
            this.clearState(2);
            wrapLater = true;
         }

         if (wrapLater) {
            this.wrap(ctx, true);
         }
      } finally {
         if (decodeOut != null) {
            decodeOut.release();
         }

         if (notifyClosure) {
            if (executedRead) {
               this.executeNotifyClosePromise(ctx);
            } else {
               this.notifyClosePromise((Throwable)null);
            }
         }

      }

      return originalLength - length;
   }

   private boolean setHandshakeSuccessUnwrapMarkReentry() throws SSLException {
      boolean setReentryState = !this.isStateSet(512);
      if (setReentryState) {
         this.setState(512);
      }

      boolean var2;
      try {
         var2 = this.setHandshakeSuccess();
      } finally {
         if (setReentryState) {
            this.clearState(512);
         }

      }

      return var2;
   }

   private void executeNotifyClosePromise(ChannelHandlerContext ctx) {
      try {
         ctx.executor().execute(new Runnable() {
            public void run() {
               SslHandler.this.notifyClosePromise((Throwable)null);
            }
         });
      } catch (RejectedExecutionException e) {
         this.notifyClosePromise(e);
      }

   }

   private void executeChannelRead(final ChannelHandlerContext ctx, final ByteBuf decodedOut) {
      try {
         ctx.executor().execute(new Runnable() {
            public void run() {
               ctx.fireChannelRead(decodedOut);
            }
         });
      } catch (RejectedExecutionException e) {
         decodedOut.release();
         throw e;
      }
   }

   private static ByteBuffer toByteBuffer(ByteBuf out, int index, int len) {
      return out.nioBufferCount() == 1 ? out.internalNioBuffer(index, len) : out.nioBuffer(index, len);
   }

   private static boolean inEventLoop(Executor executor) {
      return executor instanceof EventExecutor && ((EventExecutor)executor).inEventLoop();
   }

   private boolean runDelegatedTasks(boolean inUnwrap) {
      if (this.delegatedTaskExecutor != ImmediateExecutor.INSTANCE && !inEventLoop(this.delegatedTaskExecutor)) {
         this.executeDelegatedTask(inUnwrap);
         return false;
      } else {
         while(true) {
            Runnable task = this.engine.getDelegatedTask();
            if (task == null) {
               return true;
            }

            this.setState(128);
            if (task instanceof AsyncRunnable) {
               boolean pending = false;

               try {
                  AsyncRunnable asyncTask = (AsyncRunnable)task;
                  AsyncTaskCompletionHandler completionHandler = new AsyncTaskCompletionHandler(inUnwrap);
                  asyncTask.run(completionHandler);
                  pending = completionHandler.resumeLater();
                  if (pending) {
                     boolean var6 = false;
                     return var6;
                  }
               } finally {
                  if (!pending) {
                     this.clearState(128);
                  }

               }
            } else {
               try {
                  task.run();
               } finally {
                  this.clearState(128);
               }
            }
         }
      }
   }

   private SslTasksRunner getTaskRunner(boolean inUnwrap) {
      return inUnwrap ? this.sslTaskRunnerForUnwrap : this.sslTaskRunner;
   }

   private void executeDelegatedTask(boolean inUnwrap) {
      this.executeDelegatedTask(this.getTaskRunner(inUnwrap));
   }

   private void executeDelegatedTask(SslTasksRunner task) {
      this.setState(128);

      try {
         this.delegatedTaskExecutor.execute(task);
      } catch (RejectedExecutionException e) {
         this.clearState(128);
         throw e;
      }
   }

   private boolean setHandshakeSuccess() throws SSLException {
      SSLSession session = this.engine.getSession();
      if (this.resumptionController != null && !this.handshakePromise.isDone()) {
         try {
            if (this.resumptionController.validateResumeIfNeeded(this.engine) && logger.isDebugEnabled()) {
               logger.debug("{} Resumed and reauthenticated session", this.ctx.channel());
            }
         } catch (CertificateException e) {
            SSLHandshakeException exception = new SSLHandshakeException(e.getMessage());
            exception.initCause(e);
            throw exception;
         }
      }

      boolean notified;
      if (notified = !this.handshakePromise.isDone() && this.handshakePromise.trySuccess(this.ctx.channel())) {
         if (logger.isDebugEnabled()) {
            logger.debug("{} HANDSHAKEN: protocol:{} cipher suite:{}", new Object[]{this.ctx.channel(), session.getProtocol(), session.getCipherSuite()});
         }

         this.ctx.fireUserEventTriggered(SslHandshakeCompletionEvent.SUCCESS);
      }

      if (this.isStateSet(4)) {
         this.clearState(4);
         if (!this.ctx.channel().config().isAutoRead()) {
            this.ctx.read();
         }
      }

      return notified;
   }

   private void setHandshakeFailure(ChannelHandlerContext ctx, Throwable cause) {
      this.setHandshakeFailure(ctx, cause, true, true, false);
   }

   private void setHandshakeFailure(ChannelHandlerContext ctx, Throwable cause, boolean closeInbound, boolean notify, boolean alwaysFlushAndClose) {
      try {
         this.setState(32);
         this.engine.closeOutbound();
         if (closeInbound) {
            try {
               this.engine.closeInbound();
            } catch (SSLException e) {
               if (logger.isDebugEnabled()) {
                  String msg = e.getMessage();
                  if (msg == null || !msg.contains("possible truncation attack") && !msg.contains("closing inbound before receiving peer's close_notify")) {
                     logger.debug("{} SSLEngine.closeInbound() raised an exception.", ctx.channel(), e);
                  }
               }
            }
         }

         if (this.handshakePromise.tryFailure(cause) || alwaysFlushAndClose) {
            SslUtils.handleHandshakeFailure(ctx, cause, notify);
         }
      } finally {
         this.releaseAndFailAll(ctx, cause);
      }

   }

   private void setHandshakeFailureTransportFailure(ChannelHandlerContext ctx, Throwable cause) {
      try {
         SSLException transportFailure = new SSLException("failure when writing TLS control frames", cause);
         this.releaseAndFailAll(ctx, transportFailure);
         if (this.handshakePromise.tryFailure(transportFailure)) {
            ctx.fireUserEventTriggered(new SslHandshakeCompletionEvent(transportFailure));
         }
      } finally {
         ctx.close();
      }

   }

   private void releaseAndFailAll(ChannelHandlerContext ctx, Throwable cause) {
      if (this.resumptionController != null && (!this.engine.getSession().isValid() || cause instanceof SSLHandshakeException)) {
         this.resumptionController.remove(this.engine());
      }

      if (this.pendingUnencryptedWrites != null) {
         this.pendingUnencryptedWrites.releaseAndFailAll(ctx, cause);
      }

   }

   private void notifyClosePromise(Throwable cause) {
      if (cause == null) {
         if (this.sslClosePromise.trySuccess(this.ctx.channel())) {
            this.ctx.fireUserEventTriggered(SslCloseCompletionEvent.SUCCESS);
         }
      } else if (this.sslClosePromise.tryFailure(cause)) {
         this.ctx.fireUserEventTriggered(new SslCloseCompletionEvent(cause));
      }

   }

   private void closeOutboundAndChannel(ChannelHandlerContext ctx, final ChannelPromise promise, boolean disconnect) throws Exception {
      this.setState(32);
      this.engine.closeOutbound();
      if (!ctx.channel().isActive()) {
         if (disconnect) {
            ctx.disconnect(promise);
         } else {
            ctx.close(promise);
         }

      } else {
         ChannelPromise closeNotifyPromise = ctx.newPromise();

         try {
            this.flush(ctx, closeNotifyPromise);
         } finally {
            if (!this.isStateSet(64)) {
               this.setState(64);
               this.safeClose(ctx, closeNotifyPromise, (ChannelPromise)PromiseNotifier.cascade(false, ctx.newPromise(), promise));
            } else {
               this.sslClosePromise.addListener(new FutureListener() {
                  public void operationComplete(Future future) {
                     promise.setSuccess();
                  }
               });
            }

         }

      }
   }

   private void flush(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
      if (this.pendingUnencryptedWrites != null) {
         this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, promise);
      } else {
         promise.setFailure(newPendingWritesNullException());
      }

      this.flush(ctx);
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      this.ctx = ctx;
      Channel channel = ctx.channel();
      this.pendingUnencryptedWrites = new SslHandlerCoalescingBufferQueue(channel, 16, this.engineType.wantsDirectBuffer) {
         protected int wrapDataSize() {
            return SslHandler.this.wrapDataSize;
         }
      };
      this.setOpensslEngineSocketFd(channel);
      boolean fastOpen = Boolean.TRUE.equals(channel.config().getOption(ChannelOption.TCP_FASTOPEN_CONNECT));
      boolean active = channel.isActive();
      if (active || fastOpen) {
         this.startHandshakeProcessing(active);
         ChannelOutboundBuffer outboundBuffer;
         if (fastOpen && ((outboundBuffer = channel.unsafe().outboundBuffer()) == null || outboundBuffer.totalPendingWriteBytes() > 0L)) {
            this.setState(16);
         }
      }

   }

   private void startHandshakeProcessing(boolean flushAtEnd) {
      if (!this.isStateSet(8)) {
         this.setState(8);
         if (this.engine.getUseClientMode()) {
            this.handshake(flushAtEnd);
         }

         this.applyHandshakeTimeout();
      } else if (this.isStateSet(16)) {
         this.forceFlush(this.ctx);
      }

   }

   public Future renegotiate() {
      ChannelHandlerContext ctx = this.ctx;
      if (ctx == null) {
         throw new IllegalStateException();
      } else {
         return this.renegotiate(ctx.executor().newPromise());
      }
   }

   public Future renegotiate(final Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");
      ChannelHandlerContext ctx = this.ctx;
      if (ctx == null) {
         throw new IllegalStateException();
      } else {
         EventExecutor executor = ctx.executor();
         if (!executor.inEventLoop()) {
            executor.execute(new Runnable() {
               public void run() {
                  SslHandler.this.renegotiateOnEventLoop(promise);
               }
            });
            return promise;
         } else {
            this.renegotiateOnEventLoop(promise);
            return promise;
         }
      }
   }

   private void renegotiateOnEventLoop(Promise newHandshakePromise) {
      Promise<Channel> oldHandshakePromise = this.handshakePromise;
      if (!oldHandshakePromise.isDone()) {
         PromiseNotifier.cascade(oldHandshakePromise, newHandshakePromise);
      } else {
         this.handshakePromise = newHandshakePromise;
         this.handshake(true);
         this.applyHandshakeTimeout();
      }

   }

   private void handshake(boolean flushAtEnd) {
      if (this.engine.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING) {
         if (!this.handshakePromise.isDone()) {
            ChannelHandlerContext ctx = this.ctx;

            try {
               this.engine.beginHandshake();
               this.wrapNonAppData(ctx, false);
            } catch (Throwable e) {
               this.setHandshakeFailure(ctx, e);
            } finally {
               if (flushAtEnd) {
                  this.forceFlush(ctx);
               }

            }

         }
      }
   }

   private void applyHandshakeTimeout() {
      final Promise<Channel> localHandshakePromise = this.handshakePromise;
      final long handshakeTimeoutMillis = this.handshakeTimeoutMillis;
      if (handshakeTimeoutMillis > 0L && !localHandshakePromise.isDone()) {
         final Future<?> timeoutFuture = this.ctx.executor().schedule(new Runnable() {
            public void run() {
               if (!localHandshakePromise.isDone()) {
                  SSLException exception = new SslHandshakeTimeoutException("handshake timed out after " + handshakeTimeoutMillis + "ms");

                  try {
                     if (localHandshakePromise.tryFailure(exception)) {
                        SslUtils.handleHandshakeFailure(SslHandler.this.ctx, exception, true);
                     }
                  } finally {
                     SslHandler.this.releaseAndFailAll(SslHandler.this.ctx, exception);
                  }

               }
            }
         }, handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
         localHandshakePromise.addListener(new FutureListener() {
            public void operationComplete(Future f) throws Exception {
               timeoutFuture.cancel(false);
            }
         });
      }
   }

   private void forceFlush(ChannelHandlerContext ctx) {
      this.clearState(16);
      ctx.flush();
   }

   private void setOpensslEngineSocketFd(Channel c) {
      if (c instanceof UnixChannel && this.engine instanceof ReferenceCountedOpenSslEngine) {
         ((ReferenceCountedOpenSslEngine)this.engine).bioSetFd(((UnixChannel)c).fd().intValue());
      }

   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      this.setOpensslEngineSocketFd(ctx.channel());
      if (!this.startTls) {
         this.startHandshakeProcessing(true);
      }

      ctx.fireChannelActive();
   }

   private void safeClose(final ChannelHandlerContext ctx, final ChannelFuture flushFuture, final ChannelPromise promise) {
      if (!ctx.channel().isActive()) {
         ctx.close(promise);
      } else {
         final Future<?> timeoutFuture;
         if (!flushFuture.isDone()) {
            long closeNotifyTimeout = this.closeNotifyFlushTimeoutMillis;
            if (closeNotifyTimeout > 0L) {
               timeoutFuture = ctx.executor().schedule(new Runnable() {
                  public void run() {
                     if (!flushFuture.isDone()) {
                        SslHandler.logger.warn("{} Last write attempt timed out; force-closing the connection.", ctx.channel());
                        SslHandler.addCloseListener(ctx.close(ctx.newPromise()), promise);
                     }

                  }
               }, closeNotifyTimeout, TimeUnit.MILLISECONDS);
            } else {
               timeoutFuture = null;
            }
         } else {
            timeoutFuture = null;
         }

         flushFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) {
               if (timeoutFuture != null) {
                  timeoutFuture.cancel(false);
               }

               final long closeNotifyReadTimeout = SslHandler.this.closeNotifyReadTimeoutMillis;
               if (closeNotifyReadTimeout <= 0L) {
                  SslHandler.addCloseListener(ctx.close(ctx.newPromise()), promise);
               } else {
                  final Future<?> closeNotifyReadTimeoutFuture;
                  if (!SslHandler.this.sslClosePromise.isDone()) {
                     closeNotifyReadTimeoutFuture = ctx.executor().schedule(new Runnable() {
                        public void run() {
                           if (!SslHandler.this.sslClosePromise.isDone()) {
                              SslHandler.logger.debug("{} did not receive close_notify in {}ms; force-closing the connection.", ctx.channel(), closeNotifyReadTimeout);
                              SslHandler.addCloseListener(ctx.close(ctx.newPromise()), promise);
                           }

                        }
                     }, closeNotifyReadTimeout, TimeUnit.MILLISECONDS);
                  } else {
                     closeNotifyReadTimeoutFuture = null;
                  }

                  SslHandler.this.sslClosePromise.addListener(new FutureListener() {
                     public void operationComplete(Future future) throws Exception {
                        if (closeNotifyReadTimeoutFuture != null) {
                           closeNotifyReadTimeoutFuture.cancel(false);
                        }

                        SslHandler.addCloseListener(ctx.close(ctx.newPromise()), promise);
                     }
                  });
               }

            }
         });
      }
   }

   private static void addCloseListener(ChannelFuture future, ChannelPromise promise) {
      PromiseNotifier.cascade(false, future, promise);
   }

   private ByteBuf allocate(ChannelHandlerContext ctx, int capacity) {
      ByteBufAllocator alloc = ctx.alloc();
      return this.engineType.wantsDirectBuffer ? alloc.directBuffer(capacity) : alloc.buffer(capacity);
   }

   private ByteBuf allocateOutNetBuf(ChannelHandlerContext ctx, int pendingBytes, int numComponents) {
      return this.engineType.allocateWrapBuffer(this, ctx.alloc(), pendingBytes, numComponents);
   }

   private boolean isStateSet(int bit) {
      return (this.state & bit) == bit;
   }

   private void setState(int bit) {
      this.state = (short)(this.state | bit);
   }

   private void clearState(int bit) {
      this.state = (short)(this.state & ~bit);
   }

   private static enum SslEngineType {
      TCNATIVE(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) {
         SSLEngineResult unwrap(SslHandler handler, ByteBuf in, int len, ByteBuf out) throws SSLException {
            int nioBufferCount = in.nioBufferCount();
            int writerIndex = out.writerIndex();
            SSLEngineResult result;
            if (nioBufferCount > 1) {
               ReferenceCountedOpenSslEngine opensslEngine = (ReferenceCountedOpenSslEngine)handler.engine;

               try {
                  handler.singleBuffer[0] = SslHandler.toByteBuffer(out, writerIndex, out.writableBytes());
                  result = opensslEngine.unwrap(in.nioBuffers(in.readerIndex(), len), handler.singleBuffer);
               } finally {
                  handler.singleBuffer[0] = null;
               }
            } else {
               result = handler.engine.unwrap(SslHandler.toByteBuffer(in, in.readerIndex(), len), SslHandler.toByteBuffer(out, writerIndex, out.writableBytes()));
            }

            out.writerIndex(writerIndex + result.bytesProduced());
            return result;
         }

         ByteBuf allocateWrapBuffer(SslHandler handler, ByteBufAllocator allocator, int pendingBytes, int numComponents) {
            return allocator.directBuffer(((ReferenceCountedOpenSslEngine)handler.engine).calculateOutNetBufSize(pendingBytes, numComponents));
         }

         int calculateRequiredOutBufSpace(SslHandler handler, int pendingBytes, int numComponents) {
            return ((ReferenceCountedOpenSslEngine)handler.engine).calculateMaxLengthForWrap(pendingBytes, numComponents);
         }

         int calculatePendingData(SslHandler handler, int guess) {
            int sslPending = ((ReferenceCountedOpenSslEngine)handler.engine).sslPending();
            return sslPending > 0 ? sslPending : guess;
         }

         boolean jdkCompatibilityMode(SSLEngine engine) {
            return ((ReferenceCountedOpenSslEngine)engine).jdkCompatibilityMode;
         }
      },
      CONSCRYPT(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) {
         SSLEngineResult unwrap(SslHandler handler, ByteBuf in, int len, ByteBuf out) throws SSLException {
            int nioBufferCount = in.nioBufferCount();
            int writerIndex = out.writerIndex();
            SSLEngineResult result;
            if (nioBufferCount > 1) {
               try {
                  handler.singleBuffer[0] = SslHandler.toByteBuffer(out, writerIndex, out.writableBytes());
                  result = ((ConscryptAlpnSslEngine)handler.engine).unwrap(in.nioBuffers(in.readerIndex(), len), handler.singleBuffer);
               } finally {
                  handler.singleBuffer[0] = null;
               }
            } else {
               result = handler.engine.unwrap(SslHandler.toByteBuffer(in, in.readerIndex(), len), SslHandler.toByteBuffer(out, writerIndex, out.writableBytes()));
            }

            out.writerIndex(writerIndex + result.bytesProduced());
            return result;
         }

         ByteBuf allocateWrapBuffer(SslHandler handler, ByteBufAllocator allocator, int pendingBytes, int numComponents) {
            return allocator.directBuffer(((ConscryptAlpnSslEngine)handler.engine).calculateOutNetBufSize(pendingBytes, numComponents));
         }

         int calculateRequiredOutBufSpace(SslHandler handler, int pendingBytes, int numComponents) {
            return ((ConscryptAlpnSslEngine)handler.engine).calculateRequiredOutBufSpace(pendingBytes, numComponents);
         }

         int calculatePendingData(SslHandler handler, int guess) {
            return guess;
         }

         boolean jdkCompatibilityMode(SSLEngine engine) {
            return true;
         }
      },
      JDK(false, ByteToMessageDecoder.MERGE_CUMULATOR) {
         SSLEngineResult unwrap(SslHandler handler, ByteBuf in, int len, ByteBuf out) throws SSLException {
            int writerIndex = out.writerIndex();
            ByteBuffer inNioBuffer = SslHandler.toByteBuffer(in, in.readerIndex(), len);
            int position = inNioBuffer.position();
            SSLEngineResult result = handler.engine.unwrap(inNioBuffer, SslHandler.toByteBuffer(out, writerIndex, out.writableBytes()));
            out.writerIndex(writerIndex + result.bytesProduced());
            if (result.bytesConsumed() == 0) {
               int consumed = inNioBuffer.position() - position;
               if (consumed != result.bytesConsumed()) {
                  return new SSLEngineResult(result.getStatus(), result.getHandshakeStatus(), consumed, result.bytesProduced());
               }
            }

            return result;
         }

         ByteBuf allocateWrapBuffer(SslHandler handler, ByteBufAllocator allocator, int pendingBytes, int numComponents) {
            return allocator.heapBuffer(Math.max(pendingBytes, handler.engine.getSession().getPacketBufferSize()));
         }

         int calculateRequiredOutBufSpace(SslHandler handler, int pendingBytes, int numComponents) {
            return handler.engine.getSession().getPacketBufferSize();
         }

         int calculatePendingData(SslHandler handler, int guess) {
            return guess;
         }

         boolean jdkCompatibilityMode(SSLEngine engine) {
            return true;
         }
      };

      final boolean wantsDirectBuffer;
      final ByteToMessageDecoder.Cumulator cumulator;

      static SslEngineType forEngine(SSLEngine engine) {
         return engine instanceof ReferenceCountedOpenSslEngine ? TCNATIVE : (engine instanceof ConscryptAlpnSslEngine ? CONSCRYPT : JDK);
      }

      private SslEngineType(boolean wantsDirectBuffer, ByteToMessageDecoder.Cumulator cumulator) {
         this.wantsDirectBuffer = wantsDirectBuffer;
         this.cumulator = cumulator;
      }

      abstract SSLEngineResult unwrap(SslHandler var1, ByteBuf var2, int var3, ByteBuf var4) throws SSLException;

      abstract int calculatePendingData(SslHandler var1, int var2);

      abstract boolean jdkCompatibilityMode(SSLEngine var1);

      abstract ByteBuf allocateWrapBuffer(SslHandler var1, ByteBufAllocator var2, int var3, int var4);

      abstract int calculateRequiredOutBufSpace(SslHandler var1, int var2, int var3);
   }

   private final class AsyncTaskCompletionHandler implements Runnable {
      private final boolean inUnwrap;
      boolean didRun;
      boolean resumeLater;

      AsyncTaskCompletionHandler(boolean inUnwrap) {
         this.inUnwrap = inUnwrap;
      }

      public void run() {
         this.didRun = true;
         if (this.resumeLater) {
            SslHandler.this.getTaskRunner(this.inUnwrap).runComplete();
         }

      }

      boolean resumeLater() {
         if (!this.didRun) {
            this.resumeLater = true;
            return true;
         } else {
            return false;
         }
      }
   }

   private final class SslTasksRunner implements Runnable {
      private final boolean inUnwrap;
      private final Runnable runCompleteTask = new Runnable() {
         public void run() {
            SslTasksRunner.this.runComplete();
         }
      };

      SslTasksRunner(boolean inUnwrap) {
         this.inUnwrap = inUnwrap;
      }

      private void taskError(Throwable e) {
         if (this.inUnwrap) {
            try {
               SslHandler.this.handleUnwrapThrowable(SslHandler.this.ctx, e);
            } catch (Throwable cause) {
               this.safeExceptionCaught(cause);
            }
         } else {
            SslHandler.this.setHandshakeFailure(SslHandler.this.ctx, e);
            SslHandler.this.forceFlush(SslHandler.this.ctx);
         }

      }

      private void safeExceptionCaught(Throwable cause) {
         try {
            SslHandler.this.exceptionCaught(SslHandler.this.ctx, this.wrapIfNeeded(cause));
         } catch (Throwable error) {
            SslHandler.this.ctx.fireExceptionCaught(error);
         }

      }

      private Throwable wrapIfNeeded(Throwable cause) {
         if (!this.inUnwrap) {
            return cause;
         } else {
            return (Throwable)(cause instanceof DecoderException ? cause : new DecoderException(cause));
         }
      }

      private void tryDecodeAgain() {
         try {
            SslHandler.this.channelRead(SslHandler.this.ctx, Unpooled.EMPTY_BUFFER);
         } catch (Throwable cause) {
            this.safeExceptionCaught(cause);
         } finally {
            SslHandler.this.channelReadComplete0(SslHandler.this.ctx);
         }

      }

      private void resumeOnEventExecutor() {
         assert SslHandler.this.ctx.executor().inEventLoop();

         SslHandler.this.clearState(128);

         try {
            SSLEngineResult.HandshakeStatus status = SslHandler.this.engine.getHandshakeStatus();
            switch (status) {
               case NEED_TASK:
                  SslHandler.this.executeDelegatedTask(this);
                  break;
               case FINISHED:
               case NOT_HANDSHAKING:
                  SslHandler.this.setHandshakeSuccess();

                  try {
                     SslHandler.this.wrap(SslHandler.this.ctx, this.inUnwrap);
                  } catch (Throwable e) {
                     this.taskError(e);
                     return;
                  }

                  if (this.inUnwrap) {
                     SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                  }

                  SslHandler.this.forceFlush(SslHandler.this.ctx);
                  this.tryDecodeAgain();
                  break;
               case NEED_WRAP:
                  try {
                     if (!SslHandler.this.wrapNonAppData(SslHandler.this.ctx, false) && this.inUnwrap) {
                        SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                     }

                     SslHandler.this.forceFlush(SslHandler.this.ctx);
                  } catch (Throwable e) {
                     this.taskError(e);
                     return;
                  }

                  this.tryDecodeAgain();
                  break;
               case NEED_UNWRAP:
                  try {
                     SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                  } catch (SSLException e) {
                     SslHandler.this.handleUnwrapThrowable(SslHandler.this.ctx, e);
                     return;
                  }

                  this.tryDecodeAgain();
                  break;
               default:
                  throw new AssertionError();
            }
         } catch (Throwable cause) {
            this.safeExceptionCaught(cause);
         }

      }

      void runComplete() {
         EventExecutor executor = SslHandler.this.ctx.executor();
         executor.execute(new Runnable() {
            public void run() {
               SslTasksRunner.this.resumeOnEventExecutor();
            }
         });
      }

      public void run() {
         try {
            Runnable task = SslHandler.this.engine.getDelegatedTask();
            if (task == null) {
               return;
            }

            if (task instanceof AsyncRunnable) {
               AsyncRunnable asyncTask = (AsyncRunnable)task;
               asyncTask.run(this.runCompleteTask);
            } else {
               task.run();
               this.runComplete();
            }
         } catch (Throwable cause) {
            this.handleException(cause);
         }

      }

      private void handleException(final Throwable cause) {
         EventExecutor executor = SslHandler.this.ctx.executor();
         if (executor.inEventLoop()) {
            SslHandler.this.clearState(128);
            this.safeExceptionCaught(cause);
         } else {
            try {
               executor.execute(new Runnable() {
                  public void run() {
                     SslHandler.this.clearState(128);
                     SslTasksRunner.this.safeExceptionCaught(cause);
                  }
               });
            } catch (RejectedExecutionException var4) {
               SslHandler.this.clearState(128);
               SslHandler.this.ctx.fireExceptionCaught(cause);
            }
         }

      }
   }

   private final class LazyChannelPromise extends DefaultPromise {
      private LazyChannelPromise() {
      }

      protected EventExecutor executor() {
         if (SslHandler.this.ctx == null) {
            throw new IllegalStateException();
         } else {
            return SslHandler.this.ctx.executor();
         }
      }

      protected void checkDeadLock() {
         if (SslHandler.this.ctx != null) {
            super.checkDeadLock();
         }
      }
   }
}
