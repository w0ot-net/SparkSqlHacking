package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.unix.IovArray;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executor;

public final class KQueueSocketChannel extends AbstractKQueueStreamChannel implements SocketChannel {
   private final KQueueSocketChannelConfig config = new KQueueSocketChannelConfig(this);

   public KQueueSocketChannel() {
      super((Channel)null, BsdSocket.newSocketStream(), false);
   }

   public KQueueSocketChannel(InternetProtocolFamily protocol) {
      super((Channel)null, BsdSocket.newSocketStream(protocol), false);
   }

   public KQueueSocketChannel(int fd) {
      super(new BsdSocket(fd));
   }

   KQueueSocketChannel(Channel parent, BsdSocket fd, InetSocketAddress remoteAddress) {
      super(parent, fd, remoteAddress);
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public KQueueSocketChannelConfig config() {
      return this.config;
   }

   public ServerSocketChannel parent() {
      return (ServerSocketChannel)super.parent();
   }

   protected boolean doConnect0(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (this.config.isTcpFastOpenConnect()) {
         ChannelOutboundBuffer outbound = this.unsafe().outboundBuffer();
         outbound.addFlush();
         Object curr;
         if ((curr = outbound.current()) instanceof ByteBuf) {
            ByteBuf initialData = (ByteBuf)curr;
            if (initialData.isReadable()) {
               IovArray iov = new IovArray(this.config.getAllocator().directBuffer());

               boolean var8;
               try {
                  iov.add(initialData, initialData.readerIndex(), initialData.readableBytes());
                  int bytesSent = this.socket.connectx((InetSocketAddress)localAddress, (InetSocketAddress)remoteAddress, iov, true);
                  this.writeFilter(true);
                  outbound.removeBytes((long)Math.abs(bytesSent));
                  var8 = bytesSent > 0;
               } finally {
                  iov.release();
               }

               return var8;
            }
         }
      }

      return super.doConnect0(remoteAddress, localAddress);
   }

   protected AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
      return new KQueueSocketChannelUnsafe();
   }

   private final class KQueueSocketChannelUnsafe extends AbstractKQueueStreamChannel.KQueueStreamUnsafe {
      private KQueueSocketChannelUnsafe() {
      }

      protected Executor prepareToClose() {
         try {
            if (KQueueSocketChannel.this.isOpen() && KQueueSocketChannel.this.config().getSoLinger() > 0) {
               ((KQueueEventLoop)KQueueSocketChannel.this.eventLoop()).remove(KQueueSocketChannel.this);
               return GlobalEventExecutor.INSTANCE;
            }
         } catch (Throwable var2) {
         }

         return null;
      }
   }
}
