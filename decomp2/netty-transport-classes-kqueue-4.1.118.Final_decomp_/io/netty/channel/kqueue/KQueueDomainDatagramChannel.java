package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.unix.DomainDatagramChannel;
import io.netty.channel.unix.DomainDatagramChannelConfig;
import io.netty.channel.unix.DomainDatagramPacket;
import io.netty.channel.unix.DomainDatagramSocketAddress;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.IovArray;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.CharsetUtil;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public final class KQueueDomainDatagramChannel extends AbstractKQueueDatagramChannel implements DomainDatagramChannel {
   private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(DomainDatagramPacket.class) + ", " + StringUtil.simpleClassName(AddressedEnvelope.class) + '<' + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(DomainSocketAddress.class) + ">, " + StringUtil.simpleClassName(ByteBuf.class) + ')';
   private volatile boolean connected;
   private volatile DomainSocketAddress local;
   private volatile DomainSocketAddress remote;
   private final KQueueDomainDatagramChannelConfig config;

   public KQueueDomainDatagramChannel() {
      this(BsdSocket.newSocketDomainDgram(), false);
   }

   public KQueueDomainDatagramChannel(int fd) {
      this(new BsdSocket(fd), true);
   }

   private KQueueDomainDatagramChannel(BsdSocket socket, boolean active) {
      super((Channel)null, socket, active);
      this.config = new KQueueDomainDatagramChannelConfig(this);
   }

   public KQueueDomainDatagramChannelConfig config() {
      return this.config;
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      super.doBind(localAddress);
      this.local = (DomainSocketAddress)localAddress;
      this.active = true;
   }

   protected void doClose() throws Exception {
      super.doClose();
      this.connected = this.active = false;
      this.local = null;
      this.remote = null;
   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (super.doConnect(remoteAddress, localAddress)) {
         if (localAddress != null) {
            this.local = (DomainSocketAddress)localAddress;
         }

         this.remote = (DomainSocketAddress)remoteAddress;
         this.connected = true;
         return true;
      } else {
         return false;
      }
   }

   protected void doDisconnect() throws Exception {
      this.doClose();
   }

   protected boolean doWriteMessage(Object msg) throws Exception {
      ByteBuf data;
      DomainSocketAddress remoteAddress;
      if (msg instanceof AddressedEnvelope) {
         AddressedEnvelope<ByteBuf, DomainSocketAddress> envelope = (AddressedEnvelope)msg;
         data = (ByteBuf)envelope.content();
         remoteAddress = (DomainSocketAddress)envelope.recipient();
      } else {
         data = (ByteBuf)msg;
         remoteAddress = null;
      }

      int dataLen = data.readableBytes();
      if (dataLen == 0) {
         return true;
      } else {
         long writtenBytes;
         if (data.hasMemoryAddress()) {
            long memoryAddress = data.memoryAddress();
            if (remoteAddress == null) {
               writtenBytes = (long)this.socket.writeAddress(memoryAddress, data.readerIndex(), data.writerIndex());
            } else {
               writtenBytes = (long)this.socket.sendToAddressDomainSocket(memoryAddress, data.readerIndex(), data.writerIndex(), remoteAddress.path().getBytes(CharsetUtil.UTF_8));
            }
         } else if (data.nioBufferCount() > 1) {
            IovArray array = ((KQueueEventLoop)this.eventLoop()).cleanArray();
            array.add(data, data.readerIndex(), data.readableBytes());
            int cnt = array.count();

            assert cnt != 0;

            if (remoteAddress == null) {
               writtenBytes = this.socket.writevAddresses(array.memoryAddress(0), cnt);
            } else {
               writtenBytes = (long)this.socket.sendToAddressesDomainSocket(array.memoryAddress(0), cnt, remoteAddress.path().getBytes(CharsetUtil.UTF_8));
            }
         } else {
            ByteBuffer nioData = data.internalNioBuffer(data.readerIndex(), data.readableBytes());
            if (remoteAddress == null) {
               writtenBytes = (long)this.socket.write(nioData, nioData.position(), nioData.limit());
            } else {
               writtenBytes = (long)this.socket.sendToDomainSocket(nioData, nioData.position(), nioData.limit(), remoteAddress.path().getBytes(CharsetUtil.UTF_8));
            }
         }

         return writtenBytes > 0L;
      }
   }

   protected Object filterOutboundMessage(Object msg) {
      if (msg instanceof DomainDatagramPacket) {
         DomainDatagramPacket packet = (DomainDatagramPacket)msg;
         ByteBuf content = (ByteBuf)packet.content();
         return UnixChannelUtil.isBufferCopyNeededForWrite(content) ? new DomainDatagramPacket(this.newDirectBuffer(packet, content), (DomainSocketAddress)packet.recipient()) : msg;
      } else if (msg instanceof ByteBuf) {
         ByteBuf buf = (ByteBuf)msg;
         return UnixChannelUtil.isBufferCopyNeededForWrite(buf) ? this.newDirectBuffer(buf) : buf;
      } else {
         if (msg instanceof AddressedEnvelope) {
            AddressedEnvelope<Object, SocketAddress> e = (AddressedEnvelope)msg;
            if (e.content() instanceof ByteBuf && (e.recipient() == null || e.recipient() instanceof DomainSocketAddress)) {
               ByteBuf content = (ByteBuf)e.content();
               return UnixChannelUtil.isBufferCopyNeededForWrite(content) ? new DefaultAddressedEnvelope(this.newDirectBuffer(e, content), (DomainSocketAddress)e.recipient()) : e;
            }
         }

         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(msg) + EXPECTED_TYPES);
      }
   }

   public boolean isActive() {
      return this.socket.isOpen() && (this.config.getActiveOnOpen() && this.isRegistered() || this.active);
   }

   public boolean isConnected() {
      return this.connected;
   }

   public DomainSocketAddress localAddress() {
      return (DomainSocketAddress)super.localAddress();
   }

   protected DomainSocketAddress localAddress0() {
      return this.local;
   }

   protected AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
      return new KQueueDomainDatagramChannelUnsafe();
   }

   public PeerCredentials peerCredentials() throws IOException {
      return this.socket.getPeerCredentials();
   }

   public DomainSocketAddress remoteAddress() {
      return (DomainSocketAddress)super.remoteAddress();
   }

   protected DomainSocketAddress remoteAddress0() {
      return this.remote;
   }

   final class KQueueDomainDatagramChannelUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
      void readReady(KQueueRecvByteAllocatorHandle allocHandle) {
         assert KQueueDomainDatagramChannel.this.eventLoop().inEventLoop();

         DomainDatagramChannelConfig config = KQueueDomainDatagramChannel.this.config();
         if (KQueueDomainDatagramChannel.this.shouldBreakReadReady(config)) {
            this.clearReadFilter0();
         } else {
            ChannelPipeline pipeline = KQueueDomainDatagramChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            allocHandle.reset(config);
            this.readReadyBefore();
            Throwable exception = null;

            try {
               ByteBuf byteBuf = null;

               try {
                  boolean connected = KQueueDomainDatagramChannel.this.isConnected();

                  do {
                     byteBuf = allocHandle.allocate(allocator);
                     allocHandle.attemptedBytesRead(byteBuf.writableBytes());
                     DomainDatagramPacket packet;
                     if (connected) {
                        allocHandle.lastBytesRead(KQueueDomainDatagramChannel.this.doReadBytes(byteBuf));
                        if (allocHandle.lastBytesRead() <= 0) {
                           byteBuf.release();
                           break;
                        }

                        packet = new DomainDatagramPacket(byteBuf, (DomainSocketAddress)this.localAddress(), (DomainSocketAddress)this.remoteAddress());
                     } else {
                        DomainDatagramSocketAddress remoteAddress;
                        if (byteBuf.hasMemoryAddress()) {
                           remoteAddress = KQueueDomainDatagramChannel.this.socket.recvFromAddressDomainSocket(byteBuf.memoryAddress(), byteBuf.writerIndex(), byteBuf.capacity());
                        } else {
                           ByteBuffer nioData = byteBuf.internalNioBuffer(byteBuf.writerIndex(), byteBuf.writableBytes());
                           remoteAddress = KQueueDomainDatagramChannel.this.socket.recvFromDomainSocket(nioData, nioData.position(), nioData.limit());
                        }

                        if (remoteAddress == null) {
                           allocHandle.lastBytesRead(-1);
                           byteBuf.release();
                           break;
                        }

                        DomainSocketAddress localAddress = remoteAddress.localAddress();
                        if (localAddress == null) {
                           localAddress = (DomainSocketAddress)this.localAddress();
                        }

                        allocHandle.lastBytesRead(remoteAddress.receivedAmount());
                        byteBuf.writerIndex(byteBuf.writerIndex() + allocHandle.lastBytesRead());
                        packet = new DomainDatagramPacket(byteBuf, localAddress, remoteAddress);
                     }

                     allocHandle.incMessagesRead(1);
                     this.readPending = false;
                     pipeline.fireChannelRead(packet);
                     ByteBuf var17 = null;
                  } while(allocHandle.continueReading(UncheckedBooleanSupplier.TRUE_SUPPLIER));
               } catch (Throwable t) {
                  if (byteBuf != null) {
                     byteBuf.release();
                  }

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
