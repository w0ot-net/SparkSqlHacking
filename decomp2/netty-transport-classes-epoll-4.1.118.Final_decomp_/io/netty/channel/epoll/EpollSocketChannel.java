package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executor;

public final class EpollSocketChannel extends AbstractEpollStreamChannel implements SocketChannel {
   private final EpollSocketChannelConfig config = new EpollSocketChannelConfig(this);
   private volatile Collection tcpMd5SigAddresses = Collections.emptyList();

   public EpollSocketChannel() {
      super(LinuxSocket.newSocketStream(), false);
   }

   public EpollSocketChannel(InternetProtocolFamily protocol) {
      super(LinuxSocket.newSocketStream(protocol), false);
   }

   public EpollSocketChannel(int fd) {
      super(fd);
   }

   EpollSocketChannel(LinuxSocket fd, boolean active) {
      super(fd, active);
   }

   EpollSocketChannel(Channel parent, LinuxSocket fd, InetSocketAddress remoteAddress) {
      super(parent, fd, remoteAddress);
      if (parent instanceof EpollServerSocketChannel) {
         this.tcpMd5SigAddresses = ((EpollServerSocketChannel)parent).tcpMd5SigAddresses();
      }

   }

   public EpollTcpInfo tcpInfo() {
      return this.tcpInfo(new EpollTcpInfo());
   }

   public EpollTcpInfo tcpInfo(EpollTcpInfo info) {
      try {
         this.socket.getTcpInfo(info);
         return info;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public EpollSocketChannelConfig config() {
      return this.config;
   }

   public ServerSocketChannel parent() {
      return (ServerSocketChannel)super.parent();
   }

   protected AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
      return new EpollSocketChannelUnsafe();
   }

   boolean doConnect0(SocketAddress remote) throws Exception {
      if (Native.IS_SUPPORTING_TCP_FASTOPEN_CLIENT && this.config.isTcpFastOpenConnect()) {
         ChannelOutboundBuffer outbound = this.unsafe().outboundBuffer();
         outbound.addFlush();
         Object curr;
         if ((curr = outbound.current()) instanceof ByteBuf) {
            ByteBuf initialData = (ByteBuf)curr;
            long localFlushedAmount = this.doWriteOrSendBytes(initialData, (InetSocketAddress)remote, true);
            if (localFlushedAmount > 0L) {
               outbound.removeBytes(localFlushedAmount);
               return true;
            }
         }
      }

      return super.doConnect0(remote);
   }

   void setTcpMd5Sig(Map keys) throws IOException {
      synchronized(this) {
         this.tcpMd5SigAddresses = TcpMd5Util.newTcpMd5Sigs(this, this.tcpMd5SigAddresses, keys);
      }
   }

   private final class EpollSocketChannelUnsafe extends AbstractEpollStreamChannel.EpollStreamUnsafe {
      private EpollSocketChannelUnsafe() {
      }

      protected Executor prepareToClose() {
         try {
            if (EpollSocketChannel.this.isOpen() && EpollSocketChannel.this.config().getSoLinger() > 0) {
               ((EpollEventLoop)EpollSocketChannel.this.eventLoop()).remove(EpollSocketChannel.this);
               return GlobalEventExecutor.INSTANCE;
            }
         } catch (Throwable var2) {
         }

         return null;
      }
   }
}
