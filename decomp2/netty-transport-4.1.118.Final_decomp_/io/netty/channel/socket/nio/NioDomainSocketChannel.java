package io.netty.channel.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.FileRegion;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.ServerChannel;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.socket.DuplexChannel;
import io.netty.channel.socket.DuplexChannelConfig;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class NioDomainSocketChannel extends AbstractNioByteChannel implements DuplexChannel {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(NioDomainSocketChannel.class);
   private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
   private static final Method OPEN_SOCKET_CHANNEL_WITH_FAMILY = SelectorProviderUtil.findOpenMethod("openSocketChannel");
   private final ChannelConfig config;
   private volatile boolean isInputShutdown;
   private volatile boolean isOutputShutdown;

   static SocketChannel newChannel(SelectorProvider provider) {
      if (PlatformDependent.javaVersion() < 16) {
         throw new UnsupportedOperationException("Only supported on java 16+");
      } else {
         try {
            SocketChannel channel = (SocketChannel)SelectorProviderUtil.newDomainSocketChannel(OPEN_SOCKET_CHANNEL_WITH_FAMILY, provider);
            if (channel == null) {
               throw new ChannelException("Failed to open a socket.");
            } else {
               return channel;
            }
         } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
         }
      }
   }

   public NioDomainSocketChannel() {
      this(DEFAULT_SELECTOR_PROVIDER);
   }

   public NioDomainSocketChannel(SelectorProvider provider) {
      this(newChannel(provider));
   }

   public NioDomainSocketChannel(SocketChannel socket) {
      this((Channel)null, socket);
   }

   public NioDomainSocketChannel(Channel parent, SocketChannel socket) {
      super(parent, socket);
      if (PlatformDependent.javaVersion() < 16) {
         throw new UnsupportedOperationException("Only supported on java 16+");
      } else {
         this.config = new NioDomainSocketChannelConfig(this, socket);
      }
   }

   public ServerChannel parent() {
      return (ServerChannel)super.parent();
   }

   public ChannelConfig config() {
      return this.config;
   }

   protected SocketChannel javaChannel() {
      return (SocketChannel)super.javaChannel();
   }

   public boolean isActive() {
      SocketChannel ch = this.javaChannel();
      return ch.isOpen() && ch.isConnected();
   }

   public boolean isOutputShutdown() {
      return this.isOutputShutdown || !this.isActive();
   }

   public boolean isInputShutdown() {
      return this.isInputShutdown || !this.isActive();
   }

   public boolean isShutdown() {
      return this.isInputShutdown() && this.isOutputShutdown() || !this.isActive();
   }

   @SuppressJava6Requirement(
      reason = "guarded by version check"
   )
   protected void doShutdownOutput() throws Exception {
      this.javaChannel().shutdownOutput();
      this.isOutputShutdown = true;
   }

   public ChannelFuture shutdownOutput() {
      return this.shutdownOutput(this.newPromise());
   }

   public ChannelFuture shutdownOutput(final ChannelPromise promise) {
      EventLoop loop = this.eventLoop();
      if (loop.inEventLoop()) {
         ((AbstractChannel.AbstractUnsafe)this.unsafe()).shutdownOutput(promise);
      } else {
         loop.execute(new Runnable() {
            public void run() {
               ((AbstractChannel.AbstractUnsafe)NioDomainSocketChannel.this.unsafe()).shutdownOutput(promise);
            }
         });
      }

      return promise;
   }

   public ChannelFuture shutdownInput() {
      return this.shutdownInput(this.newPromise());
   }

   protected boolean isInputShutdown0() {
      return this.isInputShutdown();
   }

   public ChannelFuture shutdownInput(final ChannelPromise promise) {
      EventLoop loop = this.eventLoop();
      if (loop.inEventLoop()) {
         this.shutdownInput0(promise);
      } else {
         loop.execute(new Runnable() {
            public void run() {
               NioDomainSocketChannel.this.shutdownInput0(promise);
            }
         });
      }

      return promise;
   }

   public ChannelFuture shutdown() {
      return this.shutdown(this.newPromise());
   }

   public ChannelFuture shutdown(final ChannelPromise promise) {
      ChannelFuture shutdownOutputFuture = this.shutdownOutput();
      if (shutdownOutputFuture.isDone()) {
         this.shutdownOutputDone(shutdownOutputFuture, promise);
      } else {
         shutdownOutputFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture shutdownOutputFuture) throws Exception {
               NioDomainSocketChannel.this.shutdownOutputDone(shutdownOutputFuture, promise);
            }
         });
      }

      return promise;
   }

   private void shutdownOutputDone(final ChannelFuture shutdownOutputFuture, final ChannelPromise promise) {
      ChannelFuture shutdownInputFuture = this.shutdownInput();
      if (shutdownInputFuture.isDone()) {
         shutdownDone(shutdownOutputFuture, shutdownInputFuture, promise);
      } else {
         shutdownInputFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture shutdownInputFuture) throws Exception {
               NioDomainSocketChannel.shutdownDone(shutdownOutputFuture, shutdownInputFuture, promise);
            }
         });
      }

   }

   private static void shutdownDone(ChannelFuture shutdownOutputFuture, ChannelFuture shutdownInputFuture, ChannelPromise promise) {
      Throwable shutdownOutputCause = shutdownOutputFuture.cause();
      Throwable shutdownInputCause = shutdownInputFuture.cause();
      if (shutdownOutputCause != null) {
         if (shutdownInputCause != null) {
            logger.debug("Exception suppressed because a previous exception occurred.", shutdownInputCause);
         }

         promise.setFailure(shutdownOutputCause);
      } else if (shutdownInputCause != null) {
         promise.setFailure(shutdownInputCause);
      } else {
         promise.setSuccess();
      }

   }

   private void shutdownInput0(ChannelPromise promise) {
      try {
         this.shutdownInput0();
         promise.setSuccess();
      } catch (Throwable t) {
         promise.setFailure(t);
      }

   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   private void shutdownInput0() throws Exception {
      this.javaChannel().shutdownInput();
      this.isInputShutdown = true;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   protected SocketAddress localAddress0() {
      try {
         return this.javaChannel().getLocalAddress();
      } catch (Exception var2) {
         return null;
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   protected SocketAddress remoteAddress0() {
      try {
         return this.javaChannel().getRemoteAddress();
      } catch (Exception var2) {
         return null;
      }
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      SocketUtils.bind(this.javaChannel(), localAddress);
   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (localAddress != null) {
         this.doBind(localAddress);
      }

      boolean success = false;

      boolean var5;
      try {
         boolean connected = SocketUtils.connect(this.javaChannel(), remoteAddress);
         if (!connected) {
            this.selectionKey().interestOps(8);
         }

         success = true;
         var5 = connected;
      } finally {
         if (!success) {
            this.doClose();
         }

      }

      return var5;
   }

   protected void doFinishConnect() throws Exception {
      if (!this.javaChannel().finishConnect()) {
         throw new Error();
      }
   }

   protected void doDisconnect() throws Exception {
      this.doClose();
   }

   protected void doClose() throws Exception {
      try {
         super.doClose();
      } finally {
         this.javaChannel().close();
      }

   }

   protected int doReadBytes(ByteBuf byteBuf) throws Exception {
      RecvByteBufAllocator.Handle allocHandle = this.unsafe().recvBufAllocHandle();
      allocHandle.attemptedBytesRead(byteBuf.writableBytes());
      return byteBuf.writeBytes(this.javaChannel(), allocHandle.attemptedBytesRead());
   }

   protected int doWriteBytes(ByteBuf buf) throws Exception {
      int expectedWrittenBytes = buf.readableBytes();
      return buf.readBytes(this.javaChannel(), expectedWrittenBytes);
   }

   protected long doWriteFileRegion(FileRegion region) throws Exception {
      long position = region.transferred();
      return region.transferTo(this.javaChannel(), position);
   }

   private void adjustMaxBytesPerGatheringWrite(int attempted, int written, int oldMaxBytesPerGatheringWrite) {
      if (attempted == written) {
         if (attempted << 1 > oldMaxBytesPerGatheringWrite) {
            ((NioDomainSocketChannelConfig)this.config).setMaxBytesPerGatheringWrite(attempted << 1);
         }
      } else if (attempted > 4096 && written < attempted >>> 1) {
         ((NioDomainSocketChannelConfig)this.config).setMaxBytesPerGatheringWrite(attempted >>> 1);
      }

   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      SocketChannel ch = this.javaChannel();
      int writeSpinCount = this.config().getWriteSpinCount();

      while(!in.isEmpty()) {
         int maxBytesPerGatheringWrite = ((NioDomainSocketChannelConfig)this.config).getMaxBytesPerGatheringWrite();
         ByteBuffer[] nioBuffers = in.nioBuffers(1024, (long)maxBytesPerGatheringWrite);
         int nioBufferCnt = in.nioBufferCount();
         switch (nioBufferCnt) {
            case 0:
               writeSpinCount -= this.doWrite0(in);
               break;
            case 1:
               ByteBuffer buffer = nioBuffers[0];
               int attemptedBytes = buffer.remaining();
               int localWrittenBytes = ch.write(buffer);
               if (localWrittenBytes <= 0) {
                  this.incompleteWrite(true);
                  return;
               }

               this.adjustMaxBytesPerGatheringWrite(attemptedBytes, localWrittenBytes, maxBytesPerGatheringWrite);
               in.removeBytes((long)localWrittenBytes);
               --writeSpinCount;
               break;
            default:
               long attemptedBytes = in.nioBufferSize();
               long localWrittenBytes = ch.write(nioBuffers, 0, nioBufferCnt);
               if (localWrittenBytes <= 0L) {
                  this.incompleteWrite(true);
                  return;
               }

               this.adjustMaxBytesPerGatheringWrite((int)attemptedBytes, (int)localWrittenBytes, maxBytesPerGatheringWrite);
               in.removeBytes(localWrittenBytes);
               --writeSpinCount;
         }

         if (writeSpinCount <= 0) {
            this.incompleteWrite(writeSpinCount < 0);
            return;
         }
      }

      this.clearOpWrite();
   }

   protected AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
      return new NioSocketChannelUnsafe();
   }

   private final class NioSocketChannelUnsafe extends AbstractNioByteChannel.NioByteUnsafe {
      private NioSocketChannelUnsafe() {
      }
   }

   private final class NioDomainSocketChannelConfig extends DefaultChannelConfig implements DuplexChannelConfig {
      private volatile boolean allowHalfClosure;
      private volatile int maxBytesPerGatheringWrite;
      private final SocketChannel javaChannel;

      private NioDomainSocketChannelConfig(NioDomainSocketChannel channel, SocketChannel javaChannel) {
         super(channel);
         this.maxBytesPerGatheringWrite = Integer.MAX_VALUE;
         this.javaChannel = javaChannel;
         this.calculateMaxBytesPerGatheringWrite();
      }

      public boolean isAllowHalfClosure() {
         return this.allowHalfClosure;
      }

      public NioDomainSocketChannelConfig setAllowHalfClosure(boolean allowHalfClosure) {
         this.allowHalfClosure = allowHalfClosure;
         return this;
      }

      public Map getOptions() {
         List<ChannelOption<?>> options = new ArrayList();
         options.add(ChannelOption.SO_RCVBUF);
         options.add(ChannelOption.SO_SNDBUF);

         for(ChannelOption opt : NioChannelOption.getOptions(this.jdkChannel())) {
            options.add(opt);
         }

         return this.getOptions(super.getOptions(), (ChannelOption[])options.toArray(new ChannelOption[0]));
      }

      public Object getOption(ChannelOption option) {
         if (option == ChannelOption.SO_RCVBUF) {
            return this.getReceiveBufferSize();
         } else if (option == ChannelOption.SO_SNDBUF) {
            return this.getSendBufferSize();
         } else {
            return option instanceof NioChannelOption ? NioChannelOption.getOption(this.jdkChannel(), (NioChannelOption)option) : super.getOption(option);
         }
      }

      public boolean setOption(ChannelOption option, Object value) {
         if (option == ChannelOption.SO_RCVBUF) {
            this.validate(option, value);
            this.setReceiveBufferSize((Integer)value);
         } else {
            if (option != ChannelOption.SO_SNDBUF) {
               if (option instanceof NioChannelOption) {
                  return NioChannelOption.setOption(this.jdkChannel(), (NioChannelOption)option, value);
               }

               return super.setOption(option, value);
            }

            this.validate(option, value);
            this.setSendBufferSize((Integer)value);
         }

         return true;
      }

      @SuppressJava6Requirement(
         reason = "Usage guarded by java version check"
      )
      private int getReceiveBufferSize() {
         try {
            return (Integer)this.javaChannel.getOption(StandardSocketOptions.SO_RCVBUF);
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      @SuppressJava6Requirement(
         reason = "Usage guarded by java version check"
      )
      private NioDomainSocketChannelConfig setReceiveBufferSize(int receiveBufferSize) {
         try {
            this.javaChannel.setOption(StandardSocketOptions.SO_RCVBUF, receiveBufferSize);
            return this;
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      @SuppressJava6Requirement(
         reason = "Usage guarded by java version check"
      )
      private int getSendBufferSize() {
         try {
            return (Integer)this.javaChannel.getOption(StandardSocketOptions.SO_SNDBUF);
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      @SuppressJava6Requirement(
         reason = "Usage guarded by java version check"
      )
      private NioDomainSocketChannelConfig setSendBufferSize(int sendBufferSize) {
         try {
            this.javaChannel.setOption(StandardSocketOptions.SO_SNDBUF, sendBufferSize);
            return this;
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      public NioDomainSocketChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
         super.setConnectTimeoutMillis(connectTimeoutMillis);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public NioDomainSocketChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
         super.setMaxMessagesPerRead(maxMessagesPerRead);
         return this;
      }

      public NioDomainSocketChannelConfig setWriteSpinCount(int writeSpinCount) {
         super.setWriteSpinCount(writeSpinCount);
         return this;
      }

      public NioDomainSocketChannelConfig setAllocator(ByteBufAllocator allocator) {
         super.setAllocator(allocator);
         return this;
      }

      public NioDomainSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
         super.setRecvByteBufAllocator(allocator);
         return this;
      }

      public NioDomainSocketChannelConfig setAutoRead(boolean autoRead) {
         super.setAutoRead(autoRead);
         return this;
      }

      public NioDomainSocketChannelConfig setAutoClose(boolean autoClose) {
         super.setAutoClose(autoClose);
         return this;
      }

      public NioDomainSocketChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
         super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
         return this;
      }

      public NioDomainSocketChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
         super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
         return this;
      }

      public NioDomainSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
         super.setWriteBufferWaterMark(writeBufferWaterMark);
         return this;
      }

      public NioDomainSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
         super.setMessageSizeEstimator(estimator);
         return this;
      }

      protected void autoReadCleared() {
         NioDomainSocketChannel.this.clearReadPending();
      }

      void setMaxBytesPerGatheringWrite(int maxBytesPerGatheringWrite) {
         this.maxBytesPerGatheringWrite = maxBytesPerGatheringWrite;
      }

      int getMaxBytesPerGatheringWrite() {
         return this.maxBytesPerGatheringWrite;
      }

      private void calculateMaxBytesPerGatheringWrite() {
         int newSendBufferSize = this.getSendBufferSize() << 1;
         if (newSendBufferSize > 0) {
            this.setMaxBytesPerGatheringWrite(newSendBufferSize);
         }

      }

      private SocketChannel jdkChannel() {
         return this.javaChannel;
      }
   }
}
