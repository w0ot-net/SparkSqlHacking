package io.netty.channel.kqueue;

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

public final class KQueueDomainSocketChannel extends AbstractKQueueStreamChannel implements DomainSocketChannel {
   private final KQueueDomainSocketChannelConfig config;
   private volatile DomainSocketAddress local;
   private volatile DomainSocketAddress remote;

   public KQueueDomainSocketChannel() {
      super((Channel)null, BsdSocket.newSocketDomain(), false);
      this.config = new KQueueDomainSocketChannelConfig(this);
   }

   public KQueueDomainSocketChannel(int fd) {
      this((Channel)null, new BsdSocket(fd));
   }

   KQueueDomainSocketChannel(Channel parent, BsdSocket fd) {
      super(parent, fd, true);
      this.config = new KQueueDomainSocketChannelConfig(this);
      this.local = fd.localDomainSocketAddress();
      this.remote = fd.remoteDomainSocketAddress();
   }

   protected AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
      return new KQueueDomainUnsafe();
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

   public KQueueDomainSocketChannelConfig config() {
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

   private final class KQueueDomainUnsafe extends AbstractKQueueStreamChannel.KQueueStreamUnsafe {
      private KQueueDomainUnsafe() {
      }

      void readReady(KQueueRecvByteAllocatorHandle allocHandle) {
         switch (KQueueDomainSocketChannel.this.config().getReadMode()) {
            case BYTES:
               super.readReady(allocHandle);
               break;
            case FILE_DESCRIPTORS:
               this.readReadyFd();
               break;
            default:
               throw new Error();
         }

      }

      private void readReadyFd() {
         if (KQueueDomainSocketChannel.this.socket.isInputShutdown()) {
            super.clearReadFilter0();
         } else {
            ChannelConfig config = KQueueDomainSocketChannel.this.config();
            KQueueRecvByteAllocatorHandle allocHandle = this.recvBufAllocHandle();
            ChannelPipeline pipeline = KQueueDomainSocketChannel.this.pipeline();
            allocHandle.reset(config);
            this.readReadyBefore();

            try {
               while(true) {
                  int recvFd = KQueueDomainSocketChannel.this.socket.recvFd();
                  switch (recvFd) {
                     case -1:
                        allocHandle.lastBytesRead(-1);
                        this.close(this.voidPromise());
                        return;
                     case 0:
                        allocHandle.lastBytesRead(0);
                        break;
                     default:
                        allocHandle.lastBytesRead(1);
                        allocHandle.incMessagesRead(1);
                        this.readPending = false;
                        pipeline.fireChannelRead(new FileDescriptor(recvFd));
                        if (allocHandle.continueReading()) {
                           continue;
                        }
                  }

                  allocHandle.readComplete();
                  pipeline.fireChannelReadComplete();
                  return;
               }
            } catch (Throwable t) {
               allocHandle.readComplete();
               pipeline.fireChannelReadComplete();
               pipeline.fireExceptionCaught(t);
            } finally {
               this.readReadyFinally(config);
            }
         }
      }
   }
}
