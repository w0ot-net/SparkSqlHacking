package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.DomainSocketChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.PeerCredentials;
import java.io.IOException;
import java.net.SocketAddress;

public final class EpollDomainSocketChannel extends AbstractEpollStreamChannel implements DomainSocketChannel {
   private final EpollDomainSocketChannelConfig config;
   private volatile DomainSocketAddress local;
   private volatile DomainSocketAddress remote;

   public EpollDomainSocketChannel() {
      super(LinuxSocket.newSocketDomain(), false);
      this.config = new EpollDomainSocketChannelConfig(this);
   }

   EpollDomainSocketChannel(Channel parent, FileDescriptor fd) {
      this(parent, new LinuxSocket(fd.intValue()));
   }

   public EpollDomainSocketChannel(int fd) {
      super(fd);
      this.config = new EpollDomainSocketChannelConfig(this);
   }

   public EpollDomainSocketChannel(Channel parent, LinuxSocket fd) {
      super(parent, fd);
      this.config = new EpollDomainSocketChannelConfig(this);
      this.local = fd.localDomainSocketAddress();
      this.remote = fd.remoteDomainSocketAddress();
   }

   public EpollDomainSocketChannel(int fd, boolean active) {
      super(new LinuxSocket(fd), active);
      this.config = new EpollDomainSocketChannelConfig(this);
   }

   protected AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
      return new EpollDomainUnsafe();
   }

   protected DomainSocketAddress localAddress0() {
      return this.local;
   }

   protected DomainSocketAddress remoteAddress0() {
      return this.remote;
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      this.socket.bind(localAddress);
      this.local = (DomainSocketAddress)localAddress;
   }

   public EpollDomainSocketChannelConfig config() {
      return this.config;
   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (super.doConnect(remoteAddress, localAddress)) {
         this.local = localAddress != null ? (DomainSocketAddress)localAddress : this.socket.localDomainSocketAddress();
         this.remote = (DomainSocketAddress)remoteAddress;
         return true;
      } else {
         return false;
      }
   }

   public DomainSocketAddress remoteAddress() {
      return (DomainSocketAddress)super.remoteAddress();
   }

   public DomainSocketAddress localAddress() {
      return (DomainSocketAddress)super.localAddress();
   }

   protected int doWriteSingle(ChannelOutboundBuffer in) throws Exception {
      Object msg = in.current();
      if (msg instanceof FileDescriptor && this.socket.sendFd(((FileDescriptor)msg).intValue()) > 0) {
         in.remove();
         return 1;
      } else {
         return super.doWriteSingle(in);
      }
   }

   protected Object filterOutboundMessage(Object msg) {
      return msg instanceof FileDescriptor ? msg : super.filterOutboundMessage(msg);
   }

   public PeerCredentials peerCredentials() throws IOException {
      return this.socket.getPeerCredentials();
   }

   private final class EpollDomainUnsafe extends AbstractEpollStreamChannel.EpollStreamUnsafe {
      private EpollDomainUnsafe() {
      }

      void epollInReady() {
         switch (EpollDomainSocketChannel.this.config().getReadMode()) {
            case BYTES:
               super.epollInReady();
               break;
            case FILE_DESCRIPTORS:
               this.epollInReadFd();
               break;
            default:
               throw new Error();
         }

      }

      private void epollInReadFd() {
         if (EpollDomainSocketChannel.this.socket.isInputShutdown()) {
            this.clearEpollIn0();
         } else {
            ChannelConfig config = EpollDomainSocketChannel.this.config();
            EpollRecvByteAllocatorHandle allocHandle = this.recvBufAllocHandle();
            allocHandle.edgeTriggered(EpollDomainSocketChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = EpollDomainSocketChannel.this.pipeline();
            allocHandle.reset(config);
            this.epollInBefore();

            try {
               while(true) {
                  allocHandle.lastBytesRead(EpollDomainSocketChannel.this.socket.recvFd());
                  switch (allocHandle.lastBytesRead()) {
                     case -1:
                        this.close(this.voidPromise());
                        return;
                     default:
                        allocHandle.incMessagesRead(1);
                        this.readPending = false;
                        pipeline.fireChannelRead(new FileDescriptor(allocHandle.lastBytesRead()));
                        if (allocHandle.continueReading()) {
                           break;
                        }
                     case 0:
                        allocHandle.readComplete();
                        pipeline.fireChannelReadComplete();
                        return;
                  }
               }
            } catch (Throwable t) {
               allocHandle.readComplete();
               pipeline.fireChannelReadComplete();
               pipeline.fireExceptionCaught(t);
            } finally {
               this.epollInFinally(config);
            }
         }
      }
   }
}
