package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.ServerChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public abstract class AbstractKQueueServerChannel extends AbstractKQueueChannel implements ServerChannel {
   private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);

   AbstractKQueueServerChannel(BsdSocket fd) {
      this(fd, isSoErrorZero(fd));
   }

   AbstractKQueueServerChannel(BsdSocket fd, boolean active) {
      super((Channel)null, fd, active);
   }

   public ChannelMetadata metadata() {
      return METADATA;
   }

   protected boolean isCompatible(EventLoop loop) {
      return loop instanceof KQueueEventLoop;
   }

   protected InetSocketAddress remoteAddress0() {
      return null;
   }

   protected AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
      return new KQueueServerSocketUnsafe();
   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      throw new UnsupportedOperationException();
   }

   protected Object filterOutboundMessage(Object msg) throws Exception {
      throw new UnsupportedOperationException();
   }

   abstract Channel newChildChannel(int var1, byte[] var2, int var3, int var4) throws Exception;

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      throw new UnsupportedOperationException();
   }

   final class KQueueServerSocketUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
      private final byte[] acceptedAddress = new byte[26];

      void readReady(KQueueRecvByteAllocatorHandle allocHandle) {
         assert AbstractKQueueServerChannel.this.eventLoop().inEventLoop();

         ChannelConfig config = AbstractKQueueServerChannel.this.config();
         if (AbstractKQueueServerChannel.this.shouldBreakReadReady(config)) {
            this.clearReadFilter0();
         } else {
            ChannelPipeline pipeline = AbstractKQueueServerChannel.this.pipeline();
            allocHandle.reset(config);
            allocHandle.attemptedBytesRead(1);
            this.readReadyBefore();
            Throwable exception = null;

            try {
               try {
                  do {
                     int acceptFd = AbstractKQueueServerChannel.this.socket.accept(this.acceptedAddress);
                     if (acceptFd == -1) {
                        allocHandle.lastBytesRead(-1);
                        break;
                     }

                     allocHandle.lastBytesRead(1);
                     allocHandle.incMessagesRead(1);
                     this.readPending = false;
                     pipeline.fireChannelRead(AbstractKQueueServerChannel.this.newChildChannel(acceptFd, this.acceptedAddress, 1, this.acceptedAddress[0]));
                  } while(allocHandle.continueReading());
               } catch (Throwable t) {
                  exception = t;
               }

               allocHandle.readComplete();
               pipeline.fireChannelReadComplete();
               if (exception != null) {
                  pipeline.fireExceptionCaught(exception);
               }
            } finally {
               this.readReadyFinally(config);
            }

         }
      }
   }
}
