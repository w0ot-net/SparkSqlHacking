package io.netty.channel.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.socket.DefaultSocketChannelConfig;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.Executor;

public class NioSocketChannel extends AbstractNioByteChannel implements SocketChannel {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(NioSocketChannel.class);
   private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
   private static final Method OPEN_SOCKET_CHANNEL_WITH_FAMILY = SelectorProviderUtil.findOpenMethod("openSocketChannel");
   private final SocketChannelConfig config;

   private static java.nio.channels.SocketChannel newChannel(SelectorProvider provider, InternetProtocolFamily family) {
      try {
         java.nio.channels.SocketChannel channel = (java.nio.channels.SocketChannel)SelectorProviderUtil.newChannel(OPEN_SOCKET_CHANNEL_WITH_FAMILY, provider, family);
         return channel == null ? provider.openSocketChannel() : channel;
      } catch (IOException e) {
         throw new ChannelException("Failed to open a socket.", e);
      }
   }

   public NioSocketChannel() {
      this(DEFAULT_SELECTOR_PROVIDER);
   }

   public NioSocketChannel(SelectorProvider provider) {
      this((SelectorProvider)provider, (InternetProtocolFamily)null);
   }

   public NioSocketChannel(SelectorProvider provider, InternetProtocolFamily family) {
      this(newChannel(provider, family));
   }

   public NioSocketChannel(java.nio.channels.SocketChannel socket) {
      this((Channel)null, (java.nio.channels.SocketChannel)socket);
   }

   public NioSocketChannel(Channel parent, java.nio.channels.SocketChannel socket) {
      super(parent, socket);
      this.config = new NioSocketChannelConfig(this, socket.socket());
   }

   public ServerSocketChannel parent() {
      return (ServerSocketChannel)super.parent();
   }

   public SocketChannelConfig config() {
      return this.config;
   }

   protected java.nio.channels.SocketChannel javaChannel() {
      return (java.nio.channels.SocketChannel)super.javaChannel();
   }

   public boolean isActive() {
      java.nio.channels.SocketChannel ch = this.javaChannel();
      return ch.isOpen() && ch.isConnected();
   }

   public boolean isOutputShutdown() {
      return this.javaChannel().socket().isOutputShutdown() || !this.isActive();
   }

   public boolean isInputShutdown() {
      return this.javaChannel().socket().isInputShutdown() || !this.isActive();
   }

   public boolean isShutdown() {
      Socket socket = this.javaChannel().socket();
      return socket.isInputShutdown() && socket.isOutputShutdown() || !this.isActive();
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   protected final void doShutdownOutput() throws Exception {
      if (PlatformDependent.javaVersion() >= 7) {
         this.javaChannel().shutdownOutput();
      } else {
         this.javaChannel().socket().shutdownOutput();
      }

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
               ((AbstractChannel.AbstractUnsafe)NioSocketChannel.this.unsafe()).shutdownOutput(promise);
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
               NioSocketChannel.this.shutdownInput0(promise);
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
               NioSocketChannel.this.shutdownOutputDone(shutdownOutputFuture, promise);
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
               NioSocketChannel.shutdownDone(shutdownOutputFuture, shutdownInputFuture, promise);
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
      if (PlatformDependent.javaVersion() >= 7) {
         this.javaChannel().shutdownInput();
      } else {
         this.javaChannel().socket().shutdownInput();
      }

   }

   protected SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   protected SocketAddress remoteAddress0() {
      return this.javaChannel().socket().getRemoteSocketAddress();
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      this.doBind0(localAddress);
   }

   private void doBind0(SocketAddress localAddress) throws Exception {
      if (PlatformDependent.javaVersion() >= 7) {
         SocketUtils.bind(this.javaChannel(), localAddress);
      } else {
         SocketUtils.bind(this.javaChannel().socket(), localAddress);
      }

   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (localAddress != null) {
         this.doBind0(localAddress);
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
      super.doClose();
      this.javaChannel().close();
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
            ((NioSocketChannelConfig)this.config).setMaxBytesPerGatheringWrite(attempted << 1);
         }
      } else if (attempted > 4096 && written < attempted >>> 1) {
         ((NioSocketChannelConfig)this.config).setMaxBytesPerGatheringWrite(attempted >>> 1);
      }

   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      java.nio.channels.SocketChannel ch = this.javaChannel();
      int writeSpinCount = this.config().getWriteSpinCount();

      while(!in.isEmpty()) {
         int maxBytesPerGatheringWrite = ((NioSocketChannelConfig)this.config).getMaxBytesPerGatheringWrite();
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

      protected Executor prepareToClose() {
         try {
            if (NioSocketChannel.this.javaChannel().isOpen() && NioSocketChannel.this.config().getSoLinger() > 0) {
               NioSocketChannel.this.doDeregister();
               return GlobalEventExecutor.INSTANCE;
            }
         } catch (Throwable var2) {
         }

         return null;
      }
   }

   private final class NioSocketChannelConfig extends DefaultSocketChannelConfig {
      private volatile int maxBytesPerGatheringWrite;

      private NioSocketChannelConfig(NioSocketChannel channel, Socket javaSocket) {
         super(channel, javaSocket);
         this.maxBytesPerGatheringWrite = Integer.MAX_VALUE;
         this.calculateMaxBytesPerGatheringWrite();
      }

      protected void autoReadCleared() {
         NioSocketChannel.this.clearReadPending();
      }

      public NioSocketChannelConfig setSendBufferSize(int sendBufferSize) {
         super.setSendBufferSize(sendBufferSize);
         this.calculateMaxBytesPerGatheringWrite();
         return this;
      }

      public boolean setOption(ChannelOption option, Object value) {
         return PlatformDependent.javaVersion() >= 7 && option instanceof NioChannelOption ? NioChannelOption.setOption(this.jdkChannel(), (NioChannelOption)option, value) : super.setOption(option, value);
      }

      public Object getOption(ChannelOption option) {
         return PlatformDependent.javaVersion() >= 7 && option instanceof NioChannelOption ? NioChannelOption.getOption(this.jdkChannel(), (NioChannelOption)option) : super.getOption(option);
      }

      public Map getOptions() {
         return PlatformDependent.javaVersion() >= 7 ? this.getOptions(super.getOptions(), NioChannelOption.getOptions(this.jdkChannel())) : super.getOptions();
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

      private java.nio.channels.SocketChannel jdkChannel() {
         return ((NioSocketChannel)this.channel).javaChannel();
      }
   }
}
